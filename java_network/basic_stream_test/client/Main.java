import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

class MySocketClient {
    private Socket clientSocket;    // 클라이언트 소켓
    private BufferedReader br_from_user;        // 사용자로 부터 데이터를 입력받기 위함
    private InputStream is;      // 클라이언트로부터 전달받은 메시지를 읽어들이기 위함
    private OutputStream os;         // 클라이언트로 메시지를 보내기 위함
    private String ip;
    private int port;

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
            is = clientSocket.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        br_from_user = new BufferedReader(new InputStreamReader(System.in));

        try {
            os = clientSocket.getOutputStream();
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

                // 그냥 엔터만 칠 경우 먹통이 되는것을 방지
                if(inputData.equals("")) {
                    inputData = "\0";
                }

                // for(byte i : inputData.getBytes()) {
                //     System.out.print(i+" ");
                // }
                // System.out.println("");

                os.write(inputData.getBytes());

                byte[] b = new byte[1024];  // 서버로 부터 읽어온 데이터를 저장할 공간
                try {
                    is.read(b);
                }
                catch(IOException e) {
                    e.printStackTrace();
                    break;
                }

                String readData = new String(b).trim();
                System.out.println("from Server> " + readData); // 서버에서 받은 데이터를 출력
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