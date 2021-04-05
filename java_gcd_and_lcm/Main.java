import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int gcd(int x, int y) {  // Greatest Common Divisor
        // 유클리드 호제법을 이용
        while(y != 0) {
            int r = x%y;
            x = y;
            y = r;
        }
        return x;
    }

    static int lcm(int x, int y) {  // Least Common Multiple
        int g = gcd(x, y);
        return g * x/g * y/g;
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken());
        // int M = Integer.parseInt(st.nextToken());

        int N = 12;
        int M = 18;

        bw.write(gcd(N, M)+" ");    // 최대공약수
        bw.newLine();

        bw.write(lcm(N, M)+" ");    // 최소공배수
        bw.newLine();

        bw.flush();
    }
}