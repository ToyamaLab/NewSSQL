package supersql.codegenerator.VR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;

public class VRManager extends Manager {

	private VREnv vrEnv;
	private VREnv vrEnv2;
	public static boolean vrflag = false;
	
	public static ArrayList<String> multiexh = new ArrayList<>();////展示物を複数くっつけて並べる、使わない
	public static ArrayList<Integer> gindex = new ArrayList<>();////展示物を複数くっつけて並べる、使わない
	public static int nest1count = 0;

	public VRManager(VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	private void connectOutdir(String outdir, String outfile) {
		// added by goto 20120627 start
		String fileDir = new File(vrEnv.outFile).getAbsoluteFile().getParent();
		if (fileDir.length() < vrEnv.outFile.length()
				&& fileDir.equals(vrEnv.outFile.substring(0, fileDir.length()))){
			vrEnv.outFile = vrEnv.outFile.substring(fileDir.length() + 1); // 鐃緒申鐃出パワ申鐃春ワ申鐃緒申鐃緒申名
		}
		// added by goto 20120627 end

		String tmpqueryfile = new String();
		if (vrEnv.outFile.indexOf("/") > 0) {
			if (outfile != null) {
				if (vrEnv.outFile.startsWith(".")) {
					tmpqueryfile = vrEnv.outFile.substring(vrEnv.outFile
							.indexOf("/") + 1);
				}
			} else {
				tmpqueryfile = vrEnv.outFile.substring(vrEnv.outFile
						.lastIndexOf("/") + 1);
			}
		} else {
			tmpqueryfile = vrEnv.outFile;
		}
		if (!outdir.endsWith("/")) {
			outdir = outdir.concat("/");
		}
		vrEnv.outFile = outdir.concat(tmpqueryfile);
	}

	private String getOutfile(String outfile) {
		String out = new String();
		if (outfile.indexOf(".xml") > 0) {
			out = outfile.substring(0, outfile.indexOf(".xml"));
		} else {
			out = outfile;
		}
		return out;
	}

	protected void getOutfilename() {
		String file = GlobalEnv.getfilename();
		String outdir = GlobalEnv.getoutdirectory();
		String outfile = GlobalEnv.getoutfilename();
		vrEnv.outDir = outdir;

		if (GlobalEnv.getQuery() != null) {
			vrEnv.outFile = "./fromquery";

		} else if (outfile == null) {
			if (file.toLowerCase().indexOf(".sql") > 0) {
				vrEnv.outFile = file.substring(0,
						file.toLowerCase().indexOf(".sql"));
			} else if (file.toLowerCase().indexOf(".ssql") > 0) {
				vrEnv.outFile = file.substring(0,
						file.toLowerCase().indexOf(".ssql"));
			}
		} else {
			vrEnv.outFile = getOutfile(outfile);
		}

		if (vrEnv.outFile.indexOf("/") > 0) {
			vrEnv.linkOutFile = vrEnv.outFile.substring(vrEnv.outFile
					.lastIndexOf("/") + 1);
		} else {
			vrEnv.linkOutFile = vrEnv.outFile;
		}

		if (outdir != null) {
			connectOutdir(outdir, outfile);
		}
	}

	@Override
	public void finish() {

	}

	@Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {


		vrEnv.countFile = 0;
		vrEnv.code = new StringBuffer();
		VREnv.css = new StringBuffer();
		VREnv.header = new StringBuffer();
		vrEnv.footer = new StringBuffer();
		vrEnv.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv.writtenClassId = new Vector<String>();
		vrEnv.notWrittenClassId = new Vector<String>();
		vrEnv2.countFile = 0;
		vrEnv2.code = new StringBuffer();
		vrEnv2.footer = new StringBuffer();
		vrEnv2.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv2.writtenClassId = new Vector<String>();

		/*** start oka ***/

		getOutfilename();

		Log.out("[VRManager:generateCode]");
		
		vrEnv.fileName = vrEnv.outFile + ".xml";
		vrEnv2.fileName = vrEnv.fileName;
		
		vrEnv.setOutlineMode();
		VREnv.header.append("<?xml version=\"1.0\" ?>");///kotaniadd
		if (!CodeGenerator.unity_dv_flag){
			vrEnv.getFooter();
		}
		if (data_info.size() == 0
				&& !DataConstructor.SQL_string
				.equals("SELECT DISTINCT  FROM ;") && !DataConstructor.SQL_string.equals("SELECT  FROM ;")) {
			Log.out("no data");
//			if (!CodeGenerator.unity_dv_flag) {
				Element doc = vrEnv.xml.createElement("DOC");
				doc.setTextContent("NO DATA FOUND");
				vrEnv.xml.appendChild(doc);
				vrEnv.code.append("<DOC>");
				vrEnv.code.append("NO DATA FOUND");
				vrEnv.code.append("</DOC>");
//			}else{
//				vrEnv.xml.appendChild(doc);
//			}
		} else{
			tfe_info.work(data_info);
//			vrEnv.code = new StringBuffer(vrEnv.code.substring(0,vrEnv.code.lastIndexOf("<group>")));
		}
		VREnv.cs_code.append("9 "+tfe_info+"\n");
		
		try {
			if(CodeGenerator.getMedia().equalsIgnoreCase("vr") || 
			   CodeGenerator.getMedia().equalsIgnoreCase("unity") ||
			   CodeGenerator.getMedia().equalsIgnoreCase("unity_museum") ||
			   CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")
			   ){
				//xmlcreateに使った
				if (!GlobalEnv.isOpt()) {
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = null;
					try {
						transformer = transformerFactory.newTransformer();
						transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
						transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
						transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
						DOMSource source = new DOMSource(vrEnv.xml);
						StreamResult result = new StreamResult(new File(vrEnv.fileName));
						transformer.transform(source, result);
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				
				}

				// xml
				
				if (GlobalEnv.isOpt()) {
					PrintWriter pw2 = new PrintWriter(new File(vrEnv2.fileName));
					pw2.println(VREnv.header);
					pw2.println(vrEnv2.code);
					pw2.println(vrEnv2.footer);
					pw2.close();
					
					//TODO: check if the optimizer does anything on the generated XML
					//TODO: check if the generated XML can/should be optimized
					VRoptimizer xml = new VRoptimizer();
					String xml_str = xml.generateHtml(vrEnv2.fileName);
					PrintWriter pw = new PrintWriter(new File(vrEnv.fileName));
					pw.println(VREnv.header);
					pw.println(xml_str);
					pw.println(vrEnv.footer);
					pw.close();
				}
			} else { //TODO: check we don't need this bit, i.e VRManager should not be invoked if the media is not VR.
				Log.ehtmlInfo("=-=-=-=");
				Log.ehtmlInfo(VREnv.header);
				Log.ehtmlInfo(vrEnv.code);
				Log.ehtmlInfo(vrEnv.footer);
			}

			try{
//				VRfilecreate.process(vrEnv.outFile); //Generate and save the cs code ここは後日直す！！
			}catch(Exception e){
//				System.err.println("No cs file generated");
				e.printStackTrace();
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			Log.err("Error: specified outdirectory \""
					+ vrEnv.outDir + "\" is not found to write "
					+ vrEnv.fileName);
			GlobalEnv.addErr("Error: specified outdirectory \""
					+ vrEnv.outDir + "\" is not found to write "
					+ vrEnv.fileName);
		} catch (IOException e) {
			System.err
			.println("Error[VRManager]: File IO Error in VRManager");
			e.printStackTrace();
			GlobalEnv
			.addErr("Error[VRManager]: File IO Error in VRManager");
		}

	}

	//TODO: Check with Goto san the meaning of generateCode2/3 and if they are needed.
	@Override
	public StringBuffer generateCode2(ITFE tfe_info, ExtList data_info) {

		vrEnv.countFile = 0;
		vrEnv.code = new StringBuffer();
		VREnv.css = new StringBuffer();
		VREnv.header = new StringBuffer();
		vrEnv.footer = new StringBuffer();
		vrEnv.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv.writtenClassId = new Vector<String>();
		vrEnv.embedFlag = true;

		vrEnv2.countFile = 0;
		vrEnv2.code = new StringBuffer();
		vrEnv2.footer = new StringBuffer();
		String xml_str = null;
		StringBuffer returncode = new StringBuffer();
		getOutfilename();

		Log.out("[VRManager:generateCode2]");

		if (tfe_info instanceof VRG3) {
			tfe_info.work(data_info);
			VREnv.cs_code.append("10 "+tfe_info+"\n");
			return vrEnv.code;
		}
		vrEnv.setOutlineMode();
		tfe_info.work(data_info);
		VREnv.cs_code.append("100 "+tfe_info+"\n");

		VREnv.header.append("<?xml version=\"1.0\" encoding=\"Shift_JIS\"?><SSQL>");
		vrEnv2.footer.append("</SSQL>");

		if (GlobalEnv.isOpt()) {
			int i = 0;
			while (vrEnv2.code.indexOf("&", i) != -1) {
				i = vrEnv2.code.indexOf("&", i);
				vrEnv2.code = vrEnv2.code.replace(i, i + 1, "&amp;");
				i++;
			}
			StringBuffer xml_string = new StringBuffer();
			xml_string.append(VREnv.header);
			xml_string.append(vrEnv2.code);
			xml_string.append(vrEnv2.footer);
			VRoptimizer xml = new VRoptimizer();
			xml_str = xml.generateHtml(xml_string);
			returncode.append(xml_str);
		}
		vrEnv.embedFlag = false;

		//TODO: check that script is not VR related
		if (vrEnv.script.length() >= 5) {
			StringBuffer result = new StringBuffer();

			result.append(vrEnv.script);
			result.append("<end of script>\n");
			result.append(vrEnv.code);

			return result;
		} else {
			if (GlobalEnv.isOpt())
				return returncode;
			else
				return vrEnv.code;
		}
	}

	@Override
	public StringBuffer generateCode3(ITFE tfe_info, ExtList data_info) {

		vrEnv.countFile = 0;
		vrEnv.code = new StringBuffer();
		VREnv.css = new StringBuffer();
		VREnv.header = new StringBuffer();
		vrEnv.footer = new StringBuffer();
		vrEnv.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv.writtenClassId = new Vector<String>();
		vrEnv.embedFlag = true;
		
		getOutfilename();

		Log.out("[VRManager:generateCode3]");

		if (tfe_info instanceof VRG3) {
			tfe_info.work(data_info);
			VREnv.cs_code.append("101 "+tfe_info+"\n");
			return vrEnv.code;
		}

		vrEnv.setOutlineMode();
		tfe_info.work(data_info);
		VREnv.cs_code.append("102 "+tfe_info+"\n");
		vrEnv.embedFlag = false;
		Log.out("header : " + VREnv.header);
		return VREnv.css;
	}

	@Override
	public StringBuffer generateCode4(ITFE tfe_info, ExtList data_info) {
		vrEnv.countFile = 0;
		vrEnv.code = new StringBuffer();
		VREnv.css = new StringBuffer();
		VREnv.header = new StringBuffer();
		vrEnv.footer = new StringBuffer();
		vrEnv.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv.writtenClassId = new Vector<String>();

		vrEnv2.countFile = 0;
		vrEnv2.code = new StringBuffer();
		vrEnv2.footer = new StringBuffer();
		vrEnv2.foreachFlag = GlobalEnv.getForeachFlag();
		vrEnv2.writtenClassId = new Vector<String>();

		getOutfilename();

		Log.out("[VRManager:generateCode4]");

		vrEnv.fileName = vrEnv.outFile + ".xml";
		vrEnv2.fileName = vrEnv.fileName;

		vrEnv.setOutlineMode();
		tfe_info.work(data_info);
		VREnv.cs_code.append("103 "+tfe_info+"\n");
		
		if (!CodeGenerator.unity_dv_flag){
			vrEnv.getFooter();
		}
		
		vrEnv.embedFlag = false;
		Log.out("header : " + VREnv.header);

		StringBuffer headfoot = new StringBuffer(VREnv.header
				+ " ###split### " + vrEnv.footer);

		return headfoot;
	}

	@Override
	public StringBuffer generateCodeNotuple(ITFE tfe_info) {
		Log.out("no data found");
		vrEnv.code = new StringBuffer();
		if (!CodeGenerator.unity_dv_flag) {
		vrEnv.code.append("<DOC>");
		vrEnv.code.append("NO DATA FOUND");
		vrEnv.code.append("</DOC>");
		}
		return vrEnv.code;
	}
}