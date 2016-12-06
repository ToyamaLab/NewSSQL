package supersql.codegenerator.Mobile_HTML5;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.parse.ANTLRParser.finallyClause_return;

import supersql.codegenerator.Asc_Desc;
import supersql.codegenerator.Asc_Desc.AscDesc;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Compiler.Compiler;
import supersql.common.GlobalEnv;
import supersql.common.Log;

//20130529 dynamic
//20131118 dynamic
public class Mobile_HTML5_dynamic {
	
	public Mobile_HTML5_dynamic() {

	}
	
	public static boolean dynamicDisplay = false;
	
	public static String dynamicString = "";
	private static String dynamicHTMLbuf0 = "";
	private static String dynamicHTMLbuf = "";
	public static int dynamicCount = 1;
	private static String dynamicFuncCountLabel = "___SSQL_DynamicFunc_CountLabel___";
	private static ArrayList<String> dynamicAttributes = new ArrayList<>();
//	public static int Gdepth = 0;
	public static int Gnum = 0;
	public static int sindex = 0;
//	static int Gdepth_old = 0;
//	static int Gnum_old = 0;
	private static boolean dynamicAttributeFlg = true;
	public static String dyamicWhileString = "";
	private static ArrayList<String> dyamicWhileStrings = new ArrayList<>();
	private static int dyamicWhileCount = 0;
	public static int dyamicWhileCount0 = 0;
	
	//For dyamicPostStringProcess() substring
	public static int html_env_code_length = 0;
	
	
	//For Dynamic paging
	static int dynamicRow = 1;
	static boolean dynamicRowFlg = false;
	static int dynamicPagingCount = 1;
	static String dynamicPHPfileName =  "";
	//ajax
	static int ajax_loadInterval = 0;
	
	
	//Process
	public static String dynamicFuncArgProcess(ITFE tfe, Mobile_HTML5Env html_env){
		//For Function
		return createDynamicAttribute(tfe, html_env);
	}
	public static String dynamicAttributeProcess(ITFE tfe, Mobile_HTML5Env html_env){
		//For Attribute (C1, C2, G1, G2)
		return createDynamicAttribute(tfe, html_env);
	}
	private static String createDynamicAttribute(ITFE tfe, Mobile_HTML5Env html_env){
		String s = ""+tfe;
		s = s.trim();
		if(s.startsWith("\"") && s.endsWith("\"")){
			//not attribute, not number
			s = s.substring(1, s.length()-1);
		}else if(Mobile_HTML5.isNumber(s)){
			//number
		}else{
			//attribute
			
			if(dyamicWhileCount0>1)	dynamicAttributeFlg = false;
			if(dynamicAttributeFlg){
				//TODO d
//				int i = Gnum-1;
				int i = 0;//dynamicCount-1;
//				int j = new Connector().getSindex();
				int j = sindex++;
				//Log.e(j);
				//String a = "'COALESCE(CAST("+s+" AS varchar), \\'\\')'";	//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
				String a = "'"+s+"'";	//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
				String b = "'.$row"+Gnum+"["+j+"].'";
				s = b;
				//b = "$b .= '<div>"+b+"</div>';\n";
				
				//add dyamicAttributes
				try {
					String a0 = dynamicAttributes.get(i);
					if(!a0.isEmpty()) a = a0+", "+a;
					dynamicAttributes.set(i, a);
				} catch (Exception e) {
//					e.printStackTrace();
					//Log.e("dynamicAttributes.add("+i+", "+a+")");
//					dynamicAttributes.add(i, a);
					dynamicAttributes.add(a);
				}
//				Log.e(dynamicAttributes.get(i));
//				//add dyamicWhileString
////				try {
////					String b0 = dyamicWhileStrings.get(i);
////					if(!b0.isEmpty()) b = b0+" "+b;
////					dyamicWhileStrings.set(i, b);
////				} catch (Exception e) {
////					b = "";
//					if(i>=1 && j==0){
//						int x = i+1;
//						b = 	"';\n"+
//								"  		$sql"+x+" = getSQL($sql_a"+x+", $table, $where, $sql_g, $limit, $sql_a1, $row1);\n" +		//TODO d 指定値
//								"  		$result"+x+" = pg_query($dynamic_db1, $sql"+x+");\n" +
//								"  		while($row"+x+" = pg_fetch_row($result"+x+")){\n" +
//								"  $b='";
//						//dyamicWhileString += b;
//						html_env.code.append(b);
//					}
////					b += "$b = '"+dyamicWhileString+"';";
////					dyamicWhileStrings.add(i, b);
////				}
				//Log.i(Gdepth+" "+i+" "+j+" "+s+"  "/*+b*/);
				//Log.i(dyamicWhileString);
			}
			
//			if(Gdepth>1 || Gnum<Gdepth_old){
//				dynamicAttributeFlg = false;
//			}
//			Gdepth_old = Gdepth;
//			Gnum_old = Gnum;
		}
		return s;
	}
	public static void dyamicPreStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		int i = Gnum-1;
//		int j = new Connector().getSindex();
		int j = sindex;
//		if(dynamicAttributeFlg)
//			Log.i(dynamicAttributeFlg+"  "+i+"  "+j);
//		if(dynamicAttributeFlg && i==1){	//TODO d jの値を可変に
		if(dynamicAttributeFlg && i==1 && j==1){	//TODO d jの値を可変に
			int x = i+1;
			String b = 	"';\n"+
					"  		$sql"+x+" = getSQL($sql_a"+x+", $table, $where, $sql_g, $limit, $sql_a1, $row1);\n" +		//TODO d 指定値
					"  		$result"+x+" = pg_query($dynamic_db"+dynamicCount+", $sql"+x+");\n" +
					"  		while($row"+x+" = pg_fetch_row($result"+x+")){\n" +
					"  			$b .= '";
			//dyamicWhileString += b;
			html_env.code.append(b);
			dyamicWhileCount++;

//			Log.i("---");
		}
	}
	public static void dyamicWhileStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		//TODO d
		if(dyamicWhileCount0<=1){
			if(symbol.contains("G1") || symbol.contains("G2")){
				//dyamicWhileString = "";
				if(dynamicAttributeFlg){
					//Log.i("!!!! "+Gnum+" "+dyamicWhileString);
					dyamicWhileStrings.add(dyamicWhileString);
				}
			}
//			//else{
//			if(symbol.contains("C1") || symbol.contains("C2")){
//				Log.i(html_env_code_length);
////				String s = html_env.code.toString().substring(html_env_code_length, html_env.code.toString().length());
////				String s = html_env.code.toString().substring(10, 15);
//				String s = "xxx";
//				dyamicWhileString = "$b .= '"+s.replaceAll("\r\n|\r|\n", "")+"';\n";
//				//Log.i(s);
//			}
		}
//		if(symbol.contains("C1") || symbol.contains("C2")){
//			html_env_code_length = html_env.code.toString().length();
//			//Log.e(html_env_code_length);
//		}
	}
	public static void dyamicAfterWhileStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		//dyamicWhileString += "\n";
		//if(Gdepth<=1 && (symbol.contains("G1") || symbol.contains("G2"))){
//			String s = html_env.code.toString();
//			dyamicWhileString = "$b = '"+s.replaceAll("\r\n|\r|\n", "")+"';\n";
//			Log.i("---- ");
//			dyamicWhileString += " $$$$$$$$ \n";
//			html_env.code.append(" XXXXXXXXXXX ");
		//}

	}
	public static void dyamicPostStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
//		if(Gdepth<=1 && (symbol.contains("G1") || symbol.contains("G2"))){
//		if(dynamicAttributeFlg && (symbol.contains("G1") || symbol.contains("G2"))){
		if(dynamicAttributeFlg){
//			String s = html_env.code.toString();
//			dyamicWhileString = "$b = '"+s.replaceAll("\r\n|\r|\n", "")+"';\n";
			//html_env.code.append(" XXXXXXXXXXX ");
//			if((symbol.contains("G1") || symbol.contains("G2"))){
//			if(dyamicWhileCount>0){
//				html_env.code.append(" XXXXXXXXXXX ");
//				dyamicWhileCount--;
//			}
//			}
			
			//Log.e(dynamicCount+"  html_env.code.toString().substring("+html_env_code_length+")");
			String s = html_env.code.toString().substring(html_env_code_length);
			//dyamicWhileString = "		$b .= '"+s.replaceAll("\r\n|\r|\n", "")+"';\n";
			dyamicWhileString = "		$b .= '\n"+s+"';\n";
			//Log.i("---- ");
		}
	}
	
	public static String getDynamicLabel(){	//+Mobile_HTML5.getDinamicLabel()
		//For function's count
		//※ Count付きのfunc()には、+Mobile_HTML5.getDinamicLabel()を付加する
		//TODO dynamicFuncCountLabelがユニーク値かどうか判定
		if(dynamicDisplay){
			return dynamicFuncCountLabel;
		}
		return "";
	}
	
	public static boolean dynamicPreProcess0(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
//		if(Compiler.isCompiler){
//			decos.put("dynamic", "");
//		}
		
		if(decos.containsKey("dynamic")){
			dynamicHTMLbuf0 = html_env.code.toString();
			dynamicDisplay = true;
			dynamicPHPfileName = html_env.getFileName2()+"_SSQLdynamic_"+dynamicCount+".php";
			
	        if(decos.containsKey("row")){
	        	dynamicRow = Integer.parseInt(decos.getStr("row").replace("\"", ""));
	        	if(dynamicRow<1){	//範囲外のとき
	        		Log.err("<<Warning>> row指定の範囲は、1〜です。指定された「row="+dynamicRow+"」は使用できません。");
	        	}else{
	        		dynamicRowFlg = true;
	        		dynamicPHPfileName = html_env.getFileName2()+"_SSQLdynamicPaging_"+dynamicPagingCount+".php";
	        	}
	        	//Log.i("dynamicRow = "+dynamicRow);
	        }
			return true;
		}
		return false;
	}
	public static boolean dynamicPreProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("dynamic")){
			dynamicHTMLbuf = html_env.code.toString();
			Log.info("dynamicPreProcess" +symbol+"\n");
			Log.info("dynamicHTMLbuf\n"+dynamicHTMLbuf.toString()+"\n");
			dynamicDisplay = true;
			if(Mobile_HTML5G3.G3)	Mobile_HTML5G3.dynamic_G3 = true;	//added by goto 20161112 for dynamic foreach
			return true;
		}
		return false;
	}
	public static boolean dynamicStringGetProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("dynamic")){
			Log.info("dynamicStringGetProcess" + symbol+"\n");
			String currentHTML = html_env.code.toString();
			Log.info("currentHTML\n"+currentHTML+"\n");
			Log.info("dynamicHTMLbuf\n"+dynamicHTMLbuf.toString()+"\n");
			dynamicString = currentHTML.substring(dynamicHTMLbuf.length(), currentHTML.length());
			Log.info("dynamicString\n"+dynamicString+"\n");
//			Log.info("dynamicHTMLbuf\n"+dynamicHTMLbuf+"\n");
			html_env.code = new StringBuffer(dynamicHTMLbuf);
			//dynamicDisplay = false;
			return true;
		}
		return false;
	}
	public static boolean dynamicProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("dynamic")){

//			//Log.i("Mobile_HTML5.gLevel = "+Mobile_HTML5.gLevel);
//			if(Compiler.isCompiler && Mobile_HTML5.gLevel>0){
//				return false;
//			}
//			if(Mobile_HTML5.gLevel>1){	//TODO
//				return false;
//			}
			
			if(symbol.contains("G1") || symbol.contains("G2")){
				html_env.code = new StringBuffer(dynamicHTMLbuf0);
				Log.info("After C1 postProcess\n"+html_env.code.toString()+"\n");
			}
			
			//ajax load interval
			if(decos.containsKey("ajax-load") || decos.containsKey("load") || decos.containsKey("load-interval")
					|| decos.containsKey("load-next") || decos.containsKey("load-next-page")
					|| decos.containsKey("reload")){
				String s = "";
				if(decos.containsKey("ajax-load")) 				s = decos.getStr("ajax-load");
				else if(decos.containsKey("load")) 				s = decos.getStr("load");
				else if(decos.containsKey("load-interval"))		s = decos.getStr("load-interval");
				else if(decos.containsKey("load-next"))			s = decos.getStr("load-next");
				else if(decos.containsKey("load-next-page"))	s = decos.getStr("load-next-page");
				else if(decos.containsKey("reload"))			s = decos.getStr("reload");
				s = s.trim().toLowerCase().replaceAll("sec", "").replaceAll("s", "");
				ajax_loadInterval = (int) (Float.parseFloat(s)*1000.0);
				//Log.i(ajax_loadInterval);
			}
			
			//TODO div, table以外の場合
			int numberOfColumns = 1;
			String php_str1 = "", php_str2 = "", php_str3 = "", php_str4 = "";
			//Log.i("!! "+symbol);
//			Log.info(dynamicString);
			if(!symbol.contains("G1") && !symbol.contains("G2")){
				
				if(decos.containsKey("table")){
					dynamicString = "'<table border=\"1\"><tr>"+dynamicString+"</tr></table>'";
				}else if(decos.containsKey("table0")){
					dynamicString = "'<table><tr>"+dynamicString+"</tr></table>'";
				}else{
					dynamicString = "'"+dynamicString+"</div>'";
				}
			}else{
				//G1, G2
				dynamicString = "'"+dynamicString+"'";
				
				//table
				if(decos.containsKey("table") || decos.containsKey("table0")){
					int border = 1;
					if(decos.containsKey("table0"))	border = 0;
					//table
					php_str1 = "	$b .= '<table border=\""+border+"\">';\n";
					php_str2 = "		$b .= '<tr><td>';\n";
					php_str3 = "		$b .= '</td></tr>';\n";
					php_str4 = "	$b .= '</table>';\n";
				}

				//decos contains Key("column")
				if(decos.containsKey("column")){
					try{
						numberOfColumns = Integer.parseInt(decos.getStr("column"));
					}catch(Exception e){}
					if(numberOfColumns<2){
						numberOfColumns = 1;
	            		//Log.err("<<Warning>> column指定の範囲は、2〜です。指定された「column="+numberOfColumns+"」は使用できません。");
					}else{
						if(decos.containsKey("table") || decos.containsKey("table0")){
							//table
							int border = 1;
							if(decos.containsKey("table0"))	border = 0;
							php_str1 = "        $b .= '<table border=\""+border+"\"><tr>';\n";
							php_str2 = "              $b .= '<td>';\n";
							php_str3 = "              $b .= '</td>';\n" +
									   "              if($i%"+numberOfColumns+"==0) $b .= '</tr><tr>';\n";
							php_str4 = "        $b .= '</tr></table>';\n";
						}else{
							//div
							php_str1 = "        $b .= '<div Class=\"ui-grid\">';\n";
							php_str2 = "              $clear = '';\n" +
								       "              if($i%"+numberOfColumns+"==1) $clear = ' clear:left;';\n" +
									   "              $b .= '<div class=\"ui-block\" style=\"width:"+(1.0/numberOfColumns*100.0)+"%;'.$clear.'\">';\n";
							php_str3 = "              $b .= '</div>';\n";
							php_str4 = "        $b .= '</div>';\n";
						}
					}
				}
			}
			
			
			dynamicString = dynamicString.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");	//改行コードの削除
			dynamicString = dynamicString.replaceAll("\"", "\\\\\"");										//　" -> \"
			//Log.e(dynamicString);
			

			//String after_from_string = Mobile_HTML5Function.after_from_string;	//TODO
			
			
		    /*  //ユーザ定義
			    $sqlite3_DB = '/Users/goto/Desktop/SQLite_DB/sample2.db';
			    $dynamic_col = "w.name, pr.name, count(*), w.r_year, ko.kind";
			    $col_num = 5;                          //カラム数(Java側で指定)
	    	 *    $table = 'world_heritage w, prefectures pr, wh_prefectures wpr, kind_of_wh ko';
	    	 *    $where = 'w.wh_id=wpr.wh_id and wpr.p_id=pr.p_id and w.k_id=ko.k_id';
			    $dynamic_col_array = array("w.name","pr.name", "count(*)", "w.r_year", "ko.kind");
	    	 *    $groupby = " pr.name "; 	           //null => WHERE句にlikeを書く／ not null => HAVING句に～    //[要] Java側で、列名に予約語から始まるものがあるかチェック
	    	 *    $having = " count(*)>1 ";
	    	 *    $orderby = " ORDER BY w.name asc ";
	    	 *    $limit = " LIMIT 10 ";
	    	 */
	    	
	    	String columns = "";
	    	String after_from = "";
	    	columns = dynamicString;
	    	after_from = Mobile_HTML5Function.after_from_string;
	    	
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
	    			if(!s_array[i].substring(0,s_array[i].indexOf(":")).contains(")"))
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
	    	String dynamic_col = "";
	    	String dynamic_col_array = "\"";
	    	String dynamic_aFlg = "\"";		//Flg
	    	String dynamic_mailFlg = "\"";		//Flg
	    	String dynamic_popFlg = "\"";	//Flg
	    	int a_pop_count = 0;
	    	for(int i=0; i<col_num; i++){
	    		a = s_array[i].replaceAll(" ","");
	    		if( a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
	    		if(a.startsWith("a(") || a.startsWith("anchor(")){
	    			dynamic_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
	    			if(a.endsWith(")")){
	    				dynamic_col += s_array[i]+",";
	    				dynamic_col_array += s_array[i]+"\",\"";
	    				dynamic_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    			}else	a_pop_count++;
	    		}else
	    			dynamic_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    		if(a.startsWith("mail(")){
	    			dynamic_mailFlg += "true\""+((i<col_num-1)?(",\""):(""));
	    			if(a.endsWith(")")){
	    				dynamic_col += s_array[i]+",";
	    				dynamic_col_array += s_array[i]+"\",\"";
	    				dynamic_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    			}else	a_pop_count++;
	    		}else
	    			dynamic_mailFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    		if(a.startsWith("pop(") || a.startsWith("popup(")){
	    			dynamic_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
	    			if(a.endsWith(")")){
	    				dynamic_col += s_array[i]+",";
	    				dynamic_col_array += s_array[i]+"\",\"";
	    				dynamic_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    				dynamic_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
	    			}else	a_pop_count++;
	    		}else
	    			dynamic_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
	    		dynamic_col += s_array[i] +((i<col_num-1)?(","):(""));
	    		dynamic_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
	    	}
	    	col_num -= a_pop_count;
	    	dynamic_col = dynamic_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
	    	dynamic_col_array = dynamic_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
	    	
	    	//TODO 余計なコードの削除
	    	dynamic_col = dynamicString;
	    	dynamic_col_array = dynamicString;
	    	
	    	//Log.i("	1:"+title+"	2:"+columns+"	col_num:"+col_num);
	    	//Log.i("	dynamic_col:"+dynamic_col+"	dynamic_col_array:"+dynamic_col_array);
	    	//Log.i("	dynamic_aFlg:"+dynamic_aFlg+"	dynamic_popFlg:"+dynamic_popFlg);
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
	    	//Log.e(after_from);
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
    		//final int ASC_DESC_ARRAY_COUNT = (!Compiler.isCompiler)? ((dynamicCount-1)*2) : (dynamicCount-1);	//TODO d
    		final int ASC_DESC_ARRAY_COUNT = dynamicCount-1;	//TODO d
	    	String asc_desc = getOrderByString(ASC_DESC_ARRAY_COUNT);
	    	if(!asc_desc.isEmpty()){
	    		if (!orderby.isEmpty())	orderby += ", ";
	    		orderby += asc_desc;
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
	    		//where = where.replaceAll("\\'","\\\\'");		// ' -> \'
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
	    	String php = Mobile_HTML5.getSessionStartString()
	    			     +"<?php\n";
	    	//php
    		if(!dynamicRowFlg){
	    		statement += getDynamicHTML(tfeID, dynamicCount, dynamicPHPfileName);
    		}else{
    			statement += getDynamicPagingHTML(tfeID, dynamicRow, dynamicPagingCount, dynamicPHPfileName);
    		}
    		php += 
					"    $ret = array();\n" +
					"    $ret['result'] = \"\";\n\n";
    		if(dynamicRowFlg){
    			php += 
						"if ($_POST['currentPage'] != \"\") {\n" +
						"	$cp = $_POST['currentPage'];\n" +
						"	$col = "+numberOfColumns+";\n" +
						"	$r = $_POST['row'] * $col;\n" +
						"	$end = $cp * $r;\n" +
						"	$start = $end - $r + 1;\n" +
						"\n";
    		}
    		php +=
						"    //ユーザ定義\n" +
						((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
						//"    //$dynamic_col = \""+dynamic_col+"\";\n" +
						//"    //$col_num = "+col_num+";                          //カラム数(Java側で指定)\n" +
						"    $table = '"+from+"';\n" +
						"    $where = \""+where+"\";\n" +
						//"    $dynamic_col_array = array("+dynamic_col_array+");\n" +
						//"    //$dynamic_col_num = 1;\n" +//count($dynamic_col_array);\n" +
						"    $dynamic_a_Flg = array("+dynamic_aFlg+");\n" +
						"    $dynamic_mail_Flg = array("+dynamic_mailFlg+");\n" +
						"    $dynamic_pop_Flg = array("+dynamic_popFlg+");\n" +
						"    $groupby = \""+groupby+"\";\n" +
						"    $having = \""+having+"\";\n" +
						"    $orderby = \""+((!orderby.isEmpty())?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
						"    $orderby_atts = \""+new Asc_Desc().get_asc_desc_Array2(ASC_DESC_ARRAY_COUNT)+"\";\n" +	//added by goto 20161113  for @dynamic: distinct order by
						"    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
						((limit!="")?("    $limitNum = "+limit+";\n"):("")) +	//TODO dynamicPaging時にLIMITが指定されていた場合
						"\n";
//						"    $dynamicWord"+dynamicCount+" = checkHTMLsc('%');\n" +
//						"    $dynamicWord"+dynamicCount+" = preg_replace('/　/', ' ', $dynamicWord"+dynamicCount+");       //全角スペースを半角スペースへ\n" +
//						"    $dynamicWord"+dynamicCount+" = preg_replace('/\\s+/', ' ', $dynamicWord"+dynamicCount+");      //連続する半角スペースを1つの半角スペースへ\n" +
//						"    $dynamicWord"+dynamicCount+" = trim($dynamicWord"+dynamicCount+");                            //trim\n" +
//						"    $dynamicWord"+dynamicCount+" = preg_replace('/\\s/', '%', $dynamicWord"+dynamicCount+");       //半角スペースを%へ変換\n" +
//						"\n" +
//						"    if($dynamicWord"+dynamicCount+" != \"\"){\n";
    		
    		//added by goto 20161112 for dynamic foreach
    		if(Mobile_HTML5G3.dynamic_G3){
    			String att = "";
    			for(String x : Mobile_HTML5G3.dynamic_G3_atts){
    				att += "getA('"+x+"').\"||'_'||\".";
    			}
    			if(!att.isEmpty())	att = att.substring(0, att.length()-"||'_'||\".".length());
    			Mobile_HTML5G3.dynamic_G3_atts.clear();
    			
    			php += 	"    //for dynamic foreach\n" +
    					"    if(!empty($where))	$where = '('.$where.') and ';\n" +		//added by goto 20161114  'where () and ...' for dynamic foreach
    					"    $where .= "+att+"='\".$_POST['att'].\"'\";\n" +
    					"\n";
    		}
    		
    		if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
    			php +=	"    $dynamic_db"+dynamicCount+" = new SQLite3($sqlite3_DB);\n";
    		} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
    			php +=	"    $dynamic_db"+dynamicCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n";
    		}
//    		php +=
////						"    $sql = \"SELECT DISTINCT \".$dynamic_col.\" FROM \".$table;\n" +
////						"    if($where != \"\")	$sql .= \" WHERE \".$where.\" \";\n" +
////						"    if($groupby != \"\")	$sql .= \" GROUP BY \".$groupby.\" \";\n" +
////						"    if($having != \"\")	$sql .= \" HAVING \".$having.\" \";\n" +
////						"    $sql .= \" \".$orderby.\" \".$limit;\n" +
//    				"	$sql_a1 = array('d.id', 'd.name');\n" +		//TODO d
//    				"	$sql_a2 = array('e.id', 'e.name');\n";
    		for(int i=0; i<dynamicAttributes.size(); i++){
	    		php +=	"	$sql_a"+(i+1)+" = array("+dynamicAttributes.get(i)+");\n";
    		}
    		php +=
						"	$sql_g = getG($groupby, $having, $orderby);\n" +
						"\n" +
						"	$sql1 = getSQL($sql_a1, $orderby_atts, $table, $where, $sql_g, $limit, null, null);\n";	//changed by goto 20161113  for @dynamic: distinct order by
    		if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
	    		php +=
							"    $result1 = $dynamic_db"+dynamicCount+"->query($sql1);\n" +
							"\n" +
							"    //$i = 0;\n" +
							"    $pop_num = 0;\n" +
							"    $b = \"\";\n" +
							php_str1 +
							"    while($row1 = $result->fetchArray()){\n" +
							//"          //$i++;\n" +
							"          //$b .= str_replace('"+dynamicFuncCountLabel+"', '_'.$i, $row[$j]);\n";	//For function's count
							
							/* nest dynamic string  start */
							//TODO d
							
							
							
							
							/* nest dynamic string  end */
							
	    		php +=	
							((dynamicRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
//							php_str2 +
//							"          for($j=0; $j<$dynamic_col_num; $j++){\n" +
//							"          		$b .= str_replace('"+dynamicFuncCountLabel+"', '_'.$i, $row[$j]);\n" +	//For function's count
//							"          }\n" +
//							php_str3 +
							((dynamicRowFlg)? "          }\n":"") +
							"    }\n" +
							php_str4 +
//							"    }\n" +
							"    unset($dynamic_db"+dynamicCount+");\n\n";
    		} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
	    		php +=
							"    $result1 = pg_query($dynamic_db"+dynamicCount+", $sql1);\n" +
							"\n" +
							"    //$i = 0;\n" +
							"    $pop_num = 0;\n" +
							"    $b = \"\";\n" +
							php_str1 +
							"    while($row1 = pg_fetch_row($result1)){\n" +
							//"          //$i++;\n" +
							"          //$b .= str_replace('"+dynamicFuncCountLabel+"', '_'.$i, $row[$j]);\n";	//For function's count
							
							/* nest dynamic string  start */
							//TODO d
				    		for(int i=0; i<dyamicWhileStrings.size(); i++){
					    		php +=	dyamicWhileStrings.get(i);
				    		}
				    		for(int i=dyamicWhileCount; i>1; i--){		//TODO d 処理の位置
				    			php += " }\n";
				    		}
//				    		if(!decos.containsKey("table"))
//				    			php += "$b .= '</div></div></div></div>';\n";		//TODO d 4つ固定でOK?
				    		
							
							/* nest dynamic string  end */
	    		
	    		php +=	
							((dynamicRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
//							php_str2 +
//							"          for($j=0; $j<$dynamic_col_num; $j++){\n" +
//							"          		$b .= str_replace('"+dynamicFuncCountLabel+"', '_'.$i, $row[$j]);\n" +	//For function's count
//							"          }\n" +
//							php_str3 +
							((dynamicRowFlg)? "          }\n":"") +
							"    }\n" +
							php_str4 +
//							"    }\n" +
							"    pg_close($dynamic_db"+dynamicCount+");\n\n";
	    		
	    		//added by goto 20161112 for dynamic foreach	//TODO
	    		if(Mobile_HTML5G3.dynamic_G3){
	    			php += "    if(pg_num_rows($result1)<1)	$b = \"No Data Found : \".$_POST['att'];	//for dynamic foreach\n";
	    		}
    		}
    		php +=
    					((dynamicRowFlg)? "}\n":"") +
    					"    $ret['result'] = $b;\n";
    		if(dynamicRowFlg){
    			php += 
						"    $ret['start'] = $start;\n" +
						"    $ret['end'] = ($end<$i)? $end:$i;\n" +
						"    $ret['all'] = $i;\n" +
						"    $ret['info'] = (($ret['start']!=$ret['end'])? ($ret['start'].\" - \") : (\"\")) .$ret['end'].\" / \".$ret['all'];\n" +
						"    $ret['currentItems'] = ceil($i/$r);\n";
    		}
    		php += 
						"\n" +
						"    header(\"Content-Type: application/json; charset=utf-8\");\n" +
						"    echo json_encode($ret);\n" +
						"\n" +
						"\n" +
						"function getSQL($sql_a, $orderby_atts, $table, $where, $sql_g, $limit, $sql_a2, $row){\n"+ 	//changed by goto 20161113  for @dynamic: distinct order by
						"	$sql = getSF($sql_a, $orderby_atts, $table);\n" +											//changed by goto 20161113  for @dynamic: distinct order by
						"	if(is_null($sql_a2)){\n" +
						"		if($where != '')	$sql .= ' WHERE '.$where.' ';\n" +
						"		$sql .= $sql_g.' '.$limit;\n" +
						"	}else{\n" +
						"		$sql .= ' WHERE ';\n" +
						"		if($where != '')	$sql .= $where.' AND ';\n" +
						"		$sql .= getW($sql_a2, $row).$sql_g;\n" +
						"	}\n" +
						"	return $sql;\n" +
						"}\n" +
						"function getSF($sql_a, $orderby_atts, $table){\n" +											//changed by goto 20161113  for @dynamic: distinct order by
						"	return 'SELECT DISTINCT '.getAs($sql_a).$orderby_atts.' FROM '.$table;\n" +					//changed by goto 20161113  for @dynamic: distinct order by
						"}\n" +
						"function getAs($atts){\n" +
						"	$r = '';\n" +
						"	foreach($atts as $val){\n" +
						"    	$r .= getA($val).',';\n" +
						"    }\n" +
						"	return substr($r, 0, -1);\n" +
						"}\n" +
						//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
						"function getA($att){\n" +
						"	$sql_as = 'COALESCE(CAST(';\n" +	//TODO d  SQLite
						"	$sql_ae = \" AS varchar), '')\";\n" +
						"	return $sql_as.$att.$sql_ae;\n" +
						"}\n" +
						"function getW($al, $ar){\n" +
						"	$r = '';\n" +
						"	$and = ' AND ';\n" +
						"	for($i=0 ; $i<count($al); $i++){\n" +
						"		$r .= $al[$i].\" = '\".$ar[$i].\"'\".$and;\n" +
						"	}\n" +
						"	return rtrim($r, $and);\n" +
						"}\n" +
						"function getG($groupby, $having, $orderby){\n" +
						"	$r = '';\n" +
						"	if($groupby != '')	$r .= ' GROUP BY '.$groupby.' ';\n" +
						"	if($having != '')	$r .= ' HAVING '.$having.' ';\n" +
						"	$r .= ' '.$orderby;\n" +
						"	return $r;\n" +
						"}\n" +
						"\n" +
						"//XSS対策\n" +
						"function checkHTMLsc($str){\n" +
						"	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
						"}\n" +
						"?>\n";
	    	//End of php

	    	
	    	// 各引数毎に処理した結果をHTMLに書きこむ
	    	html_env.code.append(statement);

	    	if(!dynamicRowFlg){
	    		Mobile_HTML5.createFile(html_env, dynamicPHPfileName, php);//PHPファイルの作成
	    		dynamicCount++;		//TODO d
    		}else{
    			Mobile_HTML5.createFile(html_env, dynamicPHPfileName, php);//PHPファイルの作成
				dynamicPagingCount++;
				dynamicRowFlg = false;
			}
	    	ajax_loadInterval = 0;
	    	
	    	
	    	initVariables();
	    	
	    	
			//Log.e(" - End dynamic process -");
        	return true;
        }
		return false;
	}
	//initVariables
	private static void initVariables() {
		dynamicDisplay = false;
		
		dynamicString = "";
		dynamicHTMLbuf0 = "";
		dynamicHTMLbuf = "";
		//dynamicCount = 1;
		dynamicAttributes.clear();
		
//		Gdepth = 0;
		Gnum = 0;
		sindex = 0;
		
//		Gdepth_old = 0;
//		Gnum_old = 0;
		dynamicAttributeFlg = true;
		dyamicWhileString = "";
		dyamicWhileStrings.clear();
		dyamicWhileCount = 0;
		dyamicWhileCount0 = 0;

		
		//For dyamicPostStringProcess() substring
		html_env_code_length = 0;

		//For Dynamic paging
		dynamicRow = 1;
		dynamicRowFlg = false;
		//dynamicPagingCount = 1;
		dynamicPHPfileName =  "";
		////ajax
		ajax_loadInterval = 0;
	}
	
	//getOrderByString
	private static String getOrderByString(int ASC_DESC_ARRAY_COUNT) {
		String s = "";
		try {
			Asc_Desc ad = new Asc_Desc();
			//System.out.println(dynamicCount-1);
//			ad.asc_desc = ad.asc_desc_Array1.get((dynamicCount-1)*2);
//			ad.asc_desc = ad.asc_desc_Array1.get((!Compiler.isCompiler)? ((dynamicCount-1)*2) : (dynamicCount-1));
			ad.asc_desc = ad.get_asc_desc_Array1(ASC_DESC_ARRAY_COUNT);
			ad.sorting();
			
			Iterator<AscDesc> it = ad.asc_desc.iterator();
			while (it.hasNext()) {
				AscDesc data = it.next();
				s += data.getAscDesc()+", ";
				//Log.info(data.getNo() + " : " + data.getAscDesc());
			}
	        if(!s.isEmpty() && s.contains(","))	s = s.substring(0, s.lastIndexOf(","));
			//System.out.println("s="+s);
		} catch (Exception e) {
			//TODO d	//TODO 20151109
			Log.out("[Error] getOrderByString");
		}
		return s;
	}
	
	//getDynamicHTML
	private static String getDynamicHTML(String tfeID, int num, String phpFileName){
		final String DD_FUNC_NAME = "SSQL_DynamicDisplay"+num;
		final String DD_COMMENT_NAME1 = "SSQL Dynamic"+num;
		final String DD_COMMENT_NAME2 = "SSQL Dynamic Display Data"+num;
		phpFileName = new File(phpFileName).getName();
		String s = "";
		
		if(Mobile_HTML5G3.dynamic_G3)	s +=	LinkForeach.getJS("G3", DD_FUNC_NAME);	//added by goto 20161112 for dynamic foreach
		s +=		
				"\n" +
				"<!-- "+DD_COMMENT_NAME1+" start -->\n" +
				"<!-- "+DD_COMMENT_NAME1+" DIV start -->\n" +
				"<div id=\""+DD_FUNC_NAME+"_Panel\" style=\"\" data-role=\"none\">\n" +
				"<div id=\""+DD_FUNC_NAME+"\" class=\""+tfeID+"\" data-role=\"none\"><!-- "+DD_COMMENT_NAME2+" --></div>\n" +
				"</div>\n" +
				"<!-- "+DD_COMMENT_NAME1+" DIV end -->\n" +
				"\n" +
				"<!-- "+DD_COMMENT_NAME1+" JS start -->\n" +
				"<script type=\"text/javascript\">\n" +
				"<!--\n" +
				DD_FUNC_NAME+"();	//ロード時に実行\n";
		if(ajax_loadInterval>0){
			s += "setInterval(function(){\n" +
				 "	"+DD_FUNC_NAME+"();\n" +
				 "},"+ajax_loadInterval+");\n";
		}
		s +=	"function "+DD_FUNC_NAME+"_echo(str){\n" +
				//"  var textArea = document.getElementById(\""+DD_FUNC_NAME+"\");\n" +
				//"  textArea.innerHTML = str;\n" +
				"  $(\"#"+DD_FUNC_NAME+"\").html(str).trigger(\"create\");\n" +
				"}\n";
		//added by goto 20161112 for dynamic foreach
		if(!Mobile_HTML5G3.dynamic_G3)
			s +=	"function "+DD_FUNC_NAME+"(){\n";
		else
			s +=	"function "+DD_FUNC_NAME+"(value){\n";
		s += 
				"	//ajax: PHPへ値を渡して実行\n" +
				"	$.ajax({\n" +
				"		type: \"POST\",\n" +
				"		url: \""+phpFileName+"\",\n" +
				"		dataType: \"json\",\n";
				
				//added by goto 20161112 for dynamic foreach
				if(Mobile_HTML5G3.dynamic_G3){
					s += "		data: { \"att\":value },\n";
				}
				
		s +=	"		success: function(data, textStatus){\n" +
				"			if (data.result != \"\") {\n" +
				"				"+DD_FUNC_NAME+"_echo(data.result);\n" +
				"			}\n" +
				"		},\n" +
				"		error: function(XMLHttpRequest, textStatus, errorThrown) {\n" +
				"			"+DD_FUNC_NAME+"_echo(textStatus+\"<br>\"+errorThrown);\n" +
				"		}\n" +
				"	});\n" +
				"}\n" +
				"//-->" +
				"</script>\n" +
				"<!-- "+DD_COMMENT_NAME1+" JS end -->\n" +
				"<!-- "+DD_COMMENT_NAME1+" end -->\n\n";
		return s;
	}
	//getDynamicPagingHTML
	private static String getDynamicPagingHTML(String tfeID, int row, int num, String phpFileName){
		final String DDP_FUNC_NAME = "SSQL_DynamicDisplay"+num;
		final String DDP_COMMENT_NAME1 = "SSQL DynamicPaging"+num;
		final String DDP_COMMENT_NAME2 = "SSQL Dynamic Display Data"+num;
		phpFileName = new File(phpFileName).getName();
		
		String s =
				"\n" +
				"<!-- "+DDP_COMMENT_NAME1+" start -->\n" +
				"<!-- "+DDP_COMMENT_NAME1+" DIV start -->\n" +
				"<div id=\""+DDP_FUNC_NAME+"\" class=\""+tfeID+"\" data-role=\"none\"><!-- "+DDP_COMMENT_NAME2+" --></div>\n" +
				"<div id=\""+DDP_FUNC_NAME+"_Buttons\"></div>\n" +
				"<!-- "+DDP_COMMENT_NAME1+" DIV end -->\n" +
				"\n" +
				"<!-- "+DDP_COMMENT_NAME1+" JS start -->\n" +
				"<script type=\"text/javascript\">\n" +
				"<!--\n" +
				DDP_FUNC_NAME+"(1,true);	//初期ロード時\n" +
				DDP_FUNC_NAME+"_setButtons();\n" +
				"\n" +
				"var "+DDP_FUNC_NAME+"_currentItems = 1;		//グローバル変数\n" +
				"function "+DDP_FUNC_NAME+"_echo(str){\n" +
				"  $(\"#"+DDP_FUNC_NAME+"\").html(str).trigger(\"create\");\n" +
				"}\n";
		if(ajax_loadInterval>0){
			s += "\n" +
				 "setInterval(function(){\n" +
				 "	$('#"+DDP_FUNC_NAME+"_Buttons .next').trigger(\"click\");\n" +
				 "},"+ajax_loadInterval+");\n\n";
		}
		s +=	"function "+DDP_FUNC_NAME+"_setButtons(){\n" +
				"	$(function(){\n" +
				"	    $(\"[id="+DDP_FUNC_NAME+"_Buttons]\").pagination({\n" +
				"	        items: "+DDP_FUNC_NAME+"_currentItems, //ページング数\n" +
				"	        displayedPages: 2, 	  //表示したいページング要素数\n" +
				"	        onPageClick: function(pageNum){ "+DDP_FUNC_NAME+"(pageNum,false) }\n" +
				"	    })\n" +
				"	});\n" +
				"}\n" +
				"function "+DDP_FUNC_NAME+"(pn,onload){\n" +
				"	//ajax: PHPへ値を渡して実行\n" +
				"	$.ajax({\n" +
				"		type: \"POST\",\n" +
				"		url: \""+phpFileName+"\",\n" +
				"		dataType: \"json\",\n" +
				"		data: {\n" +
				"			\"currentPage\": pn,\n" +
				"			\"row\": '"+row+"',\n" +
				"		},\n" +
				"		success: function(data, textStatus){\n" +
				"			if (data.result != \"\") {\n" +
				//"				//"+DDP_FUNC_NAME+"_echo("+DDP_FUNC_NAME+"_currentItems+\" \"+data.currentItems+\"<br>\"+data.info+\"<br>\"+data.result);\n" +
				"				"+DDP_FUNC_NAME+"_echo(data.result+\"<span style='font-size:small; color:#808080;'>\"+data.info+\"</span>\");\n" +
				"				if(data.currentItems != null && data.currentItems != "+DDP_FUNC_NAME+"_currentItems){\n" +
				"					//ページ数が変わった場合の処理\n" +
				"					"+DDP_FUNC_NAME+"_currentItems = data.currentItems;\n" +
				"					"+DDP_FUNC_NAME+"_setButtons();\n" +
				"				}\n" +
				"				if(!onload){\n" +
				"					$('html,body').animate({ scrollTop: $('#"+DDP_FUNC_NAME+"').position().top-50 }, 'fast');\n" +
				"				}\n" +
				"			}\n" +
				//"			else {\n" +
				//"				"+DDP_FUNC_NAME+"_echo(\"失敗\");\n" +
				//"			}\n" +
				"		},\n" +
				"		error: function(XMLHttpRequest, textStatus, errorThrown) {\n" +
				"			"+DDP_FUNC_NAME+"_echo(textStatus+\"<br>\"+errorThrown);\n" +
				"		}\n" +
				"	});\n" +
				"}\n" +
				"//-->" +
				"</script>\n" +
				"<!-- "+DDP_COMMENT_NAME1+" JS end -->\n" +
				"<!-- "+DDP_COMMENT_NAME1+" end -->\n\n";
		return s;
	}

}
