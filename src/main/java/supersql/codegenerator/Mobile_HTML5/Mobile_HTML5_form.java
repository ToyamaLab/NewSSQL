package supersql.codegenerator.Mobile_HTML5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Sass;
import supersql.common.DB;
import supersql.common.GlobalEnv;
import supersql.common.Log;

//20131127 form
public class Mobile_HTML5_form {

	public Mobile_HTML5_form() {

	}

	static String formString = "";
	static String formHTMLbuf = "";
	public static int formCount = 1; //taji changed to public
	static int formWordCount = 1;
	public static boolean form = false;
	static ArrayList<String> formColumn = new ArrayList<>();
	static ArrayList<String> formColumn0 = new ArrayList<>();
	static ArrayList<String> formColumnAlias = new ArrayList<>();
	static ArrayList<String> formColumnTable = new ArrayList<>();

	public static Set<String> formTypeFileResetID = new HashSet<String>();

	public static boolean G2 = false; //taji changed to public
//	static int G2_dataQuantity = 0;

//	public static String formFuncArgProcess(ITFE tfe, DecorateList decos){
//		//For Function
//		return createFormAttribute(tfe, decos);
//	}
	//Process
	public static String formAttributeProcess(ITFE tfe, DecorateList decos){
		//For Attribute (C1, C2, G1, G2)
		return createFormAttribute(tfe, decos);
	}
	private static String createFormAttribute(ITFE tfe, DecorateList decos){
		String s = ""+tfe;
		s = s.trim();
		//Log.e("decos = "+decos);
		if(s.startsWith("\"") && s.endsWith("\"")){
			//not attribute
			s = s.substring(1,s.length()-1);
		}else{
			//attribute
//			s = "'||"+s+"||'";
			String buf = "";
			s = s.toLowerCase();
			if(s.contains(".")){
				formColumnAlias.add(s.substring(0,s.indexOf(".")));
				buf = s.substring(s.indexOf(".")+1);
				formColumn.add(buf);
			}else{
				formColumnAlias.add("");
				buf = s;
				formColumn.add(buf);
			}
			formColumnTable.add("");

			//E-mail:email=$session(email)@{noupdate},
//	          備考:notes@{textarea},
//	          attend={出席|欠席}",
//			"attendance WHERE id=$session(id)"
			if(decos.containsKey("label")){
				buf = decos.getStr("label")+":"+buf;
			}
			if(decos.containsKey("value")){
				buf += "="+decos.getStr("value");
			}
			if(decos.containsKey("hidden")){
				buf += "@{hidden}";
			}else if(decos.containsKey("noupdate")){
				//TODO
				buf += "@{noupdate}";
			}
			formColumn0.add(buf);
			//Log.e("buf="+buf);

			s = "    <input type=\"text\" name=\"form"+formCount+"_words"+(formWordCount++)+"\" placeholder=\"\">";	//TODO ここ以外の位置で
		}
		return s;
	}
	public static boolean formPreProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("form")){
			formHTMLbuf = html_env.code.toString();
			form = true;
			return true;
		}
		return false;
	}
	public static boolean formStringGetProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("form")){
			String currentHTML = html_env.code.toString();
			formString = currentHTML.substring(formHTMLbuf.length(), currentHTML.length());
			html_env.code = new StringBuffer(formHTMLbuf);
			return true;
		}
		return false;
	}
	public static boolean formProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("form")){


			//Log.e(" - Start form process -");
			//Log.e(formString);

			//formColumnに格納されている列名がどのTableのものか判定
			checkFormColumnSTableName();


			boolean update = false;
			boolean insert_update = true;



//	    	String title = "";
	    	String columns = "";
	    	String after_from = "";
	    	String insertFlag = "";
	    	for(String s:formColumn0){
	    		columns += s+",";
	    		//Log.i(s);
	    	}
	    	columns = columns.substring(0,columns.length()-1);
	    	after_from = formColumnTable.get(0);	//TODO 複数テーブルへのinsert

	    	//columns = "user_id = $session(user_id)@{hidden},text";

	    	try{
	    		//title（第一引数）
//	    		FuncArg fa1 = (FuncArg) this.Args.get(0);
//	    		if(!fa1.getStr().equals(""))	title = fa1.getStr();
//	    		else{
//	    			if(update || insert_update)	title = "Update Form";
//	    			else						title = "Insert";
//	    		}
//	    		//columns（第二引数）
//	    		FuncArg fa2 = (FuncArg) this.Args.get(1);
//	    		columns += fa2.getStr();
//	    		//after_from（第三引数）
//	    		FuncArg fa3 = (FuncArg) this.Args.get(2);
//	    		after_from += fa3.getStr().trim();
//	    		if(update){
//		    		//（第四引数）
//		    		FuncArg fa4 = (FuncArg) this.Args.get(3);
//		    		insertFlag += fa4.getStr().toLowerCase().trim();
//		    		if(insertFlag.equals(""))	insertFlag="false";
//	    		}
	    	}catch(Exception e){
	    		Log.info("<Warning> insert関数の引数が不足しています。1 ex. insert(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
	    		return false;
	    	}
			if(columns.trim().equals("") || after_from.equals("")){
				Log.info("<Warning> insert関数の引数が不足しています。2 ex. insert(\"title\", \"c1:column1, c2:column2, ... \", \"From以下\")");
				Log.info("columns.trim() = "+columns.trim());
				Log.info("after_from = "+after_from);
	    		return false;
			}
			if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();
			if(insert_update)	insertFlag = "true";	//20130721
			//Log.info(title);


	    	//置換 ( @ { , }  ->  @ { ; } )
			//Log.i("Before: "+columns);
	    	int inAtFlg = 0;
	    	for(int i=0; i<columns.length();i++){
	    		//Log.i(columns.charAt(i));
	    		if(inAtFlg==0){
	    			if(columns.charAt(i)=='@')		inAtFlg=1;
	    		}else if(inAtFlg==1){
		    		if(columns.charAt(i)==' ')		inAtFlg=1;
		    		else if(columns.charAt(i)=='{')	inAtFlg=2;
	    		}else if(inAtFlg==2){
	    			if(columns.charAt(i)==',')
	    				columns = columns.substring(0,i)+";"+columns.substring(i+1);	//置換
	    			else if(columns.charAt(i)=='}')	inAtFlg=0;
	    		}
	    	}
	    	//Log.i("After:  "+columns);


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
//	    		Log.i( "s_array["+i+"] = "+s_array[i]+"	"+columns0);
	    	}
	    	s_array[col_num-1] = columns0;
			//Log.i( "s_array["+(col_num-1)+"] = "+s_array[col_num-1]);
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
	    	String notnullFlg_array = "";
			String[] value = new String[col_num];
	    	String[] $session_array = new String[col_num];
	    	String[] $time_array = new String[col_num];
	    	String[] $gps_array = new String[col_num];
	    	String[] button_array = new String[col_num];
	    	String buttonSubmit = "";
	    	String insert_aFlg = "\"";	//Flg
	    	String insert_popFlg = "\"";	//Flg
	    	int noinsert_count = 0;
	    	int a_pop_count = 0;
	    	for(int i=0; i<col_num; i++){
				textareaFlg[i] = false;
				hiddenFlg[i] = false;
				noinsertFlg[i] = false;
				validationType[i] = "";
				value[i] = "";

	    		a = s_array[i].replaceAll(" ","");
	    		//Log.i(a);
	    		//$session()あり
	    		if(a.contains("=")){
	    			String a_right = a.substring(a.indexOf("=")+1).trim();
	    			if(a_right.startsWith("$session(")){
	    				$session_array[i] = a.substring(a.indexOf("$session(")+"$session(".length(),a.indexOf(")"));
	    				$time_array[i] = "";
	    				$gps_array[i] = "";
	    				button_array[i] = "";
	    				a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
	        			s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
	    			}else if(a_right.startsWith("time(") || a_right.startsWith("date(")){
	    				String d = s_array[i].substring(s_array[i].indexOf("(")+1,s_array[i].lastIndexOf(")")).trim();
//	    				$time_array[i] = "date(\"Y-m-d H:i:s\")";	//"date(\"Y/m/d(D) H:i:s\")";
	    				$time_array[i] = "date(\""+( (d.equals(""))? ("Y-m-d H:i:s") : (d) )+"\")";	//"date(\"Y/m/d(D) H:i:s\")";
	    				$session_array[i] = "";
	    				$gps_array[i] = "";
	    				button_array[i] = "";
	    				a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
	    				s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
	    			}else if(a_right.startsWith("gps_info(")){
	    				//gps_info()の取得
	    				//String d = s_array[i].substring(s_array[i].indexOf("(")+1,s_array[i].lastIndexOf(")")).trim();
	    				//$gps_array[i] = "date(\""+( (d.equals(""))? ("Y-m-d H:i:s") : (d) )+"\")";	//"date(\"Y/m/d(D) H:i:s\")";
	    				$gps_array[i] = "gps_info";

	    				$session_array[i] = "";
	    				$time_array[i] = "";
	    				button_array[i] = "";
	    				a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf(")")+1).trim();
	    				s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf(")")+1).trim();
	    			}else if(a.contains("{")){
	    				String ss = a.substring(a.indexOf("{")+"{".length(),a.indexOf("}"));
	    				button_array[i] = ss;
	    				$session_array[i] = "";
	    				$time_array[i] = "";
	    				$gps_array[i] = "";
	    				a = a.substring(0,a.indexOf("=")).trim() + a.substring(a.indexOf("}")+1).trim();
	        			s_array[i] = s_array[i].substring(0,s_array[i].indexOf("=")).trim() + s_array[i].substring(s_array[i].indexOf("}")+1).trim();
	    			}else{
	    				$session_array[i] = "";
	    				$time_array[i] = "";
	    				$gps_array[i] = "";
	    				button_array[i] = "";
	    			}
	    		}else{
	    			$session_array[i] = "";
	    			$time_array[i] = "";
	    			$gps_array[i] = "";
	    			button_array[i] = "";
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
//	    				insert_col += s_array[i] +((i<col_num-1)?(","):(""));
//	    	    		insert_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
//	    	    		insert_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
//	    	    		insert_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    	    		insert_col += s_array[i]+",";
//	    				insert_col_array += s_array[i]+"\",\"";
	    				insert_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				insert_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    			}else	a_pop_count++;
	    		}else
	    			insert_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    		if(a.startsWith("pop(") || a.startsWith("popup(")){
	    			insert_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
	    			if(a.endsWith(")")){
//	    				insert_col += s_array[i] +((i<col_num-1)?(","):(""));
//	    	    		insert_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
//	    	    		insert_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
//	    	    		insert_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    				insert_col += s_array[i]+",";
//	    				insert_col_array += s_array[i]+"\",\"";
	    				insert_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				insert_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    			}else	a_pop_count++;
	    		}else
	    			insert_popFlg += "false\""+((i<col_num-1)?(",\""):(""));

	    		//Log.i(s_array[i]);
	    		//Check: @textarea, @hidden, @noinsert, @notnull, @date, @date1-5, @time	//TODO:リファクタリング
	    		textareaFlg[i] = false;
	    		hiddenFlg[i] = false;
	    		noinsertFlg[i] = false;
	    		validationType[i] = "";
	    		String str = "";
	    		if(s_array[i].replaceAll(" ","").contains("@{")){
	    			str = s_array[i].substring(s_array[i].lastIndexOf("@")+1);	//@以下の文字列
		    		Log.info("str: " + str);
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
		    		validationType[i] = checkFormValidationType(str);	//form validation

		    		s_array[i] = s_array[i].substring(0,s_array[i].indexOf("@"));
		    		//Log.i(s_array[i]);
	    		}else{
	    			if(i==(col_num-1))	notnullFlg_array += "FALSE";
	    			else				notnullFlg_array += "FALSE,";
	    		}

	    		if(!noinsertFlg[i]){
	    			insert_col += s_array[i] +((i<col_num-1)?(","):(""));
	    			if(update)	update_col_array += s_array[i] +"'"+((i<col_num-1)?(",'"):(""));
	    		}
	    	}
	    	col_num -= a_pop_count;
	    	insert_col = insert_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("\\)","");
//	    	insert_col_array = insert_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("\\)","");


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
//	    	if(after_from.startsWith("#")){					//From以下をクエリの下(#*)から取ってくる場合
//	    		if(!after_from_string.contains(after_from)){
//	    			Log.info("<Warning> insert関数の第三引数に指定されている '"+after_from+"' が見つかりません。");
//	    			return;
//	    		}
//	    		query = after_from_string
//	    				.substring(after_from_string.indexOf(after_from)+after_from.length())
//	    				.trim().toLowerCase();
//	    		if(query.contains("#"))	query = query.substring(0,query.indexOf("#")).trim().toLowerCase();
//	    	}else
	    		query = after_from.toLowerCase();			//From以下を第三引数へ書く場合
	    	//Log.i("\n	Query: "+query);
	    	String from = "";
	    	from = query.toLowerCase().trim();
	    	if(update){
	    		update_where = from.substring(from.indexOf(" where ")).trim();
	    		if(update_where.contains("$session"))
	    			update_where = update_where.replaceAll("\\$session","'\".\\$_SESSION").replaceAll("\\(","['").replaceAll("\\)","'].\"'");
	    		from = from.substring(0,from.indexOf(" where ")).trim();
	    	}
	    	//Log.i("	FROM:"+from+"	update_where:"+update_where);
	    	//Log.i("	FROM: "+from+"\n	WHERE: "+where+"\n	GROUP: "+groupby+"\n	HAVING: "+having);
	    	//Log.i("	ORDER: "+orderby+"\n	LIMIT: "+limit+"\n	Query: "+query);



	    	String statement = "";
	    	String gps_js = "";

	    	//php
    		statement +=
    				"<!-- Form start -->\n" +
    				"<!-- Form Panel start -->\n" +
    				"<br>\n" +
    				//"<div id=\"FORM"+insertCount+"panel\" style=\"background-color:whitesmoke; width:99%; border:0.1px gray solid;\" data-role=\"none\">\n" +
    				//"<div style=\"padding:3px 5px;border-color:hotpink;border-width:0 0 1px 7px;border-style:solid;background:#F8F8F8; font-size:30;\" id=\"FormTitle"+formCount+"\">"+title+"</div>\n" +
    				"<div id=\"FORM"+formCount+"panel\" style=\"\" data-role=\"none\">\n" +
//	    				"<hr>\n<div style=\"font-size:30;\" id=\"FormTitle"+formCount+"\">"+title+"</div>\n<hr>\n" +
//	    				"<br>\n" +
    				"<form method=\"post\" action=\"\" target=\"dummy_ifr\">\n";
    				//"<form method=\"post\" action=\"\" target=\"form"+formCount+"_ifr\">\n";

    		//TODO 下の部分の処理が必要
//				statement += formString;

    		int insertWordCount = 0;
    		for(int i=0; i<col_num; i++){
				if($session_array[i].equals("") && $time_array[i].equals("") && $gps_array[i].equals("")){
					if(!button_array[i].equals("")){
						//Log.i("bt_array:"+button_array[i]);
						String ss = button_array[i]+"|";
						int btRcount = ss.length() - ss.replaceAll("\\|","").length();
						//Log.i("btRcount:"+btRcount);

						if(btRcount == 1){				//テキスト ex){2013秋}

							//statement +=
							//		"    <input type="text" disabled="disabled" value="お名前: 五嶋">";
							statement +=
									//20161207 bootstrap
									"<div class=\"form-group\">" +
									"    <"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
									""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+button_array[i]+"" +
									""+((!textareaFlg[i])?("\">"):("</textarea>"))+"\n"+
									"</div>";
							if(!noinsertFlg[i])
								statement +=
										"    <input type=\"hidden\" name=\"form"+formCount+"_words"+(++insertWordCount)+"\" value=\""+button_array[i]+"\">\n";


						}else if(btRcount == 2){		//ボタン ex){出席|欠席}
							String bt1=ss.substring(0,ss.indexOf("|")).trim();
							String bt2=ss.substring(ss.indexOf("|")+1,ss.length()-1).trim();
							insertWordCount++;
							statement +=
									"	<div class=\"ui-grid-a\">\n" +
									"		<div class=\"ui-block-a\">\n" +
									"    		<input type=\"submit\" class=\"btn btn-default\" name=\"form"+formCount+"_words"+(insertWordCount)+"\" value=\""+bt1+"\" data-theme=\"a\">\n" +
									"		</div>\n" +
									"		<div class=\"ui-block-b\">\n" +
									"    		<input type=\"submit\" class=\"btn btn-default\" name=\"form"+formCount+"_words"+(insertWordCount)+"\" value=\""+bt2+"\" data-theme=\"a\">\n" +
									"		</div>\n" +
									"	</div>\n";
							buttonSubmit += " || $_POST['form"+formCount+"_words"+(insertWordCount)+"']";
						}else{							//ラジオボタン ex){出席|欠席|その他}
							statement += "   <div data-role=\"controlgroup\">\n";
							insertWordCount++;
							for(int k=1; k<=btRcount; k++){
								String val = ss.substring(0,ss.indexOf("|")).trim();
								statement +=
										"		<input type=\"radio\" name=\"form"+formCount+"_words"+(insertWordCount)+"\" id=\"form"+formCount+"_words"+(insertWordCount)+"_"+k+"\" value=\""+val+"\""+( (k>1)? (""):(" checked=\"checked\"") )+">\n" +
										"		<label for=\"form"+formCount+"_words"+(insertWordCount)+"_"+k+"\">"+val+"</label>\n";
								ss = ss.substring(ss.indexOf("|")+1);
							}
							statement += "	</div>\n";
						}
					}else{
						if(validationType[i].isEmpty()){
							statement +=
									//20161207 bootstrap
									"<div class=\"form-group\">" +
									"    <"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" name=\"form"+formCount+"_words"+(++insertWordCount)+"\"" +
									""+((!value[i].equals(""))? " value=\""+value[i]+"\"" : "") +
									" placeholder=\""+s_name_array[i]+"\">" +
									""+((!textareaFlg[i])?(""):("</textarea>"))+"\n"+
									"</div>";
						}else{
							//TODO 2nd引数
							statement +=
									getFormValidationString(validationType[i], false, "form"+formCount+"_words"+(++insertWordCount), s_name_array[i], null, "", "");
						}
					}
				}else{
					//statement += "    <input type=\"text\" name=\"form"+formCount+"_words"+(++insertWordCount)+"\" placeholder=\""+s_name_array[i]+"\">\n";
					String echo = "";
					if(!$session_array[i].equals(""))	echo += "	echo $_SESSION['"+$session_array[i]+"'];\n";
					else if(!$time_array[i].equals(""))	echo += "	echo "+$time_array[i]+";\n";
					//else if(!$gps_array[i].equals(""))	echo += "	echo \"<script> getGPSinfo(); </script>\";\n";
					//else if(!$gps_array[i].equals(""))	echo += "	echo\"<script> getGPSinfo(); </script>\";\n";
					else if(!$gps_array[i].equals("")){
						echo += "	echo \"位置情報(緯度・経度)\";\n";
						gps_js +=
								"\n<!-- getGPSinfo() -->\n" +
								"<script src=\"http://maps.google.com/maps/api/js?sensor=false&libraries=geometry\"></script>\n" +
								"<script type=\"text/javascript\">\n" +
								"<!--\n" +
								//"$(document).on(\"pageinit\", \"#p-top1\", function(e) {\n" +
								"$(function(){\n" +
								"  	// Geolocation APIのオプション設定\n" +
								"  	var geolocationOptions = {\n" +
								"    	\"enableHighAccuracy\" : true, // 高精度位置情報の取得\n" +
								"    	\"maximumAge\" : 0, // キャッシュの無効化\n" +
								"    	\"timeout\" : 30000 // タイムアウトは30秒\n" +
								"  	};\n" +
								"    navigator.geolocation.getCurrentPosition(function(pos) {\n" +
								"      	// 経度、緯度を取得 //\n" +
								"		document.getElementsByName('form"+formCount+"_words"+(insertWordCount+1)+"')[0].value=pos.coords.latitude+\",\"+pos.coords.longitude;\n" +
								"    }, function(e) {\n" +
								"		gpsInfo = \"\";\n" +
								"    }, geolocationOptions);\n" +
								"});\n" +
								"// -->\n" +
								"</script>\n";
					}

					statement +=
							"    <"+((!textareaFlg[i])?("input"):("textarea"))+" type=\""+((!hiddenFlg[i])?("text"):("hidden"))+"\" disabled=\"disabled\" value=\""+( (!s_name_array[i].equals(""))? (s_name_array[i]+": "):("") )+"" +
							""+( (!textareaFlg[i])? ("\n") : ((!s_name_array[i].equals(""))? ("\">"+s_name_array[i]+": "):("")) )+"\n";
					statement +=
							"EOF;\n" +
							echo +
							"		echo <<<EOF\n";

					statement +=
							""+((!textareaFlg[i])?("\">"):("</textarea>"))+"\n";
					if(!noinsertFlg[i])
						statement +=
								"    <input type=\"hidden\" name=\"form"+formCount+"_words"+(++insertWordCount)+"\" value=\"\n" +
								"EOF;\n" +
								echo +
								"		echo <<<EOF\n" +
								"\">\n";
				}
    		}

    		if(buttonSubmit.equals(""))
    			statement +=
	    			"    <input type=\"submit\" class=\"btn btn-default\" value=\"OK&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" name=\"form"+formCount+"\" id=\"form"+formCount+"\" data-mini=\"false\" data-inline=\"false\">\n";

    		statement +=
    				"</form>\n" +
    				"</div>\n";
    		//getGPSinfo()
    		statement += gps_js;
    		statement +=
    				"<!-- Form Panel end -->\n" +
    				"\n";
			statement +=
    				"\n" +
    				"<script type=\"text/javascript\">\n" +
    				"function Form"+formCount+"_echo1(str){\n" +
    				"  jQuery(function ($) {\n" +
    				"  	$('input,textarea').not('input[type=\\\"radio\\\"],input[type=\\\"checkbox\\\"],:hidden, :button, :submit,:reset').val('');\n" +
    				"	$('input[type=\"radio\"], input[type=\\\"checkbox\\\"],select').removeAttr('checked').removeAttr('selected');\n" +
    				"  });\n" +
    				"  var textArea = document.getElementById(\"Form"+formCount+"_result\");\n" +
    				"  textArea.innerHTML = str;\n" +
    				"}\n" +
    				"</script>\n" +
    				"<!-- Form end -->\n";


			Mobile_HTML5Env.PHP +=
    				"<?php\n" +
    				"if($_POST['form"+formCount+"'] "+buttonSubmit+"){\n" +
    				//"    //ユーザ定義\n" +
    				((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
    				"    $insert_col = \""+insert_col+"\";\n";
			if(update){
				Mobile_HTML5Env.PHP +=
						"    $update_col_array = array("+update_col_array+");\n" +
						"    $update_where = \""+update_where+"\";\n";
			}
			Mobile_HTML5Env.PHP +=
    				"    $notnullFlg = array("+notnullFlg_array+");\n" +
    				"    $col_num = "+(col_num - noinsert_count)+";\n" +                          //カラム数(Java側で指定)\n" +
    				"    $table = '"+from+"';\n" +
    				"\n" +
    				"	$insert_str = \"notnull\";\n" +
    				"	for($k=1; $k<=$col_num; $k++){\n" +
    				"    	$var[$k] = checkHTMLsc($_POST['form"+formCount+"_words'.$k]);\n" +
    				"    	$var[$k] = str_replace(array(\"\\r\\n\",\"\\r\",\"\\n\"), '<br>', $var[$k]);	//改行コードを<br>へ\n" +
    				//"    	//$var[$k] = mb_convert_encoding($var[$k], 'UTF-8', 'auto');					//エンコードをUTF-8へ PHP環境によってはうまく動かない？\n" +
    				//"    	$insert_str .= trim($var[$k]);\n" +
    				"    	if($notnullFlg[$k-1]){\n" +
    				"    		if(trim($var[$k]) == \"\")	$insert_str = \"\";\n" +
    				"    	}\n";
			for(int i=0; i<col_num; i++){
				if(!$time_array[i].equals(""))
					Mobile_HTML5Env.PHP += "		if($k=="+i+")	$var[$k] = "+$time_array[i]+";\n";	//現在時刻
			}
			Mobile_HTML5Env.PHP +=
    				"    }\n" +
    				"\n" +
    				"	if($insert_str == \"\"){\n" +
    				"        form"+formCount+"_p1('<font color=\\\"red\\\">Please check the value.</font>');\n" +
    				"	}else{\n";

			if(!update){
				//insert()
				if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
					Mobile_HTML5Env.PHP +=
		    				"		$insert_str = \"\";\n" +
		    				"		for($k=1; $k<=$col_num; $k++){\n" +
		    				"			if($k==1)	$insert_str .= \"'\".$var[$k].\"'\";\n" +
		    				"			else		$insert_str .= \",'\".$var[$k].\"'\";\n" +
		    				"		}\n" +
		    				"		//DBへ登録\n" +
		    				"		$insert_db"+formCount+" = new SQLite3($sqlite3_DB);\n" +
		    				"       $insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES (\".$insert_str.\")\";\n" +
		    				"       \n" +
		    				"       try{\n" +
		    				"			$result2 = $insert_db"+formCount+"->exec($insert_sql);\n" +
		    				"		 	form"+formCount+"_p1(\"Registration completed.\");\n" +
		    				"		 	//form"+formCount+"_p1($insert_sql);\n" +
		    				"       }catch(Exception $e){\n" +
		    				"       		form"+formCount+"_p1('<font color=red>Registration failed.</font>');	//登録失敗\n" +
		    				"       }\n" +
		    				"		unset($insert_db"+formCount+");\n";
				} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
					String pg_prepare_array = "";
					for(int i=1; i<=(col_num - noinsert_count); i++)
						pg_prepare_array += "$"+i+",";
					pg_prepare_array = pg_prepare_array.substring(0, pg_prepare_array.length()-1);

					Mobile_HTML5Env.PHP +=
							"		//DBへ登録\n" +
		    				"		$insert_db"+formCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
		    				"		$insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES ("+pg_prepare_array+")\";\n" +
		    				"       \n" +
		    				"		try{\n" +
							"			$result2 = pg_prepare($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $insert_sql);\n" +
							"			$result2 = pg_execute($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $var);\n" +
		    				"		 	form"+formCount+"_p1(\"Registration completed.\");\n" +
		    				"		 	//form"+formCount+"_p1($insert_sql);\n" +
		    				"       }catch(Exception $e){\n" +
		    				"       		form"+formCount+"_p1('<font color=red>Registration failed.</font>');	//登録失敗\n" +
		    				"       }\n" +
		    				"		pg_close($insert_db"+formCount+");\n";
				}
			}else{
				//update()
				if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
					Mobile_HTML5Env.PHP +=
							"		$insert_db"+formCount+" = new SQLite3($sqlite3_DB);\n" +
							"		try{\n" +
							"			//データが存在しているかチェック\n" +
							"			$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
							"			$result2 = $insert_db"+formCount+"->query($select_sql);\n" +
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
							"				$result2 = $insert_db"+formCount+"->exec($update_sql);\n" +
							"				//echo '変更された行の数: ', $db->changes();\n" +
							"				form"+formCount+"_p1(\"Update completed.\");\n" +
							"			}else{\n";
				} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
					Mobile_HTML5Env.PHP +=
							"		$insert_db"+formCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
							"		try{\n" +
							"			//データが存在しているかチェック\n" +
							"			$select_sql = \"SELECT \".$insert_col.\" FROM \".$table.\" \".$update_where;\n" +
							"			$result2 = pg_query($insert_db"+formCount+", $select_sql);\n" +						//TODO $update_where
							"			$j = 0;\n" +
							"			while($row = pg_fetch_assoc($result2)){\n" +
							"			    $j++;\n" +
							"			}\n" +
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
							"				$result2 = pg_prepare($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $update_sql);\n" +
							"				$result2 = pg_execute($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $var);\n" +
							"				form"+formCount+"_p1(\"Update completed.\");\n" +
							"			}else{\n";
				}
				if(!insertFlag.equals("true"))
						Mobile_HTML5Env.PHP +=
							"				form"+formCount+"_p1('<font color=red>No data found.</font>');	//更新データなし\n";
				else
						if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
							Mobile_HTML5Env.PHP +=
									"				//新規登録(insert)\n" +
									"				$insert_str = \"\";\n" +
									"				for($k=1; $k<=$col_num; $k++){\n" +
									"					if($k==1)	$insert_str .= \"'\".$var[$k].\"'\";\n" +
									"					else		$insert_str .= \",'\".$var[$k].\"'\";\n" +
									"				}\n" +
									"				\n" +
									"				$insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES (\".$insert_str.\")\";\n" +
									"				$result2 = $insert_db"+formCount+"->exec($insert_sql);\n" +
									"				form"+formCount+"_p1(\"Registration completed.\");\n";
						} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
							String pg_prepare_array = "";
							for(int i=1; i<=(col_num - noinsert_count); i++)
								pg_prepare_array += "$"+i+",";
							pg_prepare_array = pg_prepare_array.substring(0, pg_prepare_array.length()-1);
							Mobile_HTML5Env.PHP +=
									"				//新規登録(insert)\n" +
									"				$insert_sql = \"INSERT INTO \".$table.\" (\".$insert_col.\") VALUES ("+pg_prepare_array+")\";\n" +
									"				$result2 = pg_prepare($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $insert_sql);\n" +
									"				$result2 = pg_execute($insert_db"+formCount+", \"ssql_insert_"+formCount+"\", $var);\n" +
									"				form"+formCount+"_p1(\"Registration completed.\");\n";
						}
				Mobile_HTML5Env.PHP +=
						"			}\n" +
						"        }catch(Exception $e){\n" +
						"       		form"+formCount+"_p1('<font color=red>Update failed.</font>');	//更新失敗\n" +
						"        }\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("        unset($insert_db"+formCount+");\n"):"") +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("        pg_close($insert_db"+formCount+");\n"):"");
			}

			Mobile_HTML5Env.PHP +=
    				"    }\n" +
    				"}\n" +
    				"function form"+formCount+"_p1($str){\n" +
    				"    echo '<script type=\"text/javascript\">window.parent.Form"+formCount+"_echo1(\"'.$str.'\");</script>';\n" +
    				"}\n" +
    				"?>\n";
			//End of php


	    	// 各引数毎に処理した結果をHTMLに書きこむ
	    	html_env.code.append(statement);

			formCount++;
			formWordCount = 1;
			formColumn.clear();
			form = false;
		}
		return true;
	}
	//formColumnに格納されている列名がどのTableのものか判定
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void checkFormColumnSTableName() {
		//formColumnに格納されている列名がどのTableのものか判定
		ArrayList<ArrayList> tables = DB.getTableNamesFromQuery(GlobalEnv.query);	//(0)=Table name, (1)=Table alias, (2)=From phrase
		ArrayList<String> al1 = tables.get(0);	//Table name
		ArrayList<String> al2 = tables.get(1);	//Table alias
		Map<String,HashSet<String>> dbTablesMap = DB.getTableAndColumnNamesMap();
		for(int i=0;i<formColumn.size();i++){
			String fcAlias = formColumnAlias.get(i);
			String fc = formColumn.get(i);

			if(fcAlias.isEmpty()){
				//Aliasなし: From句に書かれている全テーブルの列名(上で取得)と照合してTable名を決定
				for(String tableName : al1){
					try{
						for(String dbColumn: dbTablesMap.get(tableName)){
				            if(fc.equals(dbColumn)){
				            	formColumnTable.set(i,tableName);
				            }
						}
					}catch (Exception e) { }
				}
			}else{
				//Aliasあり: From句に書かれているテーブル名のAliasと照合してTable名を決定
				for(int j=0;j<al1.size();j++){
					String tableAlias = al2.get(j);
					if(tableAlias.equals(fcAlias)){
						formColumnTable.set(i,al1.get(j));
					}
				}
			}
			//Log.i(fcAlias+" "+fc+" "+formColumnTable.get(i));
		}
	}

	//20131201 form validation
	public static String checkFormValidationType(String s){
		String type = "";
		String types[] = {"tel","url","email","password","alphabet_number","alphabet","number","color",
				"file","image","img","audio","video",
				"date1","date2","date3","date4","date5","date","time"};	//Order is significant!
		for(int i=0;i<types.length;i++){
			if(s.contains(types[i])){	//TODO: リファクタリング
				type = types[i];
				break;
			}
		}
		//Log.e("s = "+s+", "+"type = "+type);
		return type;
	}
//	static String upFormVal = "";
	public static String getFormValidationString(String type, Boolean notnull, String name, String placeholder, String updateFromValue, String outTitle, String uploadFilePath){
		String s = "";
		type = type.toLowerCase().trim();
//		upFormVal = updateFromValue; //TODO: 他の方法

		//date, time
//	    ◎date <input type="text" name="insert1_words4" placeholder="Year / Month / Day" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true, "overrideCalHeaderFormat": "%Y / %m / %d", "overrideDateFormat": "%Y/%m/%d" }' >
//	    ◎date1<input type="text" name="insert1_words4" placeholder="Year" data-role="datebox" data-options='{"mode":"flipbox", "useNewStyle":true, "overrideHeaderFormat": "%Y", "overrideDateFormat": "%Y", "overrideDateFieldOrder":["y"] }' >
//	    ◎date2<input type="text" name="insert1_words4" placeholder="Month" data-role="datebox" data-options='{"mode":"flipbox", "useNewStyle":true, "overrideHeaderFormat": "%m", "overrideDateFormat": "%m", "overrideDateFieldOrder":["m"] }' >
//	    ◎date3<input type="text" name="insert1_words4" placeholder="Day" data-role="datebox" min="2016-01-01" max="2016-01-31" data-options='{"mode":"flipbox", "useNewStyle":true, "overrideHeaderFormat": "%d", "overrideDateFormat": "%d", "overrideDateFieldOrder":["d"] }' >
//	    ◎date4<input type="text" name="insert1_words4" placeholder="Year / Month" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true, "overrideCalHeaderFormat": "%Y / %m", "overrideDateFormat": "%Y/%m" }'}' >
//	    ◎date5<input type="text" name="insert1_words4" placeholder="Month / Day" data-role="datebox" min="2016-01-01" max="2016-12-31" data-options='{"mode":"datebox", "useNewStyle":true, "overrideHeaderFormat": "%m / %d",  "overrideDateFormat": "%m/%d", "overrideDateFieldOrder":["m","d"] }'}' >
//	    X date5<input type="text" name="insert1_words4" placeholder="Month / Day" data-role="datebox" data-options='{"mode":"calbox", "useNewStyle":true, "overrideCalHeaderFormat": "%m / %d", "overrideDateFormat": "%m/%d" }'}' >
//	    ◎time <input type="text" name="insert1_words5" placeholder="Ex) 12:01" data-role="datebox" data-options='{"mode":"timebox", "overrideTimeFormat":24, "useNewStyle":true}'>

		s += outTitle;
		switch (type){
		  case "tel":	//tel (custom type)
			  s += getFormTag("tel", name, placeholder, "Telephone number", notnull, type);
			  break;
		  case "url":	//url
			  s += getFormTag("url", name, placeholder,"URL", notnull, "");
			  break;
		  case "email":	//email
			  s += getFormTag("email", name, placeholder,"E-mail address", notnull, "");
			  break;
		  case "password":	//password
			s += getFormTag("password", name, placeholder,"Password", notnull, "");
			break;
		  case "number"://number
			  s += getFormTag("number", name, placeholder,"Number", notnull, "");
			  break;
		  case "color":	//color
			  s += getFormTag("color", name, placeholder,"Color", notnull, "");
			  break;
		  case "file":	//file
		  case "audio":	//audio
		  case "video":	//video
			  s += getFormTag(type, name, placeholder,"Choose file", notnull, "", updateFromValue, uploadFilePath);
			  break;
		  case "image":	//image
		  case "img":	//img
			  s += getFormTag("image", name, placeholder,"Choose file", notnull, "", updateFromValue, uploadFilePath);
			  break;

		  case "alphabet":	//alphabet (custom type)
			  s += getFormTag("text", name, placeholder, "Alphabet", notnull, type);
			  break;
		  case "alphabet_number":	//alphabet_number (custom type)
			  s += getFormTag("text", name, placeholder, "Alphabet or Number", notnull, type);
			  break;

		  case "date":	//Year / Month / Day
			  s += getFormTag("date", name, placeholder, "Year / Month / Day", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"calbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideCalHeaderFormat\": \"%Y / %m / %d\", \"overrideDateFormat\": \"%Y/%m/%d\" }'";
			  break;
		  case "date1":	//Year
			  s += getFormTag("date", name, placeholder, "Year", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"flipbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%Y\", \"overrideDateFormat\": \"%Y\", \"overrideDateFieldOrder\":[\"y\"] }'";
			  break;
		  case "date2":	//Month
			  s += getFormTag("date", name, placeholder, "Month", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"flipbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%m\", \"overrideDateFormat\": \"%m\", \"overrideDateFieldOrder\":[\"m\"] }'";
			  break;
		  case "date3":	//Day
			  //TODO getUpdate時
			  s += getFormTag("date", name, placeholder, "Day", notnull, "") + "data-role=\"datebox\" min=\"2016-01-01\" max=\"2016-01-31\" data-options='{\"mode\":\"flipbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%d\", \"overrideDateFormat\": \"%d\", \"overrideDateFieldOrder\":[\"d\"] }'";
			  //s += getFormTag("date", name, placeholder, "Day", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"flipbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%d\", \"overrideDateFormat\": \"%d\", \"overrideDateFieldOrder\":[\"d\"] }'";
			  break;
		  case "date4":	//Year / Month
			  s += getFormTag("date", name, placeholder, "Year / Month", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"calbox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideCalHeaderFormat\": \"%Y / %m\", \"overrideDateFormat\": \"%Y/%m\" }'";
			  break;
		  case "date5":	//Month / Day
			  //TODO getUpdate時
			  s += getFormTag("date", name, placeholder, "Month / Day", notnull, "") + "data-role=\"datebox\" min=\"2016-01-01\" max=\"2016-12-31\" data-options='{\"mode\":\"datebox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%m / %d\",  \"overrideDateFormat\": \"%m/%d\", \"overrideDateFieldOrder\":[\"m\",\"d\"] }'";
			  //s += getFormTag("date", name, placeholder, "Month / Day", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"datebox\", \"useFocus\":true, \"useNewStyle\":true, \"overrideHeaderFormat\": \"%m / %d\",  \"overrideDateFormat\": \"%m/%d\", \"overrideDateFieldOrder\":[\"m\",\"d\"] }'";
			  break;
		  case "time":	//Hour : Minute
			  s += getFormTag("time", name, placeholder, "Ex) 12:01", notnull, "") + "data-role=\"datebox\" data-options='{\"mode\":\"timebox\", \"useFocus\":true, \"overrideTimeFormat\":24, \"useNewStyle\":true }'";
			  break;
		}
		if(updateFromValue != null && !updateFromValue.isEmpty()){
			//s = s.replace("'", "\\\'");
			s += " value=\""+updateFromValue+"\"";
		}
		//Log.e("formValidation = "+s+"></span>");
		return s+">\n</div></span>\n";
	}
	private static String getFormTag(String type, String name, String placeholder, String defaultPlaceholder, Boolean notnull, String customType) {
		return getFormTag(type, name, placeholder, defaultPlaceholder, notnull, customType, "", "");
	}
	private static String getFormTag(String type, String name, String placeholder, String defaultPlaceholder, Boolean notnull, String customType, String updateFromValue, String uploadFilePath) {
		String accept = "";
		if(type.equals("image")||type.equals("audio")||type.equals("video")){
			accept = type;
			type = "file";
		}
		String ph = ((!placeholder.isEmpty())? placeholder : defaultPlaceholder);
		String ret =
				//20161207 bootstrap
				"<div class=\"form-group\">\n"+
//<<<<<<< HEAD
				((!type.equals("file"))? "" : "<div style=\"text-align:left;"+((!Ehtml.isEhtml2())? " font-size:16.5px" : "")+"\">"+ph+"</div>\n" );
//=======
//				((!type.equals("file"))? "" : "<div style=\"text-align:left; font-size:16.5px\">"+ph+"</div>\n" );
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


		//added by goto 170606 for update(file/image)
		if(type.equals("file")){
			if(!uploadFilePath.endsWith("/") && !uploadFilePath.endsWith("\\"))	uploadFilePath = uploadFilePath+GlobalEnv.OS_FS;
			ret +=  "<div>\n" +
					"	<div style=\"display: none;\" id=\""+name+"_reset_text\">"+updateFromValue+"</div>\n" +
					"	<table id=\""+name+"_reset\">\n" +
					"		<tr>\n" +
					((accept.equals("image"))?
					("			<td><img src=\""+uploadFilePath+updateFromValue+"\" style=\"width:200px; margin:5px 0px;\"></td>\n") :
					("			<td>"+updateFromValue+"</td>\n")) +
					"			<td>&nbsp;&nbsp;&nbsp;<button type=\"button\" id=\""+name+"_reset_btn\" class=\"btn btn-default btn-xs\">Reset</button></td>\n" +
					"		</tr>\n" +
					"	</table>\n" +
					"	<script type=\"text/javascript\">\n" +
					"		if(jQuery.trim($(\"#"+name+"_reset_text\").text())==\"\"){\n" +
					"			$(\"#"+name+"_reset\").empty();\n" +
					"		}\n" +
					"		$(document).on(\"click\", \"#"+name+"_reset_btn\", function() {\n" +
					"			//Reset button onClick event\n" +
					"			$(\"#"+name+"_hidden\").val(\"\");\n" +
					"			$(\"#"+name+"_reset\").empty();\n" +
					"			$(\"#"+name+"\").val(\"\");\n" +
					"		});\n" +
					"	</script>\n" +
					"</div>\n\n";
		}


		ret +=
				"    <span>\n" +
				"<input type=\""+type+"\""+((accept.isEmpty())? "" : " accept=\""+accept+"/*\"" )+
				" id=\""+name+"\" name=\""+name+"\"" +
				" placeholder=\""+ph+"\" " + getFormClass(notnull, customType);


		//added by goto 170606 for update(file/image)
		if(type.equals("file")){
			ret += 	">\n" +
					"<script type=\"text/javascript\" language=\"javascript\">\n" +
					"$(function(){\n" +
					"	$(\"#"+name+"\").change(function() {\n" +
					"		//Input file change event\n" +
					"		$(\"#"+name+"_hidden\").val(\"\");\n" +
					"		$(\"#"+name+"_reset\").empty();\n" +
					"		\n" +
					"		var file = $(this).prop(\"files\")[0];\n" +
					"		var fileRdr = new FileReader();\n" +
					"		fileRdr.onload = function() {\n" +
					"			$(\"#"+name+"_reset\").append(\"\\\n" +
					"					<tr>\\\n" +
					((accept.equals("image"))?
					("						<td><img src=\\\"\"+fileRdr.result+\"\\\" style=\\\"width:200px; margin:5px 0px;\\\"></td>\\\n") :
					("						<td>\"+file.name+\"</td>\\\n")) +																														//TODO file
					"						<td>&nbsp;&nbsp;&nbsp;<button type=\\\"button\\\" id=\\\""+name+"_reset_btn\\\" class=\\\"btn btn-default btn-xs\\\">Reset</button></td>\\\n" +
					"					</tr>\\\n" +
					"			\");\n" +
					"		}\n" +
					"		fileRdr.readAsDataURL(file);\n" +
					"	});\n" +
					"});\n" +
					"</script>\n" +
				   	"\n" +
				   	"<input type=\"hidden\" id=\""+name+"_hidden\" name=\""+name+"_hidden\" ";
			formTypeFileResetID.add(name+"_reset");
		}


		if(type.equals("password")){
			//add confirm password form
			ret += 	">\n</span>\n" +
					"</div>\n <div class=\"form-group\">\n"+
					"    <span><input type=\""+type+"\" id=\""+name+"_confirm\" name=\""+name+"_confirm\"" + getFormClass(notnull, customType) +
					" placeholder=\""+ph+" (re-input)\" equalTo=\"#"+name+"\"";
		}
		return ret;
////		if(upFormVal == null){
//			return "    <span><input type=\""+type+"\" id=\""+name+"\" name=\""+name+"\"" +
//			" placeholder=\""+((!placeholder.isEmpty())? placeholder : defaultPlaceholder)+"\" " + getFormClass(notnull, customType);
////		}else{
////			return "    <span><input type=\""+type+"\" id=\""+name+"\" name=\""+name+"\" value=\""+upFormVal+"\"" +
////					" placeholder=\""+((!placeholder.isEmpty())? placeholder : defaultPlaceholder)+"\" " + getFormClass(notnull, customType);
////		}
	}
	public static String getFormClass(Boolean notnull, String customType) {//taji changed to public
		if(!notnull && customType.isEmpty()){
			//20161207 bootstrap
			if(Sass.isBootstrapFlg()){
				return "class=\"form-control\"";
			}else{
				return "";
			}
		}
		String s = " class=\"";
		if(Sass.isBootstrapFlg()){
			s += "form-control ";
		}
		if(notnull) s += "required ";
		if(!customType.isEmpty()){
			switch (customType){
			  case "tel":				//tel
				  s += "jqValidate_TelephoneNumber";
				  break;
			  case "alphabet":			//alphabet
				  s += "jqValidate_Alphabet";
				  break;
			  case "alphabet_number":	//alphabet_number
				  s += "jqValidate_AlphabetNumber";
				  break;
			}
		}
		return s+"\" ";
	}

}
