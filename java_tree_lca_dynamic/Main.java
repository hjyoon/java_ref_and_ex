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
        br = new BufferedReader(new FileReader("../input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        List<Integer>[] a = new List[N+1];  // 0번 노드는 사용 X
        for (int i=1; i<=N; i++) {
            a[i] = new ArrayList<>();
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
        Queue<Integer> q = new LinkedList<>();
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

        int log = 1;
        while((1<<log) <= N) {
            log++;
        }
        log--;

        int[][] p = new int[N+1][log+1];    // p[i][j] : 노드 i의 2^j번째 조상
        for(int i=1; i<=N; i++) {
            p[i][0] = parent[i];    // j가 0인 경우를 미리 계산
        }

        // 다이나믹 프로그래밍을 이용한 p[i][j] 계산
        for(int j=1; (1<<j)<N; j++) {
            for(int i=1; i<=N; i++) {
                if(p[i][j-1] != 0) {
                    p[i][j] = p[p[i][j-1]][j-1];    // 2^j번째 조상은 2^(j-1) * 2^(j-1)
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(depth[x] < depth[y]) {  // 항상 x노드의 depth가 크게끔 swap
                int temp = x;
                x = y;
                y = temp;
            }

            log = 1;
            while((1<<log) <= depth[x]) {
                log += 1;
            }
            log -= 1;

            // x의 depth를 y의 depth에 맞추는 작업
            for(int i=log; i>=0; i--) {
                if(depth[x] - (1<<i) >= depth[y]) {
                    x = p[x][i];
                }
            }

            // x와 y가 같은 노드일 경우, 둘중 하나를 출력
            if(x == y) {
                bw.write(x+"");
                bw.newLine();
            }
            else {
                // 공통 조상을 찾는 과정
                for(int i=log; i>=0; i--) {
                    // p[x][i] == p[y][i] 면 공통조상이 같다.
                    if(p[x][i] != 0 && p[x][i] != p[y][i]) {
                        x = p[x][i];
                        y = p[y][i];
                    }
                }
                bw.write(parent[x]+"");
                bw.newLine();
            }
        }

        //bw.write("");
        bw.newLine();

        bw.flush();
    }
}