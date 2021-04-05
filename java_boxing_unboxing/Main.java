import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    //static String[] s;

    public static void main(String args[]) throws Exception {
    	//br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        //Integer a = 10; //boxing : 참조형 -> 기본형
        Integer a = Integer.valueOf("10");
        //int j = new Integer(20);  // Deprecated
        //int b = Integer.valueOf(20);  // unboxing : 기본형 -> 참조형
        int b = Integer.valueOf("20");

        bw.write(a + "\n");
        bw.write(b + "\n");

        int[] arr = {3, 1, 2, 5, 4};
        Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);    // int -> Integer (Method Reference)
        //int[] arr3 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();
        int[] arr3 = Arrays.stream(arr2).mapToInt(i->i).toArray();  // Integer -> int

        for(int i=0; i<arr2.length; i++) {
            bw.write(arr2[i] + " ");
        }
        bw.write("\n");
        for(int i=0; i<arr3.length; i++) {
            bw.write(arr3[i] + " ");
        }
        bw.write("\n");

        //Integer tmp = 20;
        //Integer tmp2 = 10;
        //Integer tmp = new Integer(20);    // Deprecated
        //Integer tmp2 = new Integer(10);   // Deprecated
        //Integer tmp = new Integer("20");  // Deprecated
        //Integer tmp2 = new Integer("10"); // Deprecated

        // int i1 = Integer.parseInt("100", 2);    // 100(2) == 4
        // int i2 = Integer.parseInt("100", 8);    // 100(8) == 64
        // int i3 = Integer.parseInt("100", 16);    // 100(16) == 256
        // int i4 = Integer.parseInt("FF", 16); // FF(16) == 255
        // int i5 = Integer.parseInt("FF");    //진법을 생략하면 10진수로 간주해서 NumberFormatException 발생

        // int i1 = Integer.valueOf("100", 2);    // 100(2) == 4
        // int i2 = Integer.valueOf("100", 8);    // 100(8) == 64
        // int i3 = Integer.valueOf("100", 16);   // 100(16) == 256
        // int i4 = Integer.valueOf("FF", 16); // FF(16) == 255
        // int i5 = Integer.valueOf("FF");    //진법을 생략하면 10진수로 간주해서 NumberFormatException 발생

        bw.write(Integer.toBinaryString(200)+"\n"); // 10진수 값을 2진법 문자열로 반환
        bw.write(Integer.toHexString(200)+"\n");    // 10진수 값을 16진법 문자열로 반환
        bw.write(Integer.toOctalString(200)+"\n");  // 10진수 값을 8진법 문자열로 반환

        // bw.write((tmp+tmp2)+"\n");
        // bw.write((tmp-tmp2)+"\n");
        // bw.write((tmp*tmp2)+"\n");
        // bw.write((tmp/tmp2)+"\n");
        // bw.write((tmp%tmp2)+"\n");

        // if(tmp > tmp2) {
        //     bw.write("tmp is bigger\n");
        // }
        // else if(tmp < tmp2) {
        //     bw.write("tmp2 is bigger\n");
        // }
        // else {
        //     bw.write("tmp and tmp2 is same\n");
        // }

        //int tmp = Integer.bitCount(11); // 값을 2진법으로 표현했을때 1의 개수
        //int tmp = Integer.reverse(11); // 비트를 모두 뒤집는다
        //int tmp = Integer.rotateLeft(1, 5);   // 두번째 인자만큼 왼쪽으로 비트회전연산
        int tmp = Integer.rotateRight(32, 5);   // 두번째 인자만큼 오른쪽으로 비트회전연산
        bw.write(tmp+"\n");

		bw.flush();
    }
}