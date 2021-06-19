package supersql.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import supersql.FrontEnd;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5;
import supersql.common.DB;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.common.LogError;
import supersql.common.Ssedit;
import supersql.common.Suggest;
import supersql.extendclass.ExtList;
import supersql.parser.FromParse;

public class SQLManager {

	private Connection conn;
	private ExtList<String> header_name;
	private ExtList<String> header_type;
	private ExtList<ExtList<String>> tuples;

	private ConnectDB cdb;
	private boolean isMulti = false;

    public SQLManager(ConnectDB in_cdb)
    {
    	cdb = in_cdb;
    	isMulti = true;
    }

    public SQLManager(String url, String user, String driver, String password) {
        Log.out("[SQLManager Open]");
        try {
        	if (GlobalEnv.getframeworklist() == null) {
	            Class.forName(driver);
	            Log.out("********** Database's URL is **********");
	            Log.out(url);
	            conn = DriverManager.getConnection(url, user, password);
        	}

        } catch (SQLException e) {
            Log.err("Error[SQLManager]: Can't Connect DB : jdbc path = "
                            + url + " , user = " + user);
//            GlobalEnv.errorText += "Error[SQLManager]: Can't Connect DB : jdbc path = "
//                    + url + " , user = " + user;
            Log.err(e);
//            GlobalEnv.errorText += e;
            GlobalEnv.addErr("Error[SQLManager]: Can't Connect DB : jdbc path = "
                    + url + " , user = " + user);
            return ;
        } catch (ClassNotFoundException e) {
            System.err
                    .println("Error[SQLManager]: Can't Load JDBC driver : driver = "
                            + driver);
            GlobalEnv.addErr("Error[SQLManager]: Can't Load JDBC driver : driver = "
                            + driver);
            return ;
        }
    }

    public void ExecSQL(String query) {
		ExecSQL(query, null, null);
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	boolean firstSQLStatement = false;
    int SQLstatementCount = 0;
	public void ExecSQL(String query, String create, String update) {
    	if(isMulti)
    	{
    		Log.out("thred name:"+cdb.getName());
    		Log.out("isAlive?"+cdb.isAlive());
    		try{
    			cdb.join();
	    	}catch(InterruptedException e)
	    	{}

	    	conn = cdb.getConn();
    	}
       	//exception
//    	boolean isSession = false;
    	if(query.toLowerCase().contains("$session (\"") && query.toLowerCase().contains("\" )")){
    		if(query.contains(")OR"))	query = query.replace(")OR", ") OR");
    		if(query.contains(")or"))	query = query.replace(")or", ") OR");

    		String[] b = query.split(" ");
    		boolean sq = false, s = false, w = false;
    		for(int i=0; i<b.length; i++){
    			//シングルクォート内かどうか
    			if(b[i].contains("'")){
    				for(int j=0; j<b[i].length(); j++){
    					if(b[i].charAt(j)=='\'')	sq = !sq;
    				}
    			}

    			if(!sq && s){
    				if(b[i].contains(")")){
    					s = false;
    					if(w && (i+1)<b.length){
    						if(b[i+1].equals("AND") || b[i+1].equals("OR"))
    							b[i+1] = "WHERE";
//    			    		isSession = true;
    						w = false;
    					}
    				}
    				b[i] = "";
    			}
    			else if(!sq && b[i].contains("$session")){
    				for(int j=i; j>0; j--){
    					if(b[j].equals("WHERE") || b[j].equals("AND") || b[j].equals("OR")){
    						if(b[j].equals("WHERE")) w = true;
    						b[j] = "";
    						break;
    					}
    					b[j] = "";
    				}
    				s = true;
    			}
    		}
    		query = "";
    		for(int i=0; i<b.length; i++)
    			if(!b[i].equals(""))	query += b[i]+" ";
    		query = query.trim();
    		if(!query.endsWith(";"))	query += ";";
    	}
    	if(!Ehtml.flag && query.contains(" #"))	query = query.substring(0,query.indexOf(" #"));	//TODO
    	query = Mobile_HTML5.checkQuery(query);
        Log.out("[SQLManager ExecQuery]");
        if(!query.endsWith("FROM ;")){
        	if (!firstSQLStatement) {
    	        //Log.info("\n********** SQL is **********");
    	        Log.info("\n********** SQL **********");
        		firstSQLStatement = true;
			}
	        Log.info((++SQLstatementCount)+". "+query);
        }
        GlobalEnv.query = query;

        header_name = new ExtList<String>();
        header_type = new ExtList<String>();
        tuples = new ExtList<ExtList<String>>();

        try {
            Statement stat = conn.createStatement();
            if(create != null && update != null){
            	String[] creates = create.split("\n");
            	for (int j = 0; j < creates.length; j++) {
					stat.addBatch(creates[j]);
				}
            	String[] updates = update.split("\n");
            	for(int k = 0; k < updates.length; k++){
            		stat.addBatch(updates[k]);
            	}
            	stat.executeBatch();
            }
            ResultSet rs = stat.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                header_name.add(rsmd.getColumnName(i));
                header_type.add(Integer.toString(rsmd.getColumnType(i)));
            }

            ExtList<String> tmplist;
            String val;
            StringBuffer tmp = new StringBuffer();
            while (rs.next()) {
                tmplist = new ExtList();
                for (int i = 1; i <= columnCount; i++) {
                	if(rs.getObject(i) != null) {
						val = rs.getObject(i).toString();
						tmp.append(val);
					}else{
                		val = "";
					}
                    if (val != null) {
                    	
                        tmplist.add(val.trim());
                    } else {
                        tmplist.add("");
                        Log.out("[Warning] null value exist!");
                    }
                }
                tuples.add(tmplist);
            }
            
            
			// added by masato 20151221
            if (Ehtml.flag || Ehtml.flag2) { // SQLの結果を保存
            	String outDir = GlobalEnv.getoutdirectory();
            	String outFile = GlobalEnv.getoutfilename();
            	String a = outFile.substring(0, outFile.toLowerCase().indexOf("."));
            	String sqlResultFileName = a.substring(a.lastIndexOf(GlobalEnv.OS_FS) + 1, a.length());
            	File sqlResultFileDir = new File(outDir + GlobalEnv.OS_FS + "Ssql" + GlobalEnv.OS_FS + "sqlResults" + GlobalEnv.OS_FS + sqlResultFileName);
    			String name = "ssqlResult" + GlobalEnv.getQueryNum() + ".txt";
            	File sqlResultFile = new File(sqlResultFileDir.toString() + GlobalEnv.OS_FS + name);

            	if ( !sqlResultFileDir.exists() ) {
    				sqlResultFileDir.mkdirs();
    			}
            	PrintWriter pw = null;
    			try {
    				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
    						new FileOutputStream(sqlResultFile), "UTF-8")));
    			} catch (UnsupportedEncodingException e) {
    				e.printStackTrace();
    			} catch (FileNotFoundException e) {
    				e.printStackTrace();
    			}
				String hexString = DigestUtils.md5Hex(tmp.toString());
    			pw.println(hexString);
    			pw.close();
            } else if (Incremental.flag || Incremental.flag2) { // 前回のsqlの結果を確認
            	String outDir = GlobalEnv.getoutdirectory();
            	String outFile = GlobalEnv.getoutfilename();
            	String a = outFile.substring(0, outFile.toLowerCase().indexOf("."));
            	String sqlResultFileName = a.substring(a.lastIndexOf(GlobalEnv.OS_FS) + 1, a.length());
            	File sqlResultFileDir = new File(outDir + GlobalEnv.OS_FS + "Ssql" + GlobalEnv.OS_FS + "sqlResults" + GlobalEnv.OS_FS + sqlResultFileName);
    			String name = "ssqlResult" + GlobalEnv.getQueryNum() + ".txt";
            	File sqlResultFile = new File(sqlResultFileDir.toString() + GlobalEnv.OS_FS + name);
            	StringBuffer sqlResultBuffer = new StringBuffer();
                try {
                    FileReader fr = new FileReader(sqlResultFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                    	sqlResultBuffer.append(line);
                    }
                    br.close();
                    fr.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
				String hexString = DigestUtils.md5Hex(tmp.toString());
                if(hexString.equals(sqlResultBuffer.toString())){	// TODO if outType==1
                	// 同じだった場合
                	Log.ehtmlInfo("test");

                	// 評価用出力
//                	long end = System.currentTimeMillis();
//            		Log.ehtmlInfo("Parsing Time : " + (FrontEnd.afterparser - FrontEnd.start) + "msec<br>");
//            		Log.ehtmlInfo("Data construction Time : " + (end - FrontEnd.afterparser) + "msec<br>");
//            		Log.ehtmlInfo("Code generation Time : " + 0 + "msec<br>");
//            		Log.ehtmlInfo("ExecTime: " + (end - FrontEnd.start) + "msec<br>");
                	System.exit(0);
                } else {
                	// 違った場合は更新
                	Ehtml.setEhtml();	//必要? -> おそらく必要
                	
                	if ( !sqlResultFileDir.exists() ) {
        				sqlResultFileDir.mkdirs();
        			}
                	PrintWriter pw = null;
        			try {
        				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
        						new FileOutputStream(sqlResultFile), "UTF-8")));
        			} catch (UnsupportedEncodingException e) {
        				e.printStackTrace();
        			} catch (FileNotFoundException e) {
        				e.printStackTrace();
        			}
        			pw.println(hexString);
        			pw.close();
                }
            }


//            if(true){
            	FrontEnd.aftersql = System.currentTimeMillis();
//        		Log.info("SQL Time : " + (FrontEnd.aftersql - FrontEnd.afterparser) + "msec");
//            }
            Log.out("[SQLManager:execQuerySQL] Result tuples count = "
                    + tuples.size());
            GlobalEnv.setTuplesNum(tuples.size());

            if (tuples.size() == 0) {
                tmplist = new ExtList<String>();
            	for (int i = 1; i <= columnCount; i++) {
                    tmplist.add("");
                    Log.out("[Warning] null value exist!");
                }
                tuples.add(tmplist);
                GlobalEnv.setTuplesNum(tuples.size());
                throw (new IllegalStateException());
            }

        } catch (SQLException e) {
        	if(!query.endsWith("FROM ;")){
	              Log.err("Error[SQLManager.ExecSQL]: Can't Exec Query : query = " + query);
//	              GlobalEnv.errorText += "Error[SQLManager.ExecSQL]: Can't Exec Query : query = "
//	                      + query;
			      Log.err(e);
			      LogError.logErr();
//			      GlobalEnv.errorText += e;
			      GlobalEnv.addErr("Error[SQLManager.ExecSQL]: Can't Exec Query : query = " + query);


			      //added by goto 20131016 start
			      String list = "";
			      String errorContents[] = Suggest.getErrorContents(e);  //return: [0]=Error message, [1]=Error table name or column name, [2]=Error table alias
		    	  ArrayList<ArrayList> tableNameAndAlias = DB.getTableNamesFromQuery(query);	//return: (0)=Table name, (1)=Table alias, (2)=From phrase

			      String driver = GlobalEnv.getDriver().toLowerCase();
		    	  if( (driver.contains("postgresql") && (errorContents[0].contains("column") || errorContents[0].contains("table")) ||
		    		  //(driver.contains("mysql") ) ||	//TODO
		    		  //(driver.contains("db2") ) ||	//TODO
		    		  (driver.contains("sqlite") && errorContents[0].contains("column")))
		    		  //||(driver.contains("sqlserver") )	//TODO
		    		){
			    	  if(errorContents[0].contains("ambiguous")){
			    		  list = Suggest.getgetAmbiguousTableAndColumnNameList(conn, tableNameAndAlias.get(0), errorContents[1]);
			    	  }else{
				    	  list = Suggest.getTableAndColumnNameList(conn, tableNameAndAlias.get(0), tableNameAndAlias.get(1), errorContents[1], errorContents[2], tableNameAndAlias.get(2));
			    	  }
			    	  if(!list.isEmpty()){
			    		  Log.err("\n## Column list ##\n" + list);
//			    		  GlobalEnv.errorText += "\n## Column list ##\n" + list;
			    	  }

			    	  //161110 yhac
		    		  Ssedit.getSuggestlist(list, 1000);
		    		  //AutocorrectAlgorirhm_SQLへ(2回目)
		    		  try {
		    			  Ssedit.AutocorrectAlgorirhm_SQL(null, null, null, null, "column2");
		    		  } catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
		    		  }


			      }
		    	  else if( (driver.contains("postgresql") && (errorContents[0].contains("relation"))) ||
		    			   //(driver.contains("mysql") ) ||	//TODO
		    			   //(driver.contains("db2") ) ||	//TODO
		    			   (driver.contains("sqlite") && errorContents[0].contains("table"))
		    			 ){
			    	  list = Suggest.getTableNameList(conn, errorContents[1]);
			    	  Log.err("\n## Table list ##\n" + list + "\n");
//		    		  GlobalEnv.errorText += "\n## Table list ##\n" + list + "\n";
			      }
			      //added by goto 20131016 end

			      return ;
        	}
        } catch (IllegalStateException e) {
            System.err.println("Error[SQLManager.ExecSQL]: No Data Found : query = " + query);
        }
    }


    //morya start
    public void ExecListToResult(String listarg, String query) {

        Log.out("[SQLManager ExecListToResult]");
        Log.info("********** Query For Framework **********");
        query = query.replace("SELECT DISTINCT", "");
        query = query.replace(query.substring(query.indexOf("FROM"),query.length()),"").trim();
        Log.info(query);

        header_name = new ExtList<String>();
        header_type = new ExtList<String>();
        tuples = new ExtList<ExtList<String>>();

        //necessary variable for framework
        List <String> listcol = new ArrayList<String>();
        List <List<String>> listdb = new ArrayList<List<String>>();
        List <Integer> num_from_left = new ArrayList<Integer>();
        int listdb_column_num=0;

        int fromchnum = 0, tochnum=0;
        int flag=0;
        listdb_column_num++;
        // \t divide turn  **1turn=attribute get
        while(listarg.indexOf("\t",fromchnum) != -1){
        	tochnum = listarg.indexOf("\t",fromchnum);
        	String tmpstr = listarg.substring(fromchnum,tochnum);
        	int fromchnum2 = 0, tochnum2 = 0;
        	while(tmpstr.indexOf(",",fromchnum2) != -1){
        		tochnum2 = tmpstr.indexOf(",",fromchnum2);
        		listcol.add(tmpstr.substring(fromchnum2,tochnum2));
        		fromchnum2 = tochnum2 + 1;
        		if(flag==0) listdb_column_num++;
        	}
        	tochnum2 = tmpstr.length();
            listcol.add(tmpstr.substring(fromchnum2,tochnum2));
            flag++;

        	listdb.add(listcol);
        	listcol = new ArrayList<String>();

        	fromchnum = tochnum + 1; //need +1
        }
        tochnum = listarg.length();
        String tmpstr = listarg.substring(fromchnum,tochnum);
    	int fromchnum2 = 0, tochnum2 = 0;
    	while(tmpstr.indexOf(",",fromchnum2) != -1){
    		tochnum2 = tmpstr.indexOf(",",fromchnum2);
    		listcol.add(tmpstr.substring(fromchnum2,tochnum2));
    		fromchnum2 = tochnum2 + 1;
    	}
    	tochnum2 = tmpstr.length();
        listcol.add(tmpstr.substring(fromchnum2,tochnum2));
    	listdb.add(listcol);

        //query process
        fromchnum = 0; tochnum=0;
        while(query.indexOf(",",fromchnum) != -1){
        	tochnum = query.indexOf(",",fromchnum);
        	header_name.add(query.substring(fromchnum,tochnum).trim());
        	header_type.add("4");
        	for(int i=0;i<listdb_column_num;i++){
        		if(query.substring(fromchnum,tochnum).trim().equals(listdb.get(0).get(i))){
        			num_from_left.add(i);
        		}
        	}
        	fromchnum = tochnum + 1;
        }
        tochnum = query.length();
        header_name.add(query.substring(fromchnum,tochnum).trim());
        header_type.add("4");
        for(int i=0;i<listdb_column_num;i++){
    		if(query.substring(fromchnum,tochnum).trim().equals(listdb.get(0).get(i))){
    			num_from_left.add(i);
    		}
    	}

        //get record turn
        ExtList<String> tmplist;
        String val;
        for (int i=1;i<listdb.size();i++) {//from 1 roop
            tmplist = new ExtList<String>();
            for (int j=0;j<num_from_left.size();j++) {
            	val = (String)listdb.get(i).get(num_from_left.get(j));
                if (val != null) {
                    tmplist.add(val);
                } else {
                    tmplist.add("");
                    Log.out("[Warning] null value exist!");
                }
            }
            tuples.add(tmplist);

        }
        Log.out("[SQLManager:ExecListToResult] Result tuples count = "
                + tuples.size());

        Log.out("   "+tuples+"  ");
    }

    public ExtList<ExtList<String>> GetBody() {
        return tuples;
    }

    public void close() {
    	if (GlobalEnv.getframeworklist() == null) {
	        try {
	            if (!conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            Log.err("Error[SQLManager]: Can't Close DB :");
//	            GlobalEnv.errorText += "Error[SQLManager]: Can't Close DB :";
	            GlobalEnv.addErr("Error[SQLManager]: Can't Close DB :");
	            return ;
	        }
    	}

    }

    public String getAttList(String from_string, String connector, String att){
    	String columnList = new String();
    	try{
    		Statement stmt = conn.createStatement();
    		String[] spritFrom = from_string.split(",");
    		if(connector == null || connector.isEmpty()){
    			connector = ",";
    		}
    		for(int fromnum = 0; fromnum < spritFrom.length ;fromnum++){
    			FromParse fp = new FromParse(spritFrom[fromnum].trim());
    			if(att.equals( fp.getAlias() + "." + "*") || att.equals("*")){
    				String sql = "SELECT " + att + " FROM " + fp.getRealName() + " " + fp.getAlias() +" WHERE false;";
    				ResultSet rs = stmt.executeQuery(sql);
    				ResultSetMetaData metaData = rs.getMetaData();
    				int columnCount = metaData.getColumnCount();
    				for(int i = 1 ; i <= columnCount;i++){
    					columnList += fp.getAlias() + "." + metaData.getColumnName(i) + connector;
    				}
    			}
    		}
    		columnList = columnList.substring(0,columnList.length()-1);
    	}catch(Exception e){
    		Log.err(e);
    	}
    	return columnList;
    }

    //added by taji 171103 start
	public void ExecUpdate(String query) {
        if(!query.endsWith("FROM ;")){
	        Log.info("\n********** UPDATE statement is **********");
	        Log.info(query);
        }
        GlobalEnv.query = query;

        header_name = new ExtList<String>();
        header_type = new ExtList<String>();
        tuples = new ExtList<ExtList<String>>();

        try {
            Statement stat = conn.createStatement();
            
            stat.executeUpdate(query);
        } catch (SQLException e) {
        	
        } catch (IllegalStateException e) {
            System.err
                    .println("Error[SQLManager.ExecSQL]: No Data Found : query = "
                            + query);
        }
	}

	public void create_log(String query_name, ArrayList pTables, HashMap<String, ArrayList> trigger_tables) {
		try {
            Statement stat = conn.createStatement();
            String log_table = "";
            DatabaseMetaData dbmd = conn.getMetaData();
            int pTablesize = pTables.size();
            for(int i = 0; i < pTablesize; i++){
            	String new_column = "";
            	String table = (String) pTables.get(i);
            	log_table += "create table " + query_name + "_" + table + " ( ";
            	ResultSet rs = dbmd.getColumns(null, null, table, "");
            	try{
            		while(rs.next()){
            			String name = rs.getString("COLUMN_NAME");
        				String type = rs.getString("TYPE_NAME");
        				if(type.equals("serial")){
        					type = "int4";
        				}
        				if(trigger_tables.get(table).contains(name)){
        					new_column += ", log_" + name + " " + type;
        				}
        				log_table += name + " " + type + ", ";
//        				if(!rs.isLast()){
//        					log_table += ", ";
//        				}
            		}
            	}finally {
        			rs.close();
        		}
            	log_table += "log_time timestamp, op varchar" + new_column + " );\n";
            }
            Log.info("\n********** create log tables **********");
 	        Log.info(log_table);
        	stat.executeUpdate(log_table);
        } catch (SQLException e) {
        	
        }
		
	}
	//added by taji 171103 end
	public void ExecMetaQuery(String tblName) {
    	try{
//			String statement = "SELECT * FROM " + tblName + " WHERE 1=0";
			String statement = "";
			if(GlobalEnv.getdbms().equals("hive")){
				statement = "Describe formatted " + tblName;
			}
			if(GlobalEnv.getdbms().equals("postgresql")){
				statement = "select relname, (relpages * 8192) as bytes FROM pg_class WHERE relname = '" +tblName+ "';";
			}
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			ExtList tmpList = new ExtList();
			while(rs.next()){
				try {
					ResultSetMetaData rsmd1 = rs.getMetaData();
					int columnCount1 = rsmd1.getColumnCount();
					ExtList tmp = new ExtList();
					for (int i = 1; i <= columnCount1; i++) {
						tmp.add(rs.getObject(i));
					}
					tmpList.add(tmp);
				}catch (NullPointerException e){
				}
			}
			this.tuples = tmpList;
    	}catch (SQLException e){
    		e.printStackTrace();
		}

    }

	public void getTableAtt(String tblName) {
    	try{
    		this.tuples = new ExtList<>();
    		String sql = "Select * from " + tblName + " where 1 = 0;";
    		Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			ExtList tmp = new ExtList();
			for(int i = 1 ; i <= columnCount;i++){
				tmp.add(metaData.getColumnName(i));
			}
			this.tuples.add(tmp);
		}catch(SQLException e){
    		//e.printStackTrace();
		}
	}
}
