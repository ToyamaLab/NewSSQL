package supersql.codegenerator.Mobile_HTML5;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Manager;
import supersql.codegenerator.HTML.HTMLFunction;
import supersql.extendclass.ExtList;

//import common.Log;

public class Mobile_HTML5C0 extends Connector {
	Manager manager;

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	//���󥹥ȥ饯��
	public Mobile_HTML5C0(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
		this.manager = manager;
		this.html_env = henv;
		this.html_env2 = henv2;
	}

	//C2��work�᥽�å�
	@Override
	public String work(ExtList data_info) {

//		      Log.out("data_info =" +data_info);
		
		this.setDataList(data_info);
		

//		 if(decos.containsKey("form")){
//	           	html_env.code.append(Mobile_HTML5Function.createForm(decos));
//	           	Mobile_HTML5Env.setFormItemFlg(true,null);
//	        	html_env2.code.append("<form"+Mobile_HTML5Env.getFormNumber()+"start />");
//	        	if(decos.getStr("form").toLowerCase().equals("search"))
//	        		Mobile_HTML5Env.setSearch(true);
//		 }	 
		
		String ret = "";
		while (this.hasMoreItems()) {
			ret += this.worknextItem();
		}

//        if(decos.containsKey("form")){
//        	html_env2.code.append("<form"+ Mobile_HTML5Env.getFormNumber() +"end />");
//        	html_env.code.append(Mobile_HTML5Env.exFormNameCreate());
//           	html_env.code.append("</form>");
//           	Mobile_HTML5Env.setFormItemFlg(false,null);
//           	Mobile_HTML5Env.incrementFormNumber();
//           	if(decos.getStr("form").toLowerCase().equals("search"))
//        		Mobile_HTML5Env.setSearch(false);
//        }
//		return null;
		return (!Mobile_HTML5Function.Mobile_HTML5FunctionFlag)? null : ret;
	}

	@Override
	public String getSymbol() {
		return "Mobile_HTML5C0";
	}

}