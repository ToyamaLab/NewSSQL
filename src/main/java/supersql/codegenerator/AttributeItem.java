package supersql.codegenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtHashSet;
import supersql.extendclass.ExtList;
import supersql.parser.From;
import supersql.parser.FromInfo;
import supersql.parser.FromTable;

public class AttributeItem implements Serializable{

	private int AttNo;

	private String Image;

	private ExtList UseAtts;

	private ExtHashSet UseTables;

	public boolean IsStr;

	public boolean isConst;

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
		StringTokenizer st1 = new StringTokenizer(str, ".");
		// 'で囲われてたらそれは定数
		if (str.startsWith("'") && str.endsWith("'")) {
			isConst = true;
			UseAtts.add(str);
		} else if (CodeGenerator.sqlfunc_flag > 0) {
			// sql関数だったら
			UseAtts.add(str);
			UseTables.addAll(CodeGenerator.useTablesInSQLFunc);
		} else if (st1.countTokens() == 2) {
			//st1 is table.attribute
			String table = st1.nextToken();
			String attribute = st1.nextToken();
			boolean onlyStartAndEnd = true;
			// エイリアスに""がついてたら除去(Imageでは持ってるのでSQLクエリ作るときはそっち使う)
			if (table.startsWith("\"") && table.endsWith("\"")) {
				table = table.substring(1, table.length() - 1);
				onlyStartAndEnd = false;
			}
			// 属性値に""がついてたら除去
			if (attribute.startsWith("\"") && attribute.endsWith("\"")) {
				attribute = attribute.substring(1, attribute.length() - 1);
				onlyStartAndEnd = false;
			}
			if (str.startsWith("\"") && str.endsWith("\"") && onlyStartAndEnd) {
				// "e.id"みたいな場合
				str = str.substring(1, str.length() - 1);
				UseAtts.add(str);
			} else {
				// その他
				UseTables.add(table);
				UseAtts.add(attribute);
			}
			if (UseTables.size() == 0) {
				str = UseAtts.getExtListString(0);
				ArrayList<String> containedTableList = new ArrayList<>();
				for(Map.Entry<String, ExtList> ent: GlobalEnv.tableAtts.entrySet()){
					String tableName = ent.getKey();
					ExtList attributes = ent.getValue();
					if(attributes.contains(str)){
						for (FromTable fromTable: From.getFromItems()) {
							if(fromTable.getTableName().equals(tableName)) {
								containedTableList.add(fromTable.getAlias());
							}
						}
					}
				}
				if(containedTableList.size() > 1){
					Log.err("Attribute <" + str + "> is contained by more than two tables.");
					Log.err("Please use alias in From clause");
				} else if (containedTableList.size() == 0){
					Log.err("Attribute <" + str + "> isn't contained by any tables.");
				} else {
					UseTables.add(containedTableList.get(0));
				}
			}
		} else {
			// ""で囲まれてたら除去
			if (str.startsWith("\"") && str.endsWith("\"")) {
				str = str.substring(1, str.length() - 1);
			}
			UseAtts.add(str);
			ArrayList<String> containedTableList = new ArrayList<>();
			for(Map.Entry<String, ExtList> ent: GlobalEnv.tableAtts.entrySet()){
				String tableName = ent.getKey();
				ExtList attributes = ent.getValue();
				if(attributes.contains(str)){
					for (FromTable fromTable: From.getFromItems()) {
						if(fromTable.getTableName().equals(tableName)) {
							containedTableList.add(fromTable.getAlias());
						}
					}
				}
			}
			if(containedTableList.size() > 1){
				Log.err("Attribute <" + str + "> is contained by more than two tables.");
				Log.err("Please use alias in From clause");
			}else if (containedTableList.size() == 0){
				Log.err("Attribute <" + str + "> isn't contained by any tables.");
			}else{
				UseTables.add(containedTableList.get(0));
			}
		}

		Log.out("[AttributeItem] useAtts: " + UseAtts);
		Log.out("[AttributeItem] useTables: " + UseTables);
		Log.out("[AttributeItem] Image: " + Image);
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
		}else if (isConst) {
			return data_info.getExtListString(0);
		}else {
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

	public ExtList getUseAtts() {
		return UseAtts;
	}

	public String getSQLimage() {
		// return this.Image;
		String att = this.Image.trim();
		if (GlobalEnv.getDriverName().equals("sqlserver") && att.startsWith("'") && att.endsWith("'")) {
			att = 'N'+att;					//for SQL Server
		}
		return att;
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

