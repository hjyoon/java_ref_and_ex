import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;

public class Main {
	public static void main (String args[]) {
		//PriorityQueue<Integer> pq = new PriorityQueue<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		pq.offer(20);
		pq.offer(30);
		pq.offer(30);
		pq.offer(10);
		pq.offer(10);

		//int size = pq.size();
		//int a = pq.peek();
		//int b = pq.poll();
		//
		// if(pq.contains(25)) {
		// 	;
		// }
		//pq.remove(20);

 		System.out.println(pq.poll());
 		System.out.println(pq.poll());
 		System.out.println(pq.poll());
 		System.out.println(pq.poll());
 		System.out.println(pq.poll());

 		pq.clear();
	}
}