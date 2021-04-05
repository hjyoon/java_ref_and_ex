import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Vertex implements Comparable<Vertex> {
    int v;
    int w;
    Vertex(int v, int w) {
        this.v = v;
        this.w = w;
    }
    public int compareTo(Vertex o){
        return w <= o.w ? -1 : 1;
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static String s;
    static String[] sa;
    final static int INF = 10000001;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //s = br.readLine();
        //sa = br.readLine().split(" +");
        //int N = Integer.parseInt(br.readLine());    // 0 <= N <= 4000

        int N = 6;  // the number of node
        
        // for array
        int[][] a = new int[N+1][N+1];

        // for list
        @SuppressWarnings("unchecked")
        ArrayList<Vertex>[] graph_list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph_list[i] = new ArrayList<>();
        }

        int[] d = new int[N+1];
        boolean[] c = new boolean[N+1];

        // data init
        for (int i=1; i<=N; i++) {
            Arrays.fill(a[i], INF);
        }

        // data input for array
        a[1][1] = 0;
        a[1][2] = 3;
        a[1][3] = 5;
        a[2][2] = 0;
        a[2][3] = 2;
        a[2][4] = 6;
        a[3][2] = 1;
        a[3][3] = 0;
        a[3][4] = 4;
        a[3][5] = 6;
        a[4][4] = 0;
        a[4][5] = 2;
        a[4][6] = 3;
        a[5][1] = 3;
        a[5][5] = 0;
        a[5][6] = 6;
        a[6][6] = 0;

        // data input for list
        graph_list[1].add(new Vertex(1, 0));
        graph_list[1].add(new Vertex(2, 3));
        graph_list[1].add(new Vertex(3, 5));
        graph_list[2].add(new Vertex(2, 0));
        graph_list[2].add(new Vertex(3, 2));
        graph_list[2].add(new Vertex(4, 6));
        graph_list[3].add(new Vertex(2, 1));
        graph_list[3].add(new Vertex(3, 0));
        graph_list[3].add(new Vertex(4, 4));
        graph_list[3].add(new Vertex(5, 6));
        graph_list[4].add(new Vertex(4, 0));
        graph_list[4].add(new Vertex(5, 2));
        graph_list[4].add(new Vertex(6, 3));
        graph_list[5].add(new Vertex(1, 3));
        graph_list[5].add(new Vertex(5, 0));
        graph_list[5].add(new Vertex(6, 6));
        graph_list[6].add(new Vertex(6, 0));

        
        // data input
        // for (int i=1; i<N+1; i++) {
        //     int x = sc.nextInt();
        //     int y = sc.nextInt();
        //     int z = sc.nextInt();
        //     if (a[x][y] > z) {
        //         a[x][y] = z;
        //     }
        // }

        int start = 1;  // 시작 노드
        int end = 6;    // 끝 노드


        // for array
        // destination array init
        // Arrays.fill(c, false);
        // Arrays.fill(d, INF);
        // d[start] = 0;
        // for (int k=0; k<N-1; k++) {
        //     int min = INF+1;
        //     int x = -1;
        //     for (int i=1; i<=N; i++) {
        //         if (c[i] == false && min > d[i]) {
        //             min = d[i];
        //             x = i;
        //         }
        //     }
        //     c[x] = true;
        //     for (int i=1; i<=N; i++) {
        //         if (d[i] > d[x] + a[x][i]) {
        //             d[i] = d[x] + a[x][i];
        //         }
        //     }
        // }
        // // print
        // for(int i=1; i<=N; i++) {
        //     bw.write(d[i]+" ");
        // }
        // bw.write("\n");
        // bw.write(d[end]+"\n");


        // array and heap
        Arrays.fill(c, false);
        Arrays.fill(d, INF);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        d[start] = 0;
        pq.offer(new Vertex(start, d[start]));
        while(!pq.isEmpty()){
            int cost = pq.peek().w;
            int here = pq.peek().v;
            pq.poll();
            if(cost > d[here]) {
                continue;
            } 
            for(int i=1; i<=N; i++){
                if(a[here][i] != 0 && d[i] > d[here] + a[here][i]){
                    d[i] = d[here] + a[here][i];
                    pq.offer(new Vertex(i, d[i]));
                }
            }
        }
        // print
        for(int i=1; i<=N; i++) {
            bw.write(d[i]+" ");
        }
        bw.write("\n");
        bw.write(d[end]+"\n");


        // for list
        // Arrays.fill(c, false);
        // // destination array init
        // Arrays.fill(d, INF);
        // d[start] = 0;
        // for (int k=0; k<N-1; k++) {
        //     int min = INF+1;
        //     int x = -1;
        //     for (int i=1; i<=N; i++) {
        //         if (c[i] == false && min > d[i]) {
        //             min = d[i];
        //             x = i;
        //         }
        //     }
        //     c[x] = true;
        //     for (int i=0; i<graph_list[x].size(); i++) {
        //         int v = graph_list[x].get(i).v;
        //         int w = graph_list[x].get(i).w;
        //         if (d[v] > d[x] + w) {
        //             d[v] = d[x] + w;
        //         }
        //         if (d[v] > d[x] + w) {
        //             d[v] = d[x] + w;
        //         }
        //     }
        // }
        // // print
        // for(int i=1; i<=N; i++) {
        //     bw.write(d[i]+" ");
        // }
        // bw.write("\n");
        // bw.write(d[end]+"\n");

        // list and heap
        // Arrays.fill(c, false);
        // Arrays.fill(d, INF);
        // PriorityQueue<Vertex> pq = new PriorityQueue<>();
        // d[start] = 0;
        // pq.offer(new Vertex(start, d[start]));
        // while(!pq.isEmpty()){
        //     int cost = pq.peek().w;
        //     int here = pq.peek().v;
        //     pq.poll();
        //     if(cost > d[here]) {
        //         continue;
        //     } 
        //     for (int i=0; i<graph_list[here].size(); i++) {
        //         int v = graph_list[here].get(i).v;
        //         int w = graph_list[here].get(i).w;
        //         if(d[v] > d[here] + w) {
        //             d[v] = d[here] + w;
        //             pq.offer(new Vertex(v, d[v]));
        //         }
        //     }
        // }
        // // print
        // for(int i=1; i<=N; i++) {
        //     bw.write(d[i]+" ");
        // }
        // bw.write("\n");
        // bw.write(d[end]+"\n");


        bw.flush();
    }
}