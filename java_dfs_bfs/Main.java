import java.io.*;
import java.math.*;     
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void dfs_for_array(int start, int num_vertex, int[][] graph, boolean[] visit) throws Exception {
        if(visit[start]) {
            return;
        }

        visit[start] = true;

        bw.write(start+" ");

        for(int i=1; i<=num_vertex; i++) {
            if(graph[start][i] != 0 && visit[i] == false) {
                dfs_for_array(i, num_vertex, graph, visit);
            }
        }
    }

    public static void dfs_for_array_non_recursive(int start, int num_vertex, int[][] graph, boolean[] visit) throws Exception {
        Stack<Integer> stk = new Stack<>();
        stk.push(start);

        while(!stk.empty()) {
            int now = stk.pop();
            if(visit[now] == true) {
                continue;
            }

            visit[now] = true;

            bw.write(now+" ");
            
            for(int i=num_vertex; i>=1; i--) {
                if(graph[now][i] != 0) {
                    stk.push(i);
                }
            }

        }
    }

    public static void dfs_for_list(int start, int num_vertex, ArrayList<Integer>[] graph, boolean[] visit) throws Exception {
        if(visit[start]) {
            return;
        }

        visit[start] = true;

        bw.write(start+" ");

        for (int y : graph[start]) {
            if(visit[y] == false) {
                dfs_for_list(y, num_vertex, graph, visit);
            }
        }
    }

    public static void dfs_for_list_non_recursive(int start, int num_vertex, ArrayList<Integer>[] graph, boolean[] visit) throws Exception {
        Stack<Integer> stk = new Stack<>();
        stk.push(start);

        while(!stk.empty()) {
            int now = stk.pop();
            if(visit[now] == true) {
                continue;
            }
            bw.write(now+" ");
            visit[now] = true;
            for(int i=graph[now].size()-1; i>=0; i--) {
                stk.push(graph[now].get(i));
            }

        }
    }

    public static void bfs_for_array(int start, int num_vertex, int[][] graph, boolean[] visit) throws Exception {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        visit[start] = true;
        while(!q.isEmpty()) {
            int x = q.remove();

            bw.write(x+" ");

            for(int i=1; i<=num_vertex; i++) {
                if(graph[x][i] != 0 && visit[i] == false) {
                    visit[i] = true;
                    q.add(i);
                }
            }

            // for(int y : graph[x]) {
            //     if(visit[y] == false) {
            //         visit[y] = true;
            //         q.add(y);
            //     }
            // }
        }
    }

    public static void bfs_for_list(int start, int num_vertex, ArrayList<Integer>[] graph, boolean[] visit) throws Exception {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        visit[start] = true;
        while(!q.isEmpty()) {
            int x = q.remove();

            bw.write(x+" ");

            // for(int i=1; i<=num_vertex; i++) {
            //     if(graph[x][i] != 0 && visit[i] == false) {
            //         visit[i] = true;
            //         q.add(i);
            //     }
            // }

            for(int y : graph[x]) {
                if(visit[y] == false) {
                    visit[y] = true;
                    q.add(y);
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new FileReader("input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num_vertex = Integer.parseInt(br.readLine());   // 노드 개수
        int num_edge = Integer.parseInt(br.readLine());     // 엣지 개수

        // 인접행렬 선언
        int[][] graph_array = new int[num_vertex+1][num_vertex+1];  // 0번 노드제외, 1번 노드부터 시작

        // 인접리스트 선언
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph_list = new ArrayList[num_vertex+1];
        for(int i=1; i<=num_vertex; i++) {
            graph_list[i] = new ArrayList<>();
        }

        boolean[] visit = new boolean[num_vertex+1];

        // input data
        for(int i=0; i<num_edge; i++) {
            String[] s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);

            // 인접행렬에 데이터 추가
            graph_array[u][v] = 1;    // 가중치가 없으므로 1로 통일
            graph_array[v][u] = 1;    // 무방향이므로 반대편도 똑같이 연결한다

            // 인접리스트에 데이터 추가
            graph_list[u].add(v);
            graph_list[v].add(u);
        }

        // 방문할 수 있는 정점이 여러 개인 경우, 정점 번호가 작은 것을 먼저 방문하기 위해 정렬
        for(int i=1; i<=num_vertex; i++) {
            Collections.sort(graph_list[i]);
        }

        // 인접 행렬 출력
        // for(int i=1; i<=num_vertex; i++) {
        //     for(int j=1; j<=num_vertex; j++) {
        //         bw.write(graph_array[i][j]+" ");
        //     }
        //     bw.write("\n");
        // }

        // 인접 리스트 출력
        // for(int i=1; i<=num_vertex; i++) {
        //     for(int j : graph_list[i]) {
        //         bw.write(j+" ");
        //     }
        //     bw.write("\n");
        // }

        bw.write("dfs_for_array : ");
        dfs_for_array(1, num_vertex, graph_array, visit);
        bw.write("\n");

        Arrays.fill(visit, false);

        bw.write("bfs_for_array : ");
        bfs_for_array(1, num_vertex, graph_array, visit);
        bw.write("\n");

        Arrays.fill(visit, false);

        bw.write("dfs_for_list : ");
        dfs_for_list(1, num_vertex, graph_list, visit);
        bw.write("\n");

        Arrays.fill(visit, false);
        
        bw.write("bfs_for_list : ");
        bfs_for_list(1, num_vertex, graph_list, visit);
        bw.write("\n");

        Arrays.fill(visit, false);

        bw.write("dfs_for_array_non_recursive : ");
        dfs_for_array_non_recursive(1, num_vertex, graph_array, visit);
        bw.write("\n");

        Arrays.fill(visit, false);

        bw.write("dfs_for_list_non_recursive : ");
        dfs_for_list_non_recursive(1, num_vertex, graph_list, visit);
        bw.write("\n");

        bw.flush();
    }
}