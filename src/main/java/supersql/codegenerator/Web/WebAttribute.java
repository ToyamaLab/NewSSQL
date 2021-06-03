package supersql.codegenerator.Web;

import java.io.File;
import java.util.ArrayList;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebAttribute extends Attribute {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebAttribute(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		super();
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		
		String data = this.getStr(data_info);
		
		// クラス名の取得
		String classname;
		if (this.decos.containsKey("class")) {
			classname = WebEnv.stringsub(this.decos.getStr("class"));
		} else {
			classname = WebEnv.getClassID(this);
		}
		// css情報書き込み
		webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// HTMLコード書き込み
		if (webEnv.tableFlag) {
			webEnv.code.append("<td class=\"");
			webEnv.code.append(classname);
			if (WebEnv.style != null) {
				webEnv.code.append(" style-table-att");
			}
			webEnv.code.append(" att\">");
		} else if (webEnv.listUlFlag || webEnv.listOlFlag) {
			webEnv.code.append("<li class=\"");
			webEnv.code.append(classname);
			if (WebEnv.style != null) {
				webEnv.code.append(" style-list-att");
			}
			webEnv.code.append(" att\">");
		} else if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationEndFlag.get(0)) {
				// nothing
			} else if (webEnv.decorationStartFlag.get(0)) {
				WebDecoration.divFront.get(0).append("<div");
				WebDecoration.divclass.get(0).append(" class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					WebDecoration.divEnd.get(0).append(" style-att");
				}
				WebDecoration.divEnd.get(0).append(" att\">");
			} else {
				WebDecoration.divEnd.get(0).append("<div class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					WebDecoration.divEnd.get(0).append(" style-att");
				}
				WebDecoration.divEnd.get(0).append(" att\">");
			}
		} else {
			webEnv.code.append("<div class=\"");
			webEnv.code.append(classname);
			if (WebEnv.style != null) {
				webEnv.code.append(" style-att");
			}
			webEnv.code.append(" att\">");
		}
		
		// C3, G3の場合 <a>
		if (webEnv.linkFlag > 0) {
			//TODO
			String fileDir = new File(webEnv.linkUrl).getAbsoluteFile().getParent();
			if (fileDir.length() < webEnv.linkUrl.length() && fileDir.equals(webEnv.linkUrl.substring(0, fileDir.length()))) {
				String relative_path = webEnv.linkUrl.substring(fileDir.length() + 1);
				webEnv.code.append("<a href=\"" + relative_path + "\">");
				Log.out("<a href=\"" + relative_path + "\">");
			} else {
				webEnv.code.append("<a href=\"" + webEnv.linkUrl + "\">");
				Log.out("<a href=\"" + webEnv.linkUrl + "\">");
			}
		}
		
		Log.out("data_info = " + data_info);
		Log.out("data = " + data);
		data = data.replace("\\r\\n", "<br>");
		data = data.replace("\\r", "<br>");
		data = data.replace("\\n", "<br>");
		Log.out("replace data = " + data);
		// webEnv.code.append(this.getStr(data_info));

		if (webEnv.decorationEndFlag.size() > 0) {
			if (webEnv.decorationEndFlag.get(0)) {
				String property = webEnv.decorationProperty.get(0).get(0);
				ArrayList<String> declaration = new ArrayList<String>();
				declaration = Modifier.replaceModifierValues(property, data);
				property = declaration.get(0);
				String value = declaration.get(1);
				if (property.equals("class")) {
					WebEnv.cssClass.add(value);
					WebDecoration.divclass.get(0).append(value + " ");
				} else {
					WebDecoration.divStyle.get(0).append(property + ":" + value + ";");
				}
				webEnv.decorationProperty.get(0).remove(0);
			} else {
				WebDecoration.divEnd.get(0).append(data);
			}
		} else {
			webEnv.code.append(data);
		}
		
		// C3, G3の場合 </a>
		if (webEnv.linkFlag > 0) {
			//TODO
			webEnv.code.append("</a>");
			Log.out("</a>");
		}
		
		if (webEnv.tableFlag) {
			webEnv.code.append("</td>\n");
		} else if (webEnv.listUlFlag || webEnv.listOlFlag) {
			webEnv.code.append("</li>\n");
		} else if (webEnv.decorationEndFlag.size() > 0) {
			if (webEnv.decorationEndFlag.get(0)) {
				// nothing
			} else if (webEnv.decorationStartFlag.get(0)) {
				WebDecoration.divEnd.get(0).append("</div>\n");
				webEnv.decorationStartFlag.set(0, false);
			} else {
				WebDecoration.divEnd.get(0).append("</div>\n");
			}
		} else {
			webEnv.code.append("</div>\n");
		}
		
		return null;
	}
}
