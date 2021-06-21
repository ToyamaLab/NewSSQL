package supersql.codegenerator.HTML;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

import org.antlr.v4.parse.ANTLRParser.wildcard_return;
import org.antlr.v4.runtime.CommonToken;

import com.ibm.db2.jcc.a.a;
import com.ibm.db2.jcc.sqlj.m;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Decorator;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.FuncArg;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.codegenerator.TFE;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

public class HTMLFunction extends Function implements Serializable{

	protected static String updateFile;
	private boolean link1 = false; //added by goto 20161025 for link1/foreach1

	public static boolean HTMLFunctionFlag = false;

	public static String createForm(DecorateList decos) {
		new String();
		String path = new String();
		String form = new String();

		// System.out.println(this.getAtt("label"));
		if (decos.containsKey("path")) {
			path = decos.getStr("path").replaceAll("\"", "");
		} else {
			path = ".";
		}

		form = "<form method=\"POST\" action=\"" + path
				+ "/supersql.form.FormServlet\" " + "name=\""
				+ HTMLEnv.getFormName() + "\" " + ">";

		form += "<input type=\"hidden\" name=\"configfile\" value=\""
				+ GlobalEnv.getFileDirectory() + "/config.ssql\" />";

		if (decos.containsKey("link")) {
			opt(decos.getStr("link"));
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path
					+ "/" + decos.getStr("link").replaceAll("\"", "") + "\" />";
		}

		if (decos.containsKey("cond")) {
			form += "<input type=\"hidden\" name=\"cond1\" value=\""
					+ decos.getStr("cond").replaceAll("\"", "") + "\" />";
		}

		if (decos.containsKey("updatefile")) {
			String tmp = opt(decos.getStr("updatefile"));
			updateFile = "<input type=\"hidden\" name=\"updatefile\" value=\""
					+ path + "/" + tmp + "\" />";
			form += updateFile;
		}
		if (decos.containsKey("linkfile")) {
			opt(decos.getStr("linkfile"));
			form += "<input type=\"hidden\" name=\"linkfile\" value=\"" + path
					+ "/" + decos.getStr("linkfile").replaceAll("\"", "")
					+ "\" />";
		}
		if (decos.containsKey("cond")) {
			form += "<input type=\"hidden\" name=\"linkcond\" value=\""
					+ decos.getStr("cond").replaceAll("\"", "") + "\" />";
		}
		Log.out(form);
		HTMLEnv.setFormDetail(form);
		return form;
	}

	public static String opt(String s) {
		if (s.contains("\"")) {
			s = s.replaceAll("\"", "");
		}
		if (s.startsWith("./")) {
			s = s.substring(2, s.length());
		}
		if (s.startsWith("/")) {
			s = s.substring(1, s.length());
		}
		return s;
	}

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;

	public HTMLFunction() {

	}

	// ���󥹥ȥ饯��
	public HTMLFunction(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		super();
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	// // for practice 2012/02/09
	// private void Func_button() {
	// String statement ="";
	// String button_media = this.getArgs().get(0).toString();
	// if (button_media.equals("\"goback\"")){
	// // ���ܥ������������
	// statement =
	// "<form><INPUT type=\"button\" onClick='history.back();' value=\"���\"></form>";
	// }else if(button_media.equals("\"bookmark\"")){
	// // �����˥֥å��ޡ�������򵭽Ҥ���
	// }else if(button_media.equals("\"facebook\"")){
	// // facebook�Τ����͡��ܥ���ν���򵭽Ҥ���
	// }else{
	// // �ä˻��꤬�ʤ�������ܥ���ˤ���
	// statement =
	// "<form><INPUT type=\"button\" onClick='history.back();' value=\"���\"></form>";
	// }
	// // �ư����˽����̤�HTML�˽񤭤���
	// html_env.code.append(statement);
	// return;
	// }

	private String createForm() {
		String path = new String();
		String form = new String();

		if (this.getAtt("path") != null && !this.getAtt("path").isEmpty()) {
			path = this.getAtt("path").replaceAll("\"", "");
		} else {
			path = ".";
		}
		form += "<form method=\"POST\" action=\"" + path
				+ "/servlet/supersql.form.FormServlet\"" + ">";

		form += "<input type=\"hidden\" name=\"configfile\" value=\"" + path
				+ "/config.ssql\" />";

		if (this.getAtt("link") != null && !this.getAtt("link").isEmpty()) {
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path
					+ "/" + this.getAtt("link").replaceAll("\"", "") + "\" />";
		} else if (this.getAtt("linkfile") != null
				&& !this.getAtt("linkfile").isEmpty()) {
			form += "<input type=\"hidden\" name=\"sqlfile\" value=\"" + path
					+ "/" + this.getAtt("linkfile").replaceAll("\"", "")
					+ "\" />";
		}
		/*
		 * if(!this.getAtt("default").equals(null)){ form +=
		 * "<input type=\"hidden\" name=\"value1\" value=\""
		 * +this.getAtt("default").replaceAll("\"", "")+"\" />"; }
		 */

		if (this.getAtt("cond") != null && !this.getAtt("cond").isEmpty()) {
			if (!this.getAtt("cond").replaceAll("\"", "").isEmpty())
				form += "<input type=\"hidden\" name=\"cond1\" value=\""
						+ this.getAtt("cond").replaceAll("\"", "") + "\" />";
		}

		String att = new String();
		Integer attNo = 1;
		while (!this.getAtt("att" + attNo).equals("")) {
			if (attNo > 1)
				att += ",";
			att += this.getAtt("att" + attNo);
			attNo++;
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if (attNo == 1 && !this.getAtt("att").equals("")) {
			att += this.getAtt("att");
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if (this.getAtt("update") != null && !this.getAtt("update").isEmpty()) {
			form += "<input type=\"hidden\" name=\"updatefile\" value=\""
					+ path + "/" + this.getAtt("update").replaceAll("\"", "")
					+ "(" + att + ")\" />";
		} else if (this.getAtt("updatefile") != null
				&& !this.getAtt("updatefile").isEmpty()) {
			form += "<input type=\"hidden\" name=\"updatefile\" value=\""
					+ path + "/"
					+ this.getAtt("updatefile").replaceAll("\"", "") + "("
					+ att + ")\" />";
		}

		Log.out(form);
		return form;
	}

	// added by chie 2009 func form checkbox
	private void Func_checkbox() {
		Func_FormCommon("checkbox");

		if (!this.getAtt("checked").equals("")) {
			HTMLEnv.setChecked(this.getAtt("checked"));
		}

		return;
	}

	// tk
	// start//////////////////////////////////////////////////////////////////////////////
	private void Func_embed(ExtList data_info) {
		String file = this.getAtt("file");
		String where = this.getAtt("where");
		String att = this.getAtt("att");
		this.getAtt("border");
		String att2 = this.getAtt("attString");
		String condition = new String();
		this.getAtt("defcond");

		Log.out("function embed");
		Log.out("isNewEmbed:" + GlobalEnv.isNewEmbed());

		boolean is_hidden = false;

		if (decos.containsKey("status"))
			if (decos.getStr("status").compareTo("hidden") == 0)
				is_hidden = true;

		// for tab
		if (decos.containsKey("tab")) {
			htmlEnv.code.append("<div id=\"myTab\" ");

			if (decos.containsKey("class"))
				//htmlEnv.code.append("class=\"" + decos.getStr("class") + "\"");
				htmlEnv.code.append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok


			htmlEnv.code.append(">\n");
			htmlEnv.code.append("<div id=\"mTab\" class=\"yui-navset\">\n");

			htmlEnv.code.append("</div></div>\n");

			htmlEnv.script
					.append("var mTab = new YAHOO.widget.TabView(\"mTab\");");
			htmlEnv.script
					.append("new YAHOO.util.DDTarget(\"myTab\", \"myTab\");");

			return;
		}

		if (!is_hidden) {
			htmlEnv.code.append("<table class=\"att "
					+ htmlEnv.getOutlineModeAtt() + " ");

//			if (decos.containsKey("class"))
//				htmlEnv.code.append(decos.getStr("class"));
//			else
//				htmlEnv.code.append(HTMLEnv.getClassID(this));
			htmlEnv.code.append(Modifier.getClassName(decos, HTMLEnv.getClassID(this)));//kotani_idmodifier_ok

			htmlEnv.code.append("\"");



			htmlEnv.code.append("><tr><td>");
		}

		// for ajax div id
		// //////////////////////////////////////////////////////

		String divname = new String();
		boolean has_divid = false;

		if (decos.containsKey("divid")) {
			has_divid = true;
			Log.out("embed contains decos with divid");
			String tmpdivid = decos.getStr("divid");
			String tmp;
			String ans;

			if (tmpdivid.contains("+")) {
				ans = tmpdivid.substring(0, tmpdivid.indexOf("+"));
				tmp = tmpdivid.substring(tmpdivid.indexOf("+") + 1,
						tmpdivid.length());

				if (tmp.compareTo("att") == 0) {
					tmp = att;
				}
				divname = ans + "_" + tmp;
				Log.out("ans :" + ans + " tmp:" + tmp + " divname:" + divname);
			} else {
				divname = decos.getStr("divid");
			}
		}/*
		 * else { //online file if(file.contains("/")) { divname =
		 * file.substring(file.lastIndexOf("/")+1,file.indexOf(".sql")); }
		 * //ofline file else if(file.contains("\\")) {
		 * Log.out(" // index"+file.indexOf(".sql")); divname =
		 * file.substring(file.lastIndexOf("\\")+1,file.indexOf(".sql")); }
		 * //only file name else { divname =
		 * file.substring(0,file.indexOf(".sql")); }
		 *
		 * }
		 */
		if (GlobalEnv.isAjax() && decos.containsKey("droppable")) {
			htmlEnv.script.append("new YAHOO.util.DDTarget(\"" + divname
					+ "\", \"" + divname + "\");");
		}
		// ajax & decos contains status=hidden
		if (is_hidden && GlobalEnv.isAjax()) {

			htmlEnv.code.append("<div id=\"" + divname + "\" ");

//			if (decos.containsKey("class"))
//				htmlEnv.code.append("class=\"" + decos.getStr("class") + "\" ");
			Modifier.getClassAndIdMOdifierValues(decos);

			htmlEnv.code.append("></div>");
			Log.out("<div id=" + divname + "></div>");

			return;
		}
		// end ajax divname ////////////////////////////////////////////////

		/*
		 * if(border.compareTo("1") == 0) {} else html_env.css.append(
		 * ".embed { vertical-align : text-top; padding : 0px ; margin : 0px; border: 0px,0px,0px,0px; width: 100%;}"
		 * );
		 */
		if (att.compareTo("") != 0) {
			condition = condition + where + att;
		} else if (att2.compareTo("") != 0) {
			condition = condition + where + "'" + att2 + "'";
		}
		// store original config
		Hashtable tmphash = GlobalEnv.getEnv();

		// set new config for embed
		// String[] args = {"-f",file,"-cond",condition,"-debug"};
		// Log.out("cond:"+condition);
		String[] args;
		if (GlobalEnv.isAjax()) {
			if (condition.equals("")) {
				args = new String[3];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-ajax";
				// args[3] = "-debug";

			} else {
				args = new String[5];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				args[4] = "-ajax";
				// args[5] = "-debug";
			}
		} else {
			if (GlobalEnv.isOpt()) {
				args = new String[5];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				args[4] = "-optimizer";
				// args[5] = "-debug";
			} else {
				args = new String[4];
				args[0] = "-f";
				args[1] = file;
				args[2] = "-cond";
				args[3] = condition;
				// args[4] = "-debug";
			}
		}

		htmlEnv.embedCount++;

		if (file.contains(".sql") || file.contains(".ssql")) {

			String makedfilename = file.substring(file.lastIndexOf("\\") + 1,
					file.indexOf("."));

			if (att.compareTo("") != 0)
				makedfilename = makedfilename.concat("_" + att);
			if (att2.compareTo("") != 0)
				makedfilename = makedfilename.concat("_" + att2);

			makedfilename = makedfilename.concat(".html");

			Log.out("embed tmpfilename:" + makedfilename + " option:"
					+ GlobalEnv.getEmbedOption());

			File makedfile = new File(GlobalEnv.getEmbedTmp(), makedfilename);
			if (makedfile.exists() && GlobalEnv.isNewEmbed() == 1) {
				Log.out("[Enter new Embed]");
				Log.out("embed read tmp file");
				BufferedReader dis;
				String line = new String();
				try {
					dis = new BufferedReader(new FileReader(makedfile));

					try {
						while (!line.equalsIgnoreCase(" ")) {
							Log.out("line : " + line);
							line = dis.readLine();
							if (line != null)
								htmlEnv.code.append(line);
						}
					} catch (NullPointerException e) {
						Log.out("no more lines");
					}

					dis.close();
				} catch (IOException ioe) {
					System.out.println("IOException: " + ioe);
				}
			} else {
				Log.out("embed make file");

				GlobalEnv.setGlobalEnvEmbed(args);

				Start_Parse parser;
				if (file.contains("http")) {
					parser = new Start_Parse("online");
				} else {
					parser = new Start_Parse(10000 * (htmlEnv.embedCount + 1));
				}

				CodeGenerator codegenerator = parser.getcodegenerator();
				DataConstructor dc = new DataConstructor(parser);

				StringBuffer returnedcode = codegenerator.generateCode2(parser,
						dc.getData());

				// ajax add div
				// tag////////////////////////////////////////////////////////////////////
				if (GlobalEnv.isAjax()) {
					// TODO 20140619_masato
					if (!has_divid) {
						int x = 0;
						if (file.indexOf(".sql") > 0) {
							x = file.indexOf(".sql");
						} else if (file.indexOf(".ssql") > 0) {
							x = file.indexOf(".ssql");
						}
						// online file
						if (file.contains("/")) {
							divname = file.substring(file.lastIndexOf("/") + 1,
									x);
						}
						// ofline file
						else if (file.contains("\\")) {
							divname = file.substring(
									file.lastIndexOf("\\") + 1, x);
						}
						// only file name
						else {
							divname = file.substring(0, x);
						}
					}

					htmlEnv.code.append("<div id=\"" + divname + "\" ");

//					if (decos.containsKey("class"))
//						htmlEnv.code.append("class=\"" + decos.getStr("class")
//								+ "\" ");
					htmlEnv.code.append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok

					htmlEnv.code.append(">");
					// html_env.code.append("<br><a href=\"close.html\" class=\"bottom_close_"+divname+"\" onClick=\"return closeDiv('"+divname+"')\">close</a><br>");
					Log.out("<div id=" + divname + ">");
				}

				// xml�����
				if (!is_hidden) {
					htmlEnv2.code.append("<EMBED>");
					htmlEnv.code.append(returnedcode);
					htmlEnv2.code.append(returnedcode);
					htmlEnv2.code.append("</EMBED>");
				}

				if (GlobalEnv.isAjax())
					htmlEnv.code.append("</div>");
				// end ajax
				// /////////////////////////////////////////////////////////////////

				if (htmlEnv.embedCount >= 1) {
					htmlEnv.css.append(codegenerator.generateCode3(parser,
							dc.getData()));
					htmlEnv.cssFile.append(codegenerator.generateCssfile(
							parser, dc.getData()));
				}

				// restore original config
				GlobalEnv.setEnv(tmphash);

				// writing tmpfile
				Log.out("embed hogehoge:" + GlobalEnv.isNewEmbed());
				Log.out("enb:" + GlobalEnv.getEnv());

				if (GlobalEnv.isNewEmbed() == 1) {
					GlobalEnv.addEmbedFile(makedfilename);
					Log.out("embed start writing");
					String filename = GlobalEnv.getEmbedTmp();

					if (filename.endsWith("/") || filename.endsWith("\\"))
						filename = filename + makedfilename;
					else
						filename = filename + "/" + makedfilename;

					try {
						OutputStream fout = new FileOutputStream(filename);
						OutputStream bout = new BufferedOutputStream(fout);
						OutputStreamWriter out = new OutputStreamWriter(bout,
								"UTF-8");

						out.write(htmlEnv.header.toString());
						out.write(returnedcode.toString());
						out.write(htmlEnv.footer.toString());

						out.close();
						/*
						 * PrintWriter pw = new PrintWriter(new
						 * BufferedWriter(new FileWriter( filename)));
						 * Log.out("filename:"+filename);
						 * pw.println(html_env.header);
						 * pw.println(returnedcode);
						 * pw.println(html_env.footer); pw.close();
						 */
					} catch (FileNotFoundException fe) {

						fe.printStackTrace();
						System.err
								.println("Error: specified embedtmp outdirectory \""
										+ GlobalEnv.getEmbedTmp()
										+ "\" is not found to write "
										+ htmlEnv.fileName);

						GlobalEnv
								.addErr("Error: specified embedtmp outdirectory \""
										+ GlobalEnv.getEmbedTmp()
										+ "\" is not found to write "
										+ htmlEnv.fileName);
						// comment out by chie
						// System.exit(-1);
					} catch (IOException e) {
						System.err
								.println("Error[HTMLManager]: File IO Error in HTMLManager at embed");
						e.printStackTrace();
						GlobalEnv
								.addErr("Error[HTMLManager]: File IO Error in HTMLManager at embed");
						// comment out by chie
						// System.exit(-1);
					}
				}

			}
		}
		// embed html file
		else if (file.contains(".html")) {
			String line = new String();

			if (decos.containsKey("divid"))
				divname = decos.getStr("divid");
			else if (file.contains("\\"))
				divname = file.substring(file.lastIndexOf("\\") + 1,
						file.indexOf(".html"));
			else if (file.contains("/"))
				divname = file.substring(file.lastIndexOf("/") + 1,
						file.indexOf(".html"));
			else
				divname = file.substring(0, file.indexOf(".html"));

			BufferedReader dis;
			try {
				if (file.contains("http://")) {
					URL fileurl = new URL(file);

					URLConnection fileurlConnection = fileurl.openConnection();
					dis = new BufferedReader(new InputStreamReader(
							fileurlConnection.getInputStream()));
				} else {
					try {
						Log.out("embed file (html):" + file);
						dis = new BufferedReader(new FileReader(new File(file)));
					} catch (IOException ioe) {
						String path = htmlEnv.outFile;
						if (path.contains("\\"))
							path = path
									.substring(0, path.lastIndexOf("\\") + 1);
						else if (path.contains("/"))
							path = path.substring(0, path.lastIndexOf("/") + 1);
						if (file.startsWith("./")) {
							file = file.substring(1, file.length());
						}
						Log.out("embed file (html):" + path + file);
						if (path.startsWith("http:")) {
							URL fileurl = new URL(path + file);
							URLConnection fileurlConnection = fileurl
									.openConnection();
							dis = new BufferedReader(new InputStreamReader(
									fileurlConnection.getInputStream()));
						} else {
							dis = new BufferedReader(new FileReader(new File(
									path + file)));

						}
					}
				}
				/*
				 * DataInputStream dis = new
				 * DataInputStream(fileurlConnection.getInputStream());
				 */
				line = dis.readLine(); // read <BODY> and/or <HEAD>
				if (line.contains("<head>")) {
				} else {
					line = dis.readLine(); // read <HEAD>
				}

				while (!line.equalsIgnoreCase("</head>")) {
					line = dis.readLine();
					if (!line.equalsIgnoreCase("</head>"))
						htmlEnv.header.append(line + "\n");
				}
				line = dis.readLine(); // read <body>

				htmlEnv.code.append("<div id=\"" + divname + "\" ");

//				if (decos.containsKey("class"))
//					htmlEnv.code.append("class=\"" + decos.getStr("class")
//							+ "\" ");
				htmlEnv.code.append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok

				htmlEnv.code.append(">");

				htmlEnv2.code.append("<EMBED>");
				while (!line.equalsIgnoreCase("</body>")) {
					Log.out("line : " + line);
					line = dis.readLine();
					if (!line.equalsIgnoreCase("</body>")) {
						htmlEnv.code.append(line);
						if (line.contains("&"))
							line = line.replace("&", "&amp;");
						if (line.contains("<"))
							;
						line = line.replace("<", "&lt;");
						if (line.contains(">"))
							line = line.replace(">", "&gt;");
						if (line.contains("���"))
							line = line.replace("���", "&#65374;");
						htmlEnv2.code.append(line);
					}
				}
				htmlEnv2.code.append("</EMBED>");
				// html_env.code.append("<br><a href=\"close.html\" class=\"bottom_close_"+divname+"\" onClick=\"return closeDiv('"+divname+"')\">close</a><br>");

				htmlEnv.code.append("</div>");
				dis.close();

			} catch (MalformedURLException me) {
				System.out.println("MalformedURLException: " + me);
			} catch (IOException ioe) {
				System.out.println("HTMLFuncEmbed:IOException: " + ioe);
			}

		}
		if (!is_hidden)
			htmlEnv.code.append("</td></tr></table>");

		htmlEnv.embedCount += 1;
	}

	// tk
	// end////////////////////////////////////////////////////////////////////////////
	private void Func_foreach(ExtList data_info)
			throws UnsupportedEncodingException {
		String att = new String();
		for (int i = 0; i < this.countconnectitem(); i++) {
			att = att + "_" + this.getAtt(Integer.toString(i));
		}

		if(!Start_Parse.foreach1Flag){
			//added by goto 20161019 for new foreach
			HTMLG3.foreachID = att;
		}else{
			//added by goto 20161025 for link1/foreach1
			att = URLEncoder.encode(att, "UTF-8");
			String filename = htmlEnv.outFile + att + ".html";
			htmlEnv.fileName = filename;
		}
		return;
	}

	private void Func_FormCommon(String s) {
		String form = new String();

		boolean openFormInThis = false;

		if (!HTMLEnv.getFormItemFlg()) {
			form = createForm();
			openFormInThis = true;
		}

		HTMLEnv.setFormItemFlg(true, s);

		String att = new String();
		Integer attNo = 1;
		while (!this.getAtt("att" + attNo).equals("")) {
			if (attNo > 1)
				att += ",";
			att += this.getAtt("att" + attNo);
			Log.out("att:" + att + " attNo:" + attNo);
			attNo++;
		}
		if (attNo == 1 && !this.getAtt("att").equals("")) {
			att += this.getAtt("att");
			Log.out("att:" + att + " attNo:" + attNo);
		}

		if (!this.getAtt("name").equals("")) {
			HTMLEnv.setFormPartsName(this.getAtt("name"));
			HTMLEnv.exFormName();
		} else {
			HTMLEnv.setFormPartsName(null);
		}

		if (!this.getAtt("id").equals("")) {
			HTMLEnv.nameId = this.getAtt("id");
		}

		if (!this.getAtt("cond_name").equals("")) {
			HTMLEnv.condName = this.getAtt("cond_name");
		}
		if (!this.getAtt("cond").equals("")) {
			HTMLEnv.cond = this.getAtt("cond");
		}

		htmlEnv.code.append(form);

		if (this.Args.get(0) instanceof FuncArg) {
			// HTMLEnv.setSelectFlg(true,(String)this.decos.get("select"));
			HTMLEnv.setFormValueString(att);
			Log.out("ARGS are function");
			FuncArg fa = this.Args.get(0);
			fa.workAtt();
		} else {
			this.workAtt("default");
		}

		if (openFormInThis == true) {
			htmlEnv.code.append("</form>");
			HTMLEnv.setFormItemFlg(false, null);
			openFormInThis = false;
		} else {
			HTMLEnv.setFormItemFlg(true, null);
		}
		return;
	}

	// added by chie 2009 func form hidden
	private void Func_hidden() {
		Func_FormCommon("hidden");
		return;
	}

	// added by chie 2009 func form inputtext
	private void Func_inputtext() {
		Func_FormCommon("text");
		return;
	}

	private void Func_invoke() {

		/*
		 * Invoke function : <td> <a
		 * href="${server_path}/supersql.invoke.InvokeServlet?
		 * ${dbname}+${query_filename}+${added_condition}"> TFE </a> </td>
		 */

		String path = this.getAtt("path", ".");
		if (!GlobalEnv.getFileDirectory().equals(".")) {
			path = GlobalEnv.getFileDirectory();
		}
		String filename = this.getAtt("filename");
		if (!filename.startsWith("/") && (path != null)) {
			filename = path + "/" + filename;
		}

		Log.out("invoke filename:" + filename);

		// start tk/////////////////////////////////
		/*
		 * html_env.linkurl = this.getAtt("server_path", GlobalEnv
		 * .getInvokeServletPath()) + "?" + this.getAtt("dbname",
		 * GlobalEnv.getdbname()) + "+" + filename + "+" +
		 * this.getAtt("condition");
		 */
		/*
		 * html_env.linkurl =
		 * "http://localhost:8080/invoke/servlet/supersql.invoke.InvokeServlet2"
		 * + "?" + "config=http://localhost:8080/invoke/config.ssql" + "&" +
		 * "query=" + filename + "&" + "cond=" + this.getAtt("condition");
		 */
		// change chie
		htmlEnv.linkUrl = this.getAtt("server_path",
				GlobalEnv.getInvokeServletPath())
				+ "?"
				+ "config="
				+ path
				+ "/config.ssql"
				+ "&"
				+ "query="
				+ filename + "&" + "cond=" + this.getAtt("condition");
		// end tk//////////////////////////////////////////////////

		htmlEnv.linkFlag = 1;
		this.workAtt("default");
		htmlEnv.linkFlag = 0;

		return;
	}

	// added by chie 2009 func form radio
	private void Func_radio() {

		if (!this.getAtt("checked").equals("")) {
			HTMLEnv.setChecked(this.getAtt("checked"));
		}

		Func_FormCommon("radio");

		return;
	}

	// added by chie 2009 func form select
	private void Func_select() {
		if (!this.getAtt("selected").equals("")) {
			HTMLEnv.setSelected(this.getAtt("selected"));
		}

		Func_FormCommon("select");

		return;
	}

	private void Func_sinvoke(ExtList data_info) {
		// link関数の仕様変更　link(att_name, url, value1, value2, ...)
		String file = this.Args.get(1).toString();
		if (file.startsWith("\'") || file.startsWith("\"")) {
			file = file.substring(1, file.length() - 1);
		}
		String att = new String();
		for (int i = 2; i < this.Args.size(); i++) {
			att += "_" + this.Args.get(i).getStr();
		}
		// String file = this.getAtt("file");
		String action = this.getAtt("action");
		// int attNo = 1;
		// String att = new String();
		Log.out("sinvoke file 3: " + file);
		//changed by goto 20161019 for new foreach
		if(link1){
			//added by goto 20161025 for link1/foreach1
			try {
				att = URLEncoder.encode(att, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (this.getAtt("action").equals("")) {
			try {
				if (file.toLowerCase().contains(".sql")) {
					file = file.substring(0, file.indexOf(".sql"));
				} else if (file.toLowerCase().contains(".ssql")) {
					file = file.substring(0, file.indexOf(".ssql"));
				} else if (file.toLowerCase().contains(".html")) {
					file = file.substring(0, file.indexOf(".html"));
				}
			} catch (Exception e) {
				GlobalEnv.addErr("Error[HTMLFunction]: filename is invalid.");
				// System.err.println("Error[HTMLFunction]: filename is invalid.");
				Log.err("Error[HTMLFunction]: filename is invalid.");
				// GlobalEnv.errorText +=
				// "Error[HTMLFunction]: filename is invalid.";
			}

			String filename = new String();
			if (!this.getAtt("att").equals("")) {
				if (this.getAtt("att").toLowerCase().startsWith("http://"))
					filename = this.getAtt("att");
				else if (this.getAtt("att").toLowerCase().endsWith(".html"))
					filename = this.getAtt("att");
				else
					filename = file + "_" + this.getAtt("att") + ".html";
			} else {

				if(!link1){
					//added by goto 20161019 for new foreach
					filename = file;
					//added by goto 20161109
					if(!file.endsWith(".php") && !file.endsWith(".rb") && !file.endsWith(".erb") && !file.endsWith(".jsp"))
						filename += ".html";
					filename += "?"+LinkForeach.ID2+"="+att.substring(1);
				}else{
					//added by goto 20161025 for link1/foreach1
					filename = file + att + ".html";
				}
			}

			filename.replace("\\\\", "\\");
			htmlEnv.linkUrl = filename;
			htmlEnv.sinvokeFlag = true;
		} else {
			String filename = new String();
			if (!this.getAtt("att").equals(""))
				filename = action + "/" + this.getAtt("att");
			else
				filename = action + att;

			filename.replace("\\\\", "\\");
			htmlEnv.linkUrl = filename;
			htmlEnv.sinvokeFlag = true;
		}

		if (GlobalEnv.isAjax()) {
			htmlEnv.linkUrl = file + ".html";
			htmlEnv.ajaxQuery = file + ".sql";
			// html_env.ajaxatt = this.getAtt("att");
			htmlEnv.ajaxCond = this.getAtt("ajaxcond") + "="
					+ this.getAtt("att");

			Date d2 = new Date();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyymmddHHmmss");
			String today2 = sdf2.format(d2);

			htmlEnv.dragDivId = htmlEnv.ajaxQuery + "+" + htmlEnv.ajaxCond
					+ "&" + today2;

			if (decos.containsKey("in")) {
				String effect = decos.getStr("in");

				if (effect.equalsIgnoreCase("blind"))
					htmlEnv.inEffect = 1;
				if (effect.equalsIgnoreCase("fade"))
					htmlEnv.inEffect = 2;
			}
			if (decos.containsKey("out")) {
				String effect = decos.getStr("out");

				if (effect.equalsIgnoreCase("blind"))
					htmlEnv.outEffect = 1;
				if (effect.equalsIgnoreCase("fade"))
					htmlEnv.outEffect = 2;
			}

			if (decos.containsKey("panel")) {
				htmlEnv.isPanel = true;
			}
			if (decos.containsKey("dispdiv")) {
				String dispdiv = decos.getStr("dispdiv");
				if (dispdiv.contains("+")) {
					String tmp2 = dispdiv
							.substring(0, dispdiv.lastIndexOf("+"));
					String tmp3 = dispdiv.substring(
							dispdiv.lastIndexOf("+") + 1, dispdiv.length());

					if (tmp3.compareTo("att") == 0) {
						htmlEnv.ajaxtarget = tmp2 + "_" + this.getAtt("att");
					} else
						htmlEnv.ajaxtarget = dispdiv;
				} else {
					htmlEnv.ajaxtarget = dispdiv;
				}
				htmlEnv.hasDispDiv = true;
				Log.out("html_env.ajaxtarget:" + htmlEnv.ajaxtarget);
			} else if (decos.containsKey("dragto")) {
				Log.out("draggable = ture");
				htmlEnv.draggable = true;

				// drag to
				String value = decos.getStr("dragto");
				String[] droptarget = new String[100];
				int targetnum = 0;

				if (value.contains("+")) {
					while (true) {
						if (!value.contains("+")) {
							droptarget[targetnum] = value;
							targetnum++;
							break;
						}
						droptarget[targetnum] = value.substring(0,
								value.indexOf("+"));
						value = value.substring(value.indexOf("+") + 1,
								value.length());

						targetnum++;
					}
				} else
					droptarget[0] = value;

				// script ����
				Date d1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
				String today = sdf.format(d1);

				String scriptname = "drop" + today + htmlEnv.scriptNum;
				htmlEnv.script.append(scriptname + " = new DragDrop(\""
						+ htmlEnv.dragDivId + "\", \"" + droptarget[0]
						+ "\");\n");

				Log.out(scriptname + " = new DragDrop(\"" + htmlEnv.dragDivId
						+ "\", \"" + droptarget[0] + "\");\n");

				// for tab
				htmlEnv.script.append(scriptname + ".addToGroup(\"myTab\");\n");

				for (int i = 1; i < targetnum; ++i) {
					htmlEnv.script.append(scriptname + ".addToGroup(\""
							+ droptarget[i] + "\");\n");
				}

				htmlEnv.scriptNum++;
			}
		}

		if (this.Args.get(0) instanceof FuncArg) {
			Log.out("ARGS are function");
			FuncArg fa = this.Args.get(0);
			fa.workAtt();
		} else
			this.workAtt("default");

		htmlEnv.sinvokeFlag = false;
		link1 = false;
		return;
	}

	// added by masato 20151124 for plink
	// plink(属性名, '***.php', 受け渡す値1(*.id), 受け渡す値2(*.id)...)
	private void Func_plink(ExtList data_info) {
		// リンク先phpファイル
		String target = this.Args.get(1).toString();
		if (target.startsWith("\'") || target.startsWith("\"")) {
			target = target.substring(1, target.length() - 1);
		}

		// value
		htmlEnv.valueArray = new ArrayList<>();
		for (int i = 2; i < this.Args.size(); i++) {
			htmlEnv.valueArray.add(this.Args.get(i).getStr());
		}

		htmlEnv.linkUrl = target;
		htmlEnv.plinkFlag = true;

		// added by masato 20151124 for plink
		String form_type = (glink_flag)? "glink" : "plink"; 	//TODO
		if(image_link_flag)	form_type = "image_"+form_type;
		if (htmlEnv.plinkFlag) {
			String tmp = "";
			for (int i = 0; i < htmlEnv.valueArray.size(); i++) {
				tmp += " value" + (i + 1) + "='" + htmlEnv.valueArray.get(i) + "'";
			}
			Incremental.outXMLData(htmlEnv.xmlDepth, "<"+form_type+" target='" + htmlEnv.linkUrl + "'" + tmp + ">\n");
		}

		if (this.Args.get(0) instanceof FuncArg) {
			Log.out("ARGS are function");
			FuncArg fa = this.Args.get(0);
			fa.workAtt();
		} else
			this.workAtt("default");

		// added by masato 20151124 for plink
		if (htmlEnv.plinkFlag) {
			Incremental.outXMLData(htmlEnv.xmlDepth, "</"+form_type+">\n");
		}


		htmlEnv.plinkFlag = false;
		return;
	}
	// Func_glink
	private static boolean glink_flag = false;
	private void Func_glink(ExtList data_info) {	 	//TODO 動作確認
		glink_flag = true;
		Func_plink(data_info);
		glink_flag = false;
		return;
	}
	private static boolean image_link_flag = false;
	private void Func_image_plink(ExtList data_info) {	 	//TODO 動作確認
		image_link_flag = true;
		Func_plink(data_info);
		image_link_flag = false;
		return;
	}
	private void Func_image_glink(ExtList data_info) {	 	//TODO 動作確認
		image_link_flag = true;
		Func_glink(data_info);
		image_link_flag = false;
		return;
	}

	// not use
	/*
	 * private void Func_session() { html_env.code.append("b");
	 * html_env2.code.append("<VALUE type=\"form\">b</VALUE>"); return; }
	 */

	// added by chie 2009 func form textarea
	private void Func_textarea() {
		Func_FormCommon("textarea");
		return;
	}

	// for educ2015
	protected void Func_echo() {
		String target = this.Args.get(0).getStr().trim();
		String statement = "<h1>" + target + "</h1>";
		htmlEnv.code.append(statement);
	}

	//20210604 yama Func_embed()
	protected void Func_embed() {
		String target1 = this.Args.get(0).getStr().trim().replaceAll("ssql$", "html");
		String target2 = "300";
		String target3 = "150";

		if(decos.containsKey("width")) {
			target2 = decos.getStr("width");
		}

		if(decos.containsKey("height")) {
			target3 = decos.getStr("height");
		}

		if (this.Args.size() == 3) {
			target2 = this.Args.get(1).getStr().trim();
			target3 = this.Args.get(2).getStr().trim();
		}

		String statement = "<iframe src=" + target1 + " width=" + target2 + " height=" +target3 + " frameborder=\"0\" style=\"border: none\"></iframe>";
		htmlEnv.code.append(statement);
	}

	protected void Func_imagefile() {
		HTMLFunctionFlag = true;
		/*
		 * ImageFile function : <td> <img src="${imgpath}/"+att /> </td>
		 */
		// little change by masato 20150623
		String path = "./";
		try {
			path = this.Args.get(1).toString();
			if(!path.endsWith("/'")) {
				path = path.substring(0, path.length() - 1) + "/'";

			}
		} catch (Exception e) {
			try {
				if(this.Args.get(0).getStr().startsWith("http://") || this.Args.get(0).getStr().startsWith("https://")) {
					path = this.getAtt("path", "");
				}
			} catch (Exception e2) { }
		}
		if (path == null) {
			path = "";
		} else {
			if (path.startsWith("'") || path.startsWith("\"")) {
				path = path.substring(1, path.length() - 1);
			}
		}
		// String path = this.getAtt("path", ".");
		if (!path.startsWith("/")) {
			String basedir = GlobalEnv.getBaseDir();
			if (basedir != null && basedir != "") {
				path = GlobalEnv.getBaseDir() + "/" + path;
			}
		}
		if (GlobalEnv.isServlet()) {
			path = GlobalEnv.getFileDirectory() + path;
		}

		// System.out.println(GlobalEnv.isServlet());

		// tk to make hyper link to
		// image//////////////////////////////////////////////////////////////////////////////////
		if (htmlEnv.linkFlag > 0 || htmlEnv.sinvokeFlag) {
			// added by goto 20121222 start
			// �ʲ��ϡ�-f�Υե�����̾���꤬���Хѥ��ˤʤäƤ�����ν���(?)
			// [%Ϣ���] href�λ�������Хѥ���������Хѥ������פ��ѹ�
			// 20120622�ν������ȡ���-f �ե�ѥ��ե�����̾�פ��Ѥ��Ƥ����硢���Хѥ������ˤʤ�ʤ�
			String fileDir = new File(htmlEnv.linkUrl).getAbsoluteFile()
					.getParent();
			if (htmlEnv.decorationStartFlag.size() > 0) {
				if (htmlEnv.decorationStartFlag.get(0)) {
					if (fileDir.length() < htmlEnv.linkUrl.length()
							&& fileDir.equals(htmlEnv.linkUrl.substring(0,
									fileDir.length()))) {
						String relative_path = htmlEnv.linkUrl.substring(fileDir.length() + 1);
						HTMLDecoration.fronts.get(0).append("<A href=\"" + relative_path + "\" ");
					} else {
						HTMLDecoration.fronts.get(0).append("<A href=\"" + htmlEnv.linkUrl + "\" ");
					}
					if (decos.containsKey("target")) {
						HTMLDecoration.fronts.get(0).append(" target=\"" + decos.getStr("target") + "\" ");
					}
//					if (decos.containsKey("class")) {
//						HTMLDecoration.fronts.get(0).append(" class=\"" + decos.getStr("class") + "\" ");
//					}
					HTMLDecoration.fronts.get(0).append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok
					HTMLDecoration.fronts.get(0).append(">\n");
				} else {
					if (fileDir.length() < htmlEnv.linkUrl.length()
							&& fileDir.equals(htmlEnv.linkUrl.substring(0,
									fileDir.length()))) {
						String relative_path = htmlEnv.linkUrl.substring(fileDir.length() + 1);
						HTMLDecoration.ends.get(0).append("<A href=\"" + relative_path + "\" ");
					} else {
						HTMLDecoration.ends.get(0).append("<A href=\"" + htmlEnv.linkUrl + "\" ");
					}
					if (decos.containsKey("target")) {
						HTMLDecoration.ends.get(0).append(" target=\"" + decos.getStr("target") + "\" ");
					}
//					if (decos.containsKey("class")) {
//						HTMLDecoration.ends.get(0).append(" class=\"" + decos.getStr("class") + "\" ");
//					}
					HTMLDecoration.ends.get(0).append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok
					HTMLDecoration.ends.get(0).append(">\n");
				}
			} else {
				if (fileDir.length() < htmlEnv.linkUrl.length()
						&& fileDir.equals(htmlEnv.linkUrl.substring(0,
								fileDir.length()))) {
					String relative_path = htmlEnv.linkUrl.substring(fileDir
							.length() + 1);
					htmlEnv.code.append("<A href=\"" + relative_path + "\" ");
				} else
					htmlEnv.code.append("<A href=\"" + htmlEnv.linkUrl + "\" ");

				// html_env.code.append("<A href=\"" + html_env.linkurl + "\" ");
				// added by goto 20121222 end

				if (decos.containsKey("target"))
					htmlEnv.code.append(" target=\"" + decos.getStr("target")
							+ "\" ");
//				if (decos.containsKey("class"))
//					htmlEnv.code
//							.append(" class=\"" + decos.getStr("class") + "\" ");
				htmlEnv.code.append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok
				htmlEnv.code.append(">\n");
			}

			Log.out("<A href=\"" + htmlEnv.linkUrl + "\">");
		}
		// added by masato 20151124 for plink
		if (htmlEnv.plinkFlag) {
			String tmp = "";
			for (int i = 0; i < htmlEnv.valueArray.size(); i++) {
				tmp += " value" + (i + 1) + "='" + htmlEnv.valueArray.get(i) + "'";
			}
			Incremental.outXMLData(htmlEnv.xmlDepth, "<PostLink target='" + htmlEnv.linkUrl + "'" + tmp + ">\n");
		}
		// tk/////////////////////////////////////////////////////////////////////////////////

		if (decos.containsKey("lightbox")) {
			Date d1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
			String today = sdf.format(d1);

			if (htmlEnv.decorationStartFlag.size() > 0) {
				HTMLDecoration.ends.get(0).append("<a href=\"" + path
						+ this.Args.get(0).getStr() + "\" rel=\"lightbox[lb"
						+ today + "]\">");
				if (decos.getStr("lightbox").compareTo("root") == 0
						|| decos.getStr("lightbox").compareTo("thumb") == 0) {
					HTMLDecoration.ends.get(0).append("<img class=\"" + HTMLEnv.getClassID(this) + " ");
//					if (decos.containsKey("class")) {
//						HTMLDecoration.ends.get(0).append(decos.getStr("class"));
//					}
					HTMLDecoration.ends.get(0).append(Modifier.getClassModifierValue(decos));//kotani_id_modifier
					HTMLDecoration.ends.get(0).append(Modifier.getIdModifierValue(decos));

//<<<<<<< HEAD
//					HTMLDecoration.ends.get(0).append(" \" src=\"" + path + "/"
//=======
					HTMLDecoration.ends.get(0).append(" \" src=\"" + path
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
							+ this.Args.get(0).getStr()
							+ "\" onLoad=\"initLightbox()\"/>");
				}
				HTMLDecoration.ends.get(0).append("</a>");
			} else {
				htmlEnv.code.append("<a href=\"" + path
						+ this.Args.get(0).getStr() + "\" rel=\"lightbox[lb"
						+ today + "]\">");

				if (decos.getStr("lightbox").compareTo("root") == 0
						|| decos.getStr("lightbox").compareTo("thumb") == 0) {
					htmlEnv.code.append("<img class=\"" + HTMLEnv.getClassID(this)
							+ " ");

//					if (decos.containsKey("class"))
//						htmlEnv.code.append(decos.getStr("class"));
					htmlEnv.code.append(Modifier.getClassModifierValue(decos));//kotani_idmodifier_ok
					htmlEnv.code.append(Modifier.getIdModifierValue(decos));

					htmlEnv.code.append(" \" src=\"" + path
							+ this.Args.get(0).getStr()
							+ "\" onLoad=\"initLightbox()\"/>");
				}
				htmlEnv.code.append("</a>");
			}
		} else {
			// added by masato 20151124 image function for xml
			if (Ehtml.flag || Incremental.flag) {
//<<<<<<< HEAD
				Incremental.outXMLData(htmlEnv.xmlDepth, "<img class=\'"
						+ HTMLEnv.getClassID(this) + "\' src='" + path + "/"
						+ this.Args.get(0).getStr() + "'></img>\n");
//=======
//				Incremental.outXMLData(htmlEnv.xmlDepth, "<Img class=\'"
//						+ HTMLEnv.getClassID(this) + "\' src='" + path
//						+ this.Args.get(0).getStr() + "'></Img>\n");
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

			} else {
				if (htmlEnv.decorationStartFlag.size() > 0) {
					if (htmlEnv.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("<img");
						HTMLDecoration.classes.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(HTMLEnv.getClassID(this));
//						if (decos.containsKey("class")) {
//							HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//						}
						HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));//kotani_idmodifier_ok
						HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));
						HTMLDecoration.ends.get(0).append("\" src=\"" + path + this.Args.get(0).getStr() + "\"/>");
						htmlEnv.decorationStartFlag.set(0, false);
					} else {
						HTMLDecoration.ends.get(0).append("<img");
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(HTMLEnv.getClassID(this));
//						if (decos.containsKey("class")) {
//							HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//						}
						HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));//kotani_idmodifier_ok
						HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));

//<<<<<<< HEAD
						HTMLDecoration.ends.get(0).append("\" src=\"" + path + "/" + this.Args.get(0).getStr() + "\"/>");
//=======
//						HTMLDecoration.ends.get(0).append("\" src=\"" + path + this.Args.get(0).getStr() + "\"/>");
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
					}
				} else {
					htmlEnv.code.append("<img class=\"" + HTMLEnv.getClassID(this)
							+ " ");
					htmlEnv2.code.append("<VALUE type=\"img\" class=\""
							+ HTMLEnv.getClassID(this) + " ");
//					if (decos.containsKey("class"))
//						htmlEnv.code.append(decos.getStr("class"));
					htmlEnv.code.append(Modifier.getClassModifierValue(decos));
					htmlEnv.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
					htmlEnv.code.append(" \" src=\"" + path
							+ this.Args.get(0).getStr() + "\"/>");
					htmlEnv2.code.append(" \" src=\"" + path
							+ this.Args.get(0).getStr() + "\" ");
					if (decos.containsKey("width")) {
						htmlEnv2.code.append("width=\""
								+ decos.getStr("width").replace("\"", "") + "\" ");
					}
					if (decos.containsKey("height")) {
						htmlEnv2.code.append("height=\""
								+ decos.getStr("height").replace("\"", "") + "\" ");
					}
					htmlEnv2.code.append(" ></VALUE>");
				}
			}
		}
		// tk to make hyper link to
		// image///////////////////////////////////////////////////////////////////////////////////
		if (htmlEnv.linkFlag > 0 || htmlEnv.sinvokeFlag) {
			if (htmlEnv.decorationStartFlag.size() > 0) {
				HTMLDecoration.ends.get(0).append("</a>");
			} else {
				htmlEnv.code.append("</a>");
			}
		}
		// added by masato 20151124 for plink
		if (htmlEnv.plinkFlag) {
			Incremental.outXMLData(htmlEnv.xmlDepth, "</PostLink>\n");
		}
		// tk///////////////////////////////////////////////////////////////////////////////////
		HTMLFunctionFlag = false;
		return;
	}

	protected void Func_null() {
		return;
	}

	// added by chie 2009 func form submit
	protected void Func_submit() {
		String form = new String();
		boolean openFormInThis = false;

		// submit only ----- no "@{form}"
		if (!HTMLEnv.getFormItemFlg() && !decos.containsKey("form")) {
			form = createForm();
			openFormInThis = true;
		} else if (decos.containsKey("form")) {
			form = createForm(decos);
			openFormInThis = true;
		}

		HTMLEnv.setFormItemFlg(true, "submit");

		String option = new String();
		if (!this.Args.get(0).getStr().equals(null)) {
			option += "value=\"" + this.Args.get(0).getStr() + "\"";
		}

		form += "<input type=\"submit\" " + option + " />";

		if (openFormInThis == true) {
			form += "</form>";
			HTMLEnv.setFormItemFlg(false, null);
			openFormInThis = false;
		} else {
			HTMLEnv.setFormItemFlg(true, null);
		}

		htmlEnv.code.append(form);
		htmlEnv2.code.append("<VALUE type=\"form\">" + form + "</VALUE>");
		return;
	}
	
	// hline(), vline()
	private String Func_hline() {
		String size="1", color="black", width="100%", margin="";		//default: 引数なしの場合
		size = getArg(0, size);
		color = getArg(1, color);
		
		// width
		if (isArgEmpty(2) && decos.containsKey("width")) {
			String deco_width = decos.getStr("width").replace("\"", "").trim();
			if (!deco_width.equals("")) width = deco_width;	//nullの場合はdefaultを使用
		} else {
			width = getArg(2, width);	//引数と装飾子の両方に指定があった場合は引数優先
		}
		if (GlobalEnv.isNumber(width)) width += "px";
		// margin 上下の余白
		if (!isArgEmpty(3)) {
			margin = getArg(3, margin);
			if (!margin.equals("")) {
				if (GlobalEnv.isNumber(margin)) margin += "px";
				margin = " margin:"+margin+" 0;";
			}
		}

		String stmt = "\n<div style=\"border-top:"+size+"px solid "+color+"; width:"+width+";"+margin+"\""+getClassStr()+"></div>";
		//String stmt = "\n<hr size=\""+size+"px\" color=\""+color+"\" width=\""+width+"\""+getClassStr()+"></div>";
		htmlEnv.code.append(stmt);
		return stmt;
	}
	private String Func_vline() {
		String size="1", color="black", height=(!HTMLEnv.isInsideTable()? "100vh" : "100%"), margin="";	//[重要] default: 引数なしの場合   height: tableの外側100vh, 内側100%
		size = getArg(0, size);
		color = getArg(1, color);
		
		// height
		if (isArgEmpty(2) && decos.containsKey("height")) {
			String deco_height = decos.getStr("height").replace("\"", "").trim();
			if (!deco_height.equals("")) height = deco_height;	//nullの場合はdefaultを使用
		} else {
			height = getArg(2, height);	//引数と装飾子の両方に指定があった場合は引数優先
		}
		if (GlobalEnv.isNumber(height)) height += "px";
		// margin 左右の余白
		if (!isArgEmpty(3)) {
			margin = getArg(3, margin);
			if (!margin.equals("")) {
				if (GlobalEnv.isNumber(margin)) margin += "px";
				margin = " margin:0 "+margin+";";
			}
		}
		
		String stmt = "\n<div style=\"border-left:"+size+"px solid "+color+"; height:"+height+";"+margin+"\""+getClassStr()+"></div>";
		htmlEnv.code.append(stmt);
		return stmt;
	}
	
	// hspace(), vspace()
	private String Func_hspace() {
		return getSpaceStmt("width");
	}
	private String Func_vspace() {
		return getSpaceStmt("height");
	}
	private String getSpaceStmt(String type) {
		String stmt = "\n<div style=\""+type+":";
		String arg1 = getArg(0, "10px");	//default(引数なしの場合): 10px
		if (GlobalEnv.isNumber(arg1)) arg1 += "px";
		stmt += arg1+";\""+getClassStr()+"></div>\n";
		
		htmlEnv.code.append(stmt);
		return stmt;
	}

	// added by goto 20130308 start "anchor" anchor(), a(), url(), mail()
	/**
	 * anchor関数: anchor( name/button-name/button-url, url,
	 * type(bt/button/img/image) )
	 *
	 * @{ width=~, height=~, transition=~ } /* url("title", "detail/imgURL", int
	 *    type), anchor(), a()
	 */
	/* <type:1> a(リンク元の名前, リンク先URL) <=> a(リンク元の名前, リンク先URL, 1) */
	/* <type:2> a(画像URL, リンク先URL, 2) */
	/* <type:3> a(ボタンの名前, リンク先URL, 3) */
	/* mail()でも使用 */
	private void Func_url(boolean mailFncFlg, String t) {
		String statement = "";
		FuncArg fa1 = (FuncArg) this.Args.get(0), fa2, fa3;
		String url, name, type;

		try { // 引数2つ or 3つの場合
			fa2 = (FuncArg) this.Args.get(1);
			url = ((mailFncFlg) ? ("mailto:") : ("")) + fa2.getStr();
			name = fa1.getStr();

			try { // 引数3つの場合
				if (!t.isEmpty()) {
					type = t;
				} else {
					fa3 = (FuncArg) this.Args.get(2);
					type = fa3.getStr();
				}

				// type=1 -> 文字
				if (type.equals("1") || type.equals("text") || type.equals("")) {
					if (htmlEnv.decorationStartFlag.size() > 0) {
						if (htmlEnv.decorationStartFlag.get(0)) {
							String A = "", notA1 = "", notA2 = "";
							int a1 = 0, a2 = name.length() - 1;
							try {
								for (int i = 0; i < name.length(); i++) {
									if (i > 0 && name.charAt(i) == '['
											&& name.charAt(i - 1) != '\\')
										a1 = i;
									else if (i > 0 && name.charAt(i) == ']'
											&& name.charAt(i - 1) != '\\')
										a2 = i;
								}
								if (a1 == 0 && a2 == name.length() - 1)
									A = name.substring(a1, a2 + 1);
								else
									A = name.substring(a1 + 1, a2);
								A = A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
								notA1 = name.substring(0, a1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
								notA2 = name.substring(a2 + 1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
							} catch (Exception e) {
							}
							HTMLDecoration.fronts.get(0).append(notA1);
							HTMLDecoration.fronts.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.classes.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + A + "</a>" + notA2);
							htmlEnv.decorationStartFlag.set(0, false);
						} else {
							String A = "", notA1 = "", notA2 = "";
							int a1 = 0, a2 = name.length() - 1;
							try {
								for (int i = 0; i < name.length(); i++) {
									if (i > 0 && name.charAt(i) == '['
											&& name.charAt(i - 1) != '\\')
										a1 = i;
									else if (i > 0 && name.charAt(i) == ']'
											&& name.charAt(i - 1) != '\\')
										a2 = i;
								}
								if (a1 == 0 && a2 == name.length() - 1)
									A = name.substring(a1, a2 + 1);
								else
									A = name.substring(a1 + 1, a2);
								A = A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
								notA1 = name.substring(0, a1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
								notA2 = name.substring(a2 + 1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
							} catch (Exception e) {
							}
							HTMLDecoration.ends.get(0).append(notA1);
							HTMLDecoration.ends.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.ends.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + A + "</a>" + notA2);
						}
					} else {
						statement = getTextAnchor(url, name);
						// statement =
						// "<a href=\""+url+"\""+transition()+prefetch()+target(url)+">"+name+"</a>";
					}
					// type=2 -> urlモバイルボタン
				} else if (type.equals("3") || type.equals("button")
						|| type.equals("bt")) {
					if (htmlEnv.decorationStartFlag.size() > 0) {
						if (htmlEnv.decorationStartFlag.get(0)) {
							HTMLDecoration.fronts.get(0).append("<a href=\"" + url + "\" data-role=\"button\"");
							HTMLDecoration.classes.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + name + "</a>");
							htmlEnv.decorationStartFlag.set(0, false);
						} else {
							HTMLDecoration.ends.get(0).append("<a href=\"" + url + "\" data-role=\"button\"");
							HTMLDecoration.ends.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok


							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + name + "</a>");
						}
					} else {
						statement = "<a href=\"" + url + "\" data-role=\"button\""
								+ className() + transition() + prefetch()
								+ target(url) + ">" + name + "</a>";
					}

					// urlボタン(デスクトップ・モバイル共通)
				} else if (type.equals("dbutton") || type.equals("dbt")) {
					if (htmlEnv.decorationStartFlag.size() > 0) {
						if (htmlEnv.decorationStartFlag.get(0)) {
							HTMLDecoration.fronts.get(0).append("<input type=\"button\" value=\"" + name
									+ "\" onClick=\"location.href='" + url + "'\"");
							HTMLDecoration.classes.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok


							HTMLDecoration.ends.get(0).append("\">");
							htmlEnv.decorationStartFlag.set(0, false);
						} else {
							HTMLDecoration.ends.get(0).append("<input type=\"button\" value=\"" + name
									+ "\" onClick=\"location.href='" + url + "'\"");
							HTMLDecoration.ends.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));//kotani_idmodifier_ok
							HTMLDecoration.ends.get(0).append("\"");
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
							HTMLDecoration.ends.get(0).append(">");
						}
					} else {
						statement = "<input type=\"button\" value=\"" + name
								+ "\" onClick=\"location.href='" + url + "'\""
								+ className();

						// urlボタン width,height指定時の処理
						if (decos.containsKey("width")
								|| decos.containsKey("height")) {
							statement += " style=\"";
							if (decos.containsKey("width"))
								statement += "WIDTH:"
										+ decos.getStr("width").replace("\"", "")
										+ "; ";
							if (decos.containsKey("height"))
								statement += "HEIGHT:"
										+ decos.getStr("height").replace("\"", "")
										+ "; "; // 100; ";
							statement += "\"";
						}
						statement += ">";
					}

					// type=3 -> url画像
				} else if (type.equals("2") || type.equals("image")
						|| type.equals("img")) {
					if (htmlEnv.decorationStartFlag.size() > 0) {
						if (htmlEnv.decorationStartFlag.get(0)) {
							HTMLDecoration.fronts.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.classes.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + "><img src=\"" + name + "\"></a>");
							htmlEnv.decorationStartFlag.set(0, false);
						} else {
							HTMLDecoration.ends.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.ends.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + "><img src=\"" + name + "\"></a>");
						}
					} else {
						statement = "<a href=\"" + url + "\"" + className()
								+ transition() + prefetch() + target(url)
								+ "><img src=\"" + name + "\"";

						// url画像 width,height指定時の処理
						if (decos.containsKey("width"))
							statement += " width="
									+ decos.getStr("width").replace("\"", "");
						// else{
						// //added by goto 20130312 "Default width: 100%"
						// statement += " width=\"100%\"";
						// }
						if (decos.containsKey("height"))
							statement += " height="
									+ decos.getStr("height").replace("\"", ""); // 100;
																				// ";
						statement += "></a>";
					}
				}

			} catch (Exception e) { // 引数2つの場合
				// added by masato 20151124 anchor function for xml
				if (Ehtml.flag || Incremental.flag) {
					// statement = "<" + fa1 + " func='anchor' url='"+ url +
					// "'>" + name + "</" + fa1 + ">\n";
					statement = "<Anchor url='" + url + "'>" + "<" + fa1 + ">"
							+ name + "</" + fa1 + "></Anchor>\n";
				} else
					if (htmlEnv.decorationStartFlag.size() > 0) {
						if (htmlEnv.decorationStartFlag.get(0)) {
							String A = "", notA1 = "", notA2 = "";
							int a1 = 0, a2 = name.length() - 1;
							try {
								for (int i = 0; i < name.length(); i++) {
									if (i > 0 && name.charAt(i) == '['
											&& name.charAt(i - 1) != '\\')
										a1 = i;
									else if (i > 0 && name.charAt(i) == ']'
											&& name.charAt(i - 1) != '\\')
										a2 = i;
								}
								if (a1 == 0 && a2 == name.length() - 1)
									A = name.substring(a1, a2 + 1);
								else
									A = name.substring(a1 + 1, a2);
								A = A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
								notA1 = name.substring(0, a1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
								notA2 = name.substring(a2 + 1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
							} catch (Exception err) {
							}
							HTMLDecoration.fronts.get(0).append(notA1);
							HTMLDecoration.fronts.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.classes.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + A + "</a>" + notA2);
							htmlEnv.decorationStartFlag.set(0, false);
						} else {
							String A = "", notA1 = "", notA2 = "";
							int a1 = 0, a2 = name.length() - 1;
							try {
								for (int i = 0; i < name.length(); i++) {
									if (i > 0 && name.charAt(i) == '['
											&& name.charAt(i - 1) != '\\')
										a1 = i;
									else if (i > 0 && name.charAt(i) == ']'
											&& name.charAt(i - 1) != '\\')
										a2 = i;
								}
								if (a1 == 0 && a2 == name.length() - 1)
									A = name.substring(a1, a2 + 1);
								else
									A = name.substring(a1 + 1, a2);
								A = A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
								notA1 = name.substring(0, a1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
								notA2 = name.substring(a2 + 1).replaceAll("\\\\\\[", "[")
										.replaceAll("\\\\\\]", "]");
							} catch (Exception err) {
							}
							HTMLDecoration.ends.get(0).append(notA1);
							HTMLDecoration.ends.get(0).append("<a href=\"" + url + "\"");
							HTMLDecoration.ends.get(0).append(" class=\"");
							HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//							if (decos.containsKey("class")) {
//								HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//							}
							HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
							HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

							HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + A + "</a>" + notA2);
							htmlEnv.decorationStartFlag.set(0, false);
						}
					} else {
						statement = getTextAnchor(url, name);
					// statement =
					// "<a href=\""+url+"\""+transition()+prefetch()+target(url)+">"+name+"</a>";
					}
			}

		} catch (Exception e) { // 引数1つの場合
			url = fa1.getStr();
			if (htmlEnv.decorationStartFlag.size() > 0) {
				if (htmlEnv.decorationStartFlag.get(0)) {
					HTMLDecoration.fronts.get(0).append("<a href=\"" + ((mailFncFlg) ? ("mailto:") : ("")) + url + "\"");
					HTMLDecoration.classes.get(0).append(" class=\"");
					HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//					if (decos.containsKey("class")) {
//						HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//					}
					HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
					HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

					HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + url + "</a>");
					htmlEnv.decorationStartFlag.set(0, false);
				} else {
					HTMLDecoration.ends.get(0).append("<a href=\"" + ((mailFncFlg) ? ("mailto:") : ("")) + url + "\"");
					HTMLDecoration.ends.get(0).append(" class=\"");
					HTMLDecoration.ends.get(0).append(htmlEnv.getClassID(this));
//					if (decos.containsKey("class")) {
//						HTMLDecoration.ends.get(0).append(" " + decos.getStr("class"));
//					}
					HTMLDecoration.ends.get(0).append(" " + Modifier.getClassModifierValue(decos));
					HTMLDecoration.ends.get(0).append(" " + Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

					HTMLDecoration.ends.get(0).append("\" " + transition() + prefetch() + target(url) + ">" + url + "</a>");
				}
			} else {
				statement = "<a href=\"" + ((mailFncFlg) ? ("mailto:") : (""))
						+ url + "\"" + transition() + prefetch() + target(url)
						+ ">" + url + "</a>";
			}
		}

		// // 各引数毎に処理した結果をHTMLに書きこむ
		// added by masato 20151124 anchor function for xml
		if (Ehtml.flag || Incremental.flag) {
			Incremental.outXMLData(htmlEnv.xmlDepth, statement);
		} else {
			if (htmlEnv.decorationStartFlag.size() > 0) {
				// do nothing
			} else {
				htmlEnv.code.append(statement);
			}
		}
		// return statement;
	}

	protected String className() { // added 20130703
		return Modifier.getClassAndIdMOdifierValues(decos); //kotani_idmodifier_ok

//		if (decos.containsKey("class"))
//			return " class=\"" + decos.getStr("class") + "\" ";

	}


	private String transition() {
		// 画面遷移アニメーション(data-transition)指定時の処理
		// ※外部ページへの遷移には対応していない
		if (decos.containsKey("transition"))
			return " data-transition=\"" + decos.getStr("transition") + "\"";
		if (decos.containsKey("trans"))
			return " data-transition=\"" + decos.getStr("trans") + "\"";
		return "";
	}

	private String prefetch() {
		// 遷移先ページプリフェッチ(data-prefetch)指定時の処理
		// ※外部ページへの遷移に使用してはいけない決まりがある
		if (decos.containsKey("prefetch") || decos.containsKey("pref"))
			return " data-prefetch";
		return "";
	}

	private String target(String url) {
		// 新規ウィンドウで表示する場合(target="_blank")の処理　=> _blankはW3Cで禁止されているため、JS +
		// rel=externalを使用
		// 「外部ページに飛ぶ場合( http(s)://で始まる場合)」のみ新規ウィンドウ表示
		try {
			if (url.matches("\\s*(http|https)://.*"))
				return "  rel=\"external\"";
			// return " target=\"_blank\"";
		} catch (Exception e) {
		}
		return " target=\"_self\"";

	}

	private String getTextAnchor(String url, String name) {
		// [ ]で囲われた部分をハイパーリンクにする
		// ex) a("[This] is anchor.","URL")
		String A = "", notA1 = "", notA2 = "";
		int a1 = 0, a2 = name.length() - 1;
		try {
			for (int i = 0; i < name.length(); i++) {
				if (i > 0 && name.charAt(i) == '['
						&& name.charAt(i - 1) != '\\')
					a1 = i;
				else if (i > 0 && name.charAt(i) == ']'
						&& name.charAt(i - 1) != '\\')
					a2 = i;
			}
			if (a1 == 0 && a2 == name.length() - 1)
				A = name.substring(a1, a2 + 1);
			else
				A = name.substring(a1 + 1, a2);
			A = A.replaceAll("\\\\\\[", "[").replaceAll("\\\\\\]", "]");
			notA1 = name.substring(0, a1).replaceAll("\\\\\\[", "[")
					.replaceAll("\\\\\\]", "]");
			notA2 = name.substring(a2 + 1).replaceAll("\\\\\\[", "[")
					.replaceAll("\\\\\\]", "]");
		} catch (Exception e) {
		}

		return notA1 + "<a href=\"" + url + "\"" + className() + transition()
				+ prefetch() + target(url) + ">" + A + "</a>" + notA2;
	}

	// Function��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		this.setDataList(data_info);
		 Log.out("FuncName= " + this.getFuncName());
		 //add by li to fix concat in func 20210615
		HTMLFunctionFlag = true;
		//
		// Log.out("filename= " + this.getAtt("filename"));
		// Log.out("condition= " + this.getAtt("condition"));

		String FuncName = this.getFuncName();

		if (FuncName.equalsIgnoreCase("imagefile")
				|| FuncName.equalsIgnoreCase("image")) {
			Func_imagefile();
		} else if (FuncName.equalsIgnoreCase("invoke")) {
			Func_invoke();
		} else if (FuncName.equalsIgnoreCase("foreach")
				//added by goto 20161025 for link1/foreach1
				|| FuncName.equalsIgnoreCase("foreach1")) {
			try {
				Func_foreach(data_info);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (FuncName.equalsIgnoreCase("sinvoke")
				|| FuncName.equalsIgnoreCase("link")
				//added by goto 20161025 for link1/foreach1
				|| FuncName.equalsIgnoreCase("link1")) {
			if(FuncName.equalsIgnoreCase("link1")) link1 = true;
			Func_sinvoke(data_info);
		}
		// added by masato 20151124 for plink in ehtml
		else if (FuncName.equalsIgnoreCase("plink")) {
			Func_plink(data_info);
		} else if (FuncName.equalsIgnoreCase("glink")) {
			Func_glink(data_info);					// TODO  動作確認
		} else if (FuncName.equalsIgnoreCase("image_plink")) {
			Func_image_plink(data_info);
		} else if (FuncName.equalsIgnoreCase("image_glink")) {
			Func_image_glink(data_info);
		} else if (FuncName.equalsIgnoreCase("null")) {
			Func_null();
		}
		// // for practice
		// else if(FuncName.equalsIgnoreCase("button")){
		// Func_button();
		// }
		// chie
		else if (FuncName.equalsIgnoreCase("submit")) {
			Func_submit();
		} else if (FuncName.equalsIgnoreCase("select")) {
			Func_select();
		} else if (FuncName.equalsIgnoreCase("checkbox")) {
			Func_checkbox();
		} else if (FuncName.equalsIgnoreCase("radio")) {
			Func_radio();
		} else if (FuncName.equalsIgnoreCase("inputtext")) {
			Func_inputtext();
		} else if (FuncName.equalsIgnoreCase("textarea")) {
			Func_textarea();
		} else if (FuncName.equalsIgnoreCase("hidden")) {
			Func_hidden();
		} else if (FuncName.equalsIgnoreCase("session")) {
			// Func_session(); not use
		} else if(FuncName.equalsIgnoreCase("line") || FuncName.equalsIgnoreCase("hline") || FuncName.equalsIgnoreCase("hl") || FuncName.equalsIgnoreCase("l")){
			Func_hline();
		} else if(FuncName.equalsIgnoreCase("vline") || FuncName.equalsIgnoreCase("vl")){
			Func_vline();
		} else if(FuncName.equalsIgnoreCase("space") || FuncName.equalsIgnoreCase("hspace") || FuncName.equalsIgnoreCase("hs") || FuncName.equalsIgnoreCase("s")){
			Func_hspace();
		} else if(FuncName.equalsIgnoreCase("vspace") || FuncName.equalsIgnoreCase("vs")){
			Func_vspace();
		} 
//		else if(FuncName.equalsIgnoreCase("testconcat")){
//			Log.out("testconcat:" + getArg(0).getStr());
//		}
		// tk start//////////////////////////////////
		//202106 yama commentout
		// else if (FuncName.equalsIgnoreCase("embed")) {
		// 	Log.out("[enter embed]");
		// 	Func_embed(data_info);
		// }
		// tk end////////////////////////////////////
		else if (FuncName.equalsIgnoreCase("anchor")
				|| FuncName.equalsIgnoreCase("a")) {
			Func_url(false, "");
		} else if (FuncName.equalsIgnoreCase("image_anchor")
				|| FuncName.equalsIgnoreCase("img_anchor")
				|| FuncName.equalsIgnoreCase("img_a")
				|| FuncName.equalsIgnoreCase("image_a")) {
			Func_url(false, "image");
		}
		// for educ2018
		else if (FuncName.equalsIgnoreCase("echo")) {
			Func_echo();
		// for educ2018
//		else if(FuncName.equalsIgnoreCase("shift_image")){
//			Func_simage();
//		}
		} else if (FuncName.equalsIgnoreCase("embed")) {
			Func_embed();
		}

		Log.out("TFEId = " + HTMLEnv.getClassID(this));
		htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
		return null;
	}
	
	
	// 引数の個数を返す
	private int getArgSize() {
		return this.Args.size();
	}
	
	// i番目の引数の値を返す、引数指定なし(またはnullの)場合はdefaultStrを返す
	private String getArg(int i, String defaultStr) {
		try{
			String arg = ((FuncArg) this.Args.get(i)).getStr().trim();
			return (!arg.equals(""))? arg : defaultStr;
		}catch(Exception e){
			return defaultStr;
		}
	}
	
	// i番目の引数が空(null, '', ' 'など)かどうかを返す
	private boolean isArgEmpty(int i) {
		if (getArgSize()>i) 
			return ((FuncArg) this.Args.get(i)).getStr().isEmpty();
		else
			return true;
	}
	
	private String getClassStr() {
		return  " class=\"TFE"+this.getId()+"\"";
	}
	
	// TODO? 装飾子の値を返す、装飾子指定なしの場合にdefaultStrが指定されていた場合はdefaultStrを返す
}
