import java.io.*;
import java.util.regex.*;

public class Java_regex_test{
	static BufferedReader br;
    static BufferedWriter bw;

	public static void main (String args[]) throws Exception {
		//br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// [a-z0-9_-]이 3개 ~ 16개
 		// String s = "my-us3r_n4m3";	// 매칭 O
 		// //String s = "the12312312333344";	// 매칭 X (3~16개 범위를 벗어남)
 		// Pattern p = Pattern.compile("^[a-z0-9_-]{3,16}$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

 		// #? : #이 1개이거나 0개이거나, ([a-f0-9]{6}|[a-f0-9]{3}) : [a-f0-9]가 6개 또는  [a-f0-9]가 3개
 		// String s = "#ffffff";	// 매칭 O
 		// String s = "#fff";	// 매칭 O
 		// String s = "fff";	// 매칭 O
 		// String s = "#4d82h4";	// 매칭 X (h가 포함되어 있음)
 		// Pattern p = Pattern.compile("^#?([a-f0-9]{6}|[a-f0-9]{3})$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

 		// + : 1개 이상
 		// String s = "my-title-here";	// 매칭 O
 		// String s = "my_title_here";	// 매칭 X (언더스코어( _ ) 가 포함되어 있음)
 		// Pattern p = Pattern.compile("^[a-z0-9-]+$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

 		// \d : 숫자
 		// String s = "john@doe.com";	// 매칭 O
 		// String s = "john@doe.something";	// 매칭 X (TLS가 너무 김)
 		// Pattern p = Pattern.compile("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

		// * : 0개 이상
 		// String s = "http://net.tutsplus.com/about";	// 매칭 O
 		// String s = "http://google.com/some/file!.html";	// 매칭 X (느낌표가 포함되어 있음)
 		// Pattern p = Pattern.compile("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w_\\.-]*)*\\/?$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

 		// ?: : 괄호 안의 그룹을 캡쳐하지 않음
 		// String s = "73.60.124.136";	// 매칭 O
 		// String s = "256.60.124.136";	// 매칭 X (첫번째 숫자는 250~255이어야 함)
 		// Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

 		// [^ ... ] : 문자클래스 안의 문자 제외, \s : 공백문자, \1 : 캡쳐된 첫번째 그룹
 		// String s = "<a href=\"http://net.tutsplus.com/\">Nettuts+</a>";	// 매칭 O
 		//String s = "<img src=\"img.jpg\" alt=\"My image>\" />";	// 매칭 X (속성은 >기호를 가질 수 없음)
 		// Pattern p = Pattern.compile("^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$");
 		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

        // ?<foo> : 캡쳐 그룹에 foo라는 이름을 지정, \k<foo> : foo 으로 지정한 그룹을 참조
 		// String s = "aa";	// 매칭 O
 		// String s = "bb";	// 매칭 O
 		// String s = "cc";	// 매칭 O
 		// String s = "ab";	// 매칭 X (첫번째와 두번째 문자 모두 같아야 한다)
		// Pattern p = Pattern.compile("(?<foo>[abc])\\k<foo>");
		// Matcher m = p.matcher(s);
 		// if(m.matches()) {
 		// 	bw.write("매칭 O\n");
 		// }
 		// else {
 		// 	bw.write("매칭 X\n");
 		// }

		// 문자열 s를 정규표현식p에 맞춰 부분문자열로 나누기
 		// Pattern p = Pattern.compile("[A-Za-z]+");
 		// String s = "I am a student";
 		// Matcher m = p.matcher(s);
 		// while(m.find()) {
			// // find가 group보다 선행되어야 합니다.
			// bw.write(m.group()+"\n");
	  	// }

	  	// 역참조를 이용한 문자열 치환
        String s = "abc:123";
        s = s.replaceAll("(abc):(123)", "$2:$1");	// abc 부분과 123 부분의 위치를 서로 바꿈
        bw.write(s+"\n");

	    // 문자열의 시작과 끝의 마침표를 없애기
        //String s = ".123.";
        //s = s.replaceAll("^\\.", "");	// 문자열 시작부분의 .을 제거
        //s = s.replaceAll("\\.$", "");	// 문자열 끝부분의 .을 제거
        //bw.write(s+"\n");

        // Greedy and Lazy match
        // String s = "This is a <div> simple div</div> test";
        // s = s.replaceAll("<.+>", "");	// Greedy match
        // s = s.replaceAll("<.+?>", "");	// Lazy match
        // s = s.replaceAll("<[^<>]+>", "");	// Lazy match
        // bw.write(s+"\n");

        // \b : 단어 경계와 일치, \B : \b와 반대(비단어 경계)로 일치. 즉 "\b"의 예제의 반대 결과가 나옴
        // 위치 지정 문자들은 자체만으로는 의미가 없으며  ^, \b, \B의 다음이나 \b, \B, $의 앞에 나오는 문자나 부분식과 함께 동작
        // String s = "abc abcc aabc aabcc";
        // s = s.replaceAll("\\babc", "");
        // s = s.replaceAll("abc\\b", "");
        // s = s.replaceAll("\\babc\\b", "");
        // s = s.replaceAll("abc", "");
        // bw.write(s+"\n");

        // (?=(패턴)) : 지정된 패턴이 존재하는지 확인을 하지만, 매칭되지는 않음 (전방탐색)
        // String s = "abc abcc aabc aabcc az za";
        // s = s.replaceAll("a(?=z)", "");
        // bw.write(s+"\n");

        // (?<=(패턴)) : 지정된 패턴이 존재하는지 확인을 하지만, 매칭되지는 않음 (후방탐색)
        // String s = "abc abcc aabc aabcc az za";
        // s = s.replaceAll("(?<=z)a", "");
        // bw.write(s+"\n");

        // (?!(패턴)) : 지정된 패턴이 존재하지 않는지 확인을 하지만, 매칭되지는 않음 (전방탐색)
        // String s = "abc abcc aabc aabcc az za";
        // s = s.replaceAll("a(?!z)", "");
        // bw.write(s+"\n");

        // (?<!(패턴)) : 지정된 패턴이 존재하지 않는지 확인을 하지만, 매칭되지는 않음 (후방탐색)
        // String s = "abc abcc aabc aabcc az za";
        // s = s.replaceAll("(?<!z)a", "");
        // bw.write(s+"\n");

        // ?= 와 ?! 를 이용한 문자열 자르기
        // String s = "ZA1B2C3D";
 		// String[] ss = s.split("(?=[A-Z])");
 		// String[] ss = s.split("(?![A-Z])");
 		// for(String tmp : ss) {
 		// 	bw.write(tmp+"\n");
 		// }

 		bw.flush();
	}
}