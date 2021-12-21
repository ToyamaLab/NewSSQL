package supersql.codegenerator.Mobile_HTML5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.LocalEnv;
import supersql.codegenerator.Sass;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.parser.Start_Parse;

public class Mobile_HTML5Env extends LocalEnv {

	// added by taji start
	public static boolean defaultCssFlag = true;
	public static int itemNumPerPage = 0;
	public static int itemCount = 0;
	public static int g1RetNum = 0;
	public static int g2RetNum = 0;
	public static int g1PaginationRowNum = 0;
	public static int g1PaginationColumnNum = 0;
	public static int g2PaginationRowNum = 0;
	public static int g2PaginationColumnNum = 0;
	public static StringBuffer xmlCode;
	public int countFile;
	public StringBuffer cssFile = new StringBuffer();
	public String dragDivId = new String();
	public int embedCount = 0;
	public boolean embedFlag = false;
	public String fileName;
	public boolean foreachFlag;
	public int gLevel = 0;
	public ArrayList<String> outTypeList = new ArrayList<>();
	public int cNum = 0;
	public int xmlDepth = 0;
	//added by taji end

	String data;

	String pre_operator;

	public Vector written_classid;

	Connector connector;

	String title = "";		//added by goto 20130411  "title"
	String bg = "";			//added by goto 20130311  "background"
	String bgcolor = "";
	String pos = "";
	//    int maxWidth = 350;		//added by goto 20130512  "max-width"	Default:350
	int portraitWidth = -1;		//added by goto 20130512  "max-width"	Default:-1
	int landscapeWidth = -1;	//added by goto 20130512  "max-width"	Default:-1
	int pcWidth = -1;			//added by goto 20130512  "max-width"	Default:-1

	public static String 	//added by goto 20130515  "search"
	PHP = "<?php\n" +	//初期定義
			//"//XSS対策\n" +
			"function checkHTMLsc($str){\n" +
			"	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
			"}\n" +
			"?>\n";

	Vector<String> not_written_classid= new Vector();

	int total_element = 0;

	private int glevel = 0;

	public String filename = "";

	public static String outfile;

	public String linkUrl;

	public String linkoutfile;

	public String nextbackfile = new String();

	String outdir;

	public int countfile;

	PrintWriter writer;

	public StringBuffer code;

	public static StringBuffer css;

	static int ID_counter=0;	//add oka

	static int ID_old=0;		//add oka

	public String charset=null;					//added by goto 20120715
	static boolean charsetFlg=false;		//added by goto 20120715

	String copyright="";					//added by goto 20130518
	String fff = "";						//20130518  "show query"

	Boolean flickBarFlg = false;				//20130521  "flickbar"

	StringBuffer meta = new StringBuffer();
	StringBuffer div = new StringBuffer();
	StringBuffer titleclass = new StringBuffer();
	public static String jscss = new String();		//js and css file names that using in the Mobile_HTML5
	public StringBuffer cssfile = new StringBuffer();
	StringBuffer jsFile = new StringBuffer();		//added by goto 20130703
	StringBuffer cssjsFile = new StringBuffer();
	String tableborder=new String("1");
	boolean embedflag = false;
	public int embedcount = 0;

	int haveClass = 0;

	//for ajax
	public String ajaxquery = new String();
	public String ajaxcond = new String();
	String ajaxatt = new String();
	public String ajaxtarget = new String();
	public int inEffect = 0;
	public int outEffect = 0;
	public boolean has_dispdiv = false;

	//for drag
	public StringBuffer script = new StringBuffer();
	public int scriptnum = 0;
	public boolean draggable = false;
	public String dragdivid = new String();

	//for panel
	public boolean isPanel = false;

	public StringBuffer header;

	public StringBuffer footer;

	//20130503  Panel
	StringBuffer code1;
	StringBuffer code2;
	StringBuffer panel = new StringBuffer();
	int panelCount = 1;

	boolean foreach_flag;

	public boolean sinvoke_flag = false;

	// added by masato 20151124 for plink'values
	public ArrayList<String> valueArray;

	// added by masato 20151124 for plink'values
	public boolean plinkFlag = false;

	public int link_flag;

	public String linkurl;

	public String plink_glink_onclick = "";		//added by goto 20161109 for plink/glink

	public static int uiGridCount = 0;		//20130314  C1 ui-Grid用
	public static int uiGridCount2 = 0;	//20130314  G1 ui-Grid用

	public static int tabCount = 1;			//20130330  tab用
	public static int maxTab = 15;				//20130330  tab用


	static boolean noAd = false;		//20131106

	// ��?�Ѥ�CSS CLASS����?��?
	private String KeisenMode = "";

	public Mobile_HTML5Env() {
	}

	public String getEncode(){
		if(getOs().contains("Windows")){
			return "Shift_JIS";
		}else{
			return "EUC_JP";
		}
	}

	public String getOs(){
		String osname = System.getProperty("os.name");
		return osname;
	}

	public void getHeader(int headerFlag) {		//[headerFlag] 1:通常、2:Prev/Next
		if(GlobalEnv.getframeworklist() == null){
			if (!Ehtml.isEhtml2()) {
				header.insert(0, "<!DOCTYPE html>\n<HTML>\n<HEAD>\n");
				Log.out("<HTML>\n<head>");
			}

			//added by goto 20130508  "Login&Logout"
			if(Start_Parse.sessionFlag){
				//header.insert(index,"<?php\n	session_start();\n	session_regenerate_id(TRUE);\n?>\n");
				String s = "<?php\n	session_start();\n";
				if(headerFlag == 1)	s += "	session_regenerate_id(TRUE);\n";
				s += "?>\n";
				header.insert(0, s);
			}
			
			if (!Ehtml.isEhtml2()) {
				//Generator
				header.append("<meta name=\"GENERATOR\" content=\" SuperSQL (Generate Mobile_HTML5) \">\n");
			}
		}

		//tk start////////////////////////////////////////////////////
		header.append(meta);

		if(GlobalEnv.isAjax())
		{
			String js = GlobalEnv.getJsDirectory();
			if(js != null)
			{
				if(js.endsWith("/"))
					js = js.substring(0,js.lastIndexOf("/"));

				header.append("<script src=\""+js+"/prototype.js\" type=\"text/javascript\"></script>\n");
				header.append("<script src=\""+js+"/ajax.js\" type=\"text/javascript\"></script>");

			}
			else
			{
				header.append("<script src=\"http://localhost:8080/tab/prototype.js\" type=\"text/javascript\"></script>\n");
				header.append("<script src=\"http://localhost:8080/tab/ajax.js\" type=\"text/javascript\"></script>");
			}

			header.append("<script type=\"text/javascript\" src=\"build/yahoo/yahoo-min.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/event/event-min.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/dom/dom-min.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/dragdrop/dragdrop-min.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"ssqlajax.js\" ></script>\n");
			header.append("<script type=\"text/javascript\" src=\"prototype.js\" ></script>\n");

			//for tab
			header.append("<script type=\"text/javascript\" src=\"build/element/element-beta.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"build/tabview/tabview.js\"></script>\n");

			//for panel
			header.append("<script type=\"text/javascript\" src=\"build/container/container.js\"></script>\n");

			//for animation
			header.append("<script type=\"text/javascript\" src=\"build/animation/animation.js\"></script>\n");

			//for lightbox
			header.append("<script type=\"text/javascript\" src=\"js/prototype.js\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"js/scriptaculous.js?load=effects\"></script>\n");
			header.append("<script type=\"text/javascript\" src=\"js/lightbox.js\"></script>\n");

			//for tab css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/tabview/assets/border_tabs.css\">\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/tabview/assets/tabview.css\">\n");

			//for panel css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/container/assets/container.css\">\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"build/container/assets/container.css\">\n");

			//for lightbox css
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/lightbox.css\"  media=\"screen\">\n");

			//for custom tab
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tabview-core.css\"  media=\"screen\">\n");

			//for custom panel
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/panel.css\"  media=\"screen\">\n");

			header.append("<script type=\"text/javascript\">");
			header.append(script);
			header.append("</script>");
		}

		if(GlobalEnv.getframeworklist() == null){
			//added by goto 20130411  "title"
			if (!title.equals(""))
				header.append("<title>"+title+"</title>\n");

			if (!Ehtml.isEhtml2()) {
				//added by goto 20121217 start
				header.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n");
			}
			
			//	        header.append("<STYLE TYPE=\"text/css\">\n");
			//	        header.append("<!--\n");
			//	        commonCSS();
			//	        header.append(css);
			//	        Log.out(css.toString());
			//	        header.append("\n-->\n</STYLE>\n");

			header.append("<!-- SuperSQL JavaScript & CSS -->\n");
			if(Sass.isBootstrapFlg()){
				//header.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n");
				header.append("<script src=\"jscss/jquery.min.js\"></script>\n");
				header.append("<script src=\"jscss/bootstrap.js\"></script>\n");
				header.append("<script src=\"jscss/forBootstrap/jquery.twbsPagination.js\"></script>\n");
			}
			//20160603 bootstrap
			if(!Sass.isBootstrapFlg()){
				header.append("<link rel=\"stylesheet\" href=\"jscss/jquery-ui.css\"/>\n");
				header.append("<link rel=\"stylesheet\" href=\"jscss/jquery.mobile-1.3.1.min.css\"/>\n");
			}
			header.append("<link rel=\"stylesheet\" href=\"jscss/jqm-datebox.min.css\"/>\n");
			header.append("<link rel=\"stylesheet\" href=\"jscss/jqm-icon-pack-2.0-original.css\"/>\n");
			header.append("<link rel=\"stylesheet\" href=\"jscss/jquery.simplePagination.css\"/>\n");
			//20130206
			//※※　要注意　※※　 jquery.jsより先にjquerymobile.jsをインポートすると、ボタン等の表示がうまくいかなくなる!!
			//            header.append("<script src=\"jscss/jquery-1.7.1.min.js\"></script>\n");
			header.append(Mobile_HTML5Function.updateFormJS);
			if(!Sass.isBootstrapFlg()){
				header.append("<script src=\"jscss/jquery-1.7.1.min.js\"></script>\n");
				header.append("<script src=\"jscss/jquery-ui.min.js\"></script>\n");
				header.append("<script src=\"jscss/supersql.showmore.js\"></script>\n");
				header.append("<script src=\"jscss/jquery.mobile-1.3.1.min.js\"></script>\n");
			}
			header.append("<script src=\"jscss/jqm-datebox.core.min.js\"></script>\n");
			header.append("<script src=\"jscss/jqm-datebox.mode.calbox.min.js\"></script>\n");
			header.append("<script src=\"jscss/jqm-datebox.mode.datebox.min.js\"></script>\n");
			header.append("<script src=\"jscss/jqm-datebox.mode.flipbox.min.js\"></script>\n");
			header.append("<script src=\"jscss/jquery.simplePagination.js\"></script>\n");
			header.append("<script src=\"jscss/jquery.mobile.dynamic.popup.js\"></script>\n");
			header.append("<script src=\"jscss/jquery.iframe-auto-height_re.plugin.js\"></script>\n");
			header.append("<script src=\"jscss/jquery.validate.min.js\"></script>\n");
			header.append("<script src=\"jscss/supersql.prev-next.js\"></script>\n");
			//added by goto 20130512  "max-width"
			header.append(
					"<script type=\"text/javascript\">\n" +
							"<!-- \n" +
							"var portraitWidth = "+portraitWidth+";\n" +
							"var landscapeWidth = "+landscapeWidth+";\n" +
							"var pcWidth = "+pcWidth+";\n" +
							"var bootstrap = "+Sass.isBootstrapFlg()+";\n" +	//20160603 bootstrap
							"-->" +
					"</script>\n");
			header.append("<script src=\"jscss/supersql.js\"></script>\n");
			//added by goto 20121217 end

			header.append(jscss);
			header.append(cssjsFile);	//added by goto 20130703
			header.append(cssfile);
			header.append(jsFile);		//added by goto 20130703

			if(headerFlag == 1){
				String s = "";
				StringBuffer body_css = new StringBuffer();
				StringBuffer uipage_css = new StringBuffer();
				StringBuffer id_css = new StringBuffer();
				
				//added by goto 20130311  "background"
				css.append("\n");
				if (!bg.equals("")){
					if(!Sass.isBootstrapFlg()){
						uipage_css.append("\tbackground: transparent url(../"+bg+");\n");
					}else{
						if(!Ehtml.isEhtml2())
							body_css.append("\tbackground-image: url("+bg+");\n");
						else
							id_css.append("\tbackground: url("+bg+");\n");
					}
				}
				
				if(!bgcolor.equals("")){
					if(!Sass.isBootstrapFlg()){
						uipage_css.append("\tbackground: "+bgcolor+";\n");
					}else{
						s = "\tbackground-color: "+bgcolor+" !important;\n";
						if(!Ehtml.isEhtml2())
							body_css.append(s);
						else
							id_css.append(s);
					}
				}
				
				//20130309  "div"
				if(!Sass.isBootstrapFlg()){
					if(pos.equals(""))	pos = "center";		// Default align
					
					css.append("div{ text-align:"+pos+"; float:center; vertical-align:middle; }\n");
					//20130315	"長い文字が...と省略されるのを防ぐ (*:全てのタイプに適用) "
					css.append("* { white-space: normal; }\n");
					css.append(".error{ color:red; text-align:left; display:block; } -->\n");
					css.append(".ui-grid { overflow: hidden; }\n");
					css.append(".ui-block { margin: 0; padding: 0; float: left; min-height: 1px; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; -ms-box-sizing: border-box; box-sizing: border-box; }\n");
				}else if(Sass.isBootstrapFlg()){
//					if(!pos.equals(""))	pos = "left";		// Default align
					
//					if(GlobalEnv.getCenteringflag()){
					if(!pos.equals("")){
						s = "\ttext-align:"+pos+"; float:center; vertical-align:middle;\n";
						if (Ehtml.isEhtml2() && Ehtml.outType==1) 
							id_css.append(s);
						else
							body_css.append(s);
					}
				}
				
				if(body_css.length() > 0)	css.insert(0,"body{\n"+body_css+"}\n");
				if(uipage_css.length() > 0)	css.append(".ui-page{\n"+uipage_css+"}\n");
				if(id_css.length() > 0)		css.append(Ehtml.getID(1)+"{\n"+id_css+"}\n");
			}

			header.append("<!-- Generated CSS -->\n");
			header.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+ Jscss.getGenerateCssFileName(0) + "\">\n");
//			if (Ehtml.isEhtml2() && Ehtml.outType==1) {
////				header.append("<script src=\"jscss/jquery.scoped.js\"></script>\n");
////				header.append("<script type=\"text/javascript\"> window.onload = function(){ $.scoped(); } </script>\n");
//			} else {
			if (!Ehtml.isEhtml2()) {
				header.append("</HEAD>\n\n");
	
				header.append("<BODY>\n");
			}
			header.append("<!-- SuperSQL Body  Start -->\n");
			//20160603 bootstrap
			if(!Sass.isBootstrapFlg()){
				header.append("<!-- data-role=page start -->\n<div data-role=\"page\" id=\"p-top1\" >\n\n");
			}else if(Sass.isBootstrapFlg()){
				header.append("<!-- container start -->\n<div class=\"container-fluid\">\n\n");
			}

			//added by goto 20130508  "Login&Logout" start
			//ログイン・ログアウト・新規登録
			if(Start_Parse.sessionFlag){
				String s = Start_Parse.sessionString.trim();

				//置換 ( " , "  ->  " ; " )
				boolean dqFlg = false;
				for(int i=0; i<s.length();i++){
					if(!dqFlg){
						if(s.charAt(i)=='"')		dqFlg=true;
					}else if(dqFlg){
						if(s.charAt(i)==',')
							s = s.substring(0,i)+";"+s.substring(i+1);	//置換
						else if(s.charAt(i)=='"')	dqFlg=false;
					}
				}

				String sessionVariable_UniqueName = "ssql_";		//セッション変数に使用するユニークな名前（session()の最後から取ってくる）

				String s_val = "";
				if(s.startsWith("login"))	s_val = s.substring("login".length(),s.indexOf("(")).trim();
				else						s_val = s.substring("session".length(),s.indexOf("(")).trim();
				if(s_val.equals(""))		s_val = "1";
				String DB = GlobalEnv.getdbname();										//DB
				String c1str = "";
				if(!s_val.equals("3"))	c1str = "ID";									//ID string
				else					c1str = "E-mail address";						//E-mail string
				String c1 = "";															//ID column
				String c2str = "Password";												//PW string
				String c2 = "";															//PW column
				String from = "";
				String fromWhere = "";
				String fromWhere_left = "";
				String fromWhere_right = "";
				String expire_time = "";
				String sender_name = "";
				String admin_email = "";
				String c3 = "";
				String[] c3_array = null;
				int c3_array_num = 0;
				if(s_val.equals("1") || s_val.equals("2")){
					//s_val:1 or 2
					String buf = s.substring(0,s.indexOf(","));
					if(buf.contains(";")){
						//ID column and PW column
						c1 = buf.substring(buf.indexOf("\"")+1,buf.indexOf(";")).trim();			//ID column,
						if(c1.contains(":")){
							c1str = c1.substring(0,c1.lastIndexOf(":"));
							c1 = c1.substring(c1.lastIndexOf(":")+1).trim();
						}
						c2 = buf.substring(buf.indexOf(";")+1,buf.lastIndexOf("\"")).trim();		//PW column
						if(c2.contains(":")){
							c2str = c2.substring(0,c2.lastIndexOf(":"));
							c2 = c2.substring(c2.lastIndexOf(":")+1).trim();
						}
					}else{
						//ID column only
						c1 = buf.substring(buf.indexOf("\"")+1).trim();
						c1 = c1.substring(0, c1.indexOf("\"")).trim();			//ID column,
						if(c1.contains(":")){
							c1str = c1.substring(0,c1.lastIndexOf(":"));
							c1 = c1.substring(c1.lastIndexOf(":")+1).trim();
						}
					}

					s = s.substring(s.indexOf(",")+1);
					from = s.substring(0,s.indexOf(",")).trim();		//FROM
					from = from.replace("\"", "").toLowerCase();
					if(from.contains(" where ")){
						fromWhere = ".\" and "+from.substring(from.indexOf(" where ")+6).trim()+"\"";
						if(from.contains("=")){
							fromWhere_left = ", "+from.substring(from.indexOf(" where ")+6,from.indexOf("=")).trim();
							fromWhere_right = ", "+from.substring(from.indexOf("=")+1).trim();
						}
						from = from.substring(0,from.indexOf(" where ")).trim();
						//Log.e(from+"|"+fromWhere+"|"+fromWhere_left+"|"+fromWhere_right);
					}

					s = s.substring(s.indexOf(",")+1);
					c3 = s.substring(0,s.indexOf(",")).replaceAll("\"","").replaceAll(";",",").trim();	//セッション変数として保存する属性
					if(c3.endsWith(","))	c3 = c3.substring(0, c3.lastIndexOf(","));
					//Log.i(c3.length() - c3.replaceAll(",","").length()+1);
					c3_array_num = c3.length() - c3.replaceAll(",","").length()+1;
					c3_array = new String[c3_array_num];
					String c3_buf = c3+",";
					for(int i=0; i<c3_array_num; i++){
						c3_array[i] = c3_buf.substring(0,c3_buf.indexOf(",")).trim();
						c3_buf = c3_buf.substring(c3_buf.indexOf(",")+1);
					}

					sessionVariable_UniqueName += s.substring(s.lastIndexOf(",")+1,s.indexOf(")")).replaceAll("\"","").replaceAll(" ","").trim()+"_";	//セッション変数に使用するユニークな名前
					//Log.i("sessionVariable_UniqueName: "+sessionVariable_UniqueName);
				}else{
					//s_val:3（E-mail登録）
					c1 = s.substring(s.indexOf("(")+1,s.indexOf(",")).trim();			//ID column
					if(c1.contains(":")){
						c1str = c1.substring(0,c1.lastIndexOf(":"));
						c1 = c1.substring(c1.lastIndexOf(":")+1).trim();
					}
					s = s.substring(s.indexOf(",")+1);
					c2 = s.substring(0,s.indexOf(",")).trim();							//PW column
					if(c2.contains(":")){
						c2str = c2.substring(0,c2.lastIndexOf(":"));
						c2 = c2.substring(c2.lastIndexOf(":")+1).trim();
					}
					//TODO: only 1 column
					
					s = s.substring(s.indexOf(",")+1);
					from = s.substring(0,s.indexOf(",")).trim();						//FROM
					from = from.replace("\"", "").toLowerCase();
					if(from.contains(" where ")){
						fromWhere = ".\" and "+from.substring(from.indexOf(" where ")+6).trim()+"\"";
						if(from.contains("=")){
							fromWhere_left = ", "+from.substring(from.indexOf(" where ")+6,from.indexOf("=")).trim();
							fromWhere_right = ", "+from.substring(from.indexOf("=")+1).trim();
						}
						from = from.substring(0,from.indexOf(" where ")).trim();
					}

					s = s.substring(s.indexOf(",")+1);
					//for(int j=0; j<3;j++)	s = s.substring(s.indexOf(",")+1);			//3つ目の,までカット
					//	        		Log.i("s: "+s);
					expire_time = s.substring(0, s.indexOf(",")).replaceAll("\"","").trim();
					if(expire_time.equals(""))	expire_time = "30";
					s = s.substring(s.indexOf(",")+1);
					sender_name = s.substring(0, s.indexOf(",")).replaceAll("\"","").trim();
					s = s.substring(s.indexOf(",")+1);
					admin_email = s.substring(0, s.indexOf(",")).replaceAll("\"","").trim();
					s = s.substring(s.indexOf(",")+1);
					sessionVariable_UniqueName += s.substring(0, s.indexOf(")")).replaceAll("\"","").replaceAll(" ","").trim()+"_";	//セッション変数に使用するユニークな名前

					Log.i("s: "+s+" expire_time:"+expire_time+" sender_name:"+sender_name+" admin_email:"+admin_email+" sessionVariable_UniqueName:"+sessionVariable_UniqueName);
				}
				String DBMS = GlobalEnv.getdbms();										//DBMS
				String HOST = "", USER = "", PASSWD = "";
				if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
					HOST = GlobalEnv.gethost();
					USER = GlobalEnv.getusername();
					PASSWD = GlobalEnv.getpassword();
				}
				//Log.i(c1str+c1+"   "+c2str+c2+"      "+c3+"   from:"+from+"   DB:"+DB+"	DBMS:"+DBMS);

				//php
				if(s_val.equals("1") || s_val.equals("2")){
					header.append("\n" +
							"<!-- \"Login & Logout\" start -->\n" +
							"<?php\n" +
							"//最初にログイン中かどうか判定\n" +
							"if(isset($_SESSION['"+sessionVariable_UniqueName+"id'])){\n" +
							"?>\n" +
							"	<script type=\"text/javascript\">\n" +
							"	$(document).ready(function(){\n" +
							"		$('#login1').attr('checked', 'checked');	//load時\n" +
							"		$('#LOGINpanel1').hide();\n" +
							"	});\n" +
							"	</script>\n" +
							"<?php\n" +
							"	display_html();								//display_html\n" +
							//ユーザ名
							"	echo '<script type=\"text/javascript\">$(\\'#showValues\\').html(\\'<div style=\\\"text-align:right;color:gray;font-size:12;background-color:whitesmoke;\\\">ログイン日時: '.$_SESSION['"+sessionVariable_UniqueName+"logintime'].'<br>ようこそ '.$_SESSION['"+sessionVariable_UniqueName+"id'].'さん</div>\\');</script>';\n" +
							"}else{\n" +
							"?>\n" +
							"	<script type=\"text/javascript\">\n" +
							"	$(document).ready(function(){\n" +
							"		$('#LOGOUTpanel1').hide();\n" +
							"	});\n" +
							"	</script>\n" +
							"<?php\n" +
							"}\n" +
							"?>\n" +
							"\n" +
							"<!-- Login & Registration start -->\n" +
							"<!-- Login Panel start -->\n");
					if(!Sass.isBootstrapFlg()){
						header.append(
								"<br>\n" +
										//"<div id=\"LOGINpanel1\" style=\"background-color:black; width:100%;\" data-role=\"none\">\n" +
										//"<div id=\"LOGINpanel1\" style=\"background-color:whitesmoke; border-radius:20px; border:5px gray solid;\" data-role=\"none\">\n" +
										"<div id=\"LOGINpanel1\" style=\"background-color:whitesmoke; border-radius:10px; border:3px gray solid;\" data-role=\"none\">\n" +
										//"<div style=\"color:lightgray; font-size:30; background-color:black; border-radius:15px 15px 0px 0px;\" id=\"loginTitle1\">Log in</div>\n" +
										"<div style=\"color:lightgray; font-size:30; background-color:black; border-radius:5px 5px 0px 0px;\" id=\"loginTitle1\">Log in</div>\n" +
										"<br>\n" +
										"<form method=\"post\" action=\"\" target=\"login_ifr1\">\n" +
										"<div>\n" +
										"	<div style=\"font-size:20;\">"+c1str+"&nbsp;&nbsp;</div>\n" +
								"	<input type=\"text\" name=\"id\" data-mini=\"true\">\n");
					}else if (Sass.isBootstrapFlg()){
						header.append(
								"<center>\n" +
								"<div id=\"LOGINpanel1\" style=\"width:350px;\">\n" +
								"<div id=\"loginTitle1\"><h2 class=\"form-signin-heading\">Log In</h2></div>" +
								//"<div style=\"color:lightgray; font-size:30; background-color:black; border-radius:15px 15px 0px 0px;\" id=\"loginTitle1\">Log in</div>\n" +
								"<form method=\"post\" action=\"\" target=\"login_ifr1\" class=\"form-signin\">\n" +
								"<input type=\"text\" id=\"id\" name=\"id\" class=\"form-control\" placeholder=\""+c1str+"\" required autofocus>\n");
					}
					if(!s_val.equals("1")){
						if(!Sass.isBootstrapFlg()){
							header.append(
									"	<fieldset data-role=\"controlgroup\" data-type=\"horizontal\" data-mini=\"true\">\n" +
											"		<input type=\"radio\" name=\"choose\" id=\"login1\" value=\"login1\" checked=\"checked\">\n" +
											"	    <label for=\"login1\">I have an account</label>\n" +
											"		<input type=\"radio\" name=\"choose\" id=\"signup1\">\n" +
											"		<label for=\"signup1\"> I am new!</label>\n" +
									"	</fieldset>\n");
						}else if(Sass.isBootstrapFlg()){
							header.append(
									"	<div class=\"radio-inline\">\n" +
											"		<input type=\"radio\" name=\"choose\" id=\"login1\" value=\"login1\" checked=\"checked\">\n" +
											"	    <label for=\"login1\">I have an account</label>\n" +
											"	</div>\n" +
											"	<div class=\"radio-inline\">\n" +
											"		<input type=\"radio\" name=\"choose\" id=\"signup1\">\n" +
											"		<label for=\"signup1\"> I am new!</label>\n" +
									"	</div>\n");
						}
					}

					if(!Sass.isBootstrapFlg()){
						header.append(
								"</div>\n" +
										"<div id=\"login_block\">\n" +
										"	<div style=\"font-size:20;\">"+c2str+":&nbsp;&nbsp;&nbsp;</div>\n" +
										getCol2(c2,"	<input type=\"password\" name=\"password\" id=\"password\" data-mini=\"true\">\n") +
										"	<input type=\"submit\" value=\" Login \" name=\"ssql_login1\" id=\"ssql_login1\" data-mini=\"false\" data-inline=\"false\">\n" +
								"</div>\n");
					}else if(Sass.isBootstrapFlg()){
						header.append(
								"<div id=\"login_block\">\n" +
										getCol2(c2,"	<input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\" placeholder=\""+c2str+"\">\n") +
										"	<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" value=\" Login \" name=\"ssql_login1\" id=\"ssql_login1\">Sign in</button>\n" +
								"</div>\n");
					}
					if(!s_val.equals("1")){
						if(!Sass.isBootstrapFlg()){
							header.append(
									"<div id=\"signup_block\" style=\"display:none\" data-role=\"none\">\n" +
											"	<div style=\"font-size:20;\">Choose "+c2str+":&nbsp;&nbsp;</div>\n" +
											"	<input type=\"password\" name=\"newpassword\" id=\"newpassword\" data-mini=\"true\">\n" +
											"	<div style=\"font-size:20;\">Reinput "+c2str+":&nbsp;&nbsp;</div>\n" +
											getCol2(c2,"	<input type=\"password\" name=\"re_newpassword\" id=\"re_newpassword\" data-mini=\"true\">\n") +
											"	<input type=\"submit\" value=\" Signup \" name=\"ssql_login1\" id=\"ssql_login1\" data-mini=\"false\" data-inline=\"false\">\n" +
									"</div>\n");
						}else if(Sass.isBootstrapFlg()){
							header.append(
									"<div id=\"signup_block\" style=\"display:none\">\n" +
											"	<input type=\"password\" name=\"newpassword\" id=\"newpassword\" class=\"form-control\" placeholder=\"Choose "+c2str+"\">\n" +
											getCol2(c2,"	<input type=\"password\" name=\"re_newpassword\" id=\"re_newpassword\" class=\"form-control\" placeholder=\"Reinput "+ c2str +"\">\n") +
											"	<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" value=\" Signup \" name=\"ssql_login1\" id=\"ssql_login1\">Sign Up</button>\n" +
									"</div>\n");
						}
					}
					header.append(
									"</form>\n" +
									"\n" +
									"<iframe name=\"login_ifr1\" style=\"display:none;\"></iframe>\n" +
									"<p id=\"Login_text1\"  data-role=\"none\"><!-- ここに表示 --></p>\n" +
									"<br>\n" +
									"</div>\n" +
									((Sass.isBootstrapFlg())? "</center>\n" : "") +
									"<!-- Login Panel end -->\n" +
									"\n" +
									"<?php\n" +
									"//Login or Registration\n" +
									"if(isset($_POST['ssql_login1'])){\n" +
									//"	//ユーザ定義\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
									"	$ssql_id = '"+c1+"';\n" +
									getCol2(c2,"	$ssql_pw = '"+c2+"';\n") +
									"	$ssql_c3 = '"+((!c3.equals(""))?(","+c3):("") )+"';\n" +
									"	$ssql_table = \""+from+"\";\n" +
									"\n" +
									"	$id = checkHTMLsc($_POST['id']);\n" +
									getCol2(c2,"	$pw = checkHTMLsc($_POST['password']);\n") +
									"	$newpw = checkHTMLsc($_POST['newpassword']);\n" +
									"	$re_newpw = checkHTMLsc($_POST['re_newpassword']);\n" +
									"\n" +
									"	if("+getCol2(c2,"$pw && ")+"$id){\n" +
							"		//Login\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"		$db = new SQLite3($sqlite3_DB);\n" +
										"		$sql = \"SELECT \".$ssql_id."+getCol2(c2,"\",\".$ssql_pw.\"\".")+"$ssql_c3.\" FROM \".$ssql_table.\" WHERE \".$ssql_id.\"='\".$id.\"'"+getCol2(c2," and \".$ssql_pw.\"='\".$pw.\"'\"")+""+fromWhere+";\n" +
										"	    $result = $db->query($sql);\n" +
										"	    $i = 0;\n" +
								"	    while($res = $result->fetchArray()){\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"		$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"		$sql = \"SELECT \".$ssql_id."+getCol2(c2,"\",\".$ssql_pw.\"\".")+"$ssql_c3.\" FROM \".$ssql_table.\" WHERE \".$ssql_id.\"=$1"+getCol2(c2," and \".$ssql_pw.\"=$2")+"\""+fromWhere+";\n" +
										"		$result = pg_prepare($db, \"ssql_login0\", $sql);\n" +
										"		$result = pg_execute($db, \"ssql_login0\", array($id"+getCol2(c2,",$pw")+"));\n" +
										"	    $i = 0;\n" +
								"	    while($res = pg_fetch_row($result)){\n");
					}
					if(!c3.equals("")){
						for(int i=0; i<c3_array_num; i++){
							//c3_array[i];
							header.append(
									"	          $_SESSION['"+c3_array[i]+"'] = $res["+(i+((!c2.isEmpty())? 2 : 1))+"];\n");
						}
					}
					header.append(
							"	          $i++;\n" +
									"	          if($i==1)	break;\n" +
									"	    }\n" +
									"	    if($i == 0)	p('<font color=#ff0000>Login failed.</font>');	//Login failed.\n" +
									"	    else{\n" +
									"	    	//Login success.\n" +
									"	    	$_SESSION['"+sessionVariable_UniqueName+"id'] = $id;\n" +
									"	    	$_SESSION['"+sessionVariable_UniqueName+"logintime'] = date('Y/m/d(D) H:i:s', time());\n" +		//ユーザ名
									"			echo '<script type=\"text/javascript\">window.parent.$(\\'#Login_text1\\').text(\"\");</script>';\n" +
									"			echo '<script type=\"text/javascript\">window.parent.location.reload(true);</script>';	//reload\n" +
									"	    }\n" +
									"	}else if($newpw && $re_newpw && $id){\n" +
									"		//check (pw==re_pw)?\n" +
									"		if($newpw != $re_newpw){\n" +
									"			p('<font color=#ff0000>Please input the same "+c2str+".</font>');	//PW is not equal\n" +
									"		}else{\n" +

							"			//check & registration\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"			$db = new SQLite3($sqlite3_DB);\n" +
										"			\n" +
										"			//check\n" +
										"			$sql1 = \"SELECT \".$ssql_id.\" FROM \".$ssql_table.\" where \".$ssql_id.\"='\".$id.\"'\""+fromWhere+";\n" +
										"		    $result1 = $db->query($sql1);\n" +
										"		    $i=0;\n" +
								"		    while($res = $result1->fetchArray(SQLITE3_ASSOC)){\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"			$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"			\n" +
										"			//check\n" +
										"			$sql1 = \"SELECT \".$ssql_id.\" FROM \".$ssql_table.\" where \".$ssql_id.\"=$1\""+fromWhere+";\n" +
										"			$result1 = pg_prepare($db, \"ssql_login1\", $sql1);\n" +
										"			$result1 = pg_execute($db, \"ssql_login1\", array($id));\n" +
										"	    	$i = 0;\n" +
								"	    	while($res = pg_fetch_assoc($result1)){\n");
					}
					header.append(
							"	   	       $i++;\n" +
									"	   	       if($i==1)	break;\n" +
									"		    }\n" +
									"		    if($i > 0)	p('<font color=#ff0000>\\\''.$id.'\\\' has been already registered.</font>');	//already registered.\n" +
									"		    else{\n" +
							"	   	       //registration\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"	   	       $sql2 = \"INSERT INTO \".$ssql_table.\" (\".$ssql_id.\", \".$ssql_pw.\""+fromWhere_left+") VALUES ('\".$id.\"','\".$newpw.\"'"+fromWhere_right+")\";\n" +
										"	   	       try{\n" +
								"					$result2 = $db->exec($sql2);\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"	   	       $sql2 = \"INSERT INTO \".$ssql_table.\" (\".$ssql_id.\", \".$ssql_pw.\""+fromWhere_left+") VALUES ($1, $2"+fromWhere_right+")\";\n" +
										"	   	       try{\n" +
										"					$result2 = pg_prepare($db, \"ssql_login2\", $sql2);\n" +
								"					$result2 = pg_execute($db, \"ssql_login2\", array($id,$newpw));\n");
					}
					header.append(
							"					p('<font color=gold>Registration Success!!</font>');\n" +
									"	   	       }catch(Exception $e){\n" +
									"					p('<font color=#ff0000>Registration failed.</font>'.$e->getMessage());\n" +
									"	   	       }\n" +
									"		    }\n" +

	        				"		}\n" +
	        				"	}else{\n" +
	        				"		p('<font color=#ff0000>Please input form.</font>');\n" +
	        				"	}\n" +
	        				((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("	unset($db);\n"):"") +
	        				((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("	pg_close($db);\n"):"") +
	        				"}\n" +
	        				"function p($str){\n" +
	        				"	//親ウインドウのJavaScript関数（テキストエリアへ書き込み）を呼び出す\n" +
	        				"	echo '<script type=\"text/javascript\">window.parent.Login_echo1(\"'.$str.'\");</script>';\n" +
	        				"}\n" +
	        				"?>\n" +
	        				"\n" +
							"<script type=\"text/javascript\">\n");

					if(!s_val.equals("1"))
						if(!Sass.isBootstrapFlg()){
							header.append(
									"$(document).ready(function(){\n" +
											"	//アカウントあり or 新規登録 クリック時の処理\n" +
											"	$('#signup1').click(function(){\n" +
											"		$('#loginTitle1').text('Sign up');\n" +
											"		$('#password').val('');\n" +
											"		$('#login_block').hide();\n" +
											"		$('#signup_block').show();\n" +
											"		Login_echo1(\"\");\n" +
											"	});\n" +
											"	$('#login1').click(function(){\n" +
											"		$('#loginTitle1').text('Log in');\n" +
											"		$('#newpassword').val('');\n" +
											"		$('#re_newpassword').val('');\n" +
											"		$('#signup_block').hide();\n" +
											"		$('#login_block').show();\n" +
											"		Login_echo1(\"\");\n" +
											"	});\n" +
											"});\n" +
									"\n");
						}else if(Sass.isBootstrapFlg()){
							header.append(
									"$(document).ready(function(){\n" +
											"	//アカウントあり or 新規登録 クリック時の処理\n" +
											"	$('#signup1').click(function(){\n" +
											"		$('#loginTitle1').html('<h2>Sign Up</h2>');\n" +
											"		$('#password').val('');\n" +
											"		$('#login_block').hide();\n" +
											"		$('#signup_block').show();\n" +
											"		Login_echo1(\"\");\n" +
											"	});\n" +
											"	$('#login1').click(function(){\n" +
											"		$('#loginTitle1').html('<h2>Log In</h2>');\n" +
											"		$('#newpassword').val('');\n" +
											"		$('#re_newpassword').val('');\n" +
											"		$('#signup_block').hide();\n" +
											"		$('#login_block').show();\n" +
											"		Login_echo1(\"\");\n" +
											"	});\n" +
											"});\n" +
									"\n");
						}

					header.append(
							"//テキストエリアへ書き込み\n" +
									"function Login_echo1(str){\n" +
									"  var textArea = document.getElementById(\"Login_text1\");\n" +
									"  textArea.innerHTML = \"<h2>\" + str + \"</h2>\";\n" +
									"}\n" +
									"</script>\n" +
									"<!-- Login & Registration end -->\n" +
									"\n" +
							"\n");

				}else{	//s_val:3（E-mail登録）
					header.append(
							"<!-- \"Login & Logout\" start -->\n" +
									"<?php\n" +
									"    //定義\n" +
									"    $tokendir = dirname( __FILE__ ).DIRECTORY_SEPARATOR.\"token\".DIRECTORY_SEPARATOR;\n" +
									"    $email = \"\";\n" +
									"    $expire_time = "+expire_time+"*60;						//分\n" +
									"    $sender_name = \""+sender_name+"\";                    //送信者名\n" +
									"    $admin_email = \""+admin_email+"\";	//本当は、管理者メールアドレスはconfig.phpのMAILTO_MASTERを利用した方が良い\n" +
									"    \n" +
									"    $success_page = \"http://\".$_SERVER[\"HTTP_HOST\"].$_SERVER[\"REQUEST_URI\"];	//ログインページ\n" +
									"    $pos = strpos($success_page, '?');		//?以下をカット\n" +
									"	if($pos !== false)	$success_page = substr($success_page, 0, $pos); \n" +
									"    $resistration_page = $success_page;											//もう一度こちらから\n" +
									"    \n" +
									"    mb_language(\"ja\");				//カレントの言語を日本語に設定する\n" +
									"    mb_internal_encoding(\"UTF-8\");	//内部文字エンコードを設定する\n" +
									"?>\n" +
									"\n" +
									"<?php\n" +
									"//最初にログイン中かどうか判定\n" +
									"if(isset($_SESSION['"+sessionVariable_UniqueName+"id'])){\n" +
									"?>\n" +
									"	<script type=\"text/javascript\">\n" +
									"	$(document).ready(function(){\n" +
									"		$('#login1').attr('checked', 'checked');	//load時\n" +
									"		$('#LOGINpanel1').hide();\n" +
									"	});\n" +
									"	</script>\n" +
									"<?php\n" +
									"	display_html();								//display_html\n" +
									"}else{\n" +
									"?>\n" +
									"	<script type=\"text/javascript\">\n" +
									"	$(document).ready(function(){\n" +
									"		$('#LOGOUTpanel1').hide();\n" +
									"	});\n" +
									"	</script>\n" +
									"<?php\n" +
									"}\n" +
									"?>\n" +
									"\n" +
									"<div id=\"message\"  data-role=\"none\"><!-- ここに表示 --></div>\n" +
									"\n" +
									"<script type=\"text/javascript\">\n" +
									"$(document).ready(function(){\n" +
									"<?php\n" +
									"    if(isset($_GET[\"key\"])){\n" +
									"        if(delete_old_token($_GET[\"key\"])){\n" +
									"            global $email,$success_page;\n" +
									"           \n" +
									"            if(mail_to_success($email)){//ユーザーにメール\n" +
									"            	mail_to_master($email); //管理者にメール\n" +
									"?>\n" +
									"            	$(\"#message\").html(\"<br><div>登録完了メールを送信しました。<a href=\\\"<?php echo $success_page; ?>\\\" target=\\\"_self\\\"><br>ログインページ</a>へ</div>\");\n" +
									"            	$(\"#LOGINpanel1\").hide();\n" +
									"<?php\n" +
									"			}\n" +
									"        }else{\n" +
									"            //document.write(\"もう一度初めからやり直してください。\");\n" +
									"?>\n" +
									"            $(\"#message\").html(\"<br><div>もう一度<a href=\\\"<?php echo $resistration_page; ?>\\\" target=\\\"_self\\\">こちら</a>からやり直してください。</div>\");\n" +
									"            $(\"#LOGINpanel1\").hide();\n" +
									"<?php\n" +
									"        }\n" +
									"    }\n" +
									"?>\n" +
									"});\n" +
									"</script>\n" +
									"\n" +
									"<!-- Login & Registration start -->\n" +
									"<!-- Login Panel start -->\n" +
									"<div id=\"LOGINpanel1\">\n" +
									"	<br>\n" +
									"	<div style=\"text-align:right; margin-right:20px; font-size:18px;\">\n" +
									"		<span id=\"login1\"><span style=\"font-weight:bold;\">ログイン</span></span>\n" +
									"		<span id=\"signup1\"><a href=\"\">新規登録</a></span>\n" +
									"		<!--<fieldset data-role=\"controlgroup\" data-type=\"horizontal\" data-mini=\"true\" data-role=\"none\">\n" +
									"			<input type=\"radio\" name=\"choose\" id=\"login1\" value=\"login1\" checked=\"checked\">\n" +
									"		    <label for=\"login1\">I have an account</label>\n" +
									"			<input type=\"radio\" name=\"choose\" id=\"signup1\">\n" +
									"			<label for=\"signup1\"> I am new!</label>\n" +
									"		</fieldset>-->\n" +
									"	</div>\n" +
									"	<div style=\"background-color:whitesmoke; width:97%;  border-radius:20px; border:5px gray solid;\" data-role=\"none\">\n" +
									"		<div style=\"color:lightgray; font-size:30; background-color:black; border-radius:15px 15px 0px 0px;\" id=\"loginTitle1\">ログイン</div>\n" +
									"		<br><br>\n" +
									"		\n" +
									"		<form method=\"post\" action=\"\" target=\"login_ifr1\">\n" +
									"			<div id=\"login_block\">\n" +
									"				<div style=\"font-size:20;\">"+c1str+"&nbsp;&nbsp;</div>\n" +
									"				<input type=\"text\" name=\"id\" data-mini=\"true\">	\n" +
									"				<div style=\"font-size:20;\">"+c2str+":&nbsp;&nbsp;&nbsp;</div>\n" +
									"				<input type=\"password\" name=\"password\" id=\"password\" data-mini=\"true\">\n" +
									"				<input type=\"submit\" value=\" Login \" name=\"ssql_login1\" id=\"ssql_login1\" data-mini=\"false\" data-inline=\"false\">\n" +
									"			</div>\n" +
									"			<div id=\"signup_block\" style=\"display:none\" data-role=\"none\">\n" +
									"			    <div style=\"font-size:20;\">"+c1str+"&nbsp;&nbsp;</div>\n" +
									"			    <input type=\"text\" name=\"mail1\" id=\"mail1\" data-mini=\"true\">\n" +
									"			    <input type=\"submit\" value=\" Send \" name=\"mail\" id=\"mail\" data-mini=\"false\" data-inline=\"false\">\n" +
									"			</div>\n" +
									"		</form>\n" +
									"		\n" +
									"		<iframe name=\"login_ifr1\" style=\"display:none;\"></iframe>\n" +
									"		<div id=\"Login_text1\"  data-role=\"none\"><!-- ここに表示 --></div>\n" +
									"		<br>\n" +
									"	</div>\n" +
									"</div>\n" +
									"\n" +
									"<form method=\"post\" action=\"\" target=\"reset_ifr1\" id=\"RESETpanel1\" name=\"RESETpanel1\">\n" +
									"<input type=\"hidden\" name=\"ssql_reset1\" value=\"\">\n" +
									"</form>\n" +
									"<iframe name=\"reset_ifr1\" style=\"display:none;\"></iframe>\n" +
									"<!-- Login Panel end -->\n" +
									"\n" +
									"<?php\n" +
									"	//ログイン処理\n" +
									"	if(isset($_POST['ssql_login1'])){\n" +
									//"		//ユーザ定義\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("    $sqlite3_DB = '"+DB+"';\n"):"") +
									"		$ssql_id = '"+c1+"';\n" +
									"		$ssql_pw = '"+c2+"';\n" +
									"		$ssql_table = \""+from+"\";\n" +
									"	\n" +
									"		$id = checkHTMLsc($_POST['id']);\n" +
									"		$pw = checkHTMLsc($_POST['password']);\n" +
									"	\n" +
							"		if($pw && $id){\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"	   		$db = new SQLite3($sqlite3_DB);\n" +
										"			$sql = \"SELECT \".$ssql_id.\",\".$ssql_pw.\" FROM \".$ssql_table.\" where \".$ssql_id.\"='\".$id.\"' and \".$ssql_pw.\"='\".$pw.\"'\""+fromWhere+";\n" +
										"		    $result = $db->query($sql);\n" +
										"		    $i = 0;\n" +
								"		    while($res = $result->fetchArray(SQLITE3_ASSOC)){\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"			$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"			$sql = \"SELECT \".$ssql_id.\",\".$ssql_pw.\" FROM \".$ssql_table.\" where \".$ssql_id.\"=$1 and \".$ssql_pw.\"=$2\""+fromWhere+";\n" +
										"			$result = pg_prepare($db, \"ssql_email_login0\", $sql);\n" +
										"			$result = pg_execute($db, \"ssql_email_login0\", array($id,$pw));\n" +
										"	    	$i = 0;\n" +
								"	    	while($res = pg_fetch_assoc($result)){\n");
					}
					header.append(
							"		          $i++;\n" +
									"		          if($i==1)	break;\n" +
									"		    }\n" +
									"		    if($i == 0)	p('<font color=#ff0000>Login failed.</font>');	//Login failed.\n" +
									"		    else{\n" +
									"		    	//Login success.\n" +
									"		    	$_SESSION['"+sessionVariable_UniqueName+"id'] = $id;\n" +
									"				echo '<script type=\"text/javascript\">window.parent.$(\\'#Login_text1\\').text(\"\");</script>';\n" +
									"				echo '<script type=\"text/javascript\">window.parent.location.reload(true);</script>';	//reload\n" +
									"		    }\n" +
									"		}else{\n" +
									"			p('<font color=#ff0000>Please input form.</font>');\n" +
									"		}\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("		unset($db);"):"") +
									((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("		pg_close($db);\n"):"") +
									"	}\n" +
									"\n" +
									"	//メール送信 ＆ 新規登録\n" +
									"	if(isset($_POST['mail']) || isset($_POST['mail1'])){\n" +
									"	    if(!isset($_POST[\"mail1\"])){\n" +
									"	        p(\"<font color=#ff0000>メールアドレスを入力してください。</font>\");\n" +
									"	    }elseif(mb_strlen($_POST[\"mail1\"])> 0 && !preg_match(\"/^([a-z0-9_]|\\-|\\.|\\+)+@(([a-z0-9_]|\\-)+\\.)+[a-z]{2,6}$/i\",$_POST[\"mail1\"])){\n" +
									"	        p(\"<font color=#ff0000>メールアドレスの書式に<br>誤りがあります。</font>\");\n" +
									"	    }else{\n" +
									"	    	//TODO: トークンが存在しているかどうかチェック\n" +
									"	    	//hasToken($_POST[\"mail1\"]);\n" +
									"	    	\n" +
									//"			//ユーザ定義\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("			$sqlite3_DB = '"+DB+"';\n"):"") +
									"			$ssql_id = '"+c1+"';\n" +
									"			$ssql_table = \""+from+"\";\n" +
									"			\n" +
									"			$email = $_POST[\"mail1\"];\n" +
									"	        \n" +
									"	        //DB登録未登録のメールアドレスかどうかの判定\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("	        if( isResistered($email, $sqlite3_DB, $ssql_id, $ssql_table) ){\n"):"") +
									((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("	        if( isResistered($email, \"\", $ssql_id, $ssql_table) ){\n"):"") +
									"	       		//登録済み\n" +
									"		    	p('<font color=#ff0000>\\''.$email.'\\' は<br>既に登録済です。</font><br><span style=\\\"font-size:11px;\\\">パスワードを忘れた場合は、<a href=\\\"\\\" onclick=\\\"window.parent.document.RESETpanel1.ssql_reset1.value=\\''.$email.'\\';window.parent.document.RESETpanel1.submit();return false;\\\">こちら</a>をクリックしてください。</span>');\n" +
									"	        }else{\n" +
									"	        	//未登録　→　確認メール送信\n" +
									"	        	p(\"確認メールを送信しました。\".\"<br>\".'<span style=\\\"font-size:15px;\\\">E-mail to: '.$email.'</span>');\n" +
									"	        	mail_to_token($email);\n" +
									"	        }\n" +
									"	    }\n" +
									"	}\n" +
									"\n" +
									"	//パスワードをランダムパスワードで初期化。ユーザーへメール送信\n" +
									"	if(isset($_POST['ssql_reset1'])){ \n" +
									"		$mail = $_POST['ssql_reset1'];\n" +
									"	    echo '<script type=\"text/javascript\">window.parent.window.parent.Login_echo1(\"'.$mail.' 登録済みパスワードを初期化します。\");</script>';\n" +
									"		password_reset_and_send_mail($mail);\n" +
									"		//echo '<script type=\"text/javascript\">window.parent.window.parent.Login_echo1(\"テスト2\");</script>';\n" +
									"	}\n" +
									"	\n" +
									"	function password_reset_and_send_mail($mail){\n" +
									"		//8桁ランダムパスワードの発行\n" +
									"		$r_password = getRandomPassword(8);\n" +
									"		\n" +
									//"		//ユーザ定義\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("		$sqlite3_DB = '"+DB+"';\n"):"") +
									"		$ssql_id = '"+c1+"';\n" +
									"		$ssql_pw = '"+c2+"';\n" +
									"		$ssql_table = \""+from+"\";\n" +
									"\n" +
							"		//$mail列のパスワードを「ランダムpassword」で初期化\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"		$reset_sql1 = \"UPDATE \".$ssql_table.\" SET \".$ssql_pw.\"='\".$r_password.\"' where \".$ssql_id.\"='\".$mail.\"'\""+fromWhere+";\n" +
										"		$db = new SQLite3($sqlite3_DB);\n" +
										"		$result1 = $db->exec($reset_sql1);\n" +
										"		if ($result1) {\n" +
								"			$changed_num = $db->changes();		//変更された行の数: $db->changes()\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"		$reset_sql1 = \"UPDATE \".$ssql_table.\" SET \".$ssql_pw.\"=$1 where \".$ssql_id.\"=$2\""+fromWhere+";\n" +
										"		$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"		$result1 = pg_prepare($db, \"ssql_email_login1\", $reset_sql1);\n" +
										"		$result1 = pg_execute($db, \"ssql_email_login1\", array($r_password, $mail));\n" +
										"		if ($result1) {\n" +
								"			$changed_num = pg_affected_rows($result1);		//変更されたタプル数\n");
					}
					header.append(
							"			\n" +
									"			if($changed_num > 0){\n" +
									"				//リセット成功　→　リセット済みパスワードの送信\n" +
									"				echo '<script type=\"text/javascript\">window.parent.window.parent.Login_echo1(\"パスワードを初期化して<br>ご登録先へ送信しました。\");</script>';\n" +
									"				mail_to_reset($mail,$r_password);\n" +
									"			}else{\n" +
									"				//リセット失敗\n" +
									"				echo '<script type=\"text/javascript\">window.parent.window.parent.Login_echo1(\"<span style=\\\"color:red;\\\">初期化に失敗しました。しばらく経ってからもう一度お試しください。</span>\");</script>';\n" +
									"			}\n" +
									"		}\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("		unset($db);\n"):"") +
									((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("		pg_close($db);\n"):"") +
									"	}\n" +
									"\n" +
									"	//DB登録未登録かどうかの判定\n" +
									"    function isResistered($check_string, $database, $c1, $table) {\n" +
							"		//DB登録未登録のメールアドレスかどうかチェック\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"		$db = new SQLite3($database);\n" +
										"		$sql = \"SELECT \".$c1.\" FROM \".$table.\" where \".$c1.\"='\".checkHTMLsc($check_string).\"'\";\n" +
										"	    $result = $db->query($sql);\n" +
										"	    $i = 0;\n" +
								"	    while($res = $result->fetchArray(SQLITE3_ASSOC)){\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"		$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"		$sql = \"SELECT \".$c1.\" FROM \".$table.\" where \".$c1.\"=$1\";\n" +
										"		$result = pg_prepare($db, \"ssql_email_login3\", $sql);\n" +
										"		$result = pg_execute($db, \"ssql_email_login3\", array(checkHTMLsc($check_string)));\n" +
										"	   	$i = 0;\n" +
								"	   	while($res = pg_fetch_assoc($result)){\n");
					}
					header.append(
							"	          $i++;\n" +
									"	          if($i==1)	break;\n" +
									"	    }\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("		unset($db);\n"):"") +
									((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("		pg_close($db);\n"):"") +
									"	    if($i > 0)	return true;	//登録済\n" +
									"	    else		return false;  	//未登録\n" +
									"    }\n" +
									"    \n" +
									"    function mail_to_token($address) {\n" +
									"        global $tokendir, $sender_name, $expire_time;\n" +
									"      \n" +
									"        $limit = (time()+$expire_time);\n" +
									"        $token= rand(0,100).uniqid();		//トークン\n" +
									"        touch($tokendir.$token.\".log\");		//トークンファイル作成\n" +
									"    \n" +
									"        $url = $_SERVER[\"HTTP_REFERER\"].\"?key=\".$token;\n" +
									"        file_put_contents($tokendir.$token.\".log\", $address, LOCK_EX);	//期限保存\n" +
									"        delete_old_token($tokendir);									//古いトークンの削除\n" +
									"        $message=\"登録を完了するには、以下のアドレスを開いてください。\n\".($expire_time/60).\"分以内にアクセスが無かった場合は無効となります。\n\";\n" +
									"        $message.= $url.\"\n\n\";\n" +
									"\n" +
									"        if($sender_name != \"\")	my_send_mail($address,'['.$sender_name.'] 登録確認',$message);\n" +
									"        else					my_send_mail($address,'登録確認',$message);\n" +
									"    }\n" +
									"    \n" +
									"    function mail_to_success($mailto) {\n" +
									"        global $success_page, $resistration_page, $sender_name;\n" +
									"       \n" +
									"        //「メールアドレス」と「ランダムpassword」をDBへ登録\n" +
									"        $random_password = register_email_and_random_password($mailto);\n" +
									"      	if($random_password != \"\"){\n" +
									"	        $message=\"登録が完了しました。\n\nログイン仮パスワード：\".$random_password.\" (※ログイン後に必ず変更してください）\n\n\";\n" +
									"	        $message.=\"こちらからログインできます: \".$success_page.\"\n\n\";\n" +
									"	        if($sender_name != \"\")	my_send_mail($mailto, '['.$sender_name.'] ご登録ありがとうございます', $message);\n" +
									"	        else					my_send_mail($mailto, 'ご登録ありがとうございます', $message);\n" +
									"	        return true;\n" +
									"	    }else{\n" +
									"	    	p('<font color=#ff0000>\\''.$mailto.'\\'の登録に失敗しました。既に登録済みの可能性があります。<br>もう一度<a href=\"'.$resistration_page.'\" target=\"_self\">こちら</a>からやり直してください。</font>');	//登録失敗（レアケース）\n" +
									"	    	return false;\n" +
									"	    }\n" +
									"    }\n" +
									"    \n" +
									"    //「メールアドレス」と「ランダムpassword」をDBへ登録する\n" +
									"    function register_email_and_random_password($mail) {\n" +
									"		//8桁ランダムパスワードの発行\n" +
									"		$r_password = getRandomPassword(8);\n" +
									"		\n" +
									//"		//ユーザ定義\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))? ("		$sqlite3_DB = '"+DB+"';\n"):"") +
									"		$ssql_id = '"+c1+"';\n" +
									"		$ssql_pw = '"+c2+"';\n" +
									"		$ssql_table = \""+from+"\";\n" +
									"		\n" +
									"		//「メールアドレス」と「ランダムpassword」をDBへ登録\n" +
							"		//check & registration\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"		$db = new SQLite3($sqlite3_DB);\n" +
										"		//一応、再check\n" +
										"		$sql1 = \"SELECT \".$ssql_id.\" FROM \".$ssql_table.\" where \".$ssql_id.\"='\".$mail.\"'\""+fromWhere+";\n" +
										"	    $result1 = $db->query($sql1);\n" +
										"	    $i=0;\n" +
								"	    while($res = $result1->fetchArray(SQLITE3_ASSOC)){\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"		$db = pg_connect (\"host="+HOST+" dbname="+DB+" user="+USER+""+(!PASSWD.isEmpty()? (" password="+PASSWD):"")+"\");\n" +
										"		//一応、再check\n" +
										"		$sql1 = \"SELECT \".$ssql_id.\" FROM \".$ssql_table.\" where \".$ssql_id.\"=$1\""+fromWhere+";\n" +
										"		$result1 = pg_prepare($db, \"ssql_email_login4\", $sql1);\n" +
										"		$result1 = pg_execute($db, \"ssql_email_login4\", array($mail));\n" +
										"	    $i = 0;\n" +
								"	    while($res = pg_fetch_assoc($result1)){\n");
					}
					header.append(
							"   	       $i++;\n" +
									"   	       if($i==1)	break;\n" +
									"	    }\n" +
									"	    if($i > 0){\n" +
									((DBMS.equals("sqlite") || DBMS.equals("sqlite3"))?      ("	    	unset($db);\n"):"") +
									((DBMS.equals("postgresql") || DBMS.equals("postgres"))? ("	    	pg_close($db);\n"):"") +
									"	     	return \"\";		//既に登録済み（レアケース）\n" +
									"	    }else{\n" +
							"   	       //「メールアドレス」と「ランダムpassword」をDBへ登録\n");
					if(DBMS.equals("sqlite") || DBMS.equals("sqlite3")){
						header.append(
								"   	       $sql2 = \"INSERT INTO \".$ssql_table.\" (\".$ssql_id.\", \".$ssql_pw.\""+fromWhere_left+") VALUES ('\".$mail.\"','\".$r_password.\"'"+fromWhere_right+")\";\n" +
										"   	       try{\n" +
										"				$result2 = $db->exec($sql2);\n" +
										"				unset($db);\n" +
										"				return $r_password;	//登録成功\n" +
										"   	       }catch(Exception $e){\n" +
								"   	       		unset($db);\n");
					} else if(DBMS.equals("postgresql") || DBMS.equals("postgres")){
						header.append(
								"   	       $sql2 = \"INSERT INTO \".$ssql_table.\" (\".$ssql_id.\", \".$ssql_pw.\""+fromWhere_left+") VALUES ($1, $2"+fromWhere_right+")\";\n" +
										"   	       try{\n" +
										"				$result2 = pg_prepare($db, \"ssql_email_login5\", $sql2);\n" +
										"				$result2 = pg_execute($db, \"ssql_email_login5\", array($mail,$r_password));\n" +
										"				pg_close($db);\n" +
										"				return $r_password;	//登録成功\n" +
										"   	       }catch(Exception $e){\n" +
								"   	       		pg_close($db);\n");
					}
					header.append(
							"   	       		return \"\";			//登録失敗\n" +
									"   	       }\n" +
									"	    }\n" +
									"	}\n" +
									"	function getRandomPassword($length){\n" +
									"    	return substr(str_shuffle('1234567890abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 0, $length);\n" +
									"	}\n" +
									"\n" +
									"    function mail_to_master($mail) {\n" +
									"        global $admin_email, $sender_name;\n" +
									"        \n" +
									"        if($admin_email != \"\"){\n" +
									"	        $message =\"新しく登録されたユーザーの情報は次の通りです。\n\";\n" +
									"	        $message.=\"────────────────────────────────────────────────\n\";\n" +
									"	        $message.= $mail.\"\n\n\";\n" +
									"	        $message.=\"DATE: \".date(\"Y/m/d - H:i:s\").\"\n\";\n" +
									"	        $message.=\"IP: \".$_SERVER['REMOTE_ADDR'].\"\n\";\n" +
									"	        $message.=\"HOST: \".@gethostbyaddr($_SERVER['REMOTE_ADDR']).\"\n\";\n" +
									"	        $message.=\"USER AGENT: \".$_SERVER['HTTP_USER_AGENT'].\"\n\";\n" +
									"	        $message.=\"────────────────────────────────────────────────\n\";\n" +
									"	      \n" +
									"	        //my_send_mail(MAILTO_MASTER, 'ユーザー登録通知', $message);\n" +
									"	        if($sender_name != \"\")	my_send_mail($admin_email, '['.$sender_name.' admin] ユーザー登録通知', $message);\n" +
									"	        else					my_send_mail($admin_email, '[admin] ユーザー登録通知', $message);\n" +
									"        }\n" +
									"    }\n" +
									"    \n" +
									"    function mail_to_reset($mail,$random_password) {\n" +
									"        global $sender_name,$success_page;\n" +
									"       \n" +
									"       	$message=\"ご登録パスワードを初期化しました。\n\nログイン仮パスワード：\".$random_password.\" (※ログイン後に必ず変更してください）\n\n\";\n" +
									"	    $message.=\"こちらからログインできます: \".$success_page.\"\n\n\";\n" +
									"      \n" +
									"        if($sender_name != \"\")	my_send_mail($mail, '['.$sender_name.'] パスワード初期化通知', $message);\n" +
									"        else					my_send_mail($mail, 'パスワード初期化通知', $message);\n" +
									"    }\n" +
									"    \n" +
									"    function delete_old_token($token = NULL) {\n" +
									"        global $tokendir,$email,$expire_time;\n" +
									"        if (is_dir($tokendir)) {\n" +
									"            if ($dh = opendir($tokendir)) {\n" +
									"                while (($file = readdir($dh)) !== false) {\n" +
									"                    if(is_file($tokendir.$file) && is_null($token)){\n" +
									"                        $log = file_get_contents($tokendir.$file);\n" +
									"                        list($data,$mail) = preg_split(\"<>\",$log);\n" +
									"                        $email = $mail;\n" +
									"                        if(time()> $data) @unlink($tokendir.$file);\n" +
									"                      \n" +
									"                    }else if(basename($file,\".log\")==$token && !is_null($token)){\n" +
									"                        if(time() <(filemtime($tokendir.$token.\".log\")+$expire_time) ){\n" +
									"                            $log = file_get_contents($tokendir.$token.\".log\");\n" +
									"                            //p(\"log:\".$log);\n" +
									"                           \n" +
									"                            $mail = trim($log);\n" +
									"                            $email = $mail;\n" +
									"                      \n" +
									"                            @unlink($tokendir.$token.\".log\");\n" +
									"                            return true;\n" +
									"                        }else{\n" +
									"                            @unlink($tokendir.$token.\".log\");\n" +
									"                            return false;\n" +
									"                        }\n" +
									"                    }\n" +
									"                }\n" +
									"                closedir($dh);\n" +
									"            }\n" +
									"        }\n" +
									"    }\n" +
									"    \n" +
									"    function my_send_mail($mailto, $subject, $message) {\n" +
									"        global $sender_name;\n" +
									"       \n" +
									"        $header =\"From: \".$sender_name.\" <info@example.com>\n\";\n" +
									"        $mmm = mb_send_mail($mailto, $subject, $message, $header);\n" +
									"    }\n" +
									"\n" +
									"	function p($str){\n" +
									"	    //親ウインドウのJavaScript関数（テキストエリアへ書き込み）を呼び出す\n" +
									"	    echo '<script type=\"text/javascript\">window.parent.Login_echo1(\"'.$str.'\");</script>';\n" +
									"	}\n" +
									"?>\n" +
									"\n" +
									"<script type=\"text/javascript\">\n" +
									"$(document).ready(function(){\n" +
									"	//アカウントあり or 新規登録 クリック時の処理\n" +
									"	$('#signup1').click(function(){\n" +
									"		$('#login1').html('<a href=\"\" style=\"color:#0000CC;\">ログイン</a>');\n" +
									"		$('#signup1').html('<span style=\"font-weight:bold;\">新規登録</span>');\n" +
									"		$('#loginTitle1').text('新規登録');\n" +
									"		$('#password').val('');\n" +
									"		$('#login_block').hide();\n" +
									"		$('#signup_block').show();\n" +
									"		Login_echo1(\"\");\n" +
									"	});\n" +
									"	$('#login1').click(function(){\n" +
									"		$('#login1').html('<span style=\"font-weight:bold;\">ログイン</span>');\n" +
									"		$('#signup1').html('<a href=\"\" style=\"color:#0000CC;\">新規登録</a>');\n" +
									"		$('#loginTitle1').text('ログイン');\n" +
									"		$('#mail1').val('');\n" +
									"		$('#signup_block').hide();\n" +
									"		$('#login_block').show();\n" +
									"		Login_echo1(\"\");\n" +
									"	});\n" +
									"});\n" +
									"\n" +
									"//テキストエリアへ書き込み\n" +
									"function Login_echo1(str){\n" +
									"  var textArea = document.getElementById(\"Login_text1\");\n" +
									"  textArea.innerHTML = \"<h2>\" + str + \"</h2>\";\n" +
									"}\n" +
									"</script>\n" +
									"<!-- Login & Registration end -->\n" +
							"\n");
				}//End of s_val:3（E-mail Login）


				//ログアウトボタンの付加

				//通常時のみ（Prev/Nextでは行わない）
				if(headerFlag==1){
					if(!Sass.isBootstrapFlg()){
						header.append(
								"<!-- Logout start -->\n" +
										"<form method=\"post\" action=\"\" target=\"logout_ifr1\" id=\"LOGOUTpanel1\" name=\"LOGOUTpanel1\">\n" +
										"<input type=\"submit\" value=\" Logout \" name=\"ssql_logout1\" data-mini=\"false\" data-inline=\"false\">\n" +
										"<input type=\"hidden\" value=\" Logout \" name=\"ssql_logout1\">\n" +
										"</form>\n" +
										"<iframe name=\"logout_ifr1\" style=\"display:none;\"></iframe>\n" +
								"\n");
					}else if(Sass.isBootstrapFlg()){
						header.append(
								"<!-- Logout start -->\n" +
										"<center>\n" +
										"<form method=\"post\" action=\"\" target=\"logout_ifr1\" id=\"LOGOUTpanel1\" name=\"LOGOUTpanel1\"  style=\"width:350px;\">\n" +
										"<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" value=\" Logout \" name=\"ssql_logout1\">Logout</button>\n" +
										"<input type=\"hidden\" value=\" Logout \" name=\"ssql_logout1\">\n" +
										"</form>\n" +
										"<iframe name=\"logout_ifr1\" style=\"display:none;\"></iframe>\n" +
										"</center>\n" +					
								"\n");
					}
				}
				//通常時のみ（Prev/Nextでは行わない）&& ( header()があるとき || button("logout")があるとき )
				if(headerFlag==1 && ( !Mobile_HTML5Function.headerString.equals("") || Mobile_HTML5Function.logoutButtonFlg ))
					header.append(
							"<script type=\"text/javascript\">\n" +
									"$(document).ready(function(){\n" +
									"	$('#LOGOUTpanel1').hide();\n" +
									"});\n" +
									"</script>\n"
							);
				//通常時のみ（Prev/Nextでは行わない）
				if(headerFlag==1)
					header.append("<?php\n" +
							"if(isset($_POST['ssql_logout1'])){\n" +
							"	//ログアウト処理\n" +
							"	//セッション変数を全て解除\n" +
							"	$_SESSION = array();\n" +
							"	//セッションを切断するにはセッションクッキーも削除\n" +
							"	//Note: セッション情報だけでなくセッションを破壊\n" +
							"	if (isset($_COOKIE[session_name()])) {\n" +
							"    	setcookie(session_name(), '', time()-42000, '/');\n" +
							"	}\n" +
							"	session_destroy();\n" +
							"	echo '<script type=\"text/javascript\">window.parent.location.reload(true);</script>';	//reload\n" +
							"}\n" +
							"?>\n" +
							"<!-- Logout end -->\n");

				header.append(
						"<!-- \"Login & Logout\" end -->\n" +
								"\n" +
								"\n" +
								"<?php\n" +
								"//<!-- display_html start -->\n" +
								"function display_html(){\n" +
								"	if(isset($_SESSION['"+sessionVariable_UniqueName+"id'])){\n" +
								"		echo <<<EOF\n" +
						"		\n");
			}
			//added by goto 20130508  "Login&Logout" end

			if(headerFlag==1){		//通常時のみ（Prev/Nextでは行わない）
				if(!Mobile_HTML5Function.headerString.equals("")){		//data-role="header"
					header.append("\n<!-- data-role=header start -->\n"
							+Mobile_HTML5Function.headerString+"<!-- data-role=header end -->\n\n\n");
				}
			}
			//data-role="content"
			//20160603 bootstrap
			if(!Sass.isBootstrapFlg()){
				header.append("<!-- data-role=content start -->\n<div data-role=\"content\" style=\"padding:0\" id=\"content1\">\n");
			}
			header.append("<div id=\""+((!Ehtml.isEhtml2())? "ssql_body_contents" : Ehtml.getID(0))+"\"  class=\"\">\n");	//added by goto 20161019 for new foreach
			if(Start_Parse.sessionFlag){
				header.append("\n<div id=\"showValues\"><!-- ユーザ名等を表示 --></div>\n");	//ユーザ名
			}

			Log.out("--></style></head>");
			Log.out("<body>");
		}

		if(Connector.loginFlag ){
			header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/servlet/supersql.form.Session\" method = \"post\" name=\"theForm\">\n");
			//header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/supersql.form.Session\" method = \"post\" name=\"theForm\">\n");
			header.append("<input type=\"hidden\" name=\"tableinfo\" value=\"" + Start_Parse.get_from_info_st() + "\" >");
			header.append("<input type=\"hidden\" name=\"configfile\" value=\"" + GlobalEnv.getconfigfile() + "\" >");
		}

		if(Connector.logoutFlag ){
			header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/servlet/supersql.form.Session\" method = \"post\" name=\"theForm\">\n");
			//header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/supersql.form.Session\" method = \"post\" name=\"theForm\">\n");
			//header.append("<input type=\"hidden\" name=\"tableinfo\" value=\"" + Start_Parse.get_from_info_st() + "\" >");
			header.append("<input type=\"hidden\" name=\"configfile\" value=\"" + GlobalEnv.getconfigfile() + "\" >");
		}


		if(Connector.insertFlag || Connector.deleteFlag || Connector.updateFlag){
			header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/servlet/supersql.form.Update\" method = \"post\" name=\"theForm\">\n");
			//header.append("<form action = \""+ GlobalEnv.getFileDirectory() + "/supersql.form.Update\" method = \"post\" name=\"theForm\">\n");
			header.append("<input type=\"hidden\" name=\"tableinfo\" value=\"" + Start_Parse.get_from_info_st() + "\" >");
			header.append("<input type=\"hidden\" name=\"configfile\" value=\"" + GlobalEnv.getconfigfile() + "\" >");
			if(Connector.insertFlag)
				header.append("<input type=\"hidden\" name=\"sql_param\" value=\"insert\" >");
			if(Connector.deleteFlag)
				header.append("<input type=\"hidden\" name=\"sql_param\" value=\"delete\" >");
			if(Connector.updateFlag)
				header.append("<input type=\"hidden\" name=\"sql_param\" value=\"update\" >");
		}

	}

	//getCol2
	private String getCol2(String c2, String str) {
		return (!c2.isEmpty())? str :"";
	}

	public static String commonCSS() {
		String s = "";
		// modifeid by masato 20151118 for ehtml start
		if(Ehtml.flag){
				String id ="ssqlResult" + GlobalEnv.getQueryNum();
			if(defaultCssFlag){ // TODO 場合分けをしっかり
				s += "#"+ id + " div, table, tr, td, th, img, a{\n" +
						"\tmargin: 0;\n" +
						"\tpadding: 0;\n" +
						"\tborder: 0;\n" +
						"\tfont-style:normal;\n" +
						"\tfont-weight: normal;\n" +
						"\tfont-size: 100%;\n" +
						"}\n\n";

				s += "#"+ id + " .row { display: flex; flex-direction: row; }\n";
				s += "#"+ id + " .col { display: flex; flex-direction: column; }\n";
				s += "#"+ id + " .att { border: solid 0px; }\n";

				s += "#"+ id + " table {\n" +
						"\tborder: 1px solid;\n" +
						"\tpadding: 1px;\n" +
						"}\n\n";

				s += "#"+ id + " td {\n" +
						"\tvertical-align: middle;\n" +
						"}\n\n";

//				s += "#"+ id + " table {\n" +
//						"\tmargin-left: auto;\n" +
//						"\tmargin-right: auto;\n" +
//						"\tmargin-top: auto;\n" +
//						"\tmargin-bottom: auto;\n" +
//						"\twidth: 100px;\n" +
//						"\tborder: 1px #BFBFBF solid;\n" +
//						"}\n\n";
//				s += "#"+ id + " table th {\n" +
//						"\tfont-weight: normal;\n" +
//						"\tbackground-color: #F0F0F0;\n" +
//						"\tborder:1px solid #BFBFBF;\n" +
//						"\ttext-align: center;\n" +
//						"\tpadding: 1px;\n" +
//						"}\n\n";
//				s += "#"+ id + " table tr td {\n" +
//						"\twidth: 100px;\n" +
//						"\tbackground-color: #3f3f3f;\n" +
//						"\tcolor: #e9e9e9;\n" +
//						"\ttext-align: center;\n" +
//						"\tpadding: 0px;\n" +
//						"\tvertical-align: middle;\n" +
//						"}\n\n";
//				s += "#"+ id + " table.att {\n" +
//						"\tmargin-left: auto;\n" +
//						"\tmargin-right: auto;\n" +
//						"\tmargin-top: auto;\n" +
//						"\tmargin-bottom: auto;\n" +
//						"\tborder: 1px #F0F0F0 solid;\n" +
//						"}\n\n";
			}
		} else
		// modifeid by masato 20151118 for ehtml end
		if (!GlobalEnv.isOpt()) {
			s += Ehtml.getID(1)+" .att { padding:0px; margin:0px; height:100%; z-index:2; }\n";
			s += Ehtml.getID(1)+" .linkbutton { text-align:center; margin-top:5px; padding:5px; }\n";
			s += Ehtml.getID(1)+" .embed { vertical-align:text-top; padding:0px; margin:0px; border:0px,0px,0px,0px; width:100%; }\n" +
				 Ehtml.getID(1)+" .noborder { border-width:0px; margin-top:-1px; padding-top:-1px; "
					+ "margin-bottom:-1px; padding-bottom:-1px; }\n\n";
		}
		return s;
	}

	public void getFooter(int footerFlag) {		//[headerFlag] 1:通常、2:Prev/Next
		if(Connector.updateFlag || Connector.insertFlag|| Connector.deleteFlag || Connector.loginFlag ){
			footer.append("<input type=\"submit\" name=\"login\" value=\"Let's go!\">");
			footer.append("</form>\n");
			Log.out("</form>");
			Connector.updateFlag = false;
			Connector.insertFlag = false;
			Connector.deleteFlag = false;
			Connector.loginFlag = false;
		}

		if(Connector.logoutFlag ){
			footer.append("</form>\n");
			Log.out("</form>");
			Connector.logoutFlag = false;
		}

		if(GlobalEnv.getframeworklist() == null){
			//added by goto 20161019 for new foreach
			footer.append("</div><!-- Close id=\""+((!Ehtml.isEhtml2())? "ssql_body_contents" : Ehtml.getID(0))+"\" -->\n");
			footer.append(LinkForeach.getC3contents());

			//added by goto 20161109 for plink/glink
			if(!plink_glink_onclick.isEmpty())
				footer.append(LinkForeach.getPlinkGlinkContents());

			if(footerFlag==1){		//通常時のみ（Prev/Nextでは行わない）
				if((!noAd || !copyright.isEmpty()) && !Ehtml.isEhtml2() && !CodeGenerator.getMedia().toLowerCase().equals("php"))
					footer.append("<hr size=\"1\">\n");
				if(!copyright.equals("")){	//copyrightを付加
					footer.append("<div>\n");
					footer.append("Copyright &COPY; "+copyright+" All Rights Reserved.\n");
					footer.append("</div>\n\n");
				}
				if(!noAd && !Ehtml.isEhtml2() && !supersql.codegenerator.Compiler.PHP.PHP.isPHP){
					//SuperSQLの宣伝を付加
					footer.append("<div style=\"font-size:11;\">\n");
					if(fff.equals(""))	footer.append("This HTML was generated by <a href=\"http://ssql.db.ics.keio.ac.jp/\" rel=\"external\">SuperSQL</a>\n");
					else				footer.append("<a href=\""+ fff +"\" target=\"_self\">This HTML</a> was generated by <a href=\"http://ssql.db.ics.keio.ac.jp/\" rel=\"external\">SuperSQL</a>\n");
					footer.append("</div>\n\n");
				}
			}

			//20160601 bootstrap
			if(!Sass.isBootstrapFlg()){
				footer.append("</div><!-- Close <div data-role=\"content\"> -->\n<!-- data-role=content end -->\n");		//Close <div data-role="content">
				//data-role="footer"
			}
			if(footerFlag==1 && Mobile_HTML5Function.footerString.equals("") && flickBarFlg)	//通常時のみ（Prev/Nextでは行わない）
				Mobile_HTML5Function.footerString
				+= "<div data-role=\"footer\" data-position=\"fixed\" style=\"padding:11px 0px; background:gray; filter: alpha(opacity=25); -moz-opacity:0.25; opacity:0.25;\" id=\"footer1\">\n" +
						"<=&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Flick bar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=>"+"\n" +
						"</div>\n";
			if(footerFlag==1 && !Mobile_HTML5Function.footerString.equals(""))	//通常時のみ（Prev/Nextでは行わない）
				footer.append("\n\n<!-- data-role=footer start -->\n"+Mobile_HTML5Function.footerString+"<!-- data-role=footer end -->\n\n");
			if(panel.length() != 0)															//20130503  Panel
				footer.append("\n<!-- Panel start -->\n"+panel+"\n<!-- Panel end -->\n\n");	//Add panel contents.	
			//added by goto 20130508  "Login&Logout"
			if(Start_Parse.sessionFlag){
				footer.append("\n" +
						"EOF;\n" +
						"	}//end if\n" +
						"}//end function\n" +
						"//<!-- display_html end -->\n" +
						"?>\n");
			}//else
			footer.append("<iframe name=\"dummy_ifr\" style=\"display:none;\"><!-- dummy iframe for Form target --></iframe>\n");	//dummy iframe
			//TODO: 下記でOK?
			if(Start_Parse.sessionFlag || Mobile_HTML5Function.searchCount>1 || Mobile_HTML5Function.selectCount>1){
				footer.append(Mobile_HTML5Env.PHP);			//PHPストリングを付加			//added by goto 20130515  "search"
			}
			if(footerFlag==1)		//通常時のみ（Prev/Nextでは行わない）
				footer.append("\n\n");
			//20160603 bootstrap
			if(!Sass.isBootstrapFlg()){
				footer.append("</div><!-- Close <div data-role=\"page\"> -->\n<!-- data-role=page end -->\n");			//Close <div data-role="page">
			}else if(Sass.isBootstrapFlg()){
				footer.append("</div><!-- Close container -->\n");
			}
			footer.append("<!-- SuperSQL Body  End -->");
			
			if (!Ehtml.isEhtml2()) {
				footer.append("\n</BODY>\n</HTML>\n");
				Log.out("</body></html>");
			}
		}
	}

	public void append_css_def_td(String classid, DecorateList decos) {
		haveClass=0;
		Log.out("[HTML append_css_def_att] classid=" + classid);
		Log.out("decos = " + decos);

		//��classid�Υ�����?�����Ȥ��������ꤷ�����Ȥ���?��
		if (written_classid.contains(classid)) {
			// �����?�ѤΥ�����?������
			haveClass=1;
			Log.out("==> already created style");
			return;
		}else if(not_written_classid != null && not_written_classid.contains(classid)){
			Log.out("==> style is null. not created style");
			return;
		}

		Log.out("==> new style");
		Log.out("@@ creating style no " + classid);

		StringBuffer cssbuf = new StringBuffer();

		//tk start////////////////////////////////////////////////////////////////
		StringBuffer metabuf = new StringBuffer();

		if (decos.containsKey("class")){
			cssclass.put(classid,decos.getStr("class"));
			Log.out("class =" + classid + decos.getStr("class"));
		}

		//changed by goto 20130703  ex) cssfile=" a.css; b.css "
		if (decos.containsKey("cssfile")) {
			String css = decos.getStr("cssfile").trim();
			if(!css.contains(",")){
				cssfile.delete(0, cssfile.length());
				if (GlobalEnv.isServlet()) {
					cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ GlobalEnv.getFileDirectory()
							+ css + "\">\n");
				} else {
					cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""
							+ css + "\">\n");
				}
			}else{
				if(!css.endsWith(","))	css+=",";
				while(css.contains(",")){
					cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css.substring(0,css.indexOf(",")).trim() + "\">\n");
					css = css.substring(css.indexOf(",")+1);
				}
			}
		}else if(cssfile.length() == 0){
			if(GlobalEnv.isServlet()){
				cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + GlobalEnv.getFileDirectory() +"/default1.css \">\n");
			}else{
				if(getOs().contains("Windows")){
					cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"default1.css\">\n");
				}else{
					//commented out by goto 201303
					//            		//itc
					//            		if(GlobalEnv.isOpt())
					//            			cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqljscss/default_opt.css\">\n");
					//            		else
					//            			cssfile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.db.ics.keio.ac.jp/ssqljscss/default1.css\">\n");
				}
			}
		}

		//added by goto 20130703  ex) jsfile=" a.js; b.js "
		if (decos.containsKey("jsfile")) {
			String js = decos.getStr("jsfile").trim();
			if(!js.endsWith(","))	js+=",";
			while(js.contains(",")){
				jsFile.append("<script type=\"text/javascript\" src=\""	+ js.substring(0,js.indexOf(",")).trim() + "\"></script>\n");
				js = js.substring(js.indexOf(",")+1);
			}
		}

		//added by goto 20130703  ex) require=" a.css; a.js;  b.css; b.js "
		if (decos.containsKey("require")) {
			String file = decos.getStr("require").trim();
			if(!file.endsWith(","))	file+=",";
			String fileName = "";
			while(file.contains(",")){
				fileName = file.substring(0,file.indexOf(",")).trim();
				if(fileName.endsWith(".css"))
					cssjsFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + fileName + "\">\n");
				else if(fileName.endsWith(".js"))
					cssjsFile.append("<script type=\"text/javascript\" src=\"" + fileName + "\"></script>\n");
				else{
					//added by goto 20130710  ex) require="Folder name"
					try{
						String[] fileArray = new File(fileName).getAbsoluteFile().list();
						for(int i = 0; i < fileArray.length; i++) {
							if(fileArray[i].endsWith(".css"))
								cssjsFile.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + fileName + "/" + fileArray[i] + "\">\n");
							else if(fileArray[i].endsWith(".js"))
								cssjsFile.append("<script type=\"text/javascript\" src=\"" + fileName + "/" + fileArray[i] + "\"></script>\n");
						}
					}catch (Exception e){
						System.err.println("<Warning> require=に指定されたフォルダ「"+fileName+"」が見つかりません。");
					}
				}

				file = file.substring(file.indexOf(",")+1);
			}
		}

		if (decos.containsKey("divalign") && div.length() == 0)
			div.append(" align=" +decos.getStr("divalign"));

		//if (decos.containsKey("title") && title.length() == 0)	//disuse
		//	title.append(decos.getStr("title"));
		if (decos.containsKey("title_class"))
			titleclass.append(" class=\""+decos.getStr("title_class")+"\"");
		if (decos.containsKey("tableborder") )//&& tableborder.length() == 0)
			tableborder = decos.getStr("tableborder");

		//tk end//////////////////////////////////////////////////////////////

		// ��??
		if (decos.containsKey("width")) {
			if(GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("width")))
				cssbuf.append(" width:" + decos.getStr("width") + ";");
			else
				cssbuf.append(" width:" + decos.getStr("width") + "px;");
		}

		// ��??
		if (decos.containsKey("height")){
			if(GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("height")))
				cssbuf.append(" height:" + decos.getStr("height") + ";");
			else
				cssbuf.append(" height:" + decos.getStr("height") + "px;");
		}


		// margin
		if (decos.containsKey("margin")) {
			cssbuf.append(" margin:" + decos.getStr("margin") + ";");
			//        } else {
			//            cssbuf.append(" padding:0.3em;");
		}

		// �ѥǥ��󥰡�;���
		if (decos.containsKey("padding")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding:" + decos.getStr("padding") + ";");
			else
				cssbuf.append(" padding:" + decos.getStr("padding") + "px;");
		}
		//padding
		if (decos.containsKey("padding-left")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-left:" + decos.getStr("padding-left") + ";");
			else
				cssbuf.append(" padding-left:" + decos.getStr("padding-left") + "px;");
		}
		if (decos.containsKey("padding-right")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-right:" + decos.getStr("padding-right") + ";");
			else
				cssbuf.append(" padding-right:" + decos.getStr("padding-right") + "px;");
		}
		if (decos.containsKey("padding-top")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-top:" + decos.getStr("padding-top") + ";");
			else
				cssbuf.append(" padding-top:" + decos.getStr("padding-top") + "px;");
		}
		if (decos.containsKey("padding-bottom")) {
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag)
				cssbuf.append(" padding-bottom:" + decos.getStr("padding-top") + ";");
			else
				cssbuf.append(" padding-bottom:" + decos.getStr("padding-top") + "px;");
		}

		// ������
		if (decos.containsKey("align"))
			cssbuf.append(" text-align:" + decos.getStr("align") + ";");

		// �İ���
		if (decos.containsKey("valign"))
			cssbuf.append(" vertical-align:" + decos.getStr("valign") + ";");

		// �طʿ�
		if (decos.containsKey("background-color"))
			cssbuf.append(" background-color:"
					+ decos.getStr("background-color") + ";");
		if (decos.containsKey("bgcolor"))
			cssbuf.append(" background-color:" + decos.getStr("bgcolor") + ";");

		// ʸ��
		if (decos.containsKey("color"))
			cssbuf.append(" color:" + decos.getStr("color") + ";");
		if (decos.containsKey("font-color"))
			cssbuf.append(" color:" + decos.getStr("font-color") + ";");
		if (decos.containsKey("font color"))
			cssbuf.append(" color:" + decos.getStr("font color") + ";");

		// ʸ����
		//170710 changed by tbt
		if (decos.containsKey("font-size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("font-size")))
				cssbuf.append(" font-size:" + decos.getStr("font-size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("font-size") + "px;");
		}
		if (decos.containsKey("font size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag && !GlobalEnv.isNumber(decos.getStr("font size")))
				cssbuf.append(" font-size:" + decos.getStr("font size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("font size") + "px;");}
		if (decos.containsKey("size")){
			if (GlobalEnv.getframeworklist() == null && !Ehtml.flag  && !GlobalEnv.isNumber(decos.getStr("size")))
				cssbuf.append(" font-size:" + decos.getStr("size") + ";");
			else
				cssbuf.append(" font-size:" + decos.getStr("size") + "px;");
		}
		//tbt end

		// ʸ�������
		if (decos.containsKey("font-weight"))
			cssbuf.append(" font-weight:" + decos.getStr("font-weight") + ";");

		// ʸ����?
		if (decos.containsKey("font-style"))
			cssbuf.append(" font-style:" + decos.getStr("font-style") + ";");
		if (decos.containsKey("font-family"))
			cssbuf.append(" font-family:" + decos.getStr("font-family") + ";");

		if(decos.containsKey("border"))
			cssbuf.append(" border:" + decos.getStr("border")+";");
		if(decos.containsKey("border-width"))
			cssbuf.append(" border-width:" + decos.getStr("border-width")+";");
		if(decos.containsKey("border-color"))
			cssbuf.append(" border-color:" + decos.getStr("border-color")+";");
		if(decos.containsKey("border-style"))
			cssbuf.append(" border-style:" + decos.getStr("border-style")+";");
		if(decos.containsKey("border-collapse"))
			cssbuf.append(" border-collapse:" + decos.getStr("border-collapse")+";");

		//tk start////////////////////////////////////////////////////////////////
		//added by goto 20120715 start
		//      if (decos.containsKey("charset"))
		//      metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + decos.getStr("charset") + "\">");
		////      else
		////       	metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=euc-jp\">");
		if (decos.containsKey("charset"))
			charset=decos.getStr("charset");
		else if(!charsetFlg)
			charset="UTF-8";		//default charset = UTF-8
		if(!charsetFlg && charset!=null && !Ehtml.isEhtml2()){
			//changed by goto 20130110 start
			//metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\">");
			metabuf.append("<meta charset=\"" + charset + "\">");
			//changed by goto 20130110 end
			charsetFlg=true;
		}
		//        if (decos.containsKey("charset")){
		//            metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + decos.getStr("charset") + "\">");
		//            charset=decos.getStr("charset");
		//            charsetFlg=1;
		//        }else if(charsetFlg!=1){
		//        		metabuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=EUC-JP\">");
		//        		charset="EUC-JP";		//default charset = EUC-JP
		//        		charsetFlg=1;
		//      	}
		//added by goto 20120715 end

		//added by goto 20130501  "style"
		if (decos.containsKey("style")){
			String style = decos.getStr("style");
			cssbuf.append(" " + style);
			if(!style.matches(".*;\\s*$"))	cssbuf.append(";");	//最後に";"が無かった場合
		}

		//added by goto 20130411  "title"
		if (decos.containsKey("title"))
			title = decos.getStr("title");

		//added by goto 20130311  "background"
		if (decos.containsKey("background"))
			bg = decos.getStr("background");
		
  		if(decos.containsKey("page-bgcolor")){
  			bgcolor = decos.getStr("page-bgcolor");
  		}else if(decos.containsKey("pbgcolor")){
  			bgcolor = decos.getStr("pbgcolor");
  		}
      	
  		if(decos.containsKey("page-align")){
  			pos = decos.getStr("page-align");
  		}else if(decos.containsKey("palign")){
  			pos = decos.getStr("palign");
  		}else if(decos.containsKey("table-align")){
      		pos = decos.getStr("table-align");
      	}else if(decos.containsKey("talign")){
      		pos = decos.getStr("talign");
      	}
      	
      	

		//added by goto 20130512  "max-width"
		try{
			//	        if (decos.containsKey("max-width"))
			//	        	maxWidth = Integer.parseInt(decos.getStr("max-width"));
			//	        else if (decos.containsKey("maxwidth"))
			//	        	maxWidth = Integer.parseInt(decos.getStr("maxwidth"));
			if (decos.containsKey("portrait-width"))
				portraitWidth = Integer.parseInt(decos.getStr("portrait-width"));
			else if (decos.containsKey("p-width"))
				portraitWidth = Integer.parseInt(decos.getStr("p-width"));
			if (decos.containsKey("landscape-width"))
				landscapeWidth = Integer.parseInt(decos.getStr("landscape-width"));
			else if (decos.containsKey("l-width"))
				landscapeWidth = Integer.parseInt(decos.getStr("l-width"));
			if (decos.containsKey("pc-width"))
				pcWidth = Integer.parseInt(decos.getStr("pc-width"));
		}catch(Exception e){ /*数値以外*/ }
		if(supersql.codegenerator.Compiler.PHP.PHP.isPHP && pcWidth<0)
			pcWidth = 1000;

		//added by goto 20161217  for responsive
		Responsive.check(decos);

		if (!Ehtml.isEhtml2()) {
			if (decos.containsKey("description"))
				metabuf.append("\n<meta name=\"Description\" content=\"" + decos.getStr("description") + "\">");
			if (decos.containsKey("keyword"))
				metabuf.append("\n<meta name=\"Keyword\" content=\"" + decos.getStr("keyword") + "\">");
			if (decos.containsKey("author"))
				metabuf.append("\n<meta name=\"Author\" content=\"" + decos.getStr("author") + "\">");
			if (decos.containsKey("copyright")){
				copyright = decos.getStr("copyright");		//あとでfooterにappendする
				metabuf.append("\n<meta name=\"Copyright\" content=\"" + copyright + "\">");
			}
			if (decos.containsKey("pragma"))
				metabuf.append("\n<meta http-equiv=\"Pragma\" content=\"" + decos.getStr("pragma") + "\">");
			if (decos.containsKey("robot"))
				metabuf.append("\n<meta name=\"Robot\" content=\"" + decos.getStr("robot") + "\">");
		}

		//added by goto 20130519  "moveto"
		if (decos.containsKey("refresh")){
			//<meta http-equiv="refresh" content="3; URL=http://ssql.db.ics.keio.ac.jp/mdemo/list.html">
			metabuf.append("<meta http-equiv=\"refresh\" content=\""+decos.getStr("refresh")+"\">");
		}else if(!Mobile_HTML5Function.movetoFlg.equals("")){
			metabuf.append(Mobile_HTML5Function.movetoFlg);
			Mobile_HTML5Function.movetoFlg = "";
		}


		//20130518  "show query"
		if (decos.containsKey("showquery") || decos.containsKey("showq") || decos.containsKey("showquery&noshow") || decos.containsKey("showq&noshow") || decos.containsKey("show")  || decos.containsKey("query")){
			String replaceStrings = "";
			if(decos.containsKey("showquery&noshow"))
				replaceStrings = decos.getStr("showquery&noshow");
			else if(decos.containsKey("showq&noshow"))
				replaceStrings = decos.getStr("showq&noshow");

			fff = filename;
			fff = fff.substring(0,fff.lastIndexOf(".html"));	//.htmlをカット
			//TODO: fffが相対パスだった場合、ファイルパスを取得してきてappend（相対パス → 絶対パス）

			String code = "";
			code += "<html>\n<head>\n";
			code += "<meta name=\"GENERATOR\" content=\" SuperSQL (Generate "+((!Sass.isBootstrapFlg())? "Mobile_HTML5" : "ResponsiveHTML")+") \">\n" +
					"<meta charset=\""+charset+"\">\n" +
					"<title>"+fff.substring(fff.lastIndexOf("/")+1)+".ssql</title>\n" +
					"\n" +
					"<style type=\"text/css\">\n" +
					"<!--\n" +
					"ol {\n" +
					"    position: absolute;\n" +
					"    top:40px;\n" +
					"	list-style-type: decimal-leading-zero;\n" +
					"	font-size: 15px;\n" +
					"	line-height: 18.03px;\n" +
					"}\n" +
					"li { \n" +
					"	margin: 0 0 2 2em;\n" +
					"}\n" +
					"body,ol {\n" +
					"	margin: 0px;\n" +
					"	width: 100%;\n" +
					"	background: #f0f0f0;						/* pngがインポートされなかったとき */\n" +
					"	background-image: url(http://www.db.ics.keio.ac.jp/ssqljscss/code_bg/code_bg1.png);\n" +
					"	overflow: auto;\n" +
					"}\n" +
					"#bgcolor { margin-left:35px; }\n" +
					"#b0 { color:gray; font-size:12; }\n" +
					"#b1 { color:honeydew; }\n" +
					"#b2 { color:lightgoldenrodyellow; }\n" +
					"#b3 { color:gray; }\n" +
					"#t1 { color:gray; font-size:19; }\n" +
					"-->\n" +
					"</style>\n" +
					"\n" +
					"<script src=\"http://code.jquery.com/jquery-1.7.1.min.js\"></script>\n" +
					"<script type=\"text/javascript\">\n" +
					"<!--\n" +
					"$(function(){\n" +
					"    $('#b1').mouseover(function(){\n" +
					"   		c(1,\"black\",\"gray\",\"honeydew\");\n" +
					"    });\n" +
					"    $('#b2').mouseover(function(){\n" +
					"    	c(2,\"black\",\"gray\",\"lightyellow\");\n" +
					"    });\n" +
					"    $('#b3').mouseover(function(){\n" +
					"    	c(3,\"white\",\"white\",\"black\");\n" +
					"    });\n" +
					"    function c(val,color1,color2,bg){\n" +
					"        $(\"body,ol\").css(\"background\",bg);		//pngがインポートされなかったとき\n" +
					"        $(\"body,ol\").css(\"background-image\",\"url(http://www.db.ics.keio.ac.jp/ssqljscss/code_bg/code_bg\"+val+\".png)\");\n" +
					"        $(\"ol\").css(\"color\",color1);\n" +
					"        $(\"#t1\").css(\"color\",color2);\n" +
					"    }\n" +
					"});\n" +
					"-->\n" +
					"</script>\n" +
					"</head>\n\n";
			code += "<body>\n" +
					"<div id=\"bgcolor\">\n" +
					"	<span id=\"b0\">Background-color:&nbsp;</span>\n" +
					"	<span id=\"b1\">■</span>\n" +
					"	<span id=\"b2\">■</span>\n" +
					"	<span id=\"b3\">■</span>\n" +
					"</div>\n" +
					"\n" +
					"<pre>\n" +
					"<code>\n" +
					"\n" +
					"<ol>\n" +
					"<span id=\"t1\">"+fff.substring(fff.lastIndexOf("/")+1)+".ssql</span>";
			//create HTML file
			if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
				try {
					//Log.i("create HTML file エンコードcharset:"+charset);
					PrintWriter pw;
					if (charset != null)
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(fff+"_sql.html"),charset)));
					else
						pw = new PrintWriter(new BufferedWriter(new FileWriter(
								fff+"_sql.html")));
					pw.println(code);

					BufferedReader br = null;
					try{
						//TODO: file-encodingを取得して第二引数へ反映させる処理
						br = new BufferedReader(new InputStreamReader(new FileInputStream(fff+".ssql"), "UTF-8"));		//fileを開く
						//		              br = new BufferedReader(new InputStreamReader(new FileInputStream(fff+".ssql"), charset));		//fileを開く
						String queryString = new String();
						int c;
						while ((c = br.read()) != -1)	queryString += ((char) c);

						//***へ置換
						//Log.i("replaceStrings: "+replaceStrings);
						replaceStrings = replaceStrings.trim();
						if(!replaceStrings.equals("")){
							if(!replaceStrings.endsWith(";"))		replaceStrings += ";";
							while(replaceStrings.contains(";")){
								queryString = queryString.replaceAll(replaceStrings.substring(0,replaceStrings.indexOf(";")).trim(),"***");
								replaceStrings = replaceStrings.substring(replaceStrings.indexOf(";")+1);
							}
						}
						//書き込み
						pw.println( queryString.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("^", "<li>").replaceAll("\n", "\n<li>") );
					}finally{
						br.close();
					}

					pw.println("</ol>\n\n</code>\n</pre>\n");
					if (!Ehtml.isEhtml2())
						pw.println("</body>\n</html>");
					pw.close();
				} catch (Exception e) { /*Log.i("Create HTML failed: "+e);*/ }
			}

			//HTMLfilenameを絶対パスから「相対パス形式」へ変更
			String fileDir = new File(filename).getAbsoluteFile().getParent();
			if(fileDir.length() < filename.length()
					&& fileDir.equals(filename.substring(0,fileDir.length()))){
				//				String relative_path_filename = filename.substring(fileDir.length()+1);
				//				fff = relative_path_filename;
				//code.append("<A href=\"" + relative_path + "\" ");
				//Log.i("relative_path: "+relative_path_filename);
				fff = filename.substring(fileDir.length()+1);
			}else
				fff = filename;
			//Log.i("linkurl: " + filename);
			//code.append("<A href=\"" + htmlEnv.linkUrl + "\" ");
			fff = fff.substring(0,fff.lastIndexOf(".html"));	//.htmlをカット
			fff += "_sql.html";

		}

		//20130521  "flickbar"
		//if (decos.containsKey("nobar") || decos.containsKey("noflick") || decos.containsKey("noflickbar"))
		if (decos.containsKey("flickbar"))
			flickBarFlg = true;

		//20131106
		if (decos.containsKey("noad") || decos.containsKey("noAd"))
			noAd = true;

		if (cssbuf.length() > 0) {
			haveClass = 1;
			//����?�Υ�����?����
			// modified by masato 20151122 start for etml, css
			if(Ehtml.flag){
				String id = "ssqlResult" + GlobalEnv.getQueryNum();
				css.append("#" + id + " ." + classid + "{");
			} else {
				css.append("." + classid + "{");
			}

			css.append(cssbuf);
			//��?�Υ�����?�Ĥ�
			css.append(" }\n");

			//������?��?�Ѥߥ��饹��id����¸���Ƥ���
			written_classid.addElement(classid);
		}else{
			Log.out("==> style is null. not created style");
			not_written_classid.addElement(classid);
		}

		//tk start//////////////////////////////////////////////////////////
		if(metabuf.length() > 0)
		{
			//meta.append(" ");		//commented out by goto 201303
			meta.append(metabuf);
			meta.append("\n");

		}
		//tk end////////////////////////////////////////////////////////////


	}
	//added by taji for infinite scroll
	public void append_css_def_td(String symbol, String classid, String childid, DecorateList decos) {
		StringBuffer cssbuf = new StringBuffer();

		if(symbol.contains("G1")){
			cssbuf.append(" overflow-x: auto; overflow-y: hidden; width: 100%; white-space:nowrap; ");

			css.append("." + classid + "{");

			css.append(cssbuf);
			//��?�Υ�����?�Ĥ�
			css.append(" }\n");

			cssbuf = new StringBuffer();
			cssbuf.append(" display:inline-block; margin-right:1%; vertical-align:top; white-space:normal; ");
			css.append("." + classid + " > ." + childid + " {");

			css.append(cssbuf);
			//��?�Υ�����?�Ĥ�
			css.append(" }\n");
		}else if(symbol.contains("G2")){
			cssbuf.append(" overflow-x: hidden; overflow-y: auto; width: 100%; height: 500px; white-space:nowrap; ");

			css.append("." + classid + "{");

			css.append(cssbuf);
			//��?�Υ�����?�Ĥ�
			css.append(" }\n");

			cssbuf = new StringBuffer();
			cssbuf.append(" display:inline-block; margin-right:1%; vertical-align:top; white-space:normal; ");
			css.append("." + classid + " > ." + childid + " {");

			css.append(cssbuf);
			//��?�Υ�����?�Ĥ�
			css.append(" }\n");
		}

	}

	// outline����Ϥ�?���ɤ����Υե饰��?
	boolean OutlineMode = false;

	public void setOutlineMode() {
		OutlineMode = true;
	}

	public String getOutlineMode() {
		if (OutlineMode) {
			OutlineMode = false;
			return "";
		}
		//        return " frame=void class=nest ";
		return " frame=void ";
	}

	public String getOutlineModeAtt() {
		if (OutlineMode) {
			OutlineMode = false;
			return " outline";
		}
		return "";
	}

	public static String getClassID(ITFE tfe) {
		String result;
		if (tfe instanceof Mobile_HTML5C3) {
			result = getClassID(((ITFE) ((Mobile_HTML5C3) tfe).tfes.get(0)));
			return result;
		}
		if (tfe instanceof Mobile_HTML5G3) {
			result = getClassID(((ITFE) ((Mobile_HTML5G3) tfe).tfe));
			return result;
		}
		result =  "TFE" + tfe.getId();
//		if (!Ehtml.isEhtml2())
//			result =  "TFE" + tfe.getId();
//		else{
//			Ehtml.setTFE_ID(Ehtml.getID(0)+"_"+"TFE" + tfe.getId());	//TODO Sass.java:".TFE", Jscss.java:".TFE"
//			result = Ehtml.getTFE_ID();
//		}
		return result;
	}

	/***start oka***/
	public static String getDataID(ITFE tfe) {
		String ClassID;
		int DataID = 0;
		String return_value;

		if (tfe instanceof Mobile_HTML5C3) {
			return getClassID(((ITFE) ((Mobile_HTML5C3) tfe).tfes.get(0)));
		}
		if (tfe instanceof Mobile_HTML5G3) {
			return getClassID(((ITFE) ((Mobile_HTML5G3) tfe).tfe));
		}
		ClassID = String.valueOf(tfe.getId());
		DataID = Integer.valueOf((ClassID.substring(ClassID.length()-3,ClassID.length()))).intValue();

		Log.out("ClassID="+ClassID);
		Log.out("DataID="+DataID);
		Log.out("ID_counter="+ID_counter);

		if(DataID < ID_old){
			ID_counter = DataID;
		}
		else{
			if(DataID != ID_counter && DataID > ID_counter){
				DataID = ID_counter;
			}
		}
		ID_counter++;
		ID_old = DataID;
		return_value = String.valueOf(DataID);
		return return_value;
	}


	/********  form method  ************/
	/********** 2009 chie **************/

	//
	public static void initAllFormFlg(){
		setFormItemFlg(false,null);
		setSelectFlg(false);
		setSelectRepeat(false);
		setFormValueString(null);
		setFormPartsName(null);
		setSelected("");
		setIDU("");
		form_parts_number = 1;
		exchange_form_name = new String();
		form_detail = new String[256];
		form_number = 1;
		nameId = "";
		search = false;
		searchid = 0;
		cond_name="";
		cond ="";
	}


	static boolean isFormItem;
	static String formItemName;
	//form tag is written : true
	public static void setFormItemFlg(boolean b,String s){
		isFormItem = b;
		formItemName = s;
		return;
	}

	public static boolean getFormItemFlg(){
		return isFormItem;
	}

	public static String getFormItemName(){
		if(formItemName == null){
			return "0";
		}
		return formItemName;
	}

	static boolean select_flg;
	//function select flg -> in func_select true

	//set and get select_flg
	public static void setSelectFlg(boolean b){
		select_flg = b;
	}

	public static boolean getSelectFlg(){
		return select_flg;
	}


	static String formValueString;
	public static void setFormValueString(String s){
		formValueString = s;
	}
	public static String getFormValueString(){
		return formValueString;
	}



	static boolean select_repeat;
	//select_repeat flag
	//not write "<tr><td>" between "<option>"s
	//set and get select_repeat
	public static void setSelectRepeat(boolean b){
		select_repeat = b;
	}
	public static boolean getSelectRepeat(){
		return select_repeat;
	}

	//global form item number : t1,t2,t3...
	public static int form_parts_number = 1;
	static String form_parts_name = null;
	public static String getFormPartsName(){
		if(form_parts_name == null){
			return "t"+form_parts_number;
		}else{
			return form_parts_name;
		}
	}
	public static void incrementFormPartsNumber(){
		form_parts_number++;
	}


	public static void setFormPartsName(String s){
		form_parts_name = s;
	}

	private static String exchange_form_name = new String();
	public static void exFormName(){
		String s = "t" + form_parts_number + ":" + form_parts_name +":";
		if(exchange_form_name == null || exchange_form_name.isEmpty()){
			exchange_form_name = ":"+s;
		}else{
			if(!exchange_form_name.contains(s))
				exchange_form_name += s;
		}
	}
	public static String exFormNameCreate(){
		String ret = new String();
		if(exchange_form_name != null){
			ret = "<input type=\"hidden\" name=\"exchangeName\" value=\""+exchange_form_name+"\" />";
			setFormDetail(ret);
			return ret;
		}else{
			return null;
		}
	}


	//add js or css file names that using in the Mobile_HTML5 to the header
	public static void addJsCss(String filename){
		if(!jscss.contains(filename)){
			jscss += "<script src=\""+filename+"\"></script>\n";
		}
	}


	//global form number : 1,2,3...
	static int form_number = 1;
	public static void incrementFormNumber(){
		form_number++;
	}

	public static int getFormNumber(){
		//return formNumber 1,2,3...
		return form_number;
	}
	public static String getFormName(){
		//return formNumber f1,f2,f3...
		return "f"+form_number;
	}

	static String[] form_detail = new String[256];
	public static void setFormDetail(String s){
		if(form_detail[form_number] == null)
			form_detail[form_number] = s;
		else
			form_detail[form_number] += s;
	}
	public static String getFormDetail(int i){
		return form_detail[i];
	}

	static String IDUst = new String();
	public static void setIDU(String s){
		IDUst = s;
	}

	public static String getIDU(){
		return IDUst;
	}

	static String selected = "";

	public static void setSelected(String s){
		selected = s;
	}
	public static String getSelected(){
		return selected;
	}

	public static String nameId = "";
	public static String getNameid(){
		if(nameId != null){
			return nameId;
		}else{
			return "";
		}
	}

	static String checked = "";

	public static void setChecked(String s){
		System.out.println("checked:"+s);
		checked = s;
	}
	public static String getChecked(){
		return checked;
	}

	static boolean search = false;
	public static int searchid = 0;
	public static String cond_name = "";
	public static String cond = "";

	public static void setSearch(boolean b){
		search = b;
		searchid = 0;
	}
	public static boolean getSearch(){
		return search;
	}


	//goto 20131123
	public String getFileName(){
		//absolute path filename (/home/---/XXX.html)
		if(!filename.isEmpty())	return filename;
		else 					return GlobalEnv.getfilename();
	}
	public String getFileName1(){
		//absolute path filename (/home/---/XXX.html)
		return getFileName();
	}
	public String getFileName2(){
		//absolute path filename (/home/---/XXX)
		return getFileName().substring(0, getFileName().lastIndexOf("."));
	}
	public String getFileName3(){
		//file name (XXX.html)
		return new File(getFileName1()).getName();
	}
	public String getFileName4(){
		//file name (XXX)
		return new File(getFileName2()).getName();
	}
	public String getFileParent(){
		//file path (/home/---/)
		try {
			return new File(filename).getParent().toString();
		} catch (Exception e) {
			return ".";
		}
	}

	public static void initXML(){
		xmlCode = new StringBuffer();
		xmlCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	public int getGlevel() {
		return glevel;
	}

	public void setGlevel(int glevel) {
		this.glevel = glevel;
	}
}