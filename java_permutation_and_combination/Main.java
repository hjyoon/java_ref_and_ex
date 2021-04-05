import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void init_flag_asc(int[] flag) {
		for(int i=0; i<flag.length; i++) {
			flag[i] = i;
		}
	}

	public static void print(String data, int[] flag, int r) throws Exception {
		for(int i=0; i<r; i++) {
			bw.write(data.charAt(flag[i])+"");
		}
		bw.newLine();
	}

	public static void print(String data, int[] flag) throws Exception {
		for(int i=0; i<flag.length; i++) {
			bw.write(data.charAt(flag[i])+"");
		}
		bw.newLine();
	}

	public static void print_flag(int[] flag) throws Exception {
		for(int i=0; i<flag.length; i++) {
			bw.write(flag[i]+"");
		}
		bw.newLine();
	}

	public static void swap(int[] arr, int pos1, int pos2) {
		int tmp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = tmp;
	}

	public static void right_rotate(int[] flag, int start, int end) {
		int last = flag[end];
		for(int i=end; i>start; i--) {
			flag[i] = flag[i-1];
		}
		flag[start] = last;
	}

	public static void left_rotate(int[] flag, int start, int end) {
		int first = flag[start];
		for(int i=start; i<end; i++) {
			flag[i] = flag[i+1];
		}
		flag[end] = first;
	}

	public static void perm_dict(String data, int[] flag, int depth, int n) throws Exception {
		if (depth == n) {
			print(data, flag, n);
			//print_flag(flag);
			return;
		}
		for (int i=depth; i<n; i++) {
			right_rotate(flag, depth, i);
			perm_dict(data, flag, depth+1, n);
			left_rotate(flag, depth, i);
		}
	}

	public static void perm_dict_2(String data, int[] flag, int depth, int n, int r) throws Exception {
		if (depth == r) {
			print(data, flag, r);
			print_flag(flag);
			return;
		}
		for (int i=depth; i<n; i++) {
			right_rotate(flag, depth, i);
			perm_dict_2(data, flag, depth+1, n, r);
			left_rotate(flag, depth, i);
		}
	}

	public static void perm(String data, int[] flag, int depth, int n) throws Exception {
		if(depth == n) {
			print(data, flag, n);
			//print_flag(flag);
			return;
		}
		for (int i=depth; i<n; i++) {
			swap(flag, depth, i);
			perm(data, flag, depth+1, n);
			swap(flag, depth, i);
		}
	}

	public static void perm_overlab(String data, int[] flag, int depth, int n, int r) throws Exception {
		if (depth == r) {
			print(data, flag, r);
			//print_flag(flag);
			return;
		}
		for (int i=0; i<n; i++) {
			flag[depth] = i;
			perm_overlab(data, flag, depth+1, n, r);
		}
	}

	public static boolean next_perm_dictionary_non_recursive(int[] flag, int n) {
		int i = n-1;
		while(i > 0 && flag[i-1] >= flag[i]) {
			i--;
		}
		if (i <= 0) {
			return false; // 마지막 순열
		}
		int j = n-1;
		while (flag[j] <= flag[i-1]) {
			j--;
		}
		swap(flag, i-1, j);
		j = n-1;
		while (i < j) {
			swap(flag, i, j);
			i++; 
			j--;
		}
		return true;
	}

	public static void combi(String data, int[] flag, int idx, int n, int r, int target) throws Exception {
		if (r == 0){
			print(data, flag);
			//print_flag(flag);
			return;
		}
		else if (target == n){
			return;
		}
		else {
			flag[idx] = target;
			combi(data, flag, idx+1, n, r-1, target+1);
			combi(data, flag, idx, n, r, target+1);
		}
	}

	public static void combi_overlap(String data, int[] flag, int idx, int n, int r, int target) throws Exception {
		if (r == 0){
			print(data, flag);
			//print_flag(flag);
			return;
		}
		else if (target == n){
			return;
		}
		else {
			flag[idx] = target;
			combi_overlap(data, flag, idx+1, n, r-1, target);
			combi_overlap(data, flag, idx, n, r, target+1);
		}
	}


	public static boolean next_comb_non_recursive(int[] flag, int n, int r) {
		/* this check is not strictly necessary, but if r is not close to n,
		it makes the whole thing quite a bit faster */
		// if(flag[r-1] < n-1) {
		//     flag[r-1]++;
		//     return true;
		// }
		int i = r-1;
		while(flag[i] >= n-(r-i)) {
			i--;
			if(i < 0) {
				return false;
			}
		}
		flag[i]++;
		while(i < r-1) {
			flag[i+1] = flag[i] + 1;
			i++;
		}
		return true;
	}

	public static boolean next_comb_overlab_non_recursive(int[] flag, int n, int r) {
		/* this check is not strictly necessary, but if r is not close to n,
		it makes the whole thing quite a bit faster */
		// if(flag[r-1] < n-1) {
		//  flag[r-1]++;
		//  return true;
		// }
		int i = r-1;
		while(flag[i] >= n-1) {
			i--;
			if(i < 0) {
				return false;
			}
		}
		flag[i]++;
		while(i < r-1) {
			flag[i+1] = flag[i];
			i++;
		}
		return true;
	}

	public static boolean next_perm_overlab_dictionary_non_recursive(int[] flag, int n, int r) {
		/* this check is not strictly necessary, but if r is not close to n,
		it makes the whole thing quite a bit faster */
		// if(flag[r-1] < n-1) {
		//  flag[r-1]++;
		//  return true;
		// }
		int i = r-1;
		while(flag[i] >= n-1) {
			i--;
			if(i < 0) {
				return false;
			}
		}
		flag[i]++;
		while(i < r-1) {
			flag[i+1] = 0;
			i++;
		}
		return true;
	}

	public static void main(String args[]) throws Exception {
		//br = new BufferedReader(new FileReader("../input.txt"));
		//br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		//st = new StringTokenizer(br.readLine());
		//int N = Integer.parseInt(st.nextToken());

		String s = "ABCD";
		int n = s.length();
		int r = 2;
		int[] flag = new int[n];

		bw.write("===== Permutation Recursive =====\n");
		init_flag_asc(flag);
		perm(s, flag, 0, n);
		bw.newLine();

		bw.write("===== Permutation Dictionary Recursive =====\n");
		init_flag_asc(flag);
		perm_dict(s, flag, 0, n);
		bw.newLine();

		bw.write("===== Permutation Dictionary Recursive 2 =====\n");
		int[] flag2 = new int[n];
		init_flag_asc(flag2);
		perm_dict_2(s, flag2, 0, n, 2);
		bw.newLine();

		bw.write("===== Next Permutation Dictionary Non Recursive =====\n");
		init_flag_asc(flag);
		do {
			print(s, flag, n);
			//print_flag(flag);
		} while(next_perm_dictionary_non_recursive(flag, n));
		bw.newLine();

		flag = new int[r];

		bw.write("===== Permutation Overlab Recursive =====\n");
		Arrays.fill(flag, 0);
		perm_overlab(s, flag, 0, n, r);
		bw.newLine();

		bw.write("===== Next Permutation Overlab Non Recursive =====\n");
		Arrays.fill(flag, 0);
		do {
			print(s, flag, r);
			//print_flag(flag);
		} while(next_perm_overlab_dictionary_non_recursive(flag, n, r));
		bw.newLine();

		bw.write("===== Combination Recursive =====\n");
		Arrays.fill(flag, 0);
		combi(s, flag, 0, n, r, 0);
		bw.newLine();

		bw.write("===== Combination Overlab Recursive =====\n");
		Arrays.fill(flag, 0);
		combi_overlap(s, flag, 0, n, r, 0);
		bw.newLine();

		bw.write("===== Next Combination Non Recursive =====\n");
		init_flag_asc(flag);
		do {
			print(s, flag, r);
			//print_flag(flag);
		} while(next_comb_non_recursive(flag, n, r));
		bw.newLine();

		bw.write("===== Next Combination Overlab Non Recursive =====\n");
		Arrays.fill(flag, 0);
		do {
			print(s, flag, r);
			//print_flag(flag);
		} while(next_comb_overlab_non_recursive(flag, n, r));
		bw.newLine();

		bw.flush();
	}
}