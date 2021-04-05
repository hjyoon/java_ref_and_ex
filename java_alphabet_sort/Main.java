import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
	static BufferedReader br;
    static BufferedWriter bw;
    //static String[] s;

	public static void main(String args[]) throws Exception {
		//br = new BufferedReader(new FileReader("../input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		//String s = br.readLine();
		String s = "bdac";
		String[] a = s.split("");
		Arrays.sort(a);
		bw.write(String.join("", a) + "\n");

		String s2 = "bdac";
		char[] a2 = s2.toCharArray();
		Arrays.sort(a2);
		for(char c : a2) {
			bw.write(c + "");
		}
		bw.write("\n");

		String s3 = "bdac";
		s3.chars().mapToObj(i -> (char)i).sorted().forEach(c -> {
			try {
				bw.write(c); 
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		});
		//Arrays.sort(a3);
		// for(Character c : a3) {
		// 	bw.write(c + "");
		// }
		bw.write("\n");

		String[] s4 = {"Bravo", "Delta", "Alpha", "Charlie"};
		Arrays.sort(s4);
		bw.write(String.join(" ", s4) + "\n");
		
		List<Character> lc = new ArrayList<>();
		lc.add('b');
		lc.add('d');
		lc.add('a');
		lc.add('c');
		Collections.sort(lc);
		for(char c : lc) {
			bw.write(c + "");
		}
		bw.write("\n");

		List<String> ls = new ArrayList<>();
		ls.add("Bravo");
		ls.add("Delta");
		ls.add("Alpha");
		ls.add("Charlie");
		Collections.sort(ls);
		//bw.write(ls + "\n");
		ls.forEach(str -> {
			try {
				bw.write(str + " "); 
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		});
		bw.write("\n");


		bw.flush();
		//bw.close();
	}
}