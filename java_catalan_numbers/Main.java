import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    final static int MOD = 1000000007;

    static long cal(int n, long[] dp) {
        if(dp[n] != 0) {
            return dp[n];
        }
        else {
            long sum = 0;
            for(int i=0; i<=n-1; i++) {
                sum += (cal(i, dp) % MOD) * (cal(n-1-i, dp) % MOD);
                sum %= MOD;
            }
            dp[n] = sum;
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = 6800;

        long[] dp = new long[num+1];
        dp[0] = 1;

        // using recursive function
        // cal(num, dp);

        // using loop
        for (int i=1; i<=num; i++) {
            dp[i] = 0;
            for (int j=0; j<i; j++) {
                dp[i] += dp[j] * dp[i-1-j];
                dp[i] %= MOD;
            }
            dp[i] %= MOD;
        }

        // print test
        for(int i=0; i<=num; i++) {
            bw.write(i+" "+dp[i]+"\n");
        }

        // print : 1 1 2 5 14 42 132 429 1430 4862 ...

        bw.flush();
    }
}