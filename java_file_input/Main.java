import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner sc = new Scanner(new File("../input.txt"));
        //Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./output.txt"));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] s = br.readLine().split(" ");

		//String s1 = sc.next();
		//String s2 = sc.next();
        
		//System.out.print(n*m);

		//BigInteger nn = new BigInteger(s1);
		//BigInteger mm = new BigInteger(s2);

		BigInteger nn = new BigInteger(s[0]);
		BigInteger mm = new BigInteger(s[1]);

		//System.out.println(nn.multiply(mm));
		bw.write(nn.multiply(mm)+"");
		bw.flush();
	}
}