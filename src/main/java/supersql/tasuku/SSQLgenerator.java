package supersql.tasuku;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSQLgenerator {
	public static String ssqlgenerate(String query, int tfe, int[] tfedate, String css, String value) { StringBuilder ssql = new StringBuilder();
	ssql.append(query);
	ssql = SSQLchange(ssql, tfe, tfedate, css, value); return new String(ssql);
	}
	public static String ssqldelete(String query, int tfe, int[] tfedate, String css) { StringBuilder ssql = new StringBuilder();
	ssql.append(query);
	System.out.println("[ssqldelete]start.");
	int[] TFE_end = tfedate; int TFE_row = tfe - 10000;
	if (TFE_end[TFE_row] == query.length()) {
		System.out.println("[CSSdelete]not exist @");
	} else if (!query.substring(TFE_end[TFE_row], TFE_end[TFE_row] + 1).equals("@")) { System.out.println("[ssqldelete]not exist @");
	} else {
		int brack_sta = TFE_end[TFE_row] + 1;
		int brack_end = query.indexOf("}", TFE_end[TFE_row]); System.out.println("[ssqldelete]’{’ is " + brack_sta); System.out.println("[ssqldelete]’}’ is " + brack_end);
		String bracket = query.substring(brack_sta, brack_end + 1);; System.out.println("[ssqldelete]@" + bracket);
		Pattern p = Pattern.compile(css);
		if (css.equals("[^v]align")) {
			css = "align";
		} else if (css.equals("[^g]color")) { css = "color";
		}
		Matcher m = p.matcher(bracket);
		if (m.find()) {
			int start = m.start();
			if (css.equals("align") || css.equals("color")) {
				start = start + 1;
			}
			int end = m.end();
			System.out.println("[ssqldelete]" + css + " : start=" + start + ", end=" + end); int start1 = bracket.lastIndexOf(",", start); System.out.println("[ssqldelete]start1=" + start1);
			int css_idx_sta = -1;


			int css_idx_end = -1;
			if (start1 != -1) {
				css_idx_sta = brack_sta + start1; } else {
					css_idx_sta = brack_sta + start; }
			String tmp_bracket = bracket.substring(start); System.out.println("[ssqldelete]tmp:" + tmp_bracket); Pattern p2 = Pattern.compile(",");
			Matcher m2 = p2.matcher(tmp_bracket);
			if (m2.find()) {
				int start2 = m2.start();
				System.out.println("[ssqldelete]start2=" + start2);
				tmp_bracket = tmp_bracket.substring(start2);
				System.out.println("[ssqldelete]tmp2:" + tmp_bracket);
				p2 = Pattern.compile("[a-z]+");
				m2 = p2.matcher(tmp_bracket);
				if (m2.find()) {
					int start3 = m2.start();
					System.out.println("[ssqldelete]start3=" + start3);
					css_idx_end = css_idx_sta + start2 + start3;
				} else {
					css_idx_end = css_idx_sta + start2;
				}
			} else {
				css_idx_end = brack_end;
			}
			System.out.println("[ssqldelete]css_sta=" + css_idx_sta + ", css_end=" + css_idx_end); ssql.delete(css_idx_sta, css_idx_end);
			System.out.println("[ssqldelete]new_ssql");
		}
		query = new String(ssql);
		if (query.substring(brack_sta - 1, brack_sta + 2).equals("@{}")) { ssql.delete(brack_sta - 1, brack_sta + 2);
		}
		System.out.println(ssql);
	}
	return new String(ssql); }
	public static int CSSgetAttr(String query, int tfe, int[] tfedate) { System.out.println("[CSSgetAttr]start."); //
	int result = 0;
	int[] TFE_end = tfedate; int TFE_row = tfe - 10000;
	if (TFE_end[TFE_row] == query.length()) {
		System.out.println("[CSSgetAttr]not exist @");
	} else if (!query.substring(TFE_end[TFE_row], TFE_end[TFE_row] + 1).equals("@")) { System.out.println("[CSSgetAttr]not exist @");
	} else {
		int brack_sta = TFE_end[TFE_row] + 1;
		int brack_end = query.indexOf("}", TFE_end[TFE_row]); System.out.println("[CSSgetAttr]’{’ is " + brack_sta); System.out.println("[CSSgetAttr]’}’ is " + brack_end);
		String bracket = query.substring(brack_sta, brack_end + 1);; System.out.println("[CSSgetAttr]@" + bracket);
		Pattern p = Pattern.compile("width"); Matcher m = p.matcher(bracket);
		if (m.find()) {
			result += 1;
		}
		p = Pattern.compile("height");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 10;
		}
		p = Pattern.compile("padding");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 100;
		}
		p = Pattern.compile("[^v]align"); m = p.matcher(bracket);
		if (m.find()) {
			result += 1000;
		}
		p = Pattern.compile("valign");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 10000;
		}


		p = Pattern.compile("bgcolor"); m = p.matcher(bracket);
		if (m.find()) {
			result += 100000;
		}
		p = Pattern.compile("[^g]color");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 1000000;
		}
		p = Pattern.compile("font-size");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 10000000;
		}
		p = Pattern.compile("font-weight");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 100000000;
		}
		p = Pattern.compile("font-style");
		m = p.matcher(bracket);
		if (m.find()) {
			result += 1000000000;
		}
	}
	System.out.println("[CSSgetAttr]result = " + result); System.out.println("[CSSgetAttr]end.");
	return result;
	}
	public static String SSQLgetAttrval (String ssql, int tfe, int[] tfedate, String css) { System.out.println(ssql);
	System.out.println("");
	int[] TFE_end = tfedate; String buf = css;
	int TFE_row = tfe - 10000;
	String val = "";
	if (buf == null) {
		System.out.println("[ssql_css]error");
	} else {
		if (TFE_end[TFE_row] == ssql.length()) {
			val = "-1";
		} else if (!ssql.substring(TFE_end[TFE_row], TFE_end[TFE_row] + 1).equals("@")) { val = "-1";
		} else {
			int brack_sta = TFE_end[TFE_row] + 1;
			int brack_end = ssql.indexOf("}", TFE_end[TFE_row]);
			System.out.println("’{’ is " + brack_sta);
			System.out.println("’}’ is " + brack_end);
			String bracket = ssql.substring(brack_sta, brack_end + 1);; System.out.println("@" + bracket);
			Pattern p = Pattern.compile(buf); Matcher m = p.matcher(bracket); if (m.find()) {
				int start = m.start();
				int end = m.end();
				System.out.println(buf + " : start=" + start + ", end=" + end); int buf_idx_sta = brack_sta + end + 1;
				int buf_idx_end = -1;
				String tmp_bracket = bracket.substring(end + 1); System.out.println(tmp_bracket);
				Pattern p2 = Pattern.compile(",");
				Matcher m2 = p2.matcher(tmp_bracket);
				if (m2.find()) {
					int start2 = m2.start();
					System.out.println("start2=" + start2);
					buf_idx_end = buf_idx_sta + start2;
				} else {
					buf_idx_end = brack_end;
				}
				System.out.println("buf_sta=" + buf_idx_sta + ", buf_end=" + buf_idx_end); val = ssql.substring(buf_idx_sta, buf_idx_end);
			} else {
				val = "-1";
			}
		}
	}
	System.out.println();
	System.out.println("[ssqlgetattrval]:" + val);

	return val;
	}
	public static StringBuilder SSQLchange (StringBuilder ssql, int tfe, int[] tfedate, String css, String value) { String ssql_tmp = new String(ssql);
	System.out.println(ssql);
	System.out.println("");
	int[] TFE_end = tfedate; String buf = css;
	int TFE_row = tfe - 10000;
	if (buf == null) {
		System.out.println("[ssql_css]error");
	} else {
		String buf_val = value;
		if (TFE_end[TFE_row] == ssql.length()) {
			if (buf.equals("[^v]align")) {
				buf = "align";
			} else if (buf.equals("[^g]color")) {
				buf = "color";
			}
			ssql.insert(TFE_end[TFE_row], "@{" + buf + "=" + buf_val + "}");
		} else if (!ssql.substring(TFE_end[TFE_row], TFE_end[TFE_row] + 1).equals("@")) { if (buf.equals("[^v]align")) {
			buf = "align";
		} else if (buf.equals("[^g]color")) {
			buf = "color";
		}
		ssql.insert(TFE_end[TFE_row], "@{" + buf + "=" + buf_val + "}");
		} else {
			int brack_sta = TFE_end[TFE_row] + 1;
			int brack_end = ssql.indexOf("}", TFE_end[TFE_row]);
			System.out.println("’{’ is " + brack_sta);
			System.out.println("’}’ is " + brack_end);
			String bracket = ssql.substring(brack_sta, brack_end + 1);; System.out.println("@" + bracket);
			System.out.println("buf is \"" + buf + "\"");
			Pattern p = Pattern.compile(buf);
			if (buf.equals("[^v]align")) {
				buf = "align";
			} else if (buf.equals("[^g]color")) {
				buf = "color";
			}
			System.out.println("buf is " + buf);
			Matcher m = p.matcher(bracket);
			if (m.find()) {
				int start = m.start();
				int end = m.end();
				System.out.println(buf + " : start=" + start + ", end=" + end);
				int buf_idx_sta = brack_sta + end + 1;
				int buf_idx_end = -1;
				String tmp_bracket = bracket.substring(end + 1); System.out.println(tmp_bracket);
				Pattern p2 = Pattern.compile(",");
				Matcher m2 = p2.matcher(tmp_bracket);
				if (m2.find()) {
					int start2 = m2.start();
					System.out.println("start2=" + start2);
					buf_idx_end = buf_idx_sta + start2;
				} else {
					buf_idx_end = brack_end;
				}
				System.out.println("buf_sta=" + buf_idx_sta + ", buf_end=" + buf_idx_end); ssql.delete(buf_idx_sta, buf_idx_end);
				ssql.insert(buf_idx_sta, buf_val);
			} else {
				ssql.insert(brack_end, ", " + buf + "=" + buf_val);
			}
		}
	}
	//
	System.out.println(); System.out.println("[ssql]\n" + ssql); System.out.println();
	return ssql; }
	public static int[] TFEMatch (String ssql, String[] TFE) { String tmp = ssql;
	int tmp_start = 0;
	int tmp_end = 0;
	int[] TFE_idx = new int[TFE.length];


	for (int i = 0; i < TFE.length; i++) { System.out.println("[TFEMatch(s)]TFE1000" + i + " = " + TFE[i]); Pattern p = Pattern.compile(TFE[i]);
	Matcher m = p.matcher(ssql);
	Matcher m2 = p.matcher(tmp);
	if (m.find() && m2.find()) {
		int start = m2.start();
		int end = m2.end();
		tmp_end = tmp_start + end;
		tmp_start += start;
		TFE_idx[i] = tmp_end;
		tmp = tmp.substring(start);
	}
	}
	for (int i = 0; i < TFE_idx.length; i++) { System.out.println("TFE_end[" + TFE[i] + "] = " + TFE_idx[i]); }
	return TFE_idx;
	}
}
