package supersql.codegenerator;

import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class Ehtml {
	//TODO media名に応じて切り替え
	public static boolean isMediaHTML = true;		// for HTML
	// public static boolean isMediaHTML = false;	// for ResponsiveHTML
	
	
	public static boolean flag = false;
	public static boolean flag2 = false;	// for html output
	public static int media = 0;		// 0:HTML(Default), 1:Mobile/Responsive
	public static int outType = 0;		// 0:xml(Default), 1:html
	
	private static String scopeID = "";		// for css scope
	private static String number = "";		// for css scope
//	private static String tfeID = "";		// for css scope
	
	public static boolean infinitescroll_flag = false;	//TODO
//	public static String tfe_id = "";

	// set ehtml flag
	public static void setEhtml() {
		if (isMediaHTML)
			flag = true;	// for HTML 	// html出力テスト用にコメントアウト 20191009
		else
			setEhtml2();	// for ResponsiveHTML
		
//		//TODO
//		String m = CodeGenerator.getMedia().toLowerCase();
//		Log.ehtmlInfo("Ehtml media: "+m);
//		if(m.equals("html")){
//			flag = true;
//		}else{
//			setEhtml2();
//		}
	}
	public static void setEhtml2() {
		setIDstr("ssql_body_contents");
		setNumber(""+GlobalEnv.getQueryNum());
		flag2 = true;
	}
//	// is output html
//	public static void isOutputHTML() {
////		flag = true;
//	}
	
	// Ehtml set type  
	// [m] 0:HTML(Default), 1:Mobile/Responsive
	// [o] 0:xml(Default), 1:html
	public static void setType(int m, int o) {
		media = m;
		outType = o;
	}
	
	// Ehtml
	public static boolean isEhtml() {
		return (!infinitescroll_flag && (flag || Incremental.flag));
	}
	public static boolean isEhtml_flag() {
		return (!infinitescroll_flag && flag);
	}
	public static boolean isEhtml_Incremental() {
		return (!infinitescroll_flag && Incremental.flag);
	}
	public static boolean isEhtml2() {
//		setIDstr("ssql_body_contents");
//		setNumber(""+GlobalEnv.getQueryNum());
		return (!infinitescroll_flag && flag2);
	}
	
	
	// ID
	private static void setIDstr(String id) {
		scopeID = id;
	}
	public static String getID(int type) {
		if (type == 0)
			// type: 0
			return (isEhtml2())? (scopeID + number) : ("");
		else
			// type: 1
			return (isEhtml2())? ("#" + scopeID + number) : ("");
	}
	// Number
	private static void setNumber(String n) {
		number = n;
	}
	public static String getNumber() {
		return (isEhtml2())? number : "";
	}
//	// TFE ID
//	public static void setTFE_ID(String n) {
//		tfeID = n;
//	}
//	public static String getTFE_ID() {
//		return (isEhtml2())? tfeID : "";
//	}
	// File name
	private static String fn = "_ehtml";
	public static String getFileName() {
		return (isEhtml2())? fn+number : "";
	}
	
	
	
	
	// IS
	public static boolean isInfinitescroll() {
		return (infinitescroll_flag && (flag || Incremental.flag));
	}

	// added by masato 20151118 for ehtml
	public static void appendToHeadFromBody(String path) {
		// if(flag){
		String cssFileName = Jscss.getGenerateCssFileName(1);
		String js = "jscss/makeTable.js";

		int num = GlobalEnv.getQueryNum() - 1;
		int pageNum = 0;

		if (HTMLEnv.g1PaginationRowNum != 0
				&& HTMLEnv.g1PaginationColumnNum != 0) {
			pageNum = (int) Math
					.ceil((double) HTMLEnv.itemCount
							/ (HTMLEnv.g1PaginationRowNum * HTMLEnv.g1PaginationColumnNum));
		} else if ((HTMLEnv.g2PaginationRowNum != 0 && HTMLEnv.g2PaginationColumnNum != 0)) {
			pageNum = (int) Math
					.ceil((double) HTMLEnv.itemCount
							/ (HTMLEnv.g2PaginationRowNum * HTMLEnv.g2PaginationColumnNum));
		}

		Log.ehtmlInfo("<script>");

		// makeTable.jsに受け渡すxmlファイルのパス
		if (num == 0) {
//			Log.ehtmlInfo("	var ItemNumPerPage = " + HTMLEnv.itemNumPerPage
//					+ ";");
			Log.ehtmlInfo("	var pageNum = " + pageNum + ";");
			Log.ehtmlInfo("	var itemCount = " + HTMLEnv.itemCount + ";");

			Log.ehtmlInfo("	var g1PaginationRowNum = [];");
			Log.ehtmlInfo("	var g1PaginationColumnNum = [];");
			Log.ehtmlInfo("	var g2PaginationRowNum = [];");
			Log.ehtmlInfo("	var g2PaginationColumnNum = [];");

			Log.ehtmlInfo("	var g1RetNum = [];");
			Log.ehtmlInfo("	var g2RetNum = [];");

			Log.ehtmlInfo("	var xmlFileName = [];");
			// Log.ehtmlInfo("	var outType = [];");
		}
		Log.ehtmlInfo("	g1PaginationRowNum[" + num + "]="
				+ HTMLEnv.g1PaginationRowNum + ";\n");
		Log.ehtmlInfo("	g1PaginationColumnNum[" + num + "]="
				+ HTMLEnv.g1PaginationColumnNum + ";\n");
		Log.ehtmlInfo("	g2PaginationRowNum[" + num + "]="
				+ HTMLEnv.g2PaginationRowNum + ";\n");
		Log.ehtmlInfo("	g2PaginationColumnNum[" + num + "]="
				+ HTMLEnv.g2PaginationColumnNum + ";\n");

		Log.ehtmlInfo("	g1RetNum[" + num + "]=" + HTMLEnv.g1RetNum + ";\n");
		Log.ehtmlInfo("	g2RetNum[" + num + "]=" + HTMLEnv.g2RetNum + ";\n");
		Log.ehtmlInfo("	xmlFileName[" + num + "]=\"" + path + "\";\n");

		// // table or div
		// if(HTMLEnv.tableFlag) {
		// Log.ehtmlInfo("	outType[" + num + "]=\"" + "table" + "\";\n");
		// } else if(HTMLEnv.divFlag) {
		// Log.ehtmlInfo("	outType[" + num + "]=\"" + "div" + "\";\n");
		// }

		// css
		Log.ehtmlInfo("	var css=document.createElement(\"link\");");
		Log.ehtmlInfo("	css.setAttribute(\"rel\", \"stylesheet\");");
		Log.ehtmlInfo("	css.setAttribute(\"type\", \"text/css\");");
		Log.ehtmlInfo("	css.setAttribute(\"href\", \"" + cssFileName + "\");");
//		Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(css);\n");
		Log.ehtmlInfo("	$(\"head\")[0].append(css);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append

		if (num == 0) {
			Log.ehtmlInfo("	var pagecss=document.createElement(\"link\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"rel\", \"stylesheet\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"type\", \"text/css\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"href\", \"jscss/simplePagination.css\");");
//			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(pagecss);\n");
			Log.ehtmlInfo("	$(\"head\")[0].append(pagecss);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append

			// jquery library
			// Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
			// Log.ehtmlInfo("	jq.setAttribute(\"src\",\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");
			Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
			Log.ehtmlInfo("	jq.setAttribute(\"src\",\"jscss/jquery.js\");");
//			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");
			Log.ehtmlInfo("	$(\"head\")[0].append(jq);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append
			
			if(infinitescroll_flag) {
				Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
				Log.ehtmlInfo("	jq.setAttribute(\"src\",\"jscss/jquery-1.10.2.min.js\");");
//				Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");
				Log.ehtmlInfo("	$(\"head\")[0].append(jq);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append
			}

			// Log.ehtmlInfo("	var jqp=document.createElement(\"script\");");
			// Log.ehtmlInfo("	jqp.setAttribute(\"src\",\"jscss/jquery-p.js\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jqp);\n");

			Log.ehtmlInfo("	var pagejs=document.createElement(\"script\");");
			Log.ehtmlInfo("	pagejs.setAttribute(\"src\",\"jscss/jquery.simplePagination.js\");");
//			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(pagejs);\n");
			Log.ehtmlInfo("	$(\"head\")[0].append(pagejs);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append

			// js for xml parse
			// Log.ehtmlInfo("	var js_proto=document.createElement(\"script\");");
			// Log.ehtmlInfo("	js_proto.setAttribute(\"src\",\"jscss/prototype.js\");");
			// Log.ehtmlInfo("	js_proto.setAttribute(\"type\",\"text/javascript\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(js_proto);\n");

			if(infinitescroll_flag) {
				//taji add
				Log.ehtmlInfo("	var infinite_scroll=document.createElement(\"script\");");
				Log.ehtmlInfo("	infinite_scroll.setAttribute(\"src\",\"jscss/jquery.infinitescroll.min.js\");");
//				Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(infinite_scroll);\n");
				Log.ehtmlInfo("	$(\"head\")[0].append(infinite_scroll);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append
				
				Log.ehtmlInfo("	var infinitescroll=document.createElement(\"script\");\n");
				Log.ehtmlInfo("	var infinite_definition = "
						+ "\"$(function){"
						+ "$('#content').infinitescroll({"
						+ "navSelector  : \\\".navigation\\\","
						+ "nextSelector : \\\".navigation a\\\","
						+ "itemSelector : \\\"#wrapper\\\""
						+ "});});\";\n"
						);
				Log.ehtmlInfo("infinitescroll.appendChild(document.createTextNode(infinite_definition));\n");
//				Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(infinitescroll);\n");
				Log.ehtmlInfo("	$(\"head\")[0].append(infinitescroll);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append
			}
			
			// js generated by ssql for making table
			Log.ehtmlInfo("	var make_table=document.createElement(\"script\");");
			Log.ehtmlInfo("	make_table.setAttribute(\"src\",\"" + js + "\");");
			Log.ehtmlInfo("	make_table.setAttribute(\"type\",\"text/javascript\");");
//			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(make_table);\n");
			Log.ehtmlInfo("	$(\"head\")[0].append(make_table);\n");	// [重要] document.getElementsByTagName("head")[0].appendChild -> $("head").append
			

			// if ((HTMLEnv.g1PaginationRowNum != 0 &&
			// HTMLEnv.g1PaginationColumnNum != 0)
			// || (HTMLEnv.g2PaginationRowNum != 0 &&
			// HTMLEnv.g2PaginationColumnNum != 0)) {
			// Log.ehtmlInfo("	$(function() {\n" +
			// "	    $(\"#paging\").pagination({\n" +
			// "	        items: pageNum, // ページボタンの数\n" +
			// "	        displayedPages: 1,\n" +
			// "	        cssStyle: 'light-theme',\n" +
			// "	        prevText: '<<',\n" +
			// "	        nextText: '>>',\n" +
			// "	        onPageClick: function(pageNumber){show(pageNumber)}\n"
			// +
			// "	    })\n" +
			// "	});\n" +
			// "	function show(pageNumber){\n" +
			// "		var page = \"#page-\"+pageNumber;\n" +
			// "		$('.selection').hide()\n" +
			// "		$(page).show()\n" +
			// "	}\n");
			// }
		}
		Log.ehtmlInfo("</script>");
		// }
	}

	public static void createBaseHTMLCode() {
		// TODO masato ここで指定するidは固有のものにする、また複数クエリが同ページ内で実行される用の処理も追加
		String id = "ssqlResult" + GlobalEnv.getQueryNum();
		Log.ehtmlInfo("<div id=\"" + id + "\">");
		Log.ehtmlInfo("</div>");
		if ((HTMLEnv.g1PaginationRowNum != 0 && HTMLEnv.g1PaginationColumnNum != 0)
				|| (HTMLEnv.g2PaginationRowNum != 0 && HTMLEnv.g2PaginationColumnNum != 0)) {
			Log.ehtmlInfo("<div id=\"paging\">");
			Log.ehtmlInfo("</div>");
		}
		// if (HTMLEnv.itemNumPerPage != 0) {
		// Log.ehtmlInfo("<div id=\"paging\">");
		// Log.ehtmlInfo("</div>");
		// }
	}

}
