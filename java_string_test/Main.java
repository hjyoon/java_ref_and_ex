import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = "cba";

        //str = str.replace("c","a"); // 모든 c를 찾아서 a로 바꿈
        //str = str.replaceAll("[a-z]{2}","x"); // 모든 정규표현식을 찾아서 x로 바꿈

        //char[] c = {'c', 'b', 'a'};
        //char[] c = str.toCharArray();
        //Character[] c = new Character[4];
        Character[] c = {'B', 'A', 'D', 'C'};
        // c[0] = 'B';
        // c[1] = 'A';
        // c[2] = 'D';
        // c[3] = 'C';

        //Arrays.sort(c);
        Arrays.sort(c, Comparator.reverseOrder());

        for(char tmpc : c) {
            bw.write(tmpc+"");
        }
        bw.write("\n");

        //StringBuffer sb = new StringBuffer("def"); // 멀티스레드 환경에서 사용
        StringBuilder sb = new StringBuilder("def"); // 단일스레드 환경에서 사용
        
        //String str2 = new String(sb);
        String str2 = sb.toString();    // StringBuffer -> String
        bw.write(str2+"\n");

        sb.append('g');
        sb.insert(2, "aaa");
        //sb.deleteCharAt(0);
        //sb.delete(0, 1);
        sb.setCharAt(0, 'z');
        //sb.replace(0, 2, "ppp");
        bw.write(sb.indexOf("aaa")+"\n");
        bw.write(sb+"\n");
        bw.write(sb.reverse()+"\n");

        char[] sb_c = new char[16];
        sb.getChars(0, 3, sb_c, 0);
        bw.write("sb_c : ");
        for(int i=0; i<sb_c.length; i++) {
            bw.write(sb_c[i]+"");
        }
        bw.write("\n");

        ArrayList<Character> al = new ArrayList<>();
        //al.set(0, 'g');
        al.add('g');
        al.add('h');
        al.add('i');
        al.add('j');
        al.add('k');

        //Collections.reverse(al);
        Collections.shuffle(al);
        //Collections.rotate(al, -1);
        Collections.sort(al);
        //Collections.sort(al, Comparator.reverseOrder());

        // Collections.sort(al, new Comparator<String>() {
        //     public int compare(String s1, String s2) {
        //         if(s1.length() < s2.length()) {
        //             return -1;
        //         }
        //         else if(s1.length() > s2.length()) {
        //             return 1;
        //         }
        //         else {
        //             int sum_s1 = 0, sum_s2 = 0;
        //             for(int i=0; i<s1.length(); i++) {
        //                 if(s1.charAt(i) >= '0' && s1.charAt(i) <= '9') {
        //                     sum_s1 += s1.charAt(i)-'0'; 
        //                 }
        //             }
        //             for(int i=0; i<s2.length(); i++) {
        //                 if(s2.charAt(i) >= '0' && s2.charAt(i) <= '9') {
        //                     sum_s2 += s2.charAt(i)-'0'; 
        //                 }
        //             }
        //             if(sum_s1 < sum_s2) {
        //                 return -1;
        //             }
        //             else if(sum_s1 > sum_s2) {
        //                 return 1;
        //             }
        //             else {
        //                return s1.compareTo(s2);
        //            }
        //         }
        //     }
        // });

        for(Character cc : al) {
            bw.write(cc+"");
        }
        bw.write("\n");

        bw.write(Collections.max(al)+"\n");
        bw.write(Collections.min(al)+"\n");


        String str3 = new String(
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"+
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n"+
            "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n"+
            "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            );

        str3.lines().forEach(strr -> {  // 엔터를 기준으로 분리 후, 스트림에 넣음
            try {
                bw.write(strr+"\n");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        });

        String s = "a b   c";
        //String[] ss = s.split(" ");   // 공백 한개를 기준으로 구분
        //String[] ss = s.split("\\s+");  // 공백이 2개 이상이 들어가도 정상적으로 처리
        String[] ss = s.split(" +");  // 공백이 2개 이상이 들어가도 정상적으로 처리
        for(String s2 : ss) {
            bw.write(s2+"\n");
        }

        //bw.write(str3);
        bw.flush();
    }
}