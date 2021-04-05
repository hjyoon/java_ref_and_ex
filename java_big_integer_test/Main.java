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

        int i = Integer.parseInt("101011101", 2); // 2진법으로 입력받음
 
        String binaryString = Integer.toBinaryString(i);
        String octalString = Integer.toOctalString(i);
        String hexString = Integer.toHexString(i);
         
        bw.write(binaryString+"\n");
        bw.write(octalString+"\n");
        bw.write(hexString+"\n");
        bw.write(Integer.toString(i, 3)+"\n"); // 3진법

        //BigInteger bi = new BigInteger("1024");
        BigInteger bi = new BigInteger("1011", 2); // 2진수로 읽어들인다.

        bi = bi.add(BigInteger.TEN);
        bi = bi.subtract(BigInteger.TEN);
        bi = bi.multiply(BigInteger.TEN);
        bi = bi.divide(BigInteger.TEN);
        bi = bi.remainder(BigInteger.TEN);

        bw.write(bi.toString(2));   // 2진수로 출력

        // bw.write("\n");
        // int[][] a = new int[10][10];
        // a[0][3] = 1;
        // for(int tmp : a[0]) {
        //     bw.write(tmp+" ");
        // }






        bw.flush();
    }
}