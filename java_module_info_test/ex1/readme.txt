컴파일
javac -d mods/com.greetings src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

실행
java --module-path mods -m com.greetings/com.greetings.Main