package supersql.codegenerator.VR;

import java.util.ArrayList;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Decorator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

//tk

public class VRDecoration extends Decorator {

	private VREnv vrEnv;
	private VREnv vrEnv2;
	
	public static ArrayList<StringBuffer> fronts = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> classes = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> styles = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> ends = new ArrayList<StringBuffer>();
	
	public VRDecoration(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRDecoration";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- Decoration -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfes.size=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());
		StringBuffer Front = new StringBuffer();
		StringBuffer classname = new StringBuffer();
		StringBuffer Style = new StringBuffer();
		StringBuffer End = new StringBuffer();
		fronts.add(0, Front);
		classes.add(0, classname);
		styles.add(0, Style);
		ends.add(0, End);
		ArrayList<String> decoproperty = new ArrayList<String>();
		vrEnv.decorationProperty.add(0, decoproperty);
		vrEnv.decorationStartFlag.add(0, false);
		vrEnv.decorationEndFlag.add(0, false);

		this.setDataList(data_info);
		
		int i = 0;

		while (this.hasMoreItems()) {
			ITFE tfe = tfes.get(i);
			String classid = VREnv.getClassID(tfe);

			vrEnv.decorationStartFlag.set(0, true);
			
			this.worknextItem();

			vrEnv.decorationEndFlag.set(0, true);

			i++;
		}
		
		fronts.remove(0);
		classes.remove(0);
		styles.remove(0);
		ends.remove(0);
		vrEnv.decorationProperty.remove(0);
		vrEnv.decorationStartFlag.remove(0);
		vrEnv.decorationEndFlag.remove(0);
		
		Log.out("+++++++ Decoration +++++++");
		return null;
	}

}
