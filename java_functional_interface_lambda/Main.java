import java.io.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

/*
Java 함수형 인터페이스 종류
1. Consumer : 매개변수O, 반환값X
    - 매개변수를 받아서 소비
    - Consumer<매개변수타입>
    - BiConsumer<매개변수타입1, 매개변수타입2>

2. Supplier : 매개변수X, 반환값O
    - 데이터를 공급
    - Supplier<반환값타입>

3. Function : 매개변수O, 반환값O
    - 주로 매개변수를 반환값 타입으로 변환 후 반환
    - Function<매개변수타입, 반환값타입>

4. Operator : 매개변수O, 반환값O
    - 주로 매개변수를 연산 후 결과값 반환
    - 매개값과 반환값의 타입이 동일하다.
    - Function의 서브 셋
    - Operator<매개변수and반환값의타입>

5. Predicate : 매개변수O, 반환값O
    - 주로 매개변수를 조사한 후 논리값 반환
    - Function의 서브 셋
    - Predicate<매개변수타입1> 
    - BiPredicate<매개변수타입1, 매개변수타입2>

인터페이스 합치기
1. andThen()
    - A.andThen(B) -> A와 B인터페이스의 조합
    - A실행 -> B실행
2. compose()
    - A.compose(B)
    - B실행 -> A실행
*/


// @FunctionalInterface 는 해당 인터페이스를 함수적 인터페이스로 사용하겠다는 어노테이션
// 추상메소드가 단 1개만 존재해야한다.

// Consumer<String> 와 같음
@FunctionalInterface
interface MyConsumer {
    void abc(String txt);
}

interface MyNumber {
    int getNum();
}

class MyNumberC {
    public int getNum() {
        return 100;
    }
}

// Function<Integer, Integer> 또는 UnaryOperator<Integer> 와 같음
@FunctionalInterface
interface MyFunction {
    int abc(int x);
}

// Function<Integer, Long>와 같음
@FunctionalInterface
interface MyFunction2 {
    long abc(int x);
}

// Supplier<Integer> 와 같음
@FunctionalInterface
interface MySupplier {
    int abc();
}

// Predicate<Integer> 와 같음
@FunctionalInterface
interface MyPredicate {
    boolean abc(int x);
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        MyConsumer c1 = txt -> System.out.println("결과 : " + txt);
        BiConsumer<String, Integer> bc = (name, age) -> {
            System.out.println("이름 : " + name);
            System.out.println("나이 : " + age);
            System.out.println("결과 : " + ((age > 19) ? "어른" : "아이"));
        };
        Consumer<String> c2 = txt -> System.out.println("결과 : " + txt);
        Consumer<MyNumber> c3 = num -> System.out.println(num.getNum());
        Consumer<MyNumberC> c4 = num -> System.out.println(num.getNum());

        MySupplier s1 = () -> {
            return 200;
        };
        Supplier<Integer> s2 = () -> {
            return 200;
        };

        MyFunction f1 = x -> x*x;
        Function<Integer, Integer> f2 = x -> x*x;
        BiFunction<Integer, Integer, Integer> bf = (x, y) -> (x+y)*(x+y);

        UnaryOperator<Integer> uo = (n) -> n;
        BinaryOperator<Integer> bo = (n1, n2) -> n1+n2;

        MyPredicate p1 = n -> n > 0;
        Predicate<Integer> p2 = n -> n > 0;
        BiPredicate<String, String> bp = (str1, str2) -> str1.length() > str2.length();

        ////////////////////////////////////////////////////////////////////////////////////////////

        c1.abc("test");
        c2.accept("test2");
        c3.accept(new MyNumber() {
            @Override
            public int getNum() {
                return 100;
            }
        });
        c4.accept(new MyNumberC());
        bc.accept("hjyoon", 29);

        bw.write(f1.abc(2)+"\n");
        bw.write(f2.apply(2)+"\n");
        bw.write(bf.apply(2,3)+"\n");

        bw.write(s1.abc()+"\n");
        bw.write(s2.get()+"\n");

        bw.write(uo.apply(10)+"\n");
        bw.write(bo.apply(10, 20)+"\n");

        bw.write(p1.abc(10)+"\n");
        bw.write(p2.test(10)+"\n");
        bw.write(bp.test("alpha", "beta")+"\n");


        // 인터페이스 합치기

        Function<Integer, Integer> ff1 = x -> x+3;
        Function<Integer, Integer> ff2 = x -> x*10;
        Function<Integer, Integer> ff3 = null;

        ff3 = ff1.andThen(ff2);
        bw.write(ff3.apply(1)+"\n");    // result : (1+3)*10 = 40

        ff3 = ff1.compose(ff2);
        bw.write(ff3.apply(1)+"\n");    // result : (1*10)+4 = 14

        
        //st = new StringTokenizer(br.readLine());
        //int n = Integer.parseInt(st.nextToken());

        bw.newLine();
        bw.flush();
    }
}