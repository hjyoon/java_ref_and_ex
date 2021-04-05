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

    static class Compare implements Comparator<Edge> {
        public int compare(Edge one, Edge two) {
            return Integer.compare(one.cost, two.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = 6;  // Node 수
        int M = 9;  // Edge 수

        @SuppressWarnings("unchecked")
        List<Edge>[] graph_list = (List<Edge>[]) new List[N+1];
        for (int i=1; i<=N; i++) {
            graph_list[i] = new ArrayList<Edge>();
        }

        // 양방향으로 연결
        graph_list[1].add(new Edge(1, 2, 5));
        graph_list[2].add(new Edge(2, 1, 5));
        graph_list[1].add(new Edge(1, 3, 4));
        graph_list[3].add(new Edge(3, 1, 4));
        graph_list[2].add(new Edge(2, 3, 2));
        graph_list[3].add(new Edge(3, 2, 2));
        graph_list[2].add(new Edge(2, 4, 7));
        graph_list[4].add(new Edge(4, 2, 7));
        graph_list[3].add(new Edge(3, 4, 6));
        graph_list[4].add(new Edge(4, 3, 6));
        graph_list[3].add(new Edge(3, 5, 11));
        graph_list[5].add(new Edge(5, 3, 11));
        graph_list[4].add(new Edge(4, 5, 3));
        graph_list[5].add(new Edge(5, 4, 3));
        graph_list[4].add(new Edge(4, 6, 8));
        graph_list[6].add(new Edge(6, 4, 8));
        graph_list[5].add(new Edge(5, 6, 8));
        graph_list[6].add(new Edge(6, 5, 8));

        boolean[] c = new boolean[N+1];

        // 먼저, 임의의 노드 하나를 선택 후, 그 Node에 연결된 모든 Edge를 우선순위 큐에 넣는다.
        int start_node = 1; // 임의로 1번 노드를 선택하였음. (어느 것을 선택해도 상관 없음)
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        c[start_node] = true;
        for (Edge e : graph_list[start_node]) {
            pq.add(e);
        }

        int ans = 0;
        for (int i=0; i<N-1; i++) {
            Edge e = new Edge();
            while (!pq.isEmpty()) {
                e = pq.poll();  // 우선순위 큐에서 cost가 최소인 Edge가 나온다.
                if (c[e.end] == false) {    // 목적지 노드가 아직 선택되지 않았을 경우에 선택함.
                    break;
                }
            }
            c[e.end] = true;
            ans += e.cost;
            for (Edge ee : graph_list[e.end]) {
                pq.add(ee);
            }
        }
        
        bw.write(ans+"");
        bw.newLine();

        bw.flush();
    }
}