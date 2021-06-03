package supersql.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Document;

public class Ssedit {

	public Ssedit() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	static String be = "";
	static String ot = "";
	static String ao = "";

	static String generateclause = "";
	static String fromclause = "";
	static String tfe = "";
	static String media = "";

	static String nquery = "";

	static String candidate1 = "";
	static String candidate2 = "";
	static ArrayList<Document> suggestarray = new ArrayList<Document>();
//	static ArrayList<String> suggestarray = new ArrayList<String>();

	static String suggestattributeFlag = "";
	static boolean escapeFlag = true;
	static boolean mediaFlag = false;

    //comboboxflag
    static boolean comboboxFlag = false;

    static String suggestlist = "";
    static String suggestlist2 = "";
    static String from_suggestlist = "";

    static String errorTableNameAlias2 = "";
    static String errorColumnName2 = "";
    static ArrayList<String> tableAlias2 = new ArrayList<String>();
    static ArrayList<String> columnNames2 = new ArrayList<String>();

    public static ArrayList<String> attributearray = new ArrayList<String>();

	public static ArrayList<String> infoarray = new ArrayList<String>();


	//いずれ消す
	public static void getOffending(String before_offending, String offendingtoken, String after_offending) {

		be = before_offending;
		ot = offendingtoken;
		ao = after_offending;
	}

	public static String getMedia_and_From(String query) {
		/////////////////////////////////////////////////////////////////////////////////////////////
		//generate MEDIA までの文字列
		//TODO mobile_html5など
		if (query.toLowerCase().contains("html")) {
			generateclause = query.substring(0, query.toLowerCase().indexOf("html") + 4);
			escapeFlag = false;
		}else{
			///GENERATE htnl [e.dept]! FROM employee e;
			if (query.toLowerCase().contains("generate") && !query.contains("{")) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("["));
//				System.err.println("a");
				escapeFlag = false;

				if (generateclause.toLowerCase().trim().equals("generate")) {
					mediaFlag = true;
				}
			}
			///GENERATE htnl { [e.dept, {e.floor! e.name}]! } FROM employee e;
			else if (query.toLowerCase().contains("generate") && (query.indexOf("{") < query.indexOf("["))) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("{"));
				escapeFlag = false;
//				System.err.println("b");

				if (generateclause.toLowerCase().trim().equals("generate")) {
					mediaFlag = true;
				}
			}
			///GENERATE htnl [e.dept, {e.floor! e.name}]! FROM employee e;
			else if (query.toLowerCase().contains("generate") && (query.indexOf("[") < query.indexOf("{"))) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("[")-1);
				escapeFlag = false;
//				System.err.println("c");

				if (generateclause.toLowerCase().trim().equals("generate")) {
					mediaFlag = true;
				}
			}
			///GENERAT htnl [e.dept]! FROM employee e;
			else if ((!query.toLowerCase().contains("generate") && !query.contains("{")) && !(query.length() == 0) && query.contains("[")) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("[")-1);
				escapeFlag = false;
//				System.err.println("d");
			}
			///GENERAT htnl { [e.dept, {e.floor! e.name}]! } FROM employee e;
			else if (!query.toLowerCase().contains("generate") && query.indexOf("{") < query.indexOf("[")) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("{"));
				escapeFlag = false;
//				System.err.println("e");
			}
			///GENERAT htnl [e.dept, {e.floor! e.name}]! FROM employee e;
			else if (!query.toLowerCase().contains("generate") && query.indexOf("[") < query.indexOf("{")) {
				generateclause = query.substring(0, query.toLowerCase().indexOf("[")-1);
				escapeFlag = false;
//				System.err.println("f");
			}

		}

		//from句
		if (query.toLowerCase().contains("from")) {
			fromclause = query.substring(query.toLowerCase().lastIndexOf("from"));
		}
		else {
			fromclause = "";
		}

		//TFE
		getTFE(query);

//		System.out.println(generateclause);
//		System.out.println("tfe is:" + tfe.trim());
//		System.out.println(fromclause);
//		System.out.println("tfe is: " +tfe);
		/////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////
		///GENERATE [e.dept]! FROM employee e; (メディアが何も書かれていない)の特別処理
		//mediaFlagによる判定
		if (mediaFlag == true) {
			tfe = "\n" + tfe;
//			correctMedia();
		}
		////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////////////////////////
		///TFEを{}で囲む
		if (escapeFlag == false) {
//			System.out.println("aaa: " + tfe.trim().substring(0,1));
//			System.out.println("bbb: " + tfe.trim().substring(tfe.length()-4, tfe.length()-3));

//			if (!tfe.trim().substring(0,1).equals("{") && !tfe.trim().substring(tfe.length()-4, tfe.length()-3).equals("}")) {
			if (!tfe.trim().substring(0,1).equals("{")) {
				tfe = "\n" + "{" + "\n" + tfe.trim() + "\n" + "}" + "\n";
			}
		}

		nquery = generateclause + tfe + fromclause;

		/////////////////////////////////////////////////////////////////////////////////////////////


		//いずれ消す
//		System.out.println("query is: " + nquery);
//		System.out.println("generate is: " + generateclause);
//		System.out.println("tfe is: " + tfe);
//		System.out.println("from is: " + fromclause);

		sseditInfo("?query is:" + nquery + "?");
		sseditInfo("?generate is:" + generateclause + "?");
		sseditInfo("?tfe is:" + tfe + "?");
		sseditInfo("?from is:" + fromclause + "?");

		return nquery;
	}

	public static void getTFE(String query) {
		if (!fromclause.isEmpty()) {
			tfe = query.substring(generateclause.length(), query.indexOf(fromclause));
		}
		else {
			tfe = query.substring(generateclause.length());
		}
	}


//	//error in media
//	public static void correctMedia() {
//		suggestarray.add(SuggestAttribute.getsuggestAttribute("GENERATE", tfe, fromclause, "media"));
//		AutoCorrect3.sethintcontentLabel("メディアを正しく指定してください。");
//	}

//	//error in generate(パーズ前の処理)
//	public static void correctGenerate(String query) {
//
//		if((!query.toLowerCase().contains("generate") && !query.toLowerCase().contains("from")) || query == null) {
//			suggestarray.add(SuggestAttribute.getsuggestAttribute("", "HTML", "{\n\n} FROM", "GENERATE"));
//			AutoCorrect3.sethintcontentLabel("クエリにGENERATE句やFROM句が見つかりません。");
//		}
//		else if(!query.toLowerCase().contains("generate") && query.toLowerCase().contains("from")) {
//			suggestarray.add(SuggestAttribute.getsuggestAttribute("", "HTML\n", tfe + fromclause, "GENERATE"));
//			AutoCorrect3.sethintcontentLabel("GENERATE句に誤りがあります。");
//		}
//	}

	//エラーごとのアルゴリズム(SQLパーザ)
	public static void AutocorrectAlgorirhm_SQL(String errorTableNameAlias, String errorColumnName, ArrayList<String> tableAlias, ArrayList<String> columnNames, String flag) throws IOException {

		//1)error in table alias
		if (flag.equals("alias")) {

			//TODO なぜか")"がerrorColumnNameの末尾に入っている
			if (errorColumnName.contains(")")) {
				errorColumnName = errorColumnName.replace(")", "");
			}
			String error = errorTableNameAlias + "." + errorColumnName;
			String ans = Suggest.getAns(errorTableNameAlias, tableAlias);

			String be = nquery.substring(0, nquery.indexOf(error));
			String ao = nquery.substring(be.length() + errorTableNameAlias.length());

//			if (be.substring(be.length()-1).equals("[") || be.substring(be.length()-1).equals("(") || be.substring(be.length()-1).equals("{")) {
//				suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", ans));
//			}
//			else{
//				suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", " " + ans));
//			}

			sseditInfo("?be_1a is:" + be + "?");
			sseditInfo("?ans_1a is:" + ans + "?");
			sseditInfo("?ao_1a is:" + ao + "?");
//
//			AutoCorrect3.sethintcontentLabel("エイリアスに誤りがあります。");
		}
		//2)error in table column
		else if (flag.equals("column")) {

			String ans = Suggest.getAns(errorColumnName, columnNames);
			//①ansが？？の場合(似たものがサジェストされない場合)
			if (ans.equals("？？")) {

				//AutoCorrect3でコンボボックスを生成
				comboboxFlag = true;

				//TODO なぜか")"がerrorColumnNameの末尾に入っている
				if (errorColumnName.contains(")")) {
					errorColumnName = errorColumnName.replace(")", "");
				}

				String error = errorTableNameAlias + "." + errorColumnName;

				be = nquery.substring(0, nquery.indexOf(error) + errorTableNameAlias.length()+1);
				ao = nquery.substring(be.length() + errorColumnName.length());

//				suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "column", ans));
//
//				AutoCorrect3.sethintcontentLabel("カラム名に誤りがあります。右側のカラムリストから選択してください。");

				sseditInfo("?be_cmb is:" + be + "?");
				sseditInfo("?ans_cmb is:" + ans + "?");
				sseditInfo("?ao_cmb is:" + ao + "?");

				sseditInfo("?suggestlist is:" + getSuggestlist() + "?");

				//for 2回目
				errorTableNameAlias2 = errorTableNameAlias;
				errorColumnName2 = errorColumnName;
				tableAlias2 = tableAlias;
				columnNames2 = columnNames;

			}
			//②ansが存在する場合
			else {

				//TODO なぜか")"がerrorColumnNameの末尾に入っている
				if (errorColumnName.contains(")")) {
					errorColumnName = errorColumnName.replace(")", "");
				}

				String error = errorTableNameAlias + "." + errorColumnName;

				String be = nquery.substring(0, nquery.indexOf(error) + errorTableNameAlias.length()+1);
				String ao = nquery.substring(be.length() + errorColumnName.length());

//				suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "column", ans));
//
//				AutoCorrect3.sethintcontentLabel("カラム名に誤りがあります");

				sseditInfo("?be_sgg is:" + be + "?");
				sseditInfo("?ans_sgg is:" + ans + "?");
				sseditInfo("?ao_sgg is:" + ao + "?");

			}
		}
//		3)2回目入ってきたときの特別処理
		else if (flag.equals("column2")) {

			String[] columnlist = suggestlist2.split("\n", 0);

			if (!tableAlias2.isEmpty()) {

				for (int i=0;i<columnlist.length;i++) {
					if (columnlist[i].indexOf(errorColumnName2 + ",") >= 0) {
						String ans = tableAlias2.get(i);

						String error = errorTableNameAlias2 + "." + errorColumnName2;
						String be = nquery.substring(0, nquery.indexOf(error));
						String ao = nquery.substring(be.length() + errorTableNameAlias2.length());

//						if (be.substring(be.length()-1).equals("[") || be.substring(be.length()-1).equals("(") || be.substring(be.length()-1).equals("{")) {
//							suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", ans));
//						}
//						else{
//							suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", " " + ans));
//						}
						sseditInfo("?be_2a" + i + " is:" + be + "?");
						sseditInfo("?ans_2a" + i + " is:" + ans + "?");
						sseditInfo("?ao_2a" + i + " is:" + ao + "?");
					}
					else if (columnlist[i].indexOf(errorColumnName2 + ")") >= 0) {
						String ans = tableAlias2.get(i);

						String error = errorTableNameAlias2 + "." + errorColumnName2;
						String be = nquery.substring(0, nquery.indexOf(error));
						String ao = nquery.substring(be.length() + errorTableNameAlias2.length());

//						if (be.substring(be.length()-1).equals("[") || be.substring(be.length()-1).equals("(") || be.substring(be.length()-1).equals("{")) {
//							suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", ans));
//						}
//						else{
//							suggestarray.add(SuggestAttribute.getsuggestAttribute_SQL(be, null, ao, "alias", " " + ans));
//						}
						sseditInfo("?be_2a" + i + " is:" + be + "?");
						sseditInfo("?ans_2a" + i + " is:" + ans + "?");
						sseditInfo("?ao_2a" + i + " is:" + ao + "?");
					}
				}
			}

//			AutoCorrect3.sethintcontentLabel("カラム名に誤りがあります。もしくはエイリアスに誤りがあります。");

		}
		//4)FROM句
		else if (flag.equals("from")) {
			String tName = errorTableNameAlias;
			ArrayList<String> tNames = tableAlias;

			String ans = Suggest.getAns(tName, tNames);

			//ansが存在しない(似ているテーブルがない)
			if (ans.equals("？？")) {

				//TODO なぜか")"がerrorColumnNameの末尾に入っている
				if (tName.contains(")")) {
					tName = tName.replace(")", "");
				}

				String be = nquery.substring(0, nquery.indexOf(tName));
				String ao = nquery.substring(nquery.indexOf(tName) + tName.length());

				sseditInfo("?be_fcm is:" + be + "?");
				sseditInfo("?ans_fcm is:" + ans + "?");
				sseditInfo("?ao_fcm is:" + ao + "?");

				sseditInfo("?suggestlist is:" + from_suggestlist + "?");
			}
			//ansが存在する(似ているテーブルがある)
			else {

				//TODO なぜか")"がerrorColumnNameの末尾に入っている
				if (tName.contains(")")) {
					tName = tName.replace(")", "");
				}

				String be = nquery.substring(0, nquery.indexOf(tName));
				String ao = nquery.substring(nquery.indexOf(tName) + tName.length());

				sseditInfo("?be_fsg is:" + be + "?");
				sseditInfo("?ans_fsg is:" + ans + "?");
				sseditInfo("?ao_fsg is:" + ao + "?");
			}

		}
	}


	//1107
	public static int bracketcount(String bbe) {
		int count = 0;

		for (int i=0; i<bbe.length(); i++) {
			if (bbe.charAt(i) == '{') {
				count++;
			}
		}
		return count;
	}

	//161013
	public static ArrayList<Document> getSuggestarray() {
		return suggestarray;
	}
	//161023
//	public static void openAutocorrect() {
//		AutoCorrect3 autocorrect = new AutoCorrect3();
//	}
	//1029
	public static boolean getComboboxflag() {
		return comboboxFlag;
	}

	//1105
	public static void getattribute(String name) {
		 attributearray.add(name);
	}

	public static String getSuggestlist(String list) {
		suggestlist = list;
		return suggestlist;
	}
	public static String getSuggestlist(String list, int id) {
		suggestlist2 = list;
		return suggestlist2;
	}
	public static String getfromSuggestlist(String list) {
		from_suggestlist = list;
		return from_suggestlist;
	}
	public static String getSuggestlist() {
		return suggestlist;
	}

	//161109
	public static void sseditInfo(String info) {
		infoarray.add(info);

//		Log.err(info);
	}

	//161110
	public static void sseditInfo() {
		for (int i=0; i<infoarray.size(); i++) {
			Log.err(infoarray.get(i));
		}
	}

	//161206
	public static String getautocorrectValue() {

		return "autocorrect=" + ((GlobalEnv.isSsedit_autocorrect())? "on" : "off");
	}
}

