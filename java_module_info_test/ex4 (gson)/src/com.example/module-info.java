/*
	opens 없으면 아래와 같은 오류가 뜬다.
	Exception in thread "main" java.lang.reflect.InaccessibleObjectException:
	Unable to make field private java.lang.String com.example.Data.userName accessible:
	module com.example does not "opens com.example" to module com.google.gson
*/

module com.example {
    requires com.google.gson;
    opens com.example to com.google.gson;
}