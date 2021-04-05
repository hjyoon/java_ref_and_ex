import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static String s;
    static String[] sa;
    final static int INF = 100000001;

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new FileReader("../input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 0 <= N <= 100,000

        //PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                if(Math.abs(i1) == Math.abs(i2)) {
                    if(i1 == i2) {
                        return 0;
                    }
                    else if(i1 > i2) {
                        return 1;
                    }
                    else {
                        return -1;
                    }
                }
                else if(Math.abs(i1) > Math.abs(i2)) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
       
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            if(x == 0) {
                if(pq.isEmpty()) {
                    bw.write("0"+"\n");
                }
                else {
                    bw.write(pq.poll()+"\n");
                }
            }
            else {
                pq.offer(x);
            }
        }

        bw.flush();
    }
}