import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        List<Integer>[] a = (List<Integer>[]) new List[N+1];
        for (int i=1; i<=N; i++) {
            a[i] = new ArrayList<Integer>();
        }
        int[] ind = new int[N+1];   // indegree 해당 노드를 가리키는 노드의 개수

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            a[x].add(y);    // x노드가 y노드를 가리킴
            ind[y]++;    // y노드의 indegree를 1 증가시킴
        }

        //Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();    // default : natural ordering (min heap)
        for (int i=1; i<=N; i++) {
            if (ind[i] == 0) {  // indegree가 0인 노드부터 큐에 넣어서 탐색을 시작
                //q.add(i);
                q.offer(i);
            }
        }

        for (int k=0; k<N; k++) {
            int x = q.remove();
            bw.write(x+" ");    // 출력
            for (int y : a[x]) {
                ind[y]--;   // x노드가 가리키는 노드의 indegree를 감소시킴
                if (ind[y] == 0) {  // x노드가 가리키는 노드의 indegree가 0이면 그 노드를 큐에 넣음
                    //q.add(y);
                    q.offer(y);
                }
            }
        }

        bw.newLine();
        bw.flush();
    }
}