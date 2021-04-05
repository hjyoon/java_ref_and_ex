import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.text.*;
import java.net.*;
import java.net.http.*;
import java.nio.channels.*;

/*
    SelectionKey selectionKey = serverSocketChannel.register(Selector sel, int ops);
    SelectionKey selectionKey = SocketChannel.register(Selector sel, int ops);
*/

/*  두 번째 매개값으로 사용할 수 있는 작업 유형별 SelectionKey 상수

    SelectionKey 상수     설명
    OP_ACCEPT             ServerSocketChannel 의 연결 수락 작업
    OP_CONNECT            SocketChannel의 서버 연결 작업
    OP_READ               SocketChannel의 데이터 읽기 작업
    OP_WRITE              SocketChannel의 데이터 쓰기 작업
*/

public class Main {
    public static void main(String args[]) throws Exception {
        try (
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            SocketChannel sc = SocketChannel.open();
        ) {
            // 넌블로킹 방식으로 설정
            ssc.configureBlocking(false);
            sc.configureBlocking(false);

            SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

            //SelectionKey selectionKey = sc.register(selector, SelectionKey.OP_CONNECT);
            //SelectionKey selectionKey = sc.register(SelectionKey.OP_READ);
            //SelectionKey selectionKey = sc.register(SelectionKey.OP_WRITE);

            SelectionKey key = sc.keyFor(selector);


        }
        catch(Exception e) {
            e.printStackTrace();
        }


        // try (
        //     ServerSocketChannel ssc = ServerSocketChannel.open();
        // ) {
        //     ssc.configureBlocking(true);    // 블로킹 방식으로 동작 설정
        //     ssc.bind(new InetSocketAddress(5001));

        //     while(true) {
        //         System.out.println("[연결 기다림]");
        //         SocketChannel sc = ssc.accept();
        //         InetSocketAddress isa = (InetSocketAddress)sc.getRemoteAddress();
        //         System.out.println("[연결 수락함] " + isa.getHostName());
        //     }
        // }
        // catch(Exception e) {
        //     e.printStackTrace();
        // }

    }
}