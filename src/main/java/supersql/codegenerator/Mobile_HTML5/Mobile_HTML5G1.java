package supersql.codegenerator.Mobile_HTML5;

import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Sass;
import supersql.codegenerator.TFE;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.infinitescroll.Infinitescroll;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Mobile_HTML5G1 extends Grouper {

	Manager manager;

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	public static int G1_count = 0;

	//20130309
	public static int gridInt = 0;
	public static int ii =0;

	public static int jj = 0;

	public static int Count = 0;
	public static boolean G1Flg = false;
	public int numberOfColumns = 0;		//1行ごとのカラム数 (range: 2〜)
	public int table_column_num = 0;		//20130917  [ ],10@{table}

	public static boolean tableFlg = false;		//20130314  table
	public static boolean table0Flg = false;		//20130325  table0
	public static boolean divFlg = false;			//20130326  div

	public boolean firstFlg = false;	//20161203 bootstrap

	//added by goto 20130413  "row Prev/Next"
	public int j = 1;
	public int row = 1;		//1ページごとの行数指定 (Default: 1, range: 1〜)
	public int rowNum = 0;
	public boolean rowFlg = false;
	public StringBuffer codeBuf = new StringBuffer();

	static String classid = "" ;

	//���󥹥ȥ饯��
	public Mobile_HTML5G1(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;

	}

	//G1��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		//if(Mobile_HTML5G3.G3 && Mobile_HTML5G3.G3_while_i>0)  	return null;	//TODO

		G1_count++;
		Mobile_HTML5Attribute.attributeHasWidth = false;

		if(!Mobile_HTML5.preProcess(getSymbol(), decos, html_env))	return null;	//Pre-process (前処理)

		//20131001 tableDivHeader
		Mobile_HTML5G2.tableDivHeader = "";
		Mobile_HTML5G2.tableDivHeader_codeBuf = "";
		Mobile_HTML5G2.tableDivHeader_Count1 = 0;
		Mobile_HTML5G2.tableDivHeader_Count2 = 0;

		int panelFlg = 0;	//20130503  Panel

		Sass.incrementId();
		int responsiveId = Sass.responsiveCandidateId;	//20161217 bootstrap

		//1行ごとのカラム数 (range: 2〜)
		boolean columnFlg = false;
		if(tableFlg)	numberOfColumns = -1;	//@{table}時のDefault	//20130917  [ ],10@{table}
		else			numberOfColumns = data_info.size();	//div
		if(decos.containsKey("column") && !Mobile_HTML5_dynamic.dynamicDisplay){
			try{
				numberOfColumns = Integer.parseInt(decos.getStr("column").replace("\"", ""));
				if(numberOfColumns<2){
					Log.err("<<Warning>> column指定の範囲は、2〜です。指定された「column="+numberOfColumns+"」は使用できません。");
					if(tableFlg)	numberOfColumns = -1;							//20130917  [ ],10@{table}
					else			numberOfColumns = data_info.size();	//div
				}else columnFlg = true;
			}catch(Exception e){ }
		}
		else if(decos.containsKey("column") && !Mobile_HTML5_stream.streamDisplay){
			try{
				numberOfColumns = Integer.parseInt(decos.getStr("column").replace("\"", ""));
				if(numberOfColumns<2){
					Log.err("<<Warning>> column指定の範囲は、2〜です。指定された「column="+numberOfColumns+"」は使用できません。");
					if(tableFlg)	numberOfColumns = -1;							//20130917  [ ],10@{table}
					else			numberOfColumns = data_info.size();	//div
				}else columnFlg = true;
			}catch(Exception e){ }
		}

		//added by goto 20130413  "row Prev/Next"
		//1ページごとの行数指定 (Default: 1, range: 1〜)
		String parentfile = null;
		String parentnextbackfile = null;
		StringBuffer parentcode = null;
		StringBuffer parentheader = null;
		StringBuffer parentfooter = null;
		if(decos.containsKey("row") && columnFlg && !Mobile_HTML5_dynamic.dynamicDisplay){
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
		if(decos.containsKey("row") && columnFlg && !Mobile_HTML5_stream.streamDisplay){
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

		Log.out("------- G1 -------");
		this.setDataList(data_info);

		String classid = Mobile_HTML5Env.getClassID(this);
		String classid2 = Mobile_HTML5Env.getClassID(tfe);


		if (Ehtml.infinitescroll_flag && (Incremental.flag || Ehtml.flag)) {
			Infinitescroll.G1(this, html_env, data_info, data, tfe);
			return null;
		}else{
			boolean isEhtml = Ehtml.isEhtml();
			
			if (isEhtml) {
				String row = "";
				String column = "";
				
				// ページネーション
				if (decos.containsKey("row") && decos.containsKey("column")) {
					html_env.g1PaginationRowNum = Integer.parseInt(decos
							.getStr("row"));
					row = " row=\'" + html_env.g1PaginationRowNum + "\'";
					html_env.g1PaginationColumnNum = Integer.parseInt(decos
							.getStr("column"));
					column = " column=\'" + html_env.g1PaginationColumnNum + "\'";
				} else if (decos.containsKey("column")) { // 複合反復子
					html_env.g1RetNum = Integer.parseInt(decos.getStr("column"));
					column = " column=\'" + html_env.g1RetNum + "\'";
				}
//				if (decos.containsKey("row") && decos.containsKey("column")) {
//					html_env.itemNumPerPage = Integer.parseInt(decos.getStr("row"));
//					column = " row=\'"
//						+ html_env.itemNumPerPage
//						+ "\'";
//				}
				String outType = "div";
				if (html_env.xmlDepth != 0) {
					// 親のoutTypeを継承
					outType = html_env.outTypeList.get(html_env.xmlDepth - 1);
				}
				if (decos.containsKey("table") || !outType.equals("div")) {
					html_env.outTypeList.add(html_env.xmlDepth, "table");
				} else {
					html_env.outTypeList.add(html_env.xmlDepth, "div");
				}
				if (decos.containsKey("div")) {
					html_env.outTypeList.add(html_env.xmlDepth, "div");
				}
				Log.info("out:"+html_env.outTypeList);

				// System.out.println("G1 tableFlg = " + tableFlg + ", divFlg = " +
				// divFlg);
				html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
				Incremental.outXMLData(html_env.xmlDepth, "<Grouper"
						+ html_env.gLevel + " type=\'G1\' outType=\'"
						+ html_env.outTypeList.get(html_env.xmlDepth)
						+ "\' class=\'" + HTMLEnv.getClassID(this) + "\'" + row + column + ">\n");
			}
			
			
			html_env.append_css_def_td(classid, this.decos);
			//20130309
			G1Flg=true;

			//☆重要★
			gridInt = 0;

			//20130325  table0
			if(decos.containsKey("table0"))	table0Flg = true;
			else							table0Flg = false;
			//20130314  table
			if(decos.containsKey("table") || table0Flg || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G2.tableFlg){
				tableFlg = true;
			}//else	tableFlg = false;

			//20130326  div
			if(decos.containsKey("div")){
				divFlg = true;
				tableFlg = false;
			}//else divFlg = false;

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

								//replace: 不要な「<div class=〜」をカット
								Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+classid+" \">", "");

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
					if(!tableFlg){
						//        		if(html_env.written_classid.contains(classid))
						html_env.code.append("\n<DIV Class=\"ui-grid ##"+Mobile_HTML5Env.uiGridCount2+" "+classid+"\"");
						//        		else
						//        			html_env.code.append("\n<DIV Class=\"ui-grid ##"+Mobile_HTML5Env.uiGridCount2+"\"");
						html_env.code.append(">\n");
						Mobile_HTML5Env.uiGridCount2++;
					}

					//20130314  table
					if(tableFlg){
						//added by goto 20130318  横スクロール
						if(numberOfColumns < 0)	html_env.code.append("<div style=\"overflow:auto;\">\n");	//20130917  [ ],10@{table}

						if(row>1 && tableFlg)	Mobile_HTML5G2.tableStartTag = Mobile_HTML5C1.getTableStartTag(html_env, decos, this)+"<TR>";
						else					html_env.code.append(Mobile_HTML5C1.getTableStartTag(html_env, decos, this)+"<TR>");
					}
				}else if(Sass.isBootstrapFlg()){
					//        		if(!decos.containsKey("C1") && !decos.containsKey("G1")){
					//            		html_env.code.append("<DIV Class=\"row\">");
					//            		if(Sass.outofloopFlg.peekFirst()){
					//            			Sass.makeRowClass();
					//            		}
					//            	}
					//
					//        		html_env.code.append("<DIV Class=\""+classid+"\">");
					//        		html_env.code.append("<DIV Class=\"row\">");
					//        		if(Sass.outofloopFlg.peekFirst()){
					//        			Sass.makeClass(classid);
					//        			Sass.defineGridBasic(classid, decos);
					//        			Sass.makeRowClass();
					//	      		}
					//        		Sass.beforeLoop();

					if(firstFlg){
						html_env.code.append("<DIV Class=\"row\">\n");
						html_env.code.append("<DIV Class=\""+classid+"\">\n");
						if (isEhtml) {
//							html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid + "\'>\n");
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid + "\' outType=\'div\' class=\'" + classid + "\'>\n");
						}
						if(Sass.outofloopFlg.peekFirst()){
							//        				Sass.makeClass(classid);
							//        				Sass.defineGridBasic(classid, decos);
							//        				Sass.closeBracket();
							Sass.makeColumn(classid, decos, "", -1);
						}
					}

					html_env.code.append("<DIV Class=\"row\">\n");
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid + "\'>\n");
					}
					if(Sass.outofloopFlg.peekFirst()){
						//        			Sass.makeRowClass();
					}

					Sass.beforeLoop();

					//added 161125 taji for @slide
					if(decos.containsKey("slide")){
						html_env.code.append("<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">\n"
								+ "<!-- Indicators -->\n"
								+ "<ol class=\"carousel-indicators\">\n");
						for(int i = 0; i < this.numberOfColumns; i++){
							if(i == 0){
								html_env.code.append("<li data-target=\"#carousel-example-generic\" data-slide-to=\""+ i +"\" class=\"active\"></li>\n");
							}else{
								html_env.code.append("<li data-target=\"#carousel-example-generic\" data-slide-to=\""+ i +"\"></li>\n");
							}
						}
						html_env.code.append("</ol>\n"
								+ "<!-- Wrapper for slides -->\n"
								+ "<div class=\"carousel-inner\" role=\"listbox\">\n");
					}
					//added 161125 taji for @slide
				}
			}

			Mobile_HTML5.beforeWhileProcess(getSymbol(), decos, html_env);
			while (this.hasMoreItems()) {
				if (isEhtml) {
					html_env.gLevel++;
					html_env.xmlDepth++;
				}
				
				Mobile_HTML5.gLevel1++;
				Mobile_HTML5.whileProcess1_1(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);


				Mobile_HTML5Function.setGlvl(html_env.getGlevel());	//added by goto 20130914  "SEQ_NUM"

				if(decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G2.table0Flg)	table0Flg = true;
				if(decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G2.tableFlg || table0Flg)	tableFlg=true;
				if(decos.containsKey("div")){
					divFlg = true;
					tableFlg = false;
				}

				html_env.setGlevel(html_env.getGlevel() + 1);

				//added by goto 20130413  "row Prev/Next"
				if(rowFlg){
					html_env.code = new StringBuffer();
					html_env.countfile++;
					html_env.filename = html_env.outfile + "_row" + Mobile_HTML5G2.rowFileNum + "_" + j + Compiler.getExtension();
					html_env.nextbackfile = html_env.linkoutfile + "_row" + Mobile_HTML5G2.rowFileNum + "_" + j + Compiler.getExtension();
					html_env.setOutlineMode();
				}


				//20130309
				Count = ( ((gridInt>=jj)&&(Mobile_HTML5G1.G1Flg))? jj:gridInt );
				Count %= numberOfColumns;


				//20130917  [ ],10@{table}
				if(tableFlg && numberOfColumns > 1 && Count == 0){
					if(table_column_num>0)	html_env.code.append("</TR><TR>\n");
					else					table_column_num++;
				}

				if(!Sass.isBootstrapFlg()){
					//20130309
					if(!tableFlg){
						//20131002
						//TODO style=を.cssへ
						String divWidth = Mobile_HTML5.getDivWidth("G1", decos, numberOfColumns - Mobile_HTML5Function.func_null_count);	//null()
						if(!decos.containsKey("column")){
							if(Count!=0)	html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\">\n");
							else{
								if(!Mobile_HTML5_dynamic.dynamicDisplay){
									html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\" style=\"clear:left;\">\n");
								}
								else if(!Mobile_HTML5_stream.streamDisplay){
									html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\" style=\"clear:left;\">\n");
								}
								else{
									//html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\" '.(($j++>0)? '' : 'style=\"clear:left;\"').'>\n");
									html_env.code.append("\n<div class=\"ui-block"+" "+classid2+" "+classid2+"-"+G1_count+"\" '.(($i"+(Mobile_HTML5.gLevel0+1)+">0)? '' : 'style=\"clear:left;\"').'>\n");	//TODO d2 change if ?
								}
							}
							if(gridInt<1)	Mobile_HTML5Attribute.attributeDivWidth2 += "."+classid2+"-"+G1_count+"{ width:"+divWidth+"; }\n";
						}else{
							if(Count!=0)	html_env.code.append("\n<div class=\"ui-block"+" "+classid2+"\">\n");
							else			html_env.code.append("\n<div class=\"ui-block"+" "+classid2+"\" style=\"clear:left;\">\n");
							Mobile_HTML5Attribute.attributeDivWidth = divWidth;
						}
						//System.out.println(this.decos+ " "+ divWidth+" "+gridInt);
					}
					//20130314  table
					//20160527 bootstrap
					else if(tableFlg){
						html_env.code.append("<TD class=\"" + classid2 + " nest\">\n");
					}

					classid = classid2;
				}else if(Sass.isBootstrapFlg()){
					(tfe).decos.put("G1",""+(numberOfColumns - Mobile_HTML5Function.func_null_count));
					if (isEhtml) {
						html_env.append_css_def_td(HTMLEnv.getClassID(this), ((TFE)tfe).decos);
					}
					
					html_env.code.append("<div class=\"" + classid2 +"\">\n");
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid2 + "\' outType=\'div\' class=\'" + classid2 + "\'>\n");
					}
					if(Sass.outofloopFlg.peekFirst()){
						//            		Sass.makeClass(classid2);
						//            		Sass.defineGridBasic(classid2, (tfe).decos);

						//            		Sass.makeClass(classid2);
						//            		Sass.defineGridBasic(classid2, (tfe).decos);
						//            		Sass.closeBracket();
						Sass.makeColumn(classid2, (tfe).decos, getSymbol(), responsiveId);
					}
				}

				Mobile_HTML5.whileProcess1_2(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);

				if(this.dindex == 0 && decos.containsKey("slide")){
					tfe.decos.put("slide","true");
				}
				this.worknextItem();
				if(decos.containsKey("slide")){
					tfe.decos.put("slide", "false");
				}

				Mobile_HTML5.whileProcess2_1(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);

				if(decos.containsKey("table0") || Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G2.table0Flg)	table0Flg = true;
				if(decos.containsKey("table") || Mobile_HTML5C1.tableFlg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G2.tableFlg || table0Flg)	tableFlg=true;
				if(decos.containsKey("div")){
					divFlg = true;
					tableFlg = false;
				}

				ii++;
				jj++;
				gridInt++;

				//20160527 bootstrap
				if(!Sass.isBootstrapFlg()){
					if(!tableFlg)	html_env.code.append("</div>");	//20130309
					else if(tableFlg)	        html_env.code.append("</TD>\n");    //20130314 table
				}else if(Sass.isBootstrapFlg()){
					html_env.code.append("</div>\n");//classid2
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
					}
					if(Sass.outofloopFlg.peekFirst()){
						//            		Sass.closeBracket();//classid2
					}
					Sass.afterFirstLoop();
				}

				html_env.setGlevel(html_env.getGlevel() - 1);

				Mobile_HTML5G2.tableDivHeader_Count1++;	//20131001 tableDivHeader

				//added by goto 20130413  "row Prev/Next"
				if(rowFlg){
					codeBuf.append(html_env.code);
					if((rowNum+1)%(row*numberOfColumns)==0){
						Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, codeBuf);
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
			if (Sass.isBootstrapFlg()){
				Sass.afterLoop();
			}

			Mobile_HTML5.afterWhileProcess(getSymbol(), classid2, decos, html_env);

			//added by goto 20130413  "row Prev/Next"
			if(rowFlg){
				if(rowNum%(row*numberOfColumns)!=0){	//最後の child HTML を create
					Mobile_HTML5G2.createHTMLfile_ForPrevNext(html_env, codeBuf);
				}
				//ファイル名・コード等をparent HTMLのものへ戻す
				html_env.filename = parentfile;
				html_env.code = parentcode;
				html_env.header = parentheader;
				html_env.footer = parentfooter;
				html_env.nextbackfile = parentnextbackfile;
				Log.out("TFEId = " + classid);
				html_env.append_css_def_td(classid, this.decos);

				int first = 1, last = ((rowNum%(row*numberOfColumns)!=0)? (rowNum/(row*numberOfColumns)+1):(rowNum/(row*numberOfColumns)));	//for G1
				Mobile_HTML5G2.PrevNextProcess(html_env, rowNum, row, first, last, numberOfColumns);
			}

			/*
//    		//,で結合(水平結合)した際
//    		//replace: 不要な「<div class=〜」をカット
//			String[] s = {"a","b","c","d","e"};
//			int j=0;
//			while(!HTMLManager.replaceCode(html_env, "<div class=\"ui-block-"+s[j]+" "+HTMLEnv.getClassID(this)+"\">", "")){
//				j++;
//				if(j>4) break;
//			}
			 */

			if(Mobile_HTML5Env.getFormItemFlg()){
				Mobile_HTML5Env.incrementFormPartsNumber();
			}

			if(!Sass.isBootstrapFlg()){
				if(!tableFlg)	html_env.code.append("\n</DIV>\n");			//20130309	//20160527 bootstrap
				else if (tableFlg){	//20160527 bootstrap
					if(!(row>1 && tableFlg))	html_env.code.append("</TR></TABLE>\n");	//20130314  table
					tableFlg = false;
					table0Flg = false;		//20130325 table0
					if(numberOfColumns < 0)	html_env.code.append("</div>\n");	//added by goto 20130318  横スクロール		//20130917  [ ],10@{table}
				}
			}else if(Sass.isBootstrapFlg()){
				if(decos.containsKey("slide")){
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
				if (isEhtml) {
					Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
				}
				if(Sass.outofloopFlg.peekFirst()){
					//        		Sass.closeBracket();//row
				}

				if(firstFlg){
					html_env.code.append("</DIV>\n");//.classid
					html_env.code.append("</DIV>\n");//.row
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
						Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
					}
					if(Sass.outofloopFlg.peekFirst()){
						//        			Sass.closeBracket();//classid
						//        			Sass.closeBracket();//row
					}
					firstFlg = false;
				}
			}
			if(divFlg)	divFlg = false;		//20130326  div

			G1Flg=false;
			Log.out("</TR></TABLE>");

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

			//added by goto 20130413  "row Prev/Next"
			if(rowFlg){
				Mobile_HTML5G2.rowFileNum++;
				rowFlg = false;
				Mobile_HTML5G2.tableStartTag = "";
			}

			Mobile_HTML5.postProcess(getSymbol(), classid2, decos, html_env);	//Post-process (後処理)

			//added by goto 20130914  "SEQ_NUM"
			Mobile_HTML5Function.Func_seq_num_initialization(html_env.getGlevel());

			Log.out("TFEId = " + classid);
			
			if (isEhtml) {
				Incremental.outXMLData(html_env.xmlDepth, "</Grouper" + html_env.gLevel + ">\n");
			}
			return null;
		}
	}

	@Override
	public String getSymbol() {
		return "Mobile_HTML5G1";
	}
}
