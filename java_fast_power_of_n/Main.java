import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int cal(final int a, final int b) {  // a^b
        int base = a;
        int exp = b;
        int frag = 1;
        if(a == 1 || b == 0) {
            return 1;
        }
        if(b == 1) {
            return base;
        }
        while(true) {
            if(exp == 2) {
                return base * base * frag;
            }

            if(exp % 2 == 0) {
                exp /= 2;
                base *= base;
            }
            else {
                exp--;
                frag *= base;
            }
        }
    }

    static int cal_1(final int a, final int b) {  // a^b
        int base = a;
        int exp = b;
        int ans = 1;
        if(a == 1 || b == 0) {
            return 1;
        }
        while(true) {
            if(exp == 0) {
                return ans;
            }

            if(exp % 2 == 1) {
                ans *= base;
            }
            base *= base;
            exp /= 2;
        }
    }

    static int cal_1_2(int a, int b) {  // a^b // 이진법 원리 이용
        int ans = 1;
        if(a == 1 || b == 0) {
            return 1;
        }
        while(b > 0) {
            if(b % 2 == 1) {
                ans *= a;
            }
            a *= a;
            b /= 2;
        }
        return ans;
    }

    static int cal_2(final int a, final int b) {  // a^b
        if(b == 0) {
            return 1;
        }
        if(b == 1) {
            return a;
        }
        else {
            if(b % 2 == 1) {
                return a * cal_2(a, b-1);
            }
            else {
                // /*
                int tmp = cal_2(a, b/2);
                return tmp * tmp;
                // */

                // or

                /*
                return cal_2(a*a, b/2);
                */
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //st = new StringTokenizer(br.readLine());
        //int A = Integer.parseInt(st.nextToken());
        //int B = Integer.parseInt(st.nextToken());

        int A = 2;
        int B = 30;

        bw.write(cal(A, B)+"");
        bw.newLine();
        bw.write(cal_1(A, B)+"");
        bw.newLine();
        bw.write(cal_1_2(A, B)+"");
        bw.newLine();
        bw.write(cal_2(A, B)+"");

        bw.newLine();
        bw.flush();
    }
}