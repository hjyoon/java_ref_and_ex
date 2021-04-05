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
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //st = new StringTokenizer(br.readLine());

        //BigDecimal a = new BigDecimal("10");
        BigDecimal a = BigDecimal.valueOf(10);
        BigDecimal b = new BigDecimal("3");

        //bw.write(a.divide(b, MathContext.DECIMAL32)+"");    // 소수 6번째 자리까지 출력
        //bw.write(a.divide(b, MathContext.DECIMAL64)+"");       // 소수 15번째 자리까지 출력
        //bw.write(a.divide(b, MathContext.DECIMAL128)+""); // 소수 33번째 자리까지 출력
        //bw.write(a.divide(b, new MathContext(50))+"");   // 50입력시 소수 49번째 자리까지 출력
        bw.write(a.divide(b, 3, RoundingMode.HALF_UP)+"");  // 소수 3번째 자리까지 반올림
        bw.newLine();
        
        bw.flush();
    }
}