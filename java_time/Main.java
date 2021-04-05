import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static void pause() {
        try {
            System.err.println("Press Enter to Continue.");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 현재 시간을 입력
        LocalTime lt = LocalTime.now();
        //LocalTime lt = LocalTime.now(ZoneId.of("Asia/Tokyo"));    // 타임존 설정
        LocalDate ld = LocalDate.now();
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.now();

        // 타임스탬프를 나타낸다.
        // 타임스탬프 : UTC기준으로 1970년 1월1일 0시0분0초를 숫자 0으로 정하고,
        //             그로부터 경과된 시간을 양수 또는 음수로 표현
        Instant ins = Instant.now();
        lt = LocalTime.ofInstant(ins, ZoneId.of("Asia/Tokyo"));

        // UTC로부터 offset이 포함된 시간
        OffsetTime ot = OffsetTime.now();
        OffsetDateTime odt = OffsetDateTime.now();
        //bw.write(ot+"\n");
        //bw.write(odt+"\n");
        //bw.write(zdt+"\n");


        // 사용자가 직접 입력
        // lt = LocalTime.of(1, 40, 29);
        // lt = LocalTime.parse("18:23:30");   // 기본적인 형식일 경우 2번째 인자 생략 가능
        // lt = LocalTime.parse("18:23:30", DateTimeFormatter.ofPattern("HH:mm:ss"));
        // lt = LocalTime.parse("18:23:30", DateTimeFormatter.ISO_LOCAL_TIME);
        // ld = LocalDate.of(1993, 1, 4);
        // ldt = LocalDateTime.of(1993, 1, 4, 1, 40, 29);
        // ldt = LocalDateTime.of(ld, lt);

        // toString() 을 이용한 기본 출력
        // bw.write(lt+"\n");
        // bw.write(ld+"\n");
        // bw.write(ldt+"\n");
        // bw.write(zdt+"\n");

        // 직접 패턴을 설정하여 출력
        // bw.write(lt.format(DateTimeFormatter.ofPattern("HH:mm:ss:n"))+"\n");
        // bw.write(ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\n");
        // bw.write(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"\n");
        // bw.write(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV zzzz zzz zz z"))+"\n");

        // 이미 정의된 상수를 사용하여 출력
        // bw.write(lt.format(DateTimeFormatter.ISO_LOCAL_TIME)+"\n");
        // bw.write(ld.format(DateTimeFormatter.ISO_LOCAL_DATE)+"\n");
        // bw.write(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+"\n");
        // bw.write(zdt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)+"\n");

        // 비교
        // LocalTime lt2 = LocalTime.parse("18:30:00");
        // LocalTime lt3 = LocalTime.of(18, 30, 00);

        // if(lt2.equals(lt3)) {   // l2와 l3가 같은 시간일 경우 true
        //     bw.write("same\n");
        // }
        
        // if(lt2.compareTo(lt3) > 0) {
        //     bw.write("lt2 is bigger\n");
        // }
        // else if(lt2.compareTo(lt3) < 0) {
        //     bw.write("lt3 is bigger\n");
        // }
        // else {
        //     bw.write("lt2 and lt3 are same\n");
        // }

        // if(lt2.isAfter(lt3)) {  // lt2가 lt3 보다 이후의 시간일 경우 true
        //     bw.write("lt2 is bigger\n");
        // }
        // else if(lt2.isBefore(lt3)) {    // lt2가 lt3 보다 이전의 시간일 경우 true
        //     bw.write("lt3 is bigger\n");
        // }
        // else {
        //     bw.write("lt2 and lt3 are same\n");
        // }

        // 시간 연산
        // lt = LocalTime.now();
        // bw.write(lt+"\n");
        // bw.write(lt.plusHours(1)+"\n");     // 1시간을 더한다
        // bw.write(lt.plusMinutes(1)+"\n");   // 1분을 더한다
        // bw.write(lt.plusSeconds(1)+"\n");   // 1초를 더한다
        // bw.write(lt.plusNanos(1)+"\n");     // 1나노초를 더한다

        // bw.write(lt.minusHours(1)+"\n");     // 1시간을 뺀다
        // bw.write(lt.minusMinutes(1)+"\n");   // 1분을 뺀다
        // bw.write(lt.minusSeconds(1)+"\n");   // 1초를 뺀다
        // bw.write(lt.minusNanos(1)+"\n");     // 1나노초를 뺀다

        // 특정 필드의 값 가져오기
        // lt = LocalTime.now();
        // bw.write(lt+"\n");
        // bw.write(lt.getHour()+"\n");    // 시간을 가져온다
        // bw.write(lt.getMinute()+"\n");  // 분을 가져온다
        // bw.write(lt.getSecond()+"\n");  // 초를 가져온다
        // bw.write(lt.getNano()+"\n");    // 나노초를 가져온다

        // 상수를 이용
        // bw.write(lt.get(ChronoField.AMPM_OF_DAY)+"\n");    // 0이면 AM, 1이면 PM
        // bw.write(lt.get(ChronoField.CLOCK_HOUR_OF_DAY)+"\n");    // 시간을 가져온다 (1~24사이의 값)
        // bw.write(lt.get(ChronoField.CLOCK_HOUR_OF_AMPM)+"\n");    // 시간을 가져온다 (1~12사이의 값)
        // bw.write(lt.get(ChronoField.HOUR_OF_DAY)+"\n");    //   시간을 가져온다 (0~23사이의 값)
        // bw.write(lt.get(ChronoField.HOUR_OF_AMPM)+"\n");    //   시간을 가져온다 (0~11사이의 값)
        // bw.write(lt.get(ChronoField.MINUTE_OF_HOUR)+"\n");    // 분을 가져온다
        // bw.write(lt.get(ChronoField.MINUTE_OF_DAY)+"\n");    // 오늘 하루 중 경과한 총 분을 가져온다
        // bw.write(lt.get(ChronoField.SECOND_OF_MINUTE)+"\n");    // 초를 가져온다
        // bw.write(lt.get(ChronoField.SECOND_OF_DAY)+"\n");    // 오늘 하루 중 경과한 총 초를 가져온다

        // time에 사용가능한지, date에 사용가능한지 여부 확인
        // bw.write(ChronoField.SECOND_OF_DAY.isTimeBased()+"\n");
        // bw.write(ChronoField.SECOND_OF_DAY.isDateBased()+"\n");

        // 해당 상수의 값 범위 출력
        // bw.write(ChronoField.HOUR_OF_AMPM.range()+"\n");

        // 해당 상수가 가질 수 있는 값의 범위에 인자값이 포함 될 수 있는지 판단.
        // 만약 가질 수 있다면, 해당 값 그대로 출력, 없다면 예외 발생
        // 범위는 range() 메서드를 사용하여 알 수 있다.
        // bw.write(ChronoField.HOUR_OF_AMPM.checkValidIntValue(11)+"\n"); // int type로 반환
        // bw.write(ChronoField.HOUR_OF_AMPM.checkValidValue(11)+"\n");    // long type로 반환

        // 문자열을 이용하여 해당 상수를 찾아서 사용
        // bw.write(lt.get(ChronoField.valueOf("SECOND_OF_DAY"))+"\n\n");

        // 모든 상수 리스트
        // ChronoField[] cf = ChronoField.values();
        // for(ChronoField i : cf) {
        //     bw.write(i+"\n");
        // }

        // 해당 시간으로 설정
        // lt = lt.with(ChronoField.HOUR_OF_DAY, 10);
        // lt = lt.withHour(10);
        // lt = lt.withMinute(30);
        // lt = lt.withSecond(20);
        // lt = lt.withNano(100000000);    // 0.1초

        // 해당 상수 필드가 지원되는지 여부
        // bw.write(lt.isSupported(ChronoField.HOUR_OF_AMPM)+"\n");
        // bw.write(lt.isSupported(ChronoField.DAY_OF_MONTH)+"\n");
        // bw.write(lt.isSupported(ChronoUnit.HOURS)+"\n");
        // bw.write(lt.isSupported(ChronoUnit.DAYS)+"\n");

        // 해당 유닛 뒷부분을 제외
        // lt = lt.truncatedTo(ChronoUnit.HOURS);
        // lt = lt.truncatedTo(ChronoUnit.MINUTES);
        // lt = lt.truncatedTo(ChronoUnit.SECONDS);
        // lt = lt.truncatedTo(ChronoUnit.NANOS);

        // lt부터 해당 Time까지의 시간 (단위는 두번째 인자)
        // bw.write(lt.until(LocalTime.of(4, 16, 0), ChronoUnit.MINUTES)+"\n");

        // 시 분 초를 총 초로 나타낸다
        // bw.write(lt.toSecondOfDay()+"\n");

        // 총 초로 시 분 초 를 가지는 시간을 나타낸다
        // bw.write(lt.ofSecondOfDay(3661)+"\n");

        //bw.write(lt+"\n");

        LocalTime start = LocalTime.of(10, 35, 40);
        LocalTime end = LocalTime.of(23, 36, 50, 999999999);
        // 두 시간 사이의 간격을 초나 나노 초 단위로 나타냄
        Duration res = Duration.between(start, end);
        //res = res.plusDays(10);
        //bw.write(res.getSeconds()+"\n");
        //bw.write(res.toSeconds()+"\n");
        //bw.write(res.toSecondsPart()+"\n");
        //bw.write(res.toDays()+"\n");
        //bw.write(res.toDaysPart()+"\n");

        LocalDate start2 = LocalDate.of(1939, 9, 1);
        LocalDate end2 = LocalDate.of(1945, 9, 2);
        // 두 날짜 사이의 간격을 년 월 일 단위로 나타냄
        Period res2 = Period.between(start2, end2);
        res2 = res2.normalized();   // "1 Year and 15 months" will be normalized to "2 years and 3 months".
        bw.write(res2.getYears()+"\n");
        bw.write(res2.getMonths()+"\n");
        bw.write(res2.getDays()+"\n");
        bw.write(res2.toTotalMonths()+"\n");

        bw.flush();
    }
}