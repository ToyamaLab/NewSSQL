package supersql;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.common.LogError;
import supersql.common.LogInfo;
import supersql.common.Ssedit;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;
import supersql.parser.Preprocessor;
import supersql.parser.Start_Parse;

public class FrontEnd {

	public final static String VERSION = "2.2.0_73";

	public static Start_Parse parser;
	public static long start = 0;
	public static long afterparser = 0;
	public static long afterdc;
	public static long aftercg;
	public static long aftersql;

	public static void main(String[] args) {
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

		if (GlobalEnv.getErrFlag() != 0 && GlobalEnv.getOnlineFlag() == 0)
			System.exit(-1);
		else
			return;
	}

	public static void execEmbedSuperSQL(String[] args, ExtList execFile_list) {
		GlobalEnv.setGlobalEnv(args);
		if(GlobalEnv.versionProcess())	return;	//added by goto 170612  for --version

		parser = new Start_Parse(); //read file & parse query
		Log.out("tfe_tree:"+parser.list_tfe);
		if (GlobalEnv.isCheckquery()){
			if (GlobalEnv.getErrFlag() == 0)
				Log.info("// Parser completed normally //");
			return;
		}

		//20210924 yama
		Preprocessor.setExecFileList(execFile_list);

		if (GlobalEnv.getErrFlag() == 0) {
			CodeGenerator codegenerator = parser.getcodegenerator();
			codegenerator.CodeGenerator(parser);

			DataConstructor dc = new DataConstructor(parser);
			codegenerator.generateCode(parser, dc.getData());
			Responsive.process(codegenerator, parser, dc.getData());	//added by goto 20161217  for responsive
		}

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

		if (GlobalEnv.getErrFlag() != 0 && GlobalEnv.getOnlineFlag() == 0)
			System.exit(-1);
		else
			return;
	}

}
