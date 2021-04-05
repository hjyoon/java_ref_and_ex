import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

public class Main {
    public static void main(String args[]) {
        // 스레드를 단 1개만 생성
        // ExecutorService es = Executors.newSingleThreadExecutor();

        // 처리할 스레드가 많아지면 그만큼 스레드를 증가
        //ExecutorService es = Executors.newCachedThreadPool();

        // 주어진 스레드 개수만큼 생성하고 그 수를 유지. 생성된 스레드 중 일부가 종료되었으면 스레드를 다시 생성.
        ExecutorService es = Executors.newFixedThreadPool(3);

        // 반환값 없음
        // Runnable task = new Runnable() {
        //     @Override
        //     public void run() {
        //         int tmp = 0;
        //         for(int i=0; i<200000000; i++) {
        //             tmp = (tmp + i) % 103;
        //         }
        //         System.out.println("answer : " + tmp);
        //     }
        // };

        // for(int i=0; i<100; i++) {
        //     es.execute(task);
        // }

        // 반환값 있음
        Callable<Integer> task2 = new Callable<>() {
            @Override
            public Integer call() {
                int tmp = 0;
                for(int i=0; i<200000000; i++) {
                    tmp = (tmp + i) % 103;
                }
                return tmp;
            }
        };

        Future<Integer> data1 = es.submit(task2);

        try {
            // 작업이 완료될 때 까지 블로킹되었다가 처리 결과를 리턴
            System.out.println("answer : " + data1.get());
        }  
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        catch(ExecutionException e) {
            e.printStackTrace();
        }



        // 작업큐에 남아있는 작업까지 모두 마무리 후 종료 (오버헤드를 줄이기 위해 일반적으로 많이 사용.)
        es.shutdown();

        // 작업큐 작업 잔량 상관없이 강제 종료
        // es.shutdownNow();

        // 모든 작업 처리를 timeout 시간안에 처리하면 true 리턴 ,처리하지 못하면 작업스레드들을 interrupt()시키고 false리턴
        // es.awaitTermination(long timeout, TimeUnit unit);
    }
}