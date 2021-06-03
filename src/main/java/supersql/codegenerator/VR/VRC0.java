package supersql.codegenerator.VR;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Manager;
import supersql.extendclass.ExtList;

//import common.Log;

public class VRC0 extends Connector {
	private VREnv vrEnv;
	private VREnv vrEnv2;

	public VRC0(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRC0";
	}

	@Override
	public String work(ExtList data_info) {
		this.setDataList(data_info);
		while (this.hasMoreItems()) {
			this.worknextItem();
		}
		return null;
	}

}