import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

/*
    synchronized("객체") -> 이 동기화 블록 안에 전달된 객체를 모니터 객체(a monitor object) 라 한다.
    이 모니터 객체를 기준으로 동기화가 이루어짐을 나타내고 있다.
    동기화된 인스턴스 메소드는 자신(메소드)을 내부에 가지고 있는 객체를 모니터 객체로 사용한다.
    같은 모니터 객체를 기준으로 동기화된 블록 안의 코드를 오직 한 쓰레드만이 실행할 수 있다.
*/

class DoSomething {
    public void doSome() {
        int tmp = 0;
        for(int i=0; i<200000000; i++) {
            tmp = (tmp + i) % 103;
        }
        System.out.println("answer : " + tmp);
    }

    public static void doSomeStatic() {
        int tmp = 0;
        for(int i=0; i<200000000; i++) {
            tmp = (tmp + i) % 103;
        }
        System.out.println("answer : " + tmp);
    }

    public synchronized void doSomeSyn() {
        int tmp = 0;
        for(int i=0; i<200000000; i++) {
            tmp = (tmp + i) % 103;
        }
        System.out.println("answer : " + tmp);
    }

    public synchronized static void doSomeSynStatic() {
        int tmp = 0;
        for(int i=0; i<200000000; i++) {
            tmp = (tmp + i) % 103;
        }
        System.out.println("answer : " + tmp);
    }

    public static void doSomeSynStaticIn() {
        int tmp = 0;
        synchronized(DoSomething.class) {
            for(int i=0; i<200000000; i++) {
                tmp = (tmp + i) % 103;
            }
        }
        System.out.println("answer : " + tmp);
    }

    public void doSomeSynIn() {
        int tmp = 0;
        synchronized(this) {
            for(int i=0; i<200000000; i++) {
                tmp = (tmp + i) % 103;
            }
        }
        System.out.println("answer : " + tmp);
    }

    public void doSomeWait() {
        synchronized(this) {
            try {
                wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            int tmp = 0;
            for(int i=0; i<200000000; i++) {
                tmp = (tmp + i) % 103;
            }
            System.out.println("answer : " + tmp);
        }
    }

    public void doSomeNotify() {
        synchronized(this) {
            System.out.println("go on !!");
            notify();
        }
    }

    public void doSomeNotifyAll() {
        synchronized(this) {
            System.out.println("go on all !!");
            notifyAll();
        }
    }
}

class Worker implements Runnable {
    private DoSomething ds;

    public Worker(DoSomething ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        ds.doSome();
        //ds.doSomeSynIn();
        //ds.doSomeStatic();
        //ds.doSomeStaticIn();
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        System.out.println(Thread.currentThread().getName());   // 현재 코드를 실행중인 쓰레드의 이름을 반환

        // 방법 1
        // Worker w = new Worker("Thread Name");
        // w.start();

        // 방법 2
        // Thread t = new Thread(new Worker2(), "Thread Name");
        // t.start();

        // 방법 3
        // new Thread() {
        //     public void run() {
        //         System.out.println("Thread: " + getName() + " running");
        //     }
        // }.start();

        // 방법 4
        // new Thread(new Runnable() {
        //     public void run() {
        //         System.out.println("test");
        //     }
        // }).start();

        // synchronized 테스트
        // DoSomething ds = new DoSomething();
        // DoSomething ds2 = new DoSomething();

        // Thread t = new Thread(new Worker2(ds), "Thread Name");
        // t.start();

        // Thread t2 = new Thread(new Worker2(ds2), "Thread Name");
        // t2.start();

        // wait(), notify(), notifyAll() 테스트
        // DoSomething ds = new DoSomething();
        // Thread t = new Thread(new Worker(ds), "Wait");
        // Thread t2 = new Thread(new Worker(ds), "Wait");
        // t.start();
        // t2.start();
        // ds.doSomeNotify();
        // ds.doSomeNotifyAll();

        // join() 테스트
        // DoSomething ds = new DoSomething();
        // Thread t = new Thread(new Worker(ds), "Wait");
        // t.start();
        // t.join();   // 메인 스레드가 t스레드가 끝날때 까지 대기.
        // Thread t2 = new Thread(new Worker(ds), "Wait");
        // t2.start();

        // Thread.sleep() 테스트
        // DoSomething ds = new DoSomething();
        // Thread t = new Thread(new Worker(ds), "Wait");
        // t.start();
        // Thread.sleep(1000); // 이 메서드를 호출한 스레드는 1000 밀리세컨드 초 동안 일시정지.
        // Thread t2 = new Thread(new Worker(ds), "Wait");
        // t2.start();

        // Thread.sleep() 테스트
        // DoSomething ds = new DoSomething();
        // Thread t = new Thread(new Worker(ds), "Wait");
        // t.start();
        // Thread.sleep(1000); // 이 메서드를 호출한 스레드는 1000 밀리세컨드 초 동안 일시정지.
        // Thread t2 = new Thread(new Worker(ds), "Wait");
        // t2.start();

        // Thread.yield() : 현재 돌고 있는 쓰레드가 다른 Thread를 위해서 양보하겠다는 것

        // interrupt() : 스레드가 일시 정지 상태에 있을 때, InterruptedException 예외를 발생시킨다.
        // 스레드가 일시 정지 상태가 되지 않는다면 interrupt() 메소드 호출은 아무런 의미가 없어지게 된다.
        /* 예시
        @Override
        public void run() {
            while(!Thread.interrupted()) {
                System.out.println("실행중");
            }
        }
        */

        /*
        자바는 모든 쓰레드가 끝나야 JVM이 종료된다는 기본 원칙이 있다.
        따라서 자바 애플리케이션에서 main쓰레드가 죽는다고 자바프로그램이 완전히 종료된 것이 아니다.
        자바 애플리케이션에서 백그라운드 서비스에 일반 쓰레드를 쓰면 영원히 정지하지 않을 수 있다.
        이러면 안되기 때문에 Daemon thread라는 개념이 생겼다.
        */

        // Daemon 스레드 : 단순히 백그라운드로 main쓰레드가 종료되었을 때 같이 종료해도 되는 쓰레드로 지정.
        DoSomething ds = new DoSomething();
        Thread t = new Thread(new Worker(ds), "Wait");
        t.setDaemon(true);  // * 주의할 점은 실행(t.start())하기 전에 t.setDaemon(true)을 시켜야한다.
        t.start();
    }
}