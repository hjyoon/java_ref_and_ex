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

    final static int MAX = 100111;
    static List<Integer>[] a = new List[MAX];
    static int[][] p = new int[MAX][18];
    static int[] tin = new int[MAX];    // dfs로 i에 방문할 때, 몇 번째 였는지 저장
    static int[] tout = new int[MAX];   // dfs로 i에서 나갈 때, 몇 번째 였는지 저장
    static int timer;
    static int l;

    static void dfs(int v, int parent) {
        tin[v] = ++timer;

        // i가 0일땐 바로 윗 조상
        p[v][0] = parent;

        // 다이나믹 프로그래밍을 이용한 p[i][j] 계산
        // 2^i번째 조상이 없을 경우 root노드의 번호가 들어간다.
        for (int i=1; i<=l; i++) {
            p[v][i] = p[p[v][i-1]][i-1];    // 2^i번째 조상은 2^(i-1) * 2^(i-1)
        }

        for (int to : a[v]) {
            if (to != parent) {
                dfs(to, v);
            }
        }

        tout[v] = ++timer;
    }

    static boolean upper(int u, int v) {    // u가 v의 위(조상)에 있으면 true
        return (tin[u] <= tin[v] && tout[u] >= tout[v]);
    }

    static int lca(int u, int v) {
        if (upper(u, v)) {
            return u;
        }
        if (upper(v, u)) {
            return v;
        }
        for (int i=l; i>=0; i--) {
            if (!upper(p[u][i], v)) {
                u = p[u][i];
            }
        }
        return p[u][0]; // u의 바로 윗 부모노드 번호를 리턴
    }

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

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

        l = 1;
        while((1<<l) <= N) {
            l++;
        }
        l--;

        // 1번 노드를 root로 설정
        dfs(1, 1);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int res = lca(x, y);
            bw.write(res+"");
            bw.newLine();
        }

        //bw.write("");
        //bw.newLine();

        bw.flush();
    }
}