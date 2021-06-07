package supersql.codegenerator.VR;

import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRC4 extends Connector {

	private VREnv vrEnv;
	private VREnv vrEnv2;

	public VRC4(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRC4";
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("------- C4 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfessize=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());
		this.setDataList(data_info);


		//TODO: verify these 3 ifs are not needed
		if (decos.containsKey("insert")) {
			VREnv.setIDU("insert");
		}
		if (decos.containsKey("update")) {
			VREnv.setIDU("update");
		}
		if (decos.containsKey("delete")) {
			VREnv.setIDU("delete");
		}

		int i = 0;
		
		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			Element connector = vrEnv.xml.createElement("Connector"+VRC1.j);
			connector.setAttribute("type","C4");
			vrEnv.currentNode = vrEnv.currentNode.appendChild(connector);
			VRC1.j++;
		}
		while (this.hasMoreItems()) {

			if(VRAttribute.genre.equals("")){/// kotani 16/10/04
				if(vrEnv.gLevel == 0){
					VRAttribute.groupcount++;
				}
			}

			this.worknextItem();

			Log.out("</TD>");////</TR>消した

			i++;
		}
		
		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			vrEnv.currentNode = vrEnv.currentNode.getParentNode();
			VRC1.j--;
		}
		
		if(vrEnv.gLevel == 2 && VRManager.gindex.get(vrEnv.gLevel-2) == 1 && VRManager.gindex.get(vrEnv.gLevel-1) == 1){
			try {
				String l=VRManager.multiexh.get(VRManager.nest1count);
				VRManager.multiexh.set(VRManager.nest1count,l+"#");//gindex[]++
			} catch (Exception e) {
				VRManager.multiexh.add("#");
			}
		}
		
		if(VRAttribute.gjudge == 0){
			if(VRAttribute.billnum >= 2){
				VRAttribute.billnum = 0;
			}
		}
		
		Log.out("TFEId = " + VREnv.getClassID(this));
		return null;

	}

}
