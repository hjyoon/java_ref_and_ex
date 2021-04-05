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

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken());
        // int M = Integer.parseInt(st.nextToken());

        // 역원은 a와 m이 서로소인 경우에만 존재
        int a = 7;
        int m = 5;
        int x = 0;

        if(gcd(a, m) != 1) {    // a와 b의 최대공약수가 1이 아닐경우.
            bw.write(a+"와 "+m+"은 서로소가 아니므로 역원이 존재하지 않음.");
            // 서로소 : 1 이외에 공약수를 갖지 않는 둘 이상의 양의 정수
        }
        else {
            for (int i=1; i<m; i++) {
                if ((a*i) % m == 1) {
                    x = i;
                    bw.write(x+"");
                    
                }
            }
        }

        bw.newLine();
        bw.flush();
    }
}