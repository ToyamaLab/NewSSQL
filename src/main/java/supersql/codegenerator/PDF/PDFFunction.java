package supersql.codegenerator.PDF;

import supersql.codegenerator.Function;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.common.GlobalEnv;
import supersql.extendclass.ExtList;

public class PDFFunction extends Function implements PDFTFE {

	private PDFEnv pdf_env;
	private PDFValue value;
	
	private float data_width;
	private float data_height;
	private float box_width;
	private float box_height;
	private float padding_H;
	private float padding_V;

	private TFE newLE;
	private boolean change = false;
	private boolean widthDECO = false;
	

	public PDFFunction(Manager manager, PDFEnv penv) {
		super();
		this.pdf_env = penv;
	}

	@Override
	public String work(ExtList data_info) {

		this.setDataList(data_info);
		//    	Log.out("FuncName= " + this.getFuncName());
		//    	Log.out("filename= " + this.getAtt("filename"));
		//    	Log.out("condition= " + this.getAtt("condition"));

		String FuncName = this.getFuncName();

		if(FuncName.equalsIgnoreCase("imagefile")){
			Func_imagefile();
		}
		return null;
	}


	private void Func_imagefile() {

		/*
		 * ImageFile function : (imagefile="path=")
		 */
		
		padding_H = pdf_env.linewidth*2;// /2f;//0;//pdf_env.padding_H;
		padding_V = pdf_env.linewidth*2;// /2f;//0;//pdf_env.padding_V;
		
		this.value = new PDFValue("Func");
		
		String path = this.getAtt("path", ".");
		if(!path.startsWith("/")) {
			String basedir = GlobalEnv.getBaseDir();
			if(basedir != null && basedir != "") {
				path = path + "/" + GlobalEnv.getBaseDir();
			}
		}
		//System.out.println("yyyyyyyyyyyyy "+GlobalEnv.getBaseDir());
		
		String data = this.getAtt("default");
		/* 
		StringTokenizer token = new StringTokenizer(data, ".", false);
		String type = "null";
		while(token.hasMoreTokens() ){
			type = token.nextToken();
		}
		type = type.toLowerCase();
		if(type.equals("jpg") || type.equals("jpeg") || type.equals("gif")){
			if(type.equals("jpg") )
				type = "jpeg";
			if( type.equals("gif") ){
				
			}
		}
		*/
		String type = "auto";
		String filename = path + "/" + data;
		
		setDecoration1();
		
		value.data = filename;
		System.out.println("filename = "+filename);
		int image_num = pdf_env.open_image_file(type, filename);
		value.image_num = image_num;

		
//		System.out.println("xxxxxxxxxxx "+box_width+" "+box_height);
//		if(image_num!=-1)
//			pdf_env.fit_image_blind(image_num, 0, 0, box_width, box_height);
////////////////
		
		data_width = pdf_env.get_value("imagewidth", image_num);
		data_height = pdf_env.get_value("imageheight", image_num);
		System.out.println("qqqqqqqqqq "+data_width+" "+data_height);
		box_width = data_width + padding_H * 2;
		box_height = data_height + padding_V * 2;
		
		setDecoration2();
		
			
		/* 
		if( (instance.data_width > instance.box_width) && (instance.data_height > instance.box_height) ){
			
			instance.data_width = instance.box_width - instance.padding*2f;
			instance.data_height = instance.box_height - instance.padding*2f;
		}
		else 
		*/
		if(data_width > box_width){
			float original_width = data_width;
			data_width = box_width - padding_H * 2;
			float scale = data_width / original_width;
			data_height = data_height * scale;
			box_height = data_height + padding_V * 2;
		}
		if(data_height > box_height){
			float original_height = data_height;
			data_height = box_height - padding_H * 2;
			float scale = data_height / original_height;
			data_width = data_width * scale;
			box_width = data_width + padding_H * 2;
		}
		
		
		value.data_width = data_width;
		value.data_height = data_height;
		value.box_width = box_width;
		value.box_height = box_height;
		value.padding = padding_H;
		
		value.originalWidth = box_width;		
		value.originalHeight = box_height;
		
		setDecoration3();
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;
	}
	

	public void setDecoration1(){
		if(decos.containsKey("padding")){
			padding_H = Float.parseFloat(decos.get("padding").toString());
			padding_V = Float.parseFloat(decos.get("padding").toString());
		}
	}
	
	public void setDecoration2(){
		if(decos.containsKey("width")){
			box_width = Float.parseFloat(decos.get("width").toString());
			widthDECO = true;
		}
		if(decos.containsKey("height"))
			box_height = Float.parseFloat(decos.get("height").toString());
	}
	
	public void setDecoration3(){
		if(decos.containsKey("align"))
			value.align = decos.get("align").toString();
		if(decos.containsKey("valign"))
			value.valign = decos.get("valign").toString();
		
		if(decos.containsKey("background-color"))
			value.bgcolor = decos.get("background-color").toString();
		else if(decos.containsKey("bgcolor"))
			value.bgcolor = decos.get("bgcolor").toString();
		
	}
	
	public PDFValue getInstance(){
		return this.value;
	}
	
	
	public void setLabel(PDFValue result) {
		
		int labelH = pdf_env.labelH;
		int labelV = pdf_env.labelV;
		
		int labelO = pdf_env.labelO;

		
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelH = Integer.toString(labelH);
		else
			result.labelH = Integer.toString(labelH) + pdf_env.labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelV = Integer.toString(labelV);
		else
			result.labelV = Integer.toString(labelV) + pdf_env.labelSuffixV;
		
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelOH = Integer.toString(labelO);
		else
			result.labelOH = Integer.toString(labelO) + pdf_env.labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelOV = Integer.toString(labelO);
		else
			result.labelOV = Integer.toString(labelO) + pdf_env.labelSuffixV;
		
		
		if( !pdf_env.labelListH.contains(result.labelH) )
			pdf_env.labelListH.add(result.labelH);
		if( !pdf_env.labelListV.contains(result.labelV) )
			pdf_env.labelListV.add(result.labelV);
		
		if( !pdf_env.labelListOH.contains(result.labelOH) )
			pdf_env.labelListOH.add(result.labelOH);
		if( !pdf_env.labelListOV.contains(result.labelOV) )
			pdf_env.labelListOV.add(result.labelOV);
		
	}
	
	public void restoreFOLD(PDFValue check){
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		
		if(!widthDECO){
			float newWidth = box.box_width - Dexcess;
			if(newWidth > 0){
				//box.width = newWidth;
				decos.put("width", Float.toString(newWidth));
				//change = true;
				flex = true;
			}
		}
		
		return flex;
	}
	
	
	public boolean optimizeH(float Dexcess, PDFValue box){
		boolean flex = false;
		
		return flex;
	}
	
	
	public TFE getNewChild(){
		return newLE;
	}
	
	
	public void redoChange(){
		
	}
	
	
	public boolean changeORnot(){
		return change;
	}
	
	
}