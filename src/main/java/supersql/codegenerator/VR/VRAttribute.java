package supersql.codegenerator.VR;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.VR.VRCONCAT;
import supersql.codegenerator.VR.VREnv;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRAttribute extends Attribute {

	private VREnv vrEnv;
	private VREnv vrEnv2;

	public static String genre = "";
	public static ArrayList<Integer> exharray = new ArrayList<Integer>();///1ビルにつき、categoryの数だけ同じ数字が入る
	public static ArrayList<Integer> floorarray = new ArrayList<Integer>();
	public static ArrayList<String> genrearray2 = new ArrayList<String>();///カテゴリーごとのタイトルだす、Red,Whiteとか
	public static ArrayList<Integer> genrearray22 = new ArrayList<Integer>();//0,2,6ってgroupごとのカテゴリーの数を累積で入れていく
	public static int genrecount = 0;
	public static int gjoinflag = 0;
	public static int cjoinflag = 0;
	public static int groupcount = 0;
	public static int groupcount1 = 0;
	public static int grouptag = 0;
	public static ArrayList<String> cjoinarray = new ArrayList<String>();////博物館同士を結合させる時分岐に使う
	public static int gjudge = 0;
	public static int billnum = 0;
	public static int elearraySeq = 0;
	public static ArrayList<Element> elearrayXML = new ArrayList<Element>();

	public static int[] compx = new int[100];///複合反復子に使う
	public static int[] compy = new int[100];
	public static int[] compz = new int[100];
	public static int[] compflag = new int[100];//複合反復子で、一番最初にくるTFE
	public static int cgcount = 0;//comp group count
	public static boolean componexflag = false;///compx,flagに無駄に値を代入しないよう、１ビルに一回だけ
	public static boolean componeyflag = false;///compy,flagに無駄に値を代入しないよう、１ビルに一回だけ
	public static boolean componezflag = false;///compz,flagに無駄に値を代入しないよう、１ビルに一回だけ

	public static ArrayList<String> multiexhary = new ArrayList<>();////展示物を複数くっつけて並べる、グループごとにTFEを格納
	public static ArrayList<Integer> multiexhcount = new ArrayList<>();////展示物を複数くっつけて並べる時の展示物の数

	public static ArrayList<String> namearray = new ArrayList<>();////name属性
	public static ArrayList<String> idarray = new ArrayList<>();////id属性

	public static String atname = "";
	public static boolean nameflag = false;


	public VRAttribute(Manager manager, VREnv henv, VREnv henv2) {
		super();
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	public VRAttribute(Manager manager, VREnv henv, VREnv henv2, boolean b) {
		super(b);
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}
	
	// Attribute鐃緒申work鐃潤ソ鐃獣ワ申
	@Override
	public String work(ExtList data_info) {

		//Process the decorations attached to the attribute if the attribute is not a decoration itself
		if (vrEnv.decorationStartFlag.size() > 0 
				&& ((vrEnv.decorationStartFlag.get(0) || decos.size()>0) 
						&& !vrEnv.decorationEndFlag.get(0))) {
			for (String key : decos.keySet()) {
				String value = decos.get(key).toString();
				//if the decoration value is an attribute, register its name to decorationProperty to process it later
				if (!(value.startsWith("\"") && value.endsWith("\"")) 
						&& !(value.startsWith("\'") && value.endsWith("\'")) 
						&& !supersql.codegenerator.CodeGenerator.isNumber(value)
						) {
					vrEnv.decorationProperty.get(0).add(0, key);
				}
			}
		}

		//tbt acc 180806
		if(GlobalEnv.joinFlag){
			// if (Incremental.flag || Ehtml.flag) {
			VRCONCAT.joinClassID = VREnv.getClassID(this);
			VRCONCAT.joinDecos = this.decos;
					// htmlEnv.append_css_def_td(HTMLCONCAT.joinClassID, this.decos);
			if ((!Incremental.flag && !Ehtml.flag) || !Ehtml.isEhtml2()) ;
			else	vrEnv.append_css_def_td(VRCONCAT.joinClassID, this.decos);
			return this.getStr(data_info);
		}
		//if this attribute is a decoration
		if(vrEnv.decorationEndFlag.size() > 0 && vrEnv.decorationEndFlag.get(0)){
			//get the property name from decorationProperty
			String property = vrEnv.decorationProperty.get(0).get(0);
			//if the property is name, change the name of the last element entered
			if(property.equals("name")){
				for(int i = 0; i < elearrayXML.get(elearraySeq-1).getLastChild().getChildNodes().getLength(); i++){
					Node n = elearrayXML.get(elearraySeq-1).getLastChild().getChildNodes().item(i);
					if (n.getNodeName().equals("name")){
						n.setTextContent(this.getStr(data_info));
					}
				}
			}
		} else {
			String classname;
			if (this.decos.containsKey("class")) {
				classname = this.decos.getStr("class");
			} else {
				classname = VREnv.getClassID(this);
			}

			//		if (GlobalEnv.isOpt()) {
			//			work_opt(data_info);
			//		} else {

			if(vrEnv.gLevel <= 1){// kotani 16/10/04//タグのレベルが１(1個目のcategoryが０で、二個目のcategoryは１)だったら、ジャンルの名前持ってくる
				genre = this.getStr(data_info);// kotani 16/10/04
			}else{	
				idarray.add(data_info.toString());
				if(elearrayXML.size() > elearraySeq){ //Check if the elearray already contains something for this n2 grouper
					Element n2 = elearrayXML.get(elearraySeq);
					Element elem = vrEnv.xml.createElement("element");
					Element name = vrEnv.xml.createElement("name");
					name.setTextContent(this.getStr(data_info));
					elem.appendChild(name);
					Element id = vrEnv.xml.createElement("id");
					id.setTextContent(this.getStr(data_info));
					elem.appendChild(id);
					n2.appendChild(elem);
				} else { //if not add a new n2
					Element n2 = vrEnv.xml.createElement("n2");
					n2.setAttribute("seq", Integer.toString(elearraySeq));
					Element elem = vrEnv.xml.createElement("element");
					Element name = vrEnv.xml.createElement("name");
					name.setTextContent(this.getStr(data_info));
					elem.appendChild(name);
					Element id = vrEnv.xml.createElement("id");
					id.setTextContent(this.getStr(data_info));
					elem.appendChild(id);
					n2.appendChild(elem);
					elearrayXML.add(elearraySeq,n2);
				}
				elearraySeq++;
			}

			Log.out("TFEId = " + VREnv.getClassID(this));
			//		}
		}
		return null;
	}

	// optimizer
	//TODO: write a real optimizer?
	public void work_opt(ExtList data_info) {}

}
