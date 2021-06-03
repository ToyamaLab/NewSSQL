package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFC2 extends Connector implements PDFTFE {

	private PDFEnv pdf_env;
	
	private PDFValue value;
	
	private ExtList maxHeights;
	private float max_height;
	
	private TFE newLE;
	private boolean change = false;

	public PDFC2(Manager manager, PDFEnv pdf_env) {
		this.pdf_env = pdf_env;
		maxHeights = new ExtList();
	}

	@Override
	public String work(ExtList data_info) {
		float box_width = 0;
		float box_height = 0;
		float tmp_width = 0;
		
		System.out.println("");
		System.out.println("------- C2 -------");
//		System.out.println("[PDFC2:work]tfe_info = " + makele0());
		System.out.println("[PDFC2:work]data_info = " + data_info);

		System.out.println("++++ C2 ++++");
		value = new PDFValue("C2");
		
		PDFModifier modifier = new PDFModifier();

		int i;
		int x_default; //x_tmp
		//int lap = 0;
		

		x_default = pdf_env.x_back;
		//y_max = y_default;

//del

		setDataList(data_info);

		for (i = 0; i < tfeItems; i++) {
			ITFE tfe = (ITFE) tfes.get(i);

			if (tfe instanceof Attribute) {
				System.out.println("[PDFC2:work]tfe is Attribute");

				//modi if( ope_modi == false )
				modifier.clean2();
				//modi if( o_modi[i] instanceof AddedInfo )
				//modi modifier.get_modifier2(o_modi[i]);

				//pdf_env.level++;
//				pdf_env.level = pdf_env.level_max+1;
				
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}

				worknextItem();
				
				System.out.println("++++ C2 ++++");
				value.inList.add(((PDFTFE)tfe).getInstance());

				box_height += pdf_env.tmp_height;
				tmp_width = pdf_env.tmp_width;
				if (tmp_width > box_width) {
					box_width = tmp_width;
				}
//del

//////			vector_local.addElement(value);		////////////


				/////////////////////////////////
				//if( i+1 < data_info.size() )
				if (hasMoreItems())
					pdf_env.y_back++;

				//lap++;
			}

			else {
				System.out.println("[PDFC2:work]tfe is instance of Operator");

				//if( i == number-1 && writer.flag == true )
				//    writer.flag = true;
				//else
				//    writer.flag = false;

				//System.out.println("lap=" + lap);

				//////////////////////
				pdf_env.x_back = x_default;
				
				//pdf_env.level++;
				//pdf_env.level = level + tfeitems +1;
//				pdf_env.level = pdf_env.level_max+1;
				
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}
				
				worknextItem();
				
				System.out.println("++++ C2 ++++");
				//�ɲ�10.17 value�Υ��å�
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				box_height += pdf_env.tmp_height;
				tmp_width = pdf_env.tmp_width;
				if (tmp_width > box_width) {
					box_width = tmp_width;
				}

				////////////////////////////////
				//if( i+1 < data_info.size() )
				if (hasMoreItems())
					pdf_env.y_back++;

				//if(flag == true)
				//    writer.x_back = x_tmp;

			}

			
			checkMaxHeight(pdf_env.tmp_height, i);
			//if(maxHeight[i] < box_height)
			//	maxHeight[i] = box_height;
			//if(maxHeight[i] < pdf_env.tmp_height)
			//	maxHeight[i] = pdf_env.tmp_height;
					
		}
		///////////////////////////////////////////////////////////////////////////

		for(i=0; i<value.inList.size(); i++){
			PDFValue re_set = (PDFValue)value.inList.get(i);
			re_set.box_width = box_width;
		}
		
		value.box_width = box_width;
		value.box_height = box_height;
		
		value.originalWidth = box_width;		
		value.originalHeight = box_height;

		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;
		return null;
		
	}

	public void checkMaxHeight(float box_height, int i){
		if(i >= maxHeights.size())
			maxHeights.add("0");
		max_height = Float.parseFloat((String)maxHeights.get(i));
		if(max_height < box_height){
			max_height = box_height;
			maxHeights.set(i, Float.toString(max_height));
		}
	}
	
	public PDFValue getInstance(){
		System.out.println("++++ C2 ++++");
		return value;
	}
	
	
	public void setLabel(PDFValue result) {

		int i;
		PDFValue instance;
		
		int labelH = pdf_env.labelH;
		int labelV = pdf_env.labelV;
		String labelSuffixH = pdf_env.labelSuffixH;
		String labelSuffixV = pdf_env.labelSuffixV;
		
		//---------------------------------
		int labelO = pdf_env.labelO;


//pdf_env.labelH++;

		for (i = 0; i < tfeItems; i++) {
			ITFE tfe = (ITFE) tfes.get(i);

			pdf_env.labelV = pdf_env.labelmaxV + 1;			
			if(pdf_env.labelV > pdf_env.labelmaxV ){
				pdf_env.labelmaxV = pdf_env.labelV;
			}

			pdf_env.labelH = labelH;
			pdf_env.labelH++;
			if(pdf_env.labelH > pdf_env.labelmaxH ){
				pdf_env.labelmaxH = pdf_env.labelH;
			}
			
			//---------------------------------
			pdf_env.labelO = pdf_env.labelmaxO + 1;			
			if(pdf_env.labelO > pdf_env.labelmaxO ){
				pdf_env.labelmaxO = pdf_env.labelO;
			}
				
			
			instance = (PDFValue)result.inList.get(i);
			((PDFTFE)tfe).setLabel(instance);
		}
		
//pdf_env.labelH = labelH;

		if( pdf_env.labelSuffixH.equals("null") )
			result.labelH = Integer.toString(labelH);
		else
			result.labelH = Integer.toString(labelH) + labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelV = Integer.toString(labelV);
		else
			result.labelV = Integer.toString(labelV) + labelSuffixV;
		
		//---------------------------------
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelOH = Integer.toString(labelO);
		else
			result.labelOH = Integer.toString(labelO) + labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelOV = Integer.toString(labelO);
		else
			result.labelOV = Integer.toString(labelO) + labelSuffixV;
		
		
		if( !pdf_env.labelListH.contains(result.labelH) )
			pdf_env.labelListH.add(result.labelH);
		if( !pdf_env.labelListV.contains(result.labelV) )
			pdf_env.labelListV.add(result.labelV);
		
		//---------------------------------
		if( !pdf_env.labelListOH.contains(result.labelOH) )
			pdf_env.labelListOH.add(result.labelOH);
		if( !pdf_env.labelListOV.contains(result.labelOV) )
			pdf_env.labelListOV.add(result.labelOV);
	}
	
	
	public void restoreFOLD(PDFValue check){
		int local;
		
		for(local=0; local<tfeItems; local++){
			ITFE tfe = (ITFE)tfes.get(local);
			((PDFTFE)tfe).restoreFOLD(check);
		}
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		
		int local;
		
		float tmpDexcess;
		
		float maxCutWidth = 0;
	
		for (local=tfeItems-1; local>-1; local--) {
			ITFE tfe = (ITFE)tfes.get(local);
			PDFValue inBox = (PDFValue)box.inList.get(local);
			//tmpDexcess = box.box_width - /**/inBox.originalWidth/**/ + Dexcess;
			tmpDexcess = Dexcess - (box.box_width - /**/inBox.originalWidth/**/);
			//if(Dexcess >= tmpDexcess ){
			if(tmpDexcess > 0){
				flex = ((PDFTFE)tfe).optimizeW(tmpDexcess, inBox);
				if(!flex)
					break;
				else{
					if(maxCutWidth < pdf_env.cutWidth)
						maxCutWidth = pdf_env.cutWidth;
				}
			}
		}
		
		if(flex){
			for (local=tfeItems-1; local>-1; local--) {
				ITFE tfe = (ITFE)tfes.get(local);
				if( ((PDFTFE)tfe).changeORnot() )
					this.tfes.set(local, ((PDFTFE)tfe).getNewChild() );
			}
			//flex = true;
		}
		
		pdf_env.cutWidth = maxCutWidth;
		
		return flex;
	}
	
	
	public boolean optimizeH(float Dexcess, PDFValue box){
		boolean flex = false;
		
		return flex;
	}

	
	public TFE getNewChild(){
		return newLE;
	}
	
	
	public boolean changeORnot(){
		return change;
	}
	
	
	public void redoChange(){
		
	}
	
	
	@Override
	public String getSymbol() {
		return "C2";
	}

}