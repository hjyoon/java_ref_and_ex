import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Map.*;

class Person {
    String name;
    int residentNumber;
    public Person(String name,int residentNumber) {
        this.name = name;
        this.residentNumber = residentNumber;
    }

    // 만약 equals에서 두 객체가 같다라고 true를 반환했다면 hashCode는 두 객체에서 항상 같은 값을 반환해야합니다. 만일 equals에서 false를 반환하여 같지 않다고 반환했다면 hashCode 역시 다르게 반환하는 것이 좋습니다.
    @Override
    public int hashCode() {
        /* Objects.hash 메소드로 residentNumber의 해쉬값 반환 */
        return Objects.hash(residentNumber);
    }
    
    // 만약 두 객체(현재 이 객체와 인자로 넘어온 객체 o)가 같다는 것을 알려주려면 equals 메소드에서 true를 반환해주어야합니다.
    @Override
    public boolean equals(Object o) {
        /* 주민 번호가 같은 Person은 true 반환 */
        Person p = (Person)o;
        return (p.residentNumber == this.residentNumber);
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //int[] arr = {1, 0, 3, 5, 2, 2, 1, 1, 4, 7, 9, 0, 0, 8, 9, 6};

        HashSet<Integer> hs = new HashSet<>();  // 저장된 데이터에 순서가 없음
        TreeSet<Integer> ts = new TreeSet<>();  // 입력된 순서대로 저장 X, 오름차순으로 자동정렬
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();  // 입력된 순서대로 값이 저장됨

        Random r = new Random();
        for(int i=0; i<40; i++) {
            int tmp = r.nextInt(100);
            hs.add(tmp);
            ts.add(tmp);
            lhs.add(tmp);
        }

        //hs.clear();    // 모든 원소 삭제
        //hs.remove(1);  // 특정 원소 삭제 (존재할 경우)

        if(hs.isEmpty()) {  // 원소가 하나도 없을 경우
            bw.write("HashSet has no elements.\n\n");
        }
        else {
            bw.write("size : "+hs.size()+"\n");
            bw.write("HashSet : ");
            for(int i : hs) {
                bw.write(i+" ");
            }
            bw.write("\n");

            // if(hs.contains(0)) {
            //     bw.write("HashSet has 0.\n");
            // }

            bw.write("\n");
        }

        if(ts.isEmpty()) {
            bw.write("TreeSet has no elements.\n\n");
        }
        else {
            bw.write("first : "+ts.first()+"\n");
            bw.write("last : "+ts.last()+"\n");
            //ts.pollFirst(); // 첫번째 원소를 빼낸뒤 제거
            //ts.pollLast();  // 마지막 원소를 빼낸뒤 제거 
            bw.write("higher : "+ts.higher(30)+"\n"); // 30 초과값 중 가장 작은 값 리턴
            bw.write("lower : "+ts.lower(30)+"\n");   // 30 미만값 중 가장 큰 값 리턴
            bw.write("ceiling : "+ts.ceiling(30)+"\n"); // 30 이상의 값 중 가장 작은 값 리턴 (30 포함 가능)
            bw.write("floor : "+ts.floor(30)+"\n");     // 30 이하의 값 중 가장 큰 값 리턴 (30 포함 가능)

            bw.write("size : "+ts.size()+"\n");
            bw.write("TreeSet : ");
            for(int i : ts) {
                bw.write(i+" ");
            }
            bw.write("\n");

            // if(ts.contains(0)) {
            //     bw.write("TreeSet has 0.\n");
            // }

            bw.write("\n");
        }

        if(lhs.isEmpty()) {
            bw.write("LinkedHashSet has no elements.\n\n");
        }
        else {
            bw.write("size : "+lhs.size()+"\n");
            bw.write("LinkedHashSet : ");
            for(int i : lhs) {
                bw.write(i+" ");
            }
            bw.write("\n");
            
            // if(lhs.contains(0)) {
            //     bw.write("LinkedHashSet has 0.\n");
            // }

            bw.write("\n");
        }

        bw.flush();
    }
}