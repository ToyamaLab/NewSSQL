
//changed by goto 20161019 for new foreach
/*
 * Created on 2004/07/25
 */
package supersql.codegenerator.Mobile_HTML5;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import supersql.codegenerator.FileFormatter;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.LinkForeach;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Sass;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

public class Mobile_HTML5G3 extends Grouper {

	public static String foreachID = "";	//added by goto 20161019 for new foreach

	//added by goto 20161112 for dynamic foreach
	public static boolean G3 = false;
	public static boolean dynamic_G3 = false;
	public static boolean stream_G3 = false;
	public static ArrayList<String> dynamic_G3_atts = new ArrayList<>();
	public static ArrayList<String> stream_G3_atts = new ArrayList<>();
//	static int G3_while_i = 0;

    Manager manager;

    Mobile_HTML5Env html_env;
    Mobile_HTML5Env html_env2;

    private String backfile = new String();

    private int countinstance = 0;

    //���󥹥ȥ饯��
    public Mobile_HTML5G3(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
        this.manager = manager;
        this.html_env = henv;
        this.html_env2 = henv2;
    }

    //G3��work�᥽�å�
    @Override
	public String work(ExtList data_info) {
    	//added by goto 20161112 for dynamic foreach
    	G3 = true;
    	dynamic_G3 = false;
			stream_G3 = false;
//    	G3_while_i = 0;

		//added by goto 20161019 for new foreach
		final String ID = LinkForeach.ID1;
		StringBuffer foreachContents = new StringBuffer((!Compiler.isCompiler && (!Mobile_HTML5_dynamic.dynamicDisplay || !Mobile_HTML5_stream.streamDisplay))? LinkForeach.getJS("G3", "") : "");	//added by goto 20161112 for dynamic foreach

        String parentfile = html_env.filename;
        String parentnextbackfile = html_env.nextbackfile;
        StringBuffer parentcode = html_env.code;
        StringBuffer parentcss = html_env.css;
        StringBuffer parentheader = html_env.header;
        StringBuffer parentfooter = html_env.footer;
//        String parentfile2 = html_env2.filename;
//        String parentnextbackfile2 = html_env2.nextbackfile;
//        StringBuffer parentcode2 = html_env2.code;
//        StringBuffer parentcss2 = html_env2.css;
//        StringBuffer parentheader2 = html_env2.header;
//        StringBuffer parentfooter2 = html_env2.footer;
        Log.out("------- G3 -------");

        html_env.css = new StringBuffer();
        html_env.header = new StringBuffer();
        html_env.footer = new StringBuffer();
//        html_env2.css = new StringBuffer();
//        html_env2.header = new StringBuffer();
//        html_env2.footer = new StringBuffer();

        if(Sass.isBootstrapFlg()){
        	Sass.beforeLoop();
        }

        this.setDataList(data_info);
        while (this.hasMoreItems()) {
        	//Log.e(G3_and_dynamic);
        	//added by goto 20161112 for dynamic foreach
        	//if(dynamic_G3)	break;
            html_env.setGlevel(html_env.getGlevel() + 1);

//            boolean b = tfe instanceof Attribute;
        	html_env.code = new StringBuffer();
//            html_env2.code = new StringBuffer();

            /*
             * ����Foreach func�Ǥʤ��?�����̤�G3
             */
            if (!html_env.foreach_flag) {
                backfile = html_env.nextbackfile;
                html_env.countfile++;
                countinstance++;
                html_env.filename = html_env.outfile
                        + String.valueOf(html_env.countfile) + Compiler.getExtension();
                html_env.nextbackfile = html_env.linkoutfile
                        + String.valueOf(html_env.countfile) + Compiler.getExtension();
            }

            html_env.setOutlineMode();
            this.worknextItem();

			if(!Start_Parse.foreach1Flag){
				//added by goto 20161019 for new foreach
//				if(!dynamic_G3){	//added by goto 20161112 for dynamic foreach
//					html_env.code.insert(0, "<DIV G3 id=\""+ID+foreachID+"\" style=\"display:none\">\n");
//					html_env.code.append("</DIV>\n\n");
//				}else{
//					foreachContents = new StringBuffer();	//for Mobile_HTML5/Bootstrap dynamic_G3
//				}
				//added by goto 20161112 for dynamic foreach
				html_env.code.insert(0, "<DIV G3 id=\""+ID+foreachID+"\" style=\"display:none\">\n");
				html_env.code.append("</DIV>\n\n");
				foreachContents.append(html_env.code);
			}

            if (!html_env.foreach_flag) {
                setLinkButton();
            }
            html_env.setGlevel(html_env.getGlevel() - 1);

			if(Start_Parse.foreach1Flag){
				//added by goto 20161025 for link1/foreach1
	            html_env.getHeader(1);
	            html_env.getFooter(1);
	//            html_env2.header.append("<?xml version=\"1.0\" encoding=\""+html_env.getEncode()+"\"?><SSQL>");
	//            html_env2.footer.append("</SSQL>");

	            if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
		            try {
		        		//changed by goto 20120715_2 start
		        		PrintWriter pw;
			            if (html_env.charset != null){
				        	pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				        			new FileOutputStream(html_env.filename),html_env.charset)));
				        	//Log.info("File encoding: "+html_env.charset);
			            }else
			            	pw = new PrintWriter(new BufferedWriter(new FileWriter(
			        	                    html_env.filename)));
		        		//changed by goto 20120715_2 end

						//changed by goto 20161019 for HTML Formatter
						String html = "" + html_env.header + html_env.code + html_env.footer;
						if(!Start_Parse.sessionFlag)
							html = FileFormatter.process(html);
						pw.println(html);

		                pw.close();
		//                if(GlobalEnv.isOpt()){
		//	                html_env2.filename = html_env.filename.substring(0,html_env.filename.lastIndexOf(".html"))+".xml";
		//	        		//changed by goto 20120715_2 start
		//		        	//PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(
		//		            //        html_env2.filename)));
		//	        		PrintWriter pw2;
		//		            if (html_env.charset != null){
		//			        	pw2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
		//			        			new FileOutputStream(html_env2.filename),html_env.charset)));
		//			        	//Log.info("File encoding: "+html_env.charset);
		//		            }else
		//		            	pw2 = new PrintWriter(new BufferedWriter(new FileWriter(
		//		        	                    html_env2.filename)));
		//		            //Log.info("File encoding: "+((html_env.charset!=null)? html_env.charset : "UTF-8"));
		//	        		//changed by goto 20120715_2 end
		//
		//	                pw2.println(html_env2.header);
		//	                pw2.println(html_env2.code);
		//	                pw2.println(html_env2.footer);
		//	                pw2.close();
		//	                Mobile_HTML5optimizer xml = new Mobile_HTML5optimizer();
		//	                String xml_str = xml.generateHtml(html_env2.filename);
		//	                pw = new PrintWriter(new BufferedWriter(new FileWriter(html_env.filename)));
		//					pw.println(html_env.header);
		//					pw.println(xml_str);
		//					StringBuffer footer = new StringBuffer("</div></body></html>");
		//					pw.println(footer);
		//					pw.close();
		//                }
		                html_env.header = new StringBuffer();
						Jscss.process();
		                html_env.footer = new StringBuffer();
		//                html_env2.header = new StringBuffer();
		//                html_env2.footer = new StringBuffer();
		            } catch (FileNotFoundException fe) {
		                System.err.println("Error: specified outdirectory \""
		                        + html_env.outdir + "\" is not found");
		                GlobalEnv.addErr("Error: specified outdirectory \""
		                        + html_env.outdir + "\" is not found");
		            } catch (IOException e) {
		                System.err.println("Error[HTMLG3]: File IO Error in HTMLG3");
		                e.printStackTrace();
		                GlobalEnv.addErr("Error[HTMLG3]: File IO Error in HTMLG3");
		            }
	            }else{
	            	Jscss.process();
	            }
			}
			if(Sass.isBootstrapFlg()){
            	Sass.afterFirstLoop();
            }
        }

        if (Sass.isBootstrapFlg()){
        	Sass.afterLoop();
        }

		if(!Start_Parse.foreach1Flag){
			//added by goto 20161019 for new foreach
			html_env.getHeader(1);
			html_env.getFooter(1);

			if(!Responsive.isReExec()){	//added by goto 20161217  for responsive
				try {
					PrintWriter pw;
					if (html_env.charset != null) {
						pw = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(
										html_env.outfile+Compiler.getExtension()), html_env.charset)));
					} else
						pw = new PrintWriter(new BufferedWriter(new FileWriter(
								html_env.filename+Compiler.getExtension())));

					//changed by goto 20161019 for HTML Formatter
					String html = "" + html_env.header + foreachContents + html_env.footer;
					if(!Start_Parse.sessionFlag)
						html = FileFormatter.process(html);
					pw.println(html);

					pw.close();

					Jscss.process();
				} catch (FileNotFoundException fe) {
					Log.err("Error: specified outdirectory \""
							+ html_env.outdir + "\" is not found");
					GlobalEnv.addErr("Error: specified outdirectory \""
							+ html_env.outdir + "\" is not found");
				} catch (Exception e) {
					Log.err("Error[HTMLG3]: File IO Error in HTMLG3");
					e.printStackTrace();
					GlobalEnv.addErr("Error[HTMLG3]: File IO Error in HTMLG3");
				}
			}else{
				Jscss.process();
			}
		}

        html_env.filename = parentfile;
        html_env.code = parentcode;
        html_env.css = parentcss;
        html_env.header = parentheader;
        html_env.footer = parentfooter;
        html_env.nextbackfile = parentnextbackfile;
//        html_env2.filename = parentfile2;
//        html_env2.code = parentcode2;
//        html_env2.css = parentcss2;
//        html_env2.header = parentheader2;
//        html_env2.footer = parentfooter2;
//        html_env2.nextbackfile = parentnextbackfile2;

        Log.out("TFEId = " + Mobile_HTML5Env.getClassID(this));
        html_env.append_css_def_td(Mobile_HTML5Env.getClassID(this), this.decos);

        //added by goto 20161112 for dynamic foreach
        G3 = false;
        dynamic_G3 = false;
				stream_G3 = false;
//        G3_while_i = 0;
        return null;
    }

    private void setLinkButton() {
        String nextfile = new String();
        nextfile = html_env.linkoutfile.substring(html_env.linkoutfile.lastIndexOf("/")+1)
        		+ String.valueOf(html_env.countfile + 1) + ".html";
        html_env.code.append("<DIV class=\"linkbutton "
                + Mobile_HTML5Env.getClassID(tfe) + "\">\n");
        if (countinstance > 1) {
            html_env.code.append("<A href=\"" + backfile.substring(html_env.linkoutfile.lastIndexOf("/")+1) + "\">");
            html_env.code.append("[back]");
            html_env.code.append("</A>\n");
        }
        if (this.hasMoreItems()) {
            html_env.code.append("<A href=\"" + nextfile + "\">");
            html_env.code.append("[next]");
            html_env.code.append("</A>\n");
        }
        html_env.code.append("</div>\n");
    }

    @Override
	public String getSymbol() {
        return "Mobile_HTML5G3";
    }

}
