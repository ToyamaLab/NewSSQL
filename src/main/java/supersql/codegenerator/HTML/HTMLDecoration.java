package supersql.codegenerator.HTML;

import java.util.ArrayList;

import supersql.codegenerator.Decorator;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

//tk

public class HTMLDecoration extends Decorator {

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;
	
	public static ArrayList<StringBuffer> fronts = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> classes = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> styles = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> ends = new ArrayList<StringBuffer>();

	// ���󥹥ȥ饯��
	public HTMLDecoration(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "HTMLDecoration";
	}

	// Decoration��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		Log.out("------- Decoration -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfes.size=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());
		
		StringBuffer Front = new StringBuffer();
		StringBuffer classname = new StringBuffer();
		StringBuffer Style = new StringBuffer();
		StringBuffer End = new StringBuffer();
		fronts.add(0, Front);
		classes.add(0, classname);
		styles.add(0, Style);
		ends.add(0, End);
		ArrayList<String> decoproperty = new ArrayList<String>();
		htmlEnv.decorationProperty.add(0, decoproperty);
		htmlEnv.decorationStartFlag.add(0, false);
		htmlEnv.decorationEndFlag.add(0, false);

		this.setDataList(data_info);//d.floor
		
		
		if (decos.containsKey("form")) {
			htmlEnv.code.append(HTMLFunction.createForm(decos));
			HTMLEnv.setFormItemFlg(true, null);
		}

		while (this.hasMoreItems()) {
			//ITFE tfe = tfes.get(i);
			//String classid = HTMLEnv.getClassID(tfe);
			htmlEnv.decorationStartFlag.set(0, true);
			this.worknextItem();
			htmlEnv.decorationEndFlag.set(0, true);
		}

		if (htmlEnv.decorationStartFlag.size() > 1) {
			ends.get(1).append(fronts.get(0));
			
			if (!styles.get(0).equals("")) {
				ends.get(1).append(" style=\"");
				ends.get(1).append(styles.get(0));
				ends.get(1).append("\"");
			}
			ends.get(1).append(classes.get(0));
			ends.get(1).append(ends.get(0));
		} else {
			htmlEnv.code.append(fronts.get(0));
			if (!styles.get(0).equals("")) {
				htmlEnv.code.append(" style=\"");
				htmlEnv.code.append(styles.get(0));
				htmlEnv.code.append("\"");
			}
			htmlEnv.code.append(classes.get(0));
			htmlEnv.code.append(ends.get(0));
			
			//
			String jsBuf = "", key = "", value = "";
			String cssStrs[] = styles.get(0).toString().split(";");
			for(String cssStr : cssStrs){
				key = cssStr.substring(0, cssStr.indexOf(":"));
				value = cssStr.substring(cssStr.indexOf(":")+1);
				if(key.equals("background"))
					jsBuf += "	$(\"body\").css(\"background\",\"url('"+value+"')\");\n";
				else if(key.equals("page-bgcolor") || key.equals("pbgcolor"))
					jsBuf += "	$(\"body\").css(\"background-color\",\""+value+"\");\n";
			}
			if(jsBuf.length() > 0){
				htmlEnv.code.append(
						"<script type=\"text/javascript\">\n" +
						"<!--\n" +
						""+LinkForeach.ID1+"_Func.sff"+HTMLG3.foreachID+" = function(){\n" +
						jsBuf +
						"}\n" +
						"//-->" +
						"</script>"
						);
			}
		}
		
		fronts.remove(0);
		classes.remove(0);
		styles.remove(0);
		ends.remove(0);
		htmlEnv.decorationProperty.remove(0);
		htmlEnv.decorationStartFlag.remove(0);
		htmlEnv.decorationEndFlag.remove(0);
		
		Log.out("+++++++ Decoration +++++++");
		return null;
	}

}
