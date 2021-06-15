package supersql.codegenerator;

import supersql.codegenerator.HTML.HTMLC0;
import supersql.codegenerator.HTML.HTMLCONCAT;
import supersql.codegenerator.HTML.HTMLFunction;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Function;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_dynamic;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_stream;
import supersql.codegenerator.infinitescroll.Infinite_dynamic;
import supersql.common.Log;
import supersql.extendclass.ExtList;


public class FuncArg {

	String Name; //

	public TFE tfe; //

	ExtList Data = new ExtList(); //
	
	boolean concatFlag;
//	public static boolean FuncArgFlag = false;
	

	public FuncArg(String nm, TFE t) {
		Name = nm;
		tfe = t;
		concatFlag = false;
	}

	public void setName(String nm) {
		Name = nm;
	}
	
	public void debugout() {
		debugout(0);
	}

	public void debugout(int count) {

		Debug dbgout = new Debug();

		dbgout.prt(count, "<FuncArg Name:" + Name + ">");
		tfe.debugout(count + 1);
		dbgout.prt(count, "</FuncArg>");

	}

	public ExtList makesch() {

		return tfe.makesch();

	}

	public ExtList makeschImage() {

		return tfe.makeschImage();

	}

	public ExtList makele0() {

		return tfe.makele0();

	}

	public int countconnectitem() {
		return tfe.countconnectitem();
	}

	public String getKey() {
		if (Name == null) {
			return "default";
		}
		return Name;
	}

	@Override
	public String toString() {
		return tfe.toString();
	}

	public void setData(ExtList data_info) {
		Data = data_info;
	}

	public String getStr() {
		if (tfe instanceof Function) {
			//20131201 nesting function
			Function.nestingLevel++;
			return tfe.work(Data);	//recursive call

		}
		else if (tfe instanceof Attribute) {
			//20131118 dynamic
			if(Mobile_HTML5_dynamic.dynamicDisplay){
				return Mobile_HTML5_dynamic.dynamicFuncArgProcess(tfe, null, null);
			}
			else if(Mobile_HTML5_stream.streamDisplay){
				return Mobile_HTML5_stream.streamFuncArgProcess(tfe, null, null);
			}

			return ((Attribute) tfe).getStr(Data);
		}
		else {
//			return null;
//			return "null2";
//			return tfe.toString();
			
//			FuncArgFlag = true;
//			tfe.work(Data);
//			FuncArgFlag = false;
			

//				// Log.out("data_info =" +data_info);
//
//				this.setDataList(Data);
//
////				if (decos.containsKey("form")) {
////					htmlEnv.code.append(HTMLFunction.createForm(decos));
////					HTMLEnv.setFormItemFlg(true, null);
////					htmlEnv2.code
////							.append("<form" + HTMLEnv.getFormNumber() + "start />");
////					if (decos.getStr("form").toLowerCase().equals("search"))
////						HTMLEnv.setSearch(true);
////				}
//
//				while (this.hasMoreItems()) {
//					this.worknextItem();
//				}
//
//				//tbt add 180807
//				//for decoration of HTMLJOIN
//				//TODO: Check hierarchical structure between each exp
//				htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
//				//tbt end
//
////				if (decos.containsKey("form")) {
////					htmlEnv2.code.append("<form" + HTMLEnv.getFormNumber() + "end />");
////					htmlEnv.code.append(HTMLEnv.exFormNameCreate());
////					htmlEnv.code.append("</form>");
////					HTMLEnv.setFormItemFlg(false, null);
////					HTMLEnv.incrementFormNumber();
////					if (decos.getStr("form").toLowerCase().equals("search"))
////						HTMLEnv.setSearch(false);
////				}
//			
////			return null;
//			return null;
			if(HTMLFunction.HTMLFunctionFlag || Mobile_HTML5Function.Mobile_HTML5FunctionFlag){
				// Concat対策
				// C0かつその下がHTMLCONCATだったら
				Log.out("In concat tfe: " + tfe);
				if (tfe instanceof HTMLC0 && tfe.makele0().getExtListString(1, 0).equals("HTMLCONCAT")) {
					Log.out("[Found concat in SSQL Function]");
					Log.out("[TFE le0]: " + tfe.makele0());
					Log.out("[Data]: " + Data);
					return ((HTMLC0) tfe).getConcatStr(Data);
				}else if(tfe instanceof HTMLCONCAT){
					
					return tfe.work(Data);
				}
				return tfe.work(Data);
			}
			return null;
//			return ((HTMLC0) tfe).getStr(Data);
		}
	}
	//taji added for infinite scroll
	public String getStr_ifs() {
		if (tfe instanceof Function) {
			//20131201 nesting function
			Function.nestingLevel++;
			return tfe.work(Data);	//recursive call

		}
		else if (tfe instanceof Attribute) {

			//20131118 dynamic
			if(Mobile_HTML5_dynamic.dynamicDisplay){
				return Infinite_dynamic.dynamicFuncArgProcess(tfe, null, null);
			}
//			//20131127 form
//			if(Mobile_HTML5.form){
//				return Mobile_HTML5.formFuncArgProcess(tfe);
//			}

			return ((Attribute) tfe).getStr(Data);
		}
		else {
			// return null;
			if(HTMLFunction.HTMLFunctionFlag || Mobile_HTML5Function.Mobile_HTML5FunctionFlag){
				return tfe.work(Data);
			}
			return null;
			
		}
	}

	public void workAtt() {
		tfe.work(Data);
		return;
	}

	public Object createNode(){
		return tfe.createNode(Data);
	}

	public Class<? extends TFE> getTFEClass(){
		return tfe.getClass();
	}
	
	public TFE getTFE(){
		return tfe;
	}

	public void setConcatFlag(boolean _concatFlag) {
		concatFlag = _concatFlag;
	}
	
	public boolean getConcatFlag() {
		return concatFlag;
	}
}
