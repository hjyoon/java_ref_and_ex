import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

class MySocketClient {
    private Socket clientSocket;    // 클라이언트 소켓
    private BufferedReader br_from_socket;      // 클라이언트로부터 전달받은 메시지를 읽어들이기 위함
    private BufferedReader br_from_user;        // 사용자로 부터 데이터를 입력받기 위함
    private PrintWriter pw;         // 클라이언트로 메시지를 보내기 위함
    private String ip;
    private int port;

    public MySocketClient() {
        this("localhost", 8981);
    }

    public MySocketClient(String ip) {
        this(ip, 8981);
    }

    public MySocketClient(int port) {
        this("localhost", port);
    }

    public MySocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void init() {
        try {
            System.out.println("Trying to connect...");
            clientSocket = new Socket(ip, port);
            System.out.println("Connection success");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br_from_socket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        br_from_user = new BufferedReader(new InputStreamReader(System.in));

        try {
            pw = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while(true) {
                System.out.print("to Server> ");

                String inputData = br_from_user.readLine();
                if(inputData.equals("exit")) {
                    break;
                }

                pw.println(inputData);      // 보낼 내용을 읽어와서 전송
                pw.flush();                 // 버퍼에 있던 데이터를 전송하고 버퍼를 비운다

                System.out.println("from Server> " + br_from_socket.readLine()); // 서버에서 받은 데이터를 출력
            }
            clientSocket.close();   //연결 종료하면 소켓을 닫는다.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        MySocketClient msc = new MySocketClient("localhost", 8981);
        msc.init();
        msc.run();
    }
}