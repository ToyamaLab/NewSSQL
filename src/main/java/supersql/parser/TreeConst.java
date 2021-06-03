
package supersql.parser;

import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import supersql.common.Log;
import supersql.extendclass.ExtList;

public class TreeConst {
	public static ExtList exttree;
	//	public static List<SSQLParseTree> tree;
	public static boolean terminal_flag = false; // flag for if terminal node?

	public static ExtList createSSQLParseTree(ParseTree t, queryParser recog) {
		String[] ruleNames = recog != null ? recog.getRuleNames() : null;
		List<String> ruleNamesList = ruleNames != null ? Arrays.asList(ruleNames) : null;
		return createSSQLParseTree(t, ruleNamesList);
	}


	public static ExtList createSSQLParseTree(ParseTree t, prefixParser recog) {
		String[] ruleNames = recog != null ? recog.getRuleNames() : null;
		List<String> ruleNamesList = ruleNames != null ? Arrays.asList(ruleNames) : null;
		return createSSQLParseTree(t, ruleNamesList);
	}


	public static ExtList createSSQLParseTree(final ParseTree t, final List<String> ruleNamesList){
		exttree = new ExtList(makeExtList(t, ruleNamesList));
		//		Log.info(exttree);
		return exttree;
	}

	public static ExtList makeExtList(final ParseTree t, final List<String> ruleNamesList){
		ExtList c_1 = new ExtList();
		String s = getNodeText(t, ruleNamesList);

		c_1.add(s);

		ExtList c_2 = new ExtList();
		for (int i = 0; i<t.getChildCount(); i++) {
			ParseTree p = t.getChild(i);
			String k = getNodeText(p, ruleNamesList);

			if(p.getChildCount() != 0){
				c_2.add(makeExtList(t.getChild(i), ruleNamesList));
				continue;
			}
			else if(p.getChildCount() == 0){
				c_2.add(k);
			}
		}
		c_1.add(c_2);

		return c_1;

	}



	public static String getNodeText(ParseTree t, List<String> ruleNames) {
		if ( ruleNames!=null ) {
			if ( t instanceof RuleNode) {
				int ruleIndex = ((RuleNode)t).getRuleContext().getRuleIndex();
				String ruleName = ruleNames.get(ruleIndex);
				terminal_flag  = false;
				return ruleName;
			}
			else if ( t instanceof TerminalNode) {
				Token symbol = ((TerminalNode)t).getSymbol();
				if (symbol != null) {
					String s = symbol.getText();
					terminal_flag = true;
					return s;
				}
			}
		}
		// no recog for rule names
		Object payload = t.getPayload();
		if ( payload instanceof Token ) {
			return ((Token)payload).getText();
		}
		return t.getPayload().toString();
	}

	public static String getMedia(ExtList tree){
		for(int i = 0; i < tree.size(); i++){

		}
		return null;
	}

	public static ExtList getforeach(ExtList tree){
		ExtList atts = new ExtList();
		String att = new String();

		//changed by goto 20161025 for link1/foreach1
		String x = ((ExtList)tree.get(1)).get(0).toString().toLowerCase();
		if(x.equals("foreach") || x.equals("foreach1") || x.equals("parameter")){
			if( ((ExtList)tree.get(1)).get(1) instanceof  String){
				for(int i = 2; i < ((ExtList)tree.get(1)).size(); i+=2){
					ExtList tfe_tree = (ExtList)((ExtList)tree.get(1)).get(i);
					if(tfe_tree.get(0).toString().equals("operand")){
						if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("table_alias")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
							att = att + ((ExtList)tfe_tree.get(1)).get(1).toString();
							att = att + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(2)).get(1)).get(0)).get(1)).get(0).toString();
							Log.info(att);
						}else if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("column_name")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
						}
					}
					atts.add(att);
				}
			}else{
				for(int i = 1; i < ((ExtList)tree.get(1)).size(); i+=2){
					ExtList tfe_tree = (ExtList)((ExtList)tree.get(1)).get(i);
					if(tfe_tree.get(0).toString().equals("operand")){
						if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("table_alias")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
							att = att + ((ExtList)tfe_tree.get(1)).get(1).toString();
							att = att + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(2)).get(1)).get(0)).get(1)).get(0).toString();
							Log.info(att);
						}else if(((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(0).toString().equals("column_name")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe_tree.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
						}
					}
					atts.add(att);
				}
			}
		}


		return atts;
	}


}
