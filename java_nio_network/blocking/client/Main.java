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
        try (
            SocketChannel sc = SocketChannel.open();
        ) {
            sc.configureBlocking(true);

            System.out.println( "[연결 요청]");
            sc.connect(new InetSocketAddress("localhost", 5001));
            System.out.println( "[연결 성공]");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}