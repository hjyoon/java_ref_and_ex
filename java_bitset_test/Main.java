import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //BitSet bs = new BitSet();
        //bs.set(3);  // 인덱스 3의 비트를 true로 설정
        //bs.set(5, false);   // 인덱스 5의 비트를 false로 설정
        //bs.set(9, 13); // bs.set(9, 13, true); 와 같다. 인덱스 9~12의 비트를 true로 설정
        //bs.set(9, 13, false); // 인덱스 9~12의 비트를 false로 설정

        //bs.get(3);  // 인덱스 3의 비트를 리턴

        //bs.clear(); // 모든 인덱스의 비트를 false로 설정

        //int n = bs.cardinality();   // true인 비트의 개수를 리턴

        //bs.isEmpty(); // true인 비트가 하나도 없을경우 true, 하나라도 있으면 false

        //int l = bs.length();    // the index of the highest set bit in the BitSet plus one
        //int size = bs.size();   // BitSet 에서 실제로 사용중인 공간의 비트 수를 리턴 (64의 배수 형태를 가지는듯?)

        //bs.flip(0); // 해당 인덱스의 비트를 뒤집는다. (true -> false, false -> true)
        //bs.flip(0, 10); // 해당 인덱스 범위의 비트를 뒤집는다. (true -> false, false -> true)

        // BitSet bs = new BitSet();
        // bs.set(0, 10);

        // BitSet bs2 = new BitSet();
        // bs2.set(4, 7);

        // BitSet bs3 = (BitSet)bs.clone();

        //bs.intersects(bs2);  // 겹치는 bit가 하나라도 있을경우 true, 하나도 겹치지 않으면 false

        //bs.and(bs2);  // 같으면 1 (교집합)
        //bs.andNot(bs2); // 같으면 0

        //bs.or(bs2); // 합집합

        //bs.xor(bs2);    // 같으면 0, 다르면 1

        //bs3.equals(bs2);  // bs3과 bs2가 같으면

        // bw.write(bs+"\n");
        // bw.write(bs2+"\n");
        // bw.write(bs3+"\n");

        //BitSet bs = new BitSet();
        //bs.set(4, 7);

        //bs.nextClearBit(4);   // 지정된 시작 인덱스 이후에 발생하는 false로 설정된 첫 번째 비트의 인덱스를 리턴
        //bs.nextSetBit(4); // 지정된 시작 인덱스 이후에 발생하는 true로 설정된 첫 번째 비트의 인덱스를 리턴

        //bs.previousClearBit(6); //  지정된 시작 인덱스 또는 그 이전에 발생하는 false로 설정된 가장 가까운 비트의 인덱스를 반환합니다.
        //bs.previousSetBit(10);   //  지정된 시작 인덱스 또는 그 이전에 발생하는 true로 설정된 가장 가까운 비트의 인덱스를 반환합니다.

        //bw.write(bs.previousSetBit(10)+"\n");

        BitSet bs = new BitSet();
        bs.set(4, 10);

        //byte[] b = bs.toByteArray(); // -1, 3 이 들어감. (1111111/111) 이렇게 끊어서 입력된다.

        //BitSet bs2 = BitSet.valueOf(b); // 반대로, byte배열로 입력한다. (0~9 비트는 true)

        int[] arr = bs.stream().toArray();  // int배열에 true인 비트의 인덱스가 입력된다.
        for(int i : arr) {
            bw.write(i+" ");
        }


        bw.flush();
    }
}