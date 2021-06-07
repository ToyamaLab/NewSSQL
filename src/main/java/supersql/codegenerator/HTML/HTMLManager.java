package supersql.codegenerator.HTML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Vector;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.FileFormatter;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class HTMLManager extends Manager implements Serializable {

	public HTMLEnv htmlEnv;
	public HTMLEnv htmlEnv2;

	public HTMLManager(HTMLEnv henv, HTMLEnv henv2) {
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	private void connectOutdir(String outdir, String outfile) {
		// added by goto 20120627 start
		String fileDir = new File(htmlEnv.outFile).getAbsoluteFile()
				.getParent();
		if (fileDir.length() < htmlEnv.outFile.length()
				&& fileDir
						.equals(htmlEnv.outFile.substring(0, fileDir.length())))
			htmlEnv.outFile = htmlEnv.outFile.substring(fileDir.length() + 1); // 鐃緒申鐃出パワ申鐃春ワ申鐃緒申鐃緒申名
		// added by goto 20120627 end

		String tmpqueryfile = new String();
		if (htmlEnv.outFile.indexOf("/") > 0) {
			if (outfile != null) {
				if (htmlEnv.outFile.startsWith(".")
						|| htmlEnv.outFile.startsWith("/")) {
					tmpqueryfile = htmlEnv.outFile.substring(htmlEnv.outFile
							.indexOf("/") + 1);
				}
			} else {
				tmpqueryfile = htmlEnv.outFile.substring(htmlEnv.outFile
						.lastIndexOf("/") + 1);
			}
		} else {
			tmpqueryfile = htmlEnv.outFile;
		}
		if (!outdir.endsWith("/")) {
			outdir = outdir.concat("/");
		}
		htmlEnv.outFile = outdir.concat(tmpqueryfile);
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

	private int indexOf(String string) {
		// TODO 鐃緒申動鐃緒申鐃緒申鐃緒申鐃曙た鐃潤ソ鐃獣ド¥申鐃緒申鐃緒申鐃緒申
		return 0;
	}

	private int lastIndexOf(String string) {
		// TODO 鐃緒申動鐃緒申鐃緒申鐃緒申鐃曙た鐃潤ソ鐃獣ド¥申鐃緒申鐃緒申鐃緒申
		return 0;
	}

	protected void getOutfilename() {
		String file = GlobalEnv.getfilename();
		String outdir = GlobalEnv.getoutdirectory();
		String outfile = GlobalEnv.getoutfilename();
		htmlEnv.outDir = outdir;

		/*
		 * 鐃緒申鐃熟フワ申鐃緒申?(outfilename)鐃緒申鐃緒申鐃所さ?鐃銃わ申???
		 * html_env.outfile鐃緒申globalenv.outfilename鐃祝わ申?
		 * 鐃緒申?鐃淑鰹申鐃塾とわ申鐃熟ワ申鐃緒申?鐃春ワ申鐃緒申?鐃緒申名鐃緒申(filename)鐃祝わ申?
		 */
		if (GlobalEnv.getQuery() != null) {
			// modified by masato 20151118 for ehtml and incremental
			if(Ehtml.flag || Incremental.flag){
				// TODO masato 複数のクエリをどうページ内で実行できるようにdivのid等にする必要あり
				htmlEnv.outFile = outfile.substring(0, outfile.toLowerCase().indexOf("."));
			} else if (!GlobalEnv.getoutfilename().isEmpty()) {
				htmlEnv.outFile = GlobalEnv.getoutfilename().substring(0, outfile.toLowerCase().indexOf("."));;
			} else {
				htmlEnv.outFile = "./fromquery";
			}
		} else if (outfile == null) {
			if (file.toLowerCase().indexOf(".sql") > 0) {
				htmlEnv.outFile = file.substring(0,
						file.toLowerCase().indexOf(".sql"));
			} else if (file.toLowerCase().indexOf(".ssql") > 0) {
				htmlEnv.outFile = file.substring(0,
						file.toLowerCase().indexOf(".ssql"));
			}//tbt There is no code for other 拡張子(e.g. sspl, txt)
		} else {
			htmlEnv.outFile = getOutfile(outfile);
		}

		if (htmlEnv.outFile.indexOf("/") > 0) {
			htmlEnv.linkOutFile = htmlEnv.outFile.substring(htmlEnv.outFile
					.lastIndexOf("/") + 1);
		} else {
			htmlEnv.linkOutFile = htmlEnv.outFile;
		}
		/*
		 * //tk start if(html_env.outfile.lastIndexOf("\\") != -1) {
		 * html_env.outfile =
		 * html_env.outfile.substring(html_env.outfile.lastIndexOf("\\"));
		 * Log.out("outfile log:"+html_env.outfile); } //tk end
		 */
		/*
		 * 鐃緒申鐃緒申鐃緒申妊鐃����?(outdirectory)�����ꤵ?�Ƥ�???
		 * outdirectory��filename��Ĥʤ�����Τ�file�Ȥ�?
		 */

		if (outdir != null) {
			connectOutdir(outdir, outfile);
		}
		// added by masato 201151123 where xml is generated
//		if(Ehtml.flag || Incremental.flag){
//			htmlEnv.outFile = htmlEnv.outFile.substring(0, outfile.toLowerCase().lastIndexOf(GlobalEnv.OS_FS));
//		}
	}

	@Override
	public void finish() {

	}

	@Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {
		HTMLEnv.initAllFormFlg();
		// added by masato 20150914
		HTMLEnv.initXML();
		htmlEnv.countFile = 0;
		htmlEnv.code = new StringBuffer();
		HTMLEnv.css = new StringBuffer();
		HTMLEnv.header = new StringBuffer();
		htmlEnv.footer = new StringBuffer();
		htmlEnv.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv.writtenClassId = new Vector();
		htmlEnv.notWrittenClassId = new Vector();
		htmlEnv2.countFile = 0;
		htmlEnv2.code = new StringBuffer();
		htmlEnv2.css = new StringBuffer();
		htmlEnv2.header = new StringBuffer();
		htmlEnv2.footer = new StringBuffer();
		htmlEnv2.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv2.writtenClassId = new Vector<String>();
		// HTMLEnv localenv = new HTMLEnv();
		/*** start oka ***/

		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof HTMLG3) {
			tfe_info.work(data_info);
			return;
		}

		// ?�ֳ�¦��G3�Ǥʤ�??]
		htmlEnv.fileName = htmlEnv.outFile + ".html";
		htmlEnv2.fileName = htmlEnv.outFile + ".xml";

		htmlEnv.setOutlineMode();

		if (data_info.size() == 0
		// added by goto 20130306 "FROM�ʤ��������к� 3/3"
//<<<<<<< HEAD
//				&& !DataConstructor.SQL_string
//						.equals("SELECT DISTINCT  FROM ;") && !DataConstructor.SQL_string.equals("SELECT  FROM ;")) {
//			Log.out("no data");
//			htmlEnv.code.append("<div class=\"nodata\" >");
//			htmlEnv.code.append("NO DATA FOUND");
//			htmlEnv.code.append("</div>");
//=======
				/*&& !DataConstructor.SQL_string
						.equals("SELECT DISTINCT  FROM ;") && !DataConstructor.SQL_string.equals("SELECT  FROM ;")*/) {
//			Log.out("no data");
//			htmlEnv.code.append("<div class=\"nodata\" >");
//			htmlEnv.code.append("NO DATA FOUND");
//			htmlEnv.code.append("</div>");
			tfe_info.work(data_info);

//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
		} else
			tfe_info.work(data_info);
		

		// add by masato 20151118 start for incremental
		if (Ehtml.flag) {
			// 生成するXMLは埋め込み先のphp or htmlファイルのある場所にその名前.xmlで生成
			// TODO masato 複数のクエリをどうページ内で実行できるようにdivのid等にする必要あり
			String id = "ssqlResult" + GlobalEnv.getQueryNum();
			String phpFileName = htmlEnv.outFile.substring(htmlEnv.outFile.lastIndexOf(GlobalEnv.OS_FS) + 1, htmlEnv.outFile.length());
			//TODO -scrolled 1 -> ssqlresult1-1.xml, -scrolled == null -> ssqlresult1.xml

			String xmlFileName = htmlEnv.outFile.substring(htmlEnv.outFile.lastIndexOf(GlobalEnv.OS_FS) + 1, htmlEnv.outFile.length());
			String path = htmlEnv.outDir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
//			String path = "";
			Incremental.createXML(path, htmlEnv.xmlCode);
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
		else if (Incremental.flag) {
			// TODO
			String id = "ssqlResult" + GlobalEnv.getQueryNum();
			String xmlFileName = htmlEnv.outFile.substring(htmlEnv.outFile.lastIndexOf(GlobalEnv.OS_FS) + 1, htmlEnv.outFile.length());
			String path = htmlEnv.outDir + GlobalEnv.OS_FS + "GeneratedXML" + GlobalEnv.OS_FS + xmlFileName + GlobalEnv.OS_FS + id + ".xml";
			Incremental.createXML(path, htmlEnv.xmlCode);
			// 既存のHTMLのヘッダー内に書き込むjsコード
			Ehtml.appendToHeadFromBody(path);
			// XMLをparseして生成したテーブルをappendするhtmlコード（divタグ）
			Ehtml.createBaseHTMLCode();
			// add by masato 20151120 end for incremental

		} else {
			htmlEnv.getHeader();
			htmlEnv.getFooter();
//			htmlEnv2.header.append("<?xml version=\"1.0\" encoding=\""
//					+ Utils.getEncode() + "\"?><SSQL>");
//			htmlEnv2.footer.append("</SSQL>");
			try {
				if (CodeGenerator.getMedia().equalsIgnoreCase("html")) {
					if (!GlobalEnv.isOpt()) {
						// changed by goto 20120715 start
						PrintWriter pw;
						if (htmlEnv.charset != null) {
							pw = new PrintWriter(new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(
													htmlEnv.fileName),
											htmlEnv.charset)));
							Log.info("File encoding: " + htmlEnv.charset);
						} else
							pw = new PrintWriter(new BufferedWriter(
									new FileWriter(htmlEnv.fileName)));
						// Log.info("File encoding: "+((html_env.charset!=null)?
						// html_env.charset : "UTF-8"));
						// changed by goto 20120715 end

						//changed by goto 20161019 for HTML Formatter
						String html = "";
						if (GlobalEnv.cssout() == null)
						html += htmlEnv.header;
						html += htmlEnv.code;
						html += htmlEnv.footer;
						html = FileFormatter.process(html);
						pw.println(html);

						pw.close();
					}
					// xml
					if (GlobalEnv.isOpt()) {

						/*
						 * int i=0; while(html_env2.code.indexOf("&",i) != -1){
						 * i = html_env2.code.indexOf("&",i); html_env2.code =
						 * html_env2.code.replace(i,i+1, "&amp;"); i++; }
						 */

						htmlEnv2.fileName = htmlEnv.outFile + ".xml";
						PrintWriter pw2 = new PrintWriter(new BufferedWriter(
								new FileWriter(htmlEnv2.fileName)));
						if (GlobalEnv.cssout() == null)
							pw2.println(htmlEnv2.header);
						pw2.println(htmlEnv2.code);
						pw2.println(htmlEnv2.footer);
						pw2.close();
						HTMLoptimizer xml = new HTMLoptimizer();
						String xml_str = xml.generateHtml(htmlEnv2.fileName);
						PrintWriter pw = new PrintWriter(new BufferedWriter(
								new FileWriter(htmlEnv.fileName)));
						pw.println(htmlEnv.header);
						pw.println(xml_str);
						// StringBuffer footer = new
						// StringBuffer("</div></body></html>");
						pw.println(htmlEnv.footer);
						pw.close();
					}

					if (GlobalEnv.cssout() != null) {
						PrintWriter pw3 = new PrintWriter(new BufferedWriter(
								new FileWriter(GlobalEnv.cssout())));
						pw3.println(htmlEnv.header);
						pw3.close();
					}
					// add 20141204 masato for ehtml
				} else {
					
					Log.ehtmlInfo("=-=-=-=");
					Log.ehtmlInfo(htmlEnv.header);
					Log.ehtmlInfo(htmlEnv.code);
					Log.ehtmlInfo(htmlEnv.footer);
				}

				HTMLEnv.initAllFormFlg();
				Jscss.process(); // goto 20141209
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
				Log.err("Error: specified outdirectory \"" + htmlEnv.outDir
						+ "\" is not found to write " + htmlEnv.fileName);
				GlobalEnv.addErr("Error: specified outdirectory \""
						+ htmlEnv.outDir + "\" is not found to write "
						+ htmlEnv.fileName);
				// comment out by chie
				// System.exit(-1);
			} catch (IOException e) {
				System.err
						.println("Error[HTMLManager]: File IO Error in HTMLManager");
				e.printStackTrace();
				GlobalEnv
						.addErr("Error[HTMLManager]: File IO Error in HTMLManager");
				// comment out by chie
				// System.exit(-1);
			}
		}
	}

	// tk
	// start///////////////////////////////////////////////////////////////////////
	@Override
	public StringBuffer generateCode2(ITFE tfe_info, ExtList data_info) {
		HTMLEnv.initAllFormFlg();

		htmlEnv.countFile = 0;
		htmlEnv.code = new StringBuffer();
		htmlEnv.css = new StringBuffer();
		htmlEnv.header = new StringBuffer();
		htmlEnv.footer = new StringBuffer();
		htmlEnv.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv.writtenClassId = new Vector();
		htmlEnv.embedFlag = true;

		htmlEnv2.countFile = 0;
		htmlEnv2.code = new StringBuffer();
		htmlEnv2.css = new StringBuffer();
		htmlEnv2.header = new StringBuffer();
		htmlEnv2.footer = new StringBuffer();
		String xml_str = null;
		StringBuffer returncode = new StringBuffer();
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode2]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof HTMLG3) {
			tfe_info.work(data_info);
			return htmlEnv.code;
		}
		// ?�ֳ�¦��G3�Ǥʤ�??
		htmlEnv.setOutlineMode();
		tfe_info.work(data_info);

//		htmlEnv2.header
//				.append("<?xml version=\"1.0\" encoding=\"Shift_JIS\"?><SSQL>");
//		htmlEnv2.footer.append("</SSQL>");

		if (GlobalEnv.isOpt()) {
			int i = 0;
			while (htmlEnv2.code.indexOf("&", i) != -1) {
				i = htmlEnv2.code.indexOf("&", i);
				htmlEnv2.code = htmlEnv2.code.replace(i, i + 1, "&amp;");
				i++;
			}
			StringBuffer xml_string = new StringBuffer();
			xml_string.append(htmlEnv2.header);
			xml_string.append(htmlEnv2.code);
			xml_string.append(htmlEnv2.footer);
			HTMLoptimizer xml = new HTMLoptimizer();
			// System.out.println(xml_string); //commented out by goto 20120620
			xml_str = xml.generateHtml(xml_string);
			returncode.append(xml_str);
		}
		htmlEnv.embedFlag = false;

		if (htmlEnv.script.length() >= 5) {
			StringBuffer result = new StringBuffer();

			result.append(htmlEnv.script);
			result.append("<end of script>\n");
			result.append(htmlEnv.code);

			return result;
		} else {
			if (GlobalEnv.isOpt())
				return returncode;
			else
				return htmlEnv.code;

		}
	}

	@Override
	public StringBuffer generateCode3(ITFE tfe_info, ExtList data_info) {
		HTMLEnv.initAllFormFlg();

		htmlEnv.countFile = 0;
		htmlEnv.code = new StringBuffer();
		htmlEnv.css = new StringBuffer();
		htmlEnv.header = new StringBuffer();
		htmlEnv.footer = new StringBuffer();
		htmlEnv.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv.writtenClassId = new Vector();
		htmlEnv.embedFlag = true;
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		// ?�ֳ�¦��G3��??
		if (tfe_info instanceof HTMLG3) {
			tfe_info.work(data_info);
			return htmlEnv.code;
		}
		// ?�ֳ�¦��G3�Ǥʤ�??

		htmlEnv.setOutlineMode();
		tfe_info.work(data_info);
		// html_env.getCSS();
		htmlEnv.embedFlag = false;
		Log.out("header : " + htmlEnv.header);
		return htmlEnv.css;
	}

	@Override
	public StringBuffer generateCode4(ITFE tfe_info, ExtList data_info) {
		HTMLEnv.initAllFormFlg();
		htmlEnv.countFile = 0;
		htmlEnv.code = new StringBuffer();
		htmlEnv.css = new StringBuffer();
		htmlEnv.header = new StringBuffer();
		htmlEnv.footer = new StringBuffer();
		htmlEnv.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv.writtenClassId = new Vector();

		htmlEnv2.countFile = 0;
		htmlEnv2.code = new StringBuffer();
		htmlEnv2.css = new StringBuffer();
		htmlEnv2.header = new StringBuffer();
		htmlEnv2.footer = new StringBuffer();
		htmlEnv2.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv2.writtenClassId = new Vector<String>();

		// HTMLEnv localenv = new HTMLEnv();

		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		// ?�ֳ�¦��G3�Ǥʤ�??
		htmlEnv.fileName = htmlEnv.outFile + ".html";
		htmlEnv2.fileName = htmlEnv.outFile + ".xml";

		htmlEnv.setOutlineMode();
		tfe_info.work(data_info);

		htmlEnv.getHeader();
		htmlEnv.getFooter();
		htmlEnv.embedFlag = false;
		Log.out("header : " + htmlEnv.header);

		StringBuffer headfoot = new StringBuffer(htmlEnv.header
				+ " ###split### " + htmlEnv.footer);

		return headfoot;
	}

	@Override
	public StringBuffer generateCodeNotuple(ITFE tfe_info) {
		Log.out("no data found");
		htmlEnv.code = new StringBuffer();
		htmlEnv.code.append("<div class=\"nodata\" >");
		htmlEnv.code.append("NO DATA FOUND");
		htmlEnv.code.append("</div>");

		return htmlEnv.code;
	}

	@Override
	public StringBuffer generateCssfile(ITFE tfe_info, ExtList data_info) {

		htmlEnv.countFile = 0;
		htmlEnv.code = new StringBuffer();
		htmlEnv.css = new StringBuffer();
		htmlEnv.header = new StringBuffer();
		htmlEnv.footer = new StringBuffer();
		htmlEnv.foreachFlag = GlobalEnv.getForeachFlag();
		htmlEnv.writtenClassId = new Vector();
		htmlEnv.embedFlag = true;
		// ���Ϥ�?�ե���?̾����?
		getOutfilename();

		Log.out("[HTMLManager:generateCode]");

		htmlEnv.setOutlineMode();
		tfe_info.work(data_info);
		htmlEnv.embedFlag = false;
		Log.out("header : " + htmlEnv.header);
		return htmlEnv.cssFile;
	}
	// tk
	// end///////////////////////////////////////////////////////////////////////////////
}
