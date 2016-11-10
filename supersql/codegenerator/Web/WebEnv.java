package supersql.codegenerator.Web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public boolean decorationFlag = false;
	public boolean decorationStartFlag = false;
	public boolean decorationEndFlag = false;
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
	
	public void append_css_def_att(String classid, DecorateList decos) {
		Log.out("[Web append_css_def_att] classid = " + classid);
		Log.out("decos = " + decos);
		
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
			cssbuf.append("\tbackground-attachment: " + str + ";\n");
		}
		
		// background-clip (CSS3)
		if (decos.containsKey("background-clip")) { // TODO
			String str = stringsub(decos.getStr("background-clip"));
			cssbuf.append("\tbackground-clip: " + str + ";\n");
		}
		
		// background-color & bgcolor
		if (decos.containsKey("background-color")) { // TODO
			String str = stringsub(decos.getStr("background-color"));
			cssbuf.append("\tbackground-color: " + str + ";\n");
		} else if (decos.containsKey("bgcolor")) {
			String str = stringsub(decos.getStr("bgcolor"));
			cssbuf.append("\tbackground-color: " + str + ";\n");
		}
		
		// background-image
		if (decos.containsKey("background-image")) { // TODO
			String str = stringsub(decos.getStr("background-image"));
			if ((str.indexOf("url") != -1) || str.equals("none") || str.equals("inherit")) {
				cssbuf.append("\tbackground-image: " + str + ";\n");
			} else {
				cssbuf.append("\tbackground-image: url(\"" + str + "\");\n");
			}
		}
		
		// background-origin (CSS3)
		if (decos.containsKey("background-origin")) { // TODO
			String str = stringsub(decos.getStr("background-origin"));
			cssbuf.append("\tbackground-origin: " + str + ";\n");
		}
		
		// background-repeat
		if (decos.containsKey("background-repeat")) { // TODO
			String str = stringsub(decos.getStr("background-repeat"));
			cssbuf.append("\tbackground-repeat: " + str + ";\n");
		}
		
		// background-size (CSS3)
		if (decos.containsKey("background-size")) { // TODO
			String str = stringsub(decos.getStr("background-size"));
			if (isNumber(str)) {
				cssbuf.append("\tbackground-size: " + str + "px;\n");
			} else {
				cssbuf.append("\tbackground-size: " + str + ";\n");
			}
		}
		
		// border
		if (decos.containsKey("border")) { // TODO
			Log.out("****** div border on ******");
			borderFlag = true;
		}
		
		// border-collapse (only table element)
		if (decos.containsKey("border-collapse")) { // TODO
			String str = stringsub(decos.getStr("border-collapse"));
			cssbuf.append("\tborder-collapse: " + str + ";\n");
		}
		
		// border-color
		if (decos.containsKey("border-color")) { // TODO
			String str = stringsub(decos.getStr("border-color"));
			cssbuf.append("\tborder-color: " + str + ";\n");
		} else if (borderFlag) {
			cssbuf.append("\tborder-color: #F0F0F0;\n");
		}
		
		// border-bottom-color & border-color-bottom
		if (decos.containsKey("border-bottom-color")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-color"));
			cssbuf.append("\tborder-bottom-color: " + str + ";\n");
		} else if (decos.containsKey("border-color-bottom")) {
			String str = stringsub(decos.getStr("border-color-bottom"));
			cssbuf.append("\tborder-bottom-color: " + str + ";\n");
		}
		// border-left-color & border-color-left
		if (decos.containsKey("border-left-color")) { // TODO
			String str = stringsub(decos.getStr("border-left-color"));
			cssbuf.append("\tborder-left-color: " + str + ";\n");
		} else if (decos.containsKey("border-color-left")) {
			String str = stringsub(decos.getStr("border-color-left"));
			cssbuf.append("\tborder-left-color: " + str + ";\n");
		}
		// border-right-color & border-color-right
		if (decos.containsKey("border-right-color")) { // TODO
			String str = stringsub(decos.getStr("border-right-color"));
			cssbuf.append("\tborder-right-color: " + str + ";\n");
		} else if (decos.containsKey("border-color-right")) {
			String str = stringsub(decos.getStr("border-color-right"));
			cssbuf.append("\tborder-right-color: " + str + ";\n");
		}
		// border-top-color & border-color-top
		if (decos.containsKey("border-top-color")) { // TODO
			String str = stringsub(decos.getStr("border-top-color"));
			cssbuf.append("\tborder-top-color: " + str + ";\n");
		} else if (decos.containsKey("border-color-top")) {
			String str = stringsub(decos.getStr("border-color-top"));
			cssbuf.append("\tborder-top-color: " + str + ";\n");
		}
		
		// border-radius (CSS3)
		if (decos.containsKey("border-radius")) { // TODO
			String str = stringsub(decos.getStr("border-radius"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-radius: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-radius: " + str + ";\n");
			}
		}
		
		// border-spacing (only table element)
		if (decos.containsKey("border-spacing")) { // TODO
			String str = stringsub(decos.getStr("border-spacing"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-spacing: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-spacing: " + str + ";\n");
			}
		}
		
		// border-style
		if (decos.containsKey("border-style")) { // TODO
			String str = stringsub(decos.getStr("border-style"));
			cssbuf.append("\tborder-style: " + str + ";\n");
		} else if (borderFlag) {
			cssbuf.append("\tborder-style: solid;\n");
		}
		
		// border-bottom-style & border-style-bottom
		if (decos.containsKey("border-bottom-style")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-style"));
			cssbuf.append("\tborder-bottom-style: " + str + ";\n");
		} else if (decos.containsKey("border-style-bottom")) {
			String str = stringsub(decos.getStr("border-style-bottom"));
			cssbuf.append("\tborder-bottom-style: " + str + ";\n");
		}
		// border-left-style & border-style-left
		if (decos.containsKey("border-left-style")) { // TODO
			String str = stringsub(decos.getStr("border-left-style"));
			cssbuf.append("\tborder-left-style: " + str + ";\n");
		} else if (decos.containsKey("border-style-left")) {
			String str = stringsub(decos.getStr("border-style-left"));
			cssbuf.append("\tborder-left-style: " + str + ";\n");
		}
		// border-right-style & border-style-right
		if (decos.containsKey("border-right-style")) { // TODO
			String str = stringsub(decos.getStr("border-right-style"));
			cssbuf.append("\tborder-right-style: " + str + ";\n");
		} else if (decos.containsKey("border-style-right")) {
			String str = stringsub(decos.getStr("border-style-right"));
			cssbuf.append("\tborder-right-style: " + str + ";\n");
		}
		// border-top-style & border-style-top
		if (decos.containsKey("border-top-style")) { // TODO
			String str = stringsub(decos.getStr("border-top-style"));
			cssbuf.append("\tborder-top-style: " + str + ";\n");
		} else if (decos.containsKey("border-style-top")) {
			String str = stringsub(decos.getStr("border-style-top"));
			cssbuf.append("\tborder-top-style: " + str + ";\n");
		}
		
		// border-width
		if (decos.containsKey("border-width")) { // TODO
			String str = stringsub(decos.getStr("border-width"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-width: " + str + ";\n");
			}
		} else if (borderFlag) {
			cssbuf.append("\tborder-width: 1px;\n");
		}
		
		// border-bottom-width & border-width-bottom
		if (decos.containsKey("border-bottom-width")) { // TODO
			String str = stringsub(decos.getStr("border-bottom-width"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-bottom-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-bottom-width: " + str + ";\n");
			}
		} else if (decos.containsKey("border-width-bottom")) {
			String str = stringsub(decos.getStr("border-width-bottom"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-bottom-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-bottom-width: " + str + ";\n");
			}
		}
		// border-left-width & border-width-left
		if (decos.containsKey("border-left-width")) { // TODO
			String str = stringsub(decos.getStr("border-left-width"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-left-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-left-width: " + str + ";\n");
			}
		} else if (decos.containsKey("border-width-left")) {
			String str = stringsub(decos.getStr("border-width-left"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-left-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-left-width: " + str + ";\n");
			}
		}
		// border-right-width & border-width-right
		if (decos.containsKey("border-right-width")) { // TODO
			String str = stringsub(decos.getStr("border-right-width"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-right-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-right-width: " + str + ";\n");
			}
		} else if (decos.containsKey("border-width-right")) { 
			String str = stringsub(decos.getStr("border-width-right"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-right-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-right-width: " + str + ";\n");
			}
		}
		// border-top-width & border-width-top
		if (decos.containsKey("border-top-width")) { // TODO
			String str = stringsub(decos.getStr("border-top-width"));
			if (isNumber(decos.getStr("border-top-width"))) {
				cssbuf.append("\tborder-top-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-top-width: " + str + ";\n");
			}
		} else if (decos.containsKey("border-width-top")) { 
			String str = stringsub(decos.getStr("border-width-top"));
			if (isNumber(str)) {
				cssbuf.append("\tborder-top-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tborder-top-width: " + str + ";\n");
			}
		}
		
		// box-decoration-break (CSS3)
		if (decos.containsKey("box-decoration-break")) { // TODO
			String str = stringsub(decos.getStr("box-decoration-break"));
			cssbuf.append("\tbox-decoration-break: " + str + ";\n");
		}
		
		// box-sizing (CSS3)
		if (decos.containsKey("box-sizing")) { // TODO
			String str = stringsub(decos.getStr("box-sizing"));
			cssbuf.append("\tbox-sizing: " + str + ";\n");
		}
		
		// caption-side & caption (only caption element)
		if (decos.containsKey("caption-side")) { // TODO
			String str = stringsub(decos.getStr("caption-side"));
			cssbuf.append("\tcaption-side: " + str + ";\n");
		} else if (decos.containsKey("caption")) {
			String str = stringsub(decos.getStr("caption"));
			cssbuf.append("\tcaption-side: " + str + ";\n");
		}
		
		// color & font-color
		if (decos.containsKey("color")) { // TODO
			String str = stringsub(decos.getStr("color"));
			cssbuf.append("\tcolor: " + str + ";\n");
		} else if (decos.containsKey("font-color")) {
			String str = stringsub(decos.getStr("font-color"));
			cssbuf.append("\tcolor: " + str + ";\n");
		}
		
		// column-count (CSS3)
		if (decos.containsKey("column-count")) { // TODO
			String str = stringsub(decos.getStr("column-count"));
			cssbuf.append("\t-moz-column-count: " + str + ";\n");
			cssbuf.append("\t-webkit-column-count: " + str + ";\n");
			cssbuf.append("\tcolumn-count: " + str + ";\n");
		}
		
		// column-fill (CSS3)
		if (decos.containsKey("column-fill")) { // TODO
			String str = stringsub(decos.getStr("column-fill"));
			cssbuf.append("\t-moz-column-fill: " + str + ";\n");
			cssbuf.append("\t-webkit-column-fill: " + str + ";\n");
			cssbuf.append("\tcolumn-fill: " + str + ";\n");
		}
		
		// column-gap (CSS3)
		if (decos.containsKey("column-gap")) { // TODO
			String str = stringsub(decos.getStr("column-gap"));
			if (isNumber(str)) {
				cssbuf.append("\t-moz-column-gap: " + str + "px;\n");
				cssbuf.append("\t-webkit-column-gap: " + str + "px;\n");
				cssbuf.append("\tcolumn-gap: " + str + "px;\n");
			} else {
				cssbuf.append("\t-moz-column-gap: " + str + ";\n");
				cssbuf.append("\t-webkit-column-gap: " + str + ";\n");
				cssbuf.append("\tcolumn-gap: " + str + ";\n");
			}
		}
		
		// column-rule-color (CSS3)
		if (decos.containsKey("column-rule-color")) { // TODO
			String str = stringsub(decos.getStr("column-rule-color"));
			cssbuf.append("\t-moz-column-rule-color: " + str + ";\n");
			cssbuf.append("\t-webkit-column-rule-color: " + str + ";\n");
			cssbuf.append("\tcolumn-rule-color: " + str + ";\n");
		}
		
		// column-rule-style (CSS3)
		if (decos.containsKey("column-rule-style")) { // TODO
			String str = stringsub(decos.getStr("column-rule-style"));
			cssbuf.append("\t-moz-column-rule-style: " + str + ";\n");
			cssbuf.append("\t-webkit-column-rule-style: " + str + ";\n");
			cssbuf.append("\tcolumn-rule-style: " + str + ";\n");
		}
		
		// column-rule-width (CSS3)
		if (decos.containsKey("column-rule-width")) { // TODO
			String str = stringsub(decos.getStr("column-rule-width"));
			if (isNumber(str)) {
				cssbuf.append("\t-moz-column-rule-width: " + str + "px;\n");
				cssbuf.append("\t-webkit-column-rule-width: " + str + "px;\n");
				cssbuf.append("\tcolumn-rule-width: " + str + "px;\n");
			} else {
				cssbuf.append("\t-moz-column-rule-width: " + str + ";\n");
				cssbuf.append("\t-webkit-column-rule-width: " + str + ";\n");
				cssbuf.append("\tcolumn-rule-width: " + str + ";\n");
			}
		}
		
		// column-span (CSS3)
		if (decos.containsKey("column-span")) { // TODO
			String str = stringsub(decos.getStr("column-span"));
			cssbuf.append("\t-webkit-column-rule-width: " + str + ";\n");
			cssbuf.append("\tcolumn-rule-width: " + str + ";\n");
		}
		
		// column-width (CSS3)
		if (decos.containsKey("column-width")) { // TODO
			String str = stringsub(decos.getStr("column-width"));
			if (isNumber(str)) {
				cssbuf.append("\t-moz-column-width: " + str + "px;\n");
				cssbuf.append("\t-webkit-column-width: " + str + "px;\n");
				cssbuf.append("\tcolumn-width: " + str + "px;\n");
			} else {
				cssbuf.append("\t-moz-column-width: " + str + ";\n");
				cssbuf.append("\t-webkit-column-width: " + str + ";\n");
				cssbuf.append("\tcolumn-width: " + str + ";\n");
			}
		}
		
		// counter-increment
		if (decos.containsKey("counter-increment")) { // TODO
			String str = stringsub(decos.getStr("counter-increment"));
			cssbuf.append("\tcounter-increment: " + str + ";\n");
		}
		
		// counter-reset
		if (decos.containsKey("counter-reset")) { // TODO
			String str = stringsub(decos.getStr("counter-reset"));
			cssbuf.append("\tcounter-reset: " + str + ";\n");
		}
		
		// cursor
		if (decos.containsKey("cursor")) { // TODO
			String str = stringsub(decos.getStr("cursor"));
			cssbuf.append("\tcursor: " + str + ";\n");
		}
		
		// direction
		if (decos.containsKey("direction")) { // TODO
			String str = stringsub(decos.getStr("direction"));
			cssbuf.append("\tdirection: " + str + ";\n");
		}
		
		// display
		if (decos.containsKey("display")) { // TODO
			String str = stringsub(decos.getStr("display"));
			cssbuf.append("\tdisplay: " + str + ";\n");
		}
		
		// empty-cells (only cell elements)
		if (decos.containsKey("empty-cells")) { // TODO
			String str = stringsub(decos.getStr("empty-cells"));
			cssbuf.append("\tempty-cells: " + str + ";\n");
		}
		
		// font
		if (decos.containsKey("font")) { // TODO
			String str = stringsub(decos.getStr("font"));
			cssbuf.append("\tfont: " + str + ";\n");
		}
		
		// font-family
		if (decos.containsKey("font-family")) { // TODO
			String str = stringsub(decos.getStr("font-family"));
			cssbuf.append("\tfont-family: " + str + ";\n");
		}
		
		// font-size & size
		if (decos.containsKey("font-size")) { // TODO
			String str = stringsub(decos.getStr("font-size"));
			if (isNumber(str)) {
				cssbuf.append("\tfont-size: " + str + "px;\n");
			} else {
				cssbuf.append("\tfont-size: " + str + ";\n");
			}
		} else if (decos.containsKey("size")) {
			String str = stringsub(decos.getStr("size"));
			if (isNumber(str)) {
				cssbuf.append("\tfont-size: " + str + "px;\n");
			} else {
				cssbuf.append("\tfont-size: " + str + ";\n");
			}
		}
		
		// font-size-adjust
		if (decos.containsKey("font-size-adjust")) { // TODO
			String str = stringsub(decos.getStr("font-size-adjust"));
			cssbuf.append("\tfont-size-adjust: " + str + ";\n");
		}
		
		// font-stretch (CSS3)
		if (decos.containsKey("font-stretch")) { // TODO
			String str = stringsub(decos.getStr("font-stretch"));
			cssbuf.append("\tfont-stretch: " + str + ";\n");
		}
		
		// font-style
		if (decos.containsKey("font-style")) { // TODO
			String str = stringsub(decos.getStr("font-style"));
			cssbuf.append("\tfont-style: " + str + ";\n");
		}
		
		// font-variant
		if (decos.containsKey("font-variant")) { // TODO
			String str = stringsub(decos.getStr("font-variant"));
			cssbuf.append("\tfont-variant: " + str + ";\n");
		}
		
		// font-weight
		if (decos.containsKey("font-weight")) { // TODO
			String str = stringsub(decos.getStr("font-weight"));
			cssbuf.append("\tfont-weight:" + str + ";\n");
		}
		
		// height
		if (decos.containsKey("height")) { // TODO
			String str = stringsub(decos.getStr("height"));
			if (isNumber(str)) {
				cssbuf.append("\theight: " + str + "px;\n");
			} else {
				cssbuf.append("\theight: " + str + ";\n");
			}
		}
		
		// letter-spacing
		if (decos.containsKey("letter-spacing")) { // TODO
			String str = stringsub(decos.getStr("letter-spacing"));
			if (isNumber(str)) {
				cssbuf.append("\tletter-spacing: " + str + "px;\n");
			} else {
				cssbuf.append("\tletter-spacing: " + str + ";\n");
			}
		}
		
		// line-break (CSS3)
		if (decos.containsKey("line-break")) { // TODO
			String str = stringsub(decos.getStr("line-break"));
			cssbuf.append("\tline-break: " + str + ";\n");
		}
		
		// line-height
		if (decos.containsKey("line-height")) { // TODO
			String str = stringsub(decos.getStr("line-height"));
			if (isNumber(str)) {
				cssbuf.append("\tline-height: " + str + "px;\n");
			} else {
				cssbuf.append("\tline-height: " + str + ";\n");
			}
		}
		
		// list
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
				if ((decos.getStr("list-style-image").indexOf("url") != -1) || decos.getStr("list-style-image").equals("none") || decos.getStr("list-style-image").equals("inherit")) {
					cssbuf.append("\tlist-style-image: " + decos.getStr("list-style-image") + ";\n");
				} else {
					cssbuf.append("\tlist-style-image: url(\"" + decos.getStr("list-style-image") + "\");\n");
				}
			}		
			// list-style-position
			if (decos.containsKey("list-style-position")) { // TODO
				if (!listUlFlag && !listOlFlag) {
					listUlFlag = true;
				}
				cssbuf.append("\tlist-style-position: " + decos.getStr("list-style-position") + ";\n");
			}
		}
		
		// margin
		if (decos.containsKey("margin")) { // TODO
			String str = stringsub(decos.getStr("margin"));
			if (isNumber(str)) {
				cssbuf.append("\tmargin: " + str + "px;\n");
			} else {
				cssbuf.append("\tmargin: " + str + ";\n");
			}
		}
		// margin-bottom
		if (decos.containsKey("margin-bottom")) { // TODO
			String str = stringsub(decos.getStr("margin-bottom"));
			if (isNumber(str)) {
				cssbuf.append("\tmargin-bottom: " + str + "px;\n");
			} else {
				cssbuf.append("\tmargin-bottom: " + str + ";\n");
			}
		}
		// margin-left
		if (decos.containsKey("margin-left")) { // TODO
			String str = stringsub(decos.getStr("margin-left"));
			if (isNumber(str)) {
				cssbuf.append("\tmargin-left: " + str + "px;\n");
			} else {
				cssbuf.append("\tmargin-left: " + str + ";\n");
			}
		} else if (!topLevelDiv) {
			cssbuf.append("\tmargin-left: auto;\n"); // topLevelDiv default
		}
		
		// margin-right
		if (decos.containsKey("margin-right")) { // TODO
			String str = stringsub(decos.getStr("margin-right"));
			if (isNumber(str)) {
				cssbuf.append("\tmargin-right: " + str + "px;\n");
			} else {
				cssbuf.append("\tmargin-right: " + str + ";\n");
			}
		} else if (!topLevelDiv) {
			cssbuf.append("\tmargin-right: auto;\n"); // topLevelDiv default
		}		
		
		// margin-top
		if (decos.containsKey("margin-top")) { // TODO
			String str = stringsub(decos.getStr("margin-top"));
			if (isNumber(str)) {
				cssbuf.append("\tmargin-top: " + str + "px;\n");
			} else {
				cssbuf.append("\tmargin-top: " + str + ";\n");
			}
		}
		
		// max-height
		if (decos.containsKey("max-height")) { // TODO
			String str = stringsub(decos.getStr("max-height"));
			if (isNumber(str)) {
				cssbuf.append("\tmax-height: " + str + "px;\n");
			} else {
				cssbuf.append("\tmax-height: " + str + ";\n");
			}
		}
		// max-width
		if (decos.containsKey("max-width")) { // TODO
			String str = stringsub(decos.getStr("max-width"));
			if (isNumber(str)) {
				cssbuf.append("\tmax-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tmax-width: " + str + ";\n");
			}
		}
		
		// min-height
		if (decos.containsKey("min-height")) { // TODO
			String str = stringsub(decos.getStr("min-height"));
			if (isNumber(str)) {
				cssbuf.append("\tmin-height: " + str + "px;\n");
			} else {
				cssbuf.append("\tmin-height: " + str + ";\n");
			}
		}
		// min-width
		if (decos.containsKey("min-width")) { // TODO
			String str = stringsub(decos.getStr("min-width"));
			if (isNumber(str)) {
				cssbuf.append("\tmin-width: " + str + "px;\n");
			} else {
				cssbuf.append("\tmin-width: " + str + ";\n");
			}
		}
		
		// opacity (CSS3)
		if (decos.containsKey("opacity")) { // TODO
			String str = stringsub(decos.getStr("opacity"));
			cssbuf.append("\topacity: " + str + ";\n");
		}
		
		// outline-color
		if (decos.containsKey("outline-color")) { // TODO
			String str = stringsub(decos.getStr("outline-color"));
			cssbuf.append("\toutline-color: " + str + ";\n");
		}
		
		// outline-style
		if (decos.containsKey("outline-style")) { // TODO\
			String str = stringsub(decos.getStr("outline-style"));
			cssbuf.append("\toutline-style: " + str + ";\n");
		}
		
		// outline-width
		if (decos.containsKey("outline-width")) { // TODO
			String str = stringsub(decos.getStr("outline-width"));
			if (isNumber(str)) {
				cssbuf.append("\toutline-width: " + str + "px;\n");
			} else {
				cssbuf.append("\toutline-width: " + str + ";\n");
			}
		}
		
		// overflow
		if (decos.containsKey("overflow")) { // TODO
			String str = stringsub(decos.getStr("overflow"));
			cssbuf.append("\toverflow: " + str + ";\n");
		}
		
		// padding
		if (decos.containsKey("padding")) { // TODO
			String str = stringsub(decos.getStr("padding"));
			if (isNumber(str)) {
				cssbuf.append("\tpadding: " + str + "px;\n");
			} else {
				cssbuf.append("\tpadding: " + str + ";\n");
			}
		}
		// padding-bottom
		if (decos.containsKey("padding-bottom")) { // TODO
			String str = stringsub(decos.getStr("padding-bottom"));
			if (isNumber(str)) {
				cssbuf.append("\tpadding-bottom: " + str + "px;\n");
			} else {
				cssbuf.append("\tpadding-bottom: " + str + ";\n");
			}
		}
		// padding-left
		if (decos.containsKey("padding-left")) { // TODO
			String str = stringsub(decos.getStr("padding-left"));
			if (isNumber(str)) {
				cssbuf.append("\tpadding-left: " + str + "px;\n");
			} else {
				cssbuf.append("\tpadding-left: " + str + ";\n");
			}
		}
		// padding-right
		if (decos.containsKey("padding-right")) { // TODO
			String str = stringsub(decos.getStr("padding-right"));
			if (isNumber(str)) {
				cssbuf.append("\tpadding-right: " + str + "px;\n");
			} else {
				cssbuf.append("\tpadding-right: " + str + ";\n");
			}
		}
		// padding-top
		if (decos.containsKey("padding-top")) { // TODO
			String str = stringsub(decos.getStr("padding-top"));
			if (isNumber(str)) {
				cssbuf.append("\tpadding-top: " + str + "px;\n");
			} else {
				cssbuf.append("\tpadding-top: " + str + ";\n");
			}
		}
		
		// position
		if (decos.containsKey("position")) { // TODO
			String str = stringsub(decos.getStr("position"));
			cssbuf.append("\tposition: " + str + ";\n");
		}
		// top (set position)
		if (decos.containsKey("position") && decos.containsKey("top")) { // TODO
			String str = stringsub(decos.getStr("top"));
			if (isNumber(str)) {
				cssbuf.append("\ttop: " + str + "px;\n");
			} else {
				cssbuf.append("\ttop: " + str + ";\n");
			}
		}
		// right (set position)
		if (decos.containsKey("position") && decos.containsKey("right")) { // TODO
			String str = stringsub(decos.getStr("right"));
			if (isNumber(str)) {
				cssbuf.append("\tright: " + str + "px;\n");
			} else {
				cssbuf.append("\tright: " + str + ";\n");
			}
		}
		// bottom (set position)
		if (decos.containsKey("position") && decos.containsKey("bottom")) { // TODO
			String str = stringsub(decos.getStr("bottom"));
			if (isNumber(str)) {
				cssbuf.append("\tbottom: " + str + "px;\n");
			} else {
				cssbuf.append("\tbottom: " + str + ";\n");
			}
		}
		// left (set position)
		if (decos.containsKey("position") && decos.containsKey("left")) { // TODO
			String str = stringsub(decos.getStr("left"));
			if (isNumber(str)) {
				cssbuf.append("\tleft: " + str + "px;\n");
			} else {
				cssbuf.append("\tleft: " + str + ";\n");
			}
		}
		// z-index (set position)
		if (decos.containsKey("position") && decos.containsKey("z-index")) { // TODO
			String str = stringsub(decos.getStr("z-index"));
			cssbuf.append("\tz-index: " + str + ";\n");
		}
		
		// table-layout (only table element)
		if (decos.containsKey("table-layout")) { // TODO
			String str = stringsub(decos.getStr("table-layout"));
			cssbuf.append("\ttable-layout: " + str + ";\n");
		}
		
		// tab-size (CSS3)
		if (decos.containsKey("tab-size")) { // TODO
			String str = stringsub(decos.getStr("tab-size"));
			cssbuf.append("\ttab-size: " + str + ";\n");
		}
		
		// text-align & align
		if (decos.containsKey("text-align")) { // TODO
			String str = stringsub(decos.getStr("text-align"));
			cssbuf.append("\ttext-align: " + str + ";\n");
		} else if (decos.containsKey("align")) {
			String str = stringsub(decos.getStr("align"));
			cssbuf.append("\ttext-align: " + str + ";\n");
		}
		
		// text-align-last (CSS3)
		if (decos.containsKey("text-align-last")) { // TODO
			String str = stringsub(decos.getStr("text-align-last"));
			cssbuf.append("\ttext-align-last: " + str + ";\n");
		}
		
		// text-decoration & text-decoration-line (CSS3)
		if (decos.containsKey("text-decoration")) { // TODO
			String str = stringsub(decos.getStr("text-decoration"));
			cssbuf.append("\ttext-decoration: " + str + ";\n");
		} else if (decos.containsKey("text-decoration-line")) {
			String str = stringsub(decos.getStr("text-decoration-line"));
			cssbuf.append("\t-moz-text-decoration-line: " + str + ";\n");
			cssbuf.append("\ttext-decoration-line: " + str + ";\n");
		}
		
		// text-decoration-color (CSS3)
		if (decos.containsKey("text-decoration-color")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-color"));
			cssbuf.append("\t-moz-text-decoration-color: " + str + ";\n");
			cssbuf.append("\ttext-decoration-color: " + str + ";\n");
		}
		
		// text-decoration-skip (CSS3)
		if (decos.containsKey("text-decoration-skip")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-skip"));
			cssbuf.append("\t-moz-text-decoration-skip: " + str + ";\n");
			cssbuf.append("\ttext-decoration-skip: " + str + ";\n");
		}
		
		// text-decoration-style (CSS3)
		if (decos.containsKey("text-decoration-style")) { // TODO
			String str = stringsub(decos.getStr("text-decoration-style"));
			cssbuf.append("\t-moz-text-decoration-style: " + str + ";\n");
			cssbuf.append("\ttext-decoration-style: " + str + ";\n");
		}
		
		// text-indent & indent
		if (decos.containsKey("text-indent")) { // TODO
			String str = stringsub(decos.getStr("text-indent"));
			if (isNumber(str)) {
				cssbuf.append("\ttext-indent: " + str + "px;\n");
			} else {
				cssbuf.append("\ttext-indent: " + str + ";\n");
			}
		} else if (decos.containsKey("indent")) {
			String str = stringsub(decos.getStr("indent"));
			if (isNumber(str)) {
				cssbuf.append("\ttext-indent: " + str + "px;\n");
			} else {
				cssbuf.append("\ttext-indent: " + str + ";\n");
			}
		}
		
		// text-justify (CSS3)
		if (decos.containsKey("text-justify")) {
			String str = stringsub(decos.getStr("text-justify"));
			cssbuf.append("\ttext-justify: " + str + ";\n");
		}
		
		// text-transform
		if (decos.containsKey("text-transform")) { // TODO
			String str = stringsub(decos.getStr("text-transform"));
			cssbuf.append("\ttext-transform: " + str + ";\n");
		}
		
		// text-underline-position (CSS3)
		if (decos.containsKey("text-underline-position")) { // TODO
			String str = stringsub(decos.getStr("text-underline-position"));
			cssbuf.append("\t-moz-text-underline-position: " + str + ";\n");
			cssbuf.append("\ttext-underline-position: " + str + ";\n");
		}
		
		// text-wrap (CSS3)
		if (decos.containsKey("text-wrap")) { // TODO
			String str = stringsub(decos.getStr("text-wrap"));
			cssbuf.append("\ttext-wrap: " + str + ";\n");
		}
		
		// unicode-bidi
		if (decos.containsKey("unicode-bidi")) { // TODO
			String str = stringsub(decos.getStr("unicode-bidi"));
			cssbuf.append("\tunicode-bidi: " + str + ";\n");
		}
		
		// virtical-align & valign
		// tableと分ける必要性あり
		if (decos.containsKey("virtical-align")) {  // TODO
			String str = stringsub(decos.getStr("virtical-align"));
			//cssbuf.append("\tvirtical-align: " + decos.getStr("virtical-align") + ";\n");
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
		} else if (decos.containsKey("valign")) {
			String str = stringsub(decos.getStr("valign"));
			//cssbuf.append("\tvirtical-align: " + decos.getStr("valign") + ";\n");
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
		
		// visibility
		if (decos.containsKey("visibility")) { // TODO
			String str = stringsub(decos.getStr("visibility"));
			cssbuf.append("\tvisibility: " + str + ";\n");
		}
		
		// white-space
		if (decos.containsKey("white-space")) { // TODO
			String str = stringsub(decos.getStr("white-space"));
			cssbuf.append("\twhite-space: " + str + ";\n");
		}
		
		// width
		if (decos.containsKey("width")) { // TODO
			String str = stringsub(decos.getStr("width"));
			if (isNumber(str)) {
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
				cssbuf.append("\twidth: 100%;\n"); // default
			}
		}
		
		// word-break (CSS3)
		if (decos.containsKey("word-break")) { // TODO
			String str = stringsub(decos.getStr("word-break"));
			cssbuf.append("\tword-break: " + str + ";\n");
		}
		
		// word-spacing
		if (decos.containsKey("word-spacing")) { // TODO
			String str = stringsub(decos.getStr("word-spacing"));
			if (isNumber(str)) {
				cssbuf.append("\tword-spacing: " + str + "px;\n");
			} else {
				cssbuf.append("\tword-spacing: " + str + ";\n");
			}
		}
		
		// word-wrap (CSS3)
		if (decos.containsKey("word-wrap")) { // TODO
			String str = stringsub(decos.getStr("word-wrap"));
			cssbuf.append("\tword-wrap: " + str + ";\n");
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
		Log.out("");
		
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
		header.append("<body>\n");
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
			String host = "localhost";
			String dbname = "halken"; // データベース名
			String url = "jdbc:postgresql://" + host + "/" + dbname;
			String user = "halken";
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
			if (!style.isEmpty() && (css.size() != 0)) {
				sql = sql + "SELECT c.name as selector, d.property, d.value "
						+ "FROM template t, component c, declaration d, tem_com tc, com_dec cd "
						+ "WHERE t.id=tc.tem_id AND c.id=tc.com_id AND c.id=cd.com_id AND d.id=cd.dec_id ";
				sql = sql + "AND t.name=\'" + style + "\' AND (";
				for (int i = 0; i < css.size(); i++) {
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
	
	public boolean isNumber(String val) { // 文字列が全部数字であるかチェック
		String regex = "^\\-?[0-9]*\\.?[0-9]+$";
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(val);
	    return m.find();
	}

}
