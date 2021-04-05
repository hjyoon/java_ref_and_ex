import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

class MySocketServer {
    private ServerSocket serverSocket;      // 서버 소켓
    private Socket clientSocket;            // 클라이언트 소켓
    private BufferedReader br;              // 클라이언트로부터 전달받은 메시지를 읽어드릴 버퍼 메모리
    private PrintWriter pw;                 // 클라이언트로 메시지를 보냄
    private int port;

    public MySocketServer() {
        this(8981);
    }

    public MySocketServer(int port) {
        this.port = port;
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(port); // 현재 아이피로 8981포트를 사용하여 서버 오픈
            System.out.println("Server is ready");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println("Waiting for connection...");

            clientSocket = serverSocket.accept();

            System.out.println("Client has accepted");

            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // 클라이언트로 부터 데이터를 읽어올 준비
            pw = new PrintWriter(clientSocket.getOutputStream()); // 클라이언트로 데이터를 보낼 준비

            String readData = ""; // 클라이언트로 부터 읽어온 데이터를 저장할 공간

            while(!(readData = br.readLine()).equals(null)) {
                System.out.println("from Client> " + readData);
                pw.println(readData);   // 읽은 메시지를 그대로 클라이언트에 다시 보냄
                pw.flush();             // 프린트 라이터 메모리를 초기화 시켜야 데이터가 보내짐 
            }
            clientSocket.close(); 
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}

public class Main {
    public static void main(String args[]) throws Exception {
        MySocketServer mss = new MySocketServer(8981);
        mss.init();
        mss.run();
    }
}