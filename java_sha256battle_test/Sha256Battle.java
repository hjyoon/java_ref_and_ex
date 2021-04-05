import java.util.*;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.util.Scanner;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;


/*
1. 체력(1000~2000)
2. 공격력(100~200)
3. 주문력(100~200)
4. 방어력(0~75)% 데미지감소
5. 마법방어력(0~75)% 데미지감소
6. 흡혈(0~100)%
7. 주문흡혈(0~100)%
8. 크리티컬율(0~75)%
9. 크리티컬 공격력 증가율(100~250)%
10. 회피율(0~75)%
11. 스피드(0~100)
12. 연속공격율(0~75)%
*/

class Unit implements Comparable<Unit> {
	boolean log;
	String name;
	int max_hp;
	int current_hp;
	int ad;
	int ap;
	int ad_def;
	int ap_def;
	int ad_life_steal;
	int ap_life_steal;
	int critical;
	int critical_upto;
	int dodge;
	int spd;
	int chain;
	double ability_index;

    int elo_rating;
    int win;
    int lose;
    int draw;

	Unit(Unit u) {
		this.name = u.name;
		this.max_hp = u.max_hp;
		this.current_hp = u.current_hp;
		this.ad = u.ad;
		this.ap = u.ap;
		this.ad_def = u.ad_def;
		this.ap_def = u.ap_def;
		this.ad_life_steal = u.ad_life_steal;
		this.ap_life_steal = u.ap_life_steal;
		this.critical = u.critical;
		this.critical_upto = u.critical_upto;
		this.dodge = u.dodge;
		this.spd = u.spd;
		this.chain = u.chain;
		this.log = u.log;

		elo_rating = 1000;
		rating_to_ability_index();
	}

	Unit(String name, byte[] seed, boolean log) {
		this.name = name;
		this.log = log;

		max_hp = (int)seed[0] & 0x000000ff;
		max_hp = (int)((max_hp/255.0)*1000+1000);

		current_hp = max_hp;

		ad = (int)seed[1] & 0x000000ff;
		ad = (int)((ad/255.0)*100+100);

		ap = (int)seed[2] & 0x000000ff;
		ap = (int)((ap/255.0)*100+100);

		ad_def = (int)seed[3] & 0x000000ff;
		ad_def = (int)((ad_def/255.0)*75);

		ap_def = (int)seed[4] & 0x000000ff;
		ap_def = (int)((ap_def/255.0)*75);

		ad_life_steal = (int)seed[5] & 0x000000ff;
		ad_life_steal = (int)((ad_life_steal/255.0)*100);

		ap_life_steal = (int)seed[6] & 0x000000ff;
		ap_life_steal = (int)((ap_life_steal/255.0)*100);

		critical = (int)seed[7] & 0x000000ff;
		critical = (int)((critical/255.0)*75);

		critical_upto = (int)seed[8] & 0x000000ff;
		critical_upto = (int)((critical_upto/255.0)*150+100);

		dodge = (int)seed[9] & 0x000000ff;
		dodge = (int)((dodge/255.0)*75);

		spd = (int)seed[10] & 0x000000ff;
		spd = (int)((spd/255.0)*100);

		chain = (int)seed[11] & 0x000000ff;
		chain = (int)((chain/255.0)*75);

		elo_rating = 1000;
		rating_to_ability_index();
	}

	void attack(Unit target) {
		Random r = new Random();

		while(true) {
			int tmp_ad = this.ad;
			int tmp_ap = this.ap;

			if(log == true) System.out.print(name + "의 ");
			if(r.nextDouble() <= (this.critical/100.0)) {
				if(log == true) System.out.print("크리티컬 ");
				tmp_ad = (int)(tmp_ad * (this.critical_upto/100.0));
			}
			if(log == true) System.out.println("공격!! ");
			if(r.nextDouble() > target.dodge/100.0) {
				tmp_ad = (int)(tmp_ad * ((100-target.ad_def)/100.0));
				target.current_hp = target.current_hp - tmp_ad;
				this.current_hp = this.current_hp + (int)(tmp_ad*(this.ad_life_steal/100.0));
				if(this.current_hp > this.max_hp) {
					this.current_hp = this.max_hp;
				}
				if(log == true) System.out.println(target.name + "가 " + tmp_ad + "의 물리 피해를 입었다!! " + "+" + (int)(tmp_ad*(this.ad_life_steal/100.0)) + " 체력 회복!!" );
			}
			else {
				if(log == true) System.out.println(target.name + "가 공격을 회피!!");
			}

			tmp_ap = (int)(tmp_ap * ((100-target.ap_def)/100.0));
			target.current_hp = target.current_hp - tmp_ap;
			this.current_hp = this.current_hp + (int)(tmp_ap*(this.ap_life_steal/100.0));
			this.current_hp = this.current_hp + (int)(tmp_ad*(this.ad_life_steal/100.0));
			if(this.current_hp > this.max_hp) {
				this.current_hp = this.max_hp;
			}
			if(log == true) System.out.println(target.name + "가 " + tmp_ap + "의 마법 피해를 입었다!! " + "+" + (int)(tmp_ap*(this.ap_life_steal/100.0)) + " 체력 회복!!" );
			if(log == true) System.out.println(target.name + "의 남은 체력 : " + target.current_hp + "/" + target.max_hp + '\n');

			if(r.nextDouble() <= (this.chain/100.0) && target.current_hp > 0) {
				if(log == true) System.out.println(name + "의 연속공격!!" + '\n');
				continue;
			}
			else {
				break;
			}

		}
	}

	void rating_to_ability_index() {
        ability_index = Math.pow(10, (elo_rating-1000)/(double)400);
    }

    void set_elo_rating(int elo_rating) {
        this.elo_rating = elo_rating;
        rating_to_ability_index();
    }

	void show_stats() {
		System.out.println("이름 : " + name);
		System.out.println("체력 : " + max_hp);
		System.out.println("공격력 : " + ad);
		System.out.println("주문력 : " + ap);
		System.out.println("방어력 : " + ad_def + "%");
		System.out.println("마법방어력 : " + ap_def + "%");
		System.out.println("흡혈율 : " + ad_life_steal + "%");
		System.out.println("주문흡혈율 : " + ap_life_steal + "%");
		System.out.println("크리티컬율 : " + critical + "%");
		System.out.println("크리티컬 공격력 증가율 : " + critical_upto + "%");
		System.out.println("회피율 : " + dodge + "%");
		System.out.println("스피드 : " + spd);
		System.out.println("연속공격율 : " + chain + "%");
		System.out.println("");
	}

	@Override
    public int compareTo(Unit d) {
        if(elo_rating < d.elo_rating) {
            return 1;
        }
        else if(elo_rating > d.elo_rating) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

class Battle {
	boolean log;
	Unit u1;
	Unit u2;
	Unit tmp_u1;
	Unit tmp_u2;
	int cnt_u1_w;
	int cnt_u2_w;
	int draw;
	int cnt;
	int min_cnt;
	int max_cnt;
	int avg_cnt;

	Battle(Unit u1, Unit u2, boolean log) {
		this.log = log;
		cnt_u1_w = 0;
		cnt_u2_w = 0;
		draw = 0;
		this.u1 = u1;
		this.u2 = u2;
		cnt = 1;
		min_cnt = 100;
		max_cnt = 0;
		avg_cnt = 0;
	}

	void reset() {
		tmp_u1 = new Unit(u1);
		tmp_u2 = new Unit(u2);
		cnt = 1;
	}

	void start() {
		int total_game = 200;
		for(int i=1; i<=total_game; i++) {
			reset();
			if(log == true) System.out.println(i + "/" + "1000" + " 라운드 시작");
			while(true) {
				if(log == true) System.out.println("cnt : " + cnt);
				cnt++;
				if(tmp_u1.spd > tmp_u2.spd) {
					tmp_u1.attack(tmp_u2);
					if(tmp_u2.current_hp <= 0) {
						cnt_u1_w++;
						break;
					}
					tmp_u2.attack(tmp_u1);
					if(tmp_u1.current_hp <= 0) {
						cnt_u2_w++;
						break;
					}
				}
				else if(tmp_u1.spd < tmp_u2.spd) {
					tmp_u2.attack(tmp_u1);
					if(tmp_u1.current_hp <= 0) {
						cnt_u2_w++;
						break;
					}
					tmp_u1.attack(tmp_u2);
					if(tmp_u2.current_hp <= 0) {
						cnt_u1_w++;
						break;
					}
				}
				else {
					if(i%2 == 0) {
						tmp_u1.attack(tmp_u2);
						if(tmp_u2.current_hp <= 0) {
								cnt_u1_w++;
								break;
						}
						tmp_u2.attack(tmp_u1);
						if(tmp_u1.current_hp <= 0) {
							cnt_u2_w++;
							break;
						}
					} 
					else {
						tmp_u2.attack(tmp_u1);
						if(tmp_u1.current_hp <= 0) {
							cnt_u2_w++;
							break;
						}
						tmp_u1.attack(tmp_u2);
						if(tmp_u2.current_hp <= 0) {
							cnt_u1_w++;
							break;
						}
					}
				}
				if(cnt == 100) {
					draw++;
					break;
				}
			}

			// 조기 끝냄. (남은 판수를 모두 이겨도 어차피 질 경우)
			int rest_round = total_game-(cnt_u1_w+cnt_u2_w+draw);
			if(cnt_u1_w > cnt_u2_w+rest_round) {
				break;
			}
			else if(cnt_u2_w > cnt_u1_w+rest_round) {
				break;
			}

			if(cnt < min_cnt) {
				min_cnt = cnt;
			}
			if(cnt > max_cnt) {
				max_cnt = cnt;
			}
			avg_cnt = avg_cnt + cnt;
		}
	}

	int who_win() {
		if(cnt_u1_w > cnt_u2_w) {
			return 1;
		}
		else if(cnt_u1_w < cnt_u2_w) {
			return 2;
		}
		else {
			return 0;
		}
	}

	void show_res() {
		System.out.println(u1.name + " 승 : " + cnt_u1_w);
		System.out.println(u2.name + " 승 : " + cnt_u2_w);
		System.out.println("무승부 : " + draw);
		System.out.println("최대 cnt : " + max_cnt);
		System.out.println("최소 cnt : " + min_cnt);
		System.out.println("평균 cnt : " + avg_cnt / 1000.0);
		System.out.println("");
	}
}

public class Sha256Battle {
	static byte[] make_status_from_name(String name) throws Exception {
		//MessageDigest mDigest = MessageDigest.getInstance("MD5");
		//MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(name.getBytes());
		byte[] s = mDigest.digest();
		return s;
	}

	static Unit make_unit(String name) throws Exception {
		byte[] s = make_status_from_name(name);
		Unit u = new Unit(name, s, false);
		return u;
	}

	static void print_current_time() throws Exception {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt+"");
	}

	static Unit make_unit_by_random() throws Exception {
		char[] c = new char[16];
		for(int i=0; i<16; i++) {
			Random r = new Random();
			c[i] = (char)(r.nextInt(10)+48);
		}
		String str = new String(c);
		MessageDigest mDigest_test = MessageDigest.getInstance("SHA-256");
		mDigest_test.update(str.getBytes());
		byte[] s = mDigest_test.digest();
		Unit u = new Unit(str, s, false);
		return u;
	}

	static double two_ability_index_to_win_rate(double i1, double i2) {
        // i1 : winner, i2 : loser
        double tmp = i1/i2;
        double res = tmp/(tmp+1);
        return res;
    }

    static int cal_point(double winner_index, double loser_index) {
        double expected_win_rate = two_ability_index_to_win_rate(winner_index, loser_index);
        int K = 40; // 변동 상수
        int res = (int)Math.round(K * (1 - expected_win_rate));
        return res;
    }

    static void start_league(Unit[] u_arr) {
    	for(int i=0; i<u_arr.length; i++) {
			System.out.println(i+"");
			for(int j=0; j<u_arr.length; j++) {
				if(i == j) {
					continue;
				}
				Battle b = new Battle(u_arr[i], u_arr[j], false);
				b.start();
				if(b.who_win() == 1) {
					u_arr[i].win++;
					u_arr[j].lose++;
					int point_value = cal_point(u_arr[i].ability_index, u_arr[j].ability_index);
            		u_arr[i].set_elo_rating(u_arr[i].elo_rating+point_value);
            		u_arr[j].set_elo_rating(u_arr[j].elo_rating-point_value);
				}
				else if(b.who_win() == 2) {
					u_arr[j].win++;
					u_arr[i].lose++;
					int point_value = cal_point(u_arr[j].ability_index, u_arr[i].ability_index);
            		u_arr[i].set_elo_rating(u_arr[i].elo_rating-point_value);
            		u_arr[j].set_elo_rating(u_arr[j].elo_rating+point_value);
				}
				else {
					u_arr[i].draw++;
					u_arr[j].draw++;
					int point_value_i = cal_point(u_arr[i].ability_index, u_arr[j].ability_index);
					int point_value_j = cal_point(u_arr[j].ability_index, u_arr[i].ability_index);
					if(point_value_i > point_value_j) {
						int point_value = (int)Math.round((point_value_i - point_value_j)/2.0);
						u_arr[i].set_elo_rating(u_arr[i].elo_rating-point_value);
						u_arr[j].set_elo_rating(u_arr[j].elo_rating+point_value);
						//System.out.println(point_value+"");
						//System.out.println(u_arr[i].elo_rating+"");
						//System.out.println(u_arr[j].elo_rating+"");
					}
					else if(point_value_i < point_value_j) {
						int point_value = (int)Math.round((point_value_j - point_value_i)/2.0);
						u_arr[i].set_elo_rating(u_arr[i].elo_rating+point_value);
            			u_arr[j].set_elo_rating(u_arr[j].elo_rating-point_value);
            			//System.out.println(point_value+"");
						//System.out.println(u_arr[i].elo_rating+"");
						//System.out.println(u_arr[j].elo_rating+"");
					}
					else {
						;
					}
				}
				//b.show_res();
			}
		}
    }

	public static void main(String[] args) throws Exception {
		// Unit u1 = make_unit("8208443868628278");
		// Unit u2 = make_unit("2673473497540801");
		// u1.show_stats();
		// u2.show_stats();

		// Battle b = new Battle(u1, u2, false);
		// b.start();
		// b.show_res();

		// while(true) {
		// 	Unit u_test = make_unit_by_random();

		// 	Battle b = new Battle(u1, u_test, false);
		// 	b.start();

		// 	if(b.who_win() == 2) {	// if u_test is winner
		// 		print_current_time();

		// 		b.show_res();
		// 		u1 = new Unit(u_test);
		// 		u1.show_stats();
		// 	}
		// }

		// 랜덤하게 100명 뽑아서 리그 돌리기
		Unit[] u_arr = new Unit[200];
		for(int i=0; i<u_arr.length; i++) {
			u_arr[i] = make_unit_by_random();
			//System.out.println(u_arr[i].elo_rating+"");
		}

		u_arr[0] = make_unit("8208443868628278");
		u_arr[1] = make_unit("5748031606021609");
		u_arr[2] = make_unit("7605956498410339");
		u_arr[3] = make_unit("0574711560079329");
		u_arr[4] = make_unit("2472296181078861");
		u_arr[5] = make_unit("0713731968599362");
		u_arr[6] = make_unit("2140942590770770");
		u_arr[7] = make_unit("4593614898044874");
		u_arr[8] = make_unit("0138766434017654");
		u_arr[9] = make_unit("9613664440410810");
		u_arr[10] = make_unit("6324742645541613");
		u_arr[11] = make_unit("4593614898044874");
		u_arr[12] = make_unit("8518912612895455");

		for(int i=0; i<3; i++) {
			start_league(u_arr);
		}

		Arrays.sort(u_arr);

		for(int i=0; i<u_arr.length; i++) {
			System.out.println(i+" "+u_arr[i].name+" "+u_arr[i].elo_rating+" "+u_arr[i].win+" "+u_arr[i].lose+" "+u_arr[i].draw+"");
		}
	}
}
