import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.text.*;
import java.net.*;
import java.net.http.*;
import java.nio.channels.*;

public class Main {
    public static void main(String args[]) throws Exception {
        // ServerSocketChannel ssc = null;
        // try {
        //     ssc = ServerSocketChannel.open();
        //     ssc.configureBlocking(true);    // 블로킹 방식으로 설정
        //     ssc.bind(new InetSocketAddress(5001));

        //     while(true) {
        //         System.out.println("[연결 기다림]");
        //         SocketChannel sc = ssc.accept();
        //         InetSocketAddress isa = (InetSocketAddress)sc.getRemoteAddress();
        //         System.out.println("[연결 수락함] " + isa.getHostName());
        //     }
        // }
        // catch(Exception e) {
        //     if(ssc.isOpen()) {
        //         try {
        //             ssc.close();
        //         }
        //         catch (IOException e1) {

        //         }
        //     }
        // }

        /*  
            Try-with-resources 사용

            try(여기에서 선언한 변수들은 try 안에서 사용할 수 있습니다.)

            코드의 실행 위치가 try 문을 벗어나면,
            try-with-resources는 try(...) 안에서 선언된 객체의 close() 메소드들을 호출합니다.
            그래서 finally에서 close()를 명시적으로 호출해줄 필요가 없습니다.

            try-with-resources에서 자동으로 close가 호출되는 것은 AutoCloseable을 구현한 객체에만 해당이 됩니다.
        */
        try (
            ServerSocketChannel ssc = ServerSocketChannel.open();
        ) {
            ssc.configureBlocking(true);    // 블로킹 방식으로 설정
            ssc.bind(new InetSocketAddress(5001));

            while(true) {
                System.out.println("[연결 기다림]");
                SocketChannel sc = ssc.accept();
                InetSocketAddress isa = (InetSocketAddress)sc.getRemoteAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}