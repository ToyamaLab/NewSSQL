package supersql.codegenerator.VR;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import supersql.codegenerator.Function;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRFunction extends Function {



	//	public static Element color;
	private final int COLOR = 0;
	private final int GRADIENTCOLOR = 1;
//	private final int COLORHSV = 2;
	private final int PULSE = 2;
	private final int HOP = 3;
	private final int ROTATE = 4;
	private final int LOCATION = 5;
	private final int SCENE = 6;
	private final int ASCENE = 7;
	private final int IMAGE = 8;
	private final int POSITION = 9;
	private final int MOVE = 10;
	public static TFE att;
	protected static String updateFile;
	private static Element[] options = new Element[11];
	public static java.util.List<Element> filterset = new ArrayList<>();
	static Boolean argFlag = false;
	static Float[] oldPosition = {0f,0f,0f};
	static Element passedfilter = null;
	
	//added by li 20210601
	public static boolean VRFunctionFlag = false;
	
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

	private VREnv vrEnv;
	private VREnv vrEnv2;

	public VRFunction() {

	}

	public VRFunction(Manager manager, VREnv henv, VREnv henv2) {
		super();
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	protected void Func_null() {
		return;
	}

	protected String className() { // added 20130703
		if (decos.containsKey("class"))
			return " class=\"" + decos.getStr("class") + "\" ";
		return "";
	}

	// Function��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		//2017/09/21 tatsu AddFunction
		this.setDataList(data_info);
		String FuncName = this.getFuncName().toLowerCase();
		
		switch (FuncName) {
		case "testconcat":
			try {
				Log.info("Func test100: " + getArg(0).getStr());
			} catch (Exception e) {
				// TODO: handle exception
			}
		case "asset":
			try{
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
					//Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element asset = vrEnv.xml.createElement("Asset");
					asset.setAttribute("name", getArg(0).getStr());
					asset.setAttribute("size", getArg(1).getStr());
					if(decos.containsKey("target")){
						asset.setAttribute("target", decos.getStr("target"));
					}
					addOptions(asset);
					//					n2.appendChild(asset);
					vrEnv.currentNode.appendChild(asset);
				} else {
					Element asset = vrEnv.xml.createElement("Asset");
					//					Element n2 = vrEnv.xml.createElement("n2");  //n2エレメントの作成
					//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					/*location Element start
					 * after if(x,y,zがあるかないか)*/
					asset.setAttribute("name", getArg(0).getStr());
					asset.setAttribute("size", getArg(1).getStr());
					
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						asset.setAttribute("asize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						asset.setAttribute("asize_name", getArg(sizeArg()-1).toString());
					} 
					
					asset.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
					asset.setAttribute("filter", getArg(1).getTFE().decos.getStr("filter"));

					if(decos.containsKey("target")){
						asset.setAttribute("target", decos.getStr("target"));
					}
					addOptions(asset);
					if (filterset.size() > 0){
						addFilterSet(asset);
					}
					//					n2.appendChild(asset);
					//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
					vrEnv.currentNode.appendChild(asset);
				}
				VRAttribute.elearraySeq++;

			} catch (Exception e) {
				// TODO: handle exception
				for (int i = 0; i < sizeArg(); i++) {
					getArg(i).workAtt();
				}

			}
			break;

		case "object":
			switch (getArg(0).getStr().toLowerCase()) {
			case "plane":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
					//					Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element plane = vrEnv.xml.createElement("plane");
					plane.setAttribute("size", getArg(1).getStr());
					addOptions(plane);
					vrEnv.currentNode.appendChild(plane);
				} else {
					//					Element n2 = vrEnv.xml.createElement("n2");  //n2エレメントの作成
					//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element plane = vrEnv.xml.createElement("plane");
					plane.setAttribute("size", getArg(1).getStr());
					plane.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
					plane.setAttribute("filter", getArg(1).getTFE().decos.getStr("filter"));
					
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						plane.setAttribute("csize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						plane.setAttribute("csize_name", getArg(sizeArg()-1).toString());
					}
					addOptions(plane);
					if (filterset.size() > 0){
						addFilterSet(plane);
					}
					vrEnv.currentNode.appendChild(plane);
					//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;
				
			case "cube":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
					//					Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element cube = vrEnv.xml.createElement("cube");
					cube.setAttribute("size", getArg(1).getStr());
					addOptions(cube);
					vrEnv.currentNode.appendChild(cube);
				} else {
					//					Element n2 = vrEnv.xml.createElement("n2");  //n2エレメントの作成
					//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element cube = vrEnv.xml.createElement("cube");
					/*location Element start
					 * konoato if(x,y,zがあるかないか)*/
					cube.setAttribute("size", getArg(1).getStr());
					cube.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
					cube.setAttribute("filter", getArg(1).getTFE().decos.getStr("filter"));
					
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						cube.setAttribute("csize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						cube.setAttribute("csize_name", getArg(sizeArg()-1).toString());
					}
					addOptions(cube);
					if (filterset.size() > 0){
						addFilterSet(cube);
					}
					vrEnv.currentNode.appendChild(cube);
					//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;

			case "torus":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
//					Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element torus = vrEnv.xml.createElement("torus");
					torus.setAttribute("rOut", getArg(1).getStr()); //r1が大半形
					torus.setAttribute("rIn", getArg(2).getStr()); //r2が小半径
					addOptions(torus);
					vrEnv.currentNode.appendChild(torus);
				} else {
//					Element n2 = vrEnv.xml.createElement("n2");
//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element torus = vrEnv.xml.createElement("torus");
					torus.setAttribute("rOut", getArg(1).getStr());
					torus.setAttribute("rIn", getArg(2).getStr());
					
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						torus.setAttribute("rInsize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						torus.setAttribute("rInsize_name", getArg(sizeArg()-1).toString());
					}
					
					if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
						torus.setAttribute("rOutsize_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
					} else {
						torus.setAttribute("rOutsize_name", getArg(sizeArg()-2).toString());
					}
					
					torus.setAttribute("rOut_name", getArg(1).getTFE().decos.getStr("filtername"));
					torus.setAttribute("rIn_name", getArg(1).getTFE().decos.getStr("filtername"));
//					n2.appendChild(torus);
					addOptions(torus);
					if (filterset.size() > 0){
						addFilterSet(torus);
					}
					vrEnv.currentNode.appendChild(torus);
//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;

			case "pyramid":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
//					Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element pyramid = vrEnv.xml.createElement("pyramid");
					pyramid.setAttribute("size", getArg(1).getStr());
					pyramid.setAttribute("height", getArg(2).getStr());
					addOptions(pyramid);
					vrEnv.currentNode.appendChild(pyramid);
//					n2.appendChild(pyramid);
				} else {
//					Element n2 = vrEnv.xml.createElement("n2");
//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element pyramid = vrEnv.xml.createElement("pyramid");
					pyramid.setAttribute("size", getArg(1).getStr());
					pyramid.setAttribute("height", getArg(2).getStr());
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						pyramid.setAttribute("height_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						pyramid.setAttribute("height_name", getArg(sizeArg()-1).toString());
					}
					
					if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
						pyramid.setAttribute("size_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
					} else {
						pyramid.setAttribute("size_name", getArg(sizeArg()-2).toString());
					}
					pyramid.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
					pyramid.setAttribute("height_name", getArg(2).getTFE().decos.getStr("filtername"));
//					n2.appendChild(pyramid);
					addOptions(pyramid);
					if (filterset.size() > 0){
						addFilterSet(pyramid);
					}
					vrEnv.currentNode.appendChild(pyramid);
//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;
				//object内に装飾はしない
			case "cuboid":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
//					Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element cuboid = vrEnv.xml.createElement("cuboid");
					cuboid.setAttribute("l_size", getArg(1).getStr());
					cuboid.setAttribute("w_size", getArg(2).getStr());
					cuboid.setAttribute("d_size", getArg(3).getStr());
					addOptions(cuboid);
					vrEnv.currentNode.appendChild(cuboid);
//					n2.appendChild(cuboid);
				} else {
//					Element n2 = vrEnv.xml.createElement("n2");
//					n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element cuboid = vrEnv.xml.createElement("cuboid");
					cuboid.setAttribute("l_size", getArg(1).getStr());
					cuboid.setAttribute("w_size", getArg(2).getStr());
					cuboid.setAttribute("d_size", getArg(3).getStr());
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						cuboid.setAttribute("dsize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						cuboid.setAttribute("dsize_name", getArg(sizeArg()-1).toString());
					}
					
					if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
						cuboid.setAttribute("wsize_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
					} else {
						cuboid.setAttribute("wsize_name", getArg(sizeArg()-2).toString());
					}
					
					if (getArg(sizeArg()-3).getTFE().decos.containsKey("name")){
						cuboid.setAttribute("lsize_name", getArg(sizeArg()-3).getTFE().decos.getStr("name"));
					} else {
						cuboid.setAttribute("lsize_name", getArg(sizeArg()-3).toString());
					}
					cuboid.setAttribute("l_size_name", getArg(1).getTFE().decos.getStr("filtername"));
					cuboid.setAttribute("w_size_name", getArg(2).getTFE().decos.getStr("filtername"));
					cuboid.setAttribute("d_size_name", getArg(3).getTFE().decos.getStr("filtername"));
//					n2.appendChild(cuboid);
					addOptions(cuboid);
					if (filterset.size() > 0){
						addFilterSet(cuboid);
					}
					vrEnv.currentNode.appendChild(cuboid);
//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;

			case "sphere":
				if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
					//Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
					Element sphere = vrEnv.xml.createElement("sphere");
					sphere.setAttribute("size", getArg(1).getStr());
					//sphere.setAttribute("size_name", attribute)
					addOptions(sphere);
					vrEnv.currentNode.appendChild(sphere);
				} else {
					//Element n2 = vrEnv.xml.createElement("n2");
					//n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
					Element sphere = vrEnv.xml.createElement("sphere");
					sphere.setAttribute("size", getArg(1).getStr());
					if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
						sphere.setAttribute("spsize_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
					} else {
						sphere.setAttribute("spsize_name", getArg(sizeArg()-1).toString());
					}
					sphere.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
					sphere.setAttribute("filter", getArg(1).getTFE().decos.getStr("filter"));
					addOptions(sphere);
					if (filterset.size() > 0){
						addFilterSet(sphere);
					}
					vrEnv.currentNode.appendChild(sphere);
					//					VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
				}
				VRAttribute.elearraySeq++;
				break;

			case "text":
				Element text = vrEnv.xml.createElement("text");
				for(int i = 0; i < sizeArg(); i++){
					Log.out("break: " + (getArg(1).tfe));
					Log.out("In text Arg(" + i + ") " + (getArg(i).tfe).countconnectitem());
				}
				/*
				Connector tmp = ((Connector)getArg(1).tfe);
				Log.out(tmp.tfes.get(0));
				Log.out(tmp.tfes.get(1));
				Connector tmp2 = ((Connector)tmp.tfes.get(0));
				Log.out(tmp2.tfes.get(0));
				Connector tmp3 = ((Connector)tmp2.tfes.get(0));
				Log.out(tmp3.tfes.get(0));
				Log.out(tmp3.tfes.get(1));
				*/
				//String contents = tmp3.tfes.get(0).toString() + tmp3.tfes.get(1).toString();
				//String t_size = tmp.tfes.get(1).toString();
				
				String contents = getArg(1).getStr();
				String t_size = getArg(2).getStr();
				//String t_size = getArg(sizeArg()-1).getStr();		
				//String t_size = "50";
				text.setAttribute("contents",contents);
				text.setAttribute("size", t_size);
				text.setAttribute("size_name", getArg(1).getTFE().decos.getStr("filtername"));
				if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
					text.setAttribute("size_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
				} else {
					text.setAttribute("size_name", getArg(sizeArg()-1).toString());
				}
				if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
					text.setAttribute("contents_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
				} else {
					text.setAttribute("contents_name", getArg(sizeArg()-2).toString());
				}
				addOptions(text);
				if (filterset.size() > 0){
					addFilterSet(text);
				}
				vrEnv.currentNode.appendChild(text);
				break;

			case "obj":
				for (int i = 0; i < this.sizeArg(); i++)
					getArg(i).workAtt();
				break;

			default:
				break;

			}
			break;
			//object内のswitch文の終わり	


		case "location":
			Element oldLocate = options[LOCATION];    //color(color()) block
			String lX = getArg(sizeArg()-3).getStr();
			String lY = getArg(sizeArg()-2).getStr();
			String lZ = getArg(sizeArg()-1).getStr();
			Element location = vrEnv.xml.createElement("location");
			location.setAttribute("x", lX);
			location.setAttribute("x_name", getArg(1).getTFE().decos.getStr("filtername"));
			location.setAttribute("y", lY);
			location.setAttribute("y_name", getArg(2).getTFE().decos.getStr("filtername"));
			location.setAttribute("z", lZ);
			location.setAttribute("z_name", getArg(3).getTFE().decos.getStr("filtername"));
			options[LOCATION] = location;
			for (int i = 0; i < sizeArg()-3; i++) {
				getArg(i).workAtt();
			}
			options[LOCATION] = oldLocate;
			break;

		case "color":
			Element oldColor = options[COLOR];    //color(color()) block
			String colorname; 
			colorname = getArg(sizeArg()-1).getStr();
			Element color = vrEnv.xml.createElement("color");
			color.setTextContent(colorname);
			options[COLOR] = color;	
			for (int i = 0; i < sizeArg()-1; i++) {
				getArg(i).workAtt();
			}
			options[COLOR] = oldColor;
			break;
			
		case "color_gradient":
			Element oldGradientColor = options[GRADIENTCOLOR];
			float min_value;
			float max_value;
			String min_color;
			String max_color;
			float value;
			String result;
			min_color = getArg(sizeArg()-5).getStr();
			min_value = Float.parseFloat(getArg(sizeArg()-4).getStr());
			max_color = getArg(sizeArg()-3).getStr();
			max_value = Float.parseFloat(getArg(sizeArg()-2).getStr());
			value = Float.parseFloat(getArg(sizeArg()-1).getStr());
			value = (value - min_value) / (max_value - min_value);
			
			if (value <= 0) {
				value = 0;
			}
			result = value + "";
			Element gradientColor = vrEnv.xml.createElement("gradient");
			gradientColor.setAttribute("min_c", min_color);
			gradientColor.setAttribute("max_c", max_color);
			gradientColor.setAttribute("value", getArg(sizeArg()-1).getStr());
			
			if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
				gradientColor.setAttribute("gradient_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
			} else {
				gradientColor.setAttribute("gradient_name", getArg(sizeArg()-1).toString());
			}
			gradientColor.setAttribute("filter", getArg(sizeArg()-1).getTFE().decos.getStr("filter"));
			gradientColor.setTextContent(result);
			options[GRADIENTCOLOR] = gradientColor;	
			for (int i = 0; i < sizeArg()-5; i++) {
				getArg(i).workAtt();
			}
			options[GRADIENTCOLOR] = oldGradientColor;
			
			break;

			//		case "color_RGBA":
			//			Element oldColorRgba = options[COLORRGBA];    //color(color()) block
			//			
			//			colorr = getArg(sizeArg()-3).getStr();
			//			Element colorrgba = vrEnv.xml.createElement("color");
			//			color.setTextContent(colorname);
			//			options[COLOR] = color;	
			//			for (int i = 0; i < sizeArg()-1; i++) {
			//				getArg(i).workAtt();
			//			}
			//			options[COLOR] = oldColor;
			//			break;
			//			
			//		case "color_HSV":
			//			Element oldColor = options[COLOR];    //color(color()) block
			//			String colorname; 
			//			colorname = getArg(sizeArg()-1).getStr();
			//			Element color = vrEnv.xml.createElement("color");
			//			color.setTextContent(colorname);
			//			options[COLOR] = color;	
			//			for (int i = 0; i < sizeArg()-1; i++) {
			//				getArg(i).workAtt();
			//			}
			//			options[COLOR] = oldColor;
			//			break;

		case "pulse":
			Element oldpulse = options[PULSE];
			String pScale = getArg(sizeArg()-2).getStr();
			String pSpeed = getArg(sizeArg()-1).getStr();
			Element pulse = vrEnv.xml.createElement("pulse");
			pulse.setAttribute("speed",pSpeed);
			pulse.setAttribute("scale", pScale);
			pulse.setAttribute("sp_name", getArg(2).getTFE().decos.getStr("filtername"));
			pulse.setAttribute("sc_name", getArg(1).getTFE().decos.getStr("filtername"));
			if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
				pulse.setAttribute("speed_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
			} else {
				pulse.setAttribute("speed_name", getArg(sizeArg()-1).toString());
			}
			if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
				pulse.setAttribute("scale_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
			} else {
				pulse.setAttribute("scale_name", getArg(sizeArg()-2).toString());
			}
			options[PULSE] = pulse;
			for (int i = 0; i < sizeArg()-2; i++) {
				getArg(i).workAtt();
			}
			options[PULSE] = oldpulse;

			break;

		case "hop":			
			Element oldhop = options[HOP];
			String hSpeed = getArg(sizeArg()-3).getStr();
			String hTop = getArg(sizeArg()-2).getStr();
			String hAxis = getArg(sizeArg()-1).getStr();
			Element hop = vrEnv.xml.createElement("hop");
			hop.setAttribute("speed", hSpeed);
			hop.setAttribute("top", hTop);
			hop.setAttribute("axis", hAxis);
			
			hop.setAttribute("s_name", getArg(sizeArg()-3).getTFE().decos.getStr("filtername"));
			hop.setAttribute("t_name", getArg(sizeArg()-2).getTFE().decos.getStr("filtername"));
			hop.setAttribute("filter", getArg(sizeArg()-2).getTFE().decos.getStr("filter"));
			if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
				hop.setAttribute("speed_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
			} else {
				hop.setAttribute("speed_name", getArg(sizeArg()-1).toString());
			}
			if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
				hop.setAttribute("top_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
			} else {
				hop.setAttribute("top_name", getArg(sizeArg()-2).toString());
			}
			options[HOP] = hop;
			for (int i = 0; i < sizeArg()-3; i++) {
				getArg(i).workAtt();
			}
			options[HOP] = oldhop;

			break;

		case "rotate":
			Element oldrotate = options[ROTATE];
			String rX = getArg(sizeArg()-3).getStr();
			String rY = getArg(sizeArg()-2).getStr();
			String rZ = getArg(sizeArg()-1).getStr();
			Element rotate = vrEnv.xml.createElement("rotate");
			rotate.setAttribute("x", rX);
			rotate.setAttribute("y", rY);
			rotate.setAttribute("z", rZ);
			rotate.setAttribute("x_name", getArg(1).getTFE().decos.getStr("filtername"));
			rotate.setAttribute("y_name", getArg(2).getTFE().decos.getStr("filtername"));
			rotate.setAttribute("z_name", getArg(3).getTFE().decos.getStr("filtername"));
			if(decos.containsKey("target")){
				rotate.setAttribute("target", decos.getStr("target"));
			}
			if (getArg(sizeArg()-1).getTFE().decos.containsKey("name")){
				rotate.setAttribute("z_name", getArg(sizeArg()-1).getTFE().decos.getStr("name"));
			} else {
				rotate.setAttribute("z_name", getArg(sizeArg()-1).toString());
			}
			if (getArg(sizeArg()-2).getTFE().decos.containsKey("name")){
				rotate.setAttribute("y_name", getArg(sizeArg()-2).getTFE().decos.getStr("name"));
			} else {
				rotate.setAttribute("y_name", getArg(sizeArg()-2).toString());
			}
			if (getArg(sizeArg()-3).getTFE().decos.containsKey("name")){
				rotate.setAttribute("x_name", getArg(sizeArg()-3).getTFE().decos.getStr("name"));
			} else {
				rotate.setAttribute("x_name", getArg(sizeArg()-3).toString());
			}
			options[ROTATE] = rotate;
			for (int i = 0; i < sizeArg()-3; i++) {
				getArg(i).workAtt();
			}
			options[ROTATE] = oldrotate;
			break;
		
		case "scene":
			//scene(..., destination, ref) or scene(..., destination)
		try{
			if (sizeArg() == 3){
			//引数が3この場合にはlink関数のように扱う
			Element oldscene = options[SCENE];
			String destination = getArg(sizeArg()-2).getStr();
			if (!destination.contains("."))
			{
				destination = destination + ".xml";
			}
			else if (!destination.endsWith(".xml"))
			{
				int pLocate = destination.indexOf(".");
				destination = destination.substring(0, pLocate);
				destination = destination + ".xml";
			}
			String ref = getArg(sizeArg()-1).getStr();
			Element scene = vrEnv.xml.createElement("link");
			scene.setAttribute("destination", destination);
			scene.setAttribute("id", ref);
			options[SCENE] = scene;
			for (int i = 0; i < sizeArg()-2; i++) {
				getArg(i).workAtt();
			}
			options[SCENE] = oldscene;
			break;
			
			} else if (sizeArg() == 2){
				//引数が2個の場合にはanchor関数のように扱う
				Element oldascene = options[ASCENE];
				String adestination = getArg(sizeArg()-1).getStr();
				if (!adestination.contains("."))
				{
					adestination = adestination + ".xml";
				}
				else if (!adestination.endsWith(".xml"))
				{
					int pLocate = adestination.indexOf(".");
					adestination = adestination.substring(0, pLocate);
					adestination = adestination + ".xml";
				}
				Element ascene = vrEnv.xml.createElement("alink");
				ascene.setAttribute("destination", adestination);
				options[ASCENE] = ascene;
				for (int i = 0; i < sizeArg()-1; i++) {
					getArg(i).workAtt();
				}
				options[ASCENE] = oldascene;
				break;
				
			} else {
				if (argFlag.equals(false)){
					System.err.println("Warning: Scene function needs two or three argument");
					argFlag = true;
				}
				break;
			}	
			
		}catch(Exception e){
			System.err.println("Scene function needs two or three argument");
		}
			
		case "image":
			Element oldImage = options[IMAGE];
			String imageName = getArg(sizeArg()-1).getStr();
			Element image = vrEnv.xml.createElement("image");
			image.setAttribute("name", imageName);
			options[IMAGE] = image;
			for (int i = 0; i < sizeArg()-1; i++){
				getArg(i).workAtt();
			}
			options[IMAGE] = oldImage;
			break;
			
		case "move":
			//positionが前にあれば、positionの子ノードにする、moveが複数ある場合には、位置の足し算をする = optionsの個数は一つのまま
			//move(..., x, y, z)相対座標 , 複数持てる仕様
			Float oldX = oldPosition[0];
			Float oldY = oldPosition[1];
			Float oldZ = oldPosition[2];
			Element oldmove = options[MOVE];
			Node oldChildNode = null;
			Node childNode = null;
			Element move = vrEnv.xml.createElement("localposition");
			String rPositionX = getArg(sizeArg()-3).getStr();
			String rPositionY = getArg(sizeArg()-2).getStr();
			String rPositionZ = getArg(sizeArg()-1).getStr();
			Float rPx = Float.parseFloat(rPositionX);
			Float rPy = Float.parseFloat(rPositionY);
			Float rPz = Float.parseFloat(rPositionZ);
			
			if (options[POSITION] != null){
				//positionがある
				oldChildNode = options[POSITION].getFirstChild();
				childNode = options[POSITION].getFirstChild();
				if(!options[POSITION].hasChildNodes()){
					//positionありかつmoveなし
					move.setAttribute("x", String.valueOf(rPx));
					move.setAttribute("y", String.valueOf(rPy));
					move.setAttribute("z", String.valueOf(rPz));
					options[POSITION].appendChild(move);
					childNode = options[POSITION].getFirstChild();
					//最後にoldmoveになる
				} else {
					//positionありかつmoveあり
					Float childX = oldPosition[0];
					Float childY = oldPosition[1];
					Float childZ = oldPosition[2];
					rPx += childX;
					rPy += childY;
					rPz += childZ;
					move.setAttribute("x", String.valueOf(rPx));
					move.setAttribute("y", String.valueOf(rPy));
					move.setAttribute("z", String.valueOf(rPz));
					options[POSITION].replaceChild(move, oldChildNode);
					childNode = options[POSITION].getFirstChild();
				}
			} else {
				//positionがない時、moveはobjectの子になる
				if (options[MOVE] != null){
					Float childX = oldPosition[0];
					Float childY = oldPosition[1];
					Float childZ = oldPosition[2];
					rPx += childX;
					rPy += childY;
					rPz += childZ;
					move.setAttribute("x", String.valueOf(rPx));
					move.setAttribute("y", String.valueOf(rPy));
					move.setAttribute("z", String.valueOf(rPz));
					options[MOVE] = move;	
				} else {
					move.setAttribute("x", String.valueOf(rPx));
					move.setAttribute("y", String.valueOf(rPy));
					move.setAttribute("z", String.valueOf(rPz));
					options[MOVE] = move;
				}
			}
			
			oldPosition[0] = rPx;
			oldPosition[1] = rPy;
			oldPosition[2] = rPz;
			for (int i = 0; i < sizeArg()-3; i++){
				getArg(i).workAtt();
			}	
			oldPosition[0] = oldX;
			oldPosition[1] = oldY;
			oldPosition[2] = oldZ;
			if(options[POSITION] != null){
				if (oldChildNode == null){
					options[POSITION].removeChild(childNode);
				} else {
					options[POSITION].replaceChild(oldChildNode, childNode);
				}
			}
			options[MOVE] = oldmove;
			break;
			
		case "position":
			//position(..., x, y, z) 世界座標
			Element oldMoveOnPosition = options[MOVE];
			if (options[MOVE] != null){
				options[MOVE] = null;
			}
			Element oldposition = options[POSITION]; 
			Element position = vrEnv.xml.createElement("worldposition");
			String positionX = getArg(sizeArg()-3).getStr();
			String positionY = getArg(sizeArg()-2).getStr();
			String positionZ = getArg(sizeArg()-1).getStr();
			position.setAttribute("x", positionX);
			position.setAttribute("y", positionY);
			position.setAttribute("z", positionZ);
			options[POSITION] = position;
			for (int i = 0; i < sizeArg()-3; i++){
				getArg(i).workAtt();
			}	
			options[MOVE] = oldMoveOnPosition;
			options[POSITION] = oldposition;
			break;
				
		case "foreach":
			String fId = getArg(sizeArg()-1).getStr();
			Element foreach = vrEnv.xml.createElement("foreach");
			foreach.setAttribute("id", fId);
			vrEnv.currentNode = vrEnv.currentNode.appendChild(foreach);
			break;
			
		case "filter":
			//filter(..., t_target@{name="f_target"}, "f_pattern")   f_pattern: slider, checkbox, inputfield
			Element oldfilter = passedfilter; 
			Element filter = vrEnv.xml.createElement("filter");
			String f_target = null;
			String f_value = getArg(sizeArg()-2).getStr();
			if(getArg(sizeArg()-2).getTFE().decos.getStr("name") == null){
				f_target = getArg(sizeArg()-2).getTFE().toString();
			} else {
				f_target = getArg(sizeArg()-2).getTFE().decos.getStr("name");
			}
			String f_pattern = getArg(sizeArg()-1).getStr();
			filter.setAttribute("f_value", f_value);
			filter.setAttribute("f_target", f_target);
			filter.setAttribute("f_pattern", f_pattern);
			passedfilter = filter;
			filterset.add(filter);
			for (int i = 0; i < sizeArg()-2; i++){
				getArg(i).workAtt();
			}
			filterset.remove(filter);
			passedfilter = oldfilter;
			break;
		case "popup":
			
			break;
			
		default:
			break;


		}
		//No functions supported in VR yet
		Log.out("TFEId = " + VREnv.getClassID(this));
		//		htmlEnv.append_css_def_td(VREnv.getClassID(this), this.decos);
		return null;
	}

	private void addOptions(Element object){
		for (int i=0; i < options.length; i++){
			if (options[i]!= null) {
				object.appendChild(options[i].cloneNode(true));
			}
		}
	}
	
	private void addFilterSet(Element object){
		for (Element filter : filterset){
			object.appendChild(filter.cloneNode(true));
		}
	}
}
