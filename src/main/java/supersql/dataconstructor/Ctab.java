package supersql.dataconstructor;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Ctab {
	public ExtList makeCtab(ExtList tfe){
		Log.out("start_tfe:::"+tfe);
		ExtList finalForm = new ExtList();
		//get the number of arguments and set top, side and value
		int args_num = (tfe.size() - 2) / 2;
		ExtList top = new ExtList(tfe.getExtList(2));
		ExtList side = new ExtList(tfe.getExtList(4));
		ExtList value = new ExtList();
		for (int i = 3; i < args_num + 1; i++) {
			value.add(tfe.getExtList(i * 2));
		}
//		Log.out("top:::"+top);
//		Log.out("side:::"+side);
//		Log.out("value:::"+value);
		addTag(top, "ctab_head");
		addTag(top, "width=100");
		addTag(top, "height=50");
		addTag(side, "ctab_side");
		addTag(side, "width=100");
		addTag(side, "height=50");
//		addTag(side, "border=0");
		addTag(value, "ctab_value");
		addTag(value, "width=100");
		addTag(value, "height=50");
//		addTag(value, "border=0");

//		Log.out("top_addtag:::"+top);
//		Log.out("side_addtag:::"+side);
//		Log.out("value_addtag:::"+value);
		//check the number of each part
		int top_num = 1, side_num = 1, value_num = 1;
		//if top structure is forest
		if(((ExtList)top.get(1)).get(0) instanceof String && top.getExtListString(1, 0).toString().equals("{")){
			//the number of trees is equal to the number of childs of h_exp
			top_num = (top.getExtList(1, 1, 1, 0, 1, 0, 1, 0, 1).size() + 1) / 2;
		}
		if(((ExtList)side.get(1)).get(0) instanceof String && side.getExtListString(1, 0).toString().equals("{")){
			side_num = (side.getExtList(1, 1, 1, 0, 1, 0, 1).size() + 1) / 2;
		}
		value_num = value.size();

//		Log.out("top_num:::"+top_num);
//		Log.out("side_num:::"+side_num);
//		Log.out("value_num:::"+value_num);

		if(value_num != 1 && side_num * top_num != value_num){
			System.err.println("Incorrect number of cross_tab arguments");
			System.exit(1);
		}


		//make top attribute structure
		addSorts(top);
		Log.out("top_added_sort:::"+top);
		ExtList topAttribute = new ExtList(top);
		ExtList top1 = new ExtList();
		ExtList top2 = new ExtList();
		ExtList top3 = new ExtList();
		ExtList top4 = new ExtList();
		ExtList top5 = new ExtList();
		ExtList top6 = new ExtList();
		ExtList top7 = new ExtList();
		ExtList top8 = new ExtList();
		ExtList top9 = new ExtList();
		ExtList top10 = new ExtList();
		ExtList top11 = new ExtList();
		ExtList top12 = new ExtList();
		String deco = "@{width=" + GlobalEnv.sideWidth + ", ctab_leftup}";
		top1.add("' '");
		top2.add("sl");
		top2.add(top1);
		top3.add(top2);
		top3.add(deco);
		top4.add("operand");
		top4.add(top3);
		top5.add(top4);
		top5.add(",");
		top5.add(topAttribute);
		top6.add("h_exp");
		top6.add(top5);
		top7.add("[");
		top7.add(top6);
		top7.add("]");
		top7.add(",");
		top9.add("grouper");
		top9.add(top7);
		top11.add("operand");
		top10.add(top9);
		top11.add(top10);
		ExtList topAttributeSide = (ExtList)top11.clone();


		//make side and value structure
		//sideの個数分for文回す
		int value_count = 0;
		ExtList sideValueAttribute = new ExtList();
		for (int i = 0; i < side_num; i++) {
			ExtList side_child = new ExtList();
			if(side_num == 1){
				side_child = side;
			}else {
				side_child = new ExtList(side.getExtList(1, 1, 1, 0, 1, 0, 1, i * 2, 1, 0));
			}
			Log.out("side_child:::"+side_child);
			ExtList valueTops = new ExtList();
			//topの個数分for文回す
			for (int j = 0; j < top_num; j++) {
				//topに含まれる属性を抜き出す
				ExtList top_child = new ExtList();
				if(top_num == 1){
					top_child = top;
				}else {
					top_child = new ExtList(top.getExtList(1, 1, 1, 0, 1, 0, 1, 0, 1, j * 2));
				}
				Log.out("top_child:::"+top_child);
				Attributes.clear();
				getAttribute(top_child, "attribute");
				Log.out("top_attributes:::"+Attributes);
				ExtList top_child_atts = (ExtList)Attributes.clone();
				Attributes.clear();
				ExtList first = new ExtList();
				//null関数に入れる
				ExtList nulls = new ExtList();

				// まずは左上の空白
				ExtList emp1 = new ExtList();
				ExtList emp2 = new ExtList();
				ExtList emp3 = new ExtList();
				ExtList emp4 = new ExtList();
				emp1.add("' '");
				emp2.add("sl");
				emp2.add(emp1);
				emp3.add(emp2);
				emp3.add(deco);
				emp4.add("operand");
				emp4.add(emp3);

				nulls.add(addNull(emp4));

				for (int k = 0; k < top_child_atts.size(); k++) {
					if(first.size() == 0 && top_child_atts.get(k) instanceof String){
						continue;
					}
					ExtList top_child_child = new ExtList();
					top_child_child = top_child_atts.getExtList(k);
					first.add(top_child_child);
					if(top_child_child.getExtListString(0).equals("sorting")){
						continue;
					}
					if(first.size() == 1){
						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();

						tmp1.add("(");
						tmp1.add("asc");
						tmp1.add(")");
						tmp2.add("sorting");
						tmp2.add(tmp1);
						first.add(0, tmp2);
					}
					if(top_child_atts.size() > k && top_child_atts.get(k + 1) instanceof String){

						String dec = top_child_atts.getExtListString(k + 1);
						if(!dec.contains("border")){
							dec = dec.substring(0, dec.length() - 1);
							dec += ", border=0}";
						}
						first.add(dec);
						k++;
					}
					ExtList tmp1 = new ExtList();
					ExtList tmp2 = (ExtList)first.clone();
					tmp1.add("operand");
					tmp1.add(tmp2);
					//make function name

					nulls.add(addNull(tmp1));
					first.clear();
				}
				Log.out("nulls:::"+ nulls);
				//nullとvalueを合体させる
				//使うvalueを決定
				ExtList value_child = new ExtList();
				if(value_num == 1){
					value_child = (ExtList)value.getExtList(0).clone();
				}else{
					value_child = (ExtList)value.getExtList(value_count).clone();
				}

				//h_expでnullsとvalueを並べてgrouperまで作成する
				ExtList tmp1 = new ExtList();
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				ExtList tmp4 = new ExtList();
				ExtList tmp5 = new ExtList();
				ExtList tmp6 = new ExtList();
				ExtList tmp7 = new ExtList();
				ExtList tmp8 = new ExtList();
				ExtList tmp9 = new ExtList();
				ExtList tmp10 = new ExtList();
				ExtList tmp11 = new ExtList();
				ExtList tmp12 = new ExtList();

				for (int k = 0; k < nulls.size(); k++) {
					tmp1.add(nulls.getExtList(k));
					tmp1.add(",");
				}
				tmp1.add(value_child);
				tmp2.add("h_exp");
				tmp2.add(tmp1);
				tmp3.add(tmp2);
				tmp4.add("v_exp");
				tmp4.add(tmp3);
				tmp5.add(tmp4);
				tmp6.add("d_exp");
				tmp6.add(tmp5);
				tmp7.add(tmp6);
				tmp8.add("exp");
				tmp8.add(tmp7);
				tmp9.add("[");
				tmp9.add(tmp8);
				tmp9.add("]");
				tmp9.add(",");
				tmp10.add("grouper");
				tmp10.add(tmp9);
				tmp11.add(tmp10);
				tmp12.add("operand");
				tmp12.add(tmp11);
				ExtList valueTop = new ExtList(tmp12);
				valueTops.add(valueTop);
				value_count++;
			}
			Log.out("valueTops:::"+valueTops);
			//sideの一番奥の属性の下にvalueTopをブッコム
			//sideだからh_expで繋がっている。h_expの子要素が全て属性(not grouper)だったら一番右にvalueTopを入れる。
			//まだgrouperがあったら其奴を探索する
			addValues(side_child, valueTops);
			sideValueAttribute.add(side_child);
			Log.out("side_child_added:::"+side_child);
		}
		ExtList tmp1 = new ExtList();
		ExtList tmp2 = new ExtList();
		ExtList tmp3 = new ExtList();
		ExtList tmp4 = new ExtList();
		ExtList tmp5 = new ExtList();
		ExtList tmp6 = new ExtList();

		tmp1.add(topAttributeSide);
		for (int i = 0; i < sideValueAttribute.size(); i++) {
			tmp1.add("!");
			tmp1.add(sideValueAttribute.getExtList(i));
		}
		tmp2.add("v_exp");
		tmp2.add(tmp1);
		tmp3.add(tmp2);
		tmp4.add("d_exp");
		tmp4.add(tmp3);
		tmp5.add(tmp4);
		tmp6.add("exp");
		tmp6.add(tmp5);
		finalForm = tmp6;
		Log.out("Ctab_finished:::"+finalForm);
		return finalForm;
	}

	private ExtList addNull(ExtList content) {
		ExtList tmp3 = new ExtList();
		ExtList tmp4 = new ExtList();
		ExtList tmp5 = new ExtList();
		ExtList tmp6 = new ExtList();
		ExtList tmp7 = new ExtList();
		ExtList tmp8 = new ExtList();

		tmp7.add("null");
		tmp6.add("keyword");
		tmp6.add(tmp7);
		tmp5.add(tmp6);
		tmp4.add("any_name");
		tmp4.add(tmp5);
		tmp3.add(tmp4);
		tmp8.add("function_name");
		tmp8.add(tmp3);

		ExtList tmp9 = new ExtList();

		tmp9.add(tmp8);
		tmp9.add("(");
		tmp9.add(content);
		tmp9.add(")");

		ExtList tmp10 = new ExtList();
		ExtList tmp11 = new ExtList();
		ExtList tmp12 = new ExtList();

		tmp10.add("function");
		tmp10.add(tmp9);
		tmp11.add(tmp10);
		tmp12.add("operand");
		tmp12.add(tmp11);

		return tmp12;
	}

	private void addTag(ExtList list, String tag) {
		int num = list.size();
		for (int i = 0; i < num; i++) {
			if(list.get(i) instanceof String){
				try{
					if(list.get(i).toString().equals("operand")){
						if (!(((ExtList)list.get(1)).get(0) instanceof String)){
							if (list.getExtListString(1, 0, 0).equals("attribute") || list.getExtListString(1, 0, 0).equals("sorting") || list.getExtListString(1, 0, 0).equals("aggregate")){
								int count = 0;
								if(!tag.contains("ctab")){
									if(!(((ExtList)list.get(1)).get(((ExtList)list.get(1)).size() - 1) instanceof ExtList)){
										String deco = list.getExtListString(1, list.getExtList(1).size() - 1);
										boolean contain = false;
										if(tag.contains("=")){
											if(deco.contains(tag.split("=")[0].trim())){
												contain = true;
											}
										}else{
											if (deco.contains(tag)){
												contain = true;
											}
										}
										if(!contain) {
											deco = deco.split("}")[0];
											deco += ", " + tag + "}";
											list.getExtList(1).remove(list.getExtList(1).size() - 1);
											list.getExtList(1).add(deco);
										}
									}else{
										list.getExtList(1).add("@{" + tag + "}");
									}
								}else {
									if (tag.contains("head")) {
										count = GlobalEnv.headCount;
									} else if (tag.contains("side")) {
										count = GlobalEnv.sideCount;
									} else {
										count = GlobalEnv.valueCount;
									}
									boolean notUse = false;
									if (!(((ExtList) list.get(1)).get(((ExtList) list.get(1)).size() - 1) instanceof ExtList)) {
										String deco = list.getExtListString(1, list.getExtList(1).size() - 1);
										deco = deco.split("}")[0];
										if (tag.contains("head")) {
											if (list.getExtListString(1, 0, 0).equals("aggregate")) {
												deco += ", " + tag + "_agg}";
												notUse = true;
											} else {
												deco += ", " + tag + count + "}";
											}
										} else {
											deco += ", " + tag + count + "}";
										}
										list.getExtList(1).remove(list.getExtList(1).size() - 1);
										list.getExtList(1).add(deco);
									} else {
										if (tag.contains("head")) {
											if (list.getExtListString(1, 0, 0).equals("aggregate")) {
												list.getExtList(1).add("@{" + tag + "_agg}");
												notUse = true;
											} else {
												list.getExtList(1).add("@{" + tag + count + "}");
											}
										} else {
											list.getExtList(1).add("@{" + tag + count + "}");
										}
									}
									if (!notUse) {
										if (tag.contains("head")) {
											GlobalEnv.headCount++;
										} else if (tag.contains("side")) {
											GlobalEnv.sideCount++;
										} else {
											GlobalEnv.valueCount++;
										}
									}
								}
							}
						}
					}
				}catch (NullPointerException | IndexOutOfBoundsException e){
					continue;
				}
			}else{
				addTag(list.getExtList(i), tag);
			}
		}
	}

	private void addSorts(ExtList tfe) {
		int num = tfe.size();
		for (int i = 0; i < num; i++) {
			if(tfe.get(i) instanceof String){
				try {
					if (tfe.getExtListString(i).equals("operand") && tfe.getExtListString(1, 0, 0).equals("attribute")) {
						ExtList tmp1 = new ExtList();
						ExtList tmp2 = new ExtList();

						tmp1.add("(");
						tmp1.add("asc");
						tmp1.add(")");
						tmp2.add("sorting");
						tmp2.add(tmp1);
						tfe.getExtList(1).add(0, tmp2);
					}
				}catch(NullPointerException | IndexOutOfBoundsException e){
					continue;
				}
			}else{
				addSorts(tfe.getExtList(i));
			}
		}
	}

	private void addValues(ExtList side_child, ExtList valueTops) {
		int num = side_child.size();
		for (int i = 0; i < num; i++) {
			if (side_child.get(i) instanceof String){
				if(side_child.get(i).toString().equals("h_exp")){
					boolean containGrouper = false;
					int num2 = (side_child.getExtList(1).size() + 1) / 2;
					for (int j = 0; j < num2; j++) {
//						System.out.println("num2:::"+num2);
//						System.out.println("side_child_get:::"+side_child.getExtList(1));
						try {
							if (side_child.getExtListString(1, j * 2, 1, 0, 0).equals("grouper")) {
								containGrouper = true;
								addValues(side_child.getExtList(1, j * 2), valueTops);
							}
						}catch (NullPointerException e){
							continue;
						}
					}
					if(!containGrouper){
						for (int j = 0; j < valueTops.size(); j++) {
							side_child.getExtList(1).add(",");
							side_child.getExtList(1).add(valueTops.getExtList(j));
						}
					}
					break;
				}
			}else{
				addValues(side_child.getExtList(i), valueTops);
			}
		}
	}

	private ExtList Attributes = new ExtList();
	private void getAttribute(ExtList top_child, String target) {
		int num = top_child.size();
		for (int i = 0; i < num; i++) {
			if(top_child.get(i) instanceof String){
				if(top_child.get(i).toString().equals(target)){
					Attributes.add(top_child);
				}
				//sorting情報を一緒に持って来るため
				//いらなかったらコメントしてください
				if(target.equals("attribute") && top_child.get(i).toString().equals("sorting")){
					Attributes.add(top_child);
				}
				if(target.equals("attribute") && top_child.get(i).toString().equals("aggregate")){
					break;
				}
				if(top_child.get(i).toString().indexOf("@{") != -1){
					Attributes.add(top_child.get(i));
				}
			}else{
				getAttribute(top_child.getExtList(i), target);
			}
		}
	}
}
