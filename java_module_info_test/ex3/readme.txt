컴파일
javac -d mods/com.socket src/com.socket/module-info.java src/com.socket/com/socket/NetworkSocket.java src/com.socket/com/socket/spi/NetworkSocketProvider.java
javac --module-path mods -d mods/org.fastsocket src/org.fastsocket/module-info.java src/org.fastsocket/org/fastsocket/FastNetworkSocketProvider.java src/org.fastsocket/org/fastsocket/FastNetworkSocket.java
javac --module-path mods -d mods/com.greetings src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

한 번에 모두 컴파일
javac -d mods --module-source-path src $(find src -name "*.java")
또는
javac -d mods --module-source-path src --module com.greetings,com.socket,org.fastsocket

실행
java -p mods -m com.greetings/com.greetings.Main