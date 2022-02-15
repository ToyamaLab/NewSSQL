package supersql.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.parser.Start_Parse;

public class Incremental {
	public static boolean flag = false;
	public static boolean flag2 = false;
	
	
	public Incremental(){
		
	}
	
	// set incremental flag
	public static void setIncremental(){
		if (Ehtml.isMediaHTML)
			flag = true;		// for HTML 	// html出力テスト用にコメントアウト 20191009
		else
			setIncremental2();	// for ResponsiveHTML
		
//		//TODO
//		String m = CodeGenerator.getMedia().toLowerCase();
//		Log.ehtmlInfo("Ehtml media: "+m);
//		if(m.equals("html")){
//			flag = true;
//		}else{
//			setIncremental2();
//		}
	}
	public static void setIncremental2(){
		flag2 = true;
		//Ehtml.setEhtml2();
	}
	
	// output XMLData
	public static void outXMLData(int depth, String str){
		String[] factry = CodeGenerator.getFactory().toString().split("\\.");
		if(flag || Ehtml.flag){
			String indentchar = "";
			for (int i = 0; i < depth; i++) {
				indentchar += "\t";
			}
			if(factry[2].equals("HTML")){
				HTMLEnv.xmlCode.append(indentchar + str);
			// }else if(factry[2].equals("Mobile_HTML5")){
			}else if(Ehtml.media == 1){
				Mobile_HTML5Env.xmlCode.append(indentchar + str);
			}
		} else {
			return;
		}
	}
	
	// create xml data for incremental update
	public static void createXML(String outFile, StringBuffer sb) {
		if(flag || Ehtml.flag){
			File file = new File(outFile.substring(0, outFile.lastIndexOf(GlobalEnv.OS_FS)));
			if ( !file.exists() ) {
				file.mkdirs();
			}
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile), "UTF-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			pw.println(sb);
			pw.close();
		} else {
			return;
		}
	}
	
	// save ssqlparser instance
	public static void save(Start_Parse parser) {
		// オブジェクトをファイルに保存
		try {
			FileOutputStream outFile = new FileOutputStream("/Users/masato/saveData.dat");
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.writeObject(parser);
			out.close();
			outFile.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// road ssqlparser instance
	public static Start_Parse road() {
		// オブジェクトの読み込み
		Start_Parse parser = null;
		try {
			FileInputStream inFile = new FileInputStream("/Users/masato/saveData.dat");
			ObjectInputStream ois = new ObjectInputStream(inFile);
			parser = (Start_Parse)ois.readObject();
		    ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return parser;
	}
	
}
