import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Map.*;
import java.util.stream.*;

// 10 ~ 99
class Haeyoung {
    static void run() throws Exception {
        // Random r = new Random(System.currentTimeMillis());
        // int N = 8;
        // int[] num = new int[N];
        // for(int i=0; i<N; i++) {
        //     int tmp = r.nextInt(90) + 10; // 10~99

        //     num[i] = tmp;
        //     System.out.println(tmp);
        // }

        IntStream is_distinct_test = new Random().ints(8, 10, 100); // 10~99
        int[] num = is_distinct_test.distinct().toArray();

        ArrayList<Integer> al = new ArrayList<>(); // 16개의 숫자가 저장됨
        for(int i=0; i<8; i++) {
            al.add(num[i]);
            al.add(num[i]);
        }

        Collections.shuffle(al);

        int[][] map = new int[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                map[i][j] = al.get(i*4+j);
            }
        }

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.print("\n");
        }

    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Random r = new Random();
        //Random r = new Random(seed);
        //Random r = new Random(System.currentTimeMillis());
        //r.setSeed(10);
        int N = 100;
        //double[] p = new double[N];
        double[] p = new double[N];

        for(int i=0; i<N; i++) {
            //p[i] = r.nextInt(10); // 0~9
            p[i] = r.nextDouble();
            //p[i] = r.nextGaussian();
        }

        Arrays.sort(p);

        // print
        // for(int i=0; i<N; i++) {
        //     bw.write(p[i]+"\n");
        // }

        Haeyoung.run();

        bw.flush();
    }
}