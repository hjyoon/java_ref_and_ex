import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static long phi(long n) {
        long ans = n;
        for(long i=2; i*i<=n; i++) {
            if(n%i == 0) {
                while(n%i == 0) {
                    n /= i;
                }
                ans -= ans / i;
            }
        }
        if(n > 1) {
            ans -= ans / n;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken());
        // int M = Integer.parseInt(st.nextToken());
        long N = 9;

        bw.write(phi(N)+" ");
        bw.newLine();

        bw.flush();
    }
}