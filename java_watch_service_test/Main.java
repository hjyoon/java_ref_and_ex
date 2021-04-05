import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;
import java.nio.file.WatchEvent.*;

/*
    주의 사항, 알아야 할 것

    - watchKey는 한 번 사용하고 나면 반드시 reset()을 호출해줘야 다시 이벤트를 받을 수 있다.
    - watchService는 이벤트 방식이다.
    - 한 번에 여러개의 이벤트가 발생할 수 있다.
    - 파일을 지정할 수 없다.
    - 무한 루프 주의
    - 디렉토리의 서브 디렉토리(하위 디렉토리)에 변경 사항이 생겨도 감지한다.
*/

public class Main {
    //프로젝트 경로
    private static final String projPath = System.getProperty("user.dir");

    public static void main(String args[]) throws Exception {
        //watchService 생성
        WatchService watchService = FileSystems.getDefault().newWatchService();

        //경로 생성
        Path path = Paths.get(projPath);

        //해당 디렉토리 경로에 와치서비스와 이벤트 등록
        path.register(watchService,
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_DELETE,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.OVERFLOW);

        while(true) {
            WatchKey watchKey = watchService.take(); //이벤트가 오길 대기(Blocking)
            List<WatchEvent<?>> events = watchKey.pollEvents(); //이벤트들을 가져옴
            for(WatchEvent<?> event : events) {
                //이벤트 종류
                Kind<?> kind = event.kind();

                //경로
                Path paths = (Path)event.context();

                System.out.println(paths.toAbsolutePath()); // C:\...\...\test.txt

                if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                    System.out.println("created something in directory");
                }
                else if(kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    System.out.println("delete something in directory");
                }
                else if(kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                    System.out.println("modified something in directory");
                }
                else if(kind.equals(StandardWatchEventKinds.OVERFLOW)) {
                    System.out.println("overflow");
                }
                else {
                    System.out.println("hello world");
                }
            }
            if(!watchKey.reset()) {
                try {
                    watchService.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}