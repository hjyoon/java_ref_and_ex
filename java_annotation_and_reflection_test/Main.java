import java.io.*;
import java.util.*;
import java.lang.annotation.*;
import java.lang.reflect.*;

/*
어노테이션 사용의 대표적인 목적
1. 컴파일러를 위한 정보제공 :  Java 8 @FunctionalInterface , @supresswarnings
2. 자동 문서 작성 :  generate reports automatically like Jenkins, Jira or Teamcity.
3. 코드 자동 생성 :  A good example of this is the JAXB library.
4. 런타임 프로세싱 : testing (Junit), dependency injection (Spring), logging (Log4J) ,data access (Hibernate) etc.
*/

// @interface "어노테이션 명" : 이 키워드는 자바 컴파일러에게 "어노테이션 명"이 어노테이션 정의라는걸 알려준다.
// 요소들은 인터페이스에서의 메서드 정의와 유사하다. 자바 기본요소들을 모두 사용할수있고, 배열도 사용가능하다. (복잡객체사용은 불가능) 

// 새로운 어노테이션 MyAnnotation 정의
@interface MyAnnotation {
    String value() default ""; // 요소에 기본값을 설정할수 있다. 어노테이션 선언 시, 해당 요소에 값을 넣지 않으면 기본값이 들어감.
    String name();
    int age();
    String[] newNames();
}

// MyAnnotation 어노테이션 사용
@MyAnnotation(value="123", name="Jakob", age=37, newNames={"Jenkov", "Peterson"})

/*
어노테이션 예제
1. @Entity(tableName = "vehicles") : 자바 어노테이션은 값을 세팅할수있는 요소들을 가질 수 있는데 속성이나 파라미터라  볼 수 있다.
2. @Entity(tableName = "vehicles", primaryKey = "id") : 아래와 같이 여러개의 요소를 가질수도 있다.
3. @InsertNew("yes") : 하나만 요소로 가질 경우 다음과 같이 짧게 줄여 쓸 수 있다.
*/

/*
자바에서 기본적으로 제공되는 어노테이션 
1. @Deprecated : 해당 클래스/메소드 등은 더이상 지원하지 않으니 or  만들고 나니깐 별로 안좋은 부분이 많거나, 더 좋은 해결법이 생겼으니 사용하지 마시라는 의미
2. @Override : 슈퍼클래스에 대해 오버라이드 되었다는걸 알려줘. 따라서 만약 메서드가 슈퍼클래스와 매칭되지 않았으면 에러를 날려주지.
3. @SuppressWarnings : 해당 메소드에 대해서 compiler suppress warnings (컴파일러 경고 억제?)을 만들어. 예를들어 어떤 코드가 경고메세지를 수천개씩 쏟아내는데, 그게 보기싫을때 입막음 시켜버리는거야 조용히

메타 annotation type
1. @documented : 이 어노테이션을 자바독이나 유사한 도구로 문서화되어져야하는데 사용하는 엘리먼트를 지칭

2. @Target : 자신이 만든 어노테이션이 사용되게될 자바 요소를 지정
Ex)
@Target({ElementType.ANNOTATION_TYPE})
ElementType.CONSTRUCTOR
ElementType.FIELD
ElementType.LOCAL_VARIABLE
ElementType.METHOD
ElementType.PACKAGE
ElementType.PARAMETER
ElementType.TYPE

Ex2) 메소드에서만 사용가능한 어노테이션을 정의
@Target({ElementType.METHOD}) @interface MyAnnotation {
    String value();
}

3. @Inherited : 자동으로 상속받는 어노테이션 타입을 지칭

4. @Retention : 어노테이션된 타입이 얼마나 지속되는가를 지칭
@Retention(RetentionPolicy.SOURCE)
@Retention(RetentionPolicy.CLASS)
@Retention(RetentionPolicy.RUNTIME)

Java 8 에서의 어노테이션
1. @Repeatable : 해당 어노테이션을 여러번 사용할 수 있도록 함
Ex)
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE_USE )
@Repeatable( RepeatedValues.class )
public @interface CanBeRepeated {
    String value();
}

@CanBeRepeated( "the color is green" )
@CanBeRepeated( "the color is red" )
@CanBeRepeated( "the color is blue" )
public class RepeatableAnnotated {
    ...
}

2. @FunctionalInterface : 함수형 인터페이스 (람다식 사용을 위함)
*/

// [메소드에 지정할 어노테이션 정보들을 정의해놓은 인터페이스]
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface MethodInfo {
    String author() default "Pankaj";
    String date();
    int revision() default 1;
    String comments();
}

// [어노테이션을 적용한 메소드들이 정의되어 있는 클래스 예]
class AnnotationExample {
    @Override
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012", revision=1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2012")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List list = new ArrayList();
        list.add("abc");
        oldMethod();
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        try {
            for(Method method : Main.class.getClassLoader().loadClass(("AnnotationExample")).getMethods()) {
                if(method.isAnnotationPresent(MethodInfo.class)) {
                    try {
                        //iterates all the annotations available in the method
                        for(Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method" +method+ " : " +anno);
                        }
                        
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if(methodAnno.revision()==1) {
                            System.out.println("Method with revision no 1 = " +method);
                        }

                    }
                    catch (Throwable ex) {  
                        ex.printStackTrace();  
                    }  
                }  
            }  
        }
        catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();  
        }
    }
}