package supersql.parser;

import java.io.Serializable;
import java.util.*;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtHashSet;
import supersql.extendclass.ExtList;

//import common.Log;

public class WhereParse implements Serializable {

	private String line;

	private ExtList useatts;

	private ExtHashSet usetables;

	private final List<String> bookedWords = Arrays.asList("like", "is", "in", "not", "null");

	public WhereParse(String line) {
		this.line = line;
		this.useatts = new ExtList();
		this.usetables = new ExtHashSet();
		this.parseString(line);
	}

	public void parseString(String line) {
		StringBuffer buf = new StringBuffer();

		StringTokenizer st = new StringTokenizer(line, " 	!()+-*/<>=~@");
		while (st.hasMoreTokens()) {
			String ch = st.nextToken();
			StringTokenizer st1 = new StringTokenizer(ch, ".");
			if (ch.startsWith("'") && ch.endsWith("'")) {
				useatts.add(ch);
			} else if (st1.countTokens() == 2) {
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
				if (ch.startsWith("\"") && ch.endsWith("\"") && onlyStartAndEnd) {
					// "e.id"みたいな場合
					ch = ch.substring(1, ch.length() - 1);
					useatts.add(ch);
				} else {
					// その他
					usetables.add(table);
					useatts.add(attribute);
				}
				if (usetables.size() == 0) {
					ch = useatts.getExtListString(0);
					ArrayList<String> containedTableList = new ArrayList<>();
					for(Map.Entry<String, ExtList> ent: GlobalEnv.tableAtts.entrySet()){
						String tableName = ent.getKey();
						ExtList attributes = ent.getValue();
						if(attributes.contains(ch)){
							for (FromTable fromTable: From.getFromItems()) {
								if(fromTable.getTableName().equals(tableName)) {
									containedTableList.add(fromTable.getAlias());
								}
							}
						}
					}
					if(containedTableList.size() > 1){
						Log.err("Attribute <" + ch + "> is contained by more than two tables.");
						Log.err("Please use alias in From clause");
					} else if (containedTableList.size() == 1) {
						usetables.add(containedTableList.get(0));
					}
				}
			} else {
				// 予約語だったらスキップ
				if (bookedWords.contains(ch.toLowerCase())) {
					continue;
				}
				// ""で囲まれてたら除去
				if (ch.startsWith("\"") && ch.endsWith("\"")) {
					ch = ch.substring(1, ch.length() - 1);
				}
				useatts.add(ch);
				ArrayList<String> containedTableList = new ArrayList<>();
				for(Map.Entry<String, ExtList> ent: GlobalEnv.tableAtts.entrySet()){
					String tableName = ent.getKey();
					ExtList attributes = ent.getValue();
					if(attributes.contains(ch)){
						for (FromTable fromTable: From.getFromItems()) {
							if(fromTable.getTableName().equals(tableName)) {
								containedTableList.add(fromTable.getAlias());
							}
						}
					}
				}
				if(containedTableList.size() > 1){
					Log.err("Attribute <" + ch + "> is contained by more than two tables.");
					Log.err("Please use alias in From clause");
				} else if (containedTableList.size() == 1) {
					usetables.add(containedTableList.get(0));
				}
			}
		}
		Log.out("[WhereParse] atts : "+useatts);
		Log.out("[WhereParse] tables : "+usetables);
		Log.out("[WhereParse] line: " + line);
	}

	@Override
	public String toString() {
		return "{ line : " + line + ", useatts : " + useatts + ", usetables : "
				+ usetables + " }";
	}

	public String getLine() {
		return line;
	}

	public ExtList getUseAtts() {
		return useatts;
	}

	public ExtHashSet getUseTables() {
		return usetables;
	}

	public String getWhereSig(FromInfo from) {

		StringBuffer sig = new StringBuffer("[w]");

		StringTokenizer st = new StringTokenizer(line, " 	()+-*/<>=~@", true);
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

		//Log.out("[Where sig] : " + sig);
		return sig.toString();

	}

}
