package supersql.codegenerator.infinitescroll;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.FuncArg;
import supersql.codegenerator.Function;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Sass;
import supersql.codegenerator.TFE;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Compiler.PHP.PHP;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Attribute;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5C1;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5C2;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G1;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G2;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G3;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Manager;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_dynamic;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_form;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_show;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;
import supersql.parser.Embed;
import supersql.parser.Start_Parse;

public class Infinitescroll {

	InfiniteEnv html_Env;
	static StringBuffer codes;

	static String[] formSql = {"","delete","update","insert","login","logout"};
	static String[] formHtml = {"","submit","select","checkbox","radio","text","textarea","hidden"};
	static int whichForm;

	public static ArrayList<Boolean> decompose = new ArrayList<Boolean>();
	public static int IS_level = 0;
	public static int IS_point = 0;

	public static void Attributes(Mobile_HTML5Attribute ATT, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2, ExtList data_info){

		//		Log.ehtmlInfo(IS_level);
		String classid_for_ifs = "";
		String[] ifs_div_String = {"", ""};
		codes = new StringBuffer();
		StringBuffer tmp = new StringBuffer();


		html_env.code = Embed.preProcess(html_env.code, ATT.decos);	//goto 20130915-2  "<$  $>"

		String classid = Mobile_HTML5Env.getClassID(ATT);

		//changed by goto 20161113  for function class width
		setWidth(classid, ATT.decos, html_env);

		if(GlobalEnv.isOpt()){
			//			work_opt(data_info);
		}else{
			if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2])){

			}else{

				Mobile_HTML5.preProcess("Mobile_HTML5Attribute", ATT.decos, html_env);	//Pre-process (前処理)	//TODO この位置でOK?

				//20130309
				//20130309
				//20130314 table
				//20130409
				if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
						Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
						ATT.decos.containsKey("table") || ATT.decos.containsKey("table0"))
						&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg)){
					html_env.code.append("<table width=\"100%\"" + html_env.getOutlineModeAtt() + " ");
					html_env.code.append("class=\"att");

					tmp.append("<table width=\"100%\"" + html_env.getOutlineModeAtt() + " ");
					tmp.append("class=\"att");

					if(html_env.written_classid.contains(classid)){
						//classを持っているとき(ex.TFE10000)のみ指定 
						html_env.code.append(" " + classid);
						tmp.append(" " + classid);
					}
					if(ATT.decos.containsKey("class")){ 
						//classを持っているとき(ex.TFE10000)のみ指定 
						html_env.code.append(" " + ATT.decos.getStr("class"));
						tmp.append(" " + ATT.decos.getStr("class"));
					}
					html_env.code.append("\"");
					html_env.code.append(">");

					tmp.append("\"");
					tmp.append(">");
				}
			}

			if(Mobile_HTML5Env.getFormItemFlg()){

			}else{
				//20130309
				//20130409
				//if(decos.containsKey("table") || decos.containsKey("table0"))	html_env.code.append("<tr><td>\n");		//20130314 table
				if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
						Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
						ATT.decos.containsKey("table") || ATT.decos.containsKey("table0"))
						&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg)){
					html_env.code.append("<tr><td>\n");		//20130314 table
					tmp.append("<tr><td>\n");
				}
				Log.out("<table class=\"att\"><tr><td>");
			}

			if (html_env.link_flag > 0 || html_env.sinvoke_flag) {

				//tk start for draggable div///////////////////////////////////////
				if(html_env.draggable)
				{	
					html_env.code.append("<div id=\""+html_env.dragdivid+"\" class=\"draggable\"");
					tmp.append("<div id=\""+html_env.dragdivid+"\" class=\"draggable\"");
					Log.out("<div id=\""+html_env.dragdivid+"\" ");
				}	
				else{
					//tk end for draggable div/////////////////////////////////////////
					if(html_env.isPanel){
						html_env.code.append("<div id=\"container\">");
						tmp.append("<div id=\"container\">");
					}
					//added by goto 20120614 start
					//[%連結子] 下記の2つの問題があったため、hrefの指定を絶対パスから「相対パス形式」へ変更
					//1.絶対パスだとFirefoxではリンク先が開けない
					//2.ITCの実習環境ではリンク先が開けない
					String fileDir = new File(html_env.linkurl).getAbsoluteFile().getParent();
					if(fileDir.length() < html_env.linkurl.length()
							&& fileDir.equals(html_env.linkurl.substring(0,fileDir.length()))){
						String relative_path = html_env.linkurl.substring(fileDir.length()+1);
						html_env.code.append("<A href=\"" + relative_path + "\" ");
						tmp.append("<A href=\"" + relative_path + "\" ");
					}else
						//changed by goto 20161019 for new foreach
						//added by goto 20161109 for plink/glink
						if(html_env.plink_glink_onclick.isEmpty()){
							html_env.code.append("<A href=\"" + html_env.linkurl + "\" data-ajax=\"false\" ");
							tmp.append("<A href=\"" + html_env.linkurl + "\" data-ajax=\"false\" ");
						}else{
							html_env.code.append("<A href=\"\" onclick=\""+LinkForeach.ID1+"("+html_env.plink_glink_onclick+"); return false;\" data-ajax=\"false\" ");
							tmp.append("<A href=\"\" onclick=\""+LinkForeach.ID1+"("+html_env.plink_glink_onclick+"); return false;\" data-ajax=\"false\" ");
						}

					//html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
					//added by goto 20120614 end
				}

				//added by goto 20121217 start
				//画面遷移アニメーション (data-transition)
				//transition = fade, slide, pop, slideup, slidedown, flip
				if (ATT.decos.containsKey("transition")){
					html_env.code.append("data-transition=\"" + ATT.decos.getStr("transition") + "\" ");
					tmp.append("data-transition=\"" + ATT.decos.getStr("transition") + "\" ");
					//System.out.println(decos.getStr("transition"));
				}
				//added by goto 20121217 end


				//tk start//////////////////////////////////////////////////////////
				if(ATT.decos.containsKey("target")){
					html_env.code.append(" target=\"" + ATT.decos.getStr("target")+"\"");
					tmp.append(" target=\"" + ATT.decos.getStr("target")+"\"");
				}
				if(ATT.decos.containsKey("class")){
					html_env.code.append(" class=\"" + ATT.decos.getStr("class") + "\"");
					tmp.append(" class=\"" + ATT.decos.getStr("class") + "\"");
				}

				if(GlobalEnv.isAjax() && html_env.isPanel)
				{
					html_env.code.append(" onClick =\"return panel('Panel','"+html_env.ajaxquery+"'," +
							"'"+html_env.dragdivid+"','"+html_env.ajaxcond+"')\"");
					tmp.append(" onClick =\"return panel('Panel','"+html_env.ajaxquery+"'," +
							"'"+html_env.dragdivid+"','"+html_env.ajaxcond+"')\"");
				}
				else if(GlobalEnv.isAjax() && !html_env.draggable)
				{
					String target = GlobalEnv.getAjaxTarget();
					if(target == null)
					{
						String query = html_env.ajaxquery;
						if (query.indexOf(".sql")>0) {
							if (query.contains("/")) {
								target = query.substring(query.lastIndexOf("/")+1,query.indexOf(".sql"));
							} else {
								target = query.substring(0,query.indexOf(".sql"));
							}
						} else if (query.indexOf(".ssql")>0) {
							if (query.contains("/")) {
								target = query.substring(query.lastIndexOf("/")+1,query.indexOf(".ssql"));
							} else {
								target = query.substring(0,query.indexOf(".ssql"));
							}
						}

						if(html_env.has_dispdiv)
						{
							target = html_env.ajaxtarget;
						}
						Log.out("a target:"+target);
					}
					html_env.code.append(" onClick =\"return loadFile('"+html_env.ajaxquery+"','"+target+
							"','"+html_env.ajaxcond+"',"+html_env.inEffect+","+html_env.outEffect+")\"");
					tmp.append(" onClick =\"return loadFile('"+html_env.ajaxquery+"','"+target+
							"','"+html_env.ajaxcond+"',"+html_env.inEffect+","+html_env.outEffect+")\"");

				}


				html_env.code.append(">\n");
				tmp.append(">\n");
				//tk end////////////////////////////////////////////////////////////

				Log.out("<A href=\"" + html_env.linkurl + "\">");
			}

			//Log.out("data_info: "+Func.getStr(data_info));
			Mobile_HTML5.beforeWhileProcess("Mobile_HTML5Attribute", ATT.decos, html_env);
			Mobile_HTML5.whileProcess1_2("Mobile_HTML5Attribute", ATT.decos, html_env, null, data_info, null, null, -1);	//TODO ここでOK?

			createForm(data_info, ATT, html_env, html_env2, tmp);


			if(whichForm == 0){ //normal process (not form)
				//***APPEND DATABASE VALUE***//
				Log.out(data_info);
				if(Mobile_HTML5_dynamic.dynamicDisplay || Mobile_HTML5_form.form){
					//20131118 dynamic
					if(Mobile_HTML5_dynamic.dynamicDisplay){
						html_env.code.append( Mobile_HTML5_dynamic.dynamicAttributeProcess(ATT, html_env, ATT.decos) );
					}
					//20131127 form
					if(Mobile_HTML5_form.form){
						html_env.code.append( Mobile_HTML5_form.formAttributeProcess(ATT, ATT.decos) );
					}

				}else{
					//					if(!Sass.isBootstrapFlg()){
					html_env.code.append(ATT.getStr(data_info));
					//					}else if(Sass.isBootstrapFlg()){
					//						html_env.code.append("<div class=\"" + classid +"\">");
					//						html_env.code.append(Func.getStr(data_info));
					//						html_env.code.append("</div>");
					//						if(Sass.outofloopFlg.peekFirst()){
					//		        			Sass.makeClass(classid);
					//		        			Sass.defineGridBasic(classid, decos);
					//		        			Sass.closeBracket();
					//			      		}
					//					}
				}
				if(Infinite_dynamic.dynamicDisplay){
					//20131118 dynamic
					if(Infinite_dynamic.dynamicDisplay){
						tmp.append( Infinite_dynamic.dynamicAttributeProcess(ATT, html_env, ATT.decos) );
					}
				}else{
					//					if(!Sass.isBootstrapFlg()){
					tmp.append(ATT.getStr(data_info));
					//					}else if(Sass.isBootstrapFlg()){
					//						html_env.code.append("<div class=\"" + classid +"\">");
					//						html_env.code.append(Func.getStr(data_info));
					//						html_env.code.append("</div>");
					//						if(Sass.outofloopFlg.peekFirst()){
					//		        			Sass.makeClass(classid);
					//		        			Sass.defineGridBasic(classid, decos);
					//		        			Sass.closeBracket();
					//			      		}
					//					}
				}

				Mobile_HTML5.whileProcess2_1("Mobile_HTML5Attribute", ATT.decos, html_env, null, data_info, null, null, -1);	//TODO ここでOK?
				Mobile_HTML5.afterWhileProcess("Mobile_HTML5Attribute", classid, ATT.decos, html_env);

				if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
					if(html_env.draggable){
						html_env.code.append("</div>\n");
						tmp.append("</div>\n");
					}else
					{
						html_env.code.append("</A>\n");
						tmp.append("</A>\n");

						if(html_env.isPanel){
							html_env.code.append("</div>\n");
							tmp.append("</div>\n");
						}
					}
					Log.out("</A>");
				}


				if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2])){

				}else{
					html_env.code.append("\n");			//20130309
					if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
							Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
							ATT.decos.containsKey("table") || ATT.decos.containsKey("table0"))
							&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg)){
						html_env.code.append("</td></tr></table>\n");	//20130314 table
						tmp.append("</td></tr></table>\n");
					}
					Log.out("</td></tr></table>");
				}


				Mobile_HTML5.postProcess("Mobile_HTML5Attribute", classid, ATT.decos, html_env);	//Post-process (後処理)

				Log.out("TFEId = " + classid);
			}
		}
		codes = tmp;

	}

	public static void C1(Mobile_HTML5C1 C1, Mobile_HTML5Env html_env, ExtList data_info, ExtList data, ExtList<TFE> tfes, int tfeItems){
		String outType = "div";


		String classid_for_ifs = "";
		String[] ifs_div_String = {"", ""};
		codes = new StringBuffer();
		StringBuffer tmp = new StringBuffer();

		String classid = Mobile_HTML5Env.getClassID(C1);

		if(C1.decos.containsKey("insert")){
			Mobile_HTML5Env.setIDU("insert");
		}	
		if(C1.decos.containsKey("update")){
			Mobile_HTML5Env.setIDU("update");
		}
		if(C1.decos.containsKey("delete")){
			Mobile_HTML5Env.setIDU("delete");
		}
		html_env.append_css_def_td(classid, C1.decos);

		//20130325  table0
		if(C1.decos.containsKey("table0"))	C1.table0Flg = true;
		else							C1.table0Flg = false;
		//20130314  table
		if(C1.decos.containsKey("table") || C1.table0Flg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg){
			C1.tableFlg = true;
		}//else	tableFlg = false;

		//20130326  div
		if(C1.decos.containsKey("div")){
			C1.divFlg = true;
			C1.tableFlg = false;
		}//else divFlg = false;

		//20161203 bootstrap
		if(Sass.isFirstElementFlg()){
			C1.firstFlg = true;
			Sass.firstElementFlg(false);
		}

		if(!GlobalEnv.isOpt()){
			//20160914 bootstrap
			if(!Sass.isBootstrapFlg()){
				//20130503  Panel
				//				C1.panelFlg = panelProcess1(C1.decos, html_env);

				//20130330 tab
				//tab1
				if(C1.decos.containsKey("tab1")){
					html_env.code.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
					html_env.code.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");

					tmp.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
					tmp.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");

					if(!C1.decos.getStr("tab1").equals("")){
						html_env.code.append(C1.decos.getStr("tab1"));
						tmp.append(C1.decos.getStr("tab1"));
					}
					else{
						html_env.code.append("tab1");
						tmp.append("tab1");
					}
					html_env.code.append("</a></li>\n");
					html_env.code.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");

					tmp.append("</a></li>\n");
					tmp.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
				}
				//tab2〜tab15
				else{
					int i=2;
					while(i<=Mobile_HTML5Env.maxTab){		//html_env.maxTab=15
						//Log.info("i="+i+" !!");
						if(C1.decos.containsKey("tab"+i) || (i==2 && C1.decos.containsKey("tab"))){
							//replace: </ul>の前に<li>〜</li>を付加
							String a = "</ul>";
							String b = "	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">";
							if(C1.decos.containsKey("tab"+i))
								if(!C1.decos.getStr("tab"+i).equals(""))	b += C1.decos.getStr("tab"+i);
								else				            		b += "tab"+i;
							else
								if(!C1.decos.getStr("tab").equals(""))		b += C1.decos.getStr("tab");
								else				            		b += "tab";
							b += "</a></li>\n";
							Mobile_HTML5Manager.replaceCode(html_env, a, b+a);

							//replace: 最後の</div></div></div>カット
							Mobile_HTML5Manager.replaceCode(html_env, "</div></div></div>", "");

							//    	        		//replace: 不要な「<div class=〜」をカット
							//    	        		if(!Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+Mobile_HTML5Env.getClassID(Func)+" \">", "")){
							//    	        		}

							html_env.code.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
							tmp.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
							break;
						}
						i++;
					}
				}

				//20130312 collapsible
				if(C1.decos.containsKey("collapse")){
					html_env.code.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");
					tmp.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");

					//header
					if(!C1.decos.getStr("collapse").equals("")){
						html_env.code.append("	<h1>"+C1.decos.getStr("collapse")+"</h1>\n");
						tmp.append("	<h1>"+C1.decos.getStr("collapse")+"</h1>\n");
					}else{
						html_env.code.append("<h1>Contents</h1>\n");
						tmp.append("<h1>Contents</h1>\n");
					}
				}

				//20130309
				//20160527 bootstrap
				if(!C1.tableFlg){
					//        		if(html_env.written_classid.contains(classid))
					html_env.code.append("<DIV Class=\"ui-grid #"+Mobile_HTML5Env.uiGridCount+" "+classid+"\"");
					tmp.append("<DIV Class=\"ui-grid #"+Mobile_HTML5Env.uiGridCount+" "+classid+"\"");
					//        		else
					//        			html_env.code.append("<DIV Class=\"ui-grid #"+Mobile_HTML5Env.uiGridCount+"\"");
					Mobile_HTML5Env.uiGridCount++;
				}
				if(!C1.tableFlg){
					html_env.code.append(">");		//20130309
					tmp.append(">");		//20130309
				}

				//20130314  table
				if(C1.tableFlg){
					//					html_env.code.append(getTableStartTag(html_env, C1.decos, C1)+"<TR>"); // taji comment
				}
			}//20160914 ifmobile

			//20160527 bootstrap
			else if(Sass.isBootstrapFlg()){
				if(!C1.tableFlg){
					if(C1.firstFlg){
						html_env.code.append("<DIV Class=\"row\">\n");
						html_env.code.append("<DIV Class=\""+classid+"\">\n");

						tmp.append("<DIV Class=\"row\">\n");
						tmp.append("<DIV Class=\""+classid+"\">\n");

						if(Sass.outofloopFlg.peekFirst()){
							//            				Sass.makeRowClass();
							//            				Sass.makeClass(classid);
							//            				Sass.defineGridBasic(classid, decos);

							//            				Sass.makeClass(classid);
							//            				Sass.defineGridBasic(classid, decos);
							//            				Sass.closeBracket();
							Sass.makeColumn(classid, C1.decos, "", -1);
						}
					}

					html_env.code.append("<DIV Class=\"row\">\n");
					tmp.append("<DIV Class=\"row\">\n");
					if(Sass.outofloopFlg.peekFirst()){
						//            			Sass.makeRowClass();
					}
				}

				if(C1.tableFlg){
					//					html_env.code.append(getTableStartTag(html_env, C1.decos, C1)+"<TR>"); //taji commentout
				}
			}

		}

		//			System.out.println("C1 tableFlg = " + tableFlg + ", divFlg = " + divFlg);
		int i = 0;
		if(Sass.outofloopFlg.peekFirst()){
			C1.gridMap = Sass.beforeC1WhileProcess(tfes);
			//        	Log.info(gridMap);
		}
		Mobile_HTML5.beforeWhileProcess(C1.getSymbol(), C1.decos, html_env);
		Infinite.beforeWhileProcess(C1.getSymbol(), C1.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		while (C1.hasMoreItems()) {
			ITFE tfe = (ITFE) tfes.get(i);
			DecorateList decos2 = ((TFE)tfe).decos;
			String classid2 = Mobile_HTML5Env.getClassID(tfe);
			C1.Count = ( (C1.gridInt>=C1.jj)? C1.jj:C1.gridInt );

			if(!Sass.isBootstrapFlg()){
				if(C1.decos.containsKey("table0") || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	C1.table0Flg = true;
				if(C1.decos.containsKey("table") || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || C1.table0Flg)	C1.tableFlg=true;
				if(C1.decos.containsKey("div")){
					C1.divFlg = true;
					C1.tableFlg = false;
				}//else divFlg = false;

				if(!C1.tableFlg){	//div
					//20131002
					int tfesItemNum = tfes.size();
					Mobile_HTML5Attribute.attributeDivWidth = Mobile_HTML5.getDivWidth("C1", C1.decos, tfesItemNum - Mobile_HTML5Function.func_null_count);	//null()
					html_env.code.append("\n<div class=\"ui-block "+classid2+"\">\n");	//20130309
					tmp.append("\n<div class=\"ui-block "+classid2+"\">\n");
					//					Log.ehtmlInfo("aaaaaaaa"+tmp);
					//added by goto 20161113  for function class width
					Mobile_HTML5Attribute.setWidth(classid2, C1.decos, html_env);
				}
				//20130314  table
				if(C1.tableFlg){
					html_env.code.append("<TD valign=\"middle\" class=\"" + classid2 + " nest\">\n");
					tmp.append("<TD valign=\"middle\" class=\"" + classid2 + " nest\">\n");
				}
				if(C1.decos.containsKey("text")){
					Mobile_HTML5Function.textFlg2 = true;
				}
			}else if(Sass.isBootstrapFlg()){
				((TFE)tfe).decos.put("C1","Done");
				if(Sass.outofloopFlg.peekFirst()){
					if(C1.gridMap.get(classid2).containsKey("xs")){
						decos2.put("xs", ""+C1.gridMap.get(classid2).get("xs"));
					}
					if(C1.gridMap.get(classid2).containsKey("sm")){
						decos2.put("sm", ""+C1.gridMap.get(classid2).get("sm"));
					}
					if(C1.gridMap.get(classid2).containsKey("md")){
						decos2.put("md", ""+C1.gridMap.get(classid2).get("md"));
					}
					if(C1.gridMap.get(classid2).containsKey("lg")){
						decos2.put("lg", ""+C1.gridMap.get(classid2).get("lg"));
					}
				}
				html_env.code.append("<div class=\"" + classid2 +"\">\n");
				tmp.append("<div class=\"" + classid2 +"\">\n");
				//taji commentout
				//				if(Sass.outofloopFlg.peekFirst()){
				//					Sass.(classid2, decos2, C1.getSymbol(), C1.responsiveId);
				//				}
			}

			html_env.cNum++;
			html_env.xmlDepth++;
			Mobile_HTML5.whileProcess1_2(C1.getSymbol(), C1.decos, html_env, data, data_info, tfe, tfes, tfeItems);
			C1.worknextItem();
			tmp.append(codes.toString());
			Mobile_HTML5.whileProcess2_1(C1.getSymbol(), C1.decos, html_env, data, data_info, tfe, tfes, tfeItems);
			html_env.cNum--;
			html_env.xmlDepth--;
			if(C1.decos.containsKey("table0") || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	C1.table0Flg = true;
			if(C1.decos.containsKey("table") || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || C1.table0Flg)	C1.tableFlg=true;
			if(C1.decos.containsKey("div")){
				C1.divFlg = true;
				C1.tableFlg = false;
			}//else divFlg = false;

			C1.ii++;
			C1.jj++;
			C1.gridInt++;
			if(Mobile_HTML5Function.func_null_count<1){	//null()
				if(!Sass.isBootstrapFlg()){
					if(!C1.tableFlg){
						html_env.code.append("</div>\n");	//20130309 20160527 bootstrap
						tmp.append("</div>\n");
					}
					if(C1.tableFlg){
						html_env.code.append("</TD>\n");	//20130314  table
						tmp.append("</TD>\n");	//20130314  table
					}
				}
				else if(Sass.isBootstrapFlg()){
					html_env.code.append("\n</DIV>\n");//.classid2
					tmp.append("\n</DIV>\n");//.classid2
					if(Sass.outofloopFlg.peekFirst()){
						//	            		Sass.closeBracket();//classid2
					}
				}
			}

			i++;

			Mobile_HTML5.whileProcess2_2(C1.getSymbol(), C1.decos, html_env, data, data_info, tfe, null, -1);

		}
		Infinite.afterWhileProcess(C1.getSymbol(), classid, C1.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		Mobile_HTML5.afterWhileProcess(C1.getSymbol(), classid, C1.decos, html_env);

		if(!Sass.isBootstrapFlg()){
			//20130309
			if(!C1.tableFlg){
				html_env.code.append("\n</DIV c1>\n");			//20130309
				tmp.append("\n</DIV c1>\n");
			}

			//20130314  table
			if(C1.tableFlg){
				html_env.code.append("</TR></TABLE>\n");	//20130309
				tmp.append("</TR></TABLE>\n");
				//	      		tableFlg = false;
				//	      		table0Flg = false;			//20130325 table0
			}

			//20130312 collapsible
			if(C1.decos.containsKey("collapse")){
				html_env.code.append("</DIV>");
				tmp.append("</DIV>");
			}

			//20130330 tab
			int a=1;
			while(a<=Mobile_HTML5Env.maxTab){
				//Log.info("a="+a);
				if(C1.decos.containsKey("tab"+a) || (a==1 && C1.decos.containsKey("tab"))){
					html_env.code.append("</div></div></div>\n");
					tmp.append("</div></div></div>\n");
					Mobile_HTML5Env.tabCount++;
					break;
				}
				a++;
			}

			//20130503  Panel
			//			panelProcess2(C1.decos, html_env, C1.panelFlg);

			C1.jj=0;

			if(C1.divFlg)	C1.divFlg = false;		//20130326  div
		}
		else if(Sass.isBootstrapFlg()){
			//        	html_env.code.append("\n</DIV>\n");//.row
			//        	html_env.code.append("\n</DIV>\n");//.TFE
			//      		if(Sass.outofloopFlg.peekFirst()){
			//      			Sass.closeBracket();
			//      			Sass.closeBracket();
			//      		}
			//      		if(!decos.containsKey("C1") && !decos.containsKey("G1")){
			//        		html_env.code.append("\n</DIV>\n");
			//        		if(Sass.outofloopFlg.peekFirst()){
			//        			Sass.closeBracket();
			//        		}
			//        	}
			html_env.code.append("</DIV>\n");//.row
			tmp.append("</DIV>\n");//.row
			if(Sass.outofloopFlg.peekFirst()){
				//        		Sass.closeBracket();//row
			}

			if(C1.firstFlg){
				html_env.code.append("</DIV>\n");//.classid
				html_env.code.append("</DIV>\n");//.row

				tmp.append("</DIV>\n");//.classid
				tmp.append("</DIV>\n");//.row

				if(Sass.outofloopFlg.peekFirst()){
					//        			Sass.closeBracket();//classid
					//        			Sass.closeBracket();//row
				}
				C1.firstFlg = false;
			}
		}
		Mobile_HTML5.postProcess(C1.getSymbol(), classid, C1.decos, html_env);	//Post-process (後処理)
		codes = tmp;
	}

	public static void C2(Mobile_HTML5C2 C2, Mobile_HTML5Env html_env, ExtList data_info, ExtList data, ExtList<TFE> tfes, int tfeItems){
		String outType = "div";
		String classid = Mobile_HTML5Env.getClassID(C2);

		String classid_for_ifs = "";
		String[] ifs_div_String = {"", ""};
		codes = new StringBuffer();
		StringBuffer tmp = new StringBuffer();

		//		if(html_env.xmlDepth!=0){
		//			// 親のoutTypeを継承
		//			outType = html_env.outTypeList.get(html_env.xmlDepth-1);
		//		}
		//		if (C2.decos.containsKey("table") || !outType.equals("div")) {
		//			html_env.outTypeList.add(html_env.xmlDepth, "table");
		//		} else {
		//			html_env.outTypeList.add(html_env.xmlDepth, "div");
		//		}
		//		if (C2.decos.containsKey("div")) {
		//			html_env.outTypeList.add(html_env.xmlDepth, "div");
		//		}
		//			System.out.println("C2 tableFlg = " + tableFlg + ", divFlg = " + divFlg);
		html_env.append_css_def_td(html_env.getClassID(C2), C2.decos);
		//modified by taji
		Mobile_HTML5.beforeWhileProcess(C2.getSymbol(), C2.decos, html_env);
		Infinite.beforeWhileProcess(C2.getSymbol(), C2.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		int i = 0;
		while (C2.hasMoreItems()) {
			ITFE tfe = (ITFE) tfes.get(i);
			DecorateList decos2 = ((TFE)tfe).decos;
			String classid2 = Mobile_HTML5Env.getClassID(tfe);
			if(!Sass.isBootstrapFlg()){
				if(C2.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	C2.table0Flg = true;
				if(C2.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || C2.table0Flg)	C2.tableFlg=true;
				if(C2.decos.containsKey("div")){
					C2.divFlg = true;
					C2.tableFlg = false;
				}//else divFlg = false;

				//20130312 collapsible
				if(C2.decos.containsKey("collapse")){
					html_env.code.append("<p>\n");
					tmp.append("<p>\n");
				}
				//20160527 bootstrap
				else if(!C2.tableFlg && !Mobile_HTML5Function.textFlg2){
					//20130309
					//					html_env.code.append("<div class=\""+classid2+" \">\n");
					html_env.code.append("<div class=\""+classid+" \">\n");
					tmp.append("<div class=\""+classid+" \">\n");
				}

				//20130314  table
				if(C2.tableFlg){
					html_env.code.append("<TR><TD valign=\"middle\" class=\""
							+ classid2 + " nest\">\n");
					tmp.append("<TR><TD valign=\"middle\" class=\""
							+ classid2 + " nest\">\n");
					Log.out("<TR><TD class=\"nest "
							+ classid2 + " nest\"> decos:" + C2.decos);
				}
			}else if(Sass.isBootstrapFlg()){
				html_env.code.append("<DIV Class=\"row\">\n");
				html_env.code.append("<div class=\"" + classid2 +"\">\n");

				tmp.append("<DIV Class=\"row\">\n");
				tmp.append("<div class=\"" + classid2 +"\">\n");

				if(Sass.outofloopFlg.peekFirst()){
					Sass.makeColumn(classid2, decos2, "", -1);
				}
			}

			html_env.cNum++;
			html_env.xmlDepth++;
			Mobile_HTML5.whileProcess1_2(C2.getSymbol(), C2.decos, html_env, data, data_info, tfe, tfes, tfeItems);
			C2.worknextItem();
			tmp.append(codes.toString());
			Mobile_HTML5.whileProcess2_1(C2.getSymbol(), C2.decos, html_env, data, data_info, tfe, tfes, tfeItems);
			if(!Sass.isBootstrapFlg()){
				if(C2.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	C2.table0Flg = true;
				if(C2.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || C2.table0Flg)	C2.tableFlg=true;
				if(C2.decos.containsKey("div")){
					C2.divFlg = true;
					C2.tableFlg = false;
				}//else divFlg = false;
				//20130314  table
				if(C2.tableFlg){
					html_env.code.append("</TD></TR>\n");
					tmp.append("</TD></TR>\n");
				}
				//Log.out("</TD></TR>");

				//20130312 collapsible
				if(C2.decos.containsKey("collapse")){
					html_env.code.append("</p>\n");
					tmp.append("</p>\n");
				}
				//20160527 bootstrap
				else if(!C2.tableFlg && !Mobile_HTML5Function.textFlg && !Mobile_HTML5Function.textFlg2){	//20130914  "text"
					html_env.code.append("\n</div>");
					tmp.append("\n</div>");
				}
				if(Mobile_HTML5Function.textFlg){					//20130914  "text"
					Mobile_HTML5Function.textFlg = false;
				}
			}else if(Sass.isBootstrapFlg()){
				html_env.code.append("</div>\n");//classid2
				html_env.code.append("</div>\n");//row

				tmp.append("</div>\n");//classid2
				tmp.append("</div>\n");//row
				if(Sass.outofloopFlg.peekFirst()){
				}
			}
			html_env.code.append("\n");		//20130309
			tmp.append("\n");

			html_env.cNum--;
			html_env.xmlDepth--;
			Mobile_HTML5.whileProcess2_2(C2.getSymbol(), C2.decos, html_env, data, data_info, tfe, null, -1);
			i++;
		}
		Infinite.afterWhileProcess(C2.getSymbol(), classid, C2.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		Mobile_HTML5.afterWhileProcess(C2.getSymbol(), classid, C2.decos, html_env);
		codes = tmp;
	}

	public static void G2(Mobile_HTML5G2 G2, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2, ExtList data_info, ExtList data, ITFE tfe){
		String classid = Mobile_HTML5Env.getClassID(G2);
		String classid2 = Mobile_HTML5Env.getClassID(tfe);
		String row = "";
		String column = "";
		String classid_for_ifs = "";
		String[] ifs_div_String = {"", ""};
		codes = new StringBuffer();
		StringBuffer tmp = new StringBuffer();
		if(!Infinite.preProcess(G2.getSymbol(), G2.decos, html_env)) return;	//Pre-process (前処理)

		IS_level++;

		if(G2.decos.containsKey("infinite-scroll")){
			classid_for_ifs = classid + "_wrapper";
			DecorateList deco_ifs = new DecorateList();
			deco_ifs.put("infinite-scroll", "");
			html_env.append_css_def_td(G2.getSymbol(), classid_for_ifs, classid2 ,deco_ifs);
			G2.decos.remove("infinite-scroll");
			ifs_div_String = Mobile_HTML5.ifs_div_start(G2.getSymbol(), html_env, classid_for_ifs, ifs_div_String);

			decompose.add(true);
			if(IS_level > 1){
				if(!decompose.get(Infinitescroll.IS_level-2)){
					IS_point++;
				}
			}else{
				IS_point++;
			}
			
		}else{
			decompose.add(false);
			//			if(IS_level > 1){
			//				
			//			}
		}

		// ページネーション
		if(Mobile_HTML5Env.getSelectFlg())
			data_info = (ExtList) data_info.get(0);
		html_env.append_css_def_td(classid, G2.decos);

		//20130325  table0
		if(G2.decos.containsKey("table0"))	Mobile_HTML5G2.table0Flg = true;
		else							Mobile_HTML5G2.table0Flg = false;
		//20130314  table
		if(G2.decos.containsKey("table") || Mobile_HTML5G2.table0Flg || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg){
			Mobile_HTML5G2.tableFlg = true;
		}//else	tableFlg = false;

		//20130326  div
		if(G2.decos.containsKey("div")){
			Mobile_HTML5G2.divFlg = true;
			Mobile_HTML5G2.tableFlg = false;
		}//else divFlg = false;

		//20130914  "text"
		if(G2.decos.containsKey("text")){
			Mobile_HTML5Function.textFlg2 = true;
		}

		//20161203 bootstrap
		if(Sass.isFirstElementFlg()){
			G2.firstFlg = true;
			Sass.firstElementFlg(false);
		}

		if(!GlobalEnv.isOpt()){
			if(!Sass.isBootstrapFlg()){
				//20130503  Panel
				//				panelFlg = Mobile_HTML5C1.panelProcess1(G2.decos, html_env);

				//20130330 tab
				//tab1
				if(G2.decos.containsKey("tab1")){
					html_env.code.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
					html_env.code.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");
					if(!G2.decos.getStr("tab1").equals(""))	html_env.code.append(G2.decos.getStr("tab1"));
					else          							html_env.code.append("tab1");
					html_env.code.append("</a></li>\n");
					html_env.code.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
				}
				//tab2〜tab15
				else{
					int i=2;
					while(i<=Mobile_HTML5Env.maxTab){		//HTMLEnv.maxTab=15
						if(G2.decos.containsKey("tab"+i) || (i==2 && G2.decos.containsKey("tab"))){
							//replace: </ul>の前に<li>〜</li>を付加
							String a = "</ul>";
							String b = "	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">";
							if(G2.decos.containsKey("tab"+i))
								if(!G2.decos.getStr("tab"+i).equals(""))	b += G2.decos.getStr("tab"+i);
								else				            		b += "tab"+i;
							else
								if(!G2.decos.getStr("tab").equals(""))		b += G2.decos.getStr("tab");
								else				            		b += "tab";
							b += "</a></li>\n";
							Mobile_HTML5Manager.replaceCode(html_env, a, b+a);

							//replace: 最後の</div></div></div>カット
							Mobile_HTML5Manager.replaceCode(html_env, "</div></div></div>", "");

							//    	        		//replace: 不要な「<div class=〜」をカット
							//    	        		Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+Mobile_HTML5Env.getClassID(Func)+" \">", "");

							html_env.code.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
							break;
						}
						i++;
					}
				}

				//20130312 collapsible
				if(G2.decos.containsKey("collapse")){
					html_env.code.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");

					//header
					if(!G2.decos.getStr("collapse").equals(""))
						html_env.code.append("	<h1>"+G2.decos.getStr("collapse")+"</h1>\n");
					else
						html_env.code.append("<h1>Contents</h1>\n");
				}

				//20130309
				//20130314  table
				if(Mobile_HTML5G2.tableFlg){
					if(G2.row>1 && Mobile_HTML5G2.tableFlg)	Mobile_HTML5G2.tableStartTag = Mobile_HTML5C1.getTableStartTag(html_env, G2.decos, G2);
					else					html_env.code.append(Mobile_HTML5C1.getTableStartTag(html_env, G2.decos, G2)+"\n");
				}
			}else if(Sass.isBootstrapFlg()){
				//        		if(!decos.containsKey("C1") && !decos.containsKey("G1")){
				//            		html_env.code.append("<DIV Class=\"row\">");
				//            		if(Sass.outofloopFlg.peekFirst()){
				//            			Sass.makeRowClass();
				//            		}
				//            	}
				//        		html_env.code.append("<DIV Class=\""+classid+"\">");
				//        		if(Sass.outofloopFlg.peekFirst()){
				//        			Sass.makeClass(classid);
				//        			Sass.defineGridBasic(classid, decos);
				//	      		}
				//        		Sass.beforeLoop();
				if(G2.firstFlg){
					html_env.code.append("<DIV Class=\"row\">\n");
					html_env.code.append("<DIV Class=\""+classid+"\">\n");

					tmp.append("<DIV Class=\"row\">\n");
					tmp.append("<DIV Class=\""+classid+"\">\n");

					if(Sass.outofloopFlg.peekFirst()){
						//        				Sass.makeRowClass();
						//        				Sass.makeClass(classid);
						//        				Sass.defineGridBasic(classid, decos);

						//        				Sass.makeClass(classid);
						//        				Sass.defineGridBasic(classid, decos);
						//        				Sass.closeBracket();
						Sass.makeColumn(classid, G2.decos, "", -1);
					}
				}
				Sass.beforeLoop();
			}
		}
		Mobile_HTML5.beforeWhileProcess(G2.getSymbol(), G2.decos, html_env, ifs_div_String);
		Infinite.beforeWhileProcess(G2.getSymbol(), G2.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		while (G2.hasMoreItems()) {
			// System.out.println("ここ: tableFlg = " + tableFlg +
			// ", divFlg = " + divFlg);
			Mobile_HTML5.gLevel1++;
			Mobile_HTML5.whileProcess1_1(G2.getSymbol(), G2.decos, html_env, data, data_info, tfe, null, -1);
			//			String classid2 = Mobile_HTML5Env.getClassID(tfe);
			DecorateList decos2 = ((TFE)tfe).decos;
			Mobile_HTML5Function.setGlvl(html_env.getGlevel());	//added by goto 20130914  "SEQ_NUM"
			//[重要] For [ [], ]!        	
			Mobile_HTML5G1.jj = 0;
			Mobile_HTML5G1.gridInt = 0;

			if(G2.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg)	Mobile_HTML5G2.table0Flg = true;
			if(G2.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.table0Flg)	Mobile_HTML5G2.tableFlg=true;
			if(G2.decos.containsKey("div")){
				Mobile_HTML5G2.divFlg = true;
				Mobile_HTML5G2.tableFlg = false;
			}

			html_env.setGlevel(html_env.getGlevel() + 1);
			if(G2.rowFlg){
				html_env.code = new StringBuffer();
				html_env.countfile++;
				html_env.filename = html_env.outfile + "_row" + G2.rowFileNum + "_" + G2.j + Compiler.getExtension();
				html_env.nextbackfile = html_env.linkoutfile + "_row" + G2.rowFileNum + "_" + G2.j + Compiler.getExtension();
				html_env.setOutlineMode();
			}
			if( Mobile_HTML5Env.getSelectRepeat() ){//if form_select
			}else{
				if(!Sass.isBootstrapFlg()){
					//20130312 collapsible
					if(G2.decos.containsKey("collapse")){
						html_env.code.append("<p>\n");
						tmp.append("<p>\n");
					}
					//20130309
					if(!Mobile_HTML5G2.tableFlg){
						html_env.code.append("\n<div class=\""+classid+" "+Mobile_HTML5_show.addShowCountClassName(G2.decos)+"\">\n");	//20130309  div
						tmp.append("\n<div class=\""+classid+" "+Mobile_HTML5_show.addShowCountClassName(G2.decos)+"\">\n");	//20130309  div
					}else if(Mobile_HTML5G2.tableFlg){
						//20130314  table
						html_env.code.append("<TR><TD class=\"" + classid + " "+Mobile_HTML5_show.addShowCountClassName(G2.decos)+" nest\">\n");
						tmp.append("<TR><TD class=\"" + classid + " "+Mobile_HTML5_show.addShowCountClassName(G2.decos)+" nest\">\n");
						Log.out("<TR><TD class=\"" + classid + " nest\">");
					}
				}else if(Sass.isBootstrapFlg()){
					html_env.code.append("<DIV Class=\"row\">\n");
					html_env.code.append("<div class=\"" + classid2 +"\">\n");

					tmp.append("<DIV Class=\"row\">\n");
					tmp.append("<div class=\"" + classid2 +"\">\n");

					if(Sass.outofloopFlg.peekFirst()){
						Sass.makeColumn(classid2, decos2, "", -1);
					}
				}
			}

			html_env.xmlDepth++;
			Mobile_HTML5.whileProcess1_2(G2.getSymbol(), G2.decos, html_env, data, data_info, tfe, null, -1);
			G2.worknextItem();
			tmp.append(codes.toString());
			//			Log.ehtmlInfo(classid_for_ifs);
			//			Log.ehtmlInfo(tmp);
			Mobile_HTML5.whileProcess2_1(G2.getSymbol(), G2.decos, html_env, data, data_info, tfe, null, -1);
			if(G2.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg)	Mobile_HTML5G2.table0Flg = true;
			if(G2.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.table0Flg)	Mobile_HTML5G2.tableFlg=true;
			if(G2.decos.containsKey("div")){
				Mobile_HTML5G2.divFlg = true;
				Mobile_HTML5G2.tableFlg = false;
			}

			html_env.xmlDepth--;
			if(Mobile_HTML5Env.getSelectRepeat()){

			}else{	 
				html_env2.code.append("</tfe>");
				if(!Sass.isBootstrapFlg()){
					if(!Mobile_HTML5G2.tableFlg){
						if(!Mobile_HTML5Function.textFlg2){
							html_env.code.append("</div>\n");		//20130309  div	//20130914  "text"
							tmp.append("</div>\n");
						}
					}else if(Mobile_HTML5G2.tableFlg){
						html_env.code.append("</TD></TR>\n");			//20130314  table
						Log.out("</TD></TR>");
					}
				}else if(Sass.isBootstrapFlg()){
					html_env.code.append("</div>\n");//classid2
					html_env.code.append("</div>\n");//row

					tmp.append("</div>\n");//classid2
					tmp.append("</div>\n");//row

					if(Sass.outofloopFlg.peekFirst()){
						//                		Sass.closeBracket();//classid2
						//                		Sass.closeBracket();//row
					}
				}

				html_env.code = Embed.postProcess(html_env.code);	//goto 20130915-2  "<$  $>"

				//20130312 collapsible
				if(G2.decos.containsKey("collapse")){
					html_env.code.append("</p>\n");
					tmp.append("</p>\n");
				}
			}
			//20160527 bootstrap
			if(Sass.isBootstrapFlg()){
				Sass.afterFirstLoop();
			}

			html_env.setGlevel(html_env.getGlevel() - 1);
			Mobile_HTML5G2.tableDivHeader_Count1++;	//20131001 tableDivHeader

			//added by goto 20130413  "row Prev/Next"
			if(G2.rowFlg){
				G2.codeBuf.append(html_env.code);
				if((G2.rowNum+1)%G2.row==0){
					Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, G2.codeBuf);
					G2.j++;
					G2.codeBuf = new StringBuffer();
				}
				G2.rowNum++;
			}
			if(!Mobile_HTML5.whileProcess2_2(G2.getSymbol(), G2.decos, html_env, data, data_info, tfe, null, -1)){
				break;
			}

			Mobile_HTML5.gLevel1--;
		}
		Infinite.afterWhileProcess(G2.getSymbol(), classid, G2.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		Mobile_HTML5.afterWhileProcess(G2.getSymbol(), classid, G2.decos, html_env, ifs_div_String);
		if (Sass.isBootstrapFlg()){
			Sass.afterLoop();
			if(G2.firstFlg){
				html_env.code.append("</DIV>\n");//.classid
				html_env.code.append("</DIV>\n");//.row

				tmp.append("</DIV>\n");//.classid
				tmp.append("</DIV>\n");//.row

				if(Sass.outofloopFlg.peekFirst()){
					//        			Sass.closeBracket();//classid
					//        			Sass.closeBracket();//row
				}
				G2.firstFlg = false;
			}
		}

		//added by goto 20130413  "row Prev/Next"
		if(G2.rowFlg){
			if(G2.rowNum%G2.row!=0){	//最後の child HTML を create
				Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, G2.codeBuf);
			}
			// taji todo
			//			html_env.filename = parentfile;
			//			html_env.code = parentcode;
			//			html_env.header = parentheader;
			//			html_env.footer = parentfooter;
			//			html_env.nextbackfile = parentnextbackfile;
			Log.out("TFEId = " + classid);
			html_env.append_css_def_td(classid, G2.decos);

			int first = 1, last = ((G2.rowNum%G2.row!=0)? (G2.rowNum/G2.row+1):(G2.rowNum/G2.row));
			Mobile_HTML5G2.PrevNextProcess(html_env, G2.rowNum, G2.row, first, last, 1);
		}

		if(Mobile_HTML5Env.getSelectRepeat()){		
			if(Mobile_HTML5Env.getSelectRepeat()){
				//chie
				html_env2.code.append("</select></VALUE></tfe>");
				html_env.code.append("</select></TD></TR>\n");
				Log.out("</TD></TR>");
				Mobile_HTML5Env.setSelectRepeat(false);
				Mobile_HTML5Env.incrementFormPartsNumber();
			}else{
				Mobile_HTML5Env.incrementFormPartsNumber();
			}
		}

		//20130314  table
		if(Mobile_HTML5G2.tableFlg){
			if(!(G2.row>1 && Mobile_HTML5G2.tableFlg))	html_env.code.append("</TABLE>\n");		//20130309
			Mobile_HTML5G2.tableFlg = false;
			Mobile_HTML5G2.table0Flg = false;		//20130325 table0
		}
		Log.out("</TABLE>");

		//20130312 collapsible
		if(G2.decos.containsKey("collapse")){
			html_env.code.append("</DIV>");
			tmp.append("</DIV>");
		}

		//20130330 tab
		int a=1;
		while(a<=Mobile_HTML5Env.maxTab){
			if(G2.decos.containsKey("tab"+a) || (a==1 && G2.decos.containsKey("tab"))){
				html_env.code.append("</div></div></div>\n");
				tmp.append("</div></div></div>\n");
				Mobile_HTML5Env.tabCount++;
				break;
			}
			a++;
		}

		//20130503  Panel
		//		Mobile_HTML5C1.panelProcess2(G2.decos, html_env, G2.panelFlg);

		if(Mobile_HTML5G2.divFlg)	Mobile_HTML5G2.divFlg = false;		//20130326  div

		//added by goto 20130413  "row Prev/Next"
		if(G2.rowFlg){
			G2.rowFileNum++;
			G2.rowFlg = false;
			G2.tableStartTag = "";
		}

		//20130914  "text"
		if(Mobile_HTML5Function.textFlg2){
			Mobile_HTML5Function.textFlg2 = false;
		}

		//        if(Sass.isBootstrapFlg()){
		//
		//        }
		//		codes.append(tmp.toString());

		if(IS_level > 1){
			if(Infinitescroll.decompose.get(Infinitescroll.IS_level-2)){
				IS_point--;
			}
		}
		IS_level--;
		StringBuffer before = new StringBuffer();
		before.append("\n<div class=\""+ classid_for_ifs +"\" onscroll=\"scrolled(this)\" itemnum=\"'.$i1.'\">\n");//todo $i1
		before.append(tmp.toString());
		before.append("</div>\n");
		tmp = before;
		codes = tmp;
		Mobile_HTML5.postProcess(G2.getSymbol(), classid, G2.decos, html_env);	//Post-process (後処理)

		//added by goto 20130914  "SEQ_NUM"
		Mobile_HTML5Function.Func_seq_num_initialization(html_env.getGlevel());
	}

	public static void G1(Mobile_HTML5G1 G1, Mobile_HTML5Env html_env, ExtList data_info, ExtList data, ITFE tfe){
		String classid = Mobile_HTML5Env.getClassID(G1);
		String classid2 = Mobile_HTML5Env.getClassID(tfe);
		String row = "";
		String column = "";
		String[] ifs_div_String = {"", ""};
		String classid_for_ifs = "";
		codes = new StringBuffer();

		IS_level++;

		int infinite_scroll_num = 0;
		StringBuffer tmp = new StringBuffer();
		if(!Infinite.preProcess(G1.getSymbol(), G1.decos, html_env))	return;
		if(G1.decos.containsKey("infinite-scroll")){
			classid_for_ifs = classid + "_wrapper";// div class=TFE10009_wrapper
			DecorateList deco_ifs = new DecorateList();

			infinite_scroll_num = Integer.parseInt(G1.decos.getStr("infinite-scroll"));//infinite-scroll = 3

			deco_ifs.put("infinite-scroll", "");
			html_env.append_css_def_td(G1.getSymbol(), classid_for_ifs, classid2, deco_ifs);//make css for IS
			G1.decos.remove("infinite-scroll");
			ifs_div_String = Infinite.ifs_div_start(G1.getSymbol(), html_env, classid_for_ifs, ifs_div_String);

			decompose.add(true);
			if(IS_level > 1){
				if(!decompose.get(Infinitescroll.IS_level-2)){
					IS_point++;
				}
			}else{
				IS_point++;
			}
			
		}else{
			decompose.add(false);
		}
		html_env.append_css_def_td(classid, G1.decos);
		// ページネーション
		if (G1.decos.containsKey("row") && G1.decos.containsKey("column")) {
			html_env.g1PaginationRowNum = Integer.parseInt(G1.decos
					.getStr("row"));
			row = " row=\'" + html_env.g1PaginationRowNum + "\'";
			html_env.g1PaginationColumnNum = Integer.parseInt(G1.decos
					.getStr("column"));
			column = " column=\'" + html_env.g1PaginationColumnNum + "\'";
		} else if (G1.decos.containsKey("column")) { // 複合反復子
			html_env.g1RetNum = Integer.parseInt(G1.decos.getStr("column"));
			column = " column=\'" + html_env.g1RetNum + "\'";
		}

		if(!GlobalEnv.isOpt()){
			if(!Sass.isBootstrapFlg()){
				//20130503  Panel
				//				G1.panelFlg = Mobile_HTML5C1.panelProcess1(G1.decos, html_env);

				//20130330 tab
				//tab1
				if(G1.decos.containsKey("tab1")){
					html_env.code.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
					html_env.code.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");

					tmp.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
					tmp.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");
					if(!G1.decos.getStr("tab1").equals("")){
						html_env.code.append(G1.decos.getStr("tab1"));
						tmp.append(G1.decos.getStr("tab1"));
					}
					else{
						html_env.code.append("tab1");
						tmp.append("tab1");
					}
					html_env.code.append("</a></li>\n");
					html_env.code.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");

					tmp.append("</a></li>\n");
					tmp.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
				}
				//tab2〜tab15
				else{
					int i=2;
					while(i<=Mobile_HTML5Env.maxTab){		//HTMLEnv.maxTab=15
						if(G1.decos.containsKey("tab"+i) || (i==2 && G1.decos.containsKey("tab"))){
							//replace: </ul>の前に<li>〜</li>を付加
							String a = "</ul>";
							String b = "	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">";
							if(G1.decos.containsKey("tab"+i))
								if(!G1.decos.getStr("tab"+i).equals(""))	b += G1.decos.getStr("tab"+i);
								else				            		b += "tab"+i;
							else
								if(!G1.decos.getStr("tab").equals(""))		b += G1.decos.getStr("tab");
								else				            		b += "tab";
							b += "</a></li>\n";
							Mobile_HTML5Manager.replaceCode(html_env, a, b+a);

							//replace: 最後の</div></div></div>カット
							Mobile_HTML5Manager.replaceCode(html_env, "</div></div></div>", "");

							//replace: 不要な「<div class=〜」をカット
							Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+classid+" \">", "");

							html_env.code.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
							tmp.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
							break;
						}
						i++;
					}
				}

				//20130312 collapsible
				if(G1.decos.containsKey("collapse")){
					html_env.code.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");
					tmp.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");

					//header
					if(!G1.decos.getStr("collapse").equals("")){
						html_env.code.append("	<h1>"+G1.decos.getStr("collapse")+"</h1>\n");
						tmp.append("	<h1>"+G1.decos.getStr("collapse")+"</h1>\n");
					}else{
						html_env.code.append("<h1>Contents</h1>\n");
						tmp.append("<h1>Contents</h1>\n");
					}
				}
				//20130309
				if(!G1.tableFlg){
					//        		if(html_env.written_classid.contains(classid))
					html_env.code.append("\n<DIV Class=\"ui-grid ##"+Mobile_HTML5Env.uiGridCount2+" "+classid+"\"");
					//					Log.ehtmlInfo(html_env.code);
					//					tmp.append("\n<DIV Class=\"ui-grid ##"+Mobile_HTML5Env.uiGridCount2+" "+classid+"\"");//taji 消して大丈夫？
					//        		else
					//        			html_env.code.append("\n<DIV Class=\"ui-grid ##"+Mobile_HTML5Env.uiGridCount2+"\"");
					html_env.code.append(">\n");
					//					tmp.append(">\n");//taji 消して大丈夫？
					Mobile_HTML5Env.uiGridCount2++;
				}

				//20130314  table
				if(G1.tableFlg){
					//added by goto 20130318  横スクロール
					if(G1.numberOfColumns < 0){
						html_env.code.append("<div style=\"overflow:auto;\">\n");	//20130917  [ ],10@{table}
						tmp.append("<div style=\"overflow:auto;\">\n");	//20130917  [ ],10@{table}
					}

					if(G1.row>1 && G1.tableFlg)	Mobile_HTML5G2.tableStartTag = Mobile_HTML5C1.getTableStartTag(html_env, G1.decos, G1)+"<TR>";
					else{
						html_env.code.append(Mobile_HTML5C1.getTableStartTag(html_env, G1.decos, G1)+"<TR>");
						tmp.append(Mobile_HTML5C1.getTableStartTag(html_env, G1.decos, G1)+"<TR>");
					}
				}
			}
		}
		// System.out.println("G1 tableFlg = " + tableFlg + ", divFlg = " +
		// divFlg);
		html_env.append_css_def_td(HTMLEnv.getClassID(G1), G1.decos);
		Mobile_HTML5.beforeWhileProcess(G1.getSymbol(), G1.decos, html_env, ifs_div_String);
		Infinite.beforeWhileProcess(G1.getSymbol(), G1.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		while (G1.hasMoreItems()) {
			Mobile_HTML5.gLevel1++;
			Mobile_HTML5.whileProcess1_1(G1.getSymbol(), G1.decos, html_env, data, data_info, tfe, null, -1);

			Mobile_HTML5Function.setGlvl(html_env.getGlevel());	//added by goto 20130914  "SEQ_NUM"
			if(G1.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G2.table0Flg)	G1.table0Flg = true;
			if(G1.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G2.tableFlg || G1.table0Flg)	G1.tableFlg=true;
			if(G1.decos.containsKey("div")){
				G1.divFlg = true;
				G1.tableFlg = false;
			}
			html_env.setGlevel(html_env.getGlevel() + 1);

			if(G1.rowFlg){
				html_env.code = new StringBuffer();
				html_env.countfile++;
				html_env.filename = html_env.outfile + "_row" + Mobile_HTML5G2.rowFileNum + "_" + G1.j + Compiler.getExtension();
				html_env.nextbackfile = html_env.linkoutfile + "_row" + Mobile_HTML5G2.rowFileNum + "_" + G1.j + Compiler.getExtension();
				html_env.setOutlineMode();
			}


			//20130309
			G1.Count = ( ((G1.gridInt>=G1.jj)&&(Mobile_HTML5G1.G1Flg))? G1.jj:G1.gridInt );
			G1.Count %= G1.numberOfColumns;


			//20130917  [ ],10@{table}
			if(G1.tableFlg && G1.numberOfColumns > 1 && G1.Count == 0){
				if(G1.table_column_num>0)	html_env.code.append("</TR><TR>\n");
				else					G1.table_column_num++;
			}

			if(!Sass.isBootstrapFlg()){
				//20130309
				if(!G1.tableFlg){
					//20131002
					//TODO style=を.cssへ
					String divWidth = Mobile_HTML5.getDivWidth("G1", G1.decos, G1.numberOfColumns - Mobile_HTML5Function.func_null_count);	//null()
					if(!G1.decos.containsKey("column")){
						if(G1.Count!=0){
							html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\">\n");
							tmp.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\">\n");
						}
						else{
							if(!Mobile_HTML5_dynamic.dynamicDisplay){
								html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\" style=\"clear:left;\">\n");
								tmp.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\" style=\"clear:left;\">\n");
							}else{
								//html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\" '.(($j++>0)? '' : 'style=\"clear:left;\"').'>\n");
								html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\" '.(($i"+(Mobile_HTML5.gLevel0+1)+">0)? '' : 'style=\"clear:left;\"').'>\n");	//TODO d2 change if ?
								tmp.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1.G1_count+"\" '.(($i"+(Infinite.gLevel0+1)+">0)? '' : 'style=\"clear:left;\"').'>\n");
							}
						}
						if(G1.gridInt<1)	Mobile_HTML5Attribute.attributeDivWidth2 += "."+classid2+"-"+G1.G1_count+"{ width:"+divWidth+"; }\n";
					}else{
						if(G1.Count!=0){
							html_env.code.append("\n<div class=\"ui-block"+" "+classid2+"\">\n");
							tmp.append("\n<div class=\"ui-block"+" "+classid2+"\">\n");
						}
						else{
							html_env.code.append("\n<div class=\"ui-block"+" "+classid2+"\" style=\"clear:left;\">\n");
							tmp.append("\n<div class=\"ui-block"+" "+classid2+"\" style=\"clear:left;\">\n");
						}
						Mobile_HTML5Attribute.attributeDivWidth = divWidth;
					}
					//System.out.println(Func.decos+ " "+ divWidth+" "+gridInt);
				}
				//20130314  table
				//20160527 bootstrap
				else if(G1.tableFlg){
					html_env.code.append("<TD class=\"" + classid2 + " nest\">\n");
					tmp.append("<TD class=\"" + classid2 + " nest\">\n");
				}

				classid = classid2;

				//    	    if(Mobile_HTML5Env.dynamicFlg){	//20130529 dynamic
				//	      		//☆★
				//	      		Log.info("★★G1-1 tfe : " + tfe);
				//	    		//☆★            Log.info("G1 tfe : " + tfe);
				//	            //☆★            Log.info("G1 tfes : " + Func.tfes);
				//	            //☆★            Log.info("G1 tfeItems : " + Func.tfeItems);
				//	      	}
			}else if(Sass.isBootstrapFlg()){
				G1.decos.put("G1",""+(G1.numberOfColumns - Mobile_HTML5Function.func_null_count));
				html_env.code.append("<div class=\"" + classid2 +"\">\n");
				tmp.append("<div class=\"" + classid2 +"\">\n");
				if(Sass.outofloopFlg.peekFirst()){
					//            		Sass.makeClass(classid2);
					//            		Sass.defineGridBasic(classid2, (tfe).decos);

					//            		Sass.makeClass(classid2);
					//            		Sass.defineGridBasic(classid2, (tfe).decos);
					//            		Sass.closeBracket();
					//taji comment out
					//					Sass.makeColumn(classid2, (tfe).decos, getSymbol(), responsiveId);
					//				}
				}
			}

			Mobile_HTML5.whileProcess1_2(G1.getSymbol(), G1.decos, html_env, data, data_info, tfe, null, -1);

			G1.worknextItem();
			tmp.append(codes.toString());
			Mobile_HTML5.whileProcess2_1(G1.getSymbol(), G1.decos, html_env, data, data_info, tfe, null, -1);

			if(G1.decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G2.table0Flg)	Mobile_HTML5G1.table0Flg = true;
			if(G1.decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G2.tableFlg || Mobile_HTML5G1.table0Flg)	Mobile_HTML5G1.tableFlg=true;
			if(G1.decos.containsKey("div")){
				G1.divFlg = true;
				G1.tableFlg = false;
			}

			G1.ii++;
			G1.jj++;
			G1.gridInt++;

			//20160527 bootstrap
			if(!Sass.isBootstrapFlg()){
				if(!G1.tableFlg){
					html_env.code.append("</div>");	//20130309
					tmp.append("</div>");
				}
				else if(G1.tableFlg){
					html_env.code.append("</TD>\n");    //20130314 table
					tmp.append("</TD>\n");    //20130314 table
				}
			}else if(Sass.isBootstrapFlg()){
				html_env.code.append("</div>\n");//classid2
				tmp.append("</div>\n");//classid2
				if(Sass.outofloopFlg.peekFirst()){
					//            		Sass.closeBracket();//classid2
				}
				Sass.afterFirstLoop();
			}

			html_env.setGlevel(html_env.getGlevel() - 1);

			Mobile_HTML5G2.tableDivHeader_Count1++;	//20131001 tableDivHeader

			//added by goto 20130413  "row Prev/Next"
			if(G1.rowFlg){
				G1.codeBuf.append(html_env.code);
				if((G1.rowNum+1)%(G1.row*G1.numberOfColumns)==0){
					Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, G1.codeBuf);
					G1.j++;
					G1.codeBuf = new StringBuffer();
				}
				G1.rowNum++;
			}

			if(!Mobile_HTML5.whileProcess2_2(G1.getSymbol(), G1.decos, html_env, data, data_info, tfe, null, -1))	break;
			Mobile_HTML5.gLevel1--;
		}
		Infinite.afterWhileProcess(G1.getSymbol(), classid2, G1.decos, html_env, ifs_div_String, classid_for_ifs, tmp);
		Mobile_HTML5.afterWhileProcess(G1.getSymbol(), classid2, G1.decos, html_env, ifs_div_String);

		//added by goto 20130413  "row Prev/Next"
		if(G1.rowFlg){
			if(G1.rowNum%(G1.row*G1.numberOfColumns)!=0){	//最後の child HTML を create
				Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, G1.codeBuf);
			}
			//ファイル名・コード等をparent HTMLのものへ戻す
			//					html_env.filename = parentfile;
			//					html_env.code = parentcode;
			//					html_env.header = parentheader;
			//					html_env.footer = parentfooter;
			//					html_env.nextbackfile = parentnextbackfile;
			Log.out("TFEId = " + classid);
			html_env.append_css_def_td(classid, G1.decos);

			int first = 1, last = ((G1.rowNum%(G1.row*G1.numberOfColumns)!=0)? (G1.rowNum/(G1.row*G1.numberOfColumns)+1):(G1.rowNum/(G1.row*G1.numberOfColumns)));	//for G1
			Mobile_HTML5G2.PrevNextProcess(html_env, G1.rowNum, G1.row, first, last, G1.numberOfColumns);
		}

		if(Mobile_HTML5Env.getFormItemFlg()){		
			Mobile_HTML5Env.incrementFormPartsNumber();
		}

		if(!Sass.isBootstrapFlg()){
			if(!G1.tableFlg){
				html_env.code.append("\n</DIV>\n");			//20130309	//20160527 bootstrap
				tmp.append("\n</DIV>\n");
			}
			else if (G1.tableFlg){	//20160527 bootstrap
				if(!(G1.row>1 && G1.tableFlg)){
					html_env.code.append("</TR></TABLE>\n");	//20130314  table
					tmp.append("</TR></TABLE>\n");	//20130314  table
				}
				G1.tableFlg = false;
				G1.table0Flg = false;		//20130325 table0
				if(G1.numberOfColumns < 0){
					html_env.code.append("</div>\n");	//added by goto 20130318  横スクロール		//20130917  [ ],10@{table}
					tmp.append("</div>\n");
				}
			}
		}else if(Sass.isBootstrapFlg()){
			if(G1.decos.containsKey("slide")){
				html_env.code.append("</div>\n");
				html_env.code.append("<!-- Controls -->\n"
						+ "<a class=\"left carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"prev\">\n"
						+ "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n"
						+ "<span class=\"sr-only\">Previous</span>\n"
						+ "</a>\n"
						+ "<a class=\"right carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"next\">\n"
						+ "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n"
						+ "<span class=\"sr-only\">Next</span>\n"
						+ "</a>\n"
						+ "</div>\n");

				tmp.append("</div>\n");
				tmp.append("<!-- Controls -->\n"
						+ "<a class=\"left carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"prev\">\n"
						+ "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n"
						+ "<span class=\"sr-only\">Previous</span>\n"
						+ "</a>\n"
						+ "<a class=\"right carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"next\">\n"
						+ "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n"
						+ "<span class=\"sr-only\">Next</span>\n"
						+ "</a>\n"
						+ "</div>\n");
			}
			//        	html_env.code.append("\n</DIV>\n");//.row
			//        	html_env.code.append("\n</DIV>\n");//.TFE
			//      		if(Sass.outofloopFlg.peekFirst()){
			//      			Sass.closeBracket();
			//      			Sass.closeBracket();
			//      		}
			//      		if(!decos.containsKey("C1") && !decos.containsKey("G1")){
			//        		html_env.code.append("\n</DIV>\n");
			//        		if(Sass.outofloopFlg.peekFirst()){
			//        			Sass.closeBracket();
			//        		}
			//        	}

			html_env.code.append("</DIV>\n");//.row
			tmp.append("</DIV>\n");//.row
			if(Sass.outofloopFlg.peekFirst()){
				//        		Sass.closeBracket();//row
			}

			if(G1.firstFlg){
				html_env.code.append("</DIV>\n");//.classid
				html_env.code.append("</DIV>\n");//.row

				tmp.append("</DIV>\n");//.classid
				tmp.append("</DIV>\n");//.row
				if(Sass.outofloopFlg.peekFirst()){
					//        			Sass.closeBracket();//classid
					//        			Sass.closeBracket();//row
				}
				G1.firstFlg = false;
			}
		}
		if(G1.divFlg)	G1.divFlg = false;		//20130326  div

		G1.G1Flg=false;
		Log.out("</TR></TABLE>");

		//20130312 collapsible
		if(G1.decos.containsKey("collapse")){
			html_env.code.append("</DIV>");
			tmp.append("</DIV>");
		}

		//20130330 tab
		int a=1;
		while(a<=Mobile_HTML5Env.maxTab){
			if(G1.decos.containsKey("tab"+a) || (a==1 && G1.decos.containsKey("tab"))){
				html_env.code.append("</div></div></div>\n");
				tmp.append("</div></div></div>\n");
				Mobile_HTML5Env.tabCount++;
				break;
			}
			a++;
		}

		//20130503  Panel
		//				Mobile_HTML5C1.panelProcess2(G1.decos, html_env, G1.panelFlg);

		//added by goto 20130413  "row Prev/Next"
		if(G1.rowFlg){
			Mobile_HTML5G2.rowFileNum++;
			G1.rowFlg = false;
			Mobile_HTML5G2.tableStartTag = "";
		}

		if(IS_level > 1){
			if(Infinitescroll.decompose.get(Infinitescroll.IS_level-2)){
				IS_point--;
			}
		}
		IS_level--;
		Mobile_HTML5.postProcess(G1.getSymbol(), classid2, G1.decos, html_env);	//Post-process (後処理)
		//		codes.append(tmp.toString());
		StringBuffer before = new StringBuffer();
		before.append("\n<div class=\""+ classid_for_ifs +"\" onscroll=\"scrolled(this)\" itemnum=\"'.$i1.'\">\n");
		before.append(tmp.toString());
		before.append("</div>\n");
		tmp = before;
		codes = tmp;
	}

	public static String Funciton(Mobile_HTML5Function Func, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2, String FuncName, ExtList data_info){
		String ret = "";
		if (FuncName.equalsIgnoreCase("imagefile") || FuncName.equalsIgnoreCase("image") || FuncName.equalsIgnoreCase("img")) {
			if(!Sass.isBootstrapFlg())
				Func_imagefile(Func, html_env, html_env2);
			else
				Func_imagefile_bs(Func, html_env, html_env2);
			//ret = Func_imagefile(); //TODO
		} else if (FuncName.equalsIgnoreCase("invoke")) {
			Func_invoke(Func, html_env);
		} else if (FuncName.equalsIgnoreCase("foreach")) {
			try {
				Func_foreach(data_info, Func, html_env);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (FuncName.equalsIgnoreCase("sinvoke") || FuncName.equalsIgnoreCase("link")) {
			Func_sinvoke(data_info, 1, Func, html_env);
		} else if (FuncName.equalsIgnoreCase("glink")) {	//added by goto 20161109 for plink/glink
			Func_sinvoke(data_info, 2, Func, html_env);
		} else if (FuncName.equalsIgnoreCase("plink")) {	//added by goto 20161109 for plink/glink
			Func_sinvoke(data_info, 3, Func, html_env);
		} else if (FuncName.equalsIgnoreCase("null")) {
			Func_null(Func, html_env);
		}
		//added by goto 20121217
		else if(FuncName.equalsIgnoreCase("button")){
			ret = Func_button("", Func);
		}
		//added by goto 20130508  "Login&Logout"
		else if(FuncName.equalsIgnoreCase("logout")){
			ret = Func_button("logout", Func);
		}
		//added by goto 20130308  "urlリンク"
		else if(FuncName.equalsIgnoreCase("url") || FuncName.equalsIgnoreCase("anchor") || FuncName.equalsIgnoreCase("a")){
			ret = Func_url(false, Func);
		}
		//added by goto 20130417  "mail"
		else if(FuncName.equalsIgnoreCase("mail")){
			ret = Func_url(true, Func);
		}
		//added by goto 20130312  "line"
		else if(FuncName.equalsIgnoreCase("line")){
			ret = Func_line(Func);
		}
		//added by goto 20130325  "dline"
		else if(FuncName.equalsIgnoreCase("dline")){
			ret = Func_dline(Func);
		}
		//added by goto 20130502  "vline"
		else if(FuncName.equalsIgnoreCase("vline")){
			ret = Func_vline(Func);
		}
		//added by goto 20130313  "header"
		else if(FuncName.equalsIgnoreCase("header")){
			Func_header(Func);
		}
		//added by ryosuke 20161010  "navbar"
		else if(FuncName.equalsIgnoreCase("navbar")){
			ret = Func_navbar(Func);
		}
		//added by ryosuke 20161010  "dropdown"
		else if(FuncName.equalsIgnoreCase("dropdown")){
			ret = Func_dropdown(Func);
		}
		//added by goto 20130313  "footer"
		else if(FuncName.equalsIgnoreCase("footer")){
			Func_footer(Func);
		}

		//added by goto 20130313  "popup"
		else if(FuncName.equalsIgnoreCase("pop")
				|| FuncName.equalsIgnoreCase("pop_anchor")
				|| FuncName.equalsIgnoreCase("pop_a")
				|| FuncName.equalsIgnoreCase("popup")
				|| FuncName.equalsIgnoreCase("popup_anchor")
				|| FuncName.equalsIgnoreCase("popup_a")){
			if(!Sass.isBootstrapFlg())
				ret = Func_pop(1, Func);
			else
				ret = Func_pop_bs(1, Func);
		}
		//added by goto 20140120  "popup_button"
		else if(FuncName.equalsIgnoreCase("pop_button")
				|| FuncName.equalsIgnoreCase("pop_bt")
				|| FuncName.equalsIgnoreCase("popup_button")
				|| FuncName.equalsIgnoreCase("popup_bt")){
			if(!Sass.isBootstrapFlg())
				ret = Func_pop(2, Func);
			else
				ret = Func_pop_bs(2, Func);
		}
		//added by goto 20140120  "popup_image"
		else if(FuncName.equalsIgnoreCase("pop_image")
				|| FuncName.equalsIgnoreCase("pop_img")
				|| FuncName.equalsIgnoreCase("popup_image")
				|| FuncName.equalsIgnoreCase("popup_img")){
			if(!Sass.isBootstrapFlg())
				ret = Func_pop(3, Func);
			else
				ret = Func_pop_bs(3, Func);
		}
		else if(FuncName.equalsIgnoreCase("pop_over")){
			ret = Func_pop_over(4, Func);
		}
		else if(FuncName.equalsIgnoreCase("pop_over")){
			ret = Func_pop_over(4, Func);
		}
		//added by goto 20130515  "search"
		else if(FuncName.equalsIgnoreCase("search")){
			ret = Func_search(Func);
		}
		//added by goto 20130529  "select"
		else if(FuncName.equalsIgnoreCase("select")){
			ret = Func_select(Func);
		}
		//added by goto 20130529  "insert"
		else if(FuncName.equalsIgnoreCase("insert")){
			ret = Func_insert(false,false, Func, html_env);
		}
		//added by goto 20130605  "update"
		else if(FuncName.equalsIgnoreCase("update")){
			ret = Func_insert(true,false, Func, html_env);
		}
		//added by goto 20130721  "insert_update", "form"
		else if(FuncName.equalsIgnoreCase("insert_update") || FuncName.equalsIgnoreCase("form")){
			ret = Func_insert(false,true, Func, html_env);
		}
		//20131127 form
		else if(FuncName.equalsIgnoreCase("result") || FuncName.equalsIgnoreCase("form_result")){
			ret = Func_result();
		}
		//added by goto 20130531  "check"
		else if(FuncName.equalsIgnoreCase("check")){
			ret = Func_check(Func);
		}
		//added by goto 20130519  "moveto"
		else if(FuncName.equalsIgnoreCase("moveto")){
			ret = Func_moveto(Func);
		}
		//added by goto 20130603  "$session"
		else if (FuncName.equalsIgnoreCase("$session")||FuncName.equalsIgnoreCase("$s")||FuncName.equalsIgnoreCase("$_session")||FuncName.equalsIgnoreCase("$_s")) {
			ret = Func_$session(Func);
		}
		//added by goto 20130607  "time,date"
		else if (FuncName.equalsIgnoreCase("time") || FuncName.equalsIgnoreCase("date")) {
			ret = Func_time(Func);
		}
		//added by goto 20130717  "map"
		else if (FuncName.equalsIgnoreCase("map")) {
			ret = Func_map(false, Func);
		}
		//added by goto 20130721  "search_map"
		else if (FuncName.equalsIgnoreCase("search_map")) {
			ret = Func_map(true, Func);
		}
		//added by goto 20130717  "gps,gps_map"
		else if (FuncName.equalsIgnoreCase("gps") || FuncName.equalsIgnoreCase("gps_map")) {
			ret = Func_gps(Func);
		}
		//added by goto 20130717  "gps_info"
		else if (FuncName.equalsIgnoreCase("gps_info")) {
			ret = Func_gps_info();
		}
		//added by goto 20130914  "audio"
		else if (FuncName.equalsIgnoreCase("music") || FuncName.equalsIgnoreCase("audio")) {
			ret = Func_audio(Func);
		}
		//added by goto 20130914  "movie"
		else if (FuncName.equalsIgnoreCase("movie") || FuncName.equalsIgnoreCase("video")) {
			ret = Func_movie(Func, html_env);
		}
		//added by goto 20130914  "object"
		else if (FuncName.equalsIgnoreCase("object")) {
			ret = Func_object("", Func, html_env);
		}
		//added by goto 20130914  "SEQ_NUM"
		else if (FuncName.equalsIgnoreCase("seq_num") || FuncName.equalsIgnoreCase("row_number")) {
			ret = Func_seq_num(Func);
		}
		//added by goto 20130915  "text"
		else if (FuncName.equalsIgnoreCase("text")) {
			ret = Func_text(Func);
		}

		//chie
		else if (FuncName.equalsIgnoreCase("submit")) {
			Func_submit(Func, html_env, html_env2);
		}
		//        else if (FuncName.equalsIgnoreCase("select")) {
		//            Func_select();
		//        }
		else if (FuncName.equalsIgnoreCase("checkbox")) {
			Func_checkbox(Func, html_env);
		}
		else if (FuncName.equalsIgnoreCase("radio")) {
			Func_radio(Func, html_env);
		}
		else if (FuncName.equalsIgnoreCase("inputtext")) {
			Func_inputtext(Func, html_env);
		}
		else if (FuncName.equalsIgnoreCase("textarea")) {
			Func_textarea(Func, html_env);
		}
		else if (FuncName.equalsIgnoreCase("hidden")) {
			Func_hidden(Func, html_env);
		}
		else if (FuncName.equalsIgnoreCase("session")) {
			//Func_session(); not use
		}
		//tk start//////////////////////////////////
		else if (FuncName.equalsIgnoreCase("embed")) {
			Log.out("[enter embed]");
			Func_embed(data_info, Func, html_env, html_env2);
			//ret = Func_embed(data_info);	//TODO
		}
		//tk end////////////////////////////////////
		else if (FuncName.equalsIgnoreCase("addition") || FuncName.equalsIgnoreCase("add")) {
			ret = Func_addition(Func);
		}
		else if (FuncName.equalsIgnoreCase("subtract") || FuncName.equalsIgnoreCase("sub")) {
			ret = Func_subtract(Func);
		}
		else if (FuncName.equalsIgnoreCase("multiply") || FuncName.equalsIgnoreCase("mul")) {
			ret = Func_multiply(Func);
		}
		else if (FuncName.equalsIgnoreCase("divide") || FuncName.equalsIgnoreCase("div")) {
			ret = Func_divide(Func);
		}
		else{
			//Log.err("[Warning] no such function name: "+FuncName+"()");
		}

		return ret;
	}

	public static void setWidth(String classid, DecorateList decos, Mobile_HTML5Env html_env) {
		if(!decos.containsKey("width") && (!Mobile_HTML5Attribute.attributeDivWidth.equals("") || !Mobile_HTML5Attribute.attributeDivWidth2.equals(""))){
			//attributeDivWidth, attributeDivWidth2
			if(!Mobile_HTML5Attribute.attributeDivWidth2.equals("") && !Mobile_HTML5Attribute.attributeHasWidth){
				html_env.css.append(Mobile_HTML5Attribute.attributeDivWidth2);
			}else if(!Mobile_HTML5Attribute.attributeDivWidth.equals("")){
				decos.put("width", Mobile_HTML5Attribute.attributeDivWidth);
			}
			Mobile_HTML5Attribute.attributeDivWidth2 = "";
		}
		Mobile_HTML5Attribute.attributeDivWidth = "";
		if(decos.containsKey("width"))	Mobile_HTML5Attribute.attributeHasWidth = true;
		else							Mobile_HTML5Attribute.attributeHasWidth = false;
		html_env.append_css_def_td(classid, decos);
	}
	private static void createForm(ExtList data_info, Mobile_HTML5Attribute ATT, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2, StringBuffer tmp){

		String form = new String();
		String name = new String();		
		String inputFormString = new String();

		for(int i = 1; i < formSql.length ; i++ ){
			if(ATT.decos.containsKey(formSql[i]) || Mobile_HTML5Env.getIDU().equals(formSql[i])){
				switch(i){
				case 1 : //delete
					if(ATT.decos.containsKey(formSql[i])){
						name = ATT.decos.getStr("delete");
					}else{
						name = ATT.decos.getStr("attributeName");
					}
					inputFormString += "<input type=\"checkbox\" name=\"" + name + "\" value=\"" + ATT.getStr(data_info) + "\" />";
					whichForm = i;
					break;
				case 2 : //update
					if(ATT.decos.containsKey(formSql[i])){
						name = ATT.decos.getStr("update");
					}else{
						name = ATT.decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 3 : //insert
					if(ATT.decos.containsKey(formSql[i])){
						name = ATT.decos.getStr("insert");
					}else{
						name = ATT.decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 4 : //login
					name = ATT.decos.getStr("login");
					if(ATT.decos.containsKey("att")){
						inputFormString += "<input type=\"hidden\" name=\"att\" value=\"" + ATT.decos.getStr("att") +"\" />";
					}
					whichForm = i;
					break;
				case 5 : //logout
					inputFormString += "<input type=\"hidden\" name=\"sqlfile\" value=\""+ATT.decos.getStr("linkfile").replace("\"", "")+"\" />";
					inputFormString += "<input type=\"submit\" name=\"logout\" value=\""+ATT.getStr(data_info)+"\" />";
					whichForm = i;
					break;
				}	
			}
		}


		if( 1 < whichForm && whichForm < formSql.length-1 ){ //update,insert,login
			String s;
			if(whichForm < 3) {//update
				s = ATT.getStr(data_info);
			}else{//insert,login,logout
				s = "";
			}
			if(ATT.decos.containsKey("pwd")){
				inputFormString += "<input type=\"password\" name=\"" + name + "\" value=\"" + s + "\" />";
				if(ATT.decos.containsKey("md5")){
					inputFormString += "<input type=\"hidden\" name=\"" + name + ":pwd\" value=\"md5\" />";
				}

			}else{
				if(s.isEmpty()){
					inputFormString += "<input type=\"text\" name=\"" + name + "\" />";					
				}else{
					inputFormString += "<input type=\"text\" name=\"" + name + "\" value=\"" + s + "\" />";
				}
			}	

			//add constraint
			String constraint = new String();
			if(ATT.decos.containsKey("notnull")){//not null
				constraint = "notnull";
			}
			if(ATT.decos.containsKey("number")){//num or eng
				if(ATT.decos.containsKey("english")){
					if(constraint.isEmpty())
						constraint = "numeng";
					else
						constraint += ",numeng";
				}else{//number
					if(constraint.isEmpty())
						constraint = "number";
					else
						constraint += ",number";
				}	
			}else if(ATT.decos.containsKey("english")){//eng
				if(constraint.isEmpty())
					constraint = "english";
				else
					constraint += ",english";
			}

			if(ATT.decos.containsKey("unique")){//unique
				if(constraint.isEmpty())
					constraint = "unique";
				else
					constraint += ",unique";
			}

			if(constraint != null && !constraint.isEmpty())
				inputFormString += "<input type=\"hidden\" name=\""+ name +":const\" value=\""+ constraint +"\" />";


			Log.out("pppppp"+ATT.decos.containsKey("pkey"));
			if(ATT.decos.containsKey("pkey") && whichForm == 2){//update
				if(!html_env.code.toString().contains("<input type=\"hidden\" name=\"pkey\" value=\"" + name + "\" />"))
					inputFormString += "<input type=\"hidden\" name=\"pkey\" value=\"" + name + "\" />";
			}
		}

		html_env.code.append(inputFormString);
		tmp.append(inputFormString);
		html_env2.code.append(inputFormString);
		Log.out(inputFormString);

		inputFormString = new String();

		if(Mobile_HTML5Env.getFormItemFlg()){
			for(int i = 1; i < formHtml.length ; i++ ){
				String real_value = Mobile_HTML5Env.getFormValueString();
				if(Mobile_HTML5Env.getFormItemName().equals(formHtml[i])){
					switch(i){
					case 1: //submit
						inputFormString = inputFormItems(data_info,formHtml[i],"", ATT);
						whichForm =  i + formSql.length;
						break;
					case 2: //select
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;
					case 3: //checkbox
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;
					case 4: //radio
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;

					case 5: //input text
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;

					case 6: //textarea
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;

					case 7: //hidden
						inputFormString = inputFormItems(data_info,formHtml[i],real_value, ATT);
						whichForm =  i + formSql.length;
						break;
					}
				}
			}
		}

		html_env.code.append(inputFormString);
		tmp.append(inputFormString);
		html_env2.code.append(inputFormString);
		Log.out(inputFormString);

	}

	private static String inputFormItems(ExtList data_info,String itemType,String real_value, Mobile_HTML5Attribute ATT){
		String ret = "";
		String formname = Mobile_HTML5Env.getFormPartsName();;
		if(Mobile_HTML5Env.getSearch()){
			ret += cond();
			formname = "value"+Mobile_HTML5Env.form_parts_number;
		}
		String s = ATT.getStr(data_info);
		//tuple_count++;
		if(real_value.isEmpty()){
			real_value = s;
		}
		//sizeoption
		String size = new String();
		if(ATT.decos.containsKey("size")){
			size += " size=\""+ ATT.decos.getStr("size")+"\"";
		}
		if(ATT.decos.containsKey("height")){
			size += " height=\""+ ATT.decos.getStr("height")+"\"";
		}
		if(ATT.decos.containsKey("cols")){
			size += " cols=\""+ ATT.decos.getStr("cols")+"\"";
		}
		if(ATT.decos.containsKey("rows")){
			size += " rows=\""+ ATT.decos.getStr("rows")+"\"";
		}

		if(ATT.decos.containsKey("class")){
			size += " class=\""+ ATT.decos.getStr("class")+"\"";
		}

		if(itemType.equals(formHtml[1])){//submit

		}else if(itemType.equals(formHtml[2])){//select
			if(Mobile_HTML5Env.getSelectRepeat() == false){
				ret += "<select name=\""+ formname +"\">";
				Mobile_HTML5Env.setSelectRepeat(true);
			}
			if(Mobile_HTML5Env.getSelected().length() != 0 && Mobile_HTML5Env.getSelected().equals(real_value)){
				ret += "<option value=\"" + real_value + "\"" + size +" selected=\"selected\" >" + s + "</option>";
			}else{
				ret += "<option value=\"" + real_value + "\"" + size +" >" + s + "</option>";
			}
		}else if(itemType.equals(formHtml[3])){//checkbox
			String checked = "";
			if(Mobile_HTML5Env.getChecked().length() != 0 && Mobile_HTML5Env.getChecked().equals(real_value)){
				checked = " checked=\"checked\" ";
			}
			if(Mobile_HTML5Env.nameId.length() != 0){
				ret += "<input type=\"checkbox\" name=\""+ formname + "[" + Mobile_HTML5Env.nameId+ "]" + "\" value=\"" + real_value + "\"" + size + checked +" />";
				ret += s;
			}else{
				ret += "<input type=\"checkbox\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size + checked +" />";
				ret += s;
			}
		}else if(itemType.equals(formHtml[4])){//radio
			String checked = "";
			if(Mobile_HTML5Env.getChecked().length() != 0 && Mobile_HTML5Env.getChecked().equals(real_value)){
				checked = " checked=\"checked\" ";
			}
			ret += "<input type=\"radio\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size + checked + " />";
			ret += s;
		}else if(itemType.equals(formHtml[5])){//text
			if(ATT.decos.containsKey("pwd")){
				ret += "<input type=\"password\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size +" />";
				if(ATT.decos.containsKey("md5")){
					ret += "<input type=\"hidden\" name=\"" + formname + ":pwd\" value=\"md5\" />";
				}
			}else{
				ret += "<input type=\"text\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size +" />";
			}
		}else if(itemType.equals(formHtml[6])){//textarea
			ret += "<textarea name=\""+ formname + "\"" + size +">";
			if(s != null){
				ret += s;
			}
			ret += "</textarea>";
		}else if(itemType.equals(formHtml[7])){//text
			ret += "<input type=\"hidden\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size +" />";
		}

		String constraint = new String();
		if(ATT.decos.containsKey("notnull")){
			constraint = "notnull";
		}
		if(ATT.decos.containsKey("number")){
			if(ATT.decos.containsKey("english")){//number or english
				if(constraint.isEmpty())
					constraint = "numeng";
				else
					constraint += ",numeng";
			}else{//number
				if(constraint.isEmpty())
					constraint = "number";
				else
					constraint += ",number";
			}
		}else if(ATT.decos.containsKey("english")){//english
			if(constraint.isEmpty())
				constraint = "english";
			else
				constraint += ",english";
		}
		if(ATT.decos.containsKey("unique")){//unique
			if(constraint.isEmpty())
				constraint = "unique";
			else
				constraint += ",unique";
		}

		if(constraint != null && !constraint.isEmpty())
			ret += "<input type=\"hidden\" name=\""+ formname +":const\" value=\""+ constraint +"\" />";


		return ret;
	}

	private static String cond(){
		String ret = "";
		if(Mobile_HTML5Env.form_parts_number != Mobile_HTML5Env.searchid){
			Mobile_HTML5Env.searchid = Mobile_HTML5Env.form_parts_number;
			if(!Mobile_HTML5Env.cond_name.isEmpty() && !Mobile_HTML5Env.cond.isEmpty()){
				ret += "<input type=\"hidden\" name=\"cond_name"+ Mobile_HTML5Env.form_parts_number +"\" value=\""+ Mobile_HTML5Env.cond_name +"\" />";
				ret += "<input type=\"hidden\" name=\"cond"+ Mobile_HTML5Env.form_parts_number +"\" value=\""+ Mobile_HTML5Env.cond +"\" />";
				ret += "<input type=\"hidden\" name=\"value_type"+ Mobile_HTML5Env.form_parts_number +"\" value=\"String\" />";
			}
		}
		return ret;
	}

	private static String Func_pop_over(int popupType, Mobile_HTML5Function Func) {
		String statement = "";
		String title = getValue(1, Func);
		String header = getValue(2, Func);
		String detailORurl = getValue(3, Func);
		if(header.equals("")){
			if(title.isEmpty()){
				Log.info("<Warning> popup関数の引数が不足しています。 ex. popup(title, header, Detail/URL)");
				return "";
			}else{
				header = title;
			}
		}

		if(popupType==4){
			statement += "<button type=\"button\" class=\"btn btn-default\" "
					+ "data-container=\"body\" data-toggle=\"popover\" data-placement=\""+ detailORurl  +"\""
					+ "data-content=\""+ header +"\">"
					+ title
					+ "</button>";
		}

		return statement;
	}

	static int ArithmeticOperationCount = 1;
	private static String Func_addition(Mobile_HTML5Function Func) {
		return createArithmeticOperation("+", Func);
	}
	private static String Func_subtract(Mobile_HTML5Function Func) {
		return createArithmeticOperation("-", Func);
	}
	private static String Func_multiply(Mobile_HTML5Function Func) {
		return createArithmeticOperation("*", Func);
	}
	private static String Func_divide(Mobile_HTML5Function Func) {
		return createArithmeticOperation("/", Func);
	}
	private static String createArithmeticOperation(String operator, Mobile_HTML5Function Func) {
		String s = "";
		String label = "SSQL_Func_ArithmeticOperation";
		if(Function.nestingLevel < 1){
			s += ""
					+ "<div id=\""+label+ArithmeticOperationCount+"\"><!-- Computation result --></div>\n"
					+ "<SCRIPT language=\"JavaScript\">\n"
					+ "val = ";
		}
		s += "(";
		for(int i=1; !getValue(i, Func).isEmpty(); i++){
			s += getValue(i, Func)+operator;
		}
		s = s.substring(0, s.length()-1);	//cut last 'operator'
		s += ")";
		if(Function.nestingLevel < 1){
			s += ""
					+ ";\n"
					+ "document.getElementById(\""+label+ArithmeticOperationCount+"\").innerHTML = val;\n"
					+ "</SCRIPT>\n";
			ArithmeticOperationCount++;
		}
		return s;
	}



	private static void Func_imagefile(Mobile_HTML5Function Func, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2) {

		StringBuffer tmp = new StringBuffer();
		StringBuffer tmp2 = new StringBuffer();
		codes = new StringBuffer();
		/*
		 * ImageFile function : <td> <img src="${imgpath}/"+att /> </td>
		 */
		String path = "";
		try {
			path = Func.getArg(1).toString();
		} catch (Exception e) {
			try {
				path = Func.getAtt("path", ".");
			} catch (Exception e2) { }
		}
		if (path == null) {
			path = ".";
		} else {
			if (path.startsWith("'") || path.startsWith("\"")) {
				path = path.substring(1, path.length() - 1);
			}
		}
		// String path = this.getAtt("path", ".");
		if (!path.startsWith("/")) {
			String basedir = GlobalEnv.getBaseDir();
			if (basedir != null && basedir != "") {
				path = GlobalEnv.getBaseDir() + "/" + path;
			}
		}
		if(GlobalEnv.isServlet()){
			path = GlobalEnv.getFileDirectory() + path;
		}
		//System.out.println(GlobalEnv.isServlet());

		//added by goto 20130110 start
		String type = Func.getAtt("type", ".");
		//System.out.println("type="+type);
		//if(type.matches("\\."))	type=null;

		//added by goto 20130110 end

		//tk to make hyper link to image//////////////////////////////////////////////////////////////////////////////////
		if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
			//added by goto 20121222 start
			//以下は、-fのファイル名指定が絶対パスになっている場合の処理(?)
			//[%連結子] hrefの指定を絶対パスから「相対パス形式」へ変更
			//20120622の修正だと、「-f フルパスファイル名」を用いている場合、相対パス形式にならない
			String fileDir = new File(html_env.linkurl).getAbsoluteFile().getParent();

			if(fileDir.length() < html_env.linkurl.length()
					&& fileDir.equals(html_env.linkurl.substring(0,fileDir.length()))){
				String relative_path = html_env.linkurl.substring(fileDir.length()+1);
				html_env.code.append("<A href=\"" + relative_path + "\" ");
				tmp.append("<A href=\"" + relative_path + "\" ");
			}else{
				html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
				tmp.append("<A href=\"" + html_env.linkurl + "\" ");
			}

			//html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
			//added by goto 20121222 end

			//added by goto 20121217 start
			//画面遷移アニメーション (data-transition)
			//for 'hyperlink of image file'
			//transition = fade, slide, pop, slideup, slidedown, flip
			if (Func.decos.containsKey("transition")){
				html_env.code.append("data-transition=\"" + Func.decos.getStr("transition") + "\" ");
				tmp.append("data-transition=\"" + Func.decos.getStr("transition") + "\" ");
				//System.out.println(decos.getStr("transition"));
			}
			//added by goto 20121217 end


			if(Func.decos.containsKey("target")){
				html_env.code.append(" target=\"" + Func.decos.getStr("target")+"\" ");
				tmp.append(" target=\"" + Func.decos.getStr("target")+"\" ");
			}
			if(Func.decos.containsKey("class")){
				html_env.code.append(" class=\"" + Func.decos.getStr("class") + "\" ");
				tmp.append(" class=\"" + Func.decos.getStr("class") + "\" ");
			}
			html_env.code.append(">\n");
			tmp.append(">\n");

			Log.out("<A href=\"" + html_env.linkurl + "\">");
		}
		//tk/////////////////////////////////////////////////////////////////////////////////

		if(Func.decos.containsKey("lightbox"))
		{
			Date d1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
			String today = sdf.format(d1);

			html_env.code.append("<a href=\"" + path+"/" + Func.getAtt("default")
			+"\" rel=\"lightbox[lb"+today+"]\">");
			tmp.append("<a href=\"" + path+"/" + Func.getAtt("default")
			+"\" rel=\"lightbox[lb"+today+"]\">");

			if(Func.decos.getStr("lightbox").compareTo("root") == 0 || Func.decos.getStr("lightbox").compareTo("thumb") == 0)
			{
				html_env.code.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
				tmp.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");

				if(Func.decos.containsKey("class")){
					html_env.code.append(Func.decos.getStr("class"));
					tmp.append(Func.decos.getStr("class"));
				}

				html_env.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" onLoad=\"initLightbox()\"/>");
				tmp.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" onLoad=\"initLightbox()\"/>");
			}
			html_env.code.append("</a>");
			tmp.append("</a>");
		}
		else{
			//added by goto 20121217 start
			//html_env.code.append("<img class=\"" + HTMLEnv.getClassID(Func) +" ");
			if(type.matches(".") || type.matches("normal")){					//type==null
				//20130206
				//defaultは下記の1行のみ
				//html_env.code.append("<img class=\"" + HTMLEnv.getClassID(Func) +" ");

				//        		//20130206
				if (Func.decos.containsKey("effect") && Func.decos.getStr("effect").matches("bound")){
					//String display_type = decos.getStr("display-type");//.replace("\"", "") +"\" " );
					//Func.getAtt("display-type", "null");
					//Log.info("bound!");
					//System.out.println("type="+type);
					html_env.code.append("<div id=\"bounce\" class=\"ui-widget-content ui-corner-all\">" +
							"<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
					tmp.append("<div id=\"bounce\" class=\"ui-widget-content ui-corner-all\">" +
							"<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
				}else{
					html_env.code.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");

					tmp.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
				}

				//added by goto 20130312  "Default width: 100%"
				if(!Func.decos.containsKey("width")){
					if(!Sass.isBootstrapFlg()){
						html_env.code.append("\" width=\"100% " );
						tmp.append("\" width=\"100% " );
					}
				}
			}
			html_env2.code.append("<VALUE type=\"img\" class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
			tmp2.append("<VALUE type=\"img\" class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
			if(Func.decos.containsKey("class")){
				html_env.code.append(Func.decos.getStr("class"));
				tmp.append(Func.decos.getStr("class"));
			}

			//Log.info("out:path:"+Func.getAtt("default"));

			//added by goto 20121217 start
			//html_env.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\"/>");
			if(type.matches(".") || type.matches("normal")){					//type==null


				//TODO 20131106
				String url = "";
				String url_ifs = "";
				//url = Func.getAtt("default"); 	//TODO
				try{
					FuncArg fa1 = (FuncArg) Func.getArg(0);
					url = fa1.getStr();
					url_ifs = fa1.getStr_ifs();
				}catch(Exception e){ e.printStackTrace(); }


				//added 20130703  For external URLs.
				//html_env.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\"/>");
				if(url.startsWith("http://") || url.startsWith("https://")){
					html_env.code.append(" \" src=\"" + url + "\"/>");
					tmp.append(" \" src=\"" + url_ifs + "\"/>");
				}else{
					html_env.code.append(" \" src=\"" + path + "/" + url + "\"/>");
					tmp.append(" \" src=\"" + path + "/" + url_ifs + "\"/>");
					if(Sass.isBootstrapFlg()){
						html_env.code.append("\n</DIV>\n");
						tmp.append("\n</DIV>\n");
					}
				}

				//20130206
				if (Func.decos.containsKey("effect") && Func.decos.getStr("effect").matches("bound")){
					html_env.code.append("</div>");
					tmp.append("</div>");
				}


				//        		//20130205
				//        		html_env.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\"/>" +
				//        				"<span>"+Func.getAtt("default")+"</span></a></div></div>");
			}else if(type.matches("slideshow")){	//type==slideshow
				//System.out.println("slideshowFlg="+slideshowFlg+"  lio="+html_env.code.lastIndexOf("</TD"));
				//tableタグの削除
				if(Mobile_HTML5Function.slideshowFlg!=true){
					//html_env.code.substring(0,html_env.code.lastIndexOf("<TABLE"));
					html_env.code.append("<div data-role=\"page\" data-add-back-btn=\"true\" id=\"p-gallery\">\n");
					html_env.code.append("<ul id=\"Gallery\" class=\"gallery\">\n");

					tmp.append("<div data-role=\"page\" data-add-back-btn=\"true\" id=\"p-gallery\">\n");
					tmp.append("<ul id=\"Gallery\" class=\"gallery\">\n");
					Mobile_HTML5Function.slideshowFlg=true;
				}else{
					html_env.code.delete(html_env.code.lastIndexOf("</ul>"),html_env.code.length());
					tmp.delete(tmp.lastIndexOf("</ul>"),tmp.length());
				}
				Mobile_HTML5Function.slideshowNum++;

				//column : 列数(<li>のwidthで指定)
				String column = Func.getAtt("column", "null");
				if(column.matches("null")){	//column==null
					column = "3";			//default
				}
				//        		Log.info(column);
				int li_width = 100/Integer.parseInt(column);
				html_env.code.append(
						"<li style=\"width:"+li_width+"%;\"><a href=\""+path+"/"+Func.getAtt("default")+"\" rel=\"external\">" +
								"<img src=\"" + path + "/" + Func.getAtt("default") + "\" class=\"" + Mobile_HTML5Env.getClassID(Func) +"\" alt=\""+Mobile_HTML5Function.slideshowNum+"\" /></a></li>\n");

				tmp.append(
						"<li style=\"width:"+li_width+"%;\"><a href=\""+path+"/"+Func.getAtt("default")+"\" rel=\"external\">" +
								"<img src=\"" + path + "/" + Func.getAtt("default") + "\" class=\"" + Mobile_HTML5Env.getClassID(Func) +"\" alt=\""+Mobile_HTML5Function.slideshowNum+"\" /></a></li>\n");
			}
			html_env2.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" ");
			tmp2.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" ");
			if(Func.decos.containsKey("width")){
				html_env2.code.append("width=\"" + Func.decos.getStr("width").replace("\"", "")+"\" " );
				tmp2.append("width=\"" + Func.decos.getStr("width").replace("\"", "")+"\" " );
			}
			if(Func.decos.containsKey("height")){
				html_env2.code.append("height=\"" + Func.decos.getStr("height").replace("\"", "") +"\" " );
				tmp2.append("height=\"" + Func.decos.getStr("height").replace("\"", "") +"\" " );
			}
			html_env2.code.append(" ></VALUE>");
			tmp2.append(" ></VALUE>");
		}
		//tk  to make hyper link to image///////////////////////////////////////////////////////////////////////////////////
		if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
			html_env.code.append("</a>");
			tmp.append("</a>");
		}
		codes.append(tmp.toString());
		//tk///////////////////////////////////////////////////////////////////////////////////
		return;
	}

	private static void Func_imagefile_bs(Mobile_HTML5Function Func, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2) {
		/*
		 * ImageFile function : <td> <img src="${imgpath}/"+att /> </td>
		 */

		String path = Func.getAtt("path", ".");
		if (!path.startsWith("/")) {
			String basedir = GlobalEnv.getBaseDir();
			if (basedir != null && basedir != "") {
				path = GlobalEnv.getBaseDir() + "/" + path;
			}
		}
		if(GlobalEnv.isServlet()){
			path = GlobalEnv.getFileDirectory() + path;
		}

		String type = Func.getAtt("type", ".");
		if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
			String fileDir = new File(html_env.linkurl).getAbsoluteFile().getParent();

			if(fileDir.length() < html_env.linkurl.length()
					&& fileDir.equals(html_env.linkurl.substring(0,fileDir.length()))){
				String relative_path = html_env.linkurl.substring(fileDir.length()+1);
				html_env.code.append("<A href=\"" + relative_path + "\" ");
			}else
				html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");

			if (Func.decos.containsKey("transition")){
				html_env.code.append("data-transition=\"" + Func.decos.getStr("transition") + "\" ");
			}


			if(Func.decos.containsKey("target"))
				html_env.code.append(" target=\"" + Func.decos.getStr("target")+"\" ");
			if(Func.decos.containsKey("class"))
				html_env.code.append(" class=\"" + Func.decos.getStr("class") + "\" ");
			html_env.code.append(">\n");

			Log.out("<A href=\"" + html_env.linkurl + "\">");
		}

		if(Func.decos.containsKey("lightbox"))
		{
			Date d1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
			String today = sdf.format(d1);

			html_env.code.append("<a href=\"" + path+"/" + Func.getAtt("default")
			+"\" rel=\"lightbox[lb"+today+"]\">");

			if(Func.decos.getStr("lightbox").compareTo("root") == 0 || Func.decos.getStr("lightbox").compareTo("thumb") == 0)
			{
				html_env.code.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");

				if(Func.decos.containsKey("class"))
					html_env.code.append(Func.decos.getStr("class"));

				html_env.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" onLoad=\"initLightbox()\"/>");

			}
			html_env.code.append("</a>");
		}
		else{
			if(type.matches(".") || type.matches("normal")){					//type==null
				if (Func.decos.containsKey("effect") && Func.decos.getStr("effect").matches("bound")){
					html_env.code.append("<div id=\"bounce\" class=\"ui-widget-content ui-corner-all\">" +
							"<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
				}else{
					if(!Sass.isBootstrapFlg()){
						html_env.code.append("<img class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
					}else if(Sass.isBootstrapFlg()){
						//						if(Sass.outofloopFlg.peekFirst()){
						//							Sass.makeClass(Mobile_HTML5Env.getClassID(Func));
						//							Sass.defineGridBasic(Mobile_HTML5Env.getClassID(Func), decos);
						//							Sass.closeBracket();
						//						}
						if(Func.decos.containsKey("slide")){	
							if(Func.decos.get("slide").equals("true")){
								html_env.code.append("<div class=\"item active\">");
							}else{
								html_env.code.append("<div class=\"item\">");
							}
							html_env.code.append("<img ");
						}else{
							//							html_env.code.append("<div class=\"" + Mobile_HTML5Env.getClassID(Func) + "\">");
							html_env.code.append("<img class=\"img-responsive ");
						}
					}
				}

				if(!Func.decos.containsKey("width")){
					if(!Sass.isBootstrapFlg()){
						html_env.code.append("\" width=\"100% " );
					}
				}
			}

			html_env2.code.append("<VALUE type=\"img\" class=\"" + Mobile_HTML5Env.getClassID(Func) +" ");
			if(Func.decos.containsKey("class"))
				html_env.code.append(Func.decos.getStr("class"));

			if(type.matches(".") || type.matches("normal")){					//type==null

				//TODO 20131106
				String url = "";
				//url = Func.getAtt("default"); 	//TODO
				try{
					FuncArg fa1 = (FuncArg) Func.getArg(0);
					url = fa1.getStr();
				}catch(Exception e){ return; }

				if(url.startsWith("http://") || url.startsWith("https://")){
					html_env.code.append(" \" src=\"" + url + "\"/>");
				}else{
					html_env.code.append(" \" src=\"" + path + "/" + url + "\"/>");
					//					html_env.code.append("</div>");
					if(Sass.isBootstrapFlg()){
						//						html_env.code.append("\n</DIV>\n");
					}
				}

				//20130206
				if (Func.decos.containsKey("effect") && Func.decos.getStr("effect").matches("bound"))
					html_env.code.append("</div>");


			}else if(type.matches("slideshow")){	//type==slideshow
				if(Mobile_HTML5Function.slideshowFlg!=true){
					html_env.code.append("<div data-role=\"page\" data-add-back-btn=\"true\" id=\"p-gallery\">\n");
					html_env.code.append("<ul id=\"Gallery\" class=\"gallery\">\n");
					Mobile_HTML5Function.slideshowFlg=true;
				}else
					html_env.code.delete(html_env.code.lastIndexOf("</ul>"),html_env.code.length());

				Mobile_HTML5Function.slideshowNum++;

				//column : 列数(<li>のwidthで指定)
				String column = Func.getAtt("column", "null");
				if(column.matches("null")){	//column==null
					column = "3";			//default
				}
				//	        		Log.info(column);
				int li_width = 100/Integer.parseInt(column);
				html_env.code.append(
						"<li style=\"width:"+li_width+"%;\"><a href=\""+path+"/"+Func.getAtt("default")+"\" rel=\"external\">" +
								"<img src=\"" + path + "/" + Func.getAtt("default") + "\" class=\"" + Mobile_HTML5Env.getClassID(Func) +"\" alt=\""+Mobile_HTML5Function.slideshowNum+"\" /></a></li>\n");
			}
			html_env2.code.append(" \" src=\"" + path + "/" + Func.getAtt("default") + "\" ");
			if(Func.decos.containsKey("width")){
				html_env2.code.append("width=\"" + Func.decos.getStr("width").replace("\"", "")+"\" " );
			}
			if(Func.decos.containsKey("height")){
				html_env2.code.append("height=\"" + Func.decos.getStr("height").replace("\"", "") +"\" " );
			}
			html_env2.code.append(" ></VALUE>");
		}
		//tk  to make hyper link to image///////////////////////////////////////////////////////////////////////////////////
		if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
			html_env.code.append("</a>");
		}
		//tk///////////////////////////////////////////////////////////////////////////////////
		return;
	}

	//added by goto 20121217 start 		    // for practice 2012/02/09 を改良
	private static String Func_button(String key, Mobile_HTML5Function Func) {
		String statement = "";
		FuncArg fa = (FuncArg) Func.getArg(0);
		String button_media = fa.getStr();

		if (button_media.equals("back")){				//ex. button("back")
			// 戻るボタンの生成
			statement = "<input type=\"button\" onClick='history.back();' value=\"戻る\">";
		}else if(button_media.equals("bookmark")){		//ex. button("bookmark")
			// ブックマーク処理
			statement = "<input type=\"button\" value=\"お気に入りに登録\" data-icon=\"star\" onClick=\"addBookmark(document.title, location.href);\">";
		}else if(button_media.equals("facebook")){		//ex. button("facebook")
			// facebookのいいね！ボタンの処理
			statement = "<table data-inline=\"true\"><tr><td><iframe class=\"like-btn\" scrolling=\"no\" frameborder=\"0\" style=\"border:none; overflow:hidden; width:200px; height:21px;\" allowTransparency=\"true\"></iframe></td></tr></table>";
		}else if(button_media.equals("twitter")){		//ex. button("twitter")
			// twitterボタンの処理
			statement = "<table data-inline=\"true\"><tr><td><a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-text=\"Twitter\" data-lang=\"ja\" data-size=\"small\" target=\"_blank\">Tweet</a></td></tr></table>";
		}else if(button_media.equals("google+1")){		//ex. button("google+1")
			// google+1(プラスワン)ボタンの処理
			statement = "<table><tr><td>" +
					"<script type=\"text/javascript\" src=\"https://apis.google.com/js/plusone.js\">" +
					"  {lang: 'ja'}" +
					"</script>" +
					"<g:plusone size=\"medium\"></g:plusone>" +
					"</td></tr></table>\n";
		}else if(button_media.equals("line")){			//ex. button("line")
			// LINEで送るボタンの処理
			statement = "<span>" +
					"<script type=\"text/javascript\" src=\"http://media.line.naver.jp/js/line-button.js?v=20130417\" ></script>" +
					"<script type=\"text/javascript\">" +
					"new jp.naver.line.media.LineButton({\"pc\":true,\"lang\":\"ja\",\"type\":\"b\"});" +
					//"new jp.naver.line.media.LineButton({\"pc\":true,\"lang\":\"ja\",\"type\":\"b\",\"withUrl\":true});" +	//ページタイトルとURLを使う場合、withUrlは無くてもOK(?)
					"</script>" +
					"</span>\n";
		}else if(button_media.equals("sns")){			//ex. button("sns")
			statement += "<DIV class=\"ui-grid-b\">\n<div class=\"ui-block-a\">\n";
			statement += "<table><tr><td><iframe class=\"like-btn\" scrolling=\"no\" frameborder=\"0\" style=\"border:none; overflow:hidden; width:200px; height:21px;\" allowTransparency=\"true\"></iframe></td></tr></table>\n";

			statement += "</div>\n<div class=\"ui-block-b\">\n";
			statement += "<table><tr><td><a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-text=\"Twitter\" data-lang=\"ja\" data-size=\"small\" target=\"_blank\">Tweet</a></td></tr></table>\n";

			statement += "</div>\n<div class=\"ui-block-c\">\n";
			statement += "<table><tr><td>" +
					"<script type=\"text/javascript\" src=\"https://apis.google.com/js/plusone.js\">" +
					"  {lang: \"ja\"}" +
					"</script>" +
					"<g:plusone size=\"medium\"></g:plusone>" +
					"</td></tr></table>\n";
			statement += "</div>\n</DIV>\n";
		}else if(button_media.equals("logout") || key.contains("logout")){		//ex. button("logout") || logout()
			//added by goto 20130508  "Login&Logout"
			//Logoutボタンを設置
			if(Start_Parse.sessionFlag){
				statement += "<a href=\"\" onclick=\"document.LOGOUTpanel1.submit();return falese;\" data-role=\"button\">Logout</a>\n";
				Func.logoutButtonFlg = true;
			}
		}

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}


	private static String Func_url(boolean mailFncFlg, Mobile_HTML5Function Func) {
		String statement = "";
		FuncArg fa1 = (FuncArg) Func.getArg(0), fa2, fa3;
		String url, name, type;

		try{					//引数2つ or 3つの場合
			fa2 = (FuncArg) Func.getArg(1);
			url = ((mailFncFlg)?("mailto:"):("")) + fa2.getStr();
			name = fa1.getStr();

			try{						//引数3つの場合
				fa3 = (FuncArg) Func.getArg(2);
				type = fa3.getStr();

				//type=1 -> 文字
				if(type.equals("1") || type.equals("text") || type.equals("")){
					statement = getTextAnchor(url, name, Func);
					//statement = "<a href=\""+url+"\""+transition()+prefetch()+target(url)+">"+name+"</a>";

					//type=2 -> urlモバイルボタン
				}else if(type.equals("3") || type.equals("button") || type.equals("bt")){
					statement = "<a href=\""+url+"\" data-role=\"button\""+className(Func)+transition(Func)+prefetch(Func)+target(url)+">"+name+"</a>";

					//urlボタン(デスクトップ・モバイル共通)
				}else if(type.equals("dbutton") || type.equals("dbt")){
					statement = "<input type=\"button\" value=\""+name+"\" onClick=\"location.href='"+url+"'\""+className(Func);

					//urlボタン width,height指定時の処理
					if(Func.decos.containsKey("width") || Func.decos.containsKey("height")){
						statement += " style=\"";
						if(Func.decos.containsKey("width"))	statement += "WIDTH:"+Func.decos.getStr("width").replace("\"", "")+"; ";
						if(Func.decos.containsKey("height"))	statement += "HEIGHT:"+Func.decos.getStr("height").replace("\"", "")+"; ";	//100; ";
						statement += "\"";
					}
					statement += ">";

					//type=3 -> url画像
				}else if(type.equals("2") || type.equals("image") || type.equals("img")){
					statement = "<a href=\""+url+"\""+className(Func)+transition(Func)+prefetch(Func)+target(url)+"><img src=\""+name+"\"";

					//url画像 width,height指定時の処理
					if(Func.decos.containsKey("width"))	statement += " width="+Func.decos.getStr("width").replace("\"", "");
					else{
						//added by goto 20130312  "Default width: 100%"
						statement += " width=\"100%\"";
					}
					if(Func.decos.containsKey("height"))	statement += " height="+Func.decos.getStr("height").replace("\"", "");	//100; ";
					statement += "></a>";
				}

			}catch(Exception e){		//引数2つの場合
				statement = getTextAnchor(url, name, Func);
				//statement = "<a href=\""+url+"\""+transition()+prefetch()+target(url)+">"+name+"</a>";
			}

		}catch(Exception e){	//引数1つの場合
			url = fa1.getStr();
			statement = "<a href=\""+((mailFncFlg)?("mailto:"):("")) + url+"\""+transition(Func)+prefetch(Func)+target(url)+">"+url+"</a>";
		}

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}

	private static String getTextAnchor(String url, String name, Mobile_HTML5Function Func) {
		//[ ]で囲われた部分をハイパーリンクにする
		//ex) a("[This] is anchor.","URL")
		String A="",notA1="",notA2="";
		int a1 = 0, a2 = name.length()-1;
		try{
			for(int i=0;i<name.length();i++){
				if(i>0 && name.charAt(i)=='[' && name.charAt(i-1)!='\\')		a1=i;
				else if(i>0 && name.charAt(i)==']' && name.charAt(i-1)!='\\')	a2=i;
			}
			if(a1==0 && a2==name.length()-1)	A=name.substring(a1,a2+1);
			else								A=name.substring(a1+1,a2);
			A=A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
			notA1=name.substring(0,a1).replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
			notA2=name.substring(a2+1).replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
		}catch(Exception e){}

		return notA1+"<a href=\""+url+"\""+className(Func)+transition(Func)+prefetch(Func)+target(url)+">"+A+"</a>"+notA2;
	}
	protected static String className(Mobile_HTML5Function Func) {	//added 20130703
		if(Func.decos.containsKey("class"))
			return " class=\""+Func.decos.getStr("class")+"\" ";
		return "";
	}
	private static String transition(Mobile_HTML5Function Func) {
		//画面遷移アニメーション(data-transition)指定時の処理
		//※外部ページへの遷移には対応していない
		if (Func.decos.containsKey("transition"))
			return " data-transition=\"" + Func.decos.getStr("transition") + "\"";
		if (Func.decos.containsKey("trans"))
			return " data-transition=\"" + Func.decos.getStr("trans") + "\"";
		return "";
	}
	private static String prefetch(Mobile_HTML5Function Func) {
		//遷移先ページプリフェッチ(data-prefetch)指定時の処理
		//※外部ページへの遷移に使用してはいけない決まりがある
		if (Func.decos.containsKey("prefetch") || Func.decos.containsKey("pref"))
			return " data-prefetch";
		return "";
	}
	private static String target(String url) {
		//新規ウィンドウで表示する場合(target="_blank")の処理　=> _blankはW3Cで禁止されているため、JS + rel=externalを使用
		//「外部ページに飛ぶ場合( http(s)://で始まる場合)」のみ新規ウィンドウ表示
		try{
			if (url.matches("\\s*(http|https)://.*"))
				return "  rel=\"external\"";
			//return " target=\"_blank\"";
		}catch(Exception e){}
		return " target=\"_self\"";

	}
	//added by goto 20130308 end

	//added by goto 20130312 start  "line"
	/*  line(color, size)  */
	private static String Func_line(Mobile_HTML5Function Func) {
		String statement = "\n<hr";
		try{
			//color
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))
				statement += " color=\""+fa1.getStr()+"\"";
			//size
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			statement += " size=\""+fa2.getStr()+"\"";
		}catch(Exception e){
			statement += " size=\"1\"";
		}
		statement += ">\n";

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}
	//added by goto 20130312 end

	//added by goto 20130325 start  "dline"	dotted line(点線)
	/*  dline(color, size)  */
	private static String Func_dline(Mobile_HTML5Function Func) {
		//ex. <hr style="border-top: 1px dotted black;">
		String statement = "\n<hr style=\"border-top: ";
		String color = "";
		try{
			//color
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))	color = fa1.getStr();
			else							color = "black";
			//size
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			statement += fa2.getStr();
		}catch(Exception e){
			statement += "1";
		}
		statement += "px dotted "+color+"\">\n";

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}
	//added by goto 20130325 end

	//added by goto 20130502 start  "vline"
	/*  vline(color, size)  */
	//&thinsp;<span style="border-left:1px solid red; line-height:1.0em;"></span>&thinsp;
	private static String Func_vline(Mobile_HTML5Function Func) {
		String statement = "<span style=\"border:";
		String color = "";
		try{
			//color
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))	color = fa1.getStr();
			else							color = "black";
			//size
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			statement += fa2.getStr();
		}catch(Exception e){
			statement += "1";
		}
		statement += "px solid "+color+"; line-height:1.0em;\"></span>";

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}
	//added by goto 20130502 end

	//added by goto 20130313 start  "header"
	/*	header(Title [, Home [, Menu] ] )	*/
	/*	ex) header("Title1", "http://localhost/index.html", "Google:'https://www.google.co.jp/' , 'http://www.yahoo.co.jp/'")	*/
	private static void Func_header(Mobile_HTML5Function Func) {
		//TODO: 第2引数で画像のURL,リンク先等

		String title = getValue(1, Func).trim();
		if(title.isEmpty())	return;
		String home = getValue(2, Func).trim();
		String menus = getValue(3, Func).trim();

		ArrayList<String> menuTitle = new ArrayList<String>();
		ArrayList<String> url = new ArrayList<String>();
		if(!menus.isEmpty()){
			//create menu list
			menus += ",";
			int i = 0;
			String s = "";
			while(menus.contains(",")){
				s = menus.substring(0,menus.indexOf(",")).trim();
				if(s.contains(":") && !s.startsWith("'")){
					menuTitle.add(i, s.substring(0, s.indexOf(":")));
					url.add(i, s.substring(s.indexOf(":")+1).replaceAll("'", ""));
				}else{
					menuTitle.add(i, "");
					url.add(i, s.replaceAll("'", ""));
				}
				menus = menus.substring(menus.indexOf(",")+1);
				i++;
			}
		}

		Func.headerString += "<div data-role=\"header\" data-position=\"fixed\" style=\"padding: 11px 0px;\" id=\"header1\">\n" +
				"<a href=\"\" data-rel=\"back\" data-role=\"button\" data-icon=\"back\" data-mini=\"true\">Back</a>\n" +
				"\n" +
				"<div class=\"ui-btn-right\">\n" +
				"	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr>\n";
		if(!home.isEmpty()){
			Func.headerString += 
					"	<td>\n" +
							"		<a href=\""+home+"\" data-role=\"button\" data-icon=\"home\" data-iconpos=\"notext\" data-mini=\"true\" data-ajax=\"false\"></a>\n" +
							"	</td>\n";
		}
		Func.headerString += 
				"	<td>\n" +
						"		<form style=\"display:inline;\">\n" +
						"			<input type=\"button\" data-icon=\"forward\" data-iconpos=\"notext\" data-mini=\"true\" onClick=\"history.forward()\" >\n" +
						"		</form>\n" +
						"	</td>\n";
		if(Start_Parse.sessionFlag || url.size()>0){
			Func.headerString += 
					"	<td>\n" +
							"		<a href=\"#popupMenu\" data-rel=\"popup\" data-role=\"button\" data-icon=\"grid\" data-iconpos=\"notext\" data-mini=\"true\"></a>\n" +
							"		<div data-role=\"popup\" id=\"popupMenu\" data-transition=\"slidedown\" style=\"width:95%;\" data-overlay-theme=\"a\">\n" +
							"			<a href=\"#\" data-rel=\"back\" data-role=\"button\" data-theme=\"a\" data-icon=\"delete\" data-iconpos=\"notext\" class=\"ui-btn-right\">Close</a>\n" +
							"			<ul data-role=\"listview\" data-inset=\"true\" style=\"min-width:210px;\" data-theme=\"d\">\n" +
							"				<li data-role=\"divider\" data-theme=\"a\">Menu</li>\n";
			if(url.size()>0){
				for(int i=0; i<url.size(); i++){
					if(menuTitle.get(i).isEmpty()){
						Func.headerString += "				<li><a href=\""+url.get(i)+"\" data-ajax=\"false\">"+url.get(i)+"</a></li>\n";
					}else{
						Func.headerString += "				<li><a href=\""+url.get(i)+"\" data-ajax=\"false\">"+menuTitle.get(i)+"</a></li>\n";
					}
				}
			}
			//added by goto 20130508  "Login&Logout"
			//Logoutボタン
			if(Start_Parse.sessionFlag){
				Func.headerString += "				<li><a href=\"\" data-rel=\"back\" onclick=\"document.LOGOUTpanel1.submit();return falese;\">Logout</a></li>\n";
			}
			Func.headerString += 
					"			</ul>\n" +
							"		</div>\n" +
							"	</td>\n";
		}
		Func.headerString += 
				"	</tr></table>\n" +
						"</div>\n\n" +
						"<div>"+title+"</div>\n" +
						"</div>\n";
		return;
	}
	//added by goto 20130313 end

	//added by ryosuke 20161010
	private static String Func_navbar(Mobile_HTML5Function Func) {
		String statement = "";
		try{
			String title = getValue(1, Func).trim();

			statement += "<nav class=\"navbar navbar-default\" role=\"navigation\">\n" +
					"	<div class=\"container-fluid\">\n" +
					"		<div class=\"navbar-header\">\n" +
					"			<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#nav-menu-1\">\n" +
					"				<span class=\"sr-only\">Toggle navigation</span>\n" +
					"				<span class=\"icon-bar\"></span>\n" +
					"				<span class=\"icon-bar\"></span>\n" +
					"				<span class=\"icon-bar\"></span>\n" +
					"			</button>\n";
			if(!title.isEmpty()){
				if(title.contains(":")){
					statement +=
							"		<a class=\"navbar-brand\" href=\""+title.substring(title.indexOf(":")+1).replaceAll("'", "")+"\">"+title.substring(0, title.indexOf(":"))+"</a>\n";
				}else{
					statement +=
							"		<div class=\"navbar-brand\">"+title+"</div>\n";
				}
			}
			statement +=
					"	</div>"+
							"		<div class=\"collapse navbar-collapse\" id=\"nav-menu-1\">\n" +
							"			<ul class=\"nav navbar-nav\">\n";

			for(int i=1; i<Func.sizeArg(); i++){
				FuncArg menu_arg = (FuncArg) Func.getArg(i);
				String menu = menu_arg.getStr().trim();
				String menuTitle = "";
				String url = "";
				if(menu.startsWith("<li class=\"dropdown\">")){
					statement += menu;
				}
				else if(menu.contains(":") && !menu.startsWith("'")){
					menuTitle = menu.substring(0, menu.indexOf(":"));
					url = menu.substring(menu.indexOf(":")+1).replaceAll("'", "");
					statement += "				<li><a href=\""+url+"\">"+menuTitle+"</a></li>\n";
				}else{
					url = menu.substring(menu.indexOf(":")+1).replaceAll("'", "");
					statement += "				<li><a href=\""+url+"\">Menu"+i+"</a></li>\n";
				}
			}

			statement +=
					"			</ul>\n" +
							"		</div>\n" +
							"	</div>\n" +
							"</nav>\n";
		}catch(Exception e){
			Log.info("<Warning> navbar関数の引数が不足しています。 ex. navbar(title:'URL', menu:'URL',...)");
		}
		return statement;
	}
	//added by ryosuke 20161010

	private static String Func_dropdown(Mobile_HTML5Function Func) {
		String statement = "";
		ArrayList<String> menuTitle = new ArrayList<String>();
		ArrayList<String> url = new ArrayList<String>();
		try{
			//dropdownmenu
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			String d_menu = fa1.getStr();
			statement += "<li class=\"dropdown\">\n" +
					"	<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+d_menu+"</a>\n" +
					"	<ul class=\"dropdown-menu\">\n";
			//menu
			for(int i=1; i<Func.sizeArg(); i++){
				FuncArg menu_arg = (FuncArg) Func.getArg(i);
				String menu = menu_arg.getStr().trim();
				if(menu.contains(":") && !menu.startsWith("'")){
					menuTitle.add(i-1, menu.substring(0, menu.indexOf(":")));
					url.add(i-1, menu.substring(menu.indexOf(":")+1).replaceAll("'", ""));
				}else{
					menuTitle.add(i-1, "");
					url.add(i-1, menu.replaceAll("'", ""));
				}
			}
			if(url.size()>0){
				for(int i=0; i<url.size(); i++){
					if(menuTitle.get(i).isEmpty()){
						statement += "		<li><a href=\""+url.get(i)+"\">"+url.get(i)+"</a></li>\n";
					}else{
						statement += "		<li><a href=\""+url.get(i)+"\">"+menuTitle.get(i)+"</a></li>\n";
					}
				}
			}
			statement +=
					"	</ul>\n"+
							"</li>";
		}catch(Exception e){
			statement += "1";
		}
		return statement;

	}
	//added by goto 20130313 start  "footer"
	/*	footer("title")	*/
	private static void Func_footer(Mobile_HTML5Function Func) {
		String statement = "";
		try{
			//title
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))
				statement = fa1.getStr();
			else	return;
		}catch(Exception e){ return; }

		Func.footerString += "<div data-role=\"footer\" data-position=\"fixed\" style=\"padding: 11px 0px;\" id=\"footer1\">\n" +
				statement+"\n" +
				"</div>\n";
		return;
	}

	private static String Func_pop(int popupType, Mobile_HTML5Function Func) {	//popupType: 1=anchor, 2=button, 3=image
		String btHTML = "";
		if(popupType==2){	//popup button
			btHTML = " data-role=\"button\" data-icon=\"arrow-r\"";
		}

		String statement = "";
		String title = getValue(1, Func);
		String detailORurl = getValue(2, Func);
		if(detailORurl.equals("")){
			if(title.isEmpty()){
				Log.info("<Warning> popup関数の引数が不足しています。 ex. popup(title, Detail/URL)");
				return "";
			}else{
				detailORurl = title;
			}
		}

		if(!isImage(detailORurl)){
			//string
			statement += "	<a href=\"#popup"+getCount(Func.popCount)+"\" data-rel=\"popup\""+btHTML+" data-inline=\"true\">"+getPopupTitle(title, "Open", popupType, Func)+"</a>\n" +
					//TODO: data-transition  transition()使用可能
					"	<div data-role=\"popup\" id=\"popup"+getCount(Func.popCount)+"\" data-transition=\"slideup\" style=\"width:95%;\" data-overlay-theme=\"a\">\n" +
					"		<a href=\"#\" data-rel=\"back\" data-role=\"button\" data-theme=\"a\" data-icon=\"delete\" data-iconpos=\"notext\" class=\"ui-btn-right\">Close</a>\n" +
					"		<p>"+detailORurl+"</p>\n" +
					"	</div>\n";

		}else{
			//imageFile
			statement += "	<a href=\"#popup"+getCount(Func.popCount)+"\" data-rel=\"popup\""+btHTML+" data-inline=\"true\" class=\"ui-li-inside\">"+getPopupTitle(title, "Photo", popupType, Func)+"</a>\n" +
					//TODO: data-transition  transition()使用可能
					"	<div data-role=\"popup\" id=\"popup"+getCount(Func.popCount)+"\" data-transition=\"pop\" style=\"width:95%;\" data-overlay-theme=\"a\">\n" +
					"		<a href=\"#\" data-rel=\"back\" data-role=\"button\" data-theme=\"a\" data-icon=\"delete\" data-iconpos=\"notext\" class=\"ui-btn-right\">Close</a>\n" +
					"		<img src=\""+detailORurl+"\"";
			//        			//type=2 width,height指定時の処理
			//            		if(decos.containsKey("width"))
			//            			statement += " width="+decos.getStr("width").replace("\"", "");
			//            		else{
			//            	        //added by goto 20130312  "Default width: 100%"
			//            			statement += " width=\"100%\"";
			//            		}
			//        			if(decos.containsKey("height"))
			//        				statement += " height="+decos.getStr("height").replace("\"", "");
			statement += ">\n";
			////画像下部にtitleを付加
			//if(!title.equals(""))	statement += "		<p style=\"margin:0px;\">"+title+"</p>\n";
			statement += "	</div>\n";
		}
		Func.popCount++;
		return statement;
	}

	private static String Func_pop_bs(int popupType, Mobile_HTML5Function Func) {	//popupType: 1=anchor, 2=button, 3=image, 4=over    	
		String statement = "";
		String title = getValue(1, Func);
		String header = getValue(2, Func);
		String detailORurl = getValue(3, Func);
		if(header.equals("")){
			if(title.isEmpty()){
				Log.info("<Warning> popup関数の引数が不足しています。 ex. popup(title, header, Detail/URL)");
				return "";
			}else{
				header = title;
			}
		}

		if(popupType==1){
			statement += "<a data-toggle=\"modal\" data-target=\"#myModal"+getCount(Func.popCount)+"\">" + title + "</a>\n";
		}else if(popupType==2){
			statement += "<button type=\"button\" class=\"btn btn-info btn-lg\"  data-toggle=\"modal\" data-target=\"#myModal"+getCount(Func.popCount)+"\">" + title + "</button>\n";
		}else if(popupType==3){
			statement += "<a data-toggle=\"modal\" data-target=\"#myModal" + getCount(Func.popCount)+"\">" + 
					"<img src=\"" + title + "\" class=\"img-responsive\">" + 
					"</a>\n";
		}

		statement += "<div class=\"modal fade\" id=\"myModal"+ getCount(Func.popCount) +"\" role=\"dialog\">\n"
				+ "<div class=\"modal-dialog\">\n"
				+ "<div class=\"modal-content\">\n"
				+ "<div class=\"modal-header\">\n"
				+ "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n"
				+ "<h4 class=\"modal-title\">" + header + "</h4>\n"
				+ "</div>";
		if(!isImage(detailORurl)){
			//string
			statement += "<div class=\"modal-body\">\n"
					+ "<p>" + detailORurl + "<p>\n"
					+ "</div>\n";
		}else{
			//imageFile
			statement += "<div class=\"modal-body\">\n"
					+ "<img src=\"" + detailORurl + "\" class=\"img-responsive\">\n"
					+ "</div>\n";
		}

		statement += "</div>\n" +
				"</div>\n" + 
				"</div>\n";

		Func.popCount++;
		return statement;
	}

	private static String getPopupTitle(String title, String defaultTitle, int popupType, Mobile_HTML5Function Func) {
		title = (!title.equals(""))? title : defaultTitle ;
		if(popupType==3){	//popup image
			//title = "<img src=\""+title+"\" alt=\""+title+"\">";
			title = "<img src=\""+title+"\"";
			title += getWidthHeight(Func);
			title += ">";
		}
		return title;
	}
	private static String getWidthHeight(Mobile_HTML5Function Func) {
		String s = "";
		if(Func.decos.containsKey("width"))	s += " width="+Func.decos.getStr("width").replace("\"", "");
		else			    			s += " width=\"100%\"";
		if(Func.decos.containsKey("height"))	s += " height="+Func.decos.getStr("height").replace("\"", "");
		return s;
	}
	//added by goto 20130313 end

	//added by goto 20130515 start  "search"
	/*	search("title", "c1:column1, c2:column2, ... ", "From以下")	*/
	private static String Func_search(Mobile_HTML5Function Func) {

		String title = "";
		String columns = "";
		String after_from = "";
		try{
			//title（第一引数）
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))	title = fa1.getStr();
			else							title = "Search";
			//columns（第二引数）
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			columns += fa2.getStr();
			//after_from（第三引数）
			FuncArg fa3 = (FuncArg) Func.getArg(2);
			after_from += fa3.getStr().trim();
		}catch(Exception e){
			Log.info("<Warning> serach関数の引数が不足しています。 ex. search(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			return "";
		}
		if(columns.trim().equals("") || after_from.equals("")){
			Log.info("<Warning> serach関数の引数が不足しています。 ex. search(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			return "";
		}
		if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();
		//Log.info(title);


		int col_num=1;
		String columns0 = columns;
		while(columns0.contains(",")){
			columns0 = columns0.substring(columns0.indexOf(",")+1);
			col_num++;		//カウント
		}
		String[] s_name_array = new String[col_num];
		String[] s_array = new String[col_num];
		columns0 = columns;
		for(int i=0; i<col_num-1; i++){
			s_array[i] = columns0.substring(0,columns0.indexOf(","));
			columns0 = columns0.substring(columns0.indexOf(",")+1);
			//Log.i( "s_array["+i+"] = "+s_array[i]+"	"+columns0);
		}
		s_array[col_num-1] = columns0;
		//Log.i( "s_array["+(col_num-1)+"] = "+s_array[col_num-1]);
		int j=0;
		for(int i=0; i<col_num; i++){
			if(s_array[i].contains(":")){
				//if(!s_array[i].substring(0,s_array[i].indexOf(":")).contains(")"))
				s_name_array[j++] = s_array[i].substring(0,s_array[i].indexOf(":"));
				s_array[i] = s_array[i].substring(s_array[i].indexOf(":")+1);
			}else{
				if(!s_array[i].contains(")"))	s_name_array[j++] = s_array[i];
			}
			//Log.i("s_name_array["+(j-1)+"] = "+s_name_array[j-1] + "	s_array["+i+"] = "+s_array[i]);
		}
		boolean groupbyFlg = false;	//Flg
		//boolean[] aFlg = new boolean[col_num];	//Flg
		//boolean[] popFlg = new boolean[col_num];	//Flg
		String a = "";
		String search_col = "";
		String search_col_array = "\"";
		String search_aFlg = "\"";		//Flg
		String search_mailFlg = "\"";		//Flg
		String search_popFlg = "\"";	//Flg
		String search_pop_imgFlg = "\"";	//Flg
		int a_pop_count = 0;
		for(int i=0; i<col_num; i++){
			a = s_array[i].replaceAll(" ","");
			if( a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
			if(a.startsWith("a(") || a.startsWith("anchor(")){
				search_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					search_col += s_array[i]+",";
					search_col_array += s_array[i]+"\",\"";
					search_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_pop_imgFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				search_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("mail(")){
				search_mailFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					search_col += s_array[i]+",";
					search_col_array += s_array[i]+"\",\"";
					search_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_pop_imgFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				search_mailFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("pop(") || a.startsWith("popup(")){
				search_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					search_col += s_array[i]+",";
					search_col_array += s_array[i]+"\",\"";
					search_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_pop_imgFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				search_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("popup_image(")){
				search_pop_imgFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					search_col += s_array[i]+",";
					search_col_array += s_array[i]+"\",\"";
					search_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					search_pop_imgFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				search_pop_imgFlg += "false\""+((i<col_num-1)?(",\""):(""));

			search_col += s_array[i] +((i<col_num-1)?(","):(""));
			search_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
		}
		col_num -= a_pop_count;
		search_col = search_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("popup_image\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
		search_col_array = search_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("popup_image\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");

		String DBMS = GlobalEnv.getdbms();										//DBMS
		String DB = GlobalEnv.getdbname();										//DB
		String HOST = "", USER = "", PASSWD = "";
		if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			HOST = GlobalEnv.gethost();
			USER = GlobalEnv.getusername();
			PASSWD = GlobalEnv.getpassword();
		}


		String query = "";
		//Log.i(after_from_string);
		if(after_from.startsWith("#")){					//From以下をクエリの下(#*)から取ってくる場合
			if(!Func.after_from_string.contains(after_from)){
				Log.info("<Warning> serach関数の第三引数に指定されている '"+after_from+"' が見つかりません。");
				return "";
			}
			query = Func.after_from_string
					.substring(Func.after_from_string.indexOf(after_from)+after_from.length())
					.trim().toLowerCase();
			if(query.contains("#"))	query = query.substring(0,query.indexOf("#")).trim().toLowerCase();
		}else
			query = after_from.toLowerCase();			//From以下を第三引数へ書く場合
		//Log.i("\n	Query: "+query);
		String from = "";
		String where = "";
		String groupby = "";
		String having = "";
		String orderby = "";
		String limit = "";
		if(query.contains(" limit ")){
			limit = query.substring(query.lastIndexOf(" limit ")+" limit ".length());
			query = query.substring(0,query.lastIndexOf(" limit "));
		}
		if(query.contains(" order by ")){
			orderby = query.substring(query.lastIndexOf(" order by ")+" order by ".length());
			query = query.substring(0,query.lastIndexOf(" order by "));
		}
		if(query.contains(" having ")){
			having = query.substring(query.lastIndexOf(" having ")+" having ".length());
			having = having.replaceAll("\\\"","\\\\\"");	// " -> \"
			query = query.substring(0,query.lastIndexOf(" having "));
		}
		if(query.contains(" group by ")){
			groupby = query.substring(query.lastIndexOf(" group by ")+" group by ".length());
			query = query.substring(0,query.lastIndexOf(" group by "));
		}
		if(query.contains(" where ")){
			where = query.substring(query.lastIndexOf(" where ")+" where ".length());
			where = where.replaceAll("\\'","\\\\'");		// ' -> \'
			if(where.contains("$session")){
				//if WHERE phrase contains $session(XX)
				where = where.replaceAll("\\$session","'\".\\$_SESSION").replaceAll("\\(","[\"").replaceAll("\\)","\"].\"'");
				//if it contains $_SESSION [""attribute""] or $_SESSION [" "attribute" "]
				where = where.replace("[\"\"","[\"").replace("\"\"]","\"]").replace("[\" \"","[\"").replace("\" \"]","\"]");
			}
			query = query.substring(0,query.lastIndexOf(" where "));
		}
		from = query.trim();


		String statement = "";
		statement += 
				"<!-- Search start -->\n" +
						"<!-- Search Panel start -->\n" +
						"<br>\n" +
						"<div id=\"SEARCH"+Func.searchCount+"panel\" style=\"\" data-role=\"none\">\n" +
						"<hr>\n<div style=\"font-size:30;\" id=\"SearchTitle"+Func.searchCount+"\">"+title+"</div>\n<hr>\n" +
						"<br>\n" +
						"<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n" +
						"    <input type=\"search\" name=\"search_words"+Func.searchCount+"\" placeholder=\"Search keywords\">\n" +
						"    <input type=\"submit\" value=\"Search&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" name=\"search"+Func.searchCount+"\" id=\"search"+Func.searchCount+"\" data-icon=\"search\" data-mini=\"false\" data-inline=\"false\">\n" +
						"</form>\n" +
						"\n" +
						"<div id=\"Search"+Func.searchCount+"_text0\" data-role=\"none\"><!-- 件数 --></div>\n" +
						"\n" +
						"<table style=\"table-layout:fixed;\" data-role=\"table\" id=\"table-column-toggle"+Func.searchCount+"\" data-mode=\"columntoggle\" class=\"ui-responsive table-stroke\">\n" +
						"  <thead>\n" +
						"    <tr id=\"Search"+Func.searchCount+"_text_th\">\n";
		for(int i=0; i<col_num; i++){
			statement += 
					"        <th data-priority=\"1\">"+s_name_array[i]+"</th>\n";
		}
		statement += 
				"    </tr>\n" +
						"  </thead>\n" +
						"  <tbody>\n" +
						"    <tr>\n";
		for(int i=0; i<col_num; i++){
			statement +=
					"        <td id=\"Search"+Func.searchCount+"_text"+(i+1)+"\" style=\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\"></td>\n";
		}
		statement += 
				"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<br>\n" +
						"</div>\n" +
						"<script type=\"text/javascript\"> $('#Search"+Func.searchCount+"_text_th').hide(); </script>\n" +
						"<!-- Search Panel end -->\n" +
						"\n";

		//php
		Mobile_HTML5Env.PHP +=
				"<?php\n" +
						"error_reporting(E_ALL & ~E_NOTICE);\n" +
						"if($_POST['search"+Func.searchCount+"'] || $_POST['search_words"+Func.searchCount+"']){\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Search"+Func.searchCount+"_refresh();</script>';    //表示をリフレッシュ\n" +
						"\n" +
						//"    //ユーザ定義\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
						"    $search_col = \""+search_col+"\";\n" +
						"    $col_num = "+col_num+";\n" +                          //カラム数(Java側で指定)\n" +
						"    $table = '"+from+"';\n" +
						"    $where0 = '"+where+"';\n" +
						"    $search_col_array = array("+search_col_array+");\n" +
						"    $search_col_num = count($search_col_array);\n" +
						"    $search_a_Flg = array("+search_aFlg+");\n" +
						"    $search_mail_Flg = array("+search_mailFlg+");\n" +
						"    $search_pop_Flg = array("+search_popFlg+");\n" +
						"    $search_pop_img_Flg = array("+search_pop_imgFlg+");\n" +
						"    $groupby = \""+groupby+"\"; 	           //null => WHERE句にlikeを書く／ not null => HAVING句に～    //[要] Java側で、列名に予約語から始まるものがあるかチェック\n" +
						"    $having0 = \""+having+"\";\n" +
						"    $orderby = \""+((orderby!="")?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
						"    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
						"\n" +
						"    $searchWord"+Func.searchCount+" = checkHTMLsc($_POST['search_words"+Func.searchCount+"']);\n" +
						"    $searchWord"+Func.searchCount+" = preg_replace('/　/', ' ', $searchWord"+Func.searchCount+");       //全角スペースを半角スペースへ\n" +
						"    $searchWord"+Func.searchCount+" = preg_replace('/\\s+/', ' ', $searchWord"+Func.searchCount+");      //連続する半角スペースを1つの半角スペースへ\n" +
						"    $searchWord"+Func.searchCount+" = trim($searchWord"+Func.searchCount+");                            //trim\n" +
						"    $searchWord"+Func.searchCount+" = preg_replace('/\\s/', '%', $searchWord"+Func.searchCount+");       //半角スペースを%へ変換\n" +
						"\n" +
						"    if($searchWord"+Func.searchCount+" != \"\"){\n";
		if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
			Mobile_HTML5Env.PHP +=
					"        $db"+Func.searchCount+" = new SQLite3($sqlite3_DB);\n" +
							"        $sql = \"SELECT \".$search_col.\" FROM \".$table;\n" +
							"        if($where0 != \"\")    $sql .= \" WHERE \".$where0.\" \";\n" +
							"    \n" +
							"    	//左辺の作成（※Java側でOK?)\n" +
							"        $sw = $searchWord"+Func.searchCount+";\n" +
							"        $sw_buf = \"\";\n" +
							"        $l_str = \"\";\n" +
							"        foreach($search_col_array as $val)    $l_str .= \"ifnull(\".$val.\",'')||\";\n" +
							"        $l_str = substr($l_str, 0, -2);      //substring   最後の||をカット\n" +
							"        $l_str .= \" LIKE '%\";\n" +
							"        //右辺の作成\n" +
							"        while(strpos($sw,'%')){		//%を含んでいる間\n" +
							"            $pos = strpos($sw,'%');          //indxOf  		%が最初に現れる位置\n" +
							"            $rest = substr($sw, 0, $pos);    //substring    最初の%以降をカット\n" +
							"            $sw = substr($sw, $pos+1);       //substring    最初の%までカット\n" +
							"            $sw_buf .= $l_str.$rest.\"%' AND \";\n" +
							"        }\n" +
							"        $sw_buf .= $l_str.$sw.\"%' \";         //最後のswを結合\n" +
							"        \n" +
							"        if($groupby == \"\"){    //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** WHERE句の作成 start ***/\n" +
							"            //WHERE  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //   AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $WHERE = \"\";\n" +
							"            if($where0 == \"\")   $WHERE = \" WHERE \";\n" +
							"            else                $WHERE = \" AND \";\n" +
							"            $WHERE .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" \".$WHERE.\" \";\n" +
							"            //$sql .= $WHERE.\" \".$groupby.\" \";\n" +
							"            /*** WHERE句の作成 end ***/\n" +
							"        }else{                        //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** HAVING句の作成 start ***/\n" +
							"            //HAVING  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //    AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $HAVING = \"\";\n" +
							"            if($having0 == \"\")  $HAVING = \" HAVING \";\n" +
							"            else	            $HAVING = \" HAVING \".$having0.\" AND \";\n" +
							"    		$HAVING .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" GROUP BY \".$groupby.\" \".$HAVING;\n" +
							"            /*** HAVING句の作成 end ***/\n" +
							"        }\n" +
							"        $sql .= \" \".$orderby.\" \".$limit;	//order by句とlimitを結合\n" +
							"        search"+Func.searchCount+"_p1('<font color=red>SQL error: '.$sql.\";</font>\");	//エラー時\n" +
							"\n" +
							"        $result = $db"+Func.searchCount+"->query($sql);\n" +
							"\n" +
							"        $i = 0;\n" +
							"        $pop_num = 0;\n" +
							"        $t = date('YmdHis');\n" +
							"        while($row = $result->fetchArray()){\n";

		} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			Mobile_HTML5Env.PHP +=
					"        $db"+Func.searchCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
							"        $sql = \"SELECT \".$search_col.\" FROM \".$table;\n" +
							"        if($where0 != \"\")    $sql .= \" WHERE \".$where0.\" \";\n" +
							"    \n" +
							"    	//左辺の作成（※Java側でOK?)\n" +
							"        $sw = $searchWord"+Func.searchCount+";\n" +
							"        $sw_buf = \"\";\n" +
							"        $l_str = \"\";\n" +
							"        foreach($search_col_array as $val)    $l_str .= $val.\"||\";\n" +
							"        $l_str = substr($l_str, 0, -2);      //substring   最後の||をカット\n" +
							"        $l_str .= \" LIKE '%\";\n" +
							"        //右辺の作成\n" +
							"        while(strpos($sw,'%')){		//%を含んでいる間\n" +
							"            $pos = strpos($sw,'%');          //indxOf  		%が最初に現れる位置\n" +
							"            $rest = substr($sw, 0, $pos);    //substring    最初の%以降をカット\n" +
							"            $sw = substr($sw, $pos+1);       //substring    最初の%までカット\n" +
							"            $sw_buf .= $l_str.$rest.\"%' AND \";\n" +
							"        }\n" +
							"        $sw_buf .= $l_str.$sw.\"%' \";         //最後のswを結合\n" +
							"        \n" +
							"        if($groupby == \"\"){    //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** WHERE句の作成 start ***/\n" +
							"            //WHERE  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //   AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $WHERE = \"\";\n" +
							"            if($where0 == \"\")   $WHERE = \" WHERE \";\n" +
							"            else                $WHERE = \" AND \";\n" +
							"            $WHERE .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" \".$WHERE.\" \";\n" +
							"            //$sql .= $WHERE.\" \".$groupby.\" \";\n" +
							"            /*** WHERE句の作成 end ***/\n" +
							"        }else{                        //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** HAVING句の作成 start ***/\n" +
							"            //HAVING  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //    AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $HAVING = \"\";\n" +
							"            if($having0 == \"\")  $HAVING = \" HAVING \";\n" +
							"            else	            $HAVING = \" HAVING \".$having0.\" AND \";\n" +
							"    		$HAVING .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" GROUP BY \".$groupby.\" \".$HAVING;\n" +
							"            /*** HAVING句の作成 end ***/\n" +
							"        }\n" +
							"        $sql .= \" \".$orderby.\" \".$limit;	//order by句とlimitを結合\n" +
							"        search"+Func.searchCount+"_p1('<font color=red>SQL error: '.$sql.\";</font>\");	//エラー時\n" +
							"\n" +
							"        $result = pg_query($db"+Func.searchCount+", $sql);\n" +
							"\n" +
							"        $i = 0;\n" +
							"        $pop_num = 0;\n" +
							"        $t = date('YmdHis');\n" +
							"        while($row = pg_fetch_row($result)){\n";
		}
		Mobile_HTML5Env.PHP +=
				"              $i++;\n" +
						"              $k=0;\n" +
						"              for($j=0; $j<$search_col_num; $j++){\n" +
						"					if($search_a_Flg[$j]=='true' || $search_mail_Flg[$j]=='true' || $search_pop_Flg[$j]=='true' || $search_pop_img_Flg[$j]=='true')	;\n" +
						"                    else if($j>0 && $search_a_Flg[$j-1]=='true')	search"+Func.searchCount+"_p2('<a href=\\\"'.$row[$j].'\\\" target=\\\"_blank\\\" rel=\\\"external\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     //tdに結果を埋め込む\n" +
						"                    else if($j>0 && $search_mail_Flg[$j-1]=='true')	search"+Func.searchCount+"_p2('<a href=\\\"mailto:'.$row[$j].'\\\" target=\\\"_self\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     				//tdに結果を埋め込む\n" +
						"                    //else if($j>0 && $search_pop_Flg[$j-1]=='true')	search"+Func.searchCount+"_p2('<a href=\\\"'.$row[$j].'\\\" target=\\\"_blank\\\" rel=\\\"external\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     //tdに結果を埋め込む\n" +
						//				"                    else if($j>0 && $search_pop_Flg[$j-1]=='true' && !is_null($row[$j])){\n" +
						//				"                    	$pop_str = '<a href=\\\"#search_popup1_'.(++$pop_num).'\\\" data-rel=\\\"popup\\\" data-icon=\\\"arrow-r\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>'\n" +
						//				"							.'<div data-role=\\\"popup\\\" id=\\\"search_popup1_'.($pop_num).'\\\" data-transition=\\\"slideup\\\" style=\\\"width:95%;\\\" data-overlay-theme=\\\"a\\\">'\n" +
						//				"								.'<a href=\\\"#\\\" data-rel=\\\"back\\\" data-role=\\\"button\\\" data-theme=\\\"a\\\" data-icon=\\\"delete\\\" data-iconpos=\\\"notext\\\" class=\\\"ui-btn-right\\\">Close</a>'\n" +
						//				"								.'<h2>'.$row[$j-1].'</h2>'\n" +
						//				"								.'<p>'.$row[$j].'</p>'\n" +
						//				"							.'</div>';\n" +
						"                    else if($j>0 && ($search_pop_Flg[$j-1]=='true' || $search_pop_img_Flg[$j-1]=='true') && !is_null($row[$j])){\n" +
						"                    	$pop_str = '<a href=\\\"#search_popup"+Func.searchCount+"_'.(++$pop_num).'_'.$t.'\\\" data-rel=\\\"popup\\\" data-icon=\\\"arrow-r\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\"';\n" +
						"                    	if($search_pop_img_Flg[$j-1] == 'true'){\n" +
						"                    		$pop_str .= ' class=\\\"ui-li-inside\\\"';\n" +
						"                    	}\n" +
						"                    	$pop_str .= '>'.$row[$j-1].'</a>'\n" +
						"                    		.'<div data-role=\\\"popup\\\" id=\\\"search_popup"+Func.searchCount+"_'.($pop_num).'_'.$t.'\\\" data-transition=\\\"';\n"+
						"                    	if($search_pop_Flg[$j-1]=='true'){\n" +
						"                    		$pop_str .= 'slideup';\n"+
						"                    	}else{\n" +
						"                    		$pop_str .= 'pop';\n"+
						"                    	}\n" +
						"                    	$pop_str .= '\\\" style=\\\"width:95%;\\\" data-overlay-theme=\\\"a\\\">'\n" +
						"                    		.'<a href=\\\"#\\\" data-rel=\\\"back\\\" data-role=\\\"button\\\" data-theme=\\\"a\\\" data-icon=\\\"delete\\\" data-iconpos=\\\"notext\\\" class=\\\"ui-btn-right\\\">Close</a>'\n" +
						"                    		.'<h2>'.$row[$j-1].'</h2>';\n" +
						"                    	if($search_pop_Flg[$j-1]=='true'){\n" +
						"                    		$pop_str .= '<p>'.$row[$j].'</p>';\n" +
						"                    	}else{\n" +
						"                    		$pop_str .= '<img src=\\\"'.$row[$j].'\\\">';\n" +
						"                    	}\n" +
						"                    	$pop_str .= '</div>';\n" +
						"                    	search"+Func.searchCount+"_p2($pop_str, ++$k);     	//tdに結果を埋め込む\n" +
						"                    }else									search"+Func.searchCount+"_p2($row[$j], ++$k);     //tdに結果を埋め込む\n" +
						"              }\n" +
						"        }\n" +
						"		 if($i>0)	echo \"<script type=\\\"text/javascript\\\">window.parent.$('#Search"+Func.searchCount+"_text_th').show();</script>\";    //カラム名を表示\n" +
						"        search"+Func.searchCount+"_p1($i.' result'.(($i != 1)?('s'):('')));    //件数表示\n" +
						"    }else{\n" +
						"        search"+Func.searchCount+"_p1('0 results');\n" +
						"    }\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("    unset($db"+Func.searchCount+");\n"):"") +
						((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("    pg_close($db"+Func.searchCount+");\n"):"") +
						"}\n\n" +
						"function search"+Func.searchCount+"_p1($str){\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Search"+Func.searchCount+"_echo1(\"'.$str.'\");</script>';\n" +
						"}\n" +
						"function search"+Func.searchCount+"_p2($str,$num){\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Search"+Func.searchCount+"_echo2(\"'.$str.'\",\"'.$num.'\");</script>';\n" +
						"}\n" +
						"?>\n";
		//End of php

		statement += 
				"\n" +
						"<script type=\"text/javascript\">\n" +
						"function Search"+Func.searchCount+"_echo1(str){\n" +
						"  var textArea = document.getElementById(\"Search"+Func.searchCount+"_text0\");\n" +
						"  textArea.innerHTML = str;\n" +
						"}\n" +
						"function Search"+Func.searchCount+"_echo2(str,num){\n" +
						"  var textArea = document.getElementById(\"Search"+Func.searchCount+"_text\"+num);\n" +
						//"  textArea.innerHTML += str+\"<br>\";\n" +
						"  $(\"#Search"+Func.searchCount+"_text\"+num).html(textArea.innerHTML+str+\"<br>\").trigger(\"create\");\n" +
						"}\n" +
						"\n" +
						"function Search"+Func.searchCount+"_refresh(){\n";

		for(int i=0; i<col_num; i++){
			statement +=
					"  document.getElementById(\"Search"+Func.searchCount+"_text"+(i+1)+"\").innerHTML = \"\";\n";

		}
		statement +=
				"}\n" +
						"</script>\n" +
						"<!-- Search end -->\n";


		Func.searchCount++;
		return statement;
	}
	//search end


	//added by goto 20130529 start  "select"
	/*	select("title", "c1:column1, c2:column2, ... ", "From以下")	*/
	private static String Func_select(Mobile_HTML5Function Func) {

		String title = "";
		String columns = "";
		String after_from = "";
		try{
			//title（第一引数）
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().equals(""))	title = fa1.getStr();
			else							title = "Select";
			//columns（第二引数）
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			columns += fa2.getStr();
			//after_from（第三引数）
			FuncArg fa3 = (FuncArg) Func.getArg(2);
			after_from += fa3.getStr().trim();
		}catch(Exception e){
			Log.info("<Warning> serach関数の引数が不足しています。 ex. select(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			return "";
		}
		if(columns.trim().equals("") || after_from.equals("")){
			Log.info("<Warning> serach関数の引数が不足しています。 ex. select(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			return "";
		}
		if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();
		//Log.info(title);

		int col_num=1;
		String columns0 = columns;
		while(columns0.contains(",")){
			columns0 = columns0.substring(columns0.indexOf(",")+1);
			col_num++;		//カウント
		}
		String[] s_name_array = new String[col_num];
		String[] s_array = new String[col_num];
		columns0 = columns;
		for(int i=0; i<col_num-1; i++){
			s_array[i] = columns0.substring(0,columns0.indexOf(","));
			columns0 = columns0.substring(columns0.indexOf(",")+1);
			//Log.i( "s_array["+i+"] = "+s_array[i]+"	"+columns0);
		}
		s_array[col_num-1] = columns0;
		//Log.i( "s_array["+(col_num-1)+"] = "+s_array[col_num-1]);
		int j=0;
		for(int i=0; i<col_num; i++){
			if(s_array[i].contains(":")){
				//if(!s_array[i].substring(0,s_array[i].indexOf(":")).contains(")"))
				s_name_array[j++] = s_array[i].substring(0,s_array[i].indexOf(":"));
				s_array[i] = s_array[i].substring(s_array[i].indexOf(":")+1);
			}else{
				if(!s_array[i].contains(")"))	s_name_array[j++] = s_array[i];
			}
			//Log.i("s_name_array["+(j-1)+"] = "+s_name_array[j-1] + "	s_array["+i+"] = "+s_array[i]);
		}
		boolean groupbyFlg = false;	//Flg
		//boolean[] aFlg = new boolean[col_num];	//Flg
		//boolean[] popFlg = new boolean[col_num];	//Flg
		String a = "";
		String select_col = "";
		String select_col_array = "\"";
		String select_aFlg = "\"";		//Flg
		String select_mailFlg = "\"";		//Flg
		String select_popFlg = "\"";	//Flg
		int a_pop_count = 0;
		for(int i=0; i<col_num; i++){
			a = s_array[i].replaceAll(" ","");
			if( a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
			if(a.startsWith("a(") || a.startsWith("anchor(")){
				select_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					select_col += s_array[i]+",";
					select_col_array += s_array[i]+"\",\"";
					select_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				select_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("mail(")){
				select_mailFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					select_col += s_array[i]+",";
					select_col_array += s_array[i]+"\",\"";
					select_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				select_mailFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("pop(") || a.startsWith("popup(")){
				select_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					select_col += s_array[i]+",";
					select_col_array += s_array[i]+"\",\"";
					select_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					select_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				select_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
			select_col += s_array[i] +((i<col_num-1)?(","):(""));
			select_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
		}
		col_num -= a_pop_count;
		//    	select_col = select_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("\\)","");
		//    	select_col_array = select_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("\\)","");
		select_col = select_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
		select_col_array = select_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");

		//Log.i("	1:"+title+"	2:"+columns+"	col_num:"+col_num);
		//Log.i("	select_col:"+select_col+"	select_col_array:"+select_col_array);
		//Log.i("	select_aFlg:"+select_aFlg+"	select_popFlg:"+select_popFlg);
		//Log.i("	groupbyFlg: "+groupbyFlg);


		String DBMS = GlobalEnv.getdbms();										//DBMS
		String DB = GlobalEnv.getdbname();										//DB
		String HOST = "", USER = "", PASSWD = "";
		if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			HOST = GlobalEnv.gethost();
			USER = GlobalEnv.getusername();
			PASSWD = GlobalEnv.getpassword();
		}

		String query = "";
		//Log.i(after_from_string);
		if(after_from.startsWith("#")){					//From以下をクエリの下(#*)から取ってくる場合
			if(!Func.after_from_string.contains(after_from)){
				Log.info("<Warning> select関数の第三引数に指定されている '"+after_from+"' が見つかりません。");
				return "";
			}
			query = Func.after_from_string
					.substring(Func.after_from_string.indexOf(after_from)+after_from.length())
					.trim().toLowerCase();
			if(query.contains("#"))	query = query.substring(0,query.indexOf("#")).trim().toLowerCase();
		}else
			query = after_from.toLowerCase();			//From以下を第三引数へ書く場合

		//Log.i("\n	Query: "+query);
		String from = "";
		String where = "";
		String groupby = "";
		String having = "";
		String orderby = "";
		String limit = "";
		if(query.contains(" limit ")){
			limit = query.substring(query.lastIndexOf(" limit ")+" limit ".length());
			query = query.substring(0,query.lastIndexOf(" limit "));
		}
		if(query.contains(" order by ")){
			orderby = query.substring(query.lastIndexOf(" order by ")+" order by ".length());
			query = query.substring(0,query.lastIndexOf(" order by "));
		}
		if(query.contains(" having ")){
			having = query.substring(query.lastIndexOf(" having ")+" having ".length());
			having = having.replaceAll("\\\"","\\\\\"");	// " -> \"
			query = query.substring(0,query.lastIndexOf(" having "));
		}
		if(query.contains(" group by ")){
			groupby = query.substring(query.lastIndexOf(" group by ")+" group by ".length());
			query = query.substring(0,query.lastIndexOf(" group by "));
		}
		if(query.contains(" where ")){
			where = query.substring(query.lastIndexOf(" where ")+" where ".length());
			where = where.replaceAll("\\'","\\\\'");		// ' -> \'
			if(where.contains("$session")){
				//if WHERE phrase contains $session(XX)
				where = where.replaceAll("\\$session","'\".\\$_SESSION").replaceAll("\\(","[\"").replaceAll("\\)","\"].\"'");
				//if it contains $_SESSION [""attribute""] or $_SESSION [" "attribute" "]
				where = where.replace("[\"\"","[\"").replace("\"\"]","\"]").replace("[\" \"","[\"").replace("\" \"]","\"]");
			}
			query = query.substring(0,query.lastIndexOf(" where "));
		}
		from = query.trim();
		//Log.i("	FROM: "+from+"\n	WHERE: "+where+"\n	GROUP: "+groupby+"\n	HAVING: "+having);
		//Log.i("	ORDER: "+orderby+"\n	LIMIT: "+limit+"\n	Query: "+query);

		//    	if(!groupbyFlg){
		//    		groupby = "";
		//    		having = "";
		//    	}


		String statement = "";
		//php
		statement += 
				"<!-- Select start -->\n" +
						"<!-- Select Panel start -->\n" +
						"<br>\n" +
						"<div id=\"SELECT"+Func.selectCount+"panel\" style=\"\" data-role=\"none\">\n" +
						"<hr>\n<div style=\"font-size:30;\" id=\"SelectTitle"+Func.selectCount+"\">"+title+"</div>\n<hr>\n" +
						"<br>\n" +
						"\n" +
						"<div id=\"Select"+Func.selectCount+"_text0\" data-role=\"none\"><!-- 件数 --></div>\n" +
						"\n" +
						"<table style=\"table-layout:fixed;\" data-role=\"table\" id=\"table-column-toggle"+Func.selectCount+"\" data-mode=\"columntoggle\" class=\"ui-responsive table-stroke\">\n" +
						"  <thead>\n" +
						"    <tr id=\"Select"+Func.selectCount+"_text_th\">\n";
		for(int i=0; i<col_num; i++){
			statement += 
					"        <th data-priority=\"1\">"+s_name_array[i]+"</th>\n";
		}
		statement += 
				"    </tr>\n" +
						"  </thead>\n" +
						"  <tbody>\n" +
						"    <tr>\n";
		for(int i=0; i<col_num; i++){
			statement +=
					"        <td id=\"Select"+Func.selectCount+"_text"+(i+1)+"\" style=\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\"></td>\n";
		}
		statement += 
				"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<br>\n" +
						"</div>\n" +
						"<script type=\"text/javascript\"> $('#Select"+Func.selectCount+"_text_th').hide(); </script>\n" +
						"<!-- Select Panel end -->\n" +
						"\n";

		Mobile_HTML5Env.PHP +=
				"<?php\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Select"+Func.selectCount+"_refresh();</script>';    //表示をリフレッシュ\n" +
						"\n" +
						//"    //ユーザ定義\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
						"    $select_col = \""+select_col+"\";\n" +
						"    $col_num = "+col_num+";\n" +                          //カラム数(Java側で指定)\n" +
						"    $table = '"+from+"';\n" +
						"    $where0 = '"+where+"';\n" +
						"    $select_col_array = array("+select_col_array+");\n" +
						"    $select_col_num = count($select_col_array);\n" +
						"    $select_a_Flg = array("+select_aFlg+");\n" +
						"    $select_mail_Flg = array("+select_mailFlg+");\n" +
						"    $select_pop_Flg = array("+select_popFlg+");\n" +
						"    $groupby = \""+groupby+"\"; 	           //null => WHERE句にlikeを書く／ not null => HAVING句に～    //[要] Java側で、列名に予約語から始まるものがあるかチェック\n" +
						"    $having0 = \""+having+"\";\n" +
						"    $orderby = \""+((orderby!="")?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
						"    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
						"\n" +
						"    $selectWord"+Func.selectCount+" = checkHTMLsc('%');\n" +
						"    $selectWord"+Func.selectCount+" = preg_replace('/　/', ' ', $selectWord"+Func.selectCount+");       //全角スペースを半角スペースへ\n" +
						"    $selectWord"+Func.selectCount+" = preg_replace('/\\s+/', ' ', $selectWord"+Func.selectCount+");      //連続する半角スペースを1つの半角スペースへ\n" +
						"    $selectWord"+Func.selectCount+" = trim($selectWord"+Func.selectCount+");                            //trim\n" +
						"    $selectWord"+Func.selectCount+" = preg_replace('/\\s/', '%', $selectWord"+Func.selectCount+");       //半角スペースを%へ変換\n" +
						"\n" +
						"    if($selectWord"+Func.selectCount+" != \"\"){\n";
		if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
			Mobile_HTML5Env.PHP +=
					"        $select_db"+Func.selectCount+" = new SQLite3($sqlite3_DB);\n" +
							"        $sql = \"SELECT \".$select_col.\" FROM \".$table;\n" +
							"        if($where0 != \"\")    $sql .= \" WHERE \".$where0.\" \";\n" +
							"    \n" +
							"    	//左辺の作成（※Java側でOK?)\n" +
							"        $sw = $selectWord"+Func.selectCount+";\n" +
							"        $sw_buf = \"\";\n" +
							"        $l_str = \"\";\n" +
							"        foreach($select_col_array as $val)    $l_str .= \"ifnull(\".$val.\",'')||\";\n" +
							"        $l_str = substr($l_str, 0, -2);      //substring   最後の||をカット\n" +
							"        $l_str .= \" LIKE '%\";\n" +
							"        //右辺の作成\n" +
							"        while(strpos($sw,'%')){		//%を含んでいる間\n" +
							"            $pos = strpos($sw,'%');          //indxOf  		%が最初に現れる位置\n" +
							"            $rest = substr($sw, 0, $pos);    //substring    最初の%以降をカット\n" +
							"            $sw = substr($sw, $pos+1);       //substring    最初の%までカット\n" +
							"            $sw_buf .= $l_str.$rest.\"%' AND \";\n" +
							"        }\n" +
							"        $sw_buf .= $l_str.$sw.\"%' \";         //最後のswを結合\n" +
							"        \n" +
							"        if($groupby == \"\"){    //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** WHERE句の作成 start ***/\n" +
							"            //WHERE  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //   AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $WHERE = \"\";\n" +
							"            if($where0 == \"\")   $WHERE = \" WHERE \";\n" +
							"            else                $WHERE = \" AND \";\n" +
							"            $WHERE .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" \".$WHERE.\" \";\n" +
							"            //$sql .= $WHERE.\" \".$groupby.\" \";\n" +
							"            /*** WHERE句の作成 end ***/\n" +
							"        }else{                        //null => WHERE句にlikeを書く／ not null => HAVING句に～\n" +
							"            /*** HAVING句の作成 start ***/\n" +
							"            //HAVING  ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[1]%'\n" +
							"            //    AND ifnull(id,'')||ifnull(name,'')||ifnull(r_year,'') LIKE '%sw[2]%'...\n" +
							"            \n" +
							"            $HAVING = \"\";\n" +
							"            if($having0 == \"\")  $HAVING = \" HAVING \";\n" +
							"            else	            $HAVING = \" HAVING \".$having0.\" AND \";\n" +
							"    		$HAVING .= $sw_buf;\n" +
							"            \n" +
							"            $sql .= \" GROUP BY \".$groupby.\" \".$HAVING;\n" +
							"            /*** HAVING句の作成 end ***/\n" +
							"        }\n" +
							"        $sql .= \" \".$orderby.\" \".$limit;	//order by句とlimitを結合\n" +
							"        select"+Func.selectCount+"_p1('<font color=red>SQL error: '.$sql.\";</font>\");	//エラー時\n" +
							"\n" +
							"        $result = $select_db"+Func.selectCount+"->query($sql);\n" +
							"\n" +
							"        $i = 0;\n" +
							"        $pop_num = 0;\n" +
							"        $t = date('YmdHis');\n" +
							"        while($row = $result->fetchArray()){\n";
		} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			Mobile_HTML5Env.PHP +=
					"        $select_db"+Func.selectCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
							"        $sql = \"SELECT \".$select_col.\" FROM \".$table;\n" +
							"        if($where0 != \"\")	$sql .= \" WHERE \".$where0.\" \";\n" +
							"        if($groupby != \"\")	$sql .= \" GROUP BY \".$groupby.\" \";\n" +
							"        if($having0 != \"\")	$sql .= \" HAVING \".$having0.\" \";\n" +
							"        $sql .= \" \".$orderby.\" \".$limit;\n" +
							"        select"+Func.selectCount+"_p1('<font color=red>SQL error: '.$sql.\";</font>\");	//エラー時\n" +
							"\n" +
							"        $result = pg_query($select_db"+Func.selectCount+", $sql);\n" +
							"\n" +
							"        $i = 0;\n" +
							"        $pop_num = 0;\n" +
							"        $t = date('YmdHis');\n" +
							"        while($row = pg_fetch_row($result)){\n";
		}
		Mobile_HTML5Env.PHP +=
				"              $i++;\n" +
						"              $k=0;\n" +
						"              for($j=0; $j<$select_col_num; $j++){\n" +
						"					if($select_a_Flg[$j]=='true' || $select_mail_Flg[$j]=='true' || $select_pop_Flg[$j]=='true')	;\n" +
						"                    else if($j>0 && $select_a_Flg[$j-1]=='true')	select"+Func.selectCount+"_p2('<a href=\\\"'.$row[$j].'\\\" target=\\\"_blank\\\" rel=\\\"external\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     //tdに結果を埋め込む\n" +
						"                    else if($j>0 && $select_mail_Flg[$j-1]=='true')	select"+Func.selectCount+"_p2('<a href=\\\"mailto:'.$row[$j].'\\\" target=\\\"_self\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     //tdに結果を埋め込む\n" +
						"                    //else if($j>0 && $select_pop_Flg[$j-1]=='true')	select"+Func.selectCount+"_p2('<a href=\\\"'.$row[$j].'\\\" target=\\\"_blank\\\" rel=\\\"external\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>', ++$k);     //tdに結果を埋め込む\n" +
						"                    else if($j>0 && $select_pop_Flg[$j-1]=='true' && !is_null($row[$j])){\n" +
						"                    	$pop_str = '<a href=\\\"#select_popup"+Func.selectCount+"_'.(++$pop_num).'_'.$t.'\\\" data-rel=\\\"popup\\\" data-icon=\\\"arrow-r\\\" style=\\\"white-space:nowrap; overflow:hidden; text-overflow:ellipsis;\\\">'.$row[$j-1].'</a>'\n" +
						"							.'<div data-role=\\\"popup\\\" id=\\\"select_popup"+Func.selectCount+"_'.($pop_num).'_'.$t.'\\\" data-transition=\\\"slideup\\\" style=\\\"width:95%;\\\" data-overlay-theme=\\\"a\\\">'\n" +
						"								.'<a href=\\\"#\\\" data-rel=\\\"back\\\" data-role=\\\"button\\\" data-theme=\\\"a\\\" data-icon=\\\"delete\\\" data-iconpos=\\\"notext\\\" class=\\\"ui-btn-right\\\">Close</a>'\n" +
						"								.'<h2>'.$row[$j-1].'</h2>'\n" +
						"								.'<p>'.$row[$j].'</p>'\n" +
						"							.'</div>';\n" +
						"                    	select"+Func.selectCount+"_p2($pop_str, ++$k);     	//tdに結果を埋め込む\n" +
						"                    }else									select"+Func.selectCount+"_p2($row[$j], ++$k);     //tdに結果を埋め込む\n" +
						"              }\n" +
						"        }\n" +
						"		 if($i>0)	echo \"<script type=\\\"text/javascript\\\">window.parent.$('#Select"+Func.selectCount+"_text_th').show();</script>\";    //カラム名を表示\n" +
						"        select"+Func.selectCount+"_p1($i.' result'.(($i != 1)?('s'):('')));    //件数表示\n" +
						"    }else{\n" +
						"        select"+Func.selectCount+"_p1('0 results');\n" +
						"    }\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("    unset($select_db"+Func.selectCount+");\n\n"):"") +
						((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("    pg_close($select_db"+Func.selectCount+");\n\n"):"") +
						"function select"+Func.selectCount+"_p1($str){\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Select"+Func.selectCount+"_echo1(\"'.$str.'\");</script>';\n" +
						"}\n" +
						"function select"+Func.selectCount+"_p2($str,$num){\n" +
						"    echo '<script type=\"text/javascript\">window.parent.Select"+Func.selectCount+"_echo2(\"'.$str.'\",\"'.$num.'\");</script>';\n" +
						"}\n" +
						"?>\n";
		//End of php


		statement += 
				"\n" +
						"<script type=\"text/javascript\">\n" +
						"function Select"+Func.selectCount+"_echo1(str){\n" +
						"  var textArea = document.getElementById(\"Select"+Func.selectCount+"_text0\");\n" +
						"  textArea.innerHTML = str;\n" +
						"}\n" +
						"function Select"+Func.selectCount+"_echo2(str,num){\n" +
						"  var textArea = document.getElementById(\"Select"+Func.selectCount+"_text\"+num);\n" +
						"  $(\"#Select"+Func.selectCount+"_text\"+num).html(textArea.innerHTML+str+\"<br>\");\n" +
						"}\n" +
						"\n" +
						"function Select"+Func.selectCount+"_refresh(){\n";

		for(int i=0; i<col_num; i++){
			statement +=
					"  document.getElementById(\"Select"+Func.selectCount+"_text"+(i+1)+"\").innerHTML = \"\";\n";
		}
		statement +=
				"}\n" +
						"</script>\n" +
						"<!-- Select end -->\n";
		//End of php


		Func.selectCount++;
		return statement;
	}
	static boolean G2_form = false;
	static int G2_form_count = 0;
	static boolean formFileUpload = false;
	private static String Func_insert(boolean update, boolean insert_update, Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {

		if(Mobile_HTML5_form.G2){
			//for [ form() ]!
			G2_form = true;
			G2_form_count++;
			//Log.e(Mobile_HTML5_form.G2_dataQuantity+"  "+G2_form_count);
		}

		String title = "";
		String columns = "";
		String after_from = "";
		//    	String insertFlag = "false";
		String pKey = "";	//primary key
		String buttonName = "";
		try{
			//title（第一引数）
			FuncArg fa1 = (FuncArg) Func.getArg(0);
			if(!fa1.getStr().isEmpty())	title = fa1.getStr();
			//columns（第二引数）
			FuncArg fa2 = (FuncArg) Func.getArg(1);
			columns += fa2.getStr();
			//after_from（第三引数）
			FuncArg fa3 = (FuncArg) Func.getArg(2);
			after_from += fa3.getStr().trim();
			if(update){
				//（第四引数）
				FuncArg fa4 = (FuncArg) Func.getArg(3);
				pKey += fa4.getStr().toLowerCase().trim();	//TODO nullの場合のエラーチェック
				buttonName = getValue(5, Func).trim();
			}else{
				buttonName = getValue(4, Func).trim();
			}

		}catch(Exception e){
			if(update){
				Log.err("<Warning> update関数の引数が不足しています。 ex. update(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\", \"pKey\")");
			}else if(insert_update){
				Log.err("<Warning> form関数 or insert_update関数の引数が不足しています。 ex. insert_update(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			}else{
				Log.err("<Warning> insert関数の引数が不足しています。 ex. (\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			}
			return "";
		}
		if(columns.trim().equals("") || after_from.equals("")){
			Log.err("<Warning> insert関数の引数が不足しています。 ex. insert(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
			return "";
		}
		if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();

		//Check options
		boolean noresult = false;
		boolean noreset = false;
		boolean reloadAfterInsert = false;
		int reloadAfterInsertTime = 1;	//Default = 1sec
		if(Func.decos.containsKey("noresult") || Func.decos.containsKey("noclear"))	noresult = true;
		if(Func.decos.containsKey("noreset"))	noreset = true;
		if(Func.decos.containsKey("reloadafterinsert") || Func.decos.containsKey("reloadafterupdate")){
			reloadAfterInsert = true;
			try{
				if(Func.decos.containsKey("reloadafterinsert")){
					reloadAfterInsertTime = Integer.parseInt(Func.decos.getStr("reloadafterinsert").replace("sec","").replace("s","").trim());
				}else if(Func.decos.containsKey("reloadafterupdate")){
					reloadAfterInsertTime = Integer.parseInt(Func.decos.getStr("reloadafterupdate").replace("sec","").replace("s","").trim());
				}
			}catch (Exception e) { }
		}
		//Log.e(buttonName+" "+noresult+" "+noreset+" "+reloadAfterInsert+" "+reloadAfterInsertTime+"  "+decos);


		int col_num = 0;
		String[] s_array = new String[columns.length()-columns.replace(",", "").length()];
		boolean sq = false, dq = false;
		int braces/*{}*/ = 0;
		int lastIndex = 0;
		columns += ",";
		for(int i=0; i<columns.length();i++){
			char c = columns.charAt(i);

			if(c=='\'' && !dq && braces==0)		sq = !sq;
			else if(c=='"' && !sq && braces==0)	dq = !sq;
			else if(c=='{' && !sq && !dq)		braces++;
			else if(c=='}' && !sq && !dq)		braces--;
			else if(c==',' && !dq && !sq && braces==0){
				s_array[col_num++] = columns.substring(lastIndex, i);
				lastIndex = i+1;
			}
		}
		String[] s_name_array = new String[col_num];
		int j=0;
		for(int i=0; i<col_num; i++){
			//Log.i( "s_array["+i+"] = "+s_array[i]);
			if(s_array[i].contains(":")){
				//if(!s_array[i].substring(0,s_array[i].indexOf(":")).contains(")"))
				s_name_array[j++] = s_array[i].substring(0,s_array[i].indexOf(":")).trim();
				s_array[i] = s_array[i].substring(s_array[i].indexOf(":")+1);
			}else{
				s_name_array[j++] = "";
				//if(!s_array[i].contains(")"))	s_name_array[j++] = s_array[i];	  <- ??
			}
			//Log.i("s_name_array["+(j-1)+"] = "+s_name_array[j-1] + "	s_array["+i+"] = "+s_array[i]);
		}
		boolean groupbyFlg = false;	//Flg
		//boolean[] aFlg = new boolean[col_num];	//Flg
		//boolean[] popFlg = new boolean[col_num];	//Flg
		String a = "";
		String insert_col = "";
		String update_col_array = "'";
		String update_where = "";
		boolean[] textareaFlg = new boolean[col_num];
		boolean[] hiddenFlg = new boolean[col_num];
		boolean[] noinsertFlg = new boolean[col_num];
		String[] validationType = new String[col_num];
		boolean[] notnullFlg = new boolean[col_num];
		String[] value = new String[col_num];
		String[] uploadFilePath = new String[col_num];
		String[] at_array = new String[col_num];
		String[] radioButton_array = new String[col_num];
		String[] selectbox_array = new String[col_num];
		String[] checkbox_array = new String[col_num];
		Arrays.fill(radioButton_array, "");
		Arrays.fill(selectbox_array, "");
		Arrays.fill(checkbox_array, "");
		String notnullFlg_array = "";
		String checkboxFlg_array = "";
		String[] $session_array = new String[col_num];
		String[] $time_array = new String[col_num];
		String[] $gps_array = new String[col_num];
		String[] text_array = new String[col_num];
		String buttonSubmit = "";
		String insert_aFlg = "\"";	//Flg
		String insert_popFlg = "\"";	//Flg
		int noinsert_count = 0;
		int a_pop_count = 0;
		for(int i=0; i<col_num; i++){
			//Log.i(s_array[i]);
			//Check: @textarea, @hidden, @noinsert, @notnull, @date, @date1-5, @time	//TODO:リファクタリング
			textareaFlg[i] = false;
			hiddenFlg[i] = false;
			noinsertFlg[i] = false;
			validationType[i] = "";
			notnullFlg[i] = false;
			value[i] = "";
			uploadFilePath[i] = "";
			at_array[i] = "";
			String str = "";

			if(s_array[i].replaceAll(" ","").contains("@{")){
				str = s_array[i].substring(s_array[i].lastIndexOf("@")+1);	//@以下の文字列
				at_array[i] = str;
				//Log.e(str);
				if(str.contains("='")){
					//image='', file=''
					String l = str.substring(str.indexOf("='")+2);
					uploadFilePath[i] = l.substring(0,l.indexOf("'"));
					str = str.substring(0,str.indexOf("='"));
					if(l.contains(";")){
						str += l.substring(l.indexOf(";"));
					}
					formFileUpload = true;
					//Log.e(str+" "+uploadFile[i]);
				}
				if(str.contains("textarea"))
					textareaFlg[i] = true;
				if(str.contains("hidden"))
					hiddenFlg[i] = true;
				if(str.contains("noinsert") || str.contains("noupdate")){
					noinsertFlg[i] = true;
					noinsert_count++;
				}else{
					if(str.contains("notnull")){
						if(i==(col_num-1))	notnullFlg_array += "TRUE";
						else				notnullFlg_array += "TRUE,";
					}else{
						if(i==(col_num-1))	notnullFlg_array += "FALSE";
						else				notnullFlg_array += "FALSE,";
					}
				}
				if(str.contains("notnull"))	notnullFlg[i] = true;
				validationType[i] = Mobile_HTML5_form.checkFormValidationType(str);	//form validation

				s_array[i] = s_array[i].substring(0,s_array[i].lastIndexOf("@"));
				//Log.i(s_array[i]);
			}else{
				if(i==(col_num-1))	notnullFlg_array += "FALSE";
				else				notnullFlg_array += "FALSE,";
			}

			a = s_array[i].replaceAll(" ","");
			//Log.i(a);
			//$session()あり
			if(a.contains("=")){
				String a_right = a.substring(a.indexOf("=")+1).trim();
				if(a_right.startsWith("$session(")){
					$session_array[i] = a.substring(a.indexOf("$session(")+"$session(".length(),a.indexOf(")"));
					$time_array[i] = "";
					$gps_array[i] = "";
					text_array[i] = "";
					a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
					s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
				}else if(a_right.startsWith("time(") || a_right.startsWith("date(")){
					String d = s_array[i].substring(s_array[i].indexOf("(")+1,s_array[i].lastIndexOf(")")).trim(); 
					$time_array[i] = "date(\""+( (d.equals(""))? ("Y-m-d H:i:s") : (d) )+"\")";	//"date(\"Y/m/d(D) H:i:s\")";
					$session_array[i] = "";
					$gps_array[i] = "";
					text_array[i] = "";
					a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
					s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
				}else if(a_right.startsWith("gps_info(")){
					//gps_info()の取得
					$gps_array[i] = "gps_info";

					$session_array[i] = "";
					$time_array[i] = "";
					text_array[i] = "";
					a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
					s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
				}else if(a.contains("{") && a.contains("}")){
					String x = s_array[i];
					text_array[i] = x.substring(x.indexOf("{")+"{".length(), x.indexOf("}"));
					$session_array[i] = "";
					$time_array[i] = "";
					$gps_array[i] = "";
					a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf("}")+1).trim();
					s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf("}")+1).trim();
				}else{
					$session_array[i] = "";
					$time_array[i] = "";
					$gps_array[i] = "";
					text_array[i] = "";
				}
			}else{
				$session_array[i] = "";
				$time_array[i] = "";
				$gps_array[i] = "";
				text_array[i] = "";
			}
			//Log.i(s_array[i]+"	"+$session_array[i]);
			//Log.i(button_array[i]+"	"+button_array[i]);

			s_array[i] = s_array[i].trim();
			if(s_array[i].contains("=")){
				value[i] = s_array[i].substring(s_array[i].indexOf("=")+1);
				s_array[i] = s_array[i].substring(0, s_array[i].indexOf("="));
			}

			if(a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
			if(a.startsWith("a(") || a.startsWith("anchor(")){
				insert_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					insert_col += s_array[i]+",";
					insert_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					insert_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				insert_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
			if(a.startsWith("pop(") || a.startsWith("popup(")){
				insert_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
				if(a.endsWith(")")){
					insert_col += s_array[i]+",";
					insert_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
					insert_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
				}else	a_pop_count++;
			}else
				insert_popFlg += "false\""+((i<col_num-1)?(",\""):(""));

			if(!noinsertFlg[i]){
				insert_col += s_array[i] +((i<col_num-1)?(","):(""));
				if(update || insert_update)	update_col_array += s_array[i] +"'"+((i<col_num-1)?(",'"):(""));
			}
		}
		col_num -= a_pop_count;
		insert_col = insert_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("\\)","");


		//Log.i("	1:"+title+"	2:"+columns+"	col_num:"+col_num);
		//Log.i("	insert_col:"+insert_col+"	update_col_array:"+update_col_array);
		//Log.i("	insert_aFlg:"+insert_aFlg+"	insert_popFlg:"+insert_popFlg);
		//Log.i("	notnullFlg_array: "+notnullFlg_array);


		String DBMS = GlobalEnv.getdbms();										//DBMS
		String DB = GlobalEnv.getdbname();										//DB
		String HOST = "", USER = "", PASSWD = "";
		if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			HOST = GlobalEnv.gethost();
			USER = GlobalEnv.getusername();
			PASSWD = GlobalEnv.getpassword();
		}

		String query = "";
		//Log.i(after_from_string);
		if(after_from.startsWith("#")){					//From以下をクエリの下(#*)から取ってくる場合
			if(!Func.after_from_string.contains(after_from)){
				Log.info("<Warning> insert関数の第三引数に指定されている '"+after_from+"' が見つかりません。");
				return "";
			}
			query = Func.after_from_string
					.substring(Func.after_from_string.indexOf(after_from)+after_from.length())
					.trim().toLowerCase();
			if(query.contains("#"))	query = query.substring(0,query.indexOf("#")).trim().toLowerCase();
		}else
			query = after_from.toLowerCase();			//From以下を第三引数へ書く場合
		//Log.i("\n	Query: "+query);
		String from = "";
		from = query.toLowerCase().trim();
		if(update || insert_update){
			if(after_from.startsWith("#")){
				update_where = from.substring(from.indexOf(" where ")).trim();
			}else{
				update_where = after_from;
				if(update_where.indexOf(" where ") > 1){
					update_where = update_where.substring(update_where.indexOf(" where ")).trim();
				}else{
					update_where = "";
				}
			}
			if(update_where.contains("$session")){
				update_where = update_where.replaceAll("\\$session","'\".\\$_SESSION").replaceAll("\\(","['").replaceAll("\\)","'].\"'");
			}
			if(from.indexOf(" where ") > 1){
				from = from.substring(0,from.indexOf(" where ")).trim();
			}
		}
		//Log.i("	FROM:"+from+"	update_where:"+update_where);
		//Log.i("	FROM: "+from+"\n	WHERE: "+where+"\n	GROUP: "+groupby+"\n	HAVING: "+having);
		//Log.i("	ORDER: "+orderby+"\n	LIMIT: "+limit+"\n	Query: "+query);

		String[] cols = new String[col_num];
		if(update){
			String s = insert_col+",";
			try {
				for(int i=0;i<col_num;i++){
					cols[i] = s.substring(0,s.indexOf(","));
					s = s.substring(s.indexOf(",")+1);
					//Log.e(cols[i]);
				}
			} catch (Exception e) {	}
		}


		String statement = "";
		String update_statement = "";
		String gps_js = "";
		String php = "";
		String formPHPfileName = "";
		if(update){
			//update()
			formPHPfileName = html_env.getFileName2()+"_SSQLupdateform_"+Func.insertCount+".php";
		}else{
			//insert(), form()=insert_update()
			formPHPfileName = html_env.getFileName2()+"_SSQLform_"+Func.insertCount+".php";
		}

		//php
		statement += 
				"\n" +
						"<!-- SSQL Insert"+Func.insertCount+" start -->\n" +
						"<!-- SSQL Insert"+Func.insertCount+" FORM start -->\n" +
						"<br>\n" +
						//"<div id=\"SSQL_insert"+insertCount+"panel\" style=\"background-color:whitesmoke; width:99%; border:0.1px gray solid;\" data-role=\"none\">\n" +
						//"<div style=\"padding:3px 5px;border-color:hotpink;border-width:0 0 1px 7px;border-style:solid;background:#F8F8F8; font-size:30;\" id=\"SSQL_insertTitle"+insertCount+"\">"+title+"</div>\n" +
						"<div id=\"SSQL_insert"+Func.insertCount+"panel\" style=\"\" data-role=\"none\">\n";
		if(!title.isEmpty()){
			statement += 
					"<hr>\n<div style=\"font-size:30;\" id=\"SSQL_insertTitle"+Func.insertCount+"\">"+title+"</div>\n<hr>\n" +
							"<br>\n";
		}
		statement += "<form method=\"post\" action=\"\" target=\"dummy_ifr\""+getFormFileUploadHTML1()+">\n";

		int insertWordCount = 0;
		for(int i=0; i<col_num; i++){
			String updateFromValue = "";
			if(update){
				updateFromValue = "'.sr($row['"+cols[i]+"']).'";
			}
			if($session_array[i].equals("") && $time_array[i].equals("") && $gps_array[i].equals("")){
				if(!text_array[i].equals("")){
					//Log.i("text_array:"+text_array[i]);
					String ss = text_array[i]+"|";
					String at = at_array[i];
					int pipeCount = ss.length() - ss.replaceAll("\\|","").length();
					//Log.i("pipeCount:"+pipeCount);

					if(!at.contains("radio") && !at.contains("select") && !at.contains("check") && pipeCount==1){
						//編集不可テキスト ex){2013秋}
						statement += 
								"	<"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
										""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+text_array[i]+"" +
										""+((!textareaFlg[i])?("\">"):("</textarea>"))+"\n";
						update_statement += 	//TODO: button
								"	<"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" " +
								"value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
								""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+text_array[i]+"" +
								""+((!textareaFlg[i])?("\">"):(updateFromValue+"</textarea>"))+"\n";
						if(!noinsertFlg[i]){
							statement += 
									"	<input type=\"hidden\" id=\"SSQL_insert"+Func.insertCount+"_words"+(++insertWordCount)+"\" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" value=\""+text_array[i]+"\">\n";
							update_statement += //TODO: button
									"	<input type=\"hidden\" id=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" value=\""+text_array[i]+"\">\n";
						}
					}else{

						String inputType = "radio";		//Default: input type="radio"
						if(at.contains("select"))		inputType = "select";
						else if(at.contains("check"))	inputType = "checkbox";
						boolean isSQL = !at.contains("sql")? false : true;
						String update_statement_buf = "";

						//ラジオボタン ex){出席|欠席|その他}@{radio}, {★☆☆=1|★★☆=2|★★★=3}など (2個以上の選択項目がある場合、@{radio}はあってもなくてもOK)
						//セレクトボックス ex){出席|欠席|その他}@{selectbox もしくは select}
						//チェックボックス ex){出席|欠席|その他}@{checkbox もしくは check}
						//※ DBから値を取得する場合の記述例：{select name, id from movie}@{sql, selectbox}
						statement += "   <div data-role=\"controlgroup\">\n" + 
								"		<div style=\"text-align:left; font-size:16.5px\">"+s_name_array[i]+"</div>\n";
						update_statement += "   <div data-role=\"controlgroup\">\n" + 
								"		<div style=\"text-align:left; font-size:16.5px\">"+s_name_array[i]+"</div>\n";
						insertWordCount++;

						if(inputType.equals("select")){
							//セレクトボックス
							String name = "SSQL_insert"+Func.insertCount+"_words"+(insertWordCount);
							selectbox_array[i] = name;
							//							statement += "		<select name=\""+name+"\" data-native-menu=\"false\">\n";
							//							update_statement += "		<select name=\""+name+"\" data-native-menu=\"false\">\n";	//TODO?
							//20161207 bootstrap
							statement += "		<select class=\"form-control\" name=\""+name+"\">\n";
							update_statement += "		<select class=\"form-control\" name=\""+name+"\">\n";	//TODO?
						}
						for(int k=1; k<=pipeCount; k++){
							String val = ss.substring(0,ss.indexOf("|")).trim();
							String insert_val = val;
							if(val.contains("=")){
								insert_val = val.substring(val.indexOf("=")+1).trim();
								val = val.substring(0, val.indexOf("=")).trim();
							}

							String id = "SSQL_insert"+Func.insertCount+"_words"+insertWordCount;
							String fn = html_env.getFileName2()+"_"+id+".php";
							String name = id;
							String checked = (k>1)? "" : " checked=\"checked\"" ;
							String php_echo = "";
							if(inputType.equals("radio") || inputType.equals("checkbox")){
								//ラジオボタン, チェックボックス
								if(inputType.equals("checkbox")){
									//チェックボックス
									checkbox_array[i] = name;
									name += "[]";
									checked = "";
								}else
									radioButton_array[i] = name;

								if(!isSQL){
									statement += 
											"		<input type=\""+inputType+"\" name=\""+name+"\" id=\""+id+"_"+k+"\" value=\""+insert_val+"\""+checked+">\n" +
													"		<label for=\""+id+"_"+k+"\">"+val+"</label>\n";
									update_statement += //TODO radio button
											"		<input type=\""+inputType+"\" name=\""+name+"\" id=\""+id+"_"+k+"\" value=\""+insert_val+"\""+checked+">\n" +
											"		<label for=\""+id+"_"+k+"\">"+val+"</label>\n";
								}else{
									//DBから値を取得する処理 ex){select name, id from movie}@{sql, selectbox}
									php_echo += 
											"			echo '		<input type=\""+inputType+"\" name=\"'.$name.'\" id=\"'.$id.'_'.$i.'\" value=\"'.$insert_val.'\"'.(($i != $checked_num)? '' : '"+checked+"').\">\n\";\n" +
													"	    	echo '		<label for=\"'.$id.'_'.$i.'\">'.$val.\"</label>\n\";\n";
								}
							}
							else if(inputType.equals("select")){
								//セレクトボックス
								if(!isSQL){
									statement += "			<option value=\""+insert_val+"\""+((k>1)? (""):(" selected"))+">"+val+"</option>\n";
									update_statement += "			<option value=\""+insert_val+"\""+((k>1)? (""):(" selected"))+">"+val+"</option>\n";	//TODO?
								}else{
									//DBから値を取得する処理 ex){select name, id from movie}@{sql, selectbox}
									php_echo += 
											"			echo '			<option value=\"'.$insert_val.'\"'.(($i != $checked_num)? '' : ' selected').'>'.$val.'</option>\n';\n";
								}
							}

							if(isSQL){
								String b = "require_once ('"+fn+"');";
								if(!Start_Parse.sessionFlag)
									b = "<?php "+b+" ?>\n";
								else
									b = "EOF;\n"+b+"\n		echo <<<EOF\n";
								statement += b;
								update_statement += b;

								//php
								int checked_num = 1;
								String sql = val;
								String php2 =	Mobile_HTML5.getSessionStartString() +
										"<?php\n" +
										"	$checked_num = "+checked_num+";\n" +
										"	$name = '"+name+"';\n" +
										"	$id = '"+id+"';\n" +
										"   \n" +
										"   $sql = '"+sql+"';\n" +
										"	try{\n";
								if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
									php2 += 
											"		$db = new SQLite3('"+DB+"');\n" +
													"		$results = $db->query($sql);\n" +
													"		$i = 1;\n" +
													"		while ($row = $results->fetchArray()) {\n";
								}
								else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
									php2 +=
											"		$db = pg_connect(\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
													"		$results = pg_query($db, $sql);\n" +
													"		$i = 1;\n" +
													"		while ($row = pg_fetch_row($results)) {\n";
								}
								php2 +=
										"			$val = $row[0];\n" +
												"			$insert_val = $row[1];\n" +
												"			if(is_null($insert_val))	$insert_val = $val;\n" +
												php_echo +
												"	    	$i++;\n" +
												"		}\n" +
												"	}catch(Exception $e){\n" +
												"		echo '<font color=red>Select failed.<br>'.$e.'</font>';\n" +
												"	}\n";
								if(DBMS.equals("sqlite") || DBMS.equals("sqlite3"))
									php2 += "	$db->close();";
								else if(DBMS.equals("postgresql") || DBMS.equals("postgres"))
									php2 += "	pg_close($db);\n";
								php2 += "?>\n";

								Mobile_HTML5.createFile(html_env, fn, php2);//PHPファイルの作成
							}

							update_statement_buf = "   <script type=\"text/javascript\"> $(\\\'input[name="+id+"]\\\').val([\\\'"+updateFromValue+"\\\']); </script>\n";
							ss = ss.substring(ss.indexOf("|")+1);
						}
						if(inputType.equals("select")){
							//セレクトボックス
							statement += "		</select>\n";
							update_statement += "		</select>\n";
						}
						statement += "   </div>\n";
						update_statement += "   </div>\n";
						update_statement += update_statement_buf;
					}
				}else{
					String at = at_array[i];
					boolean isOutTitle = (at.contains("outtitle") || at.contains("outlabel") || at.contains("noplaceholder"))? true : false;	//placeholderにタイトルをセットしない
					String outTitle = "";
					if(isOutTitle){
						outTitle = "<div style=\"text-align:left; font-size:16.5px; margin-top:15px;\">"+s_name_array[i]+"</div>\n";
						s_name_array[i] = "";
					}

					if(validationType[i].isEmpty()){
						statement += 
								outTitle +
								"<div class=\"form-group\">" +	//20161207 bootstrap
								"	"+( (!textareaFlg[i])? "" : "<span>" )+"<span>" +
								"<"+((!textareaFlg[i])?("input"):("textarea"))+"" +
								" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\"" +
								" id=\"SSQL_insert"+Func.insertCount+"_words"+(++insertWordCount)+"\"" +
								" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\"" +
								""+((!value[i].equals(""))? " value=\""+value[i]+"\"" : "")+
								" placeholder=\""+s_name_array[i]+"\""+Mobile_HTML5_form.getFormClass(notnullFlg[i], "")+">" +
								""+((!textareaFlg[i])?(""):("</textarea>")) +
								"</span>"+( (!textareaFlg[i])? "" : "</span>" )+"\n"+
								"</div>";	//20161207 bootstrap
						update_statement += 
								outTitle +
								"<div class=\"form-group\">" +	//20161207 bootstrap
								"	"+( (!textareaFlg[i])? "" : "<span>" )+"<span>" +
								"<"+((!textareaFlg[i])?("input"):("textarea"))+"" +
								" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\"" +
								" id=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\"" +
								" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\"" +
								""+((!value[i].equals(""))? " value=\""+value[i]+"\"" : "")+
								" "+((!textareaFlg[i])?("value=\""+updateFromValue+"\""):(""))+
								" placeholder=\""+s_name_array[i]+"\""+Mobile_HTML5_form.getFormClass(notnullFlg[i], "")+">" +
								""+((!textareaFlg[i])?(""):(updateFromValue+"</textarea>")) +
								"</span>"+( (!textareaFlg[i])? "" : "</span>" )+"\n"+
								"</div>";	//20161207 bootstrap
						//statement += "    <input type=\"text\" name=\"SSQL_insert"+insertCount+"_words"+(insertWordCount)+"\" placeholder=\""+s_name_array[i]+"\">\n";
					}else{
						statement += Mobile_HTML5_form.getFormValidationString(validationType[i], notnullFlg[i], "SSQL_insert"+Func.insertCount+"_words"+(++insertWordCount), s_name_array[i], updateFromValue, outTitle, uploadFilePath[i]);
						update_statement
						+= Mobile_HTML5_form.getFormValidationString(validationType[i], notnullFlg[i], "SSQL_insert"+Func.insertCount+"_words"+(insertWordCount), s_name_array[i], updateFromValue, outTitle, uploadFilePath[i]);
					}
				}
			}else{
				String echo = "", echo2 = "";
				if(!$session_array[i].equals("")){
					echo += "	echo $_SESSION['"+$session_array[i]+"'];\n";
					echo2 += "'.$_SESSION['"+$session_array[i]+"'].'";
				}
				else if(!$time_array[i].equals("")){
					echo += "	echo "+$time_array[i]+";\n";
					echo2 += "'."+$time_array[i]+".'";
				}
				else if(!$gps_array[i].equals("")){
					Mobile_HTML5Env.addJsCss("jscss/googleMap.js");
					echo += "	echo \"位置情報(緯度・経度)\";\n";
					gps_js +=
							"\n<!-- getGPSinfo() -->\n" +
									"<script type=\"text/javascript\">\n" +
									"<!--\n" +
									//"$(document).on(\"pageinit\", \"#p-top1\", function(e) {\n" +	//←　$(function(){の方が良いかも
									"$(function(){\n" +
									"  	// Geolocation APIのオプション設定\n" +
									"  	var geolocationOptions = {\n" +
									"    	\"enableHighAccuracy\" : true, // 高精度位置情報の取得\n" +
									"    	\"maximumAge\" : 0, // キャッシュの無効化\n" +
									"    	\"timeout\" : 30000 // タイムアウトは30秒\n" +
									"  	};\n" +
									"    navigator.geolocation.getCurrentPosition(function(pos) {\n" +
									"      	// 経度、緯度を取得 //\n" +
									"		document.getElementsByName('SSQL_insert"+Func.insertCount+"_words"+(insertWordCount+1)+"')[0].value=pos.coords.latitude+\",\"+pos.coords.longitude;\n" +
									"    }, function(e) {\n" +
									"		gpsInfo = \"\";\n" +
									"    }, geolocationOptions);\n" +
									"});\n" +
									"// -->\n" +
									"</script>\n";
				}

				statement += 
						"    <"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
								""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+"\n" +
								"EOF;\n" +
								echo +
								"		echo <<<EOF\n" +
								""+((!textareaFlg[i])?("\">"):("</textarea>"))+"\n";
				update_statement += 
						"    <"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
								""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+"" +
								echo2 +
								""+((!textareaFlg[i])?("\">"):("</textarea>"))+"\n";
				if(!noinsertFlg[i]){
					statement += 
							"    <input type=\"hidden\" id=\"SSQL_insert"+Func.insertCount+"_words"+(++insertWordCount)+"\" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" value=\"\n" +
									"EOF;\n" +
									echo +
									"		echo <<<EOF\n" +
									"\">\n";
					update_statement += 
							"    <input type=\"hidden\" id=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" name=\"SSQL_insert"+Func.insertCount+"_words"+(insertWordCount)+"\" value=\"" +
									echo2 +
									"\">\n";
				}
			}
		}
		if(buttonSubmit.equals("")){
			if(buttonName.isEmpty()){
				statement += 
						"    <input type=\"submit\" class=\"btn btn-default\" value=\""+( (!update)? ("登録"):("更新") )+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" name=\"SSQL_insert"+Func.insertCount+"\" id=\"SSQL_insert"+Func.insertCount+"\" data-icon=\"insert\" data-mini=\"false\" data-inline=\"false\">\n";
			}else{
				statement += 
						"    <input type=\"submit\" class=\"btn btn-default\"  value=\""+buttonName+"\" name=\"SSQL_insert"+Func.insertCount+"\" id=\"SSQL_insert"+Func.insertCount+"\" data-mini=\"false\" data-inline=\"false\">\n";
			}
		}
		statement += 
				"</form>\n" +
						"\n";
		if(!noresult){
			statement += 
					"<div id=\"SSQL_insert"+Func.insertCount+"_result\" data-role=\"none\"><!-- SSQL Insert"+Func.insertCount+" Result"+Func.insertCount+" --></div>\n" +
							"\n" +
							//added by goto 20141128 form confirm  start
							"<div id=\"SSQL_insert"+Func.insertCount+"_confirmButton\">\n" +
							"	<input type=\"button\" class=\"btn btn-default\" value=\"戻る\" data-icon=\"arrow-l\" data-inline=\"true\" onClick=\"javascript:SSQL_insert"+Func.insertCount+"_showButton(0);\" >\n" +
							"	<input type=\"button\" class=\"btn btn-default\" value=\"登録\" data-icon=\"insert\" data-inline=\"true\" data-theme=\"a\" onClick=\"javascript:SSQL_insert"+Func.insertCount+"();\" >\n" +
							"</div>\n" +
							"\n" +
							//added by goto 20141128 form confirm  end
							"<br>\n";
		}
		statement += 
				"</div>\n\n";
		//getGPSinfo()
		statement += gps_js;
		statement += 
				"<!-- SSQL Insert"+Func.insertCount+" FORM end -->\n" +
						"\n" +
						"<!-- SSQL Insert"+Func.insertCount+" JS start -->\n" +
						"<script type=\"text/javascript\">\n" +
						//added by goto 20141128 form confirm  start
						"SSQL_insert"+Func.insertCount+"_showButton(0);\n" +
						"function SSQL_insert"+Func.insertCount+"_showButton(num){\n" +
						"	$(function () {\n" +
						"		if(num != 1){\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"panel form\").show();\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"_registButton\").show();\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"_confirmButton\").hide();\n" +
						"			document.getElementById(\"SSQL_insert"+Func.insertCount+"_result\").innerHTML = '';\n" +
						"		}else{\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"panel form\").hide();\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"_registButton\").hide();\n" +
						"			$(\"#SSQL_insert"+Func.insertCount+"_confirmButton\").show();\n" +
						"		}\n" +
						"	});\n" +
						"}\n" +
						//added by goto 20141128 form confirm  end
						"function SSQL_insert"+Func.insertCount+"_echo(str){\n";
		if(!noresult){
			statement += 
					"	var textArea = document.getElementById(\"SSQL_insert"+Func.insertCount+"_result\");\n" +
							"	textArea.innerHTML = str;\n" +
							"	$(\"#SSQL_insert"+Func.insertCount+"_confirmButton\").hide();\n";	//added by goto 20141128 form confirm
		}
		if(!noreset){
			statement += 
					"	if(str.indexOf(\"completed\") !== -1) {\n" +
							"		$(\"#SSQL_insert"+Func.insertCount+"panel form\")[0].reset();\n";
			//added by goto 170606 for update(file/image)
			if(!Mobile_HTML5_form.formTypeFileResetID.isEmpty()){
				for(String id : Mobile_HTML5_form.formTypeFileResetID)
					statement += "		$(\"#"+id+"\").empty();\n";
			}
			statement +=
					"	}\n";
		}
		if(reloadAfterInsert){
			statement += 
					"	setInterval(function(){\n" +
							"		location.reload();\n" +
							"	}, "+(reloadAfterInsertTime*1000)+");\n";
		}else{
			//3秒後に結果をクリア
			statement += 
					"	$(function(){\n" +
							"		setTimeout(function(){\n" +
							"			document.getElementById(\"SSQL_insert"+Func.insertCount+"_result\").innerHTML = '';\n" +
							"			SSQL_insert"+Func.insertCount+"_showButton(0);\n" +	//added by goto 20141128 form confirm
							"		},3000);\n" +
							"	});\n";
		}
		statement += 
				"}\n" +
						"$(function(){\n" +
						"	//validation\n" +
						"	$(\"#SSQL_insert"+Func.insertCount+"panel form\").validate({\n" +
						"	 	errorPlacement: function(error, element) {\n" +
						"        	error.appendTo(element.parent().parent().after());\n" +
						"    	},\n" +
						"		submitHandler: function(form) {\n" +
						"		 	SSQL_insert"+Func.insertCount+"_confirm();\n" +	//added by goto 20141128 form confirm
						"		    return false;\n" +
						"		}\n" +
						"	});\n" +
						"})\n" +
						//added by goto 20141128 form confirm  start
						"function SSQL_insert"+Func.insertCount+"_confirm(){\n" +
						"	//confirm form\n" +
						//"	var SSQL_insert"+insertCount+"_formVal = $(\"#SSQL_insert"+insertCount+"panel form\").serializeArray();\n" +
						//						"	var s = \"<div style='background:#FEF9F9;'>\";\n" +
						"	var s = \"<div>\";\n" +
						"	s += \"<span style='line-height:40px; font-weight:800;'>下記の内容で登録します。</span><br>\";\n" +
						"	s += \"<table style='width:100%; font-weight:500; line-height:30px;'>\";\n";
		for(int i=0; i<col_num; i++){
			String s = "";
			if(hiddenFlg[i]) continue;
			if(!checkbox_array[i].equals("")){
				s = checkbox_array[i];
				statement += 
						"	var "+s+"=[];\n" +
								"	$('[name=\""+s+"[]\"]:checked').each(function(){\n" +
								"		//"+s+".push($(Func).val());\n" +
								"		"+s+".push(' '+$('label[for=\"'+Func.id+'\"]').text().trim()+' ');\n" +
								"	});\n";
				checkboxFlg_array += "TRUE,";
			}else if(!radioButton_array[i].equals("")){
				s = radioButton_array[i];
				statement += "	var "+s+"=$(\"label[for='\"+$(\"input:radio[name='"+s+"']:checked\").attr(\"id\")+\"']\").text().trim();\n";
				checkboxFlg_array += "FALSE,";
			}else if(!selectbox_array[i].equals("")){
				s = selectbox_array[i];
				statement += "	var "+s+"=$('[name=\""+s+"\"] option:selected').text().trim();\n";
				checkboxFlg_array += "FALSE,";
			}else{
				//s = "SSQL_insert"+insertCount+"_formVal["+i+"].value";
				s = "SSQL_insert"+Func.insertCount+"_words"+(i+1);
				statement += "	var "+s+"=$('#"+s+"').val();\n";
				checkboxFlg_array += "FALSE,";
			}
			statement += 
					"	s += \"<tr><td style='width:40%; text-align:center;'>"+s_name_array[i]+"</td>" +
							"<td>「<span style='color:red;'>\"+"+s+"+\"</span>」</td></tr>\";\n";
		}
		if(checkboxFlg_array.contains(","))	
			checkboxFlg_array = checkboxFlg_array.substring(0, checkboxFlg_array.lastIndexOf(","));
		statement += 
				"	document.getElementById(\"SSQL_insert"+Func.insertCount+"_result\").innerHTML = s+\"</table></div>\";\n" +
						"	SSQL_insert"+Func.insertCount+"_showButton(1);\n" +
						"}\n" +
						//added by goto 20141128 form confirm  end
						"function SSQL_insert"+Func.insertCount+"(){\n" +
						//"	//ajax: PHPへ値を渡して実行\n" +
						"	$.ajax({\n" +
						"		type: \"POST\",\n" +
						"		url: \""+new File(formPHPfileName).getName()+"\",\n" +
						getFormFileUploadHTML2("#SSQL_insert"+Func.insertCount) +
						"		dataType: \"json\",\n" +
						"		beforeSend: function(xhr, settings) {\n" +
						"			$('#SSQL_insert"+Func.insertCount+"').attr('disabled', true);\n" +
						"		},\n" +
						"		complete: function(xhr, textStatus) {\n" +
						"			$('#SSQL_insert"+Func.insertCount+"').attr('disabled', false);\n" +
						"		},\n" +
						"		success: function(data, textStatus){\n" +
						"			if (data.result != \"\") {\n" +
						"				SSQL_insert"+Func.insertCount+"_echo(data.result);\n" +
						"			}\n" +
						"		},\n" +
						"		error: function(XMLHttpRequest, textStatus, errorThrown) {\n" +
						"			SSQL_insert"+Func.insertCount+"_echo(textStatus+\"<br>\"+errorThrown);\n" +
						"		}\n" +
						"	});\n" +
						"}\n" +
						"</script>\n" +
						"<!-- SSQL Insert"+Func.insertCount+" JS end -->\n" +
						"<!-- SSQL Insert"+Func.insertCount+" end -->\n";

		//php
		String pKeyWhere = "";
		if(update){
			if(!update_where.isEmpty()){
				pKeyWhere += " and ";
			}else{
				pKeyWhere += " WHERE ";
			}

			if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
				pKeyWhere += pKey+"='\".$_POST['SSQL_insert"+Func.insertCount+"_pkey'].\"'";
			} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
				pKeyWhere += pKey+"='\".$_POST['SSQL_insert"+Func.insertCount+"_pkey'].\"'";
			}
		}
		php +=	Mobile_HTML5.getSessionStartString() +
				"<?php\n" +
				"    $ret = array();\n" +
				"    $ret['result'] = \"\";\n" +
				"    \n" +
				//"    //ユーザ定義\n" +
				((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
				"    $insert_col = \""+insert_col+"\";\n" +
				getFormFileUploadPHP0(uploadFilePath);
		if(update || (insert_update && !update_where.isEmpty()) ){
			//form()=insert_update() with where, update()
			php += "    $update_col_array = array("+update_col_array+");\n" +
					"    $update_where = \""+ update_where + pKeyWhere +"\";\n";
		}
		php +=
				"    $notnullFlg = array("+notnullFlg_array+");\n" +
						"    $checkboxFlg = array("+checkboxFlg_array+");\n" +
						"    $col_num = "+(col_num - noinsert_count)+";\n" +                          //カラム数(Java側で指定)\n" +
						"    $table = '"+from+"';\n" +
						"\n" +
						"	$insert_str = \"notnull\";\n" +
						"	for($k=1; $k<=$col_num; $k++){\n" +
						getFormFileUploadPHP1(Func) +
						"    	$var[$k] = str_replace(array(\"\\r\\n\",\"\\r\",\"\\n\"), '<br>', $var[$k]);	//改行コードを<br>へ\n" +
						//"    	//$var[$k] = mb_convert_encoding($var[$k], 'UTF-8', 'auto');					//エンコードをUTF-8へ PHP環境によってはうまく動かない？\n" +
						"    	if($notnullFlg[$k-1]){\n" +
						"    		if(trim($var[$k]) == \"\")	$insert_str = \"\";\n" +
						"    	}\n";
		for(int i=0; i<col_num; i++){
			if(!$time_array[i].equals(""))
				php += "		if($k=="+i+")	$var[$k] = "+$time_array[i]+";\n";	//現在時刻
		}
		php +=	
				"    }\n" +
						"\n" +
						"	$b = \"\";\n" +
						"	if($insert_str == \"\"){\n" +
						"        $b = '<font color=\"red\">Please check the value.</font>';\n" +
						"	}else{\n";
		if(!update && (insert_update && update_where.isEmpty())){
			//insert(), form()=insert_update() with no where
			if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
				php +=
						"		$insert_str = \"\";\n" +
								"		for($k=1; $k<=$col_num; $k++){\n" +
								"			if($k==1)	$insert_str .= \"'\".$var[$k].\"'\";\n" +
								"			else		$insert_str .= \",'\".$var[$k].\"'\";\n" +
								"		}\n" +
								"		//DBへ登録\n" +
								"        $insert_db"+Func.insertCount+" = new SQLite3($sqlite3_DB);\n" +
								"        $insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES (\".$insert_str.\")\";\n" +
								"        \n" +
								"        try{\n" +
								"			$result2 = $insert_db"+Func.insertCount+"->exec($insert_sql);\n" +
								"		 	$b = \"Registration completed.\";\n" +
								"		 	//$b = $insert_sql;\n" +
								"        }catch(Exception $e){\n" +
								"       		$b = '<font color=red>Insert failed.</font>';	//登録失敗\n" +
								"        }\n" +
								"        unset($insert_db"+Func.insertCount+");\n";
			} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
				String pg_prepare_array = "";
				for(int i=1; i<=(col_num - noinsert_count); i++)
					pg_prepare_array += "$"+i+",";
				pg_prepare_array = pg_prepare_array.substring(0, pg_prepare_array.length()-1);
				//$query = "INSERT INTO member (login_name,pwd) VALUES($1, $2)”;
				//$result = pg_prepare ($conn, “my_query”, $query);
				//$result = pg_execute ($conn, “my_query”,array($login_name,$md5pwd));
				php +=
						"		//DBへ登録\n" +
								"        $insert_db"+Func.insertCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
								"        $insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES ("+pg_prepare_array+")\";\n" +
								"        \n" +
								"        try{\n" +
								"			$result2 = pg_prepare($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $insert_sql);\n" +
								"			$result2 = pg_execute($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $var);\n" +
								"		 	$b = \"Registration completed.\";\n" +
								"		 	//$b = $insert_sql;\n" +
								"        }catch(Exception $e){\n" +
								"       		$b = '<font color=red>Insert failed.</font>';	//登録失敗\n" +
								"        }\n" +
								"        pg_close($insert_db"+Func.insertCount+");\n";
			}
		}else{
			//form()=insert_update() with where, update()
			if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
				php +=
						"		$insert_db"+Func.insertCount+" = new SQLite3($sqlite3_DB);\n" +
								"		try{\n" +
								"			//データが存在しているかチェック\n" +
								"			$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
								"			$result2 = $insert_db"+Func.insertCount+"->query($select_sql);\n" +
								"			$j = 0;\n" +
								"			while($row = $result2->fetchArray()){\n" +
								"			    $j++;\n" +
								"			}\n" +
								"			\n" +
								"			if($j>0){\n" +
								"				//更新(update)\n" +
								"				$update_str = \"\";\n" +
								"				for($k=1; $k<=$col_num; $k++){\n" +
								"					if($k==1)	$update_str .= $update_col_array[$k-1].\"='\".$var[$k].\"'\";\n" +
								"					else		$update_str .= \",\".$update_col_array[$k-1].\"='\".$var[$k].\"'\";\n" +
								"				}\n" +
								"				\n" +
								"				$update_sql = \"UPDATE \".$table.\" SET \".$update_str.\" \".$update_where;\n" +
								"				$result2 = $insert_db"+Func.insertCount+"->exec($update_sql);\n" +
								"				//echo '変更された行の数: ', $db->changes();\n" +
								"				$b = \"Update completed.\";\n" +
								"			}else{\n" +
								"				//新規登録(insert)\n" +
								"				$insert_str = \"\";\n" +
								"				for($k=1; $k<=$col_num; $k++){\n" +
								"					if($k==1)	$insert_str .= \"'\".$var[$k].\"'\";\n" +
								"					else		$insert_str .= \",'\".$var[$k].\"'\";\n" +
								"				}\n" +
								"				\n" +
								"				$insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES (\".$insert_str.\")\";\n" +
								"				$result2 = $insert_db"+Func.insertCount+"->exec($insert_sql);\n" +
								"				$b = \"Registration completed.\";\n" +
								"			}\n" +
								"        }catch(Exception $e){\n" +
								"       		$b = '<font color=red>Update failed.</font>';	//更新失敗\n" +
								"        }\n" +
								"        unset($insert_db"+Func.insertCount+");\n";
			} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
				String pg_prepare_array = "";
				for(int i=1; i<=(col_num - noinsert_count); i++)
					pg_prepare_array += "$"+i+",";
				pg_prepare_array = pg_prepare_array.substring(0, pg_prepare_array.length()-1);

				php +=
						"		$insert_db"+Func.insertCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
								"		try{\n" +
								"			//データが存在しているかチェック\n" +
								"			$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
								"			$result2 = pg_query($insert_db"+Func.insertCount+", $select_sql);\n" +						//TODO $update_where
								"			$j = pg_num_rows($result2);\n" +
								"			\n" +
								"			if($j>0){\n" +
								"				//更新(update)\n" +
								"				$update_str = \"\";\n" +
								"				for($k=1; $k<=$col_num; $k++){\n" +
								"					if($k==1)	$update_str .= $update_col_array[$k-1].\"=$\".$k;\n" +
								"					else		$update_str .= \",\".$update_col_array[$k-1].\"=$\".$k;\n" +
								"				}\n" +
								"				\n" +
								"				$update_sql = \"UPDATE \".$table.\" SET \".$update_str.\" \".$update_where;\n" +	//TODO $update_where
								"				$result2 = pg_prepare($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $update_sql);\n" +
								"				$result2 = pg_execute($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $var);\n" +
								"				$b = \"Update completed.\";\n" +
								"			}else{\n" +
								"				//新規登録(insert)\n" +
								"				$insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES ("+pg_prepare_array+")\";\n" +
								"				$result2 = pg_prepare($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $insert_sql);\n" +
								"				$result2 = pg_execute($insert_db"+Func.insertCount+", \"ssql_insert_"+Func.insertCount+"\", $var);\n" +
								"				$b = \"Registration completed.\";\n" +
								"			}\n" +
								"        }catch(Exception $e){\n" +
								"       		$b = '<font color=red>Update failed.</font>';	//更新失敗\n" +
								"        }\n" +
								"        pg_close($insert_db"+Func.insertCount+");\n";
			}
		}
		php +=
				"	}\n" +
						"	$ret['result'] = $b;\n" +
						"	//header(\"Content-Type: application/json; charset=utf-8\");\n" +
						"	echo json_encode($ret);\n" +
						"\n" +
						getFormFileUploadPHP2() +
						//"//XSS対策\n" +
						"function checkHTMLsc($str){\n" +
						"	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
						"}\n" +
						"?>\n";
		//End of php


		if(update){
			String getUpdateFormPJPFileName = html_env.getFileName2()+"_SSQLgetUpdateForm_"+Func.insertCount+".php";
			String getUpdateFormPHP = getSSQLgetUpdateformPHP(DBMS, Func.insertCount, DB, insert_col, update_where, from, pKey, buttonName, update_statement);
			Mobile_HTML5.createFile(html_env, getUpdateFormPJPFileName, getUpdateFormPHP);//PHPファイルの作成

			statement = getSSQLgetUpdateformJS(Func.insertCount, getUpdateFormPJPFileName)+"\n";
			statement += getSSQLUpdateformHTML(Func.insertCount, formPHPfileName, title);
		}

		Mobile_HTML5.createFile(html_env, formPHPfileName, php);//PHPファイルの作成
		Func.insertCount++;
		formFileUpload = false;
		return statement;
	}
	//insert end

	//getFormFileUploadHTML1
	private static String getFormFileUploadHTML1(){
		if(formFileUpload){
			return " enctype=\"multipart/form-data\" data-ajax=\"false\"";
		}
		return "";
	}
	//getFormFileUploadHTML2
	private static String getFormFileUploadHTML2(String label){
		if(formFileUpload){
			return  "		contentType: false,\n" +
					"		processData: false,\n" +
					"		data: new FormData($(\""+label+"panel form\")[0]),\n";
		}
		return "		data: $(\""+label+"panel form\").serializeArray(),\n";
	}
	//getFormFileUploadPHP1
	private static String getFormFileUploadPHP0(String[] dirS){
		if(formFileUpload){
			String dir = "";
			for(int i=0; i<dirS.length; i++){
				if(dirS[i].isEmpty()){
					dir += "'',";
				}else{
					dir += "'"+dirS[i]+"',";
				}
			}
			dir = dir.substring(0,dir.length()-1);
			//Log.e(dir);
			return "    $fileDir = array("+dir+");\n";
		}
		return "";
	}
	//getFormFileUploadPHP1
	private static String getFormFileUploadPHP1(Mobile_HTML5Function Func){
		String r = 
				"		$s = '';\n" +
						"		if(!$checkboxFlg[$k-1]){\n" +
						"			$s = $_POST['SSQL_insert"+Func.insertCount+"_words'.$k];\n" +
						"		}else{\n" +
						"			if(isset($_POST['SSQL_insert"+Func.insertCount+"_words'.$k]))\n" +
						"				foreach($_POST['SSQL_insert"+Func.insertCount+"_words'.$k] as $val)\n" +
						"        			$s .= $val.',';\n" +
						"			if(strstr($s, ','))	$s = substr($s, 0, -1);\n" +
						"		}\n";
		if(formFileUpload){
			return  r + 
					"		if(empty($fileDir[$k-1])){\n" +
					"			$var[$k] = checkHTMLsc($s);\n" +
					"    	}else{\n" +
					"    		//file\n" +
					"    		$dir = $fileDir[$k-1];\n" +
					"    		$dir = rtrim($dir, DIRECTORY_SEPARATOR).DIRECTORY_SEPARATOR;\n" +
					"    		$file1 = $_FILES['SSQL_insert"+Func.insertCount+"_words'.$k]['tmp_name'];\n" +
					"    		$file2 = $_FILES['SSQL_insert"+Func.insertCount+"_words'.$k]['name'];\n" +
					"    		$filename = $dir.$file2;\n" +
					"    		$var[$k] = fileUpload($dir, $file1, $file2, $filename);\n" +
					"    		$var[$k] = checkHTMLsc($var[$k]);\n" +
					"    		if(empty($var[$k])){\n" +
					"    			$var[$k] = checkHTMLsc($_POST['SSQL_insert"+Func.insertCount+"_words'.$k.'_hidden']);\n" +
					"    		}\n" +
					"    	}\n\n";
		}
		return r + "		$var[$k] = checkHTMLsc($s);\n";
	}
	//getFormFileUploadPHP2
	private static String getFormFileUploadPHP2(){
		if(formFileUpload){
			return  "//file upload\n" +
					"function fileUpload($dir, $file1, $file2, $filename){\n" +
					"  if(is_uploaded_file($file1)){\n" +
					//"    //if(exif_imagetype($file1)){   //image file or not\n" +
					"	  while(file_exists($filename)){\n" +
					"	    //change filename\n" +
					"	    $filename = $dir.date('YmdHis').'_'.mt_rand().'_'.$file2;\n" +
					"	  }\n" +
					"\n" +
					//"	  //$filename = mb_convert_encoding($filename, \"UTF-8\", \"AUTO\");	<- サーバでエラーが出る\n" +
					"	  if(move_uploaded_file($file1, $filename)){\n" +
					"	    chmod($filename, 0644);\n" +
					"		$filename = pathinfo($filename, PATHINFO_BASENAME); \n"+
					"	    return $filename;\n" +
					"	  }\n" +
					//"    //}\n" +
					"  }\n" +
					"  return \"\";\n" +
					"}\n\n";
		}
		return "";
	}

	/* */
	public static String updateFormJS = "";	//not use?
	private static String getSSQLgetUpdateformPHP(String DBMS, int num, String DB, String insert_col, String update_where, String from, String pKey, String buttonName, String update_statement) {//taji changed to static
		String HOST = "", USER = "", PASSWD = "";
		if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			HOST = GlobalEnv.gethost();
			USER = GlobalEnv.getusername();
			PASSWD = GlobalEnv.getpassword();
		}

		String s = "";
		//update_statement = update_statement.replace("'", "\\\'");//.replace("\n", "");
		s += 	Mobile_HTML5.getSessionStartString() +
				"<?php\n" +
				"    $ret = array();\n" +
				"    $ret['result'] = \"\";\n" +
				"    \n" +
				//"    //ユーザ定義\n" +
				((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
				"    $insert_col = \""+pKey+","+insert_col+"\";\n" +
				"    $update_where = \""+update_where+"\";\n" +
				"    $table = '"+from+"';\n" +
				"\n" +
				"	$b = \"\";\n";
		if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
			s +=
					"	$insert_db"+num+" = new SQLite3($sqlite3_DB);\n" +
							"	try{\n" +
							"		$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
							"		$result2 = $insert_db"+num+"->query($select_sql);\n" +
							"		$j = 0;\n" +
							"		while($row = $result2->fetchArray()){\n";
		} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
			s +=
					"	$insert_db"+num+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
							"	try{\n" +
							"		$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
							"		$result2 = pg_query($insert_db"+num+", $select_sql);\n" +
							"		$j = 0;\n" +
							"		while($row = pg_fetch_assoc($result2)){\n";
		}
		s +=
				"			$b .= '<div id=\"SSQL_insert"+num+"_'.$j.'panel\" style=\"\" data-role=\"none\">';\n" +
						"			$b .= '<form method=\"post\" action=\"\" target=\"dummy_ifr\">';\n" +
						"			\n" +
						"			$b .= '<input type=\"hidden\" disabled=\"disabled\" value=\"'.$row['"+pKey+"'].'\">';	//New\n" +
						"			$b .= '<input type=\"hidden\" id=\"SSQL_insert"+num+"_pkey\" name=\"SSQL_insert"+num+"_pkey\" value=\"'.$row['"+pKey+"'].'\">';	//New\n" +
						"			\n" +
						"			$b .= '"+update_statement+"';\n" +
						"			\n" +
						"			$b .= '<input type=\"submit\" class=\"btn btn-default\"  value=\""+((buttonName.isEmpty())? "更新":buttonName )+"\" name=\"SSQL_insert"+num+"_'.$j.'\" id=\"SSQL_insert"+num+"_'.$j.'\" onclick=\"SSQL_Update"+num+"_vali(\\'SSQL_insert"+num+"_'.$j.'\\');\" data-mini=\"false\" data-inline=\"false\">';\n" +
						"			\n" +
						"			$b .= '</form>';\n" +
						"			$b .= '</div>';\n" +
						"			$b .= '<div id=\"SSQL_insert"+num+"_'.$j.'_result\"></div>';\n" +
						"			$b .= '<hr size=\"1\" color=\"silver\" width=\"99%\">';\n" +
						"		    $j++;\n" +
						"		}\n" +
						"    }catch(Exception $e){\n" +
						"   		$b = '<font color=red>Get Update Form failed.</font>';\n" +
						"    }\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("    unset($insert_db"+num+");\n"):"") +
						((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("    pg_close($insert_db"+num+");\n"):"") +
						"	\n" +
						"	$ret['result'] = $b;\n" +
						"	//header(\"Content-Type: application/json; charset=utf-8\");\n" +
						"	echo json_encode($ret);\n" +
						"\n" +
						"function sr($str){\n" +
						"	return str_replace('<br>', PHP_EOL, $str);	//<br> -> PHP_EOL\n" +
						"}\n" +
						"?>\n";
		return s;
	}
	private static String getSSQLgetUpdateformJS(int num, String phpFileName) {
		String s = "";
		//s += "<script>\n" +
		s +=    "<!-- SSQL get Update Form"+num+" start -->\n" +
				"<script type=\"text/javascript\">\n" +
				"SSQL_getUpdateForm"+num+"();\n" +
				"function SSQL_getUpdateForm"+num+"(){\n" +
				"	$.ajax({\n" +
				"		type: \"POST\",\n" +
				"		url: \""+new File(phpFileName).getName()+"\",\n" +
				"		dataType: \"json\",\n" +
				"		success: function(data, textStatus){\n" +
				"			if (data.result != \"\") {\n" +
				"				SSQL_echo(\"SSQL_UpdateForm"+num+"\", data.result, false);\n" +
				"			}\n" +
				"		},\n" +
				"		error: function(XMLHttpRequest, textStatus, errorThrown) {\n" +
				"			SSQL_echo(\"SSQL_UpdateForm"+num+"\", textStatus+\"<br>\"+errorThrown, false);\n" +
				"		}\n" +
				"	});\n" +
				"}\n" +
				"function SSQL_echo(id, str, clear){\n" +
				"	$(\"#\"+id).html(str).trigger(\"create\");\n" +
				"	if(clear){\n" +
				"		$(function(){\n" +
				"			setTimeout(function(){\n" +
				"				document.getElementById(id).innerHTML = '';\n" +
				"			},3000);\n" +
				"		});\n" +
				"	}\n" +
				"}\n" +
				"</script>\n" +
				"<!-- SSQL get Update Form"+num+" end -->\n";
		return s;
	}
	private static String getSSQLUpdateformHTML(int num, String phpFileName, String title) {
		String s = "";
		if(!title.isEmpty()){
			s += 
					"<hr>\n<div style=\"font-size:30;\" id=\"SSQL_insertTitle"+num+"\">"+title+"</div>\n<hr>\n" +
							"<br>\n";
		}
		s += "<!-- SSQL Update"+num+" start -->\n" +
				//"\n" +
				"<div id=\"SSQL_UpdateForm"+num+"\"></div>\n" +
				//"\n" +
				"<!-- SSQL Update"+num+" JS start -->\n" +
				"<script type=\"text/javascript\">\n" +
				"function SSQL_Update"+num+"_vali(id){\n" +
				"	$(function(){\n" +
				"		$(\"#\"+id+\"panel form\").validate({\n" +
				"		 	errorPlacement: function(error, element) {\n" +
				"	        	error.appendTo(element.parent().parent().after());\n" +
				"	    	},\n" +
				"			submitHandler: function(form) {\n" +
				"			 	SSQL_Update"+num+"(id);\n" +
				"			    return false;\n" +
				"			}\n" +
				"		});\n" +
				"	})\n" +
				"}\n" +
				"function SSQL_Update"+num+"(id){\n" +
				"	$.ajax({\n" +
				"		type: \"POST\",\n" +
				"		url: \""+new File(phpFileName).getName()+"\",\n" +
				//"		data: $(\"#\"+id+\"panel form\").serializeArray(),\n" +
				getFormFileUploadHTML2("#\"+id+\"") +
				"		dataType: \"json\",\n" +
				"        beforeSend: function(xhr, settings) {\n" +
				"            $('#'+id).attr('disabled', true);\n" +
				"        },\n" +
				"        complete: function(xhr, textStatus) {\n" +
				"            $('#'+id).attr('disabled', false);\n" +
				"        },\n" +
				"		success: function(data, textStatus){\n" +
				"			if (data.result != \"\") {\n" +
				"				SSQL_echo(\"\"+id+\"_result\", data.result, true);\n" +
				"			}\n" +
				"		},\n" +
				"		error: function(XMLHttpRequest, textStatus, errorThrown) {\n" +
				"			SSQL_echo(\"\"+id+\"_result\", textStatus+\"<br>\"+errorThrown, true);\n" +
				"		}\n" +
				"	});\n" +
				"}\n" +
				"</script>\n" +
				"<!-- SSQL Update"+num+" JS end -->\n" +
				"<!-- SSQL Update"+num+" end -->\n";
		return s;
	}





	//20131127 form
	//result start
	private static String Func_result() {
		int count = Mobile_HTML5_form.formCount;
		//if(!Mobile_HTML5.form)	count -= 1;
		String s =
				"\n" +
						"<div id=\"Form"+count+"_result\" data-role=\"none\"><!-- Form result --></div>\n" +
						"\n";
		//"<br>\n";
		//html_env.code.append(s);
		return s;
	}
	//result end


	//added by goto 20130531  "check"
	/*  1:check(type, 演算子(=,!=,<,<=,>,>=,...), answer, 正解ステートメント, 不正解ステートメント)  */
	/*  2:check(type, 識別子("yes|no"など), answer, 正解ステートメント, 不正解ステートメント)  */
	//check("form","=",answer,"正解","不正解")
	private static String Func_check(Mobile_HTML5Function Func) {
		String statement = "\n";
		String type = "";
		String operator = "";
		String ans = "";
		String correct = "";
		String incorrect = "";
		try{
			type = ((FuncArg) Func.getArg(0)).getStr().trim();
			operator = ((FuncArg) Func.getArg(1)).getStr();
			ans = ((FuncArg) Func.getArg(2)).getStr();
			correct = ((FuncArg) Func.getArg(3)).getStr();
			incorrect = ((FuncArg) Func.getArg(4)).getStr();
		}catch(Exception e){
			Log.info("<Warning> check関数の引数が不足しています。 ex. check(type, 演算子(=,!=,<,<=,>,>=,...)・識別子(\"yes|no\"など), answer, 正解ステートメント, 不正解ステートメント)");
			return "";
		}

		if(operator.trim().equals("="))	operator = "==";
		else if(operator.trim().equals(""))	operator = "==";

		//statement += "演算子: "+operator+"<br>";
		//statement += incorrect;

		//"<iframe name=\"dummy_ifr\" style=\"display:none;\"><!-- dummy for Form target --></iframe>\n";

		//type = 1
		if(type.equals("1") || type.equals("form")){
			statement += 
					"<!-- Check"+Func.checkCount+" start -->\n" +
							"<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n" +
							//"<form method=\"post\" action=\"\" target=\"check"+checkCount+"_ifr\">\n" +
							"    <input type=\"text\" name=\"check_word"+Func.checkCount+"\" placeholder=\"Check words\">\n" +
							"    <input type=\"submit\" class=\"btn btn-default\"  value=\"Check&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" name=\"check"+Func.checkCount+"\" id=\"check"+Func.checkCount+"\" data-icon=\"question\" data-mini=\"false\" data-inline=\"false\">\n" +
							"</form>\n" +
							//				"<iframe name=\"check"+checkCount+"_ifr\" style=\"display:none;\"></iframe>\n" +
							"\n" +
							"<div id=\"Check"+Func.checkCount+"_text0\" data-role=\"none\"><!-- 結果 --></div>\n" +
							"\n" +
							"<script type=\"text/javascript\">\n" +
							"function Check"+Func.checkCount+"_echo1(str){\n" +
							//				"  var textArea = document.getElementById(\"Check"+checkCount+"_text0\");\n" +
							//				"  textArea.innerHTML = str;\n" +
							"  $.dynamic_popup(str);\n" +
							"}\n" +
							"</script>\n" +
							"<!-- Check"+Func.checkCount+" end -->\n";

			Mobile_HTML5Env.PHP += 
					"<?php\n" +
							"//Check"+Func.checkCount+"\n" +
							//"else if($_POST['check"+checkCount+"'] || $_POST['check_word"+checkCount+"']){\n" +
							"if($_POST['check"+Func.checkCount+"'] || $_POST['check_word"+Func.checkCount+"']){\n" +
							"	if(trim($_POST['check_word"+Func.checkCount+"'])==\"\")\n" +
							"		check"+Func.checkCount+"_p1(\"<br><font color=red>値を入力してください。</font>\");\n" +
							"	else if($_POST['check_word"+Func.checkCount+"']"+operator+"\""+ans+"\")\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=goldenrod>&nbsp;&nbsp;"+correct+"&nbsp;&nbsp;</font></p>\");\n" +
							"	else\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=red>&nbsp;&nbsp;"+incorrect+"&nbsp;&nbsp;</font></p>\");\n" +
							"}\n" +
							"function check"+Func.checkCount+"_p1($str){\n" +
							"    echo '<script type=\"text/javascript\">window.parent.Check"+Func.checkCount+"_echo1(\"'.$str.'\");</script>';\n" +
							"}\n" +
							"?>\n";
			//		HTMLEnv.PHPpost += 
			//				"\n" +
			//						"//Check"+checkCount+"\n" +
			//						//"else if($_POST['check"+checkCount+"'] || $_POST['check_word"+checkCount+"']){\n" +
			//						"if($_POST['check"+checkCount+"'] || $_POST['check_word"+checkCount+"']){\n" +
			//						"	if($_POST['check_word"+checkCount+"']"+operator+"\""+ans+"\")\n" +
			//						"		check"+checkCount+"_p1(\""+correct+"\");\n" +
			//						"	else\n" +
			//						"		check"+checkCount+"_p1(\"<font color=red>"+incorrect+"</font>\");\n" +
			//						"}\n";
			//		HTMLEnv.PHPfunc +=
			//				"<?php\n" +
			//						"function check"+checkCount+"_p1($str){\n" +
			//						"    echo '<script type=\"text/javascript\">window.parent.Check"+checkCount+"_echo1(\"'.$str.'\");</script>';\n" +
			//						"}\n" +
			//						"?>\n";
		}
		//type = 2
		else if(type.equals("2") || type.equals("yesno") || type.equals("ox")){
			String yes = "yes";
			String no = "no";
			if(operator.contains("|")){
				yes = operator.substring(0,operator.indexOf("|")).trim();
				no = operator.substring(operator.indexOf("|")+1).trim();
			}
			//Log.i("y:"+yes+"	n:"+no);

			statement += 
					"<!-- Check"+Func.checkCount+" start -->\n" +
							"<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n" +
							"	<div class=\"ui-grid-a\">\n" +
							"		<div class=\"ui-block-a\">\n" +
							"    		<input type=\"submit\" class=\"btn btn-default\"  name=\"check"+Func.checkCount+"_yes\" value=\"YES\" data-theme=\"a\">\n" +
							"		</div>\n" +
							"		<div class=\"ui-block-b\">\n" +
							"    		<input type=\"submit\"  class=\"btn btn-default\" name=\"check"+Func.checkCount+"_no\" value=\"NO\" data-theme=\"a\">\n" +
							"		</div>\n" +
							"	</div>\n" +
							"</form>\n" +
							"\n" +
							//"<div id=\"Check"+checkCount+"_text0\" data-role=\"none\"><!-- 結果 --></div>\n" +
							//"\n" +
							"<script type=\"text/javascript\">\n" +
							"function Check"+Func.checkCount+"_echo1(str){\n" +
							"  $.dynamic_popup(str);\n" +
							"}\n" +
							"</script>\n" +
							"<!-- Check"+Func.checkCount+" end -->\n";

			Mobile_HTML5Env.PHP += 
					"<?php\n" +
							"//Check"+Func.checkCount+"\n" +
							"if($_POST['check"+Func.checkCount+"_yes'] || $_POST['check"+Func.checkCount+"_no']){\n" +
							"	$ans = '"+ans+"';\n" +
							"	if($_POST['check"+Func.checkCount+"_yes'] && $ans=='"+yes+"')\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=goldenrod>&nbsp;&nbsp;"+correct+"&nbsp;&nbsp;</font></p>\");\n" +
							"	else if($_POST['check"+Func.checkCount+"_no'] && $ans=='"+no+"')\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=goldenrod>&nbsp;&nbsp;"+correct+"&nbsp;&nbsp;</font></p>\");\n" +
							"	else\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=red>&nbsp;&nbsp;"+incorrect+"&nbsp;&nbsp;</font></p>\");\n" +
							"}\n" +
							"function check"+Func.checkCount+"_p1($str){\n" +
							"    echo '<script type=\"text/javascript\">window.parent.Check"+Func.checkCount+"_echo1(\"'.$str.'\");</script>';\n" +
							"}\n" +
							"?>\n";
		}
		//type = 3
		else if(type.equals("3") || type.equals("choose") || type.equals("choice")){
			//    		int columnNum = 1;
			//    		if(operator.contains("|")){
			//    			columnNum = operator.length() - operator.replaceAll("\\|","").length() + 1;
			//    			//Log.info(columnNum);
			//    		}
			String[] sbuf = new String[ operator.length() - operator.replaceAll("\\|","").length() + 1 ];
			String operator2 = operator + "|";
			int columnNum = 0;
			while(operator2.contains("|")){
				sbuf[columnNum] = operator2.substring(0, operator2.indexOf("|"));
				operator2 = operator2.substring(operator2.indexOf("|")+1);
				//Log.i("sbuf["+columnNum+"] = "+sbuf[columnNum]);
				columnNum++;
			}


			statement += 
					"<!-- Check"+Func.checkCount+" start -->\n" +
							"<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n" +
							"   <div data-role=\"controlgroup\">\n";
			//	    			"	<div data-role=\"fieldcontain\">\n" +
			//	    			"		<fieldset data-role=\"controlgroup\" style=\"width:100%;\">\n";
			for(int i=0;i<columnNum;i++){
				statement += 	
						"    		<input type=\"radio\" name=\"check"+Func.checkCount+"_choose\" id=\"check"+Func.checkCount+"_choose"+(i+1)+"\" value=\""+sbuf[i]+"\""+( (i<1)? (" checked=\"checked\""):("") )+">\n" +
								"    		<label for=\"check"+Func.checkCount+"_choose"+(i+1)+"\">"+sbuf[i]+"</label>\n";
				//"    		<input type=\"radio\" name=\"check"+checkCount+"_choose\" value=\""+sbuf[i]+"\""+( (i<1)? (" checked"):("") )+">"+sbuf[i]+"\n";
			}

			statement += 
					//"		</fieldset>\n" +
					"	</div>\n" +
					"   <input type=\"submit\" class=\"btn btn-default\"  value=\"Check&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" name=\"check"+Func.checkCount+"\" id=\"check"+Func.checkCount+"\" data-icon=\"question\" data-mini=\"false\" data-inline=\"false\">\n" +
					"</form>\n" +
					"\n" +
					//"<div id=\"Check"+checkCount+"_text0\" data-role=\"none\"><!-- 結果 --></div>\n" +
					//"\n" +
					"<script type=\"text/javascript\">\n" +
					"function Check"+Func.checkCount+"_echo1(str){\n" +
					"  $.dynamic_popup(str);\n" +
					"}\n" +
					"</script>\n" +
					"<!-- Check"+Func.checkCount+" end -->\n";

			Mobile_HTML5Env.PHP += 
					"<?php\n" +
							"//Check"+Func.checkCount+"\n" +
							"if($_POST['check"+Func.checkCount+"']){\n" +
							"	if($_POST['check"+Func.checkCount+"_choose']==\""+ans+"\")\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=goldenrod>&nbsp;&nbsp;"+correct+"&nbsp;&nbsp;</font></p>\");\n" +
							"	else\n" +
							"		check"+Func.checkCount+"_p1(\"<h2>結果</h2><br><p><font color=red>&nbsp;&nbsp;"+incorrect+"&nbsp;&nbsp;</font></p>\");\n" +
							"}\n" +
							"function check"+Func.checkCount+"_p1($str){\n" +
							"    echo '<script type=\"text/javascript\">window.parent.Check"+Func.checkCount+"_echo1(\"'.$str.'\");</script>';\n" +
							"}\n" +
							"?>\n";
		}


		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);

		Func.checkCount++;
		return statement;
	}
	//check end


	//added by goto 20130519 start  "moveto"
	/*  moveto(url, sec)  */
	/*  moveto(title, url, sec)  */
	private static String Func_moveto(Mobile_HTML5Function Func) {
		String statement = "\n";
		String url = "";
		String sec = "";
		String title = "";
		try{
			url = ((FuncArg) Func.getArg(0)).getStr();
			sec = ((FuncArg) Func.getArg(1)).getStr();

			try{						//引数3つ　→　入れ替える
				String buf = sec;
				sec = ((FuncArg) Func.getArg(2)).getStr();
				title = url;
				url = buf;
			}catch(Exception e){ }		//引数2つ
		}catch(Exception e){
			Log.info("<Warning> moveto関数の引数が不足しています。 ex. moveto(url, sec) or moveto(title, url, sec)");
			return "";
		}

		//movetoFlg  下記は、header()内でappendされる
		//<meta http-equiv="refresh" content="3; URL=http://ssql.db.ics.keio.ac.jp/mdemo/list.html">
		Func.movetoFlg += "<meta http-equiv=\"refresh\" content=\""+sec+"; URL="+url+"\">";

		//3秒後にDEMO listのページへ移動します。<br>
		//自動的に移動しない場合は、<a href="http://ssql.db.ics.keio.ac.jp/mdemo/list.html" target="_self">こちら</a>をクリックしてください。
		statement += sec+"秒後に"+((!title.equals(""))? (title+"へ"):(""))+"移動します。<br>\n";
		statement += "自動的に移動しない場合は、<a href=\""+url+"\" target=\"_self\">こちら</a>をクリックしてください。\n";

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}
	//added by goto 20130519 end


	//added by goto 20130603 start  "$session"
	/*  $session("SESSION関数(第3引数)でセッション変数へ格納した属性")  */
	/*  $session("name")など  */
	private static String Func_$session(Mobile_HTML5Function Func) {
		if(!Start_Parse.sessionFlag){
			Log.info("<Warning> $session関数は、SESSION()使用時のみ使用可能です。");
			return "";
		}else{
			String statement = "\n";
			String attribute = "";
			try{
				if(Func.ArgstoString().contains("\""))
					// $session("id")
					attribute = ((FuncArg) Func.getArg(0)).getStr();
				else
					// $session(id)
					attribute = Func.getArg(0).toString().replace("[", "").replace("]", "").trim();
			}catch(Exception e){
				Log.info("<Warning> $session関数の引数が不足しています。 ex. $session(\"name\")");
				return "";
			}
			statement += "EOF;\n" +
					"		echo $_SESSION['"+attribute+"'];\n" +
					"		echo <<<EOF\n";
			//	    	// 各引数毎に処理した結果をHTMLに書きこむ
			//	    	html_env.code.append(statement);
			return statement;
		}
	}
	//$session end

	//added by goto 20130607 start  "time,date"
	/*  time(),date(),time("Y-m-d")など  */
	private static String Func_time(Mobile_HTML5Function Func) {
		String statement = "\n";
		String format = "";
		try{
			format = ((FuncArg) Func.getArg(0)).getStr();
		}catch(Exception e){ }
		statement += "EOF;\n" +
				"		echo date(\""+( (format.equals(""))? ("Y/m/d(D) H:i:s"):(format) )+"\");\n" +		//第二引数のデフォルト値:time()		//"		echo date(\"Y/m/d(D) H:i:s\", time());\n" +
				"		echo <<<EOF\n";
		//		// 各引数毎に処理した結果をHTMLに書きこむ
		//		html_env.code.append(statement);
		return statement;
	}
	//time end

	//added by goto 20130717  "map"
	/*  map(geolocation, zoom, icon)  */
	/*  search_map(zoom, icon)  */
	/*  geolocation: 住所(address) or 緯度,経度(latitude,longitude)  */
	private static String Func_map(boolean searchFlg, Mobile_HTML5Function Func) {
		String statement = "\n";
		String geolocation = "";
		String zoom = "";
		String icon = "";
		try{
			if(!searchFlg){
				//map()
				geolocation = ((FuncArg) Func.getArg(0)).getStr().trim();
				try{
					zoom = ((FuncArg) Func.getArg(1)).getStr().trim();
					try{
						icon = ((FuncArg) Func.getArg(2)).getStr().trim();
					}catch(Exception e){ }
				}catch(Exception e){ }
			}else{
				//search_map()
				zoom = ((FuncArg) Func.getArg(0)).getStr().trim();
				try{
					icon = ((FuncArg) Func.getArg(1)).getStr().trim();
				}catch(Exception e){ }
			}
		}catch(Exception e){
			if(!searchFlg){
				System.err.println("<Warning> map関数の引数が不足しています。 ex. map(geolocation, zoom, icon)");
				return "";
			}
		}

		Mobile_HTML5Env.addJsCss("jscss/googleMap.js");

		if(searchFlg){
			statement += 
					"		<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n" +
							"    		<input type=\"search\" id=\"search_map_words"+getCount(Func.mapFuncCount)+"\" placeholder=\"住所など\">\n" +
							"    		<input type=\"submit\" class=\"btn btn-default\"  value=\"地図を表示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" id=\"search_map"+getCount(Func.mapFuncCount)+"\" data-icon=\"search\" data-mini=\"false\" data-inline=\"false\">\n" +
							"		</form>\n";
		}
		statement += 
				//"		<script src=\"http://maps.google.com/maps/api/js?sensor=false&libraries=geometry\"></script>\n" +
				//"		<script src=\"jscss/googleMap.js\"></script>\n" +
				"		<script type=\"text/javascript\">\n";
		//"		<!--\n";
		//if(!searchFlg)	statement += "$(document).on(\"pageinit\", \"#p-top1\", function(e) {\n";	//←　dynamic時に動かないため、$(function(){へ変更
		if(!searchFlg)	statement += "$(function(){\n";

		else			statement += "$(\"#search_map"+getCount(Func.mapFuncCount)+"\").click(function () {\n";
		statement += 
				//				"  	var map = null; // Google Map\n" +
				"  	var map = null;\n" +
				//"    $(\"#map"+getCount(mapFuncCount)+"\").remove();	// 地図をクリア\n" +
				"    $(\"#map"+getCount(Func.mapFuncCount)+"\").remove();\n" +
				//"    $(\"#map-wrapper"+getCount(mapFuncCount)+"\").append('<div id=\"map"+getCount(mapFuncCount)+"\" style=\"width: 100%; height: 250px;\"></div>'); // 地図を作成\n" +
				"    $(\"#map-wrapper"+getCount(Func.mapFuncCount)+"\").append(\"<div id=\\\\\"map"+getCount(Func.mapFuncCount)+"\\\\\"" +
				//				" style=\"width: 100%; height: "+( (!decos.containsKey("height"))? ("250px"):(decos.getStr("height")) )+";\"></div>'); // 地図を作成\n" +
				" style=\\\\\"width: 95%; height: "+( (!Func.decos.containsKey("height"))? ("250px"):(Func.decos.getStr("height")) )+";\\\\\"></div>\");\n" +
				"      \n";
		if(!searchFlg)	statement += "    var sad = \""+geolocation+"\";\n";
		else			statement += "    var sad = $(\"#search_map_words"+getCount(Func.mapFuncCount)+"\").val();\n";
		statement += 
				"    var geocoder = new google.maps.Geocoder();\n" +
						"    geocoder.geocode({\"address\": sad}, function(results, status) {\n" +
						"      if (status == google.maps.GeocoderStatus.OK) {\n" +
						"	      var mapOptions = {\n" +
						//"	        zoom: 17, // ズーム倍率\n" +
						//"        	zoom: " + ((zoom.equals(""))? ("17"):(zoom)  ) + ", // ズーム倍率\n" +
						"        	zoom: " + ((zoom.equals(""))? ("17"):(zoom)  ) + ",\n" +
						"	        center: results[0].geometry.location,\n" +
						//"	        mapTypeId: google.maps.MapTypeId.ROADMAP // 地図の種類(市街地図)\n" +
						"	        mapTypeId: google.maps.MapTypeId.ROADMAP\n" +
						"	      };\n" +
						"	      map = new google.maps.Map(document.getElementById(\"map"+getCount(Func.mapFuncCount)+"\"),mapOptions);\n" +
						//				"      	  new google.maps.Marker({map : map, position : results[0].geometry.location" + ((icon.equals(""))? (""):(", icon : '"+icon+"'")  ) + "}); //\n" +
						"      	  new google.maps.Marker({map : map, position : results[0].geometry.location" + ((icon.equals(""))? (""):(", icon : \""+icon+"\"")  ) + "});\n" +
						//"	      new google.maps.Marker({map : map, position : results[0].geometry.location});\n" +
						"      } else {\n" +
						//"      	  alert('場所を特定できませんでした。入力内容をご確認ください。');\n" +
						"      	  $(\"#map"+getCount(Func.mapFuncCount)+"\").text(\"場所を特定できませんでした。\");\n" +
						"      }\n" +
						"    });\n" +
						"});\n" +
						//"		// -->\n" +
						"		</script>\n" +
						"		\n" +
						"		<div id=\"map-wrapper"+getCount(Func.mapFuncCount)+"\" style=\"width:260;\"></div>";

		Func.mapFuncCount++;

		return statement;
	}
	//map end

	//added by goto 20130717  "gps,gps_map"
	/*  gps(type[,icon]) or gps_map(type[,icon])  */
	/*  type:1 map  */
	/*  type:2 map + button */
	private static String Func_gps(Mobile_HTML5Function Func) {
		String statement = "\n";
		String type = "";
		String zoom = "";
		String icon = "";
		try{
			type = ((FuncArg) Func.getArg(0)).getStr().trim();
			try{
				zoom = ((FuncArg) Func.getArg(1)).getStr().trim();
				try{
					icon = ((FuncArg) Func.getArg(2)).getStr().trim();
				}catch(Exception e){ }
			}catch(Exception e){ }
		}catch(Exception e){ }

		Mobile_HTML5Env.addJsCss("jscss/googleMap.js");

		statement += 
				//"		<script src=\"http://maps.google.com/maps/api/js?sensor=false&libraries=geometry\"></script>\n" +
				//"		<script src=\"jscss/googleMap.js\"></script>\n" +
				"		<script type=\"text/javascript\">\n" +
				"		<!--\n" +
				//"$(document).on(\"pageinit\", \"#p-top1\", function(e) {\n" +	//←　$(function(){の方が良いかも
				"$(function(){\n" +
				"  // Geolocation APIのオプション設定\n" +
				"  var geolocationOptions = {\n" +
				"    \"enableHighAccuracy\" : true, // 高精度位置情報の取得\n" +
				"    \"maximumAge\" : 0, // キャッシュの無効化\n" +
				"    \"timeout\" : 30000 // タイムアウトは30秒\n" +
				"  };\n" +
				"  var Position = null; // 開始位置\n" +
				"\n" +
				"  var map = null; // Google Map\n";
		if(type.equals("2")){
			statement += 
					"  // 移動開始ボタンクリック時の処理\n" +
							"  $(Func).on(\"click\", \"#gps_button\", function(e) {\n" +
							"    $(\"#gps_button\").addClass(\"ui-disabled\"); 	// ボタンの無効化\n";
		}
		statement += 
				"    navigator.geolocation.getCurrentPosition(function(pos) {\n" +
						"      // 画面上の経度、緯度、距離、地図をクリア //\n" +
						"      $(\"[id=gps_latitude]\").html(\"\");\n" +
						"      $(\"[id=gps_longitude]\").html(\"\");\n" +
						"      $(\"#gps_map\").remove();\n" +
						"      // 位置を表示 //\n" +
						"      $(\"[id=gps_latitude]\").html(pos.coords.latitude);\n" +
						"      $(\"[id=gps_longitude]\").html(pos.coords.longitude);\n" +
						"      Position = new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude); //\n" +
						"      // 地図を作成 //\n" +
						"      $(\"#gps_map-wrapper\").append('<div id=\"gps_map\" style=\"width: 100%; height: 250px;\"></div>'); //\n" +
						"      var mapOptions = {\n" +
						"        zoom: " + ((zoom.equals(""))? ("17"):(zoom)  ) + ", // ズーム倍率\n" +
						"        center: Position,\n" +
						"        mapTypeId: google.maps.MapTypeId.ROADMAP // 地図の種類(市街地図)\n" +
						"      };\n" +
						"      map = new google.maps.Map(document.getElementById(\"gps_map\"),mapOptions);\n" +
						"      new google.maps.Marker({map : map, position : Position" + ((icon.equals(""))? (""):(", icon : '"+icon+"'")  ) + "}); //\n" +
						//"      //new google.maps.Marker({map : map, position : Position, icon : 'star6.gif'}); // \n" +
						"    }, function(e) {\n" +
						"      alert(e.message);\n" +
						"    }, geolocationOptions);\n";
		if(type.equals("2"))
			statement += 
			"    $(\"#gps_button\").removeClass(\"ui-disabled\"); // ボタンの有効化\n" +
					"  });\n";
		statement +=
				"});\n" +
						"		// -->\n" +
						"		</script>\n" +
						"		\n";
		if(type.equals("2")){
			statement += 
					"		<div>\n" +
							"			<a href=\"#\" data-role=\"button\" data-icon=\"home\" id=\"gps_button\">現在地を表示</a>\n" +
							"		</div>\n";
		}
		statement +=
				"		<div id=\"gps_map-wrapper\"></div>\n" +
						"\n";
		//				"		<ul data-role=\"listview\" data-inset=\"true\">\n" +
		//				"			<li>緯度:&nbsp;<span id=\"gps_latitude\"></span></li>\n" +
		//				"			<li>経度:&nbsp;<span id=\"gps_longitude\"></span></li>\n" +
		//				"		</ul>";

		//		// 各引数毎に処理した結果をHTMLに書きこむ
		//		html_env.code.append(statement);
		return statement;
	}
	//gps end
	//added by goto 20130717  "gps_info"
	/*  gps_info()  */
	private static String Func_gps_info() {
		String statement = "\n";
		//		String format = "";
		//		try{
		//			format = ((FuncArg) Func.getArg(0)).getStr();
		//		}catch(Exception e){ }
		Mobile_HTML5Env.addJsCss("jscss/googleMap.js");
		statement += 
				//"		<script src=\"http://maps.google.com/maps/api/js?sensor=false&libraries=geometry\"></script>\n" +
				//"		<script src=\"jscss/googleMap.js\"></script>\n" +
				"		<script type=\"text/javascript\">\n" +
				"		<!--\n" +
				//"$(document).on(\"pageinit\", \"#p-top1\", function(e) {\n" +	//←　$(function(){の方が良いかも
				"$(function(){\n" +
				"  	// Geolocation APIのオプション設定\n" +
				"  	var geolocationOptions = {\n" +
				"    	\"enableHighAccuracy\" : true, // 高精度位置情報の取得\n" +
				"    	\"maximumAge\" : 0, // キャッシュの無効化\n" +
				"    	\"timeout\" : 30000 // タイムアウトは30秒\n" +
				"  	};\n" +
				"    navigator.geolocation.getCurrentPosition(function(pos) {\n" +
				"      	// 経度、緯度を表示 //\n" +
				"      	$(\"[id=gps_latitude]\").html(pos.coords.latitude);\n" +
				"      	$(\"[id=gps_longitude]\").html(pos.coords.longitude);\n" +
				"    }, function(e) {\n" +
				"      	alert(e.message);\n" +
				"    }, geolocationOptions);\n" +
				"});\n" +
				"		// -->\n" +
				"		</script>\n" +
				"		<ul data-role=\"listview\" data-inset=\"true\">\n" +
				"			<li>緯度:&nbsp;<span id=\"gps_latitude\"></span></li>\n" +
				"			<li>経度:&nbsp;<span id=\"gps_longitude\"></span></li>\n" +
				"		</ul>";

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(statement);
		return statement;
	}
	//gps_info end

	//added by goto 20130914  "audio"
	/*  audio("HTML・画像・動画ファイル等のファイル名")  */
	private static String Func_audio(Mobile_HTML5Function Func) {
		//    	String classID = HTMLEnv.getClassID(Func);
		//    	HTMLManager.replaceCode(html_env, classID, "");		//直前の<div>に書き込まれているclassIDを削除

		String str = "";
		try{
			str = ((FuncArg) Func.getArg(0)).getStr();
		}catch(Exception e){ }

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append("<audio src=\""+str+"\" controls>\n");
		return "<audio src=\""+str+"\" controls>\n";
	}
	//audio end

	//added by goto 20130914  "movie"
	/*  movie("HTML・画像・動画ファイル等のファイル名")  */
	private static String Func_movie(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		String classID = Mobile_HTML5Env.getClassID(Func);
		Mobile_HTML5Manager.replaceCode(html_env, classID, "");		//直前の<div>に書き込まれているclassIDを削除

		String str = "";
		try{
			str = ((FuncArg) Func.getArg(0)).getStr();
		}catch(Exception e){ }

		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		////    	html_env.code.append("<video src=\""+str+"\" class=\"" + classID +"\">\n</video>\n");
		////    	html_env.code.append("<video src=\""+str+"\" class=\"" + classID +"\" controls>\n</video>\n");
		//    	html_env.code.append("<video src=\""+str+"\" class=\"" + classID +"\" preload=\"none\" onclick=\"Func.play()\" controls>\n</video>\n");
		////    	html_env.code.append("<video src=\""+str+"\" class=\"" + classID +"\" poster=\"XXX.jpg\" preload=\"none\" onclick=\"Func.play()\" controls>\n</video>\n");
		return "<video src=\""+str+"\" class=\"" + classID +"\" preload=\"none\" onclick=\"Func.play()\" controls>\n</video>\n";
	}
	//movie end

	//added by goto 20130914  "object"
	/*  object("file name")  */
	/*  object("HTML・PDF・FLASH・画像・動画・PHP・JSファイル等のファイル名")  */
	private static String Func_object(String path, Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		String statement = "";
		String classID = Mobile_HTML5Env.getClassID(Func);

		//not @{table}
		if(!Func.decos.containsKey("table") && !Mobile_HTML5C1.table0Flg && !Mobile_HTML5C2.tableFlg && !Mobile_HTML5G1.tableFlg && !Mobile_HTML5G2.tableFlg)
			Mobile_HTML5Manager.replaceCode(html_env, classID, "");		//直前の<div>に書き込まれているclassIDを削除

		if(path.equals("")){
			try{
				path = ((FuncArg) Func.getArg(0)).getStr().trim();
			}catch(Exception e){ }
		}

		// 各引数毎に処理した結果をHTMLに書きこむ
		if(path.endsWith(".php")){	//.php file
			BufferedReader in;
			try{
				in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
				String line = null;
				while (true){
					line = in.readLine();
					if (line == null)	break;
					else statement += line+"\n";
				}
			}catch(Exception e){
				System.err.println("<Warning> Can't open '"+path+"'.");
			}
		}else if(path.endsWith(".js"))	//.js file
			statement += "<script type=\"text/javascript\" src=\""+path+"\">\n</script>\n";
		else	//.html, .pdf, .swf, .gif, .mp4, etc.
			statement += "<object data=\""+path+"\" class=\"" + classID +"\" >\n</object>\n";
		return statement;
	}
	//object end

	//added by goto 20130914  "SEQ_NUM"
	static ArrayList<Integer> seq_num = new ArrayList<Integer>();
	static ArrayList<String> seq_num_ClassID = new ArrayList<String>();
	static ArrayList<Integer> seq_num_gl = new ArrayList<Integer>();
	static ArrayList<Integer> seq_num_startNum = new ArrayList<Integer>();
	static ArrayList<Boolean> seq_num_DESC_Flg = new ArrayList<Boolean>();
	static String classID = "";
	private static int glvl = 0;
	/*  SEQ_NUM( [Start number [, ASC or DESC] ] )  */
	private static String Func_seq_num(Mobile_HTML5Function Func) {
		String s = "";
		classID = Mobile_HTML5Env.getClassID(Func);

		int i;
		for(i=0; i<seq_num_ClassID.size()+1; i++){
			try{
				if(classID.equals(seq_num_ClassID.get(i)))
					break;
			}catch(Exception e1){
				seq_num_ClassID.add(i, classID);
				seq_num_gl.add(i, getGlvl());
				try{
					//第一引数 Start number
					seq_num_startNum.add(i, Integer.parseInt(getValue(1, Func)));
					//第二引数 ASC or DESC
					if(getValue(2, Func).toLowerCase().trim().equals("desc"))	seq_num_DESC_Flg.add(i, true);
					else												seq_num_DESC_Flg.add(i, false);
				}catch(Exception e2){
					seq_num_startNum.add(i, 1);			//default: 1
					seq_num_DESC_Flg.add(i, false);		//default: false
				}
				seq_num.add(i, seq_num_startNum.get(i));
				break;
			}
		}

		s = ""+((!seq_num_DESC_Flg.get(i))? (seq_num.get(i)):(seq_num.get(i)));
		//Log.i("s = "+s+"  "+nestingLevel+"  "+adjustValue(s));
		s = adjustValue(s);
		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(""+((!seq_num_DESC_Flg.get(i))? (seq_num.get(i)):(seq_num.get(i))));
		if(!seq_num_DESC_Flg.get(i))	seq_num.set(i,seq_num.get(i)+1);
		else							seq_num.set(i,seq_num.get(i)-1);
		return s;
	}
	//seq_num end
	//added by goto 20130914  "SEQ_NUM"
	public static void Func_seq_num_initialization(int gl) {		//initialize seq_num
		//Log.i(" !! Func_seq_num_initialization !! ");
		try{
			for(int i=0; i<seq_num_ClassID.size(); i++){
				if(seq_num_ClassID.get(i).equals(classID) && seq_num_gl.get(i)==gl){
					for(int j=i; j>=0; j--){
						if(seq_num_gl.get(j)==gl){
							seq_num.set(j, seq_num_startNum.get(j));	//replace
						}
						if(seq_num_gl.get(j)!=gl)	break;
					}
					break;
				}
			}
		}catch(Exception e){}
		return;
	}
	static String adjustValue(String s){
		try{
			if(Function.nestingLevel > 0){
				s = ""+Integer.parseInt(s)/(Function.nestingLevel*2);
			}
		}catch(Exception e){}
		return s;
	}

	//added by goto 20130914  "text"
	/*  text("#TextLabel_" + Number)  */
	//    static String text = "";
	public static boolean textFlg2 = false; //for C2
	private static String Func_text(Mobile_HTML5Function Func) {
		//    	html_env.code.delete(html_env.code.lastIndexOf("<"),html_env.code.lastIndexOf(">")+1);	//delete last <div class="">
		//    	html_env.code.delete(html_env.code.lastIndexOf("<"),html_env.code.lastIndexOf(">")+1);	//delete last <div class="">
		//TODO: 改行コード削除
		Func.textFlg = true;

		String str = "";
		int textNum = -1;
		try{
			//第一引数
			str = ((FuncArg) Func.getArg(0)).getStr();
			if(str.startsWith("#TextLabel_"))
				textNum = Integer.parseInt( str.substring("#TextLabel_".length()) );
			str = Start_Parse.textString.get(textNum);
		}catch(Exception e){ }


		////		text = str;
		//    	// 各引数毎に処理した結果をHTMLに書きこむ
		//    	html_env.code.append(str);
		return str;
	}
	//text end

	//null()
	private static void Func_null(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		Func.func_null_count++;
		String c = html_env.code.toString();
		html_env.code = new StringBuffer(c.substring(0, c.lastIndexOf("<")));	//TODO
		return;
	}


	//added by chie 2009 func form submit
	private static void Func_submit(Mobile_HTML5Function Func, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2) {
		String form = new String();
		boolean openFormInThis = false;

		//submit only ----- no "@{form}"
		if(!Mobile_HTML5Env.getFormItemFlg() && !Func.decos.containsKey("form")){
			form = createForm(Func);
			openFormInThis = true;
		}else if(Func.decos.containsKey("form")){
			form = createForm(Func.decos, Func);
			openFormInThis = true;
		}


		Mobile_HTML5Env.setFormItemFlg(true,"submit");

		String value = new String();
		if(!Func.getAtt("value").equals(null)){
			value = "value=\"" + Func.getAtt("value") + "\"";
		}


		String option = new String();
		if(!Func.getAtt("default").equals(null)){
			option += "value=\"" + Func.getAtt("default") + "\"";
		}

		form += "<input type=\"submit\" class=\"btn btn-default\" " + option + " />";

		if(openFormInThis == true){
			form += "</form>";
			Mobile_HTML5Env.setFormItemFlg(false,null);
			openFormInThis = false;
		}else{
			Mobile_HTML5Env.setFormItemFlg(true,null);
		}

		html_env.code.append(form);
		html_env2.code.append("<VALUE type=\"form\">"+form+"</VALUE>");
		return;
	}

	//  //added by chie 2009 func form select
	//    private void Func_select() {
	//        if(!Func.getAtt("selected").equals("")){
	//        	HTMLEnv.setSelected(Func.getAtt("selected"));
	//        }
	//
	//		Func_FormCommon("select");
	//
	//        return;
	//    }
	//added by chie 2009 func form checkbox
	private static void Func_checkbox(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		Func_FormCommon("checkbox", Func, html_env);

		if(!Func.getAtt("checked").equals("")){
			Mobile_HTML5Env.setChecked(Func.getAtt("checked"));
		}

		return;
	}
	//added by chie 2009 func form radio
	private static void Func_radio(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {

		if(!Func.getAtt("checked").equals("")){
			Mobile_HTML5Env.setChecked(Func.getAtt("checked"));
		}

		Func_FormCommon("radio", Func, html_env);

		return;
	}
	//added by chie 2009 func form inputtext
	private static void Func_inputtext(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		Func_FormCommon("text", Func, html_env);
		return;
	}
	//added by chie 2009 func form textarea
	private static void Func_textarea(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		Func_FormCommon("textarea", Func, html_env);
		return;
	}

	//added by chie 2009 func form hidden
	private static void Func_hidden(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		Func_FormCommon("hidden", Func, html_env);
		return;
	}

	private static void Func_FormCommon(String s, Mobile_HTML5Function Func, Mobile_HTML5Env html_env){
		String form = new String();

		boolean openFormInThis = false;

		if(!Mobile_HTML5Env.getFormItemFlg()){
			form = createForm(Func);
			openFormInThis = true;
		}

		Mobile_HTML5Env.setFormItemFlg(true,s);

		String att = new String();
		Integer attNo = 1;
		while (!Func.getAtt("att"+attNo).equals("")){
			if(attNo > 1)
				att += ",";
			att += Func.getAtt("att"+attNo);
			Log.out("att:" + att + " attNo:" + attNo);
			attNo ++;
		}
		if(attNo == 1 && !Func.getAtt("att").equals("")){
			att += Func.getAtt("att");
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if(!Func.getAtt("name").equals("")){
			Mobile_HTML5Env.setFormPartsName(Func.getAtt("name"));
			Mobile_HTML5Env.exFormName();
		}else{
			Mobile_HTML5Env.setFormPartsName(null);
		}

		if(!Func.getAtt("id").equals("")){
			Mobile_HTML5Env.nameId = Func.getAtt("id");
		}

		if(!Func.getAtt("cond_name").equals("")){
			Mobile_HTML5Env.cond_name = Func.getAtt("cond_name");
		}
		if(!Func.getAtt("cond").equals("")){
			Mobile_HTML5Env.cond = Func.getAtt("cond");
		}


		html_env.code.append(form);

		if(Func.getArg(0) instanceof FuncArg)
		{
			//HTMLEnv.setSelectFlg(true,(String)Func.decos.get("select"));
			Mobile_HTML5Env.setFormValueString(att);
			Log.out("ARGS are function");
			FuncArg fa = (FuncArg) Func.getArg(0);
			fa.workAtt();
		}
		else{
			Func.workAtt("default");
		}

		if(openFormInThis == true){
			html_env.code.append("</form>");
			Mobile_HTML5Env.setFormItemFlg(false,null);
			openFormInThis = false;
		}else{
			Mobile_HTML5Env.setFormItemFlg(true,null);
		}
		return;
	}


	private static String createForm(Mobile_HTML5Function Func) {
		String path = new String();
		String form = new String();
		if(Func.getAtt("path") != null &&  !Func.getAtt("path").isEmpty()){
			path =  Func.getAtt("path").replaceAll("\"", "");
		}else{
			path = ".";
		}

		form += "<form method=\"POST\" action=\"" + path + "/servlet/supersql.form.FormServlet\"" +">";

		form += "<input type=\"hidden\" name=\"configfile\" value=\"" +
				path + "/config.ssql\" />";

		if(Func.getAtt("link") != null && !Func.getAtt("link").isEmpty()){
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path + "/" + Func.getAtt("link").replaceAll("\"", "") + "\" />";
		}else if(Func.getAtt("linkfile") != null && !Func.getAtt("linkfile").isEmpty()){
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path + "/" + Func.getAtt("linkfile").replaceAll("\"", "") + "\" />";
		}
		/*
        if(!Func.getAtt("default").equals(null)){
        	form += "<input type=\"hidden\" name=\"value1\" value=\""+Func.getAtt("default").replaceAll("\"", "")+"\" />";
        }
		 */

		if(Func.getAtt("cond")!= null && !Func.getAtt("cond").isEmpty()){
			if(!Func.getAtt("cond").replaceAll("\"", "").isEmpty())
				form += "<input type=\"hidden\" name=\"cond1\" value=\""+Func.getAtt("cond").replaceAll("\"", "")+"\" />";
		}

		String att = new String();
		Integer attNo = 1;
		while (!Func.getAtt("att"+attNo).equals("")){
			if(attNo > 1)
				att += ",";
			att += Func.getAtt("att"+attNo);
			attNo ++;
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if(attNo == 1 && !Func.getAtt("att").equals("")){
			att += Func.getAtt("att");
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if(Func.getAtt("update")!=null && !Func.getAtt("update").isEmpty()){
			form += "<input type=\"hidden\" name=\"updatefile\" value=\"" + path + "/" +Func.getAtt("update").replaceAll("\"", "")+"(" + att + ")\" />";
		}else if(Func.getAtt("updatefile")!=null && !Func.getAtt("updatefile").isEmpty()){
			form += "<input type=\"hidden\" name=\"updatefile\" value=\"" + path + "/" +Func.getAtt("updatefile").replaceAll("\"", "")+"(" + att + ")\" />";
		}


		Log.out(form);
		return form;
	}

	public static String createForm(DecorateList decos, Mobile_HTML5Function Func) {
		String option = new String();
		String path = new String();
		String form = new String();
		//System.out.println(Func.getAtt("label"));
		if(decos.containsKey("path")){
			path =  decos.getStr("path").replaceAll("\"", "");
		}else{
			path = ".";
		}

		form = "<form method=\"POST\" action=\"" + path + "/supersql.form.FormServlet\" " + "name=\""+ Mobile_HTML5Env.getFormName() + "\" " +">";


		form += "<input type=\"hidden\" name=\"configfile\" value=\"" +
				GlobalEnv.getFileDirectory() + "/config.ssql\" />";

		if(decos.containsKey("link")){
			String tmp = opt(decos.getStr("link"));
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path + "/" + decos.getStr("link").replaceAll("\"", "") + "\" />";
		}

		if(decos.containsKey("cond")){
			form += "<input type=\"hidden\" name=\"cond1\" value=\""+decos.getStr("cond").replaceAll("\"", "")+"\" />";
		}


		if(decos.containsKey("updatefile")){
			String tmp = opt(decos.getStr("updatefile"));
			Func.updateFile = "<input type=\"hidden\" name=\"updatefile\" value=\"" + path + "/" +tmp+"\" />";
			form += Func.updateFile;
		}
		if(decos.containsKey("linkfile")){
			String tmp = opt(decos.getStr("linkfile"));
			form += "<input type=\"hidden\" name=\"linkfile\" value=\"" + path + "/" +decos.getStr("linkfile").replaceAll("\"", "")+"\" />";
		}
		if(decos.containsKey("cond")){
			form += "<input type=\"hidden\" name=\"linkcond\" value=\"" + decos.getStr("cond").replaceAll("\"", "")+"\" />";
		}
		Log.out(form);
		Mobile_HTML5Env.setFormDetail(form);
		return form;
	}

	//not use


	private static void Func_invoke(Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {

		/*
		 * Invoke function : <td> <a
		 * href="${server_path}/supersql.invoke.InvokeServlet?
		 * ${dbname}+${query_filename}+${added_condition}"> TFE </a> </td>
		 */

		String path = Func.getAtt("path", ".");
		if(!GlobalEnv.getFileDirectory().equals(".")){
			path = GlobalEnv.getFileDirectory();
		}
		String filename = Func.getAtt("filename");
		if (!filename.startsWith("/") && (path != null)) {
			filename = path + "/" + filename;
		}



		Log.out("invoke filename:"+filename);


		//start tk/////////////////////////////////
		/*
        html_env.linkurl = Func.getAtt("server_path", GlobalEnv
                .getInvokeServletPath())
                + "?"
                + Func.getAtt("dbname", GlobalEnv.getdbname())
                + "+"
                + filename + "+" + Func.getAtt("condition");
		 */
		/*
        html_env.linkurl = "http://localhost:8080/invoke/servlet/supersql.invoke.InvokeServlet2"
                + "?"
                + "config=http://localhost:8080/invoke/config.ssql"
                + "&"
                + "query=" + filename
                + "&"
                + "cond=" + Func.getAtt("condition");
		 */
		//change chie
		html_env.linkurl = Func.getAtt("server_path", GlobalEnv
				.getInvokeServletPath())
				+ "?"
				+ "config="+path+"/config.ssql"
				+ "&"
				+ "query=" + filename
				+ "&"
				+ "cond=" + Func.getAtt("condition");
		// end tk//////////////////////////////////////////////////

		html_env.link_flag=1;
		Func.workAtt("default");
		html_env.link_flag=0;

		return;
	}

	private static void Func_foreach(ExtList data_info, Mobile_HTML5Function Func, Mobile_HTML5Env html_env) throws UnsupportedEncodingException {
		String att = new String();
		Mobile_HTML5G3.dynamic_G3_atts.clear();
		for (int i = 0; i < Func.countconnectitem(); i++) {
			att = att + "_" + Func.getAtt(Integer.toString(i));

			//added by goto 20161112 for dynamic foreach
			//if(Compiler.isCompiler || decos.containsKey("dynamic"))
			Mobile_HTML5G3.dynamic_G3_atts.add(""+Func.getArg(i)); //add attribute name
		}

		if(!Start_Parse.foreach1Flag){
			//added by goto 20161019 for new foreach
			Mobile_HTML5G3.foreachID = att;
		}else{
			//added by goto 20161025 for link1/foreach1
			att = URLEncoder.encode(att, "UTF-8");
			String filename = html_env.outfile + att + Compiler.getExtension();
			html_env.filename = filename;
		}
		return;
	}

	//tk start//////////////////////////////////////////////////////////////////////////////
	private static void Func_embed(ExtList data_info, Mobile_HTML5Function Func, Mobile_HTML5Env html_env, Mobile_HTML5Env html_env2){
		//goto 20130917
		try{
			Func_object( ((FuncArg) Func.getArg(0)).getStr().trim(), Func, html_env );	//if embed("file name")
			return;
		}catch(Exception e){ }


		String file = Func.getAtt("file");
		String where = Func.getAtt("where");
		String att = Func.getAtt("att");
		String border = Func.getAtt("border");
		String att2 = Func.getAtt("attString");
		String condition = new String();
		String defcond = Func.getAtt("defcond");


		Log.out("function embed");
		Log.out("isNewEmbed:"+GlobalEnv.isNewEmbed());

		boolean is_hidden = false;

		if(Func.decos.containsKey("status"))
			if(Func.decos.getStr("status").compareTo("hidden") == 0)
				is_hidden = true;

		//for tab
		if(Func.decos.containsKey("tab"))
		{
			html_env.code.append("<div id=\"myTab\" ");

			if(Func.decos.containsKey("class"))
				html_env.code.append("class=\""+Func.decos.getStr("class")+"\"");

			html_env.code.append(">\n");
			html_env.code.append("<div id=\"mTab\" class=\"yui-navset\">\n");

			html_env.code.append("</div></div>\n");

			html_env.script.append("var mTab = new YAHOO.widget.TabView(\"mTab\");");
			html_env.script.append("new YAHOO.util.DDTarget(\"myTab\", \"myTab\");");

			return;
		}

		if(!is_hidden)
		{
			html_env.code.append("<table class=\"att " + html_env.getOutlineModeAtt() + " ");

			if(Func.decos.containsKey("class"))
				html_env.code.append(Func.decos.getStr("class"));
			else
				html_env.code.append(Mobile_HTML5Env.getClassID(Func));

			html_env.code.append("\"");
			html_env.code.append("><tr><td>");
		}

		// for ajax div id //////////////////////////////////////////////////////

		String divname = new String();
		boolean has_divid = false;

		if(Func.decos.containsKey("divid"))
		{
			has_divid = true;
			Log.out("embed contains decos with divid");
			String tmpdivid = Func.decos.getStr("divid");
			String tmp;
			String ans;

			if(tmpdivid.contains("+"))
			{
				ans = tmpdivid.substring(0,tmpdivid.indexOf("+"));
				tmp = tmpdivid.substring(tmpdivid.indexOf("+")+1,tmpdivid.length());

				if(tmp.compareTo("att") == 0)
				{
					tmp = att;
				}
				divname = ans + "_" + tmp;
				Log.out("ans :"+ans+" tmp:"+tmp+" divname:"+divname);
			}
			else{
				divname = Func.decos.getStr("divid");
			}
		}/*else
		{
			//online file
			if(file.contains("/"))
			{
				divname = file.substring(file.lastIndexOf("/")+1,file.indexOf(".sql"));
			}
			//ofline file
			else if(file.contains("\\"))
			{
				Log.out(" // index"+file.indexOf(".sql"));
				divname = file.substring(file.lastIndexOf("\\")+1,file.indexOf(".sql"));
			}
			//only file name
			else
			{
				divname = file.substring(0,file.indexOf(".sql"));
			}

		}
		 */
		if(GlobalEnv.isAjax() && Func.decos.containsKey("droppable"))
		{
			html_env.script.append("new YAHOO.util.DDTarget(\""+divname+"\", \""+divname+"\");");
		}
		//ajax & decos contains status=hidden
		if(is_hidden && GlobalEnv.isAjax()){

			html_env.code.append("<div id=\""+divname+"\" ");

			if(Func.decos.containsKey("class"))
				html_env.code.append("class=\""+Func.decos.getStr("class")+ "\" ");

			html_env.code.append("></div>");
			Log.out("<div id="+divname+"></div>");

			return;
		}
		// end ajax divname ////////////////////////////////////////////////

		/*    	if(border.compareTo("1") == 0)
    	{}
    	else
    		html_env.css.append(".embed { vertical-align : text-top; padding : 0px ; margin : 0px; border: 0px,0px,0px,0px; width: 100%;}");
		 */
		if(att.compareTo("") != 0 ){
			condition = condition + where+att;
		}
		else if(att2.compareTo("") != 0){
			condition = condition + where+"'"+att2+"'";
		}
		//store original config
		Hashtable tmphash = GlobalEnv.getEnv();

		//set new config for embed
		//    	String[] args = {"-f",file,"-cond",condition,"-debug"};
		//    	Log.out("cond:"+condition);
		String[] args;
		if(GlobalEnv.isAjax())
		{
			if(condition.equals(""))
			{
				args = new String[3];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-ajax";
				//        		args[3] = "-debug";

			}
			else
			{
				args = new String[5];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				args[4] = "-ajax";
				//        		args[5] = "-debug";
			}
		}
		else
		{
			if(GlobalEnv.isOpt()){
				args = new String[5];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				args[4] = "-optimizer";
				//    		args[5] = "-debug";
			}else{
				args = new String[4];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				//    		args[4] = "-debug";
			}
		}

		html_env.embedcount++;

		if(file.contains(".sql") || file.contains(".ssql"))
		{

			String makedfilename = file.substring(file.lastIndexOf("\\")+1, file.indexOf("."));

			if(att.compareTo("") != 0)
				makedfilename = makedfilename.concat("_"+att);
			if(att2.compareTo("") != 0)
				makedfilename = makedfilename.concat("_"+att2);

			makedfilename= makedfilename.concat(".html");

			Log.out("embed tmpfilename:"+makedfilename+" option:"+GlobalEnv.getEmbedOption());

			File makedfile = new File(GlobalEnv.getEmbedTmp(), makedfilename);

			if(makedfile.exists() && GlobalEnv.isNewEmbed() == 1)
			{
				Log.out("[Enter new Embed]");
				Log.out("embed read tmp file");
				BufferedReader dis;
				String line = new String();
				try{
					dis = new BufferedReader(new FileReader(makedfile));

					try{
						while(!line.equalsIgnoreCase(" "))
						{
							Log.out("line : "+line);
							line = dis.readLine();
							if(line != null)
								html_env.code.append(line);
						}
					}catch(NullPointerException e)
					{
						Log.out("no more lines");
					}

					dis.close();
				}
				catch (IOException ioe) {
					System.out.println("IOException: " + ioe);
				}
			}
			else
			{
				Log.out("embed make file");

				GlobalEnv.setGlobalEnvEmbed(args);


				Start_Parse parser;
				if(file.contains("http"))
				{
					parser = new Start_Parse("online");
				}
				else
				{
					parser = new Start_Parse(10000*(html_env.embedcount+1));
				}

				CodeGenerator codegenerator = parser.getcodegenerator();
				DataConstructor dc = new DataConstructor(parser);
				StringBuffer returnedcode = codegenerator.generateCode2(parser,dc.getData());

				//ajax add div tag////////////////////////////////////////////////////////////////////
				if(GlobalEnv.isAjax())
				{
					if(!has_divid)
					{
						int x = 0;
						if (file.indexOf(".sql")>0) {
							x = file.indexOf(".sql");
						} else if (file.indexOf(".ssql")>0) {
							x = file.indexOf(".ssql");
						}

						//online file
						if(file.contains("/"))
						{
							divname = file.substring(file.lastIndexOf("/")+1,x);
						}
						//ofline file
						else if(file.contains("\\"))
						{
							divname = file.substring(file.lastIndexOf("\\")+1,x);
						}
						//only file name
						else
						{
							divname = file.substring(0,x);
						}
					}

					html_env.code.append("<div id=\""+divname+"\" ");

					if(Func.decos.containsKey("class"))
						html_env.code.append("class=\""+Func.decos.getStr("class")+ "\" ");

					html_env.code.append(">");
					//	    			html_env.code.append("<br><a href=\"close.html\" class=\"bottom_close_"+divname+"\" onClick=\"return closeDiv('"+divname+"')\">close</a><br>");
					Log.out("<div id="+divname+">");
				}

				// ajax depends on decos status //////////////////////////////////////////
				boolean status_flag = false;

				//xml�����
				if(!is_hidden){
					html_env2.code.append("<EMBED>");
					html_env.code.append(returnedcode);
					html_env2.code.append(returnedcode);
					html_env2.code.append("</EMBED>");
				}

				if(GlobalEnv.isAjax())
					html_env.code.append("</div>");
				// end ajax /////////////////////////////////////////////////////////////////

				if(html_env.embedcount >= 1)
				{
					html_env.css.append(codegenerator.generateCode3(parser,dc.getData()));
					html_env.cssfile.append(codegenerator.generateCssfile(parser,dc.getData()));
				}

				//restore original config
				GlobalEnv.setEnv(tmphash);

				//writing tmpfile
				Log.out("embed hogehoge:"+GlobalEnv.isNewEmbed());
				Log.out("enb:"+GlobalEnv.getEnv());

				if(GlobalEnv.isNewEmbed() == 1)
				{
					GlobalEnv.addEmbedFile(makedfilename);
					Log.out("embed start writing");
					String filename = GlobalEnv.getEmbedTmp();

					if(filename.endsWith("/") || filename.endsWith("\\"))
						filename = filename + makedfilename;
					else
						filename = filename + "/" + makedfilename;

					try {
						OutputStream fout = new FileOutputStream(filename);
						OutputStream bout = new BufferedOutputStream(fout);
						OutputStreamWriter out = new OutputStreamWriter(bout,"UTF-8");

						out.write(html_env.header.toString());
						out.write(returnedcode.toString());
						out.write(html_env.footer.toString());

						out.close();
						/*
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
			                    filename)));
			            Log.out("filename:"+filename);
			            pw.println(html_env.header);
			            pw.println(returnedcode);
			            pw.println(html_env.footer);
			            pw.close();
						 */
					} catch (FileNotFoundException fe) {

						fe.printStackTrace();
						System.err.println("Error: specified embedtmp outdirectory \""
								+ GlobalEnv.getEmbedTmp() + "\" is not found to write " + html_env.filename );

						GlobalEnv.addErr("Error: specified embedtmp outdirectory \""
								+ GlobalEnv.getEmbedTmp() + "\" is not found to write " + html_env.filename);
						//comment out by chie
						//System.exit(-1);
					} catch (IOException e) {
						System.err.println("Error[HTMLManager]: File IO Error in HTMLManager at embed");
						e.printStackTrace();
						GlobalEnv.addErr("Error[HTMLManager]: File IO Error in HTMLManager at embed");
						//comment out by chie
						//System.exit(-1);
					}
				}

			}
		}
		//embed html file
		else if(file.contains(".html"))
		{
			String line = new String();

			if(Func.decos.containsKey("divid"))
				divname = Func.decos.getStr("divid");
			else if(file.contains("\\"))
				divname = file.substring(file.lastIndexOf("\\")+1,file.indexOf(".html"));
			else if(file.contains("/"))
				divname = file.substring(file.lastIndexOf("/")+1,file.indexOf(".html"));
			else
				divname = file.substring(0,file.indexOf(".html"));

			BufferedReader dis;
			try {
				if(file.contains("http://"))
				{
					URL fileurl = new URL(file);

					URLConnection fileurlConnection = fileurl.openConnection();
					dis = new BufferedReader(new InputStreamReader(fileurlConnection.getInputStream()));
				}
				else{
					try{
						Log.out("embed file (html):"+file);
						dis = new BufferedReader(new FileReader(new File(file)));
					}catch(IOException ioe){
						String path = html_env.outfile;
						if(path.contains("\\"))
							path = path.substring(0,path.lastIndexOf("\\")+1);
						else if(path.contains("/"))
							path = path.substring(0,path.lastIndexOf("/")+1);
						if(file.startsWith("./")){
							file = file.substring(1,file.length());
						}
						Log.out("embed file (html):"+path+file);
						//TODO
						if(path.startsWith("http:")){
							URL fileurl = new URL(path + file);
							URLConnection fileurlConnection = fileurl.openConnection();
							dis = new BufferedReader(new InputStreamReader(fileurlConnection.getInputStream()));
						}else{
							dis = new BufferedReader(new FileReader(new File(path+file)));

						}
					}
				}
				/* DataInputStream dis = new
                DataInputStream(fileurlConnection.getInputStream());*/
				line = dis.readLine(); //read <BODY> and/or <HEAD>
				if(line.contains("<head>"))
				{
				}
				else
				{
					line = dis.readLine(); //read <HEAD>
				}


				while(!line.equalsIgnoreCase("</head>"))
				{
					line = dis.readLine();
					if(!line.equalsIgnoreCase("</head>"))
						html_env.header.append(line+"\n");
				}
				line = dis.readLine(); //read <body>

				html_env.code.append("<div id=\""+divname+"\" ");

				if(Func.decos.containsKey("class"))
					html_env.code.append("class=\""+Func.decos.getStr("class")+ "\" ");

				html_env.code.append(">");


				html_env2.code.append("<EMBED>");
				while(!line.equalsIgnoreCase("</body>"))
				{
					Log.out("line : "+line);
					line = dis.readLine();
					if(!line.equalsIgnoreCase("</body>")){
						html_env.code.append(line+"\n");
						if(line.contains("&"))
							line = line.replace("&", "&amp;");
						if(line.contains("<"));
						line = line.replace("<", "&lt;");
						if(line.contains(">"))
							line = line.replace(">", "&gt;");
						if(line.contains("���"))
							line = line.replace("���", "&#65374;");
						html_env2.code.append(line);
					}
				}
				html_env2.code.append("</EMBED>");
				//    			html_env.code.append("<br><a href=\"close.html\" class=\"bottom_close_"+divname+"\" onClick=\"return closeDiv('"+divname+"')\">close</a><br>");

				html_env.code.append("</div>");
				dis.close();

			} catch (MalformedURLException me) {
				System.out.println("MalformedURLException: " + me);
			} catch (IOException ioe) {
				System.out.println("HTMLFuncEmbed:IOException: " + ioe);
			}

		}
		if(!is_hidden)
			html_env.code.append("</td></tr></table>");

		html_env.embedcount += 1;
	}
	//tk end////////////////////////////////////////////////////////////////////////////

	//int ltype : 1=link, 2=glink, 3=plink
	private static void Func_sinvoke(ExtList data_info, int ltype, Mobile_HTML5Function Func, Mobile_HTML5Env html_env) {
		// link関数の仕様変更　link(att_name, url, value1, value2, ...)
		String file = Func.getArg(1).toString();
		if (file.startsWith("\'") || file.startsWith("\"")) {
			file = file.substring(1, file.length() - 1);
		}
		String att = new String();
		for (int i = 2; i < Func.sizeArg(); i++) {
			att += "_" + Func.getArg(i).getStr();
		}
		String action = Func.getAtt("action");
		Log.out("sinvoke file 3: " + file);
		//        String file = Func.getAtt("file");
		//        String action = Func.getAtt("action");
		//        int attNo = 1;
		//        String att = new String();
		//        Log.out("sinvoke file 3: "+file);
		//      	Log.out("1 att:" + att + " attNo:" + attNo + " att1:" + Func.getAtt("att1"));
		//        while (!Func.getAtt("att"+attNo).equals("")){
		//        	att = att + "_" + Func.getAtt("att"+attNo);
		//        	attNo ++;
		//        	Log.out("att:" + att + " attNo:" + attNo);
		//        }

		//changed by goto 20161019 for new foreach
		if(Func.link1){
			//added by goto 20161025 for link1/foreach1
			try {
				att = URLEncoder.encode(att, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if(Func.getAtt("action").equals("")){
			try{
				if(file.toLowerCase().contains(".sql")){
					file = file.substring(0, file.indexOf(".sql"));
				}else if(file.toLowerCase().contains(".ssql")){
					file = file.substring(0, file.indexOf(".ssql"));
				}else if(file.toLowerCase().contains(".html")){
					file = file.substring(0, file.indexOf(".html"));
				}
			}catch(Exception e){
				GlobalEnv.addErr("Error[HTMLFunction]: filename is invalid.");
				System.err.println("Error[HTMLFunction]: filename is invalid.");
			}

			String filename = new String();
			if(!Func.getAtt("att").equals("")){
				if(Func.getAtt("att").toLowerCase().startsWith("http://"))
					filename = Func.getAtt("att");
				else if(Func.getAtt("att").toLowerCase().endsWith(".html"))
					filename = Func.getAtt("att");
				else
					filename = file + "_" + Func.getAtt("att") + ".html";
			}else{
				if(!Func.link1){
					//added by goto 20161019 for new foreach
					filename = file;
					//added by goto 20161109 for plink/glink
					if(!file.endsWith(".php") && !file.endsWith(".rb") && !file.endsWith(".erb") && !file.endsWith(".jsp"))
						filename += ".html";
					if(ltype == 1)
						filename += "?"+LinkForeach.ID2+"="+att.substring(1);
					else if(ltype==2 || ltype==3){
						//<A href="" onclick="ssql_foreach(\'GET\', \'test04_php-foreach.html\', \''.$row1[1].'_'.$row1[2].'_'.$row1[3].'\'); return false;" data-ajax="false" >
						if(!PHP.isPHP && !Mobile_HTML5_dynamic.dynamicDisplay)
							html_env.plink_glink_onclick = "'"+(ltype==2? "GET" : "POST")+"', '"+filename+"', '"+att.substring(1)+"'";
						else
							html_env.plink_glink_onclick = "\\'"+(ltype==2? "GET" : "POST")+"\\', \\'"+filename+"\\', \\'"+att.substring(1)+"\\'";
						LinkForeach.plink_glink = true;
					}
				}else{
					//added by goto 20161025 for link1/foreach1
					filename = file + att + ".html";
				}
			}

			filename.replace("\\\\","\\");
			html_env.linkurl = filename;
			html_env.sinvoke_flag = true;

		}else{
			String filename = new String();
			if(!Func.getAtt("att").equals(""))
				filename = action + "/" + Func.getAtt("att");
			else
				filename = action + att;

			filename.replace("\\\\","\\");
			html_env.linkurl = filename;
			html_env.sinvoke_flag = true;
		}

		//tk to make hyper link to image///////////////////////////////////////////////////
		//tk to ajax
		if(GlobalEnv.isAjax())
		{
			html_env.linkurl =  file+".html";
			html_env.ajaxquery = file+".ssql";
			//        	html_env.ajaxatt = Func.getAtt("att");
			html_env.ajaxcond = Func.getAtt("ajaxcond")+"="+Func.getAtt("att");

			Date d2 = new Date();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyymmddHHmmss");
			String today2 = sdf2.format(d2);

			html_env.dragdivid = html_env.ajaxquery+"+"+html_env.ajaxcond+"&"+today2;

			if(Func.decos.containsKey("in"))
			{
				String effect = Func.decos.getStr("in");

				if(effect.equalsIgnoreCase("blind"))
					html_env.inEffect = 1;
				if(effect.equalsIgnoreCase("fade"))
					html_env.inEffect = 2;
			}
			if(Func.decos.containsKey("out"))
			{
				String effect = Func.decos.getStr("out");

				if(effect.equalsIgnoreCase("blind"))
					html_env.outEffect = 1;
				if(effect.equalsIgnoreCase("fade"))
					html_env.outEffect = 2;
			}

			if(Func.decos.containsKey("panel"))
			{
				html_env.isPanel = true;
			}
			if(Func.decos.containsKey("dispdiv"))
			{
				String dispdiv = Func.decos.getStr("dispdiv");
				if(dispdiv.contains("+"))
				{
					String tmp2 = dispdiv.substring(0,dispdiv.lastIndexOf("+"));
					String tmp3 = dispdiv.substring(dispdiv.lastIndexOf("+")+1,dispdiv.length());

					if(tmp3.compareTo("att") == 0)
					{
						html_env.ajaxtarget = tmp2 + "_" + Func.getAtt("att");
					}
					else
						html_env.ajaxtarget = dispdiv;
				}
				else
				{
					html_env.ajaxtarget = dispdiv;
				}
				html_env.has_dispdiv = true;
				Log.out("html_env.ajaxtarget:"+html_env.ajaxtarget);
			}
			else if(Func.decos.containsKey("dragto"))
			{
				Log.out("draggable = ture");
				html_env.draggable = true;


				//drag to
				String value = Func.decos.getStr("dragto");
				String[] droptarget = new String[100];
				int targetnum = 0;

				if(value.contains("+"))
				{
					while(true)
					{
						if(!value.contains("+"))
						{
							droptarget[targetnum] = value;
							targetnum++;
							break;
						}
						droptarget[targetnum] = value.substring(0,value.indexOf("+"));
						value = value.substring(value.indexOf("+")+1,value.length());

						targetnum++;
					}
				}else
					droptarget[0] = value;


				//script ����
				Date d1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
				String today = sdf.format(d1);

				String scriptname = "drop"+today + html_env.scriptnum;
				html_env.script.append(scriptname+" = new DragDrop(\""+
						html_env.dragdivid+"\", \""+droptarget[0]+"\");\n");

				Log.out(scriptname+" = new DragDrop(\""+
						html_env.dragdivid+"\", \""+droptarget[0]+"\");\n");

				//for tab
				html_env.script.append(scriptname+".addToGroup(\"myTab\");\n");

				for(int i = 1; i < targetnum ; ++i)
				{
					html_env.script.append(scriptname+".addToGroup(\""+droptarget[i]+"\");\n");
				}

				html_env.scriptnum++;
			}
		}
		if(Func.getArg(0) instanceof FuncArg)
		{
			Log.out("ARGS are function");
			FuncArg fa = (FuncArg) Func.getArg(0);
			fa.workAtt();
		}
		else
			Func.workAtt("default");

		html_env.sinvoke_flag = false;
		return;
	}

	public static String opt(String s){
		if(s.contains("\"")){
			s = s.replaceAll("\"","");
		}
		if(s.startsWith("./")){
			s = s.substring(2,s.length());
		}
		if(s.startsWith("/")){
			s = s.substring(1,s.length());
		}
		return s;
	}

	//20130920
	private static String getValue(int x, Mobile_HTML5Function Func) {
		try{
			String str = ((FuncArg) Func.getArg(x-1)).getStr();	//第x引数
			if(!str.equals(""))	return str;
			else				return "";
		}catch(Exception e){
			return "";
		}
	}
	private static int getIntValue(int x) {
		try{
			return Integer.parseInt(getValue(x, null));
		}catch(Exception e){
			return Integer.MIN_VALUE;
		}
	}

	private static boolean isImage(String detailORurl) {
		detailORurl = detailORurl.toLowerCase().trim();
		if(detailORurl.endsWith(".gif")
				|| detailORurl.endsWith(".jpg")
				|| detailORurl.endsWith(".jpeg")
				|| detailORurl.endsWith(".png")
				|| detailORurl.endsWith(".tif")
				|| detailORurl.endsWith(".tiff")
				)	return true;
		return false;
	}

	//20131118 dynamic
	private static String getCount(int count){
		return count+Mobile_HTML5_dynamic.getDynamicLabel();
	}
	public static int getGlvl() {
		return glvl;
	}
	public static void setGlvl(int glvl) {
		Mobile_HTML5Function.glvl = glvl;
	}

}
