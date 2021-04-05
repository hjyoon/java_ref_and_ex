import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken());

        int N = 3000;

        for(int i=2; i*i<=N; i++) {
            while(N%i == 0) {
                bw.write(i+"");
                bw.newLine();
                N /= i;
            }
        }

        if(N > 1) {
            bw.write(N+"");
        }
        bw.newLine();

        bw.flush();
    }
}