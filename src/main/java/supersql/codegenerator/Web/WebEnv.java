package supersql.codegenerator.Web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.jsoup.nodes.Document;

import supersql.codegenerator.CSS;
import supersql.codegenerator.CSSList;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.LocalEnv;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class WebEnv extends LocalEnv {
	
	private Document WebEnv;
	
	public boolean borderFlag = false;
	public StringBuffer code;
	public int countFile = 0; // C3, G3
	public static StringBuffer css;
	public static ArrayList<String> cssClass = new ArrayList<String>();
//	public String decorateAttribute = null;
//	public boolean decorationFlag = false;
	public ArrayList<ArrayList<String>> decorationProperty = new ArrayList<ArrayList<String>>();
//	public boolean decorationStartFlag = false;
	public ArrayList<Boolean> decorationStartFlag = new ArrayList<Boolean>();
//	public boolean decorationEndFlag = false;
	public ArrayList<Boolean> decorationEndFlag = new ArrayList<Boolean>();
	public String fileName;
	public StringBuffer footer;
	public static boolean footerFlag = false;
	public int haveClass = 0;
	public StringBuffer header;
	public static boolean headerFlag = false;
	public int linkFlag = 0; // C3, G3
	public String linkOutFile;
	public String linkUrl; // C3, G3
	public boolean listOlFlag = false;
	public boolean listUlFlag = false;
	public Vector<String> notWrittenClassId = new Vector<String>();
	public String outDir;
	public String outFile;
	public static String style = null;
	public boolean tableFlag = false;
	public boolean topLevelDiv = false;
	public Vector<String> writtenClassId = new Vector<String>();
	
	public WebEnv() {
		this.WebEnv = new Document("");
		new Document("");
	}
	
	public void append_css_def_att(String classid, DecorateList decolist) {
		DecorateList decos = new DecorateList();
		for (String key : decolist.keySet()) {
			decos.put(key, decolist.get(key));
		}
		
		Log.out("[Web append_css_def_att] classid = " + classid);
		Log.out("decos = " + decos);
		
		if (decorationStartFlag.size() > 0) {
//			if (decorationStartFlag && !decorationEndFlag) {
			if ((decorationStartFlag.get(0) || decos.size()>0) && !decorationEndFlag.get(0)) {
			//if (decorationStartFlag.get(0) && !decorationEndFlag.get(0)) {
//				decorationProperty = new ArrayList<String>();
				for (String key : decos.keySet()) {
					Log.out("decos-[" + key + "] -> " + decos.get(key).toString());
					if (!(decos.get(key).toString().startsWith("\"") && decos.get(key).toString().endsWith("\""))
							&& !(decos.get(key).toString().startsWith("\'") && decos.get(key).toString().endsWith("\'"))) {
//						decorationProperty.add(0, key);
						decorationProperty.get(0).add(0, key);
					}
				}
//				for (int i = 0; i < decorationProperty.size(); i++) {
				for (int i = 0; i < decorationProperty.get(0).size(); i++) {
					Log.out("decos property [" + i + "] -> " + decorationProperty.get(0).get(i));
					decos.remove(decorationProperty.get(0).get(i));
				}
			}
		}
		
		haveClass = 0;
		if (writtenClassId.contains(classid)) {
			haveClass = 1;
			Log.out("==> already created css style");
			return;
		} else if (notWrittenClassId != null && notWrittenClassId.contains(classid)) {
			Log.out("==> css style is null. not created style");
			return;
		}
		Log.out("==> new style @@ creating style no " + classid);
		
		// css情報の書き込み
		StringBuffer cssbuf = new StringBuffer();
		
		// background-attachment
		if (decos.containsKey("background-attachment")) { // TODO
			String str = stringsub(decos.getStr("background-attachment"));
			if (str != null) {
				cssbuf.append("\tbackground-attachment: " + str + ";\n");
			}
		}
		
		// background-clip (CSS3)
		if (decos.containsKey("background-clip")) { // TODO
			String str = stringsub(decos.getStr("background-clip"));
			if (str != null) {
				cssbuf.append("\tbackground-clip: " + str + ";\n");
			}
		}
		
		// background-color & bgcolor
		if (decos.containsKey("background-color")) { // TODO
			String str = stringsub(decos.getStr("background-color"));
			if (str != null) {
				cssbuf.append("\tbackground-color: " + str + ";\n");
			}
		} else if (decos.containsKey("bgcolor")) {
			String str = stringsub(decos.getStr("bgcolor"));
			if (str != null) {
				cssbuf.append("\tbackground-color: " + str + ";\n");
			}
		}
		
		// background-image
		if (decos.containsKey("background-image")) { // TODO
			String str = stringsub(decos.getStr("background-image"));
			if (str != null) {
				if ((str.indexOf("url") != -1) || str.equals("none") || str.equals("inherit")) {
					cssbuf.append("\tbackground-image: " + str + ";\n");
				} else {
					cssbuf.append("\tbackground-image: url(\"" + str + "\");\n");
				}
			}
		}
		
		// background-origin (CSS3)
		if (decos.containsKey("background-origin")) { // TODO
			String str = stringsub(decos.getStr("background-origin"));
			if (str != null) {
				cssbuf.append("\tbackground-origin: " + str + ";\n");
			}
		}
		
		// background-repeat
		if (decos.containsKey("background-repeat")) { // TODO
			String str = stringsub(decos.getStr("background-repeat"));
			if (str != null) {
				cssbuf.append("\tbackground-repeat: " + str + ";\n");
			}
		}
		
		// background-size (CSS3)
		if (decos.containsKey("background-size")) { // TODO
			String str = stringsub(decos.getStr("background-size"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tbackground-size: " + str + "px;\n");
				} else {
					cssbuf.append("\tbackground-size: " + str + ";\n");
				}
			}
		}
		
		// border
		if (decos.containsKey("border")) { // TODO
			Log.out("****** div border on ******");
			borderFlag = true;
		}
		
		// border-top
		if (decos.containsKey("border-top")) {
			String str = stringsub(decos.getStr("border-top"));
			if (str != null) {
				cssbuf.append("\tborder-top: " + str + ";\n");
			}
		}
		
		// border-right
		if (decos.containsKey("border-right")) {
			String str = stringsub(decos.getStr("border-right"));
			if (str != null) {
				cssbuf.append("\tborder-right: " + str + ";\n");
			}
		}
		
		// border-bottom
		if (decos.containsKey("border-bottom")) {
			String str = stringsub(decos.getStr("border-bottom"));
			if (str != null) {
				cssbuf.append("\tborder-bottom: " + str + ";\n");
			}
		}
		
		// border-left
		if (decos.containsKey("border-left")) { // TODO
			String str = stringsub(decos.getStr("border-left"));
			if (str != null) {
				cssbuf.append("\tborder-left: " + str + ";\n");
			}
		}
		
		// border-collapse (only table element)
		if (decos.containsKey("border-collapse")) { // TODO
			String str = stringsub(decos.getStr("border-collapse"));
			if (str != null) {
				cssbuf.append("\tborder-collapse: " + str + ";\n");
			}
		}
		
		// border-color
		if (decos.containsKey("border-color")) { // TODO
			String str = stringsub(decos.getStr("border-color"));
			if (str != null) {
				cssbuf.append("\tborder-color: " + str + ";\n");
			}
		} else if (borderFlag) {
			cssbuf.append("\tborder-color: #F0F0F0;\n");
		}
		
		// border-bottom-color & border-color-bottom
		if (decos.containsKey("border-bottom-color")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-color"));
			if (str != null) {
				cssbuf.append("\tborder-bottom-color: " + str + ";\n");
			}
		} else if (decos.containsKey("border-color-bottom")) {
			String str = stringsub(decos.getStr("border-color-bottom"));
			if (str != null) {
				cssbuf.append("\tborder-bottom-color: " + str + ";\n");
			}
		}
		// border-left-color & border-color-left
		if (decos.containsKey("border-left-color")) { // TODO
			String str = stringsub(decos.getStr("border-left-color"));
			if (str != null) {
				cssbuf.append("\tborder-left-color: " + str + ";\n");
			}
		} else if (decos.containsKey("border-color-left")) {
			String str = stringsub(decos.getStr("border-color-left"));
			if (str != null) {
				cssbuf.append("\tborder-left-color: " + str + ";\n");
			}
		}
		// border-right-color & border-color-right
		if (decos.containsKey("border-right-color")) { // TODO
			String str = stringsub(decos.getStr("border-right-color"));
			if (str != null) {
				cssbuf.append("\tborder-right-color: " + str + ";\n");
			}
		} else if (decos.containsKey("border-color-right")) {
			String str = stringsub(decos.getStr("border-color-right"));
			if (str != null) {
				cssbuf.append("\tborder-right-color: " + str + ";\n");
			}
		}
		// border-top-color & border-color-top
		if (decos.containsKey("border-top-color")) { // TODO
			String str = stringsub(decos.getStr("border-top-color"));
			if (str != null) {
				cssbuf.append("\tborder-top-color: " + str + ";\n");
			}
		} else if (decos.containsKey("border-color-top")) {
			String str = stringsub(decos.getStr("border-color-top"));
			if (str != null) {
				cssbuf.append("\tborder-top-color: " + str + ";\n");
			}
		}
		
		// border-radius (CSS3)
		if (decos.containsKey("border-radius")) { // TODO
			String str = stringsub(decos.getStr("border-radius"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-radius: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-radius: " + str + ";\n");
				}
			}
		}
		
		// border-spacing (only table element)
		if (decos.containsKey("border-spacing")) { // TODO
			String str = stringsub(decos.getStr("border-spacing"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-spacing: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-spacing: " + str + ";\n");
				}
			}
		}
		
		// border-style
		if (decos.containsKey("border-style")) { // TODO
			String str = stringsub(decos.getStr("border-style"));
			if (str != null) {
				cssbuf.append("\tborder-style: " + str + ";\n");
			}
		} else if (borderFlag) {
			cssbuf.append("\tborder-style: solid;\n");
		}
		
		// border-bottom-style & border-style-bottom
		if (decos.containsKey("border-bottom-style")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-style"));
			if (str != null) {
				cssbuf.append("\tborder-bottom-style: " + str + ";\n");
			}
		} else if (decos.containsKey("border-style-bottom")) {
			String str = stringsub(decos.getStr("border-style-bottom"));
			if (str != null) {
				cssbuf.append("\tborder-bottom-style: " + str + ";\n");
			}
		}
		// border-left-style & border-style-left
		if (decos.containsKey("border-left-style")) { // TODO
			String str = stringsub(decos.getStr("border-left-style"));
			if (str != null) {
				cssbuf.append("\tborder-left-style: " + str + ";\n");
			}
		} else if (decos.containsKey("border-style-left")) {
			String str = stringsub(decos.getStr("border-style-left"));
			if (str != null) {
				cssbuf.append("\tborder-left-style: " + str + ";\n");
			}
		}
		// border-right-style & border-style-right
		if (decos.containsKey("border-right-style")) { // TODO
			String str = stringsub(decos.getStr("border-right-style"));
			if (str != null) {
				cssbuf.append("\tborder-right-style: " + str + ";\n");
			}
		} else if (decos.containsKey("border-style-right")) {
			String str = stringsub(decos.getStr("border-style-right"));
			if (str != null) {
				cssbuf.append("\tborder-right-style: " + str + ";\n");
			}
		}
		// border-top-style & border-style-top
		if (decos.containsKey("border-top-style")) { // TODO
			String str = stringsub(decos.getStr("border-top-style"));
			if (str != null) {
				cssbuf.append("\tborder-top-style: " + str + ";\n");
			}
		} else if (decos.containsKey("border-style-top")) {
			String str = stringsub(decos.getStr("border-style-top"));
			if (str != null) {
				cssbuf.append("\tborder-top-style: " + str + ";\n");
			}
		}
		
		// border-width
		if (decos.containsKey("border-width")) { // TODO
			String str = stringsub(decos.getStr("border-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-width: " + str + ";\n");
				}
			}
		} else if (borderFlag) {
			cssbuf.append("\tborder-width: 1px;\n");
		}
		
		// border-bottom-width & border-width-bottom
		if (decos.containsKey("border-bottom-width")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-bottom-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-bottom-width: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("border-width-bottom")) {
			String str = stringsub(decos.getStr("border-width-bottom"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-bottom-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-bottom-width: " + str + ";\n");
				}
			}
		}
		// border-left-width & border-width-left
		if (decos.containsKey("border-left-width")) { // TODO
			String str = stringsub(decos.getStr("border-left-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-left-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-left-width: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("border-width-left")) {
			String str = stringsub(decos.getStr("border-width-left"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-left-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-left-width: " + str + ";\n");
				}
			}
		}
		// border-right-width & border-width-right
		if (decos.containsKey("border-right-width")) { // TODO
			String str = stringsub(decos.getStr("border-right-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-right-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-right-width: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("border-width-right")) { 
			String str = stringsub(decos.getStr("border-width-right"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-right-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-right-width: " + str + ";\n");
				}
			}
		}
		// border-top-width & border-width-top
		if (decos.containsKey("border-top-width")) { // TODO
			String str = stringsub(decos.getStr("border-top-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(decos.getStr("border-top-width"))) {
					cssbuf.append("\tborder-top-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-top-width: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("border-width-top")) { 
			String str = stringsub(decos.getStr("border-width-top"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tborder-top-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tborder-top-width: " + str + ";\n");
				}
			}
		}
		
		// box-decoration-break (CSS3)
		if (decos.containsKey("box-decoration-break")) { // TODO
			String str = stringsub(decos.getStr("box-decoration-break"));
			if (str != null) {
				cssbuf.append("\tbox-decoration-break: " + str + ";\n");
			}

		}
		
		// box-sizing (CSS3)
		if (decos.containsKey("box-sizing")) { // TODO
			String str = stringsub(decos.getStr("box-sizing"));
			if (str != null) {
				cssbuf.append("\tbox-sizing: " + str + ";\n");
			}

		}
		
		// caption-side & caption (only caption element)
		if (decos.containsKey("caption-side")) { // TODO
			String str = stringsub(decos.getStr("caption-side"));
			if (str != null) {
				cssbuf.append("\tcaption-side: " + str + ";\n");
			}
		} else if (decos.containsKey("caption")) {
			String str = stringsub(decos.getStr("caption"));
			if (str != null) {
				cssbuf.append("\tcaption-side: " + str + ";\n");
			}
		}
		
		// color & font-color
		if (decos.containsKey("color")) { // TODO
			String str = stringsub(decos.getStr("color"));
			if (str != null) {
				cssbuf.append("\tcolor: " + str + ";\n");
			}
		} else if (decos.containsKey("font-color")) {
			String str = stringsub(decos.getStr("font-color"));
			if (str != null) {
				cssbuf.append("\tcolor: " + str + ";\n");
			}
		}
		
		// column-count (CSS3)
		if (decos.containsKey("column-count")) { // TODO
			String str = stringsub(decos.getStr("column-count"));
			if (str != null) {
				cssbuf.append("\t-moz-column-count: " + str + ";\n");
				cssbuf.append("\t-webkit-column-count: " + str + ";\n");
				cssbuf.append("\tcolumn-count: " + str + ";\n");
			}
		}
		
		// column-fill (CSS3)
		if (decos.containsKey("column-fill")) { // TODO
			String str = stringsub(decos.getStr("column-fill"));
			if (str != null) {
				cssbuf.append("\t-moz-column-fill: " + str + ";\n");
				cssbuf.append("\t-webkit-column-fill: " + str + ";\n");
				cssbuf.append("\tcolumn-fill: " + str + ";\n");
			}

		}
		
		// column-gap (CSS3)
		if (decos.containsKey("column-gap")) { // TODO
			String str = stringsub(decos.getStr("column-gap"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\t-moz-column-gap: " + str + "px;\n");
					cssbuf.append("\t-webkit-column-gap: " + str + "px;\n");
					cssbuf.append("\tcolumn-gap: " + str + "px;\n");
				} else {
					cssbuf.append("\t-moz-column-gap: " + str + ";\n");
					cssbuf.append("\t-webkit-column-gap: " + str + ";\n");
					cssbuf.append("\tcolumn-gap: " + str + ";\n");
				}
			}
		}
		
		// column-rule-color (CSS3)
		if (decos.containsKey("column-rule-color")) { // TODO
			String str = stringsub(decos.getStr("column-rule-color"));
			if (str != null) {
				cssbuf.append("\t-moz-column-rule-color: " + str + ";\n");
				cssbuf.append("\t-webkit-column-rule-color: " + str + ";\n");
				cssbuf.append("\tcolumn-rule-color: " + str + ";\n");
			}
		}
		
		// column-rule-style (CSS3)
		if (decos.containsKey("column-rule-style")) { // TODO
			String str = stringsub(decos.getStr("column-rule-style"));
			if (str != null) {
				cssbuf.append("\t-moz-column-rule-style: " + str + ";\n");
				cssbuf.append("\t-webkit-column-rule-style: " + str + ";\n");
				cssbuf.append("\tcolumn-rule-style: " + str + ";\n");
			}
		}
		
		// column-rule-width (CSS3)
		if (decos.containsKey("column-rule-width")) { // TODO
			String str = stringsub(decos.getStr("column-rule-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\t-moz-column-rule-width: " + str + "px;\n");
					cssbuf.append("\t-webkit-column-rule-width: " + str + "px;\n");
					cssbuf.append("\tcolumn-rule-width: " + str + "px;\n");
				} else {
					cssbuf.append("\t-moz-column-rule-width: " + str + ";\n");
					cssbuf.append("\t-webkit-column-rule-width: " + str + ";\n");
					cssbuf.append("\tcolumn-rule-width: " + str + ";\n");
				}
			}
		}
		
		// column-span (CSS3)
		if (decos.containsKey("column-span")) { // TODO
			String str = stringsub(decos.getStr("column-span"));
			if (str != null) {
				cssbuf.append("\t-webkit-column-rule-width: " + str + ";\n");
				cssbuf.append("\tcolumn-rule-width: " + str + ";\n");
			}
		}
		
		// column-width (CSS3)
		if (decos.containsKey("column-width")) { // TODO
			String str = stringsub(decos.getStr("column-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\t-moz-column-width: " + str + "px;\n");
					cssbuf.append("\t-webkit-column-width: " + str + "px;\n");
					cssbuf.append("\tcolumn-width: " + str + "px;\n");
				} else {
					cssbuf.append("\t-moz-column-width: " + str + ";\n");
					cssbuf.append("\t-webkit-column-width: " + str + ";\n");
					cssbuf.append("\tcolumn-width: " + str + ";\n");
				}
			}
		}
		
		// counter-increment
		if (decos.containsKey("counter-increment")) { // TODO
			String str = stringsub(decos.getStr("counter-increment"));
			if (str != null) {
				cssbuf.append("\tcounter-increment: " + str + ";\n");
			}
		}
		
		// counter-reset
		if (decos.containsKey("counter-reset")) { // TODO
			String str = stringsub(decos.getStr("counter-reset"));
			if (str != null) {
				cssbuf.append("\tcounter-reset: " + str + ";\n");
			}
		}
		
		// cursor
		if (decos.containsKey("cursor")) { // TODO
			String str = stringsub(decos.getStr("cursor"));
			if (str != null) {
				cssbuf.append("\tcursor: " + str + ";\n");
			}
		}
		
		// direction
		if (decos.containsKey("direction")) { // TODO
			String str = stringsub(decos.getStr("direction"));
			if (str != null) {
				cssbuf.append("\tdirection: " + str + ";\n");
			}
		}
		
		// display
		if (decos.containsKey("display")) { // TODO
			String str = stringsub(decos.getStr("display"));
			if (str != null) {
				cssbuf.append("\tdisplay: " + str + ";\n");
			}
		}
		
		// empty-cells (only cell elements)
		if (decos.containsKey("empty-cells")) { // TODO
			String str = stringsub(decos.getStr("empty-cells"));
			if (str != null) {
				cssbuf.append("\tempty-cells: " + str + ";\n");
			}
		}
		
		// font
		if (decos.containsKey("font")) { // TODO
			String str = stringsub(decos.getStr("font"));
			if (str != null) {
				cssbuf.append("\tfont: " + str + ";\n");
			}
		}
		
		// font-family
		if (decos.containsKey("font-family")) { // TODO
			String str = stringsub(decos.getStr("font-family"));
			if (str != null) {
				cssbuf.append("\tfont-family: " + str + ";\n");
			}
		}
		
		// font-size & size
		if (decos.containsKey("font-size")) { // TODO
			String str = stringsub(decos.getStr("font-size"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tfont-size: " + str + "px;\n");
				} else {
					cssbuf.append("\tfont-size: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("size")) {
			String str = stringsub(decos.getStr("size"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tfont-size: " + str + "px;\n");
				} else {
					cssbuf.append("\tfont-size: " + str + ";\n");
				}
			}
		}
		
		// font-size-adjust
		if (decos.containsKey("font-size-adjust")) { // TODO
			String str = stringsub(decos.getStr("font-size-adjust"));
			if (str != null) {
				cssbuf.append("\tfont-size-adjust: " + str + ";\n");
			}
		}
		
		// font-stretch (CSS3)
		if (decos.containsKey("font-stretch")) { // TODO
			String str = stringsub(decos.getStr("font-stretch"));
			if (str != null) {
				cssbuf.append("\tfont-stretch: " + str + ";\n");
			}
		}
		
		// font-style
		if (decos.containsKey("font-style")) { // TODO
			String str = stringsub(decos.getStr("font-style"));
			if (str != null) {
				cssbuf.append("\tfont-style: " + str + ";\n");
			}
		}
		
		// font-variant
		if (decos.containsKey("font-variant")) { // TODO
			String str = stringsub(decos.getStr("font-variant"));
			if (str != null) {
				cssbuf.append("\tfont-variant: " + str + ";\n");
			}
		}
		
		// font-weight
		if (decos.containsKey("font-weight")) { // TODO
			String str = stringsub(decos.getStr("font-weight"));
			if (str != null) {
				cssbuf.append("\tfont-weight:" + str + ";\n");
			}
		}
		
		// height
		if (decos.containsKey("height")) { // TODO
			String str = stringsub(decos.getStr("height"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\theight: " + str + "px;\n");
				} else {
					cssbuf.append("\theight: " + str + ";\n");
				}
			}
		}
		
		// letter-spacing
		if (decos.containsKey("letter-spacing")) { // TODO
			String str = stringsub(decos.getStr("letter-spacing"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tletter-spacing: " + str + "px;\n");
				} else {
					cssbuf.append("\tletter-spacing: " + str + ";\n");
				}
			}
		}
		
		// line-break (CSS3)
		if (decos.containsKey("line-break")) { // TODO
			String str = stringsub(decos.getStr("line-break"));
			if (str != null) {
				cssbuf.append("\tline-break: " + str + ";\n");
			}
		}
		
		// line-height
		if (decos.containsKey("line-height")) { // TODO
			String str = stringsub(decos.getStr("line-height"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tline-height: " + str + "px;\n");
				} else {
					cssbuf.append("\tline-height: " + str + ";\n");
				}
			}
		}
		
		// list ""でバグる可能性あり
		if (decos.containsKey("list") || decos.containsKey("list-style-type") || decos.containsKey("list-style-image") || decos.containsKey("list-style-position")) { // TODO
			Log.out("********list start********");
			if (decos.containsKey("list")) {
				if (decos.getStr("list").equals("") || decos.getStr("list").equals("disc")) {
					listUlFlag = true;
					cssbuf.append("\tlist-style-type: disc;\n");
				} else if (decos.getStr("list").equals("none") || decos.getStr("list").equals("circle") || decos.getStr("list").equals("square")) {
					listUlFlag = true;
					cssbuf.append("\tlist-style-type: " + decos.getStr("list") + ";\n");
				} else if (decos.getStr("list").equals("number")) {
					listOlFlag = true;
					cssbuf.append("\tlist-style-type: decimal;\n");
				} else if (decos.getStr("list").equals("lower-roman") || decos.getStr("list").equals("upper-roman")
						|| decos.getStr("list").equals("lower-greek") || decos.getStr("list").equals("decimal")
						|| decos.getStr("list").equals("decimal-leading-zero") || decos.getStr("list").equals("lower-latin")
						|| decos.getStr("list").equals("lower-alpha") || decos.getStr("list").equals("upper-latin")
						|| decos.getStr("list").equals("upper-alpha") || decos.getStr("list").equals("cjk-ideographic")
						|| decos.getStr("list").equals("katakana") || decos.getStr("list").equals("hiragana")
						|| decos.getStr("list").equals("katakana-iroha") || decos.getStr("list").equals("hiragana-iroha")
						|| decos.getStr("list").equals("hebrew") || decos.getStr("list").equals("armenian")
						|| decos.getStr("list").equals("georgian")) {
					listOlFlag = true;
					cssbuf.append("\tlist-style-type: " + decos.getStr("list") + ";\n");
				}
			}
			if (decos.containsKey("list-style-type")) { // TODO
				if (decos.getStr("list-style-type").equals("") || decos.getStr("list-style-type").equals("disc")) {
					listUlFlag = true;
					cssbuf.append("\tlist-style-type: disc;\n");
				} else if (decos.getStr("list-style-type").equals("none") || decos.getStr("list-style-type").equals("circle") || decos.getStr("list-style-type").equals("square")) {
					listUlFlag = true;
					cssbuf.append("\tlist-style-type: " + decos.getStr("list-style-type") + ";\n");
				} else if (decos.getStr("list-style-type").equals("number")) {
					listOlFlag = true;
					cssbuf.append("\tlist-style-type: decimal;\n");
				} else if (decos.getStr("list-style-type").equals("lower-roman") || decos.getStr("list-style-type").equals("upper-roman")
						|| decos.getStr("list-style-type").equals("lower-greek") || decos.getStr("list-style-type").equals("decimal")
						|| decos.getStr("list-style-type").equals("decimal-leading-zero") || decos.getStr("list-style-type").equals("lower-latin")
						|| decos.getStr("list-style-type").equals("lower-alpha") || decos.getStr("list-style-type").equals("upper-latin")
						|| decos.getStr("list-style-type").equals("upper-alpha") || decos.getStr("list-style-type").equals("cjk-ideographic")
						|| decos.getStr("list-style-type").equals("katakana") || decos.getStr("list-style-type").equals("hiragana")
						|| decos.getStr("list-style-type").equals("katakana-iroha") || decos.getStr("list-style-type").equals("hiragana-iroha")
						|| decos.getStr("list-style-type").equals("hebrew") || decos.getStr("list-style-type").equals("armenian")
						|| decos.getStr("list-style-type").equals("georgian")) {
					listOlFlag = true;
					cssbuf.append("\tlist-style-type: " + decos.getStr("list-style-type") + ";\n");
				}
			}
			// list-style-image
			if (decos.containsKey("list-style-image")) { // TODO
				if (!listUlFlag && !listOlFlag) {
					listUlFlag = true;
				}
				String str = stringsub(decos.getStr("list-style-image"));
				if (str != null) {
					if ((str.indexOf("url") != -1) || str.equals("none") || str.equals("inherit")) {
						cssbuf.append("\tlist-style-image: " + str + ";\n");
					} else {
						cssbuf.append("\tlist-style-image: url(\"" + str + "\");\n");
					}
				}
			}		
			// list-style-position
			if (decos.containsKey("list-style-position")) { // TODO
				if (!listUlFlag && !listOlFlag) {
					listUlFlag = true;
				}
				String str = stringsub(decos.getStr("list-style-position"));
				if (str != null) {
					cssbuf.append("\tlist-style-position: " + decos.getStr("list-style-position") + ";\n");
				}
			}
		}
		
		// margin
		if (decos.containsKey("margin")) { // TODO
			String str = stringsub(decos.getStr("margin"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmargin: " + str + "px;\n");
				} else {
					cssbuf.append("\tmargin: " + str + ";\n");
				}
			}
		}
		// margin-bottom
		if (decos.containsKey("margin-bottom")) { // TODO
			String str = stringsub(decos.getStr("margin-bottom"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmargin-bottom: " + str + "px;\n");
				} else {
					cssbuf.append("\tmargin-bottom: " + str + ";\n");
				}
			}
		}
		// margin-left
		if (decos.containsKey("margin-left")) { // TODO
			String str = stringsub(decos.getStr("margin-left"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmargin-left: " + str + "px;\n");
				} else {
					cssbuf.append("\tmargin-left: " + str + ";\n");
				}
			} else if (!topLevelDiv) {
				cssbuf.append("\tmargin-left: auto;\n"); // topLevelDiv default
			}
		} else if (!topLevelDiv) {
			cssbuf.append("\tmargin-left: auto;\n"); // topLevelDiv default
		}
		
		// margin-right
		if (decos.containsKey("margin-right")) { // TODO
			String str = stringsub(decos.getStr("margin-right"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmargin-right: " + str + "px;\n");
				} else {
					cssbuf.append("\tmargin-right: " + str + ";\n");
				}
			} else if (!topLevelDiv) {
				cssbuf.append("\tmargin-right: auto;\n"); // topLevelDiv default
			}
		} else if (!topLevelDiv) {
			cssbuf.append("\tmargin-right: auto;\n"); // topLevelDiv default
		}		
		
		// margin-top
		if (decos.containsKey("margin-top")) { // TODO
			String str = stringsub(decos.getStr("margin-top"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmargin-top: " + str + "px;\n");
				} else {
					cssbuf.append("\tmargin-top: " + str + ";\n");
				}
			}
		}
		
		// max-height
		if (decos.containsKey("max-height")) { // TODO
			String str = stringsub(decos.getStr("max-height"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmax-height: " + str + "px;\n");
				} else {
					cssbuf.append("\tmax-height: " + str + ";\n");
				}
			}
		}
		// max-width
		if (decos.containsKey("max-width")) { // TODO
			String str = stringsub(decos.getStr("max-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmax-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tmax-width: " + str + ";\n");
				}
			}
		}
		
		// min-height
		if (decos.containsKey("min-height")) { // TODO
			String str = stringsub(decos.getStr("min-height"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmin-height: " + str + "px;\n");
				} else {
					cssbuf.append("\tmin-height: " + str + ";\n");
				}
			}
		}
		// min-width
		if (decos.containsKey("min-width")) { // TODO
			String str = stringsub(decos.getStr("min-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tmin-width: " + str + "px;\n");
				} else {
					cssbuf.append("\tmin-width: " + str + ";\n");
				}
			}
		}
		
		// opacity (CSS3)
		if (decos.containsKey("opacity")) { // TODO
			String str = stringsub(decos.getStr("opacity"));
			if (str != null) {
				cssbuf.append("\topacity: " + str + ";\n");
			}
		}
		
		// outline-color
		if (decos.containsKey("outline-color")) { // TODO
			String str = stringsub(decos.getStr("outline-color"));
			if (str != null) {
				cssbuf.append("\toutline-color: " + str + ";\n");
			}
		}
		
		// outline-style
		if (decos.containsKey("outline-style")) { // TODO\
			String str = stringsub(decos.getStr("outline-style"));
			if (str != null) {
				cssbuf.append("\toutline-style: " + str + ";\n");
			}
		}
		
		// outline-width
		if (decos.containsKey("outline-width")) { // TODO
			String str = stringsub(decos.getStr("outline-width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\toutline-width: " + str + "px;\n");
				} else {
					cssbuf.append("\toutline-width: " + str + ";\n");
				}
			}
		}
		
		// overflow
		if (decos.containsKey("overflow")) { // TODO
			String str = stringsub(decos.getStr("overflow"));
			if (str != null) {
				cssbuf.append("\toverflow: " + str + ";\n");
			}
		}
		
		// padding
		if (decos.containsKey("padding")) { // TODO
			String str = stringsub(decos.getStr("padding"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tpadding: " + str + "px;\n");
				} else {
					cssbuf.append("\tpadding: " + str + ";\n");
				}
			}
		}
		// padding-bottom
		if (decos.containsKey("padding-bottom")) { // TODO
			String str = stringsub(decos.getStr("padding-bottom"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tpadding-bottom: " + str + "px;\n");
				} else {
					cssbuf.append("\tpadding-bottom: " + str + ";\n");
				}
			}
		}
		// padding-left
		if (decos.containsKey("padding-left")) { // TODO
			String str = stringsub(decos.getStr("padding-left"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tpadding-left: " + str + "px;\n");
				} else {
					cssbuf.append("\tpadding-left: " + str + ";\n");
				}
			}
		}
		// padding-right
		if (decos.containsKey("padding-right")) { // TODO
			String str = stringsub(decos.getStr("padding-right"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tpadding-right: " + str + "px;\n");
				} else {
					cssbuf.append("\tpadding-right: " + str + ";\n");
				}
			}
		}
		// padding-top
		if (decos.containsKey("padding-top")) { // TODO
			String str = stringsub(decos.getStr("padding-top"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tpadding-top: " + str + "px;\n");
				} else {
					cssbuf.append("\tpadding-top: " + str + ";\n");
				}
			}
		}
		
		// position
		if (decos.containsKey("position")) { // TODO
			String str = stringsub(decos.getStr("position"));
			if (str != null) {
				cssbuf.append("\tposition: " + str + ";\n");
			}
		}
		// top (set position)
		if (decos.containsKey("position") && decos.containsKey("top")) { // TODO
			String str = stringsub(decos.getStr("top"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\ttop: " + str + "px;\n");
				} else {
					cssbuf.append("\ttop: " + str + ";\n");
				}
			}
		}
		// right (set position)
		if (decos.containsKey("position") && decos.containsKey("right")) { // TODO
			String str = stringsub(decos.getStr("right"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tright: " + str + "px;\n");
				} else {
					cssbuf.append("\tright: " + str + ";\n");
				}
			}
		}
		// bottom (set position)
		if (decos.containsKey("position") && decos.containsKey("bottom")) { // TODO
			String str = stringsub(decos.getStr("bottom"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tbottom: " + str + "px;\n");
				} else {
					cssbuf.append("\tbottom: " + str + ";\n");
				}
			}
		}
		// left (set position)
		if (decos.containsKey("position") && decos.containsKey("left")) { // TODO
			String str = stringsub(decos.getStr("left"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tleft: " + str + "px;\n");
				} else {
					cssbuf.append("\tleft: " + str + ";\n");
				}
			}
		}
		// z-index (set position)
		if (decos.containsKey("position") && decos.containsKey("z-index")) { // TODO
			String str = stringsub(decos.getStr("z-index"));
			if (str != null) {
				cssbuf.append("\tz-index: " + str + ";\n");
			}
		}
		
		// table-layout (only table element)
		if (decos.containsKey("table-layout")) { // TODO
			String str = stringsub(decos.getStr("table-layout"));
			if (str != null) {
				cssbuf.append("\ttable-layout: " + str + ";\n");
			}
		}
		
		// tab-size (CSS3)
		if (decos.containsKey("tab-size")) { // TODO
			String str = stringsub(decos.getStr("tab-size"));
			if (str != null) {
				cssbuf.append("\ttab-size: " + str + ";\n");
			}
		}
		
		// text-align & align
		if (decos.containsKey("text-align")) { // TODO
			String str = stringsub(decos.getStr("text-align"));
			if (str != null) {
				cssbuf.append("\ttext-align: " + str + ";\n");
			}
		} else if (decos.containsKey("align")) {
			String str = stringsub(decos.getStr("align"));
			if (str != null) {
				cssbuf.append("\ttext-align: " + str + ";\n");
			}
		}
		
		// text-align-last (CSS3)
		if (decos.containsKey("text-align-last")) { // TODO
			String str = stringsub(decos.getStr("text-align-last"));
			if (str != null) {
				cssbuf.append("\ttext-align-last: " + str + ";\n");
			}
		}
		
		// text-decoration & text-decoration-line (CSS3)
		if (decos.containsKey("text-decoration")) { // TODO
			String str = stringsub(decos.getStr("text-decoration"));
			if (str != null) {
				cssbuf.append("\ttext-decoration: " + str + ";\n");
			}
		} else if (decos.containsKey("text-decoration-line")) {
			String str = stringsub(decos.getStr("text-decoration-line"));
			if (str != null) {
				cssbuf.append("\t-moz-text-decoration-line: " + str + ";\n");
				cssbuf.append("\ttext-decoration-line: " + str + ";\n");
			}
		}
		
		// text-decoration-color (CSS3)
		if (decos.containsKey("text-decoration-color")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-color"));
			if (str != null) {
				cssbuf.append("\t-moz-text-decoration-color: " + str + ";\n");
				cssbuf.append("\ttext-decoration-color: " + str + ";\n");
			}
		}
		
		// text-decoration-skip (CSS3)
		if (decos.containsKey("text-decoration-skip")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-skip"));
			if (str != null) {
				cssbuf.append("\t-moz-text-decoration-skip: " + str + ";\n");
				cssbuf.append("\ttext-decoration-skip: " + str + ";\n");
			}
		}
		
		// text-decoration-style (CSS3)
		if (decos.containsKey("text-decoration-style")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-style"));
			if (str != null) {
				cssbuf.append("\t-moz-text-decoration-style: " + str + ";\n");
				cssbuf.append("\ttext-decoration-style: " + str + ";\n");
			}
		}
		
		// text-indent & indent
		if (decos.containsKey("text-indent")) { // TODO
			String str = stringsub(decos.getStr("text-indent"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\ttext-indent: " + str + "px;\n");
				} else {
					cssbuf.append("\ttext-indent: " + str + ";\n");
				}
			}
		} else if (decos.containsKey("indent")) {
			String str = stringsub(decos.getStr("indent"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\ttext-indent: " + str + "px;\n");
				} else {
					cssbuf.append("\ttext-indent: " + str + ";\n");
				}
			}
		}
		
		// text-justify (CSS3)
		if (decos.containsKey("text-justify")) {
			String str = stringsub(decos.getStr("text-justify"));
			if (str != null) {
				cssbuf.append("\ttext-justify: " + str + ";\n");
			}
		}
		
		// text-transform
		if (decos.containsKey("text-transform")) { // TODO
			String str = stringsub(decos.getStr("text-transform"));
			if (str != null) {
				cssbuf.append("\ttext-transform: " + str + ";\n");
			}
		}
		
		// text-underline-position (CSS3)
		if (decos.containsKey("text-underline-position")) { // TODO
			String str = stringsub(decos.getStr("text-underline-position"));
			if (str != null) {
				cssbuf.append("\t-moz-text-underline-position: " + str + ";\n");
				cssbuf.append("\ttext-underline-position: " + str + ";\n");
			}
		}
		
		// text-wrap (CSS3)
		if (decos.containsKey("text-wrap")) { // TODO
			String str = stringsub(decos.getStr("text-wrap"));
			if (str != null) {
				cssbuf.append("\ttext-wrap: " + str + ";\n");
			}
		}
		
		// unicode-bidi
		if (decos.containsKey("unicode-bidi")) { // TODO
			String str = stringsub(decos.getStr("unicode-bidi"));
			if (str != null) {
				cssbuf.append("\tunicode-bidi: " + str + ";\n");
			}
		}
		
		// virtical-align & valign
		// tableと分ける必要性あり
		if (decos.containsKey("virtical-align")) {  // TODO
			String str = stringsub(decos.getStr("virtical-align"));
			if (str != null) {
				if (str.equals("top")) {
					cssbuf.append("\t-webkit-align-self: flex-start;\n");
					cssbuf.append("\talign-self: flex-start;\n");
				} else if (str.equals("middle")) {
					cssbuf.append("\t-webkit-align-self: center;\n");
					cssbuf.append("\talign-self: center;\n");
				} else if (str.equals("bottom")) {
					cssbuf.append("\t-webkit-align-self: flex-end;\n");
					cssbuf.append("\talign-self: flex-end;\n");
				}
			}
		} else if (decos.containsKey("valign")) {
			String str = stringsub(decos.getStr("valign"));
			if (str != null) {
				if (str.equals("top")) {
					cssbuf.append("\t-webkit-align-self: flex-start;\n");
					cssbuf.append("\talign-self: flex-start;\n");
				} else if (str.equals("middle")) {
					cssbuf.append("\t-webkit-align-self: center;\n");
					cssbuf.append("\talign-self: center;\n");
				} else if (str.equals("bottom")) {
					cssbuf.append("\t-webkit-align-self: flex-end;\n");
					cssbuf.append("\talign-self: flex-end;\n");
				}
			}	
		}
		
		// visibility
		if (decos.containsKey("visibility")) { // TODO
			String str = stringsub(decos.getStr("visibility"));
			if (str != null) {
				cssbuf.append("\tvisibility: " + str + ";\n");
			}
		}
		
		// white-space
		if (decos.containsKey("white-space")) { // TODO
			String str = stringsub(decos.getStr("white-space"));
			if (str != null) {
				cssbuf.append("\twhite-space: " + str + ";\n");
			}
		}
		
		// width
		if (decos.containsKey("width")) { // TODO
			String str = stringsub(decos.getStr("width"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\twidth: " + str + "px;\n");
				} else {
					cssbuf.append("\twidth: " + str + ";\n");
				}
			} else {
				if (tableFlag) {
					cssbuf.append("\twidth: auto;\n"); // table default
				} else if (!topLevelDiv) {
					cssbuf.append("\tmax-width: 1280px;\n"); // topLevelDiv default
				} else {
//					cssbuf.append("\twidth: 100%;\n"); // default
				}
			}
		} else {
			if (tableFlag) {
				cssbuf.append("\twidth: auto;\n"); // table default
			} else if (!topLevelDiv) {
				cssbuf.append("\tmax-width: 1280px;\n"); // topLevelDiv default
			} else {
//				cssbuf.append("\twidth: 100%;\n"); // default
			}
		}
		
		// word-break (CSS3)
		if (decos.containsKey("word-break")) { // TODO
			String str = stringsub(decos.getStr("word-break"));
			if (str != null) {
				cssbuf.append("\tword-break: " + str + ";\n");
			}
		}
		
		// word-spacing
		if (decos.containsKey("word-spacing")) { // TODO
			String str = stringsub(decos.getStr("word-spacing"));
			if (str != null) {
				if (GlobalEnv.isNumber(str)) {
					cssbuf.append("\tword-spacing: " + str + "px;\n");
				} else {
					cssbuf.append("\tword-spacing: " + str + ";\n");
				}
			}
		}
		
		// word-wrap (CSS3)
		if (decos.containsKey("word-wrap")) { // TODO
			String str = stringsub(decos.getStr("word-wrap"));
			if (str != null) {
				cssbuf.append("\tword-wrap: " + str + ";\n");
			}
		}
		
		// これ以降CSSでないルール
		
		// classでclass名の変更
		if (decos.containsKey("class")) {
			boolean flag = true;
			String str = stringsub(decos.getStr("class"));
			for (int i = 0; i < cssClass.size(); i++) {
				if (cssClass.get(i).equals(str)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				cssClass.add(str);
			}
		}
		
		// styleでtemplate選択
		if (decos.containsKey("style")) {
			style = stringsub(decos.getStr("style"));
		}

		// tableタグで生成
		if (decos.containsKey("table")) { // TODO
			Log.out("********table start********");
			tableFlag = true;
		}
		
		if (cssbuf.length() > 0) { // css情報が1つ以上ある
			haveClass = 1;
			css.append("." + classid + "{\n");
			css.append(cssbuf);
			css.append("}\n");
			writtenClassId.addElement(classid);
		} else {
			Log.out("==> css style is null.");
			notWrittenClassId.addElement(classid);
		}
		
		if (!topLevelDiv) {
			topLevelDiv = true; // 一番親だけ特別なクラスをもたせるため
		}
	}
	
	public static String commonCSS() { // デフォルトのcss付与
		// TODO
		String s = "";
		if (!GlobalEnv.isOpt()) {
			if (headerFlag && footerFlag) {
//				s += "body {\n width: 1280px;\n margin-left: auto;\n margin-right: auto;\n padding-top: 30px;\n padding-bottom: 30px;\n}\n";
				s += ".header {\n position: fixed !important;\n position: absolute;\n top: 0;\n left: 0;\n width: 100%;\n height: 30px;\n background-color: #000000;\n color: #fff;\n}\n";
				s += ".footer {\n position: fixed !important;\n position: absolute;\n bottom: 0;\n left: 0;\n width: 100%;\n height: 30px;\n background-color: #000000;\n color: #fff;\n}\n";
			} else if (headerFlag) {
//				s += "body {\n width: 1280px;\n margin-left: auto;\n margin-right: auto;\n padding-top: 30px;\n}\n";
				s += ".header {\n position: fixed !important;\n position: absolute;\n top: 0;\n left: 0;\n width: 100%;\n height: 30px;\n background-color: #000000;\n color: #fff;\n}\n";
			} else if (footerFlag) {
//				s += "body {\n width: 1280px;\n margin-left: auto;\n margin-right: auto;\n padding-bottom: 30px;\n}\n";
				s += ".footer {\n position: fixed !important;\n position: absolute;\n bottom: 0;\n left: 0;\n width: 100%;\n height: 30px;\n background-color: #000000;\n color: #fff;\n}\n";
			} else {
//				s += "body {\n width: 1280px;\n margin-left: auto;\n margin-right: auto;\n}\n";
			}
			s += ".row {\n display: flex;\n flex-direction: row;\n text-align: center;\n}\n";
			s += ".col {\n display: flex;\n flex-direction: column;\n text-align: center;\n}\n";
			s += ".att {\n margin-left: auto;\n margin-right: auto;\n}\n";
		}
		return s;
	}
	
	public static String getClassID(ITFE tfe) {
		String result;
		
		// ここにC3, G3の何かが入る可能性
		
		result = "TFE" + tfe.getId();
		return result;
	}
	
	public static String stringsub(String value) {
		if (value.startsWith("'")) {
			value = value.substring(1, value.length()-1);
		}
		return value;
	}
	
	public void getHeader() { // ヘッダーの作成
		header.append("<html>\n");
		header.append("<head>\n");
		header_creation(); // ヘッダー情報の追加 cssなど
		header.append("</head>\n");
		if (style != null) {
			header.append("<body class=\"style-body\">");
		} else {
			header.append("<body>\n");
		}
	}
	
	public void getFooter() { // フッダーの作成
		footer.append("</body>\n");
		footer.append("</html>\n");
	}
	
	public void header_creation() { // css等詳細ヘッダーの追加
		header.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		header.append("<!-- Genarated CSS -->\n");
		header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + Jscss.getGenerateCssFileName(0) + "\">\n");
	}
	
	public static String cssTableInput(ArrayList<String> css) { // cssテーブルから出力
		ArrayList<CSSList> data = new ArrayList<CSSList>();
		ArrayList<ArrayList<CSSList>> group = new ArrayList<ArrayList<CSSList>>();
		ArrayList<CSS> csslist = new ArrayList<CSS>();
		
		try {
			// JDBCドライバの登録
			String driver = "org.postgresql.Driver";
			// データベースの指定
			String host = GlobalEnv.gethost();
			String dbname = GlobalEnv.getdbname();
//			String url = "jdbc:postgresql://" + host + "/" + dbname;
			String url = GlobalEnv.geturl();
			String user = GlobalEnv.getusername();
			
			Class.forName(driver);
			// データベースとの接続
			Connection con = DriverManager.getConnection(url, user, null);
			// テーブル照会実行
			Statement stmt = con.createStatement();
			
			String sql = "";
//			if (css.size() != 0) {
//				sql = sql + "SELECT * FROM style WHERE ";
//				for (int i = 0; i < css.size(); i++) {
//					sql = sql + "selector=\'." + css.get(i) + "\'";
//					if (i != (css.size()-1)) {
//						sql = sql + " OR ";
//					}
//				}
//			} else {
//				return "";
//			}
//			if (style != null && (css.size() != 0)) {
			if (style != null) {
//				sql = sql + "SELECT c.name as selector, d.property, d.value "
				sql = sql + "SELECT c.name as selector, d.property, d.value, cd.action "
						+ "FROM template t, component c, declaration d, tem_com tc, com_dec cd "
						+ "WHERE t.id=tc.tem_id AND c.id=tc.com_id AND c.id=cd.com_id AND d.id=cd.dec_id ";
				sql = sql + "AND t.name=\'" + style + "\' AND ("
						+ "c.name='style-body' OR "
						+ "c.name='style-row' OR "
						+ "c.name='style-col' OR "
						+ "c.name='style-att' OR "
						+ "c.name='style-table-row' OR "
						+ "c.name='style-list-row' OR "
						+ "c.name='style-table-col' OR "
						+ "c.name='style-list-col' OR "
						+ "c.name='style-table-att' OR "
						+ "c.name='style-list-att' OR "
						+ "c.name='style-img' OR "
						+ "c.name='style-line' OR "
						+ "c.name='style-anchor'";
				for (int i = 0; i < css.size(); i++) {
					if (i == 0) {
						sql = sql + " OR ";
					}
					sql = sql + "c.name=\'" + css.get(i) + "\'";
					if (i != (css.size()-1)) {
						sql = sql + " OR ";
					}
				}
				sql = sql + ")";
			} else {
				return "";
			}
			Log.info("[CSStablequery]: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			// テーブル照会結果を出力
			while(rs.next()) {
				String selector = "." + rs.getString("selector");
				if (!rs.getString("action").equals("")) {
					selector = selector + ":" + rs.getString("action");
				};
				String property = rs.getString("property");
				String value = rs.getString("value");
				CSSList getcss = new CSSList(selector, property, value);
				data.add(getcss);
			}
			// データベースのクローズ
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			Log.err(e);
		} catch (ClassNotFoundException ex) {
			Log.err(ex);
		}
		
		// ログ(後で消す)
		Log.out("[ArrayList<CSSList>]");
		for(int i = 0; i < data.size(); i++) {
			Log.out("[ [" + data.get(i).getSelector() + "] , [" + data.get(i).getProperty() + "] , [" + data.get(i).getValue() + "] ]");
		}
		Log.out("");
		
		while (data.size() != 0) {
			ArrayList<CSSList> tmp_list = new ArrayList();
			tmp_list.add(data.get(0));
			
			String tmp_property = data.get(0).getProperty();
			String tmp_value = data.get(0).getValue();
			boolean flag;
			for (int i = 1; i < data.size(); i++) {
				do {
					flag = false;
					if (data.size() != i) {
						if (tmp_property.equals(data.get(i).getProperty()) && tmp_value.equals(data.get(i).getValue())) {
							tmp_list.add(data.get(i));
							data.remove(i);
							flag = true;
						}
					}
				} while (flag);
			}
			
			group.add(tmp_list);
			data.remove(0);
		}
		
		// ログ(後で消す)
		Log.out("[ArrayList<ArrayList<CSSList>>]");
		for (int i = 0; i < group.size(); i++) {
			Log.out("<Group> -> [ [property: " + group.get(i).get(0).getProperty() + "], [value: " + group.get(i).get(0).getValue() + "] ]");
			for (int j = 0; j < group.get(i).size(); j++) {
				Log.out(group.get(i).get(j).getSelector() + " ");
			}
			Log.out("");
		}
		Log.out("");
		
		while (group.size() != 0) {
			ArrayList<String> tmp_selector = new ArrayList<String>();
			ArrayList<ArrayList<String>> tmp_element = new ArrayList<ArrayList<String>>();
			
			for (int i = 0; i < group.get(0).size(); i++) {
				tmp_selector.add(group.get(0).get(i).getSelector());
			}
			ArrayList<String> property_value = new ArrayList<String>();
			property_value.add(group.get(0).get(0).getProperty());
			property_value.add(group.get(0).get(0).getValue());
			tmp_element.add(property_value);
			
			boolean flag;
			for (int i = 1; i < group.size(); i++) {
				do {
					flag = false;
					if (group.size() != i) {
						if (tmp_selector.size() == group.get(i).size()) {
							boolean allmatch_flag = true;
							for (int j = 0; j < tmp_selector.size(); j++) {
								boolean match_flag = false;
								for (int k = 0; k < group.get(i).size(); k++) {
									if (tmp_selector.get(j).equals(group.get(i).get(k).getSelector())) {
										match_flag = true;
										break;
									}
								}
								if (!match_flag) {
									allmatch_flag = false;
								}
							}
							if (allmatch_flag) {
								ArrayList<String> property_value2 = new ArrayList<String>();
								property_value2.add(group.get(i).get(0).getProperty());
								property_value2.add(group.get(i).get(0).getValue());
								tmp_element.add(property_value2);
								group.remove(i);
								flag = true;
							}
						}
					}
				} while (flag);
			}
			CSS tmp_css = new CSS(tmp_selector, tmp_element);
			csslist.add(tmp_css);
			group.remove(0);
		}
		
		// ログ(後で消す)
		Log.out("");
		Log.out("[ArrayList<CSS>]");
		for (int i = 0; i < csslist.size(); i++) {
			Log.out("<selector> -> ");
			for (int j = 0; j < csslist.get(i).selectorSize(); j++) {
				Log.out(csslist.get(i).getSelector(j) + " ");
			}
			Log.out("");
			Log.out("<element>");
			for (int j = 0; j < csslist.get(i).elementSize(); j++) {
				Log.out("\t" + csslist.get(i).getProperty(j) + ": " + csslist.get(i).getValue(j) + ";");
			}
		}
		
		// cssファイル書き出し
		StringBuffer cssbuf = new StringBuffer();
		for (int i = 0; i < csslist.size(); i++) {
			String tmp = "";
			for (int j = 0; j < csslist.get(i).selectorSize(); j++) {
				tmp = tmp + csslist.get(i).getSelector(j);
				if ((j+1) == csslist.get(i).selectorSize()) {
					tmp = tmp + " {\n";
				} else {
					tmp = tmp + ", ";
				}
			}
			cssbuf.append(tmp);
			tmp = "";
			for (int j = 0; j < csslist.get(i).elementSize(); j++) {
				cssbuf.append("\t" + csslist.get(i).getProperty(j) + ": " + csslist.get(i).getValue(j) + ";\n");
			}
			cssbuf.append("}\n");
		}
		String str = cssbuf.toString();
 		return str;
	}

}
