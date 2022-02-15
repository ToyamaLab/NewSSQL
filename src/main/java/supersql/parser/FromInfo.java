package supersql.parser;


import java.io.Serializable;
import java.util.Hashtable;

import supersql.common.Log;

public class FromInfo implements Serializable {

	private String line;

	private Hashtable from_table;


	public FromInfo(String line) {
		this.line = line;
		this.from_table = new Hashtable();
//		this.makeInfo(line);
		//20210925 yama
		Preprocessor.setFromClause(line);
		Log.out("FromInfo [line] : " + line);
	}

//	public void makeInfo(String line) {
//		Start_Parse.set_from_info_st(line);
//		if(line.equalsIgnoreCase("dbpedia")){
//			Start_Parse.setDbpediaQuery(true);
//		}else if(line.toLowerCase().startsWith("api")){
//			Start_Parse.setJsonQuery(true);
//		}
//		else{
//			StringTokenizer st = new StringTokenizer(line, ",");
//			while (st.hasMoreTokens()) {
//				String ch = st.nextToken().trim();
//				Log.out("ch : " + ch);
//				String decos = new String();
//				if(ch.contains("@")){
//					decos = addDeco(ch.substring(ch.indexOf("@")+1));
//					ch = ch.substring(0,ch.indexOf("@"));
//				}
//
//				FromParse fp = new FromParse(ch);
//				from_table.put(fp.alias, fp);
//
//				if(decos!= "" && decos.equalsIgnoreCase("update")){
//					Start_Parse.set_from_info_st(ch);
//				}
//			}
//		}
//	}

//	private String addDeco(String st){
//		Log.out("@from decoration found@");
//		TFEtokenizer toks = new TFEtokenizer(st);
//		String token = new String();
//		int equalidx;
//		String name = new String();
//		String value = new String();
//		String decos = new String();
//		while(toks.hasMoreTokens()){
//			token = toks.nextToken();
//            Log.out("decoration*looktoken=" + token);
//			if (token.equals("}")) {
//                break;
//            }
//			if (token.equals("{")) {
//                continue;
//            }
//			equalidx = token.indexOf('=');
//            if (equalidx != -1) {
//                // key = idx
//                name = token.substring(0, equalidx);
//                value = token.substring(equalidx + 1);
//                while (toks.hasMoreTokens()) {
//                    token = toks.lookToken();
//                    Log.out("decoration*looktoken=" + token);
//                    if (token.equals(",") || token.equals("}")) {
//                        break;
//                    }
//                    value = value.concat(toks.nextToken());
//                }
//        		decos = value;
//            } else {
//                // key only
//            	while(toks.hasMoreTokens()){
//            		name += token;
//            		token = toks.nextToken();
//            		if(token.equals(",") || token.equals("}")){
//            			break;
//            		}
//            	}
//            	Log.out(name);
//        		decos = name;
//            }
//            if (token.equals("}")) {
//                // end of decoration
//                break;
//            }
//            if (!token.equals(",")) {
//                // not close in "}"
//                System.err
//                        .println("*** Found Illegal Token after Decoration value ***");
//                throw (new IllegalStateException());
//            }
//        }
//        Log.out("@ from decoration end @");
//        return decos;
//	}

	@Override
	public String toString() {
		return "{ line : " + line + ", from_table : " + from_table + " }";
	}

	public String getLine() {
		return line;
	}

	public Hashtable getFromTable() {
		return from_table;
	}

	public String getOrigTable (String alias) {
		return ((FromParse)from_table.get(alias)).getRealName();
	}

}
