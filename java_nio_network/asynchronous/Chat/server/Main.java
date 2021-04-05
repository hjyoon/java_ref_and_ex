import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.text.*;
import java.net.*;
import java.net.http.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class Main {
    public static void main(String args[]) throws Exception {
        ServerExample server = new ServerExample();
        server.startServer();
    }
}

class ServerExample {
    AsynchronousChannelGroup acg;
    AsynchronousServerSocketChannel assc;

    // 연결된 클라이언트를 저장, 스레드에 안전한 Vector를 사용
    List<Client> connections = new Vector<Client>();

    void startServer() {
        try {
            //CPU 코어 수만큼 스레드를 관리하는 스레드풀 생성
            acg = AsynchronousChannelGroup.withFixedThreadPool(
                    Runtime.getRuntime().availableProcessors(),
                    Executors.defaultThreadFactory()
                );

            assc = AsynchronousServerSocketChannel.open(acg);

            //5001번 포트에서 클라이언트의 연결을 수락
            assc.bind(new InetSocketAddress(5001));
        }
        // 5001포트가 이미 다른 곳에서 사용 중이면 예외발생, 이 경우 비동기서버소켓채널이 열려있는지 확인하고 stopServer() 호출
        catch(Exception e) {
            e.printStackTrace();
            if(assc.isOpen()) {
                stopServer();
            }
            return;
        }

        System.out.println("[서버 시작]");

        // accept(A attachment, new CompletionHandler<AsynchronousSocketChannel, ? super A> handler)
        assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
                try {
                    String message = "[연결 수락: " + socketChannel.getRemoteAddress()  + ": " + Thread.currentThread().getName() + "]";
                    System.out.println(message);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                Client client = new Client(socketChannel, connections);
                connections.add(client);    // 클라이언트 객체 저장
                
                System.out.println("[연결 개수: " + connections.size() + "]");

                // * accept()를 반복 호출하는 무한 루프가 없고, 대신에 completed()메소드 끝에 accept()를
                // 재호출해서 반복적으로 클라이언트 연결 수락 작업을 수행.
                assc.accept(null, this); //accept() 메서드 호출
            }

            @Override
            public void failed(Throwable exc, Void attachment) { // 실패할 경우
                if(assc.isOpen()) {
                    stopServer();
                }
            }
        });
    }

    void stopServer() {
        try {
            connections.clear();    // connections 컬렉션에 저장되어 있는 모든 Client 제거
            if(acg != null && !acg.isShutdown()) {
                acg.shutdownNow();  // 비동기 채널 그룹에 포함된 모든 비동기 채널 닫음.  // 채널 자동 close
                //assc.close();
            }

            System.out.println("[서버 멈춤]");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Client {
    AsynchronousSocketChannel asc;
    List<Client> connections;

    Client(AsynchronousSocketChannel socketChannel, List<Client> connections) {
        this.asc = socketChannel;
        this.connections = connections;
        receive();
    }

    void receive() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);  // JVM 힙 메모리에 넌다이렉트 버퍼를 생성

        // read(ByteBuffer dst, A attachment, new CompletionHandler<Integer, ? super A> handler)
        asc.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                try {
                    String message = "[요청 처리: " + asc.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

                    System.out.println(message);

                    attachment.flip();  // flip() 메소드를 호출하면 limit을 현재 position 인덱스로 설정하고, position 을 0번 인덱스로 설정
                    Charset charset = Charset.forName("utf-8");
                    String data = charset.decode(attachment).toString(); // .decode() : ByteBuffer -> CharBuffer

                    // 모든 클라이언트에게 브로드캐스팅
                    for(Client client : connections) {
                        // 보낸사람 본인에게는 전송하지 않기 위함 (에코현상방지)
                        if(asc == client.asc) {
                            continue;
                        }
                        client.send(data);
                    }

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    asc.read(byteBuffer, byteBuffer, this); // 다시 데이터 읽기
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

            // 읽기 작업 실패시 콜백되는 failed() 메서드 재정의
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    String message = "[클라이언트 통신 안됨: " + asc.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

                    System.out.println(message);

                    connections.remove(Client.this);
                    asc.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void send(String data) {
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        // write(ByteBuffer src, A attachment, new CompletionHandler<Integer, ? super A> handler);
        asc.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {

            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                try {
                    String message = "[클라이언트 통신 안됨: " + asc.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

                    System.out.println(message);

                    connections.remove(Client.this);
                    asc.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}