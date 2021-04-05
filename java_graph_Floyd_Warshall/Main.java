import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    //static String s;
    //static String[] sa;
    final static int INF = 10000; // 문제의 조건에 따라 최대치를 적절하게 설정

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //s = br.readLine();
        //sa = s.split(" +");
        int n = 5;

        //int[][] d = new int[n+1][n+1];   // 0번 정점 제외 , 1번 정점 부터 시작
        int[][] d = new int[][] {
            {0, INF, 1, 1, INF},
            {INF, 0, 1, INF, INF},
            {1, 1, 0, 1, INF},
            {1, INF, 1, 0, 1},
            {INF, INF, INF, 1, 0}
        };

        // data init
        // for(int i=0; i<5; i++) {
        //     //Arrays.fill(d[i], 0);
        // }

        // data init
        // for (int i=0; i<N; i++) {
        //     for (int j=0; j<N; j++) {
        //         if(i == j) {
        //             d[i][j] = 0;
        //             //d[i][j] = INF;
        //         }
        //         else {
        //             d[i][j] = INF;
        //         }
        //     }
        // }

        // data input
        // for(int i=0; i<M; i++) {
        //     sa = br.readLine().split(" +");
        //     int src = Integer.parseInt(sa[0]);
        //     int dst = Integer.parseInt(sa[1]);
        //     int cost = Integer.parseInt(sa[2]);  // 가중치가 있을경우
               // 데이터 입력시 조건 로직
        //     if(d[src][dst] > cost) {
        //         d[src][dst] = cost;
                   //d[dst][src] = cost; // 무방향일 경우
        //     }
        // }


        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    // 방문가능여부 확인 로직
                    // if (d[i][k] == 1 && d[k][j] == 1) {
                    //     d[i][j] = 1;
                    // }

                    // 최단거리 계산 로직
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        // 문제에 따른 정답을 위한 마무리 계산
        // int ans = -1;
        // int who = -1;
        // for (int i=0; i<n; i++) {
        //     int sum = 0;
        //     for (int j=0; j<n; j++) {
        //         sum += d[i][j];
        //     }
        //     if (ans == -1 || sum < ans) {
        //         ans = sum;
        //         who = i+1;
        //     }
        // }

        // print test
        // for (int i=0; i<n; i++) {
        //     for (int j=0; j<n; j++) {
        //         bw.write(d[i][j]+" ");
        //     }
        //     bw.write("\n");
        // }

        bw.write(who + "\n");
        bw.flush();
    }
}