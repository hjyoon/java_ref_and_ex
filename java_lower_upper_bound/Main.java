import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    //static String s;
    //static String[] sa;

    // rear에 끝 인덱스가 아닌, 실제 data의 사이즈가 들어감.
    public static int upperBound(int arr[], int front, int rear, int target){
        int mid = 0;
        while(front < rear){
            mid = (front + rear) / 2;
            if(arr[mid] <= target) {
                front = mid + 1;
            }
            else rear = mid;
        }
        return rear;
    }

    // rear에 끝 인덱스가 아닌, 실제 data의 사이즈가 들어감.
    public static int lowerBound(int arr[], int front, int rear, int target){
        int mid = 0;
        while(front < rear){
            mid = (front + rear) / 2;
            if(arr[mid] < target) {
                front = mid + 1;
            }
            else rear = mid;
        }
        return rear;
    }

    // rear에 끝 인덱스가 아닌, 실제 data의 사이즈가 들어감.
    public static int upperBound_for_list(List<Integer> data, int front, int rear, int target) {      
        int mid = 0;
        while(front < rear) {
            mid = (front + rear) / 2;
            if(data.get(mid) <= target) {
                front = mid + 1;
            }
            else {
                rear = mid;
            }
        }
        return rear;
    }

    // rear에 끝 인덱스가 아닌, 실제 data의 사이즈가 들어감.
    public static int lowerBound_for_list(List<Integer> data, int front, int rear, int target) {
        int mid = 0;
        while(front < rear) {
            mid = (front + rear) / 2;
            if(data.get(mid) < target) {
                front = mid + 1;
            }
            else {
                rear = mid;
            }
        }
        return rear;
    }

    // equal_range() : upperBound() - lowerBound()

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //s = br.readLine();
        //sa = br.readLine().split(" +");

        int[] arr = { 1, 3, 3, 5, 7, 7, 7, 8, 8, 9 };
        ArrayList<Integer> al = new ArrayList<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        for(int i : arr) {
            bw.write(i + " ");
        }
        bw.newLine();

        int target = 7;

        bw.write("target : "+ target);
        bw.newLine();

        // array
        bw.write("upperBound : "+upperBound(arr, 0, arr.length-1, target) + "\n"); // target 보다 큰 첫번째 위치 반환
        bw.write("lowerBound : "+lowerBound(arr, 0, arr.length-1, target) + "\n"); // target 크거나 같은 첫번째 위치 반환

        // list
        bw.write("upperBound_for_list : "+upperBound_for_list(al, 0, al.size()-1, 10) + "\n"); // target 보다 큰 첫번째 위치 반환
        bw.write("lowerBound_for_list : "+lowerBound_for_list(al, 0, al.size()-1, target) + "\n"); // target 보다 큰 첫번째 위치 반환

        bw.flush();
    }
}