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

        // Integer[] -> List<Integer>
        // Integer[] arr = {4, 3, 1, 5, 2};
        // List<Integer> al = new ArrayList<>(Arrays.asList(arr));
        // List<Integer> al = Arrays.asList(arr);
        // List<Integer> al = new ArrayList<>(Arrays.asList(new Integer[] {4, 3, 1, 5, 2}));
        // for(Integer i : al) {
        //     bw.write(i + " ");
        // }


        // List<Integer> -> Integer[]
        // List<Integer> al = new ArrayList<>();
        // al.add(4);
        // al.add(3);
        // al.add(1);
        // al.add(5);
        // al.add(2);
        // Integer[] arr = al.toArray(new Integer[al.size()]); // 사이즈가 맞으면 저장됨, 그렇지 않으면 새 배열이 할당.
        // Integer[] arr = al.toArray(Integer[]::new);
        // for(Integer i : arr) {
        //     bw.write(i + " ");
        // }
        

        // int[] -> List<Integer>
        // int arr[] = {4, 3, 1, 5, 2};
        // List<Integer> al = new ArrayList<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        // //List<Integer> al = new ArrayList<>(Arrays.stream(new int[] {4, 3, 1, 5, 2}).boxed().collect(Collectors.toList()));
        // for(Integer i : al) {
        //     bw.write(i + " ");
        // }


        // List<Integer> -> int[]
        // List<Integer> al = new ArrayList<>(Arrays.asList(new Integer[] {4, 3, 1, 5, 2}));
        // // al.add(4);
        // // al.add(3);
        // // al.add(1);
        // // al.add(5);
        // // al.add(2);
        // int arr[] = al.stream().mapToInt(i->i).toArray();
        // for(int i : arr) {
        //     bw.write(i + " ");
        // }


        // String[] -> List<String>
        // String[] s = {"abc", "ab", "a"};
        // List<String> al = new ArrayList<>(Arrays.asList(s));
        // List<String> al = Arrays.asList(s);
        // List<String> al = new ArrayList<>(Arrays.asList(new String[] {"abc", "ab", "a"}));
        // for(String i : al) {
        //     bw.write(i + " ");
        // }


        // List<String> -> String[]
        // List<String> al = new ArrayList<>();
        // al.add("abc");
        // al.add("ab");
        // al.add("a");
        // String[] arr = al.toArray(new String[al.size()]);
        // //String[] arr = al.toArray(String[]::new);
        // for(String i : arr) {
        //     bw.write(i + " ");
        // }

        ArrayList<Boolean> isSelected = new ArrayList<>(36);
        for(int i=0)
        Collections.fill(isSelected, true);

        bw.write(isSelected + "");

		bw.flush();
    }
}