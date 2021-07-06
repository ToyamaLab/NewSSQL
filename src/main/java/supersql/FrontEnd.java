package supersql;

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
import supersql.common.LogError;
import supersql.common.LogInfo;
import supersql.common.Ssedit;
import supersql.dataconstructor.DataConstructor;
import supersql.parser.Start_Parse;
import supersql.tasuku.HTMLwrite_ssql_embedded;
import supersql.tasuku.T_parser;
import supersql.tasuku.Tasuku;

public class FrontEnd {

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
		public static String pre_css;

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
		public static String html_file;
		public static String Preview_URL;

	public static void main(String[] args) {
		panel_option.setSelected(false);
		new FrontEnd(args);
	}

	public FrontEnd() {

	}
	public FrontEnd(String[] args) {
		execSuperSQL(args);
	}

	public void execSuperSQL(String[] args) {
		start = System.currentTimeMillis();
		//Log.info("0");

		GlobalEnv.setGlobalEnv(args);
		if(GlobalEnv.versionProcess())	return;	//added by goto 170612  for --version

		Log.info("//Entering SuperSQL System//");
//		Log.info("1");

		parser = new Start_Parse(); //read file & parse query
		Log.out("tfe_tree:"+parser.list_tfe);
		if (GlobalEnv.isCheckquery()){
			if (GlobalEnv.getErrFlag() == 0)
				Log.info("// Parser completed normally //");
			return;
		}
//		Log.info("2");

		afterparser = System.currentTimeMillis();
		afterdc = 0;
		aftercg = 0;
		aftersql = 0;

		if (GlobalEnv.getErrFlag() == 0) {
//			Log.info("2-1");
			CodeGenerator codegenerator = parser.getcodegenerator();
//			Log.info("2-2");
			if (GlobalEnv.getErrFlag() == 0) {
//				Log.info("2-2-1");
				codegenerator.CodeGenerator(parser);
//				Log.info("2-2-2");
				GlobalEnv.beforedc = System.currentTimeMillis();
				DataConstructor dc = new DataConstructor(parser);
				GlobalEnv.afterdc2 = System.currentTimeMillis();
//				Log.info("MakeSch time : " + (GlobalEnv.afterMakeSch - GlobalEnv.beforedc) + "ms");
//				Log.info("MakeSQL time : " + (GlobalEnv.afterMakeSQL - GlobalEnv.beforeMakeSQL) + "ms");
//				Log.info("GetFromDB time : " + (GlobalEnv.afterGetFromDB - GlobalEnv.beforeGetFromDB) + "ms");
//				Log.info("MakeTree time : " + (GlobalEnv.afterMakeTree - GlobalEnv.beforeMakeTree) + "ms");
//				Log.info("DataConstruct Time : " + (GlobalEnv.afterdc2 - GlobalEnv.beforedc) + "ms");
//				System.exit(0);
//				Log.info("2-2-3");
				if (GlobalEnv.getErrFlag() == 0) {
//					Log.info("2-2-3-1");
					codegenerator.generateCode(parser, dc.getData());
//					Log.info("2-2-3-2");
			        Responsive.process(codegenerator, parser, dc.getData());	//added by goto 20161217  for responsive
//					Log.info("2-2-3-3");
					aftercg = System.currentTimeMillis();
				}
			}
//			Log.info("2-3");
		}
//		Log.info("3");

		long end = System.currentTimeMillis();
//		Log.info("Parsing Time : " + (afterparser - start) + "msec");
//		Log.info("Data construction Time : "+ (afterdc - afterparser) + "msec");
//		Log.info("Code generation Time : " + (aftercg - afterdc) + "msec");
		Log.info("ExecTime: " + (end - start) + "msec");

		// eHTML
//		Log.ehtmlInfo("Parsing Time : " + (afterparser - start) + "msec<br>");
//		Log.ehtmlInfo("Data construction Time : " + (afterdc - afterparser) + "msec<br>");
//		Log.ehtmlInfo("Code generation Time : " + (aftercg - afterdc) + "msec<br>");
//		Log.ehtmlInfo("ExecTime: " + (end - start) + "msec<br>");

		GlobalEnv.queryInfo += GlobalEnv.getusername() + " | " + GlobalEnv.queryName +  " | ";
		if (GlobalEnv.getErrFlag() == 0){
			Ssedit.sseditInfo();
			Log.info("// completed normally //");
			LogInfo.logInfo(true);
		} else {
			LogError.logErr();
			if (GlobalEnv.isSsedit_autocorrect()) {
				Ssedit.sseditInfo();
			}
		}

		if (GlobalEnv.isSSstyle()) {
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
	
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		if (GlobalEnv.getErrFlag() != 0 && GlobalEnv.getOnlineFlag() == 0)
			System.exit(-1);
		else
			return;
	}

}
