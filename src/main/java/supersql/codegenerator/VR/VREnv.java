package supersql.codegenerator.VR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.antlr.v4.codegen.CodeGenerator;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LocalEnv;
import supersql.codegenerator.Modifier;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.common.Utils;

public class VREnv extends LocalEnv implements Serializable{
	static String formPartsName = null;
	private static String checked = "";
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
	public org.w3c.dom.Document xml;
	public  Node currentNode;

	//added by li 20210601
	public static String bgcolor = "";
	public static String pos = "";
	private static boolean charsetFlg = false;
	StringBuffer jsFile = new StringBuffer();
	
	public static String condName = "";
	// global form item number : t1,t2,t3...
	public static int formPartsNumber = 1;
	public static String nameId = "";
	public static int searchId = 0;

	public static String getChecked() {
		return checked;
	}

	public static String getClassID(ITFE tfe) {
		String result;
		if (tfe instanceof VRC3) {
			result = getClassID((((VRC3) tfe).tfes.get(0)));
			return result;
		}
		if (tfe instanceof VRG3) {
			result = getClassID((((VRG3) tfe).tfe));
			return result;
		}
		result = "TFE" + tfe.getId();
		return result;
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

	public static Document vrEnv1;
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
	public static StringBuffer cs_code = new StringBuffer();//added by kotani 20161122
	
	// added by masato 20150914
	public static StringBuffer xmlCode;
	
	public int countFile;

	public static StringBuffer css;

	public StringBuffer cssFile = new StringBuffer();

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
	
	public String tableBorder = new String("1");

	public Vector<String> writtenClassId;

	public VREnv() {
		VREnv.vrEnv1 = new Document("");
		new Document("");
		try {
			this.xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			this.currentNode = this.xml;
		} catch (ParserConfigurationException e) {
			this.xml = null;
			e.printStackTrace();
		}
	}
	
	public void getFooter() {
		header_creation();
	}

	public String getOutlineMode() {
		if (OutlineMode) {
			OutlineMode = false;
			return "";
		}
		// return " frame=void class=nest ";
		return " frame=void ";
	}

	public void header_creation() {
		this.currentNode = this.currentNode.appendChild(this.xml.createElement("DOC"));
	}

	public void setOutlineMode() {
		OutlineMode = true;
	}
	
	public static void initXML(){
		xmlCode = new StringBuffer();
		xmlCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
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
//						cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqlcss/default_opt.css\">\n");
//					else
//						cssFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqlcss/default.css\">\n");
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

		if (decos.containsKey("debug")) //190604
			if(decos.getStr("debug").toLowerCase().trim().equals("on") && !decos.containsKey("border")
				&& !decos.containsKey("tableborder")) {
				tableBorder = new String("1");
			}

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
        if (decos.containsKey("background"))
        	bg = decos.getStr("background");

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


}
