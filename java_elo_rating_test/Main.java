import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.time.*;

class Player {
    public int win;
    public int lose;
    public int total;
    public double win_rate;
    public double lose_rate;
    public double ability_index;
    public int elo_rating;

    Player(int win, int lose) {
        this.win = win;
        this.lose = lose;
        cal_elo_rating();
    }

    Player(int elo_rating) {
        this.elo_rating = elo_rating;
        rating_to_ability_index();
    }

    void set_elo_rating(int elo_rating) {
        this.elo_rating = elo_rating;
        rating_to_ability_index();
    }

    void cal_elo_rating() {
        total = win+lose;
        win_rate = win / (double)total;
        lose_rate = 1-win_rate;
        ability_index = win_rate / lose_rate;
        elo_rating = (int)Math.round(1000 + 400*Math.log10(ability_index));
    }

    void rating_to_ability_index() {
        ability_index = Math.pow(10, (elo_rating-1000)/(double)400);
    }
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static double two_ability_index_to_win_rate(double i1, double i2) {
        // i1 : winner, i2 : loser
        double tmp = i1/i2;
        return tmp/(tmp+1);
    }

    static int cal_point(double winner_index, double loser_index) throws Exception {
        double expected_win_rate = two_ability_index_to_win_rate(winner_index, loser_index);
        int K = 20; // 변동 상수
        //bw.write(winner_index+" "+loser_index+"\n");
        //bw.write(K * (1 - expected_win_rate)+"\n");
        int res = (int)Math.round(K * (1 - expected_win_rate));
        return res;
    }

    public static void main(String[] args) throws Exception {
        //br = new BufferedReader(new FileReader("../input.txt"));
        //br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //Player p1 = new Player(10, 10); // win, lose
        Player p1 = new Player(0); // elo rating

        //Player p2 = new Player(30, 10);
        Player p2 = new Player(-179); // elo rating

        bw.write(p1.elo_rating+"\n");
        bw.write(p2.elo_rating+"\n");

        if(false) {
            // p1 is winner;
            int point_value = cal_point(p1.ability_index, p2.ability_index);
            p1.set_elo_rating(p1.elo_rating+point_value);
            p2.set_elo_rating(p2.elo_rating-point_value);
            bw.write(point_value+"\n");
        }
        else {
            // p2 is winner;
            int point_value = cal_point(p2.ability_index, p1.ability_index);
            p1.set_elo_rating(p1.elo_rating-point_value);
            p2.set_elo_rating(p2.elo_rating+point_value);
            bw.write(point_value+"\n");
        }

        bw.write(p1.elo_rating+"\n");
        bw.write(p2.elo_rating+"\n");

        bw.flush();
    }
}