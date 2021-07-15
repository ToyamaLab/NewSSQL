package supersql.codegenerator.HTML;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.LocalEnv;
import supersql.codegenerator.Modifier;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.parser.Start_Parse;

public class HTMLEnv extends LocalEnv implements Serializable{
	static String formPartsName = null;
	private static boolean charsetFlg = false; // added by goto 20120715
	private static String checked = "";
	private static String exchange_form_name = new String();
	private static String[] formDetail = new String[256];
	private static String formItemName;
	// global form number : 1,2,3...
	private static int formNumber = 1;
	private static String formValueString;
	private static String IDUst = new String();
	private static boolean isFormItem;
	private static boolean search = false;
	private static String selected = "";
	private static boolean selectFlg;
	private static boolean selectRepeat;
	protected static int IDCounter = 0; // add oka
	protected static int IDOld = 0; // add oka
	public static String cond = "";
	public static String bg = "";
	public static String bgsize = ""; //add ayumi 20210714
	public static boolean bgrepeat = false;//add ayumi 20210714
	public static String bgcolor = "";
	public static String pos = "";
	public ArrayList<ArrayList<String>> decorationProperty = new ArrayList<ArrayList<String>>();
	public ArrayList<Boolean> decorationStartFlag = new ArrayList<Boolean>();
	public ArrayList<Boolean> decorationEndFlag = new ArrayList<Boolean>();
	// added by masato 20151202
	public static boolean defaultCssFlag = true;
	// added by masato 20151214 for paging
	public static int itemNumPerPage = 0;
	public static int itemCount = 0;
	// added by masato 20151214 for paging
	public static int g1RetNum = 0;
	public static int g2RetNum = 0;
	public static int g1PaginationRowNum = 0;
	public static int g1PaginationColumnNum = 0;
	public static int g2PaginationRowNum = 0;
	public static int g2PaginationColumnNum = 0;


	public static String condName = "";
	// global form item number : t1,t2,t3...
	public static int formPartsNumber = 1;
	public static String nameId = "";
	public static int searchId = 0;


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//20210416  new table border
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// new & old table
	private static int currnt_nestDepth = 0;	//G1, G2のネストの深さ
	private static int new_table_start_nestDepth = -1;		//start ネスト位置の保存用, end実行判定に使用
	private static int old_table_start_nestDepth = -1;		//start ネスト位置の保存用, end実行判定に使用
	private static String new_table_start_symbol = null;	//start symbolの保存用, end実行判定に使用
	private static String old_table_start_symbol = null;	//start symbolの保存用, end実行判定に使用
	private static boolean isUseOldTable = GlobalEnv.isUseOldTable();	//旧tableborderを使うかどうか
	public static void start_table(String symbol, DecorateList decos, HTMLEnv html_env) {
		if (symbol.endsWith("G1") || symbol.endsWith("G2")) {
			currnt_nestDepth++;
			//System.out.println(symbol+" start "+currnt_nestDepth);
		}

		//System.out.println("isUseOldTable = "+isUseOldTable);

		if (decos.containsKey("border1") || decos.containsKey("tableborder1") ||
			decos.containsKey("old_table") || decos.containsKey("old_border") || decos.containsKey("old_tableborder")) {
			decos.put("table1", "");
		}

		if (decos.containsKey("debug")) {		 //20210416
			String d = decos.getStr("debug").toLowerCase().trim();
			String[] ds = d.split("\\s*,\\s*");
			//System.out.println("debug = "+Arrays.toString(ds));
			if (Arrays.asList(ds).contains("on")) {
//				if (!isUseOldTable) {	//new table
//					System.out.println("new table");
					if (Arrays.asList(ds).contains("table1") || isUseOldTable) {	//@table1
						//System.out.println("table1");
						decos.put("table1", "");
					} else {				//@table		//default
						//System.out.println("table (debug)");
						decos.put("table", "");
					}
//				} else {				//old table
//					System.out.println("old table");
//					System.out.println("table1");
//					decos.put("table1", "");
//				}

			}
		} else if (decos.containsKey("border") || decos.containsKey("tableborder")) {
			if (!decos.containsKey("table") && !decos.containsKey("table1")) {
				if (!isUseOldTable) {	//new table
					//System.out.println("table (border)");
					decos.put("table", "");		//@table		//default
				} else {				//old table
					//System.out.println("table1 (border)");
					decos.put("table1", "");
				}
			}
		}

		if (decos.containsKey("table")) {
		//if (decos.containsKey("table") || decos.containsKey("new_table")) {
			//System.out.println("table");
			new_table_start_symbol = symbol;
			new_table_start_nestDepth = currnt_nestDepth;		//TODO { { }@{table1} }@{table} のためには, new/oldそれぞれ別々に保存する必要がある？
			HTMLEnv.start_new_table(decos, html_env);
		} else if (decos.containsKey("table1")) {
			//System.out.println("table1");						//TODO { { }@{table1} }@{table} のためには, new/oldそれぞれ別々に保存する必要がある？
			old_table_start_symbol = symbol;
			old_table_start_nestDepth = currnt_nestDepth;
			HTMLEnv.start_old_table(decos, html_env);
		}
	}
	public static void end_table(String symbol) {
//		System.out.println("end_table "+nestDepth);
		end_new_table(symbol);
		end_old_table(symbol);
		//tableBorder = "0";					//TODO ok? -> TFE全体にtableborder/borderが指定されていない場合はok?

		if (symbol.endsWith("G1") || symbol.endsWith("G2")) {
			//System.out.println(symbol+" end "+currnt_nestDepth);
			currnt_nestDepth--;
		}
	}


	// new table
	public static boolean isNewTableBorder = false;		//20210416  new table border	//TODO 実行引数や装飾子でfalseにする処理
	private static boolean insideTable = false;			//20210416  new table border
	private static boolean newTableBorderDIV_startFlag = false;	//foreach TFE全体@table でdivが閉じられない問題対策用    TODO 他の方法の検討
	private static int newTableBorderNum = 10001;
	public static String getNewTableBorderDIV_start() {
		if (!insideTable && isNewTableBorder) {
//		if (!insideTable && (isNewTableBorder || isOldTableBorder))
//			System.out.println("getNewTableBorderDIV_start");
			newTableBorderDIV_startFlag = true;
			return "<div id=\""+getCurrentNewTableBorderID()+"\">";
		}
		return "";
	}
	public static String getNewTableBorderStyle() {				//20210416  new table border
		if (!insideTable && isNewTableBorder) {
//		if (!insideTable && (isNewTableBorder || isOldTableBorder)) {
			//insideTable = true;
			//if (isNewTableBorder)
			//	return " style=\"border-style:solid;\" ";
			insideTable = true;
			if (isNewTableBorder)
				return " style=\"border-style:solid;\" ";
			// id="T10001"  style="border-style:solid;"
//			return " id=\""+getCurrentNewTableBorderID()+"\" style=\"border-style:solid;\" ";
		}
		return "";
	}
	public static String getNewTableBorderDIV_end(String symbol) {
	//public static String getNewTableBorderDIV_end() {
		//System.out.println("!! "+!insideTable +" "+ isNewTableBorder);
		if (!insideTable && isNewTableBorder) {
		//if (insideTable && isNewTableBorder) {
//		if (symbol.equals(new_table_start_symbol) && currnt_nestDepth == new_table_start_nestDepth && isNewTableBorder) {
//		if (!insideTable && (isNewTableBorder || isOldTableBorder))
//			System.out.println("getNewTableBorderDIV_end: "+symbol);
			newTableBorderDIV_startFlag = false;
			return getNewTableBorderDIV_endStr();
		}
		return "";
	}
	public static String getNewTableBorderDIV_end_C3G3() {	//C3(%), G3(foreach)から呼ばれる
		if (newTableBorderDIV_startFlag) {
			newTableBorderDIV_startFlag = false;
			return getNewTableBorderDIV_endStr();
		}
		return "";
	}
	public static String getNewTableBorderDIV_endStr() {
		return "</div>";
	}
//	public static String getNewTableBorderTableStyle(DecorateList decos) {
//		String s = "";
//		if (decos.containsKey("style")){
//			s = decos.getStr("style");
//			if (!s.trim().endsWith(";"))	s += ";";
//			s += " ";
//		}
//		return " style=\""+s+"border:0;\" ";
//	}
//	public static String getNewTableBorderDIV_start2() {	//[重要]セルの途中で折返しがあった場合でも縦線を途切れさせないため  CSSと連携 -> おそらく不要
////		if (isNewTableBorder && insideTable)
////			return "<div>";
//		return "";
//	}
//	public static String getNewTableBorderDIV_end2() {		//[重要]セルの途中で折返しがあった場合でも縦線を途切れさせないため  CSSと連携 -> おそらく不要
////		if (isNewTableBorder && insideTable)
////			return "</div>";
//		return "";
//	}
	private static String getCurrentNewTableBorderID() {
		return "ssqlTable"+newTableBorderNum;
//		String r = "ssqlTable"+newTableBorderNum;
//		newTableBorderNum++;
//		return r;
	}
	private static void start_new_table(DecorateList decos, HTMLEnv html_env) {
		isNewTableBorder = true;
		if (decos.containsKey("tableborder"))
			tableBorder = decos.getStr("tableborder");
		else if (decos.containsKey("border"))
			tableBorder = decos.getStr("border");
		else
			tableBorder = "1";					//TODO ok?
		html_env.css.append(HTMLEnv.getNewTableBorderCSS());
		//System.out.println("start_new_table "+currnt_nestDepth);
	}
	private static void end_new_table(String symbol) {
		if (symbol.equals(new_table_start_symbol) && currnt_nestDepth == new_table_start_nestDepth && isNewTableBorder) {
			isNewTableBorder = false;
			insideTable = false;
			newTableBorderNum ++;
			tableBorder = "0";
			new_table_start_nestDepth = -1;
			//System.out.println("end_new_table "+currnt_nestDepth);
		}
	}
	private static String getNewTableBorderCSS() {
		return getNewTableBorderCSS(true);
	}
	private static String getNewTableBorderCSS(boolean withID) {
		if (isNewTableBorder) {
//		if (isNewTableBorder || isOldTableBorder) {
			// TODO  線colorの取得			//idで適用範囲を制限, 線サイズの取得
			String id = "";
			if(withID)	id = "#"+getCurrentNewTableBorderID()+" ";
			if (isNewTableBorder) {
				//newTableBorderNum++;
				return "\n"+
					   id+"table, "+id+"tr, "+id+"td { border:"+tableBorder+"px solid black; }\n" +
					   id+"table { border-collapse:collapse; border-style:hidden; border-spacing:0; height:100%; }\n\n";//" +		//TODO border-spacing:0は不要？
//				       id+"tr, "+id+"td { border-collapse:collapse; }\n\n";		//不要？
				       //id+"td { padding:0; height:100%; }\n" +				//おそらく不要
					   //id+"td div { height:100%; display:flex; justify-content:center; flex-direction:column; }\n\n";		//[重要]セルの途中で折返しがあった場合でも縦線を途切れさせないため -> おそらく不要
				//#ssqlTable10001 table:not(.att) { border:1px solid black; }
				//s += "tr, td { border:solid; border-collapse:collapse; }\n\n";
				//s += "td { empty-cells:hide; }\n";
				//s += "th:empty, td:empty { border-collapse:collapse; border:0; visibility:hidden; }\n\n";
			}
//			else if (HTMLEnv.isOldTableBorder) {
//				return "\n"+
//					   id+"table, "+id+"tr, "+id+"td { border:"+tableBorder+"px solid black; }\n\n";
////				return "\n";
//			}
		}
		return "";
	}
	public static boolean isInsideTable() {
		return insideTable;
	}

	//old table
	private static boolean isOldTableBorder = false;
	private static void start_old_table(DecorateList decos, HTMLEnv html_env) {
		isOldTableBorder = true;
		if (decos.containsKey("tableborder"))
			tableBorder = decos.getStr("tableborder");
		else if (decos.containsKey("border"))
			tableBorder = decos.getStr("border");
		else
			tableBorder = "1";					//TODO ok?
		//html_env.css.append(HTMLEnv.getNewTableBorderCSS());
		//System.out.println("start_old_table "+currnt_nestDepth);
	}
	private static void end_old_table(String symbol) {
		if (symbol.equals(old_table_start_symbol) && currnt_nestDepth == old_table_start_nestDepth && isOldTableBorder) {
			isOldTableBorder = false;
			//insideTable = false;
			//newTableBorderNum ++;
			tableBorder = "0";
			old_table_start_nestDepth = -1;
			//System.out.println("end_old_table "+currnt_nestDepth);
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public static void computeConditionalDecorations(DecorateList decos,
			StringBuffer css) {
		Object decorationKeys;
		Object decorationValue;
		String decorationKey;
		String condition;
		String className;
		for (Entry<String, Object> conditions : decos.getConditions()
				.entrySet()) {
			decorationKeys = conditions.getValue();
			condition = conditions.getKey();

			className = "C_" + decos.getClassesIds().get(condition);
			css.append("." + className + "{");

			if (decorationKeys instanceof String) {
				decorationKey = (String) decorationKeys;
				decorationValue = decos
						.getDecorationValueFromDecorationKeyAndCondition(
								decorationKey, condition);
				css.append(decorationKey + " : " + decorationValue + ";");
			} else {
				Iterator<String> decorationKeysIterator = ((ArrayList<String>) (decorationKeys))
						.iterator();
				while (decorationKeysIterator.hasNext()) {
					decorationKey = decorationKeysIterator.next();
					decorationValue = decos
							.getDecorationValueFromDecorationKeyAndCondition(
									decorationKey, condition);
					css.append(decorationKey + " : " + decorationValue + ";");
				}
			}

			css.append("}");
		}
	}

	public static void exFormName() {
		String s = "t" + formPartsNumber + ":" + formPartsName + ":";
		if (exchange_form_name == null || exchange_form_name.isEmpty()) {
			exchange_form_name = ":" + s;
		} else {
			if (!exchange_form_name.contains(s))
				exchange_form_name += s;
		}
	}

	public static String exFormNameCreate() {
		String ret = new String();
		if (exchange_form_name != null) {
			ret = "<input type=\"hidden\" name=\"exchangeName\" value=\""
					+ exchange_form_name + "\" />";
			setFormDetail(ret);
			return ret;
		} else {
			return null;
		}
	}

	public static String getChecked() {
		return checked;
	}

	public static String getClassID(ITFE tfe) {
		String result;
		if (tfe instanceof HTMLC3) {
			result = getClassID((((HTMLC3) tfe).tfes.get(0)));
			return result;
		}
		if (tfe instanceof HTMLG3) {
			result = getClassID((((HTMLG3) tfe).tfe));
			return result;
		}

		result = "TFE" + tfe.getId();
//		Ehtml.tfe_id = result;
		return result;
	}

	/*** start oka ***/
	public static String getDataID(ITFE tfe) {
		String ClassID;
		int DataID = 0;
		String return_value;

		if (tfe instanceof HTMLC3) {
			return getClassID((((HTMLC3) tfe).tfes.get(0)));
		}
		if (tfe instanceof HTMLG3) {
			return getClassID((((HTMLG3) tfe).tfe));
		}
		ClassID = String.valueOf(tfe.getId());
		DataID = Integer.valueOf(
				(ClassID.substring(ClassID.length() - 3, ClassID.length())))
				.intValue();

		Log.out("ClassID=" + ClassID);
		Log.out("DataID=" + DataID);
		Log.out("ID_counter=" + IDCounter);

		if (DataID < IDOld) {
			IDCounter = DataID;
		} else {
			if (DataID != IDCounter && DataID > IDCounter) {
				DataID = IDCounter;
			}
		}
		IDCounter++;
		IDOld = DataID;
		return_value = String.valueOf(DataID);
		return return_value;
	}

	public static String getFormDetail(int i) {
		return formDetail[i];
	}

	public static boolean getFormItemFlg() {
		return isFormItem;
	}

	public static String getFormItemName() {
		if (formItemName == null) {
			return "0";
		}
		return formItemName;
	}

	public static String getFormName() {
		// return formNumber f1,f2,f3...
		return "f" + formNumber;
	}

	public static int getFormNumber() {
		// return formNumber 1,2,3...
		return formNumber;
	}

	public static String getFormPartsName() {
		if (formPartsName == null) {
			return "t" + formPartsNumber;
		} else {
			return formPartsName;
		}
	}

	public static String getFormValueString() {
		return formValueString;
	}

	public static String getIDU() {
		return IDUst;
	}

	public static String getNameid() {
		if (nameId != null) {
			return nameId;
		} else {
			return "";
		}
	}

	public static boolean getSearch() {
		return search;
	}

	public static String getSelected() {
		return selected;
	}

	public static boolean getSelectFlg() {
		return selectFlg;
	}

	public static boolean getSelectRepeat() {
		return selectRepeat;
	}

	public static void incrementFormNumber() {
		formNumber++;
	}

	public static void incrementFormPartsNumber() {
		formPartsNumber++;
	}

	/******** form method ************/
	/********** 2009 chie **************/

	//
	public static void initAllFormFlg() {
		setFormItemFlg(false, null);
		setSelectFlg(false);
		setSelectRepeat(false);
		setFormValueString(null);
		setFormPartsName(null);
		setSelected("");
		setIDU("");
		formPartsNumber = 1;
		exchange_form_name = new String();
		formDetail = new String[256];
		formNumber = 1;
		nameId = "";
		search = false;
		searchId = 0;
		condName = "";
		cond = "";
	}

	public static void setChecked(String s) {
		System.out.println("checked:" + s);
		checked = s;
	}

	public static void setFormDetail(String s) {
		if (formDetail[formNumber] == null)
			formDetail[formNumber] = s;
		else
			formDetail[formNumber] += s;
	}

	// form tag is written : true
	public static void setFormItemFlg(boolean b, String s) {
		isFormItem = b;
		formItemName = s;
		return;
	}

	public static void setFormPartsName(String s) {
		formPartsName = s;
	}

	public static void setFormValueString(String s) {
		formValueString = s;
	}

	public static void setIDU(String s) {
		IDUst = s;
	}

	public static void setSearch(boolean b) {
		search = b;
		searchId = 0;
	}

	public static void setSelected(String s) {
		selected = s;
	}

	public static void setSelectFlg(boolean b) {
		selectFlg = b;
	}

	// select_repeat flag
	// not write "<tr><td>" between "<option>"s
	public static void setSelectRepeat(boolean b) {
		selectRepeat = b;
	}

	public static Document htmlEnv1;
	protected String charset = null; // added by goto 20120715
	protected Connector connector;
	protected StringBuffer div = new StringBuffer();
	protected StringBuffer meta = new StringBuffer();
	// outline�����������������������������������������������������?
	protected boolean OutlineMode = false;
	protected StringBuffer title = new StringBuffer();
	protected StringBuffer titleClass = new StringBuffer();

	public String ajaxCond = new String();

	// for ajax
	public String ajaxQuery = new String();

	public String ajaxtarget = new String();

	// add 20141203_masato
	public String code_tmp = "";

	public StringBuffer code;

	// added by masato 20150914
	public static StringBuffer xmlCode;

	public int countFile;

	public static StringBuffer css;

	StringBuffer cssFile = new StringBuffer();
	StringBuffer jsFile = new StringBuffer();		//added by goto 20130703

	public String dragDivId = new String();

	public boolean draggable = false;

	public int embedCount = 0;

	public boolean embedFlag = false;

	public String fileName;

	public StringBuffer footer;

	public boolean foreachFlag;

	public int gLevel = 0;

	public ArrayList<String> outTypeList = new ArrayList<>();

	// added by masato 20150914
	public int cNum = 0;
	public int xmlDepth = 0;

	public boolean hasDispDiv = false;

	public int haveClass = 0;

	public static StringBuffer header;

	public int inEffect = 0;

	// for panel
	public boolean isPanel = false;
	// tk end//////////////////////////////////////////////////////

	public int linkFlag;

	public String linkOutFile;

	public String linkUrl;

	public String nextBackFile = new String();

	public Vector<String> notWrittenClassId = new Vector<String>();

	public String outDir;

	public int outEffect = 0;

	public String outFile;

	// for drag
	public StringBuffer script = new StringBuffer();

	public int scriptNum = 0;

	public boolean sinvokeFlag = false;

	// added by masato 20151124 for plink'values
	public ArrayList<String> valueArray;

	// added by masato 20151124 for plink'values
	public boolean plinkFlag = false;

	public static String tableBorder = new String("0"); //190604 1->0

	public Vector<String> writtenClassId;

	//tbt add
	public StringBuffer body_css = new StringBuffer();
	public StringBuffer ssql_body_css = new StringBuffer();

	public HTMLEnv() {
		this.htmlEnv1 = new Document("");
		new Document("");
	}

	private Element createInput(String type, String name, String value) {
		Attributes attributes = new Attributes();
		attributes.put("type", type);
		attributes.put("name", name);
		attributes.put("value", value);
		return new Element(Tag.valueOf("input"), "", attributes);
	}

	private Element createInsertDeleteUpdateForm() {
		Attributes formAttributes = new Attributes();
		formAttributes.put("action", GlobalEnv.getFileDirectory()
				+ "/servlet/supersql.form.Update");
		formAttributes.put("method", "post");
		formAttributes.put("name", "theForm");
		Element form = new Element(Tag.valueOf("form"), "", formAttributes);

		form.appendChild(createInput("hidden", "tableinfo",
				Start_Parse.get_from_info_st()));
		form.appendChild(createInput("hidden", "configfile",
				GlobalEnv.getconfigfile()));

		return form;

	}

	private Element createJsElement(String src) {
		Attributes attributes = new Attributes();
		attributes.put("type", "text/javascript");
		attributes.put("src", src);
		return new Element(Tag.valueOf("script"), "", attributes);
	}

	private ArrayList<Element> createJsElements(String... srcs) {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (String string : srcs) {
			elements.add(createJsElement(string));
		}
		return elements;
	}

	private Element createLoginForm() {
		Attributes formAttributes = new Attributes();
		formAttributes.put("action", GlobalEnv.getFileDirectory()
				+ "/servlet/supersql.form.Session");
		formAttributes.put("method", "post");
		formAttributes.put("name", "loginForm");
		Element form = new Element(Tag.valueOf("form"), "", formAttributes);

		Attribute hidden = new Attribute("type", "hidden");
		Tag input = Tag.valueOf("input");

		Attributes firstInputAttributes = new Attributes();
		firstInputAttributes.put(hidden);
		firstInputAttributes.put("name", "tableinfo");
		firstInputAttributes.put("value", Start_Parse.get_from_info_st());
		Element firstInput = new Element(input, "", firstInputAttributes);

		Attributes secondInputAttributes = new Attributes();
		secondInputAttributes.put(hidden);
		secondInputAttributes.put("name", "configfile");
		secondInputAttributes.put("value", GlobalEnv.getconfigfile());
		Element secondInput = new Element(input, "", secondInputAttributes);

		form.appendChild(firstInput);
		form.appendChild(secondInput);
		return form;
	}

	private Element createLogoutForm() {
		Attributes formAttributes = new Attributes();
		formAttributes.put("action", GlobalEnv.getFileDirectory()
				+ "/servlet/supersql.form.Session");
		formAttributes.put("method", "post");
		formAttributes.put("name", "logoutForm");
		Element form = new Element(Tag.valueOf("form"), "", formAttributes);

		Attributes inputAttributes = new Attributes();
		inputAttributes.put("type", "hidden");
		inputAttributes.put("name", "configfile");
		inputAttributes.put("value", GlobalEnv.getconfigfile());
		Element input = new Element(Tag.valueOf("input"), "", inputAttributes);

		form.appendChild(input);
		return form;
	}

	private Element createStylesheetElement(String src) {
		return createStylesheetElement(src, null);
	}

	private Element createStylesheetElement(String src, String media) {
		Attributes attributes = new Attributes();
		attributes.put("rel", "stylesheet");
		attributes.put("type", "text/css");
		attributes.put("href", src);
		if (media != null) {
			attributes.put("media", media);
		}
		return new Element(Tag.valueOf("link"), "", attributes);
	}

	private ArrayList<Element> createStylesheetElements(String... srcs) {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (String string : srcs) {
			elements.add(createStylesheetElement(string));
		}
		return elements;
	}

	private ArrayList<Element> createStylesheetElements(String[]... srcs) {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (String[] strings : srcs) {
			if (strings.length != 2)
				continue;
			elements.add(createStylesheetElement(strings[0], strings[1]));
		}
		return elements;
	}

	public static String commonCSS() {
		String s = "";
		//end
		// modifeid by masato 20151118 for ehtml start
		// TODO
		if(Ehtml.flag){
				String id ="ssqlResult" + GlobalEnv.getQueryNum();
			if(defaultCssFlag){ // TODO 場合分けをしっかり
				s += "#"+ id + " div, table, tr, td, th, img, a{\n" +
						"\tmargin: 0;\n" +
						"\tpadding: 0;\n" +
						"\tborder: 0;\n" +
						"\tfont-style:normal;\n" +
						"\tfont-weight: normal;\n" +
						"\tfont-size: 100%;\n" +
						"}\n\n";

				s += "#"+ id + " .row { display: flex; flex-direction: row; }\n";
				s += "#"+ id + " .col { display: flex; flex-direction: column; }\n";
				s += "#"+ id + " .att { border: solid 0px; }\n";

				s += "#"+ id + " table {\n" +
						"\tborder: 1px solid;\n" +
						"\tpadding: 1px;\n" +
						"}\n\n";

				s += "#"+ id + " td {\n" +
						"\tvertical-align: middle;\n" +
						"}\n\n";

//				s += "#"+ id + " table {\n" +
//						"\tmargin-left: auto;\n" +
//						"\tmargin-right: auto;\n" +
//						"\tmargin-top: auto;\n" +
//						"\tmargin-bottom: auto;\n" +
//						"\twidth: 100px;\n" +
//						"\tborder: 1px #BFBFBF solid;\n" +
//						"}\n\n";
//				s += "#"+ id + " table th {\n" +
//						"\tfont-weight: normal;\n" +
//						"\tbackground-color: #F0F0F0;\n" +
//						"\tborder:1px solid #BFBFBF;\n" +
//						"\ttext-align: center;\n" +
//						"\tpadding: 1px;\n" +
//						"}\n\n";
//				s += "#"+ id + " table tr td {\n" +
//						"\twidth: 100px;\n" +
//						"\tbackground-color: #3f3f3f;\n" +
//						"\tcolor: #e9e9e9;\n" +
//						"\ttext-align: center;\n" +
//						"\tpadding: 0px;\n" +
//						"\tvertical-align: middle;\n" +
//						"}\n\n";
//				s += "#"+ id + " table.att {\n" +
//						"\tmargin-left: auto;\n" +
//						"\tmargin-right: auto;\n" +
//						"\tmargin-top: auto;\n" +
//						"\tmargin-bottom: auto;\n" +
//						"\tborder: 1px #F0F0F0 solid;\n" +
//						"}\n\n";
			}
		} else
		// modifeid by masato 20151118 for ehtml end
		if (!GlobalEnv.isOpt()) {
//			s += getNewTableBorderCSS(false);	// TODO debug=がついている場合のみ？
//			s += getNewTableBorderCSS();	// TODO debug=がついている場合のみ？

			s += ".att { padding:0px; margin:0px; height:100%; z-index:2; }\n";
			s += ".linkbutton { text-align:center; margin-top:5px; padding:5px; }\n";
			s += ".embed { vertical-align:text-top; padding:0px; margin:0px; border:0px,0px,0px,0px; width:100%; }\n" +
				".noborder { border-width:0px; margin-top:-1px; padding-top:-1px; "
					+ "margin-bottom:-1px; padding-bottom:-1px; }\n\n";
		}
		return s;
	}


	public void append_css_def_td(String classid, DecorateList decolist) {
		DecorateList decos = new DecorateList();
		for (String key : decolist.keySet()) {
			decos.put(key, decolist.get(key));
		}

		Log.out("[HTML append_css_def_att] classid=" + classid);


		if (decorationStartFlag.size() > 0) {
			if ((decorationStartFlag.get(0) || decos.size()>0) && !decorationEndFlag.get(0)) {
			//if (decorationStartFlag.get(0) && !decorationEndFlag.get(0)) {
				for (String key : decos.keySet()) {
					if (!(decos.get(key).toString().startsWith("\"") && decos.get(key).toString().endsWith("\""))
							&& !(decos.get(key).toString().startsWith("\'") && decos.get(key).toString().endsWith("\'"))
							&& !supersql.codegenerator.CodeGenerator.isNumber(decos.get(key).toString())
							&& !"".equals(decos.get(key).toString())
							) {
						decorationProperty.get(0).add(0, key);
					}
				}
				for (int i = 0; i < decorationProperty.get(0).size(); i++) {
					decos.remove(decorationProperty.get(0).get(i));
				}
			}
		}

		haveClass = 0;
		// ������classid��������������������?����������������������������������������������������������������������?������
		if (writtenClassId.contains(classid)) {
			// �������������������������������������?������������������
			haveClass = 1;
			Log.out("==> already created style");
			return;
		} else if (notWrittenClassId != null
				&& notWrittenClassId.contains(classid)) {
			Log.out("==> style is null. not created style");
			return;
		}

		Log.out("==> new style");
		Log.out("@@ creating style no " + classid);

		StringBuffer cssbuf = new StringBuffer();

		// tk
		// start////////////////////////////////////////////////////////////////
		StringBuffer metabuf = new StringBuffer();

//		if (decos.containsKey("class")) {
//			cssclass.put(classid, decos.getStr("class"));
//		}
		Modifier.getClassModifierPutValue(decos, cssclass,classid);
		Modifier.getIdModifierPutValue(decos, cssclass, classid);//kotani_idmodifier_ok

		if (decos.containsKey("cssfile")) {
			String css = decos.getStr("cssfile").trim();
			if(!css.contains(",")){
				cssFile.delete(0, cssFile.length());
				if (GlobalEnv.isServlet()) {
					cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ GlobalEnv.getFileDirectory()
							+ css + "\">\n");
				} else {
					cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ css + "\">\n");
				}
			}else{
				if(!css.endsWith(","))	css+=",";
				while(css.contains(",")){
					cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css.substring(0,css.indexOf(",")).trim() + "\">\n");
					css = css.substring(css.indexOf(",")+1);
				}
			}
		}
//		else if (cssFile.length() == 0) {
//			if (GlobalEnv.isServlet()) {
//				cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
//						+ GlobalEnv.getFileDirectory() + "/default.css \">\n");
//			} else {
//				if (Utils.getOs().contains("Windows")) {
//					cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"default.css\">\n");
//				} else {
//					// itc
//					if (GlobalEnv.isOpt())
//						//cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqlcss/default_opt.css\">\n");
//						cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"jscss/default_opt.css\">\n");			//TODO jscss以下へ
//					else
//						//cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqlcss/default.css\">\n");
//						cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"jscss/default.css\">\n");				//TODO jscss以下へ
//				}
//			}
//		}

		//added by goto 20130703  ex) jsfile=" a.js; b.js "
		if (decos.containsKey("jsfile")) {
			String js = decos.getStr("jsfile").trim();
			if(!js.endsWith(","))	js+=",";
			while(js.contains(",")){
				jsFile.append("<script type=\"text/javascript\" src=\""	+ js.substring(0,js.indexOf(",")).trim() + "\"></script>\n");
				js = js.substring(js.indexOf(",")+1);
			}
		}



		if (decos.containsKey("divalign") && div.length() == 0)
			div.append(" align=" + decos.getStr("divalign"));

		if (decos.containsKey("title") && title.length() == 0)
			title.append(decos.getStr("title"));
		if (decos.containsKey("title_class"))
			titleClass.append(" class=\"" + decos.getStr("title_class") + "\"");
		if (decos.containsKey("tableborder"))// && tableborder.length() == 0)
			tableBorder = decos.getStr("tableborder");
		else if (decos.containsKey("border"))
			tableBorder = decos.getStr("border");

		// tk end//////////////////////////////////////////////////////////////

		computeConditionalDecorations(decos, css);

		// width
		if (decos.containsKey("width")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("width")))
				cssbuf.append(" width:" + decos.getStr("width") + ";");
			else
				cssbuf.append(" width:" + decos.getStr("width") + "px;");
		}

		// height
		if (decos.containsKey("height")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("height")))
				cssbuf.append(" height:" + decos.getStr("height") + ";");
			else
				cssbuf.append(" height:" + decos.getStr("height") + "px;");
		}

		// margin
		if (decos.containsKey("margin")) {
			cssbuf.append(" margin:" + decos.getStr("margin") + ";");
			// } else {
			// cssbuf.append(" padding:0.3em;");
		}

		// added by masato
		// margin
		if (decos.containsKey("margin-left")) {
			cssbuf.append(" margin-left:" + decos.getStr("margin-left") + ";");
		}
		if (decos.containsKey("margin-top")) {
			cssbuf.append(" margin-top:" + decos.getStr("margin-top") + ";");
		}
		if (decos.containsKey("margin-right")) {
			cssbuf.append(" margin-right:" + decos.getStr("margin-right")
					+ ";");
		}
		if (decos.containsKey("margin-bottom")) {
			cssbuf.append(" margin-bottom:" + decos.getStr("margin-bottom")
					+ ";");
		}

		// �������������������������������
		if (decos.containsKey("padding")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding:" + decos.getStr("padding") + ";");
			else
				cssbuf.append(" padding:" + decos.getStr("padding") + "px;");

			// } else {
			// cssbuf.append(" padding:0.3em;");
		}
		// padding
		if (decos.containsKey("padding-left")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-left:" + decos.getStr("padding-left") + ";");
			else
				cssbuf.append(" padding-left:" + decos.getStr("padding-left") + "px;");
		}
		if (decos.containsKey("padding-right")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-right:" + decos.getStr("padding-right") + ";");
			else
				cssbuf.append(" padding-right:" + decos.getStr("padding-right") + "px;");
		}
		if (decos.containsKey("padding-top")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-top:" + decos.getStr("padding-top") + ";");
			else
				cssbuf.append(" padding-top:" + decos.getStr("padding-top") + "px;");
		}
		if (decos.containsKey("padding-bottom")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-bottom:" + decos.getStr("padding-top") + ";");
			else
				cssbuf.append(" padding-bottom:" + decos.getStr("padding-top") + "px;");
		}

		// added by masato 20151207 new decos line-height ex) @{line-height=150}
		if (decos.containsKey("line-height"))
			cssbuf.append(" line-height:" + decos.getStr("line-height") + "%;");


		// ������������������
		if (decos.containsKey("align")){
			cssbuf.append(" text-align:" + decos.getStr("align") + ";");
		}

		// ��������������
		if (decos.containsKey("valign"))
			cssbuf.append(" vertical-align:" + decos.getStr("valign") + ";");

		// ����������
		if (decos.containsKey("background-color"))
			cssbuf.append(" background-color:"
					+ decos.getStr("background-color") + ";");
		if (decos.containsKey("bgcolor"))
			cssbuf.append(" background-color:" + decos.getStr("bgcolor") + ";");



		// ��������
		if (decos.containsKey("color"))
			cssbuf.append(" color:" + decos.getStr("color") + ";");
		if (decos.containsKey("font-color"))
			cssbuf.append(" color:" + decos.getStr("font-color") + ";");
		if (decos.containsKey("font color"))
			cssbuf.append(" color:" + decos.getStr("font color") + ";");

		// ��������������
		//170710 changed by tbt
		if (decos.containsKey("font-size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("font-size")))
				cssbuf.append(" font-size:" + decos.getStr("font-size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("font-size") + "px;");
		}
		if (decos.containsKey("font size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("font size")))
				cssbuf.append(" font-size:" + decos.getStr("font size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("font size") + "px;");}
		if (decos.containsKey("size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag  && !GlobalEnv.isNumber(decos.getStr("size")))
				cssbuf.append(" font-size:" + decos.getStr("size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("size") + "px;");}
		//tbt end
		// �����������������������
		if (decos.containsKey("font-weight"))
			cssbuf.append(" font-weight:" + decos.getStr("font-weight") + ";");

		// ��������������?
		if (decos.containsKey("font-style"))
			cssbuf.append(" font-style:" + decos.getStr("font-style") + ";");
		if (decos.containsKey("font-family"))
			cssbuf.append(" font-family:" + decos.getStr("font-family") + ";");

//		if (decos.containsKey("debug")) {		 //190604
//			if(decos.getStr("debug").toLowerCase().trim().equals("on") && !decos.containsKey("border")
//				&& !decos.containsKey("tableborder")) {
//				tableBorder = new String("1");
//			}
//		}

		if (decos.containsKey("border"))
			cssbuf.append(" border:" + decos.getStr("border") + ";");
		if (decos.containsKey("border-width"))
			cssbuf.append(" border-width:" + decos.getStr("border-width") + ";");
		if (decos.containsKey("border-color"))
			cssbuf.append(" border-color:" + decos.getStr("border-color") + ";");
		if (decos.containsKey("border-style"))
			cssbuf.append(" border-style:" + decos.getStr("border-style") + ";");
		if (decos.containsKey("border-collapse"))
			cssbuf.append(" border-collapse:" + decos.getStr("border-collapse")
					+ ";");

        //added by masato 20141214  "style"
        if (decos.containsKey("style")){
        	String style = decos.getStr("style");
        	cssbuf.append(" " + style);
        	if(!style.matches(".*;\\s*$"))	cssbuf.append(";");	//���������";"���������������������
        }

        //added by goto 20130311  "background"
        if (decos.containsKey("background")) {
        	bg = decos.getStr("background");
        //added by ayumi 20210714 background option
        } else if (decos.containsKey("backgroundcover")) {
        	bg = decos.getStr("backgroundcover");
        	bgsize = "cover";
        } else if (decos.containsKey("backgroundcontain")) {
        	bg = decos.getStr("backgroundcontain");
        	bgsize = "contain";
        } else if (decos.containsKey("backgroundtile")) {
        	bg = decos.getStr("backgroundtile");
        	bgsize = "20%";
        	bgrepeat = true;
        }
        //added by ayumi 20210714 "pbgimage"
        if (decos.containsKey("pbgimage")) {
        	bg = decos.getStr("pbgimage");
		} else if (decos.containsKey("pbgimagecover")) {
			bgsize = "cover";
        	bg = decos.getStr("pbgimagecover");
		} else if (decos.containsKey("pbgimagecontain")) {
        	bg = decos.getStr("pbgimagecontain");
        	bgsize = "contain";
		} else if (decos.containsKey("pbgimagetile")) {
        	bg = decos.getStr("pbgimagetile");
        	bgsize = "20%";
        	bgrepeat = true;
		}

      //added by ayumi 20210714 "pbg"
        if (decos.containsKey("pbg")) {
        	bg = decos.getStr("pbg");
		} else if (decos.containsKey("pbgcover")) {
        	bg = decos.getStr("pbgcover");
			bgsize = "cover";
		} else if (decos.containsKey("pbgcontain")) {
        	bg = decos.getStr("pbgcontain");
        	bgsize = "contain";
		} else if (decos.containsKey("pbgtile")) {
        	bg = decos.getStr("pbgtile");
        	bgsize = "20%";
        	bgrepeat = true;
		}

        //added by ayumi 20210714 "bgsize"
        if (decos.containsKey("backgroundsize")) {
        	String[] sizes = (decos.getStr("backgroundsize")).split("[\\s]*,[\\s]*");
        	for(int i=0;i<sizes.length;i++) {
        		if(i==0) {
        			bgsize = "";
        			bgsize += sizes[i];
        		}else {
        			bgsize += " ";
        			bgsize += sizes[i];
        		}
        	}
		} else if (decos.containsKey("pbgimagesize")) {
			String[] sizes = (decos.getStr("pbgimagesize")).split("[\\s]*,[\\s]*");
        	for(int i=0;i<sizes.length;i++) {
        		if(i==0) {
        			bgsize = "";
        			bgsize += sizes[i];
        		}else {
        			bgsize += " ";
        			bgsize += sizes[i];
        		}
        	}
		} else if (decos.containsKey("pbgsize")) {
			String[] sizes = (decos.getStr("pbgsize")).split("[\\s]*,[\\s]*");
        	for(int i=0;i<sizes.length;i++) {
        		if(i==0) {
        			bgsize = "";
        			bgsize += sizes[i];
        		}else {
        			bgsize += " ";
        			bgsize += sizes[i];
        		}
        	}
		}

//<<<<<<< HEAD
        //tbt add
  		if(decos.containsKey("page-bgcolor")){
  			bgcolor = decos.getStr("page-bgcolor");
  		}else if(decos.containsKey("pbgcolor")){
  			bgcolor = decos.getStr("pbgcolor");
  		}

  		if(decos.containsKey("page-align")){
  			pos = decos.getStr("page-align");
  		}else if(decos.containsKey("palign")){
  			pos = decos.getStr("palign");
  		}else if(decos.containsKey("table-align")){
      		pos = decos.getStr("table-align");
      	}else if(decos.containsKey("talign")){
//=======
//      //tbt add
//      	if(decos.containsKey("page-bgcolor") || decos.containsKey("pbgcolor")){
//      		if(decos.containsKey("page-bgcolor")){
//      			color = decos.getStr("page-bgcolor");
//      		}else{
//      			color = decos.getStr("pbgcolor");
//      		}
//      	}
//
//      	if(decos.containsKey("table-align")){
//      		pos = decos.getStr("table-align");
//      	}
//
//      	if(decos.containsKey("talign")){
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      		pos = decos.getStr("talign");
      	}


        // added by masato 20151202  "no default css"
        if (decos.containsKey("nodefaultcss"))
        	defaultCssFlag = false;

		// tk
		// start////////////////////////////////////////////////////////////////
		// added by goto 20120715 start
		if (decos.containsKey("charset"))
			charset = decos.getStr("charset");
		else if (!charsetFlg)
//			charset = "EUC-JP"; // default charset = EUC-JP
			charset = "UTF-8"; // default charset = UTF-8
		if (!charsetFlg && charset != null) {
			metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
					+ charset + "\">");
			charsetFlg = true;
		}
		// if (decos.containsKey("charset")){
		// metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
		// + decos.getStr("charset") + "\">");
		// charset=decos.getStr("charset");
		// charsetFlg=1;
		// }else if(charsetFlg!=1){
		// metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=EUC-JP\">");
		// charset="EUC-JP"; //default charset = EUC-JP
		// charsetFlg=1;
		// }
		// added by goto 20120715 end

		//added by goto 20161217  for responsive
		Responsive.check(decos);

		if (decos.containsKey("description"))
			metabuf.append("<meta name=\"Description\" content=\""
					+ decos.getStr("description") + "\">");
		if (decos.containsKey("keyword"))
			metabuf.append("<meta name=\"Keyword\" content=\""
					+ decos.getStr("keyword") + "\">");
		if (decos.containsKey("author"))
			metabuf.append("<meta name=\"Author\" content=\""
					+ decos.getStr("author") + "\">");
		if (decos.containsKey("pragma"))
			metabuf.append("<meta http-equiv=\"Pragma\" content=\""
					+ decos.getStr("pragma") + "\">");
		if (decos.containsKey("robot"))
			metabuf.append("<meta name=\"Robot\" content=\""
					+ decos.getStr("robot") + "\">");
		// tk
		// end///////////////////////////////////////////////////////////////////

		// taji 20170124 for infinite-scroll
		if(decos.containsKey("infinite-scroll")){
			cssbuf.append(" height:500px; overflow: auto;");//todo heightを変数に
		}
		if (cssbuf.length() > 0) {
			haveClass = 1;
			// ������������?��������������������?������������
			// modified by masato 20151122 start for etml, css
			if(Ehtml.flag){
				String id = "ssqlResult" + GlobalEnv.getQueryNum();
				css.append("#" + id + " ." + classid + "{");
			} else {
				css.append("." + classid + "{");

			}

			css.append(cssbuf);
			// ������?��������������������?��������
			css.append(" }\n");

			// ������������������?������?����������������������id����������������������������������
			writtenClassId.addElement(classid);
		} else {
			Log.out("==> style is null. not created style");
			notWrittenClassId.addElement(classid);
		}

		// tk start//////////////////////////////////////////////////////////
		if (metabuf.length() > 0) {
			// meta.append(" "); //commented out by goto 201303
			meta.append(metabuf);
			meta.append("\n");

		}
		// tk end////////////////////////////////////////////////////////////


	}

	public void createHeader() {
		Attributes attributes = new Attributes();
		attributes.put("type", "text/css");
		Element style = new Element(Tag.valueOf("style"), "", attributes);

		if (GlobalEnv.isOpt()) {
			style.text("<!--\n.att { padding: 0px; margin : 0px;height : 100%; z-index: 2}\n"
					+ ".linkbutton {text-align:center; margin-top: 5px; padding:5px;}\n"
					+ ".embed { vertical-align : text-top; padding : 0px ; margin : 0px; border: 0px,0px,0px,0px; width: 100%;}"
					+ ".noborder { 	border-width : 0px; "
					+ "-top : -1px; padding-top : -1px;	"
					+ "-bottom : -1px;	padding-bottom : -1px;}\n-->\n"
					+ "</STYLE>");
		}

		htmlEnv1.head().appendChild(style);

		Attributes meta_attributes = new Attributes();
		meta_attributes.put("http-equiv", "Content-Type");
		meta_attributes.put("content", "text-html");
		meta_attributes.put("charset", "UTF-8");
		Element meta = new Element(Tag.valueOf("meta"), "", meta_attributes);

		htmlEnv1.head().appendChild(meta);

		if (GlobalEnv.isAjax()) {
			String js = GlobalEnv.getJsDirectory();

			if (js != null) {
				if (js.endsWith("/"))
					js = js.substring(0, js.lastIndexOf("/"));
				htmlEnv1.head().appendChild(
						createJsElement(js + "/prototype.js"));
				htmlEnv1.head().appendChild(createJsElement(js + "/ajax.js"));
			} else {
				htmlEnv1.head()
						.appendChild(
								createJsElement("http://localhost:8080/tab/prototype.js"));
				htmlEnv1.head().appendChild(
						createJsElement("http://localhost:8080/tab/ajax.js"));
			}

			// Insertion of javascript files
			ArrayList<Element> elements = createJsElements("js/lightbox.js",
					"js/scriptaculous.js?load=effects", "js/prototype.js",
					"build/animation/animation.js",
					"build/container/container.js", "build/tabview/tabview.js",
					"build/element/element-beta.js", "prototype.js",
					"ssqlajax.js", "build/yahoo/yahoo-min.js",
					"build/event/event-min.js", "build/dom/dom-min.js",
					"build/dragdrop/dragdrop-min.js");

			for (Element element : elements) {
				htmlEnv1.head().appendChild(element);
			}

			// Insertion of css files
			elements = createStylesheetElements(
					"build/tabview/assets/border_tabs.css",
					"build/tabview/assets/tabview.css",
					"build/container/assets/container.css");
			for (Element element : elements) {
				htmlEnv1.head().appendChild(element);
			}
			elements = createStylesheetElements(new String[] {
					"css/lightbox.css", "screen" }, new String[] {
					"css/tabview-core.css", "screen" }, new String[] {
					"css/panel.css", "screen" });
			for (Element element : elements) {
				htmlEnv1.head().appendChild(element);
			}
			Attributes jsType = new Attributes();
			attributes.put("type", "text/javascript");
			Element element = new Element(Tag.valueOf("script"), "", jsType);
			element.text(script.toString());
			htmlEnv1.appendChild(element);
		} else {

		}

	}

	public void createSSQLForm() {
		if (Connector.loginFlag) {
			htmlEnv1.body().appendChild(createLoginForm());
		} else if (Connector.logoutFlag) {
			htmlEnv1.body().appendChild(createLogoutForm());
		} else if (Connector.insertFlag || Connector.deleteFlag
				|| Connector.updateFlag) {
			Element form = createInsertDeleteUpdateForm();
			if (Connector.insertFlag)
				form.appendChild(createInput("hidden", "sql_param", "insert"));
			if (Connector.deleteFlag)
				form.appendChild(createInput("hidden", "sql_param", "delete"));
			if (Connector.updateFlag)
				form.appendChild(createInput("hidden", "sql_param", "update"));

			form.appendChild(createInput("submit", "login", "Let's go!"));
			htmlEnv1.appendChild(form);
		}
	}

	public void getFooter() {
		if (Connector.updateFlag || Connector.insertFlag
				|| Connector.deleteFlag || Connector.loginFlag) {
			footer.append("<input type=\"submit\" name=\"login\" value=\"Let's go!\">");
			footer.append("</form>\n");
			Log.out("</form>");
			Connector.updateFlag = false;
			Connector.insertFlag = false;
			Connector.deleteFlag = false;
			Connector.loginFlag = false;
		}

		if (Connector.logoutFlag) {
			footer.append("</form>\n");
			Log.out("</form>");
			Connector.logoutFlag = false;
		}

		if (GlobalEnv.getframeworklist() == null) {
			footer.append("<BR><BR>\n");
			footer.append("</div>\n");
			footer.append("<!-- SuperSQL Body  End -->");
			footer.append(LinkForeach.getC3contents());	//added by goto 20161019 for new foreach
			footer.append("</BODY>\n</HTML>\n");
			Log.out("</body>\n</html>");
		}

		header_creation();
	}

	public void getHeader() {
		if (GlobalEnv.getframeworklist() == null) {
			header.insert(0, "<!DOCTYPE html>\n<HTML>\n<HEAD>\n");
			Log.out("<HTML>\n<head>");
//			header.append("<STYLE TYPE=\"text/css\">\n");
//			header.append("<!--\n");
//			commonCSS();
//			header.append(css);
//			Log.out(css.toString());
//			header.append("\n-->\n</STYLE>\n");

	        //Generator
	        header.append("<meta name=\"GENERATOR\" content=\" SuperSQL (Generate HTML) \">\n");
		}
	}

	public String getOutlineMode() {
		if (OutlineMode) {
			OutlineMode = false;
			return "";
		}
		// return " frame=void class=nest ";
		return " frame=void ";
	}

	public String getOutlineModeAtt() {
		if (OutlineMode) {
			OutlineMode = false;
			return " outline";
		}
		return "";
	}

	public void header_creation() {
		// tk start////////////////////////////////////////////////////
		header.append(meta);

		if (!title.equals(""))
			header.append("<title>"+title+"</title>\n");

		if(!title.toString().trim().equals("")){
			header.append("<title>");
			header.append(title);
			header.append("</title>\n");
		}

		if (GlobalEnv.isAjax()) {
			String js = GlobalEnv.getJsDirectory();
			if (js != null) {
				if (js.endsWith("/"))
					js = js.substring(0, js.lastIndexOf("/"));

				header.append("<script src=\""
						+ js
						+ "/prototype.js\" type=\"text/javascript\"></script>\n");
				header.append("<script src=\"" + js
						+ "/ajax.js\" type=\"text/javascript\"></script>");

			} else {
				header.append("<script src=\"http://localhost:8080/tab/prototype.js\" type=\"text/javascript\"></script>\n");
				header.append("<script src=\"http://localhost:8080/tab/ajax.js\" type=\"text/javascript\"></script>");
			}

			header.append("<script type=\"text/javascript\" src=\"build/yahoo/yahoo-min.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/event/event-min.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/dom/dom-min.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/dragdrop/dragdrop-min.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"ssqlajax.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"prototype.js\" ></script>\n");

			// for tab
			header.append("<script type=\"text/javascript\" src=\"build/element/element-beta.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/tabview/tabview.js\"></script>\n");

			// for panel
			header.append("<script type=\"text/javascript\" src=\"build/container/container.js\"></script>\n");

			// for animation
			header.append("<script type=\"text/javascript\" src=\"build/animation/animation.js\"></script>\n");

			// for lightbox
			header.append("<script type=\"text/javascript\" src=\"js/prototype.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"js/scriptaculous.js?load=effects\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"js/lightbox.js\"></script>\n");

			// for tab css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/tabview/assets/border_tabs.css\">\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/tabview/assets/tabview.css\">\n");

			// for panel css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/container/assets/container.css\">\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/container/assets/container.css\">\n");

			// for lightbox css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/lightbox.css\"  media=\"screen\">\n");

			// for custom tab
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tabview-core.css\"  media=\"screen\">\n");

			// for custom panel
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/panel.css\"  media=\"screen\">\n");

			header.append("<script type=\"text/javascript\">");
			header.append(script);
			header.append("</script>");

		}

		if (GlobalEnv.getframeworklist() == null) {
			// 20140528_masato
			header.append(
					// 20140701_masato
					"<!-- SuperSQL JavaScript & CSS -->\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"jscss/ssql-pagination.css\">\n"
					+ "<script type=\"text/javascript\" src=\"jscss/jquery.js\"></script>\n"
					+ "<script type=\"text/javascript\" src=\"jscss/jquery-p.js\"></script>\n");

			header.append(cssFile);
			header.append(jsFile);		//added by goto 20130703

			// 20140704_masato
			css.append("\n");
			//tbt add
			if (!bg.equals("")){
//	            body_css.append("body { background-image: url(../"+bg+"); }");
				body_css.append("\tbackground-image: url(../"+bg+");\n");
				if(bgrepeat == false) {                                 //added by ayumi 20210714
					body_css.append("\tbackground-repeat: no-repeat;\n");
				}
	        }
			// added by ayumi 20210714
			if (!bgsize.equals("")) {
				body_css.append("\tbackground-size: " + bgsize + ";\n");
			}
			if(!bgcolor.equals("")){
				body_css.append("\tbackground-color: "+ bgcolor +";\n");
			}
			if(!pos.equals("")){
				body_css.append("\ttext-align: "+pos+";\n");
				ssql_body_css.append("\tdisplay: inline-block;\n");
				ssql_body_css.append("\ttext-align: left;\n");
			}
			css.insert(0, "#ssql_body_contents {\n"+ssql_body_css+"}\n\n");
			css.insert(0,"body {\n"+body_css+"}\n");


			header.append("<!-- Generated CSS -->\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+ Jscss.getGenerateCssFileName(0) + "\">\n");
			header.append("</HEAD>\n");
			//changed by goto 20161019
			Log.out("<body>");
			code_tmp = "";
			code_tmp += "<BODY class=\"body\">\n";
			code_tmp += "<!-- SuperSQL Body  Start -->";
			code_tmp += "<div id=\"ssql_body_contents\">\n";	//added by goto 20161019 for new foreach
//			if(!title.toString().trim().equals("")){
//				code_tmp += "<div";
//				code_tmp += div;
//				code_tmp += titleClass;
//				code_tmp += ">";
//				code_tmp += title;
//				code_tmp += "</div>";
//			}
		}

		if (Connector.loginFlag) {
			code_tmp += "<form action = \""
					+ GlobalEnv.getFileDirectory()
					+ "/servlet/supersql.form.Session\" method = \"post\" name=\"theForm\">\n";
			code_tmp += "<input type=\"hidden\" name=\"tableinfo\" value=\""
					+ Start_Parse.get_from_info_st() + "\" >";
			code_tmp += "<input type=\"hidden\" name=\"configfile\" value=\""
					+ GlobalEnv.getconfigfile() + "\" >";
		}

		if (Connector.logoutFlag) {
			code_tmp += "<form action = \""
					+ GlobalEnv.getFileDirectory()
					+ "/servlet/supersql.form.Session\" method = \"post\" name=\"theForm\">\n";
			code_tmp += "<input type=\"hidden\" name=\"configfile\" value=\""
					+ GlobalEnv.getconfigfile() + "\" >";
		}

		if (Connector.insertFlag || Connector.deleteFlag
				|| Connector.updateFlag) {
			code_tmp += "<form action = \""
					+ GlobalEnv.getFileDirectory()
					+ "/servlet/supersql.form.Update\" method = \"post\" name=\"theForm\">\n";
			code_tmp += "<input type=\"hidden\" name=\"tableinfo\" value=\""
					+ Start_Parse.get_from_info_st() + "\" >";
			code_tmp += "<input type=\"hidden\" name=\"configfile\" value=\""
					+ GlobalEnv.getconfigfile() + "\" >";
			if (Connector.insertFlag)
				code_tmp += "<input type=\"hidden\" name=\"sql_param\" value=\"insert\" >";
			if (Connector.deleteFlag)
				code_tmp += "<input type=\"hidden\" name=\"sql_param\" value=\"delete\" >";
			if (Connector.updateFlag)
				code_tmp += "<input type=\"hidden\" name=\"sql_param\" value=\"update\" >";
		}

		code.insert(0, code_tmp);

	}

	public void includeDecorationProperties(String classId, DecorateList decos) {
		haveClass = 0;
		// TODO refactor this part
		if (writtenClassId.contains(classId)) {
			haveClass = 1;
			Log.out("==> already created style");
			return;
		} else if (notWrittenClassId != null
				&& notWrittenClassId.contains(classId)) {
			Log.out("==> style is null. not created style");
			return;
		}

//		if (decos.containsKey("class")) {
//			cssclass.put(classId, decos.getStr("class"));
//		}
		Modifier.getClassModifierPutValue(decos, cssclass, classId);
		Modifier.getIdModifierPutValue(decos, cssclass, classId);//kotani_idmodifier_ok

		if (decos.containsKey("div"))
		if (decos.containsKey("divalign"))
			div.append(" align=" + decos.getStr("divalign"));

		if (decos.containsKey("title")) {
			if (decos.contains("title_class")) {
				Attributes titleAttribute = new Attributes();
				titleAttribute.put("class", decos.getStr("title_class"));
				Element title = new Element(Tag.valueOf("title"), "",
						titleAttribute);
				title.html(decos.getStr("title"));
				htmlEnv1.head().appendChild(title);
			} else {
				Element title = new Element(Tag.valueOf("title"), "");
				title.html(decos.getStr("title"));
				htmlEnv1.appendChild(title);
			}

			// TODO something to put a h1 tag for the title
		}

		if (decos.containsKey("tableborder"))
			tableBorder = decos.getStr("tableborder");
		else if (decos.containsKey("border"))
			tableBorder = decos.getStr("border");

		computeConditionalDecorations(decos, css);

		// TODO a lot of decorations should be added but in css file, we should
		// call another method here

		Attributes attributes = new Attributes();

		// added by goto 20120715 start
		if (decos.containsKey("charset"))
			charset = decos.getStr("charset");
		else if (!charsetFlg)
//			charset = "EUC-JP"; // default charset = EUC-JP
			charset = "UTF-8"; // default charset = UTF-8
		if (!charsetFlg && charset != null) {
			attributes.put("http-equiv", "Content-Type");
			attributes.put("content", "text/html");
			attributes.put("charset", charset);
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
			charsetFlg = true;
		}

		if (decos.containsKey("description")) {
			attributes = new Attributes();
			attributes.put("name", "Description");
			attributes.put("content", decos.getStr("description"));
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
		}
		if (decos.containsKey("keyword")) {
			attributes = new Attributes();
			attributes.put("name", "Keyword");
			attributes.put("content", decos.getStr("keyword"));
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
		}
		if (decos.containsKey("author")) {
			attributes = new Attributes();
			attributes.put("name", "Author");
			attributes.put("content", decos.getStr("author"));
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
		}
		if (decos.containsKey("pragma")) {
			attributes = new Attributes();
			attributes.put("name", "Pragma");
			attributes.put("content", decos.getStr("pragma"));
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
		}
		if (decos.containsKey("robot")) {
			attributes = new Attributes();
			attributes.put("name", "Robot");
			attributes.put("content", decos.getStr("robot"));
			htmlEnv1.head().appendChild(
					new Element(Tag.valueOf("meta"), "", attributes));
		}

		if ("".length() > 0) {
			// TODO
		} else {
			Log.out("==> style is null. not created style");
			notWrittenClassId.addElement(classId);
		}
	}

	public void setOutlineMode() {
		OutlineMode = true;
	}

	public static void initXML(){
		xmlCode = new StringBuffer();
		xmlCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

}
