import java.io.*;
import java.math.*;     
import java.util.*;

class Pair {
    int y;
    int x;
    Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // map size
        int H = 7;
        int W = 7;

        // init map
        int[][] map = {
            {0, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 0, 0, 1, 0},
        };

        // 4 direction
        // int[] dx = {1, 0, -1, 0};
        // int[] dy = {0, 1, 0, -1};

        // 8 direction
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


        int[][] d = new int[H][W];  // visit 대신 걸음 수를 저장 할 변수
        int[][] search_order = new int[H][W];  // 탐색 순서를 저장 할 변수 (출력용)
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                d[i][j] = -1;
                search_order[i][j] = -1;
            }
        }

        // (0, 0) 에서 출발
        int start_y = 0;
        int start_x = 0;
        d[start_y][start_x] = 0;
        search_order[start_y][start_x] = 0;

        // 탐색 횟수
        int cnt = 1;

        // use Queue (bfs)
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(start_y, start_x));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int now_y = p.y;
            int now_x = p.x;
            for (int k=0; k<dy.length; k++) {
                int new_y = now_y + dy[k];
                int new_x = now_x + dx[k];
                if (0 <= new_x && new_x < W && 0 <= new_y && new_y < H) {
                    if (d[new_y][new_x] == -1) {
                        if (map[new_y][new_x] == 0) {
                            d[new_y][new_x] = d[now_y][now_x] + 1;
                            search_order[new_y][new_x] = cnt++;
                            q.offer(new Pair(new_y, new_x));
                        }
                    }
                }
            }
        }

        // use Stack (dfs)
        // Stack<Pair> stk = new Stack<>();
        // stk.push(new Pair(start_y, start_x));
        // label : while(!stk.isEmpty()) {
        //     Pair p = stk.peek();
        //     int now_y = p.y;
        //     int now_x = p.x;

        //     for (int k=0; k<dy.length; k++) {
        //         int new_y = now_y + dy[k];
        //         int new_x = now_x + dx[k];
        //         if(0 <= new_x && new_x < W && 0 <= new_y && new_y < H) {
        //             if(d[new_y][new_x] == -1) {
        //                 if(map[new_y][new_x] == 0) {
        //                     d[new_y][new_x] = d[now_y][now_x] + 1;
        //                     search_order[new_y][new_x] = cnt++;
        //                     stk.push(new Pair(new_y, new_x));
        //                     continue label;
        //                 }
        //             }
        //         }
        //     }

        //     stk.pop();
        // }

        // print result
        bw.write("탐색 순서\n");
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(d[i][j] != -1) {
                    bw.write(String.format("%02d ", search_order[i][j]));
                }
                else {
                    bw.write(String.format("XX ", search_order[i][j]));
                }
            }
            bw.newLine();
        }

        // print result
        bw.write("\n최단 거리 (if only BFS)\n");
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(d[i][j] != -1) {
                    bw.write(String.format("%02d ", d[i][j]));
                }
                else {
                    bw.write(String.format("XX ", d[i][j]));
                }
            }
            bw.newLine();
        }

        bw.flush();
    }
}