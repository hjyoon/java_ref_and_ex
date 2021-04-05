import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] cal(int a, int b) {
        if (b == 0) {
            return new int[] {a, 1, 0};
        }

        int[] res = cal(b, a%b);

        int g = res[0];
        int x = res[1];
        int y = res[2];

        res[0] = g;
        res[1] = y;
        res[2] = x-(a/b)*y;

        return res;
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 확장 유클리드 알고리즘 : ax + by = 1 이 되는 정수 x,y를 구하는 문제
        // ax + by = c, c는 무조건 a와 b의 최대공약수로 나누어 떨어져야 한다.
        int a = 11;
        int b = 7;
        int c = 1;

        int[] res = cal(a, b);  // 최대공약수, x, y를 리턴

        for(int i=0; i<3; i++) {
            bw.write(res[i]+" ");
        }
        bw.newLine();

        bw.flush();
    }
}