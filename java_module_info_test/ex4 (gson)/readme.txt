컴파일
javac -d mods/com.example src/com.example/module-info.java src/com.example/com/example/Main.java

한 번에 모두 컴파일
javac -d mods --module-source-path src $(find src -name "*.java")
또는
javac --module-path mlib -d mods --module-source-path src --module com.example

실행
java -p mlib;mods -m com.example/com.example.Main