package supersql.codegenerator;

import java.io.Serializable;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Connector extends Operator implements Serializable{

	public int tfeItems;
	public ExtList<TFE> tfes;

    //oka start
    public static boolean updateFlag;
    public static boolean insertFlag;
    public static boolean deleteFlag;
    //oka end
    public static boolean loginFlag;
    public static boolean logoutFlag;

	protected int sindex, dindex;

	public Connector() {
		super();
		Dimension = -1;
		tfeItems = 0;
		tfes = new ExtList<TFE>();
	}

	public Connector(int d) {
		super();
		Dimension = d;
		tfeItems = 0;
		tfes = new ExtList<TFE>();
	}

	public void setTFE(ITFE t) {
		tfeItems++;
		tfes.add((TFE) t);
	}

	public void debugout() {
		debugout(0);
	}

	public void debugout(int count) {
		Debug dbgout = new Debug();
		dbgout.prt(count, "<Connector type=" + getSymbol() + " tfeitems="
				+ tfeItems + " decoitems=" + decos.size() + " id=" + id + ">");

		decos.debugout(count + 1);

		for (int i = 0; i < tfeItems; i++) {
			((ITFE) tfes.get(i)).debugout(count + 1);
		}
		dbgout.prt(count, "</Connector>");
	}

	public ExtList<Integer> makesch() {
		ExtList<Integer> outsch = new ExtList<Integer>();
		for (int i = 0; i < tfeItems; i++) {

			outsch.addAll(tfes.get(i).makesch());
		}
		return outsch;
	}

	public ExtList makele0() {
		ExtList le0 = new ExtList();
		le0.add(this.getSymbol());
		for (int i = 0; i < tfeItems; i++) {
			le0.add(((ITFE) tfes.get(i)).makele0());
		}

		Log.out("Con le0:" + le0);
		return le0;
	}

	public String getSymbol() {
		return "C?";
	}

	public int countconnectitem() {
		int items = 0;
		for (int i = 0; i < tfes.size(); i++) {
			items += ((ITFE) tfes.get(i)).countconnectitem();
		}
		return items;
	}

	public void setDataList(ExtList d) {
		data = d;
		sindex = 0;
		dindex = 0;
	}

	public boolean hasMoreItems() {
		return (sindex < tfes.size());
	}

	public Object createNextItemNode(ExtList data) {
		ITFE tfe = (ITFE) tfes.get(sindex);
		int ci = tfe.countconnectitem();

		ExtList subdata = data.ExtsubList(dindex, dindex + ci);
		sindex++;
		dindex += ci;
		if (tfe instanceof Connector || tfe instanceof Attribute
				|| tfe instanceof Function || tfe instanceof IfCondition || tfe instanceof Decorator) {
			return tfe.createNode(subdata);
		}
		else {
			return tfe.createNode((ExtList) subdata.get(0));
		}
	}
	//tbt add variable 'string' 180806
	public String worknextItem() {
		ITFE tfe = (ITFE) tfes.get(sindex);
		int ci = tfe.countconnectitem();

		ExtList subdata = data.ExtsubList(dindex, dindex + ci);
		String string = new String();
		if (tfe instanceof Connector || tfe instanceof Attribute
				|| tfe instanceof Function || tfe instanceof IfCondition || tfe instanceof Decorator) {

//			//20131118 dynamic
//			if(Mobile_HTML5.dynamicDisplay){
//				subdata = Mobile_HTML5.dynamicConnectorProcess(tfe, subdata);
//			}
			string = tfe.work(subdata);

		}
		else {
			string = tfe.work((ExtList) subdata.get(0));
		}
		sindex++;
		dindex += ci;
//		if(GlobalEnv.joinFlag){
			return string;
//		}
//		return null;

	}
	// tbt end

	public boolean isFirstItem() {
	    return (sindex == 0);
	}

	public TFE gettfe(int i) {
		return tfes.get(i);
	}

	//added by ria 20110913 start
	public ExtList makeschImage() {
		ExtList outsch = new ExtList();
		for (int i = 0; i < tfeItems; i++) {
			outsch.addAll(((ITFE) tfes.get(i)).makeschImage());
		}
		return outsch;
	}
	//added by ria 20110913 end

	public void addDeco(String key, String val, String condition) {
		decos.put(key, val, condition);

	}

	@Override
	public String work(ExtList data_info) {
		return null;
//		return aggregate;
	}

	@Override
	public Object createNode(ExtList<ExtList<String>> data_info) {
		return null;
	}

	public ExtList<ExtList<String>> getData() {
		return data;
	}


	//added by taji 171102 start
	public ExtList get_keys(boolean flag){
		ExtList keys = new ExtList();
		ExtList buf = new ExtList();
		ExtList anotherkyes_buf = new ExtList();
		for(int i = 0; i < tfeItems; i++){
			if(!(tfes.get(i) instanceof Grouper)){
				if(flag == true){
					buf.add(tfes.get(i));
				}else{

				}
			}else{
				ExtList another_key = new ExtList();
				another_key = tfes.get(i).get_keys(false);
				if(another_key.size() != 0){
					anotherkyes_buf.add(another_key);
				}
			}
		}
		if(anotherkyes_buf.size() != 0){
			if(buf.size() != 0){
				keys.add(buf);
			}
			for(int j = 0; j < anotherkyes_buf.size(); j++){
				keys.add(anotherkyes_buf.get(j));
			}
		}else{
			if(buf.size() != 0){
				keys.add(buf);
			}
		}

		return keys;

	}
	//added by taji 171102 end
}
