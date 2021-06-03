package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFC1 extends Connector implements PDFTFE {

	private Manager manager;

	private PDFEnv pdf_env;
	
	private PDFValue value;
	
	private ExtList maxWidths;
	private float max_width;
	
	private TFE newLE;
	private boolean change = false;


	public PDFC1(Manager manager, PDFEnv pdf_env) {
		this.manager = manager;
		this.pdf_env = pdf_env;

		maxWidths = new ExtList();
	}

	@Override
	public String work(ExtList data_info) {
		float box_width = 0;
		float box_height = 0;
		float tmp_height = 0;
//		int level = pdf_env.level;
		
		System.out.println("\n------- C1 -------");
		System.out.println("[PDFC1:work]data_info = " + data_info);

		value = new PDFValue("C1");
		
		int i;
		int y_default = pdf_env.y_back;
		setDataList(data_info);

		for (i = 0; i < tfeItems; i++) {
			ITFE tfe = (ITFE) tfes.get(i);

			if (tfe instanceof Attribute) {
				System.out.println("[PDFC1:work]tfe is Attribute");
				worknextItem();
				
				value.inList.add(((PDFTFE)tfe).getInstance());
				

				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (tmp_height > box_height) {
					box_height = tmp_height;
				}
				/////////////////////////////////
				//if( i+1 < data_info.size() ) //??
				if (hasMoreItems())
					pdf_env.x_back++;

				//lap++;
			}

			else {
				System.out.println("[PDFC1:work]tfe is instance of Operator");

				//if( i == number-1 && writer.flag == true )
				//    writer.flag = true;
				//else
				//    writer.flag = false;

				//System.out.println("lap=" + lap);

				//////////////////////

				pdf_env.y_back = y_default;
				
				//pdf_env.level++;
				//pdf_env.level = level + tfeitems +1;
//				pdf_env.level = pdf_env.level_max+1;
				
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}
				
				worknextItem();
				
				System.out.println("++++ C1");
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (tmp_height > box_height) {
					box_height = tmp_height;
				}

				////////////////////////////////
				//if( i+1 < data_info.size() )
				if (hasMoreItems())
					pdf_env.x_back++;

				//if(flag == true)
				//    writer.y_back = y_tmp;

			}

			
			checkMaxWidth(pdf_env.tmp_width, i);
			//if(maxWidth[i] < box_width)
			//	maxWidth[i] = box_width;
			//if(maxWidth[i] < pdf_env.tmp_width)
			//	maxWidth[i] = pdf_env.tmp_width;
					
		}
		///////////////////////////////////////////////////////////////////////////

		for(i=0; i<value.inList.size(); i++){
			PDFValue re_set = (PDFValue)value.inList.get(i);
			re_set.box_height = box_height;
		}
		
		//�ɲ�10.17
		//�ѥ�᡼���򥻥å�
		value.box_width = box_width;
		value.box_height = box_height;
		
		value.originalWidth = box_width;	
		value.originalHeight = box_height;
		
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;
		return null;
		
	}

	
	
	public void checkMaxWidth(float box_width, int i){
		if(i >= maxWidths.size())
			maxWidths.add("0");
		max_width = Float.parseFloat((String)maxWidths.get(i));
		if(max_width < box_width){
			max_width = box_width;
			maxWidths.set(i, Float.toString(max_width));
		}
	}
	
	public PDFValue getInstance(){
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


//pdf_env.labelV++;

		
		for (i = 0; i < tfeItems; i++) {
			ITFE tfe = (ITFE) tfes.get(i);

			
			pdf_env.labelV = labelV;
			pdf_env.labelV++;
			if(pdf_env.labelV > pdf_env.labelmaxV ){
				pdf_env.labelmaxV = pdf_env.labelV;
			}
			
			pdf_env.labelH = pdf_env.labelmaxH + 1;			
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
		
//pdf_env.labelV = labelV;

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
		
		int local, local2, local3;
		
		float tmpDexcess;
		float tmpWidth = 0;
		PDFValue keyBox = box;		
		
		ExtList originalTFE = this.tfes;	
		
		ExtList DList = new ExtList();
		ExtList sortDList = new ExtList();
	//	System.out.println("AAAAAAAAAAAA "+Dexcess);
		
	//	for(local=0; local<tfeitems; local++) {
		for(local=tfeItems-1; local>-1; local--) {
			ITFE tfe = (ITFE)tfes.get(local);
			PDFValue inBox = (PDFValue)box.inList.get(local);
			
			tmpDexcess = (inBox.box_width / box.box_width) * Dexcess;
			flex = ((PDFTFE)tfe).optimizeW(tmpDexcess, inBox);

			 	DList.add(Float.toString(pdf_env.cutWidth));
			 	
			
			if(!flex){
				for(local2=tfeItems-1; local2>=local; local2--){
					System.out.println("@@@@@@@@@@@@@@@@");
					tfe = (ITFE)tfes.get(local2);
					((PDFTFE)tfe).redoChange();
				}
				break;
			}
		}
		
		sortDList = (ExtList)DList.clone();

		float tempD;
		for(local=0; local<sortDList.size(); local++){		
			for(local2=sortDList.size()-1; local2>local; local2--){
				float bubbleD = Float.parseFloat((String)sortDList.get(local2));
				float waterD = Float.parseFloat((String)sortDList.get(local2-1));
				
				if(bubbleD > waterD){
					tempD = bubbleD;
					sortDList.set(local2, Float.toString(waterD));
					sortDList.set(local2-1, Float.toString(bubbleD));
				}
			}		
		}
		for(local=0; local<DList.size(); local++){
			System.out.println("DList "+DList.get(local));
		}
		for(local=0; local<sortDList.size(); local++){
			System.out.println("sortDList "+sortDList.get(local));
		}
		System.out.println("Dexcess "+Dexcess);


		for(local=sortDList.size()-1; local>-1; local--){
			tempD = Float.parseFloat((String)sortDList.get(local));
			if(tempD >= Dexcess){
				int index = DList.lastIndexOf(Float.toString(tempD));
				System.out.println("index " +index);
				//index++;					
				ITFE tfe = (ITFE)tfes.get(index);
				this.tfes.set(index, ((PDFTFE)tfe).getNewChild() );
				int local4;
				for(local4=0; local4<tfes.size(); local4++){
					System.out.println("tfe "+((ITFE)tfes.get(local4)).getClass());
				}
				flex = true;
				pdf_env.cutWidth = tempD;
				break;
			}
			else{
				flex = false;
			}
		}
if(!flex){
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		float sumD = 0;
		tempD = 0;
		escape:
		for(local=sortDList.size()-1; local>-1; local--){
			for(local2=2; local2<=sortDList.size(); local2++){
				for(local3=0; local3<local2; local3++){
					tempD = Float.parseFloat((String)DList.get(local));
					sumD += tempD;
				}
				if(sumD >= Dexcess){
					for(local3=0; local3<local2; local3++){
						tempD = Float.parseFloat((String)DList.get(local));
						int index = DList.lastIndexOf(Float.toString(tempD));
						ITFE tfe = (ITFE)tfes.get(index);
						this.tfes.set(index, ((PDFTFE)tfe).getNewChild() );
					}
					flex = true;
					pdf_env.cutWidth = tempD;
					break escape;
				}
			}
		}
}		

		
		if(flex){
			System.out.println("vvvvvvvvvvvvvvvvvv");

			for (local=tfeItems-1; local>-1; local--) {
				ITFE tfe = (ITFE)tfes.get(local);
				if( ((PDFTFE)tfe).changeORnot() )
					this.tfes.set(local, ((PDFTFE)tfe).getNewChild() );
			}
		}

		
		if(!flex){

			for(local=0; local<box.inList.size(); local++){
				PDFValue inBox = (PDFValue)box.inList.get(local);
				if(tmpWidth < inBox.box_width){
					keyBox = inBox;
					tmpWidth = inBox.box_width;
				}
			}
			//---------------------------------------------------------//
			if(box.box_width - keyBox.box_width >= Dexcess){
//				if( !(pdf_env.flexTH < (tmpHeight - box.box_height) / Dexcess) ){
					newLE = new PDFC2(manager, pdf_env);
					((PDFC2)newLE).tfeItems = this.tfeItems;
					((PDFC2)newLE).tfes = originalTFE;//this.tfes;
					((PDFC2)newLE).decos = this.decos;
					System.out.println("C1 change to C2");
					change = true;
					flex = true;
					pdf_env.cutWidth = box.box_width - keyBox.box_width;
//				}
//				else
//					flex = false;
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
	
	
	public boolean changeORnot(){
		return change;
	}
	
	public void redoChange(){
		
	}
	
	
	@Override
	public String getSymbol() {
		return "C1";
	}

}