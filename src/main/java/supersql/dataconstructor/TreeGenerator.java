package supersql.dataconstructor;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Preprocessor;

/**
* 鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申?鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申鐃緒申
*/
public class TreeGenerator {

	private int sep;
	private int tuples_length;
	public TreeGenerator() {
	}

	public ExtList makeTree(ExtList sch, ExtList tuples) {
		// public void makeTree(ExtList sch, ExtList tuples) {
		//ネスティング開始位置
		//add tbt 180701
		GlobalEnv.start_mt = System.currentTimeMillis();
		ExtList result = new ExtList();
		Log.out("= makeTree =");
		Log.out("sch : " + sch);
		Log.out("tuples : " + tuples);
		
		//hanki start
		if (Preprocessor.isAggregate() && !GlobalEnv.isMultiQuery()) {

			ExtList info = new ExtList();
			ExtList criteria_set = new ExtList();
			Aggregate aggregate = new Aggregate();

			Log.out("= aggregate started =");

			info = (ExtList)Preprocessor.getAggregateList().clone();
			
			ExtList info_bak = (ExtList)info.clone();
			if(Integer.parseInt(sch.unnest().get(0).toString()) > 0){
				int diff = Integer.parseInt(sch.unnest().get(0).toString());
				for (int i = 0; i < info_bak.size(); i++) {
					int target_before = Integer.parseInt(info_bak.get(i).toString().split(" ")[0]);
					String method = info_bak.get(i).toString().split(" ")[1].trim();
					info_bak.remove(i);
					String target_after = (target_before - diff) + " " + method;
					info_bak.add(i, target_after);
				}
			}
			ExtList sch_bak = new ExtList();
			DataConstructor.copySepSch(sch, sch_bak);
			count = 0;
			initializeSepSch(sch);
			tuples = aggregate.aggregate(criteria_set, info_bak, sch, tuples);
			sch = sch_bak;
			Log.out("= aggregate completed =");
			Log.out("tuples : " + tuples);

		}
		//hanki end

		//otawa start
		//changed by li 20210610
		if (Preprocessor.isGGplot()) {

				ExtList info = new ExtList();
				ExtList ggdecos = new ExtList();
				ExtList criteria_set = new ExtList();
				ExtList tuple = new ExtList();
				GGplot ggplot = new GGplot();

				Log.out("= ggplot started =");
				info = Preprocessor.getGGplotList();
				//Log.out("break info:" + info.get(0).toString().split(" ")[0]);
				//Log.out("break sch:" + sch.get(0).toString().split(",")[0]);
				//added by li to check sch level 20210611
				if(('['+info.get(0).toString().split(" ")[0]).equals(sch.get(0).toString().split(",")[0])) {
					for (int i = 0; i < sch.size(); i++) {
						for (int j = 0; j < info.size(); j++) {
							/* "ggplot functions" found */
							//Log.out("info.get(" + j + "): " + info.get(j).toString().split(" ")[0]);
							//	Log.out("sch.get(" + i + "): " + sch.get(i));
						}
					}
						
					ggdecos = Preprocessor.getGGdecoList();
					ExtList info_bak = (ExtList)info.clone();

					if(Integer.parseInt(sch.unnest().get(0).toString()) > 0){
						int diff = Integer.parseInt(sch.unnest().get(0).toString()) - 0;
						for (int i = 0; i < info_bak.size(); i++) {
							int target_before = Integer.parseInt(info_bak.get(i).toString().substring(0, 1));
							String method = info_bak.get(i).toString().substring(2);
							info_bak.remove(i);
							String target_after = (target_before - diff) + " " + method;
							info_bak.add(i, target_after);
						}
					}
					ExtList sch_bak = new ExtList();
					DataConstructor.copySepSch(sch, sch_bak);
					count = 0;
					//comment out by li 20210611
					//initializeSepSch(sch);
					//changed by li 20210610
					for (int i = 0; i < ggdecos.size(); i++) {
						int index = Integer.parseInt(ggdecos.getExtListString(i).split(":")[ggdecos.getExtListString(i).split(":").length - 1]);
						info.set(index, info.get(index) + ggdecos.getExtListString(i).substring(0, ggdecos.getExtListString(i).lastIndexOf(":")));
					}

					//Log.out("TreeGenerator.java ggplot info:" + info.get(0) + ", sch: " + sch);
					tuple = ggplot.ggplot(criteria_set, info, sch, tuples);
					sch = sch_bak;

					tuples = ggplot.getResult();
				}	
		}

		//terui start
		GlobalEnv.realLimit = new Limiter().new RealLimiter();
		if(GlobalEnv.limit.size() != 0){
			for (int i = 0; i < GlobalEnv.limit.size(); i++) {
				GlobalEnv.limit.get(0).initMaxDepth();
				GlobalEnv.limit.get(0).haveLimitAttribute(sch);
				if(GlobalEnv.limit.get(0).getLimitFrag()) i--;
				else break;
			}
		}
		GlobalEnv.realLimit.logStatus();
		//terui end

		for (int i = 0; i < tuples.size(); i++) {
			if (tuples.get(i) instanceof ExtList) {
				result = nest_tuple(sch, (ExtList) tuples.get(i));
			} else {
				ExtList tmp = new ExtList();
				tmp.add(tuples.get(i));
				result = nest_tuple(sch, tmp);
			}
			// Log.out("result = " + result);
			tuples.set(i, result);
		}
		Log.out("= nest_tuple end =");
		Log.out("tuples : " + tuples);
		//tk start/////
		if(tuples.size() != 0)
		{
			//tk end///////
			SortNesting sn = new SortNesting();
			sn.bufferall(tuples);
			Log.out("sn_result"+sn);

			//hanki start
			if (Preprocessor.isOrderBy()) {

				ExtList info = new ExtList();

				Log.out("= order by started =");
				Log.out(" * schema : " + sch + " *");
				//Log.info("BEFORE"+Preprocessor.getOrderByTable());
				//tbt add 180730
				//for sorting forest
				//compare OrderTable with sch
				//OrderTable -> [asc[0], asc[2], asc[4]], sch -> [3, 4, 5]
				//then OrderTable -> [asc[4]] -> [asc[1]], sch -> [0, 1, 2]
				//			if(GlobalEnv.isMultiQuery()){
				ExtList otables = new ExtList(Preprocessor.getOrderByTable());
				ExtList otables_b = new ExtList();
				ExtList sep_unnest = sch.unnest();
				ExtList aggregateList = new ExtList(Preprocessor.getAggregateList());
				ExtList aggList_tmp = new ExtList();
				for (int j = 0; j < sep_unnest.size(); j++) {
					int sep = Integer.parseInt(sep_unnest.getExtListString(j));
					boolean containFlag = false;
					String order = new String();
					for (int i = 0; i < otables.size(); i++) {
						String otable = otables.get(i).toString();
						if(sep == Integer.parseInt(otable.substring(otable.indexOf("[") + 1, otable.indexOf("]")))){
							containFlag = true;
							order = otable.substring(0, otable.indexOf("["));
							break;
						}
					}
					if(containFlag) {
						otables_b.add(order + "[" + j + "]");
					}
					boolean aggContainFlag = false;
					String method = new String();
					for (int i = 0; i < aggregateList.size(); i++) {
						String agg_sch = aggregateList.getExtListString(i).split(" ")[0];
						if(sep == Integer.parseInt(agg_sch)){
							aggContainFlag = true;
							method = aggregateList.getExtListString(i).split(" ")[1];
							break;
						}
					}
					if(aggContainFlag){
						aggList_tmp.add(j + " " + method);
					}
				}
				//			System.out.println("otables_b:::"+otables_b);
				GlobalEnv.aggListTmp = aggList_tmp;
				count = 0;
				initializeSepSch(sch);
				info = OrderBy.tableToList(otables_b, sch.contain_itemnum());
				//tbt end
				//			}else{
				//				info = OrderBy.tableToList(Preprocessor.getOrderByTable(), sch.contain_itemnum());
				//			}
				//Log.info("AFTER "+info);
				result = new ExtList(sn.GetResultWithOrderBy(info, sch));
				Log.out("= orderBy completed =");

			} else {
				//hanki end

				result = new ExtList(sn.GetResult());

				//hanki start
			}
			//hanki end

			tuples.clear();
			tuples.addAll(((ExtList) result.get(0)));
			Log.out("= makeTree end =");
			//tbt add 180701
			GlobalEnv.end_mt = System.currentTimeMillis();
			Log.out("tuples_num: "+GlobalEnv.getTuplesNum());
			Log.out("makeTree time taken: " + (GlobalEnv.end_mt - GlobalEnv.start_mt) + "ms");
			//hanki
			//return;
			return tuples;

			//tk start///////////////////////////////////////////////
		}
		else
		return tuples;
		//tk end//////////////////////////////////////////////////
	}

	//tbt add 180718
	//to change sep_sch [6, [7]] -> [0, [1]]
	private int count = 0;
	private void initializeSepSch(ExtList sep_sch){
		for (int i = 0; i < sep_sch.size(); i++) {
			try{
				ExtList sep_child = (ExtList)sep_sch.get(i);
				initializeSepSch(sep_child);
			}catch(ClassCastException e){
				sep_sch.remove(i);
				sep_sch.add(i, count);
				count++;
			}
		}
	}
	//tbt end

	private ExtList nest_tuple(ExtList sch, ExtList tuple) {
		int tidx = 0;
		int count;
		ExtList result = new ExtList();
		Object o;
		// Log.out("sch = "+sch);
		// Log.out("tuple = "+tuple);

		for (int idx = 0; idx < sch.size(); idx++) {
			o = sch.get(idx);
			//			Log.out("sep_sch = "+o);
			if (o instanceof ExtList) {
				count = ((ExtList) o).contain_itemnum();
				result.add(nest_tuple((ExtList) o, tuple.ExtsubList(tidx, tidx
				+ count)));
				tidx += count;
			} else {
				result.add(tuple.get(tidx));
				tidx++;
			}
		}
		//				Log.out("result = "+result);
		return result;
	}
}
