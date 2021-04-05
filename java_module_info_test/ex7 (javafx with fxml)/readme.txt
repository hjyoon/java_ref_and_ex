컴파일
javac --module-path C:\\javafx-sdk-11.0.2\\lib -d mods/me.hjyoon src/me.hjyoon/module-info.java src/me.hjyoon/me/hjyoon/Main.java

한 번에 모두 컴파일
javac -d mods --module-source-path src $(find src -name "*.java")
또는
javac --module-path C:\\javafx-sdk-11.0.2\\lib -d mods --module-source-path src --module me.hjyoon

실행
java -p C:\\javafx-sdk-11.0.2\\lib;mods -m me.hjyoon/me.hjyoon.Main

※ --module-path는 -p로 줄여 쓸 수 있습니다.
※ --module-source-path는 실제 모듈의 소스코드가 위치한 루트 디렉터리를 가리킨다.
※ --module는 -m로 줄여 쓸 수 있습니다. 그리고 -m 옵션은 main 모듈을 지정한다.

// module-info.java 작성 시, --add-modules 명령을 사용 할 필요가 없음. (대신, module-info.java에서 requires사용)