import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
	static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		//br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5}));     // 1 2 3 4 5
        ArrayList<Integer> al2 = new ArrayList<>(Arrays.asList(new Integer[] {4, 5, 6, 7, 8}));    // 1 2 3 4 5
        ArrayList<Integer> al3 = new ArrayList<>(Arrays.asList(new Integer[] {6, 7, 8, 9, 10}));   // 6 7 8 9 10

        //HashSet<Integer> hs = new HashSet<>(al);
        //HashSet<Integer> hs2 = new HashSet<>(al2);

        // list -> set
        //hs.addAll(al);
        //hs2.addAll(al2);

        //al.addAll(al2);     // al에 al2의 모든 원소를 추가한다. (al = al + al2)
        //al.retainAll(al2);  // al와 al2의 겹치는 원소들만으로 al을 새로 구성한다.   (al = al and al2)
        al.removeAll(al2);    // al와 al2의 겹치는 모든 원소들을 al에서 제거한다.    (al = al - al2)

        //al.add();
        //al.remove();
        //al.contains();

        bw.write(Collections.disjoint(al, al2)+"\n");	// 서로 겹치는 원소가 없으면 true, 있으면 false

        for(int i : al) {
            bw.write(i+" ");
        }

        bw.write("\n");
        bw.flush();
	}
}