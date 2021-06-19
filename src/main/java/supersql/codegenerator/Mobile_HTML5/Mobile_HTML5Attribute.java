package supersql.codegenerator.Mobile_HTML5;

import java.io.File;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Manager;
import supersql.codegenerator.infinitescroll.Infinitescroll;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Embed;
//added by goto

public class Mobile_HTML5Attribute extends Attribute {

	Manager manager;

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	String[] formSql = {"","delete","update","insert","login","logout"};
	String[] formHtml = {"","submit","select","checkbox","radio","text","textarea","hidden"};
	int whichForm;

	Connector connector;	//add oka

	int loop_counter;		//add oka

	static String alias;	//add oka

	int colum_num;			//add oka

	public static String attributeDivWidth = "", attributeDivWidth2 = "";
	public static boolean attributeHasWidth = false;

	//���󥹥ȥ饯��
	public Mobile_HTML5Attribute(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
		super();
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;
	}
	public Mobile_HTML5Attribute(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2, Boolean b) {
		super(b);
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;
	}
	//Attribute��work�᥽�å�
	public String work(ExtList data_info) {
		
		//tbt acc 180806
		if(GlobalEnv.joinFlag){
			// if (Incremental.flag || Ehtml.flag) {
			Mobile_HTML5CONCAT.joinClassID = Mobile_HTML5Env.getClassID(this);
			Mobile_HTML5CONCAT.joinDecos = this.decos;
			// html_env.append_css_def_td(Mobile_HTML5CONCAT.joinClassID, this.decos);
			if ((!Incremental.flag && !Ehtml.flag) || !Ehtml.isEhtml2()) ;
			else	html_env.append_css_def_td(Mobile_HTML5CONCAT.joinClassID, this.decos);
			return this.getStr(data_info);
		}
		//tbt end
		
		/*
        if(GlobalEnv.getSelectFlg())
        	data_info = (ExtList) data_info.get(0);
		 */
		//        //20131002  moved to HTMLEnv
		//		if(!decos.containsKey("width")){
		//			if(!HTMLEnv.divWidth.equals(""))
		//				decos.put("width", HTMLEnv.divWidth);
		//	  	}
		//		HTMLEnv.divWidth = "";

		if (Ehtml.infinitescroll_flag && (Incremental.flag || Ehtml.flag)) {
			Infinitescroll.Attributes(this, html_env,html_env2, data_info);
			return null;
		} else {
			html_env.code = Embed.preProcess(html_env.code, decos);	//goto 20130915-2  "<$  $>"

			String classid = Mobile_HTML5Env.getClassID(this);

			//changed by goto 20161113  for function class width
			setWidth(classid, this.decos, this.html_env);

			if(GlobalEnv.isOpt()){
				work_opt(data_info);
			}else{
				if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2])){

				}else{

					Mobile_HTML5.preProcess("Mobile_HTML5Attribute", decos, html_env);	//Pre-process (前処理)	//TODO この位置でOK?

					//20130309
					//20130309
					//20130314 table
					//20130409
					if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
							Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
							decos.containsKey("table") || decos.containsKey("table0"))
							&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg)){
						html_env.code.append("<table width=\"100%\"" + html_env.getOutlineModeAtt() + " ");
						html_env.code.append("class=\"att");
						if(html_env.written_classid.contains(classid)){
							//classを持っているとき(ex.TFE10000)のみ指定
							html_env.code.append(" " + classid);
						}
						if(decos.containsKey("class")){
							//classを持っているとき(ex.TFE10000)のみ指定
							html_env.code.append(" " + decos.getStr("class"));
						}
						html_env.code.append("\"");
						html_env.code.append(">");
					}
				}

				if(Mobile_HTML5Env.getFormItemFlg()){

				}else{
					//20130309
					//20130409
					//if(decos.containsKey("table") || decos.containsKey("table0"))	html_env.code.append("<tr><td>\n");		//20130314 table
					if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
							Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
							decos.containsKey("table") || decos.containsKey("table0"))
							&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg)){
						html_env.code.append("<tr><td>\n");		//20130314 table
					}
					Log.out("<table class=\"att\"><tr><td>");
				}

				if (html_env.link_flag > 0 || html_env.sinvoke_flag) {

					//tk start for draggable div///////////////////////////////////////
					if(html_env.draggable)
					{
						html_env.code.append("<div id=\""+html_env.dragdivid+"\" class=\"draggable\"");
						Log.out("<div id=\""+html_env.dragdivid+"\" ");
					}
					else{
						//tk end for draggable div/////////////////////////////////////////
						if(html_env.isPanel)
							html_env.code.append("<div id=\"container\">");

						//added by goto 20120614 start
						//[%連結子] 下記の2つの問題があったため、hrefの指定を絶対パスから「相対パス形式」へ変更
						//1.絶対パスだとFirefoxではリンク先が開けない
						//2.ITCの実習環境ではリンク先が開けない
						String fileDir = new File(html_env.linkurl).getAbsoluteFile().getParent();
						if(fileDir.length() < html_env.linkurl.length()
								&& fileDir.equals(html_env.linkurl.substring(0,fileDir.length()))){
							String relative_path = html_env.linkurl.substring(fileDir.length()+1);
							html_env.code.append("<A href=\"" + relative_path + "\" ");
						}else
							//changed by goto 20161019 for new foreach
							//added by goto 20161109 for plink/glink
							if(html_env.plink_glink_onclick.isEmpty())
								html_env.code.append("<A href=\"" + html_env.linkurl + "\" data-ajax=\"false\" ");
							else
								html_env.code.append("<A href=\"\" onclick=\""+LinkForeach.ID1+"("+html_env.plink_glink_onclick+"); return false;\" data-ajax=\"false\" ");

						//html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
						//added by goto 20120614 end
					}

					//added by goto 20121217 start
					//画面遷移アニメーション (data-transition)
					//transition = fade, slide, pop, slideup, slidedown, flip
					if (decos.containsKey("transition")){
						html_env.code.append("data-transition=\"" + decos.getStr("transition") + "\" ");
						//System.out.println(decos.getStr("transition"));
					}
					//added by goto 20121217 end


					//tk start//////////////////////////////////////////////////////////
					if(decos.containsKey("target")){
						html_env.code.append(" target=\"" + decos.getStr("target")+"\"");
					}
					if(decos.containsKey("class")){
						html_env.code.append(" class=\"" + decos.getStr("class") + "\"");
					}

					if(GlobalEnv.isAjax() && html_env.isPanel)
					{
						html_env.code.append(" onClick =\"return panel('Panel','"+html_env.ajaxquery+"'," +
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

					}


					html_env.code.append(">\n");
					//tk end////////////////////////////////////////////////////////////

					Log.out("<A href=\"" + html_env.linkurl + "\">");
				}

				//Log.out("data_info: "+this.getStr(data_info));
				Mobile_HTML5.beforeWhileProcess("Mobile_HTML5Attribute", decos, html_env);
				Mobile_HTML5.whileProcess1_2("Mobile_HTML5Attribute", decos, html_env, null, data_info, null, null, -1);	//TODO ここでOK?

				createForm(data_info);

				if(whichForm == 0){ //normal process (not form)
					//***APPEND DATABASE VALUE***//
					Log.out(data_info);
//<<<<<<< HEAD
					
					// added by masato 20150924 incremental update
					if (Incremental.flag || Ehtml.flag) {
						// modified by masato 20151201 XMLの要素名をTFE******に変更
						// Incremental.outXMLData(htmlEnv.xmlDepth, "<" +
						// Items.get(0) + tfe + ">" + this.getStr(data_info) + "</"
						// + Items.get(0) + ">\n");
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
						String data = this.getStr(data_info)
								.replaceAll("<", "&lt;");
						data = data.replaceAll(">", "&gt;");
						
						if (!Ehtml.infinitescroll_flag) {
							String cid = Mobile_HTML5Env.getClassID(this);
							Incremental.outXMLData(
									html_env.xmlDepth,
									"<"+cid+" outType=\'"
											+ html_env.outTypeList.get(html_env.xmlDepth)
											+ "\'>" + data + "</"+cid+">\n");
						} else {
							Incremental.outXMLData(
									html_env.xmlDepth,
									"<Value outType=\'"
											+ html_env.outTypeList.get(html_env.xmlDepth)
											+ "\' class=\'" + Mobile_HTML5Env.getClassID(this)
											+ "'>" + data + "</Value>\n");
						}

					}else if(Mobile_HTML5_dynamic.dynamicDisplay || Mobile_HTML5_stream.streamDisplay || Mobile_HTML5_form.form){
//=======
//					if(Mobile_HTML5_dynamic.dynamicDisplay || Mobile_HTML5_stream.streamDisplay || Mobile_HTML5_form.form){
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
						//20131118 dynamic
						if(Mobile_HTML5_dynamic.dynamicDisplay){
							html_env.code.append( Mobile_HTML5_dynamic.dynamicAttributeProcess(this, html_env, decos) );
						}
						if(Mobile_HTML5_stream.streamDisplay){
							html_env.code.append( Mobile_HTML5_stream.streamAttributeProcess(this, html_env, decos) );
						}
						//20131127 form
						if(Mobile_HTML5_form.form){
							html_env.code.append( Mobile_HTML5_form.formAttributeProcess(this, decos) );
						}

					}else{
						//					if(!Sass.isBootstrapFlg()){
						html_env.code.append(this.getStr(data_info));
						//					}else if(Sass.isBootstrapFlg()){
						//						html_env.code.append("<div class=\"" + classid +"\">");
						//						html_env.code.append(this.getStr(data_info));
						//						html_env.code.append("</div>");
						//						if(Sass.outofloopFlg.peekFirst()){
						//		        			Sass.makeClass(classid);
						//		        			Sass.defineGridBasic(classid, decos);
						//		        			Sass.closeBracket();
						//			      		}
						//					}
					}

					Mobile_HTML5.whileProcess2_1("Mobile_HTML5Attribute", decos, html_env, null, data_info, null, null, -1);	//TODO ここでOK?
					Mobile_HTML5.afterWhileProcess("Mobile_HTML5Attribute", classid, decos, html_env);

					if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
						if(html_env.draggable)
							html_env.code.append("</div>\n");
						else
						{
							html_env.code.append("</A>\n");

							if(html_env.isPanel)
								html_env.code.append("</div>\n");
						}
						Log.out("</A>");
					}
					
					// added by masato 20151124 for plink
					if (html_env.plinkFlag) {
						Incremental.outXMLData(html_env.xmlDepth, "</PostLink>\n");

					}

					/*
			if(whichForm > 0){
				html_env.code.append("\" />\n");
				Log.out("\" \\>\n");
			}
					 */



					//Log.out("tuple: " + tuple_count + "/"+GlobalEnv.getTuplesNum() );

					if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2])){

					}else{
						html_env.code.append("\n");			//20130309
						//html_env.code.append("</diV>\n");
						//20130309
						//20130409
						//if(decos.containsKey("table") || decos.containsKey("table0"))	html_env.code.append("</td></tr></table>\n");	//20130314 table
						if((Mobile_HTML5C1.tableFlg||Mobile_HTML5C1.table0Flg||Mobile_HTML5G1.tableFlg||Mobile_HTML5G1.table0Flg||
								Mobile_HTML5C2.tableFlg||Mobile_HTML5C2.table0Flg||Mobile_HTML5G2.tableFlg||Mobile_HTML5G2.table0Flg||
								decos.containsKey("table") || decos.containsKey("table0"))
								&& (!Mobile_HTML5C1.divFlg&&!Mobile_HTML5C2.divFlg&&!Mobile_HTML5G1.divFlg&&!Mobile_HTML5G2.divFlg))
							html_env.code.append("</td></tr></table>\n");	//20130314 table
						Log.out("</td></tr></table>");
					}


					Mobile_HTML5.postProcess("Mobile_HTML5Attribute", classid, decos, html_env);	//Post-process (後処理)

					Log.out("TFEId = " + classid);
				}
			}
		}

		return null;
	}

	//setWidth
	//added by goto 20161113  for function class width
	public static void setWidth(String classid, DecorateList decos, Mobile_HTML5Env html_env) {
		if(!decos.containsKey("width") && (!attributeDivWidth.equals("") || !attributeDivWidth2.equals(""))){
			//attributeDivWidth, attributeDivWidth2
			if(!attributeDivWidth2.equals("") && !attributeHasWidth){
				html_env.css.append(attributeDivWidth2);
			}else if(!attributeDivWidth.equals("")){
				decos.put("width", attributeDivWidth);
			}
			attributeDivWidth2 = "";
		}
		attributeDivWidth = "";
		if(decos.containsKey("width"))	attributeHasWidth = true;
		else							attributeHasWidth = false;
		html_env.append_css_def_td(classid, decos);
	}

	//optimizer
	public void work_opt(ExtList data_info){
		String classid = Mobile_HTML5Env.getClassID(this);
		StringBuffer string_tmp = new StringBuffer();
		string_tmp.append("<VALUE");
		if(html_env.written_classid.contains(classid)){
			//class���äƤ���Ȥ�(ex.TFE10000)�Τ߻���
			string_tmp.append(" class=\"");
			string_tmp.append(classid);
		}

		if(decos.containsKey("class")){
			//class����(ex.class=menu)������Ȥ�
			if(!html_env.written_classid.contains(classid)){
				string_tmp.append(" class=\"");
			}else{
				string_tmp.append(" ");
			}
			string_tmp.append(decos.getStr("class") + "\"");
		}else if(html_env.written_classid.contains(classid)){
			string_tmp.append("\"");
		}

		if(decos.containsKey("update") || decos.containsKey("insert")||decos.containsKey("delete")||decos.containsKey("login")||decos.containsKey("logout") || Mobile_HTML5Env.getFormItemFlg() ||
				(Mobile_HTML5Env.getIDU()!= null && !Mobile_HTML5Env.getIDU().isEmpty())){
			string_tmp.append(" type=\"form\"");
		}


		if(decos.containsKey("tabletype")){
			string_tmp.append(" tabletype=\"" + decos.getStr("tabletype") + "\"");
		}

		//link and sinvoke
		if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
			string_tmp.append(" href=\"" + html_env.linkurl + "\" ");
			if(decos.containsKey("target")){
				string_tmp.append(" target=\"" + decos.getStr("target")+"\"");
			}
			if(decos.containsKey("class")){
				string_tmp.append(" aclass=\"" + decos.getStr("class") + "\"");
			}
		}

		string_tmp.append(">");


		if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2]) && Mobile_HTML5Env.getSelectRepeat()){

		}else{
			html_env2.code.append(string_tmp);
			Log.out(string_tmp);
		}
		
		// added by masato 20151124 for plink
		if (html_env.plinkFlag) {
			String tmp = "";
			for (int i = 0; i < html_env.valueArray.size(); i++) {
				tmp += " value" + (i + 1) + "='" + html_env.valueArray.get(i) + "'";
			}
			Incremental.outXMLData(html_env.xmlDepth, "<PostLink target='" + html_env.linkUrl + "'" + tmp + ">\n");
		}
		

		createForm(data_info);


		if(whichForm == 0){
			//***APPEND DATABASE VALUE***//
			String s = this.getStr(data_info);
			if(s.contains("&"))
				s = s.replace("&", "&amp;");
			if(s.contains("<"))
				s = s.replaceAll("<", "&lt;");
			if(s.contains(">"))
				s = s.replaceAll(">", "&gt;");
			if(s.contains("���"))
				s = s.replaceAll("���", "&#65374;");
			if(s.isEmpty())
				s = "��";
			html_env2.code.append(s);
			Log.out(this.getStr(data_info));
		}

		/*
		if(decos.containsKey("update") || decos.containsKey("insert")|| decos.containsKey("delete") || decos.containsKey("login")){
			html_env2.code.append("\" />");
			Log.out("\" \\>\n");
		}
		 */

		//Log.out("tuple: " + tuple_count + "/"+GlobalEnv.getTuplesNum() );

		if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[2])){
			//select
		}else{
			html_env2.code.append("</VALUE>");
			Log.out("</VALUE>");
			if(Mobile_HTML5Env.getFormItemFlg() && Mobile_HTML5Env.getFormItemName().equals(formHtml[5])){
				Mobile_HTML5Env.incrementFormPartsNumber();
			}
		}


	}



	/*
  //Attribute��work�᥽�å�
    public void work(ExtList data_info) {

        html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

        if(!GlobalEnv.isOpt()){
        	if(HTMLEnv.getFormItemFlg()){

            }else{
	        	html_env.code.append("<table" + html_env.getOutlineModeAtt() + " ");
	        	html_env.code.append("class=\"att");
	        	//tk start/////////////////////////////////////////////////////////
	        	if(html_env.written_classid.contains(HTMLEnv.getClassID(this))){
	        		//class���äƤ���Ȥ�(ex.TFE10000)�Τ߻���
	        		html_env.code.append(" " + HTMLEnv.getClassID(this));
	        	}
	        	if(decos.containsKey("class")){
	        		//class����(ex.class=menu)������Ȥ�
	        		html_env.code.append(" " + decos.getStr("class"));
	        	}
	        	html_env.code.append("\"");
	        	html_env.code.append(">");
            }
        }


        if(GlobalEnv.isOpt()){
        	html_env2.code.append("<VALUE");
        	if(html_env.written_classid.contains(HTMLEnv.getClassID(this))){
        		//class���äƤ���Ȥ�(ex.TFE10000)�Τ߻���
        		html_env2.code.append(" class=\"");
        		html_env2.code.append(HTMLEnv.getClassID(this));
        	}

        	if(decos.containsKey("class")){
        		//class����(ex.class=menu)������Ȥ�
        		if(!html_env.written_classid.contains(HTMLEnv.getClassID(this))){
        			html_env2.code.append(" class=\"");
        		}else{
        			html_env2.code.append(" ");
        		}
        		html_env2.code.append(decos.getStr("class") + "\"");
        	}else if(html_env.written_classid.contains(HTMLEnv.getClassID(this))){
        		html_env2.code.append("\"");
        	}

        	if(decos.containsKey("update") || decos.containsKey("insert")||decos.containsKey("delete")||decos.containsKey("login")){
        		html_env2.code.append(" type=\"form\"");
        	}


        	if(decos.containsKey("tabletype"))
        		html_env2.code.append(" tabletype=\"" + decos.getStr("tabletype") + "\"");

        }
        //tk end////////////////////////////////////////////////////////////

        if(HTMLEnv.getFormItemFlg()){

        }else{
	        html_env.code.append("<tr><td>\n");
	        Log.out("<table class=\"att\"><tr><td>");
        }

        if (html_env.link_flag > 0 || html_env.sinvoke_flag) {

        	//tk start for draggable div///////////////////////////////////////
        	if(html_env.draggable)
        	{
        		html_env.code.append("<div id=\""+html_env.dragdivid+"\" class=\"draggable\"");
        		Log.out("<div id=\""+html_env.dragdivid+"\" ");
        	}
        	else{
        	//tk end for draggable div/////////////////////////////////////////
        		if(html_env.isPanel)
        			html_env.code.append("<div id=\"container\">");

        		html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
        		html_env2.code.append(" href=\"" + html_env.linkurl + "\" ");

        	}
            //tk start//////////////////////////////////////////////////////////
            if(decos.containsKey("target")){
            	html_env.code.append(" target=\"" + decos.getStr("target")+"\"");
            	html_env2.code.append(" target=\"" + decos.getStr("target")+"\"");
            }
            if(decos.containsKey("class")){
            	html_env.code.append(" class=\"" + decos.getStr("class") + "\"");
            	html_env2.code.append(" aclass=\"" + decos.getStr("class") + "\"");
            }

            if(GlobalEnv.isAjax() && html_env.isPanel)
            {
            	html_env.code.append(" onClick =\"return panel('Panel','"+html_env.ajaxquery+"'," +
            			"'"+html_env.dragdivid+"','"+html_env.ajaxcond+"')\"");
            }
            else if(GlobalEnv.isAjax() && !html_env.draggable)
            {
            	String target = GlobalEnv.getAjaxTarget();
            	if(target == null)
            	{
            		String query = html_env.ajaxquery;
            		if(query.contains("/"))
            		{
            			target = query.substring(query.lastIndexOf("/")+1,query.indexOf(".sql"));
            		}
            		else
            			target = query.substring(0,query.indexOf(".sql"));

            		if(html_env.has_dispdiv)
            		{
            			target = html_env.ajaxtarget;
            		}
            		Log.out("a target:"+target);
            	}
            	html_env.code.append(" onClick =\"return loadFile('"+html_env.ajaxquery+"','"+target+
            			"','"+html_env.ajaxcond+"',"+html_env.inEffect+","+html_env.outEffect+")\"");

            }


            html_env.code.append(">\n");
            //tk end////////////////////////////////////////////////////////////

            Log.out("<A href=\"" + html_env.linkurl + "\">");
        }

        //Log.out("data_info: "+this.getStr(data_info));

        html_env2.code.append(">");

        String form = new String();
        if(decos.containsKey("update") || decos.containsKey("insert")|| decos.containsKey("login")){
        	String name = new String();
        	//Log.out(decos.containsKey("insert"));
        	//Log.out(decos.getStr("insert"));
        	//String DataID = HTMLEnv.getDataID(this);
        	if(decos.containsKey("update")){
        		name = decos.getStr("update");
        	}else if(decos.containsKey("insert")){
        		name = decos.getStr("insert");
        	}else if(decos.containsKey("login")){
        		name = decos.getStr("login");
        		if(decos.containsKey("att")){
        			html_env.code.append("<input type=\"hidden\" name=\"att\" value=\"" + decos.getStr("att") +"\" />");
                	html_env2.code.append("<input type=\"hidden\" name=\"att\" value=\"" + decos.getStr("att") +"\" />");
        		}
        	}

    		if(decos.containsKey("pwd")){
    			html_env.code.append("<input type=\"password\" name=\"" + name + "\" value=\"");
            	html_env2.code.append("<input type=\"password\" name=\"" + name + "\" value=\"");
    		}else{
    			html_env.code.append("<input type=\"text\" name=\"" + name + "\" value=\"");
        		html_env2.code.append("<input type=\"text\" name=\"" + name + "\" value=\"");
    		}
        	Log.out("<input type=\"text\" name=\"" + name + "\" value=\"");

        }else if(decos.containsKey("delete")){
    		String name = decos.getStr("delete");
        	html_env.code.append("<input type=\"checkbox\" name=\"" + name + "\" value=\"");
        	html_env2.code.append("<input type=\"checkbox\" name=\"" + name + "\" value=\"");
        	Log.out("<input type=\"checkbox\" name=\"" + name + "\" value=\"");
        }else if(HTMLEnv.getFormItemFlg()){
    		String name = decos.getStr("select");
    		form = inputFormItems(data_info,"select",name);
        	html_env.code.append(form);
        }

    	Log.out(GlobalEnv.getTuplesNum());
        //change      chie

        if(decos.containsKey("insert") || decos.containsKey("login") || !form.isEmpty()){

        }else{
        	//***APPEND DATABASE VALUE***
        	html_env.code.append(this.getStr(data_info));


            String s = this.getStr(data_info);
            if(s.contains("&"))
            	s = s.replace("&", "&amp;");
            if(s.contains("<"))
            	s = s.replaceAll("<", "&lt;");
            if(s.contains(">"))
            	s = s.replaceAll(">", "&gt;");
            if(s.contains("���"))
            	s = s.replaceAll("���", "&#65374;");
            html_env2.code.append(s);

        	Log.out(this.getStr(data_info));
        }

        if (html_env.link_flag > 0 || html_env.sinvoke_flag) {
        	if(html_env.draggable)
        		html_env.code.append("</div>\n");
        	else
        	{
        		html_env.code.append("</A>\n");

        		if(html_env.isPanel)
        			html_env.code.append("</div>\n");
        	}
            Log.out("</A>");
        }


        if(decos.containsKey("update") || decos.containsKey("insert")|| decos.containsKey("delete") || decos.containsKey("login")){
            html_env.code.append("\" />\n");
            html_env2.code.append("\" />");
            Log.out("\" \\>\n");
        }

        html_env2.code.append("</VALUE>");

        if(HTMLEnv.getFormItemFlg()){
            if(tuple_count == GlobalEnv.getTuplesNum()){
                closeFormItems("select");
            }
        }else{
        	html_env.code.append("</td></tr></table>\n");
            Log.out("</td></tr></table>");
        }


        Log.out("TFEId = " + HTMLEnv.getClassID(this));
        //html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

    }
	 */


	//static int tuple_count = 0;

	private void createForm(ExtList data_info){

		String form = new String();
		String name = new String();
		String inputFormString = new String();

		for(int i = 1; i < formSql.length ; i++ ){
			if(decos.containsKey(formSql[i]) || Mobile_HTML5Env.getIDU().equals(formSql[i])){
				switch(i){
				case 1 : //delete
					if(decos.containsKey(formSql[i])){
						name = decos.getStr("delete");
					}else{
						name = decos.getStr("attributeName");
					}
					inputFormString += "<input type=\"checkbox\" name=\"" + name + "\" value=\"" + this.getStr(data_info) + "\" />";
					whichForm = i;
					break;
				case 2 : //update
					if(decos.containsKey(formSql[i])){
						name = decos.getStr("update");
					}else{
						name = decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 3 : //insert
					if(decos.containsKey(formSql[i])){
						name = decos.getStr("insert");
					}else{
						name = decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 4 : //login
					name = decos.getStr("login");
					if(decos.containsKey("att")){
						inputFormString += "<input type=\"hidden\" name=\"att\" value=\"" + decos.getStr("att") +"\" />";
					}
					whichForm = i;
					break;
				case 5 : //logout
					inputFormString += "<input type=\"hidden\" name=\"sqlfile\" value=\""+decos.getStr("linkfile").replace("\"", "")+"\" />";
					inputFormString += "<input type=\"submit\" name=\"logout\" value=\""+this.getStr(data_info)+"\" />";
					whichForm = i;
					break;
				}
			}
		}


		if( 1 < whichForm && whichForm < formSql.length-1 ){ //update,insert,login
			String s;
			if(whichForm < 3) {//update
				s = this.getStr(data_info);
			}else{//insert,login,logout
				s = "";
			}
			if(decos.containsKey("pwd")){
				inputFormString += "<input type=\"password\" name=\"" + name + "\" value=\"" + s + "\" />";
				if(decos.containsKey("md5")){
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
			if(decos.containsKey("notnull")){//not null
				constraint = "notnull";
			}
			if(decos.containsKey("number")){//num or eng
				if(decos.containsKey("english")){
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
			}else if(decos.containsKey("english")){//eng
				if(constraint.isEmpty())
					constraint = "english";
				else
					constraint += ",english";
			}

			if(decos.containsKey("unique")){//unique
				if(constraint.isEmpty())
					constraint = "unique";
				else
					constraint += ",unique";
			}

			if(constraint != null && !constraint.isEmpty())
				inputFormString += "<input type=\"hidden\" name=\""+ name +":const\" value=\""+ constraint +"\" />";


			Log.out("pppppp"+decos.containsKey("pkey"));
			if(decos.containsKey("pkey") && whichForm == 2){//update
				if(!html_env.code.toString().contains("<input type=\"hidden\" name=\"pkey\" value=\"" + name + "\" />"))
					inputFormString += "<input type=\"hidden\" name=\"pkey\" value=\"" + name + "\" />";
			}
		}

		html_env.code.append(inputFormString);
		html_env2.code.append(inputFormString);
		Log.out(inputFormString);

		inputFormString = new String();

		if(Mobile_HTML5Env.getFormItemFlg()){
			for(int i = 1; i < formHtml.length ; i++ ){
				String real_value = Mobile_HTML5Env.getFormValueString();
				if(Mobile_HTML5Env.getFormItemName().equals(formHtml[i])){
					switch(i){
					case 1: //submit
						inputFormString = inputFormItems(data_info,formHtml[i],"");
						whichForm =  i + formSql.length;
						break;
					case 2: //select
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;
					case 3: //checkbox
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;
					case 4: //radio
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;

					case 5: //input text
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;

					case 6: //textarea
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;

					case 7: //hidden
						inputFormString = inputFormItems(data_info,formHtml[i],real_value);
						whichForm =  i + formSql.length;
						break;
					}
				}
			}
		}

		html_env.code.append(inputFormString);
		html_env2.code.append(inputFormString);
		Log.out(inputFormString);


	}

	/*
	private String closeFormItems(String itemType){
		String ret = new String();
		tuple_count = 0;
		if(itemType.equals("select")){
			HTMLEnv.setSelectRepeat(false);
			ret = "</select>";
		}
		HTMLEnv.incrementFormName();
		return ret;
	}
	 */

	private String inputFormItems(ExtList data_info,String itemType,String real_value){
		String ret = "";
		String formname = Mobile_HTML5Env.getFormPartsName();;
		if(Mobile_HTML5Env.getSearch()){
			ret += cond();
			formname = "value"+Mobile_HTML5Env.form_parts_number;
		}
		String s = this.getStr(data_info);
		//tuple_count++;
		if(real_value.isEmpty()){
			real_value = s;
		}
		//sizeoption
		String size = new String();
		if(decos.containsKey("size")){
			size += " size=\""+ decos.getStr("size")+"\"";
		}
		if(decos.containsKey("height")){
			size += " height=\""+ decos.getStr("height")+"\"";
		}
		if(decos.containsKey("cols")){
			size += " cols=\""+ decos.getStr("cols")+"\"";
		}
		if(decos.containsKey("rows")){
			size += " rows=\""+ decos.getStr("rows")+"\"";
		}

		if(decos.containsKey("class")){
			size += " class=\""+ decos.getStr("class")+"\"";
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
			if(decos.containsKey("pwd")){
				ret += "<input type=\"password\" name=\""+ formname +"\" value=\"" + real_value + "\"" + size +" />";
				if(decos.containsKey("md5")){
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
		if(decos.containsKey("notnull")){
			constraint = "notnull";
		}
		if(decos.containsKey("number")){
			if(decos.containsKey("english")){//number or english
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
		}else if(decos.containsKey("english")){//english
			if(constraint.isEmpty())
				constraint = "english";
			else
				constraint += ",english";
		}
		if(decos.containsKey("unique")){//unique
			if(constraint.isEmpty())
				constraint = "unique";
			else
				constraint += ",unique";
		}

		if(constraint != null && !constraint.isEmpty())
			ret += "<input type=\"hidden\" name=\""+ formname +":const\" value=\""+ constraint +"\" />";


		return ret;
	}

	private String cond(){
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

}
