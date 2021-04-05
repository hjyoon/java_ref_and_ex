import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Map.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> al = new ArrayList<>();
        LinkedList<Integer> ll = new LinkedList<>();

        Random r = new Random();
        for(int i=0; i<40; i++) {
            int tmp = r.nextInt(100);
            al.add(tmp);
        }
        for(int i : al) {
            bw.write(i+" ");
        }
        bw.write("\n");

        HashSet<Integer> hs = new HashSet<>();  // 저장된 데이터에 순서가 없음
        //hs.addAll(al);
        for(int i : hs) {
            bw.write(i+" ");
        }
        bw.write("\n");

        TreeSet<Integer> ts = new TreeSet<>(hs);  // 입력된 순서대로 저장 X, 오름차순으로 자동정렬
        for(int i : ts) {
            bw.write(i+" ");
        }
        bw.write("\n");


        LinkedHashSet<Integer> lhs = new LinkedHashSet<>(hs);  // 입력된 순서대로 값이 저장됨
        for(int i : lhs) {
            bw.write(i+" ");
        }
        bw.write("\n");

        


        bw.flush();
    }
}