package supersql.codegenerator;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Compiler.JSP.JSPFactory;
import supersql.codegenerator.Compiler.PHP.PHP;
import supersql.codegenerator.Compiler.Rails.RailsFactory;
import supersql.codegenerator.HTML.HTMLCONCAT;
import supersql.codegenerator.HTML.HTMLFactory;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Factory;
import supersql.codegenerator.PDF.PDFFactory;
import supersql.codegenerator.VR.VRAttribute;
import supersql.codegenerator.VR.VRFactory;
import supersql.codegenerator.VR.VRManager;
import supersql.codegenerator.VR.VRcjoinarray;
import supersql.codegenerator.VR.VRfilecreate;
import supersql.codegenerator.VR.VRFunction;
import supersql.codegenerator.Web.WebFactory;
import supersql.codegenerator.X3D.X3DFactory;
import supersql.common.GlobalEnv;
import supersql.common.LevenshteinDistance;
import supersql.common.Log;
import supersql.common.ParseXML;
import supersql.common.Ssedit;
import supersql.dataconstructor.Ctab;
import supersql.dataconstructor.Limiter;
import supersql.extendclass.ExtList;
import supersql.parser.From;
import supersql.parser.FromTable;
import supersql.parser.Preprocessor;
import supersql.parser.Start_Parse;


public class CodeGenerator {

	private static Hashtable attp;

	private static int attno;

	private static String att_tmp;

	private static String media;

	static Factory factory;

    public static int sqlfunc_flag = 0;
    public static int inner_sqlfunc_count = 0;
    public static HashSet<String> useTablesInSQLFunc = new HashSet<>();

	//	private static boolean decocheck = false;

	public static TFE schemaTop;
	public static ExtList sch;
	public static ExtList schema;
	public static Manager manager;
	public static int TFEid;
	public static ExtList keys;//added by taji 171102

	//module20180506 kotani
	public static int filenum;
//	public static ArrayList<String> filecon= new ArrayList<>();//mediaが一致したファイルの中身
	public static String[] filesplit;

	public static boolean limitFlag;
	public static boolean unity_dv_flag = false;

	public static DecorateList decorate_summary;


	public void CodeGenerator(Start_Parse parser) {
		attno = 0;
		initialize(parser);
	}

	public static TFE initialize(ExtList tfe){
		TFE out_sch = null;
		int dim;
		out_sch = makeschematop(tfe);

		return out_sch;
	}

	public static void initiate() {
		if (factory != null) {
			Log.out("factory is " + factory);
			factory.createLocalEnv();
			manager = factory.createManager();
		}
	}

	public static Factory getFactory(){
		return factory;
	}
	public static void setFactory(String media) {
		if (media.toLowerCase().equals("html")) {
			factory = new HTMLFactory();
			Ehtml.setType(0, 0);
			// Sass.bootstrapFlg(true);	// OK?
		}else if(media.toLowerCase().equals("mobile_html5")){
			factory = new Mobile_HTML5Factory();
			Ehtml.setType(1, 1);
			// Sass.bootstrapFlg(true);	// OK?
		} else if (media.toLowerCase().equals("bhtml") || media.toLowerCase().equals("html_bootstrap") || media.toLowerCase().equals("responsivehtml")|| media.toLowerCase().equals("responsive_html")) {
			factory = new Mobile_HTML5Factory();
			Ehtml.setType(1, 1);
			Sass.bootstrapFlg(true);
		}else if(media.toLowerCase().equals("web")) {
			factory = new WebFactory();
		}else if(media.toLowerCase().equals("x3d")){
			factory = new X3DFactory();
		}else if(media.toLowerCase().equals("vr") || 
				media.toLowerCase().equals("unity") ||
				media.toLowerCase().equals("unity_museum") ||
				media.toLowerCase().equals("unity_dv")
				){
			if(media.toLowerCase().equals("unity_dv")){
				unity_dv_flag = true;
			}
			factory = new VRFactory();
			Ehtml.setType(0, 0);
		}else if(media.toLowerCase().equals("pdf")){
			factory = new PDFFactory();
		}else if(media.toLowerCase().equals("php")){	//added by goto 20161104
			PHP.isPHP = true;
			supersql.codegenerator.Compiler.Compiler.isCompiler = true;
			factory = new Mobile_HTML5Factory();
			//factory = new PHPFactory();
		}else if(media.toLowerCase().equals("rails")){	//added by goto 20161104
			factory = new RailsFactory();
		}else if(media.toLowerCase().equals("jsp")){	//added by goto 20161104
			factory = new JSPFactory();
		}
		else {
			String m = media.toLowerCase();
			Log.err("Error[Media]: valid medium '"+m+"' not found");
			//			GlobalEnv.errorText += "Error[Media]: valid medium '"+m+"' not found";
			GlobalEnv.addErr("Error[Media]: valid medium '"+m+"' not found");

			//20131106
			//Log.err("\nGENERATE >>>> "+m+" <<<<");
			String XMLfile = GlobalEnv.MEDIA_XML;
			ArrayList<String> medias = ParseXML.getAttributes(XMLfile, "media", "name");
			String media_list = LevenshteinDistance.checkLevenshteinAndSuggest(m, medias);
			if(!media_list.isEmpty()){
				Log.err("\n## Media list ##\n" + media_list);
				// 20140624_masato
				//				GlobalEnv.errorText += "\n## Media list ##\n" + media_list;
			}

			//161114 yhac
			if (GlobalEnv.isSsedit_autocorrect()) {
				Ssedit.sseditInfo();
			}

			System.exit(1);
		}
	}

	public static String getMedia(){
		return media;
	}

	public static void initialize(Start_Parse parser){
		attp = new Hashtable();
		ExtList tfe = (ExtList)parser.list_tfe.get(1);
		media = ((ExtList) parser.list_media.get(1)).get(1).toString();
		setFactory(media);
//		System.exit(0);
		initiate();
		schemaTop = initialize((ExtList)tfe.get(0));
		sch = schemaTop.makesch();

		schema = schemaTop.makeschImage();
		Log.info("Schema is " + sch);
		Log.info("le0 is " + schemaTop.makele0());


		// 2016/12/16 commentout by taji
		//		ExtList test = reverse(schemaTop.makele0());
		//		Log.info("test:" + test);
		//		Log.info( getText(test, Start_Parse.ruleNames) );

		parser.schemaTop = schemaTop;
		parser.sch = sch;
		parser.schema = schema;
	}

	// 2016/12/16 commentout by taji
	//	private static ExtList reverse(ExtList extlist){
	//		ExtList tmp = new ExtList();
	//		if(extlist.get(0).toString().endsWith("G2")){
	//			tmp.add("grouper");
	//			ExtList G = new ExtList();
	//			G.add("[");
	//			G.add( reverse((ExtList)extlist.get(1)) );
	//			G.add("]");
	//			G.add("!");
	//			tmp.add(G);
	//		}else if(extlist.get(0).toString().endsWith("G1")){
	//			tmp.add("grouper");
	//			ExtList G = new ExtList();
	//			G.add("[");
	//			G.add( reverse((ExtList)extlist.get(1)) );
	//			G.add("]");
	//			G.add(",");
	//			tmp.add(G);
	//		}else if(extlist.get(0).toString().endsWith("C2")){
	//			ExtList C = new ExtList();
	//			tmp.add("v_exp");
	//			for(int i = 1; i < extlist.size(); i++){
	//				C.add(reverse((ExtList)extlist.get(i)));
	//				if(i != extlist.size() - 1){
	//					C.add("!");
	//				}
	//			}
	//			tmp.add(C);
	//		}else if(extlist.get(0).toString().endsWith("C1")){
	//			ExtList C = new ExtList();
	//			tmp.add("h_exp");
	//			for(int i = 1; i < extlist.size(); i++){
	//				C.add(reverse((ExtList)extlist.get(i)));
	//				if(i != extlist.size() - 1){
	//					C.add(",");
	//				}
	//			}
	//			tmp.add(C);
	//		}
	//		else if(extlist.size() > 1){//function?
	//			ExtList F = new ExtList();
	//			for(int i = 0; i < extlist.size(); i++){
	//				if(extlist.get(i) instanceof ExtList){
	//					F.add(reverse((ExtList)extlist.get(i)));
	//				}else{
	//					ExtList temp1 = new ExtList();
	//					temp1.add(extlist.get(i));
	//					F.add(reverse(temp1));
	//				}
	//				if(i != extlist.size() - 1){
	//					F.add(",");
	//				}
	//			}
	//			tmp.add(F);
	//		}
	//		else if( extlist.get(0) instanceof Integer ){
	//			ExtList A = new ExtList();
	//			A.add(attp.get(extlist.get(0)).toString());
	//			tmp.add("operand");
	//			tmp.add(A);
	//		}else if( extlist.get(0) instanceof String ){
	//			ExtList S = new ExtList();
	//			S.add( "\"" + extlist.get(0) + "\"" );
	//			tmp.add("operand");
	//			tmp.add(S);
	//		}
	//
	//		return tmp;
	//	}

	public Hashtable get_attp() {
		return this.attp;
	}

	public void generateCode(Start_Parse parser, ExtList data_info) {
		ITFE tfe_info = parser.get_TFEschema();
//		Log.info("2-2-3-1-1");
		//	ɬ�פʤ饳���ȥ����ȳ�����Manager������ѹ�
		//	manager.preProcess(tab,le,le1,le2,le3);
		//	manager.createSchema(tab,le,le1,le2,le3);
		// ?�ֳ��� Grouper�ΤȤ���data_info��Ĵ����?
		if (tfe_info instanceof Grouper && data_info.size() != 0) {
			data_info = (ExtList) data_info.get(0);
		}
//		Log.info("2-2-3-1-2");

		manager.generateCode(tfe_info, data_info);
//		Log.info("2-2-3-1-3");

		manager.finish();


	};
	public StringBuffer generateCode2(Start_Parse parser, ExtList data_info) {
		ITFE tfe_info = parser.get_TFEschema();

		//	ɬ�פʤ饳���ȥ����ȳ�����Manager������ѹ�
		//	manager.preProcess(tab,le,le1,le2,le3);
		//	manager.createSchema(tab,le,le1,le2,le3);

		Log.out("===============================");
		Log.out("     generateCode2 is start     ");
		Log.out("===============================");


		// ?�ֳ��� Grouper�ΤȤ���data_info��Ĵ����?
		if (tfe_info instanceof Grouper && data_info.size() != 0) {
			data_info = (ExtList) data_info.get(0);
		}
		Log.out("data_info.size " + data_info.size());

		if(data_info.size() == 0)
			return manager.generateCodeNotuple(tfe_info);
		else
			return manager.generateCode2(tfe_info, data_info);

	};

	public StringBuffer generateCode3(Start_Parse parser, ExtList data_info) {
		ITFE tfe_info = parser.get_TFEschema();

		//	ɬ�פʤ饳���ȥ����ȳ�����Manager������ѹ�
		//	manager.preProcess(tab,le,le1,le2,le3);
		//	manager.createSchema(tab,le,le1,le2,le3);

		Log.out("===============================");
		Log.out("     generateCode3 is start     ");
		Log.out("===============================");

		// ?�ֳ��� Grouper�ΤȤ���data_info��Ĵ����?
		if (tfe_info instanceof Grouper && data_info.size() != 0) {
			data_info = (ExtList) data_info.get(0);
		}
		Log.out("data_info.size " + data_info.size());

		if(data_info.size() == 0)
			return manager.generateCodeNotuple(tfe_info);
		else
			return manager.generateCode3(tfe_info, data_info);

	};

	public StringBuffer generateCode4(Start_Parse parser, ExtList data_info) {
		ITFE tfe_info = parser.get_TFEschema();

		//	ɬ�פʤ饳���ȥ����ȳ�����Manager������ѹ�
		//	manager.preProcess(tab,le,le1,le2,le3);
		//	manager.createSchema(tab,le,le1,le2,le3);

		Log.out("===============================");
		Log.out("     generateCode4 is start     ");
		Log.out("===============================");

		// ?�ֳ��� Grouper�ΤȤ���data_info��Ĵ����?
		if (tfe_info instanceof Grouper && data_info.size() != 0) {
			data_info = (ExtList) data_info.get(0);
		}
		Log.out("data_info.size " + data_info.size());

		if(data_info.size() == 0)
			return manager.generateCodeNotuple(tfe_info);
		else
			return manager.generateCode4(tfe_info, data_info);

	};

	public StringBuffer generateCssfile(Start_Parse parser, ExtList data_info) {
		ITFE tfe_info = parser.get_TFEschema();

		//	ɬ�פʤ饳���ȥ����ȳ�����Manager������ѹ�
		//	manager.preProcess(tab,le,le1,le2,le3);
		//	manager.createSchema(tab,le,le1,le2,le3);

		Log.out("==================================");
		Log.out("     generateCssfile is start     ");
		Log.out("==================================");

		// ?�ֳ��� Grouper�ΤȤ���data_info��Ĵ����?
		if (tfe_info instanceof Grouper && data_info.size() != 0) {
			data_info = (ExtList) data_info.get(0);
		}
		Log.out("data_info.size " + data_info.size());

		if(data_info.size() == 0)
			return manager.generateCodeNotuple(tfe_info);
		else
			return manager.generateCssfile(tfe_info, data_info);

	}

	private static TFE makeschematop(ExtList list){
		TFE tfe = null;
		tfe = read_attribute(list);

		return tfe;

	}
	//	public static boolean flag = true;
	private static TFE read_attribute(ExtList tfe_tree){
		String att = new String();
		TFE out_sch = null;
		String decos = new String();
		String iterator = new String();
	
 		
		boolean add_deco = false;
		//		if(flag){
		//			tfe_tree.add(tfe_tree.size(), "}}");
		//			Log.info("tfe:"+tfe_tree);
		//			flag = !flag;
		//		}
		//		Log.info("tfe_tree"+tfe_tree);
		Asc_Desc ascDesc = new Asc_Desc();
//		Log.info("ExtList:"+tfe_tree.getExtList(new int[]{1, 0}));
//		Log.info("String:"+tfe_tree.getExtListString(new int[] {1, 0, 0}));
//		Log.info("tfe_tree:"+tfe_tree);

		if(tfe_tree.get(0).toString().equals("operand")){
			if (tfe_tree.getExtListString(tfe_tree.size() - 1) instanceof String) {
				if(tfe_tree.getExtListString(tfe_tree.size() - 1).equals("ggplot_att")) {
					add_deco = true;
					if(decos.isEmpty()){
						decos = "ggplot";
					}else{
						decos = decos + ",ggplot";
					}
				}
			}

			if( ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size()-1) instanceof String  && !tfe_tree.contains("true")
					&& (decos = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size()-1).toString().trim()).startsWith("@")
					){
				ExtList new_out = checkDecoration(tfe_tree, decos);
				//					Log.info(new_out);
				out_sch = read_attribute(new_out);
				System.out.println("decolator:"+decos);
			}

			else if( ((ExtList)tfe_tree.get(1)).get(0) instanceof String ){
				if(((ExtList)tfe_tree.get(1)).get(0).toString().equals("{")){
					((ExtList)tfe_tree.get(1)).remove(0);
					((ExtList)tfe_tree.get(1)).remove(((ExtList)tfe_tree.get(1)).indexOf("}"));
					out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
				}else{
					att =  ((ExtList)tfe_tree.get(1)).get(0).toString();
					Attribute Att = makeAttribute(att);
					out_sch = Att;
				}
			}
			else{
				Log.out("function in read_att: " + ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString());
				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sorting")){
					
					if(decos.isEmpty()){	
						decos = ((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(1).toString();
					}
					add_deco = true;
					((ExtList)tfe_tree.get(1)).remove(0);
					
				}
				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("aggregate") ){

					if(decos.isEmpty()){
						decos = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
					}else{
						decos = decos + "," + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
					}
					add_deco = true;
					ExtList att1 = new ExtList();
					String dec_tmp = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();

					//					if( ((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(2)).get(0).toString().equals("table_alias") ){
					//						att1.add((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(2));
					//						att1.add(((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(3));
					//						att1.add((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(4));
					//					}else{
					att1.add((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(2));
					//					}
					tfe_tree.remove(1);
//					Log.info(tfe_tree);
					int i = tfe_tree.indexOf("true");
					if(i > 0){
						tfe_tree.remove(i);
					}

					tfe_tree.add(att1);
					if(dec_tmp.startsWith("@{")){
						tfe_tree.add(tfe_tree.size(), "true");
						((ExtList)tfe_tree.get(1)).add(((ExtList)tfe_tree.get(1)).size(), dec_tmp);
					}
//										Log.info(tfe_tree);


				}

				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("join_string") ){
					String operand = getText((ExtList)((ExtList)tfe_tree.get(1)).get(0), Start_Parse.ruleNames);
					builder = new String();
					Attribute Att = makeAttribute(operand);
					out_sch = Att;
				}
				else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("attribute") ){
					//					att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
					//					att = att + ((ExtList)tfe_tree.get(1)).get(1).toString();
					//					if( ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(2)).get(1)).get(0)).get(1)).get(0) instanceof ExtList){
					//						att = att + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(2)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0);
					//					}else{
					//						att = att + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(2)).get(1)).get(0)).get(1)).get(0);
					//					}

//										Log.info(tfe_tree);
				}
				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("ggplot") ){
//					if(decos.isEmpty()){
//						decos = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
//					}else{
//						decos = decos + "," + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
//					}
//					add_deco = true;
					ExtList att1 = new ExtList();
					ExtList att2 = new ExtList();
					ExtList tfe_tree_buf = new ExtList();
					String dec_tmp = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();
					String gg_decos;
					boolean sort_flag = false;

					Log.out("tfe_tree in ggplot: " + tfe_tree);
					att1.add("operand");
					att1.add(new ExtList());
					//added by li 20210615 for sorting

					if(tfe_tree.getExtList(1, 0, 1, 2).get(0).equals("sorting")) {
						sort_flag = true;
						att1.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 3));
						//att1.add(tfe_tree.getExtList(1, 0, 1, 2));
					}else {
						att1.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 2));
					}
					
					//att1.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 2));
					att1.add("ggplot_att");
					att2.add("operand");
					att2.add(new ExtList());
					//added by li 20210615 for sorting
					if(!sort_flag&&!tfe_tree.getExtList(1, 0, 1, 4).get(0).equals("sorting")) {
						att2.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 4));
					}else if(!sort_flag&&tfe_tree.getExtList(1, 0, 1, 4).get(0).equals("sorting")) {
						//sorting found
						att2.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 5));
						sort_flag = true;
						//att2.add(tfe_tree.getExtList(1, 0, 1, 4));
					}else if(sort_flag&&tfe_tree.getExtList(1, 0, 1, 4).get(0).equals("sorting")){
						Log.err("Double sort mark in ggplot!!");
					}
					
					
					att2.add("ggplot_att");
					Log.out("In ggplot att1: " + att1);
					Log.out("att2: " + att2);
					//
//					}


					tfe_tree_buf.add("h_exp");
					tfe_tree_buf.add(new ExtList());
					tfe_tree_buf.getExtList(1).add(att1);
					tfe_tree_buf.getExtList(1).add(tfe_tree.getExtListString(1, 0, 1, 3));
					tfe_tree_buf.getExtList(1).add(att2);
					
//					tfe_tree.clear();

//					tfe_tree = tfe_tree_buf;

					out_sch = read_attribute(tfe_tree_buf);

					//					Log.info(tfe_tree);
					try {
						gg_decos = tfe_tree.getExtListString(1, 1).substring(2, tfe_tree.getExtListString(1, 1).length() - 1);
						Preprocessor.putGGplotDeco(splitComma(gg_decos));
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e);
					}
				}

				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("attribute") ){
					att = getText((ExtList)((ExtList)tfe_tree.get(1)).get(0), Start_Parse.ruleNames);
					builder = new String();
					Attribute Att = makeAttribute(att);
					out_sch = Att;
				}else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("grouper") ){
					out_sch = grouper((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));
					//added by goto 20161113  for Compiler:[ ] -> [ ]@{dynamic}
					Compiler.addDynamicModifier(tfe_tree);
				}else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("composite_iterator") ){
					ExtList group = composite( (ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1) );
					add_deco = true;
					decos = (String) group.get(group.size() - 1);
					group.remove(group.size() - 1);
					out_sch = grouper(group);
				}
				else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("function") ){
					String func_name;
					ExtList fn = new ExtList();
					fn = (ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1);
					func_name = ((ExtList)((ExtList)((ExtList)((ExtList)fn.get(0)).get(1)).get(0)).get(1)).get(0).toString();
					if(func_name.equals("cross_tab")){
						GlobalEnv env = new GlobalEnv();
						env.setCtabflag();
						if(tfe_tree.getExtList(1).size() > 1){
							String tmp_dec = tfe_tree.getExtListString(1, 1);
							ArrayList<String> decs = splitComma(tmp_dec);
							for (int i = 0; i < decs.size(); i++) {
								if(decs.get(i).contains("null_value")){
									String tmp_null = decs.get(i).split("=")[1].trim();
									if(tmp_null.charAt(0) == '"'){
										GlobalEnv.nullValue = tmp_null.substring(tmp_null.indexOf("\"") + 1, tmp_null.lastIndexOf("\""));
									}else{
										GlobalEnv.nullValue = tmp_null.substring(tmp_null.indexOf("'") + 1, tmp_null.lastIndexOf("'"));
									}
								}else if (decs.get(i).contains("side_width")){
									GlobalEnv.sideWidth = Integer.parseInt(decs.get(i).split("=")[1].trim());
								}
							}
						}
						Ctab ctab = new Ctab();
						GlobalEnv.setMultiQuery();
						ExtList result = ctab.makeCtab(fn);
						out_sch = read_attribute(result);
					}else {
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));
						//out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).fnc;
					}
				}else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sqlprocess") ) {
					ExtList base = ((ExtList) ((ExtList) tfe_tree.get(1)).get(0)); 
					int p_size = ((ExtList)base.get(1)).size();
					String sql = "&";
					
					Log.out("Is a sql process!");
					 
					
					Log.out("base:" + base.get(1));
					Log.out("p_size:" + p_size);
					for(int i = 1; i < p_size ; i++) {
						sql += ((ExtList)base.get(1)).get(i);
						Log.out("sql: " + sql);
					}
					Attribute func = makeAttribute(sql);
                 	out_sch = func;
                 	
                 	//for(int i = 0; i < argArith.unnest().size(); i++) {
                 		
                 		
                 	//}
				}
				else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sqlfunc") ){
					int sqlfunc_arg_size = 0;
					
                    sqlfunc_flag++;
//                    System.out.println("====sqlfunc eval start====");
                    ExtList base = ((ExtList) ((ExtList) tfe_tree.get(1)).get(0)); 
//                    System.out.println("image: " + getText((ExtList) tfe_tree.get(1), Start_Parse.ruleNames));
                    builder = "";
//                    System.out.println("base: " + base);
                    ArrayList<Integer> isSQLFuncIdx = new ArrayList<>();
                    try {
                    	//add by li to use concat in sqlfunc
                    	
                        if(base.getExtList(1,3,1,0,1,0,1,0,1,0,1,0,1,0).get(0).toString().equals(("concat_exp"))) {
                        	//concat確認
                        	if(GlobalEnv.getdbms().equals("sqlserver")){
    	                    	Log.out("SQLFunc has a concat in first arg!");
    	                    	
    	                       	String sqlfunc = getText(base, Start_Parse.ruleNames);
    	                      	String new_arg = null;
    	                        new_arg = sqlfunc.replace("||", "+");
    	                        //sqlserverの場合"||"を"+"に置換
    	                        Log.out("new_arg: " + new_arg);
    	                        Attribute func = makeAttribute(new_arg);
                            	out_sch = func;
    	                        useTablesInSQLFunc.addAll(getUsedtablesInSQLFunc(base));
    		                    Log.out("useTablesInSQLFunc: " + useTablesInSQLFunc);
    		                    if (inner_sqlfunc_count == 0) useTablesInSQLFunc = new HashSet<>();
    		                    sqlfunc_flag--;
                        	}
                        //
                        }
					} catch (Exception e) {
						// TODO: handle exception
                        for (int i = 3; i < base.getExtList(1).size(); i += 2) {
                            ExtList arg = base.getExtList(1, i);
//                            System.out.println("arg: " + arg);
                            Log.out("arg of sqlfunc:" + arg);
                            try {
                            	Log.out("arg.getExtListString(1, 0, 0):" + arg.getExtListString(1, 0, 0));
                            	 if (arg.getExtListString(1, 0, 0).equals("sqlfunc")) {
                                     isSQLFuncIdx.add(i);
                                 }
                            	 sqlfunc_arg_size++;
    						} catch (Exception e1) {
    							// TODO: handle exception
    							Log.out("SQLFunc arg size is 0");
    						}
                           
                        }
                       Log.out("SQLFunc arg size: " + sqlfunc_arg_size);
                       
                        if (isSQLFuncIdx.size() > 0) {
                            // sql関数の入れ子になっていたらその下も確認しないとダメ
                            for (int idx : isSQLFuncIdx) {
                                ExtList arg = base.getExtList(1, idx);
                                inner_sqlfunc_count++;
                                read_attribute(arg);
                                inner_sqlfunc_count--;
                            }
                        }
                        
                        if(sqlfunc_arg_size==0) {
                        //引数が0個の時の処理
                        //added by li 20210615
                        	//Log.out("FuncIdx.size() == 0");
                        	String sqlfunc = getText(base, Start_Parse.ruleNames);
                        	Attribute func = makeAttribute(sqlfunc);
                        	out_sch = func;
                        }else {
    	                    // useTablesInSQLFuncに利用するテーブルを入れておく
    	                    useTablesInSQLFunc.addAll(getUsedtablesInSQLFunc(base));
    	                    Log.out("useTablesInSQLFunc: " + useTablesInSQLFunc);
    	                    if (inner_sqlfunc_count == 0) {
    	                        // 入子の大元のSQLFuncだったらAttributeに登録しuseTablesだけ更新しておく
    	//                        System.out.println("usedTablesInSQLFunc: " + useTablesInSQLFunc);
    	                        String sqlfunc = getText(base, Start_Parse.ruleNames);
    	                        builder = "";
    	                        Attribute func = makeAttribute(sqlfunc);
    	//                        System.out.println("out_sch: " + func);
    	                        out_sch = func;
    	                    }
    	                    
    	                    if (inner_sqlfunc_count == 0) useTablesInSQLFunc = new HashSet<>();
    	                    sqlfunc_flag--;
                       
                        }
    
                        //
					}
				}
				else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("if_then_else") ){
					out_sch = IfCondition.IfCondition((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));
				}
				else if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sl")){
					att = ((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0).toString();
					Attribute SL = makeAttribute(att);
					out_sch = SL;
				}
				else if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("arithmetics")){
					att = getText( (ExtList)((ExtList)tfe_tree.get(1)).get(0), Start_Parse.ruleNames);
					builder = new String();
					Attribute arithmetics = makeAttribute(att);
					out_sch = arithmetics;
				}
				else{

				}
			}
			if( !(((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ) instanceof ExtList) ){
				String deco = ((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ).toString();
				if(deco.contains("@{")){
					//changed by goto 20161205
					ascDesc.add_asc_desc_Array(deco);

					if(add_deco){
						deco = deco.substring(0, deco.lastIndexOf("}")) + "," + decos + "}";
						setDecoration(out_sch, deco);
					}else{
						limitFlag = false;
						setDecoration(out_sch, deco);
						if(limitFlag){
							GlobalEnv.limit.get(GlobalEnv.limit.size() - 1).findGrouper(tfe_tree.get(1).toString());
						}
						limitFlag = false;
					}
				}
//				System.out.println("tfe_tree_deco2:::"+tfe_tree);
			}else if(add_deco){
				String deco = "@{" + decos + "}";
				Log.out("out_sch: " + out_sch);
				setDecoration(out_sch, deco);
			}
		}else if(tfe_tree.get(0).toString().equals("Decoration")){
			if( ((ExtList)tfe_tree.get(1)).size() == 1 )
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			else if( ((ExtList)tfe_tree.get(1)).size() == 0 ){
				((ExtList)tfe_tree.get(1)).add("\"\"");
				//				Log.info(tfe_tree);
				Attribute WS = makeAttribute(((ExtList)tfe_tree.get(1)).get(0).toString());
				out_sch = WS;
			}else{
				out_sch = decoration((ExtList)tfe_tree.get(1), 1);
			}
		}
		//tbt add 180806
		else if(tfe_tree.get(0).toString().equals("concat_exp")){
			out_sch = connector_main((ExtList)tfe_tree.get(1), -1);
		}
		//tbt end
		else if(tfe_tree.get(0).toString().equals("n_exp")){
			out_sch = connector_main((ExtList)tfe_tree.get(1), 0);
		}else if(tfe_tree.get(0).toString().equals("h_exp")){
			if( ((ExtList)tfe_tree.get(1)).size() == 1 ){
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			}else if( ((ExtList)tfe_tree.get(1)).size() == 0 ){
				((ExtList)tfe_tree.get(1)).add("\"\"");
				//				Log.info(tfe_tree);
				Attribute WS = makeAttribute(((ExtList)tfe_tree.get(1)).get(0).toString());
				out_sch = WS;
			}else {
				out_sch = connector_main((ExtList)tfe_tree.get(1), 1);
			}
		}else if(tfe_tree.get(0).toString().equals("v_exp")){
			if( ((ExtList)tfe_tree.get(1)).size() == 1 ){
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			}else{
				out_sch = connector_main((ExtList)tfe_tree.get(1), 2);
			}
		}else if(tfe_tree.get(0).toString().equals("d_exp")){
			if( ((ExtList)tfe_tree.get(1)).size() == 1 ){
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			}else{
				out_sch = connector_main((ExtList)tfe_tree.get(1), 3);
			}
		}else if(tfe_tree.get(0).toString().equals("expr")) {
			int idx = ((ExtList) tfe_tree.get(1)).indexOf("=");
			out_sch = read_attribute((ExtList) ((ExtList) tfe_tree.get(1)).get(idx + 1));
		}
		else{
			out_sch = makeschematop((ExtList)((ExtList)tfe_tree.get(1)).get(0));
		}

		return out_sch;
	}

	private static String join_operand(ExtList extList, int idx) {
		String operand = null;

		if( ((ExtList)extList.get(0)).get(0).toString().equals("table_alias") ){
			operand = ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0).toString();
			operand = operand + extList.get(1).toString();
			if( ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)extList).get(2)).get(1)).get(0)).get(1)).get(0) instanceof ExtList){
				operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)extList).get(2)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0);
			}else{
				operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(2)).get(1)).get(0)).get(1)).get(0);
			}
			//			operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(2)).get(1)).get(0)).get(1)).get(0).toString();
		}else if( ((ExtList)extList.get(0)).get(0).toString().equals("column_name") ){
			if(  ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0) instanceof ExtList){
				operand= ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
			}else{
				operand = ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0).toString();
			}
			//			operand = ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0).toString();
		}else if( ((ExtList)extList.get(0)).get(0).toString().equals("sl") ){
			operand = ((ExtList)((ExtList)extList.get(0)).get(1)).get(0).toString();
		}
		else if( ((ExtList)extList.get(0)).get(0).toString().equals("sqlfunc") ){
			//			Log.info(extList);
			operand = getText( (ExtList)extList.get(0), Start_Parse.ruleNames );
			builder = new String();
			operand = operand.replaceAll("\"", "'");
		}
		else if( ((ExtList)extList.get(0)).get(0).toString().equals("arithmetics") ){
			operand = getText( (ExtList)((ExtList)extList.get(0)).get(1), Start_Parse.ruleNames);
		}

		if(idx > -1){
			operand = operand + extList.get(idx).toString();

			extList = (ExtList)((ExtList)extList.get(idx + 1)).get(1);
			//			Log.info(extList);

			String a = join_operand(extList, extList.indexOf("||"));
			//			operand = operand + join_operand(extList, extList.indexOf("||"));
			operand = operand + a;
		}
		//		if( ((ExtList)extList.get(0)).get(0).toString().equals("table_alias") ){
		//			operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0).toString();
		//			operand = operand + extList.get(1).toString();
		//			operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(2)).get(1)).get(0)).get(1)).get(0).toString();
		//		}else if( ((ExtList)extList.get(0)).get(0).toString().equals("column_name") ){
		//			operand = operand + ((ExtList)((ExtList)((ExtList)((ExtList)extList.get(0)).get(1)).get(0)).get(1)).get(0).toString();
		//		}else if( ((ExtList)extList.get(0)).get(0).toString().equals("sl") ){
		//			operand = operand + ((ExtList)((ExtList)extList.get(0)).get(1)).get(0).toString();
		//		}
		return operand;
	}

	private static Decorator decoration(ExtList operand, int dim) {
		ExtList atts = new ExtList();
		for(int i = 0; i <= operand.size(); i++){
			//			Log.info(operand.get(i));
			TFE att = read_attribute((ExtList)operand.get(i));
			atts.add(att);
			i++;
		}
		Decorator deco = createdecorator(1);

		for (int i = 0; i < atts.size(); i++) {
			deco.setTFE((ITFE) (atts.get(i)));
		}
		return deco;
	}

	private static Connector connector_main(ExtList operand, int dim){
		ExtList atts = new ExtList();

		for(int i = 0; i <= operand.size(); i++){
//			System.out.println("operand.get(i): " + operand.get(i));
//			System.out.println("operand.get(i) class: " + operand.get(i).getClass());
			if(operand.get(i).equals(",")) {
//				System.out.println("operand.get(i+1): " + operand.get(i+1));
//				System.out.println("operand.get(i+1) class: " + operand.get(i+1).getClass());
			}
			TFE att = read_attribute((ExtList)operand.get(i));
			atts.add(att);
			i++;
		}
		//		decocheck =false;
//		System.out.println("atts:::"+atts);
		Connector con = createconnector(dim);

		for (int i = 0; i < atts.size(); i++) {
			Log.out("!!In codegenerator atts.get(" + i +"): " +atts.get(i));
			con.setTFE((ITFE) (atts.get(i)));
		}
		return con;

	}

	private static Grouper grouper(ExtList operand){
		String iterator = new String();
		int dim = 0;
		TFE operand1 = read_attribute((ExtList)operand.get(1));

		if(operand.get(operand.size() - 1).toString().equals("◯") || operand.get(operand.size() - 1).toString().equals("@")){
			dim = 5;
		}else if (operand.get(operand.size() - 1).toString().equals("#")){
			dim = 4;
		}else if(operand.get(operand.size() - 1).toString().equals("%")){
			dim = 3;
		}else if(operand.get(operand.size() - 1).toString().equals("!")){
			dim = 2;
		}else if(operand.get(operand.size() - 1).toString().equals(",")){
			dim = 1;
		}

		Grouper grp = creategrouper(dim);
		grp.setTFE(operand1);

		return grp;
	}

    static ArrayList<String> getUsedtablesInSQLFunc(ExtList base) {
//        System.out.println("====Enter getUsedTablesInSQLFunc====");
//        System.out.println("base: " + base);
        HashSet<String> usedTables = new HashSet<>();
        // 引数を順に洗っていく
        for (int i = 3; i < base.getExtList(1).size(); i += 2) {
            ExtList arg = base.getExtList(1, i);
//            System.out.println("arg: " + arg);
                       // attributeの場合
            if (arg.getExtListString(1, 0, 0).equals("attribute")) {
                // table_aliasがある場合
                if (arg.getExtListString(1, 0, 1, 0, 0).equals("table_alias")) {
//                    System.out.println("table_aliasを発見 -> " + getText(arg.getExtList(1, 0, 1, 0), Start_Parse.ruleNames));
                    builder = "";
                    usedTables.add(getText(arg.getExtList(1, 0, 1, 0), Start_Parse.ruleNames));
                    builder = "";
                } else {
                    // column_nameのみの場合は探す
                    String column_name = getText(arg.getExtList(1, 0, 1, 0), Start_Parse.ruleNames);
                    builder = "";
                    ArrayList<String> correTableList = new ArrayList();
                    for (Map.Entry<String, ExtList> ent : GlobalEnv.tableAtts.entrySet()) {
                        String tableName = ent.getKey();
                        ExtList attributes = ent.getValue();
                        if (attributes.contains(column_name)) {
                            for (FromTable fromTable : From.getFromItems()) {
                                if (fromTable.getTableName().equals(tableName)) {
                                    correTableList.add(fromTable.getAlias());
                                }
                            }
                        }
                    }
                    if (correTableList.size() == 1) {
//                        System.out.println("該当するテーブルを発見しました -> " + correTableList.get(0));
                        usedTables.add(correTableList.get(0));
                    }
                }
            }
            // arithmeticsの場合
            if (arg.getExtListString(1, 0, 0).equals("arithmetics")) {
//                System.out.println("算術演算子を発見");
                ExtList baseAriths = arg.getExtList(1, 0, 1);
                //Log.out("baseAriths.size(): " + baseAriths.size());
                for (int j = 0; j < baseAriths.size(); j += 2) {
//                    System.out.println("used:: " + usedTables);
                    ExtList argArith = baseAriths.getExtList(j);
                    System.out.println("argArith: " + argArith);
                    Log.out("items: " + argArith.unnest());
                    Log.out("argArith get(1,0,1,0,0)" + argArith.getExtListString(1, 0, 1, 0, 0));
                    // 整数の場合
                    if (argArith.getExtList(1).size() == 1 && !(argArith.getExtList(1).get(0) instanceof ExtList)) {
                        continue;
                    }
                    if (
                            argArith.getExtList(1, 0).size() == 2 &&
                                    argArith.getExtListString(1, 0, 0).equals("arith") &&
                                    !(argArith.getExtList(1, 0, 1).get(0) instanceof ExtList)
                    ) {
                        continue;
                    }
                    // attributeの場合
                   
                    if (argArith.getExtListString(1, 0, 1, 0, 0).equals("attribute")) {
                        // table_aliasの場合
                        //Log.out("In getUsedtablesInSQLFunc attribute");
                    	//Log.out("In attribute argArith get(1,0,1,0,1,0,0)" + argArith.getExtListString(1, 0, 1, 0, 1, 0, 0));
                        if (argArith.getExtListString(1, 0, 1, 0, 1, 0, 0).equals("table_alias")) {
//                            System.out.println("table_aliasを発見 -> " + getText(argArith.getExtList(1, 0, 1, 0, 1, 0), Start_Parse.ruleNames));
                            builder = "";
                            Log.out("argArith get(1,0,1,0,1,0): "  + argArith.getExtList(1, 0, 1, 0, 1, 0));
                            usedTables.add(getText(argArith.getExtList(1, 0, 1, 0, 1, 0), Start_Parse.ruleNames));
                            builder = "";
                        } else {
                            // column_nameのみの場合は探す
                            String column_name = getText(argArith.getExtList(1, 0, 1, 0, 1, 0), Start_Parse.ruleNames);
                            builder = "";
                            ArrayList<String> correTableList = new ArrayList();
                            for (Map.Entry<String, ExtList> ent : GlobalEnv.tableAtts.entrySet()) {
                                String tableName = ent.getKey();
                                ExtList attributes = ent.getValue();
                                if (attributes.contains(column_name)) {
                                    for (FromTable fromTable : From.getFromItems()) {
                                        if (fromTable.getTableName().equals(tableName)) {
                                            correTableList.add(fromTable.getAlias());
                                        }
                                    }
                                }
                            }
                            if (correTableList.size() == 1) {
//                                System.out.println("該当するテーブルを発見しました -> " + correTableList.get(0));
                                usedTables.add(correTableList.get(0));
                            }
                        }
                     //Added by li 20210613 to fix the arith in sqlfunc
                    }else {
                    	
                    	try {
							for(i = 0; i < argArith.unnest().size(); i++) {
								ExtList tmp_x = new ExtList();
		                    	ExtList tmp_any_name = new ExtList();
		                    	ExtList tmp_any_name2 = new ExtList();
		                    	ExtList tmp_table_alias = new ExtList();
								if(argArith.unnest().get(i).toString().equals("table_alias")) {
									//Log.out("argArith.unnest():" + argArith.unnest().get(i) + ", index: " + i);
									tmp_x.add(argArith.unnest().get(i+2));
									tmp_any_name.add(argArith.unnest().get(i+1));
									tmp_any_name.add(tmp_x);
									//Log.out("tmp_any_name: " + tmp_any_name);
									tmp_any_name2.add(tmp_any_name);
									//Log.out("tmp_any_name2: " + tmp_any_name2);
									Log.out("table alias: " + tmp_x);
									tmp_table_alias.add(argArith.unnest().get(i));
									tmp_table_alias.add(tmp_any_name2);
									//Log.out("tmp_table_alias: " + tmp_table_alias.getExtList(1,0,1).get(0));
									try {
										if(usedTables.contains(tmp_table_alias.getExtList(1,0,1).get(0))) {
											//同じテーブルを除去
											Log.out("The same table!");
										}else {
											builder = "";
				                        	usedTables.add(getText(tmp_table_alias, Start_Parse.ruleNames));
				                        	Log.out("use Table: " +usedTables);
				                            builder = "";
										}
									} catch (Exception e) {
										// TODO: handle exception
										builder = "";
			                        	usedTables.add(getText(tmp_table_alias, Start_Parse.ruleNames));
			                        	Log.out("use Table: " +usedTables);
			                            builder = "";
									}
									
									
		                           //
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							Log.err("Exception in sqlfunc arithmetics");
						}
                    }
                    //
                }
            }
            //add by li to use concat in sqlfunc 20210617*
            /*
            try {
            	  if(base.getExtList(1,3,1,0,1,0,1,0,1,0,1,0,1,0).get(0).toString().equals(("concat_exp"))) {
               	   ExtList tmp_table_alias = new ExtList();
               	   //tmp_table_alias.add(argArith.unnest().get(i));
               	   Log.out("concat get: " + base.getExtList(1,3,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1));
               	   builder = "";
               	   tmp_table_alias.add(base.getExtList(1,3,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1));
                  	   usedTables.add(getText(tmp_table_alias, Start_Parse.ruleNames));
                      builder = "";
                  }
			} catch (Exception e) {
				// TODO: handle exception
			}
            */
           //
        }
        return new ArrayList<>(usedTables);
    }

	private static ExtList composite(ExtList operand){
		int index = operand.indexOf("]");
		String deco = "";
		ArrayList iterators = new ArrayList();

		for(int i = 1; i+index < operand.size(); i++ ){
			iterators.add(operand.get(i+index));
			if(i != 1){
				operand.remove(i+index);
				i--;
			}
		}

		//Merged by li 20210526
				while(!iterators.isEmpty()){
					switch(iterators.get(0).toString()){
					case ",":
						iterators.remove(0);
						if (iterators.isEmpty()) {
							deco = deco + ", vr_x=0";
						} else {
							deco += (deco.equals("")? "column=":", column=") + iterators.get(0);
							deco = deco + ", vr_x=" + iterators.get(0);
							iterators.remove(0);
						}
						break;
						
					case "!":
						iterators.remove(0);
						if (iterators.isEmpty()) {
							deco = deco + ", vr_y=0";
						} else {
							deco += (deco.equals("")? "row=":", row=") + iterators.get(0);
							deco = deco + ", vr_y=" + iterators.get(0);
							iterators.remove(0);
						}
						break;
					
					case "%":
						iterators.remove(0);
						if (iterators.isEmpty()) {
							deco = deco + ", vr_z=0";
						} else {
							deco += (deco.equals("")? "row=":", row=") + iterators.get(0);
							deco = deco + ", vr_z=" + iterators.get(0);
							iterators.remove(0);
						}
						break;
						
					case "#":
						iterators.remove(0);
						if (iterators.isEmpty()) {
							deco = deco + ", vr_t=0";
						} else {
							deco += (deco.equals("")? "vr_t=":", vr_t=") + iterators.get(0);
							iterators.remove(0);
						}
						break;
						
					case "◯":
					case "@":
						iterators.remove(0);
						if (iterators.isEmpty()) {
							deco = deco + ", vr_c=0";
						} else {
							deco += (deco.equals("")? "vr_c=": ", vr_c=") + iterators.get(0);
							iterators.remove(0);
						}
						break;
					
					}
				}
		
//		try {
//			java.nio.file.Files.write(java.nio.file.Paths.get("/Users/tatsu/Desktop/output.txt"), ("added " + deco + "to " + operand + "\n").getBytes(), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		operand.add(deco);
		return operand;

	}

	private static Decorator createdecorator(int dim){
		Decorator decorator = new Decorator();
		if(dim == 1){
			//factory and manager
			decorator = factory.createDecoration(manager);
		}
		decorator.setId(TFEid++);
		return decorator;
	}

	private static Connector createconnector(int dim){
		Connector connector = new Connector();

		if (dim == 4){
			connector = factory.createC4(manager);
		}else if(dim == 3){
			//factory and manager
			connector = factory.createC3(manager);
		}else if(dim == 2){
			//factory and manager
			connector = factory.createC2(manager);
		}else if(dim == 1){
			//factory and manager
			connector = factory.createC1(manager);
		}else if(dim == 0){
			//factory and manager
			connector = factory.createC0(manager);
		}else if(dim == -1){
			//tbt add 180806
			connector = factory.createConcat(manager);
			//tbt end
		}
		connector.setId(TFEid++);
		return connector;
	}

	private static Grouper creategrouper(int dim){
		Grouper grouper = null;

		if (dim == 5){
			grouper = factory.createG5(manager);
		}else if(dim == 4){
			grouper = factory.createG4(manager);
		}else if(dim == 3){
			//factory and manager
			grouper = factory.createG3(manager);
		}else if(dim == 2){
			//factory and manager
			grouper = factory.createG2(manager);
		}else if(dim == 1){
			//factory and manager
			grouper = factory.createG1(manager);
		}
		grouper.setId(TFEid++);
		return grouper;
	}

	private static Function createFunction() {
		Function function = factory.createFunction(manager);
		function.setId(TFEid++);
		return function;
	}

	private static Attribute createAttribute() {
		Attribute attribute = factory.createAttribute(manager);
		attribute.setId(TFEid++);
		return attribute;
	}


	private static Attribute makeAttribute(String token) {
		return makeAttribute(token, false);
	}
	static Attribute makeAttribute(String token, boolean skipCondition) {
		String line;
		String name;
		String key = "";
		int equalidx = token.indexOf('=');
		
		boolean equalSignOutsideDoubleQuote = false;
		boolean equalSignOutsideSingleQuote = false;
		if(token.contains("\"")){
			for(int i=0;i<token.length();i++){
				if(token.charAt(i) == '"'){
					break;
				}else if(token.charAt(i)=='='){
					equalSignOutsideDoubleQuote = true;
					break;
				}
			}
		}else equalSignOutsideDoubleQuote = true;
		if(token.contains("'")){
			for(int i=0;i<token.length();i++){
				if(token.charAt(i) == '\''){
					break;
				}else if(token.charAt(i)=='='){
					equalSignOutsideSingleQuote = true;
					break;
				}
			}
		}else equalSignOutsideSingleQuote = true;

		//		if (equalidx != -1 && !skipCondition) {
		if (equalidx != -1 && !skipCondition && equalSignOutsideDoubleQuote && equalSignOutsideSingleQuote) {
			// found key = att
			key = token.substring(0, equalidx);
			token = token.substring(equalidx + 1);		//TODO: <= This causes an error.  ex) "x==100"!  -> solved

			// tk to ignore space between = and value/////////////////
			key = key.trim();
			// tk///////////////////

			Log.out("[makeAttiribute] === Attribute Key : " + key + " ===");
		} else {
		}

		int as_string = token.toLowerCase().indexOf(" as ");
		if (as_string != -1) {
			line = (String) (token.substring(0, as_string));
			name = (String) (token.substring(as_string + 4));
		} else {
			line = token;
			name = token;
		}
		// tk to ignore space between = and value/////////////////
		line = line.trim();

		name = name.trim();
		att_tmp = name;
		// tk//////////////////////////////////
		Log.out("[makeAttribute] line : " + line);
		Log.out("[makeAttribute] name : " + name);

		Attribute att = createAttribute();

		attno = att.setItem(attno, name, line, key, attp);

		return att;

	}

	private static Function func_read(ExtList fn) {
		String token;
		Function fnc = createFunction();
		String func_name = new String();
		ExtList atts = new ExtList();
		ExtList func_atts = new ExtList();

		for(int i = 0; i < fn.size(); i++){
			if(i == 0){
				func_name = ((ExtList)((ExtList)((ExtList)((ExtList)fn.get(i)).get(1)).get(0)).get(1)).get(0).toString();
			}else if(fn.get(i) instanceof String){
				if(fn.get(i).toString().equals(",")){
					atts.add(fn.get(i));
				}else{
					continue;
				}
			}else{
				atts.add(fn.get(i));
			}
		}



		func_atts.add("h_exp");
		func_atts.add(atts);
		fnc.setFname( func_name );
		FunctionData fnd = new FunctionData(func_name);

		String name, value;

		Log.out("[func*read start funcname]=" + fn);
		/* func_read */
		TFE read_tfe = read_attribute(func_atts);
		//Log.out("read_tfe " + ((Connector)read_tfe).countconnectitem());
		Log.out("[func*TFE]=" + read_tfe.makele0());
		if (read_tfe instanceof Connector) {
			//		if(read_tfe instanceof Connector && ((Connector) read_tfe).getDimension() == 1){
			for(TFE tfe: ((Connector)read_tfe).tfes){
				//Log.out("break FncArg(tfe): " + ((Connector)read_tfe).tfes);
				fnc.addArg(makeFuncArg(tfe));
				//Log.out("break: get.Arg: " + fnc.getArg(0));
			}
		}
		else
			fnc.addArg(makeFuncArg(read_tfe));
		if (func_name.toLowerCase().equals("select")) {
			fnc.addDeco("select", att_tmp);
		}
		return fnc;

	}

	private static FuncArg makeFuncArg(TFE arg) {
		FuncArg out_fa;
		Log.out("argsaregs: " + arg.getClass().getName());

		VRFunction vrfnc = new VRFunction();

		if (arg instanceof Attribute) {
			out_fa = new FuncArg(((Attribute) arg).getKey(), arg);
		}else if(arg instanceof HTMLCONCAT) {
			out_fa = new FuncArg("default", arg);
			out_fa.setConcatFlag(true);
		}
		else {
			out_fa = new FuncArg("default", arg);
		}

		return out_fa;
	}

	static String exprtostring(ExtList expr){
		String str = null;
		String att = null;
		ExtList tfe_tree = expr.getExtList(0, 1, 0);
		if("operand".equals(tfe_tree.getExtListString(0))){
			ExtList tmp = tfe_tree.getExtList(1, 0, 1);
			if("table_alias".equals(tmp.getExtListString(0, 0))){
				att = tmp.getExtListString(0, 1, 0, 1, 0);
				att = att + tmp.getExtListString(1);
				if(tmp.getExtListString(2, 1, 0, 1, 0) == null){
					att = att + tmp.getExtListString(2, 1, 0, 1, 0, 1, 0);
				}else{
					att = att + tmp.getExtListString(2, 1, 0, 1, 0);
				}
			}else if("column_name".equals(tmp.getExtListString(0, 0))){
				if(tmp.getExtListString(0, 1, 0, 1, 0) == null){
					att = tmp.getExtListString(0, 1, 0, 1, 0, 1, 0);
				}else{
					att = tmp.getExtListString(0, 1, 0, 1, 0);
				}
			}
		}
		str = att + expr.getExtListString(1) + expr.getExtListString(2, 1, 0, 1, 0);
		return str;
	}

	public Attribute createConditionalAttribute(){
		Attribute condAttribute = factory.createConditionalAttribute(manager);
		condAttribute.setId(TFEid++);
		return condAttribute;
	}

	//	private Attribute makeConditionalAttribute(String condition,
	//			String[] attributes) {
	//
	//		Attribute att = cg.createConditionalAttribute();
	//		attno = att.setItem(attno, attributes[0], attributes[0], null, attp);
	//		if (attributes.length == 2) {
	//			attno = att.setItem(attno, attributes[1], attributes[1], null, attp);
	//		}
	//		att.setCondition(condition);
	//		attno = att.setItem(attno, condition, condition,
	//				null, attp);
	//		this.setDecoration(att);
	//
	//		return att;
	//	}

	private static ExtList checkDecoration(ExtList extList, String decos) {
		String token = new String();
		String name, value;
		int equalidx;

		if(decos.contains("{") && decos.contains("}"))
			decos = decos.substring(decos.indexOf("{")+1, decos.lastIndexOf("}"));
		else
			return extList;


		//decos.split(",")
		ArrayList<String> decoList = splitComma(decos);
		ExtList new_list = new ExtList();
		ExtList med = new ExtList();
		extList.add("true");
		med.add(extList);
		for(String d : decoList) {

			name = new String();
			value = new String();

			// read name
			token = d;
			equalidx = token.indexOf('=');
			if (equalidx != -1) {
				// key = idx
				name = token.substring(0, equalidx).trim();
				value = token.substring(equalidx + 1).trim();
				if(value.startsWith("\'") && value.endsWith("\'")){
					continue;
				}else if(value.startsWith("\"") && value.endsWith("\"")){
					continue;
				}else if(isNumber(value)){
					continue;
				}else{//////////////////////////////////////////説明文の文字列連結
					if(!new_list.contains("Decoration"))
						new_list.add("Decoration");
					//value:e.color->[operand, [e.color]]
					ExtList a1 = new ExtList(), a2 = new ExtList();
					a1.add("operand");
					a1.add(a2);
					((ExtList)a1.get(1)).add(value);
					med.add(",");
					med.add(a1);
				}
			}
		}
		new_list.add(med);
		//		decocheck = true;
		if(!new_list.contains("Decoration")){
			return extList;
		}else{
			return new_list;
		}
	}
	private static void setDecoration(ITFE tfe, String decos) {

		if(decos.contains("{") && decos.contains("}"))
			decos = decos.substring(decos.indexOf("{")+1, decos.lastIndexOf("}"));
		else
			return;
		//decos.split(",")
		ArrayList<String> decoList = splitComma(decos);

		String token = new String();
		String name, value;
		int equalidx;
		for(int i = 0; i < decoList.size(); i++) {
			name = new String();
			value = new String();

			// read name
			token = decoList.get(i);
			if (token.toLowerCase().contains("limit")) {
				Log.out("@ limit found @");

				equalidx = token.indexOf('=');
				if (equalidx != -1) {
					// key = idx
					name = token.substring(0, equalidx).trim();
					value = token.substring(equalidx + 1).trim();
					if(value.startsWith("'")){
						value = value.replaceAll("'", "\"");
					}
					Log.out("Value exits.");
					limitFlag = true;
					GlobalEnv.limit.add(new Limiter(attno, Integer.parseInt(value)));

				} else {
					token = token.trim();
					Log.out("Value does not exit.");
				}
			}


			//added by goto 170604 for asc/desc@dynamic
			if (token.toLowerCase().contains("dynamic")) {
				Log.out("@ dynamic found @");
				new Asc_Desc().dynamicTokenProcess();
			}

			else if (token.toLowerCase().contains("stream")) {
				Log.out("@ stream found @");

				equalidx = token.indexOf('=');
				if (equalidx != -1) {
					// key = idx
					name = token.substring(0, equalidx).trim();
					value = token.substring(equalidx + 1).trim();
					if(value.startsWith("'")){
						value = value.replaceAll("'", "\"");
					}
					Log.out("Value exits.");
					new Asc_Desc().streamTokenProcess(value);
				} else {
					token = token.trim();
					Log.out("Value does not exit.");
					new Asc_Desc().streamTokenProcess("1000");
				}
			}

			if (token.toLowerCase().contains("asc") || token.toLowerCase().contains("desc")) {
				Log.out("@ order by found @");
				Log.out("tfe: " + tfe);
				new Asc_Desc().addOrderBy(token, tfe.toString());
				new Preprocessor().setOrderBy();
				tfe.setOrderBy(token);

				/* "aggregate functions" found */
			} else if (token.equalsIgnoreCase("max") ||
					token.equalsIgnoreCase("min") ||
					token.equalsIgnoreCase("avg") ||
					token.equalsIgnoreCase("sum") ||
					token.equalsIgnoreCase("count") /*||
            		   //added by goto 20130122
            		   toks.lookToken().equalsIgnoreCase("slideshow")*/) {

				Log.out("@ aggregate functions found @");

				decos = decos+",count2";
				new Preprocessor().setAggregate();

				tfe.setAggregate(token);

				tfe.addDeco(token.toLowerCase(), "");	//added by goto 170604

			 //added by otawa 20181025
			} else if (token.toLowerCase().contains("ggplot")) {
				Log.out("@ ggplot found @");
				new Preprocessor().setGGplot();
				tfe.setGGplot(token);
				tfe.addDeco(token.toLowerCase(), "");
			} else if(token.contains("ctab")){
				new Preprocessor().setCtab();
				tfe.setCtab(token);
				tfe.addDeco(token, "");
			} else{
				equalidx = token.indexOf('=');
				if (equalidx != -1) {
					// key = idx
					name = token.substring(0, equalidx).trim();
					value = token.substring(equalidx + 1).trim();
					if(value.startsWith("'")){
						value = value.replaceAll("'", "\"");
					}
					decoration_out(tfe, name, value);
				} else {
					// key only
					// 20161113 halken
					token = token.trim();
					decoration_out(tfe, token, "");
				}
			}
		}
		Log.out("@ decoration end @");
		// Log.out(toks.DebugTrace());
	}
	//split(",")
	private static ArrayList<String> splitComma(String decos) {
		if(decos.charAt(decos.length() - 1) == '}'){
			decos = decos.substring(2, decos.length() - 1);
		}
		ArrayList<String> al = new ArrayList<>();
		Boolean sq = false, dq = false;
		int lastIndex = 0;
		char c;
		for(int i=0; i<decos.length(); i++){
			c = decos.charAt(i);
			if(c=='\'' && !dq)		sq = !sq;
			else if(c=='"' && !sq)	dq = !dq;
			else{
				if(!sq && !dq && c==','){
					al.add(decos.substring(lastIndex, i));
					lastIndex = i+1;
				}
			}
		}
		al.add(decos.substring(lastIndex, decos.length()));
		return al;
	}


	private CodeGenerator(int id){
		TFEid = id;
	}
	public CodeGenerator() {
	}

	private static void decoration_out(ITFE tfe, String name, Object value) {

		/* 鐃緒申?的鐃緒申String鐃緒申鐃緒申鐃宿わ申覆鐃�*/
		tfe.addDeco(name, (String) value);
		Log.out("[decoration name=" + name + " value=" + value + "]");

	}

	static String builder = new String();
	public static String getText(ExtList tree, String[] ruleNames){
		if(tree.size() != 1){
			for(int i = 0; i < tree.size(); i++){
				if(tree.get(i) instanceof String){
					if(Arrays.asList(ruleNames).contains(tree.get(i).toString())){
						continue;
					}else{
						if( tree.get(i).toString().equals(".") ){
							builder = builder.trim();
							builder += tree.get(i).toString();
						}
						else if(tree.get(i).toString().equals("&")){
							continue;
						}
						else{
							builder += tree.get(i).toString();
							//builder += " ";
						}
					}
				}else {
					getText((ExtList)tree.get(i), ruleNames);
				}
			}
		}
		else if(tree.size() == 1 && (tree.get(0) instanceof String)){
			String t = tree.get(0).toString();
			if(t.startsWith("\"") && t.endsWith("\"")){
				t = "\"" + t.substring(1, t.length()-1).replaceAll("'", "''") + "\"";
			}
			//builder += t + " ";
			builder += t;
			return builder.toString();
		}
		else if(tree.size() == 1 && ((ExtList)tree.get(0)).size() > 1 ){
			return getText((ExtList)tree.get(0), ruleNames);
		}
		else if(tree.size() == 1 && ((ExtList)tree.get(0)).size() == 1 ){
			return getText((ExtList)tree.get(0), ruleNames);
		}
		return builder.toString();
	}

	public static boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
	public static boolean mediaUnityModule(String media){//module
		for(int i=0; i<GlobalEnv.medialist.size();i++){
			if(GlobalEnv.medialist.get(i).equals(media)){
				filenum = i;
				return true;
			}
		}
		return false;
	}
}
