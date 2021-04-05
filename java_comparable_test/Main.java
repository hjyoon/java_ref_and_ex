import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Data implements Comparable<Data> {
    public int num;
    public int index;

    Data(int num, int index) {
        this.num = num;
        this.index = index;
    }

    // num에 대한 오름차순(ascending)
    @Override
    public int compareTo(Data d) {
        if(num > d.num) {
            return 1;
        }
        else if(num < d.num) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    //static String s;
    //static String[] sa;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //s = br.readLine();
        //sa = s.split(" +");

        Data[] arr = new Data[5];
        arr[0] = new Data(4, 0);
        arr[1] = new Data(3, 1);
        arr[2] = new Data(2, 2);
        arr[3] = new Data(5, 3);
        arr[4] = new Data(1, 4);

        Arrays.sort(arr);

        // print test
        for(Data tmp : arr) {
            bw.write(tmp.num + " ");
        }
        bw.write("\n");

        bw.flush();
    }
}