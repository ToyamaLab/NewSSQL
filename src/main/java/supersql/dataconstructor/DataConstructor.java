//proposed process
package supersql.dataconstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import supersql.codegenerator.AttributeItem;
import supersql.codegenerator.Debug;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.db.ConnectDB;
import supersql.db.GetFromDB;
import supersql.extendclass.ExtList;
import supersql.extendclass.QueryBuffer;
import supersql.parser.*;

public class DataConstructor {

	private ExtList data_info;

	private ArrayList<SQLQuery> sqlQueries = null;
	private QueryDivider qd;
	private String key = null;
	private Attribute keyAtt = null;
	private int col = -1;
	private long[] exectime = new long[4];
	private final int ISDIVIS = 0;
	private final int MAKESQL = 1;
	private final int EXECSQL = 2;
	private final int MKETREE = 3;
	private boolean flag = true;
	public boolean isForest = true;
	public static ArrayList<String> SQL_queries;
	public static String SQL_string; // added by goto 20130306
										// "FROM鐃淑わ申鐃緒申鐃緒申鐃緒申鐃出削申"

	public DataConstructor(Start_Parse parser) {

		ExtList sep_sch;
		ExtList sep_data_info;

		MakeSQL msql = null;

		// Make schema
		sep_sch = parser.sch;
		GlobalEnv.afterMakeSch = System.currentTimeMillis();
//		Log.info("Schema: " + sep_sch);

		// Check Optimization Parameters
		if (GlobalEnv.getOptLevel() == 0 || !GlobalEnv.isOptimizable()
				|| Start_Parse.isDbpediaQuery() || Start_Parse.isJsonQuery()) {
			sqlQueries = null;
		} else {
		// Initialize QueryDivider
			long start = System.nanoTime();

//			try {
//				qd = new QueryDivider(parser);
//
//				if (qd.MakeGraph()) {
//					// if graph was made successfully, divide
//					sqlQueries = qd.divideQuery();
//				}
//			} catch (Exception e) {
//				;
//				// System.out.println( e.getMessage() ); //commented out by goto
//				// 20120620
//			}

			long end = System.nanoTime();
			exectime[ISDIVIS] = end - start;
		}

		// Make SQL
		//make table relation
		WhereInfo wi = parser.whereInfo;
		GlobalEnv.relatedTableSet = new HashMap<>();
		HashMap<String, String> tblList = new HashMap<>();//alias=tblname
		if(From.hasFromItems()){
			List<FromTable> fts = From.getFromItems();
			if(From.hasJoinItems()){
				List<JoinItem> jis = From.getJoinItems();
				FromTable ft = fts.get(0);
				tblList.put(ft.getAlias(), ft.getTableName());
				ArrayList<ArrayList<String>> constraints = new ArrayList<>();
				ArrayList<String> tableList = new ArrayList<>();
				tableList.add(ft.getAlias());
				for (int i = 0; i < jis.size(); i++) {
					JoinItem ji = jis.get(i);
					if(ji.getUseTables().size() > 0){
						for (int j = 0; j < ji.getUseTables().size(); j++) {
							constraints.add(ji.getUseTables().get(j));
						}
					}
					tblList.put(ji.table.getAlias(), ji.table.getTableName());
					tableList.add(ji.table.getAlias());
				}
				for (int i = 0; i < tableList.size(); i++) {
					String alias = tableList.get(i);
					ArrayList<String> relatedTables = new ArrayList<>();
					for (int j = 0; j < constraints.size(); j++) {
						if(constraints.get(j).contains(alias)){
							for (int k = 0; k < constraints.get(j).size(); k++) {
								if (constraints.get(j).get(0).equals("constant_value")){
									break;
								}
								if(constraints.get(j).get(1).equals("constant_value")){
									if(!relatedTables.contains(constraints.get(j).get(1))){
										relatedTables.add("contains_one_side_constraint");
									}
									break;
								}
								if(!constraints.get(j).get(k).equals(alias) && !relatedTables.contains(constraints.get(j).get(k))){
									relatedTables.add(constraints.get(j).get(k));
								}
							}
						}
					}
					Iterator itr = wi.getWhereClause().iterator();
					while(itr.hasNext()){
						WhereParse w = (WhereParse) itr.next();
						if(w.getUseTables().contains(alias)){
							if(w.getUseTables().size() == 1){
								relatedTables.add("contains_one_side_constraint");
							}
							Iterator itr2 = w.getUseTables().iterator();
							while(itr2.hasNext()){
								String name = itr2.next().toString();
								if(!name.equals(alias) && !relatedTables.contains(name)){
									relatedTables.add(name);
								}
							}
						}
					}
					GlobalEnv.relatedTableSet.put(alias, relatedTables);
				}
			}else{
//				System.out.println(fts.size());
				for (int i = 0; i < fts.size(); i++) {
					FromTable ft = fts.get(i);
					String alias = ft.getAlias();
					tblList.put(alias, ft.getTableName());
					Iterator itr = wi.getWhereClause().iterator();
					ArrayList<String> relatedTables = new ArrayList<>();
					while(itr.hasNext()){
						WhereParse w = (WhereParse) itr.next();
						if(w.getUseTables().contains(alias)){
							if(w.getUseTables().size() == 1){
								relatedTables.add("contains_one_side_constraint");
							}
							Iterator itr2 = w.getUseTables().iterator();
							while(itr2.hasNext()){
								String name = itr2.next().toString();
								if(!name.equals(alias)){
									relatedTables.add(name);
								}
							}
						}
					}
					GlobalEnv.relatedTableSet.put(alias, relatedTables);
				}
			}
		}
		if(GlobalEnv.isOrderFrom() || GlobalEnv.isMultiGB()) {
			GetFromDB gfd = new GetFromDB();
			//テーブル毎のメタ情報入手
			//{att_name=att_type, att_name=att_type,...}
			Log.info("Getting table info");
			Long startGTI = System.currentTimeMillis();
			GlobalEnv.attType = new HashMap<>();
			GlobalEnv.tableSize = new HashMap<>();
			for (Map.Entry<String, String> key: tblList.entrySet()) {
				//問い合わせ結果を用いてalias.att=attTypeの形でattTypeに保存
				ExtList ret_result = new ExtList();
				String tblName = key.getValue();
				String alias = key.getKey();
				gfd.getTableSize(tblName, ret_result);
				ret_result = ret_result.getExtList(0);
				boolean attFlag = true;
				if(GlobalEnv.getdbms().equals("hive")) {
					for (int k = 0; k < ret_result.size(); k++) {
						try {
							if (ret_result.getExtListString(k, 0).contains("# Detailed Table Information")) {
								attFlag = false;
							}
							if (attFlag && !ret_result.getExtListString(k, 0).contains("# col_name")) {
								String attName = alias + "." + ret_result.getExtListString(k, 0).trim();
								String typeName = ret_result.getExtListString(k, 1);
								typeName = typeName.toUpperCase();
								GlobalEnv.attType.put(attName, typeName);
							}
							if (ret_result.getExtListString(k, 1).contains("totalSize")) {
								GlobalEnv.tableSize.put(alias, Long.parseLong(ret_result.getExtListString(k, 2).trim()));
							}
						} catch (NullPointerException e) {
							continue;
						}
					}
				}
				if(GlobalEnv.getdbms().equals("postgresql")){
					GlobalEnv.tableSize.put(alias, Long.parseLong(ret_result.getExtListString(0, 1)));
				}
			}
			Long endGTI = System.currentTimeMillis();

			Log.info("Getting table info Time taken: " + (endGTI - startGTI) + "ms");
//			System.out.println("attType:::" + GlobalEnv.attType);
//			System.out.println("tblSize:::" + GlobalEnv.tableSize);
		}
//		System.out.println("relatedTableSet:::"+GlobalEnv.relatedTableSet);

		if ((sqlQueries == null || sqlQueries.size() < 2)
				&& !Start_Parse.isDbpediaQuery()) {
			// if graph was not made successfully or
			// if graph has only one connected component
			// query cannot be divided
			GlobalEnv.beforeMakeSQL = System.currentTimeMillis();
			msql = new MakeSQL(parser);
			GlobalEnv.afterMakeSQL = System.currentTimeMillis();
		}
		sep_data_info = new ExtList();
		GlobalEnv.beforeSchemaToData = System.currentTimeMillis();
		if (Start_Parse.isDbpediaQuery()) {
//			sep_data_info = schemaToData(parser, sep_sch, sep_data_info);
		} else if (Start_Parse.isJsonQuery()) {
//			sep_data_info = schemaToDataFromApi(parser, msql, sep_sch,
//					sep_data_info);
		} else {
			sep_data_info = schemaToData(parser, msql, sep_sch, sep_data_info);
		}
		GlobalEnv.afterSchemaToData = System.currentTimeMillis();
		data_info = sep_data_info;

		Log.info("## Result ##");
		Log.info(data_info);
	}

//	private ExtList schemaToDataFromApi(Start_Parse parser, MakeSQL msql,
//			ExtList sep_sch, ExtList sep_data_info) {
//		String[] fromInfos = Start_Parse.get_from_info_st()
//				.split("api\\(|,|\\)");
//		String url = fromInfos[1];
//		url = url.substring(url.indexOf("'") + 1,
//				url.indexOf("'", url.indexOf("'") + 1));
//		int attno = parser.get_att_info().size();
//		String[] array = new String[attno];
//		int i = 0;
//		for (Object info : parser.get_att_info().values()) {
//			String infoText = ((AttributeItem) info).toString();
//			array[i] = infoText;
//			i++;
//		}
//		sep_data_info = getDataFromApi(url, array, msql, sep_sch);
//		sep_data_info = ma:keTree(sep_sch, sep_data_info);
//		return sep_data_info;
//	}

//	private ExtList getDataFromApi(String url,
//			String[] array, MakeSQL msql, ExtList sep_sch) {
//		ExtList<ExtList<String>> data = new ExtList<ExtList<String>>();
//		String createSql = "";
//		String insertSql = "";
//		try {
//			ArrayList<String> newArray = new ArrayList<String>();
//			String fromLine = "";
//			for(int i = 0; i < array.length; i++){
//				String tableName = array[i].split("\\.")[0];
//				if(!newArray.contains(tableName)){
//					newArray.add(tableName);
//					fromLine += " " + tableName + ",";
//				}
//			}
//			fromLine = fromLine.substring(0, fromLine.length() - 1);
//			for (int i = 0; i < newArray.size(); i++) {
//				ArrayList<String> elements = new ArrayList<String>();
//				String element = newArray.get(i);
//				String itemsUrl = url.replaceAll(":table_name", element);
//				String itemsJson = Utils.sendGet(itemsUrl);
//				JSONArray items = new JSONArray(itemsJson);
//				for (int j = 0; j < items.length(); j++) {
//					JSONObject item = items.getJSONObject(j);
//					Iterator<String> keyIterator = item.keys();
//					if (j == 0) {
//						createSql += "CREATE TABLE " + element + "(";
//						while (keyIterator.hasNext()) {
//							String key = keyIterator.next();
//							createSql += key + ",";
//						}
//						createSql = createSql.substring(0, createSql.length() - 1) + ");\n";
//					}
//					insertSql += "INSERT INTO " + element + " VALUES " + "(";
//					keyIterator = item.keys();
//					while(keyIterator.hasNext()){
//						String key = keyIterator.next();
//						insertSql += "'" + item.get(key).toString() + "',";
//					}
//					insertSql = insertSql.substring(0, insertSql.length() - 1) + ");\n";
//				}
//			}
//
//			msql.setFrom(new FromInfo(fromLine));
//
//			String sqlString = msql.makeSQL(sep_sch);
//
//			SQLManager manager = new SQLManager("jdbc:sqlite::memory:",
//					GlobalEnv.getusername(), "org.sqlite.JDBC", GlobalEnv.getpassword());
//			manager.ExecSQL(sqlString, createSql, insertSql);
//			data = manager.GetBody();
//
//			return data;
//		} catch (Exception e) {
//			Log.err("Could not connect to the Api server");
//			e.printStackTrace();
//			throw new IllegalStateException();
//		}
//	}
//
//	private ExtList schemaToData(Start_Parse parser, ExtList sep_sch,
//			ExtList sep_data_info) {
//		int attno = parser.get_att_info().size();
//		String[] array = new String[attno];
//		int i = 0;
//		for (Object info : parser.get_att_info().values()) {
//			String infoText = ((AttributeItem) info).toString();
//			array[i] = infoText;
//			i++;
//		}
//		sep_data_info = getDataFromDBPedia(parser.get_where_info()
//				.getSparqlWhereQuery(), array);
//		sep_data_info = makeTree(sep_sch, sep_data_info);
//		return sep_data_info;
//	}

	private ExtList schemaToData(Start_Parse parser, MakeSQL msql,
			ExtList sep_sch, ExtList sep_data_info) {

		long start, end;
		if (msql != null) {
			GlobalEnv.sep_sch_bak = new ExtList();
			copySepSch(sep_sch, GlobalEnv.sep_sch_bak);
			GlobalEnv.beforeGetFromDB = System.currentTimeMillis();
			getFromDB(msql, sep_sch, sep_data_info);
			GlobalEnv.afterGetFromDB = System.currentTimeMillis();
			//tbt add 1807118
			//make nested_tuples for each trees from forest
			if(GlobalEnv.isMultiQuery()){
//				System.out.println("isCtab:::"+Preprocessor.getCtabList());
				for (int i = 0; i < GlobalEnv.sameTree_set.size(); i++) {
					ArrayList<QueryBuffer> qb = GlobalEnv.sameTree_set.get(i);
					for (int j = 0; j < qb.size(); j++) {
						QueryBuffer q = qb.get(j);
						if(Preprocessor.isCtab()){
//							Log.info("Making All Pattern");
							Long makeAllPatternStart = System.currentTimeMillis();
							q.makeAllPattern();
							Long makeAllPatternEnd = System.currentTimeMillis();
//							Log.info("Make All Pattern Time taken: " + (makeAllPatternEnd - makeAllPatternStart) + "ms");
						}
						ExtList flatResult = new ExtList(q.getResult());
//						q.showDebug("before: ");
						ExtList sep_bak = new ExtList();
						copySepSch(q.sep_sch, sep_bak);
//						Log.info("Making Tree");
						Long makeTreeStart = System.currentTimeMillis();
						q.constructedResult = makeTree(q.sep_sch, flatResult);
						Long makeTreeEnd = System.currentTimeMillis();
//						Log.info("Make Tree Time taken: " + (makeTreeEnd - makeTreeStart) + "ms");
						q.sep_sch = sep_bak;
//						q.showDebug("after: ");
					}
				}
				GlobalEnv.sameForest_set = new ArrayList<>();
				for (int i = 0; i < GlobalEnv.sameTree_set.size(); i++) {
					ArrayList<QueryBuffer> qb = GlobalEnv.sameTree_set.get(i);
					for (int j = 0; j < qb.size(); j++) {
//						qb.get(j).showDebug();
						if(GlobalEnv.sameForest_set.size() < qb.get(j).forestNum + 1){
							ArrayList<QueryBuffer> qb_list = new ArrayList<>();
							qb_list.add(qb.get(j));
							GlobalEnv.sameForest_set.add(qb.get(j).forestNum, qb_list);
						}else{
							GlobalEnv.sameForest_set.get(qb.get(j).forestNum).add(qb.get(j));
						}
					}
				}
				ArrayList<QueryBuffer> last = new ArrayList<>();
//				System.out.println();
//				Log.info("Merging Same Forest");
				Long mergeSameForestStart = System.currentTimeMillis();
				for (int i = 0; i < GlobalEnv.sameForest_set.size(); i++) {
					ArrayList<QueryBuffer> qb = GlobalEnv.sameForest_set.get(i);
//					System.out.println("---Forest---");
					QueryBuffer resultQB = qb.get(0);
//					System.out.println();
//					Log.info("Merging From This QueryBuffer");
					for (int j = 1; j < qb.size(); j++) {
						QueryBuffer q = qb.get(j);
//						System.out.println();
//						Log.info("\tMerging forest!!!");
//						q.showDebug("\t");
						sep_sch = GlobalEnv.sep_sch_bak;
						Long mqbStart = System.currentTimeMillis();
						resultQB = mergeQueryBuffer(q, resultQB, sep_sch);
						Long mqbEnd = System.currentTimeMillis();
//						Log.info("\tEnd forest!!!");
//						Log.info("\tTime taken: " + (mqbEnd - mqbStart) + "ms");
//						System.out.println("resultQB");
					}
					last.add(resultQB);
//					System.out.println("++++++");
				}
				Long mergeSameForestEnd = System.currentTimeMillis();
//				Log.info("Merge Same Forest Time taken: " + (mergeSameForestEnd - mergeSameForestStart) + "ms");
				sep_data_info.clear();
				if(last.size() == 1 && last.get(0).sep_sch.size() > 1){
					sep_data_info = last.get(0).constructedResult;
				}else {
					for (int i = 0; i < last.size(); i++) {
						sep_data_info.add(last.get(i).constructedResult.get(0));
					}
				}
//				System.out.println("sep_data_info::"+sep_data_info);

			} else {
				ExtList result = new ExtList();
				if(GlobalEnv.isNoForestDiv() || sep_sch.size() == 1 || !isForest){
					if(GlobalEnv.isOrderFrom()){
						for (ArrayList<QueryBuffer> qb: GlobalEnv.qbs){
							for(QueryBuffer q: qb){
								ExtList tmp_sep2 = new ExtList();
								copySepSch(q.sep_sch, tmp_sep2);
								ExtList flatResult = new ExtList(q.getResult());
								result = makeTree(tmp_sep2, flatResult);
							}
						}
					}else {
						result = makeTree(sep_sch, (ExtList) sep_data_info.get(0));
					}
				} else {
					if(GlobalEnv.isMultiGB() || GlobalEnv.isOrderFrom()) {
						for (ArrayList<QueryBuffer> qb: GlobalEnv.qbs){
							for(QueryBuffer q: qb){
								ExtList tmp_sep2 = new ExtList();
								copySepSch(q.sep_sch, tmp_sep2);
								ExtList flatResult = new ExtList(q.getResult());
								ExtList tmp = makeTree(tmp_sep2, flatResult);
								result.add(tmp.get(0));
							}
						}
					}else {
						for (int i = 0; i < sep_data_info.size(); i++) {
							ExtList tmp_sep = new ExtList();
							ExtList tmp_sep2 = new ExtList();
							copySepSch((ExtList) sep_sch.get(i), tmp_sep2);
//					count_ini = 0;
//					initializeSepSch(tmp_sep2);
							tmp_sep.add(tmp_sep2);
							ExtList tmp = makeTree(tmp_sep, (ExtList) sep_data_info.get(i));
							result.add(tmp.get(0));
						}
					}
				}
				sep_data_info.clear();
				sep_data_info = result;
			}
			GlobalEnv.afterMakeTree = System.currentTimeMillis();
			//tbt end
		} else {
			getTuples(sep_sch, sep_data_info);
			start = System.nanoTime();
			Log.info("I'm in not msql ");
			sep_data_info = MakeTree(qd.getSchema());
//			 System.out.println(sep_data_info);
			end = System.nanoTime();

			exectime[MKETREE] = end - start;
		}

		return sep_data_info;

	}

	ArrayList<Integer> plist;

	private QueryBuffer mergeQueryBuffer(QueryBuffer qb1, QueryBuffer qb2, ExtList sep_sch) {
//		System.out.println("!!!!!qb1!!!!!");
//		qb1.showDebug();
//		System.out.println("!!!!!qb2!!!!!");
//		qb2.showDebug();
		ExtList sep_sch1 = qb1.sep_sch;
		ExtList sep_sch2 = qb2.sep_sch;

		ExtList cr1 = qb1.constructedResult;
		ExtList cr2 = qb2.constructedResult;

		ExtList res1 = qb1.getResult();
		ExtList res2 = qb2.getResult();

		QueryBuffer retQB;
//		System.out.println("sep_sch1:::"+(ExtList)sep_sch1.get(0));
//		System.out.println("sep_sch2:::"+(ExtList)sep_sch2.get(0));
//		ExtList resultss = mergeSepSch((ExtList)sep_sch1.get(0), (ExtList)sep_sch2.get(0));
		ExtList resultss = new ExtList();
		if(isForest) {
			copySepSch((ExtList) sep_sch.get(qb1.forestNum), resultss);
		}else{
			copySepSch(sep_sch, resultss);
		}
//		System.out.println("resultss:::"+resultss);
		for (int i = 0; i < resultss.unnest().size(); i++) {
			if(!(sep_sch1.unnest().contains(resultss.unnest().get(i))) && !(sep_sch2.unnest().contains(resultss.unnest().get(i)))){
				resultss.removeContent(resultss.unnest().get(i));
				i--;
			}
		}
		while(resultss.removeNull());
//		System.out.println("mergedSepSch:::"+resultss);
		ExtList sep = new ExtList();
		sep.add(resultss);
		retQB = new QueryBuffer(resultss.unnest());
		retQB.sep_sch = sep;
		ExtList result = mergeResult(resultss, sep_sch1, sep_sch2, cr1, cr2, res1, res2);
		retQB.constructedResult = result;
		return retQB;
	}

	public static void copySepSch(ExtList src, ExtList dist){
		for(int i = 0; i < src.size(); i++) {
			try {
				ExtList child = (ExtList)src.get(i);
				ExtList tmp = new ExtList();
				dist.add(tmp);
				copySepSch(child, (ExtList)dist.get(i));
			}catch (ClassCastException e){
				dist.add(i, src.get(i));

			}
		}
	}

	private ExtList mergeResult(ExtList dist_sep, ExtList sep_sch1, ExtList sep_sch2, ExtList cr1, ExtList cr2, ExtList res1, ExtList res2) {

		HashMap<ExtList, ExtList> dep_set = new HashMap<>();
		ArrayList<Integer> same_set = new ArrayList();


		for (int i = 0; i < sep_sch1.unnest().size(); i++) {
			for (int j = 0; j < sep_sch2.unnest().size(); j++) {
				if((int)sep_sch1.unnest().get(i) == (int)sep_sch2.unnest().get(j)){
					same_set.add((int)sep_sch1.unnest().get(i));
					break;
				}
			}
		}

		ExtList tmp_sep_sch1 = new ExtList();
		try {
			copySepSch((ExtList) sep_sch1.get(0), tmp_sep_sch1);
		}catch (ClassCastException e){
			copySepSch(sep_sch1, tmp_sep_sch1);
		}
		for (int i = 0; i < res1.size(); i++) {
			ExtList tmp_res = new ExtList((ExtList)res1.get(i));
			ExtList same_list = new ExtList();
			ExtList value_list = new ExtList();
			for (int j = 0; j < tmp_res.size(); j++) {
				if(same_set.contains((int)tmp_sep_sch1.unnest().get(j))){
					same_list.add(tmp_res.get(j));
				}else{
					value_list.add(tmp_res.get(j));
				}
			}
			if(dep_set.containsKey(same_list)){
				ExtList value = dep_set.get(same_list);
				value.add(value_list);
				dep_set.put(same_list, value);
			}else{
				ExtList tmp = new ExtList();
				tmp.add(value_list);
				dep_set.put(same_list, tmp);
			}
		}
//		System.out.println("dep_set:::"+dep_set);

		for (int i = 0; i < same_set.size(); i++) {
			tmp_sep_sch1.removeContent(same_set.get(i));
		}
//		System.out.println("tmp_sep_sch1::"+tmp_sep_sch1);

//		ExtList prev = new ExtList();
		while(tmp_sep_sch1.size() == 1){
//			prev = tmp_sep_sch1;
			try{
				tmp_sep_sch1 = (ExtList)tmp_sep_sch1.get(0);
			}catch(ClassCastException e){
				break;
			}
		}
//		tmp_sep_sch1 = prev;
		ExtList tmp_sep_sch_new = new ExtList();

		ExtList att_list = dist_sep.unnest();
//		System.out.println("dist_sep:::"+dist_sep);
		HashMap<Integer, ExtList> pathSet_dist = new HashMap<>();
		for (int i = 0; i < att_list.size(); i++) {
			ExtList path = new ExtList();
			path = findPath((int)att_list.get(i), dist_sep);
			pathSet_dist.put((int)att_list.get(i), path);
		}
//		System.out.println("passSet_dist:::"+pathSet_dist);
		HashMap<Integer, ExtList> pathSet_sep2 = new HashMap<>();
		for (int i = 0; i < att_list.size(); i++) {
			ExtList path = new ExtList();
			ExtList targ = new ExtList();
			if(isForest){
				targ = (ExtList)sep_sch2.get(0);
			}else{
				targ = sep_sch2;
			}
			path = findPath((int)att_list.get(i), targ);
			pathSet_sep2.put((int)att_list.get(i), path);
		}
//		System.out.println("passSet_sep2:::"+pathSet_sep2);
//		System.out.println("same_set:::"+same_set);

		tmp_sep_sch_new.add(tmp_sep_sch1);
		ExtList tmp_sep_sch1_bak = new ExtList();
		ExtList tmp_sep_sch_new_bak = new ExtList();
		copySepSch(tmp_sep_sch1, tmp_sep_sch1_bak);
		copySepSch(tmp_sep_sch_new, tmp_sep_sch_new_bak);
		for(Map.Entry<ExtList, ExtList> entry: dep_set.entrySet()){
			ExtList tmp_sep_sch_new_restore = new ExtList();
			copySepSch(tmp_sep_sch_new_bak, tmp_sep_sch_new_restore);
			tmp_sep_sch_new = tmp_sep_sch_new_restore;
			ExtList newValue = makeTree(tmp_sep_sch_new, entry.getValue());
			dep_set.put(entry.getKey(), newValue);
		}
//		System.out.println("tmp_sep_sch_new:::"+tmp_sep_sch_new);
//		System.out.println("dep_set_new:::"+dep_set);
		tmp_sep_sch1 = tmp_sep_sch1_bak;

		for(Map.Entry<ExtList, ExtList> entry: dep_set.entrySet()) {
			ExtList idxList = new ExtList();
			ExtList key = entry.getKey();
			ExtList idxList_list = new ExtList();
			for (int i = 0; i < key.size(); i++) {
//				System.out.println("Path:::"+pathSet_sep2.get(same_set.get(i)));

//				System.out.println("target:::"+key.get(i).toString());
				ExtList cr2_input = new ExtList();
				if(isForest){
					cr2_input = (ExtList)cr2.get(0);
				}else{
					cr2_input = cr2;
				}
//				System.out.println("cr2:::"+cr2_input);
				idxList = findSameIndex(pathSet_sep2.get(same_set.get(i)), cr2_input, key.get(i).toString());
//				System.out.println("idxList:::"+idxList);
				ExtList idxList_af = idxList2Path(idxList);
				idxList_list.add(idxList_af);
			}
//			System.out.println("idxList_list:::"+idxList_list);
			ExtList stan = new ExtList();
			ExtList same_result = new ExtList();
			for (int i = 1; i < idxList_list.size(); i++) {
				if(i != 1){
					stan.clear();
					copySepSch(same_result, stan);
					same_result.clear();
				}else{
					copySepSch(idxList_list.getExtList(0), stan);
				}
				ExtList comp = idxList_list.getExtList(i);
				for (int j = 0; j < stan.size(); j++) {
					ExtList s = new ExtList();
					try {
						s = (ExtList) stan.get(j);
					}catch(ClassCastException e){
						s.add(stan.get(j));
					}
					for (int k = 0; k < comp.size(); k++) {
						ExtList c = new ExtList();
						try {
							c = (ExtList) comp.get(k);
						}catch(ClassCastException e) {
							c.add(comp.get(k));
						}
						int min = Math.min(s.size(), c.size());
						boolean same_flag = true;
						for (int l = 0; l < min; l++) {
							if((int)s.get(l) != (int)c.get(l)){
								same_flag = false;
								break;
							}
						}
						if(same_flag){
							if(s.size() >= c.size()){
								same_result.add(s);
							}else{
								same_result.add(c);
							}
						}
					}
				}
			}
			if(same_set.size() == 1){
				same_result = idxList_list.getExtList(0);
			}
//			System.out.println("same_result:::"+same_result);
			tmp_sep_sch1.unnest().sort(Comparator.naturalOrder());
//			System.out.println("tmp_sep_sch1::"+tmp_sep_sch1);
//			System.out.println("cr2:::"+cr2);
			int landmark = 0;
			for (int i = 0; i < tmp_sep_sch1.size(); i++) {
				if(!(tmp_sep_sch1.get(i) instanceof ExtList)){
					landmark = (int)tmp_sep_sch1.get(i);
					break;
				}
			}

			ExtList path = pathSet_dist.get(landmark);
//			System.out.println("path:::"+path);
			ExtList value = entry.getValue();
			ExtList insertPath = new ExtList();
			insertPath.add(0);
			for (int j = 0; j < same_result.size(); j++) {
				ExtList idx = same_result.getExtList(j);
//				System.out.println("idx:::"+idx);
//				if(idx.size() >= path.size()){
				for (int k = 0; k < path.size() - 1; k++) {
					if(k >= idx.size()){
						insertPath.add(0);
					}else {
						insertPath.add(idx.get(k));
					}
					insertPath.add(path.get(k));
				}
				insertPath.remove(insertPath.size() - 1);
//				System.out.println("insertPath:::"+insertPath);
				cr2.getExtList(insertPath).add((int)path.get(path.size() - 2), value.get(0));
//				}else{
//					for (int k = 0; k < idx.size(); k++) {
//						insertPath.add(idx.get(k));
//						insertPath.add(path.get(k));
//						}
//						for (int k = idx.size() - 1; k < ; k++) {
//
//						}
//					}
			}
		}
//		System.out.println("cr2_result:::"+cr2);
		return cr2;
	}

	private ExtList idxList2Path(ExtList idxList) {
		ExtList num = new ExtList();
		ExtList tmp = new ExtList();
		ExtList result = new ExtList();
		boolean onlyNumFlag = true;

		for (int i = 0; i < idxList.size(); i++) {
			if(idxList.get(i) instanceof ExtList){
				ExtList tmp2 = idxList2Path((ExtList)idxList.get(i));
				for (int j = 0; j < tmp2.size(); j++) {
					tmp.add(tmp2.get(j));
				}
				onlyNumFlag = false;
			}else{
				ExtList tmp3 = new ExtList();
				tmp3.add(idxList.get(i));
				num.add(tmp3);
			}
		}
		if (onlyNumFlag){
			return num;
		}else{
			if(num.size() > 0){
				for (int i = 0; i < tmp.size(); i++) {
					ExtList t = (ExtList)tmp.get(i);
					for (int j = 0; j < num.size(); j++) {
						ExtList n = (ExtList)num.get(j);
						t.add(0, n.get(0));
						result.add(t);
					}
				}
				return result;
			}else{
				return tmp;
			}
		}
	}


	private ExtList findSameIndex(ExtList path, ExtList constructedList, String target) {
		ExtList idxList = new ExtList();
		for (int i = 0; i < constructedList.size(); i++) {
			if(path.size() == 1){
				try{
					String compare = constructedList.getExtListString(i, (int)path.get(0));
//					System.out.println("compare:::"+compare);
					if(compare.equals(target)){
						idxList.add(i);
					}
				}catch (NullPointerException e){
					continue;
				}
			}else{
				int nextPath = (int)path.get(0);
				ExtList path2 = new ExtList();
				for (int j = 1; j < path.size(); j++) {
					path2.add(path.get(j));
				}
				ExtList tmp = new ExtList();
				tmp.add(i);
				tmp.add(/*nextPath,*/ findSameIndex(path2, constructedList.getExtList(i, nextPath), target));
				idxList.add(tmp);
			}
		}
		return idxList;
	}

	private ExtList findPath(int num, ExtList sep_sch) {
		ExtList pass = new ExtList();
		for (int i = 0; i < sep_sch.size(); i++) {
			if(sep_sch.get(i) instanceof ExtList){
				pass = findPath(num, (ExtList)sep_sch.get(i));
				if (pass.size() > 0){
					pass.add(0, i);
					break;
				}else{
					continue;
				}
			}else{
				if((int)sep_sch.get(i) == num){
					pass.add(i);
					break;
				}else{
					continue;
				}
			}
		}
		return pass;
	}


	static int count = 0;


	private ExtList[] getTuples(ExtList sep_sch, ExtList sep_data_info) {

		for(int i=0; i < sep_sch.size(); i++){
			Log.info("getsep_sch "+ sep_sch.get(i));
			Log.info("getsep_data_info "+ sep_data_info.get(i));
		}
		long start, end;
		start = System.nanoTime();

		ExtList[] table;
		GetFromDB gfd;
		int comp_size;

		comp_size = sqlQueries.size();
		table = new ExtList[comp_size];

		if (GlobalEnv.isMultiThread()) {
			System.out.println("[Enter MultiThread mode]");
			ConnectDB cdb = new ConnectDB(GlobalEnv.geturl(),
					GlobalEnv.getusername(), GlobalEnv.getDriver(),
					GlobalEnv.getpassword());
			System.out.println(GlobalEnv.geturl() + GlobalEnv.getusername()
					+ GlobalEnv.getpassword());

			cdb.setName("CDB1");
			cdb.run();

			gfd = new GetFromDB(cdb);
		}

		else {
			gfd = new GetFromDB();
		}

		long time = 0;

		// changed by goto 20120630
		Log.info("sqlQueries.size() = " + sqlQueries.size());
		for (int i = 0; i < sqlQueries.size() ; i++) {
			table[i] = new ExtList();

			long time1 = System.nanoTime();
			String s = sqlQueries.get(i).getString();
			time += (System.nanoTime() - time1);

			gfd.execQuery(s, table[i]);
			sqlQueries.get(i).setResult(table[i]);
		}

		gfd.close();
		end = System.nanoTime();

		exectime[EXECSQL] = end - start - time;
		exectime[MAKESQL] = time;

		Log.out("## DB result ##");

		return table;

	}

	/*
	[0, [1], [2], 3]を[[0, [1], 3], [0, [2], 3]]に分割する
	 */
	private ExtList divideSepSch(ExtList list){
		ExtList sameList = new ExtList();
		ExtList result = new ExtList();
		Boolean noExtList = true;
		for (int i = 0; i < list.size(); i++) {
			Object factor = list.get(i);
			if(factor instanceof ExtList){
				noExtList = false;
				ExtList tmp = divideSepSch((ExtList)factor);
				for (int j = 0; j < tmp.size(); j++) {
					ExtList oneSepSch = new ExtList(sameList);
					oneSepSch.add(tmp.get(j));
					result.add(oneSepSch);
				}
			}else{
				sameList.add(factor);
				if(result.size() > 0){
					for (int j = 0; j < result.size(); j++) {
						((ExtList)result.get(j)).add(Integer.parseInt(factor.toString()));
					}
				}
			}
		}
		if(noExtList){
			ExtList tmp = new ExtList();
			tmp.add(list);
			return  tmp;
		}else{
			return result;
		}
	}

	private QueryBuffer mergeSameTreeQueryBuffer(QueryBuffer qb1, QueryBuffer qb2, ExtList sep_sch) {
//		System.out.println("!!!!!qb1!!!!!");
//		qb1.showDebug();
//		System.out.println("!!!!!qb2!!!!!");
//		qb2.showDebug();
		ExtList sep_sch1 = qb1.sep_sch;
		ExtList sep_sch2 = qb2.sep_sch;


		QueryBuffer retQB;

//		System.out.println("sep_sch1:::"+(ExtList)sep_sch1.get(0));
//		System.out.println("sep_sch2:::"+(ExtList)sep_sch2.get(0));
		ExtList sep_tmp = new ExtList();
		copySepSch(sep_sch, sep_tmp);
		for (int i = 0; i < sep_tmp.unnest().size(); i++) {
			if (!sep_sch1.unnest().contains(sep_tmp.unnest().get(i)) && !sep_sch2.unnest().contains(sep_tmp.unnest().get(i))) {
				sep_tmp.removeContent(sep_tmp.unnest().get(i));
				i--;
			}
		}
		while(sep_tmp.removeNull());
		ExtList resultss = new ExtList();
		resultss = sep_tmp;

//		ExtList resultss = mergeSepSch((ExtList)sep_sch1.get(0), (ExtList)sep_sch2.get(0));
//		System.out.println("mergedSepSch:::"+resultss);
//		ExtList sep = new ExtList();
//		sep.add(resultss);
		retQB = new QueryBuffer(resultss.unnest());
		retQB.sep_sch = resultss;
		// attsを混ぜる
		HashMap<Integer, AttributeItem> mergedAtts = new HashMap();
		mergedAtts.putAll(qb1.getAtts());
		mergedAtts.putAll(qb2.getAtts());
		retQB.setAtts(mergedAtts);

		ExtList synthesizedResult = new ExtList();
		ExtList attributeList = new ExtList();

		ExtList compResult = qb2.getResult();
		ExtList compSchf = qb2.getSchf();
		ArrayList<Integer> sameAttNum = new ArrayList<>();
//					System.out.println("compResult:"+compResult);
//					System.out.println("compSchf:"+compSchf);
//					System.out.println("synthesizedResult:"+synthesizedResult);
//					System.out.println("attributeList:"+attributeList);
				//同じ属性番号探し
		for (int j = 0; j < compSchf.size(); j++) {
			for (int k = 0; k < qb1.getSchf().size(); k++) {
				if(Integer.parseInt(compSchf.get(j).toString()) == Integer.parseInt(((ExtList) qb1.getSchf()).get(k).toString())){
					sameAttNum.add(Integer.parseInt(compSchf.get(j).toString()));
				}
			}
		}
		//属性番号が同じとこを比較
		ExtList tmpResultSet = new ExtList();
		ExtList tmpSchf = new ExtList();
		for (int j = 0; j < compResult.size(); j++) {
//						System.out.println("comp:"+compResult.get(j));
			for (int k = 0; k < qb1.getResult().size(); k++) {
//						System.out.println("qb:"+qb.getResult().get(k));
				boolean differentFlag = false;
				for (int l = 0; l < sameAttNum.size(); l++) {
					if(!((ExtList)compResult.get(j)).get(compSchf.indexOf(sameAttNum.get(l))).toString().equals(((ExtList)qb1.getResult().get(k)).get(qb1.getSchf().indexOf(sameAttNum.get(l))).toString())){
						differentFlag = true;
						break;
					}
				}
//							System.out.println("flag:"+differentFlag);
				if(differentFlag){
					//違う部分があったら次
					continue;
				}else{
					//全部同じだったら合成
					ExtList tmpResult = new ExtList();
					int max = Math.max(Integer.parseInt(qb1.getSchf().get(qb1.getSchf().size() - 1).toString()), Integer.parseInt(compSchf.get(compSchf.size() - 1).toString()));
//								System.out.println("max:"+max);
//								System.out.println("qb.scfh:"+qb.getSchf());
//								System.out.println("qb.result:"+qb.getResult());
//								System.out.println("compschf:"+compSchf);
//								System.out.println("compResult:"+compResult);
					for (int l = 0; l <= max; l++) {
						if(compSchf.contains(l)){
							tmpResult.add(((ExtList)compResult.get(j)).get(compSchf.indexOf(l)));
							if(!tmpSchf.contains(l)) {
								tmpSchf.add(l);
							}
						}else if(qb1.getSchf().contains(l)){
							tmpResult.add(((ExtList)qb1.getResult().get(k)).get(qb1.getSchf().indexOf(l)));
							if(!tmpSchf.contains(l)) {
								tmpSchf.add(l);
							}
						}
					}
							//入れ方考えないと
							//tmpResultを溜め込んで後で全部やったら更新
//								System.out.println("Synth:"+synthesizedResult);
//								System.out.println("tmpResult:"+tmpResult);
//								System.out.println("attL:"+attributeList);
//								System.out.println("tmpS:"+tmpSchf);
					tmpResultSet.add(tmpResult);
				}
			}
//						System.out.println("tmpResultSet:"+tmpResultSet);
		}
		retQB.setResult(tmpResultSet);
		return retQB;
	}

	private ExtList getFromDB(MakeSQL msql, ExtList sep_sch,
			ExtList sep_data_info) {
		System.out.println("sep_sch: " + sep_sch.get(0));
		// MakeSQL
		long start, end;
		start = System.nanoTime();
		//tbt add 180601
		// Connect to DB
		start = System.nanoTime();

		GetFromDB gfd;
		if (GlobalEnv.isMultiThread()) {
			System.out.println("[Enter MultiThread mode]");
			ConnectDB cdb = new ConnectDB(GlobalEnv.geturl(),
					GlobalEnv.getusername(), GlobalEnv.getDriver(),
					GlobalEnv.getpassword());
			System.out.println(GlobalEnv.geturl() + GlobalEnv.getusername()
					+ GlobalEnv.getpassword());

			cdb.setName("CDB1");
			cdb.run();

			gfd = new GetFromDB(cdb);
		} else {
			gfd = new GetFromDB();
		}

		GlobalEnv.qbs = new ArrayList<>();
		long makesql_start = 0;
		int treeNum = sep_sch.size();
		for (int i = 0; i < treeNum; i++) {
			if(!(sep_sch.get(i) instanceof ExtList)){
				isForest = false;
			}
		}
//		System.out.println("isForest:::"+isForest);
 		GlobalEnv.qbs = new ArrayList<>();
		if (!GlobalEnv.isMultiQuery()) {
			makesql_start = System.currentTimeMillis();
			SQL_queries = new ArrayList<>();
			if(GlobalEnv.isNoForestDiv() || !isForest){
				if(GlobalEnv.isOrderFrom()){
					msql.makeSQL(sep_sch);
				}else {
					SQL_queries.add(msql.makeSQL(sep_sch));
				}
			}else {
				for (int i = 0; i < treeNum; i++) {
					ExtList tmp = new ExtList();
					ExtList tmp2 = new ExtList();
					copySepSch((ExtList) sep_sch.get(i), tmp2);
					tmp.add(tmp2);
					if(GlobalEnv.isMultiGB() || GlobalEnv.isOrderFrom()){
						msql.makeSQL(tmp);
					}else {
						SQL_queries.add(msql.makeSQL(tmp));
					}
				}
			}
//			SQL_string = msql.makeSQL(sep_sch);
			long makesql_end = System.currentTimeMillis();
//			System.out.println();
			Log.info("Make SQL Time:" + (makesql_end - makesql_start) + "ms");
			for (int i = 0; i < SQL_queries.size(); i++) {
				Log.out("Query is : " + SQL_queries.get(i));
			}
		} else {
			//if the query contains aggregations, divide query.
			makesql_start = System.currentTimeMillis();
			for (int i = 0; i < sep_sch.size(); i++) {
				ExtList result;
				ArrayList<QueryBuffer> qb = new ArrayList<>();
				boolean singleValue = false;
				if (sep_sch.get(i) instanceof ExtList) {
					result = divideSepSch(sep_sch.getExtList(i));
				} else {
					ExtList tmp = new ExtList();
					final String att = sep_sch.get(i).toString();
					tmp.add(sep_sch.get(i));
					result = divideSepSch(tmp);
					// 集約じゃなかったら
					if (Preprocessor.getAggregateList().stream().filter(s -> s.toString().split(" ")[0].equals(att)).count() == 0) singleValue = true;
				}
				/*
				 最も低レベルの場所に属性 or 文字列が置いてあったら特殊な場合として扱う
				 sep_schが[0, 1, [2]]みたいな時, divideすると[[0]]みたいなのができるので
				 そう言う時は[0]を渡すようにする。なおこの場合の0, 1は本来しない置き方(文字列 or 集約のみ)な気がします
				 */
				if (result.size() == 1 && result.get(0) instanceof ExtList) {
					if (result.getExtList(0).size() == 1 && !(result.getExtList(0).get(0) instanceof ExtList)) {
						qb = new ArrayList<>(msql.makeMultipleSQL(result.getExtList(0)));
						if (singleValue) {
							ExtList buf = new ExtList();
							buf.add(sep_sch.get(i));
							qb.get(0).sep_sch = buf;
						}
						for (QueryBuffer q : qb) {
							q.forestNum = i;
						}
						GlobalEnv.qbs.add(qb);
						continue;
					}
				}
				for (int j = 0; j < result.size(); j++) {
					ExtList tmp = new ExtList();
					ExtList n = result.getExtList(j);
					tmp.add(n);
					qb = new ArrayList<>(msql.makeMultipleSQL(tmp));
					for (QueryBuffer q : qb) {
						q.forestNum = i;
						q.treeNum = i;
					}
					GlobalEnv.qbs.add(qb);
				}
			}
		}
		ArrayList<ArrayList<QueryBuffer>> fromGroupQBS = new ArrayList<>();
		if(GlobalEnv.isMultiGB()){
			HashMap<ArrayList<String>, Integer> usedTableSetVariations = new HashMap<>();
			for (int i = 0; i < GlobalEnv.qbs.size(); i++) {
				ArrayList<QueryBuffer> qb = GlobalEnv.qbs.get(i);
				for (int j = 0; j < qb.size(); j++) {
					QueryBuffer q = qb.get(j);
					ArrayList<String> usedTables = q.getUsedTables();
					Collections.sort(usedTables);
					int groupNum = -1;
					if(usedTableSetVariations.containsKey(usedTables)){
						groupNum = usedTableSetVariations.get(usedTables);
					}else{
						int size = usedTableSetVariations.size();
						groupNum = size;
						usedTableSetVariations.put(usedTables, size);
					}
					q.fromGroupNum = groupNum + 1;
				}
			}
			for (int i = 0; i < GlobalEnv.qbs.size(); i++) {
				ArrayList<QueryBuffer> qb = GlobalEnv.qbs.get(i);
				for (int j = 0; j < qb.size(); j++) {
					QueryBuffer q = qb.get(j);
					int groupNum = q.fromGroupNum;
					if (groupNum > fromGroupQBS.size()){
						ArrayList<QueryBuffer> tmp_qb = new ArrayList<>();
						tmp_qb.add(q);
						fromGroupQBS.add(tmp_qb);
					}else{
						fromGroupQBS.get(groupNum - 1).add(q);
					}
				}
			}
		}else{
			fromGroupQBS = (ArrayList<ArrayList<QueryBuffer>>)GlobalEnv.qbs.clone();
		}
		end = System.nanoTime();
		exectime[MAKESQL] = end - start;
		Log.out("## SQL Query ##");
		if (!GlobalEnv.isMultiQuery() && !GlobalEnv.isMultiGB()) {
			for (int i = 0; i < SQL_queries.size(); i++) {
				Log.out(SQL_queries.get(i));
			}
		} else {
			for (int i = 0; i < fromGroupQBS.size(); i++) {
				ArrayList<QueryBuffer> qb_tmp = fromGroupQBS.get(i);
//				Log.info("------same from group------");
				for (int j = 0; j < qb_tmp.size(); j++) {
					QueryBuffer q = qb_tmp.get(j);
//					q.showDebug();
				}
//				Log.info("+++++++++++++++++++++++++++");
			}
		}

		//180705 tbt add to retrieve data by multiple SQL queries
		//Be aware of the deference between shallow copy and deep copy.
		if (GlobalEnv.isMultiQuery() || GlobalEnv.isMultiGB() || GlobalEnv.isOrderFrom()) {
			for (ArrayList<QueryBuffer> qb : fromGroupQBS) {
				if(GlobalEnv.isMultiGB() && qb.size() > 1 && GlobalEnv.getdbms().equals("hive")){
					//multiple group by
					ArrayList<String> queries = new ArrayList<>();
					ArrayList<String> createTBLQuery = new ArrayList<>();
					ArrayList<String> selectTBLQuery = new ArrayList<>();
					ArrayList<String> deleteTBLQuery = new ArrayList<>();

					for (int i = 0; i < qb.size(); i++) {
						QueryBuffer q = qb.get(i);
						String query = "";
						if(i == 0){
							//FROM抽出
							queries.add(q.fromClause);
						}
						query += " INSERT OVERWRITE TABLE tmp" + i;
						query += " " + q.selectClause;
						if(!q.whereCluase.equals("")){
							query += " " + q.whereCluase;
						}
						if(!q.groupbyClause.equals("")){
							query += " " + q.groupbyClause;
						}
						queries.add(query);
						//insert用のテーブル作成クエリ
						String query_tmp = "CREATE TABLE tmp" + i + "(";
						String[] select_tmp = q.selectClause.substring(q.selectClause.indexOf("SELECT ") + 7).trim().split(",");
						String query_tmp2 = "SELECT *";
						String query_tmp3 = "DROP TABLE tmp" + i + ";";
						for (int j = 0; j < select_tmp.length; j++) {
							String selectItem = "";
							if(select_tmp[j].indexOf("DISTINCT") != -1){
								select_tmp[j] = select_tmp[j].substring(select_tmp[j].indexOf("DISTINCT ") + 9);
							}
							if(select_tmp[j].indexOf("(") != -1){
								selectItem = select_tmp[j].trim().substring(select_tmp[j].trim().indexOf("(") + 1, select_tmp[j].trim().indexOf(")"));
							}else {
								selectItem = select_tmp[j].trim();
							}
							String type = GlobalEnv.attType.get(selectItem);
							if(select_tmp[j].indexOf("(") != -1 && select_tmp[j].trim().substring(0, select_tmp[j].trim().indexOf("(")).toLowerCase().equals("avg")){
								type = "FLOAT";
							}
							if(selectItem.indexOf(".") != -1){
								selectItem = selectItem.split("\\.")[0].trim() + selectItem.split("\\.")[1].trim();
							}
							if(j != select_tmp.length - 1) {
								query_tmp += selectItem + " " + type + ", ";
//								query_tmp2 += selectItem + ", ";
							}else{
								query_tmp += selectItem + " " + type;
//								query_tmp2 += selectItem;
							}
						}
						query_tmp += ");";
//						if(query_tmp2.trim().charAt(query_tmp2.length() - 1) == ','){
//							query_tmp2 = query_tmp2.trim().substring(0, query_tmp2.trim().length() - 2);
//						}
						query_tmp2 += " FROM " + "tmp" + i + ";";
						createTBLQuery.add(query_tmp);
						selectTBLQuery.add(query_tmp2);
						deleteTBLQuery.add(query_tmp3);
					}
					String finalMakeQuery = queries.get(0) + " ";
					ArrayList<Integer> noGBIdx = new ArrayList<>();
					for (int i = 1; i < queries.size(); i++) {
						if(queries.get(i).indexOf("GROUP BY") == -1){
							finalMakeQuery += queries.get(i);
						}else{
							noGBIdx.add(i);
						}
					}
					if(noGBIdx.size() != 0){
						for(int idx: noGBIdx){
							finalMakeQuery += queries.get(idx);
						}
					}
					finalMakeQuery += ";";
					/*System.out.println("<CREATE STATEMEMT>");
					for(String qq: createTBLQuery){
						System.out.println(qq);
					}
					System.out.println();
					System.out.println("<MAKE STATEMEMT>");
					System.out.println(finalMakeQuery);
					System.out.println();
					System.out.println("<SELECT STATEMEMT>");
					for(String qq: selectTBLQuery){
						System.out.println(qq);
					}
					System.out.println();
					System.out.println("<DELETE STATEMEMT>");
					for(String qq: deleteTBLQuery){
						System.out.println(qq);
					}*/
					//問い合わせ処理
					//一時テーブルの作成
					for(String qry: createTBLQuery){
						Long updstart = System.currentTimeMillis();
						gfd.execUpdate(qry, new ExtList());
						Long updend = System.currentTimeMillis();
						Log.info("CREATE TMPORARY TABLE Time taken: " + (updend - updstart) + "ms");
					}
					//データ作成
					Long makeStart = System.currentTimeMillis();
					gfd.execUpdate(finalMakeQuery, new ExtList());
					Long makeEnd = System.currentTimeMillis();
					Log.info("MAKE DATA Time taken: " + (makeEnd - makeStart) + "ms");
					//データ取得
					for (int i = 0; i < selectTBLQuery.size(); i++) {
						String qry = selectTBLQuery.get(i);
						QueryBuffer tmp_q = qb.get(i);
						Long getStart = System.currentTimeMillis();
						gfd.execQuery(qry, sep_data_info);
						Long getEnd = System.currentTimeMillis();
						Log.info("GET FROM TEMPORARY TABLR Time taken: " + (getEnd - getStart) + "ms");
						Log.info("tuples num : " + sep_data_info.size());
						ExtList result = new ExtList(sep_data_info);
						tmp_q.setResult(result);
					}
					//一時テーブル削除
					for(String qry: deleteTBLQuery){
						Long deleteStart = System.currentTimeMillis();
						gfd.execUpdate(qry, new ExtList());
						Long deleteEnd = System.currentTimeMillis();
						Log.info("DELETE TEMPORARY TABLR Time taken: " + (deleteEnd - deleteStart) + "ms");
					}
				}else {
					for (QueryBuffer q : qb) {
						Long execQuery_start = System.currentTimeMillis();
						gfd.execQuery(q.getQuery(), sep_data_info);
						Long execQuery_end = System.currentTimeMillis();
//						Log.info("tuples num : " + sep_data_info.size());
//						Log.info("Query Exec Time taken:" + (execQuery_end - execQuery_start) + "ms");
						GlobalEnv.totalTupleNum += sep_data_info.size();
						ExtList tmp = new ExtList(sep_data_info);
						q.setResult(tmp);
					}
				}
			}
//			Log.info("total tuples num : " + GlobalEnv.totalTupleNum);
			GlobalEnv.qbs.clear();
			GlobalEnv.qbs = fromGroupQBS;
		} else {
			Long execQuery_start = System.currentTimeMillis();
			for (int i = 0; i < SQL_queries.size(); i++) {
				ExtList tmp = new ExtList();
				Long start_exec = System.currentTimeMillis();
				gfd.execQuery(SQL_queries.get(i), tmp);
				Long end_exec = System.currentTimeMillis();
//				Log.info("tuples num : " + tmp.size());
//				Log.info("Query Exec Time taken:" + (end_exec - start_exec) + "ms");
				sep_data_info.add(tmp);
			}
//			gfd.execQuery(SQL_string, sep_data_info);
			Long execQuery_end = System.currentTimeMillis();
//			Log.info("tuples num : " + sep_data_info.size());
//			Log.info("Query Exec Time taken:" + (execQuery_end - execQuery_start) + "ms");
		}
		gfd.close();
		end = System.nanoTime();
		exectime[EXECSQL] = end - start;

//		Log.info("## DB result ##");
		System.out.println();
		if (!GlobalEnv.isMultiQuery()){
			Log.out("result");
			for (int i = 0; i < sep_data_info.size(); i++) {
				Log.out(sep_data_info.get(i));
			}
		}
		else{
			for (ArrayList<QueryBuffer> qb: GlobalEnv.qbs) {
				for (QueryBuffer q: qb) {
//					q.showDebug("getFromDB: ");
				}
			}
		}
		GlobalEnv.beforeMakeTree = System.currentTimeMillis();
		if(GlobalEnv.isMultiQuery()) {
			GlobalEnv.headSet = new HashMap<>();
//			System.out.println();
//			Log.info("Merging Same Tree");
			Long mergeTreeStart = System.currentTimeMillis();
			GlobalEnv.sameTree_set = new ArrayList<>();
			for (int i = 0; i < GlobalEnv.qbs.size(); i++) {
				ArrayList<QueryBuffer> qb = GlobalEnv.qbs.get(i);
				for (int j = 0; j < qb.size(); j++) {
					QueryBuffer q = qb.get(j);
					if(GlobalEnv.sameTree_set.size() >= q.treeNum + 1){
						GlobalEnv.sameTree_set.get(q.treeNum).add(q);
					}else{
						int num = GlobalEnv.sameTree_set.size();
						for (int k = 0; k < q.treeNum + 1 - num; k++) {
							ArrayList<QueryBuffer> tmp2 = new ArrayList<>();
							if(k == q.treeNum - num){
								tmp2.add(q);
							}
							GlobalEnv.sameTree_set.add(tmp2);
						}
					}
				}
			}
			for (int i = 0; i < GlobalEnv.sameTree_set.size(); i++) {
				ArrayList<QueryBuffer> tree = GlobalEnv.sameTree_set.get(i);
//				System.out.println("----tree start----");
				for (int j = 0; j < tree.size(); j++) {
//					tree.get(j).showDebug();
				}
//				System.out.println("++++tree end++++");
			}
			for (int i = 0; i < GlobalEnv.sameTree_set.size(); i++) {
				ArrayList<QueryBuffer> tree1 = GlobalEnv.sameTree_set.get(i);
				if(GlobalEnv.sameTree_set.get(i).size() > 1){
					ArrayList<QueryBuffer> tree = GlobalEnv.sameTree_set.get(i);
					QueryBuffer qb_result = tree.get(0);
//					System.out.println();
//					Log.info("Merge Start From This QueryBuffer!!!");
//					qb_result.showDebug();
					if(Preprocessor.isCtab()) qb_result.makeAllPattern();
					for (int j = 1; j < tree.size(); j++) {
						if(Preprocessor.isCtab()){
//							Log.info("Making All Pattern");
							Long makeAllPatternStart = System.currentTimeMillis();
							tree.get(j).makeAllPattern();
							Long makeAllPatternEnd = System.currentTimeMillis();
//							Log.info("Make All Pattern Time taken: " + (makeAllPatternEnd - makeAllPatternStart) + "ms");
						}
//						System.out.println();
//						Log.info("\tMerging tree!!!");
//						tree.get(j).showDebug("\t");
						sep_sch = GlobalEnv.sep_sch_bak;
						qb_result = mergeSameTreeQueryBuffer(tree.get(j), qb_result, sep_sch);
//						Log.info("\tEnd tree!!!");
//							qb_result = mergeQueryBuffer(tree.get(2), qb_result);
					}
					qb_result.treeNum = tree.get(0).treeNum;
					qb_result.forestNum = tree.get(0).forestNum;
//					System.out.println("qb_result");
//					qb_result.showDebug("tree_merged: ");
//					System.out.println("--------");
					GlobalEnv.sameTree_set.remove(i);
					ArrayList<QueryBuffer> tmp = new ArrayList<>();
					tmp.add(qb_result);
					GlobalEnv.sameTree_set.add(i, tmp);
				}
			}
			Long mergeTreeEnd = System.currentTimeMillis();
//			Log.info("Merge Same Tree Time taken: " + (mergeTreeEnd - mergeTreeStart) + "ms");


		}
		//tbt end

		//170714 tbt add for the thing that only single attribute([e.salary]!) won't return empty cell
		//if each tuples is single, remove empty tuple
		try{
			if(((ExtList)sep_data_info.get(0)).size() == 1){
				for(int i = 0; i < sep_data_info.size(); i++){
					if(((ExtList)sep_data_info.get(i)).get(0).toString().isEmpty()){
						sep_data_info.remove(i);
						i--;
					}
				}
				Log.out("removed:"+sep_data_info);
			}
		}catch(Exception e){

		}

		//add "dummy" for null tuples
		//skip at aggregate and codegenerator
//		for(int i = 0; i < sep_data_info.size(); i++){
//			for(int j = 0; j < ((ExtList)sep_data_info.get(i)).size(); j++){
//				if(((ExtList)sep_data_info.get(i)).get(j).equals("")){
//					((ExtList)sep_data_info.get(i)).remove(j);
//					((ExtList)sep_data_info.get(i)).add(j, "dummydummydummy");
//				}
//			}
//		}
//		Log.out("add_dummy:"+sep_data_info);
		//tbt end
		return sep_data_info;

	}



	private ExtList makeTree(ExtList sep_sch, ExtList sep_data_info) {

		// MakeTree
		long start, end;
		start = System.nanoTime();

		TreeGenerator tg = new TreeGenerator();
		sep_data_info = tg.makeTree(sep_sch, sep_data_info);

		//terui start
		if(GlobalEnv.realLimit.getLimitFrag()) {
			for (int i = 0; i <  sep_data_info.size(); i++){
				// Log.out("Start Limit");
				GlobalEnv.realLimit.limitTuple((ExtList) sep_data_info.get(0));
			}
		}
		//terui end

		end = System.nanoTime();

		exectime[MKETREE] = end - start;

		Log.out("## constructed Data ##");
		Log.out(sep_data_info);
		return sep_data_info;
	}

	public ExtList getData() {
		return data_info;
	}

	private ExtList MakeTree(ExtList schema) {
		// added by ria
		Object o;
		ExtList buf = new ExtList();
		for (int i = 0; i < schema.size(); i++) {
			o = schema.get(i);

			if (!(o instanceof ExtList)) {
				if (keyAtt == null) {
					keyAtt = (Attribute) o;
					buf.add(keyAtt.getTuple());
					// System.out.println(buf);
					key = keyAtt.getTuple().toString();
					col = keyAtt.getColumn();
				} else {
					Attribute a = (Attribute) o;
					if (a == keyAtt) {
						buf.add(keyAtt.getTuple());
						// System.out.println(buf);
						key = keyAtt.getTuple().toString();
					} else {
						// add here checking if the keyAtt is a connector
						buf.add(a.getTuple(key, col));
						// System.out.println(buf);
						a.delTuples(key, col);
					}
				}
			} else if (IsLeaf((ExtList) o)) {

				ExtList obj = (ExtList) o;
				ExtList temp = new ExtList();

				Attribute a = (Attribute) obj.get(0);
				temp.addAll((a.getTuples(key, col)));

				if (temp.size() == 0) {
					flag = false;
				} else {
					flag = true;
				}

				buf.add(temp);
				// System.out.println(buf);

				if (keyAtt != null) {
					keyAtt.delTuples(key, col);
				}

			} else {
				if (schema.size() == 1) {
					ExtList temp = new ExtList();
					do {
						ExtList temp2 = MakeTree((ExtList) o);
						if (!temp2.isEmpty()) {
							temp.add(temp2);
							if (keyAtt != null) {
								keyAtt.delTuples(key, col);
							}
						}
					} while ((keyAtt != null) && keyAtt.getSize() != 0);

					buf.add(temp);
					// System.out.println(buf);
					flag = true;
				} else {
					ExtList temp = new ExtList();
					temp.add(MakeTree((ExtList) o));

					if (flag) {
						buf.add(temp);
						// System.out.println(buf);
					}
				}
			}
		}
		if (!flag) {
			buf = new ExtList();
		}

		return buf;
	}

	private boolean IsLeaf(ExtList sch) {
		for (int i = 0; i < sch.size(); i++) {
			if (sch.get(i) instanceof ExtList)
				return false;
		}
		return true;
	}

	public static ExtList getDataFromDBPedia(String sparqlWhereQuery,
			String[] varNames) {
		BufferedReader br = null;
		String everything = "";
		try {
			br = new BufferedReader(new FileReader("dbpedia.config"));
		} catch (FileNotFoundException e1) {
			Log.err("*** DBPedia config file not found ***");
			e1.printStackTrace();
			throw new IllegalStateException();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (IOException e) {
			Log.err("*** Error while reading the Dbpedia config file ***");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				Log.err("*** Error while closig the dbpedia config file ***");
				e.printStackTrace();
			}
		}
		try {
			Document doc;
			ExtList data = new ExtList();
			String query = everything + "\nSELECT ";
			for (int i = (varNames.length - 1); i >= 0; i--) {
				query += "?" + varNames[i] + " ";
			}
			query += " WHERE " + sparqlWhereQuery + "";
			doc = Jsoup.connect("http://dbpedia.org/sparql?")
					.data("default-graph-uri", "http://dbpedia.org")
					.data("query", query).data("format", "text/html")
					.data("debug", "on").timeout(0).get();
			Elements tdInfos = doc.getElementsByTag("td");
			int columnCount = 0;
			int rowCount = -1;
			for (Element info : tdInfos) {
				String infoText = info.html();
				columnCount %= varNames.length;
				if (columnCount == 0) {
					ExtList e = new ExtList();
					e.add(infoText);
					data.add(e);
					columnCount += 1;
					rowCount += 1;
				} else {
					((ExtList) data.get(rowCount)).add(infoText);
					columnCount += 1;
				}

			}
			return data;
		} catch (IOException e) {
			Log.err("*** Error while querying dbpedia, please check your internet connection and your query syntax ***");
			throw new IllegalStateException();
		}
	}
}
