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
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //st = new StringTokenizer(br.readLine());
        long N = 100;
        long K = 45;
        int P = 13;

        int[][] d = new int[P+1][P+1];

        // 파스칼 삼각형 만들기
        for(int i=0; i<=P; i++) {
            d[i][0] = d[i][i] = 1;
            for(int j=1; j<=i-1; j++) {
                d[i][j] = d[i-1][j-1] + d[i-1][j];
                d[i][j] %= P;
            }
        }

        // print test
        for(int i=0; i<=P; i++) {
            for(int j=0; j<=P; j++) {
                bw.write(d[i][j]+" ");
            }
            bw.newLine();
        }

        // P진법으로 만들기
        ArrayList<Integer> a = new ArrayList<>();   // N의 P진법 저장
        ArrayList<Integer> b = new ArrayList<>();   // K의 P진법 저장
        while (N > 0 || K > 0) {
            a.add((int)(N%P));
            b.add((int)(K%P));
            N /= P;
            K /= P;
        }

        // a.get(i) : P진법 N의 i번째 자릿수
        // b.get(i) : P진법 K의 i번째 자릿수

        for(int i : a) {
            bw.write(i+" ");
        }
        bw.newLine();

        for(int i : b) {
            bw.write(i+" ");
        }
        bw.newLine();

        long res = 1;
        for (int i=0; i<a.size(); i++) {
            res *= d[a.get(i)][b.get(i)];
            bw.write(d[a.get(i)][b.get(i)]+" ");
            res %= P;
        }
        bw.newLine();
        bw.write(res+"");
        bw.newLine();
        bw.flush();
    }
}