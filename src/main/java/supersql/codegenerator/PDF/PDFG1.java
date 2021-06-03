package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFG1 extends Grouper implements PDFTFE {

	private PDFManager manager;

	private PDFEnv pdf_env;
	
	private PDFValue value;
	
	private ExtList rows;
	
	private float tmp_width;
	private float tmp_height;
	private float box_widthMAX;
	private float box_heightSUM;
	private float fold;
	private int topID;

//	boolean fold_or_not = false;
	
	ExtList maxHeights;

	int rowNum;
	ExtList rowHeights;

//	float rowMaxHeight;

	
	int repeatNum = 0;
	
	
	TFE newLE;
	boolean change = false;
	
	
	public PDFG1(Manager manager, PDFEnv pdf_env) {
		this.manager = (PDFManager) manager;
		this.pdf_env = pdf_env;
		maxHeights = new ExtList();
	}

	@Override
	public String work(ExtList data_info) {
		float box_width = 0;
		float box_height = 0;
//		int level = pdf_env.level;
		
//		int fold_num;

		box_widthMAX = 0;
		box_heightSUM = 0;

		fold = 0;
		
		rowNum = 0;
		
		
		
		System.out.println("");
		System.out.println("------- G1 -------");
		System.out.println("[PDFG1:work]tfe_info = " + makele0());
		System.out.println("[PDFG1:work]data_info = " + data_info);

		System.out.println("++++ G1��value��new���ޤ�");
		//�ɲ�10.17
		value = new PDFValue("G1");
		
		rows = new ExtList();
		rowHeights = new ExtList();
//		rowHeights.add("0");

		int i;

		setDataList(data_info);
		setDecoration();

		if (tfe instanceof Attribute) {
			System.out.println("[PDFG1:work]tfe is Attribute");
			System.out.println("number of data = " + data_info.size());


			for (i = 0; i < data_info.size(); i++) {
//				pdf_env.level_max = level;

				
//				pdf_env.level++;
////				pdf_env.level += rowNum;
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}

				worknextItem();
				
				//pdf_env.level--;
//				pdf_env.level = level;
				
				
				if(fold != 0){
					if(Fold_or_Not(box_width, box_height, fold)){
						box_width = 0;
						box_height = 0;
					}
				}
				
				
				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (box_height < tmp_height)
					box_height = tmp_height;
				
				
				//checkMaxHeight(pdf_env.tmp_width);
				checkMaxHeight(box_height);
				
				
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				//��ư

			}

			
		}

		//////////////////////////////////////////
		//////////////////(G2 (C1 1 2))
		//////////////////////////////////////////
		else {
			System.out.println("[PDFG1:work]tfe is Operator");


			for (i = 0; i < data_info.size(); i++) {
//				pdf_env.level_max = level;


//				pdf_env.level++;
////				pdf_env.level += rowNum;
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}
				
				worknextItem();
				
				//pdf_env.level--;
//				pdf_env.level = level;

				
				
				if(fold != 0){
					if(Fold_or_Not(box_width, box_height, fold)){
						box_width = 0;
						box_height = 0;
					}
				}
				
				

				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (box_height < tmp_height)
					box_height = tmp_height;


				//checkMaxHeight(pdf_env.tmp_height);
				checkMaxHeight(box_height);
				
				
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				

			}

			
		}

		///////////////////////////////

		//for(i=0; i<value.inList.size(); i++){
		//	PDFValue re_set = (PDFValue)value.inList.get(i);
		//	re_set.box_height = box_height;
		//}
		re_setHeight(topID, value.inList.size(), box_height);

		
		if(fold != 0){
			if(box_width < box_widthMAX){
				box_width = box_widthMAX;
			}
			box_height += box_heightSUM;
		}

		
//		if(columns.size() == 0)
//			columns.add(Integer.toString(value.inList.size()));
		rows.add(Integer.toString(value.inList.size()));

		
		float rowHeight = 0;
		
		for(i=0; i<rowHeights.size(); i++)
			rowHeight += Float.parseFloat((String)rowHeights.get(i));
		box_height = rowHeight;
		//------------------------------------------------//
		
		value.box_width = box_width;
		value.box_height = box_height;
		
		value.originalWidth = box_width;
		value.originalHeight = box_height;
		
		
		value.rowNum = rowNum;
		value.rows = rows;
		value.rowHeights = rowHeights;
		
		value.fold = fold;
		value.tfeID = getId();
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;

		
		
		float maxHeight;
		
		if( maxHeights.size() < rows.size() ){
			int diff = rows.size() - maxHeights.size();
			for(i=0; i<diff; i++)
				maxHeights.add("0");
		}
		for(i=0; i<rowHeights.size(); i++){
			maxHeight = Float.parseFloat((String)maxHeights.get(i));
			rowHeight = Float.parseFloat((String)rowHeights.get(i));
			if(maxHeight < rowHeight)
				maxHeights.set(i, Float.toString(rowHeight));
		}
		//------------------------------------------------//
		
		
		int local;
		for(local=0;local<rows.size();local++){
			System.out.println("dore"+rows.get(local));
		}
		return null;
	}
	
	
	
	
	public boolean Fold_or_Not(float box_width, float box_height, float fold){
		boolean fold_or_not = false;
//		fold_or_not = false;
		tmp_width = box_width;
		tmp_width += pdf_env.tmp_width;
		if(tmp_width>fold){
			Fold(box_width, box_height);
			rows.add(Integer.toString(value.inList.size()));
			System.out.println("eeeeeeeeeeeeeee"+value.inList.size());
			rowNum++;
			fold_or_not = true;
		}
		return fold_or_not;
	}
	
	public void Fold(float box_width, float box_height){
		tmp_width = box_width;
		if(tmp_width > box_widthMAX){
			box_widthMAX = tmp_width;
		}
		box_heightSUM += box_height;
		re_setHeight(topID, value.inList.size(), box_height);
		topID = value.inList.size();
	}
	
	public void checkMaxHeight(float box_height){
		float rowTmpHeight;
		
		if(rowNum >= rowHeights.size())
			rowHeights.add("0");
		rowTmpHeight = Float.parseFloat((String)rowHeights.get(rowNum));
		if(rowTmpHeight < box_height)
			rowHeights.set(rowNum, Float.toString(box_height));
		
//		if(fold_or_not)
//			rowNum++;
	}
	
	public void re_setHeight(int start, int end, float set_height){
		int i;
		for(i=start; i<end; i++){
			PDFValue re_set = (PDFValue)value.inList.get(i);
			re_set.box_height = set_height;
		}
	}
	
	public void setDecoration(){
		if(decos.containsKey("fold")){
			fold = Float.parseFloat(decos.get("fold").toString());
		}
	}
	
	public PDFValue getInstance(){
		return value;
	}

	
	public void setLabel(PDFValue result) {

		int i;
		PDFValue instance;
		
		int rowNum = 0;
		//int columnNum = 0;
		
		int labelH = pdf_env.labelH;
		int labelV = pdf_env.labelV;
		String labelSuffixH = pdf_env.labelSuffixH;
		String labelSuffixV = pdf_env.labelSuffixV;
		
		
		//----------------------------------------------
		int labelO = pdf_env.labelO;
		
		
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelH = Integer.toString(labelH);
		else
			result.labelH = Integer.toString(labelH) + labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelV = Integer.toString(labelV);
		else
			result.labelV = Integer.toString(labelV) + labelSuffixV;
		//-----------------------------------------------------------------//
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
		//-----------------------------------------------------------------//
		if( !pdf_env.labelListOH.contains(result.labelOH) )
			pdf_env.labelListOH.add(result.labelOH);
		if( !pdf_env.labelListOV.contains(result.labelOV) )
			pdf_env.labelListOV.add(result.labelOV);
		
		

		
		
		int maxHtmp;
		int maxVtmp;
		
		pdf_env.labelmaxH++;
		maxHtmp = pdf_env.labelmaxH;
		pdf_env.labelmaxV++;
		maxVtmp = pdf_env.labelmaxV;
		
		
		int labelHtmp;
		int labelVtmp;
		
		pdf_env.labelH = pdf_env.labelmaxH;
		labelHtmp = pdf_env.labelH;
		pdf_env.labelV = pdf_env.labelmaxV;
		labelVtmp = pdf_env.labelV;
		
		//----------------------------------
		int maxOtmp;
		pdf_env.labelmaxO++;
		maxOtmp = pdf_env.labelmaxO;
		int labelOtmp;
		pdf_env.labelO = pdf_env.labelmaxO;
		labelOtmp = pdf_env.labelO;
		
		
		for (i = 0; i < result.inList.size(); i++) {		
			
			pdf_env.labelH = labelHtmp;
			pdf_env.labelmaxH = maxHtmp;
			
			pdf_env.labelV = labelVtmp;
			pdf_env.labelmaxV = maxVtmp;

			
			//-----------------------------------
			pdf_env.labelO = labelOtmp;
			pdf_env.labelmaxO = maxOtmp;
			
			
			
			repeatNum++;
			//columnNum = i;
			String columnStr = Integer.toString(repeatNum);
			if(labelSuffixH.equals("null"))
				pdf_env.labelSuffixH = "-" + columnStr;
			else
				pdf_env.labelSuffixH = labelSuffixH + "-" + columnStr;
			//----------------------------------------------------------------//
			

			if( i == Integer.parseInt((String)result.rows.get(rowNum)) ){
				rowNum++;
			}
			String rowStr = Integer.toString(rowNum);
			if(labelSuffixV.equals("null"))
				pdf_env.labelSuffixV = "-" + rowStr;
			else
				pdf_env.labelSuffixV = labelSuffixV + "-" + rowStr;
			//----------------------------------------------------------------//
			
	
			
			instance = (PDFValue)result.inList.get(i);
			((PDFTFE)tfe).setLabel(instance);
			
				
			//pdf_env.labelV = labelV;
			
			pdf_env.labelSuffixH = labelSuffixH;
			pdf_env.labelSuffixV = labelSuffixV;
			//----------------------------------------------------------------//
		}			


	}
	
	
	public void restoreFOLD(PDFValue check){

		if( check.tfeID != getId() ){
			((PDFTFE)tfe).restoreFOLD(check);
		}
		else{			
			int i;
			
			float widthSUM = 0;
			float maxHeight = 0;
			int rowNum = 0;
			
			this.rows.clear();
			this.rowHeights.clear();
			
			for(i=0; i<check.inList.size(); i++){
				PDFValue inBox = (PDFValue)check.inList.get(i);
			
				if(inBox.box_width > check.fold){
					System.out.println("ERROR: one Box's width is over the folding width");
					System.exit(0);
				}
				
				widthSUM += inBox.box_width;
				
				if(widthSUM > check.fold){
					this.rows.add(Integer.toString(i));
					this.rowHeights.add(Float.toString(maxHeight));
					
					maxHeight = 0;
					widthSUM = inBox.box_width;
					rowNum++;
				}
				
				if(maxHeight < inBox.box_height)
					maxHeight = inBox.box_height;				
			}
		
			this.rows.add(Integer.toString(i));
			this.rowHeights.add(Float.toString(maxHeight));
			
			this.rowNum = rowNum;	
			
			check.rows = this.rows;
			check.rowHeights = this.rowHeights;
			check.rowNum = this.rowNum;
		}
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		
		int local;
		
		float tmpDexcess;
		float tmpWidth = 0;
		float tmpHeight = 0;
		PDFValue keyBox = box;
		
		float sumCutWidth = 0;
	
		TFE originalTFE = this.tfe;
		
		for (local=box.inList.size()-1; local>-1; local--) {
			PDFValue inBox = (PDFValue)box.inList.get(local);
			
			if(inBox.type.equals("Att") || inBox.type.equals("Func")){
				flex = false;
				break;
			}
			//---------------------------------------------------------------//
			
			tmpDexcess = (inBox.box_width / box.box_width) * Dexcess;
			flex = ((PDFTFE)tfe).optimizeW(tmpDexcess, inBox);
			if(!flex)
				break;
			else{
				sumCutWidth += pdf_env.cutWidth;
			}
			

		}
		if(flex){
			if( ((PDFTFE)tfe).changeORnot() ){
				this.tfe = ((PDFTFE)tfe).getNewChild();
				//flex = true;
				pdf_env.cutWidth = sumCutWidth;
			}
		}
		
		//�ޤ���ߥȥ饤
		if(!flex){
			float newFold;
			
			newFold = box.box_width - Dexcess;
			tmpHeight = box.box_height;
			for(local=0; local<box.inList.size(); local++){
				PDFValue inBox = (PDFValue)box.inList.get(local);
				if(tmpWidth > newFold){
					tmpWidth = 0;
					tmpHeight += inBox.box_height;
				}
				tmpWidth += inBox.box_width;
			}
			if( !(pdf_env.flexTH < (tmpHeight - box.box_height) / Dexcess) ){
				decos.put("fold", Float.toString(newFold));
				System.out.println("G1 is folding!!");
				flex = true;
				pdf_env.cutWidth = Dexcess;
			}
		}
		
		if(!flex){
			tmpWidth = 0;
			tmpHeight = 0;
			
			for(local=0; local<box.inList.size(); local++){
				PDFValue inBox = (PDFValue)box.inList.get(local);
				if(tmpWidth < inBox.box_width){
					keyBox = inBox;
					tmpWidth = inBox.box_width;
				}
				tmpHeight += inBox.box_height;
			}
			//---------------------------------------------------------//
			if(box.box_width - keyBox.box_width >= Dexcess){
				if( !(pdf_env.flexTH < (tmpHeight - box.box_height) / Dexcess) ){
					newLE = new PDFG2(manager, pdf_env);
					((PDFG2)newLE).tfe = originalTFE;//this.tfe;
					((PDFG2)newLE).decos = this.decos;
					this.decos.put("fold", "0");
					System.out.println("G1 change to G2");
					change = true;
					flex = true;
					pdf_env.cutWidth = box.box_width - keyBox.box_width;
				}
				else
					flex = false;
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
		return "G1";
	}

}