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
    private InputStream is;              // 클라이언트로부터 전달받은 메시지를 읽어드릴 버퍼 메모리
    private OutputStream os;                 // 클라이언트로 메시지를 보냄
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

            is = clientSocket.getInputStream(); // 클라이언트로 부터 데이터를 읽어올 준비
            os = clientSocket.getOutputStream(); // 클라이언트로 데이터를 보낼 준비

            while(true) {
                // try {
                //     is.transferTo(os);   // 읽은 데이터를 그대로 다시 전달
                // }
                // catch(IOException e) {
                //     e.printStackTrace();
                //     break;
                // }

                // 1개씩 읽어들이는 방식
                // int data = is.read();
                // if(data == -1) {
                //     break;
                // }
                // System.out.print(data+" ");
                // os.write(data);

                // while(true) {
                //     int size = is.available();  // 현재 읽어들일 수 있는 바이트의 수를 리턴
                //     if(size != 0) {
                //         System.out.println(size);
                //         break;
                //     }
                // }

                byte[] b = new byte[1024];  // 클라이언트로 부터 읽어온 데이터를 저장할 공간
                try {
                    is.read(b);
                    //is.readFully(b);
                }
                catch(IOException e) {
                    e.printStackTrace();
                    break;
                }


                // for(byte i : b) {
                //     System.out.print(i+" ");
                // }
                // System.out.println("");

                String readData = new String(b).trim();
                System.out.println("from Client> " + readData);

                os.write(b); // 읽은 메시지를 그대로 클라이언트에 다시 보냄
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