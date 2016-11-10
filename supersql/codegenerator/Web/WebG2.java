package supersql.codegenerator.Web;

import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebG2 extends Grouper {
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebG2(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("------- G2 -------");
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// 親要素がtable状態なのかcheck
		boolean table_event = false;
		if (webEnv.tableFlag) {
			table_event = true;
		}
		// 親要素がborder状態なのかcheck
		boolean border_event = false;
		if (webEnv.borderFlag) {
			border_event = true;
		}
		// 親要素がlist-ul状態なのかcheck
		boolean listul_event = false;
		if (webEnv.listUlFlag) {
			listul_event = true;
		}
		// 親要素がlist-ol状態なのかcheck
		boolean listol_event = false;
		if (webEnv.listOlFlag) {
			listol_event = true;
		}
		
		// クラス名の取得
		String classname;
		if (this.decos.containsKey("class")) {
			classname = WebEnv.stringsub(this.decos.getStr("class"));
		} else {
			classname = WebEnv.getClassID(this);
		}
		
		// cssの情報を取得
		webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// htmlコード書き込み
		if (!GlobalEnv.isOpt()) {
			if (table_event) {
				webEnv.code.append("<td>\n");
			} else if (listul_event || listol_event) {
				webEnv.code.append("<li>\n");
			} else if (webEnv.decorationStartFlag) {
				WebDecoration.divFront.append("<div class=\"");
//				WebDecoration.divEnd.append(WebEnv.getClassID(this));
				WebDecoration.divEnd.append(classname);
				WebDecoration.divEnd.append(" col\">\n");
				webEnv.decorationStartFlag = false;
			} else if (webEnv.decorationFlag) {
				WebDecoration.divEnd.append("<div class=\"");
//				WebDecoration.divEnd.append(WebEnv.getClassID(this));
				WebDecoration.divEnd.append(classname);
				WebDecoration.divEnd.append(" col\">\n");
			} else {
				webEnv.code.append("<div class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" col\">\n");
			}
			if (webEnv.tableFlag) { // table
				webEnv.code.append("<table class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" col\">\n");
			} else if (webEnv.listUlFlag) { // list-ul
				webEnv.code.append("<ul class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" col\">\n");
			} else if (webEnv.listOlFlag) { // list-ol
				webEnv.code.append("<ol class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" col\">\n");
			}
		}
		
		// 子要素に書き込み
		while (this.hasMoreItems()) {
			String classid = WebEnv.getClassID(tfe);
			
			if (webEnv.tableFlag) {
				webEnv.code.append("<tr>\n");
			}
			
			this.worknextItem();
			
			if (webEnv.tableFlag) {
				webEnv.code.append("</tr>\n");
			}
		}
		
		if (webEnv.borderFlag && !border_event) {
			webEnv.borderFlag = false;
			Log.out("****** border off ******");
		}
		
		if (webEnv.tableFlag) {
			webEnv.code.append("</table>\n");
			if (!table_event) {
				webEnv.tableFlag = false;
				Log.out("********table end********");
			}
		} else if (webEnv.listUlFlag) {
			webEnv.code.append("</ul>\n");
			if (!listul_event) {
				webEnv.listUlFlag = false;
				Log.out("********list end********");
			}
		} else if (webEnv.listOlFlag) {
			webEnv.code.append("</ol>\n");
			if (!listol_event) {
				webEnv.listOlFlag = false;
				Log.out("********list end********");
			}
		}
		if (table_event) {
			webEnv.code.append("</td>\n");
		} else if (listul_event || listol_event) {
			webEnv.code.append("</li>\n");
		} else if (webEnv.decorationFlag) {
			WebDecoration.divEnd.append("</div>\n");
		} else {
			webEnv.code.append("</div>\n");
		}
		
		Log.out("+++++++ G2 +++++++");
		return null;
	}

}
