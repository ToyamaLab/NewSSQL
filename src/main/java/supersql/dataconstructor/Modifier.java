package supersql.dataconstructor;

import supersql.codegenerator.DecorateList;
import supersql.extendclass.ExtList;

public class Modifier {
	
	private DecorateList outer_decos = new DecorateList();
	//{[tfe],, [tfe]}@{outer_deco}
	public void setOuterDeco(ExtList tfe_tree) {
		ExtList tree_unnest = tfe_tree.unnest();
		String deco = tree_unnest.get(tree_unnest.size() - 1).toString();
		deco = deco.substring(deco.indexOf("{") + 1, deco.indexOf("}"));
		boolean sq_flag = false;
		boolean dq_flag = false;
		String parts = new String();
		int begin = 0;
		int i = 0;
		for(i = 0; i < deco.length(); i++){
			char c = deco.charAt(i);
			if(c == '\''){
				sq_flag = !sq_flag;
			}else if(c == '"'){
				dq_flag = !dq_flag;
			}else if(c == ','){
				if(!sq_flag && !dq_flag){
					parts = String.join("", deco.substring(begin, i).split(" "));
					begin = i + 1;
					outer_decos.put(parts.substring(0, parts.indexOf("=")), parts.substring(parts.indexOf("'") + 1, parts.lastIndexOf("'")));
				}
			}	
		}
		parts = String.join("", deco.substring(begin, i).split(" "));
		outer_decos.put(parts.substring(0, parts.indexOf("=")), parts.substring(parts.indexOf("'") + 1, parts.lastIndexOf("'")));
//		Log.info("decos:"+outer_decos);
	}
	
	public DecorateList getOuterDeco(){
		return outer_decos;
	}
}
