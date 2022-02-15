package supersql.codegenerator;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.bit3.jsass.CompilationException;
import io.bit3.jsass.Compiler;
import io.bit3.jsass.Options;
import io.bit3.jsass.Output;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.Responsive.Responsive;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Sass {
	private static boolean bootstrapFlg = false;
	private static boolean firstElementFlg = true;

	public static StringBuffer sass = new StringBuffer();
	public static ArrayList<String> writtenSassClassid = new ArrayList<String>();

	private static final String fs = GlobalEnv.OS_FS;
	private static final String outdirPath = GlobalEnv.getOutputDirPath();
	private static final String generateCssFileDir = Jscss.generateCssFileDir;

	public static boolean loopFlg = true;
	
	public static int responsiveCandidateId = 0;
	public static LinkedHashMap<Integer, LinkedHashMap> HTMLCheckMap = new LinkedHashMap<Integer, LinkedHashMap>();
	
	public static void incrementId(){
		responsiveCandidateId++;
	}

	public static Deque<Boolean> outofloopFlg = new ArrayDeque<Boolean>();
	static{
		outofloopFlg.offerFirst(true);
	}
	public static void bootstrapFlg(boolean b){
		bootstrapFlg = b;
	}
	
	public static boolean isBootstrapFlg(){
		return bootstrapFlg;
	}
	
	public static void firstElementFlg(boolean b){
		firstElementFlg = b;
	}
	
	public static boolean isFirstElementFlg(){
		return firstElementFlg;
	}
	

	public static void beforeLoop(){
		outofloopFlg.offerFirst(true);
	}

	public static void afterFirstLoop(){
		outofloopFlg.pollFirst();
		outofloopFlg.offerFirst(false);
	}

	public static void afterLoop(){
		outofloopFlg.pollFirst();
	}

	public static void makeRowClass(){
		sass.append(".row{\n");
	}

	public static void makeClass(String classid){
		sass.append("." + classid + "{\n");
	}

	public static void makeRow(String classid){
		sass.append("." + classid + "{\n\t@include make-row();\n");
	}

	public static void closeBracket(){
		sass.append("}\n");
	}
	
	public static void makeColumn(String classid, DecorateList decos, String C1G1, int responsiveId){
		if(!writtenSassClassid.contains(classid)){
			makeClass(classid);
			defineGridBasic(classid, decos, C1G1, responsiveId);
			closeBracket();
			writtenSassClassid.add(classid);
		}	
	}

	public static LinkedHashMap beforeC1WhileProcess(ExtList tfes){
//		LinkedHashMap<String,LinkedHashMap> gridMapResult = new LinkedHashMap<String,LinkedHashMap>();
		LinkedHashMap<String,DecorateList> gridMapResult = new LinkedHashMap<String,DecorateList>();
		LinkedHashMap<String,LinkedHashMap> gridMap = new LinkedHashMap<String,LinkedHashMap>();
		int xs_count=0;
		int sm_count=0;
		int md_count=0;
		int lg_count=0;

		Fraction xs_total = new Fraction("0/1");
		Fraction sm_total = new Fraction("0/1");
		Fraction md_total = new Fraction("0/1");
		Fraction lg_total = new Fraction("0/1");

//		Log.info(tfes.size());
		for( int i=0; i<tfes.size(); i++){
			LinkedHashMap<String,Fraction> sizeMap = new LinkedHashMap<String,Fraction>();
			ITFE tfe = (ITFE) tfes.get(i);
			String classid = Mobile_HTML5Env.getClassID(tfe);

			if( ((TFE)tfe).decos.containsKey("xs")){
				xs_count++;

				Fraction xs = new Fraction(((TFE)tfe).decos.getStr("xs"));
				sizeMap.put("xs", xs);

				xs_total.addition(xs);
			}
			if( ((TFE)tfe).decos.containsKey("sm")){
				sm_count++;

				Fraction sm = new Fraction(((TFE)tfe).decos.getStr("sm"));
				sizeMap.put("sm", sm);

				sm_total.addition(sm);
			}
			if( ((TFE)tfe).decos.containsKey("md")){
				md_count++;

				Fraction md = new Fraction(((TFE)tfe).decos.getStr("md"));
				sizeMap.put("md", md);

				md_total.addition(md);
			}
			if( ((TFE)tfe).decos.containsKey("lg")){
				lg_count++;

				Fraction lg = new Fraction(((TFE)tfe).decos.getStr("lg"));
				sizeMap.put("lg", lg);

				lg_total.addition(lg);
			}
			gridMap.put(classid,sizeMap);//classid -> (xs, 1/2)
		}
//		Log.info(gridMap);
		for(Map.Entry<String, LinkedHashMap> entry : gridMap.entrySet()) {
//			LinkedHashMap<String,String> sizeMapResult = new LinkedHashMap<String,String>();
			DecorateList sizeMapResult = new DecorateList();
			DecorateList result = new DecorateList();

		    String class_id = entry.getKey();
		    LinkedHashMap<String,Fraction> sizeMap = new LinkedHashMap<String,Fraction>();
		    sizeMap = entry.getValue();
//		    sass.append("." + entry.getKey() + "{\n");//class出力
			if(xs_count==0 && sm_count==0 && md_count==0 && lg_count==0){//none specified
//				Fraction xs = new Fraction("1/" + (tfes.size() - Mobile_HTML5Function.func_null_count));
				sizeMapResult.put("xs", "1/" + (tfes.size() - Mobile_HTML5Function.func_null_count));
				//define grid-column for tfe.size()-null.count
				//make grid-xs-column: 1
			}else{
				if(xs_count>0){
					if(sizeMap.containsKey("xs")){
						sizeMapResult.put("xs", sizeMap.get("xs").toString());
//						sizeMap.get("xs").getDenominator();
//						sizeMapResult.put("xs_grid", sizeMap.get("xs").getDenominator());
//						sizeMapResult.put("xs_col", sizeMap.get("xs").getNumerator());
					}else{
						int limit = xs_total.getNumerator()/xs_total.getDenominator() + 1;

						Fraction assignRest = new Fraction(limit + "/1");
						assignRest.subtraction(xs_total);
						assignRest.divideby(tfes.size()-xs_count);

						sizeMapResult.put("xs", assignRest.toString() );
					}
				}
				if(sm_count>0){
					if(sizeMap.containsKey("sm")){
						sizeMapResult.put("sm", sizeMap.get("sm").toString());
					}else{
						int limit = sm_total.getNumerator()/sm_total.getDenominator() + 1;

						Fraction assignRest = new Fraction(limit + "/1");
						assignRest.subtraction(sm_total);
						assignRest.divideby(tfes.size()-sm_count);

						sizeMapResult.put("sm", assignRest.toString() );
					}
				}
				if(md_count>0){
					if(sizeMap.containsKey("md")){
						sizeMapResult.put("md", sizeMap.get("md").toString());
					}else{
						int limit = md_total.getNumerator()/md_total.getDenominator() + 1;

						Fraction assignRest = new Fraction(limit + "/1");
						assignRest.subtraction(md_total);
						assignRest.divideby(tfes.size()-md_count);

						sizeMapResult.put("md", assignRest.toString() );
					}
				}
				if(lg_count>0){
					if(sizeMap.containsKey("lg")){
						sizeMapResult.put("lg", sizeMap.get("lg").toString());
					}else{
						int limit = lg_total.getNumerator()/lg_total.getDenominator() + 1;

						Fraction assignRest = new Fraction(limit + "/1");
						assignRest.subtraction(lg_total);
						assignRest.divideby(tfes.size()-lg_count);

						sizeMapResult.put("lg", assignRest.toString() );
					}
				}
			}
			gridMapResult.put(class_id, sizeMapResult);
		}
		return gridMapResult;

	}

	public static void defineGrid(int columnNum){
		sass.append("$grid-columns:" + columnNum + " !global;\n");
	}

	public static void defineGridBasic(String classid, DecorateList decos, String C1G1, int responsiveId){
		LinkedHashMap<String, LinkedHashMap> C1G1Map = new LinkedHashMap<String, LinkedHashMap>();
		LinkedHashMap<String, LinkedHashMap> ClassMap = new LinkedHashMap<String, LinkedHashMap>();
		LinkedHashMap<String, Fraction> SizeMap = new LinkedHashMap<String, Fraction>();
		
		int xs_grid;
		int sm_grid;
		int md_grid;
		int lg_grid;

		int xs_col;
		int sm_col;
		int md_col;
		int lg_col;
		
		if(Responsive.fixMap.containsKey(classid)){
			LinkedHashMap<String, Fraction> fixSizeMap = Responsive.fixMap.get(classid);
			 for(Entry<String, Fraction> e : fixSizeMap.entrySet()) {
				 String decos_key = e.getKey();
				 String decos_value = e.getValue().toString();
				 decos.put(decos_key, decos_value);
			 }
		}
		
		
		if(!decos.containsKey("xs") && !decos.containsKey("sm") && !decos.containsKey("md") && !decos.containsKey("lg")){//none specified
			if(decos.containsKey("G1")){
				sass.append("	$grid-columns:" + decos.getStr("G1") + " !global;\n");
				sass.append("	@include make-xs-column(1);\n");
				SizeMap.put("xs", new Fraction("1/"+decos.getStr("G1")));
			}else{
				xs_grid = 1;
				xs_col=1;
				sass.append("	$grid-columns:" + xs_grid + " !global;\n");
				sass.append("	@include make-xs-column("+ xs_col + ");\n");
				SizeMap.put("xs", new Fraction("1/1"));
			}
		}else{//some or all specified
			if(decos.containsKey("xs")){
				Fraction xs = new Fraction(decos.getStr("xs"));
				xs_grid = xs.getDenominator();
				xs_col = xs.getNumerator();
				sass.append("	$grid-columns:" + xs_grid + " !global;\n");
				sass.append("	@include make-xs-column("+ xs_col + ");\n");
				SizeMap.put("xs", xs);
			}
			if(decos.containsKey("sm")){
				Fraction sm = new Fraction(decos.getStr("sm"));
				sm_grid = sm.getDenominator();
				sm_col = sm.getNumerator();
				sass.append("	$grid-columns:" + sm_grid + " !global;\n");
				sass.append("	@include make-sm-column("+ sm_col + ");\n");
				SizeMap.put("sm", sm);
			}
			if(decos.containsKey("md")){
				Fraction md = new Fraction(decos.getStr("md"));
				md_grid = md.getDenominator();
				md_col = md.getNumerator();
				sass.append("	$grid-columns:" + md_grid + " !global;\n");
				sass.append("	@include make-md-column("+ md_col + ");\n");
				SizeMap.put("md", md);
			}
			if(decos.containsKey("lg")){
				Fraction lg = new Fraction(decos.getStr("lg"));
				lg_grid = lg.getDenominator();
				lg_col = lg.getNumerator();
				sass.append("	$grid-columns:" + lg_grid + " !global;\n");
				sass.append("	@include make-lg-column("+ lg_col + ");\n");
				SizeMap.put("lg", lg);
			}
		}
		
		ClassMap.put(classid, SizeMap);
		if(C1G1.equals("Mobile_HTML5G1")){
			C1G1Map.put("G1", ClassMap);
			HTMLCheckMap.put(responsiveId, C1G1Map);
		}else if(C1G1.equals("Mobile_HTML5C1")){
			if(HTMLCheckMap.containsKey(responsiveId)){
				LinkedHashMap<String,LinkedHashMap> nowMap = (LinkedHashMap<String, LinkedHashMap>) HTMLCheckMap.get(responsiveId).get("C1");
				nowMap.put(classid, SizeMap);
			}else{
				C1G1Map.put("C1", ClassMap);
				HTMLCheckMap.put(responsiveId, C1G1Map);
			}	
		}
		
	}

	public static void output(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outdirPath+fs+"jscss"+fs+"forBootstrap"+fs+"_bootstrap.scss"), "UTF-8"));

			StringBuffer sb = new StringBuffer();

			String str;
			while((str = in.readLine()) != null){
				sb.append(str + "\n");
			}

			sb.append(sass);
			
			writtenSassClassid.clear();

//			Log.info(sb);

			URI inputFile = new File(outdirPath+fs+"jscss"+fs+"forBootstrap"+fs+"_bootstrap.scss").toURI();
		    URI outputFile = new File(outdirPath+fs+"stylesheet.css").toURI();

			Compiler compiler = new Compiler();
			Options options = new Options();

			Output output = compiler.compileString(sb.toString(), inputFile, outputFile, options);

//			Log.info(output.getCss());
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter
					(new FileOutputStream(outdirPath+fs+"stylesheet.css"), "UTF-8")));
			pw.println(output.getCss());
			pw.close();

//			out.println("Compiled successfully");
//			//            out.println(output.getCss());
//			File file = new File("/Users/ryosuke/Desktop/style.css");
//			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
//			pw.println(output.getCss());
//			pw.close();
		} catch (CompilationException e) {
			err.println("Compile failed");
			e.printStackTrace(err);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
	public static String compile(){
		String r="";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outdirPath+fs+"jscss"+fs+"forBootstrap"+fs+"_bootstrap.scss"), "UTF-8"));

			StringBuffer sb = new StringBuffer();

			String str;
			while((str = in.readLine()) != null){
				sb.append(str + "\n");
			}

			sb.append(sass);
			writtenSassClassid.clear();

//			Log.ehtmlInfo("sb = "+sb);
//			Log.info(sb);
//			Log.info(HTMLCheckMap);

			URI inputFile = new File(outdirPath+fs+"jscss"+fs+"forBootstrap"+fs+"_bootstrap.scss").toURI();
			URI outputFile = new File(outdirPath+fs+"stylesheet.css").toURI();

			Compiler compiler = new Compiler();
			Options options = new Options();

			Output output = null;
			
			
			String contents_id = Ehtml.getID(1);		//TODO add number
//			String contents_id = ".container-fluid";
			if (Ehtml.isEhtml2() && Ehtml.outType==1) {
				// Specify class name (Nested sass)
//				String a1 = sb.toString() +
//					"\n" +
//					"#ssql_body_contents {" +
//					" @include 'jscss/ssql/ssqlResult1.css'; " +
//					"}\n";
//				output = compiler.compileString(a1, inputFile, outputFile, options);

				
//				output = compiler.compileString(sb.toString(), inputFile, outputFile, options);
				
				
//				String c = sb.toString();
//				String s = ".TFE";
////				String s = "// Reset and dependencies";
//				String c1 = c.substring(0, c.indexOf(s));
//				String c2 = c.substring(c.indexOf(s), c.length());
//				c = "#ssql_body_contents {\n"+c1+"}\n\n"+c2;
////				c = ""+c1+""+c2;
//				Log.ehtmlInfo("c = \n"+c);
//				output = compiler.compileString(c, inputFile, outputFile, options);
				
				
				
				Output output1, output2;
				String r1, r2;
				output1 = compiler.compileString(contents_id+" {"+sb.toString()+"}", inputFile, outputFile, options);
				output2 = compiler.compileString(""+sb.toString()+"", inputFile, outputFile, options);
				r1 = output1.getCss();
				r2 = output2.getCss();
				
				String substring_name = ".TFE";		//TODO -> ".ssql_body_contents1_TFE"
//				String substring_name = "."+Ehtml.getTFE_ID();		// e.g. ".ssql_body_contents1_TFE"
				r = r1.substring(0, r1.indexOf(contents_id+" "+substring_name)) 
				  + r2.substring(r2.indexOf(substring_name), r2.length());
				
				
//				output = compiler.compileString(".bootstrap-scope {"+sb.toString()+"}", inputFile, outputFile, options);
////				output = compiler.compileString(""+sb.toString()+"", inputFile, outputFile, options);
//				r = output.getCss();

			} else {
				output = compiler.compileString(sb.toString(), inputFile, outputFile, options);
//				output = compiler.compileString(contents_id+" {"+sb.toString()+"}", inputFile, outputFile, options);
//				System.out.println(contents_id+" {"+sb.toString()+"}");
				r = output.getCss();
				
//				Output output1, output2;
//				String r1, r2;
//				output1 = compiler.compileString(contents_id+" {"+sb.toString()+"}", inputFile, outputFile, options);
//				output2 = compiler.compileString(""+sb.toString()+"", inputFile, outputFile, options);
//				r1 = output1.getCss();
//				r2 = output2.getCss();
//				
//				r = r1.substring(0, r1.indexOf(contents_id+" .TFE")) 
//				  + r2.substring(r2.indexOf(".TFE"), r2.length());
			}
//			r = output.getCss();
			
//			if (Ehtml.isEhtml2() && Ehtml.outType==1) {
//				r = r.replace(contents_id+" html {", contents_id+" {")
//					     .replace(contents_id+" body {", contents_id+" {")
//					     .replace(contents_id+" *", contents_id+"");
			if (Ehtml.isEhtml2() && Ehtml.outType==1) {
//				r = r.replace(contents_id+" html {", contents_id+" {")
//					     .replace(contents_id+" body {", contents_id+" {")
//					     .replace(contents_id+" *", contents_id+"");
				
//				r = r.replaceAll(contents_id+" html \\{*\\}", "\n")
//					 .replaceAll(contents_id+" body \\{*\\}", "\n");	//TODO
				
				r = r.replace(contents_id+" * {", contents_id+" {");
				
				//r = r.replace(".TFE", contents_id+" .TFE");		//TODO Mobile_HTML5Env.java:"TFE"
			}
			
//			Log.ehtmlInfo(r);
//			Log.info(r);

		} catch (CompilationException e) {
			err.println("Compile failed");
			e.printStackTrace(err);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return r;
	}
}
