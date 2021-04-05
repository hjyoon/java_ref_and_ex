import java.io.*;
import java.math.*;     
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        deque.add(1);
        deque.add(2);
        deque.add(3);
        //deque.offer(3);  // return true or false

        deque.addFirst(0);
        //deque.offerFirst(0);  // return true or false
        deque.addLast(4);
        //deque.offerLast(4);   // return true or false

        deque.add(5);

        bw.write(deque.getFirst()+" ");
        bw.write(deque.getLast()+" ");
        bw.newLine();

        //deque.peek(); // Retrieves, but does not remove, the head, or returns null if this deque is empty.
        //deque.poll(); // Retrieves and removes the head, or returns null if this deque is empty.
        //deque.pop();

        for(Iterator<Integer> it = deque.iterator(); it.hasNext(); ) {
            bw.write(it.next()+" ");
        }
        bw.newLine();


        bw.write(deque+"");
        bw.newLine();

        bw.flush();
    }
}