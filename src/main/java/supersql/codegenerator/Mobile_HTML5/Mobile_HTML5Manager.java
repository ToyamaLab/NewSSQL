package supersql.codegenerator.Mobile_HTML5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Vector;


import supersql.codegenerator.Ehtml;
import supersql.codegenerator.FileFormatter;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

public class Mobile_HTML5Manager extends Manager{

	//����
	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	//���󥹥ȥ饯��
	public Mobile_HTML5Manager(Mobile_HTML5Env henv,Mobile_HTML5Env henv2) {
		this.html_env = henv;
		this.html_env2 = henv2;
	}

	//20130330 tab
	//replaceCode
	//replace a to b in html_env.code
	//(html_env.code内の「a」を「b」へ置換する)
	public static boolean replaceCode(Mobile_HTML5Env html_env,String a,String b){
		try{
			html_env.code.replace(
					html_env.code.lastIndexOf(a),
					html_env.code.lastIndexOf(a)+a.length(),
					b);
		}catch(Exception e){
			/*Log.info("Catch exception.");*/
			return false;
		}
		return true;
	}
	//メソッドオーバーロード
	public static boolean replaceCode(StringBuffer sb,String a,String b){
		try{
			sb.replace(
					sb.lastIndexOf(a),
					sb.lastIndexOf(a)+a.length(),
					b);
		}catch(Exception e){
			/*Log.info("Catch exception.");*/
			return false;
		}
		return true;
	}

	@Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {

		Mobile_HTML5Env.initAllFormFlg();
		
		// added by masato 20150914
		Mobile_HTML5Env.initXML();
		
		html_env.countfile = 0;
		html_env.code = new StringBuffer();
		html_env.css = new StringBuffer();
		html_env.header = new StringBuffer();
		html_env.footer = new StringBuffer();
		html_env.foreach_flag = GlobalEnv.getForeachFlag();
		html_env.written_classid = new Vector();
		html_env.not_written_classid = new Vector();
		html_env2.countfile = 0;
		html_env2.code = new StringBuffer();
		html_env2.css = new StringBuffer();
		html_env2.header = new StringBuffer();
		html_env2.footer = new StringBuffer();
		html_env2.foreach_flag = GlobalEnv.getForeachFlag();
		html_env2.written_classid = new Vector<String>();
		// Mobile_HTML5Env localenv = new Mobile_HTML5Env();

		/*** start oka ***/
		
//		Log.info("2-2-3-1-2-1");


		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof Mobile_HTML5G3) {
			tfe_info.work(data_info);
			return;
		}

		// ?�ֳ�¦��G3�Ǥʤ�??]
		if(Ehtml.isEhtml2())
			html_env.filename = html_env.outfile + Ehtml.getFileName() + Compiler.getExtension();
		else
			html_env.filename = html_env.outfile + Compiler.getExtension();
		html_env2.filename = html_env.outfile + ".xml";

		html_env.setOutlineMode();
		
//		Log.info("2-2-3-1-2-2");
		Log.info("DataConstructor.SQL_string = "+DataConstructor.SQL_string);
		Log.info("data_info.size() = "+data_info.size());

//		if(data_info.size() == 0
//				//added by goto 20130306  "FROMなしクエリ対策 3/3"
//				&& !DataConstructor.SQL_string.equals("SELECT DISTINCT  FROM ;") 
//				&& !DataConstructor.SQL_string.equals("SELECT  FROM ;"))
		if(data_info.size() == 0)
		{
			Log.info("data_info.size() == 0");
			Log.info("no data");

//			html_env.code.append("<div class=\"nodata\" >");
//			html_env.code.append("NO DATA FOUND");
//			html_env.code.append("</div>");
			
			tfe_info.work(data_info);	//added by goto 201911  FROMなしクエリ(e.g., form()など)用
		}
		else {
			Log.info("manager2-0");
			
			Log.info("manager:"+html_env.code);
			Log.info("data_info:"+data_info);
			Log.info("tfe_info:"+tfe_info);
			// System.out.println(data_info);
			tfe_info.work(data_info);
			Log.info("manager2");
		}
		

//		Log.info("2-2-3-1-2-3");
		
		
		

//		if (Ehtml.infinitescroll_flag && Ehtml.flag) {
		if (Ehtml.isInfinitescroll()) {
			String id = "ssqlResult" + GlobalEnv.getQueryNum();
			String phpFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
			String path = "";
			// 既存のHTMLのヘッダー内に書き込むjsコード
			Ehtml.appendToHeadFromBody(path);
			// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
			Ehtml.createBaseHTMLCode();
			// cssの生成・コピー
			Jscss.process();

//<<<<<<< HEAD
			// TODO 終了どうする？
			//			System.exit(0);
		}

		// add by masato 20151118 start for incremental
//		else if (Ehtml.flag) {
		else if (Ehtml.isEhtml_flag() && Ehtml.outType==0) {
			System.out.println("Ehtml.flag");
			// 生成するXMLは埋め込み先のphp or htmlファイルのある場所にその名前.xmlで生成
			// TODO masato 複数のクエリをどうページ内で実行できるようにdivのid等にする必要あり
			String id = "ssqlResult" + GlobalEnv.getQueryNum();
			String phpFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
			//TODO -scrolled 1 -> ssqlresult1-1.xml, -scrolled == null -> ssqlresult1.xml

			String xmlFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
			String path = html_env.outdir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
//			String path = "";
			Incremental.createXML(path, html_env.xmlCode);
			// 既存のHTMLのヘッダー内に書き込むjsコード
			Ehtml.appendToHeadFromBody(path);
			// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
			Ehtml.createBaseHTMLCode();
			// cssの生成・コピー
			Jscss.process();	//TODO

			// TODO 終了どうする？
//			System.exit(0);
		}
		// add by masato 20151118 end for incremental
		// add by masato 20151120 start
//		else if (Incremental.flag) {
		else if (Ehtml.isEhtml_Incremental() && Ehtml.outType==0) {
			System.out.println("Incremental.flag");
			// TODO
			String id = "ssqlResult" + GlobalEnv.getQueryNum();
			String xmlFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
			String path = html_env.outdir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
			Incremental.createXML(path, html_env.xmlCode);
			// 既存のHTMLのヘッダー内に書き込むjsコード
			Ehtml.appendToHeadFromBody(path);
			// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
			Ehtml.createBaseHTMLCode();
			// add by masato 20151120 end for incremental

		} else {
			html_env.getHeader(1);
			html_env.getFooter(1);
			//        html_env2.header.append("<?xml version=\"1.0\" encoding=\""+html_env.getEncode()+"\"?><SSQL>");
			//        html_env2.footer.append("</SSQL>");

			if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
				
				// Ehtml
				//if (Ehtml.isEhtml() && Ehtml.outType==1) {
				if (Ehtml.isEhtml2() && Ehtml.outType==1) {
					String html = "";
					if (GlobalEnv.cssout() == null)
						html += html_env.header;
					html += html_env.code;
					html += html_env.footer;
					
					// Embedded_SuperSQL_container
					html = "<div id=\"Embedded_SuperSQL_container"+Ehtml.getNumber()+"\">\n" +
					        html + 
					        "</div>\n";
					
					if(!Start_Parse.sessionFlag)
						html = FileFormatter.process(html);
					
					
					// form()でソースを見ると<html>等が付与されていたため対策
					html = html
					   .replace("<html>", "")
					   .replace("<head>", "")
					   .replace("</head>", "")
					   .replace("<body>", "")
					   .replace("</body>", "")
					   .replace("</html>", "");
					
					
//					html = html.replace("<!DOCTYPE html>", "")
//							   .replace("<html>", "")
////							   .replace("<html>", "<div class=\"container1\">")
////							   .replace("<html>", "<div class=\"container1\">")
//							   .replace("<head>", "")
//							   .replace("</head>", "")
////							   .replace("<body>", "")
//							   .replace("<body>", 
////									   "<div class=\"container\">\n" +
//									   "<div class=\"bootstrap-scope\">\n" +
////									   "<style> #ssql_body_contents {" +
////										" @include 'jscss/ssql/ssqlResult1.css'; " +
////										"} </style>\n" +
////									   "<style scoped>\n" +
//									   "<style>\n" +
//									   Jscss.getCssContents() + 
//									   "\n</style>\n"
//									   )
////							   .replace("</body>", "</div>")
//							   .replace("</body>", "")
//							   .replace("</html>", "");			//TODO
					
					
//					html = "<div>\n" + html + "\n</div>\n";		//OK?
//					html = html
					
//					// for test
//					html = 
//							//"<script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>"
//							//+ 
//							"<script src=\"jscss/jquery.scoped.js\"></script>"
//							+ "<script type=\"text/javascript\"> window.onload = function(){ $.scoped(); } </script>"
//							+ "    <p>This will be black.</p>"
//							+ "    <section>"
//							+ "     <style scoped>"
//							+ "       p {color:red;}"
//							+ "     </style> "
//							+ "     <p>This will be red.</p>"
//							+ "    </section>";
////							+ html;
					
					
					Log.ehtmlInfo(html);

					Mobile_HTML5Env.initAllFormFlg();
					Jscss.process();	//goto 20141209
//=======
//		if (Incremental.flag) {
//			//			html_env.getHeader(1);
//			// TODO
////			String id = "ssqlResult" + GlobalEnv.getQueryNum();
////			String xmlFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
////			String path = html_env.outdir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
////			if(GlobalEnv.scrollednum == 0){
////				path = html_env.outdir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
////			}else if(GlobalEnv.scrollednum > 0){
////				path = html_env.outdir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + "-" +GlobalEnv.scrollednum + ".xml";
////			}
////			Incremental.createXML(path, html_env.xmlCode);
////			// 既存のHTMLのヘッダー内に書き込むjsコード
////			Ehtml.appendToHeadFromBody(path);
////			// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
////			Ehtml.createBaseHTMLCode();
////			// add by masato 20151120 end for incremental
//
//		}else{
//			tfe_info.work(data_info);
//
//			if (Ehtml.flag) {
//				String id = "ssqlResult" + GlobalEnv.getQueryNum();
//				String phpFileName = html_env.outfile.substring(html_env.outfile.lastIndexOf(GlobalEnv.OS_FS) + 1, html_env.outfile.length());
//				String path = "";
////				Incremental.createXML(path, html_env.xmlCode);
//				// 既存のHTMLのヘッダー内に書き込むjsコード
//				Ehtml.appendToHeadFromBody(path);
//				// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
//				Ehtml.createBaseHTMLCode();
//				// cssの生成・コピー
//				Jscss.process();
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

				}else{
					try {
						if(!GlobalEnv.isOpt()){
							//changed by goto 20120715 start
							PrintWriter pw;
							if (html_env.charset != null){
								pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(html_env.filename),html_env.charset)));
								Log.info("\nFile encoding: "+html_env.charset);
							}else
								pw = new PrintWriter(new BufferedWriter(new FileWriter(
										html_env.filename)));
							//changed by goto 20120715 end
	
							//changed by goto 20161019 for HTML Formatter
							String html = "";
							if (GlobalEnv.cssout() == null)
								html += html_env.header;
							html += html_env.code;
							html += html_env.footer;
							if(!Start_Parse.sessionFlag)
								html = FileFormatter.process(html);
							pw.println(html);
	
							pw.close();
						}
						//            //xml
						//	        if(GlobalEnv.isOpt()){
						//
						//            	/*
						//            	int i=0;
						//	            while(html_env2.code.indexOf("&",i) != -1){
						//	            	i = html_env2.code.indexOf("&",i);
						//	            	html_env2.code = html_env2.code.replace(i,i+1, "&amp;");
						//	            	i++;
						//	            }
						//	            */
						//
						//	            html_env2.filename = html_env.outfile + ".xml";
						//	            PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(
						//	                    html_env2.filename)));
						//	            if(GlobalEnv.cssout()==null)
						//	            	pw2.println(html_env2.header);
						//	            pw2.println(html_env2.code);
						//	            pw2.println(html_env2.footer);
						//	            pw2.close();
						//	            Mobile_HTML5optimizer xml = new Mobile_HTML5optimizer();
						//	            String xml_str =  xml.generateHtml(html_env2.filename);
						//	        	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
						//	                    html_env.filename)));
						//				pw.println(html_env.header);
						//				pw.println(xml_str);
						//				//StringBuffer footer = new StringBuffer("</div></body></html>");
						//				pw.println(html_env.footer);
						//				pw.close();
						//            }
	
						if(GlobalEnv.cssout()!=null){
							PrintWriter pw3 = new PrintWriter(new BufferedWriter(new FileWriter(
									GlobalEnv.cssout())));
							pw3.println(html_env.header);
							pw3.close();
						}
	
						//create '.htaccess'
						String fn = html_env.getFileParent()+GlobalEnv.OS_FS+".htaccess";
						//					String fn = ".htaccess";
						//					if(!html_env.getFileParent().isEmpty())
						//						fn = html_env.getFileParent()+GlobalEnv.OS_FS+fn;
						if (new File(fn).exists()){
							//System.out.println(".htaccess already exists");
						}else{
							PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(fn), GlobalEnv.DEFAULT_CHARACTER_CODE)));
							pw.println("AddType application/x-httpd-php .html");
							pw.close();
						}
	
						Mobile_HTML5Env.initAllFormFlg();
						Jscss.process();	//goto 20141209
					} catch (FileNotFoundException fe) {
						fe.printStackTrace();
						System.err.println("Error: specified outdirectory \""
								+ html_env.outdir + "\" is not found to write " + html_env.filename );
						GlobalEnv.addErr("Error: specified outdirectory \""
								+ html_env.outdir + "\" is not found to write " + html_env.filename );
						//comment out by chie
						//System.exit(-1);
					} catch (IOException e) {
						System.err.println("Error[HTMLManager]: File IO Error in HTMLManager");
						e.printStackTrace();
						GlobalEnv.addErr("Error[HTMLManager]: File IO Error in HTMLManager");
						//comment out by chie
						//System.exit(-1);
					}
				}
				
			}else{
				Jscss.process();
			}
		}
		//Log.info("End");
	}

	private int lastIndexOf(String string) {
		// TODO ��ư�������줿�᥽�åɡ�������
		return 0;
	}


	private int indexOf(String string) {
		// TODO ��ư�������줿�᥽�åɡ�������
		return 0;
	}


	//tk start///////////////////////////////////////////////////////////////////////
	@Override
	public StringBuffer generateCode2(ITFE tfe_info, ExtList data_info) {
		Mobile_HTML5Env.initAllFormFlg();

		html_env.countfile = 0;
		html_env.code = new StringBuffer();
		html_env.css = new StringBuffer();
		html_env.header = new StringBuffer();
		html_env.footer = new StringBuffer();
		html_env.foreach_flag = GlobalEnv.getForeachFlag();
		html_env.written_classid = new Vector();
		html_env.embedflag = true;


		html_env2.countfile = 0;
		html_env2.code = new StringBuffer();
		html_env2.css = new StringBuffer();
		html_env2.header = new StringBuffer();
		html_env2.footer = new StringBuffer();
		String xml_str = null;
		StringBuffer returncode = new StringBuffer();
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode2]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof Mobile_HTML5G3) {
			tfe_info.work(data_info);
			return html_env.code;
		}
		// ?�ֳ�¦��G3�Ǥʤ�??
		html_env.setOutlineMode();
		tfe_info.work(data_info);

		//        html_env2.header.append("<?xml version=\"1.0\" encoding=\"Shift_JIS\"?><SSQL>");
		//        html_env2.footer.append("</SSQL>");


		if(GlobalEnv.isOpt()){
			int i=0;
			while(html_env2.code.indexOf("&",i) != -1){
				i = html_env2.code.indexOf("&",i);
				html_env2.code = html_env2.code.replace(i,i+1, "&amp;");
				i++;
			}
			StringBuffer xml_string = new StringBuffer();
			xml_string.append(html_env2.header);
			xml_string.append(html_env2.code);
			xml_string.append(html_env2.footer);
			Mobile_HTML5optimizer xml = new Mobile_HTML5optimizer();
			//System.out.println(xml_string);		//commented out by goto 20120620
			xml_str = xml.generateHtml(xml_string);
			returncode.append(xml_str);
		}
		html_env.embedflag = false;

		if(html_env.script.length() >= 5)
		{
			StringBuffer result = new StringBuffer();

			result.append(html_env.script);
			result.append("<end of script>\n");
			result.append(html_env.code);

			return result;
		}
		else
		{
			if(GlobalEnv.isOpt())
				return returncode;
			else
				return html_env.code;

		}
	}
	@Override
	public StringBuffer generateCodeNotuple(ITFE tfe_info) {
		Log.out("no data found");
		html_env.code = new StringBuffer();
		html_env.code.append("<div class=\"nodata\" >");
		html_env.code.append("NO DATA FOUND");
		html_env.code.append("</div>");

		return html_env.code;
	}

	@Override
	public StringBuffer generateCode3(ITFE tfe_info, ExtList data_info) {
		Mobile_HTML5Env.initAllFormFlg();

		html_env.countfile = 0;
		html_env.code = new StringBuffer();
		html_env.css = new StringBuffer();
		html_env.header = new StringBuffer();
		html_env.footer = new StringBuffer();
		html_env.foreach_flag = GlobalEnv.getForeachFlag();
		html_env.written_classid = new Vector();
		html_env.embedflag = true;
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof Mobile_HTML5G3) {
			tfe_info.work(data_info);
			return html_env.code;
		}
		// ?�ֳ�¦��G3�Ǥʤ�??


		html_env.setOutlineMode();
		tfe_info.work(data_info);
		//        html_env.getCSS();
		html_env.embedflag = false;
		Log.out("header : "+ html_env.header);
		return html_env.css;
	}

	@Override
	public StringBuffer generateCode4(ITFE tfe_info, ExtList data_info) {
		Mobile_HTML5Env.initAllFormFlg();
		html_env.countfile = 0;
		html_env.code = new StringBuffer();
		html_env.css = new StringBuffer();
		html_env.header = new StringBuffer();
		html_env.footer = new StringBuffer();
		html_env.foreach_flag = GlobalEnv.getForeachFlag();
		html_env.written_classid = new Vector();

		html_env2.countfile = 0;
		html_env2.code = new StringBuffer();
		html_env2.css = new StringBuffer();
		html_env2.header = new StringBuffer();
		html_env2.footer = new StringBuffer();
		html_env2.foreach_flag = GlobalEnv.getForeachFlag();
		html_env2.written_classid = new Vector<String>();

		Mobile_HTML5Env localenv = new Mobile_HTML5Env();

		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");


		// ?�ֳ�¦��G3�Ǥʤ�??
		if(Ehtml.isEhtml2())
			html_env.filename = html_env.outfile + Ehtml.getFileName() + Compiler.getExtension();
		else
			html_env.filename = html_env.outfile + Compiler.getExtension();
		html_env2.filename = html_env.outfile + ".xml";

		html_env.setOutlineMode();
		tfe_info.work(data_info);

		html_env.getHeader(1);
		html_env.getFooter(1);
		html_env.embedflag = false;
		Log.out("header : "+ html_env.header);

		StringBuffer headfoot = new StringBuffer(html_env.header + " ###split### " + html_env.footer);

		return headfoot;
	}
	@Override
	public StringBuffer generateCssfile(ITFE tfe_info, ExtList data_info) {

		html_env.countfile = 0;
		html_env.code = new StringBuffer();
		html_env.css = new StringBuffer();
		html_env.header = new StringBuffer();
		html_env.footer = new StringBuffer();
		html_env.foreach_flag = GlobalEnv.getForeachFlag();
		html_env.written_classid = new Vector();
		html_env.embedflag = true;
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		html_env.setOutlineMode();
		tfe_info.work(data_info);
		html_env.embedflag = false;
		Log.out("header : "+ html_env.header);
		return html_env.cssfile;
	}
	//tk end///////////////////////////////////////////////////////////////////////////////

	private void getOutfilename() {
		String file = GlobalEnv.getfilename();
		String outdir = GlobalEnv.getoutdirectory();
		String outfile = GlobalEnv.getoutfilename();
		html_env.outdir = outdir;

		/*
		 * ���ϥե���?(outfilename)�����ꤵ?�Ƥ�???
		 * html_env.outfile��globalenv.outfilename�ˤ�?
		 * ��?�ʳ��ΤȤ��ϥ���?�ե���?��̾��(filename)�ˤ�?
		 */
		if (GlobalEnv.getQuery()!=null) {
			// modified by masato 20151118 for ehtml and incremental
			if(Ehtml.flag || Incremental.flag){
				// TODO masato 複数のクエリをどうページ内で実行できるようにdivのid等にする必要あり
				html_env.outfile = outfile.substring(0, outfile.toLowerCase().indexOf("."));
			} else if (!GlobalEnv.getoutfilename().isEmpty()) {
				html_env.outfile = GlobalEnv.getoutfilename().substring(0, outfile.toLowerCase().indexOf("."));
			} else {
				html_env.outfile = "./fromquery";
			}
		}else if (outfile == null) {
			if (file.toLowerCase().indexOf(".sql")>0) {
				html_env.outfile = file.substring(0, file.toLowerCase().indexOf(".sql"));
			} else if (file.toLowerCase().indexOf(".ssql")>0) {
				html_env.outfile = file.substring(0, file.toLowerCase().indexOf(".ssql"));
			}
		} else {
			html_env.outfile = getOutfile(outfile);
		}

		if (html_env.outfile.indexOf("/") > 0) {
			html_env.linkoutfile = html_env.outfile.substring(html_env.outfile
					.lastIndexOf("/") + 1);
		} else {
			html_env.linkoutfile = html_env.outfile;
		}
		/*
        //tk start
        if(html_env.outfile.lastIndexOf("\\") != -1)
        {
        	html_env.outfile = html_env.outfile.substring(html_env.outfile.lastIndexOf("\\"));
        	Log.out("outfile log:"+html_env.outfile);
        }
        //tk end
		 */
		/*
		 * ������ǥ�?����?(outdirectory)�����ꤵ?�Ƥ�???
		 * outdirectory��filename��Ĥʤ�����Τ�file�Ȥ�?
		 */



		if (outdir != null) {
			connectOutdir(outdir, outfile);
		}
	}

	private String getOutfile(String outfile) {
		String out = new String();
		if (outfile.indexOf(".html") > 0) {
			out = outfile.substring(0, outfile.indexOf(".html"));
		} else {
			out = outfile;
		}
		return out;
	}

	private void connectOutdir(String outdir, String outfile) {
		//added by goto 20120627 start
		String fileDir = new File(html_env.outfile).getAbsoluteFile().getParent();
		if(fileDir.length() < html_env.outfile.length()
				&& fileDir.equals(html_env.outfile.substring(0,fileDir.length())))
			html_env.outfile = html_env.outfile.substring(fileDir.length()+1);	//���Хѥ��ե�����̾
		//added by goto 20120627 end

		String tmpqueryfile = new String();
		if (html_env.outfile.indexOf("/") > 0) {
			if (outfile != null) {
				if (html_env.outfile.startsWith(".")
						|| html_env.outfile.startsWith("/")) {
					tmpqueryfile = html_env.outfile.substring(html_env.outfile
							.indexOf("/") + 1);
				}
			} else {
				tmpqueryfile = html_env.outfile.substring(html_env.outfile
						.lastIndexOf("/") + 1);
			}
		} else {
			tmpqueryfile = html_env.outfile;
		}
		if (!outdir.endsWith("/")) {
			outdir = outdir.concat("/");
		}
		html_env.outfile = outdir.concat(tmpqueryfile);
	}

	@Override
	public void finish() {

	}

	//initAllValiables of Mobile_HTML5
	public static void initAllValiables() {
		//TODO
		Jscss.initValiables();
	}
}
