package supersql.parser;


import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;

import supersql.extendclass.ExtList;

public class WhereInfo implements Serializable {

	private ExtList where_clause;
	private String sparqlWhereQuery;

	public WhereInfo() {
		if(Start_Parse.isDbpediaQuery() || Start_Parse.isJsonQuery())
			setSparqlWhereQuery("");
		else{
			this.where_clause = new ExtList();
		}	
	}

	public WhereInfo(String line) {
		if(Start_Parse.isDbpediaQuery() || Start_Parse.isJsonQuery())
			setSparqlWhereQuery(line);
		else{
			this.where_clause = new ExtList();
			this.makeClause(line);
		}
	}

	public void appendWhere(String line) {
		this.makeClause(line);
	}

	public void makeClause(String line) {
		if (line == null)
			return;
		if (line.equals(""))
			return;

		StringBuffer buf = new StringBuffer();
		String skip_ch = " \t\n\r\f;";
		StringTokenizer st = new StringTokenizer(line, "()" + skip_ch, true);
		int paren = 0;
		boolean inQ = false;
		while (st.hasMoreTokens()) {
			String ch = st.nextToken().trim();
			if (skip_ch.indexOf(ch) == -1) {
				if(ch.contains("'")){
					char c;
					for(int j=0; j<ch.length(); j++){
						c = ch.charAt(j);
						if(c == '\'' && !(j>0 && ch.charAt(j-1)=='\'')){	// ' && not ''
							inQ = !inQ;
						}
					}
				}
				
				if (!inQ && ch.equals("(")) {
					paren++;
					buf.append(ch);
				} else if (!inQ && ch.equals(")")) {
					paren--;
					buf.append(ch);
				} else if (!inQ && ch.equalsIgnoreCase("and") && (paren == 0)) {
					//clause分鐃緒申
					WhereParse wp = new WhereParse(buf.toString().trim());
					where_clause.add(wp);
					buf = new StringBuffer();
				} else {
					buf.append(ch + " ");
				}
			}
		}
		//clause分鐃緒申
		WhereParse wp = new WhereParse(buf.toString());
		where_clause.add(wp);
	}

	@Override
	public String toString() {
		return "{ where_clause : " + where_clause + " }";
	}

	public ExtList getWhereClause() {
		return where_clause;
	}

	
	public String getWhereSig(FromInfo from) {
		
		StringBuffer sig = new StringBuffer();
		
		Iterator whei = where_clause.iterator();
		while (whei.hasNext()){
			sig.append(((WhereParse)whei.next()).getWhereSig(from));
			sig.append("@@");
		}

		return sig.toString();
		
	}

	public String getSparqlWhereQuery() {
		return sparqlWhereQuery;
	}

	public void setSparqlWhereQuery(String sparqlWhereQuery) {
		this.sparqlWhereQuery = sparqlWhereQuery;
	}

}
