/*
    생성하기
        - 배열 / 컬렉션 / 빈 스트림
        - Stream.builder() / Stream.generate() / Stream.iterate()
        - 기본 타입형 / String / 파일 스트림
        - 병렬 스트림 / 스트림 연결하기
        
    가공하기
        - Filtering
        - Mapping
        - Sorting
        - Iterating

    결과 만들기
        - Calculating
        - Reduction
        - Collecting
        - Matching
        - Iterating
*/

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    //static String[] s;

    public static void main(String args[]) throws Exception {
    	//br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int[] arr = {3, 1, 2, 5, 4};
        Integer[] arr2 = {3, 1, 2, 5, 4};
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(arr2));
        //ArrayList<Integer> al = new ArrayList<>(Arrays.asList(new Integer[] {3, 1, 2, 5, 4}));

        IntStream is = Arrays.stream(arr);
        Stream<Integer> is2 = Arrays.stream(arr2);
        Stream<Integer> is3 = al.stream();
        IntStream is4 = IntStream.of(3, 1, 2, 5, 4);
        Stream<Integer> is5 = Stream.of(3, 1, 2, 5, 4);
        IntStream is6 = IntStream.of(new int[] {3, 1, 2, 5, 4});

        // String의 각 문자(char)를 IntStream으로 변환
        IntStream is7 = "Stream".chars(); // [83, 116, 114, 101, 97, 109]

        // 정규표현식(RegEx)을 이용해서 문자열을 자르고, 각 요소들로 스트림을 만든다.
        Stream<String> ss = Pattern.compile(", ").splitAsStream("Eric, Elena, Java");

        IntStream is_range = IntStream.range(0, 5); // 0~4 마지막 수 포함X
        IntStream is_range2 = IntStream.rangeClosed(0, 5);  // 0~5 마지막 수 포함O

        IntStream is8 = IntStream.rangeClosed(0, 20);

        // 특정한 엘리먼트까지 왔다면 멈추고 그 엘리먼트까지 반환
        is8.takeWhile(n -> n < 10).forEach(System.out::println);    // 0~9출력

        // 특정한 엘리먼트까지 왔다면 멈추고 그 엘리먼트를 제외한 나머지를 모두 반환
        //is8.dropWhile(n -> n < 10).forEach(System.out::println);    // 10~20출력

        // 빌더를 이용한 초기화
        IntStream is_builder = IntStream.builder().add(1).add(3).add(5).build();
        Stream<Integer> is_builder2 = Stream.<Integer>builder().add(1).add(3).add(5).build();
        Stream<String> ss_builder = Stream.<String>builder().add("Eric").add("Elena").add("Java").build();

        //System.out.println(is_builder.count());
        //System.out.println(is_builder.sum());

        // 만약 스트림이 비어 있는 경우 count 와 sum 은 0을 출력하면 됩니다.
        // 하지만 average, min, max의 경우에는 표현할 수가 없기 때문에 Optional 을 이용해 리턴
        //is_builder.min().ifPresent(System.out::println);
        //is_builder.max().ifPresent(System.out::println);
        //is_builder.average().ifPresent(System.out::println);
        // 또는
        System.out.println(is_builder.min().getAsInt());
        //System.out.println(is_builder.max().getAsInt());
        //System.out.println(is_builder.average().getAsDouble());

        //IntStream is_random = new Random().ints(); // 임의의 수를 무한하게 스트림으로 생성
        //IntStream is_random = new Random().ints(0, 2); // 0~1 의 수를 무한하게 스트림으로 생성
        //IntStream is_random = new Random().ints(5); // 임의의 수를 랜덤으로 5개 스트림으로 생성
        IntStream is_random = new Random().ints(5, 0, 2); // 0~1 의 수를 랜덤으로 5개 스트림으로 생성
        //is_random.forEach(System.out::println);

        // 무한 스트림
        IntStream infinite_stream = IntStream.iterate(2, n -> n + 2);   // 2, 4, 6, 8, 10, ...
        IntStream infinite_stream2 = IntStream.generate(() -> 1).limit(5); // 1, 1, 1, 1, 1
        DoubleStream infinite_stream3 = DoubleStream.generate(Math::random).limit(5);

        // double sum = infinite_stream3.sum();
        // bw.write(sum + " ");

        // IntStream is_filter_test = IntStream.range(0, 100);
        // is_filter_test.filter(n -> n % 2 != 0).forEach(n -> {
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // IntStream is_distinct_test = new Random().ints(100, 0, 50); // 0~49
        // is_distinct_test.distinct().forEach(n -> {
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // IntStream is_map_test = IntStream.range(0, 100);
        // is_map_test.map(n -> n*3).forEach(n -> {
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // String[] sa = {"I study hard", "You study JAVA", "I am hungry"};
        // Stream<String> ss = Arrays.stream(sa);
        // ss.flatMap(s -> Stream.of(s.split(" +"))).forEach(s -> {
        //     try {
        //         bw.write(s+"\n");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // IntStream is_skip_test = IntStream.range(0, 10);
        // is_skip_test.skip(3).forEach(n -> { // 0, 1, 2 를 제외한 나머지
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // IntStream is_sort_test = new Random().ints(100, 0, 50); // 0~49
        // is_sort_test.sorted().distinct().forEach(n -> {
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });

        // IntStream is_sort_test = new Random().ints(100, 0, 50); // 0~49
        // is_sort_test.
        // sorted().
        // peek(n -> { // 중간 결과를 보여준다
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // }).
        // distinct().
        // forEach(n -> {
        //     try {
        //         bw.write(n+" ");
        //     }
        //     catch(Exception e) {
        //         e.printStackTrace();
        //     }
        // });


        // Stream<String> stream1 = Stream.of("넷", "둘", "셋", "하나");
        // Stream<String> stream2 = Stream.of("넷", "둘", "셋", "하나");
        // Optional<String> result1 = stream1.reduce((s1, s2) -> s1 + "++" + s2);
        // result1.ifPresent(System.out::println);
        // String result2 = stream2.reduce("시작", (s1, s2) -> s1 + "++" + s2);
        // System.out.println(result2);


        // IntStream stream1 = IntStream.of(4, 2, 7, 3, 5, 1, 6);
        // IntStream stream2 = IntStream.of(4, 2, 7, 3, 5, 1, 6);
        // OptionalInt result1 = stream1.sorted().findFirst();  // 첫 번째 요소를 반환
        // System.out.println(result1.getAsInt());
        // OptionalInt result2 = stream2.sorted().findAny();   // 병렬 스트림일때 사용
        // System.out.println(result2.getAsInt());


        // IntStream stream1 = IntStream.of(30, 90, 70, 10);
        // IntStream stream2 = IntStream.of(30, 90, 70, 10);
        // IntStream stream3 = IntStream.of(30, 90, 70, 10);
        // System.out.println(stream1.anyMatch(n -> n > 80));  // 해당 스트림의 일부 요소가 특정 조건을 만족할 경우에 true를 반환함.
        // System.out.println(stream2.allMatch(n -> n > 80));  // 해당 스트림의 모든 요소가 특정 조건을 만족할 경우에 true를 반환함.
        // System.out.println(stream3.noneMatch(n -> n > 100));    // 해당 스트림의 모든 요소가 특정 조건을 만족하지 않을 경우에 true를 반환함.


        // IntStream stream1 = IntStream.of(30, 90, 70, 10);
        // IntStream stream2 = IntStream.of(30, 90, 70, 10);
        // IntStream stream3 = IntStream.of(30, 90, 70, 10);
        // System.out.println(stream1.count());
        // System.out.println(stream2.max().getAsInt());
        // System.out.println(stream3.min().getAsInt());


        // IntStream stream1 = IntStream.of(30, 90, 70, 10);
        // DoubleStream stream2 = DoubleStream.of(30.3, 90.9, 70.7, 10.1);
        // System.out.println(stream1.sum());
        // System.out.println(stream2.average().getAsDouble());


        // 스트림 -> 리스트
        // Stream<String> stream = Stream.of("넷", "둘", "하나", "셋");
        // List<String> list = stream.collect(Collectors.toList());
        // Iterator<String> iter = list.iterator();
        // while(iter.hasNext()) {
        //     System.out.print(iter.next() + " ");
        // }


        // Collectors 클래스의 partitioningBy() 메소드를 이용하여 해당 스트림의 각 요소별 글자 수에 따라 홀수와 짝수로 나누어 저장
        Stream<String> stream = Stream.of("HTML", "CSS", "JAVA", "PHP");
        Map<Boolean, List<String>> patition = stream.collect(Collectors.partitioningBy(s -> (s.length() % 2) == 0));
        List<String> oddLengthList = patition.get(false);
        System.out.println(oddLengthList);
        List<String> evenLengthList = patition.get(true);
        System.out.println(evenLengthList);

		bw.flush();
    }
}