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
    Selector selector; // 넌블로킹의 핵심인 Selector 필드 선언
    ServerSocketChannel serverSocketChannel; // 클라이언트 연결 수락하는 ServerSocketChannel 필드 선언
    List<Client> connections = new Vector<Client>(); // 연결된 클라이언트를 저장하는 List<Client>타입의 connections 필드 선언하고 스레드에 안전한 Vector로 초기화

    void startServer() {
        try {
            selector = Selector.open(); // 셀렉터 생성
            serverSocketChannel = ServerSocketChannel.open(); // 생성
            serverSocketChannel.configureBlocking(false); // 넌블로킹 설정
            serverSocketChannel.bind(new InetSocketAddress(5001)); // 포트에 바인딩
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //셀럭터를 등록, 작업 유형을 OP_ACCEPT로 지정
        }
        catch (Exception e) {
            if(serverSocketChannel.isOpen()) {
                stopServer();
            }
            return; // 예외발생시 서버소켓채널이 열려있으면 stop() 호출
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        int keyCount = selector.select(); // 작업 처리가 준비된 채널이 있을 때까지 대기
                        if(keyCount == 0) {
                            continue;
                        }
                        Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 작업 처리 준비가 된 키를 얻고 Set 컬렉션으로 리턴.
                        Iterator<SelectionKey> iterator = selectedKeys.iterator();
                        while(iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            if(selectionKey.isAcceptable()) { // 연결 수락 작업일 경우
                                accept(selectionKey);
                            }
                            else if(selectionKey.isReadable()) { // 읽기 작업일 경우
                                Client client = (Client)selectionKey.attachment();
                                client.receive(selectionKey);
                            }
                            else if(selectionKey.isWritable()) { // 쓰기 작업일 경우
                                Client client = (Client)selectionKey.attachment();
                                client.send(selectionKey);
                            }

                            iterator.remove(); // 선택된 키셋에서 처리 완료된 SelectionKey를 제거
                        }
                    }
                    catch (Exception e) {
                        if(serverSocketChannel.isOpen()) {
                            stopServer();
                        }
                        break;
                    }
                }
            }
        };
        thread.start(); // 스레드 시작
        // Platform.runLater(()->{
        //     displayText("[서버 시작]");
        //     btnStartStop.setText("stop");
        // });
        System.out.println("[서버 시작]");
    }

    void stopServer() {
        try {
            Iterator<Client> iterator = connections.iterator();
            while(iterator.hasNext()) {
                Client client = iterator.next();
                client.socketChannel.close();
                iterator.remove();
            }
            if(serverSocketChannel != null && serverSocketChannel.isOpen()) { 
                serverSocketChannel.close(); 
            }
            if(selector != null && selector.isOpen()) {
                selector.close();
            }
            // Platform.runLater(()->{
            //     displayText("[서버 멈춤]");
            //     btnStartStop.setText("start");   
            // });
            System.out.println("[서버 멈춤]");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void accept(SelectionKey selectionKey) {
        try {
            // SelectionKey로부터 ServerSocketChannel을 얻음.
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();

            // ServerSocketChannel의 accept()를 호출하면, SocketChannel리턴.
            SocketChannel socketChannel = serverSocketChannel.accept();

            String message = "[연결 수락: " + socketChannel.getRemoteAddress()  + ": " + Thread.currentThread().getName() + "]";

            //Platform.runLater(()->displayText(message));
            System.out.println(message);

            Client client = new Client(selector, socketChannel, connections); // Client생성하고 connections컬렉션에 추가
            connections.add(client);

            //Platform.runLater(()->displayText("[연결 개수: " + connections.size() + "]"));
            System.out.println("[연결 개수: " + connections.size() + "]");
        }
        catch(Exception e) {
            if(serverSocketChannel.isOpen()) {
                stopServer();
            }
        }
    }

}

class Client {
    Selector selector;
    SocketChannel socketChannel;
    List<Client> connections;

    String sendData; // 클라이언트에 보낼 데이터를 저장할 필드

    Client(Selector selector, SocketChannel socketChannel, List<Client> connections) throws IOException {
        this.selector = selector;
        this.socketChannel = socketChannel;
        this.connections = connections;
        socketChannel.configureBlocking(false); // 넌블로킹 지정
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ); // 읽기작업 유형으로 Selector에 등록
        selectionKey.attach(this); //SelectionKey에 자신을 첨부 객체로 저장.
    }

    void receive(SelectionKey selectionKey) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            //상대방이 비저상 종료를 했을 경우 자동 IOException 발생

            int byteCount = socketChannel.read(byteBuffer); // .. 데이터 받기

            //상대방이 SocketChannel의 close() 메소드를 호출할 경우
            if(byteCount == -1) { 
                throw new IOException();
            }
            String message = "[요청 처리: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

            //Platform.runLater(()->displayText(message));
            System.out.println(message);

            byteBuffer.flip();
            Charset charset = Charset.forName("UTF-8");
            String data = charset.decode(byteBuffer).toString(); // 문자열 변환

            for(Client client : connections) {
                client.sendData = data;
                SelectionKey key = client.socketChannel.keyFor(selector);
                key.interestOps(SelectionKey.OP_WRITE); // 모든 클라이언트에게 문자열을 전송
            }

            selector.wakeup(); // 변경된 작업 유형일 감지하도록 하기 위해 Selector의 select()블로킹을 해제하고 다시 실행
        }
        catch(Exception e) {
            try {
                connections.remove(this);

                String message = "[클라이언트 통신 안됨: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

                //Platform.runLater(()->displayText(message));
                System.out.println(message);

                socketChannel.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    void send(SelectionKey selectionKey) {
        try {
            Charset charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = charset.encode(sendData);
            socketChannel.write(byteBuffer); // 데이터 보내기
            selectionKey.interestOps(SelectionKey.OP_READ); // 작업 유형 변경
            selector.wakeup(); // 변경된 작업 유형일 감지하도록 하기위해 Selector의 select()블로킹을 해제
        }
        catch(Exception e) {
            try {
                String message = "[클라이언트 통신 안됨: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";

                //Platform.runLater(()->displayText(message));
                System.out.println(message);

                connections.remove(this);
                socketChannel.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}