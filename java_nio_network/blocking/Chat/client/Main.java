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
            String msg = br.readLine();
            client.send(msg);
        }
    }
}

class ClientExample {
    SocketChannel socketChannel; // 클라이언트 통신을 위해 SocketChannel 필드 선언

    void startClient() {
        Thread thread = new Thread() { // 스레드 생성
            @Override
            public void run() {
                try { 
                    socketChannel = SocketChannel.open(); // 소켓 생성 및 연결 요청
                    socketChannel.configureBlocking(true);
                    socketChannel.connect(new InetSocketAddress("localhost", 5001));
                    // Platform.runLater(()->{
                    //     try {
                    //         displayText("[연결 완료: "  + socketChannel.getRemoteAddress() + "]");
                    //         btnConn.setText("stop");
                    //         btnSend.setDisable(false);
                    //     }
                    //     catch (Exception e) {
                    //         e.printStackTrace();
                    //     }
                    // });
                    System.out.println("[연결 완료: "  + socketChannel.getRemoteAddress() + "]");
                }
                catch(Exception e) {
                    //Platform.runLater(()->displayText("[서버 통신 안됨]"));
                    System.out.println("[서버 통신 안됨]");
                    if(socketChannel.isOpen()) {
                        stopClient();
                    }
                    return;
                }
                receive(); // 서버에서 보낸 데이터 받기
            }
        };
        thread.start(); // 스레드 시작
    }

    void stopClient() {
        try {
            // Platform.runLater(()->{
            //     displayText("[연결 끊음]");
            //     btnConn.setText("start");
            //     btnSend.setDisable(true);
            // });
            System.out.println("[연결 끊음]");
            if(socketChannel != null && socketChannel.isOpen()) {
                socketChannel.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void receive() {
        while(true) {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                //서버가 비정상적으로 종료했을 경우 IOException 발생
                int readByteCount = socketChannel.read(byteBuffer); //데이터받기
                //서버가 정상적으로 Socket의 close()를 호출했을 경우
                if(readByteCount == -1) {
                throw new IOException();
                }
                byteBuffer.flip(); // 문자열로 변환
                Charset charset = Charset.forName("UTF-8");
                String data = charset.decode(byteBuffer).toString();
                //Platform.runLater(()->displayText("[받기 완료] "  + data));
                System.out.println("[받기 완료] " + data);
            }
            catch (Exception e) {
                //Platform.runLater(()->displayText("[서버 통신 안됨]"));
                System.out.println("[서버 통신 안됨]");
                stopClient();
                break;
            }
        }
    }

    void send(String data) {
        Thread thread = new Thread() { // 스레드 생성
            @Override
            public void run() {
                try {
                    Charset charset = Charset.forName("UTF-8");
                    ByteBuffer byteBuffer = charset.encode(data);
                    socketChannel.write(byteBuffer); // 서버로 데이터 보내기
                    //Platform.runLater(()->displayText("[보내기 완료]"));
                    System.out.println("[보내기 완료]");
                }
                catch(Exception e) {
                    //Platform.runLater(()->displayText("[서버 통신 안됨]"));
                    System.out.println("[서버 통신 안됨]");
                    stopClient();
                }    
            }
        };
        thread.start(); // 스레드 시작
    }
}