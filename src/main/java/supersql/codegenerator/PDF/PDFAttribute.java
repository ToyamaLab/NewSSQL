package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFAttribute extends Attribute implements PDFTFE {

	private PDFEnv pdf_env;
	private PDFValue value;
	private String data;
	private float data_width;
	private float data_height;
	private float box_width;
	private float box_height;
	private float padding_H;
	private float padding_V;
	private float fontsize;
	private String fontstyle;

	
	private TFE newLE;
	private boolean change = false;
	private boolean fontsizeDECO = false;
	private boolean widthDECO = false;
	private float tuneWidth = 0;
	private float tmpTuneWidth;
	private float tuneFontsize = 0;
	private float tmpTuneFontsize;
	
	
	public PDFAttribute(Manager manager, PDFEnv penv) {
		super();
		this.pdf_env = penv;
		
		new PDFModifier();
	}


	@Override
	public String work(ExtList data_info) {
		
//		int level = pdf_env.level;
		
		padding_H = pdf_env.padding_H;
		padding_V = pdf_env.padding_V;
		fontsize = pdf_env.DefaultFontSize;
		
		this.value = new PDFValue("Att");
		

		data = this.getStr(data_info);
		System.out.println("[PDFAtt]data = "+data);
		
		
		setDecoration1();
		
		if(tuneFontsize != 0)
			fontsize = tuneFontsize;
		
		//-------------------------------------------------------//
		//data_width = modifier.stringwidth(data, pdf_env);
		data_width = pdf_env.stringwidth(data, fontsize);
		data_height = fontsize;
		
		if(data_width - (int)data_width > 0.9)
			data_width = (int)data_width + 1;
		
		box_width = data_width + padding_H * 2;
		box_height = data_height + padding_V * 2;
		//-------------------------------------------------------//
		
		fontstyle = pdf_env.DefaultFontStyle;
		setDecoration2();
		
		if(tuneWidth != 0)
			box_width = tuneWidth;
		
		if(box_width - padding_H * 2 < data_width){
			data_width = box_width - padding_H * 2;
			data_height = pdf_env.textflow_blind(data, fontsize, fontstyle, data_width);
			box_height = data_height + padding_V * 2;
		}
		
		System.out.println("*************"+data_width+" "+box_width);
		
		value.data = data;
		value.data_width = data_width;
		value.data_height = data_height;
		value.box_width = box_width;
		value.box_height = box_height;
		value.padding = padding_H;
		value.fontsize = fontsize;
		value.fontstyle = fontstyle;
		
		value.originalWidth = box_width;
		value.originalHeight = box_height;
		

		setDecoration3();
			
		
		//modifier.set_modifier2(data, data_width, pdf_env, value);
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;
		return null;
		

	}
	
	public void setDecoration1(){
		//;��
		if(decos.containsKey("padding")){
			padding_H = Float.parseFloat(decos.get("padding").toString());
			padding_V = Float.parseFloat(decos.get("padding").toString());
		}
		
		if(decos.containsKey("font-size")){
			fontsize = Float.parseFloat(decos.get("font-size").toString());
			fontsizeDECO = true;
		}
		else if(decos.containsKey("font size")){
			fontsize = Float.parseFloat(decos.get("font size").toString());
			fontsizeDECO = true;
		}
		else if(decos.containsKey("size")){
			fontsize = Float.parseFloat(decos.get("size").toString());
			fontsizeDECO = true;
		}
		else if(decos.containsKey("fontsize")){
			fontsize = Float.parseFloat(decos.get("fontsize").toString());
			fontsizeDECO = true;
		}
		
		if(decos.containsKey("style"))
			fontstyle = decos.get("style").toString();
		else if(decos.containsKey("font-style"))
			fontstyle = decos.get("font-style").toString();
		else if(decos.containsKey("font style"))
			fontstyle = decos.get("font style").toString();
		else if(decos.containsKey("fontstyle"))
			fontstyle = decos.get("fontstyle").toString();
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
		
		if(decos.containsKey("color"))
			value.fontcolor = decos.get("color").toString();
		else if(decos.containsKey("font-color"))
			value.fontcolor = decos.get("font-color").toString();
		else if(decos.containsKey("font color"))
			value.fontcolor = decos.get("font color").toString();
		else if(decos.containsKey("fontcolor"))
			value.fontcolor = decos.get("fontcolor").toString();
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
	
	
	//Attribute�Ǥ��ä˰�̣�Ϥʤ�
	public void restoreFOLD(PDFValue check){
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		

		if(!fontsizeDECO){
			int charNum = box.data.length();
			float DperChar = Dexcess / charNum;
			float newFontsize = box.fontsize - DperChar;
			if( newFontsize > box.fontsize - (pdf_env.DefaultFontSize - pdf_env.minFontsize) ){
			//if(newFontsize > pdf_env.minFontsize){
				
	//			decos.put("fontsize", Float.toString(newFontsize));
				tmpTuneFontsize = tuneFontsize;
				tuneFontsize = newFontsize;
				
				System.out.println("fontsize change");
				flex = true;
				pdf_env.cutWidth = Dexcess;
			}
		}
	
		//else if(!widthDECO){
		if(!widthDECO && !flex){
		//if(!widthDECO){
			float newWidth = box.box_width - Dexcess;
			if(newWidth > 0){
				

				
	//			decos.put("width", Float.toString(newWidth));
				tmpTuneWidth = tuneWidth;
				tuneWidth = newWidth;
		
				System.out.println("width change");
				//change = true;
				flex = true;
				pdf_env.cutWidth = Dexcess;
			}
		}
		
		return flex;
	}
	
	
	public boolean optimizeH(float Dexcess, PDFValue box){
		boolean flex = false;
		
		return flex;
	}
	
	
	public void redoChange(){
		tuneWidth = tmpTuneWidth;
		tuneFontsize = tmpTuneFontsize;
	}
	
	
	public TFE getNewChild(){
		return newLE;
	}
	
	
	public boolean changeORnot(){
		return change;
	}
	
	
}