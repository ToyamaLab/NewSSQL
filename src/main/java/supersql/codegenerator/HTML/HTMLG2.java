package supersql.codegenerator.HTML;

import java.io.Serializable;

import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class HTMLG2 extends Grouper implements Serializable {

	private HTMLEnv html_env;
	private HTMLEnv html_env2;
	boolean retFlag = false; // 20140611_masato pagenationフラグ
	boolean pageFlag = false; // 20140611_masato pagenationフラグ

	// int counter = 0;
	// int level = 0;

	// ���󥹥ȥ饯��
	public HTMLG2(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.html_env = henv;
		this.html_env2 = henv2;

	}

	@Override
	public String getSymbol() {
		return "HTMLG2";
	}

	// G2��work�᥽�å�
	@Override
	public String work(ExtList data_info) {

		Log.out("------- G2 -------");
		this.setDataList(data_info);

		if (Incremental.flag || Ehtml.flag) {
			String row = "";
			String column = "";
			String scroll = "";

			if(decos.containsKey("infinite-scroll")){
				scroll = " scroll=\'id'";//TODO id->変数にして<div>タグのidを作成
			}
			// ページネーション
			if (decos.containsKey("row") && decos.containsKey("column")) {
				html_env.g2PaginationRowNum = Integer.parseInt(decos
						.getStr("row"));
				row = " row=\'" + html_env.g2PaginationRowNum + "\'";
				html_env.g2PaginationColumnNum = Integer.parseInt(decos
						.getStr("column"));
				column = " column=\'" + html_env.g2PaginationColumnNum + "\'";
			} else if (decos.containsKey("row")) { // 複合反復子
				html_env.g2RetNum = Integer.parseInt(decos.getStr("row"));
				row = " row=\'" + html_env.g2RetNum + "\'";
			}
			// if (decos.containsKey("row") && decos.containsKey("column")) {
			// html_env.itemNumPerPage = Integer.parseInt(decos.getStr("row"));
			// row = " row=\'"
			// + html_env.itemNumPerPage
			// + "\'";
			// }
			String outType = "div";
			if (html_env.xmlDepth != 0) {
				// 親のoutTypeを継承
				outType = html_env.outTypeList.get(html_env.xmlDepth - 1);
			}
			if (decos.containsKey("table") || !outType.equals("div")) {
				html_env.outTypeList.add(html_env.xmlDepth, "table");
			} else { // デフォルト -> div
				html_env.outTypeList.add(html_env.xmlDepth, "div");
			}
			if (decos.containsKey("div")) {
				html_env.outTypeList.add(html_env.xmlDepth, "div");
			}
			// System.out.println("G2 tableFlg = " + tableFlg + ", divFlg = " +
			// divFlg);
			html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			// add taji for infinite scroll 20170203
			Incremental.outXMLData(html_env.xmlDepth, "<Grouper"
					+ html_env.gLevel + " type=\'G2\' outType=\'" + html_env.outTypeList.get(html_env.xmlDepth) + "\'"
					+ " class=\'" + HTMLEnv.getClassID(this) + "\'" + row + column + scroll
					+ ">\n");
			while (this.hasMoreItems()) {
				// System.out.println("ここ: tableFlg = " + tableFlg +
				// ", divFlg = " + divFlg);
				html_env.gLevel++;
				html_env.xmlDepth++;
				this.worknextItem();
				html_env.gLevel--;
				html_env.xmlDepth--;
				if (decos.containsKey("row") && decos.containsKey("column")) {
					html_env.itemCount++;
				}
			}
			Incremental.outXMLData(html_env.xmlDepth, "</Grouper"
					+ html_env.gLevel + ">\n");
			return null;
		} else {

			if (HTMLEnv.getSelectFlg())
				data_info = (ExtList) data_info.get(0);

			String classname = Modifier.getClassName(decos, HTMLEnv.getClassID(this));//kotani_idmodifier_ok
			//			if (this.decos.containsKey("class")) {
			//				classname = this.decos.getStr("class");
			//			} else {
			//				classname = HTMLEnv.getClassID(this);
			//			}

			// tk start////////////////////////////////////////////////////
			html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

			boolean flag = false; // 20140528_masato
			int count = 0; // 20140526_masato
			int count2 = 0; // 20140611_masato
			int i = 0; // 20140526_masato
			int j = 0; // 20140611_masato

			if (decos.containsKey("row") && decos.containsKey("column")) {
				String paginationHTML_JS = HTMLPagination.getPaginationHTML_JS1();

				if (html_env.decorationStartFlag.size() > 0) {
					if (html_env.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0)
						.append(paginationHTML_JS);
					} else {
						HTMLDecoration.ends.get(0)
						.append(paginationHTML_JS);
					}
				}
				html_env.code
				.append(paginationHTML_JS);
			}

			// 20140528_masato
			if (decos.containsKey("row")) {
				retFlag = true;
				i = Integer.parseInt(decos.getStr("row"));
				if (html_env.decorationStartFlag.size() > 0) {
					if (html_env.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.fronts.get(0).append(html_env.tableBorder + "\"");
						HTMLDecoration.fronts.get(0).append(" class=\"");
						HTMLDecoration.fronts.get(0).append("nest\"");
						HTMLDecoration.fronts.get(0).append(html_env.getOutlineMode());
						HTMLDecoration.fronts.get(0).append(">");
						HTMLDecoration.fronts.get(0).append("<TR><TD class=\"" + HTMLEnv.getClassID(tfe) + " nest\">\n");
					} else {
						HTMLDecoration.ends.get(0).append("TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append("nest\"");
						HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
						HTMLDecoration.ends.get(0).append(">");
						HTMLDecoration.ends.get(0).append("<TR><TD class=\"" + HTMLEnv.getClassID(tfe) + " nest\">\n");
					}
				} else {
					//taji comment out for []!3% //todo: G1と同じ処理にする？
					//					html_env.code
					//					.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					//					html_env.code.append(html_env.tableBorder + "\" ");
					//					html_env.code.append("class=\"");
					//					html_env.code.append("nest\"");
					//					html_env.code.append(html_env.getOutlineMode());
					//					html_env.code.append(">");
					//					html_env.code.append("<TR><TD class=\""
					//							+ HTMLEnv.getClassID(tfe) + " nest\">\n");
				}
			}

			if (decos.containsKey("row") && decos.containsKey("column")) {
				retFlag = false;
				pageFlag = true;
				i = Integer.parseInt(decos.getStr("row"));
				j = Integer.parseInt(decos.getStr("column"));
			}

			// 20140613_masato && j != 1を追加　→　[tfe]!3%とかのとき
			if (!GlobalEnv.isOpt()) {
				if (html_env.decorationStartFlag.size() > 0) {
					if (html_env.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.fronts.get(0).append(html_env.tableBorder + "\"");
						HTMLDecoration.classes.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						if (html_env.embedFlag) {
							HTMLDecoration.ends.get(0).append(" embed");
						}
						if (decos.containsKey("outborder")) {
							HTMLDecoration.ends.get(0).append(" noborder");
						}
						HTMLDecoration.ends.get(0).append(" nest\"");
						HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
						HTMLDecoration.ends.get(0).append(">");
						html_env.decorationStartFlag.set(0, false);
					} else {
						HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						if (html_env.embedFlag) {
							HTMLDecoration.ends.get(0).append(" embed");
						}
						if (decos.containsKey("outborder")) {
							HTMLDecoration.ends.get(0).append(" noborder");
						}
						HTMLDecoration.ends.get(0).append(" nest\"");
						HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
						HTMLDecoration.ends.get(0).append(">");
					}
				} else {
					html_env.code
					.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					html_env.code.append(html_env.tableBorder + "\" ");
					Log.out("embed flag :" + html_env.embedFlag);
					html_env.code.append("class=\"");

					if (html_env.embedFlag)
						html_env.code.append(" embed ");

					if (decos.containsKey("outborder"))
						html_env.code.append(" noborder ");

					//					if (decos.containsKey("class")) {
					//						html_env.code.append(decos.getStr("class") + " ");
					//					}
					html_env.code.append(Modifier.getClassModifierValue(decos) + " ");//kotani_idmodifier_ok


					if (html_env.writtenClassId.contains(HTMLEnv.getClassID(this))) {
						// TFE10000�Ȃǂ̎w�肪��������t��
						// TODO 20140619_masato bgcolorとか
						html_env.code.append(HTMLEnv.getClassID(this) + " ");
					}
					html_env.code.append("nest\"");

					html_env.code.append(html_env.getOutlineMode());

					html_env.code.append(Modifier.getIdModifierValue(decos) + " ");//kotani_idmodifier_ok
					html_env.code.append(">");
					html_env.code.append("<TR>");//taji add
				}
			}
			// tk end/////////////////////////////////////////////////////

			Log.out("<TABLE class=\"" + HTMLEnv.getClassID(this) + "\">");

			while (this.hasMoreItems()) {

				// 20140528_masato
				count++;
				// System.out.println(html_env.gLevel);
				html_env.gLevel++;
				html_env.xmlDepth++;
				Log.out("selectFlg" + HTMLEnv.getSelectFlg());
				Log.out("selectRepeatFlg" + HTMLEnv.getSelectRepeat());
				Log.out("formItemFlg" + HTMLEnv.getFormItemFlg());
				if (HTMLEnv.getSelectRepeat()) {// if form_select
					// null
					// in case "select" repeat : not write "<TR><TD>" between
					// "<option>"s
				} else {
					// 20140613_masato
					// if(i != 0 && counter % i != 0 && j != 1){
					//
					// } else {
					if (html_env.decorationStartFlag.size() > 0) {

						HTMLDecoration.ends.get(0).append("<TR><TD class=\"" + HTMLEnv.getClassID(tfe) + " nest\">\n");
					} else {
						//						if(!((ExtList)data.get(dindex)).get(0).toString().equals("dummydummydummy")){
						html_env.code.append("<TR><TD class=\""
								+ HTMLEnv.getClassID(tfe) + " nest\">\n");
						//						}
					}
					Log.out("<TR><TD class=\"" + HTMLEnv.getClassID(tfe)
					+ " nest\">");
					// }

					// counter++;
				}
				String classid = HTMLEnv.getClassID(tfe);

				if (GlobalEnv.isOpt() && !HTMLEnv.getSelectRepeat()) {
					html_env2.code
					.append("<tfe type=\"repeat\" dimension=\"2\"");
					html_env2.code.append(" border=\"" + html_env.tableBorder
							+ "\"");

					if (decos.containsKey("tablealign"))
						html_env2.code.append(" align=\""
								+ decos.getStr("tablealign") + "\"");
					if (decos.containsKey("tablevalign"))
						html_env2.code.append(" valign=\""
								+ decos.getStr("tablevalign") + "\"");

					//					if (decos.containsKey("class")) {
					//						html_env2.code.append(" class=\"");
					//						html_env2.code.append(decos.getStr("class") + " ");
					//					}
					if (decos.containsKey("class")) {
						html_env2.code.append(" class=\"");//kotani_idmodifier_ok
						html_env2.code.append(Modifier.getClassModifierValue(decos)+ " ");//ここでclassを中途半端に書く
					}

					if (html_env.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						//						if (decos.containsKey("class")) {
						//							html_env2.code.append(HTMLEnv.getClassID(this)
						//									+ "\"");
						//						} else {
						//							html_env2.code.append(" class=\""
						//									+ HTMLEnv.getClassID(this) + "\"");
						//						}
						html_env2.code.append(Modifier.getClassName(decos, HTMLEnv.getClassID(this)));//kotani_idmodifier_ok

					} else if (decos.containsKey("class")) {
						html_env2.code.append("\"");
					}
					html_env2.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok　ここでidで終わる

					if (decos.containsKey("tabletype")) {
						html_env2.code.append(" tabletype=\""
								+ decos.getStr("tabletype") + "\"");
						if (decos.containsKey("cellspacing")) {
							html_env2.code.append(" cellspacing=\""
									+ decos.getStr("cellspacing") + "\"");
						}
						if (decos.containsKey("cellpadding")) {
							html_env2.code.append(" cellpadding=\""
									+ decos.getStr("cellpadding") + "\"");
						}
					}
					html_env2.code.append(">");
				}
				this.worknextItem();

				// if (html_env.notWrittenClassId.contains(classid)
				// && html_env.code.indexOf(classid) >= 0) {
				// html_env.code.delete(html_env.code.indexOf(classid),
				// html_env.code.indexOf(classid) + classid.length()
				// + 1);
				// }

				if (HTMLEnv.getSelectRepeat()) {

				} else {
					// chie
					html_env2.code.append("</tfe>");
					// if(i != 0 && counter % i != 0 && j != 1){
					//
					// } else {
					if (html_env.decorationStartFlag.size() > 0) {
						HTMLDecoration.ends.get(0).append("</TD></TR>\n");
					} else {
						html_env.code.append("</TD></TR>\n");
					}
					Log.out("</TD></TR>");
					// }
				}

				// 20140528_masato
				if (retFlag) {
					if (i != 0) {
						if (count % i == 0) {
							if (html_env.decorationStartFlag.size() > 0) {
								HTMLDecoration.ends.get(0).append("</TABLE></TD>");
								if (!this.hasMoreItems()) {
									flag = true;
									HTMLDecoration.ends.get(0).append("</TR>");
								} else {
									HTMLDecoration.ends.get(0).append("<TD>\n");
									HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
									HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
									HTMLDecoration.ends.get(0).append(" class=\"");
									HTMLDecoration.ends.get(0).append("nest\"");
									HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
									HTMLDecoration.ends.get(0).append(">");
								}
							} else {
								html_env.code.append("</TABLE></TD>");
								if (!this.hasMoreItems()) {
									flag = true;
									html_env.code.append("</TR>");
								} else {
									html_env.code.append("<TD>\n");
									html_env.code
									.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
									html_env.code.append(html_env.tableBorder
											+ "\" ");
									html_env.code.append("class=\"");
									html_env.code.append("nest\"");
									html_env.code.append(html_env.getOutlineMode());
									html_env.code.append(">");
								}
							}
						}
					}
				}

				if (pageFlag) {
					if (count % i == 0) {
						count2++;
						// if(counter % i != 0 && j != 1){
						//
						// } else {
						if (html_env.decorationStartFlag.size() > 0) {
							HTMLDecoration.ends.get(0).append("</TABLE></TD>");
							if (!this.hasMoreItems()) {
								flag = true;
								HTMLDecoration.ends.get(0).append("</TR>");
							}
						} else {
							html_env.code.append("</TABLE></TD>");
							if (!this.hasMoreItems()) {
								flag = true;
								html_env.code.append("</TR>");
							}
						}
						// }
						if (count2 % j == 0 && this.hasMoreItems()) {
							if (html_env.decorationStartFlag.size() > 0) {
								HTMLDecoration.ends.get(0).append("</TR></TABLE>");
								HTMLDecoration.ends.get(0).append("</div>\n");
								HTMLDecoration.ends.get(0).append("<div class=\"result\">\n");
								HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
								HTMLDecoration.ends.get(0).append(" class=\"");
								HTMLDecoration.ends.get(0).append("nest\"");
								HTMLDecoration.ends.get(0).append(">");
								HTMLDecoration.ends.get(0).append("<TR><TD class=\"" + HTMLEnv.getClassID(tfe) + "nest\">\n");
								HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
								HTMLDecoration.ends.get(0).append(" class=\"");
								HTMLDecoration.ends.get(0).append("nest\"");
								HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
								HTMLDecoration.ends.get(0).append(">");
							} else {
								html_env.code.append("</TR></TABLE>");
								html_env.code.append("</div>\n");
								html_env.code.append("<div class=\"result\">\n");
								html_env.code
								.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								html_env.code.append(html_env.tableBorder + "\" ");
								html_env.code.append("class=\"");
								html_env.code.append("nest\"");
								html_env.code.append(">");
								html_env.code.append("<TR><TD class=\""
										+ HTMLEnv.getClassID(tfe) + " nest\">\n");
								// 20140613_masato
								// if(counter % i != 0 && j != 1){
								//
								// } else {
								html_env.code
								.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								html_env.code.append(html_env.tableBorder + "\" ");
								html_env.code.append("class=\"");
								html_env.code.append("nest\"");
								html_env.code.append(html_env.getOutlineMode());

								html_env.code.append(">");
								// }
							}
						} else {
							if (html_env.decorationStartFlag.size() > 0) {
								HTMLDecoration.ends.get(0).append("<TD>\n");
								HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								HTMLDecoration.ends.get(0).append(html_env.tableBorder + "\"");
								HTMLDecoration.ends.get(0).append(" class=\"");
								HTMLDecoration.ends.get(0).append("nest\"");
								HTMLDecoration.ends.get(0).append(html_env.getOutlineMode());
								HTMLDecoration.ends.get(0).append(">");
							} else {
								html_env.code.append("<TD>\n");
								html_env.code
								.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								html_env.code.append(html_env.tableBorder + "\" ");
								html_env.code.append("class=\"");
								html_env.code.append("nest\"");
								html_env.code.append(html_env.getOutlineMode());
								html_env.code.append(">");
							}
						}
					}
				}

				html_env.gLevel--;
				html_env.xmlDepth--;
			}

			if (HTMLEnv.getSelectRepeat()) {
				if (HTMLEnv.getSelectRepeat()) {
					// chie
					html_env2.code.append("</select></VALUE></tfe>");
					if (html_env.decorationStartFlag.size() > 0) {
						HTMLDecoration.ends.get(0).append("</select></TD></TR>\n");
					} else {
						html_env.code.append("</select></TD></TR>\n");
					}
					Log.out("</TD></TR>");
					HTMLEnv.setSelectRepeat(false);
					HTMLEnv.incrementFormPartsNumber();
				} else {
					HTMLEnv.incrementFormPartsNumber();
				}
			}

			// html_env2.code.append("</tfe>");
			// 20140528_masato
			if (html_env.decorationStartFlag.size() > 0) {
				if (html_env.decorationStartFlag.get(0)) {
					if (flag) {
						HTMLDecoration.ends.get(0).append("</TABLE>\n");
					}
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
					html_env.decorationStartFlag.set(0, false);
				} else {
					if (flag) {
						HTMLDecoration.ends.get(0).append("</TABLE>\n");
					}
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
				}
			} else {
				if (flag) {
					html_env.code.append("</TABLE>\n");
				}
				html_env.code.append("</TR></TABLE>\n");
				if (pageFlag) {//taji add
					html_env.code.append("</div>\n");
					html_env.code.append("</div>\n");
					html_env.code.append("</div>\n");
				}
			}
			Log.out("</TABLE>");

			Log.out("TFEId = " + HTMLEnv.getClassID(this));
			// html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			// System.out.println("</Grouper" + html_env.gLevel + ">");
			// html_env.xmlCode.append("</Grouper" + html_env.gLevel + ">\n");
			return null;
		}
	}

}
