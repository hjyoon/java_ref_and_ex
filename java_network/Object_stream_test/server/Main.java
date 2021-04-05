import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

class Data implements Serializable {
    private String userName;
    private String userMessage;

    Data(String userName, String userMessage) {
        this.userName = userName;
        this.userMessage = userMessage;
    }

    String getUserName() {
        return userName;
    }

    String getUserMessage() {
        return userMessage;
    }
}

class MySocketServer {
    private ServerSocket serverSocket;      // 서버 소켓
    private Socket clientSocket;            // 클라이언트 소켓
    private ObjectInputStream ois;              // 클라이언트로부터 전달받은 메시지를 읽어드릴 버퍼 메모리
    private ObjectOutputStream oos;                 // 클라이언트로 메시지를 보냄
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

            ois = new ObjectInputStream(clientSocket.getInputStream()); // 클라이언트로 부터 데이터를 읽어올 준비
            oos = new ObjectOutputStream(clientSocket.getOutputStream()); // 클라이언트로 데이터를 보낼 준비

            while(true) {
                //byte[] b = new byte[1024];  // 클라이언트로 부터 읽어온 데이터를 저장할 공간
                Data data = null;
                try {
                    //ois.read(b);
                    data = (Data)ois.readObject();
                }
                catch(IOException e) {
                    e.printStackTrace();
                    break;
                }


                // for(byte i : b) {
                //     System.out.print(i+" ");
                // }
                // System.out.println("");

                String userName = data.getUserName();
                String userMessage = data.getUserMessage();

                System.out.println(userName+"> " + userMessage);

                //oos.write(b); // 읽은 메시지를 그대로 클라이언트에 다시 보냄
                oos.writeObject(data);
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