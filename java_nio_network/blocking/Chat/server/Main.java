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
    ExecutorService executorService; // 스레드풀 필드 선언
    ServerSocketChannel serverSocketChannel; // 서버소켓채널 필드 선언
    List<Client> connections = new Vector<Client>(); // 연결된 클라이언트를 저장하는

    public void startServer() {
        // 스레드풀 생성
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            serverSocketChannel = ServerSocketChannel.open(); // 서버 소켓 채널 생성
            serverSocketChannel.configureBlocking(true); // 블로킹임을 명시적으로 알려줌
            serverSocketChannel.bind(new InetSocketAddress(5001)); // 포트 바인딩
        }
        catch(Exception e) { // 예외 발생시 서버 소켓 채널 열려있으면 서버 멈춤.
            if(serverSocketChannel.isOpen()) {
                stopServer();
            }
            return;
        }

        Runnable runnable = new Runnable() { // 연결 수락 작업을 Runnable로 정의
            @Override
            public void run() {
                // Platform.runLater(()->{
                //     displayText("[서버 시작]");
                //     btnStartStop.setText("stop");
                // });

                System.out.println("[서버 시작]");

                while(true) {
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept(); // 클라이언트의 요청을 기다림.
                        String message = "[연결 수락: " + socketChannel.getRemoteAddress()  + ": " + Thread.currentThread().getName() + "]";
                        System.out.println(message);
                        //Platform.runLater(()->displayText(message));
                        Client client = new Client(executorService, socketChannel, connections);
                        connections.add(client); // 클래이언트 객체 생성 후 connections에 추가
                        //Platform.runLater(()->displayText("[연결 개수: " + connections.size() + "]")); // 연결된 클라이언트 수
                        System.out.println("[연결 개수: " + connections.size() + "]");
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

        executorService.submit(runnable);
    }

    public void stopServer() {
        try {
            Iterator<Client> iterator = connections.iterator();

            while(iterator.hasNext()) {
                Client client = iterator.next();
                client.socketChannel.close();
                iterator.remove();
            } // 모든 소켓 채널 닫기

            if(serverSocketChannel != null && serverSocketChannel.isOpen()) { 
                serverSocketChannel.close(); 
            } // 서버 소켓 채널 닫기

            if(executorService != null && !executorService.isShutdown()) { 
                executorService.shutdown(); 
            } // 스레드풀 닫기

            // 작업 스레드는 UI 변경 불가 -> Platform.runLater()사용
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
}

class Client {
    ExecutorService executorService;
    SocketChannel socketChannel;
    List<Client> connections;

    Client(ExecutorService executorService, SocketChannel socketChannel, List<Client> connections) {
        this.executorService = executorService;
        this.socketChannel = socketChannel;
        this.connections = connections;
        receive();
    }

    void receive() {
        // 스레드풀의 작업 스레드가 처리하도록 Runnable로 작업 정의
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true) { // 클라이언트가 보낸 데이터를 반복적으로 받기 위한 무한 루프
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

                        //클라이언트가 비정상 종료를 했을 경우 IOException 발생
                        int readByteCount = socketChannel.read(byteBuffer);

                        //클라이언트가 정상적으로 SocketChannel의 close()를 호출했을 경우
                        if(readByteCount == -1) {
                            throw new IOException();
                        }

                        String message = "[요청 처리: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";
                        //Platform.runLater(()->displayText(message));
                        System.out.println(message);

                        byteBuffer.flip();
                        Charset charset = Charset.forName("UTF-8");
                        String data = charset.decode(byteBuffer).toString();

                        for(Client client : connections) {
                            client.send(data); 
                        }
                    }
                    catch(Exception e) {
                        try {
                            connections.remove(Client.this);
                            String message = "[클라이언트 통신 안됨: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";
                            //Platform.runLater(()->displayText(message));
                            System.out.println(message);

                            socketChannel.close();
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        break;
                    }
                }
            }
        };

        executorService.submit(runnable); // 스레드 풀에서 처리
    }

    void send(String data) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Charset charset = Charset.forName("UTF-8");
                    ByteBuffer byteBuffer = charset.encode(data);
                    socketChannel.write(byteBuffer);
                }
                catch(Exception e) {
                    try {
                        String message = "[클라이언트 통신 안됨: " + socketChannel.getRemoteAddress() + ": " + Thread.currentThread().getName() + "]";
                        //Platform.runLater(()->displayText(message));
                        System.out.println(message);
                        connections.remove(Client.this);
                        socketChannel.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        };

        executorService.submit(runnable); // 스레드풀에서 처리
    }
}