package supersql.codegenerator.Mobile_HTML5;

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
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class Mobile_HTML5_stream {

  public static boolean streamDisplay = false;
  public static boolean streamPullDisplay = false;
  public static boolean streamPushDisplay = false;

  public static String streamString = "";
  private static String streamHTMLbuf = "";
  public static int streamCount = 1;
  private final static String STREAM_FUNC_COUNT_LABEL = "___SSQL_StreamFunc_CountLabel___";

  private static ArrayList<String> streamAttributes = new ArrayList<>();
  private static boolean streamAttributeFlg = false;
  private static ArrayList<String> streamWhileStrings = new ArrayList<>();
  private static int streamWhileCount = 0;
  public static int streamWhileCount0 = 0;


  public static ArrayList<Integer> sindex = new ArrayList<>();
  public static ArrayList<Integer> $array_index = new ArrayList<>();
  public static ArrayList<Integer> streamAttributes_NestLevels = new ArrayList<>();	//Nest levels of each attributes
  private static ArrayList<String> streamAttributes_keys = new ArrayList<>();
  private final static String STREAM_ATTRIBUTES_KEYS_LABEL = "________SupserSQL-STREAM_ATTRIBUTES_KEYS_LABEL________";

  static int streamRow = 1;
  static boolean streamRowFlg = false;
  static int streamPagingCount = 1;
  static String streamPHPfileName =  "";
  //ajax
  // static int ajax_loadInterval = 0;

  //For aggregate functions
  private static boolean groupByFlg = false;
  private static HashSet<String> groupBySet = new HashSet<String>();


  //Process
  public static String streamFuncArgProcess(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
    //For Function
    return createStreamAttribute(tfe, html_env, decos);
  }
  public static String streamAttributeProcess(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
    //For Attribute (C1, C2, G1, G2)
    return createStreamAttribute(tfe, html_env, decos);
  }
  public static ArrayList<String> getstreamAttributes_keys(){
    return streamAttributes_keys;
  }
  private static String createStreamAttribute(ITFE tfe, Mobile_HTML5Env html_env, DecorateList decos){
    String s = ""+tfe;
    s = s.trim();
    if(s.startsWith("\"") && s.endsWith("\"")){
      //not attribute, not number
      s = s.substring(1, s.length()-1);
    }else if(Mobile_HTML5.isNumber(s)){
      //number
    }else{
      //attribute
      if(streamWhileCount0>1)	streamAttributeFlg = false;
      if(streamAttributeFlg){
        int i = 0;

        int index = Mobile_HTML5.gLevel0;
        try {
          int si = sindex.get(index);
          sindex.set(index, si+1);	//sindex++
        } catch (Exception e) {
          sindex.add(1);				//sindex=1
        }
        int j = sindex.get(index)-1;

        //For aggregate functions
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

        String a = "'"+s.replace("'", "\\'")+"'";

        int x = Mobile_HTML5.gLevel0+1;

        //$array_index
        int y = 1;
        try {
          y = $array_index.get(Mobile_HTML5.gLevel0-1);
        } catch (Exception e) {	}

        String b = "";
        if(x==1){
          b = "'.$array"+x+"_"+y+"[$i"+x+"]["+j+"].'";
        }else{
          b = "'.$array"+x+"_"+y+"[$key"+x+"][$i"+x+"]["+j+"].'";
        }

        String key = "";
        if(x==1){
          key = "$array"+x+"_"+y+"[$i"+x+"]["+j+"]";
        }else{
          key = "$key"+x+".'_'.";
          key += "$array"+x+"_"+y+"[$key"+x+"][$i"+x+"]["+j+"]";
        }
        try {
          String keys = streamAttributes_keys.get(Mobile_HTML5.gLevel0);
          streamAttributes_keys.set(Mobile_HTML5.gLevel0, keys+".'_'."+key);
        } catch (Exception e) {
          streamAttributes_keys.add(key);
        }

        s = b;
        streamAttributes_NestLevels.add(Mobile_HTML5.gLevel0);

        //add dyamicAttributes
        try {
          String a0 = streamAttributes.get(i);
          if(!a0.isEmpty()) a = a0+", "+a;
          streamAttributes.set(i, a);
        } catch (Exception e) {
          streamAttributes.add(a);
        }
      }
    }
    return s;
  }

  public static void streamPreStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){

    int x = Mobile_HTML5.gLevel0+1;

    String key_label = STREAM_ATTRIBUTES_KEYS_LABEL+(x-2);

    //$array_index
    int y = 1;
    int index = Mobile_HTML5.gLevel0-1;
    try {
      int ai = $array_index.get(index);
      $array_index.set(index, ai+1);
      y = ai+1;
    } catch (Exception e) {
      $array_index.add(1);
    }


    html_env.code.append("';\n\n");
    html_env.code.append("          $key"+x+" = "+key_label+";\n");
    html_env.code.append("          for($i"+x+"=0; $i"+x+"<count($array"+x+"_"+y+"[$key"+x+"]); $i"+x+"++){\n");
    html_env.code.append("          $b .= '\n");
  }
  public static void streamPreStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_String){

    int x = Mobile_HTML5.gLevel0+1;

    String key_label = STREAM_ATTRIBUTES_KEYS_LABEL+(x-2);

    //$array_index
    int y = 1;
    int index = Mobile_HTML5.gLevel0-1;
    try {
      int ai = $array_index.get(index);
      $array_index.set(index, ai+1);	//$array_index++
      y = ai+1;
    } catch (Exception e) {
      $array_index.add(1);			//$array_index=1
    }

    html_env.code.append("';\n\n");
    html_env.code.append("          $b .= '"+ifs_div_String[0]+"';\n");
    html_env.code.append("          $key"+x+" = "+key_label+";\n");
    html_env.code.append("          for($i"+x+"=0; $i"+x+"<count($array"+x+"_"+y+"[$key"+x+"]); $i"+x+"++){\n");
    html_env.code.append("          $b .= '\n");
  }

  public static void streamPostStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
    html_env.code.append("';\n");
    html_env.code.append("          }\n\n");
    html_env.code.append("          $b .= '\n");
  }
  public static void streamPostStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env, String[] ifs_div_String){
    html_env.code.append("';\n");
    html_env.code.append("          }\n");
    html_env.code.append("          $b .= '" + ifs_div_String[1] + "';\n");
    html_env.code.append("          $b .= '\n");
  }

  public static String getStreamLabel(){
    //For function's count
    if(streamDisplay){
      return STREAM_FUNC_COUNT_LABEL;
    }
    return "";
  }

  public static boolean streamPreProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
    if(decos.containsKey("stream")){
      if(Mobile_HTML5G3.G3)	Mobile_HTML5G3.stream_G3 = true;	//for foreach
      streamHTMLbuf = html_env.code.toString();
      streamDisplay = true;
      streamPullDisplay = true;

      // if(decos.containsKey("pull")){
      //   streamPullDisplay = true;
      //   streamPushDisplay = false;
      // }
      // else if (decos.containsKey("push")){
      //   streamPullDisplay = false;
      //   streamPushDisplay = true;
      // }

      String s = "";
      if(decos.containsKey("mode")) 	s = decos.getStr("mode");
      if(s.equals("pull")){
        streamPullDisplay = true;
        streamPushDisplay = false;
      }
      else if(s.equals("push")){
        streamPullDisplay = false;
        streamPushDisplay = true;
      }

      streamAttributeFlg = true;
      streamPHPfileName = html_env.getFileName2()+"_SSQLstream_"+streamCount+".php";
      //Log.info("streamPHPfileName = "+streamPHPfileName);

      if(decos.containsKey("row")){
        streamRow = Integer.parseInt(decos.getStr("row").replace("\"", ""));
        if(streamRow<1){	//範囲外のとき
          Log.err("<<Warning>> row指定の範囲は、1〜です。指定された「row="+streamRow+"」は使用できません。");
        }else{
          streamRowFlg = true;
          streamPHPfileName = html_env.getFileName2()+"_SSQLstreamPaging_"+streamPagingCount+".php";
        }
      }
      return true;
    }
    return false;
  }

  public static boolean streamStringGetProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
    if(streamDisplay){
      String currentHTML = html_env.code.toString();
      streamString = currentHTML.substring(streamHTMLbuf.length(), currentHTML.length());
      //replace KEYS_LABELs
      for(int i=0; i<streamAttributes_keys.size(); i++){
        String key_label = STREAM_ATTRIBUTES_KEYS_LABEL+(i);
        String key = streamAttributes_keys.get(i);

        streamString = streamString.replace(key_label, key);
      }

      return true;
    }
    return false;
  }

  public static void streamWhileStringProcess(String symbol, DecorateList decos, Mobile_HTML5Env html_env){
    if(streamWhileCount0<=1){
      if(symbol.contains("G1") || symbol.contains("G2")){
        if(streamAttributeFlg){
          streamWhileStrings.add(streamString);
        }
      }
    }
  }

  public static boolean streamPullProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env){
    if(streamDisplay){
      if(symbol.contains("G1") || symbol.contains("G2")){
        html_env.code = new StringBuffer(streamHTMLbuf);
      }

      int numberOfColumns = 1;
      String php_str1 = "", php_str2 = "", php_str3 = "", php_str4 = "";

      if(!symbol.contains("G1") && !symbol.contains("G2")){

        if(decos.containsKey("table")){
          streamString = "'<table border=\"1\"><tr>"+streamString+"</tr></table>'";
        }else if(decos.containsKey("table0")){
          streamString = "'<table><tr>"+streamString+"</tr></table>'";
        }else{
          streamString = "'"+streamString+"</div>'";
        }
      }else{
        //G1, G2
        streamString = "'"+streamString+"'";

        //decos contains Key("column")
        if(decos.containsKey("column")){
          try{
            numberOfColumns = Integer.parseInt(decos.getStr("column"));
          }catch(Exception e){
          }
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


      streamString = streamString.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");	//改行コードの削除
      streamString = streamString.replaceAll("\"", "\\\\\"");

      String columns = "";
      String after_from = "";
      columns = streamString;
      after_from = Mobile_HTML5Function.after_from_string;

      if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();

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
      }
      s_array[col_num-1] = columns0;

      int j=0;
      for(int i=0; i<col_num; i++){
        if(s_array[i].contains(":")){
          s_name_array[j++] = s_array[i].substring(0,s_array[i].indexOf(":"));
          s_array[i] = s_array[i].substring(s_array[i].indexOf(":")+1);
        }else{
          if(!s_array[i].contains(")"))	s_name_array[j++] = s_array[i];
        }
      }
      boolean groupbyFlg = false;	//Flg
      String a = "";
      String stream_col = "";
      String stream_col_array = "\"";
      String stream_aFlg = "\"";		//Flg
      String stream_mailFlg = "\"";		//Flg
      String stream_popFlg = "\"";	//Flg
      int a_pop_count = 0;
      for(int i=0; i<col_num; i++){
        a = s_array[i].replaceAll(" ","");
        if( a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
        if(a.startsWith("a(") || a.startsWith("anchor(")){
          stream_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
        if(a.startsWith("mail(")){
          stream_mailFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_mailFlg += "false\""+((i<col_num-1)?(",\""):(""));
        if(a.startsWith("pop(") || a.startsWith("popup(")){
          stream_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
        stream_col += s_array[i] +((i<col_num-1)?(","):(""));
        stream_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
      }
      col_num -= a_pop_count;
      stream_col = stream_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
      stream_col_array = stream_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");

      stream_col = streamString;
      stream_col_array = streamString;


      String DBMS = GlobalEnv.getdbms();										//DBMS
      String DB = GlobalEnv.getdbname();										//DB
      String HOST = "", PORT = "", USER = "", PASSWD = "";
      if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        HOST = GlobalEnv.gethost();
        PORT = GlobalEnv.getport();
        USER = GlobalEnv.getusername();
        PASSWD = GlobalEnv.getpassword();
      }

      String query = "";
      query = after_from.toLowerCase();			//From以下を第三引数へ書く場合

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

      final int ASC_DESC_ARRAY_COUNT = streamCount-1;
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
      String php = Mobile_HTML5.getSessionStartString();
      //Start of php
      php +=
      "<?php\n";
      if(!streamRowFlg){
        statement += getStreamPullHTML(tfeID, streamCount, streamPHPfileName);
      }else{
        statement += getStreamPagingHTML(tfeID, streamRow, streamPagingCount, streamPHPfileName);
      }
      php +=
      "    $ret = array();\n" +
      "    $ret['result'] = \"\";\n\n";
      if(streamRowFlg){
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
      ((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
      "    $table = '"+from+"';\n" +
      "    $where = \""+where+"\";\n" +
      "    $stream_a_Flg = array("+stream_aFlg+");\n" +
      "    $stream_mail_Flg = array("+stream_mailFlg+");\n" +
      "    $stream_pop_Flg = array("+stream_popFlg+");\n" +
      "    $groupby = \""+groupby+"\";\n" +
      "    $having = \""+having+"\";\n" +
      "    $orderby = \""+((!orderby.isEmpty())?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
      "    $orderby_atts = \""+new Asc_Desc().get_asc_desc_Array2(ASC_DESC_ARRAY_COUNT)+"\";\n" +
      "    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
      ((limit!="")?("    $limitNum = "+limit+";\n"):("")) +
      "\n";

      if(Mobile_HTML5G3.stream_G3){
        String att = "";
        for(String x : Mobile_HTML5G3.stream_G3_atts){
          att += "getA('"+x+"').\"||'_'||\".";
        }
        if(!att.isEmpty())	att = att.substring(0, att.length()-"||'_'||\".".length());

        php += 	"    //for stream foreach\n" +
        "    if(!empty($where))	$where = '('.$where.') and ';\n" +
        "    $where .= "+att+"='\".$_POST['att'].\"'\";\n" +
        "\n";
      }

      if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
        php +=	"    $stream_db"+streamCount+" = new SQLite3($sqlite3_DB);\n";
      } else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        php +=	"    $stream_db"+streamCount+" = pg_connect (\"host="+HOST+" port="+PORT+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n";
      }
      for(int i=0; i<streamAttributes.size(); i++){
        php +=	"	$sql_a"+(i+1)+" = array("+streamAttributes.get(i)+");\n";
      }
      php +=
      "	$sql_g = getG($groupby, $having, $orderby);\n" +
      "\n" +
      "	$sql1 = getSQL($sql_a1, $orderby_atts, $table, $where, $sql_g, $limit, null, null);\n";
      if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
        php +=
        "    $result1 = $stream_db"+streamCount+"->query($sql1);\n" +
        "\n" +
        "    //$i = 0;\n" +
        "    $j = 0;\n" +
        "    $pop_num = 0;\n" +
        "    $b = \"\";\n" +
        php_str1 +
        "\n"+
        Compiler_Dynamic.createNestWhile(streamAttributes_NestLevels)+
        "    for($i1=0; $i1<count($array1_1); $i1++){\n" +
        "          //$b .= str_replace('"+STREAM_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count

        php +=
        ((streamRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
        ((streamRowFlg)? "          }\n":"") +
        "    }\n" +
        php_str4 +
        "    unset($stream_db"+streamCount+");\n\n";
      } else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        php +=
        "    $result1 = pg_query($stream_db"+streamCount+", $sql1);\n" +
        "\n" +
        "    //$i = 0;\n" +
        "    $j = 0;\n" +
        "    $pop_num = 0;\n" +
        "    $b = \"\";\n" +
        php_str1 +
        "\n"+
        Compiler_Dynamic.createNestWhile(streamAttributes_NestLevels)+
        "    for($i1=0; $i1<count($array1_1); $i1++){\n" +
        "          //$b .= str_replace('"+STREAM_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count


        for(int i=0; i<streamWhileStrings.size(); i++){
          php +=	"          $b .= '"+streamWhileStrings.get(i)+"';\n";
        }
        for(int i=streamWhileCount; i>1; i--){
          php += " }\n";
        }


        php +=
        ((streamRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
        ((streamRowFlg)? "          }\n":"") +
        "    }\n" +
        php_str4 +
        "    pg_close($stream_db"+streamCount+");\n\n";

        if(Mobile_HTML5G3.stream_G3){
          php += "    if(pg_num_rows($result1)<1)	$b = \"No Data Found : \".$_POST['att'];	//for stream foreach\n";
        }
      }
      php +=
      ((streamRowFlg)? "}\n":"") +
      "    $ret['result'] = $b;\n";
      if(streamRowFlg){
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
      "function getSQL($sql_a, $orderby_atts, $table, $where, $sql_g, $limit, $sql_a2, $row){\n"+
      "	$sql = getSF($sql_a, $orderby_atts, $table);\n" +
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
      "function getSF($sql_a, $orderby_atts, $table){\n" +
      "	return 'SELECT DISTINCT '.getAs($sql_a).$orderby_atts.' FROM '.$table;\n" +
      "}\n" +
      "function getAs($atts){\n" +
      "	$r = '';\n" +
      "	foreach($atts as $val){\n" +
      "    	$r .= getA($val).',';\n" +
      "    }\n" +
      "	return substr($r, 0, -1);\n" +
      "}\n" +
      "function getA($att){\n" +
      "	$sql_as = 'COALESCE(CAST(';\n" +
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
      //"//XSS対策\n" +
      "function checkHTMLsc($str){\n" +
      "	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
      "}\n" +
      "?>\n";
      //End of php

      // 各引数毎に処理した結果をHTMLに書きこむ
      html_env.code.append(statement);

      if(!streamRowFlg){
        Mobile_HTML5.createFile(html_env, streamPHPfileName, php);//PHPファイルの作成
        streamCount++;
      }else{
        Mobile_HTML5.createFile(html_env, streamPHPfileName, php);//PHPファイルの作成
        streamPagingCount++;
        streamRowFlg = false;
      }
      // ajax_loadInterval = 0;

      initVariables();
      return true;
    }
    return false;
  }

  public static boolean streamPushProcess(String symbol, String tfeID, DecorateList decos, Mobile_HTML5Env html_env){
    if(streamDisplay){
      if(symbol.contains("G1") || symbol.contains("G2")){
        html_env.code = new StringBuffer(streamHTMLbuf);
      }

      int numberOfColumns = 1;
      String php_str1 = "", php_str2 = "", php_str3 = "", php_str4 = "";

      if(!symbol.contains("G1") && !symbol.contains("G2")){

        if(decos.containsKey("table")){
          streamString = "'<table border=\"1\"><tr>"+streamString+"</tr></table>'";
        }else if(decos.containsKey("table0")){
          streamString = "'<table><tr>"+streamString+"</tr></table>'";
        }else{
          streamString = "'"+streamString+"</div>'";
        }
      }else{
        //G1, G2
        streamString = "'"+streamString+"'";

        //decos contains Key("column")
        if(decos.containsKey("column")){
          try{
            numberOfColumns = Integer.parseInt(decos.getStr("column"));
          }catch(Exception e){
          }
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


      streamString = streamString.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");	//改行コードの削除
      streamString = streamString.replaceAll("\"", "\\\\\"");

      String columns = "";
      String after_from = "";
      columns = streamString;
      after_from = Mobile_HTML5Function.after_from_string;

      if(after_from.toLowerCase().startsWith("from "))	after_from = after_from.substring("from".length()).trim();

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
      }
      s_array[col_num-1] = columns0;

      int j=0;
      for(int i=0; i<col_num; i++){
        if(s_array[i].contains(":")){
          s_name_array[j++] = s_array[i].substring(0,s_array[i].indexOf(":"));
          s_array[i] = s_array[i].substring(s_array[i].indexOf(":")+1);
        }else{
          if(!s_array[i].contains(")"))	s_name_array[j++] = s_array[i];
        }
      }
      boolean groupbyFlg = false;	//Flg
      String a = "";
      String stream_col = "";
      String stream_col_array = "\"";
      String stream_aFlg = "\"";		//Flg
      String stream_mailFlg = "\"";		//Flg
      String stream_popFlg = "\"";	//Flg
      int a_pop_count = 0;
      for(int i=0; i<col_num; i++){
        a = s_array[i].replaceAll(" ","");
        if( a.startsWith("max(") || a.startsWith("min(") || a.startsWith("avg(") ||  a.startsWith("count(") )	groupbyFlg = true;
        if(a.startsWith("a(") || a.startsWith("anchor(")){
          stream_aFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_aFlg += "false\""+((i<col_num-1)?(",\""):(""));
        if(a.startsWith("mail(")){
          stream_mailFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_mailFlg += "false\""+((i<col_num-1)?(",\""):(""));
        if(a.startsWith("pop(") || a.startsWith("popup(")){
          stream_popFlg += "true\""+((i<col_num-1)?(",\""):(""));
          if(a.endsWith(")")){
            stream_col += s_array[i]+",";
            stream_col_array += s_array[i]+"\",\"";
            stream_aFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_mailFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
            stream_popFlg += ((i<col_num-1)?(""):(",\""))+"false\""+((i<col_num-1)?(",\""):(""));
          }else	a_pop_count++;
        }else
        stream_popFlg += "false\""+((i<col_num-1)?(",\""):(""));
        stream_col += s_array[i] +((i<col_num-1)?(","):(""));
        stream_col_array += s_array[i] +"\""+((i<col_num-1)?(",\""):(""));
      }
      col_num -= a_pop_count;
      stream_col = stream_col.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");
      stream_col_array = stream_col_array.replaceAll("a\\(","").replaceAll("anchor\\(","").replaceAll("mail\\(","").replaceAll("pop\\(","").replaceAll("popup\\(","").replaceAll("count\\(\\*\\)","count[*]").replaceAll("\\)","").replaceAll("count\\[\\*\\]","count(*)");

      stream_col = streamString;
      stream_col_array = streamString;


      String DBMS = GlobalEnv.getdbms();										//DBMS
      String DB = GlobalEnv.getdbname();										//DB
      String HOST = "", PORT = "", USER = "", PASSWD = "";
      if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        HOST = GlobalEnv.gethost();
        PORT = GlobalEnv.getport();
        USER = GlobalEnv.getusername();
        PASSWD = GlobalEnv.getpassword();
      }

      String query = "";
      query = after_from.toLowerCase();			//From以下を第三引数へ書く場合

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

      final int ASC_DESC_ARRAY_COUNT = streamCount-1;
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
      String php = Mobile_HTML5.getSessionStartString();
      //Start of php
      php +=
      "<?php\n";
      if(!streamRowFlg){
        statement += getStreamPushHTML(tfeID, streamCount, streamPHPfileName);
      }else{
        statement += getStreamPagingHTML(tfeID, streamRow, streamPagingCount, streamPHPfileName);
      }
      php +=
      "    $ret = array();\n" +
      "    $ret['result'] = \"\";\n\n";
      if(streamRowFlg){
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
      //"    //ユーザ定義\n" +
      ((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
      "    $table = '"+from+"';\n" +
      "    $where = \""+where+"\";\n" +
      "    $stream_a_Flg = array("+stream_aFlg+");\n" +
      "    $stream_mail_Flg = array("+stream_mailFlg+");\n" +
      "    $stream_pop_Flg = array("+stream_popFlg+");\n" +
      "    $groupby = \""+groupby+"\";\n" +
      "    $having = \""+having+"\";\n" +
      "    $orderby = \""+((!orderby.isEmpty())?(" ORDER BY "+orderby+" "):("")) +"\";\n" +
      "    $orderby_atts = \""+new Asc_Desc().get_asc_desc_Array2(ASC_DESC_ARRAY_COUNT)+"\";\n" +
      "    $limit = \""+((limit!="")?(" LIMIT "+limit+" "):("")) +"\";\n" +
      ((limit!="")?("    $limitNum = "+limit+";\n"):("")) +
      "\n";

      //for foreach
      if(Mobile_HTML5G3.stream_G3){
        String att = "";
        for(String x : Mobile_HTML5G3.stream_G3_atts){
          att += "getA('"+x+"').\"||'_'||\".";
        }
        if(!att.isEmpty())	att = att.substring(0, att.length()-"||'_'||\".".length());

        php += 	"    //for stream foreach\n" +
        "    if(!empty($where))	$where = '('.$where.') and ';\n" +
        "    $where .= "+att+"='\".$_POST['att'].\"'\";\n" +
        "\n";
      }

      if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
        php +=	"    $stream_db"+streamCount+" = new SQLite3($sqlite3_DB);\n";
      } else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        php +=	"    $stream_db"+streamCount+" = pg_connect (\"host="+HOST+" port="+PORT+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n";
      }

      php +=
      "header('Content-Type: text/event-stream');\n" +
      "header('Cache-Control: no-cache');\n" +
      "while(1){\n";

      for(int i=0; i<streamAttributes.size(); i++){
        php +=	"	$sql_a"+(i+1)+" = array("+streamAttributes.get(i)+");\n";
      }
      php +=
      "	$sql_g = getG($groupby, $having, $orderby);\n" +
      "\n" +
      "	$sql1 = getSQL($sql_a1, $orderby_atts, $table, $where, $sql_g, $limit, null, null);\n";
      if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
        php +=
        "    $result1 = $stream_db"+streamCount+"->query($sql1);\n" +
        "\n" +
        "    //$i = 0;\n" +
        "    $j = 0;\n" +
        "    $pop_num = 0;\n" +
        "    $b = \"\";\n" +
        php_str1 +
        "\n"+
        Compiler_Dynamic.createNestWhile(streamAttributes_NestLevels)+
        "    for($i1=0; $i1<count($array1_1); $i1++){\n" +
        "          //$b .= str_replace('"+STREAM_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count

        php +=
        ((streamRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
        ((streamRowFlg)? "          }\n":"") +
        "    }\n" +
        php_str4 +
        "    unset($stream_db"+streamCount+");\n\n";
      } else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
        php +=
        "    $result1 = pg_query($stream_db"+streamCount+", $sql1);\n" +
        "\n" +
        "    //$i = 0;\n" +
        "    $j = 0;\n" +
        "    $pop_num = 0;\n" +
        "    $b = \"\";\n" +
        php_str1 +
        "\n"+
        Compiler_Dynamic.createNestWhile(streamAttributes_NestLevels)+
        "    for($i1=0; $i1<count($array1_1); $i1++){\n" +
        "          //$b .= str_replace('"+STREAM_FUNC_COUNT_LABEL+"', '_'.$i, $row[$j]);\n";	//For function's count

        /* nest string  start */
        //TODO d
        for(int i=0; i<streamWhileStrings.size(); i++){
          php +=	"          $b .= '"+streamWhileStrings.get(i)+"';\n";
        }
        for(int i=streamWhileCount; i>1; i--){		//TODO d 処理の位置
          php += " }\n";
        }
        /* nest string  end */

        php +=
        ((streamRowFlg)? "          if($i>=$start && $i<=$end){	//New\n":"") +
        ((streamRowFlg)? "          }\n":"") +
        "    }\n" +
        php_str4;


        if(Mobile_HTML5G3.stream_G3){
          php += "    if(pg_num_rows($result1)<1)	$b = \"No Data Found : \".$_POST['att'];	//for stream foreach\n";
        }
      }
      php +=
      ((streamRowFlg)? "}\n":"") +
      "    $ret['result'] = $b;\n";
      if(streamRowFlg){
        php +=
        "    $ret['start'] = $start;\n" +
        "    $ret['end'] = ($end<$i)? $end:$i;\n" +
        "    $ret['all'] = $i;\n" +
        "    $ret['info'] = (($ret['start']!=$ret['end'])? ($ret['start'].\" - \") : (\"\")) .$ret['end'].\" / \".$ret['all'];\n" +
        "    $ret['currentItems'] = ceil($i/$r);\n";
      }
      php +=
      "\n" +
      "    $ret_json = json_encode($ret);\n" +
      "    echo 'data:' . $ret_json, \"\\n\\n\";\n" +
      "    ob_flush();\n" +
      "    flush();\n" +
      "    sleep(" + Integer.parseInt(Asc_Desc.streamPeriod.get(0))/1000 + ");\n" +
      "}\n" +
      "\n" +
      "\n" +
      "    pg_close($stream_db"+streamCount+");\n\n" +
      "function getSQL($sql_a, $orderby_atts, $table, $where, $sql_g, $limit, $sql_a2, $row){\n"+
      "	$sql = getSF($sql_a, $orderby_atts, $table);\n" +
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
      "function getSF($sql_a, $orderby_atts, $table){\n" +
      "	return 'SELECT DISTINCT '.getAs($sql_a).$orderby_atts.' FROM '.$table;\n" +
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
      //"//XSS対策\n" +
      "function checkHTMLsc($str){\n" +
      "	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
      "}\n" +
      "?>\n";
      Asc_Desc.streamPeriod.remove(0);
      //End of php

      // 各引数毎に処理した結果をHTMLに書きこむ
      html_env.code.append(statement);

      if(!streamRowFlg){
        Mobile_HTML5.createFile(html_env, streamPHPfileName, php);//PHPファイルの作成
        streamCount++;
      }else{
        Mobile_HTML5.createFile(html_env, streamPHPfileName, php);//PHPファイルの作成
        streamPagingCount++;
        streamRowFlg = false;
      }
      // ajax_loadInterval = 0;

      initVariables();
      return true;
    }
    return false;
  }

  private static void initVariables() {
    streamDisplay = false;

    streamString = "";
    streamHTMLbuf = "";

    streamAttributes.clear();
    sindex.clear();

    streamAttributeFlg = false;
    streamWhileStrings.clear();
    streamWhileCount = 0;
    streamWhileCount0 = 0;
    streamAttributes_NestLevels.clear();
    streamAttributes_keys.clear();

    streamRow = 1;
    streamRowFlg = false;
    streamPHPfileName =  "";
    // ajax_loadInterval = 0;
  }

  private static String getOrderByString(int ASC_DESC_ARRAY_COUNT) {
    String s = "";
    try {
      Asc_Desc ad = new Asc_Desc();
      ad.asc_desc = ad.get_asc_desc_Array1(ASC_DESC_ARRAY_COUNT);
      ad.sorting();

      Iterator<AscDesc> it = ad.asc_desc.iterator();
      while (it.hasNext()) {
        AscDesc data = it.next();
        s += data.getAscDesc()+", ";
      }
      if(!s.isEmpty() && s.contains(","))	s = s.substring(0, s.lastIndexOf(","));
    } catch (Exception e) {
      Log.out("[Error] getOrderByString");
    }
    return s;
  }

  private static String getStreamPullHTML(String tfeID, int num, String phpFileName){
    final String DD_FUNC_NAME = "SSQL_StreamDisplay"+num;
    final String DD_COMMENT_NAME1 = "SSQL Stream"+num;
    final String DD_COMMENT_NAME2 = "SSQL Stream Display Data"+num;
    phpFileName = new File(phpFileName).getName();
    boolean isTable = ((Mobile_HTML5.isTable())? true : false);

    String s = "";
    s += ((isTable)? "<tbody>\n" : "");
    if(Mobile_HTML5G3.stream_G3)	s +=	LinkForeach.getJS("G3", DD_FUNC_NAME);
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
      s +=
      "<div id=\""+DD_FUNC_NAME+"\" data-role=\"none\"><!-- "+DD_COMMENT_NAME2+" --></div>\n";
    }
    s +=
    "<!-- "+DD_COMMENT_NAME1+" DIV end -->\n" +
    "\n" +
    "<!-- "+DD_COMMENT_NAME1+" JS start -->\n" +
    "<script type=\"text/javascript\">\n" +
    "<!--\n";
    if(!Mobile_HTML5G3.stream_G3){
      s += DD_FUNC_NAME+"();	//ロード時に実行\n" +
      "window.setInterval(" + DD_FUNC_NAME + "," + Asc_Desc.streamPeriod.get(0) + ");//xミリ秒で更新\n";
      Asc_Desc.streamPeriod.remove(0);
    }

    s +=	"function "+DD_FUNC_NAME+"_echo(str){\n" +
    "  document.getElementById(\""+DD_FUNC_NAME+"\").innerHTML = str;\n" +
    "}\n";

    if(!Mobile_HTML5G3.stream_G3)
    s +=	"function "+DD_FUNC_NAME+"(){\n";
    else
    s +=	"function "+DD_FUNC_NAME+"(value){\n";
    s +=
    "	//ajax: PHPへ値を渡して実行\n" +
    "	$.ajax({\n" +
    "		type: \"POST\",\n" +
    "		url: \""+phpFileName+"\",\n" +
    "		dataType: \"json\",\n";

    if(Mobile_HTML5G3.stream_G3){
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

  private static String getStreamPushHTML(String tfeID, int num, String phpFileName){
    final String DD_FUNC_NAME = "SSQL_StreamDisplay"+num;
    final String DD_COMMENT_NAME1 = "SSQL Stream"+num;
    final String DD_COMMENT_NAME2 = "SSQL Stream Display Data"+num;

    final String DD_EVENTSOURCE_NAME = "eventSource"+num;
    final String DD_NEWELEMENT_NAME = "newElement"+num;

    phpFileName = new File(phpFileName).getName();
    boolean isTable = ((Mobile_HTML5.isTable())? true : false);

    String s = "";
    s += ((isTable)? "<tbody>\n" : "");
    if(Mobile_HTML5G3.stream_G3)	s +=	LinkForeach.getJS("G3", DD_FUNC_NAME);
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
      s +=
      "<div id=\""+DD_FUNC_NAME+"\" data-role=\"none\"><!-- "+DD_COMMENT_NAME2+" --></div>\n";
    }
    s +=
    "<!-- "+DD_COMMENT_NAME1+" DIV end -->\n" +
    "\n" +
    "<!-- "+DD_COMMENT_NAME1+" JS start -->\n" +
    "<script type=\"text/javascript\">\n" +
    "<!--\n";

    s +=
    "var " + DD_EVENTSOURCE_NAME + " = new EventSource('" + phpFileName + "');\n" +
    "var " + DD_NEWELEMENT_NAME + " = document.createElement(\"div\");\n\n" +

    DD_EVENTSOURCE_NAME + ".addEventListener('message', function (event) {\n" +
    "var eventList = document.getElementById('" + DD_FUNC_NAME + "');\n" +
    "var obj = JSON.parse(event.data);\n" +
    DD_NEWELEMENT_NAME + ".innerHTML = obj.result;\n" +
    "eventList.appendChild(" + DD_NEWELEMENT_NAME + ");\n" +
    "});\n" +
    "//-->" +
    "</script>\n" +
    "<!-- "+DD_COMMENT_NAME1+" JS end -->\n" +
    "<!-- "+DD_COMMENT_NAME1+" end -->\n\n" +
    ((isTable)? "</tbody>\n" : "");
    return s;
  }

  private static String getStreamPagingHTML(String tfeID, int row, int num, String phpFileName){
    final String DDP_FUNC_NAME = "SSQL_StreamDisplay"+num;
    final String DDP_COMMENT_NAME1 = "SSQL StreamPaging"+num;
    final String DDP_COMMENT_NAME2 = "SSQL Stream Display Data"+num;
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
      s +=
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
    "  document.getElementById(\""+DDP_FUNC_NAME+"\").innerHTML = str;\n" +
    "}\n";

    // if(ajax_loadInterval>0){
    //   s += "\n" +
    //   "setInterval(function(){\n" +
    //   "	$('#"+DDP_FUNC_NAME+"_Buttons .next').trigger(\"click\");\n" +
    //   "},"+ajax_loadInterval+");\n\n";
    // }

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
}
