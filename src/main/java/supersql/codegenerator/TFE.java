package supersql.codegenerator;

import java.io.Serializable;

import supersql.common.Log;
import supersql.extendclass.ExtList;

public class TFE implements ITFE,Serializable {

	protected int id; // SchemaID
	protected boolean orderFlag;
	protected boolean aggregateFlag;
	protected String order;
	protected String aggregate;
	public DecorateList decos;
	public static boolean decocheck;
	protected boolean ggplotFlag;
	protected String ggplot;
	protected boolean ctabFlag;
	protected String ctab;
	
	public TFE() {
		orderFlag = false;
		aggregateFlag = false;
		ctabFlag = false;
		decos = new DecorateList();
	}

	@Override
	public void debugout(int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public ExtList<Integer> makesch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtList makele0() {
		// TODO Auto-generated method stub
		return null;
	}

	public ExtList makele1() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int countconnectitem() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setId(int id) {
//		TFEmatcher.addTFEid_and_TokenPlace(id);	//halken TFEmatcher
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
		
		
	}

	@Override
	public void setOrderBy(String order) {
		orderFlag = true;
		this.order = new String();
		this.order = order;
	}

	@Override
	public void setAggregate(String aggregate) {
		aggregateFlag = true;
		this.aggregate = new String();
		this.aggregate = aggregate;
	}

	@Override
	public void setGGplot(String ggplot) {
		ggplotFlag = true;
		this.ggplot = new String();
		this.ggplot = ggplot;
	}

	@Override
	public void setCtab(String ctab) {
		ctabFlag = true;
		this.ctab = new String();
		this.ctab = ctab;
	}

	//	@Override
//	public void setcheckdeco(boolean decocheck) {
////		TFEmatcher.addTFEid_and_TokenPlace(id);	//halken TFEmatcher
//		this.decocheck = decocheck;
//	}
//
//	public boolean getcheckdeco() {
//		return decocheck;
//	}


	@Override
	public String work(ExtList<ExtList<String>> data_info) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ExtList makeschImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDeco(String key, Object val) {
		decos.put(key, val);
	}

	@Override
	public void addDeco(String name, String value, String condition) {
		decos.put(name, value,condition);
	}

	@Override
	public void setDeco(DecorateList d) {
		decos = d;
	}

	@Override
	public Object createNode(ExtList<ExtList<String>> data_info) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExtList get_keys(boolean flag) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
