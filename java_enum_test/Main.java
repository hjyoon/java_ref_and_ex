import java.io.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

enum Season {
    // 열거 상수는 열거 객체로 생성된다.
    SPRING, // 0 번째
    SUMMER, // 1 번째
    AUTUMN, // 2 번째
    WINTER  // 3 번째
}

enum Type {
    // 열거 상수 각각이 열거 객체이므로 열거 객체에 생성자를 사용해서 다음과 같이 열거 상수에 다른 값을 할당할 수 있다.
    // 상수("연결할 문자")
    WALKING("워킹화"), 
    RUNNING("러닝화"), 
    TRACKING("트래킹화"), 
    HIKING("등산화"); // 뒤에 추가적인 코드가 있을 경우, 마지막엔 세미콜론(;)을 붙여준다.

    final private String name;

    // enum에서 생성자 같은 역할
    private Type(String name) {
        this.name = name;
    }

    // 문자를 받아오는 함수
    public String getName() {
        return name;
    }
}

enum TableStatus {
    Y("1", true),
    N("0", false);

    private String table1Value;
    private boolean table2Value;

    TableStatus(String table1Value, boolean table2Value) {
        this.table1Value = table1Value;
        this.table2Value = table2Value;
    }

    public String getTable1Value() {
        return table1Value;
    }

    public boolean isTable2Value() {
        return table2Value;
    }
}

enum CalculatorType {
    CALC_A(value -> value),
    CALC_B(value -> value * 10),
    CALC_C(value -> value * 3),
    CALC_ETC(value -> 0L);

    private Function<Long, Long> expression;

    CalculatorType(Function<Long, Long> expression) {
        this.expression = expression;
    }

    public long calculate(long value) {
        return expression.apply(value);
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //st = new StringTokenizer(br.readLine());
        //int n = Integer.parseInt(st.nextToken());

        // 열거 타입 변수는 다음과 같이 null을 저장할 수 있는데, 열거 타입도 참조 타입이기 때문이다.
        // 참조 타입 변수는 객체를 참조하는 변수로 알고 있는데 열거 상수는 객체일까? 그렇다. 열거 상수는 열거 객체로 생성된다.
        Season season = null;

        // 열거 타입 변수 season은 스택 영역에 생성된다.
        // season에 저장되는 값은 Season.SPRING 열거 상수가 참조하는 객체의 주소이다.
        season = Season.SPRING;
        if(season == Season.SPRING) {   // true
            bw.write("season변수의 저장된 값은 Season.SPRING 객체의 주소와 같다.\n");
        }

        bw.write(season.name()+"\n"); // 열거 객체의 문자열 리턴
        bw.write(Season.AUTUMN.ordinal()+"\n"); // 열거 객체가 몇 번째인지를 리턴

        // 매개값으로 주어진 열거 객체를 기준으로 전 후로 몇 번째 위치하는지 비교
        Season season1 = Season.SPRING; // 0
        Season season2 = Season.WINTER; // 3
        int result1 = season1.compareTo(season2); // -3
        int result2 = season2.compareTo(season1); // 3

        // 매개값으로 주어지는 문자열과 동일한 문자열을 가지는 열거 객체를 리턴한다.
        // 외부로부터 문자열을 받아 열거 객체로 변환할 때 유용하다.
        season = Season.valueOf("SPRING");

        // 열거 타입의 모든 열거 객체들을 배열로 만들어 리턴
        Season[] seasons = Season.values();
        for(Season i : seasons) {
            bw.write(i+"\n");
        }

        for(Type i : Type.values()) {
            bw.write(i.getName()+"\n");
        }

        TableStatus ts = TableStatus.valueOf("Y");
        bw.write(ts.getTable1Value()+"\n");
        bw.write(ts.isTable2Value()+"\n");
        
        bw.newLine();
        bw.flush();
    }
}