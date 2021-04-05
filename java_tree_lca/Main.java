import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/*
입력 Ex)
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15
*/

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

        List<Integer>[] a = new List[N+1];  // 0번 노드는 사용 X
        for (int i=1; i<=N; i++) {
            a[i] = new ArrayList<Integer>();
        }

        // 트리 구성
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            a[x].add(y);
            a[y].add(x);
        }

        int[] depth = new int[N+1];
        boolean[] check = new boolean[N+1];
        int[] parent = new int[N+1];
        int start = 1;  // 1번 노드는 root

        // root 부터 bfs로 탐색하기 위한 queue
        Queue<Integer> q = new LinkedList<Integer>();
        check[start] = true;// 해당 인덱스는 노드번호를 의미하며, 해당 노드번호의 방문여부를 저장.
        depth[start] = 0;   // 해당 인덱스는 노드번호를 의미하며, 해당 노드번호의 depth가 저장됨.
        parent[start] = 0;  // 해당 인덱스는 노드번호를 의미하며, 해당 노드번호의 부모 노드번호가 저장됨.

        q.add(start);
        while(!q.isEmpty()) {
            int x = q.remove();
            for (int y : a[x]) {
                if (check[y] == false) {
                    depth[y] = depth[x] + 1;
                    check[y] = true;
                    parent[y] = x;
                    q.add(y);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (depth[x] < depth[y]) {  // 항상 x노드의 depth가 크게끔 swap
                int temp = x;
                x = y;
                y = temp;
            }
            
            // 두 노드의 depth를 같게 해주는 작업
            while(depth[x] != depth[y]) {   // x노드와 y노드의 depth가 다르면 x노드의 depth을 한단계 낮춤. (해당 노드의 부모노드로 감)
                x = parent[x];
            }

            // 공통 조상을 찾는 과정
            while (x != y) {    // 두 노드가 서로 다르다면, 같아질때까지 x노드, y노드 서로 depth을 한단계 낮춤. (해당 노드의 부모노드로 감)
                x = parent[x];
                y = parent[y];
            }

            bw.write(x+""); // x와 y가 같아졌기 때문에 둘중 하나를 출력
            bw.newLine();
        }

        //bw.write("");
        bw.newLine();

        bw.flush();
    }
}