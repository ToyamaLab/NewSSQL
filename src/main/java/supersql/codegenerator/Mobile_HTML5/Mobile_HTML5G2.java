package supersql.codegenerator.Mobile_HTML5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Sass;
import supersql.codegenerator.TFE;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Responsive.Responsive;
import supersql.codegenerator.infinitescroll.Infinitescroll;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Embed;

public class Mobile_HTML5G2 extends Grouper {

	Manager manager;

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	public boolean firstFlg = false;	//20161203 bootstrap

	//added by goto 20130413  "row Prev/Next"
	public int j = 1;
	public int row = 1;		//1ページごとの行数指定 (Default: 1, range: 1〜)
	public int rowNum = 0;
	public static int rowFileNum = 1;
	public boolean rowFlg = false;
	public StringBuffer codeBuf = new StringBuffer();

	public static boolean divFlg = false;		//20130326  div

	public static boolean table0Flg = false;	//20130325  table0

	public static boolean tableFlg = false;		//20130314  table
	public static String tableStartTag = "";

	static String tableDivHeader = "";			//20131001 tableDivHeader
	static String tableDivHeader_codeBuf = "";	//20131001 tableDivHeader
	public static int tableDivHeader_Count1 = 0;		//20131001 tableDivHeader
	static int tableDivHeader_Count2 = 0;		//20131001 tableDivHeader

	//���󥹥ȥ饯��
	public Mobile_HTML5G2(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;

	}

	//G2��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		//if(Mobile_HTML5G3.G3 && Mobile_HTML5G3.G3_while_i>0)  	return null;	//TODO
		if(!Mobile_HTML5.preProcess(getSymbol(), decos, html_env)) return null;	//Pre-process (前処理)

		//20131001 tableDivHeader
		Mobile_HTML5G2.tableDivHeader = "";
		Mobile_HTML5G2.tableDivHeader_codeBuf = "";
		Mobile_HTML5G2.tableDivHeader_Count1 = 0;
		Mobile_HTML5G2.tableDivHeader_Count2 = 0;

		Mobile_HTML5G1.G1_count = 0;

		int panelFlg = 0;	//20130503  Panel

		//added by goto 20130413  "row Prev/Next"
		//1ページごとの行数指定 (Default: 1, range: 1〜)
		String parentfile = null;
		String parentnextbackfile = null;
		StringBuffer parentcode = null;
		StringBuffer parentcss = null;
		StringBuffer parentheader = null;
		StringBuffer parentfooter = null;
		if(decos.containsKey("row") && !Mobile_HTML5_dynamic.dynamicDisplay){
			row = Integer.parseInt(decos.getStr("row").replace("\"", ""));
			if(row<1){	//範囲外のとき
				Log.err("<<Warning>> row指定の範囲は、1〜です。指定された「row="+row+"」は使用できません。");
			}else{
				parentfile = html_env.filename;
				parentnextbackfile = html_env.nextbackfile;
				parentcode = html_env.code;
				parentheader = html_env.header;
				parentfooter = html_env.footer;
				html_env.header = new StringBuffer();
				html_env.footer = new StringBuffer();
				rowFlg = true;
			}
		}
		else if(decos.containsKey("row") && !Mobile_HTML5_stream.streamDisplay){
			row = Integer.parseInt(decos.getStr("row").replace("\"", ""));
			if(row<1){	//範囲外のとき
				Log.err("<<Warning>> row指定の範囲は、1〜です。指定された「row="+row+"」は使用できません。");
			}else{
				parentfile = html_env.filename;
				parentnextbackfile = html_env.nextbackfile;
				parentcode = html_env.code;
				parentheader = html_env.header;
				parentfooter = html_env.footer;
				html_env.header = new StringBuffer();
				html_env.footer = new StringBuffer();
				rowFlg = true;
			}
		}

		Log.out("------- G2 -------");
		this.setDataList(data_info);

		//taji add
		String classid = Mobile_HTML5Env.getClassID(this);
		if (Ehtml.isInfinitescroll()) {
			Infinitescroll.G2(this, html_env, html_env2, data_info, data, tfe);
			return null;
		} else {
			boolean isEhtml = Ehtml.isEhtml();

			if (isEhtml) {
				String row = "";
				String column = "";
				String scroll = "";

				if(decos.containsKey("infinite-scroll")){
					scroll = " scroll=\'id'";//TODO id->変数にして<div>タグのidを作成
				}
				// ページネーション
				if (decos.containsKey("row") && decos.containsKey("column")) {
					html_env.g2PaginationRowNum = Integer.parseInt(decos
							.getStr("row"));
					row = " row=\'" + html_env.g2PaginationRowNum + "\'";
					html_env.g2PaginationColumnNum = Integer.parseInt(decos
							.getStr("column"));
					column = " column=\'" + html_env.g2PaginationColumnNum + "\'";
				} else if (decos.containsKey("row")) { // 複合反復子
					html_env.g2RetNum = Integer.parseInt(decos.getStr("row"));
					row = " row=\'" + html_env.g2RetNum + "\'";
				}
				// if (decos.containsKey("row") && decos.containsKey("column")) {
				// html_env.itemNumPerPage = Integer.parseInt(decos.getStr("row"));
				// row = " row=\'"
				// + html_env.itemNumPerPage
				// + "\'";
				// }
				String outType = "div";
				if (html_env.xmlDepth != 0) {
					// 親のoutTypeを継承
					outType = html_env.outTypeList.get(html_env.xmlDepth - 1);
				}
				if (decos.containsKey("table") || !outType.equals("div")) {
					html_env.outTypeList.add(html_env.xmlDepth, "table");
				} else { // デフォルト -> div
					html_env.outTypeList.add(html_env.xmlDepth, "div");
				}
				if (decos.containsKey("div")) {
					html_env.outTypeList.add(html_env.xmlDepth, "div");
				}
				// System.out.println("G2 tableFlg = " + tableFlg + ", divFlg = " +
				// divFlg);
				html_env.append_css_def_td(html_env.getClassID(this), this.decos);
				// add taji for infinite scroll 20170203
				Incremental.outXMLData(html_env.xmlDepth, "<Grouper"
						+ html_env.gLevel + " type=\'G2\' outType=\'" + html_env.outTypeList.get(html_env.xmlDepth) + "\'"
						+ " class=\'" + html_env.getClassID(this) + "\'" + row + column + scroll
						+ ">\n");
			}
			
			
			if(Mobile_HTML5Env.getSelectFlg())
			data_info = (ExtList) data_info.get(0);
			html_env.append_css_def_td(classid, this.decos);

			//20130325  table0
			if(decos.containsKey("table0"))	Mobile_HTML5G2.table0Flg = true;
			else							Mobile_HTML5G2.table0Flg = false;
			//20130314  table
			if(decos.containsKey("table") || Mobile_HTML5G2.table0Flg || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg){
				Mobile_HTML5G2.tableFlg = true;
			}

			//20130326  div
			if(decos.containsKey("div")){
				Mobile_HTML5G2.divFlg = true;
				Mobile_HTML5G2.tableFlg = false;
			}

			//20130914  "text"
			if(decos.containsKey("text")){
				Mobile_HTML5Function.textFlg2 = true;
			}

			//20161203 bootstrap
			if(Sass.isFirstElementFlg()){
				firstFlg = true;
				Sass.firstElementFlg(false);
			}

			if(!GlobalEnv.isOpt()){
				if(!Sass.isBootstrapFlg()){
					//20130503  Panel
					panelFlg = Mobile_HTML5C1.panelProcess1(decos, html_env);

					//20130330 tab
					//tab1
					if(decos.containsKey("tab1")){
						html_env.code.append("<div data-role=\"content\"> <div id=\"tabs\">\n<ul>\n");
						html_env.code.append("	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">");
						if(!decos.getStr("tab1").equals(""))	html_env.code.append(decos.getStr("tab1"));
						else          							html_env.code.append("tab1");
						html_env.code.append("</a></li>\n");
						html_env.code.append("</ul>\n<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
					}
					//tab2〜tab15
					else{
						int i=2;
						while(i<=Mobile_HTML5Env.maxTab){		//HTMLEnv.maxTab=15
							if(decos.containsKey("tab"+i) || (i==2 && decos.containsKey("tab"))){
								//replace: </ul>の前に<li>〜</li>を付加
								String a = "</ul>";
								String b = "	<li><a href=\"#tabs-"+Mobile_HTML5Env.tabCount+"\">";
								if(decos.containsKey("tab"+i))
								if(!decos.getStr("tab"+i).equals(""))	b += decos.getStr("tab"+i);
								else				            		b += "tab"+i;
								else
								if(!decos.getStr("tab").equals(""))		b += decos.getStr("tab");
								else				            		b += "tab";
								b += "</a></li>\n";
								Mobile_HTML5Manager.replaceCode(html_env, a, b+a);

								//replace: 最後の</div></div></div>カット
								Mobile_HTML5Manager.replaceCode(html_env, "</div></div></div>", "");

								//    	        		//replace: 不要な「<div class=〜」をカット
								//    	        		Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+Mobile_HTML5Env.getClassID(this)+" \">", "");

								html_env.code.append("<div id=\"tabs-"+Mobile_HTML5Env.tabCount+"\">\n");
								break;
							}
							i++;
						}
					}

					//20130312 collapsible
					if(decos.containsKey("collapse")){
						html_env.code.append("<DIV data-role=\"collapsible\" data-content-theme=\"c\" style=\"padding: 0px 12px;\">\n");

						//header
						if(!decos.getStr("collapse").equals(""))
						html_env.code.append("	<h1>"+decos.getStr("collapse")+"</h1>\n");
						else
						html_env.code.append("<h1>Contents</h1>\n");
					}

					//20130309
					//20130314  table
					if(Mobile_HTML5G2.tableFlg){
						if(row>1 && Mobile_HTML5G2.tableFlg)	Mobile_HTML5G2.tableStartTag = Mobile_HTML5C1.getTableStartTag(html_env, decos, this);
						else					html_env.code.append(Mobile_HTML5C1.getTableStartTag(html_env, decos, this)+"\n");
					}
				}else if(Sass.isBootstrapFlg()){
					if(firstFlg){
						html_env.code.append("<DIV Class=\"row\">\n");
						html_env.code.append("<DIV Class=\""+classid+"\">\n");
						if (isEhtml) {
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid + "\'>\n");
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid + "\' outType=\'div\' class=\'" + classid + "\'>\n");
						}

						if(Sass.outofloopFlg.peekFirst()){
							Sass.makeColumn(classid, decos, "", -1);
						}
					}
					Sass.beforeLoop();
				}
			}

			//        Mobile_HTML5_form.G2_dataQuantity = this.data.size();
			Mobile_HTML5.beforeWhileProcess(getSymbol(), decos, html_env);
			while (this.hasMoreItems()) {
				if (isEhtml) {
					html_env.gLevel++;
					html_env.xmlDepth++;
				}
				
				Mobile_HTML5.gLevel1++;
				Mobile_HTML5.whileProcess1_1(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);


				String classid2 = Mobile_HTML5Env.getClassID(tfe);
				DecorateList decos2 = ((TFE)tfe).decos;

				Mobile_HTML5Function.setGlvl(html_env.getGlevel());	//added by goto 20130914  "SEQ_NUM"



				//[重要] For [ [], ]!
				Mobile_HTML5G1.jj = 0;
				Mobile_HTML5G1.gridInt = 0;

				if(decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg)	Mobile_HTML5G2.table0Flg = true;
				if(decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.table0Flg)	Mobile_HTML5G2.tableFlg=true;
				if(decos.containsKey("div")){
					Mobile_HTML5G2.divFlg = true;
					Mobile_HTML5G2.tableFlg = false;
				}

				html_env.setGlevel(html_env.getGlevel() + 1);

				//added by goto 20130413  "row Prev/Next"
				if(rowFlg){
					html_env.code = new StringBuffer();
					html_env.countfile++;
					html_env.filename = html_env.outfile + "_row" + rowFileNum + "_" + j + Compiler.getExtension();
					html_env.nextbackfile = html_env.linkoutfile + "_row" + rowFileNum + "_" + j + Compiler.getExtension();
					html_env.setOutlineMode();
				}

				Log.out("selectFlg"+Mobile_HTML5Env.getSelectFlg());
				Log.out("selectRepeatFlg"+Mobile_HTML5Env.getSelectRepeat());
				Log.out("formItemFlg"+Mobile_HTML5Env.getFormItemFlg());
				if( Mobile_HTML5Env.getSelectRepeat() ){//if form_select
				}else{
					if(!Sass.isBootstrapFlg()){
						//20130312 collapsible
						if(decos.containsKey("collapse"))
						html_env.code.append("<p>\n");
						//20130309
						if(!Mobile_HTML5G2.tableFlg)
						html_env.code.append("\n<div class=\""+classid+" "+Mobile_HTML5_show.addShowCountClassName(decos)+"\">\n");	//20130309  div
						else if(Mobile_HTML5G2.tableFlg){
							//20130314  table
							html_env.code.append("<TR><TD class=\"" + classid + " "+Mobile_HTML5_show.addShowCountClassName(decos)+" nest\">\n");
							Log.out("<TR><TD class=\"" + classid + " nest\">");
						}
					}else if(Sass.isBootstrapFlg()){
						html_env.code.append("<DIV Class=\"row\">\n");
						html_env.code.append("<div class=\"" + classid2 +"\">\n");
						if (isEhtml) {
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid2 + "\'>\n");
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid2 + "\' outType=\'div\' class=\'" + classid2 + "\'>\n");
						}
						if(Sass.outofloopFlg.peekFirst()){
							Sass.makeColumn(classid2, decos2, "", -1);
						}
					}
				}

				Mobile_HTML5.whileProcess1_2(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);
				this.worknextItem();
				Mobile_HTML5.whileProcess2_1(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);

				if(decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg)	Mobile_HTML5G2.table0Flg = true;
				if(decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.table0Flg)	Mobile_HTML5G2.tableFlg=true;
				if(decos.containsKey("div")){
					Mobile_HTML5G2.divFlg = true;
					Mobile_HTML5G2.tableFlg = false;
				}

				if(Mobile_HTML5Env.getSelectRepeat()){

				}else{
					html_env2.code.append("</tfe>");
					if(!Sass.isBootstrapFlg()){
						//added by goto 20130110 start
						if(Mobile_HTML5Function.slideshowFlg==true){
							html_env.code.append("</ul>\n</div>\n");
						}
						//20160527 bootstrap
						//added by goto 20130110 end
						if(!Mobile_HTML5G2.tableFlg){
							if(!Mobile_HTML5Function.textFlg2){
								html_env.code.append("</div>\n");		//20130309  div	//20130914  "text"
							}
						}else if(Mobile_HTML5G2.tableFlg){
							html_env.code.append("</TD></TR>\n");			//20130314  table
							Log.out("</TD></TR>");
						}
					}else if(Sass.isBootstrapFlg()){
						html_env.code.append("</div>\n");//classid2
						html_env.code.append("</div>\n");//row
						if (isEhtml) {
							Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
							Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
						}
						if(Sass.outofloopFlg.peekFirst()){
						}
					}

					html_env.code = Embed.postProcess(html_env.code);	//goto 20130915-2  "<$  $>"

					//20130312 collapsible
					if(decos.containsKey("collapse"))
					html_env.code.append("</p>\n");
				}
				//20160527 bootstrap
				if(Sass.isBootstrapFlg()){
					Sass.afterFirstLoop();
				}
				html_env.setGlevel(html_env.getGlevel() - 1);

				Mobile_HTML5G2.tableDivHeader_Count1++;	//20131001 tableDivHeader

				//added by goto 20130413  "row Prev/Next"
				if(rowFlg){
					codeBuf.append(html_env.code);
					if((rowNum+1)%row==0){
						createHTMLfile_ForPrevNext(html_env, codeBuf);
						j++;
						codeBuf = new StringBuffer();
					}
					rowNum++;
				}


				if(!Mobile_HTML5.whileProcess2_2(getSymbol(), decos, html_env, data, data_info, tfe, null, -1))	break;
				Mobile_HTML5.gLevel1--;
				
				if (isEhtml) {
					html_env.gLevel--;
					html_env.xmlDepth--;
					if (decos.containsKey("row") && decos.containsKey("column")) {
						html_env.itemCount++;
					}
				}
			}	// /while
			//20160527 bootstrap
			Mobile_HTML5.afterWhileProcess(getSymbol(), classid, decos, html_env);
			if (Sass.isBootstrapFlg()){
				Sass.afterLoop();
				if(firstFlg){
					html_env.code.append("</DIV>\n");//.classid
					html_env.code.append("</DIV>\n");//.row
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
						Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
					}

					if(Sass.outofloopFlg.peekFirst()){
					}
					firstFlg = false;
				}
			}

			//added by goto 20130413  "row Prev/Next"
			if(rowFlg){
				if(rowNum%row!=0){	//最後の child HTML を create
					createHTMLfile_ForPrevNext(html_env, codeBuf);
				}
				//ファイル名・コード等をparent HTMLのものへ戻す
				html_env.filename = parentfile;
				html_env.code = parentcode;
				html_env.header = parentheader;
				html_env.footer = parentfooter;
				html_env.nextbackfile = parentnextbackfile;
				Log.out("TFEId = " + classid);
				html_env.append_css_def_td(classid, this.decos);

				int first = 1, last = ((rowNum%row!=0)? (rowNum/row+1):(rowNum/row));
				PrevNextProcess(html_env, rowNum, row, first, last, 1);
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
				if(!(row>1 && Mobile_HTML5G2.tableFlg))	html_env.code.append("</TABLE>\n");		//20130309
				Mobile_HTML5G2.tableFlg = false;
				Mobile_HTML5G2.table0Flg = false;		//20130325 table0
			}
			Log.out("</TABLE>");

			//20130312 collapsible
			if(decos.containsKey("collapse")){
				html_env.code.append("</DIV>");
			}

			//20130330 tab
			int a=1;
			while(a<=Mobile_HTML5Env.maxTab){
				if(decos.containsKey("tab"+a) || (a==1 && decos.containsKey("tab"))){
					html_env.code.append("</div></div></div>\n");
					Mobile_HTML5Env.tabCount++;
					break;
				}
				a++;
			}

			//20130503  Panel
			Mobile_HTML5C1.panelProcess2(decos, html_env, panelFlg);

			if(Mobile_HTML5G2.divFlg)	Mobile_HTML5G2.divFlg = false;		//20130326  div

			//added by goto 20130413  "row Prev/Next"
			if(rowFlg){
				rowFileNum++;
				rowFlg = false;
				tableStartTag = "";
			}

			//20130914  "text"
			if(Mobile_HTML5Function.textFlg2){
				Mobile_HTML5Function.textFlg2 = false;
			}

			Mobile_HTML5.postProcess(getSymbol(), classid, decos, html_env);	//Post-process (後処理)

			//added by goto 20130914  "SEQ_NUM"
			Mobile_HTML5Function.Func_seq_num_initialization(html_env.getGlevel());

			Log.out("TFEId = " + classid);
			if (isEhtml) {
				Incremental.outXMLData(html_env.xmlDepth, "</Grouper" + html_env.gLevel + ">\n");
			}
			return null;
		}
	}

	//added by goto 20130413  "row Prev/Next"
	public static void createHTMLfile_ForPrevNext(Mobile_HTML5Env html_env, StringBuffer codeBuf){
		html_env.getHeader(2);
		html_env.getFooter(2);

		if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
			try {
				PrintWriter pw;
				if (html_env.charset != null)
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(html_env.filename),html_env.charset)));
				else
				pw = new PrintWriter(new BufferedWriter(new FileWriter(
				html_env.filename)));
				pw.println(html_env.header);
				pw.println(Mobile_HTML5G2.tableDivHeader);	//20131001 tableDivHeader
				if(!tableStartTag.equals("")){
					pw.println(tableStartTag+"\n"+codeBuf);
					if(tableStartTag.endsWith("<TR>"))	pw.println("</TR>");
					pw.println("</TABLE>\n");
				}else{
					pw.println(codeBuf);
				}
				pw.println(html_env.footer);
				pw.close();
				html_env.header = new StringBuffer();
				html_env.footer = new StringBuffer();
			} catch (Exception e) { }
		}
	}

	//added by goto 20130413  "row Prev/Next"
	public static void PrevNextProcess(Mobile_HTML5Env html_env, int rowNum, int row, int first, int last, int column){
		//parent HTMLへ<iframe>等を埋め込む
		String divID="rowDiv"+rowFileNum+"-";
		String iframeName ="rowIframe"+rowFileNum;
		Log.info("aaa"+html_env.filename);
		String HTMLfilename=html_env.filename.substring(0,html_env.filename.indexOf(Compiler.getExtension()));
		//added by goto 20130417 start
		//HTMLfilenameを絶対パスから「相対パス形式」へ変更
		String fileDir = new File(HTMLfilename).getAbsoluteFile().getParent();
		if(fileDir.length() < HTMLfilename.length()
		&& fileDir.equals(HTMLfilename.substring(0,fileDir.length()))){
			HTMLfilename = HTMLfilename.substring(fileDir.length()+1);
		}
		//added by goto 20130417 end
		HTMLfilename = HTMLfilename+"_row"+rowFileNum+"_";

		if(!Sass.isBootstrapFlg()){
			html_env.code.append(
			"	<script type=\"text/javascript\">\n" +
			"		$(document).ready(function(){\n" +
			"			rowIframePrevNext("+first+", "+last+", '"+divID+"', '"+iframeName+"', '"+HTMLfilename+"', '"+row+"', '"+rowNum+"', '"+column+"');\n" +
			"		});\n" +
			"	</script>\n" +
			"	<hr>\n" +
			"	<div id=\""+divID+"1\"></div>\n" +
			"	<hr>\n" +
			"	<iframe src=\""+HTMLfilename+"1.html\" id=\"rowIframeAutoHeight"+rowFileNum+"\" name=\""+iframeName+"\" style=\"border:0; width:90%; overflow:hidden;\">\n" +
			"	</iframe>\n" +
			"	<script type=\"text/javascript\">\n" +
			"	    $('#rowIframeAutoHeight"+rowFileNum+"').iframeAutoHeight();\n" +
			"	</script>\n" +
			"	<hr>\n" +
			"	<div id=\""+divID+"2\"></div>\n" +
			"	<hr>\n");
		}else if(Sass.isBootstrapFlg()){
			String paginationClass = "sync-pagination"+rowFileNum;
			String paginationContentClass = "paginationContent"+rowFileNum;

			html_env.code.append(
			"	<div class=\"row\">\n" +
			"	<ul class=\""+paginationClass+"\"></ul>\n" +
			"	</div>\n" +
			"	<div class=\""+paginationContentClass+"\">Dynamic page content</div>\n" +
			"	<div class=\"row\">\n" +
			"	<ul class=\""+paginationClass+"\"></ul>\n" +
			"	</div>\n" +
			"	<script type=\"text/javascript\">\n" +
			"		$(document).ready(function(){\n" +
			"			paginationForBootstrap("+first+", "+last+", '"+paginationClass+"', '"+paginationContentClass+"', '"+HTMLfilename+"');\n" +
			"		});\n" +
			"	</script>\n");
		}
	}

	//20131001 tableDivHeader
	//for C1, C2
	public static StringBuffer createAndCutTableDivHeader(Mobile_HTML5Env html_env) {
		if(Mobile_HTML5G2.tableDivHeader_Count1<1){
			//create tableDivHeader
			Mobile_HTML5G2.tableDivHeader = html_env.code.toString().replace(Mobile_HTML5G2.tableDivHeader_codeBuf, "");
		}
		return new StringBuffer(html_env.code.replace(Mobile_HTML5G2.tableDivHeader_codeBuf.length(),
		html_env.code.length(), ""));
	}

	@Override
	public String getSymbol() {
		return "Mobile_HTML5G2";
	}

}
