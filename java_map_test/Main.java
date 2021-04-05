import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Map.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> hm = new HashMap<>();
        TreeMap<String, Integer> tm = new TreeMap<>();  // 키 값으로 자동 정렬 됨
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();

        lhm.put("b", 1);
        lhm.put("c", 2);
        lhm.put("a", 0);
        //lhm.put("b", 3);

        // int n = Integer.parseInt(br.readLine());
        // String[] s = br.readLine().split(" ");

        // for(int i=0; i<n; i++) {
        //     if(hm.containsKey(s[i])) {
        //         int tmp = hm.get(s[i]);
        //         hm.put(s[i], tmp+1);
        //     }
        //     else {
        //         hm.put(s[i], 1);
        //     }
        // }

        // key 로 value 가져오기
        // bw.write(hm.get("a")+"\n");
        // bw.write(hm.get("b")+"\n");
        // bw.write(hm.get("c")+"\n");

        // for(String ss : hm.keySet()) {
        //     bw.write(hm.get(ss)+"\n");
        // }

        // map 순회
        for(Entry<String, Integer> ss : lhm.entrySet()) {
            bw.write(ss.getKey()+" "+ss.getValue()+"\n");
        }

        // value 로 key 찾기
        //bw.write(getKey(hm, 1));

        // hm.containsKey();
        // hm.containsValue();
        // hm.clear();
        // hm.remove();
        // hm.isEmpty();
        // hm.size();


        bw.flush();
    }
}