import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Edge implements Comparable<Edge> {
    public int start;
    public int end;
    public int cost;

    public Edge() {
        this(0,0,0);
    }

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        if(cost > e.cost) {
            return 1;
        }
        else if(cost < e.cost) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void disjoint_union(int [] p, int x, int y) {
        x = find(p, x);
        y = find(p, y);
        p[x] = y;
    }

    public static int find(int[] p, int x) {
        if (x == p[x]) {
            return x;
        } else {
            return (p[x] = find(p, p[x]));
        }
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int V = 3;
        int E = 3;

        int[] p = new int[V+1];
        for (int i=0; i<=V; i++) {
            p[i] = i;
        }

        List<Edge> edge_list = new ArrayList<>();

        edge_list.add(new Edge(1, 2, 1));
        edge_list.add(new Edge(2, 3, 2));
        edge_list.add(new Edge(1, 3, 3));

        Collections.sort(edge_list);    // Edge를 cost의 오름차순으로 정렬한다.

        int ans = 0;
        for (Edge e : edge_list) {
            int x = find(p, e.start);
            int y = find(p, e.end);
            if (x != y) {   // x와 y가 같은 그래프 집합이 아니라면
                disjoint_union(p, e.start, e.end);  // x집합과 y집합을 합친다.
                ans += e.cost;
            }
        }
        
        bw.write(ans+"");
        bw.newLine();

        bw.flush();
    }
}