import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Map.*;

public class Test_15788 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int N;
    static int T;
    static long[][] p;
    static TreeSet<Long> ts;
    static TreeSet<Long> ts_zero;
    static int zero_y;
    static int zero_x;

    static void print_arr() throws Exception {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                bw.write(p[i][j]+" ");
            }
            bw.write("\n");
        }
    }

    static void print_set() throws Exception {
        bw.write("Except_Zero : ");
        for(long i : ts) {
            bw.write(i+" ");
        }
        bw.write("\n");

        bw.write("Zero : ");
        for(long i : ts_zero) {
            bw.write(i+" ");
        }
        bw.write("\n");
    }

    static void make_arr() {
        Random r = new Random();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                p[i][j] = r.nextInt(9) + 1;
                //p[i][j] = 64;
            }
        }
    }

    static void set_zero() {
        Random r = new Random();
        zero_y = r.nextInt(N);
        zero_x = r.nextInt(N);
        //int i = 1;
        //int j = 1;
        p[zero_y][zero_x] = 0;
    }

    static void cal_set_except_zero() {
        for(int i=0; i<N; i++) {
            if(i == zero_y) {
                continue;
            }
            long sum = 0;
            for(int j=0; j<N; j++) {
                sum += p[i][j];
            }
            ts.add(sum);
        }

        for(int i=0; i<N; i++) {
            if(i == zero_x) {
                continue;
            }
            long sum = 0;
            for(int j=0; j<N; j++) {
                sum += p[j][i];
            }
            ts.add(sum);
        }

        long sum = 0;
        if(zero_y != zero_x) {
            for(int i=0; i<N; i++) {
                sum += p[i][i];
            }
            ts.add(sum);
        }

        if(zero_y + zero_x + 1 != N) {
            sum = 0;
            for(int i=0; i<N; i++) {
                sum += p[i][N-i-1];
            }
            ts.add(sum);
        }
    }

    static void cal_zero_axis() {
        if(zero_y + zero_x + 1 == N) {
            long sum = 0;
            for(int i=0; i<N; i++) {
                sum += p[i][N-i-1];
            }
            ts_zero.add(sum);
        }
        if(zero_y == zero_x) {
            long sum = 0;
            for(int i=0; i<N; i++) {
                sum += p[i][i];
            }
            ts_zero.add(sum);
        }
        long sum = 0;
        for(int j=0; j<N; j++) {
            sum += p[zero_y][j];
        }
        ts_zero.add(sum);

        sum = 0;
        for(int j=0; j<N; j++) {
            sum += p[j][zero_x];
        }
        ts_zero.add(sum);
    }

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = 3;
        T = 100000000;
        p = new long[N][N];
        ts = new TreeSet<>();
        ts_zero = new TreeSet<>();

        for(int i=0; i<T; i++) {
            make_arr();
            set_zero();
            cal_set_except_zero();
            cal_zero_axis();
            if(ts.size() == 1 && ts_zero.size() == 1 && ts.first().compareTo(ts_zero.first()) <= 0) {
                print_arr();
                print_set();
                bw.write("res : "+(ts.first()-ts_zero.first())+"\n\n");
                bw.flush();
            }
            // else {
            //     bw.write("Error\n");
            //     bw.write("Error -> ts size : "+ts.size()+"\n");
            //     bw.write("Error -> ts_zero size : "+ts_zero.size()+"\n");
            //     bw.write("Error -> ts first : "+ts.first()+"\n");
            //     bw.write("Error -> ts_zero first : "+ts_zero.first()+"\n");
            // }
            ts.clear();
            ts_zero.clear();
        }

        bw.flush();
    }
}