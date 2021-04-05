import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Edge {
    int from;
    int to;
    int cost;
    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    final static int INF = 10000001;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //st = new StringTokenizer(br.readLine());
        int N = 3;
        int M = 4;

        ArrayList<Edge> al = new ArrayList<>();
        al.add(new Edge(1, 2, 2));
        al.add(new Edge(1, 3, 4));
        al.add(new Edge(2, 3, 1));
        al.add(new Edge(3, 1, -2));


        int[] d = new int[N+1];
        Arrays.fill(d, INF);

        int start = 3;
        d[start] = 0;
        boolean negative_cycle = false;

        for (int i=1; i<=N; i++) {
            for (Edge e : al) {
                int x = e.from;
                int y = e.to;
                int z = e.cost;
                if (d[x] != INF && d[y] > d[x]+z) {
                    d[y] = d[x]+z;
                    if (i == N) {
                        negative_cycle = true;
                    }
                }
            }
        }

        if (negative_cycle) {   // 음의 사이클일 경우
            bw.write("-1");
        }
        else {
            for(int i=1; i<=N; i++) {
                bw.write(d[i]+" ");
            }
        }
        bw.newLine();
        bw.flush();
    }
}