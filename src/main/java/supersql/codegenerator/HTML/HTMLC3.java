package supersql.codegenerator.HTML;

import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class HTMLC3 extends Connector {

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;

	// ・ウ・�ケ・ネ・鬣ッ・ソ
	public HTMLC3(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "HTMLC3";
	}

	// C3、ホwork・皈ス・テ・ノ
	@Override
	public String work(ExtList data_info) {
		//added by goto 20161019 for new foreach
		final String ID = LinkForeach.ID1;
		
		String parentfile = htmlEnv.fileName;
//		String parentfile2 = htmlEnv2.fileName;
		StringBuffer parentcode = new StringBuffer();
		StringBuffer parentcss = new StringBuffer();
		StringBuffer parentheader = new StringBuffer();
		StringBuffer parentfooter = new StringBuffer();
//		StringBuffer parentcode2 = new StringBuffer();
//		StringBuffer parentcss2 = new StringBuffer();
//		StringBuffer parentheader2 = new StringBuffer();
//		StringBuffer parentfooter2 = new StringBuffer();
		new String();
		ITFE[] tfe = new ITFE[tfeItems];
		int c3items = tfeItems;
		for (int j = 0; j < tfeItems - 1; j++) {
			tfe[j] = tfes.get(j);
			if (j < tfeItems - 2 && tfe[j] instanceof HTMLG3) {
				Log.err("Error: % after []% is not allowed");
				GlobalEnv.addErr("Error: % after []% is not allowed");
			}
		}
		Log.out("------- C3 -------");
		htmlEnv.countFile++;
		
		//changed by goto 20161019 for new foreach
//		// 20140619_masato  + "_"追加
//		htmlEnv.linkUrl = htmlEnv.linkOutFile + "_"
//				+ String.valueOf(htmlEnv.countFile) + ".html";
		String foreachID = String.valueOf(htmlEnv.countFile);
		htmlEnv.linkUrl = "#"+ID+"_"+foreachID;
		
		htmlEnv.linkFlag++;
		Log.out("linkflag =" + htmlEnv.linkFlag);
		Log.out("c3items = " + c3items);
		this.setDataList(data_info);

		this.worknextItem();
		htmlEnv.linkFlag--;
		
		for (int k = 1; k < c3items; k++) {
			ITFE intfe = tfes.get(k);
//			// 20140619_masato + "_"を追加
//			htmlEnv.fileName = htmlEnv.outFile + "_"
//					+ String.valueOf(htmlEnv.countFile) + ".html";
////			htmlEnv2.fileName = htmlEnv.outFile
////					+ String.valueOf(htmlEnv.countFile) + ".xml";
			if (intfe instanceof HTMLG3) {
				htmlEnv.countFile--;
				this.worknextItem();
			} else {
				parentcode = htmlEnv.code;
				parentcss = htmlEnv.css;
				parentheader = htmlEnv.header;
				parentfooter = htmlEnv.footer;
				htmlEnv.code = new StringBuffer();
				htmlEnv.header = new StringBuffer();
				htmlEnv.footer = new StringBuffer();

//				parentcode2 = htmlEnv2.code;
//				parentcss2 = htmlEnv2.css;
//				parentheader2 = htmlEnv2.header;
//				parentfooter2 = htmlEnv2.footer;
//				htmlEnv2.code = new StringBuffer();
//				htmlEnv2.header = new StringBuffer();
//				htmlEnv2.footer = new StringBuffer();

				if (k < c3items - 1) {
					htmlEnv.countFile++;
					htmlEnv.linkUrl = htmlEnv.linkOutFile
							+ String.valueOf(htmlEnv.countFile) + ".html";
					htmlEnv.linkFlag++;
					Log.out("linkflag =" + htmlEnv.linkFlag);
				}

				htmlEnv.setOutlineMode();
				this.worknextItem();
	
				//added by goto 20161019 for new foreach
				htmlEnv.code.insert(0, "<DIV id=\""+ID+"_"+foreachID+"\" style=\"display:none\">\n");
				htmlEnv.code.append(HTMLEnv.getNewTableBorderDIV_end_C3G3());
				htmlEnv.code.append("</DIV>\n\n");
				LinkForeach.C3contents.append(htmlEnv.code);

				if (k < c3items - 1) {
					htmlEnv.linkFlag--;
				}
				
//				htmlEnv.getHeader();
//				htmlEnv.getFooter();
//				// html_env2.header.append("<?xml version=\"1.0\" encoding=\"Shift_JIS\"?><SSQL>");
////				htmlEnv2.header.append("<?xml version=\"1.0\" encoding=\""+ Utils.getEncode() + "\"?><SSQL>");
////				htmlEnv2.footer.append("</SSQL>");
//				try {
//					// changed by goto 20120715_2 start
//					// This is for '%'.
//					// PrintWriter pw2 = new PrintWriter(new BufferedWriter(new
//					// FileWriter(
//					// html_env.filename)));
//					PrintWriter pw;
//					if (htmlEnv.charset != null) {
//						pw = new PrintWriter(new BufferedWriter(
//								new OutputStreamWriter(new FileOutputStream(
//										htmlEnv.fileName), htmlEnv.charset)));
//						// Log.info("File encoding: "+html_env.charset);
//					} else
//						pw = new PrintWriter(new BufferedWriter(new FileWriter(
//								htmlEnv.fileName)));
//					// Log.info("File encoding: "+((html_env.charset!=null)?
//					// html_env.charset : "UTF-8"));
//					// changed by goto 20120715_2 end
//	
//					//changed by goto 20161019 for HTML Formatter
//					String html = "";
//					html += htmlEnv.header;
//					html += htmlEnv.code;
//					html += htmlEnv.footer;
//					html = FileFormatter.process(html);
//					pw.println(html);
//					pw.close();
////					// html_env.header = new StringBuffer();
////					// html_env.footer = new StringBuffer();
////					if (GlobalEnv.isOpt()) {
////						// changed by goto 20120715_2 start
////						// PrintWriter pw2 = new PrintWriter(new
////						// BufferedWriter(new FileWriter(
////						// html_env2.filename)));
////						PrintWriter pw2;
////						if (htmlEnv.charset != null) {
////							pw2 = new PrintWriter(new BufferedWriter(
////									new OutputStreamWriter(
////											new FileOutputStream(
////													htmlEnv2.fileName),
////											htmlEnv.charset)));
////							// Log.info("File encoding: "+html_env.charset);
////						} else
////							pw2 = new PrintWriter(new BufferedWriter(
////									new FileWriter(htmlEnv2.fileName)));
////						// Log.info("File encoding: "+((html_env.charset!=null)?
////						// html_env.charset : "UTF-8"));
////						// changed by goto 20120715_2 end
////
////						pw2.println(htmlEnv2.header);
////						pw2.println(htmlEnv2.code);
////						pw2.println(htmlEnv2.footer);
////						pw2.close();
////						HTMLoptimizer xml = new HTMLoptimizer();
////						String xml_str = xml.generateHtml(htmlEnv2.fileName);
////						pw = new PrintWriter(new BufferedWriter(new FileWriter(
////								htmlEnv.fileName)));
////						pw.println(htmlEnv.header);
////						pw.println(xml_str);
////						StringBuffer footer = new StringBuffer(
////								"</div></body></html>");
////						pw.println(footer);
////						pw.close();
////					}
//				} catch (FileNotFoundException fe) {
//					fe.printStackTrace();
////					System.err.println("Error: specified outdirectory \""
////							+ htmlEnv.outDir + "\" is not found");
//					Log.err("Error: specified outdirectory \""
//							+ htmlEnv.outDir + "\" is not found");
////					GlobalEnv.errorText += "Error: specified outdirectory \""
////							+ htmlEnv.outDir + "\" is not found";
//					GlobalEnv.addErr("Error: specified outdirectory \""
//							+ htmlEnv.outDir + "\" is not found");
//					// comment out by chie
//					// System.exit(-1);
//				} catch (IOException e) {
//					System.err
//							.println("Error[HTMLC3]: File IO Error in HTMLC3");
//					e.printStackTrace();
//					GlobalEnv.addErr("Error[HTMLC3]: File IO Error in HTMLC3");
//					// comment out by chie
//					// System.exit(-1);
//				}

				htmlEnv.code = parentcode;
				htmlEnv.css = parentcss;
				htmlEnv.header = parentheader;
				htmlEnv.footer = parentfooter;

//				htmlEnv2.code = parentcode2;
//				htmlEnv2.css = parentcss2;
//				htmlEnv2.header = parentheader2;
//				htmlEnv2.footer = parentfooter2;
			}
		}
		htmlEnv.fileName = parentfile;
//		htmlEnv2.fileName = parentfile2;
		
		Log.out("TFEId = " + HTMLEnv.getClassID(this));
		htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
		return null;

	}

}
