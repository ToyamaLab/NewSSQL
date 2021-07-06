package supersql.tasuku;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.common.LogInfo;
import supersql.common.Ssedit;
import supersql.dataconstructor.DataConstructor;
import supersql.parser.Start_Parse;

public class FrontEnd_Tasuku {

	public final static String VERSION = "2.2.0_73";

	public static Start_Parse parser;
	public static long start = 0;
	public static long afterparser = 0;
	public static long afterdc;
	public static long aftercg;
	public static long aftersql;

	//tasuku
	static Tasuku tasuku;

	public static String ssql = "";
	static String pre_css;

	static JFrame main_frame;

	static JFrame frame_first;
	static JPanel panel_first;
	static JScrollPane scrollPane1;
	static JScrollPane scrollPane2;
	static JTextArea textArea1;
	static JTextArea textArea2;
	static JButton btn_first_go;
	static JButton btn_first_add;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int w = screenSize.width;
	static int h = screenSize.height;
	static JCheckBox panel_option = new JCheckBox();


	static String filename;
	static String html_file;
	static String Preview_URL;

	public static void main(String[] args) {
		panel_option.setSelected(false);
		new FrontEnd_Tasuku(args);
	}

	public FrontEnd_Tasuku() {
	}

	public FrontEnd_Tasuku(String[] args) {

		execSuperSQL(args);
	}


	public static void execSuperSQL(String[] args) {

		try {

		start = System.currentTimeMillis();

		GlobalEnv.setGlobalEnv(args);
		//if (GlobalEnv.versionProcess())
		//return; //added by goto 170612  for --version

		Log.info("//Entering SuperSQL System//");

		parser = new Start_Parse(); //read file & parse query

		Log.out("tfe_tree:" + parser.list_tfe);
		if (GlobalEnv.isCheckquery()) {
			if (GlobalEnv.getErrFlag() == 0)
				Log.info("// Parser completed normally //");
			return;
		}

		afterparser = System.currentTimeMillis();
		afterdc = 0;
		aftercg = 0;
		aftersql = 0;

		if (GlobalEnv.getErrFlag() == 0) {
			CodeGenerator codegenerator = parser.getcodegenerator();
			if (GlobalEnv.getErrFlag() == 0) {
				codegenerator.CodeGenerator(parser);
				GlobalEnv.beforedc = System.currentTimeMillis();
				DataConstructor dc = new DataConstructor(parser);
				GlobalEnv.afterdc2 = System.currentTimeMillis();

				if (GlobalEnv.getErrFlag() == 0) {
					codegenerator.generateCode(parser, dc.getData());
					Responsive.process(codegenerator, parser, dc.getData()); //added by goto 20161217  for responsive
					aftercg = System.currentTimeMillis();

				}
			}
		}

		long end = System.currentTimeMillis();

		Log.info("ExecTime: " + (end - start) + "msec");


		GlobalEnv.queryInfo += GlobalEnv.getusername() + " | " + GlobalEnv.queryName + " | ";
		if (GlobalEnv.getErrFlag() == 0) {
			Ssedit.sseditInfo();
			Log.info("// completed normally //");
			LogInfo.logInfo(true);
		}

		}catch (Exception e) {
			//System.out.println(e);
			System.exit(-1);
		}


			//tasuku

		try {
			int n = 0;
			int m = 0;
			n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
			m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;
			filename = GlobalEnv.getfilename().substring(n, m);
			filename = filename.replace(".ssql", ".html");
			html_file = GlobalEnv.getOutputDirPath() + "/" + filename;
			Preview_URL = /*"file://" +*/ html_file ;
			pre_css = HTMLEnv.css.toString();

			ssql = HTMLwrite_ssql_embedded.Html_Embed();
			Tasuku.Html_tfe_add();

			T_parser.parser(ssql);

			tasuku = new Tasuku();

		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
		}


}
