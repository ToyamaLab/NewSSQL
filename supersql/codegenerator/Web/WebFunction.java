package supersql.codegenerator.Web;

import supersql.codegenerator.FuncArg;
import supersql.codegenerator.Function;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebFunction extends Function {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebFunction() {
		
	}
	
	public WebFunction(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		super();
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("--- start Function ---");
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// クラス名の取得
		String classname;
		if (this.decos.containsKey("class")) {
			classname = WebEnv.stringsub(this.decos.getStr("class"));
		} else {
			classname = WebEnv.getClassID(this);
		}
		
		// cssの情報を取得
		webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// 関数名取得
		String FuncName = this.getFuncName();
		
		// anchor関数の発動
		if (FuncName.equalsIgnoreCase("anchor") || FuncName.equalsIgnoreCase("a")) {
			Func_url(classname);
		}
		
		// footer関数の発動
		if (FuncName.equalsIgnoreCase("footer")) {
			Func_footer();
		}
		
		// header関数の発動
		if (FuncName.equalsIgnoreCase("header")) {
			Func_header();
		}
		
		// image関数の発動
		if (FuncName.equalsIgnoreCase("image")) {
			Func_image(classname);
		}
		
		// line関数の発動
		if (FuncName.equalsIgnoreCase("line")) {
			Func_line();
		}
		
		// null関数の発動
		if (FuncName.equalsIgnoreCase("null")) {
			Func_null();
		}
		
		// text関数の発動
		if (FuncName.equalsIgnoreCase("text")) {
			Func_text();
		}
		
		Log.out("--- end Function ---");
		return null;
	}
	
	// footer関数
	protected void Func_footer() {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		String title = arg1.getStr();
		webEnv.code.append("<div class=\"");
		webEnv.code.append(WebEnv.getClassID(this));
		webEnv.code.append(" footer\">");
		webEnv.code.append(title);
		webEnv.code.append("</div>\n");
		
		webEnv.footerFlag = true;
	}
	
	// header関数
	protected void Func_header() {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		String title = arg1.getStr();
		webEnv.code.append("<div class=\"");
		webEnv.code.append(WebEnv.getClassID(this));
		webEnv.code.append(" header\">");
		webEnv.code.append(title);
		webEnv.code.append("</div>\n");
		
		webEnv.headerFlag = true;
	}
	
	// image関数
	protected void Func_image(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		FuncArg arg2 = (FuncArg) this.Args.get(1);
		
		// image(path)
		String path = arg2.getStr() + "/" + arg1.getStr();
		// HTMLに書き込み
		if (webEnv.tableFlag) {
			webEnv.code.append("<td>\n");
		}
		webEnv.code.append("<img class=\"");
//		webEnv.code.append(WebEnv.getClassID(this));
		webEnv.code.append(classname);
		webEnv.code.append(" att\" src=\"" + path + "\">\n");
		if (webEnv.tableFlag) {
			webEnv.code.append("</td>\n");
		}
	}
	
	// line関数
	protected void Func_line() {
		try {
			FuncArg arg1 = (FuncArg) this.Args.get(0);
			try { // 引数2つ(色と太さ)
				FuncArg arg2 = (FuncArg) this.Args.get(1);
				String color = arg1.getStr();
				String size = arg2.getStr();
				webEnv.code.append("<hr width=\"100%\" color=\"");
				webEnv.code.append(color);
				webEnv.code.append("\" size=\"");
				webEnv.code.append(size);
				webEnv.code.append("\">\n");
			} catch (Exception e) { // 引数1つ(色のみ)
				String color = arg1.getStr();
				webEnv.code.append("<hr width=\"100%\" color=\"");
				webEnv.code.append(color);
				webEnv.code.append("\">\n");
			}
		} catch (Exception e) { // 引数なし(デフォルト)
			webEnv.code.append("<hr width=\"100%\" color=\"#000000\" size=\"10\">");
		}
	}
	
	// null関数
	protected void Func_null() {
		// 特に何も表記しない // TODO
	}
	
	// text関数
	protected void Func_text() {
		// 引数の取得
		try {
			
		} catch (Exception e) { // 引数なし(デフォルト) 
			webEnv.code.append("<input type=\"text\">\n");
		}
	}
	
	// anchor関数 and a関数
	protected void Func_url(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		FuncArg arg2 = (FuncArg) this.Args.get(1);
		
		try {// 引数が3つ
			FuncArg arg3 = (FuncArg) this.Args.get(2);
			
			// anchor(path, url, "image")
			String path = arg1.getStr();
			String url = arg2.getStr();
			// HTMLに書き込み
			if (webEnv.tableFlag) {
				webEnv.code.append("<td class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" att\">");
			} else {
				webEnv.code.append("<div class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" att\">");
			}
			webEnv.code.append("<a href=\"" + url + "\">");
			webEnv.code.append("<img src=\"" + path + "\"></a>");
			if (webEnv.tableFlag) {
				webEnv.code.append("</td>\n");
			} else {
				webEnv.code.append("</div>\n");
			}
		} catch (Exception e) { // 引数が2つ
			// anchor(name, url)
			String name = arg1.getStr();
			String url = arg2.getStr();
			// HTMLに書き込み
			if (webEnv.tableFlag) {
				webEnv.code.append("<td class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" att\">");
			} else {
				webEnv.code.append("<div class=\"");
//				webEnv.code.append(WebEnv.getClassID(this));
				webEnv.code.append(classname);
				webEnv.code.append(" att\">");
			}
			webEnv.code.append("<a href=\"" + url + "\">" + name + "</a>");
			if (webEnv.tableFlag) {
				webEnv.code.append("</td>\n");
			} else {
				webEnv.code.append("</div>\n");
			}
		}
	}

}
