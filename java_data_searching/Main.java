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

        int[] arr = new int[80];
        ArrayList<Integer> al = new ArrayList<>();
        TreeSet<Integer> ts = new TreeSet<>(al);  // 입력된 순서대로 저장 X, 오름차순으로 자동정렬

        Random r = new Random();
        for(int i=0; i<80; i++) {
            int tmp = r.nextInt(160);
            arr[i] = tmp;
            al.add(tmp);
            ts.add(tmp);
        }

        Arrays.sort(arr);
        Collections.sort(al);

        for(int i : arr) {
            bw.write(i+" ");
        }
        bw.write("\n");

        for(int i : al) {
            bw.write(i+" ");
        }
        bw.write("\n");

        for(int i : ts) {
            bw.write(i+" ");
        }
        bw.write("\n");

        bw.write(Arrays.binarySearch(arr, 50)+"\n"); // 두번ㅤㅉㅒㅤ 인자의 값이 저장된 인덱스를 리턴
        bw.write(Collections.binarySearch(al, 50)+"\n");

        ArrayList<Integer> al2 = new ArrayList<>();
        al2.add(22);
        al2.add(23);

        bw.write(Collections.indexOfSubList(al, al2)+"\n");

        //Collections.rotate(al, 1);    모든 인덱스가 +1,  맨 마지막은 맨 처음으로
        //Collections.rotate(al, -1);   모든 인덱스가 -1,  맨 처음은 맨 끝으로

        for(int i : al) {
            bw.write(i+" ");
        }
        bw.write("\n");

        // int[] arr2 = new int[80];
        // for(int i=0; i<80; i++) {
        //     int tmp = r.nextInt(160);
        //     arr2[i] = tmp;
        // }

        // Arrays.sort(arr2);

        // for(int i : arr2) {
        //     bw.write(i+" ");
        // }
        // bw.write("\n");

        // bw.write(Arrays.mismatch(arr, arr2)+"\n");  // 두 배열중 처음으로 서로 일치하지 않는 인덱스를 리턴

        bw.flush();
    }
}