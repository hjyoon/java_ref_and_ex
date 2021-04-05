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
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ClientExample client = new ClientExample();
        client.startClient();
        while(true) {
            //st = new StringTokenizer(br.readLine());
            //String msg = st.nextToken();
            //System.out.print("You> ");
            String msg = br.readLine();
            client.send(msg+"\0");
        }
    }
}

class ClientExample {
    AsynchronousChannelGroup acg;
    AsynchronousSocketChannel asc;

    void startClient() {
        try {
            // CPU 코어 수만큼 스레드를 관리하는 스레드풀 생성
            acg = AsynchronousChannelGroup.withFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                Executors.defaultThreadFactory()
            );


            asc = AsynchronousSocketChannel.open(acg);

            // connect(SocketAddress remote, A attachment, new CompletionHandler<Void, ? super A> handler)
            asc.connect(new InetSocketAddress("localhost", 5001), null, new CompletionHandler<Void, Void>() {
                @Override
                public void completed(Void result, Void attachment) {
                    try {
                        System.out.println("[연결 완료: "  + asc.getRemoteAddress() + "]");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    receive(); // 서버에서 보낸 데이터 받기
                }

                @Override
                public void failed(Throwable e, Void attachment) {
                    //Platform.runLater(()->displayText("[서버 통신 안됨]"));
                    System.out.println("[서버 통신 안됨]");

                    if(asc.isOpen()) {
                        stopClient();
                    }
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopClient() {
        try {
            System.out.println("[연결 끊음]");

            if(acg != null && !acg.isShutdown()) {
                acg.shutdownNow(); // 비동기 채널 그룹에 포함된 모든 비동기 채널 닫음.  // 채널 자동 close
                //asc.close();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void receive() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // read(ByteBuffer dst, A attachment, new CompletionHandler<Integer, ? super A> handler)
        asc.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                try {
                    attachment.flip();
                    Charset charset = Charset.forName("utf-8");
                    String data = charset.decode(attachment).toString();

                    System.out.println("[받기 완료] "  + data);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    asc.read(byteBuffer, byteBuffer, this);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("[서버 통신 안됨]");

                stopClient();
            }
        });
    }

    void send(String data) {
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);   // .encode() : String -> ByteBuffer

        // write(ByteBuffer src, A attachment, new CompletionHandler<Integer, ? super A> handler);
        asc.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                //System.out.println("[보내기 완료]");
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("[서버 통신 안됨]");

                stopClient();
            }
        });
    }
}