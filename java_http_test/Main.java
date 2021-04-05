import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.net.*;
import java.net.http.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://trendpoker.de/"))
                .build();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Pattern p = Pattern.compile("<font size=4><h7>.*</h7>");

        while(true) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // print response headers
            HttpHeaders headers = response.headers();
            //headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

            // print status code
            //System.out.println(response.statusCode());

            // print response body
            //System.out.println(response.body());

            String body = response.body();

            Matcher m = p.matcher(body);
            
            //boolean b = m.matches();

            String format_time1 = format1.format(System.currentTimeMillis());
           
            bw.write(format_time1 + " : ");

            if(m.find()){ // find가 group보다 선행되어야 합니다.
                bw.write(m.group().substring(17, 26)+"\n");
            }
            else {
                bw.write("no players\n");
            }

            //bw.write(b+"");
            bw.flush();

            Thread.sleep(15000);
        }
    }
}