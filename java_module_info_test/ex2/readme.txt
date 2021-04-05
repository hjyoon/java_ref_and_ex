컴파일
javac -d mods/org.astro src/org.astro/module-info.java src/org.astro/org/astro/World.java
javac --module-path mods -d mods/com.greetings src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

한 번에 컴파일
javac -d mods --module-source-path src $(find src -name "*.java")
또는
javac -d mods --module-source-path src --module com.greetings,org.astro

※ 소스가 여러 개일 때는, --module 옵션으로 컴파일할 모듈을 지정하면 포함된 모든 소스를 컴파일한다.
※ $(find src -name "*.java") 문은 리눅스에서만 동작한다.

실행
java --module-path mods -m com.greetings/com.greetings.Main

jar로 패키징
jar --create --file=mlib/org.astro@1.0.jar --module-version=1.0 -C mods/org.astro .
jar --create --file=mlib/com.greetings.jar --main-class=com.greetings.Main -C mods/com.greetings .

※ --create는 -c로 줄여 쓸 수 있습니다.
※ "-C mods/org.astro ."는 디렉토리를 mods/org.astro로 변경한 다음 해당 디렉토리에 있는 모든 것을 포함하도록 지시한다.

jar 실행
java -p mlib -m com.greetings

※ --module-path는 -p로 줄여 쓸 수 있습니다.
※ --module-source-path는 실제 모듈의 소스코드가 위치한 루트 디렉터리를 가리킨다.
※ --module는 -m로 줄여 쓸 수 있습니다. 그리고 -m 옵션은 main 모듈을 지정한다.

패키징된 모듈의 선언을 출력
jar --describe-module --file=mlib/org.astro@1.0.jar

Packing a Java Module as a Standalone Application using jlink
jlink --module-path $JAVA_HOME/jmods;mlib --add-modules com.greetings --output greetingsapp

Running the Standalone Application
.\bin\java --module com.greetings/com.greetings.Main