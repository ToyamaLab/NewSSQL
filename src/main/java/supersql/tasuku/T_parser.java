package supersql.tasuku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;

public class T_parser {

	static ArrayList<String> ssql_parse = new ArrayList<String>();
	static String tmp = "";
	static int cnt = 0;
	static String ccc = "";

	static ArrayList<String> atmark_layout = new ArrayList<String>();
	static boolean l_end_flag = false;
	static boolean double_q_flag = false;
	static boolean atmark_flag = false;
	static boolean image_flag = false;
	static boolean a_flag = false;
	static boolean a_flag2 = false;
	static boolean a_flag3 = false;
	static boolean link_flag = false;
	static boolean asc_desc_flag = false;
	static boolean line_flag = false;
	//static boolean comment_flag = false;
	static boolean link_image_flag = false;
	static boolean link_image_flag2 = false;
	static int add_cnt = 0;
	static boolean add_flag = false;

	static int l_flag_cnt = 0;
	public static int[] nest_cnt = new int[64];
	static int bracket_cnt = 0;
	public static int[] bracket_level = new int[64];
	public static ArrayList<String> layout_load = new ArrayList<String>();
	public static ArrayList<String> layout_load_name = new ArrayList<String>();
	static String layout_load_name_tmp = "";
	static boolean layout_load_flag = false;
	public static int[] w_load = new int[64];
	public static int[] h_load = new int[64];
	public static int[] f_load = new int[64];
	public static int[] mt_load = new int[64];
	public static int[] mr_load = new int[64];
	public static int[] mb_load = new int[64];
	public static int[] ml_load = new int[64];
	static String l_tmp = "";
	static String a_tmp = "";
	public static ArrayList<String> image_name_List = new ArrayList<String>();

	static int[] sameName_cnt = new int[64];
	static String table_at = "";
	static boolean table_at_flag = true;
	static boolean table_at_flag2 = false;
	static ArrayList<String> line_list = new ArrayList<String>();
	static boolean pipe_flag = false;
	static boolean aggregate_flag = false;
	static boolean kakko_flag = false;

	static ArrayList<String> check_1 = new ArrayList<String>();
	static ArrayList<String> check_2 = new ArrayList<String>();
	static ArrayList<String> check_3 = new ArrayList<String>();
	static ArrayList<String> check_4 = new ArrayList<String>();
	static ArrayList<String> check_5 = new ArrayList<String>();
	static ArrayList<String> check_6 = new ArrayList<String>();
	static ArrayList<String> check_7 = new ArrayList<String>();
	static ArrayList<String> check_8 = new ArrayList<String>();
	static ArrayList<String> check_9 = new ArrayList<String>();

	public static void parser(String ssql) {


		for(int i = 0; i < 64; i++) {
			nest_cnt[i] = 99;
			bracket_level[i] = 99;
			w_load[i] = 0;
			h_load[i] = 0;
			f_load[i] = 0;
			mt_load[i] = 0;
			mr_load[i] = 0;
			mb_load[i] = 0;
			ml_load[i] = 0;
			sameName_cnt[i] = 2;
		}

		System.out.println("######### T_Parser Result ########");

		String tmp11 = "";
		String tmp12 = "";
		for(int i = 0; i < ssql.length(); i++) {
			ccc = String.valueOf(ssql.charAt(i));
			if(ccc.contains("@"))
				atmark_flag = true;
			if(atmark_flag == true && !ccc.contains("}"))
				tmp11 += ccc;
			else if(atmark_flag == true && ccc.contains("}")) {
				tmp11 += ccc;
				tmp11 = tmp11.replace("\"", "'");
				tmp12 += tmp11;
				tmp11 = "";
				atmark_flag = false;
			}
			else
				tmp12 += ccc;
		}
		ssql = tmp12;


		for(int i = 0; i < ssql.length(); i++) {
			ccc = String.valueOf(ssql.charAt(i));


			if(ccc.contains("(") && double_q_flag == false)
				kakko_flag = true;
			else if(ccc.contains(")")&& double_q_flag == false)
				kakko_flag = false;

			if(kakko_flag == false && double_q_flag == false && line_flag == false && a_flag == false && pipe_flag == false && link_image_flag==false
					&& (ccc.contains(" ") || ccc.contains("	")  ||  ccc.contains("	")))
				continue;

			if(ccc.contains("\n"))
				continue;

			if((tmp.contains("sum") || tmp.contains("max") || tmp.contains("min") || tmp.contains("avg") || tmp.contains("count")) && ccc.contains("[") ){
				//tmp = "";
				aggregate_flag = true;
			}
			if(aggregate_flag == true && ccc.contains("]")) {
				aggregate_flag = false;

				if(tmp != "") {
					tmp = tmp.concat(ccc);

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy7");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;

					}
				}

				tmp = "";

				continue;
			}

			else if(aggregate_flag == true && !ccc.contains("]")){
				tmp = tmp.concat(ccc);
				continue;
			}

			if(ccc.contains("{") && atmark_flag == false && link_flag == false && link_image_flag == false) {
				bracket_cnt++;
				add_cnt = 0;
				add_flag = false;
				//m_flag.add(true);
			}
			else if(ccc.contains("[") && aggregate_flag == false && double_q_flag == false) {
				l_flag_cnt++;
				//l_flag.add(true);
			}
			else if(ccc.contains("}") && atmark_flag == false && link_flag == false && link_image_flag == false) {
				bracket_cnt--;

				if(add_cnt == 1 && add_flag == false) {
					System.out.println("######### 666########");
					bracket_level[cnt-1]--;

			}

				add_cnt = 0;
				//add_flag = false;
				//m_flag.remove(m_flag.size() - 1);
			}
			else if(ccc.contains("]") && aggregate_flag == false && double_q_flag == false) {
				l_flag_cnt--;
				//l_flag.remove(l_flag.size() - 1);
			}

			if(tmp.contains("/*") && !tmp.contains("*/")) {
				tmp += ccc;
				continue;
			}
			else if(tmp.contains("/*") && tmp.contains("*/")) {
				int n = tmp.indexOf("/*");

				if(n == 0)
					tmp = "";
				else
					tmp = tmp.substring(0, n);
			}

			if(tmp.startsWith("||"))
				pipe_flag = true;


			if(link_image_flag2 == true && atmark_flag == false) {
				tmp += ccc;
				if(tmp.contains("(asc") && ccc.contains(")")) {
					tmp += ccc;
					int n = tmp.indexOf("(asc");
					if(n > 1)
						tmp = tmp.substring(0, n);
					else
						tmp = "";

					//tmp = tmp.replace("(asc", "");
					continue;
				}
				else if(tmp.contains("(desc") && ccc.contains(")")) {
					tmp += ccc;
					int n = tmp.indexOf("(desc");
					if(n > 1)
						tmp = tmp.substring(0, n );
					else
						tmp = "";
					//tmp = tmp.replace("(desc", "");
					continue;
				}
				else if (tmp.contains("null(") && (ccc.contains(",") || ccc.contains("!") || ccc.contains("}")
						|| ccc.contains("]")/* || ccc.contains(")")*/)) {
					tmp = "";
					if(!ccc.contains(",") && !ccc.contains("!"))
					if(ssql_parse.get(ssql_parse.size() - 1).contains(",") || ssql_parse.get(ssql_parse.size() - 1).contains("!")) {
						ssql_parse.remove(ssql_parse.size() - 1);
					}
					continue;
				}


				if(ccc.contains(")")){
					link_image_flag2 = false;
					tmp = "";
					continue;
				}
				else if(!ccc.contains("@")){
					continue;
				}
				else if(ccc.contains("@")) {
					atmark_flag = true;
					layout_load_flag = true;
					continue;
				}
			}

			if(l_end_flag == true && (ccc.contains(",") || ccc.contains("!") || ccc.contains("%"))) {
				l_end_flag = false;
				continue;
			}

			if((link_flag == true || a_flag == true) && tmp.contains("image") && ccc.contains("(")) {
				link_image_flag = true;
				tmp = "";
				link_flag = false;
				a_flag = false;
			}

			if(tmp.contains("image") && link_image_flag == false && ccc.contains("("))
				image_flag = true;

			if(tmp.contains("line") && ccc.contains("("))
				line_flag = true;

			if((tmp.equals("a") || tmp.contains("anchor"))&& ccc.contains("(")) {
				a_flag = true;
				//tmp = tmp.concat(ccc);
				//System.out.println("08080808080");
			}
			if(tmp.contains("link") && ccc.contains("("))
				link_flag = true;

			if(tmp.contains("(asc") && ccc.contains(")")) {
				tmp += ccc;
				int n = tmp.indexOf("(asc");
				if(n > 1)
					tmp = tmp.substring(0, n);
				else
					tmp = "";

				//tmp = tmp.replace("(asc", "");
				continue;
			}
			else if(tmp.contains("(desc") && ccc.contains(")")) {
				tmp += ccc;
				int n = tmp.indexOf("(desc");
				if(n > 1)
					tmp = tmp.substring(0, n);
				else
					tmp = "";
				//tmp = tmp.replace("(desc", "");
				continue;
			}
			else if (tmp.contains("null(") && (ccc.contains(",") || ccc.contains("!") || ccc.contains("}")
					|| ccc.contains("]")/* || ccc.contains(")")*/)) {
				tmp = "";
				if(!ccc.contains(",") && !ccc.contains("!"))
				if(ssql_parse.get(ssql_parse.size() - 1).contains(",") || ssql_parse.get(ssql_parse.size() - 1).contains("!")) {
					ssql_parse.remove(ssql_parse.size() - 1);
				}
				continue;
			}



			else if (image_flag == true && ccc.contains(")")) {
				int n = tmp.indexOf("(") + 1;
				int m = tmp.indexOf(",", n) ;

				if(n == -1 || m == -1) {
					m = tmp.length();
				}
				tmp = tmp.substring(n, m);
				if(tmp != "") {

					if(tmp.contains("@{")) {
						int n1 = tmp.indexOf("@{");
						int m1 = tmp.indexOf("}", n1);
						if(n1 < m1)
							tmp = tmp.substring(0, n1) + tmp.substring(m1 + 1);
					}

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;

					image_name_List.add(tmp);
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;
				}
				tmp = "";
				image_flag = false;
				continue;
			}

			else if(image_flag == true) {
				tmp = tmp.concat(ccc);
				continue;
			}

			else if ((link_flag == true || a_flag == true) && link_image_flag == false && ccc.contains(")") && atmark_flag == false) {

				int flag = 0;

				if(tmp.contains("@{")) {
					int n1 = tmp.indexOf("@{");
					int m1 = tmp.indexOf("}", n1);
					if(n1 < m1) {
						layout_load.add(tmp.substring(n1 + 1,m1));
						flag = 1;
						layout_load_flag = true;
						tmp = tmp.substring(0, n1) + tmp.substring(m1 + 1);
					}
				}

				if(tmp.contains("@{")) {
					int n1 = tmp.indexOf("@{");
					int m1 = tmp.indexOf("}", n1);
					if(n1 < m1) {

						tmp = tmp.substring(0, n1) + tmp.substring(m1 + 1);
					}
				}

				int n = 0;
				if(tmp.contains("link"))
					n = tmp.indexOf("link(") + 5;
				else if(tmp.contains("anchor")){
					n = tmp.indexOf("anchor(") + 7;
				}
				else{
					n = tmp.indexOf("a(") + 2;
				}
				int m = tmp.indexOf(",") ;

				if(n != -1 && m == -1)
					m = tmp.indexOf(")", n);

				tmp = tmp.substring(n, m);
				if(flag == 1) {

					layout_load_name.add(tmp);
				}

				if(tmp != "") {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;

					if(flag == 1)
						layout_load_flag = true;

					layout_load_name_tmp = tmp;
				}
				tmp = "";
				link_flag = false;
				a_flag = false;
				continue;
			}

			else if((link_flag == true || a_flag == true) && link_image_flag == false) {
				tmp = tmp.concat(ccc);
				continue;
			}

			else if(link_image_flag == true) {
				int flag = 0;

				if (ccc.contains(")")) {
					int n = tmp.indexOf("(") + 1;
					int m = tmp.indexOf(",", n) ;

					tmp = tmp.substring(n, m);
					if(tmp != "") {

						if(tmp.contains("@{")) {
							int n1 = tmp.indexOf("@{");
							int m1 = tmp.indexOf("}", n1);
							flag = 1;
							if(n1 < m1)
								tmp = tmp.substring(0, n1) + tmp.substring(m1 + 1);
						}

						if(tmp.contains("@{")) {
							int n1 = tmp.indexOf("@{");
							int m1 = tmp.indexOf("}", n1);
							if(n1 < m1) {

								tmp = tmp.substring(0, n1) + tmp.substring(m1 + 1);
							}
						}


						if(layout_load_flag == false && ssql_parse.size() != 0) {
							layout_load.add("dmy");
							layout_load_name.add("dmy");
						}
						layout_load_flag = false;

						if(flag == 1)
							layout_load_flag = true;

						int index;
						if((index = sameName_Check(tmp)) != -1)
							tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
						ssql_parse.add(tmp);
						add_cnt++;
						image_name_List.add(tmp);
						nest_cnt[cnt] = l_flag_cnt;
						bracket_level[cnt] = bracket_cnt;
						cnt++;
						layout_load_name_tmp = tmp;
					}
					tmp = "";
					link_image_flag = false;
					link_image_flag2 = true;

					continue;
				}

				else if( !ccc.contains(")")) {
					tmp = tmp.concat(ccc);
					continue;
				}

			}


			else if (a_flag == true && ccc.contains(")")) {
				int n = tmp.indexOf("(") + 1;
				int m = tmp.indexOf(",", n) ;

				tmp = tmp.substring(n, m);
				if(tmp != "") {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;
				}
				tmp = "";
				a_flag = false;
				continue;
			}

			else if(a_flag == true) {
				tmp = tmp.concat(ccc);
				continue;
			}

			else if (line_flag == true && (ccc.contains("!"))) {

				//ssql_parse.remove(ssql_parse.size() - 1);
				//ssql_parse.add(ccc);
				int n = tmp.indexOf("line") + 5;
				int m = tmp.indexOf(")", n);
				line_list.add(tmp.substring(n,m));
				tmp = "";
				line_flag = false;
				continue;

			}
			else if (line_flag == true && (ccc.contains("}"))) {

				tmp.concat(ccc);
				ssql_parse.remove(ssql_parse.size() - 1);
				int n = tmp.indexOf("line") + 5;
				int m = tmp.indexOf(")", n);
				line_list.add(tmp.substring(n,m));
				tmp = "";
				line_flag = false;
				continue;

			}

			else if(line_flag == true) {
				tmp += ccc;
				continue;
			}

			else if(ccc.contains("@")){
				atmark_flag = true;
				if(tmp != "") {

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;
				}
				}
				tmp = "";
				continue;

			}




			else if (ccc.contains("}") && atmark_flag == true) {
				atmark_flag = false;
				if(!l_tmp.contains("debug"))
					if(tmp == "" && layout_load_name_tmp != "") {
						layout_load_name.add(layout_load_name_tmp);
						layout_load_flag = true;
						if(l_tmp != "") {
							layout_load.add(l_tmp);
							layout_load_flag = true;
							System.out.println(l_tmp);}
					}else if(tmp != ""){
						layout_load_name.add(tmp);
						layout_load_flag = true;
						if(l_tmp != "") {
							layout_load.add(l_tmp);
							layout_load_flag = true;
							System.out.println(l_tmp);}
					}
				if(!l_tmp.contains("debug"))
					/*if(l_tmp != "") {
						layout_load.add(l_tmp);
						layout_load_flag = true;
						System.out.println(l_tmp);

					}*/
					l_tmp = "";
				layout_load_name_tmp = "";
				//tmp = "";
				continue;
			}

			else if(atmark_flag == true) {
				l_tmp += ccc;
				continue;
			}


			else if((ccc.contains(",") || ccc.contains("!") )&& link_flag == false && a_flag == false) {

				try {
					Integer.parseInt(tmp);
					tmp = "";
					continue;

				} catch (NumberFormatException nfex) {

				}

				if(tmp != "") {

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy9");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;

					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;
					}
				}
				tmp = "";
				ssql_parse.add(ccc);
				add_cnt = 999;
				add_flag=true;

			}

			else if(ccc.contains("%")) {

				try {
					Integer.parseInt(tmp);
					tmp = "";
					continue;

				} catch (NumberFormatException nfex) {

				}


				if(tmp != "") {

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;
				}
				}
				tmp = "";
				add_cnt=999;
				continue;
			}

			else if(((ccc.contains("{") || ccc.contains("}") || ccc.contains("[") )&& atmark_flag == false) ){
				if(tmp != "") {

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					bracket_level[cnt] = bracket_cnt ;
					nest_cnt[cnt] = l_flag_cnt;

					if(ccc.contains("}") && add_flag == true) {
						add_cnt = 999;
						bracket_level[cnt] = bracket_level[cnt] + 1;
						//System.out.println("2222222221111222");
					}


					cnt++;
					layout_load_name_tmp = tmp;
				}
			}

				tmp = "";
				continue;
			}



			else if(ccc.contains("]")) {
				if(tmp != "") {

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					l_flag_cnt++;

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					l_flag_cnt--;
					layout_load_name_tmp = tmp;
				}
				}

				tmp = "";
				l_end_flag = true;
				continue;
			}



			else if(tmp.contains("FROM") || tmp.contains("from") || tmp.contains("From")) {
				break;
			}

			/*else if(ccc.contains("@") || ccc.contains("]")) {//とりあえずね．
					if(tmp != "")
						ssql_parse.add(tmp);
						tmp = "";
					break;
				}*/
			else if((ccc.contains("\"") || ccc.contains("\'"))&& double_q_flag == false) {
				double_q_flag = true;
				tmp = tmp.concat(ccc);
				continue;
			}
			else if((ccc.contains("\"") || ccc.contains("\'")) && double_q_flag == true) {
				if(tmp != "") {
					tmp = tmp.concat(ccc);

					if(pipe_flag == true) {

						String a = ssql_parse.get(ssql_parse.size() - 1).concat(tmp);
						ssql_parse.remove(ssql_parse.size() - 1);
						ssql_parse.add(a);
						//add_cnt++;
						layout_load_name_tmp = a;
						pipe_flag = false;
						layout_load_flag = false;
					}
					else {

					if(layout_load_flag == false && ssql_parse.size() != 0) {
						layout_load.add("dmy7");
						layout_load_name.add("dmy");
					}
					layout_load_flag = false;

					int index;
					if((index = sameName_Check(tmp)) != -1)
						tmp += "<" + String.valueOf(sameName_cnt[index]++) + ">";
					ssql_parse.add(tmp);
					add_cnt++;
					nest_cnt[cnt] = l_flag_cnt;
					bracket_level[cnt] = bracket_cnt;
					cnt++;
					layout_load_name_tmp = tmp;

					}
				}

				tmp = "";
				double_q_flag = false;
				continue;
			}

			else {
				tmp = tmp.concat(ccc);
			}

		}

		if(layout_load_flag == false && ssql_parse.size() != 0) {
			layout_load.add("dmy");
			layout_load_name.add("dmy");

		}

		layout_load.remove(0);
		layout_load_name.remove(0);

		//System.out.println(layout_load);
		System.out.println(layout_load);
		System.out.println(layout_load_name );

		if(ssql_parse.get(ssql_parse.size() - 1).contains("!") || ssql_parse.get(ssql_parse.size() - 1).contains(","))
			ssql_parse.remove(ssql_parse.size() - 1);

		if(ssql_parse.get(ssql_parse.size() - 1).contains("!") || ssql_parse.get(ssql_parse.size() - 1).contains(","))
			ssql_parse.remove(ssql_parse.size() - 1);

		if(ssql_parse.get(ssql_parse.size() - 1).contains("!") || ssql_parse.get(ssql_parse.size() - 1).contains(","))
			ssql_parse.remove(ssql_parse.size() - 1);

		boolean pre = false;
		for(int i = 0; i < ssql_parse.size() - 1; i++) {
			if((ssql_parse.get(i).equals("!") || ssql_parse.get(i).equals(",")) && pre) {
				ssql_parse.remove(i - 1);
			}
			if(ssql_parse.get(i).equals("!") || ssql_parse.get(i).equals(","))
					pre = true;
			else {
				pre = false;
			}
		}


		if(ssql_parse.get(0).contains("for") || ssql_parse.get(0).contains("For") || ssql_parse.get(0).contains("FOR")) {
			try {

				if(HTMLEnv.css.length() != 0)
				HTMLEnv.css.delete(0, HTMLEnv.css.length());

				int n = 0;
				int m = 0;
				String p = "";
				n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
				m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

				String cssname = GlobalEnv.getfilename().substring(n, m);
				cssname = cssname.replace(".ssql", ".css");

				String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
				//System.out.println(csspass);

				BufferedReader in = new BufferedReader(new FileReader(new File(csspass)));
				String tmp = "";


			  while ((tmp = in.readLine()) != null) {
	                p += tmp;
	                p += "\n";
	            }

			  in.close();
			  HTMLEnv.css.append(p);

			} catch (Exception e1) {
				System.out.println(e1);

			}

		}


		for(int i = 0; i < ssql_parse.size(); i++)
			if(ssql_parse.get(i).contains("GENERATEHTML") || ssql_parse.get(i).contains("GENARATE")|| ssql_parse.get(i).contains("generate") || ssql_parse.get(i).contains("Generate"))
				ssql_parse.remove(i);

		for(int i = 0; i < ssql_parse.size(); i++)
			System.out.println(ssql_parse.get(i) );

//System.out.println(line_list);

		//StringBuffer sb = new StringBuffer(ssql);
		int a = ssql.lastIndexOf("@{");
		int f = 0;

		if(ssql.contains("FROM"))
			f = ssql.lastIndexOf("FROM");
		if(ssql.contains("from"))
			f = ssql.lastIndexOf("from");
		if(ssql.contains("From"))
			f = ssql.lastIndexOf("From");
		if(a < f && a!= -1 && f!=-1)
			table_at = ssql.substring(a, f);
		else {
			table_at_flag = false;
		}

		table_at_flag2 = false;
		if(table_at.contains("'on'") || table_at.contains("\"on\"")) {
			table_at_flag2 = true;
			JSplitPaneTest1.debugCheckBox.setSelected(true);
		}
		if(!table_at.contains("css-file") &&!table_at.contains("debug") &&
				ssql.split("}" + "").length - 1 != 1 && ssql.split("]" + "").length - 1 != 0 && ssql.split("!" + "").length - 1 != 0)
			if(table_at.contains("width") || table_at.contains("font-size") || table_at.contains("margin")) {
				table_at = "";
				table_at_flag = false;
			}

		//System.out.println(table_at + "ttttt");

		//if(table_at_flag == false) {

			int a1 = ssql.lastIndexOf("@");
			int f1 = ssql.indexOf("}", a);
			System.out.println(a1 + "??" + f1 + "78787");
			if (a1 < f1 && a1!=-1 && f1!=-1)
				ssql = ssql.substring(0, a1) + ssql.substring(f1 + 1);
			System.out.println(ssql);

			String a2 = "";
			a2 += "@{";

			if (table_at_flag2)
				a2 += "debug='on'";
			else {
				a2 += "debug='off'";
			}
			a2 += "}";

			if (ssql.contains("FROM"))
				ssql = ssql.replace("FROM", a2 + " FROM");
			else if (ssql.contains("from"))
				ssql = ssql.replace("from", a2 + " from");
			else if (ssql.contains("From"))
				ssql = ssql.replace("From", a2 + " From");

			FrontEnd_Tasuku.ssql = ssql;
			//JSplitPaneTest1.textArea2.setText(ssql);
			table_at_flag = true;


		System.out.println("##################################");


		for(int i = 0 ; i < 50; i ++) {
			System.out.print(bracket_level[i] + " ");

		}
		for(int i = 0 ; i < 50; i ++)
		System.out.print(nest_cnt[i] + " ");

		boolean at_flag = false;
		boolean q_flag = false;
		String ssql2 = "";

		for (int i = 0; i < ssql.length(); i++) {
			String ccc = String.valueOf(ssql.charAt(i));

			if ((ccc.equals(" ") || ccc.equals("\n") || ccc.equals("[") /*|| ccc.equals("]")*/ ||  ccc.contains("	")  ||  ccc.contains("	")) && q_flag == false)
				continue;

			if((ssql2.startsWith("\"") || ssql2.startsWith("\'")) && q_flag == false){
				q_flag = true;
			}
			else if((ssql2.startsWith("\"") || ssql2.startsWith("\'")) && q_flag == true){
				q_flag = false;
			}

			if (ccc.contains("@")) {
				at_flag = true;
				continue;
			}
			else if (at_flag == true && !ccc.contains("}")) {

			continue;
			}
			else if (at_flag == true && ccc.contains("}")) {

				at_flag = false;
				continue;

			} else if (at_flag == false) {
				ssql2 += ccc;
			}

			if(ssql2.contains("link(") && ssql2.endsWith(")")) {

				ssql2 = ssql2.replaceAll("(asc[2-9])", "");
				ssql2 = ssql2.replaceAll("(desc[2-9])", "");

				int n = ssql2.indexOf("link(");
				int l = ssql2.indexOf(",", n);
				int m = ssql2.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql2 = ssql2.substring(0, n) + ssql2.substring(n+5, l);
			}

			else if(ssql2.contains("a(") && ssql2.endsWith(")")) {

				ssql2 = ssql2.replaceAll("(asc[2-9])", "");
				ssql2 = ssql2.replaceAll("(desc[2-9])", "");

				int n = ssql2.indexOf("a(");
				int l = ssql2.indexOf(",", n);
				int m = ssql2.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql2 = ssql2.substring(0, n) + ssql2.substring(n+2, l);
			}
			else if(ssql2.contains("image(") && ssql2.endsWith(")")) {

				ssql2 = ssql2.replaceAll("(asc[2-9])", "");
				ssql2 = ssql2.replaceAll("(desc[2-9])", "");

				int n = ssql2.indexOf("image(");
				int l = ssql2.indexOf(",", n);
				int m = ssql2.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql2 = ssql2.substring(0, n) + ssql2.substring(n+6, l);
			}

			//ssql2 = ssql2.replace("{{", "{");
		}

		for(int i = 0; i < ssql_parse.size(); i++)
			if(ssql2.contains("{" + ssql_parse.get(i) + "}"))
				ssql2 = ssql2.replace("{" + ssql_parse.get(i) + "}",  ssql_parse.get(i));

		while(true)
		if(ssql2.contains("]") || ssql2.contains("]!") || ssql2.contains("],") ) {

			ssql2 = ssql2.replace("]!", "");
			ssql2 = ssql2.replace("],", "");
			ssql2 = ssql2.replace("]", "");
			continue;
		}
		else {
			break;
		}

		ssql2 = ssql2.replace("{{", "{");
		ssql2 = ssql2.replace("}}", "}");
		ssql2 = ssql2.replace("{{", "{");
		ssql2 = ssql2.replace("}}", "}");
		ssql2 = ssql2.replace("{{", "{");
		ssql2 = ssql2.replace("}}", "}");


		for(int i = 0; i < ssql_parse.size(); i++)
			for(int j = 0; j < ssql_parse.size(); j++)
				if(ssql2.contains("!" + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + "!"))
					check_1.add(ssql_parse.get(i));
				else if(ssql2.contains("!" + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + ","))
					check_2.add(ssql_parse.get(i));
				else if(ssql2.contains("," + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + "!"))
					check_3.add(ssql_parse.get(i));
				else if(ssql2.contains("," + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));


		boolean at_flag3 = false;
		boolean q_flag3 = false;
		String ssql3 = "";

		for (int i = 0; i < ssql.length(); i++) {
			String ccc = String.valueOf(ssql.charAt(i));

			if ((ccc.equals(" ") || ccc.equals("\n") /*|| ccc.equals("[") || ccc.equals("]")*/ ||  ccc.contains("	")  ||  ccc.contains("	")) && q_flag == false)
				continue;

			if((ssql3.startsWith("\"") || ssql3.startsWith("\'")) && q_flag3 == false){
				q_flag3 = true;
			}
			else if((ssql3.startsWith("\"") || ssql3.startsWith("\'")) && q_flag3 == true){
				q_flag3 = false;
			}

			if (ccc.contains("@")) {
				at_flag3 = true;
				continue;
			}
			else if (at_flag3 == true && !ccc.contains("}")) {

			continue;
			}
			else if (at_flag3 == true && ccc.contains("}")) {

				at_flag3 = false;
				continue;

			} else if (at_flag3 == false) {
				ssql3 += ccc;
			}

			if(ssql3.contains("link(") && ssql3.endsWith(")")) {

				ssql3 = ssql3.replaceAll("(asc[2-9])", "");
				ssql3 = ssql3.replaceAll("(desc[2-9])", "");

				int n = ssql3.indexOf("link(");
				int l = ssql3.indexOf(",", n);
				int m = ssql3.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql3 = ssql3.substring(0, n) + ssql3.substring(n+5, l);
			}

			else if(ssql3.contains("a(") && ssql3.endsWith(")")) {

				ssql3 = ssql3.replaceAll("(asc[2-9])", "");
				ssql3 = ssql3.replaceAll("(desc[2-9])", "");
				int n = ssql3.indexOf("a(");
				int l = ssql3.indexOf(",", n);
				int m = ssql3.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql3 = ssql3.substring(0, n) + ssql3.substring(n+2, l);
			}
			else if(ssql3.contains("anchor(") && ssql3.endsWith(")")) {

				ssql3 = ssql3.replaceAll("(asc[2-9])", "");
				ssql3 = ssql3.replaceAll("(desc[2-9])", "");

				int n = ssql3.indexOf("anchor(");
				int l = ssql3.indexOf(",", n);
				int m = ssql3.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql3 = ssql3.substring(0, n) + ssql3.substring(n+7, l);
			}
			else if(ssql3.contains("image(") && ssql3.endsWith(")")) {

				ssql3 = ssql3.replaceAll("(asc[2-9])", "");
				ssql3 = ssql3.replaceAll("(desc[2-9])", "");

				int n = ssql3.indexOf("image(");
				int l = ssql3.indexOf(",", n);
				int m = ssql3.indexOf(")", n);
				if(n != -1 && l != -1 && m != -1)
				ssql3 = ssql3.substring(0, n) + ssql3.substring(n+6, l);
			}

			//ssql3 = ssql3.replace("{{", "{");
		}

		for(int i = 0; i < ssql_parse.size(); i++)
			if(ssql3.contains("{" + ssql_parse.get(i) + "}"))
				ssql3 = ssql3.replace("{" + ssql_parse.get(i) + "}",  ssql_parse.get(i));

		ssql3 = ssql3.replace("{{", "{");
		ssql3 = ssql3.replace("}}", "}");
		ssql3 = ssql3.replace("[", "^");
		ssql3 = ssql3.replace("],", "*");
		ssql3 = ssql3.replace("]!", "*");
		ssql3 = ssql3.replace("]%", "*");
		ssql3 = ssql3.replace("^^", "^");
		ssql3 = ssql3.replace("**", "*");

		for(int i = 0; i < ssql_parse.size(); i++)
			for(int j = 0; j < ssql_parse.size(); j++)
				if(ssql3.contains("!" + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + "!"))
					check_1.add(ssql_parse.get(i));
				else if(ssql3.contains("!" + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + ","))
					check_2.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + "!"))
					check_3.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "},{" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));
				/*else if(ssql3.contains(ssql_parse.get(i) + "}!{" + ssql_parse.get(j)))
					check_5.add(ssql_parse.get(i));*/
				else if(ssql3.contains("!" + ssql_parse.get(i) + "}!{" + ssql_parse.get(j) + "!"))
					check_5.add(ssql_parse.get(i));
				else if(ssql3.contains("!" + ssql_parse.get(i) + "}!{" + ssql_parse.get(j) + ","))
					check_6.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "}!{" + ssql_parse.get(j) + "!"))
					check_7.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "}!{" + ssql_parse.get(j) + ","))
					check_8.add(ssql_parse.get(i));
				else if(ssql3.contains(ssql_parse.get(i) + ",{" + ssql_parse.get(j) + ","))
					check_9.add(ssql_parse.get(j));

				else if(ssql3.contains("!" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_1.add(ssql_parse.get(i));
				else if(ssql3.contains("!" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_2.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_3.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));
				else if(ssql3.contains("!^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_1.add(ssql_parse.get(i));
				else if(ssql3.contains("!^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_2.add(ssql_parse.get(i));
				else if(ssql3.contains(",^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_3.add(ssql_parse.get(i));
				else if(ssql3.contains(",^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));
				else if(ssql3.contains("," + ssql_parse.get(i) + "},^" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));

		/*else if(ssql2.contains(ssql_parse.get(i) + "}!{" + ssql_parse.get(j)))
			check_5.add(ssql_parse.get(i));*/

				else if(ssql2.contains("!" + ssql_parse.get(i) + "*!^" + ssql_parse.get(j) + "!"))
					check_5.add(ssql_parse.get(i));
				else if(ssql2.contains("!" + ssql_parse.get(i) + "*!^" + ssql_parse.get(j) + ","))
					check_6.add(ssql_parse.get(i));
				else if(ssql2.contains("," + ssql_parse.get(i) + "*!^" + ssql_parse.get(j) + "!"))
					check_7.add(ssql_parse.get(i));
				else if(ssql2.contains("," + ssql_parse.get(i) + "*!^" + ssql_parse.get(j) + ","))
					check_8.add(ssql_parse.get(i));
				else if(ssql2.contains("!^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_1.add(ssql_parse.get(i));
				else if(ssql2.contains("!^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_2.add(ssql_parse.get(i));
				else if(ssql2.contains(",^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + "!"))
					check_3.add(ssql_parse.get(i));
				else if(ssql2.contains(",^" + ssql_parse.get(i) + "*,^" + ssql_parse.get(j) + ","))
					check_4.add(ssql_parse.get(i));


		System.out.println("\n" + check_1 + check_8);

		System.out.println( ssql2 + "$$$$$$$");
		System.out.println( ssql3 + "$$$$$$$");


	}

	static int sameName_Check(String name) {

		if(ssql_parse.size() > 0 && name != "," && name != "!" && name != "%")
			for(int i = 0; i < ssql_parse.size(); i++)
				if(name.equals(ssql_parse.get(i)))
					return i;
				else
					continue;

		return -1;

	}

}
