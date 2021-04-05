import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static private int[] nextPermute(int[] nums) throws Exception {
        int[] copies = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copies[i] = nums[i];
        }
        int idx = -1;
        for (int i = 0; i < copies.length - 1; i++) {
            if (copies[i] < copies[i + 1]) {
                idx = i;
            }
        }
        if (idx < 0) {
            //Last Permutation
            return null;
        }
        for (int i = copies.length - 1; i > idx; i--) {
            if (copies[idx] < copies[i]) {
                int tmp = copies[idx];
                copies[idx] = copies[i];
                copies[i] = tmp;
                break;
            }
        }
        for (int i = idx + 1; i < (copies.length + idx + 1) / 2; i++) {
            int tmp = copies[i];
            copies[i] = copies[copies.length - (i - idx)];
            copies[copies.length - (i - idx)] = tmp;
        }
        return copies;
    }

    static private int[] prevPermute(int[] nums) throws Exception {
        int[] copies = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copies[i] = nums[i];
        }
        int idx = -1;
        for (int i = copies.length - 1; i > 0; i--) {
            if (copies[i] < copies[i - 1]) {
                idx = i-1;
                break;
            }
        }
        if (idx < 0) {
            //Last Permutation
            return null;
        }
        for (int i = copies.length - 1; i > idx; i--) {
            if (copies[idx] > copies[i]) {
                int tmp = copies[idx];
                copies[idx] = copies[i];
                copies[i] = tmp;
                break;
            }
        }
        for (int i = idx + 1; i < (copies.length + idx + 1) / 2; i++) {
            int tmp = copies[i];
            copies[i] = copies[copies.length - (i - idx)];
            copies[copies.length - (i - idx)] = tmp;
        }
        return copies;
    }

    static public List<List<Integer>> permute(int[] nums) throws Exception {
        List<List<Integer>> permutations = new ArrayList<>();
        Arrays.sort(nums);
        int[] curArray = nums;
        while (true) {
            List<Integer> integerList = new ArrayList<>();
            for (int i = 0; i < curArray.length; i++) {
                integerList.add(curArray[i]);
            }
            permutations.add(integerList);
            int[] nextPermutation = nextPermute(curArray);
            if (nextPermutation == null) {
                break;
            }
            curArray = nextPermutation;
        }
        return permutations;
    }

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //bw.write("hello\n");

        int[] data = new int[] {1,2,3,4};
        int[] flag = new int[] {0,0,1,1};

        List<List<Integer>> perm = permute(flag);
        for(List<Integer> tmp : perm) {
            for(int i=0; i<tmp.size(); i++) {
                for(int j=0; j<tmp.get(i); j++) {
                    bw.write(data[i]+" ");
                }
            }
            // for(int tmp_i : tmp) {
            //     bw.write(tmp_i+" ");
            // }
            bw.write("\n");
        }

        bw.flush();

    }
}