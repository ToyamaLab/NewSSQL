package supersql.codegenerator.Web;

import supersql.codegenerator.Decorator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebDecoration extends Decorator {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public static StringBuffer divFront;
	public static StringBuffer divclass;
	public static StringBuffer divEnd;
	
	public WebDecoration(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("------- Decoration -------");
		Log.out("tfes.contain_itemnum = " + tfes.contain_itemnum());
		Log.out("tfes.size = " + tfes.size());
		Log.out("countconnectitem = " + countconnectitem());
		
		divFront = new StringBuffer();
		divclass = new StringBuffer();
		divEnd = new StringBuffer();
		webEnv.decorationFlag = true;
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// cssの情報を取得
		// webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// htmlコード書き込み
//	 	if (!GlobalEnv.isOpt()) {
//			webEnv.code.append("<div class=\"");
//			webEnv.code.append(WebEnv.getClassID(this));
//			webEnv.code.append(" Deco\">\n");
//		}
		
		// 子要素に書き込み
		int i = 0;
		while (this.hasMoreItems()) {
			ITFE tfe = tfes.get(i);
			String classid = WebEnv.getClassID(tfe);
			webEnv.decorationStartFlag = true;
			
			this.worknextItem();
			
			webEnv.decorationEndFlag = true;
			i++;
		}
		webEnv.code.append(divFront);
		webEnv.code.append(divclass);
		webEnv.code.append(divEnd);
//		webEnv.code.append("</div>\n");
		
		webEnv.decorationStartFlag = false;
		webEnv.decorationFlag = false;
		webEnv.decorationEndFlag = false;
		
		Log.out("+++++++ Decoration +++++++");
		return null;
	}
}
