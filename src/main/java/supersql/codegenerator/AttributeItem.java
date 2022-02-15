package supersql.codegenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtHashSet;
import supersql.extendclass.ExtList;
import supersql.parser.FromInfo;

public class AttributeItem implements Serializable{

	private int AttNo;

	private String Image;

	private ExtList UseAtts;

	private ExtHashSet UseTables;

	public boolean IsStr;

	public AttributeItem() {
	}


	public AttributeItem(String str) {
		IsStr = true;
		Image = str;
		UseAtts = new ExtList();
		UseTables = new ExtHashSet();
	}

	public AttributeItem(String str, int no) {
		IsStr = false;
		Image = str;
		AttNo = no;
		UseAtts = new ExtList();
		UseTables = new ExtHashSet();

		StringTokenizer st = new StringTokenizer(str, " 	()+-*/<>=~@");
		while (st.hasMoreTokens()) {
			String ch = st.nextToken();
			StringTokenizer st1 = new StringTokenizer(ch, ".");
			if (st1.countTokens() == 2) {
				//st1 is table.attribute
				//Log.out("[parseString] ch : "+ch);
				UseAtts.add(new String(ch));
				String tbl = new String(st1.nextToken());
				UseTables.add(tbl);
			}else{
				UseAtts.add(new String(ch));
				ArrayList<String> containedTableList = new ArrayList<>();
				for(Map.Entry<String, ExtList> ent: GlobalEnv.tableAtts.entrySet()){
					String tableName = ent.getKey();
					ExtList attributes = ent.getValue();
					if(attributes.contains(ch)){
						containedTableList.add(tableName);
					}
				}
				if(containedTableList.size() > 1){
					Log.err("Attribute <" + ch + "> is contained by more than two tables.");
					Log.err("Please use alias in From clause");
				}else if (containedTableList.size() == 0){
					Log.err("Attribute <" + ch + "> doesn't be contained by any tables.");
				}else{
					UseTables.add(containedTableList.get(0));
				}
			}
		}
	}

	public void debugout() {
		debugout(0);
	}

	public void debugout(int count) {

		Debug dbgout = new Debug();
		dbgout.prt(count, "<AttributeItem No=" + AttNo + " AttName=\"" + Image
				+ "\">");
		dbgout.prt(count + 1, "<UseAtts>");
		dbgout.prt(count + 2, UseAtts.toString());
		dbgout.prt(count + 1, "</useatts>");
		dbgout.prt(count + 1, "<UseTables>");
		dbgout.prt(count + 2, UseTables.toString());
		dbgout.prt(count + 1, "</useTables>");
		dbgout.prt(count, "</AttributeItem>");
	}

	public ExtList<Integer> makesch() {
		ExtList<Integer> outsch = new ExtList<Integer>();
		if (!IsStr) {
			outsch.add(new Integer(AttNo));
		}
		return outsch;
	}

	public ExtList makele0() {

		ExtList attno = new ExtList();

		if (!IsStr) {
			attno.add(new Integer(AttNo));
			Log.out("AttItem le0:" + attno);
		} else {
//			attno.add("const");
			attno.add(Image); //とりあえず for ryosuke add by taji
		}

		return attno;
	}


	@Override
	public String toString() {
		return Image;
	}

	public String getStr(ExtList data_info, int idx) {
		if (IsStr) {
			return Image;
		} else {
			return (data_info.get(AttNo - idx)).toString();
		}
	}

	public int countconnectitem() {
		if (IsStr) {
			return 0;
		} else {
			return 1;
		}
	}

	public ExtHashSet getUseTables() {
		return UseTables;
	}

	public String getSQLimage() {
		return this.Image;
	}

	public String getAttributeSig(FromInfo from) {

		StringBuffer sig = new StringBuffer();

		StringTokenizer st = new StringTokenizer(Image, " 	()+-*/<>=~@", true);
		while (st.hasMoreTokens()) {
			String ch = st.nextToken();
			if (ch.equals(" ") || ch.equals("	")) {
				continue;
			}
			StringTokenizer st1 = new StringTokenizer(ch, ".");
			if (st1.countTokens() == 2) {
				//st1 is table.attribute
				sig.append(from.getOrigTable(st1.nextToken()));
				sig.append(".");
				sig.append(st1.nextToken());
			} else {
				sig.append(ch);
			}
		}

		//Log.out("[Att sig] : " + sig);
		return sig.toString();
	}

	//added by ria 20110913 start
	public ExtList makeschImage()
	{
		ExtList outsch = new ExtList();

		if (!IsStr)
		{
			outsch.add(Image);
		}

		return outsch;
	}
	//added by ria 20110913 end
}

