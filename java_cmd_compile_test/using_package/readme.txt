현재 디렉터리에서
java -d . Main.java

실행
java sample.Main

jar 만들기
jar -cvmf META-INF/manifest.mf output.jar sample/*.class

jar 실행
java -jar output.jar