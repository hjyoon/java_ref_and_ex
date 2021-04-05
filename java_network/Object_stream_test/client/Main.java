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

class MySocketClient {
    private Socket clientSocket;    // 클라이언트 소켓
    private BufferedReader br_from_user;        // 사용자로 부터 데이터를 입력받기 위함
    private ObjectInputStream ois;              // 클라이언트로부터 전달받은 메시지를 읽어드릴 버퍼 메모리
    private ObjectOutputStream oos;                 // 클라이언트로 메시지를 보냄
    private String ip;
    private int port;
    private String userName;

    public MySocketClient(String ip, int port, String userName) {
        this.ip = ip;
        this.port = port;
        this.userName = userName;
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

        br_from_user = new BufferedReader(new InputStreamReader(System.in));

        try {
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
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

                Data data = new Data(userName, inputData);

                // for(byte i : inputData.getBytes()) {
                //     System.out.print(i+" ");
                // }
                // System.out.println("");

                //oos.write(inputData.getBytes());

                oos.writeObject(data);

                //byte[] b = new byte[1024];  // 서버로 부터 읽어온 데이터를 저장할 공간
                Data readData = null;
                try {
                    //ois.read(b);
                    readData = (Data)ois.readObject();
                }
                catch(IOException e) {
                    e.printStackTrace();
                    break;
                }

                //String readData = new String(b).trim();
                System.out.println(readData.getUserName()+"> " + readData.getUserMessage()); // 서버에서 받은 데이터를 출력
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("name : ");
        String name = br.readLine();

        MySocketClient msc = new MySocketClient("localhost", 8981, name);
        msc.init();
        msc.run();
    }
}