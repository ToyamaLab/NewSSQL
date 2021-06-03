package supersql.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.*;

import supersql.codegenerator.AttributeItem;
import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.TFE;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.VR.VRcjoinarray;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.common.Ssedit;
import supersql.db.GetFromDB;
import supersql.extendclass.ExtList;
import supersql.parser.org.antlr.v4.runtime.MyErrorStrategy;


public class Start_Parse {

	private static boolean jsonQuery = false;
	private static boolean dbpediaQuery = false;
	private boolean foreachFlag = false;
	private ExtList foreachinfo;
	public static String[] parameters;
	public static ExtList parameter_atts = new ExtList();
	public static boolean sessionFlag = false;
	public static String sessionString = "";
	private CodeGenerator codegenerator;
	public static ArrayList<String> textString = new ArrayList<String>();
	private static boolean prefix = false;
	private FromInfo fromInfo;
	private String groupStatement;
	private String havingStatement;
	private String foreachFrom = "";
	private String foreachWhere = "";
	private StringBuffer from_c = new StringBuffer();
	private StringBuffer where_c = new StringBuffer();
	private StringBuffer order_c = new StringBuffer();
	private StringBuffer group_c = new StringBuffer();
	private StringBuffer having_c = new StringBuffer();
	private StringBuffer embedFrom = new StringBuffer();
	private StringBuffer embedGroup = new StringBuffer();
	private StringBuffer embedHaving = new StringBuffer();
	private StringBuffer embedWhere = new StringBuffer();
	private static String fromInfoString;
	private String QueryImage;


	public static String att = null;
	public static String media = null;
	public ExtList List_tree_a, List_tree_b, list_tfe, list_from_where, list_from, list_where, list_media, list_table;
	public static Hashtable<?, ?> atts;
	public static TFE schemaTop;
	public static ExtList sch;
	public static ExtList schema;
	public static ExtList keys;
	public WhereInfo whereInfo = new WhereInfo();
	public static boolean distinct = false;
	public static String[] ruleNames;
	public static boolean foreach1Flag = false;	//added by goto 20161025 for link1/foreach1
	public static HashMap<String, String> alias_name;



	public Start_Parse() {
		parseSSQL(this.getSSQLQuery(), 10000);
	}

	public Start_Parse(int id) {
		parseSSQL(this.getSSQLQuery(), id);
	}

	public Start_Parse(String a) {
		parseSSQL(this.getSSQLQuery2(), 10000);
	}

	public Start_Parse(StringBuffer querybuffer) {
		parseSSQL(querybuffer.toString(), 10000);
	}
	public static void set_from_info_st(String fi) {
		fromInfoString = fi;
	}
	public static String get_from_info_st() {
		if (fromInfoString == null) {
			return "";
		}
		return fromInfoString;
	}
	public static void setDbpediaQuery(boolean dbpediaQuery) {
		Start_Parse.dbpediaQuery = dbpediaQuery;
	}
	public static void setJsonQuery(boolean jsonQuery) {
		Start_Parse.jsonQuery = jsonQuery;
	}
	public FromInfo get_from_info() {
		return fromInfo;
	}
	public CodeGenerator getcodegenerator(){
		codegenerator.TFEid = 10000;
		return codegenerator;

	}

	public static boolean isDbpediaQuery() {
		return dbpediaQuery;
	}

	public static boolean isJsonQuery() {
		return jsonQuery;
	}

	public TFE get_TFEschema(){
		TFE sch = codegenerator.schemaTop;
		return sch;
	}

	public Hashtable get_att_info(){
		Hashtable attp = codegenerator.get_attp();

		return attp;
	}

	public String getTFEsig(ExtList sep_sch) {

		Hashtable atts = this.get_att_info();
		FromInfo from = this.get_from_info();
		WhereInfo where = this.whereInfo;

		int i, idx;
		Object o;
		Integer itemno;
		StringBuffer buf = new StringBuffer();
		for (idx = 0; idx < sep_sch.size(); idx++) {
			o = sep_sch.get(idx);
			if (o instanceof Integer) {

				itemno = (Integer) (sep_sch.get(idx));
				AttributeItem att1 = (AttributeItem) (atts.get(itemno));
				buf.append(att1.getAttributeSig(from) + "@@");
			} else if (o instanceof ExtList) {
				buf.append("(");
				buf.append(getTFEsig((ExtList) o));
				buf.append(")");
			}
		}

		// Where
		buf.append(where.getWhereSig(from));

		return buf.toString();

	}
	public String getSSQLsig() {
		StringBuffer sig = new StringBuffer();
		//		sig.append(QueryImage.replaceAll("\\s",""));
		sig.append(QueryImage);
		String addCondition = GlobalEnv.getCondition();
		if (addCondition != null) {
			sig.append("@@");
			sig.append(addCondition);
		}
		//Log.out("[SSQL sig] = " +sig);
		return sig.toString();
	}

	public Object[] getSQLsig(ExtList sep_sch) {

		Hashtable atts = this.get_att_info();
		FromInfo from = this.get_from_info();
		WhereInfo where = this.whereInfo;

		int idx;
		Integer itemno;
		ExtList schf = sep_sch.unnest();
		StringBuffer buf = new StringBuffer();

		Hashtable sig2idx = new Hashtable();
		TreeSet sorter = new TreeSet();
		String attsig;
		ExtList ordersig = new ExtList();

		for (idx = 0; idx < schf.size(); idx++) {
			itemno = (Integer) (schf.get(idx));
			attsig = ((AttributeItem) (atts.get(itemno))).getAttributeSig(from);
			sig2idx.put(attsig, new Integer(idx));
			sorter.add(attsig);
		}

		Iterator ite = sorter.iterator();
		while (ite.hasNext()) {
			attsig = (String) ite.next();
			buf.append(attsig + "@@");
			ordersig.add(sig2idx.get(attsig).toString());
		}

		Log.out("sig:" + buf);
		Log.out("ordersig:" + ordersig);

		buf.append(where.getWhereSig(from));
		Object[] ret = { buf.toString(), ordersig };

		return ret;
	}

	private String getSSQLQuery()
	{
		//read file & query
		//		String query = null;

		String query = GlobalEnv.getQuery();
		if(GlobalEnv.getQuery() != null && GlobalEnv.getoutfilename() != null){
			query = GlobalEnv.getQuery();
			//			query = replaceQuery_For_HTML_and_MobileHTML5(query);
			return query;
		}

		if (query != null) {
			query = query.trim();
		}
		String filename = GlobalEnv.getfilename();
		if (filename == null || filename.isEmpty()) {
			Log.err("Error[SQLparser]: File Is Not Specified.");
			GlobalEnv.addErr("Error[SQLparser]: File Is Not Specified.");
			return null;
		}

		Log.info("[Parser:Parser] filename = " + filename);
		GlobalEnv.queryName = "[Parser:Parser] filename = " + filename;
		BufferedReader in;
		StringBuffer tmp = new StringBuffer();
		try{
			//			in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));		//changed by goto 20130519 (This is an important change.)
			//			String line = null;
			//			line = in.readLine();
			//			if(line.startsWith("/*")){
			//				while (line.contains("/*")){
			//					String line1 = line.substring(0, line.indexOf("/*"));
			//					while (!line.contains("*/"))
			//						line = in.readLine();
			//					line = line1 + line.substring(line.indexOf("*/") + 2);
			//				}
			//				tmp.append(" " + line);
			//			}
			//
			//			if(line.startsWith("--")){
			//				while (line.contains("--")){
			//					String line1 = line.substring(0, line.indexOf("--"));
			//					line = in.readLine();
			//				}
			//				tmp.append(" " + line);
			//			}
			//			tmp.append(" " + line);
			//			int c;
			//			while ((c = in.read()) != -1) {
			//				tmp.append((char) c);
			//			}
			//			query = tmp.toString().trim();
			//			Log.info(query);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));		//changed by goto 20130519 (This is an important change.)
			int c;
			while ((c = in.read()) != -1) {
				tmp.append((char) c);
			}
			query = tmp.toString();

			GlobalEnv.queryLog += query;

		} catch (FileNotFoundException e) {
			Log.err("Error[SQLparser]: File(" + filename	+ ") Is Not Found.");
			GlobalEnv.addErr("Error[SQLparser]: File(" + filename + ") Is Not Found." + e);
		} catch (IOException e) {
			GlobalEnv.addErr("Error[SQLparser]:" + e);
		}

		//161109 yhac
		if (GlobalEnv.isSsedit_autocorrect()) {
			return Ssedit.getMedia_and_From(query);
		}else{
			return query;
		}

		//parse query

	}

	private String getSSQLQuery2() {

		String query = GlobalEnv.getQuery();
		if (query != null) {
			query = query.trim();
		}

		String filename = GlobalEnv.getfilename();
		if (filename != null) {
			Log.info("[Parser:Parser] filename = " + filename);
			StringBuffer tmp = new StringBuffer();
			String line = new String();
			BufferedReader dis;
			try {

				if (filename.startsWith("http:")) {
					URL fileurl = new URL(filename);

					URLConnection fileurlConnection = fileurl.openConnection();
					/*
					 * DataInputStream dis = new
					 * DataInputStream(fileurlConnection.getInputStream());
					 */
					dis = new BufferedReader(new InputStreamReader(
							fileurlConnection.getInputStream(), "EUC-JP"));
				}

				else {
					dis = new BufferedReader(new FileReader(filename));
					line = null;
				}
				while (true) {
					line = dis.readLine();

					if (line == null || line.equals("-1"))
						break;

					while (line != null && line.contains("/*")) {
						int s = line.indexOf("/*");
						String line1 = line.substring(0, s);
						// tmp.append(" "+line1);
						while (!line.contains("*/"))
							line = dis.readLine();
						int t = line.indexOf("*/");
						line = line1 + line.substring(t + 2);
					}
					// added by goto 20130412
					//					if (line != null && line.contains(commentOutLetters)) {	//commentOutLetters = "--"
					//						boolean dqFlg = false;
					//						int i = 0;
					//
					//						for (i = 0; i < line.length(); i++) {
					//							if (line.charAt(i) == '"' && !dqFlg)
					//								dqFlg = true;
					//							else if (line.charAt(i) == '"' && dqFlg)
					//								dqFlg = false;
					//
					//							if (!dqFlg
					//									&& i < line.length() - 1
					//									&& (line.charAt(i) == GlobalEnv.COMMENT_OUT_LETTER && line
					//									.charAt(i + 1) == GlobalEnv.COMMENT_OUT_LETTER))
					//								break;
					//						}
					//						line = line.substring(0, i);
					//					}

					if (line != null)
						tmp.append(" " + line);
				}
				dis.close();

			} catch (MalformedURLException me) {
				System.out.println("MalformedURLException: " + me);
			} catch (IOException ioe) {
				System.out.println("IOException: " + ioe);
				GlobalEnv.addErr("Error[SQLparser]:" + ioe);
			}

			query = tmp.toString().trim();
		}

		if (query.endsWith(";")) {
			query = query.substring(0, query.length() - 1).trim();
		}

		Log.info("[Parser:Parser] ssql statement = " + query);
		GlobalEnv.queryLog += "[Parser:Parser] ssql statement = " + query;
		return query;
	}


	private ExtList set_fromInfo(){
		ExtList from_tables = new ExtList();
		//    	ExtList from_table = new ExtList();
		for(int i = 0; i < list_from.size(); i++){
			if(list_from.get(i) instanceof ExtList){
				from_tables.add(((ExtList)list_from.get(i)).get(1));
			}else{
				continue;
			}
		}

		return from_tables;
	}

	private void processKeywords(String where){
		StringBuffer buffer = new StringBuffer();
		buffer = from_c;
		StringTokenizer st = new StringTokenizer(where);
		while (st.hasMoreTokens()) {
			String nt = st.nextToken().toString();
			if (nt.equalsIgnoreCase("WHERE")) {
				buffer = where_c;
			}
			else if (nt.equalsIgnoreCase("ORDER")) {
				buffer = order_c;
			}
			else if (nt.equalsIgnoreCase("GROUP")) {
				buffer = group_c;
			}
			else if (nt.equalsIgnoreCase("HAVING")) {
				buffer = having_c;
			}
			else {
				buffer.append(nt + " ");
			}
			Mobile_HTML5Function.after_from_string += nt+" ";	//added by goto 20130515  "search"
		}
		Log.out("from_c:"+from_c);
	}

	private void processKeywords(ExtList list_from){
		boolean from = false;
		for(int i = 0; i < list_from.size(); i++){
			if(list_from.get(i) instanceof String){
				if(!list_from.get(i).toString().equalsIgnoreCase("FROM")){
					from_c.append(list_from.get(i).toString());
				}
			}
			else if(list_from.get(i) instanceof ExtList){
				if(((ExtList)list_from.get(i)).get(0).equals("where_clause")){
					where_c.append( getText((ExtList)list_from.get(i), ruleNames) );
					builder = new String();
					if(where_c.toString().toLowerCase().startsWith("where")){
						where_c.delete(0, 6);
					}
				}else{
					from_c.append( getText((ExtList)list_from.get(i), ruleNames) );
					builder = new String();
				}
			}
		}
		if (list_from.size() > 2){
			if(list_from.get(2) instanceof ExtList){
				if (list_from.getExtListString(2, 0).equals("where_clause")){
					ExtList tmp = new ExtList();
					tmp.add(list_from.getExtList(1));
					From newFrom = new From(tmp);
				}
			}else{
				ExtList tmp = new ExtList();
				for (int i = 1; i < list_from.size(); i = i + 2) {
					if(list_from.getExtListString(i, 0).equals("table_or_subquery")){
						tmp.add(list_from.getExtList(i));
					}
				}
				From newFrom = new From(tmp);
			}
		}else if(list_from.size() == 2){
			ExtList tmp = new ExtList();
			tmp.add(list_from.getExtList(1));
			From newFrom = new From(tmp);
		}
		Mobile_HTML5Function.after_from_string = getText(list_from, ruleNames);
		if(Mobile_HTML5Function.after_from_string.toLowerCase().startsWith("from")){
			Mobile_HTML5Function.after_from_string = Mobile_HTML5Function.after_from_string.substring(4);
		}
	}
	private void postProcess() {
		// FOREACH
		if (!(foreachFrom.equals(""))) {
			from_c.append("," + foreachFrom);
		}

		groupStatement = group_c.toString();
		Log.out("[Paeser:Group] group = " + groupStatement);
		group_c.append(embedGroup + " ");

		havingStatement = having_c.toString();
		Log.out("[Paeser:Having] having = " + havingStatement);
		having_c.append(embedGroup + " ");

		fromInfo = new FromInfo(from_c.toString().trim());
		//from句に並んでるテーブルの属性名を取得
		GetFromDB gfd = new GetFromDB();
		GlobalEnv.tableAtts = new HashMap<>();
		for(FromTable fromTable: From.getFromItems()) {
			String tableName = fromTable.getTableName();
			ExtList result = new ExtList();
			gfd.getTableAtt(tableName, result);
			GlobalEnv.tableAtts.put(tableName, result.unnest());
		}
		gfd.close();
		Log.out("[Parser:From] from = " + fromInfo);
		if (!(foreachFrom.equals(""))) {
			Log.out(foreachFrom
					+ ": Used in FOREACH clause and added to FROM clause ");
		}

		if(parameters != null){
			String where_tmp = "", p = "";
			for(int i = 0; i < parameters.length; i++){
				if(i != 0){
					where_tmp += " AND "; 
				}
				// where_tmp += parameter_atts.get(i) + " = " + parameters[i];
				p = parameters[i];
				where_tmp += parameter_atts.get(i) + " = ";
				if(GlobalEnv.isNumber(p)) {
					where_tmp +=  p;
				} else {
					where_tmp +=  "'" + p + "'";
				}
			}

			// where句の中身をチェック
			if(where_c.toString().equals("")){
				where_c.append(where_tmp);
			} else {
				where_tmp += " AND ";
				where_c.insert(0, where_tmp);
			}
		}

		if (Start_Parse.isDbpediaQuery())
			whereInfo.setSparqlWhereQuery(where_c.toString().trim());
		else
			whereInfo.appendWhere(where_c.toString().trim());

		if (embedWhere.length() != 0)
			whereInfo.appendWhere(embedWhere + " ");

		Log.out("WHERE:" + whereInfo);
		// FOREACH
		if (!(foreachWhere.equals(""))) {
			whereInfo.appendWhere(foreachWhere);
			Log.out(foreachWhere
					+ ": Used in FOREACH clause and added to WHERE clause ");
		}

		String addCondition = GlobalEnv.getCondition();
		if (addCondition != null) {
			whereInfo.appendWhere(addCondition);
		}
		Log.out("[Paeser:Where] where = " + whereInfo);
	}

	private void parseSSQL(String query, int id){
		if(query==null) return;
		
		query = query.trim();
		String after_from = "";

		if(!query.toLowerCase().contains("generate")){
			GlobalEnv.addErr("didn't find 'GENERATE'. please start with 'GENERATE'.");
			Log.err("didn't find 'GENERATE'. please start with 'GENERATE'.");
		}
		//		else if(!query.toLowerCase().contains("from")) {
		//			GlobalEnv.addErr("didn't find 'FROM'. please describe 'FROM'.");
		//			Log.err("didn't find 'FROM'. please describe 'FROM'.");
		else{
			try{
				String a = query.substring(0, query.toLowerCase().indexOf("generate"));
				String b = query.substring(query.toLowerCase().indexOf("generate"));
//				Log.info(a);
				Log.info(b);
				VRcjoinarray.query = b;

				if(a.equals(" ") || a.equals("") || a.equals("\r")){
				}else{

					ANTLRInputStream input_a = new ANTLRInputStream(a);
					prefixLexer lexer_a = new prefixLexer(input_a);
					CommonTokenStream tokens_a = new CommonTokenStream(lexer_a);

					prefixParser parser_a = new prefixParser(tokens_a);
					ParseTree tree_a = parser_a.prefix(); // begin parsing at rule query
					List_tree_a = TreeConst.createSSQLParseTree(tree_a, parser_a);
					String[] ruleNamesa = parser_a.getRuleNames();
					Log.info(List_tree_a);
					int list_size = ((ExtList)List_tree_a.get(1)).size();
					for(int i = 0; i < list_size; i+=2){
						ExtList list = (ExtList)((ExtList)((ExtList)((ExtList)List_tree_a.get(1)).get(i)).get(1)).get(0);
						String pre = "";
						if( ((ExtList)list.get(1)).get(0) instanceof String){
							pre = ((ExtList)list.get(1)).get(0).toString().toLowerCase();
							if(pre.equals("foreach") || pre.equals("foreach1")){
								foreachFlag = true;
								if(pre.equals("foreach1"))	foreach1Flag = true;	//added by goto 20161025 for link1/foreach1
								foreachinfo = TreeConst.getforeach(list);
							}else if(pre.equals("parameter")){
								// -eHTMLarg{...,...,...,...}の...,...,...,...部分
								String values =  GlobalEnv.getLinkValue().substring(1, GlobalEnv.getLinkValue().length()-1);
								parameters = values.split(",");
								parameter_atts = TreeConst.getforeach(list);
							}
						}
						else if( ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)list.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString().toUpperCase().matches("SESSION.*")
								|| ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)list.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString().toUpperCase().matches("LOGIN.*")){
							sessionString = getText(list, ruleNamesa);
							builder = "";
							sessionFlag = true;
						}
					}
					prefix = true;
				}
				if(prefix && foreachFlag){
					StringTokenizer str = new StringTokenizer(b);
					String generate = null;

					ANTLRInputStream input_b = new ANTLRInputStream(b);
					queryLexer lexer_b = new queryLexer(input_b);
					CommonTokenStream tokens_b = new CommonTokenStream(lexer_b);

					queryParser parser_b = new queryParser(tokens_b);
					parser_b.setErrorHandler(new MyErrorStrategy());
					ParseTree tree_b = parser_b.query(); // begin parsing at rule query
					ExtList List_tree_b = TreeConst.createSSQLParseTree(tree_b, parser_b);
					List_tree_b = (ExtList) List_tree_b.get(1);
					String[] ruleNames = parser_b.getRuleNames();

					generate = getText((ExtList)List_tree_b.get(0), ruleNames);
					builder = "";
					String tfe = getText((ExtList)List_tree_b.get(1), ruleNames);
					builder = "";
					String from = "";
					if(List_tree_b.size() > 2){
						from = getText((ExtList)List_tree_b.get(2), ruleNames);
						builder = "";
					}

					generate = generate + "[foreach(";
					for(int i = 0; i < foreachinfo.size(); i++){
						if(i == 0)
							generate = generate + foreachinfo.get(i);
						else if(i != 0)
							generate = generate + "," + foreachinfo.get(i);
					}
					generate = generate + ")?";


					b = generate + tfe + "]%" + from;
				}
				GlobalEnv.foreach_flag = foreachFlag;
				Preprocessor preprocessor = new Preprocessor(b);

				ANTLRInputStream input_b = new ANTLRInputStream(b);
				queryLexer lexer_b = new queryLexer(input_b);
				CommonTokenStream tokens_b = new CommonTokenStream(lexer_b);

				queryParser parser_b = new queryParser(tokens_b);
				parser_b.setErrorHandler(new MyErrorStrategy());
				ParseTree tree_b = parser_b.query(); // begin parsing at rule query
				List_tree_b = TreeConst.createSSQLParseTree(tree_b, parser_b);
				List_tree_b = (ExtList) List_tree_b.get(1);
				list_media = (ExtList) List_tree_b.get(0);
				list_tfe = (ExtList) List_tree_b.get(1);
				ruleNames = parser_b.getRuleNames();
				//				Log.info(getText(list_tfe, ruleNames));
				if(List_tree_b.size() > 2){
					list_from_where = (ExtList) List_tree_b.get(2);
					//					Log.info(list_from_where);
					after_from = getText(list_from_where, ruleNames);
					builder = "";
					list_from = new ExtList();
					list_where = new ExtList();

					while(true){
						if(((ExtList)((ExtList)list_from_where.get(1)).get(0)).get(0).toString().equals("select_core")){
							list_from_where = (ExtList) ((ExtList)((ExtList)list_from_where.get(1)).get(0)).get(1);
							if(((ExtList)list_from_where.get(list_from_where.size() - 1)).get(0).toString().equals("where")){
								list_where = (ExtList) ((ExtList)list_from_where.get(list_from_where.size() - 1)).get(1);
								for(int i = 0; i < list_from_where.size() - 1; i++){
									list_from.add(list_from_where.get(i));
								}
							}else{
								for(int i = 0; i < list_from_where.size(); i++){
									list_from.add(list_from_where.get(i));
								}
							}
							break;
						}else{
							list_from_where = (ExtList)(ExtList)((ExtList)list_from_where.get(1)).get(0);
						}
					}
					list_table = set_fromInfo();
//					alias_name = new HashMap<String, String>();//key:alias, value:table name
					//tabata fixed 180521
					//下のfor文内の条件を書き換えました。list_table.size()->list_table.size()-1
//					for(int i = 0; i < list_table.size() - 1; i++){
////						System.out.println("list_table: "+list_table);
////						System.out.println("ruleNames: "+ruleNames.toString());
//						String alias = getText((ExtList)((ExtList)list_table.get(i)).get(1), ruleNames).trim();
//						builder = "";
//						String name = getText((ExtList)((ExtList)list_table.get(i)).get(0), ruleNames).trim();
//						builder = "";
//						alias_name.put(alias, name);
//					}
//					Log.info(alias_name);
					
					//					Log.info(list_from_where);
					//					String from1 = getText( list_from_where, ruleNames );
					//					after_from = from1.substring(from1.toLowerCase().indexOf("from") + 4);
					//					String from1 = getText( list_from, ruleNames );
					//					builder = "";
					//					String from2 = getText( list_where, ruleNames );

					//					after_from = from1 + "where " + from2;
					after_from = after_from.substring(after_from.toLowerCase().indexOf("from") + 4);
					String from = new String();
					while(after_from.contains("/*")){
						from = after_from.substring(0, after_from.indexOf("/*"));
						from += after_from.substring(after_from.indexOf("*/") +2);
						after_from = from;
					}
//					Log.out(after_from);
//					Log.info(list_from);
					processKeywords(list_from);

				}
				//				Log.info(List_tree_b);
				//				Log.info(list_media);
//				Log.info(list_tfe);
//				Log.info(list_from);
				postProcess();

				codegenerator = new CodeGenerator();
				Log.out("Generated cg " + codegenerator);

			}catch(Exception e){
					e.printStackTrace();
			}
		}
	}
	public static String builder = new String();
	public static String getText(ExtList tree, String[] ruleNames){
		if(tree.size() != 1){
			for(int i = 0; i < tree.size(); i++){
				if(tree.get(i) instanceof String){
					if(Arrays.asList(ruleNames).contains(tree.get(i).toString())){
						continue;
					}else{
						if( tree.get(i).toString().equals(".") ){
							builder = builder.trim();
							builder += tree.get(i).toString();
						}else{
							builder += tree.get(i).toString();
							builder += " ";
						}
					}
				}else {
					getText((ExtList)tree.get(i), ruleNames);
				}
			}
		}
		else if(tree.size() == 1 && (tree.get(0) instanceof String)){
			builder += tree.get(0).toString();
			builder += " " ;
			return builder.toString();
		}
		else if(tree.size() == 1 && ((ExtList)tree.get(0)).size() > 1 ){
			return getText((ExtList)tree.get(0), ruleNames);
		}
		else if(tree.size() == 1 && ((ExtList)tree.get(0)).size() == 1 ){
			return getText((ExtList)tree.get(0), ruleNames);
		}
		return builder.toString();
	}
}
