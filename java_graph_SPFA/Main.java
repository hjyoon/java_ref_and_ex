import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Edge {
    int to;
    int cost;
    Edge(int to, int cost) {
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

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] al = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            al[i] = new ArrayList<>();
        }

        al[1].add(new Edge(2, 2));
        al[1].add(new Edge(3, 4));
        al[2].add(new Edge(3, 1));
        al[3].add(new Edge(1, -2));


        int[] d = new int[N+1];
        Arrays.fill(d, INF);

        int start = 1;
        d[start] = 0;
        boolean negative_cycle = false;

        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] c = new boolean[N+1];
        q.add(start);
        c[start] = true;

        while(!q.isEmpty()) {
            int from = q.remove();
            c[from] = false;
            for(Edge e : al[from]) {
                int to = e.to;
                int cost = e.cost;
                if (d[from] != INF && d[to] > d[from]+cost) {
                    d[to] = d[from]+cost;
                    if(c[to] == false) {
                        q.add(to);
                        c[to] = true;
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