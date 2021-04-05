import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

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

class WorkerThread extends Thread {
    private DoSomething ds;

    public WorkerThread(ThreadGroup tg, String threadName, DoSomething ds) {
        super(tg, threadName);
        this.ds = ds;
    }

    @Override
    public void run() {
        while(!interrupted()) {
            ds.doSome();
            //ds.doSomeSynIn();
            //ds.doSomeStatic();
            //ds.doSomeStaticIn();
        }
    }
}

/*
    관련된 스레드 묶어 관리 목적으로 이용
    스레드 그룹은 계층적으로 하위 스레드 그룹 가질 수 있음

    자동 생성되는 스레드 그룹
    - system 그룹: JVM 운영에 필요한 스레드들 포함
    - system/main 그룹: 메인 스레드 포함

    스레드는 반드시 하나의 스레드 그룹에 포함
    - 기본적으로 자신을 생성한 스레드와 같은 스레드 그룹
    - 스레드 그룹에 포함시키지 않으면 기본적으로 system/main 그룹
*/

public class Main {
    public static void main(String args[]) throws Exception {
        // 스레드 그룹 이름 얻기
        // ThreadGroup tg = Thread.currentThread().getThreadGroup();
        // String groupName = tg.getName();
        // System.out.println(groupName);

        ThreadGroup tg = new ThreadGroup("myGroupParent");
        ThreadGroup tg2 = new ThreadGroup(tg, "myGroup");   // 첫 번째 인자로 부모 그룹을 지정

        DoSomething ds = new DoSomething();
        //Thread t = new Thread(tg2, new Worker(ds), "Wait"); // 첫 번째 인자로 소속될 스레드 그룹을 지정
        WorkerThread wt = new WorkerThread(tg2, "Wait", ds);

        wt.start();

        // tg2.interrupt(); // 현재 그룹에 포함된 모든 스레드들을 interrupt 한다.
        // tg2.getName();  // 현재 그룹의 이름을 리턴.
        // tg2.getParent(); // 현재 그룹의 부모 그룹을 리턴.
        // tg2.list(); // 현재 그룹에 포함된 스레드와 하위 그룹에 대한 정보를 출력한다.
        // tg2.activeCount();  // 현재 그룹 및 하위 그룹에서 활동중인 모든 스레드의 수를 리턴.
        // tg2.activeGroupCount(); // 현재 그룹에서 활동중인 모든 하위 그룹의 수를 리턴.
    }
}