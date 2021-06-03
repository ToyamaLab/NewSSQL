package supersql.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import supersql.FrontEnd;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Responsive.Responsive;
import supersql.dataconstructor.Limiter;
import supersql.extendclass.ExtList;
import supersql.extendclass.QueryBuffer;

public class GlobalEnv {

	public static final char COMMENT_OUT_LETTER = '-';	//コメントアウト等による利用(ex: -- )

	/* [����] getProperty�᥽�åɤˤ�äơ������ƥ�ץ�ѥƥ�����(OS���ե�������ڤ�ʸ��ۡ��ࡢ����ʤ�)����� */
	/* �����ƥ�ץ�ѥƥ��Ͱ����μ���: System.getProperties().list(System.out); */
	public final static String USER_HOME = System.getProperty("user.home");				//�桼���Υۡ���ǥ��쥯�ȥ�
	public final static String OS = System.getProperty("os.name");						//OS��̾��("Mac OS X" ��)
	public final static String OS_LS = System.getProperty("line.separator");			//OS���Ȥβ��ԥ�����(Windows:"\r\n",Mac:"\r",UNIX:"\n" ��)
	public final static String OS_FS = System.getProperty("file.separator");			//OS���ȤΥե�������ڤ�ʸ��(Windows:"\" , Mac��Linux:"/" ��)
	public final static String OS_PS = System.getProperty("path.separator");			//OS���ȤΥѥ����ڤ�ʸ��(Windows";" , Mac��Linux":" ��)
	public final static String EXE_FILE_PATH = getCurrentPath();	//�¹ԥե�����Υѥ�(�¹�jar�ե�������ɤ��ˤ��뤫)����� (�����:���Хѥ����֤äƤ����礢��)
	public final static String USER_LANGUAGE = System.getProperty("user.language");		//�桼���θ���(���ܸ�:ja)  ���ܸ졦�Ѹ��ڤ��ؤ���ǽ���դ���Ȥ��˻��ѡ�
	public final static String USER_COUNTRY = System.getProperty("user.country");		//�桼���ι�̾(����:JP)   ���ܸ졦�Ѹ��ڤ��ؤ���ǽ���դ���Ȥ��˻��ѡ�

	public final static String MEDIA_XML = System.getProperty("user.dir")+OS_FS+"XML"+OS_FS+"ssql_medias.xml";

	public static final String DEFAULT_CHARACTER_CODE = "UTF-8";

	public static String query = "";

	private static Hashtable<String, String> envs;

	// 20140624_masato
	public static String errorText = "";
	public static String errorText_main = "";
	public static String queryInfo = "";
	public static String queryLog = "";
	public static String queryName = "";

	//module20180506 kotani
	public static ArrayList<String> filelist = new ArrayList<>();//.ssql-unityのファイル 拡張子あり
	public static ArrayList<String> medialist= new ArrayList<>();//.ssql-unityのファイル 拡張子は除去してメディア名だけにする
	public static ArrayList<String> multifilecon= new ArrayList<>();//fileの中身(contents)　要素ごとにファイルの中身がある

	//����ե�����ξ���
	private static String layout = "";

	private static String host;

	private static String port;

	private static String db;

	private static String user;

	private static String home;

	private static String outdir;

	private static String password;

	private static String encode;

	private static String apiServerUrl;

	//chie start
	private static String driver;

	private static String optimizer;

	private static String invokeServletPath; //used by online

	private static String fileDirectory; //used by online

	private static int tupleNum;
	//chie end
	
	// goto 20200728  SQL Server
	private static String sqlserver_instanceName;
	private static String sqlserver_options;
	
	
	

	//swf��
	public static String table_name;
	public static String where_line;

	//foreachなど
	public static boolean foreach_flag = false;

	//sessionなど
	public static boolean session_flag = false;

	//optimizerなど
	private static boolean optimizable = true;

	//tk embed��
	public static StringBuffer err = new StringBuffer();
	public static int online_flag = 0;
	public static int err_flag = 0;
	public static int EmbedbyQuery = 0;
	private static String embedtmp;
	private static ArrayList<String> EmbedFile = new ArrayList<String>(100);
	//static String driver = "org.postgresql.Driver";

	//for next/prev page
	public static int startnum = 0;
	public static int endnum = 0;
	//tbt add 180806
	public static boolean joinFlag = false;
	public static ExtList sep_sch_bak;
	public static HashMap<String, ArrayList<String>> relatedTableSet;
	public static int totalTupleNum = 0;
	public static int diff;
	public static String nullValue= "PqVyySBvmTiyfKjsspwt56kXMxwqubX9DXkVNDKN";
	public static int sideWidth = 100;
	public static HashMap<String, String> attType;
	public static HashMap<String, Long> tableSize;
	public static ExtList aggListTmp;
    public static HashMap<String, ExtList> tableAtts;
    //tbt end

	public static ArrayList<Limiter> limit = new ArrayList<Limiter>();
	public static Limiter.RealLimiter realLimit;

	public static void setGlobalEnv(String[] args) { // 引数のファイル名やオプション等を取得
		// err_flag = 0; // TODO 最初に初期化されているから必要ない？
		// err = new StringBuffer(); // TODO 上と同様？
		envs = new Hashtable<String, String>();
		String key = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				if (key != null) {
					envs.put(key, "");
				}
				key = args[i];
			} else {
				// modifed by masato 20151118 for ehtml
				if(key.equals("-query")){
					String q = "";
					for(int j = i; j < args.length; j++){
						if(!args[j].startsWith("-")){
							q += args[j] + " ";
						} else {
							envs.put(key, q);
							i = j;
							j = args.length-1;
						}
						if(j==args.length-1){
							envs.put(key, q);
							i = j;
							j = args.length-1;
						}
					}
				} else {
					//
					envs.put(key, args[i]);
				}
				key = null;
			}
		}
		//		for (int i = 0; i < args.length; i++) {
		//			if (args[i].startsWith("-")) {
		//				if (key != null) {
		//					envs.put(key, "");
		//				}
		//				key = args[i];
		//			} else {
		//				envs.put(key, args[i]);
		//				key = null;
		//			}
		//		}
		if (key != null) {
			envs.put(key, "");
		}

		//added by goto 20120707 start
		//optimize level　"-O0,-O1,-O2,-O3"
		//optimize level が設定されていればオプションを書き直す
		for (int i = 0; i <= 3; i++)
		if(envs.containsKey("-O"+i)){
			envs.remove("-O"+i);
			envs.put("-O", Integer.toString(i));
			break;
		}
		//added by goto 20120707 end

		setQuietLog();

		// added by masato 20150915 start
		setIncremental();
		// added by masato 20150915 end

		// added by masato 20151118 start
		setEhtml();
		// added by masato 20151118 end

		//add tbt 180718
		setMulti();
		//add tbt end


		getConfig();

		Log.out("GlobalEnv is " + envs);
	}

	public static void setGlobalEnvEmbed(String[] args) {

		envs = new Hashtable<String, String>();
		String key = null;

		for (int i = 0; i < args.length; i++) {
			Log.out("args:"+args[i]);
			if (args[i].startsWith("-")) {
				if (key != null) {
					envs.put(key, "");
				}
				key = args[i];
			} else {
				envs.put(key, args[i]);
				key = null;
			}
		}
		if (key != null) {
			envs.put(key, "");
		}

		setQuietLog();
		Log.out("GlobalEnv is " + envs);
	}
	public static void getConfig() {
		host = null;
		db = null;
		//port="5432";
		port = null;
		user = USER_HOME;
		home = USER_HOME;
		outdir = null;
		driver = null;
		password = null;
		encode = null;
		optimizer = null;
		sqlserver_instanceName = null;
		sqlserver_options = null;
		String config = getconfigfile(); // -cでconfigファイルを指定できる
		String[] c_value;

		if (config == null) {
			//changed by goto 20120624 start
			if(new File(home.concat("/.ssql")).exists())
			config = home.concat("/.ssql");
			else
			config = home.concat("/config.ssql");
			//changed by goto 20120624 end

			Log.out("offline config");
			c_value = getConfigValue(config);
			if(c_value[14] != null){//module180426 getConfigValueで指定したunity_module_dir取ってきてる
				//c_value[14]は/Users/kotani/Desktop/module
				getUnityModuleFile(c_value[14]);
			}
		}
		else {
			Log.out("[GlobalEnv:getConfig] config file =" + config);
			c_value = getConfigValue(config);
		}


		//if (c_value[0] == null && c_value[1] == null && c_value[2] == null && c_value[3] == null) {
		if (c_value.length < 1) {
			Log.err("No config file("+config+")");
			return;
		}
		try {
			if (c_value[0] != null) {
				host = c_value[0];
			}
			if (c_value[1] != null) {
				db = c_value[1];
			}
			if (c_value[2] != null) {
				user = c_value[2];
			}
			if (c_value[3] != null) {
				outdir = c_value[3];
			}
			if (c_value[4] != null){
				embedtmp = c_value[4];
			}
			if (c_value[5] != null){
				driver = c_value[5];
			}
			if (c_value[6] != null){
				password = c_value[6];
			}
			if (c_value[7] != null){
				encode = c_value[7];
			}
			if (c_value[8] != null){
				optimizer = c_value[6];
			}
			if (c_value[9] != null){
				invokeServletPath = c_value[9];
			}
			if (c_value[10] != null){
				fileDirectory = c_value[10];
			}
			if (c_value[11] != null){
				setLayout(c_value[11]);
			}
			if (c_value[12] != null){
				setApiServerUrl(c_value[12]);
			}
			//added by goto 20161217  for responsive
			if (c_value[13] != null){
				Responsive.setOption(c_value[13]);
				Log.info("Responsive.setOption: "+c_value[13]);
			}
			if(c_value[15] != null){
				port = c_value[15];
			}
			if(c_value[16] != null){
				sqlserver_instanceName = c_value[16];	// goto 20200728  SQL Server
			}
			if(c_value[17] != null){
				sqlserver_options = c_value[17];		// goto 20200728  SQL Server
			}
			
		} catch (Exception ex) {
		}

		if(embedtmp == null) //TODO
		embedtmp = "/tmp";
		Log.out("Config is {host=" + host + ", db=" + db + ", user=" + user +
		", outdir=" + outdir + ", driver=" + driver + ", password=" + password +
		", encode=" + encode + ", optimizer=" + optimizer +", embedtmp="+ embedtmp +
		", "+Responsive.OPTION_NAME+"="+Responsive.getOption()+", port=" + port + 
		", sqlserver_instanceName=" + sqlserver_instanceName + ", sqlserver_options=" + sqlserver_options + " }");	
		return;
	}

	public static String seek(String key) {
		return envs.get(key);
	}

	public static String getconfigfile() {
		return seek("-c");
	}

	/*
	* �ƥ��ȥǡ����Υե���?�λ�? ���ߤϻ��Ѥ��Ƥ��ʤ�
	*/
	public static String gettestdatafile() {
		return seek("-t");
	}

	/**
	* SuperSQLの基本的読み込み方法
	*/
	public static String getfilename() {	// /user/xx/test.ssql
		String filename = seek("-f");
		if(filename == null){// for embed ssql
			return "";
		}else{
			if(filename.indexOf(".ssql") > 0 || filename.indexOf(".sql") > 0){
				return filename;
			}else{
				System.err.println("file extension is must be '.ssql' or '.sql'");
				System.exit(1);
			}
			return seek("-f");
		}
	}
	public static String getfilename2() {	// test.ssql
		return new File(getfilename()).getName();
	}
	public static String getfilename3() {	// test
		String fn = getfilename2();
		if (fn.contains(".")) {
			fn = fn.substring(0, fn.lastIndexOf("."));
		}
		return fn;
	}

	//added by goto 20141130
	public static String getfileparent() {
		try {
			return new File(seek("-f")).getParent().toString();
		} catch (Exception e) {
			return ".";
		}
	}

	public static String getoutdirectory() {
		String ret = seek("-d");
		if (ret == null) {
			if (outdir != null) {
				ret = outdir;
			}
		}
		if (ret != null) {
			if (ret.startsWith("~/")) {
				ret = ret.substring(1);
				ret = home + ret;
			} else if (ret.startsWith("~")) {
				ret = ret.substring(1);
				String nyoro = home.substring(0,home.indexOf(user));
				ret = nyoro + ret;
			}
		}
		return ret;
	}

	/**
	* ���ϥե�����̾
	*/
	public static String getoutfilename() {
		return seek("-o");
	}


	/**
	* �ǡ����١�������³����桼��̾
	*/
	public static String getusername() {
		String ret = seek("-u");
		if (ret == null) {
			ret = user;
		}
		return ret;
	}

	/*
	* ��³����ǡ����١��� ��ά���줿���桼��̾��Ʊ���Ȥ���
	*/
	public static String getdbname() {
		String ret = seek("-db");
		if (ret == null) {
			if (db != null) {
				ret = db;
			} else {
				if (getDriverName().equals("sqlserver")){	// goto 20200728  SQL Server
					ret = "";
				}else{
					ret = getusername();
				}
			}
		}
		return ret;
	}

	/*
	* ��³����DB�ۥ���̾
	*/
	public static String gethost() {
		String ret = seek("-h");
		if (ret == null) {
			if (host != null) {
				ret = host;
			} else {
				ret = "";
			}
			//			else {
			//				ret = "postgres.db.ics.keio.ac.jp";
			//			}
		}
		return ret;
	}

	public static String getport() {
		String ret = seek("-p");
		if (ret == null) {
			if (port != null) {
				ret = port;
			}else{
				String driverName = getDriverName();
				if(driverName.contains("postgres")){
					ret = "5432";
				}else if (driverName.equals("mysql")) {
					ret = "3306";
				}else if (driverName.equals("db2")) {
					ret = "50000";
				}else if (driverName.equals("hive")) {
					ret = "10000";
				}else if (driverName.equals("sqlserver")) {
					ret = "1433";		// goto 20200728  SQL Server
				}else{
					ret = "5432";
				}
			}
		}
		return ret;
	}

	public static String getdbms() {
		String ret = seek("-driver");
		if (ret == null) {
			if (driver != null) {
				ret = driver;
			} else {
				ret = "postgresql";
			}
		}
		return ret;
	}

	//��³����DB��url
	public static String geturl() {
		//added by goto 20141204
		if(getDriverName() != null)	driver = getDriverName();
		if(gethost() != null)		host = gethost();
		if(getdbname() != null)		db = getdbname();
		if(getport() != null)		port = getport();

		String ret = "jdbc:postgresql://" + host + ":" + port + "/" + db;
		if (driver != null) {
			if(driver.equals("postgres")){
				ret = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			}else if (driver.equals("mysql")) {
				ret = "jdbc:mysql://" + host + "/" + db + "?useUnicode=true&characterEncoding=SJIS";
			}else if (driver.equals("db2")) {
				ret = "jdbc:db2:" + db;
				//added by goto 20120518 start
			}else if (driver.equals("sqlite") || driver.equals("sqlite3")) {
				//				ret = "jdbc:sqlite:" + db;
				//added by goto 20141130 start
				ret = "jdbc:sqlite:";
				if (!new File(db).isAbsolute()) {
					if(GlobalEnv.getoutdirectory() != null)
					ret += GlobalEnv.getoutdirectory();
					else
					ret += GlobalEnv.getfileparent();
					ret += GlobalEnv.OS_FS;
				}
				ret += db;
				//added by goto 20141130 end
			}else if (driver.equals("hive")){
				ret = "jdbc:hive2://" + host + ":10000/" + db;
			}
			//added by goto 20120518 end
			
			else if (driver.equals("sqlserver")){	// goto 20200728  SQL Server
				// 接続URLの一般的な書式:		https://docs.microsoft.com/ja-jp/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver15
				// jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
				ret = "jdbc:sqlserver://";
				if(host != null && !host.equals("")){
					String instanceName = getSQLServerInstanceName().trim();
					String options = getSQLServerOptions().trim();

					ret += host.trim();																			//serverName
					if(instanceName != null && !instanceName.equals(""))	ret += "\\"+instanceName.trim();	//instanceName
					if(port != null && !port.equals(""))					ret += ":"+port.trim();				//portNumber
					if(db != null && !db.equals(""))						ret += ";databaseName="+db.trim();	//databaseName
					if(options != null && !options.equals(""))				ret += ";"+options.trim();			//上記以外のオプション指定
																												// -> 下記に書かれているものを ; 区切りで書く  e.g. sqlserver_options=accessToken=a;applicationName=a
																												//    指定可能なオプション(プロパティ)一覧 https://docs.microsoft.com/ja-jp/sql/connect/jdbc/setting-the-connection-properties?view=sql-server-ver15
//					if(getSQLServerProperty() != null){
//						//複数の場合は ; 区切りで書く		e.g. value1;value2
//						String[] properties = getSQLServerProperty().trim().split(";");
//						for(String p : properties){
//							ret += ";property="+p;																//property
//						}
//					}
				}
				// 指定可能なオプション(プロパティ)一覧： https://docs.microsoft.com/ja-jp/sql/connect/jdbc/setting-the-connection-properties?view=sql-server-ver15
				// -> 50個以上あるため、optionsへ ; 区切りで記述できる形で実装
				//if(getSQLServerSecurity() != null)		ret += ";integratedSecurity="+getSQLServerSecurity();			//integratedSecurity
				//if(getSQLServerScheme() != null)			ret += ";integratedSecurity="+getSQLServerScheme();				//authenticationScheme
				//if(getSQLServerApplicationName() != null)	ret += ";applicationName="+getSQLServerApplicationName();		//applicationName
				
				Log.out("SQL Server URL: "+ret);
			}
			
		} else {
			ret = "jdbc:postgresql://" + host + ":" + port + "/" + db;
		}

		return ret;
	}
	

	

	//��³����ɥ饤�ФΥѥ����
	public static String getpassword() {
		String ret = seek("-p");
		if (ret == null) {
			if (password != null) {
				ret = password;
			} else {
				ret = "";
			}
		}
		return ret;
	}

	//���ϥե������encode
	public static String getencode() {
		String ret;
		if (encode != null) {
			ret = encode;
		} else {
			ret = "";
		}
		return ret;
	}


	/*
	* -debugでLog.outの出力、-quietでLog.infoも出力しない
	*/
	public static void setQuietLog() {
		if (seek("-debug") == null) {
			Log.setLog(0);
		} else {
			Log.setLog(1);
		}
		if (seek("-quiet") == null) {
			Log.setinfoLog(1);
		} else {
			Log.setinfoLog(0);
			Log.setLog(0);
		}
	}

	// add tbt 180718 to set multiple query
	private static void setMulti() {
		if (seek("-multiquery") != null) {
			GlobalEnv.setMultiQuery();
		}
		if(seek("-multigb") != null){
			GlobalEnv.setMultiGB();
		}
		if(seek("-noforestdiv") != null){
			GlobalEnv.setNoForestDiv();
		}
		if(seek("-orderfrom") != null){
			GlobalEnv.setOrderFrom();
		}
	}




	//add tbt end

	// added by masato 20150915 for incremental update data
	private static void setIncremental(){
		if(seek("-incremental") != null){
			Incremental.setIncremental();
		} else {
			return;
		}
	}

	// added by masato 20151118 for embedding
	private static void setEhtml(){
		if(seek("-ehtml") != null){
			Ehtml.setEhtml();
		} else {
			return;
		}
	}


	/*
	* -queryによるクエリの入力(-f以外のパターン)
	*/
	public static String getQuery() {
		return seek("-query");
	}

	public static String cssout() {
		return seek("-cssout");
	}
	/////////////

	//added by goto 20161217  for responsive
	public static String getResponsiveURL() {
		String ret = seek("-"+Responsive.OPTION_NAME);
		if (ret == null) {
			if (Responsive.getOption() != null) {
				ret = Responsive.getOption();
			} else {
				ret = "";
			}
		}
		return ret;
	}


	// offline getConfigValue
	protected static String[] getConfigValue(String config) {

		BufferedReader filein = null;
		String line = new String();

		//(invokeServletPath and fileDirectory are not used in offline)
		String con[] = { "host", "db", "user", "outdir", "embedtmp", "driver", "password", "encode", "optimizer",
		"invokeServletPath","fileDirectory", "layout", "api_server_url", Responsive.OPTION_NAME,
		"unity_module_dir", "port", "sqlserver_instance", "sqlserver_options"};//module180426
		String c_value[] = new String[con.length];

		try {
			filein = new BufferedReader(new FileReader(config));
			while (true) {
				try {
					line = filein.readLine();
				} catch (IOException e1) {
				}
				if (line == null)
				break;
				line = line.trim();
				for (int i = 0; i < con.length; i++) {
					if (line.startsWith(con[i])) {
						c_value[i] = line.substring(line.indexOf("=") + 1)
						.trim();
					}
				}
			}
			filein.close();
		} catch (FileNotFoundException e1) {
			Log.err("Configuration file " + config + " not found.");
		} catch (IOException e) {
			Log.err("IOEXception error from supersql.common.GlobalEnv.getConfigValue.");
		}

		return c_value;
	}

	//online getConfigValue
	@SuppressWarnings("resource")
	protected static String[] getConfigValue2(String config) {	//未使用？

		String line = new String();
		String con[] = { "host", "db", "user", "outdir", "embedtmp", "driver", "password", "encode", "optimizer",
		"invokeServletPath","fileDirectory","unity_module_dir", "port"};//module180426
		String c_value[] = new String[con.length];
		BufferedReader dis;

		try{
			if(config.startsWith("http:"))
			{
				URL fileurl = new URL(config);
				URLConnection fileurlConnection = fileurl.openConnection();
				dis = new BufferedReader(new InputStreamReader(fileurlConnection.getInputStream()));
			}
			else
			{
				dis = new BufferedReader(new FileReader(config));
				line = null;
			}
			while (true) {
				try {
					line = dis.readLine();

					if(line == null)
					break;
					for (int i = 0; i < con.length; i++) {
						if (line.startsWith(con[i])) {
							c_value[i] = line.substring(line.indexOf("=") + 1)
							.trim();
						}
					}
				} catch (MalformedURLException me) {
					Log.err("MalformedURLException: " + me);
				} catch (IOException ioe) {
					Log.err("IOException: " + ioe);
				}
			}
		} catch (MalformedURLException me) {
			Log.err("MalformedURLException: " + me);
		} catch (IOException ioe) {
			Log.err("IOException: " + ioe);
		}

		return c_value;
	}
	/**
	* ����ʸ��������ʸ��WHERE��˲ä���
	*/
	public static String getCondition() {
		return seek("-cond");
	}

	/**
	* @return
	*/
	public static boolean getForeachFlag() {
		return foreach_flag;
	}

	/**
	* Imagefile��ǡ����Хѥ��򵭽Ҥ����Ȥ��� �ղä���ǥ��쥯�ȥ�
	*/
	public static String getBaseDir() {
		return seek("-basedir");
	}

	/**
	* cacheLevel
	*/
	public static String getCacheLevel() {
		return seek("-cacheLevel");
	}

	/**
	* Invoke�Υ����֥�åȤ�path
	*/
	public static String getInvokeServletPath() {
		String is = seek("-invokeservletpath");

		if (is == null) {
			is = invokeServletPath;
			if(is == null){
				is = "http://www.db.ics.keio.ac.jp/servlet/supersql.invoke.InvokeServlet2";
				//is = "http://localhost:8080/invoke/servlet/supersql.invoke.InvokeServlet2";
			}
		}
		return is;
	}

	//tk///////////////////////////////////////////////////
	public static void addErr(String errMsg)
	{
		err.append(errMsg);
		err_flag = 1;
	}

	public static String getErr()
	{
		return err.toString();
	}
	public static void setOnlineFlag()
	{
		online_flag = 1;
	}
	public static int getOnlineFlag()
	{
		return online_flag;
	}

	public static int getErrFlag()
	{
		return err_flag;
	}

	public static int getEmbedOption() {
		if (seek("-eq") == null)
		{
			//without option
			return 0;
		}else {
			//with option
			return 1;
		}
	}

	public static int isNewEmbed() {

		if(seek("-newEmbed") == null)
		{
			//without option
			return 0;
		}else {
			//with option
			return 1;
		}
	}
	public static boolean isOpt() {
		if(optimizer != null && optimizer.equals("on")){
			//with option
			return true;
		}else{
			//modified by ria 20110912 start
			//if(seek("-optimizer") == null && seek("-O") == null)
			if(seek("-optimizer") == null)
			//modified by ria 20110912 end
			{
				//without option
				return false;
			}else {
				//with option
				return true;
			}
		}
	}
	public static boolean isMultiThread(){

		if(seek("-mt") == null)
		return false;
		else
		return true;
	}

	public static boolean isAjax(){

		if(seek("-ajax") == null)
		return false;
		else
		return true;
	}

	public static boolean isServlet(){

		if(seek("-servlet") == null && seek("-invokeservletpath") == null && invokeServletPath == null){
			return false;
		}else{
			return true;
		}
	}

	public static String getJsDirectory(){
		return seek("-jsdirectory");
	}

	public static String getAjaxTarget(){
		return seek("-ajaxtarget");
	}

	public static String getEmbedTmp(){
		return embedtmp;
	}

	public static ArrayList<String> getEmbedFile(){
		return EmbedFile;
	}

	public static void addEmbedFile(String file){
		EmbedFile.add(file);
	}

	//chie
	public static String getFileDirectory(){
		if(fileDirectory == null){
			return ".";
		}
		return fileDirectory;
	}

	public static String getDriverName(){
//		return seek("-driver");
		String ret = seek("-driver");
		if (ret == null) {
			if (driver != null) {
				ret = driver;
			} else {
				ret = "postgresql";
			}
		}
		return ret;
	}

	public static String getDriver(){
		String ret = seek("-driver");

		if (ret == null) {
			if (driver != null) {
				ret = driver;
			} else {
				ret = "postgresql";
			}
		}

		if(ret.equals("postgresql") || ret.equals("postgres")){
			ret = "org.postgresql.Driver";
		} else if(ret.equals("mysql")){
			ret = "com.mysql.jdbc.Driver";
		} else if(ret.equals("db2")){
			ret = "com.ibm.db2.jcc.DB2Driver";
			//added by goto 20120518 start
		} else if (ret.equals("sqlite") || ret.equals("sqlite3")) {
			ret = "org.sqlite.JDBC";
		} else if (ret.equals("hive")){
			ret = "org.apache.hive.jdbc.HiveDriver";
		//added by goto 20120518 end
		} else if (ret.equals("sqlserver")){	// goto 20200728  SQL Server
			ret = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		}
		
		return ret;
	}

	public static Hashtable<String, String> getEnv(){
		return envs;
	}
	public static void setEnv(Hashtable<String, String> env){
		envs = env;
	}

	//set and get number of taples
	public static void setTuplesNum(int num){
		tupleNum = num;
	}

	public static int getTuplesNum(){
		return tupleNum;
	}

	//added by ria 20110628 start
	public static int getOptLevel()
	{
		String s = seek( "-O" );
		if ( s == null )
		{
			return 2;
		}
		else
		{
			return Integer.parseInt( s );
		}
	}

	public static void setOptimizable( boolean b )
	{
		optimizable = b;
	}

	public static boolean isOptimizable()
	{
		return optimizable;
	}

	public static String getLayout() {
		return layout;
	}

	public static void setLayout(String layout) {
		GlobalEnv.layout = layout;
	}

	public static String getApiServerUrl() {
		return apiServerUrl;
	}

	public static void setApiServerUrl(String apiServerUrl) {
		GlobalEnv.apiServerUrl = apiServerUrl;
	}
	public static String getframeworklist() {
		return seek("-fwlist");
	}

	//added by goto 20141130
	//halken TFEmatcher
	public static boolean isTFEmatcher() {
		if(seek("-tfematcher") != null)
		return true;
		return false;
	}

	//added by goto 20141201
	public static boolean isLogger() {
		//Default: off
		if(seek("-logger") != null && seek("-logger").equalsIgnoreCase("on"))
		return true;
		return false;
	}

	//added by goto 20150112
	public static boolean isCheckquery() {
		if(seek("-checkquery") != null || seek("-getparseresult") != null)
		return true;
		return false;
	}


	//added by goto 20141201
	private static String getCurrentPath(){
		String cp = System.getProperty("java.class.path");
		if(cp.contains(OS_PS)){
			String cps[] = cp.split(OS_PS);
			for(int i=0; i<cps.length; i++){
				if(cps[i].contains(OS_FS)){
					cp = cps[i].trim();
					break;
				}
			}
		}
		if(cp.endsWith(".jar")){
			cp = new File(cp).getParent();
			if(cp.endsWith("libs"))
			cp = new File(cp).getParent();
		}
		return cp;
	}

	//added by goto 20141209
	//return the output directory's path
	public static String getOutputDirPath() {
		String outdir = GlobalEnv.getoutdirectory();
		if (outdir == null)
		outdir = GlobalEnv.getfileparent();
		if (outdir == null )
		outdir = GlobalEnv.getfileparent();
		return outdir;
	}

	// added by masato 20150525
	public static String getLinkValue(){
		return seek("-ehtmlarg");
	}

	// added by masato 20151128 for execute multiple query in ehtml or incremental
	public static Integer getQueryNum(){
		try {
			return Integer.parseInt(seek("-querynum"));
		} catch (Exception e) {
			return 0;
		}
	}

	// added by yusuke 20161109 for autocorrect
	public static boolean isSsedit_autocorrect() {
		if(seek("-ssedit_autocorrect") != null)
		return true;
		return false;
	}
	
	
	// goto 20200728  SQL Server
	public static String getSQLServerInstanceName() {
		String ret = seek("-sqlserver_instance");
		if (ret == null) {
			if ( sqlserver_instanceName != null) {
				ret = sqlserver_instanceName;
			} else {
				ret = "";
			}
		}
		return ret;
	}
	public static String getSQLServerOptions() {
		//e.g. sqlserver_options=accessToken=a;applicationName=a
		String ret = seek("-sqlserver_options");
		if (ret == null) {
			if ( sqlserver_options != null) {
				ret = sqlserver_options;
			} else {
				ret = "";
			}
		}
		ret = ret.trim();
		if((ret.startsWith("'") && ret.endsWith("'")) || (ret.startsWith("\"") && ret.endsWith("\""))){		
			//クォーテーションありの場合   e.g. sqlserver_options='accessToken=a;applicationName=a'
			ret = ret.substring(1, ret.length()-1);		//クォーテーションを削除
		}
		return ret;
	}
	
	
	
	
	
	

	//isNumber
	public static boolean isNumber(String val) {
		String regex = "^\\-?[0-9]*\\.?[0-9]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(val);
		return m.find();
	}


	// tbt add for c_tab
	private static boolean c_tab_flag = false;

	public static void setCtabflag(){
		c_tab_flag = true;
	}
	public static void unsetCtabflag(){
		c_tab_flag = false;
	}

	public static boolean getCtabflag(){
		return c_tab_flag;
	}
	// tbt end
	// tbt add 180701
	public static Long start_mt;
	public static Long end_mt;
	private static boolean isMultiQuery = false;
	private static boolean isMultiGB = false;
	private static boolean isOrderFrom = false;
	private static boolean isNoForestDiv = false;
	public static void setMultiQuery() {
		isMultiQuery = true;
	}
	public static void unSetMultiQuery() {
		isMultiQuery = false;
	}
	public static boolean isMultiQuery(){
		return isMultiQuery;
	}
	private static void setMultiGB() {
		isMultiGB = true;
	}
	private static void setNoForestDiv() {
		isNoForestDiv = true;
	}
	public static boolean isMultiGB(){
		return isMultiGB;
	}
	public static boolean isNoForestDiv(){
		return isNoForestDiv;
	}
	public static void setOrderFrom(){
		isOrderFrom = true;
	}
	public static boolean isOrderFrom(){
		return isOrderFrom;
	}

	//for multi query
	public static ArrayList<ArrayList<QueryBuffer>> qbs;
	public static ArrayList<ArrayList<QueryBuffer>> sameTree_set;
	public static ArrayList<ArrayList<QueryBuffer>> sameForest_set;
	public static HashMap<ExtList, ExtList> headSet;
	public static int headCount = 0;
	public static int sideCount = 0;
	public static int valueCount = 0;



	//tbt add 180719 for 測定
	public static long beforedc;
	public static long afterdc2;
	public static long afterMakeSch;
	public static long beforeMakeSQL;
	public static long afterMakeSQL;
	public static long beforeGetFromDB;
	public static long afterGetFromDB;
	public static long afterMakeTree;
	public static long beforeMakeTree;
	public static long afterSchemaToData;
	public static long beforeSchemaToData;

	//tbt end

	// added by yusuke 20161206 for autocorrect
	public static String getworkingDir() {

		String workingDir = new File(GlobalEnv.EXE_FILE_PATH).getAbsolutePath(); // 実行jarファイルの絶対パスを取得

		if (workingDir.contains(":")) {
			workingDir = workingDir.substring(0, workingDir.indexOf(":"));
		}

		return workingDir;
	}


	//added by goto 170612  for --version
	private static boolean isVersion() {
		if(seek("--version") != null || seek("-version") != null || seek("-v") != null)
		return true;
		return false;
	}
	static long lastMod = Long.MIN_VALUE;
	static File choice = null;
	public static boolean versionProcess() {
		if(!isVersion()) return false;

		Log.info("SuperSQL version \""+FrontEnd.VERSION+"\"");

		String f = new FrontEnd().getClass().getResource("FrontEnd.class").toString();
		if(f.contains(":"))	f = f.substring(f.lastIndexOf(":")+1);
		if(f.contains("!"))
		f = f.substring(0, f.indexOf("!"));
		else{
			readFolder(new File(new File(f).getParent()));
			f = choice.toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Log.info("(build "+sdf.format(new File(f).lastModified())+")");
		return true;
	}
	private static void readFolder(File dir) {
		File[] files = dir.listFiles();
		if (files == null)
		return;
		for (File file : files) {
			if (!file.exists())
			continue;
			else if (file.isDirectory())
			readFolder(file);
			else if (file.isFile()){
				if (file.lastModified() > lastMod) {
					choice = file;
					lastMod = file.lastModified();
				}
			}
		}
	}
	//tbt add for centering
//	private static boolean centeringflag = false;
	private static String pos = new String();
//	public static void setCenteringflag() {
//		centeringflag = true;
//	}
//	public static boolean getCenteringflag(){
//		return centeringflag;
//	}

	private static boolean detectcenteringflag = true;
	public static void setDetectcenteringflag() {
		detectcenteringflag = false;
	}
	public static boolean getDetectcenteringflag() {
		return detectcenteringflag;
	}

	public static void setPos(String str){
		pos = str;
	}
	public static String getPos(){
		return pos;
	}

	//tbt end
	public static void getUnityModuleFile(String pass){//module180426
		//読み込んで、変数に入れる
		//メディア名は別の変数に入れといて、stringのarraylist(filelist)に入れる。codegeneratorでarralistの中にメディア名があるかどうかを判断
		//それぞれの変数(arraylist)で読み込んできて
		File directory = new File(pass);
		String filelist1[] = directory.list();//いらないファイル入ってる

		for (int i = 0; i < filelist1.length ; i++){
			if(filelist1[i].contains(".ssql-unity")){
				filelist.add(filelist1[i]);
				String cutstr = filelist1[i].substring(0, filelist1[i].length()-11);
				medialist.add(cutstr);//メディア名arraylist
			}
		}

		for (int i = 0; i < filelist.size(); i++){
			try {//ファイル読み込み
				//Fileクラスに読み込むファイルを指定する
				String pass1 = pass+"/"+filelist.get(i);
				File file = new File(pass1);

				//ファイルが存在するか確認する
				if(file.exists()) {
					char data[] = new char[3000];
					FileReader fr = new FileReader(file);
					int charscount = fr.read(data);
					String str = new String(data,0,charscount);
					multifilecon.add(str);
					fr.close();
				} else {
					System.out.print("ファイルは存在しません");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
