import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

class MySendThread extends Thread {
    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter pw;

    MySendThread(Socket clientSocket, BufferedReader br, PrintWriter pw) {
        this.clientSocket = clientSocket;
        this.br = br;
        this.pw = pw;
    }

    @Override
    public void run() {
        try {
            while(true) {
                if(!clientSocket.isConnected() && clientSocket.isClosed()) {
                    break;
                }

                System.out.print("You> ");

                String inputData = br.readLine();
                if(inputData == null) {
                    break;
                }
                if(inputData.equals("exit")) {
                    break;
                }

                pw.println(inputData);      // 보낼 내용을 읽어와서 전송
                pw.flush();                 // 버퍼에 있던 데이터를 전송하고 버퍼를 비운다
            }
            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyReceiveThread extends Thread {
    private Socket clientSocket;
    private BufferedReader br;

    MyReceiveThread(Socket clientSocket, BufferedReader br) {
        this.clientSocket = clientSocket;
        this.br = br;
    }

    @Override
    public void run() {
        try {
            while(true) {
                if(!clientSocket.isConnected() && clientSocket.isClosed()) {
                    break;
                }

                String receivedData = br.readLine();
                if(receivedData == null) {
                    break;
                }
                if(receivedData.equals("exit")) {
                    break;
                }
                System.out.println("Stranger> " + receivedData); // 서버에서 받은 데이터를 출력
            }
            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MySocketClient {
    private Socket clientSocket;            // 클라이언트 소켓
    private BufferedReader br_from_socket;  // 소켓으로부터 전달받은 메시지를 읽어들이기 위함
    private BufferedReader br_from_user;    // 사용자로 부터 데이터를 입력받기 위함
    private PrintWriter pw;                 // 소켓으로 메시지를 보내기 위함
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
            //clientSocket = new Socket();
            //clientSocket.connect(new InetSocketAddress(ip, port), 5);
            //clientSocket.setSoTimeout(1000);
            System.out.println(clientSocket);
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
        MySendThread send_thread = new MySendThread(clientSocket, br_from_user, pw);
        MyReceiveThread rec_thread = new MyReceiveThread(clientSocket, br_from_socket);
        send_thread.start();
        rec_thread.start();
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        MySocketClient msc = new MySocketClient("localhost", 8981);
        msc.init();
        msc.run();
    }
}