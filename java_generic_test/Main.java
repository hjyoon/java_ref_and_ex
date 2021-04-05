import java.io.*;
import java.math.*;
import java.util.*;

class Product<T, M> {
    private T kind;
    private M model;
 
    public T getKind() {
        return kind;
    }
 
    public void setKind(T kind) {
        this.kind = kind;
    }
 
    public M getModel() {
        return model;
    }
 
    public void setModel(M model) {
        this.model = model;
    }
}

class ChildProduct<T, M, C> extends Product<T, M> {
    private C company;
 
    public C getCompany() {
        return company;
    }
 
    public void setCompany(C company) {
        this.company = company;
    }
}

interface Storage<T> {
    public void add(T item, int index);
    public T get(int index);
}

class StorageImpl<T> implements Storage<T> {
    private T[] array;
 
    public StorageImpl(int capacity) {
        this.array = (T[]) (new Object[capacity]);
    }
 
    @Override
    public void add(T item, int index) {
        array[index] = item;
    }
 
    @Override
    public T get(int index) {
        return array[index];
    }
}

class Box<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

}

class Util {
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<T>();
        box.setT(t);
        return box;
    }
}

class Course<T> {
    private String name;
    private T[] students;
 
    public Course(String name, int capacity) {
        this.name = name;
        students = (T[]) (new Object[capacity]);
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public T[] getStudents() {
        return students;
    }
 
    public void setStudents(T[] students) {
        this.students = students;
    }
 
    public void add(T t) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = t;
                break;
            }
        }
    }
}

class Person {
    private String name;
    Person(String name) {
        this.name = name;
    }
}

class Worker extends Person {
    Worker(String name) {
        super(name);
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }
}

class HighStudent extends Student {
    HighStudent(String name) {
        super(name);
    }
}

public class Main {
	public static void main(String[] args) throws Exception {
        Box<Integer> box1 = Util.<Integer>boxing(100);
        int intValue = box1.getT();

        Box<String> box2 = Util.boxing("암묵적호출");    // 타입 추론
        String stringValue = box2.getT();

        System.out.println("intValue : " + intValue);
        System.out.println("stringValue : " + stringValue);

        Course<Person> personCourse = new Course<>("일반인 과정", 5);
        personCourse.add(new Person("일반인"));
        personCourse.add(new Worker("직장인"));
        personCourse.add(new Student("직장인"));
        personCourse.add(new HighStudent("직장인"));
 
        Course<Worker> workCourse = new Course<>("직장인 과정", 5);
        workCourse.add(new Worker("직장인"));
 
        Course<Student> studentCourse = new Course<>("학생 과정", 5);
        studentCourse.add(new Student("학생"));
        studentCourse.add(new HighStudent("고등학생"));
 
        Course<HighStudent> highStudentCourse = new Course<>("고등학생 과정", 5);
        highStudentCourse.add(new HighStudent("고등학생"));
 
        registerCourse(personCourse);
        registerCourse(workCourse);
        registerCourse(studentCourse);
        registerCourse(highStudentCourse);
 
        System.out.println();
 
        registerCourseStudent(studentCourse);
        registerCourseStudent(highStudentCourse);
 
        System.out.println();
 
        registerCourseWorker(workCourse);
        registerCourseWorker(personCourse);

        // 와일드카드를 사용한 예시
        List<?> unboundList = new ArrayList<>();
        List<? extends Foo> upperList = new ArrayList<>();
        List<? super Bar> lowerList = new ArrayList<>();
	}

    public static void registerCourse(Course<?> course) {   // 클래스 제한 없이 모두 인자로 받음
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }
 
    public static void registerCourseStudent(Course<? extends Student> course) {    // Student를 포함한 자식들만 인자로 받음
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }
 
    public static void registerCourseWorker(Course<? super Worker> course) {    // Worker를 포함한 부모, 조상들만 인자로 받음
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }
}

/*
Generic으로 구현된 메소드의 경우에는 선언된 타입으로만 매개변수를 입력해야한다.
이를 상속받은 클래스, 혹은 부모 클래스를 매개변수로 사용하고 싶어도 불가능하며,
혹은 그 어떤 타입이 와도 상관 없는 경우에 대응하기 좋지 않다.
이를 위한 해법으로 Wildcard를 사용한다.

WildCard에는 3가지 종류가 있다.
1. Unbound Wildcard          ex) List<?>
2. Upper Bounded Wildcard    ex) List<? extends Foo>
3. Lower Bounded Wildcard    ex) List<? super Foo>
*/


class Foo {
    public void foo() { }
}

class Bar extends Foo {
    public void bar() { }

}

class WildCard {
   public <T extends Foo> void test(List<T> fooList){
        fooList.get(0).foo();
        // fooList.get(0).bar(); // Error
        // Foo를 상속받은 Bar의 메서드이지만 Bar의 존재를 알 수 없기때문에 이는 불가능하다
    }
}