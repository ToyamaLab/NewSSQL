package supersql.codegenerator.infinitescroll;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import supersql.codegenerator.Asc_Desc;
import supersql.codegenerator.Asc_Desc.AscDesc;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Compiler.Compiler_Dynamic;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G1;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G3;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_dynamic;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class Infinite_dynamic {
	public Infinite_dynamic() {

	}
	public static boolean dynamicDisplay = false;

	public static String dynamicString = "";
	private static String dynamicHTMLbuf = "";
	//	private static String dynamicHTMLbuf1 = "";
	public static int dynamicCount = 1;
	private final static String DYNAMIC_FUNC_COUNT_LABEL = "___SSQL_DynamicFunc_CountLabel___";

	private static ArrayList<String> dynamicAttributes = new ArrayList<>();
	//	public static int Gdepth = 0;
	//	public static int Gnum = 0;
	//	static int Gdepth_old = 0;
	//	static int Gnum_old = 0;
	private static boolean dynamicAttributeFlg = false;
	//	public static String dynamicWhileString = "";
	private static ArrayList<String> dynamicWhileStrings = new ArrayList<>();
	private static int dynamicWhileCount = 0;
	public static int dynamicWhileCount0 = 0;


	//For [[]]@{dynamic}
	public static ArrayList<Integer> sindex = new ArrayList<>();
	public static ArrayList<Integer> $array_index = new ArrayList<>();
	public static ArrayList<Integer> dynamicAttributes_NestLevels = new ArrayList<>();	//Nest levels of each attributes
	private static ArrayList<String> dynamicAttributes_keys = new ArrayList<>();
	private final static String DYNAMIC_ATTRIBUTES_KEYS_LABEL = "________SupserSQL-DYNAMIC_ATTRIBUTES_KEYS_LABEL________";


	//	//For dyamicPostStringProcess() substring
	//	public static int html_env_code_length = 0;


	//For Dynamic paging
	static int dynamicRow = 1;
	static boolean dynamicRowFlg = false;
	static int dynamicPagingCount = 1;
	//	static String dynamicPHPfileName =  "";
	static ArrayList dynamicPHPfileName = new ArrayList();
	//ajax
	static int ajax_loadInterval = 0;
	
	//added by goto 170604
	//For Dynamic aggregate functions
	private static boolean groupByFlg = false;
	private static HashSet<String> groupBySet = new HashSet<String>();


	//Process
	public static String dynamicFuncArgProcess(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
		//For Function
//		Log.ehtmlInfo("func");
		return createDynamicAttribute(tfe, html_env, decos);
	}
	public static String dynamicAttributeProcess(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
		//For Attribute (C1, C2, G1, G2)
		return createDynamicAttribute(tfe, html_env, decos);
	}
	private static String createDynamicAttribute(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
		String s = ""+tfe;
		s = s.trim();
		if(s.startsWith("\"") && s.endsWith("\"")){
			//not attribute, not number
			s = s.substring(1, s.length()-1);
		}else if(Mobile_HTML5.isNumber(s)){
			//number
		}else{
			
			//attribute
			if(dynamicWhileCount0>1)	dynamicAttributeFlg = false;
			if(dynamicAttributeFlg){
				//Log.e("createDynamicAttribute2: "+s);
				//TODO d
				//				int i = Gnum-1;
				int i = 0;//dynamicCount-1;
				//				int j = new Connector().getSindex();

				int index = Infinite.gLevel0;
				try {
					int si = sindex.get(index);
					sindex.set(index, si+1);	//sindex++
				} catch (Exception e) {
					sindex.add(1);				//sindex=1
				}
				int j = sindex.get(index)-1;	//TODO d2 j -> OK?

				//added by goto 170604
				//For Dynamic aggregate functions
				String afs[] = {"max", "min", "avg", "sum", "count"};
				try {
					for(String x : afs){
						if(decos.containsKey(x)){
							s = x+"("+s+")";
							groupByFlg = true;
						}else{
							groupBySet.add(s);
						}
					}
				} catch (Exception e) {	}

				//String a = "'COALESCE(CAST("+s+" AS varchar), \\'\\')'";	//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
				String a = "'"+s.replace("'", "\\'")+"'";	//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
				//String b = "'.$row"+Gnum+"["+j+"].'";
				//String b = "'.$row1["+j+"].'";

//				int x = Mobile_HTML5.gLevel0+1;
				int x = Infinite.gLevel0+1;

				//$array_index
				int y = 1;							//TODO d2 y -> OK?
				try {
//					y = $array_index.get(Mobile_HTML5.gLevel0-1);
					y = $array_index.get(Infinite.gLevel0-1);
				} catch (Exception e) {	}

				//String b = "'.$array"+x+"_"+y+"["+j+"].'";
				String b = "";
				if(x==1){
					//'.$array1[$i][0].'
					b = "'.$array"+x+"_"+y+"[$i"+x+"]["+j+"].'";
				}else{
					//'.$array2_1[$key2][$j][0].'
					b = "'.$array"+x+"_"+y+"[$key"+x+"][$i"+x+"]["+j+"].'";//taji changed 170620
				}
				//Log.e("b = "+b);

				//dynamicAttributes_keys += ((!dynamicAttributes_keys.isEmpty())? ".'_'." : "")+"$array"+x+"_"+y+"[$i"+x+"]["+j+"]";
				String key = "";
				if(x==1){
					key = "$array"+x+"_"+y+"[$i"+x+"]["+j+"]";
				}else{
					key = "$key"+x+".'_'.";			//TODO d2 x ?
					key += "$array"+x+"_"+y+"[$key"+x+"][$i"+x+"]["+j+"]";
				}
				try {
					//Log.e("gL0 = "+(Mobile_HTML5.gLevel0));
//					String keys = dynamicAttributes_keys.get(Mobile_HTML5.gLevel0);
					String keys = dynamicAttributes_keys.get(Infinite.gLevel0);
//					dynamicAttributes_keys.set(Mobile_HTML5.gLevel0, keys+".'_'."+key);
					dynamicAttributes_keys.set(Infinite.gLevel0, keys+".'_'."+key);
					//Log.e(" keys = "+keys);
				} catch (Exception e) {
					dynamicAttributes_keys.add(key);
				}


				s = b;
				//b = "$b .= '<div>"+b+"</div>';\n";

//				dynamicAttributes_NestLevels.add(Mobile_HTML5.gLevel0);
				dynamicAttributes_NestLevels.add(Infinite.gLevel0);

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

	//taji added 170223 for inifite-scroll
	public static String[] ifsStringProcess(String symbol, Mobile_HTML5Env html_env, String tfeID, String[] ifs_div_String){
		if(Mobile_HTML5.gLevel0 > 0){
			ifs_div_String[0] = "\n<div class=\""+tfeID+"\" onscroll=\"scrolled(this)\" itemnum=\"'.$i1.'\">\n";
			ifs_div_String[1] = "</div>\n";
		}else{
			ifs_div_String[0] = "\n<div class=\""+tfeID+"\" onscroll=\"scrolled(this)\">\n";
			ifs_div_String[1] = "</div>\n";
		}

		return ifs_div_String;
	}


	public static void dyamicPreStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_String, StringBuffer tmp){

		int x = Infinite.gLevel0+1;

		String key_label = DYNAMIC_ATTRIBUTES_KEYS_LABEL+(x-2);

		//$array_index
		int y = 1;							//TODO d2 y -> OK?
		int index = Infinite.gLevel0-1;
		try {
			int ai = $array_index.get(index);
			$array_index.set(index, ai+1);	//$array_index++
			y = ai+1;
		} catch (Exception e) {
			$array_index.add(1);			//$array_index=1
		}

		tmp.append("';\n\n");
		//$key2 = $array1[$i][0].'_'.$array1[$i][1];
		//for($i2=0; $j<count($array2_1[$key2]); $i2++){
		if(!ifs_div_String[0].equals("")){
			tmp.append("          $key"+x+" = "+key_label+";\n");
//			tmp.append("          for($i"+x+"=0; $i"+x+"<count($array"+x+"_"+y+"[$key"+x+"]); $i"+x+"++){\n");
			tmp.append("          for($i"+x+"=$_POST['childcount']; $i"+x+"<; $i"+x+"++){\n");
			tmp.append("          $b .= '\n");
		}else{
			tmp.append("          $b .= '"+ifs_div_String[0]+"';\n");
			tmp.append("          $key"+x+" = "+key_label+";\n");
			tmp.append("          for($i"+x+"=0; $i"+x+"<count($array"+x+"_"+y+"[$key"+x+"]); $i"+x+"++){\n");
			tmp.append("          $b .= '\n");
		}
	}

	public static void dyamicPostStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_String, StringBuffer tmp){
		if(!ifs_div_String[0].equals("")){
			tmp.append("';\n");
			tmp.append("          }\n");
			//			tmp.append("          $b .= '';\n");
			tmp.append("          $b .= '\n");
			//taji comment
		}else{
			tmp.append("';\n");
			tmp.append("          }\n");
			tmp.append("          $b .= '" + ifs_div_String[1] + "';\n");
			tmp.append("          $b .= '\n");
		}

	}

	public static String getDynamicLabel(){	//+Mobile_HTML5.getDinamicLabel()
		//For function's count
		//※ Count付きのfunc()には、+Mobile_HTML5.getDinamicLabel()を付加する
		//TODO dynamicFuncCountLabelがユニーク値かどうか判定
		if(dynamicDisplay){
			return DYNAMIC_FUNC_COUNT_LABEL;
		}
		return "";
	}

	public static boolean dynamicPreProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String classid_for_ifs, StringBuffer tmp){
		//		if(Compiler.isCompiler){
		//			decos.put("dynamic", "");
		//		}
		if(decos.containsKey("dynamic")){
			if(Mobile_HTML5G3.G3)	Mobile_HTML5G3.dynamic_G3 = true;	//added by goto 20161112 for dynamic foreach
			dynamicHTMLbuf = tmp.toString();
			dynamicDisplay = true;
			dynamicAttributeFlg = true;
			dynamicPHPfileName.add(html_env.getFileName2()+"_SSQLdynamic_"+dynamicCount + "_" +classid_for_ifs +".php");

			return true;
		}
		return false;
	}

	public static boolean dynamicStringGetProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, StringBuffer tmp){
		if(dynamicDisplay){
			String currentHTML = tmp.toString();
//			Log.ehtmlInfo(dynamicHTMLbuf);
			dynamicString = currentHTML.substring(dynamicHTMLbuf.length(), currentHTML.length());
			//			Log.ehtmlInfo(dynamicString);
			//			Log.ehtmlInfo(dynamicString);
			//replace KEYS_LABELs
			dynamicAttributes_keys = Mobile_HTML5_dynamic.getdynamicAttributes_keys();
			for(int i=0; i<dynamicAttributes_keys.size(); i++){
				String key_label = DYNAMIC_ATTRIBUTES_KEYS_LABEL+(i);
				String key = dynamicAttributes_keys.get(i);
				//Log.e("replace("+key_label+", "+key+")");
				dynamicString = dynamicString.replace(key_label, key);
			}

			return true;
		}
		return false;
	}
	public static void dyamicWhileStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
		//TODO d
		if(dynamicWhileCount0<=1){
			if(symbol.contains("G1") || symbol.contains("G2")){
				if(dynamicAttributeFlg){
					dynamicWhileStrings.add(dynamicString);
				}
			}
		}
	}

	//initVariables
	private static void initVariables() {
		dynamicDisplay = false;
		//Mobile_HTML5G3.dynamic_G3 = false;

		dynamicString = "";
		dynamicHTMLbuf = "";
		//		dynamicHTMLbuf1 = "";

		//dynamicCount = 1;
		dynamicAttributes.clear();

		//		Gdepth = 0;
		//		Gnum = 0;
		sindex.clear();

		//		Gdepth_old = 0;
		//		Gnum_old = 0;
		dynamicAttributeFlg = false;
		//		dynamicWhileString = "";
		dynamicWhileStrings.clear();
		dynamicWhileCount = 0;
		dynamicWhileCount0 = 0;
		dynamicAttributes_NestLevels.clear();
		dynamicAttributes_keys.clear();


		//		//For dyamicPostStringProcess() substring
		//		html_env_code_length = 0;

		//For Dynamic paging
		dynamicRow = 1;
		dynamicRowFlg = false;
		//dynamicPagingCount = 1;
		//		dynamicPHPfileName =  "";
		dynamicPHPfileName.clear();
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
		boolean isTable = ((Mobile_HTML5.isTable())? true : false);

		String s = "";
		s += ((isTable)? "<tbody>\n" : "");
		if(Mobile_HTML5G3.dynamic_G3)	s +=	LinkForeach.getJS("G3", DD_FUNC_NAME);	//added by goto 20161112 for dynamic foreach
		s +=		
				"\n" +
						"<!-- "+DD_COMMENT_NAME1+" start -->\n" +
						"<!-- "+DD_COMMENT_NAME1+" DIV start -->\n";
		if(isTable){
			if(Mobile_HTML5G1.G1Flg){
				s += "<tr id=\""+DD_FUNC_NAME+"\"><!-- "+DD_COMMENT_NAME2+" --></tr>\n";
			}else{
				s += "<tr><td id=\""+DD_FUNC_NAME+"\"><!-- "+DD_COMMENT_NAME2+" --></td></tr>\n";
			}
		}else{
			s += //"<div id=\""+DD_FUNC_NAME+"_Panel\" style=\"\" data-role=\"none\">\n" +
					//"<div id=\""+DD_FUNC_NAME+"\" class=\""+tfeID+"\" data-role=\"none\"><!-- "+DD_COMMENT_NAME2+" --></div>\n" +
					"<div id=\""+DD_FUNC_NAME+"\" data-role=\"none\"><!-- "+DD_COMMENT_NAME2+" --></div>\n";
			//"</div>\n" +
		}
		s +=	
				"<!-- "+DD_COMMENT_NAME1+" DIV end -->\n" +
						"\n" +
						"<!-- "+DD_COMMENT_NAME1+" JS start -->\n" +
						"<script type=\"text/javascript\">\n" +
						"<!--\n";
		if(!Mobile_HTML5G3.dynamic_G3)
			s += DD_FUNC_NAME+"();	//ロード時に実行\n";
		if(ajax_loadInterval>0){
			s += "setInterval(function(){\n" +
					"	"+DD_FUNC_NAME+"();\n" +
					"},"+ajax_loadInterval+");\n";
		}
		s +=	"function "+DD_FUNC_NAME+"_echo(str){\n" +
				//"  var textArea = document.getElementById(\""+DD_FUNC_NAME+"\");\n" +
				//"  textArea.innerHTML = str;\n" +
				//"  $(\"#"+DD_FUNC_NAME+"\").html(str).trigger(\"create\");\n" +
				"  document.getElementById(\""+DD_FUNC_NAME+"\").innerHTML = str;\n" +
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
				"<!-- "+DD_COMMENT_NAME1+" end -->\n\n" +
				((isTable)? "</tbody>\n" : "");
		return s;
	}
	//getDynamicPagingHTML
	private static String getDynamicPagingHTML(String tfeID, int row, int num, String phpFileName){
		final String DDP_FUNC_NAME = "SSQL_DynamicDisplay"+num;
		final String DDP_COMMENT_NAME1 = "SSQL DynamicPaging"+num;
		final String DDP_COMMENT_NAME2 = "SSQL Dynamic Display Data"+num;
		phpFileName = new File(phpFileName).getName();
		boolean isTable = ((Mobile_HTML5.isTable())? true : false);

		String s =
				((isTable)? "<tbody>\n" : "") +
				"\n" +
				"<!-- "+DDP_COMMENT_NAME1+" start -->\n" +
				"<!-- "+DDP_COMMENT_NAME1+" DIV start -->\n";
		if(isTable){
			if(Mobile_HTML5G1.G1Flg){
				s += "<tr id=\""+DDP_FUNC_NAME+"\"><!-- "+DDP_COMMENT_NAME2+" --></tr>\n";
			}else{
				s += "<tr><td id=\""+DDP_FUNC_NAME+"\"><!-- "+DDP_COMMENT_NAME2+" --></td></tr>\n";
			}
		}else{
			s += //"<div id=\""+DDP_FUNC_NAME+"\" class=\""+tfeID+"\" data-role=\"none\"><!-- "+DDP_COMMENT_NAME2+" --></div>\n" +
					"<div id=\""+DDP_FUNC_NAME+"\" data-role=\"none\"><!-- "+DDP_COMMENT_NAME2+" --></div>\n";
		}
		s +=
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
						//"  $(\"#"+DDP_FUNC_NAME+"\").html(str).trigger(\"create\");\n" +
						"  document.getElementById(\""+DDP_FUNC_NAME+"\").innerHTML = str;\n" +
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
				"<!-- "+DDP_COMMENT_NAME1+" end -->\n\n" +
				((isTable)? "</tbody>\n" : "");
		return s;
	}


	// taji added for infinite-scroll 170225
	public static boolean dynamicProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_string, StringBuffer tmp){
		if(dynamicDisplay){
			if(symbol.contains("G1") || symbol.contains("G2")){
				//				html_env.code = new StringBuffer(dynamicHTMLbuf);
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

				//				//table
				//				if(decos.containsKey("table") || decos.containsKey("table0")){
				//					int border = 1;
				//					if(decos.containsKey("table0"))	border = 0;
				//					//table
				//					php_str1 = "	$b .= '<table border=\""+border+"\">';\n";
				//					php_str2 = "		$b .= '<tr><td>';\n";
				//					php_str3 = "		$b .= '</td></tr>';\n";
				//					php_str4 = "	$b .= '</table>';\n";
				//				}

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
			//	    	Log.info(dynamicString);

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
			
			//added by goto 170604
			//For Dynamic aggregate functions
			if(groupByFlg){
				String gbStrs = "";
		        for (String x : groupBySet) {
		        	gbStrs += (!gbStrs.isEmpty())? ","+x : x;
		        }
				groupby = (groupby.isEmpty())? gbStrs : groupby+","+gbStrs;
		        groupByFlg = false;
			}
			groupBySet = new HashSet<String>();
			
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
				if(query.endsWith(" where"))
					query = query.substring(0,query.lastIndexOf(" where"));
			}
			from = query.trim();


			String statement = "";
			String php = Mobile_HTML5.getSessionStartString()
					+"<?php\n";
			//php
			if(!dynamicRowFlg){
				//Log.info(getDynamicHTML(tfeID, dynamicCount, dynamicPHPfileName));
				//Log.info(tfeID +" "+ dynamicCount +" "+ dynamicPHPfileName);
				//	    		statement += getDynamicHTML(tfeID, dynamicCount, dynamicPHPfileName);
			}else{
				//    			statement += getDynamicPagingHTML(tfeID, dynamicRow, dynamicPagingCount, dynamicPHPfileName);
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
			//taji comment out 170919
//			php +=
//							//"    //ユーザ定義\n" +
//							((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
//							//"    //$dynamic_col = \""+dynamic_col+"\";\n" +
//							//"    //$col_num = "+col_num+";                          //カラム数(Java側で指定)\n" +
//							"    $table = '"+from+"';\n" +
//							"    $where = \""+where+"\";\n" +
//							//"    $dynamic_col_array = array("+dynamic_col_array+");\n" +
//							//"    //$dynamic_col_num = 1;\n" +//count($dynamic_col_array);\n" +
//							"    $dynamic_a_Flg = array("+dynamic_aFlg+");\n" +
//							"    $dynamic_mail_Flg = array("+dynamic_mailFlg+");\n" +
//							"    $dynamic_pop_Flg = array("+dynamic_popFlg+");\n" +
//							"    $groupby = \""+groupby+"\";\n" +
//							"    $having = \""+having+"\";\n" +
//							"    $orderby = \""+((!orderby.isEmpty())?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
//							"    $orderby_atts = \""+new Asc_Desc().get_asc_desc_Array2(ASC_DESC_ARRAY_COUNT)+"\";\n" +	//added by goto 20161113  for @dynamic: distinct order by
//							"    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
//							((limit!="")?("    $limitNum = "+limit+";\n"):("")) +	//TODO dynamicPaging時にLIMITが指定されていた場合
//							"\n";
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
				//Mobile_HTML5G3.dynamic_G3_atts.clear();

				php += 	"    //for dynamic foreach\n" +
						"    if(!empty($where))	$where = '('.$where.') and ';\n" +		//added by goto 20161114  'where () and ...' for dynamic foreach
						"    $where .= "+att+"='\".$_POST['att'].\"'\";\n" +
						"\n";
			}
			//Mobile_HTML5G3.dynamic_G3_atts.clear();

			if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
				php +=	"    $dynamic_db"+dynamicCount+" = new SQLite3($sqlite3_DB);\n";
			} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
//				php +=	"    $dynamic_db"+dynamicCount+" = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n";
				php +=	"	$dsn = 'pgsql:dbname="+ DB + " host="+ HOST +"';\n";
				php +=	"	$user = '"+ USER +"';\n";
				php +=	"	$pass = '"+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"';\n";
				php +=	"	$dynamic_db"+dynamicCount+ " = new PDO($dsn, $user, $pass, array(PDO::ATTR_PERSISTENT => true));\n";
			}
			php += "	$dynamic_db"+dynamicCount+"->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);\n"
					+ "	$dynamic_db"+dynamicCount+"->beginTransaction();\n";
			//    		php +=
			////						"    $sql = \"SELECT DISTINCT \".$dynamic_col.\" FROM \".$table;\n" +
			////						"    if($where != \"\")	$sql .= \" WHERE \".$where.\" \";\n" +
			////						"    if($groupby != \"\")	$sql .= \" GROUP BY \".$groupby.\" \";\n" +
			////						"    if($having != \"\")	$sql .= \" HAVING \".$having.\" \";\n" +
			////						"    $sql .= \" \".$orderby.\" \".$limit;\n" +
			//    				"	$sql_a1 = array('d.id', 'd.name');\n" +		//TODO d
			//    				"	$sql_a2 = array('e.id', 'e.name');\n";
			//taji comment out 170919
//			for(int i=0; i<dynamicAttributes.size(); i++){
//				php +=	"	$sql_a"+(i+1)+" = array("+dynamicAttributes.get(i)+");\n";
//			}
//			php +=
//					"	$sql_g = getG($groupby, $having, $orderby);\n" +
//							"\n" +
//							"	$sql1 = getSQL($sql_a1, $orderby_atts, $table, $where, $sql_g, $limit, null, null);\n";	//changed by goto 20161113  for @dynamic: distinct order by
			php += "	$sql1 = 'FETCH FORWARD 3 IN cu';\n";//TODO 3->変数, カーソル名を取得
			if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
				php +=
						"    $result1 = $dynamic_db"+dynamicCount+"->query($sql1);\n" +
								"\n" +
								"    //$i = 0;\n" +
								"    $j = 0;\n" +
								"    $pop_num = 0;\n" +
								"    $b = \"\";\n" +
								php_str1 +
								"\n"+
								Compiler_Dynamic.createNestWhile(dynamicAttributes_NestLevels)+
								//"    while($row1 = $result->fetchArray()){\n" +
								"    for($i1=0; $i1<count($array1_1); $i1++){\n" +
								//"          //$i++;\n" +
								"          //$b .= str_replace('"+DYNAMIC_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count

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
						"    $st1 = $dynamic_db"+dynamicCount+"->prepare($sql1);\n" +
								"    $st1->execute();\n"+
								"\n" +
								"    //$i = 0;\n" +
								"    $j = 0;\n" +
								"    $pop_num = 0;\n" +
								"    $b = \"\";\n" +
								php_str1 +
								"\n"+
								Compiler_Dynamic.createNestWhile(dynamicAttributes_NestLevels);//TODO 
				//"    while($row1 = pg_fetch_row($result1)){\n" +
				if(!ifs_div_string[0].equals("") && Infinite.infinite_level > 0){
					php += "          $i1 = $_POST['itemnum'];\n";//todo define $i1
				}else{
					php += "    for($i1=0; $i1<count($array1_1); $i1++){\n" +
							//"          //$i++;\n" +
							"          //$b .= str_replace('"+DYNAMIC_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count
				}
				/* nest dynamic string  start */
				//TODO d
				for(int i=0; i<dynamicWhileStrings.size(); i++){
					php +=	"          $b .= '"+dynamicWhileStrings.get(i)+"';\n";
				}
				for(int i=dynamicWhileCount; i>1; i--){		//TODO d 処理の位置
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
						((dynamicRowFlg)? "          }\n":"");
				if(!(Infinite.infinite_level > 0)){
					php += "    }\n";
				}
				php +=	php_str4 +
						"    $dynamic_db"+dynamicCount+"->commit();\n\n";

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
							"    //header(\"Content-Type: application/json; charset=utf-8\");\n" +
							"    echo json_encode($ret);\n" +
							"\n" +
							"\n" +
							//taji comment out 170919
//							"function getSQL($sql_a, $orderby_atts, $table, $where, $sql_g, $limit, $sql_a2, $row){\n"+ 	//changed by goto 20161113  for @dynamic: distinct order by
//							"	$sql = getSF($sql_a, $orderby_atts, $table);\n" +											//changed by goto 20161113  for @dynamic: distinct order by
//							"	if(is_null($sql_a2)){\n" +
//							"		if($where != '')	$sql .= ' WHERE '.$where.' ';\n" +
//							"		$sql .= $sql_g.' '.$limit;\n" +
//							"	}else{\n" +
//							"		$sql .= ' WHERE ';\n" +
//							"		if($where != '')	$sql .= $where.' AND ';\n" +
//							"		$sql .= getW($sql_a2, $row).$sql_g;\n" +
//							"	}\n" +
//							"	return $sql;\n" +
//							"}\n" +
//							"function getSF($sql_a, $orderby_atts, $table){\n" +											//changed by goto 20161113  for @dynamic: distinct order by
//							"	return 'SELECT DISTINCT '.getAs($sql_a).$orderby_atts.' FROM '.$table;\n" +					//changed by goto 20161113  for @dynamic: distinct order by
//							"}\n" +
//							"function getAs($atts){\n" +
//							"	$r = '';\n" +
//							"	foreach($atts as $val){\n" +
//							"    	$r .= getA($val).',';\n" +
//							"    }\n" +
//							"	return substr($r, 0, -1);\n" +
//							"}\n" +
//							//for displaying rows which include NULL values (common to postgresql, sqlie, mysql)
//							"function getA($att){\n" +
//							"	$sql_as = 'COALESCE(CAST(';\n" +	//TODO d  SQLite
//							"	$sql_ae = \" AS varchar), '')\";\n" +
//							"	return $sql_as.$att.$sql_ae;\n" +
//							"}\n" +
//							"function getW($al, $ar){\n" +
//							"	$r = '';\n" +
//							"	$and = ' AND ';\n" +
//							"	for($i=0 ; $i<count($al); $i++){\n" +
//							"		$r .= $al[$i].\" = '\".$ar[$i].\"'\".$and;\n" +
//							"	}\n" +
//							"	return rtrim($r, $and);\n" +
//							"}\n" +
//							"function getG($groupby, $having, $orderby){\n" +
//							"	$r = '';\n" +
//							"	if($groupby != '')	$r .= ' GROUP BY '.$groupby.' ';\n" +
//							"	if($having != '')	$r .= ' HAVING '.$having.' ';\n" +
//							"	$r .= ' '.$orderby;\n" +
//							"	return $r;\n" +
//							"}\n" +
//							"\n" +
							//"//XSS対策\n" +
							"function checkHTMLsc($str){\n" +
							"	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
							"}\n" +
							"?>\n";
			//End of php

			// 各引数毎に処理した結果をHTMLに書きこむ
			//	    	html_env.code.append(statement);

			if(!dynamicRowFlg){
				Mobile_HTML5.createFile(html_env, (String) dynamicPHPfileName.get(dynamicPHPfileName.size() - 1), php);//PHPファイルの作成
				dynamicPHPfileName.remove(dynamicPHPfileName.size() - 1);
				dynamicCount++;		//TODO d
			}else{
				Mobile_HTML5.createFile(html_env, (String) dynamicPHPfileName.get(dynamicPHPfileName.size() - 1), php);//PHPファイルの作成
				dynamicPHPfileName.remove(dynamicPHPfileName.size() - 1);
				dynamicPagingCount++;
				dynamicRowFlg = false;
			}
			ajax_loadInterval = 0;

			dynamicWhileStrings.clear();
			//			initVariables();

			//Log.e(" - End dynamic process -");
			return true;
		}
		return false;
	}

}
