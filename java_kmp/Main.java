import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static void print(ArrayList<Integer> al) throws Exception {
        for (int i : al) {
            bw.write(i+" ");
        }
        bw.newLine();
    }

    static ArrayList<Integer> preprocessing(String p) throws Exception {
        int m = p.length();
        ArrayList<Integer> pi = new ArrayList<>(m);
        for(int i=0; i<m; i++) {
            pi.add(0);
        }
        //pi.set(0, 0);
        int j = 0;
        for (int i=1; i<m; i++) {
            while (j>0 && p.charAt(i) != p.charAt(j)) {
                j = pi.get(j-1);
            }
            if (p.charAt(i) == p.charAt(j)) {
                pi.set(i, j+1);
                j += 1;
            }
            else {
                pi.set(i, 0);
            }
        }
        return pi;
    }

    static ArrayList<Integer> kmp(String s, String p) throws Exception {
        ArrayList<Integer> pi = preprocessing(p);
        //print(pi);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = s.length();
        int m = p.length();
        int j = 0;
        for(int i=0; i<n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pi.get(j-1);
            }
            if (s.charAt(i) == p.charAt(j)) {
                if (j == m-1) {
                    ans.add(i-m+1);
                    j = pi.get(j);
                }
                else {
                    j += 1;
                }
            }
        }
        return ans;
    }

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = "ABC ABCDAB ABCDABCDABDE";
        String P = "ABCDABD";

        ArrayList<Integer> matched = kmp(S, P);

        bw.write(matched.size()+"");
        bw.newLine();

        for (int index : matched) {
            bw.write(index+1+" ");
        }
        bw.newLine();
        bw.flush();
    }
}