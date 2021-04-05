import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if(N <= 1) {
            return;
        }

        // N+1 만큼 할당
        ArrayList<Boolean> primeList = new ArrayList<Boolean>();
        // 0번째와 1번째를 소수 아님으로 처리
        primeList.add(false);
        primeList.add(false);

        // 2~N 까지 소수로 설정
        for(int i=2; i<=N; i++ ) {
            primeList.add(true);
        }

        // 2 부터  ~ i*i <= N
        // 각각의 배수들을 지워간다. (i 가 루트 N 이하일 경우) 
        for(int i=2; (i*i)<=N; i++){
            if(primeList.get(i)){
                for(int j = i*2; j<=N; j+=i) {
                    primeList.set(j, false);
                }
                //i*i 미만은 이미 처리되었으므로 j의 시작값은 i*i로 최적화할 수 있다.
            }
        }
        
        // 소수 출력
        int size = primeList.size();
        for(int i=0; i<size; i++) {
            if(primeList.get(i) == true) {
                bw.write(i+"\n");
            }
        }
        bw.flush();
    }
}