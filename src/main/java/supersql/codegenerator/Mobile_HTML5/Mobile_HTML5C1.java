package supersql.codegenerator.Mobile_HTML5;

import java.util.LinkedHashMap;


import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Sass;
import supersql.codegenerator.TFE;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.infinitescroll.Infinitescroll;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Mobile_HTML5C1 extends Connector {

	Manager manager;

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	//20130309
	public int gridInt = 0;
	String[] gridString = {"a","b","c","d","e"};
	public static int ii =0;

	public static int jj = 0;

	public static int Count = 0;

	public static boolean tableFlg = false;		//20130314  table
	public static boolean table0Flg = false;		//20130325  table0
	public static boolean divFlg = false;			//20130326  div

	public boolean firstFlg = false;	//20161203 bootstrap
	public LinkedHashMap<String,DecorateList> gridMap = new LinkedHashMap<String,DecorateList>();	//20160916 bootstrap

	//���󥹥ȥ饯��
	public Mobile_HTML5C1(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;
	}

	//C1��work�᥽�å�
	public String work(ExtList data_info) {
		Mobile_HTML5.preProcess(getSymbol(), decos, html_env);	//Pre-process (前処理)
		//Log.i("C1 start");
		//html_env.code.append(" C1:("+Mobile_HTML5.Gdepth+" "+Mobile_HTML5.Gnum+") ");

		//20131001 tableDivHeader
		if(decos.containsKey("header") && Mobile_HTML5G2.tableDivHeader_Count2<1){
			Mobile_HTML5G2.tableDivHeader_codeBuf = html_env.code.toString();
			Mobile_HTML5G2.tableDivHeader_Count2++;
		}

		int panelFlg = 0;	//20130503  Panel

		Sass.incrementId();
		int responsiveId = Sass.responsiveCandidateId;	//20161217 bootstrap


		Log.out("------- C1 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfes.size=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());
		this.setDataList(data_info);

		
		if (Ehtml.infinitescroll_flag && (Incremental.flag || Ehtml.flag)) {
			Infinitescroll.C1(this, html_env, data_info, data, tfes, tfeItems);
			return null;
		
		} else {
//			boolean isEhtml = (!Ehtml.infinitescroll_flag && (Incremental.flag || Ehtml.flag));
			boolean isEhtml = Ehtml.isEhtml();
			
			if (isEhtml) {
				String outType = "div";
				
				if(html_env.xmlDepth!=0){
					// 親のoutTypeを継承
					outType = html_env.outTypeList.get(html_env.xmlDepth-1);
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

//				System.out.println("C1 tableFlg = " + tableFlg + ", divFlg = " + divFlg);
				html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
				Incremental.outXMLData(html_env.xmlDepth,
						"<Connector" + html_env.cNum
								+ " type=\'C1\' outType=\'" + html_env.outTypeList.get(html_env.xmlDepth) + "\' class=\'"
								+ HTMLEnv.getClassID(this) + "\'>\n");
			}
			
			
			if(decos.containsKey("insert")){
				Mobile_HTML5Env.setIDU("insert");
			}
			if(decos.containsKey("update")){
				Mobile_HTML5Env.setIDU("update");
			}
			if(decos.containsKey("delete")){
				Mobile_HTML5Env.setIDU("delete");
			}

			String classid = Mobile_HTML5Env.getClassID(this);
			html_env.append_css_def_td(classid, this.decos);

			//20130325  table0
			if(decos.containsKey("table0"))	table0Flg = true;
			else							table0Flg = false;
			//20130314  table
			if(decos.containsKey("table") || table0Flg || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg){
				tableFlg = true;
			}//else	tableFlg = false;

			//20130326  div
			if(decos.containsKey("div")){
				divFlg = true;
				tableFlg = false;
			}//else divFlg = false;

			//20161203 bootstrap
			if(Sass.isFirstElementFlg()){
				firstFlg = true;
				Sass.firstElementFlg(false);
			}

			if(!GlobalEnv.isOpt()){
				//20160914 bootstrap
				if(!Sass.isBootstrapFlg()){
					//20130503  Panel
					panelFlg = panelProcess1(decos, html_env);

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
						while(i<=Mobile_HTML5Env.maxTab){		//html_env.maxTab=15
							//Log.info("i="+i+" !!");
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
								//    	        		if(!Mobile_HTML5Manager.replaceCode(html_env, "<div class=\""+Mobile_HTML5Env.getClassID(this)+" \">", "")){
								//    	        		}

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
					//20160527 bootstrap
					if(!tableFlg){
						//        		if(html_env.written_classid.contains(classid))
						html_env.code.append("<DIV Class=\"ui-grid #"+Mobile_HTML5Env.uiGridCount+" "+classid+"\"");
						//        		else
						//        			html_env.code.append("<DIV Class=\"ui-grid #"+Mobile_HTML5Env.uiGridCount+"\"");
						Mobile_HTML5Env.uiGridCount++;
					}
					if(!tableFlg)	html_env.code.append(">");		//20130309

					//20130314  table
					if(tableFlg){
						html_env.code.append(getTableStartTag(html_env, decos, this)+"<TR>");
					}
				}//20160914 ifmobile

				//20160527 bootstrap
				else if(Sass.isBootstrapFlg()){
					if(!tableFlg){
						if(firstFlg){
							html_env.code.append("<DIV Class=\"row\">\n");
							html_env.code.append("<DIV Class=\""+classid+"\">\n");
							if (isEhtml) {
//								html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
								Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid + "\'>\n");
								Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid + "\' outType=\'div\' class=\'" + classid + "\'>\n");
							}

							if(Sass.outofloopFlg.peekFirst()){
								//            				Sass.makeRowClass();
								//            				Sass.makeClass(classid);
								//            				Sass.defineGridBasic(classid, decos);

								//            				Sass.makeClass(classid);
								//            				Sass.defineGridBasic(classid, decos);
								//            				Sass.closeBracket();
								Sass.makeColumn(classid, decos, "", -1);
							}
						}
						

						html_env.code.append("<DIV Class=\"row\">\n");
						if (isEhtml) {
							Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'row\' outType=\'div\' class=\'" + classid + "\'>\n");
						}
						if(Sass.outofloopFlg.peekFirst()){
							//            			Sass.makeRowClass();
						}
					}

					if(tableFlg){
						html_env.code.append(getTableStartTag(html_env, decos, this)+"<TR>");
					}
				}

			}

			int i = 0;

			if(Sass.outofloopFlg.peekFirst()){
				gridMap = Sass.beforeC1WhileProcess(tfes);
				//        	Log.info(gridMap);
			}
			Mobile_HTML5.beforeWhileProcess(getSymbol(), decos, html_env);
			while (this.hasMoreItems()) {
				if (isEhtml) {
					html_env.cNum++;
					html_env.xmlDepth++;
				}
				
				ITFE tfe = (ITFE) tfes.get(i);
				DecorateList decos2 = ((TFE)tfe).decos;
				String classid2 = Mobile_HTML5Env.getClassID(tfe);

				//20130309
				Count = ( (gridInt>=jj)? jj:gridInt );

				if(!Sass.isBootstrapFlg()){
					if(decos.containsKey("table0") || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	table0Flg = true;
					if(decos.containsKey("table") || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || table0Flg)	tableFlg=true;
					if(decos.containsKey("div")){
						divFlg = true;
						tableFlg = false;
					}//else divFlg = false;

					if(!tableFlg){	//div
						//20131002
						int tfesItemNum = tfes.size();
						Mobile_HTML5Attribute.attributeDivWidth = Mobile_HTML5.getDivWidth("C1", decos, tfesItemNum - Mobile_HTML5Function.func_null_count);	//null()
						html_env.code.append("\n<div class=\"ui-block "+classid2+"\">\n");	//20130309

						//added by goto 20161113  for function class width
						Mobile_HTML5Attribute.setWidth(classid2, this.decos, this.html_env);
					}

					//20130314  table
					if(tableFlg){
						html_env.code.append("<TD valign=\"middle\" class=\"" + classid2 + " nest\">\n");
					}


					//20130914  "text"
					if(decos.containsKey("text")){
						Mobile_HTML5Function.textFlg2 = true;
					}
				}else if(Sass.isBootstrapFlg()){
					((TFE)tfe).decos.put("C1","Done");
					//            	html_env.code.append("\n<div class=\""+classid2+"_nest\">\n");
					//            	html_env.code.append("\n<div class=\""+classid2+"\">\n");
					if (isEhtml) {
						html_env.append_css_def_td(HTMLEnv.getClassID(this), ((TFE)tfe).decos);
					}

					if(Sass.outofloopFlg.peekFirst()){
						if(gridMap.get(classid2).containsKey("xs")){
							decos2.put("xs", ""+gridMap.get(classid2).get("xs"));
						}
						if(gridMap.get(classid2).containsKey("sm")){
							decos2.put("sm", ""+gridMap.get(classid2).get("sm"));
						}
						if(gridMap.get(classid2).containsKey("md")){
							decos2.put("md", ""+gridMap.get(classid2).get("md"));
						}
						if(gridMap.get(classid2).containsKey("lg")){
							decos2.put("lg", ""+gridMap.get(classid2).get("lg"));
						}
					}
					html_env.code.append("<div class=\"" + classid2 +"\">\n");
					if (isEhtml) {
						Incremental.outXMLData(html_env.xmlDepth, "<div" + html_env.cNum + " type=\'" + classid2 + "\' outType=\'div\' class=\'" + classid2 + "\'>\n");
					}
					if(Sass.outofloopFlg.peekFirst()){
						//        			Sass.makeClass(classid2);
						//        			Sass.defineGridBasic(classid2, decos2);

						//        			Sass.makeClass(classid2);
						//        			Sass.defineGridBasic(classid2, decos2);
						//        			Sass.closeBracket();
						Sass.makeColumn(classid2, decos2, getSymbol(), responsiveId);
					}


				}

				Mobile_HTML5.whileProcess1_2(getSymbol(), decos, html_env, data, data_info, tfe, tfes, tfeItems);

				this.worknextItem();

				Mobile_HTML5.whileProcess2_1(getSymbol(), decos, html_env, data, data_info, tfe, tfes, tfeItems);

				if(decos.containsKey("table0") || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)	table0Flg = true;
				if(decos.containsKey("table") || Mobile_HTML5C2.tableFlg || Mobile_HTML5G1.tableFlg || Mobile_HTML5G2.tableFlg || table0Flg)	tableFlg=true;
				if(decos.containsKey("div")){
					divFlg = true;
					tableFlg = false;
				}//else divFlg = false;

				ii++;
				jj++;
				gridInt++;

				//TODO: 20130309  html_envで「class="ui-grid-〜#html_env.uiGridCount"」の「#以下」を削除

				//            //20130309
				//            //20130314  table
				//        	if(tableFlg){
				//        		try{
				//		            if (html_env.not_written_classid.contains(classid)){
				//		            	html_env.code.delete(html_env.code.indexOf(classid),html_env.code.indexOf(classid)+classid.length()+1);
				//		            }
				//        		}catch (Exception e) { }
				//        	}

				if(Mobile_HTML5Function.func_null_count<1){	//null()
					if(!Sass.isBootstrapFlg()){
						if(!tableFlg)	html_env.code.append("</div>\n");	//20130309 20160527 bootstrap
						if(tableFlg)	html_env.code.append("</TD>\n");	//20130314  table
					}
					else if(Sass.isBootstrapFlg()){
						html_env.code.append("\n</DIV>\n");//.classid2
						if (isEhtml) {
							Incremental.outXMLData(html_env.xmlDepth, "</div"+ html_env.cNum + ">\n");
						}
						if(Sass.outofloopFlg.peekFirst()){
							//	            		Sass.closeBracket();//classid2
						}
					}
				}

				i++;

				Mobile_HTML5.whileProcess2_2(getSymbol(), decos, html_env, data, data_info, tfe, null, -1);
				
				if (isEhtml) {
					html_env.cNum--;
					html_env.xmlDepth--;
				}
			}	// /while
			Mobile_HTML5.afterWhileProcess(getSymbol(), classid, decos, html_env);

			if(!Sass.isBootstrapFlg()){
				//20130309
				if(!tableFlg)	html_env.code.append("\n</DIV c1>\n");			//20130309

				//20130314  table
				if(tableFlg){
					html_env.code.append("</TR></TABLE>\n");	//20130309
					//	      		tableFlg = false;
					//	      		table0Flg = false;			//20130325 table0
				}

				//20130312 collapsible
				if(decos.containsKey("collapse")){
					html_env.code.append("</DIV>");
				}

				//20130330 tab
				int a=1;
				while(a<=Mobile_HTML5Env.maxTab){
					//Log.info("a="+a);
					if(decos.containsKey("tab"+a) || (a==1 && decos.containsKey("tab"))){
						html_env.code.append("</div></div></div>\n");
						Mobile_HTML5Env.tabCount++;
						break;
					}
					a++;
				}

				//20130503  Panel
				panelProcess2(decos, html_env, panelFlg);

				jj=0;

				if(divFlg)	divFlg = false;		//20130326  div
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

			Mobile_HTML5.postProcess(getSymbol(), classid, decos, html_env);	//Post-process (後処理)

			//20131001 tableDivHeader
			if(decos.containsKey("header"))
				html_env.code = Mobile_HTML5G2.createAndCutTableDivHeader(html_env);
			
			
			if (isEhtml) {
				Incremental.outXMLData(html_env.xmlDepth, "</Connector" + html_env.cNum + ">\n");
			}
			return null;
		}
	}

	//20130503  Panel
	public static int panelProcess1(DecorateList decos, Mobile_HTML5Env html_env){
		int panelFlg = 0;
		if(decos.containsKey("panel"))			panelFlg = 1;	//left panel
		else if(decos.containsKey("lpanel"))	panelFlg = 2;	//left panel
		else if(decos.containsKey("rpanel"))	panelFlg = 3;	//right panel
		if(panelFlg != 0){
			html_env.code1 = new StringBuffer();
			html_env.code2 = new StringBuffer();

			html_env.code1.append(html_env.code);	//code1 = code
		}
		return panelFlg;
	}
	//20130503  Panel
	public static boolean panelProcess2(DecorateList decos, Mobile_HTML5Env html_env, int panelFlg){
		if(panelFlg != 0){
			/* code1:1   code:1+2   code2:null
			 * code2=code;			//code2:1+2
			 * code=code1;			//code:1
			 * Panel=code2-code;	//code1:2	*/
			html_env.code2 = html_env.code;			//code2 = code
			html_env.code = html_env.code1;			//code = code1

			//panel.append
			html_env.panel.append("<div data-role=\"panel\" id=\"ssql_panel_"+html_env.panelCount+"\"  " +
					"data-position=\""+( (panelFlg!=3)? ("left") : ("right") )+"\" " +
					"data-display=\"reveal\" data-swipe-close=\"false\" data-dismissible=\"true\" " +
					"data-position-fixed=\"true\" data-animate=\"true\">\n");
			html_env.panel.append(html_env.code2.delete(0, html_env.code.length()));	//panel = (code2 - code1)
			html_env.panel.append("<br>\n" +
					"<a href=\"#content\" data-rel=\"close\" data-role=\"button\" data-icon=\"delete\">Close</a>\n" +
					"</div>\n");

			//code.append
			html_env.code.append("<a href=\"#ssql_panel_"+html_env.panelCount+"\" " +
					"data-role=\"button\" data-icon=\"arrow-");
			if(panelFlg == 1 && !decos.getStr("panel").equals(""))			//left panel
				html_env.code.append("l\">\n"+decos.getStr("panel")+"\n");
			else if(panelFlg == 2 && !decos.getStr("lpanel").equals(""))	//left panel
				html_env.code.append("l\">\n"+decos.getStr("lpanel")+"\n");
			else if(panelFlg == 3 && !decos.getStr("rpanel").equals(""))	//right panel
				html_env.code.append("r\" data-iconpos=\"right\">\n"+decos.getStr("rpanel")+"\n");
			else
				html_env.code.append(( (panelFlg!=3)? ("l"):("r\" data-iconpos=\"right") )+"\">\n" +
						"Open panel "+html_env.panelCount+"\n");
			html_env.code.append("</a>");
			html_env.panelCount++;

			return true;
		}
		return false;
	}

	//return <TABLE cellSpacing=\"0\" cellPadding=\"0\" border=  class=   >
	public static String getTableStartTag(Mobile_HTML5Env html_env, DecorateList decos, ITFE tfe) {
		String s = "";
		s += "<TABLE width=\"100%\" cellSpacing=\"0\" cellPadding=\"0\" border=\"";
		if(Mobile_HTML5C1.table0Flg || Mobile_HTML5C2.table0Flg || Mobile_HTML5G1.table0Flg || Mobile_HTML5G2.table0Flg)
			s += "0" + "\"";	//20130325 table0
		else	s += html_env.tableborder + "\"";
		s += html_env.getOutlineMode();

		s += getClassIdText(html_env, decos, tfe);
		return s+">";
	}

	//return class=
	public static String getClassIdText(Mobile_HTML5Env html_env, DecorateList decos, ITFE tfe) {
		//    	String s = " class=\"" + tfeID;
		//    	if(decos.containsKey("class")){
		//    		s += " " + decos.getStr("class")+" ";
		//    	}
		//    	return s+"\" ";

		String s = "";
		if(html_env.written_classid.contains(Mobile_HTML5Env.getClassID(tfe))){
			s += " class=\"" + Mobile_HTML5Env.getClassID(tfe);
		}
		if(decos.containsKey("class")){
			if(!html_env.written_classid.contains(Mobile_HTML5Env.getClassID(tfe))){
				s += " class=\"";
			}else{
				s += " ";
			}
			s += decos.getStr("class")+"\" ";
		}else if(html_env.written_classid.contains(Mobile_HTML5Env.getClassID(tfe))){
			s += "\" ";
		}
		return s;
	}


	public String getSymbol() {
		return "Mobile_HTML5C1";
	}

}
