package supersql.codegenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import supersql.FrontEnd;
import supersql.codegenerator.Compiler.Compiler;
import supersql.codegenerator.Compiler.JSP.JSPFactory;
import supersql.codegenerator.Compiler.PHP.PHP;
import supersql.codegenerator.Compiler.Rails.RailsFactory;
import supersql.codegenerator.HTML.HTMLFactory;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Factory;
import supersql.codegenerator.PDF.PDFFactory;
import supersql.codegenerator.VR.VRAttribute;
import supersql.codegenerator.VR.VRFactory;
import supersql.codegenerator.VR.VRManager;
import supersql.codegenerator.VR.VRcjoinarray;
import supersql.codegenerator.VR.VRfilecreate;
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
import supersql.parser.Preprocessor;
import supersql.parser.Start_Parse;


public class CodeGenerator {

	private static Hashtable attp;

	private static int attno;

	private static String att_tmp;

	private static String media;

	static Factory factory;

	public static boolean sqlfunc_flag = false;

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

	//20210413 yama
	public static int t_cont_count = 0;

	//20210414 yama
	public static int appbar_count = 0;
	public static int t_appbar_count = 0;
	public static int tab_count = 0;
	public static int vtab_count = 0;
	public static int f_vtab_count = 0;
	public static int embed_count = 0;
	public static int gridlist_count = 0;
	public static int button_count = 0;
	public static int textfield_count = 0;
	public static int u_textfield_count = 0;
	public static int text_count = 0;
	public static int card_count = 0;
	public static int grid_count = 0;
	public static int cont_maxnest = 0;
	public static int list_count = 0;
	public static int divider_count = 0;
	public static int avatar_count = 0;

	public static boolean tabflag;
	public static boolean t_contflag;
	//20210906 yama
	public static boolean t_embedflag;
	//20211118 yama
	public static boolean contflag;

	//20210506 yama
	public static ArrayList<Integer> embed_List = new ArrayList<Integer>();
	//20210519 yama
	public static List<List<String>> embedlines = new ArrayList<>();
	//20210706 yama tab内で使われているコンポーネントのリスト ex)textfield, button
	public static List<List<String>> t_cont_List = new ArrayList<>();
	//20211118 yama grid, card内で使われているコンポーネントのリスト ex)list, button
	public static List<List<String>> cont_List = new ArrayList<>();
	//20211118 yama grid
	public static ArrayList<ArrayList<Integer>> grid_Lg_List = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> grid_Md_List = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> grid_Sm_List = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> grid_Xs_List = new ArrayList<>();
	public static ArrayList<ArrayList<String>> grid_Align_List = new ArrayList<>();

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
		//20210412 yama media react_tHTML
		}else if (media.toLowerCase().equals("react")) {
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
		}else if(media.toLowerCase().equals("vr_museum") || media.toLowerCase().equals("unity_museum")){
			VRcjoinarray.gLemaxlist.add(0);
			VRcjoinarray.getJoin();
			VRcjoinarray.getexhJoin();
			VRAttribute.genrearray22.add(0);
			VRManager.vrflag = true;
			factory = new VRFactory();
			VRfilecreate.scene = "museum";//VRfilecreateのためのフラグ
			for(int i=1; i<=VRAttribute.cjoinarray.size()+1; i++){//あとで書き換え
				VRfilecreate.template_scene[i] = "Type_museum";//museum
				VRfilecreate.template_stand[i] = "Type_museum";//stand
				VRfilecreate.room_sizex[i] = 50;
				VRfilecreate.room_sizey[i] = 20;
				VRfilecreate.room_sizez[i] = 30;
				VRfilecreate.stand_sizex[i] = VRfilecreate.stand_sizez[i] = 1.3f;
				VRfilecreate.stand_sizey[i] = 2;
			}
		}else if(mediaUnityModule(media)){//mediaとメディア名いれたarraylistを比較してtrueを返す
			VRcjoinarray.gLemaxlist.add(0);
			VRcjoinarray.getJoin();
			VRcjoinarray.getexhJoin();
			VRAttribute.genrearray22.add(0);
			VRManager.vrflag = true;
			factory = new VRFactory();
			VRManager.VRmoduleflag = true;//この後VRでもしmoduleがあったらみたいな感じで場合分けして、変数代入していく
			//一致したメディアを特定。そのfileconを改行ごとに配列に入れていく
			filesplit = GlobalEnv.multifilecon.get(filenum).split("\n");
			//メディア名のarraylistで2番目が一致したとする。そっからは他の変数のarraylistでも2番目のを持ってきて、それを変数として扱う
			for(int i=1; i<=VRAttribute.cjoinarray.size()+1; i++){//あとで書き換え
				VRfilecreate.template_scene[i] = VRfilecreate.template_stand[i] = VRfilecreate.template_wallstand[i] = "Type_museum";
				VRfilecreate.light_r[i] = VRfilecreate.light_g[i] = VRfilecreate.light_b[i] = "255";
				VRfilecreate.exh_distancex[i] = VRfilecreate.exh_distancey[i] = VRfilecreate.exh_distancez[i] = "5";
				VRfilecreate.room_sizex[i] = 50;
				VRfilecreate.roomx[i] = VRfilecreate.room_sizex[i]/2-5;
				VRfilecreate.room_sizey[i] = 20;
				VRfilecreate.room_sizez[i] = 30;
				VRfilecreate.roomz[i] = VRfilecreate.room_sizez[i]/2-5;
				VRfilecreate.stand_sizex[i] = VRfilecreate.stand_sizez[i] = 1.3f;
				VRfilecreate.stand_sizey[i] = 2;
				//ここから壁
				VRfilecreate.picture_sizex[i] = 2;//2D
				VRfilecreate.wallstand_sizex[i] = VRfilecreate.wallstand_sizey[i] = VRfilecreate.wallstand_sizez[i]= 2;//3D
				VRfilecreate.wallexh_distancex[i] = VRfilecreate.wallexh_distancey[i] = 4;//3Dと2D共通
				VRfilecreate.wallexh_high[i] = 1;
			}
			for(int i=1; i<=VRAttribute.cjoinarray.size()+1; i++){
				for(int j=0; j<filesplit.length;j++){
					String[] str = filesplit[i].split("=");
					if(str[0].trim().equals("template_scene"))
						VRfilecreate.template_scene[i] = str[1].trim();
					if(str[0].trim().equals("template_stand"))
						VRfilecreate.template_stand[i] = str[1].trim();
					if(str[0].trim().equals("LightColor.r"))
						VRfilecreate.light_r[i] = str[1].trim();
					if(str[0].trim().equals("LightColor.g"))
						VRfilecreate.light_g[i] = str[1].trim();
					if(str[0].trim().equals("LightColor.b"))
						VRfilecreate.light_b[i] = str[1].trim();
					if(str[0].trim().equals("picture_size.x"))
						VRfilecreate.picture_sizex[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("picture_distance.y"))
						VRfilecreate.exh_distancex[i] = str[1].trim();
					if(str[0].trim().equals("exhibition_distance.y"))
						VRfilecreate.exh_distancey[i] = str[1].trim();
					if(str[0].trim().equals("exhibition_distance.z"))
						VRfilecreate.exh_distancez[i] = str[1].trim();
					if(str[0].trim().equals("room_size.x")){
						VRfilecreate.room_sizex[i] = Float.valueOf(str[1].trim());
						VRfilecreate.roomx[i] = VRfilecreate.room_sizex[i]/2-5;
					}
					if(str[0].trim().equals("room_size.y"))
						VRfilecreate.room_sizey[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("room_size.z")){
						VRfilecreate.room_sizez[i] = Float.valueOf(str[1].trim());
						VRfilecreate.roomz[i] = VRfilecreate.room_sizez[i]/2-5;
					}
					if(str[0].trim().equals("stand_size.x"))
						VRfilecreate.stand_sizex[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("stand_size.y"))
						VRfilecreate.stand_sizey[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("stand_size.z"))
						VRfilecreate.stand_sizez[i] = Float.valueOf(str[1].trim());
					//ここから壁
					if(str[0].trim().equals("template_wallstand"))
						VRfilecreate.template_wallstand[i] = str[1].trim();
					if(str[0].trim().equals("picture_size.x"))
						VRfilecreate.picture_sizex[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallstand_size.x"))
						VRfilecreate.wallstand_sizex[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallstand_size.y"))
						VRfilecreate.wallstand_sizey[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallstand_size.z"))
						VRfilecreate.wallstand_sizez[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallexh_distance.x"))
						VRfilecreate.wallexh_distancex[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallexh_distance.y"))
						VRfilecreate.wallexh_distancey[i] = Float.valueOf(str[1].trim());
					if(str[0].trim().equals("wallexh_high"))
						VRfilecreate.wallexh_high[i] = Float.valueOf(str[1].trim());
				}
			}
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

		parser.schemaTop = schemaTop;
		parser.sch = sch;
		parser.schema = schema;
	}



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
		//20210419 yama
		System.out.println("aaa ");
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
			//20210422 yama test
			//System.out.println(tfe_tree);
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
				if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sorting") ){
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

					att1.add("operand");
					att1.add(new ExtList());
					att1.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 2));
					att1.add("ggplot_att");
					att2.add("operand");
					att2.add(new ExtList());
					att2.getExtList(1).add(tfe_tree.getExtList(1, 0, 1, 4));
					att2.add("ggplot_att");

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
					//20210506 yama embed
					//}else if (func_name.equals("embed")){
						//out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));
						//拡張子より前のファイル名を抽出 [c1]
						//System.out.println("a " +fn.getExtList(2, 1, 0, 1, 0, 1, 0, 1));

					//20201130 yama appbar
					}else if(func_name.equals("appbar")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						//20210414 yama
						appbar_count++;

						//20210413 yama
						String abstr = fn.getExtList(2, 1, 0, 1).toString();
						String abpath;
						String abcsspath;

						if(appbar_count > 1) {
							Preprocessor.putComponentList("App" + appbar_count);
							abpath = GlobalEnv.getOutputDirPath() + "/../src/app" + appbar_count + ".js";
							abcsspath = GlobalEnv.getOutputDirPath()+"/../src/app" + appbar_count + ".css";
						} else {
							Preprocessor.putComponentList("App");
							abpath = GlobalEnv.getOutputDirPath() + "/../src/app.js";
							abcsspath = GlobalEnv.getOutputDirPath()+"/../src/app.css";
						}

						File file = new File(abpath);
						File cssfile = new File(abcsspath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create app.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import logo from './logo.svg';\n");

				            if(appbar_count > 1) {
				            	fw.write("import './app" + appbar_count + ".css';\n");
				            } else {
				            	fw.write("import './app.css';\n");
				            }

				            fw.write("import Button from '@material-ui/core/Button';\n" +
				            		"import { makeStyles } from '@material-ui/core/styles';\n" +
				            		"import AppBar from '@material-ui/core/AppBar';\n" +
				            		"import Toolbar from '@material-ui/core/Toolbar';\n" +
				            		"import Typography from '@material-ui/core/Typography';\n" +
				            		"import IconButton from '@material-ui/core/IconButton';\n" +
				            		"import MenuIcon from '@material-ui/icons/Menu';\n" +
				            		"import Drawer from '@material-ui/core/Drawer';\n" +
				            		"import MenuItem from '@material-ui/core/MenuItem';\n" +
				            		"import clsx from 'clsx';\n" +
				            		"import List from '@material-ui/core/List';\n" +
				            		"import Divider from '@material-ui/core/Divider';\n" +
				            		"import ListItem from '@material-ui/core/ListItem';\n" +
				            		"import ListItemIcon from '@material-ui/core/ListItemIcon';\n" +
				            		"import ListItemText from '@material-ui/core/ListItemText';\n" +
				            		"import InboxIcon from '@material-ui/icons/MoveToInbox';\n" +
				            		"import MailIcon from '@material-ui/icons/Mail';\n" +
				            		"var appbar = '"+abstr.substring(2, abstr.length() - 2)+"';\n" +
				            		"\n" +
				            		"const useStyles = makeStyles((theme) => ({\n" +
				            		"  root: {\n" +
				            		"    flexGrow: 1,\n" +
				            		"  },\n" +
				            		"  menuButton: {\n" +
				            		"    marginRight: theme.spacing(2),\n" +
				            		"  },\n" +
				            		"  title: {\n" +
				            		"    flexGrow: 1,\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"export default function App() {\n" +
				            		"  const classes = useStyles();\n" +
				            		"\n" +
				            		"  const [state, setState] = React.useState({\n" +
				            		"    top: false,\n" +
				            		"    left: false,\n" +
				            		"    bottom: false,\n" +
				            		"    right: false,\n" +
				            		"  });\n" +
				            		"\n" +
				            		"  const toggleDrawer = (anchor, open) => (event) => {\n" +
				            		"    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {\n" +
				            		"      return;\n" +
				            		"    }\n" +
				            		"\n" +
				            		"    setState({ ...state, [anchor]: open });\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  const list = (anchor) => (\n" +
				            		"    <div\n" +
				            		"      className={clsx(classes.list, {\n" +
				            		"        [classes.fullList]: anchor === 'top' || anchor === 'bottom',\n" +
				            		"      })}\n" +
				            		"      role=\"presentation\"\n" +
				            		"      onClick={toggleDrawer(anchor, false)}\n" +
				            		"      onKeyDown={toggleDrawer(anchor, false)}\n" +
				            		"    >\n" +
				            		"      <List>\n" +
				            		"        {['Inbox', 'Starred', 'Send email', 'Drafts'].map((text, index) => (\n" +
				            		"          <ListItem button key={text}>\n" +
				            		"            <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>\n" +
				            		"            <ListItemText primary={text} />\n" +
				            		"          </ListItem>\n" +
				            		"        ))}\n" +
				            		"      </List>\n" +
				            		"      <Divider />\n" +
				            		"      <List>\n" +
				            		"        {['All mail', 'Trash', 'Spam'].map((text, index) => (\n" +
				            		"          <ListItem button key={text}>\n" +
				            		"            <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>\n" +
				            		"            <ListItemText primary={text} />\n" +
				            		"          </ListItem>\n" +
				            		"        ))}\n" +
				            		"      </List>\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <div className=\"App\">\n" +
				            		"      <div className={classes.root}>\n" +
				            		"        <AppBar position=\"static\">\n" +
				            		"          <Toolbar>\n" +
				            		"            <React.Fragment key=\"left\">\n" +
				            		"              <IconButton edge=\"start\" className={classes.menuButton} color=\"inherit\" aria-label=\"menu\" onClick={toggleDrawer(\"left\", true)}>\n" +
				            		"                <MenuIcon />\n" +
				            		"              </IconButton>\n" +
				            		"              <Drawer open={state[\"left\"]} onClose={toggleDrawer(\"left\", false)}>\n" +
				            		"                {list(\"left\")}\n" +
				            		"              </Drawer>\n" +
				            		"            </React.Fragment>\n" +
				            		"\n" +
				            		"            <Typography variant=\"h6\" className={classes.title}>\n" +
				            		"              {appbar}\n" +
				            		"            </Typography>\n" +
				            		"\n" +
				            		"            <a className=\"App-link\" href=\"https://reactjs.org\" target=\"_blank\" rel=\"noopener noreferrer\">\n" +
				            		"              <Button color=\"inherit\">\n" +
				            		"                Login\n" +
				            		"              </Button>\n" +
				            		"            </a>\n" +
				            		"          </Toolbar>\n" +
				            		"        </AppBar>\n" +
				            		"      </div>\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"\n" +
				            		"//export default App;\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

						//delete an old file
						if (cssfile.exists()) {
				            cssfile.delete();
				        }

						//create a new file
					    try{
					      if (!cssfile.createNewFile()){
					        System.out.println("Could not create app.css file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
						try {
				            FileWriter fw = new FileWriter(cssfile, true);
				            fw.write(".App {\n" +
				            		"  text-align: left !important;\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-logo {\n" +
				            		"  height: 40vmin;\n" +
				            		"  pointer-events: none;\n" +
				            		"}\n" +
				            		"\n" +
				            		"@media (prefers-reduced-motion: no-preference) {\n" +
				            		"  .App-logo {\n" +
				            		"    animation: App-logo-spin infinite 20s linear;\n" +
				            		"  }\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-header {\n" +
				            		"  text-align: center;\n" +
				            		"  background-color: #282c34;\n" +
				            		"  min-height: 100vh;\n" +
				            		"  display: flex;\n" +
				            		"  flex-direction: column;\n" +
				            		"  align-items: center;\n" +
				            		"  justify-content: center;\n" +
				            		"  font-size: calc(10px + 2vmin);\n" +
				            		"  color: white;\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-link {\n" +
				            		"  color: #61dafb;\n" +
				            		"}\n" +
				            		"\n" +
				            		"@keyframes App-logo-spin {\n" +
				            		"  from {\n" +
				            		"    transform: rotate(0deg);\n" +
				            		"  }\n" +
				            		"  to {\n" +
				            		"    transform: rotate(360deg);\n" +
				            		"  }\n" +
				            		"}\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
					//20211110 yama tab_appbar
					}else if(func_name.equals("t_appbar")){
						tabflag = true;

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						t_cont_List.add(new ArrayList<String>());
						for (int i = 0; i < fn.size() / 2 - 2; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
							t_cont_List.add(new ArrayList<String>());
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);

						//20210414 yama
						t_appbar_count++;

						//20210413 yama
						String abstr = fn.getExtList(2, 1, 0, 1).toString();
						String abpath;
						String abcsspath;

						if(t_appbar_count > 1) {
							Preprocessor.putComponentList("T_App" + t_appbar_count);
							abpath = GlobalEnv.getOutputDirPath() + "/../src/t_app" + t_appbar_count + ".js";
							abcsspath = GlobalEnv.getOutputDirPath()+"/../src/t_app" + t_appbar_count + ".css";
						} else {
							Preprocessor.putComponentList("T_App");
							abpath = GlobalEnv.getOutputDirPath() + "/../src/t_app.js";
							abcsspath = GlobalEnv.getOutputDirPath()+"/../src/t_app.css";
						}

						File file = new File(abpath);
						File cssfile = new File(abcsspath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create t_app.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import logo from './picts/facebook-icon.png';\n");

				            if(t_appbar_count > 1) {
				            	fw.write("import './t_app" + t_appbar_count + ".css';\n");
				            } else {
				            	fw.write("import './t_app.css';\n");
				            }

				            fw.write("import { styled, alpha } from '@mui/material/styles';\n" +
				            		"import AppBar from '@mui/material/AppBar';\n" +
				            		"import Box from '@mui/material/Box';\n" +
				            		"import Toolbar from '@mui/material/Toolbar';\n" +
				            		"import IconButton from '@mui/material/IconButton';\n" +
				            		"import Typography from '@mui/material/Typography';\n" +
				            		"import InputBase from '@mui/material/InputBase';\n" +
				            		"import Badge from '@mui/material/Badge';\n" +
				            		"import MenuItem from '@mui/material/MenuItem';\n" +
				            		"import Menu from '@mui/material/Menu';\n" +
				            		"import MenuIcon from '@mui/icons-material/Menu';\n" +
				            		"import FacebookIcon from '@mui/icons-material/Facebook';\n" +
				            		"import SearchIcon from '@mui/icons-material/Search';\n" +
				            		"import Stack from '@mui/material/Stack';\n" +
				            		"import BarButton from '@mui/material/Button';\n" +
				            		"import AccountCircleIcon from '@mui/icons-material/AccountCircle';\n" +
				            		"import AppsIcon from '@mui/icons-material/Apps';\n" +
				            		"import MessageIcon from '@mui/icons-material/Message';\n" +
				            		"import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';\n" +
				            		"import NotificationsIcon from '@mui/icons-material/Notifications';\n" +
				            		"import MoreIcon from '@mui/icons-material/MoreVert';\n" +
				            		"\n" +
				            		"import PropTypes from 'prop-types';\n" +
				            		"import { makeStyles } from '@material-ui/core/styles';\n" +
				            		"import Tabs from '@material-ui/core/Tabs';\n" +
				            		"import Tab from '@material-ui/core/Tab';\n" +
				            		"import HomeIcon from '@mui/icons-material/Home';\n" +
				            		"import PeopleOutlineIcon from '@mui/icons-material/PeopleOutline';\n" +
				            		"import GroupsIcon from '@mui/icons-material/Groups';\n");

				            for (int i = 0; i < t_cont_List.size(); i++) {
			            		for (int j = 0; j < t_cont_List.get(i).size(); j++) {
			            			fw.write("import " + t_cont_List.get(i).get(j) + " from './" + t_cont_List.get(i).get(j).toString().toLowerCase() + "';\n");
			            		}
			            	}

				            fw.write("\n" +
				            		"const Search = styled('div')(({ theme }) => ({\n" +
				            		"  position: 'relative',\n" +
				            		"  borderRadius: theme.shape.borderRadius,\n" +
				            		"  backgroundColor: alpha(theme.palette.common.white, 0.15),\n" +
				            		"  '&:hover': {\n" +
				            		"    backgroundColor: alpha(theme.palette.common.white, 0.25),\n" +
				            		"  },\n" +
				            		"  marginRight: theme.spacing(2),\n" +
				            		"  marginLeft: 0,\n" +
				            		"  width: '100%',\n" +
				            		"  [theme.breakpoints.up('sm')]: {\n" +
				            		"    marginLeft: theme.spacing(3),\n" +
				            		"    width: 'auto',\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"const SearchIconWrapper = styled('div')(({ theme }) => ({\n" +
				            		"  padding: theme.spacing(0, 2),\n" +
				            		"  height: '100%',\n" +
				            		"  position: 'absolute',\n" +
				            		"  pointerEvents: 'none',\n" +
				            		"  display: 'flex',\n" +
				            		"  alignItems: 'center',\n" +
				            		"  justifyContent: 'center',\n" +
				            		"}));\n" +
				            		"\n" +
				            		"const StyledInputBase = styled(InputBase)(({ theme }) => ({\n" +
				            		"  color: 'inherit',\n" +
				            		"  '& .MuiInputBase-input': {\n" +
				            		"    padding: theme.spacing(1, 1, 1, 0),\n" +
				            		"    // vertical padding + font size from searchIcon\n" +
				            		"    paddingLeft: `calc(1em + ${theme.spacing(4)})`,\n" +
				            		"    transition: theme.transitions.create('width'),\n" +
				            		"    width: '100%',\n" +
				            		"    [theme.breakpoints.up('md')]: {\n" +
				            		"      width: '20ch',\n" +
				            		"    },\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"function TabPanel(props) {\n" +
				            		"  const { children, value, index, ...other } = props;\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <div\n" +
				            		"      role=\"tabpanel\"\n" +
				            		"      hidden={value !== index}\n" +
				            		"      id={`nav-tabpanel-${index}`}\n" +
				            		"      aria-labelledby={`nav-tab-${index}`}\n" +
				            		"      {...other}\n" +
				            		"    >\n" +
				            		"      {value === index && (\n" +
				            		"        <Box p={0}>\n" +
				            		"          <Typography>{children}</Typography>\n" +
				            		"        </Box>\n" +
				            		"      )}\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"\n" +
				            		"TabPanel.propTypes = {\n" +
				            		"  children: PropTypes.node,\n" +
				            		"  index: PropTypes.any.isRequired,\n" +
				            		"  value: PropTypes.any.isRequired,\n" +
				            		"};\n" +
				            		"\n" +
				            		"function a11yProps(index) {\n" +
				            		"  return {\n" +
				            		"    id: `nav-tab-${index}`,\n" +
				            		"    'aria-controls': `nav-tabpanel-${index}`,\n" +
				            		"  };\n" +
				            		"}\n" +
				            		"\n" +
				            		"function LinkTab(props) {\n" +
				            		"  return (\n" +
				            		"    <Tab\n" +
				            		"      component=\"a\"\n" +
				            		"      onClick={(event) => {\n" +
				            		"        event.preventDefault();\n" +
				            		"      }}\n" +
				            		"      {...props}\n" +
				            		"    />\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"\n" +
				            		"const useStyles = makeStyles((theme) => ({\n" +
				            		"  root: {\n" +
				            		"    flexGrow: 1,\n" +
				            		"    backgroundColor: theme.palette.background.paper,\n" +
				            		"    width: 1200,\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"export default function PrimarySearchAppBar() {\n" +
				            		"  const [anchorEl, setAnchorEl] = React.useState(null);\n" +
				            		"  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);\n" +
				            		"\n" +
				            		"  const isMenuOpen = Boolean(anchorEl);\n" +
				            		"  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);\n" +
				            		"\n" +
				            		"  const handleProfileMenuOpen = (event) => {\n" +
				            		"    setAnchorEl(event.currentTarget);\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  const handleMobileMenuClose = () => {\n" +
				            		"    setMobileMoreAnchorEl(null);\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  const handleMenuClose = () => {\n" +
				            		"    setAnchorEl(null);\n" +
				            		"    handleMobileMenuClose();\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  const handleMobileMenuOpen = (event) => {\n" +
				            		"    setMobileMoreAnchorEl(event.currentTarget);\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  const menuId = 'primary-search-account-menu';\n" +
				            		"  const renderMenu = (\n" +
				            		"    <Menu\n" +
				            		"      anchorEl={anchorEl}\n" +
				            		"      anchorOrigin={{\n" +
				            		"        vertical: 'top',\n" +
				            		"        horizontal: 'right',\n" +
				            		"      }}\n" +
				            		"      id={menuId}\n" +
				            		"      keepMounted\n" +
				            		"      transformOrigin={{\n" +
				            		"        vertical: 'top',\n" +
				            		"        horizontal: 'right',\n" +
				            		"      }}\n" +
				            		"      open={isMenuOpen}\n" +
				            		"      onClose={handleMenuClose}\n" +
				            		"    >\n" +
				            		"      <MenuItem onClick={handleMenuClose}>Profile</MenuItem>\n" +
				            		"      <MenuItem onClick={handleMenuClose}>My account</MenuItem>\n" +
				            		"    </Menu>\n" +
				            		"  );\n" +
				            		"\n" +
				            		"  const mobileMenuId = 'primary-search-account-menu-mobile';\n" +
				            		"  const renderMobileMenu = (\n" +
				            		"    <Menu\n" +
				            		"      anchorEl={mobileMoreAnchorEl}\n" +
				            		"      anchorOrigin={{\n" +
				            		"        vertical: 'top',\n" +
				            		"        horizontal: 'right',\n" +
				            		"      }}\n" +
				            		"      id={mobileMenuId}\n" +
				            		"      keepMounted\n" +
				            		"      transformOrigin={{\n" +
				            		"        vertical: 'top',\n" +
				            		"        horizontal: 'right',\n" +
				            		"      }}\n" +
				            		"      open={isMobileMenuOpen}\n" +
				            		"      onClose={handleMobileMenuClose}\n" +
				            		"    >\n" +
				            		"      <MenuItem>\n" +
				            		"        <IconButton size=\"large\" aria-label=\"show 4 new mails\" color=\"inherit\">\n" +
				            		"          <Badge badgeContent={4} color=\"error\">\n" +
				            		"            <ArrowDropDownIcon />\n" +
				            		"          </Badge>\n" +
				            		"        </IconButton>\n" +
				            		"        <p>Messages</p>\n" +
				            		"      </MenuItem>\n" +
				            		"      <MenuItem>\n" +
				            		"        <IconButton\n" +
				            		"          size=\"large\"\n" +
				            		"          aria-label=\"show 17 new notifications\"\n" +
				            		"          color=\"inherit\"\n" +
				            		"        >\n" +
				            		"          <Badge badgeContent={17} color=\"error\">\n" +
				            		"            <NotificationsIcon />\n" +
				            		"          </Badge>\n" +
				            		"        </IconButton>\n" +
				            		"        <p>Notifications</p>\n" +
				            		"      </MenuItem>\n" +
				            		"      <MenuItem onClick={handleProfileMenuOpen}>\n" +
				            		"        <IconButton\n" +
				            		"          size=\"large\"\n" +
				            		"          aria-label=\"account of current user\"\n" +
				            		"          aria-controls=\"primary-search-account-menu\"\n" +
				            		"          aria-haspopup=\"true\"\n" +
				            		"          color=\"inherit\"\n" +
				            		"        >\n" +
				            		"          <AppsIcon />\n" +
				            		"        </IconButton>\n" +
				            		"        <p>Profile</p>\n" +
				            		"      </MenuItem>\n" +
				            		"    </Menu>\n" +
				            		"  );\n" +
				            		"\n" +
				            		"  const classes = useStyles();\n" +
				            		"  const [value, setValue] = React.useState(0);\n" +
				            		"\n" +
				            		"  const handleChange = (event, newValue) => {\n" +
				            		"    setValue(newValue);\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <Box sx={{ flexGrow: 1, bgcolor: '#F0F2F5' }}>\n" +
				            		"      <AppBar position=\"static\" style={{ color: \"#000000\", backgroundColor: \"#FFFFFF\" }}>\n" +
				            		"        <Toolbar>\n" +
				            		"          <img src={logo} width=\"50\"/>\n" +
				            		"          <Search style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 20}}>\n" +
				            		"            <SearchIconWrapper>\n" +
				            		"              <SearchIcon />\n" +
				            		"            </SearchIconWrapper>\n" +
				            		"            <StyledInputBase\n" +
				            		"              placeholder=\"Facebookを検索\"\n" +
				            		"              inputProps={{ 'aria-label': 'search' }}\n" +
				            		"            />\n" +
				            		"          </Search>\n" +
				            		"          <Box sx={{ flexGrow: 1 }} />\n" +
				            		"          <Box sx={{ display: { xs: 'none', md: 'flex' } }}>\n" +
				            		"            <Stack direction=\"row\" spacing={1}>\n" +
				            		"              <Tabs\n" +
				            		"                variant=\"fullWidth\"\n" +
				            		"                value={value}\n" +
				            		"                onChange={handleChange}\n" +
				            		"                textColor=\"primary\"\n" +
				            		"                indicatorColor=\"primary\"\n" +
				            		"                aria-label=\"nav tabs example\"\n" +
				            		"              >\n");
				            for(int i = 0; i < fn.size() / 2 - 1; i++) {
			            		String t_cont1 = fn.getExtList(i * 2 + 2, 1, 0, 1, 2, 1, 0, 1).toString();
			            		String tabName = t_cont1.substring(2, t_cont1.length() - 2);

								fw.write("                <Tab icon={<" + tabName + " />} />\n");
							}
//				            for(int i = 0; i < t_cont_count; i++) {
//			            		fw.write("                <Tab icon={<HomeIcon />} />\n");
//			            	}
				            fw.write("              </Tabs>\n" +
				            		"              <BarButton variant=\"text\" style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 20}}><b>友達を検索</b></BarButton>\n" +
				            		"              <BarButton variant=\"text\" style={{ color: \"#000000\", borderRadius: 20}} startIcon={<AccountCircleIcon />}><b>匿名太郎</b></BarButton>\n" +
				            		"              <IconButton size=\"large\" style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 100}}>\n" +
				            		"                <AppsIcon />\n" +
				            		"              </IconButton>\n" +
				            		"              <IconButton size=\"large\" style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 100}}>\n" +
				            		"                <MessageIcon />\n" +
				            		"              </IconButton>\n" +
				            		"              <IconButton size=\"large\" style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 100}}>\n" +
				            		"                <Badge badgeContent={3} color=\"error\">\n" +
				            		"                  <NotificationsIcon />\n" +
				            		"                </Badge>\n" +
				            		"              </IconButton>\n" +
				            		"              <IconButton\n" +
				            		"                size=\"large\"\n" +
				            		"                edge=\"end\"\n" +
				            		"                aria-label=\"account of current user\"\n" +
				            		"                aria-controls={menuId}\n" +
				            		"                aria-haspopup=\"true\"\n" +
				            		"                onClick={handleProfileMenuOpen}\n" +
				            		"                style={{ color: \"#000000\", backgroundColor: \"#F0F2F5\", borderRadius: 100}}\n" +
				            		"              >\n" +
				            		"                <ArrowDropDownIcon />\n" +
				            		"              </IconButton>\n" +
				            		"            </Stack>\n" +
				            		"          </Box>\n" +
				            		"          <Box sx={{ display: { xs: 'flex', md: 'none' } }}>\n" +
				            		"            <IconButton\n" +
				            		"              size=\"large\"\n" +
				            		"              aria-label=\"show more\"\n" +
				            		"              aria-controls={mobileMenuId}\n" +
				            		"              aria-haspopup=\"true\"\n" +
				            		"              onClick={handleMobileMenuOpen}\n" +
				            		"              color=\"inherit\"\n" +
				            		"            >\n" +
				            		"              <MoreIcon />\n" +
				            		"            </IconButton>\n" +
				            		"          </Box>\n" +
				            		"        </Toolbar>\n" +
				            		"      </AppBar>\n");

				            int j = 0;
				            for(int i = 0; i < t_cont_count; i++) {
			            		fw.write("      <TabPanel value={value} index={" + i + "}>\n" +
			            				"        <Box p={3}>\n");
			            				for(; j < embed_List.size() && embed_List.get(j).equals(i); j++) {
			            					//System.out.println(j);
			            					for(int k = 0; k < embedlines.get(j).size(); k++) {
			            						fw.write("          " + embedlines.get(j).get(k) + "\n");
			            					}
			            				}
		            					for(int k = 0; k < t_cont_List.get(i).size(); k++) {
		            						fw.write("          <" + t_cont_List.get(i).get(k) + " />\n");
		            					}
			            	fw.write("        </Box>\n" +
			            			"      </TabPanel>\n");
			            	}
//				            	fw.write("      <TabPanel value={value} index={0}>\n" +
//				            		"        <Grid container spacing={5}>\n" +
//				            		"          <Grid item xs={4} md={3}>\n" +
//				            		"            <List />\n" +
//				            		"          </Grid>\n" +
//				            		"          <Grid item xs={8} md={6}>\n" +
//				            		"            <Card />\n" +
//				            		"          </Grid>\n" +
//				            		"          <Grid item xs={2} md={3}>\n" +
//				            		"            <List2 />\n" +
//				            		"          </Grid>\n" +
//				            		"        </Grid>\n" +
//				            		"      </TabPanel>\n" +
//				            		"      <TabPanel value={value} index={1}>\n" +
//				            		"        <Paper elevation={3}>\n" +
//				            		"          <Box p={3}>\n" +
//				            		"          </Box>\n" +
//				            		"        </Paper>\n" +
//				            		"      </TabPanel>\n" +
//				            		"      <TabPanel value={value} index={2}>\n" +
//				            		"        <Paper elevation={3}>\n" +
//				            		"          <Box p={3}>\n" +
//				            		"          </Box>\n" +
//				            		"        </Paper>\n" +
//				            		"      </TabPanel>\n" +
				            fw.write("      {renderMobileMenu}\n" +
				            		"      {renderMenu}\n" +
				            		"    </Box>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

						//delete an old file
						if (cssfile.exists()) {
				            cssfile.delete();
				        }

						//create a new file
					    try{
					      if (!cssfile.createNewFile()){
					        System.out.println("Could not create t_app.css file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
						try {
				            FileWriter fw = new FileWriter(cssfile, true);
				            fw.write(".App {\n" +
				            		"  text-align: left !important;\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-logo {\n" +
				            		"  height: 40vmin;\n" +
				            		"  pointer-events: none;\n" +
				            		"}\n" +
				            		"\n" +
				            		"@media (prefers-reduced-motion: no-preference) {\n" +
				            		"  .App-logo {\n" +
				            		"    animation: App-logo-spin infinite 20s linear;\n" +
				            		"  }\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-header {\n" +
				            		"  text-align: center;\n" +
				            		"  background-color: #282c34;\n" +
				            		"  min-height: 100vh;\n" +
				            		"  display: flex;\n" +
				            		"  flex-direction: column;\n" +
				            		"  align-items: center;\n" +
				            		"  justify-content: center;\n" +
				            		"  font-size: calc(10px + 2vmin);\n" +
				            		"  color: white;\n" +
				            		"}\n" +
				            		"\n" +
				            		".App-link {\n" +
				            		"  color: #61dafb;\n" +
				            		"}\n" +
				            		"\n" +
				            		"@keyframes App-logo-spin {\n" +
				            		"  from {\n" +
				            		"    transform: rotate(0deg);\n" +
				            		"  }\n" +
				            		"  to {\n" +
				            		"    transform: rotate(360deg);\n" +
				            		"  }\n" +
				            		"}\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

						t_cont_count = 0;
						embed_List.clear();
						t_cont_List.clear();
						t_contflag = false;

					}else if(func_name.equals("tab")){
						tabflag = true;

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						t_cont_List.add(new ArrayList<String>());
						for (int i = 0; i < fn.size() / 2 - 2; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
							t_cont_List.add(new ArrayList<String>());
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);

						//20210414 yama
						tab_count++;

						//20210413 yama
						String tabpath;

						if(tab_count > 1) {
							Preprocessor.putComponentList("Tab" + tab_count);
							tabpath = GlobalEnv.getOutputDirPath() + "/../src/tab" + tab_count + ".js";
						} else {
							Preprocessor.putComponentList("Tab");
							tabpath = GlobalEnv.getOutputDirPath() + "/../src/tab.js";
						}

						File file = new File(tabpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create tab.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

			            	fw.write("import React from 'react';\n" +
			            			"import PropTypes from 'prop-types';\n" +
			            			"import { makeStyles } from '@material-ui/core/styles';\n" +
			            			"import AppBar from '@material-ui/core/AppBar';\n" +
			            			"import Tabs from '@material-ui/core/Tabs';\n" +
			            			"import Tab from '@material-ui/core/Tab';\n" +
			            			"import Typography from '@material-ui/core/Typography';\n" +
			            			"import Box from '@material-ui/core/Box';\n" +
			            			"import './tab.css';\n" +
			            			"import Paper from '@material-ui/core/Paper';\n");

			            	for (int i = 0; i < t_cont_List.size(); i++) {
			            		for (int j = 0; j < t_cont_List.get(i).size(); j++) {
			            			fw.write("import " + t_cont_List.get(i).get(j) + " from './" + t_cont_List.get(i).get(j).toString().toLowerCase() + "';\n");
			            		}
			            	}

			            	for(int i = 0; i < fn.size() / 2 - 1; i++) {
			            		String t_cont2;

//			            		if(t_embed_List.contains(i)){
//			            			System.out.println(fn.getExtList(i * 2 + 2, 1, 0, 1, 4, 1));
//			            			t_cont2 = fn.getExtList(i * 2 + 2, 1, 0, 1, 4, 1, 0, 1, 2, 1, 0, 1).toString();
//			            		} else {
//			            			//ここをTFE対応に変える
//			            			t_cont2 = fn.getExtList(i * 2 + 2, 1, 0, 1, 4, 1, 0, 1).toString();
//			            			System.out.println("t_cont2: " + t_cont2);
//			            		}

								//String[] fileName = t_cont2.substring(2, t_cont2.length() - 2).split("\\.");
								String[] fileName = "c1.ssql".split("\\.");
			            		System.out.println("filename: " + fileName[0]);

								//20210421 yama hikisuu2 TFE taiou
								if (fileName[1].equals("ssql")) {
									fw.write("import c" + i +" from './" + fileName[0] +".js';\n");
								}
							}

			            	for(int i = 0; i < fn.size() / 2 - 1; i++) {
			            		String t_cont1 = fn.getExtList(i * 2 + 2, 1, 0, 1, 2, 1, 0, 1).toString();
			            		String tabName = t_cont1.substring(2, t_cont1.length() - 2);

								fw.write("var tlabel" + i + " = '" + tabName + "';\n");
							}

			            	fw.write("\n" +
			            			"function TabPanel(props) {\n" +
			            			"  const { children, value, index, ...other } = props;\n" +
			            			"\n" +
			            			"  return (\n" +
			            			"    <div\n" +
			            			"      role=\"tabpanel\"\n" +
			            			"      hidden={value !== index}\n" +
			            			"      id={`nav-tabpanel-${index}`}\n" +
			            			"      aria-labelledby={`nav-tab-${index}`}\n" +
			            			"      {...other}\n" +
			            			"    >\n" +
			            			"      {value === index && (\n" +
			            			"        <Box p={0}>\n" +
			            			"          <Typography>{children}</Typography>\n" +
			            			"        </Box>\n" +
			            			"      )}\n" +
			            			"    </div>\n" +
			            			"  );\n" +
			            			"}\n" +
			            			"\n" +
			            			"TabPanel.propTypes = {\n" +
			            			"  children: PropTypes.node,\n" +
			            			"  index: PropTypes.any.isRequired,\n" +
			            			"  value: PropTypes.any.isRequired,\n" +
			            			"};\n" +
			            			"\n" +
			            			"function a11yProps(index) {\n" +
			            			"  return {\n" +
			            			"    id: `nav-tab-${index}`,\n" +
			            			"    'aria-controls': `nav-tabpanel-${index}`,\n" +
			            			"  };\n" +
			            			"}\n" +
			            			"\n" +
			            			"function LinkTab(props) {\n" +
			            			"  return (\n" +
			            			"    <Tab\n" +
			            			"      component=\"a\"\n" +
			            			"      onClick={(event) => {\n" +
			            			"        event.preventDefault();\n" +
			            			"      }}\n" +
			            			"      {...props}\n" +
			            			"    />\n" +
			            			"  );\n" +
			            			"}\n" +
			            			"\n" +
			            			"const useStyles = makeStyles((theme) => ({\n" +
			            			"  root: {\n" +
			            			"    flexGrow: 1,\n" +
			            			"    backgroundColor: theme.palette.background.paper,\n" +
			            			"    width: 1200,\n" +
			            			"  },\n" +
			            			"}));\n" +
			            			"\n" +
			            			"export default function NavTabs() {\n" +
			            			"  const classes = useStyles();\n" +
			            			"  const [value, setValue] = React.useState(0);\n" +
			            			"\n" +
			            			"  const handleChange = (event, newValue) => {\n" +
			            			"    setValue(newValue);\n" +
			            			"  };\n" +
			            			"\n" +
			            			"  return (\n" +
			            			"    <div className=\"tab\">\n" +
			            			"      <AppBar position=\"static\">\n" +
			            			"        <Tabs\n" +
			            			"          variant=\"fullWidth\"\n" +
			            			"          value={value}\n" +
			            			"          onChange={handleChange}\n" +
			            			"          aria-label=\"nav tabs example\"\n" +
			            			"        >\n");
			            	for(int i = 0; i < t_cont_count; i++) {
			            		fw.write("          <LinkTab label={tlabel" + i + "} {...a11yProps(" + i + ")} />\n");
			            	}

			            	fw.write("        </Tabs>\n" +
			            			"      </AppBar>\n");

			            	int j = 0;
			            	for(int i = 0; i < t_cont_count; i++) {
			            		fw.write("      <TabPanel value={value} index={" + i + "}>\n" +
			            			"        <Paper elevation={3}>\n" +
			            			"          <Box p={3}>\n");
			            				for(; j < embed_List.size() && embed_List.get(j).equals(i); j++) {
			            					//System.out.println(j);
			            					for(int k = 0; k < embedlines.get(j).size(); k++) {
			            						fw.write("            " + embedlines.get(j).get(k) + "\n");
			            					}
			            				}
		            					for(int k = 0; k < t_cont_List.get(i).size(); k++) {
		            						fw.write("            <" + t_cont_List.get(i).get(k) + " />\n");
		            					}
			            			//"            <div dangerouslySetInnerHTML={{__html: c" + i + "}} />\n" +
			            		fw.write("          </Box>\n" +
			            			"        </Paper>\n" +
			            			"      </TabPanel>\n");
			            	}

			            	//System.out.println();

			            	fw.write("    </div>\n" +
			            			"  );\n" +
			            			"}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					    String tabcsspath = GlobalEnv.getOutputDirPath()+"/../src/tab.css";
						File cssfile = new File(tabcsspath);

						//delete an old file
						if (cssfile.exists()) {
				            cssfile.delete();
				        }

						//create a new file
					    try{
					      if (!cssfile.createNewFile()){
					        System.out.println("Could not create tab.css file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
						try {
				            FileWriter fw = new FileWriter(cssfile, true);
				            fw.write(".tab {\n" +
				            		"  margin-left: 10%;\n" +
				            		"  margin-right: 10%;\n" +
				            		"}\n" +
				            		".paper {\n" +
				            		"  width: 1000;\n" +
				            		"}\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

						t_cont_count = 0;
						embed_List.clear();
						t_cont_List.clear();
						t_contflag = false;

					//20210706 yama vtab
					}else if(func_name.equals("vtab")){
						tabflag = true;

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						t_cont_List.add(new ArrayList<String>());
						for (int i = 0; i < fn.size() / 2 - 2; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
							t_cont_List.add(new ArrayList<String>());
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);

						vtab_count++;

						String vtabpath;

						if(vtab_count > 1) {
							Preprocessor.putComponentList("VTab" + vtab_count);
							vtabpath = GlobalEnv.getOutputDirPath() + "/../src/vtab" + vtab_count + ".js";
						} else {
							Preprocessor.putComponentList("VTab");
							vtabpath = GlobalEnv.getOutputDirPath() + "/../src/vtab.js";
						}

						File file = new File(vtabpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create vtab.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

			            	fw.write("import React from 'react';\n" +
			            			"import PropTypes from 'prop-types';\n" +
			            			"import { makeStyles } from '@material-ui/core/styles';\n" +
			            			"import Tabs from '@material-ui/core/Tabs';\n" +
			            			"import Tab from '@material-ui/core/Tab';\n" +
			            			"import Typography from '@material-ui/core/Typography';\n" +
			            			"import Box from '@material-ui/core/Box';\n" +
			            			"import './vtab.css';\n" +
			            			"import Paper from '@material-ui/core/Paper';\n");

			            	for (int i = 0; i < t_cont_List.size(); i++) {
			            		for (int j = 0; j < t_cont_List.get(i).size(); j++) {
			            			fw.write("import " + t_cont_List.get(i).get(j) + " from './" + t_cont_List.get(i).get(j).toString().toLowerCase() + "';\n");
			            		}
			            	}

			            	for(int i = 0; i < fn.size() / 2 - 1; i++) {
			            		String t_cont2;
								String[] fileName = "c1.ssql".split("\\.");

								if(fileName[1].equals("ssql")) {
									fw.write("import c" + i +" from './" + fileName[0] +".js';\n");
								}
							}

			            	for(int i = 0; i < fn.size() / 2 - 1; i++) {
			            		String t_cont1 = fn.getExtList(i * 2 + 2, 1, 0, 1, 2, 1, 0, 1).toString();
			            		String tabName = t_cont1.substring(2, t_cont1.length() - 2);

								fw.write("var tlabel" + i + " = '" + tabName + "';\n");
							}

			            	fw.write("\n" +
			            			"function TabPanel(props) {\n" +
			            			"  const { children, value, index, ...other } = props;\n" +
			            			"\n" +
			            			"  return (\n" +
			            			"    <div\n" +
			            			"      role=\"tabpanel\"\n" +
			            			"      hidden={value !== index}\n" +
			            			"      id={`vertical-tabpanel-${index}`}\n" +
			            			"      aria-labelledby={`vertical-tab-${index}`}\n" +
			            			"      {...other}\n" +
			            			"    >\n" +
			            			"      {value === index && (\n" +
			            			"        <Box p={3}>\n" +
			            			"          <Typography>{children}</Typography>\n" +
			            			"        </Box>\n" +
			            			"      )}\n" +
			            			"    </div>\n" +
			            			"  );\n" +
			            			"}\n" +
			            			"\n" +
			            			"TabPanel.propTypes = {\n" +
			            			"  children: PropTypes.node,\n" +
			            			"  index: PropTypes.any.isRequired,\n" +
			            			"  value: PropTypes.any.isRequired,\n" +
			            			"};\n" +
			            			"\n" +
			            			"function a11yProps(index) {\n" +
			            			"  return {\n" +
			            			"    id: `vertical-tab-${index}`,\n" +
			            			"    'aria-controls': `vertical-tabpanel-${index}`,\n" +
			            			"  };\n" +
			            			"}\n" +
			            			"\n" +
			            			"const useStyles = makeStyles((theme) => ({\n" +
			            			"  root: {\n" +
			            			"    flexGrow: 1,\n" +
			            			//"    backgroundColor: theme.palette.background.paper,\n" +
			            			"    backgroundColor: '#FFFFF2',\n" +
			            			"    display: 'flex',\n" +
			            			"    height: 600,\n" +
			            			"  },\n" +
			            			"  tabs: {\n" +
			            			"    borderRight: `1px solid ${theme.palette.divider}`,\n" +
			            			"    backgroundColor: '#E8F1FA',\n" +
			            			"  },\n" +
			            			"}));\n" +
			            			"\n" +
			            			"export default function VerticalTabs() {\n" +
			            			"  const classes = useStyles();\n" +
			            			"  const [value, setValue] = React.useState(0);\n" +
			            			"\n" +
			            			"  const handleChange = (event, newValue) => {\n" +
			            			"    setValue(newValue);\n" +
			            			"  };\n" +
			            			"\n" +
			            			"  return (\n" +
			            			"    <div className={classes.root}>\n" +
			            			"      <Tabs\n" +
			            			"        orientation=\"vertical\"\n" +
			            			"        variant=\"scrollable\"\n" +
			            			"        value={value}\n" +
			            			"        onChange={handleChange}\n" +
			            			"        aria-label=\"Vertical tabs example\"\n" +
			            			"        className={classes.tabs}\n" +
			            			"      >\n");
			            	for(int i = 0; i < t_cont_count; i++) {
			            		fw.write("          <Tab label={tlabel" + i + "} {...a11yProps(" + i + ")} />\n");
			            	}

			            	fw.write("        </Tabs>\n");

			            	int j = 0;
			            	for(int i = 0; i < t_cont_count; i++) {
			            		fw.write("      <TabPanel value={value} index={" + i + "}>\n");
	            				for(; j < embed_List.size() && embed_List.get(j).equals(i); j++) {
	            					for(int k = 0; k < embedlines.get(j).size(); k++) {
	            						fw.write("            " + embedlines.get(j).get(k) + "\n");
	            					}
	            				}
	            				for(int k = 0; k < t_cont_List.get(i).size(); k++) {
            						fw.write("            <" + t_cont_List.get(i).get(k) + " />\n");
            					}
			            		fw.write("      </TabPanel>\n");
			            	}

			            	fw.write("    </div>\n" +
			            			"  );\n" +
			            			"}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					    String vtabcsspath = GlobalEnv.getOutputDirPath()+"/../src/vtab.css";
						File cssfile = new File(vtabcsspath);

						//delete an old file
						if (cssfile.exists()) {
				            cssfile.delete();
				        }

						//create a new file
					    try{
					      if (!cssfile.createNewFile()){
					        System.out.println("Could not create vtab.css file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
						try {
				            FileWriter fw = new FileWriter(cssfile, true);
				            fw.write(".tab {\n" +
				            		"  margin-left: 10%;\n" +
				            		"  margin-right: 10%;\n" +
				            		"}\n" +
				            		".paper {\n" +
				            		"  width: 1000;\n" +
				            		"}\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

						t_cont_count = 0;
						embed_List.clear();
						t_cont_List.clear();
						t_contflag = false;

					//20210413 yama t_cont
					}else if(func_name.equals("t_cont")){
						t_contflag = true;

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						//System.out.println(tfe_tree.getExtList(1, 0, 1, 4));
						tmp1.add(fn.getExtList(4));
						for (int i = 0; i < fn.size() / 2 - 3; i++) {
							tmp1.add(fn.get(i * 2 + 5));
							//System.out.println(fn.get(5));
							tmp1.add(fn.getExtList(i * 2 + 6));
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);
						//System.out.println(result);

						t_cont_count++;

					//20210506 yama embed
					}else if(func_name.equals("embed")) {
						embed_count++;

						if(tabflag || contflag) {
							if (tabflag) {
								t_embedflag = true;
							}
							out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

							//componentリストの一時退避
							ExtList component_list = Preprocessor.getComponentList();
							//From句&Where句の一時退避
							String fromClause = Preprocessor.getFromClause();
							String whereClause = Preprocessor.getWhereClause();

							String embedfile = fn.getExtList(2, 1, 0, 1).toString();
							String[] input = new String[4];
							input[0] = "-f";
							input[1] = GlobalEnv.getOutputDirPath() + "/../src/" + embedfile.substring(2, embedfile.length() - 2).toString();
							input[2] = "-d";
							input[3] = GlobalEnv.getOutputDirPath();

							ExtList execFile_list = Preprocessor.getExecFileList();

							if(!execFile_list.contains(embedfile.substring(2, embedfile.length() - 2).toString())) {
								execFile_list.add(embedfile.substring(2, embedfile.length() - 2).toString());
								FrontEnd.execEmbedSuperSQL(input, execFile_list);
								Preprocessor.removeExecFileList();
							} else {
								System.err.println("There is a loop in the file embedding!");
								System.exit(-1);
							}

							//componentリストを戻す
							for (int i = 0; i < component_list.size(); i++) {
								Preprocessor.putComponentList(component_list.get(i).toString());
							}
							//From句&Where句を戻す
							Preprocessor.setFromClause(fromClause);
							Preprocessor.setWhereClause(whereClause);

							String[] fileName = embedfile.substring(2, embedfile.length() - 2).split("\\.");
							String path = GlobalEnv.getOutputDirPath() + "/" + fileName[0] + ".html";
							File file = new File(path);

							List<String> list = new ArrayList<>();
							embedlines.add(list);

							try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));){
							    String str;
								//List<String> lines = new ArrayList<>();
							    while ((str = reader.readLine()) != null) {
							    	if(str.equals("<!DOCTYPE html>")) {
							    	} else {
							    		list.add(str);
							    	}
							    }
							} catch (IOException e) {
							    e.printStackTrace();
							}

							if (tabflag) {
								embed_List.add(t_cont_count);
								t_embedflag = false;
							}
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Embed" + embed_count);
							}

						} else {
							out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

//							String embedfile = fn.getExtList(2, 1, 0, 1).toString();
//							String[] fileName = embedfile.substring(2, embedfile.length() - 2).split("\\.");
//
//							String path = GlobalEnv.getOutputDirPath() + "/../src/" + fileName[0] + ".html";
//							File file = new File(path);
//
//							List<String> list = new ArrayList<>();
//							embedlines.add(list);
//
//							try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));){
//							    String str;
//								//List<String> lines = new ArrayList<>();
//							    while ((str = reader.readLine()) != null) {
//							        list.add(str);
//							    }
//							} catch (IOException e) {
//							    e.printStackTrace();
//							}
//
//							String tmp = "";
//							ExtList tmp1 = new ExtList();
//							ExtList tmp2 = new ExtList();
//							ExtList tmp3 = new ExtList();
//							ExtList result = new ExtList();
//
//							for(int i = 0; i < list.size(); i++) {
//        						tmp = tmp.concat("\'" + list.get(i) + "\'");
//        					}
//							tmp1.add(tmp);
//							tmp2.add("sl");
//							tmp2.add(tmp1);
//							tmp3.add(tmp2);
//							result.add("operand");
//							result.add(tmp3);
//							//System.out.println(result);
//							out_sch = read_attribute(result);
						}

					//20210628 yama button
					}else if(func_name.equals("button")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						button_count++;

						String buttonstr = fn.getExtList(2, 1, 0, 1).toString();
						String buttonpath;

						if (button_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Button" + button_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Button" + button_count);
							} else {
								Preprocessor.putComponentList("Button" + button_count);
							}
							buttonpath = GlobalEnv.getOutputDirPath() + "/../src/button" + button_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Button");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Button");
							} else {
								Preprocessor.putComponentList("Button");
							}
							buttonpath = GlobalEnv.getOutputDirPath() + "/../src/button.js";
						}

						File file = new File(buttonpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create button.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    String dec_button = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();
					    String[] dec = dec_button.substring(2, dec_button.length() - 1).replace('\'', '\"').split(",");

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import { makeStyles } from '@material-ui/core/styles';\n" +
				            		"import Button from '@material-ui/core/Button';\n" +
				            		"var button = '" + buttonstr.substring(2, buttonstr.length() - 2) + "';\n" +
				            		"\n" +
				            		"const useStyles = makeStyles((theme) => ({\n" +
				            		"  root: {\n" +
				            		"    '& > *': {\n" +
				            		"      margin: theme.spacing(1),\n" +
				            		"      textTransform: 'none'," +
				            		"    },\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"export default function ContainedButtons() {\n" +
				            		"  const classes = useStyles();\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <div className={classes.root}>\n" +
				            		"      <Button variant=\"contained\" ");
				            if(dec_button.startsWith("@{")) {
				            	for(int i = 0; i < dec.length; i++) {
				            		fw.write(dec[i] + " ");
				            	}
				            }
				            fw.write(">\n" +
				            		"        {button}\n" +
				            		"      </Button>\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"}");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20210706 yama textfield
					}else if(func_name.equals("textfield")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						textfield_count++;

						String textfieldstr = fn.getExtList(2, 1, 0, 1).toString();
						String textfieldpath;

						if (textfield_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Textfield" + textfield_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Textfield" + textfield_count);
							} else {
								Preprocessor.putComponentList("Textfield" + textfield_count);
							}
							textfieldpath = GlobalEnv.getOutputDirPath() + "/../src/textfield" + textfield_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Textfield");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Textfield");
							} else {
								Preprocessor.putComponentList("Textfield");
							}
							textfieldpath = GlobalEnv.getOutputDirPath() + "/../src/textfield.js";
						}

						File file = new File(textfieldpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create textfield.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    String dec_textfield = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();
					    String[] dec = dec_textfield.substring(2, dec_textfield.length() - 1).replace('\'', '\"').split(",");

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import { makeStyles } from '@material-ui/core/styles';\n" +
				            		"import TextField from '@material-ui/core/TextField';\n" +
				            		"var textfield = '" + textfieldstr.substring(2, textfieldstr.length() - 2) + "';\n" +
				            		"\n" +
				            		"const useStyles = makeStyles((theme) => ({\n" +
				            		"  root: {\n" +
				            		"    '& > *': {\n" +
				            		"      margin: theme.spacing(1),\n" +
				            		"      width: '50ch',\n" +
				            		"      backgroundColor: '#FFFFFF',\n" +
				            		"    },\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"export default function BasicTextFields() {\n" +
				            		"  const classes = useStyles();\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <form className={classes.root} noValidate autoComplete=\"off\">\n" +
				            		"      <TextField id=\"outlined-basic\" label={textfield} variant=\"outlined\" />\n" +
				            		"    </form>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"");
				            if(dec_textfield.startsWith("@{")) {
				            	for(int i = 0; i < dec.length; i++) {
				            		fw.write(dec[i] + " ");
				            	}
				            }
				            fw.write("");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20211220 yama unstyled_textfield
					}else if(func_name.equals("u_textfield")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						u_textfield_count++;

						String u_textfieldstr = fn.getExtList(2, 1, 0, 1).toString();
						String u_textfieldpath;

						if (u_textfield_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("U_Textfield" + u_textfield_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("U_Textfield" + u_textfield_count);
							} else {
								Preprocessor.putComponentList("U_Textfield" + u_textfield_count);
							}
							u_textfieldpath = GlobalEnv.getOutputDirPath() + "/../src/u_textfield" + u_textfield_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("U_Textfield");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("U_Textfield");
							} else {
								Preprocessor.putComponentList("U_Textfield");
							}
							u_textfieldpath = GlobalEnv.getOutputDirPath() + "/../src/u_textfield.js";
						}

						File file = new File(u_textfieldpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create u_textfield.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    String dec_textfield = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();
					    String[] dec = dec_textfield.substring(2, dec_textfield.length() - 1).replace('\'', '\"').split(",");
					    int width = 300;
					    for (int i = 0; i < dec.length; i++) {
					    	if (dec[i].split("=")[0].trim().equals("width")) {
					    		width = Integer.parseInt(dec[i].split("=")[1]);
					    	}
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import InputUnstyled from '@mui/core/InputUnstyled';\n" +
				            		"import { styled } from '@mui/system';\n" +
				            		"var u_textfield = '" + u_textfieldstr.substring(2, u_textfieldstr.length() - 2) + "';\n" +
				            		"\n" +
				            		"const StyledInputElement = styled('input')`\n" +
				            		"  width: " + width + "px;\n" +
				            		"  font-size: 1rem;\n" +
				            		"  font-family: IBM Plex Sans, sans-serif;\n" +
				            		"  font-weight: 400;\n" +
				            		"  line-height: 1.4375em;\n" +
				            		"  background: rgb(243, 246, 249);\n" +
				            		"  border: 1px solid #e5e8ec;\n" +
				            		"  border-radius: 30px;\n" +
				            		"  padding: 6px 10px;\n" +
				            		"  color: #20262d;\n" +
				            		"  transition: width 300ms ease;\n" +
				            		"`;\n" +
				            		"\n" +
				            		"const CustomInput = React.forwardRef(function CustomInput(props, ref) {\n" +
				            		"  return (\n" +
				            		"    <InputUnstyled components={{ Input: StyledInputElement }} {...props} ref={ref} />\n" +
				            		"  );\n" +
				            		"});\n" +
				            		"\n" +
				            		"export default function UnstyledInput() {\n" +
				            		"  return (\n" +
				            		"    <CustomInput aria-label=\"Demo input\" placeholder={u_textfield} />\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20211218 yama text
					}else if(func_name.equals("text")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						text_count++;

						String textstr = fn.getExtList(2, 1, 0, 1).toString();
						String textpath;

						if (text_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Text" + text_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Text" + text_count);
							} else {
								Preprocessor.putComponentList("Text" + text_count);
							}
							textpath = GlobalEnv.getOutputDirPath() + "/../src/text" + text_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Text");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Text");
							} else {
								Preprocessor.putComponentList("Text");
							}
							textpath = GlobalEnv.getOutputDirPath() + "/../src/text.js";
						}

						File file = new File(textpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create text.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    String dec_text = ((ExtList)tfe_tree.get(1)).get(((ExtList)tfe_tree.get(1)).size() - 1).toString();
					    String[] dec = dec_text.substring(2, dec_text.length() - 1).replace('\'', '\"').split(",");
					    String fontSize = "", color = "", bgcolor = "", fontWeight = "", lineHeight = "", letterSpacing = "", textAlign = "";

					    for (int i = 0; i < dec.length; i++) {
						    if (dec[i].split("=")[0].trim().equals("size") || dec[i].split("=")[0].trim().equals("font-size")) {
						    	fontSize = dec[i].split("=")[1];
						    } else if (dec[i].split("=")[0].trim().equals("color") || dec[i].split("=")[0].trim().equals("font-color")) {
						    	color = "'" + dec[i].split("=")[1].substring(1, dec[i].split("=")[1].length() - 1) + "'";
						    } else if (dec[i].split("=")[0].trim().equals("bgcolor") || dec[i].split("=")[0].trim().equals("background-color")) {
						    	bgcolor = "'" + dec[i].split("=")[1].substring(1, dec[i].split("=")[1].length() - 1) + "'";
						    } else if (dec[i].split("=")[0].trim().equals("font-weight")) {
						    	fontWeight = dec[i].split("=")[1];
						    } else if (dec[i].split("=")[0].trim().equals("line-height")) {
						    	lineHeight = dec[i].split("=")[1];
						    } else if (dec[i].split("=")[0].trim().equals("spacing") || dec[i].split("=")[0].trim().equals("letter-spacing")) {
						    	letterSpacing = dec[i].split("=")[1];
						    } else if (dec[i].split("=")[0].trim().equals("align") || dec[i].split("=")[0].trim().equals("text-align")) {
						    	textAlign = "'" + dec[i].split("=")[1].substring(1, dec[i].split("=")[1].length() - 1) + "'";
						    }
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"var text = '" + textstr.substring(2, textstr.length() - 2) + "';\n" +
				            		"\n" +
				            		"export default function Text() {\n" +
				            		"  return (\n" +
				            		"    <div");
				            if (fontSize.equals("") && color.equals("") && fontWeight.equals("") && lineHeight.equals("") && letterSpacing.equals("") && textAlign.equals("")) {
				            } else {
				            	fw.write(" style={{");

				            	if (!fontSize.equals("")) {
				            		fw.write("fontSize: '" + fontSize + "px', ");
				            	}
				            	if (!color.equals("")) {
				            		fw.write("color: " + color + ", ");
				            	}
				            	if (!bgcolor.equals("")) {
				            		fw.write("backgroundColor: " + bgcolor + ", ");
				            	}
				            	if (!fontWeight.equals("")) {
				            		fw.write("fontWeight: '" + fontWeight + "', ");
				            	}
				            	if (!lineHeight.equals("")) {
				            		fw.write("lineHeight: '" + lineHeight + "', ");
				            	}
				            	if (!letterSpacing.equals("")) {
				            		fw.write("letterSpacing: '" + letterSpacing + "', ");
				            	}
				            	if (!textAlign.equals("")) {
				            		fw.write("textAlign: " + textAlign);
				            	}

				            	fw.write("}}");
				            }
				            fw.write(">{text}</div>\n" +
				            		"  );\n" +
				            		"}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20211218 yama icon
					}else if(func_name.equals("icon")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						String textstr = fn.getExtList(2, 1, 0, 1).toString().substring(2, fn.getExtList(2, 1, 0, 1).toString().length() - 2);

						if (contflag) {
							cont_List.get(cont_List.size() - 1).add(textstr);
						} else if (t_contflag) {
							t_cont_List.get(t_cont_count).add(textstr);
						} else {
							Preprocessor.putComponentList(textstr);
						}

					//20211218 yama br
					}else if(func_name.equals("br")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						int brnum = Integer.parseInt(fn.getExtList(2, 1).toString().substring(1, fn.getExtList(2, 1).toString().length() - 1));

						for (int i = 0; i < brnum; i++) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("br");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("br");
							} else {
								Preprocessor.putComponentList("br");
							}
						}

					//20211118 yama list
					}else if(func_name.equals("list")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						list_count++;

						String listpath;

						if (list_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("List" + list_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("List" + list_count);
							} else {
								Preprocessor.putComponentList("List" + list_count);
							}
							listpath = GlobalEnv.getOutputDirPath() + "/../src/list" + list_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("List");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("List");
							} else {
								Preprocessor.putComponentList("List");
							}
							listpath = GlobalEnv.getOutputDirPath() + "/../src/list.js";
						}

						File file = new File(listpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create list.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import ListSubheader from '@mui/material/ListSubheader';\n" +
				            		"import List from '@mui/material/List';\n" +
				            		"import ListItem from '@material-ui/core/ListItem';\n" +
				            		"import ListItemIcon from '@mui/material/ListItemIcon';\n" +
				            		"import ListItemText from '@mui/material/ListItemText';\n" +
				            		"import Collapse from '@mui/material/Collapse';\n" +
				            		"import InboxIcon from '@mui/icons-material/MoveToInbox';\n" +
				            		"import DraftsIcon from '@mui/icons-material/Drafts';\n" +
				            		"import Box from '@mui/material/Box';\n" +
				            		"import SendIcon from '@mui/icons-material/Send';\n" +
				            		"import ExpandLess from '@mui/icons-material/ExpandLess';\n" +
				            		"import ExpandMore from '@mui/icons-material/ExpandMore';\n" +
				            		"import StarBorder from '@mui/icons-material/StarBorder';\n");

				            for (int i = 0; i < fn.size() / 2 - 1; i++) {
				            	String listpic = fn.getExtList(i * 2 + 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1).toString();
				            	fw.write("import pict" + i + " from './picts/" + listpic.substring(2, listpic.length() - 2) + "';\n");
				            }

				            fw.write("\n" +
				            		"function ListItemLink(props) {\n" +
				            		"  return <ListItem button component=\"a\" {...props} />;\n" +
				            		"}\n" +
				            		"export default function NestedList() {\n" +
				            		"  const [open, setOpen] = React.useState(false);\n" +
				            		"\n" +
				            		"  const handleClick = () => {\n" +
				            		"    setOpen(!open);\n" +
				            		"  };\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <div>\n" +
				            		"      <Box sx={{ m: 1 }} />\n" +
				            		"      <List\n" +
				            		"        sx={{ width: '100%', maxWidth: 360, bgcolor: '#F0F2F5' }}\n" +
				            		"        component=\"nav\"\n" +
				            		"      >\n");

				            for (int i = 0; i < fn.size() / 2 - 1; i++) {
				            	String liststr = fn.getExtList(i * 2 + 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1).toString();

				            	if ((fn.getExtList(i * 2 + 2, 1, 0, 1, 0, 1, 0, 1, 0, 1).size() + 1) / 2 == 3) {
				            		String urlstr = fn.getExtList(i * 2 + 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 4, 1, 0, 1).get(0).toString();
				            		fw.write("        <ListItemLink href=\"" + urlstr.substring(1, urlstr.length() - 1) + "\">\n");
				            	} else {
						            fw.write("        <ListItemLink>\n");
				            	}
						        fw.write("          <ListItemIcon>\n" +
					            		"            <img src={pict" + i + "} width=\"40\"/>\n" +
					            		"          </ListItemIcon>\n" +
					            		"          <ListItemText primary=\"" + liststr.substring(2, liststr.length() - 2) + "\" />\n" +
					            		"        </ListItemLink>\n"
						        );
				            }

				            fw.write("      </List>\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
					//20211220 yama divider
					}else if(func_name.equals("divider")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						divider_count++;

						String dividerstr = fn.getExtList(2, 1, 0, 1).toString();
						String dividerpath;

						if (divider_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Divider" + divider_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Divider" + divider_count);
							} else {
								Preprocessor.putComponentList("Divider" + divider_count);
							}
							dividerpath = GlobalEnv.getOutputDirPath() + "/../src/divider" + divider_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Divider");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Divider");
							} else {
								Preprocessor.putComponentList("Divider");
							}
							dividerpath = GlobalEnv.getOutputDirPath() + "/../src/divider.js";
						}

						File file = new File(dividerpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create divider.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import Divider from '@material-ui/core/Divider';\n");

				            fw.write("\n" +
				            "export default function Dividers() {\n" +
				            "  return (" +
				            "    <Divider variant=\"" + dividerstr + "\" />" +
				            "  )" +
				            "}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20211220 yama avatar
					}else if(func_name.equals("avatar")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						avatar_count++;

						String avatarstr = fn.getExtList(2, 1, 0, 1).toString().substring(2, fn.getExtList(2, 1, 0, 1).toString().length() - 2);
						String avatarpath;

						if (avatar_count > 1) {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Avatar" + avatar_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Avatar" + avatar_count);
							} else {
								Preprocessor.putComponentList("Avatar" + avatar_count);
							}
							avatarpath = GlobalEnv.getOutputDirPath() + "/../src/avatar" + avatar_count + ".js";
						} else {
							if (contflag) {
								cont_List.get(cont_List.size() - 1).add("Avatar");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Avatar");
							} else {
								Preprocessor.putComponentList("Avatar");
							}
							avatarpath = GlobalEnv.getOutputDirPath() + "/../src/avatar.js";
						}

						File file = new File(avatarpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create avatar.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import Avatar from '@mui/material/Avatar';\n" +
				            		"\n" +
				            		"function stringToColor(string) {\n" +
				            		"  let hash = 0;\n" +
				            		"  let i;\n" +
				            		"\n" +
				            		"  /* eslint-disable no-bitwise */\n" +
				            		"  for (i = 0; i < string.length; i += 1) {\n" +
				            		"    hash = string.charCodeAt(i) + ((hash << 5) - hash);\n" +
				            		"  }\n" +
				            		"\n" +
				            		"  let color = '#';\n" +
				            		"\n" +
				            		"  for (i = 0; i < 3; i += 1) {\n" +
				            		"    const value = (hash >> (i * 8)) & 0xff;\n" +
				            		"    color += `00${value.toString(16)}`.substr(-2);\n" +
				            		"  }\n" +
				            		"  /* eslint-enable no-bitwise */\n" +
				            		"\n" +
				            		"  return color;\n" +
				            		"}\n" +
				            		"\n" +
				            		"function stringAvatar(name) {\n" +
				            		"  return {\n" +
				            		"    sx: {\n" +
				            		"      bgcolor: stringToColor(name),\n" +
				            		"    },\n" +
				            		"    children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,\n" +
				            		"  };\n" +
				            		"}\n" +
				            		"\n" +
				            		"export default function Avatars() {\n" +
				            		"  return (\n" +
				            		"    <Avatar sx={{ width: 35, height: 35 }} {...stringAvatar('" + avatarstr + "')} />\n" +
				            		"  )\n" +
				            		"}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					//20211118 yama f_card
					}else if(func_name.equals("card")){
						contflag = true;
						List<String> contlist = new ArrayList<>();
						cont_List.add(contlist);
						cont_maxnest = cont_List.size();

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						for (int i = 0; i < fn.size() / 2 - 2; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);

						card_count++;

						String cardpath;

						if (card_count > 1) {
							if (contflag && cont_List.size() > 1) {
								cont_List.get(cont_List.size() - 2).add("Cards" + card_count);
//							} else if (c_cont_List.size() != 1) {
//								c_cont_List.get(c_cont_List.size() - 2).add("Card" + card_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Cards" + card_count);
							} else {
								Preprocessor.putComponentList("Cards" + card_count);
							}
							cardpath = GlobalEnv.getOutputDirPath() + "/../src/cards" + card_count + ".js";
						} else {
							if (contflag && cont_List.size() > 1) {
								cont_List.get(cont_List.size() - 2).add("Cards");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Cards");
							} else {
								Preprocessor.putComponentList("Cards");
							}
							cardpath = GlobalEnv.getOutputDirPath() + "/../src/cards.js";
						}

						File file = new File(cardpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create cards.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import Box from '@mui/material/Box';\n" +
				            		"import Card from '@mui/material/Card';\n" +
				            		"import CardActions from '@mui/material/CardActions';\n" +
				            		"import CardContent from '@mui/material/CardContent';\n");

				            for (int i = 0; i < cont_List.get(cont_List.size() - 1).size(); i++) {
				            	if (cont_List.get(cont_List.size() - 1).get(i).contains("Icon")) {
				            		fw.write("import " + cont_List.get(cont_List.size() - 1).get(i) + " from '@mui/icons-material/" + cont_List.get(cont_List.size() - 1).get(i).substring(0, cont_List.get(cont_List.size() - 1).get(i).length() - 4) + "';\n");
				            	} else if (cont_List.get(cont_List.size() - 1).get(i).contains("Embed") || cont_List.get(cont_List.size() - 1).get(i).contains("br")) {
				            		//何もしない
				            	} else {
				            		fw.write("import " + cont_List.get(cont_List.size() - 1).get(i) + " from './" + cont_List.get(cont_List.size() - 1).get(i).toString().toLowerCase() + "';\n");
				            	}
			            	}

				            fw.write("\n" +
				            		"export default function BasicCard() {\n" +
				            		"  return (\n" +
				            		"    <Card sx={{ minWidth: 275 }}>\n" +
				            		"      <CardContent style={{fontSize: '20px'}}>\n");

//				            for(; j < embed_List.size() && embed_List.get(j).equals(i); j++) {
//            					for(int k = 0; k < embedlines.get(j).size(); k++) {
//            						fw.write("            " + embedlines.get(j).get(k) + "\n");
//            					}
//            				}

				            for(int i = 0; i < cont_List.get(cont_List.size() - 1).size(); i++) {
				            	if (cont_List.get(cont_List.size() - 1).get(i).contains("Embed")) {
				            		for(int k = 0; k < embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).size(); k++) {
	            						fw.write("          " + embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).get(k) + "\n");
	            					}
				            		embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).clear();
					            } else {
					            	fw.write("        <" + cont_List.get(cont_List.size() - 1).get(i) + " />\n");
					            }
				            }

				            fw.write("      </CardContent>\n" +
				            		"    </Card>\n" +
				            		"  );\n" +
				            		"}\n" +
				            		"");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					    cont_List.remove(cont_List.size() - 1);
						if (cont_List.size() == 0) {
							contflag = false;
						}

					//20211118 yama grid
					}else if(func_name.equals("grid")){
						contflag = true;
						List<String> contlist = new ArrayList<>();
						cont_List.add(contlist);
						cont_maxnest = cont_List.size();

						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						for (int i = 0; i < fn.size() / 2 - 2; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						//System.out.println(g_cont_List);
						out_sch = read_attribute(result);

						grid_count++;

						String gridpath;

						if (grid_count > 1) {
							if (contflag && cont_List.size() > 1) {
								System.out.println(cont_List);
								cont_List.get(cont_List.size() - 2).add("Grids" + grid_count);
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Grids" + grid_count);
							} else {
								Preprocessor.putComponentList("Grids" + grid_count);
							}
							gridpath = GlobalEnv.getOutputDirPath() + "/../src/grids" + grid_count + ".js";
						} else {
							if (contflag && cont_List.size() > 1) {
								cont_List.get(cont_List.size() - 2).add("Grids");
							} else if (t_contflag) {
								t_cont_List.get(t_cont_count).add("Grids");
							} else {
								Preprocessor.putComponentList("Grids");
							}
							gridpath = GlobalEnv.getOutputDirPath() + "/../src/grids.js";
						}
						//System.out.println(g_cont_List.size() +" "+ g_cont_List);

						File file = new File(gridpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create grids.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import { styled } from '@mui/material/styles';\n" +
				            		"import Box from '@mui/material/Box';\n" +
				            		"import Paper from '@mui/material/Paper';\n" +
				            		"import Grid from '@mui/material/Grid';\n");

				            for (int i = 0; i < cont_List.get(cont_List.size() - 1).size(); i++) {
				            	if (cont_List.get(cont_List.size() - 1).get(i).contains("Icon")) {
				            		fw.write("import " + cont_List.get(cont_List.size() - 1).get(i) + " from '@mui/icons-material/" + cont_List.get(cont_List.size() - 1).get(i).substring(0, cont_List.get(cont_List.size() - 1).get(i).length() - 4) + "';\n");
				            	} else if (cont_List.get(cont_List.size() - 1).get(i).contains("Embed") || cont_List.get(cont_List.size() - 1).get(i).contains("br")) {
				            		//何もしない
				            	} else {
				            		fw.write("import " + cont_List.get(cont_List.size() - 1).get(i) + " from './" + cont_List.get(cont_List.size() - 1).get(i).toString().toLowerCase() + "';\n");
				            	}
			            	}

				            fw.write("\n" +
//				            		"const Item = styled(Paper)(({ theme }) => ({\n" +
//				            		"  ...theme.typography.body2,\n" +
//				            		"  padding: theme.spacing(1),\n" +
//				            		"  textAlign: 'center',\n" +
//				            		"  color: theme.palette.text.secondary,\n" +
//				            		"}));\n" +
//				            		"\n" +
				            		"export default function FullWidthGrid() {\n" +
				            		"  return (\n");

				            String[] grid_outer_deco = ((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ).toString().substring(2, ((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ).toString().length() - 1).split(",");
				            int spacing = 0, fontSize = 0, height = 0;
				            for (int i = 0; i < grid_outer_deco.length; i++) {
				            	if (grid_outer_deco[i].split("=")[0].trim().equals("spacing") || grid_outer_deco[i].split("=")[0].trim().equals("space")) {
				            		spacing = Integer.parseInt(grid_outer_deco[i].split("=")[1].trim());
				            	} else if (grid_outer_deco[i].split("=")[0].trim().equals("font-size")) {
				            		fontSize = Integer.parseInt(grid_outer_deco[i].split("=")[1].trim());
				            	} else if (grid_outer_deco[i].split("=")[0].trim().equals("height")) {
				            		height = Integer.parseInt(grid_outer_deco[i].split("=")[1].trim());
				            	}
				            }

				            fw.write("    <Grid container spacing={" + spacing + "}");
				            if (fontSize != 0) {
				            	fw.write(" fontSize={" + fontSize + "}");
				            }
				            if (height != 0) {
				            	fw.write(" style={{ height: " + height + " }}");
				            }
				            fw.write(">\n");

				            ArrayList<Integer> lglist = new ArrayList<>();
							grid_Lg_List.add(lglist);
							ArrayList<Integer> mdlist = new ArrayList<>();
							grid_Md_List.add(mdlist);
							ArrayList<Integer> smlist = new ArrayList<>();
							grid_Sm_List.add(smlist);
							ArrayList<Integer> xslist = new ArrayList<>();
							grid_Xs_List.add(xslist);
							ArrayList<String> alignlist = new ArrayList<>();
							grid_Align_List.add(alignlist);

				            //末尾がtrueなら@{}内にmd=があるか調べる
				            for (int i = 0; i < tfe_tree.getExtList(1, 0, 1).size() / 2 - 1; i++) {
				            	if (tfe_tree.getExtList(1, 0, 1, (i + 1) * 2).size() == 3 && tfe_tree.getExtList(1, 0, 1, (i + 1) * 2).get(2).equals("true")) {
					            	if (tfe_tree.getExtList(1, 0, 1, (i + 1) * 2, 1).get(1).toString().substring(0, 2).equals("@{")) {
					            		String[] grid_deco = tfe_tree.getExtList(1, 0, 1, (i + 1) * 2, 1).get(1).toString().substring(2, tfe_tree.getExtList(1, 0, 1, (i + 1) * 2, 1).get(1).toString().length() - 1).split(",");
					            		boolean lgflag = false, mdflag = false, smflag = false, xsflag = false, alignflag = false;

					            		for (int j = 0; j < grid_deco.length; j++) {
					            			if (grid_deco[j].split("=")[0].trim().equals("lg")) {
					            				grid_Lg_List.get(grid_Lg_List.size() - 1).add(Integer.parseInt(grid_deco[j].split("=")[1].trim()));
					            				lgflag = true;
					            			}
					            			if (grid_deco[j].split("=")[0].trim().equals("md")) {
					            				grid_Md_List.get(grid_Md_List.size() - 1).add(Integer.parseInt(grid_deco[j].split("=")[1].trim()));
					            				mdflag = true;
					            			}
					            			if (grid_deco[j].split("=")[0].trim().equals("sm")) {
					            				grid_Sm_List.get(grid_Sm_List.size() - 1).add(Integer.parseInt(grid_deco[j].split("=")[1].trim()));
					            				smflag = true;
					            			}
					            			if (grid_deco[j].split("=")[0].trim().equals("xs")) {
					            				grid_Xs_List.get(grid_Xs_List.size() - 1).add(Integer.parseInt(grid_deco[j].split("=")[1].trim()));
					            				xsflag = true;
					            			}
					            			if (grid_deco[j].split("=")[0].trim().equals("align") || grid_deco[j].split("=")[0].trim().equals("text-align")) {
					            				grid_Align_List.get(grid_Align_List.size() - 1).add(grid_deco[j].split("=")[1].substring(1, grid_deco[j].split("=")[1].length() - 1).trim());
					            				alignflag = true;
					            			}
					            		}

					            		if (!lgflag) {
					            			grid_Lg_List.get(grid_Lg_List.size() - 1).add(null);
					            		}
					            		if (!mdflag) {
					            			grid_Md_List.get(grid_Md_List.size() - 1).add(null);
					            		}
					            		if (!smflag) {
					            			grid_Sm_List.get(grid_Sm_List.size() - 1).add(null);
					            		}
					            		if (!xsflag) {
					            			grid_Xs_List.get(grid_Xs_List.size() - 1).add(null);
					            		}
					            		if (!alignflag) {
					            			grid_Align_List.get(grid_Align_List.size() - 1).add(null);
					            		}
					            	}
					            } else {
					            	grid_Lg_List.get(grid_Lg_List.size() - 1).add(null);
					            	grid_Md_List.get(grid_Md_List.size() - 1).add(null);
					            	grid_Sm_List.get(grid_Sm_List.size() - 1).add(null);
					            	grid_Xs_List.get(grid_Xs_List.size() - 1).add(null);
					            	grid_Align_List.get(grid_Align_List.size() - 1).add(null);
					            }
				            }

				            for(int i = 0; i < cont_List.get(cont_List.size() - 1).size(); i++) {
				            	fw.write("      <Grid item");
				            	if (grid_Lg_List.get(grid_Lg_List.size() - 1).get(i) != null) {
				            		fw.write(" lg={" + grid_Lg_List.get(grid_Lg_List.size() - 1).get(i) + "}");
				            	}
				            	if (grid_Md_List.get(grid_Md_List.size() - 1).get(i) != null) {
				            		fw.write(" md={" + grid_Md_List.get(grid_Md_List.size() - 1).get(i) + "}");
				            	}
				            	if (grid_Sm_List.get(grid_Sm_List.size() - 1).get(i) != null) {
				            		fw.write(" sm={" + grid_Sm_List.get(grid_Sm_List.size() - 1).get(i) + "}");
				            	}
				            	if (grid_Xs_List.get(grid_Xs_List.size() - 1).get(i) != null) {
				            		fw.write(" xs={" + grid_Xs_List.get(grid_Xs_List.size() - 1).get(i) + "}");
				            	}
				            	if (grid_Align_List.get(grid_Align_List.size() - 1).get(i) != null) {
				            		fw.write(" textAlign=\"" + grid_Align_List.get(grid_Align_List.size() - 1).get(i) + "\"");
				            	}
			            		fw.write(">\n");
			            		if (cont_List.get(cont_List.size() - 1).get(i).contains("Embed")) {
				            		for(int k = 0; k < embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).size(); k++) {
	            						fw.write("          " + embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).get(k) + "\n");
	            					}
				            		embedlines.get(Integer.parseInt(cont_List.get(cont_List.size() - 1).get(i).substring(5)) - 1).clear();
					            } else {
					            	fw.write("        <" + cont_List.get(cont_List.size() - 1).get(i) + " />\n");
					            }
			            		fw.write("      </Grid>\n");
			            	}

				            fw.write("    </Grid>\n" +
				            		"  );\n" +
				            		"}\n");

				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					    cont_List.remove(cont_List.size() - 1);
					    grid_Lg_List.remove(grid_Lg_List.size() - 1);
					    grid_Md_List.remove(grid_Md_List.size() - 1);
					    grid_Sm_List.remove(grid_Sm_List.size() - 1);
					    grid_Xs_List.remove(grid_Xs_List.size() - 1);
						if (cont_List.size() == 0) {
							contflag = false;
						}

					//20210419 yama gridlist
					}else if(func_name.equals("gridlist")){
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));

						gridlist_count++;

						String glstr = fn.getExtList(2, 1, 0, 1).toString();
						String glpath;
						String tdpath;

						if(gridlist_count > 1) {
							Preprocessor.putComponentList("GridList" + appbar_count);
							glpath = GlobalEnv.getOutputDirPath() + "/../src/gridlist" + appbar_count + ".js";
							tdpath = GlobalEnv.getOutputDirPath()+"/../src/tileData" + appbar_count + ".js";
						} else {
							Preprocessor.putComponentList("GridList");
							glpath = GlobalEnv.getOutputDirPath() + "/../src/gridlist.js";
							tdpath = GlobalEnv.getOutputDirPath()+"/../src/tileData.js";
						}

						File file = new File(glpath);
						File tdfile = new File(tdpath);

						//delete an old file
						if (file.exists()) {
				            file.delete();
				        }

						//create a new file
					    try{
					      if (!file.createNewFile()){
					        System.out.println("Could not create gridlist.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
					    try {
				            FileWriter fw = new FileWriter(file, true);

				            fw.write("import React from 'react';\n" +
				            		"import { makeStyles } from '@material-ui/core/styles';\n" +
				            		"import GridList from '@material-ui/core/GridList';\n" +
				            		"import GridListTile from '@material-ui/core/GridListTile';\n" +
				            		"import tileData from './tileData';\n" +
				            		"\n" +
				            		"const useStyles = makeStyles((theme) => ({\n" +
				            		"  root: {\n" +
				            		"    display: 'flex',\n" +
				            		"    flexWrap: 'wrap',\n" +
				            		"    justifyContent: 'space-around',\n" +
				            		"    overflow: 'hidden',\n" +
				            		"    backgroundColor: theme.palette.background.paper,\n" +
				            		"  },\n" +
				            		"  gridList: {\n" +
				            		"    width: 500,\n" +
				            		"    height: 450,\n" +
				            		"  },\n" +
				            		"}));\n" +
				            		"\n" +
				            		"export default function ImageGridList() {\n" +
				            		"  const classes = useStyles();\n" +
				            		"\n" +
				            		"  return (\n" +
				            		"    <div className={classes.root}>\n" +
				            		"      <GridList cellHeight={160} className={classes.gridList} cols={3}>\n" +
				            		"        {tileData.map((tile) => (\n" +
				            		"          <GridListTile key={tile.img} cols={tile.cols || 1}>\n" +
				            		"            <img src={tile.img} alt={tile.title} />\n" +
				            		"          </GridListTile>\n" +
				            		"        ))}\n" +
				            		"      </GridList>\n" +
				            		"    </div>\n" +
				            		"  );\n" +
				            		"}\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }

					    int atr_num = (fn.size() - 1) / 2;

					    ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();
						ExtList tmp3 = new ExtList();
						ExtList tmp4 = new ExtList();
						ExtList tmp5 = new ExtList();
						ExtList tmp6 = new ExtList();
						ExtList tmp7 = new ExtList();
						ExtList tmp8 = new ExtList();
						ExtList tmp9 = new ExtList();
						ExtList tmp10 = new ExtList();
						ExtList tmp11 = new ExtList();
						ExtList result = new ExtList();

						tmp1.add(fn.getExtList(2));
						for (int i = 0; i < atr_num - 1; i++) {
							tmp1.add(fn.get(i * 2 + 3));
							tmp1.add(fn.getExtList(i * 2 + 4));
						}
						tmp2.add("h_exp");
						tmp2.add(tmp1);
						tmp3.add(tmp2);
						tmp4.add("v_exp");
						tmp4.add(tmp3);
						tmp5.add(tmp4);
						tmp6.add("d_exp");
						tmp6.add(tmp5);
						tmp7.add(tmp6);
						tmp8.add("exp");
						tmp8.add(tmp7);
						tmp9.add("[");
						tmp9.add(tmp8);
						tmp9.add("]");
						tmp9.add("!");
						tmp10.add("grouper");
						tmp10.add(tmp9);
						tmp11.add(tmp10);
						result.add("operand");
						result.add(tmp11);
						out_sch = read_attribute(result);

					    //delete an old file
						if (tdfile.exists()) {
				            tdfile.delete();
				        }

						//create a new file
					    try{
					      if (!tdfile.createNewFile()){
					        System.out.println("Could not create tileData.js file.");
					      }
					    }catch(IOException e){
					      System.out.println(e);
					    }

					    //write to file
						try {
				            FileWriter fw = new FileWriter(tdfile, true);
				            fw.write("\n" +
				            		"import dog1 from './picts/dog1.jpg';\n" +
				            		"import dog2 from './picts/dog2.jpg';\n" +
				            		"import dog3 from './picts/dog3.jpg';\n" +
				            		"import dog4 from './picts/dog4.jpg';\n" +
				            		"import dog5 from './picts/dog5.jpg';\n" +
				            		"import dog6 from './picts/dog6.jpg';\n" +
				            		"import dog7 from './picts/dog7.jpg';\n" +
				            		"import dog8 from './picts/dog8.jpg';\n" +
				            		"import dog9 from './picts/dog9.jpg';\n" +
				            		"import dog10 from './picts/dog10.jpg';\n" +
				            		"\n" +
				            		"const tileData = [\n" +
				            		"    {\n" +
				            		"      img: dog1,\n" +
				            		"      title: 'お顔',\n" +
				            		"      author: 'yama',\n" +
				            		"      cols: 2,\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog2,\n" +
				            		"      title: '眠い',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog3,\n" +
				            		"      title: '恥ずかしい',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog4,\n" +
				            		"      title: '細目',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog5,\n" +
				            		"      title: 'はまり',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog6,\n" +
				            		"      title: 'お顔',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog7,\n" +
				            		"      title: '眠い',\n" +
				            		"      cols: 2,\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog8,\n" +
				            		"      title: '恥ずかしい',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog9,\n" +
				            		"      title: '細目',\n" +
				            		"    },\n" +
				            		"    {\n" +
				            		"      img: dog10,\n" +
				            		"      title: 'はまり',\n" +
				            		"    },\n" +
				            		"];\n" +
				            		"\n" +
				            		"export default tileData;\n");
				            fw.close();
				        } catch (IOException ex) {
				            ex.printStackTrace();
						}

					}else{
						out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1));
						//out_sch = func_read((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).fnc;
					}
				}
				else if( ((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("sqlfunc") ){
					String sqlfunc = new String();
					//				Log.info((ExtList)tfe_tree.get(1));
					sqlfunc = getText( (ExtList)tfe_tree.get(1), Start_Parse.ruleNames );
					builder = new String();
					sqlfunc_flag = true;
					Attribute func = makeAttribute(sqlfunc);
					sqlfunc_flag = false;
					out_sch = func;
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
					Attribute arithmetics = makeAttribute(att);
					out_sch = arithmetics;
				}
				else{

				}
			}
			if( !(((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ) instanceof ExtList) ){
				String deco = ((ExtList)tfe_tree.get(1)).get( ((ExtList)tfe_tree.get(1)).size() - 1 ).toString();
//				System.out.println("tfe_tree_deco1:::"+tfe_tree);
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
			if( ((ExtList)tfe_tree.get(1)).size() == 1 )
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			else
				out_sch = connector_main((ExtList)tfe_tree.get(1), 2);
		}else if(tfe_tree.get(0).toString().equals("d_exp")){
			if( ((ExtList)tfe_tree.get(1)).size() == 1 ){
				out_sch = read_attribute( (ExtList)((ExtList)tfe_tree.get(1)).get(0) );
			}else
				out_sch = connector_main((ExtList)tfe_tree.get(1), 3);
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
			con.setTFE((ITFE) (atts.get(i)));
		}
		return con;

	}

	private static Grouper grouper(ExtList operand){
		String iterator = new String();
		int dim = 0;
		TFE operand1 = read_attribute((ExtList)operand.get(1));

		if(operand.get(operand.size() - 1).toString().equals("%")){
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

		if(VRManager.vrflag){///vrの時 複合反復で使う
			/////for VR  column->row_x, row->vr_y
			if(iterators.get(0).equals(",")){
				deco = "vr_x=";
				iterators.remove(0);
				deco = deco + iterators.get(0);
				iterators.remove(0);
				if(iterators.get(0).equals("!")){//xy
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_y=-1";////////,5!みたいな方 -1って印つける
					}else{
						deco = deco + ",vr_y=" + iterators.get(0);//,5!3%みたいな方
						iterators.remove(0);
					}
				}else if(iterators.get(0).equals("%")){//xz
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_z=-1";
					}else{
						deco = deco + ",vr_z=" + iterators.get(0);
						iterators.remove(0);
					}
//					iterators.remove(0);
//					deco = deco + ", vr_z=1";
				}
			}else if(iterators.get(0).equals("!")){
				deco = "vr_y=";
				iterators.remove(0);
				deco = deco + iterators.get(0);
				iterators.remove(0);
				if(iterators.get(0).equals(",")){//yx
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_x=-1";//////////////
					}else{
						deco = deco + ",vr_x=" + iterators.get(0);
						iterators.remove(0);
					}
				}else if(iterators.get(0).equals("%")){//yz
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_z=-1";
					}else{
						deco = deco + ",vr_z=" + iterators.get(0);
						iterators.remove(0);
					}
//					iterators.remove(0);
//					deco = deco + ", vr_z=1";
				}

			}else if(iterators.get(0).equals("%")){
				deco = "vr_z=";
				iterators.remove(0);
				deco = deco + iterators.get(0);
				iterators.remove(0);
				if(iterators.get(0).equals("!")){//zy
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_y=-1";
					}else{
						deco = deco + ",vr_y=" + iterators.get(0);
						iterators.remove(0);
					}
				}else if(iterators.get(0).equals(",")){//zx
					iterators.remove(0);
					if(iterators.isEmpty()){
						deco = deco + ", vr_x=-1";
					}else{
						deco = deco + ",vr_x=" + iterators.get(0);
						iterators.remove(0);
					}
//					iterators.remove(0);
//					deco = deco + ", vr_y=1";
				}
			}
		}else{

			if(iterators.get(0).equals(",")){
				deco = "column=";
				iterators.remove(0);
				deco = deco + iterators.get(0);
				iterators.remove(0);
				if(iterators.get(0).equals("!")){
					iterators.remove(0);
					if(iterators.isEmpty()){
					}else{
						deco = deco + ",row=" + iterators.get(0);
						iterators.remove(0);
					}
				}else if(iterators.get(0).equals("%")){
					iterators.remove(0);
					deco = deco + ", row=1";
				}else if(iterators.get(0).equals(",")){//for infinite scroll
					deco = "infinite-scroll" + deco.substring(deco.indexOf("="));
					deco = deco + ", dynamic";
				}
			}else if(iterators.get(0).equals("!")){
				deco = "row=";
				iterators.remove(0);
				deco = deco + iterators.get(0);
				iterators.remove(0);
				if(iterators.get(0).equals(",")){
					iterators.remove(0);
					if(iterators.isEmpty()){
					}else{
						deco = deco + ",column=" + iterators.get(0);
						iterators.remove(0);
					}
				}else if(iterators.get(0).equals("%")){
					iterators.remove(0);
					deco = deco + ", column=1";
				}else if(iterators.get(0).equals("!")){//for infinite scroll
					deco = "infinite-scroll" + deco.substring(deco.indexOf("="));
					deco = deco + "dynamic";
				}

			}
		}
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
		if(dim == 3){
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
		if(dim == 3){
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

		Log.out("[func*TFE]=" + read_tfe.makele0());
		if (read_tfe instanceof Connector) {
			//		if(read_tfe instanceof Connector && ((Connector) read_tfe).getDimension() == 1){
			for(TFE tfe: ((Connector)read_tfe).tfes){
				fnc.addArg(makeFuncArg(tfe));
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
		Log.out("argsaregs: " + arg);

		if (arg instanceof Attribute) {
			out_fa = new FuncArg(((Attribute) arg).getKey(), arg);
		} else {
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
//		System.out.println("decoList:::"+decoList);
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
							builder += " ";
						}
					}
				}else {
					getText((ExtList)tree.get(i), ruleNames);
				}
			}
		}
		else if(tree.size() == 1 && (tree.get(0) instanceof String)){
			String str = new String();
			if(tree.get(0).toString().startsWith("\"") && tree.get(0).toString().endsWith("\"")){
				str = "\'" + tree.get(0).toString().substring(1, tree.get(0).toString().length()-1).replaceAll("'", "''") + "\'";
			}
			else{
				str = tree.get(0).toString();
			}
			builder += str;
			builder += " " ;
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
