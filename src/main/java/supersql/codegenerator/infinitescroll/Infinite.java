package supersql.codegenerator.infinitescroll;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import supersql.codegenerator.DecorateList;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.TFE;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G1;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G2;
import supersql.codegenerator.Responsive.Responsive;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

public class Infinite {

	public static int gLevel1 = 0;
	public static int gLevel0 = -1;
	public static ArrayList<Integer> whileCount = new ArrayList<>();

	public static int infinite_level = 0;
	public Infinite() {

	}

	//Process
	public static boolean preProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		//Pre-process (前処理)
		//		if(!symbol.contains("G1") && !symbol.contains("G2")){	//TODO disuse?
		//			Infinite_dynamic.dyamicPreStringProcess(symbol, decos, html_env);
		//		}
		if(symbol.contains("G1") || symbol.contains("G2")){
			gLevel0++;

			//			try {
			//				Log.e(gLevel0+" "+whileCount.get(gLevel0));
			//				//if(Infinite_dynamic.dynamicDisplay && (Infinite_dynamic.dynamicWhileCount0<1 || whileCount.get(gLevel0)==1)){
			//	        	//if(Infinite_dynamic.dynamicDisplay && (whileCount.get(gLevel0-1)==1 || whileCount.get(gLevel0)==1)){
			//				if(Infinite_dynamic.dynamicDisplay && gLevel0>0){
			//					Log.e(gLevel0+" "+whileCount.get(gLevel0)+" return false 1");
			//					//gLevel0--;
			//		        	return false;
			//		        }
			////				if(decos.containsKey("dynamic") && whileCount.get(gLevel0)>0){
			////					Log.e(gLevel0+" "+whileCount.get(gLevel0)+" return false 1");
			////					//gLevel0--;
			////		        	return false;
			////		        }
			//			} catch (Exception e) {
			//				Log.e(gLevel0+" 1 ?");
			//			}
		}

		//		Infinite_show.showProcess(decos, html_env);	//TODO この位置でOKか確認

		if(!symbol.contains("G1") && !symbol.contains("G2")){
			//			Infinite_form.formPreProcess(symbol, decos, html_env);
		}
		return true;
	}
	// taji added for infinite-scroll 170225
	public static boolean beforeWhileProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_String, String classid_for_ifs, StringBuffer tmp){
//		if(symbol.contains("G2")){
//			//			Infinite_form.G2 = true;
//		}
		if(symbol.contains("G1") || symbol.contains("G2")){
			if(infinite_level == 0 && !classid_for_ifs.equals("")){
				infinite_level++;
				Infinite_dynamic.dynamicPreProcess(symbol, decos, html_env, classid_for_ifs, tmp);
			}else if(infinite_level > 0 && !classid_for_ifs.equals("")){
				infinite_level++;
				Infinite_dynamic.dynamicPreProcess(symbol, decos, html_env, classid_for_ifs, tmp);
			}//todo taji 同じことしてる。
			
			if(gLevel0 == 0){
				
			}else if(Infinite_dynamic.dynamicDisplay && gLevel0 > 0){
				Infinite_dynamic.dyamicPreStringProcess(symbol, decos, html_env, ifs_div_String, tmp);
			}
			//	    	Infinite_dynamic.html_env_code_length = html_env.code.toString().length();	//未使用？
			//			Infinite_dynamic.dynamicPreProcess1(symbol, decos, html_env);
		}
		

		if(symbol.contains("G1") || symbol.contains("G2")){
			try {
				Infinite_dynamic.sindex.set(gLevel0, 0);	//sindex=0
			} catch (Exception e) {	}
			//Infinite_dynamic.sindex = 0;
			//	    	Infinite_dynamic.dynamicWhileString = "";
		}
		return true;
	}
	public static boolean whileProcess1_1(String symbol, DecorateList decos, Mobile_HTML5Env html_env, ExtList data, ExtList data_info, ITFE tfe, ExtList<TFE> tfes, int tfeItems){
		//Attribute: decos, html_env, data_info
		//C1, C2:    decos, html_env, data, data_info, tfe, tfes, tfeItems
		//G1, G2:    decos, html_env, data, data_info, tfe
		if(symbol.contains("G1") || symbol.contains("G2")){
			try {
				whileCount.set(gLevel0, whileCount.get(gLevel0)+1);	//whileCount[gLevel0]++
			} catch (Exception e) {
				whileCount.add(gLevel0, 1);							//whileCount[gLevel0]=1
			}

			if(Mobile_HTML5.gLevel1<1){
				Infinite_dynamic.dynamicWhileCount0++;
			}
		}
		return true;
	}
	public static boolean whileProcess1_2(String symbol, DecorateList decos, Mobile_HTML5Env html_env, ExtList data, ExtList data_info, ITFE tfe, ExtList<TFE> tfes, int tfeItems){
		//Attribute: decos, html_env, data_info
		//C1, C2:    decos, html_env, data, data_info, tfe, tfes, tfeItems
		//G1, G2:    decos, html_env, data, data_info, tfe
		if(symbol.contains("G1") || symbol.contains("G2")){
			//			if(Infinite_dynamic.dynamicDisplay && gLevel0 > 0){
			//				Infinite_dynamic.dyamicPreStringProcess(symbol, decos, html_env);
			//			}
		}
		return true;
	}
	public static boolean whileProcess2_1(String symbol, DecorateList decos, Mobile_HTML5Env html_env, ExtList data, ExtList data_info, ITFE tfe, ExtList<TFE> tfes, int tfeItems){
		//Attribute: decos, html_env, data_info
		//C1, C2:    decos, html_env, data, data_info, tfe, tfes, tfeItems
		//G1, G2:    decos, html_env, data, data_info, tfe

		if(symbol.contains("G1") || symbol.contains("G2")){
			//			if(Infinite_dynamic.dynamicDisplay && gLevel0 > 0){
			//				Infinite_dynamic.dyamicPostStringProcess(symbol, decos, html_env);
			//			}
		}

		return true;
	}
	public static boolean whileProcess2_2(String symbol, DecorateList decos, Mobile_HTML5Env html_env, ExtList data, ExtList data_info, ITFE tfe, ExtList<TFE> tfes, int tfeItems){
		//Attribute: decos, html_env, data_info
		//C1, C2:    decos, html_env, data, data_info, tfe, tfes, tfeItems
		//G1, G2:    decos, html_env, data, data_info, tfe
		if(symbol.contains("G1") || symbol.contains("G2")){

			if(Infinite_dynamic.dynamicDisplay && Mobile_HTML5.gLevel1<1){
				Infinite_dynamic.dynamicWhileCount0--;
			}
			//if(Infinite_dynamic.dynamicDisplay && Infinite_dynamic.dynamicWhileCount0<1){
			//if(Infinite_dynamic.dynamicDisplay && (Infinite_dynamic.dynamicWhileCount0<1 || whileCount.get(gLevel0)==1)){
			//if(Infinite_dynamic.dynamicDisplay && (whileCount.get(gLevel0-1)==1 || whileCount.get(gLevel0)==1)){
			if(Infinite_dynamic.dynamicDisplay && whileCount.get(gLevel0)>0){
				//Log.e(gLevel0+" "+whileCount.get(gLevel0)+" return false 2");
				return false;
			}

			//whileCount.set(gLevel0, whileCount.get(gLevel0)-1);	//whileCoun[gLevel0]--
		}
		return true;
	}
	// taji added for infinite-scroll 170225
	public static boolean afterWhileProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_string, String classid_for_ifs, StringBuffer tmp){
		//		Infinite_dynamic.dyamicAfterWhileStringProcess(symbol, decos, html_env);
		//		if(symbol.contains("G1") || symbol.contains("G2")){
		//			whileCount.set(gLevel0, 0);		//whileCount[gLevel0]=0
		//		}
		if(symbol.contains("G1") || symbol.contains("G2")){
			if(!classid_for_ifs.equals("")){
				infinite_level--;
				if(infinite_level > 0){
					Infinite_dynamic.dyamicPostStringProcess(symbol, decos, html_env, ifs_div_string, tmp);
				}
				Infinite_dynamic.dynamicStringGetProcess(symbol, decos, html_env, tmp);
				Infinite_dynamic.dyamicWhileStringProcess(symbol, decos, html_env);
				Infinite_dynamic.dynamicProcess(symbol, tfeID, decos, html_env, ifs_div_string, tmp);
			}else{
				if(Infinite_dynamic.dynamicDisplay && gLevel0 > 0){
					Infinite_dynamic.dyamicPostStringProcess(symbol, decos, html_env, ifs_div_string, tmp);
				}
			}
		}

		Mobile_HTML5Function.func_null_count = 0;	//null()
		if(symbol.contains("G2")){
			//			Infinite_form.G2 = false;
			//			Infinite_form.G2_dataQuantity = 0;
			//			Mobile_HTML5Function.G2_form_count = 0;
		}
		return true;
	}

	public static boolean postProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env){
		//Post-process (後処理)
		if(!symbol.contains("G1") && !symbol.contains("G2")){
			//			Infinite_form.formStringGetProcess(symbol, decos, html_env);
			//			Infinite_form.formProcess(symbol, decos, html_env);
		}

		//		Infinite_show.showCloseProcess(decos, html_env);

		//		Infinite_form.formString = "";
		Infinite_dynamic.dynamicString = "";
		if(symbol.contains("G1") || symbol.contains("G2")){
			//whileCount.set(gLevel0, 0);
			gLevel0--;
		}
		return true;
	}


	//taji added by 170223 for infinite-scroll
	public static String[] ifs_div_start(String symbol, Mobile_HTML5Env html_env, String tfeID, String[] ifs_div_String){
		if(symbol.contains("G1") || symbol.contains("G2")){
			ifs_div_String = Infinite_dynamic.ifsStringProcess(symbol, html_env, tfeID, ifs_div_String);
		}
		return ifs_div_String;

	}


	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////

	//check query
	public static String checkQuery(String query) {
		//contains $session() or not
		//ex)　WHERE s_id = $session(id)	->	WHERE s_id = id
		//大文字小文字の区別なし：先頭に(?i)
		if(query.contains(" FROM ") && query.contains(" WHERE ")){
			if(query.indexOf(" FROM ") < query.indexOf(" WHERE ")){
				//TODO " and ' の外側かどうかチェック
				query = query.replaceAll("(?i)\\$\\s*session\\s*\\(\\s*([A-Za-z0-9]+)\\s*\\)", "$1");
			}
		}

		return query;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	//get DIV width (C1,G1)
	public static String getDivWidth(String type, DecorateList decos, int numberOfColumns){
		//20131002
		String divWidthStr = "";
		if(type.equals("C1"))
			if(decos.containsKey("width")){
				divWidthStr = decos.getStr("width");
			}else{
				float divWidth = (float)Math.floor((double)(100.0/numberOfColumns)* 1000) / 1000;
				divWidthStr = divWidth+"%";
			}
		else if(type.equals("G1")){
			float divWidth = (float)Math.floor((double)(100.0/numberOfColumns)* 1000) / 1000;
			divWidthStr = divWidth+"%";
		}
		return divWidthStr;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	//return session_start string
	public static String getSessionStartString(){
		if(Start_Parse.sessionFlag){
			return "<?php\n" +
					"	session_start();\n" +
					//"	session_regenerate_id(TRUE);\n" +	//これがあると、phpファイルへのアクセスごとにセッションが切れる？
					"?>\n\n";
		}
		return "";
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	//create file
	public static boolean createFile(Mobile_HTML5Env html_env, String fileName, String code){
		if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
			try {
				PrintWriter pw;
				if (html_env.charset != null)
					pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName),html_env.charset)));
				else
					pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
				pw.println(code);
				pw.close();
				//Log.e(fileName+" created.");
				return true;
			} catch (Exception e) { }
		}
		return false;
	}

	//isNumber
	public static boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	//isTable
	public static boolean isTable() {
		if(Mobile_HTML5G1.tableFlg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.tableFlg || Mobile_HTML5G2.table0Flg){
			return true;
		}
		return false;
	}

}
