package supersql.parser;

import java.io.Serializable;
import java.util.StringTokenizer;

import supersql.extendclass.ExtHashSet;
import supersql.extendclass.ExtList;

//import common.Log;

public class WhereParse implements Serializable {

	private String line;

	private ExtList useatts;

	private ExtHashSet usetables;

	public WhereParse(String line) {
		this.line = line;
		this.useatts = new ExtList();
		this.usetables = new ExtHashSet();
		this.parseString(line);
		//20210925 yama
		Preprocessor.setWhereClause(line);
	}

	public void parseString(String line) {
		StringBuffer buf = new StringBuffer();

		StringTokenizer st = new StringTokenizer(line, " 	()+-*/<>=~@");
		while (st.hasMoreTokens()) {
			String ch = st.nextToken();
			StringTokenizer st1 = new StringTokenizer(ch, ".");
			if (st1.countTokens() == 2) {
				//属性が来た
				//		Log.out("[parseString] ch : "+ch);
				useatts.add(new String(ch));
				String tbl = new String(st1.nextToken());
				usetables.add(tbl);
				//		Log.out("[parseString] tables : "+usetables);
			}
		}
		//	Log.out("[parseString] atts : "+useatts);
		//	Log.out("[parseString] tables : "+usetables);
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
