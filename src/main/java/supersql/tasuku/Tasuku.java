package supersql.tasuku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import supersql.FrontEnd;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;

public class Tasuku extends JFrame {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int w = screenSize.width;
	static int h = screenSize.height;

	String query;
	static int cnt = 0;
	static int nest_cnt = 0;
	JSplitPaneTest1 jsp = new JSplitPaneTest1();
	static boolean skip_id_flag = false;
	static String html_file = FrontEnd.html_file;
	static int distance = 0;
	static String[] query_layout;
	static String[] query_layout2;
	static ArrayList<String> query_layout3 = new ArrayList<String>();

	public static ArrayList<Integer> tfe_id_arr = new ArrayList<Integer>();
	static ArrayList<String> tfe_name_arr = new ArrayList<String>();
	static ArrayList<String> a = new ArrayList<String>();
	static ArrayList<String> b = new ArrayList<String>();
	static ArrayList<JPanel> jPanel2 = new ArrayList<JPanel>();
	public static boolean nest_change_flag1 = false;
	public static boolean nest_change_flag2 = false;
	static ArrayList<Integer> nest_change2_list = new ArrayList<Integer>();
	public static ArrayList<Integer> nest_1_2 = new ArrayList<Integer>();

	static int[] start_width = new int[64];
	static int[] final_width = new int[64];
	static int[] start_height = new int[64];
	static int[] final_height = new int[64];
	static int frame_start_width = 0;
	static int frame_final_width = 0;
	static int frame_start_height = 0;
	static int frame_final_height = 0;
	static int[] width_arr;
	static int[] height_arr;
	static boolean a1_flag = false;
	static boolean one_flag = false;
	static String[] css_other;

	static ArrayList<String> ooo = new ArrayList<String>();
	static ArrayList<String> ppp = new ArrayList<String>();

	static String query_layout_table = "";
	static String query_layout_table2 = "";

	static ArrayList<Integer> line_tfe = new ArrayList<Integer>();
	static ArrayList<String> qqq = new ArrayList<String>();
	static ArrayList<String> rrr = new ArrayList<String>();

	int[] nest_cnt2 = new int[64];

	// 編集中のhtmlが入る場所。出力先ディレクトリにすることで、tmp.htmlがSSQL本体の方に作られて、
	// permission errorになることを回避する
	static final String tmpFile = GlobalEnv.getOutputDirPath() + "/tmp.html";

	public Tasuku() {

		if (HTMLEnv.css != null)
			HTMLEnv.css.append(".att { padding:0px; margin:0px; height:100%; z-index:2; }\n" +
					".linkbutton { text-align:center; margin-top:5px; padding:5px; }\n" +
					".embed { vertical-align:text-top; padding:0px; margin:0px; border:0px,0px,0px,0px; width:100%; }\n"
					+
					".noborder { border-width:0px; margin-top:-1px; padding-top:-1px; margin-bottom:-1px; padding-bottom:-1px; }\n"
					+
					"\n" +
					"");

		tfe_id_arr.clear();
		System.out.println(ppp);
		//System.out.println(ppp.size() + "~~9090");
		for (int i = 0; i < ppp.size(); i++)
			try {
				tfe_id_arr.add(Integer.parseInt(ppp.get(i)));
			} catch (Exception e) {
				// TODO: handle exception
			}
		//System.out.println(tfe_id_arr.size() + "9090");
		for (int i = 0; i < 64; i++)
			try {
				nest_cnt2[i] = T_parser.nest_cnt[i];
			} catch (Exception e) {
				// TODO: handle exception
			}

		size_change();
	}

	public Tasuku(String q) {
		query = q;
	}

	public void size_change() {

		int cnt99 = 1;
		for (int i = 0; i < T_parser.ssql_parse.size(); i++) {
			String s = T_parser.ssql_parse.get(i);
			if (s.equals("!") || s.equals(","))
				continue;
			if (FrontEnd.ssql.contains("[" + s + "]"))
				nest_cnt2[cnt99]--;
			else if (FrontEnd.ssql.contains("[ " + s + "]"))
				nest_cnt2[cnt99]--;
			else if (FrontEnd.ssql.contains("[" + s + " ]"))
				nest_cnt2[cnt99]--;
			else if (FrontEnd.ssql.contains("[ " + s + " ]"))
				nest_cnt2[cnt99]--;
			cnt99++;
		}

		for (int i = 0; i < 61; i++) {
			if (nest_cnt2[i] == 0 && nest_cnt2[i + 1] == 1 && nest_cnt2[i + 2] == 0)
				nest_cnt2[i + 1] = 0;
			else if (nest_cnt2[i] == 1 && nest_cnt2[i + 1] == 2 && nest_cnt2[i + 2] == 1)
				nest_cnt2[i + 1] = 1;
			else if (nest_cnt2[i] == 2 && nest_cnt2[i + 1] == 3 && nest_cnt2[i + 2] == 2)
				nest_cnt2[i + 1] = 2;
			else if (nest_cnt2[i] == 3 && nest_cnt2[i + 1] == 4 && nest_cnt2[i + 2] == 3)
				nest_cnt2[i + 1] = 3;
			else if (nest_cnt2[i] == 1 && nest_cnt2[i + 1] == 2 && nest_cnt2[i + 2] == 0)
				nest_cnt2[i + 1] = 1;
			else if (nest_cnt2[i] == 2 && nest_cnt2[i + 1] == 3 && (nest_cnt2[i + 2] == 1 || nest_cnt2[i + 2] == 0))
				nest_cnt2[i + 1] = 2;
			else if (nest_cnt2[i] == 0 && nest_cnt2[i + 1] == 2 && nest_cnt2[i + 2] == 1)
				nest_cnt2[i + 1] = 1;

			System.out.print(nest_cnt2[i] + " ");
		}

		for (int i = 0; i < T_parser.ssql_parse.size(); i++)
			b.add(T_parser.ssql_parse.get(i));


		for (int i = 0; i < b.size(); i++)
			if (!b.get(i).contains("!") && !b.get(i).contains(",")) {
				tfe_name_arr.add(b.get(i));
				cnt++;
			}

		if (b.size() == 1) {
			jsp.createTab(b.get(0));
			JSplitPaneTest1.frame.getContentPane().add(JSplitPaneTest1.jPanel.get(0), BorderLayout.CENTER);
			b.clear();

			if (tfe_id_arr.size() == 0) {
				try {

					tfe_id_arr.add(10000);

					FileWriter file1 = new FileWriter(tmpFile, true);
					BufferedWriter pw1 = new BufferedWriter(new BufferedWriter(file1));
					//FileWriter file2 = new FileWriter(tmpFile, false);
					//BufferedWriter pw2 = new BufferedWriter(new BufferedWriter(file2));
					String tmp = "";

					try (BufferedReader in = new BufferedReader(new FileReader(new File(html_file)))) {
						String line = "";

						while ((line = in.readLine()) != null) {
							if (line.contains("<table class")) {
								tmp = "<table class=\"att TFE10000\">";
								System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
								pw1.write(tmp);
								pw1.newLine();
							} else {
								pw1.write(line);
								pw1.newLine();
							}
						}

						pw1.close();

						Path sourcePath = Paths.get(tmpFile);
						Path destinationPath = Paths.get(html_file);

						try {
							Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
							System.out.println("~~~~~~~~~~~!~~~~~~~~~~~~");
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						File newdir = new File(tmpFile);
						newdir.delete();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		//reverse_TFE_id();

		//for(int i = 0 ; i < b.size(); i++)
		//	System.out.println(b.get(i));

		System.out.println("######## Panel Create Flow ########");

		boolean check_flag = false;

		while (true) {

			if (b.isEmpty()) {
				while (true) {
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						System.out.println("6767");

					}

					else if (JSplitPaneTest1.nest_tmp.size() != 0) {
						jsp.nest_match(1);
					}

					if (JSplitPaneTest1.nest_tmp.size() == 0)
						break;
					//break;
				}
				break;
			}

			if (!b.get(0).contains(",") && !b.get(0).contains("!") && !b.get(0).contains("%")
					&& !b.get(0).contains("{}")) {

				jsp.createTab(b.get(0));
				b.remove(0);
				nest_cnt++;

			}

			/*if(b.get(0).contains("!") && b.get(2).contains("!")
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]) && (T_parser.bracket_level[nest_cnt+1] != T_parser.bracket_level[nest_cnt +2])) {
				jsp.createTab(b.get(1));
				nest_cnt++;
				b.remove(1);
				jsp.tate();
				//jsp.nest_match(2);
				b.remove(0);
			}*/

			//20191212

			/*if(b.size() >= 6)
				if(b.get(0).contains("!") &&
						(T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 2] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])) {

					b.remove(0);
					jsp.createTab(b.get(0));
					nest_cnt++;
					b.remove(0);
					jsp.tate();
					System.out.println("$$$%$%$%$%$%$%$%$");

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
					continue;
				}*/

			if (b.size() > 4)
				if (T_parser.check_1.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.tate();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						JSplitPaneTest1.jsp_tmp = null;
					} else {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());

					}
					b.remove(0);

					//JSplitPaneTest1.jsp_tmp = null;

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.tate();
					System.out.println("<><><><><AAAA");
					continue;

				} else if (T_parser.check_2.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.tate();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						JSplitPaneTest1.jsp_tmp = null;
					} else {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
						JSplitPaneTest1.jsp_tmp = null;
					}
					b.remove(0);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.yoko();
					System.out.println("<><><><><AAAA");
					continue;
				} else if (T_parser.check_3.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.yoko();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						JSplitPaneTest1.jsp_tmp = null;
					} else {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
						JSplitPaneTest1.jsp_tmp = null;
					}
					b.remove(0);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.tate();
					System.out.println("<><><><><AAAA");
					continue;
				} else if (T_parser.check_4.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.yoko();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
					} else {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
					}
					b.remove(0);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.yoko();
					System.out.println("<><><><><AAAA");
					continue;
				} else if (T_parser.check_5.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.tate();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					b.remove(0);

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.tate();
					System.out.println("<><><><><BBBB");
					continue;
				} else if (T_parser.check_6.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.tate();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					b.remove(0);

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.yoko();
					System.out.println("<><><><><BBBB");
					continue;
				} else if (T_parser.check_7.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.yoko();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					b.remove(0);

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.tate();
					System.out.println("<><><><><BBBB");
					continue;
				} else if (T_parser.check_8.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt]
								+ nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
						&& (T_parser.bracket_level[nest_cnt + 1]
								+ nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt + 2]
										+ nest_cnt2[nest_cnt + 2])) {
					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.yoko();
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
									&& nest_change2_list.size() != 1) {
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					b.remove(0);

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.yoko();
					System.out.println("<><><><><BBBB");
					continue;
				}
				/*else if (T_parser.check_9.contains(b.get(1))
						&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] != T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt])) {

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					if (nest_change_flag2 == true) {
						jsp.nest_match(2);
						nest_change_flag2 = false;
					}

					nest_change_flag2 = true;

					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

					b.remove(0);
					jsp.createTab(b.get(0));
					b.remove(0);
					nest_cnt++;
					jsp.yoko();
					System.out.println("<><><><><CCCC");
					continue;
				}*/
				else {
					check_flag = true;
				}

			if (b.size() != 1 && b.size() < 3 && check_flag == true) {

				jsp.createTab(b.get(1));
				nest_cnt++;
				if (b.get(0).contains("!"))
					jsp.tate();
				else if (b.get(0).contains(","))
					jsp.yoko();
				b.remove(1);
				b.remove(0);
				jsp.nest_match(2);
				nest_change_flag1 = false;
				nest_change2_list.remove(nest_change2_list.size() - 1);

			}
			check_flag = false;

			/*else if(T_parser.check_5.contains(b.get(1))
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]) && (T_parser.bracket_level[nest_cnt+1] == T_parser.bracket_level[nest_cnt +2])){
				jsp.createTab(b.get(1));
				nest_cnt++;
				b.remove(1);
				jsp.tate();
				jsp.nest_match(1);
				b.remove(0);

				JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);


				b.remove(0);
				jsp.createTab(b.get(0));
				b.remove(0);
				nest_cnt++;
				jsp.tate();
				continue;
			}
			else if(T_parser.check_6.contains(b.get(1))
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]) && (T_parser.bracket_level[nest_cnt+1] == T_parser.bracket_level[nest_cnt +2])){
				jsp.createTab(b.get(1));
				nest_cnt++;
				b.remove(1);
				jsp.tate();
				jsp.nest_match(1);
				b.remove(0);

				JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

				b.remove(0);
				jsp.createTab(b.get(0));
				b.remove(0);
				nest_cnt++;
				jsp.yoko();

				jsp.nest_match(1);

				nest_change_flag2 = false;

				continue;
			}
			else if(T_parser.check_7.contains(b.get(1))
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]) && (T_parser.bracket_level[nest_cnt+1] == T_parser.bracket_level[nest_cnt +2])){
				jsp.createTab(b.get(1));
				nest_cnt++;
				b.remove(1);
				jsp.yoko();
				jsp.nest_match(2);
				b.remove(0);

				JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);

				b.remove(0);
				jsp.createTab(b.get(0));
				b.remove(0);
				nest_cnt++;
				jsp.tate();


				jsp.nest_match(1);

				nest_change_flag2 = false;

				continue;

			}
			else if(T_parser.check_8.contains(b.get(1))
					&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]) && (T_parser.bracket_level[nest_cnt+1] == T_parser.bracket_level[nest_cnt +2])){
				jsp.createTab(b.get(1));
				nest_cnt++;
				b.remove(1);
				jsp.yoko();
				jsp.nest_match(2);
				b.remove(0);

				JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);


				b.remove(0);
				jsp.createTab(b.get(0));
				b.remove(0);
				nest_cnt++;
				jsp.tate();


				jsp.nest_match(1);

				nest_change_flag2 = false;
				continue;
			}*/
			///

			if (b.isEmpty()) {
				if(JSplitPaneTest1.jPanel_tmp.size() != 0) {
					JSplitPaneTest1.nest_tmp2 = JSplitPaneTest1.jPanel_tmp.get(0);
					jsp.nest_match(5);
				}
				while (true) {
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
						jsp.nest_match(2);
						System.out.println("6767");

					}

					else if (JSplitPaneTest1.nest_tmp.size() != 0) {
						jsp.nest_match(1);
					}

					if (JSplitPaneTest1.nest_tmp.size() == 0)
						break;
					//break;
				}
				break;
			}

			if (b.get(0).contains("!")) {

				//if(JSplitPaneTest1.jPanel_name.get(JSplitPaneTest1.jPanel_name.size() - 1) == )

				if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt]
				/*&& nest_cnt2[nest_cnt + 1] == nest_cnt2[nest_cnt]*/)
						&& T_parser.bracket_level[nest_cnt + 1] != 99 && nest_cnt2[nest_cnt + 1] != 99) {

					if (b.size() >= 3 && (b.get(0).contains("!") && b.get(2).contains(","))) {

						/*if(JSplitPaneTest1.nest_tmp != null) {
							JSplitPaneTest1.tate_tmp = JSplitPaneTest1.nest_tmp;
						}
						else if(JSplitPaneTest1.nest_tmp2 != null) {
							JSplitPaneTest1.tate_tmp2 = JSplitPaneTest1.nest_tmp2;
						}
						else {*/
						if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 2] + nest_cnt2[nest_cnt + 2]
						/*|| nest_cnt2[nest_cnt + 2] != nest_cnt2[nest_cnt]*/)
								&& T_parser.bracket_level[nest_cnt + 1] != 99 && nest_cnt2[nest_cnt + 1] != 99) {

							/*if (nest_change_flag1 == true) {
								jsp.nest_match(1);
								nest_change_flag1 = false;
							} else */if (nest_change2_list.size() > 0 && nest_change2_list
									.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()) {
								jsp.nest_match(2);
								nest_change2_list.remove(nest_change2_list.size() - 1);
							} else {
								nest_change_flag1 = true;
								nest_1_2.add(1);
								if (JSplitPaneTest1.jsp_tmp != null)
									JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
								JSplitPaneTest1.jsp_tmp = null;
								if (JSplitPaneTest1.jPanel_tmp.size() == 0 && b.size() < 3) {
									jsp.createTab(b.get(1));
									b.remove(1);
									jsp.tate();
								} else if (JSplitPaneTest1.jPanel_tmp.size() != 0) {
									JSplitPaneTest1.nest_tmp2 = JSplitPaneTest1.jPanel_tmp.get(0);
									jsp.nest_match(5);
									JSplitPaneTest1.jPanel_tmp.clear();

								}
								//b.remove(0);
							}

							jsp.createTab(b.get(1));
							nest_cnt++;
							b.remove(1);
							jsp.tate();
							System.out.println("&&&&&&&&&&^^^^&&&&&&&&");
							b.remove(0);
							continue;
						}
						JSplitPaneTest1.tate_flag = true;
						jsp.tate();
						b.remove(0);

						continue;

					} else if (b.size() < 3 && b.size() != 0
							&& (b.get(0).contains("!") && JSplitPaneTest1.tate_flag == true)) {
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();

						JSplitPaneTest1.tate_flag = false;

						jsp.tate_match();
						b.remove(0);
						continue;
					} else if (b.size() >= 3
							&& (b.get(0).contains("!") && b.get(2).contains(",")
									&& JSplitPaneTest1.tate_flag == true)) {
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();
						//jsp.tate();

						//JSplitPaneTest1.tate_flag = false;
						//jsp.tate_match();
						b.remove(0);
						continue;
					} else if (b.size() > 0 &&
							(JSplitPaneTest1.tate_flag == true && b.get(0).contains("!")
									&& ((b.size() > 2 && !b.get(2).contains(",")) || b.size() == 2))) {
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						//jsp.tate();////
						System.out.println("%%%%%");
						//JSplitPaneTest1.create_dmy();
						jsp.tate_match();
						//jsp.tate();///
						//JSplitPaneTest1.create_dmy();
						JSplitPaneTest1.tate_flag = false;
						jsp.tate();

						b.remove(0);

						continue;

					} /* else if (b.size() > 4 && b.get(2).contains("!") &&
							(T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])
							&& (T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 2] == T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1])) {

						b.remove(0);
						jsp.createTab(b.get(0));
						nest_cnt++;
						b.remove(0);
						jsp.tate();
						System.out.println("$$$%$%$%$%$%$%$%$");

						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						continue;
						}*/

					/*if((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 2]
							|| nest_cnt2[nest_cnt + 1] != nest_cnt2[nest_cnt + 2])
							&& T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != 99 && nest_cnt2[nest_cnt + 1] != 99)
						nest_change_flag1 = true;*/

					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.tate();
					b.remove(0);

					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
						&& T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt -1] + nest_cnt2[nest_cnt - 1]){
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					if (JSplitPaneTest1.jsp_tmp != null) {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
					JSplitPaneTest1.jsp_tmp = null;
					}

					System.out.println("9999999999999999");
					continue;
				}

				else {
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
						&& T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt -1] + nest_cnt2[nest_cnt - 1]){
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					} else {
						nest_change_flag1 = true;
						nest_1_2.add(1);
						if (JSplitPaneTest1.jsp_tmp != null)
							JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						JSplitPaneTest1.jsp_tmp = null;
						if (JSplitPaneTest1.jPanel_tmp.size() == 0 && b.size() < 3) {
							jsp.createTab(b.get(1));
							b.remove(1);
							jsp.tate();
						}

						else if (JSplitPaneTest1.jPanel_tmp.size() != 0) {
							JSplitPaneTest1.nest_tmp2 = JSplitPaneTest1.jPanel_tmp.get(0);
							jsp.nest_match(5);
							JSplitPaneTest1.jPanel_tmp.clear();
						}

						b.remove(0);
					}
				}
			}

			else if (b.get(0).contains(",")) {
				if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] == T_parser.bracket_level[nest_cnt ] + nest_cnt2[nest_cnt ]
				/*&& nest_cnt2[nest_cnt + 1] == nest_cnt2[nest_cnt]*/)
						&& T_parser.bracket_level[nest_cnt + 1] != 99 && nest_cnt2[nest_cnt + 1] != 99) {

					if (b.size() >= 3 && (b.get(0).contains("!") && b.get(2).contains("!"))) {

						/*
												jsp.createTab(b.get(1));
												nest_cnt++;
												b.remove(1);
												jsp.yoko();
												System.out.println("!&&&&&&&&&&^^^^&&&&&&&&");
												b.remove(0);
												continue;
											}*/
						JSplitPaneTest1.tate_flag = true;
						jsp.yoko();
						b.remove(0);

						continue;

						/*JSplitPaneTest1.tate_flag = true;
						System.out.println("123123131");
						jsp.yoko();
						b.remove(0);
						continue;*/

					} else if (b.size() < 3 && b.size() != 0
							&& (b.get(0).contains(",") && JSplitPaneTest1.tate_flag == true)) {
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();
						JSplitPaneTest1.tate_flag = false;
						jsp.tate_match();
						b.remove(0);
						continue;
					} else if (b.size() >= 3
							&& (b.get(0).contains(",") && b.get(2).contains(",")
									&& JSplitPaneTest1.tate_flag == true)) {

						/*if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 2]
								|| nest_cnt2[nest_cnt + 1] != nest_cnt2[nest_cnt + 2])
										&& T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != 99
										&& nest_cnt2[nest_cnt + 1] != 99
										&& (b.get(1).equals(T_parser.check_3.get(0)) ||   b.get(1).equals(T_parser.check_4.get(0)))) {


							jsp.createTab(b.get(1));
							nest_cnt++;
							b.remove(1);
							jsp.yoko();

							b.remove(0);

							b.remove(0);
							JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
							JSplitPaneTest1.jsp_tmp = null;
							nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
							continue;
						}*/

						if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 2] + nest_cnt2[nest_cnt + 2]
						/*|| nest_cnt2[nest_cnt + 1] != nest_cnt2[nest_cnt + 2]*/)
								&& T_parser.bracket_level[nest_cnt + 2] != 99
								&& nest_cnt2[nest_cnt + 2] != 99) {

							jsp.createTab(b.get(1));
							nest_cnt++;
							b.remove(1);
							jsp.yoko();

							//JSplitPaneTest1.create_dmy();
							//jsp.tate_match();
							//JSplitPaneTest1.create_dmy();
							//JSplitPaneTest1.tate_flag = false;
							b.remove(0);

							/*b.remove(0);
							JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
							JSplitPaneTest1.jsp_tmp = null;
							nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
							continue;*/
						}
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();
						System.out.println("3333334");
						//JSplitPaneTest1.tate_flag = false;
						//jsp.tate_match();
						b.remove(0);
						continue;
					} /*else if (b.size() >= 3
							&& (b.get(0).contains(",") && b.get(2).contains(",")
									&& JSplitPaneTest1.tate_flag == true)) {

						if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 2]
								|| nest_cnt2[nest_cnt + 1] != nest_cnt2[nest_cnt + 2])
								&& T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != 99
								&& nest_cnt2[nest_cnt + 1] != 99) {

							jsp.createTab(b.get(1));
							nest_cnt++;
							b.remove(1);
							jsp.yoko();

							//JSplitPaneTest1.create_dmy();
							//jsp.tate_match();
							//JSplitPaneTest1.create_dmy();
							//JSplitPaneTest1.tate_flag = false;
							b.remove(0);
						}
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();

						//JSplitPaneTest1.tate_flag = false;
						//jsp.tate_match();
						b.remove(0);
						continue;
						}*/ else if (b.size() > 0 &&
							(JSplitPaneTest1.tate_flag == true && b.get(0).contains(",")
									&& ((b.size() > 2 && !b.get(2).contains(",")) || b.size() == 2))) {
						jsp.createTab(b.get(1));
						nest_cnt++;
						b.remove(1);
						jsp.yoko();

						//JSplitPaneTest1.create_dmy();
						jsp.tate_match();
						//JSplitPaneTest1.create_dmy();
						JSplitPaneTest1.tate_flag = false;
						b.remove(0);

						continue;

					}

					jsp.createTab(b.get(1));
					nest_cnt++;
					b.remove(1);
					jsp.yoko();
					b.remove(0);

					/*if(b.get(0).contains("!")
							&& T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1]
									== T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt]){
					JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
					JSplitPaneTest1.jsp_tmp = null;
					b.remove(0);
					System.out.println("123123131@");
					}*/
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
									&& T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt -1] + nest_cnt2[nest_cnt - 1]){
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					System.out.println("123123131");
					continue;
				}

				else if ((T_parser.bracket_level[nest_cnt + 1] + nest_cnt2[nest_cnt + 1] != T_parser.bracket_level[nest_cnt ] + nest_cnt2[nest_cnt]
						)
						&& T_parser.bracket_level[nest_cnt + 1] != 99 && nest_cnt2[nest_cnt + 1] != 99) {

					/*if (nest_change_flag1 == true && JSplitPaneTest1.nest_tmp.size() == 0) {
						jsp.nest_match(3);
						//nest_change_flag1 = false;
					}
					else if (nest_change_flag1 == true && JSplitPaneTest1.nest_tmp.size() != 0) {
						jsp.nest_match(1);
						//nest_change_flag1 = false;
					}*/
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
									&& T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt -1] + nest_cnt2[nest_cnt - 1]){
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					}

					nest_1_2.add(2);
					if (JSplitPaneTest1.jsp_tmp != null) {
						JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
						JSplitPaneTest1.jsp_tmp = null;
					}

					if (JSplitPaneTest1.jPanel_tmp.size() == 0 && b.size() < 3) {
						jsp.createTab(b.get(1));
						b.remove(1);
						jsp.yoko();
						//jsp.tate();
					} else if (JSplitPaneTest1.jPanel_tmp.size() != 0) {
						JSplitPaneTest1.nest_tmp2 = JSplitPaneTest1.jPanel_tmp.get(0);
						jsp.nest_match(5);
						nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
						JSplitPaneTest1.jPanel_tmp.clear();
						System.out.println("3333333");
					}
					b.remove(0);

				}

				else {
					if (nest_change2_list.size() > 0
							&& nest_change2_list.get(nest_change2_list.size() - 1) == JSplitPaneTest1.nest_tmp.size()
									&& T_parser.bracket_level[nest_cnt] + nest_cnt2[nest_cnt] == T_parser.bracket_level[nest_cnt -1] + nest_cnt2[nest_cnt - 1]){
						jsp.nest_match(2);
						nest_change2_list.remove(nest_change2_list.size() - 1);
					} /*else if (nest_change_flag1 == true) {
						jsp.nest_match(1);

						nest_change_flag1 = false;
						}*/ else {

						nest_1_2.add(2);
						if (JSplitPaneTest1.jsp_tmp != null) {
							JSplitPaneTest1.nest_tmp.add(JSplitPaneTest1.jsp_tmp);
							nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
							JSplitPaneTest1.jsp_tmp = null;
						}

						JSplitPaneTest1.jsp_tmp = null;
						if (JSplitPaneTest1.jPanel_tmp.size() == 0 && b.size() < 3) {
							jsp.createTab(b.get(1));
							b.remove(1);
							jsp.yoko();
						} else if (JSplitPaneTest1.jPanel_tmp.size() != 0) {
							JSplitPaneTest1.nest_tmp2 = JSplitPaneTest1.jPanel_tmp.get(0);
							jsp.nest_match(5);
							nest_change2_list.add(JSplitPaneTest1.nest_tmp.size());
							JSplitPaneTest1.jPanel_tmp.clear();
							System.out.println("3333333");
						}
						b.remove(0);
					}
				}
			}
		}


		System.out.println("##################################");

		jsp.c();
		JSplitPaneTest1.jPanel.get(0).setSize(new Dimension(500, 500));

		//jsp.First_panel();

		/*for (int i = 0; i < JSplitPaneTest1.yoko_num.length; i++)
			System.out.println(JSplitPaneTest1.yoko_num[i]);*/

		System.out.println(tfe_id_arr.size() + ", " + tfe_name_arr.size());
		System.out.println("######### TFE ID - TFE Name ########");

		if (tfe_name_arr.size() <= tfe_id_arr.size())
			for (int i = 0; i < tfe_name_arr.size(); i++)
				System.out.println(tfe_id_arr.get(i) + " - " + tfe_name_arr.get(i));
		else
			for (int i = 0; i < tfe_id_arr.size(); i++)
				System.out.println(tfe_id_arr.get(i) + " - " + tfe_name_arr.get(i));

		System.out.println("##################################");

		query_layout = new String[tfe_name_arr.size()];
		query_layout2 = new String[tfe_name_arr.size()];

		for (int i = 0; i < query_layout.length; i++) {
			query_layout[i] = "";
			query_layout2[i] = "";
		}

		width_arr = new int[JSplitPaneTest1.jPanel.size()];
		height_arr = new int[JSplitPaneTest1.jPanel.size()];

		//初期の色などを配置するための処理

		String tmp = "";
		String tmp2[] = new String[64];

		for (int num = 0; num < JSplitPaneTest1.jPanel.size(); num++) {

			for (int j = 0; j < T_parser.layout_load_name.size(); j++)
				if (JSplitPaneTest1.jPanel_name.get(num).contains(T_parser.layout_load_name.get(j))) {
					a1_flag = true;

					break;
				}

			if (a1_flag == false)
				continue;

			a1_flag = false;

			if (num < tfe_id_arr.size() && HTMLEnv.css != null)
				if (HTMLEnv.css.toString().contains(tfe_id_arr.get(num).toString())) {

					int n = HTMLEnv.css.indexOf(".TFE" + String.valueOf(tfe_id_arr.get(num)));
					int m = HTMLEnv.css.indexOf("}", n);
					if (n != -1 && m != -1) {
						tmp = HTMLEnv.css.substring(n + 10, m - 1);
						HTMLEnv.css.delete(n, m + 1);
					}
					//System.out.println(tmp);
					tmp2 = tmp.split(";");

					for (int i = 0; i < tmp2.length; i++) {
						HTMLEnv.css.append(".TFE" + tfe_id_arr.get(num).toString() + "{" + tmp2[i] + "; }");

						if (tmp2[i].contains("width"))
							T_parser.w_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("height"))
							T_parser.h_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("font-size"))
							T_parser.f_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("margin-top"))
							T_parser.mt_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("margin-right"))
							T_parser.mr_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("margin-bottom"))
							T_parser.mb_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));
						else if (tmp2[i].contains("margin-left"))
							T_parser.ml_load[num] = Integer
									.parseInt(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));

						//JSplitPaneTest1.w_input.setText(tmp2[i].substring(tmp2[i].indexOf(":") + 1, tmp2[i].indexOf("px")));

					}

					HTMLEnv.css.append("\n");

					tmp = "";
					tmp2 = null;

				}
		}

		try {
			int n = 0;
			int m = 0;
			n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
			m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

			String cssname = GlobalEnv.getfilename().substring(n, m);
			cssname = cssname.replace(".ssql", ".css");

			String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
			//System.out.println(csspass);

			FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
			OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
			PrintWriter pw = new PrintWriter(osw);

			pw.println(HTMLEnv.css.toString());

			//System.out.println(HTMLEnv.css.toString());
			//System.out.println(JSplitPaneTest1.jPanel_name.get(0));

			pw.flush();
			pw.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		JSplitPaneTest1.jButton.get(0).doClick();
		//JSplitPaneTest1.layout_panel.setEnabled(false);
		Query_layout_add();
		JSplitPaneTest1.undo_btn.setEnabled(false);
		JSplitPaneTest1.redo_btn.setEnabled(false);
		Preview.preview(FrontEnd.Preview_URL);
		JSplitPaneTest1.main_frame.setLocation(0, JSplitPaneTest1.frame.getHeight() + 50);
		JSplitPaneTest1.main_frame.setVisible(true);
		Time_reload();

	}

	public static void Time_reload() {

		if (JSplitPaneTest1.panel_option_flag == true) {
			//時間感覚で更新
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {

					for (int num = 0; num < JSplitPaneTest1.jPanel.size(); num++) {

						if (JSplitPaneTest1.size_fixed_name.contains(tfe_name_arr.get(num)))
							continue;

						final_width[num] = JSplitPaneTest1.jPanel.get(num).getWidth();
						final_height[num] = JSplitPaneTest1.jPanel.get(num).getHeight() /** 3 / 4*/
						;
						frame_final_width = JSplitPaneTest1.frame.getWidth();
						frame_final_height = JSplitPaneTest1.frame.getHeight() /** 3 / 4*/
						;

						try {
							if (frame_start_height != frame_final_height || frame_start_width != frame_final_width) {

								Thread.sleep(100);
								frame_start_width = JSplitPaneTest1.frame.getWidth();
								frame_start_height = JSplitPaneTest1.frame.getHeight() /** 3 / 4*/
								;

							}

							else if (final_width[num] != start_width[num] ||
									final_height[num] != start_height[num]) {

								timer.cancel();
								Jpanel_reload();
								//Thread.sleep(300);
								Query_layout_add();
								//Thread.sleep(300);
								//
								break;
							}
						} catch (Exception e) {
							System.out.println(e);
						}

					}
				}
			};
			timer.schedule(task, 1, 1);
		}
	}

	static void Jpanel_reload() {

		StringBuffer sb = HTMLEnv.css;

		if (JSplitPaneTest1.panel_option_flag == true) {

			for (int num = 0; num < JSplitPaneTest1.jPanel.size(); num++) {

				if (JSplitPaneTest1.size_fixed_name.contains(tfe_name_arr.get(num)))
					continue;
				for (int i = 0; i < JSplitPaneTest1.size_fixed_name.size(); i++)
					for (int j = 0; j < tfe_name_arr.size(); j++)
						if (tfe_name_arr.get(j).equals(JSplitPaneTest1.size_fixed_name.get(i))) {
							break;
						}

				//もしCSS内にTFEのIDがなかったら，追加

				int getWidth = JSplitPaneTest1.jPanel.get(num).getWidth();
				int getHeight = JSplitPaneTest1.jPanel.get(num).getHeight() /** 3 / 4*/
				;

				if (num < tfe_id_arr.size())
					if (!sb.toString().contains(tfe_id_arr.get(num).toString() + "{ width")) {


						sb.append(".TFE" + tfe_id_arr.get(num) + "{ width:" + (getWidth) + "px; }");

					} else {

						int n = sb.indexOf(".TFE" + String.valueOf(tfe_id_arr.get(num)) + "{ width");
						int m = sb.indexOf("}", n);
						sb.delete(n, m + 1);
						sb.append(".TFE" + tfe_id_arr.get(num) + "{ width:" + (getWidth) + "px; }");

					}

				if (num < tfe_id_arr.size())
					if (!sb.toString().contains(tfe_id_arr.get(num).toString() + "{ height")) {

						sb.append(".TFE" + tfe_id_arr.get(num) + "{ height:" + getHeight + "px; }");

					} else {
						int n = sb.indexOf(".TFE" + String.valueOf(tfe_id_arr.get(num)) + "{ height");
						int m = sb.indexOf("}", n);
						sb.delete(n, m + 1);
						sb.append(".TFE" + tfe_id_arr.get(num) + "{ height:" + getHeight + "px; }");
						JSplitPaneTest1.jPanel.get(num);
					}

				start_width[num] = getWidth;
				start_height[num] = getHeight;

				width_arr[num] = getWidth;
				height_arr[num] = getHeight;

				T_parser.w_load[num] = getWidth;
				T_parser.h_load[num] = getHeight;

				if (JSplitPaneTest1.button_index == num) {
					JSplitPaneTest1.dmy_Button.doClick();
					//JSplitPaneTest1.jButton.get(num+1).doClick();
					JSplitPaneTest1.jButton.get(num).doClick(5);
				}
			}



				try {
					int n = 0;
					int m = 0;
					n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
					m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

					String cssname = GlobalEnv.getfilename().substring(n, m);
					cssname = cssname.replace(".ssql", ".css");

					String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
					//System.out.println(csspass);

					FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
					OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
					PrintWriter pw = new PrintWriter(osw);

					pw.println(sb.toString());

					pw.close();

				} catch (Exception e) {
					System.out.println(e);

				}

			Time_reload();

		}
	}

	public static void TFE_id_add(int id) {

		tfe_id_arr.add(id);

	}

	public static void TFE_name_add(String name) {

		tfe_name_arr.add(name);

	}


	public static int countStringInString(String target, String searchWord) {
		return (target.length() - target.replaceAll(searchWord, "").length()) / searchWord.length();
	}

	public static void Query_layout_add() {

		for (int i = 0; i < query_layout.length; i++) {
			query_layout[i] = "";
			query_layout2[i] = "";
		}

//		if (T_parser.table_at != "") {
//
//		}
		query_layout_table = "";
		query_layout_table2 = "";

		String css = HTMLEnv.css.toString();
		int l = 0;
		int n = 0;
		int m = 0;
		int n1 = 0;
		int m1 = 0;

		//for(int i = 0; i < query_layout.length; i++)

		for (int i = 0; i < tfe_id_arr.size(); i++) {
			int cnt = countStringInString(css, String.valueOf(tfe_id_arr.get(i)));
//			System.out.println("tfe_id_arr: "+tfe_id_arr);
//			System.out.println("cnt: "+cnt);
			for (int j = 0; j < cnt; j++){
				try {
					if (css.contains(String.valueOf(tfe_id_arr.get(i)))) {

						n1 = css.indexOf(String.valueOf(tfe_id_arr.get(i)), l) + 7;
						m1 = css.indexOf("}", n1);
//						System.out.println("css: "+css);
//						System.out.println("m1: "+m1);
						query_layout[i] += css.substring(n1, m1 - 2);
						query_layout[i] += ",";
						l = m1;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			l = 0;
			//System.out.println(css);

			//System.out.println(query_layout[i]);
		}

		for (int i = 0; i < query_layout.length; i++)
			if (query_layout[i] == "") {
				query_layout2[i] += tfe_name_arr.get(i);
			} else if (query_layout[i] != "") {

				if (tfe_name_arr.get(i).contains("||") && !tfe_name_arr.get(i).contains("{")
						&& !tfe_name_arr.get(i).contains("}")) {
					query_layout2[i] += "{" + tfe_name_arr.get(i) + "}@{";
				} else
					query_layout2[i] += tfe_name_arr.get(i) + "@{";

				query_layout3.add(tfe_name_arr.get(i));

				String q_csv = query_layout[i];

				if (q_csv.contains("width")) {
					n = q_csv.indexOf("width") + 6;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						//query_layout2[i] += ", ";
						query_layout2[i] += "width=" + q_csv.substring(n, m);

					}
				}

				if (q_csv.contains("height")) {

					n = q_csv.indexOf("height") + 7;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "height=" + q_csv.substring(n, m);

					}
				}

				if (q_csv.contains("font-size")) {

					n = q_csv.indexOf("font-size") + 10;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "font-size=" + q_csv.substring(n, m);

					}
				}

				if (q_csv.contains("font-weight")) {
					query_layout2[i] += ", ";
					query_layout2[i] += "font-weight='bold'";
				}

				if (q_csv.contains("font-style")) {
					query_layout2[i] += ", ";
					query_layout2[i] += "font-style='italic'";
				}

				/*if (q_csv.contains("text-align:left")) {
					query_layout2[i] += ", ";
					query_layout2[i] += "align='left'";
				}*/

				if (q_csv.contains("text-align:center")) {
					query_layout2[i] += ", ";
					query_layout2[i] += "align='center'";
				}

				if (q_csv.contains("text-align:right")) {
					query_layout2[i] += ", ";
					query_layout2[i] += "align='right'";
				}

				if (q_csv.contains("margin-top")) {

					n = q_csv.indexOf("margin-top") + 11;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "margin-top='" + q_csv.substring(n, m) + "px'";

					}
				}

				if (q_csv.contains("margin-right")) {

					n = q_csv.indexOf("margin-right") + 13;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "margin-right='" + q_csv.substring(n, m) + "px'";

					}
				}

				if (q_csv.contains("margin-bottom")) {

					n = q_csv.indexOf("margin-bottom") + 14;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "margin-bottom='" + q_csv.substring(n, m) + "px'";

					}
				}

				if (q_csv.contains("margin-left")) {

					n = q_csv.indexOf("margin-left") + 12;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n) - 2;
					if (!q_csv.substring(n, m).equals("0") && !q_csv.substring(n, m).equals("00")) {
						query_layout2[i] += ", ";
						query_layout2[i] += "margin-left='" + q_csv.substring(n, m) + "px'";

					}
				}

				if (q_csv.contains("background-color")) {
					query_layout2[i] += ", ";
					n = q_csv.indexOf("background-color") + 17;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n);
					query_layout2[i] += "bgcolor='" + q_csv.substring(n, m) + "'";

					n = 0;
					m = 0;
					q_csv = q_csv.replace("background-color", " ");
				}

				if (q_csv.contains("color")) {

					n = q_csv.indexOf("color") + 6;
					m = q_csv.indexOf((q_csv.substring(n).contains(";")? ";" : ","), n);

					if (q_csv.charAt(0) == 'c') {
						query_layout2[i] += ", ";

						query_layout2[i] += "color=\'" + q_csv.substring(n, m) + "\'";
						n = 0;
						m = 0;
					}

					else if (n > 7) {
						//if (q_csv.charAt(n - 7) != '-') {
						query_layout2[i] += ", ";
						//System.out.println(q_csv.charAt(n - 7) + "9090909090");
						query_layout2[i] += "color=\'" + q_csv.substring(n, m) + "\'";
						n = 0;
						m = 0;

					}
				}

				query_layout2[i] += "}";
				System.out.println("query_layout2[i] = "+query_layout2[i]);

				if (query_layout2[i].contains("{, ")) {
					query_layout2[i] = query_layout2[i].replace("{, ", "{");
				}

				//System.out.println(query_layout2[i]);
			}

		String ssql2 = FrontEnd.ssql;

		String ssql = "";
		StringBuilder sb = new StringBuilder(ssql2);
		int same_cnt = 0;

		String[] same_name = new String[32];

		//同じ名前（[2]）があるのを抽出
		for (int i = 0; i < tfe_name_arr.size(); i++) {

			if (tfe_name_arr.get(i).contains("<2>")) {
				same_name[same_cnt++] = tfe_name_arr.get(i).substring(0, tfe_name_arr.get(i).length() - 3);
				//System.out.println(same_name[same_cnt - 1]);
			}
		}

		for (int i = 0; i < same_cnt; i++) {
			int pre_location = 0;
			pre_location = sb.indexOf(same_name[i], pre_location) + same_name[i].length();
			int aft_location = 0;
			int same_cnt2 = 2;
			while (true) {
				aft_location = sb.indexOf(same_name[i], pre_location);
				if (aft_location == -1) {
					break;
				}
				int l1 = same_name[i].length();
				int a = aft_location + l1;
				//System.out.println(aft_location + "      "+ a);
				sb.delete(aft_location, a);
				sb.insert(aft_location, same_name[i] + "<" + String.valueOf(same_cnt2) + ">");
				pre_location = aft_location + same_name.length;
				same_cnt2++;

			}
		}

		ssql2 = sb.toString();

		//クエリから@装飾を抜く
		boolean at_flag = false;
		String tmp2 = "";
		String tmp3 = "";
		boolean at_flag2 = false;
		int css_num = 0;
		css_other = new String[tfe_name_arr.size()];
		for (int j = 0; j < css_other.length; j++)
			css_other[j] = "";

		for (int i = 0; i < ssql2.length(); i++) {
			String ccc = String.valueOf(ssql2.charAt(i));

			for (int j = 0; j < css_other.length; j++) {
				//css_other[j] = "";

				if (tmp3.endsWith(tfe_name_arr.get(j))) {
					at_flag2 = true;
					css_num = j;
				}
			}


			if (ccc.contains("@") && at_flag2 == true) {
				at_flag = true;
				tmp3 = "";
				at_flag2 = false;
			}

			else if (at_flag == true && !ccc.contains("}"))
				tmp2 += ccc;

			else if (at_flag == true && ccc.contains("}")) {
				tmp2 += ccc;
				at_flag = false;
				if (tmp2.contains("{debug")) {
					int a = tmp2.indexOf("{debug");
					ssql += "@" + tmp2.substring(a, tmp2.length());
				} else if (tmp2.contains("debug")) {
					int a = tmp2.indexOf("debug");
					ssql += "@{" + tmp2.substring(a, tmp2.length());
				}
				//System.out.println(tmp2 + ":::1::::");
				if (tmp2.contains("class") && tmp2.contains("=")) {
					int a = tmp2.indexOf("class");
					int b = tmp2.indexOf("'", a) + 1;
					int c = tmp2.indexOf("'", b);
					if (a != -1 && c != -1)
						css_other[css_num] += ", " + tmp2.substring(a, c + 1);
					//System.out.println(css_other[css_num] + ":::::::");
				}
				if (tmp2.contains("valign") && tmp2.contains("=")) {
					int a = tmp2.indexOf("valign");
					int b = tmp2.indexOf("'", a) + 1;
					int c = tmp2.indexOf("'", b);
					if (a != -1 && c != -1)
						css_other[css_num] += ", " + tmp2.substring(a, c + 1);
					//System.out.println(css_other[css_num] + ":::::::");
				}
				if (tmp2.contains("id") && tmp2.contains("=")) {
					tmp2 = tmp2.replace("width", "");
					int a = tmp2.indexOf("id");
					int b = tmp2.indexOf("'", a) + 1;
					int c = tmp2.indexOf("'", b);
					if (a != -1 && c != -1)
						css_other[css_num] += ", " + tmp2.substring(a, c + 1);
					//System.out.println(css_other[css_num] + ":::::::");
				}
				if (tmp2.contains("style") && tmp2.contains("=")) {
					int a = tmp2.indexOf("style");
					int b = tmp2.indexOf("'", a) + 1;
					int c = tmp2.indexOf("'", b);
					if (a != -1 && c != -1)
						css_other[css_num] += ", " + tmp2.substring(a, c + 1);
					//System.out.println(css_other[css_num] + ":::::::");
				}
				tmp2 = "";

			} else if (at_flag == false) {
				ssql += ccc;
			}

			tmp3 += ccc;
			/*if(css_num == query_layout3.size() - 1)
				break;*/

		}

		for (int i = 0; i < css_other.length; i++) {
			if (css_other[i] != "") {
				if (query_layout2[i].contains("@{"))
					query_layout2[i] = query_layout2[i].replace("}", css_other[i] + "}");
				else
					query_layout2[i] += "@{" + css_other[i] + "}";
			}
			if (query_layout2[i].contains("{, ")) {
				query_layout2[i] = query_layout2[i].replace("{, ", "{");
			}
		}

		//System.out.println(ssql);

		for (int i = 0; i < query_layout.length; i++) {

			if (ssql.contains(tfe_name_arr.get(i))) {

				if (query_layout2[i].equals(tfe_name_arr.get(i) + "@{}"))
					continue;

				//System.out.println(query_layout2[i] + "}{}{}{{");

				String s3 = "";
				//String aa = tfe_name_arr.get(i);

				int n3 = ssql.indexOf(tfe_name_arr.get(i));
				//int m3 = ssql.indexOf(")", n3);
				if (n3 >= 12)
					s3 = ssql.substring(n3 - 12, n3);

				if (/*s3.contains("link(") ||*/ s3.contains("a(") || s3.contains("anchor(") || s3.contains("image(")) {
					int n2 = ssql.indexOf(tfe_name_arr.get(i));

					if (s3.contains("asc") || s3.contains("desc"))
						n2 = ssql.indexOf(")", n2);

					int m2 = ssql.indexOf(")", n2);
					String s2 = query_layout2[i].replaceFirst(tfe_name_arr.get(i), "");

					StringBuilder sb2 = new StringBuilder(ssql);
					if (s2.contains("@{")) {
						s2 = query_layout2[i].replaceFirst(tfe_name_arr.get(i), "");
						int aa2 = s2.indexOf("@{");
						s2 = s2.substring(aa2);
						sb2 = sb2.insert(m2 + 1, s2);
						//sb2 = sb2.delete(n2, m2 + 1);

						ssql = sb2.toString();
					}
				}

				else {

					int d = 0;
					while (true) {
						int m2 = ssql.indexOf(tfe_name_arr.get(i), d);
						int n2 = ssql.indexOf(tfe_name_arr.get(i), d) + tfe_name_arr.get(i).length();
						//String aaa = ssql.substring(m2, n2);
						if (!ssql.substring(n2, n2 + 3).contains(")")
								&& !ssql.substring(m2 - 8, m2).contains("sum[")
								&& !ssql.substring(m2 - 8, m2).contains("max[")
								&& !ssql.substring(m2 - 8, m2).contains("min[")
								&& !ssql.substring(m2 - 8, m2).contains("avg[")
								&& !ssql.substring(m2 - 8, m2).contains("count[")) {
							if (!tfe_name_arr.contains("||"))
								ssql = ssql.substring(0, m2) + query_layout2[i] + ssql.substring(n2);

							break;
						} else if (n2 - tfe_name_arr.get(i).length() < 0 || n2 < 0)
							break;
						else {
							d = n2;
							continue;
						}

					}
				}
			}

		}

		css = HTMLEnv.css.toString();
		l = 0;
		n = 0;
		m = 0;
		n1 = 0;
		m1 = 0;

//		if (T_parser.table_at_flag == true) {
//			int a = ssql.lastIndexOf("@");
//			int f = ssql.indexOf("}", a);
//			//System.out.println(a + "??" + f + "78787");
//			if (a < f && a != -1 && f != -1)
//				ssql = ssql.substring(0, a) + ssql.substring(f + 1);
//			//System.out.println(ssql);
//			//T_parser.table_at_flag = true;
//		}
//		T_parser.table_at_flag = true;
		T_parser.table_at_flag = false;
		int a1 = css.indexOf("body");
		int f2 = css.indexOf("}", a1);
		if (a1 != -1 && f2 != -1)
			query_layout_table = css.substring(a1, f2);

		query_layout_table2 += "@{";

//		if (T_parser.table_at_flag2)
//			query_layout_table2 += "debug='on'";
//		else {
//			query_layout_table2 += "debug='off'";
//		}

		if (query_layout_table.contains("text-align: center")) {
//			query_layout_table2 += ", ";
			query_layout_table2 += "table-align='center'";
		}
		if (query_layout_table.contains("text-align: right")) {
			query_layout_table2 += ", ";
			query_layout_table2 += "table-align='right'";
		}
		if (query_layout_table.contains("background-color")) {

			int n2 = query_layout_table.indexOf("background-color") + 18;
			int m2 = query_layout_table.indexOf(";", n2);
			if (n2 != -1 && m2 != -1 && n2 < m2 && (m2 - n2) > 2) {
				query_layout_table2 += ", ";
				query_layout_table2 += "pbgcolor='" + query_layout_table.substring(n2, m2) + "'";
			}
		}

		if (T_parser.table_at.contains("cssfile")) {
			query_layout_table2 += ", ";
			int n2 = T_parser.table_at.indexOf("cssfile");
			int n22 = T_parser.table_at.indexOf("'", n2) + 1;
			int m2 = T_parser.table_at.indexOf("'", n22);
			if (n2 != -1 && m2 != -1 && n2 < m2) {
				query_layout_table2 += T_parser.table_at.substring(n2, m2 + 1);
			}
		}


		if (T_parser.table_at.contains("jsfile")) {
			query_layout_table2 += ", ";
			int n2 = T_parser.table_at.indexOf("jsfile");
			int n22 = T_parser.table_at.indexOf("'", n2) + 1;
			int m2 = T_parser.table_at.indexOf("'", n22);
			if (n2 != -1 && m2 != -1 && n2 < m2) {
				query_layout_table2 += T_parser.table_at.substring(n2, m2 + 1);
			}
		}
		if (T_parser.table_at.contains("author")) {
			query_layout_table2 += ", ";
			int n2 = T_parser.table_at.indexOf("author");
			int n22 = T_parser.table_at.indexOf("'", n2) + 1;
			int m2 = T_parser.table_at.indexOf("'", n22);
			if (n2 != -1 && m2 != -1 && n2 < m2) {
				query_layout_table2 += T_parser.table_at.substring(n2, m2 + 1);
			}
		}
		if (T_parser.table_at.contains("charset")) {
			query_layout_table2 += ", ";
			int n2 = T_parser.table_at.indexOf("charset");
			int n22 = T_parser.table_at.indexOf("'", n2) + 1;
			int m2 = T_parser.table_at.indexOf("'", n22);
			if (n2 != -1 && m2 != -1 && n2 < m2) {
				query_layout_table2 += T_parser.table_at.substring(n2, m2 + 1);
			}
		}
		if (T_parser.table_at.contains("background")) {
			query_layout_table2 += ", ";
			int n2 = T_parser.table_at.indexOf("background");
			int n22 = T_parser.table_at.indexOf("'", n2) + 1;
			int m2 = T_parser.table_at.indexOf("'", n22);
			if (n2 != -1 && m2 != -1 && n2 < m2) {
				query_layout_table2 += T_parser.table_at.substring(n2, m2 + 1);
			}
		}
		
		query_layout_table2 += "}";
		if (query_layout_table2.contains("{, ")) {
			query_layout_table2 = query_layout_table2.replace("{, ", "{");
		}
		if (query_layout_table2.contains(", }")) {
			query_layout_table2 = query_layout_table2.replace(", }", "}");
		}
		//System.out.println("query_layout_table2: "+query_layout_table2);
		query_layout_table2 = T_parser.table_at+"\n";	//TODO これだと問題となるケースは無いか
//		System.out.println(ssql);
//		System.out.println(ssql.length());
		if (!query_layout_table2.contains("@{}")){		//TODO コメントアウトでOKか
//			int current_ssqlquery_length = ssql.length();
//			ssql = ssql.substring(0, T_parser.table_at_first_index)
//			       +query_layout_table2
//			       +ssql.substring(T_parser.table_at_first_index+1+T_parser.table_at.length());
//			T_parser.table_at_first_index += current_ssqlquery_length-ssql.length();
			
//			if (ssql.contains("FROM"))
//				ssql = ssql.replace("FROM", query_layout_table2 + " FROM");
//			else if (ssql.contains("from"))
//				ssql = ssql.replace("from", query_layout_table2 + " from");
//			else if (ssql.contains("From"))
//				ssql = ssql.replace("From", query_layout_table2 + " From");
		
		}
		//ssql = T_parser.ssqlquery_org;	//TODO これだと問題となるケースは無いか

		StringBuilder sb2 = new StringBuilder(ssql);
		int l2 = 0;

		for (int i = 0; i < JSplitPaneTest1.line_color.length; i++) {
			int n2 = sb2.indexOf("line(", l2);
			int m2 = sb2.indexOf(")", n2);
			if (n2 < m2 && n2 > 0 && m2 > 0) {
				sb2.delete(n2, m2);
				if (JSplitPaneTest1.line_size[i].contains("default")
						&& JSplitPaneTest1.line_color[i].contains("default"))
					;
				else if (JSplitPaneTest1.line_size[i].contains("default"))
					sb2.insert(n2, "line(" + "'" + JSplitPaneTest1.line_color[i] + "'");
				else if (JSplitPaneTest1.line_color[i].contains("default"))
					sb2.insert(n2, "line(" + JSplitPaneTest1.line_size[i]);
				else
					sb2.insert(n2,
							"line(" + JSplitPaneTest1.line_size[i] + ", '" + JSplitPaneTest1.line_color[i] + "'");
			}
			l2 = m2;
		}

		ssql = sb2.toString();

		ssql = ssql.replaceAll("<[2-9]>", "");

		JSplitPaneTest1.textArea2.setText(ssql);

//		if (!JSplitPaneTest1.debugCheckBox.isSelected()) {
//
//			for (int i = JSplitPaneTest1.undo_cnt + 1; i < JSplitPaneTest1.textArea_undo.size(); i++) {
//				JSplitPaneTest1.textArea_undo.remove(i);
//				JSplitPaneTest1.css_undo.remove(i);
//				//JSplitPaneTest1.html_undo.remove(i);
//				JSplitPaneTest1.w_undo.remove(i);
//				JSplitPaneTest1.h_undo.remove(i);
//				JSplitPaneTest1.f_undo.remove(i);
//				JSplitPaneTest1.mt_undo.remove(i);
//				JSplitPaneTest1.mr_undo.remove(i);
//				JSplitPaneTest1.mb_undo.remove(i);
//				JSplitPaneTest1.ml_undo.remove(i);
//				//JSplitPaneTest1.l_undo.remove(i);
//
//			}
//			JSplitPaneTest1.textArea_undo.add(ssql);
//			JSplitPaneTest1.undo_cnt = JSplitPaneTest1.textArea_undo.size() - 1;
//			JSplitPaneTest1.undo_btn.setEnabled(true);
//			JSplitPaneTest1.redo_btn.setEnabled(false);
//			JSplitPaneTest1.css_undo.add(HTMLEnv.css.toString());
//			/*if(JSplitPaneTest1.line_action_flag == false)
//				JSplitPaneTest1.html_undo.add("dmy");*/
//			JSplitPaneTest1.line_action_flag = false;
//			//System.out.println(HTMLEnv.css.toString());
//
//			String[] w = new String[64];
//			String[] h = new String[64];
//			String[] f1 = new String[64];
//			String[] t = new String[64];
//			String[] r = new String[64];
//			String[] b = new String[64];
//			String[] l1 = new String[64];
//			//String[] li = new String[32];
//
//			for (int i = 0; i < tfe_name_arr.size(); i++) {
//
//				w[i] = (String.valueOf(T_parser.w_load[i]));
//				h[i] = (String.valueOf(T_parser.h_load[i]));
//				f1[i] = (String.valueOf(T_parser.f_load[i]));
//				t[i] = (String.valueOf(T_parser.mt_load[i]));
//				r[i] = (String.valueOf(T_parser.mr_load[i]));
//				b[i] = (String.valueOf(T_parser.mb_load[i]));
//				l1[i] = (String.valueOf(T_parser.ml_load[i]));
//			}
//
//			/*for (int i = 0; i < T_parser.line_list.size(); i++)
//				li[i] = T_parser.line_list.get(i);*/
//
//			JSplitPaneTest1.w_undo.add(w);
//			JSplitPaneTest1.h_undo.add(h);
//			JSplitPaneTest1.f_undo.add(f1);
//			JSplitPaneTest1.mt_undo.add(t);
//			JSplitPaneTest1.mr_undo.add(r);
//			JSplitPaneTest1.mb_undo.add(b);
//			JSplitPaneTest1.ml_undo.add(l1);
//			//JSplitPaneTest1.l_undo.add(li);
//
//		}

	}

	static int[] Width_adjust() {

		int max = 0;
		int sum = 0;
		int[] index = new int[JSplitPaneTest1.jPanel.size()];

		for (int i = 0; i < JSplitPaneTest1.jPanel.size(); i++) {
			if (max < JSplitPaneTest1.jPanel.get(i).getWidth())
				max = JSplitPaneTest1.jPanel.get(i).getWidth();
		}
		for (int i = 0; i < JSplitPaneTest1.jPanel.size(); i++) {
			if (max != JSplitPaneTest1.jPanel.get(i).getWidth())
				index[i] = 1;
		}

		for (int i = 0; i < index.length; i++) {
			if (index[i] == 1)
				sum += JSplitPaneTest1.jPanel.get(i).getWidth();
		}

		distance = (max - sum) / index.length;

		//System.out.println("dis= " + distance);

		return index;

	}

	//TFE補完 & tfe_id_arr
	public static void Html_tfe_add() {


		int n = 0;
		int m = 0;
		n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
		m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;
		String filename = GlobalEnv.getfilename().substring(n, m);
		filename = filename.replace(".ssql", ".html");
		html_file = FrontEnd.html_file;

		if (filename != null) {
			try {
				FileWriter file1 = new FileWriter(tmpFile, true);
				BufferedWriter pw1 = new BufferedWriter(new BufferedWriter(file1));

				try (BufferedReader in = new BufferedReader(new FileReader(new File(html_file)))) {
					String line = "";
					String pre_line = "";
					String tfe_id = "";
					String tfe_tmp = "";
					int f = 0;
					int l = 0;

					while ((line = in.readLine()) != null) {
						if (line.contains("TFE1")) {
							f = line.indexOf("TFE1");
							//l = line.indexOf("nest", f) - 1;
							if (f > 0)
								tfe_tmp = line.substring(f, f + 8);
						}
						if (line.contains("<table class") && line.contains("TFE10")) {
							f = line.indexOf("TFE");
							//l = line.indexOf("\"", f) - 1;
							if (f < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = line.substring(f, f + 8);
							ooo.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("<table class") && !line.contains("TFE10")) {
							f = pre_line.indexOf("TFE");
							l = pre_line.indexOf("nest", f) - 1;
							if (f < 0 || l < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = pre_line.substring(f, l);
							ooo.add(tfe_id);
							line = line.replace("att", "att " + tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("<td class") && line.contains("</td>")) {
							f = line.indexOf("TFE");
							l = line.indexOf("nest", f) - 1;
							int l2 = line.indexOf(">", l);
							int l3 = line.indexOf("</td", l2);

							if (f < 0 || l < 0 || (l3 - l2 < 3)) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = line.substring(f, l);
							ooo.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("<table style")) {
							f = line.indexOf("TFE");
							l = f + 8;

							if (f < 0 || l < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = line.substring(f, l);
							ooo.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("img class") && line.contains("TFE")) {
							if (pre_line.contains("<a href"))
								ooo.remove(ooo.size() - 1);
							l = line.indexOf("TFE1");
							if (l < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = line.substring(l, l + 8);
							ooo.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("img class")) {
							if (pre_line.contains("<a href"))
								ooo.remove(ooo.size() - 1);
							f = pre_line.indexOf("TFE");
							l = pre_line.indexOf("nest", f) - 1;
							if (f < 0 || l < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = pre_line.substring(f, l);
							ooo.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("<a href")/* && !line.contains("att")*/) {
							f = pre_line.indexOf("TFE");
							l = pre_line.indexOf("nest", f) - 1;
							if (f < 0 || l < 0) {
								ooo.add(tfe_tmp);
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = pre_line.substring(f, l);
							ooo.add(tfe_id);

							pw1.write(line);
							pw1.newLine();
						} else if (line.contains("<hr")) {
							f = pre_line.indexOf("TFE");
							l = pre_line.indexOf("nest", f) - 1;
							if (f < 0 || l < 0) {
								pre_line = line;
								pw1.write(line);
								pw1.newLine();
								//line_tmp += line;
								continue;
							}
							tfe_id = pre_line.substring(f, l);
							qqq.add(tfe_id);
							pw1.write(line);
							pw1.newLine();
						} else {
							pw1.write(line);
							pw1.newLine();
						}
						pre_line = line;
						//line_tmp += line;
					}

					pw1.close();

					Set<String> set = new HashSet<>(ooo);
					ArrayList<String> list2 = new ArrayList<>(set);

					for (int i = 0; i < list2.size(); i++) {
						ppp.add(list2.get(i).replace("TFE", ""));
					}
					Collections.sort(ppp);

					Set<String> set2 = new HashSet<>(qqq);
					ArrayList<String> list3 = new ArrayList<>(set2);

					for (int i = 0; i < list3.size(); i++) {
						rrr.add(list3.get(i).replace("TFE", ""));
					}
					Collections.sort(rrr);
					//System.out.println(rrr);

					Path sourcePath = Paths.get(tmpFile);
					Path destinationPath = Paths.get(html_file);

					try {
						Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}

					File newdir = new File(tmpFile);
					newdir.delete();

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				//System.exit(-1); // 0 以外は異常終了
			} catch (IOException e) {
				e.printStackTrace();
				//System.exit(-1);
			}
		}

	}
}
