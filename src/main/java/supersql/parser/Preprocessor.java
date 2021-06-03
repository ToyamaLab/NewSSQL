/*
 * Created on 2005/02/23 by hanki
 * Modified on 2006/07/01 by hanki
 */
package supersql.parser;

import java.util.ArrayList;

import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Preprocessor {

	private static boolean is_order_by;
	private static boolean is_aggregate;
	private static boolean is_ggplot;
	private static boolean is_R;
	private static boolean is_ctab;

	private StringBuffer tmp;

	private static ExtList order_by_list;
	private static ExtList aggregate_list;
	private static ExtList ggplot_list;
	private static ExtList ggdeco_list;
	private static ExtList ctab_list;

	private static int ggplot_count = 0;
	private static int ggdeco_count = 0;

	/* constructor */
	public Preprocessor() {

	}
	public Preprocessor(String tfe) {

		is_order_by = false;
		is_aggregate = false;
		is_ggplot = false;
		is_ctab = false;

		tmp = new StringBuffer();
		tmp.append(tfe);

		order_by_list = new ExtList();
		aggregate_list = new ExtList();
		ggplot_list = new ExtList();
		ggdeco_list = new ExtList();
		ctab_list = new ExtList();

	}

    public static boolean isOrderBy() {
    	return is_order_by;
    }

    public static boolean isAggregate() {
    	return is_aggregate;
    }

    public static boolean isGGplot() {
    	return is_ggplot ;
    }

    public static boolean isR() {
    	return is_R ;
    }
	public static boolean isCtab() {
		return is_ctab;
	}

	/* store "order by" information into a hashtable */
    public static void putOrderByTable(String order, ExtList sch) {
    	Log.out("order by list "+order+" "+sch);
    	order_by_list.add(order + sch);
	}

	/* return an "order by" hashtable */
    public static ExtList getOrderByTable() {
    	return order_by_list;
	}

	/* store "aggregate functions" information into a list */
	public static void putAggregateList(ExtList sch, String aggregate) {
		aggregate_list.add(sch.get(0) + " " + aggregate);
	}

	/* return an "aggregate functions" list */
	public static ExtList getAggregateList() {
		return aggregate_list;
	}

	/* store "ggplot functions" information into a list */
	public static void putGGplotList(ExtList sch, String ggplot) {
		if (ggplot_count % 2 == 1) {
			ggplot_list.set(ggplot_list.size() - 1, ggplot_list.getExtListString(ggplot_list.size() - 1).split(" ")[0] + " " + sch.get(0) + " " + ggplot_list.getExtListString(ggplot_list.size() - 1).split(" ")[1]);
		}else {
			ggplot_list.add(sch.get(0) + " " + ggplot);
		}
		ggplot_count++;
	}

	public static void putGGplotDeco(ArrayList<String> decos) {
		ggdeco_list.add(":" + decos.get(0));
		for (int i = 1; i < decos.size(); i++) {
			ggdeco_list.set(ggdeco_list.size() - 1, ggdeco_list.getExtListString(ggdeco_list.size() - 1) + ":" + decos.get(i));
		}
		ggdeco_list.set(ggdeco_list.size() - 1, ggdeco_list.getExtListString(ggdeco_list.size() - 1) + ":" + ggdeco_count);
		ggdeco_count++;
	}

	/* return an "ggplot functions" list */
	public static ExtList getGGplotList() {
		return ggplot_list;
	}

	public static ExtList getGGdecoList() {
		return ggdeco_list;
	}

	public static void putCtabList(ExtList sch, String token){
		ctab_list.add(sch.get(0) + " " + token.trim());
	}

	public static  ExtList getCtabList(){
		return ctab_list;
	}

	public boolean setOrderBy(){
		return is_order_by = true;
	}

	public boolean setAggregate(){
		return is_aggregate = true;
	}

	public boolean setGGplot(){
		return is_ggplot = true;
	}

	public boolean setR(){
		return is_R = true;
	}

	public boolean setCtab() {
		return is_ctab = true;
	}
}
