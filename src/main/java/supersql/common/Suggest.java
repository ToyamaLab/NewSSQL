package supersql.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//added by goto 20131016 start
public class Suggest {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /****************  Did you mean?  ****************/
	private final HashMap<String, Integer> nWords = new HashMap<String, Integer>();
	public static boolean checkAndSuggest(String tName, ArrayList<String> tNames) throws IOException {
		if(tName.length() > 0){
			String ans = (new Suggest(tNames)).correct(tName);
			if(!ans.equals(tName)){
				//Log.err("\nもしかして.. "+ans+" ?");
				Log.err("\nDid you mean... '"+ans+"' ?");
//				GlobalEnv.errorText += "\nDid you mean... '"+ans+"' ?";
				return true;
			}
		}
		return false;
	}
	public Suggest(ArrayList<String> t) throws IOException {
		Pattern p = Pattern.compile("\\w+");
		String temp = "";
		for(int i=0; i<t.size(); i++){
			temp = t.get(i);
			Matcher m = p.matcher(temp.toLowerCase());
			while(m.find()) nWords.put((temp = m.group()), nWords.containsKey(temp) ? nWords.get(temp) + 1 : 1);
		}
	}
	private final ArrayList<String> edits(String word) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));
		for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
		for(int i=0; i < word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
		for(int i=0; i <= word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
		return result;
	}
	public final String correct(String word) {
		if(nWords.containsKey(word)) return word;
		ArrayList<String> list = edits(word);
		HashMap<Integer, String> candidates = new HashMap<Integer, String>();
		for(String s : list) if(nWords.containsKey(s)) candidates.put(nWords.get(s),s);
		if(candidates.size() > 0) return candidates.get(Collections.max(candidates.keySet()));
		for(String s : list) for(String w : edits(s)) if(nWords.containsKey(w)) candidates.put(nWords.get(w),w);
		return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////

	//1024 yhac ////////////////////////////////////////////////////////////////////////////////////////////////

		public static String getAns(String tName, ArrayList<String> tNames) throws IOException {
			if(tName.length() > 0){
				String ans = (new Suggest(tNames)).correct(tName);
				if(!ans.equals(tName)){
					//Log.err("\nもしかして.. "+ans+" ?");
//					Log.err("\nDid you mean... '"+ans+"' ?");
					// 20140624_masato
//					GlobalEnv.errorText += "\nDid you mean... '"+ans+"' ?";
					return ans;
				}
			}
			return "？？";
		}
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    //get 'Error message', 'Error table name or column name', 'Error table alias' from exception message
    /* return: [0]=Error message, [1]=Error table name or column name, [2]=Error table alias */
    public static String[] getErrorContents(Exception e){
    	String error = e.toString().toLowerCase();
    	String error_tableName_or_columnName = "";
    	String error_tableAlias = "";

    	try{
    		String driver = GlobalEnv.getDriver().toLowerCase();
	    	if(driver.contains("postgresql")){
	    		if(error.contains("\"")){
	    			//ERROR: relation "sa" does not exist
	    			//ERROR: column reference "name" is ambiguous
	    			error_tableName_or_columnName = error.substring(error.indexOf("\"")+1, error.lastIndexOf("\"")).trim();
	    			if(error.contains("missing")){	//no such alias		//ERROR: missing FROM-clause entry for table "wpp"
	    				error_tableAlias = error_tableName_or_columnName;
	    				error_tableName_or_columnName = "";
	    			}
	    		}else{
	    			//ERROR: column w.namee does not exist
	    			error_tableName_or_columnName = error.substring(error.indexOf("column")+6, error.lastIndexOf("does")).trim();
	    		}
	    	}else if(driver.contains("mysql")){
	    		//TODO
	    	}else if(driver.contains("db2")){
	    		//TODO
	    	}else if(driver.contains("sqlite")){
	    		//ambiguous column name: name
	    		//no such column: wpp.name
	    		//no such column: w.namee
	    		//no such table: sa
		    	error_tableName_or_columnName = error.substring(error.lastIndexOf(":")+1).trim();
	    	}else if(driver.contains("sqlserver")){
	    		//TODO
	    	}
	    	

	    	if(error_tableName_or_columnName.contains(".")){
	    		error_tableAlias = error_tableName_or_columnName.substring(0,error_tableName_or_columnName.indexOf("."));
	    	  	error_tableName_or_columnName = error_tableName_or_columnName.substring(error_tableName_or_columnName.indexOf(".")+1);
	    	}
    	}catch(Exception e0){}

    	//Log.e("error_tableName_or_columnName: "+error_tableName_or_columnName);
    	//Log.e("error_tableAlias: "+error_tableAlias);
    	return new String[]{error, error_tableName_or_columnName, error_tableAlias};
    }

//    //get table names from query
//    /* return: (0)=Table name, (1)=Table alias, (2)=From phrase */
//    @SuppressWarnings({ "rawtypes", "serial" })
//	public static ArrayList<ArrayList> getTableNamesFromQuery(String query){
//  	  	String tableNames = "";
//  	  	final ArrayList<String> tableName = new ArrayList<String>();
//  	  	final ArrayList<String> tableAlias = new ArrayList<String>();
//  	  	final ArrayList<String> fromPhrase = new ArrayList<String>();
//  	  	try{
//	    	  String q = query.toLowerCase();
//	    	  q = q.substring(q.lastIndexOf("from")+4).replaceAll(";", "");
//	    	  tableNames = q;
//	    	  if(q.contains("where")){
//	    		  tableNames = q.substring(0, q.lastIndexOf("where"));
//	    	  }else if(q.contains("group")){
//	    		  tableNames = q.substring(0, q.lastIndexOf("group"));
//	    	  }else if(q.contains("order")){
//	    		  tableNames = q.substring(0, q.lastIndexOf("order"));
//	    	  }
////	  	  	}else if(q.contains("group by")){
////	  	  		tableNames = q.substring(0, q.lastIndexOf("group by"));
////	  	  	}else if(q.contains("order by")){
////	  	  		tableNames = q.substring(0, q.lastIndexOf("order by"));
////	  	  	}
//
//	    	  //if(!tableNames.equals("")) Log.e("\n## From phrase ##\n"+tableNames.trim());
//	    	  fromPhrase.add(0,tableNames);
//
//	    	  int i=0;
//	    	  tableNames += ",";
//	    	  while(tableNames.contains(",")){
//	    		  int index = tableNames.indexOf(",");
//	    		  tableName.add(i, tableNames.substring(0,index).trim());
//	    		  tableAlias.add(i, "");
//	    		  String tn = tableName.get(i);
//	    		  if(tn.contains(" ")){
//	    			  tableName.set(i, tn.substring(0,tn.indexOf(" ")).trim());
//	    			  tableAlias.set(i, tn.substring(tn.lastIndexOf(" ")).trim());
//	    		  }
//	    		  tableNames = tableNames.substring(index+1);
//	    		  //Log.e(tableName.get(i)+" "+tableAlias.get(i));
//	    		  i++;
//	    	  }
//  	  	}catch(Exception e){}
////  	  	ArrayList<ArrayList> array = new ArrayList<ArrayList>();
////  	  	array.add(tableName);
////  	  	array.add(tableAlias);
////  	  	return array;
//  	  	return new ArrayList<ArrayList>() {{add(tableName); add(tableAlias); add(fromPhrase);}};
////  	  	return new ArrayList<ArrayList>() {{new ArrayList<String>(tableName); add(tableAlias);}};
////	  		return new ArrayList<ArrayList>(2) {{add("hoge"); add("piyo"); add("foo"); add("bar");}};
//    }

    //get table and column name list
    //- no such column: エイリアスあり: それのみ表示、 エイリアス無し: From句に書かれているテーブルの一覧を表示
    public static String getTableAndColumnNameList(Connection conn, ArrayList<String> tableName, ArrayList<String> tableAlias, String errorColumnName, String errorTableNameAlias, ArrayList<String> fromPhrase){
  	  	String list = "";
  	  	String listBuf = "";
  	  	String tn = "";
  	  	String ta = "";
  	  	ResultSet rs = null;
  	  	boolean columnNameIsWrong = true;
  	  	String errorTn = "";
  	  	boolean aliasIsWrong = true;
  	  	String tableHas = "";
  	  	try{
				DatabaseMetaData dmd = conn.getMetaData();
//				for(int i=0;i<tableAlias.size();i++){
//					if(tableAlias.get(i).equals(errorTableNameAlias)){
//						aliasIsWrong = false;
//						break;
//					}
//				}

				int c = 0;
				ArrayList<String> columnNames = new ArrayList<String>();
				boolean suggested = false;

				for(int i=0;i<tableName.size();i++){
					tn = tableName.get(i);
					ta = tableAlias.get(i);
					if((!errorTableNameAlias.isEmpty() && (ta.equals(errorTableNameAlias) || tn.equals(errorTableNameAlias)))){
						errorTn = tn;
						listBuf = list;
						list = "";
					}
					list += tn+"(";
					rs = dmd.getColumns(null, null, tn, null);
					try {
						while(rs.next()){
							if(!errorTn.equals("") && tn.equals(errorTn) && rs.getString("COLUMN_NAME").equals(errorColumnName)){
								columnNameIsWrong = false;
							}
							list += rs.getString("COLUMN_NAME") + ", ";

							if((!errorTableNameAlias.isEmpty() && (ta.equals(errorTableNameAlias) || tn.equals(errorTableNameAlias)))){
								columnNames.add(c++, rs.getString("COLUMN_NAME"));
							}
						}
					} finally {
						rs.close();
					}
					list = list.substring(0, list.length()-2);
					list += ")\n";
					if((!errorTableNameAlias.isEmpty() && (ta.equals(errorTableNameAlias) || tn.equals(errorTableNameAlias)))){
						//System.err.print("\n## "+list.replace("(", " has ##\n("));
						tableHas = "\n## "+list.replace("(", " has ##\n(");

						//161110 yhac
						Ssedit.getSuggestlist(list);

						list = listBuf+list;
						aliasIsWrong = false;
					}
				}
				if(aliasIsWrong && !errorTableNameAlias.isEmpty()){
					if(!errorColumnName.isEmpty()){
						Log.err("\n## Wrong alias: >>>> "+errorTableNameAlias+" <<<< ."+errorColumnName+"  ##");
//						GlobalEnv.errorText += "\n## Wrong alias: >>>> "+errorTableNameAlias+" <<<< ."+errorColumnName+"  ##";

						//161110 yhac
						Ssedit.AutocorrectAlgorirhm_SQL(errorTableNameAlias, errorColumnName, tableAlias, null, "alias");
					}
					else {
						Log.err("\n## Wrong alias: >>>> "+errorTableNameAlias+" <<<<  ##");
//						GlobalEnv.errorText += "\n## Wrong alias: >>>> "+errorTableNameAlias+" <<<<  ##";
					}

			  	  	try{
			  	  		suggested = checkAndSuggest(errorTableNameAlias, tableAlias);
			  	  	}catch(Exception e){}
				}
				else if(columnNameIsWrong && !errorTableNameAlias.isEmpty()){
					Log.err("\n## Wrong column name: "+errorTableNameAlias+". >>>> "+errorColumnName+" <<<< ##");
//					GlobalEnv.errorText += "\n## Wrong column name: "+errorTableNameAlias+". >>>> "+errorColumnName+" <<<< ##";
			  	  	try{
			  	  		suggested = checkAndSuggest(errorColumnName, columnNames);

			  	  		//161110 yhac
						Ssedit.AutocorrectAlgorirhm_SQL(errorTableNameAlias, errorColumnName, tableAlias, columnNames, "column");


			  	  	}catch(Exception e){}
				}
				if(!tableHas.isEmpty() && columnNameIsWrong){
					Log.err(tableHas);
				}
				if(!fromPhrase.get(0).isEmpty() && aliasIsWrong && !errorTableNameAlias.isEmpty()){
					Log.err("\n## From phrase is ##\n"+fromPhrase.get(0).trim());
//					GlobalEnv.errorText += "\n## From phrase is ##\n"+fromPhrase.get(0).trim();
				}

				if(suggested){
					list = "";
					Log.err("");
				}
  	  	}catch(Exception e){}
  	  	return list;
    }

    //get ambiguous table and column name list
    //- ambiguous column name: そのカラム名を持っているtable&column一覧を表示
    public static String getgetAmbiguousTableAndColumnNameList(Connection conn, ArrayList<String> tableName, String ambiguousColumnName){
    	String list = "";
    	String listBuf = "";
    	String tn = "";
    	String cn = "";
    	ResultSet rs = null;
    	try{
    		DatabaseMetaData dmd = conn.getMetaData();
    		for(int i=0;i<tableName.size();i++){
    			tn = tableName.get(i);
    			listBuf = tn+"(";
    			rs = dmd.getColumns(null, null, tn, null);
    			try {
    				while(rs.next()){
    					cn = rs.getString("COLUMN_NAME");
    					if(!cn.equals(ambiguousColumnName))	listBuf += cn + ", ";
    					else								listBuf += ">>>> "+cn + " <<<< , ";
    				}
    			} finally {
    				rs.close();
    			}
    			if(listBuf.contains(" <<<< , ")){
    				list += listBuf;
    				listBuf = "";
	    			list = list.substring(0, list.length()-2);
	    			list += ")\n";
    			}
    		}
    	}catch(Exception e){}
    	return list;
    }

    //get table name list
    //- no such table: 近い名前から表示
    public static String getTableNameList(Connection conn, String tName){
  	  	String list = "";
  	  	String tn = "";
  	  	ArrayList<String> tNames = new ArrayList<String>();
  	  	try{
				DatabaseMetaData dmd = conn.getMetaData();
				String types[] = { "TABLE" };
				ResultSet rs = dmd.getTables(null, null,"%", types);
				try {
					int i = 0;
					while(rs.next()){
						tn = rs.getString("TABLE_NAME").toLowerCase();
						tNames.add(i++, tn);
					}
				} finally {
					rs.close();
				}
  	  	}catch(Exception e){}
  	  	//if(!list.equals(""))  list = list.substring(0, list.length()-2);

  	  	boolean suggested = false;
  	  	try{
  	  		suggested = checkAndSuggest(tName, tNames);
  	  	}catch(Exception e){}

  	  	if(suggested)	list = LevenshteinDistance.checkLevenshteinDistance(tName, tNames);	//提案あり: 類似度判定
  	  	else	  		list = ascendingSort(tNames);					//提案なし: 昇順ソート

  	  	//161201 yhac
  	  	Ssedit.getfromSuggestlist(list);
  	  	try {
			Ssedit.AutocorrectAlgorirhm_SQL(tName, null, tNames, null, "from");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

  	  	return list;
    }
    //return Ascending sorted String
	private static String ascendingSort(ArrayList<String> al) {
		//アルファベット順にソート
		String s = "";
		Collections.sort(al);
		for(String val : al){
			s += val + ", ";
		}
		if(!s.equals(""))  s = s.substring(0, s.length()-2);
		return s;
	}

}
//added by goto 20131016 end