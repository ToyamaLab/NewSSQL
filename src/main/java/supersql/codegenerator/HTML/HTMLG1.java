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

public class HTMLG1 extends Grouper implements Serializable {

	private HTMLEnv html_env;
	private HTMLEnv html_env2;
	boolean retFlag = false;	// 20140602_masato pagenationフラグ
	boolean pageFlag = false;	// 20140602_masato pagenationフラグ

	// ���󥹥ȥ饯��
	public HTMLG1(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.html_env = henv;
		this.html_env2 = henv2;

	}

	@Override
	public String getSymbol() {
		return "HTMLG1";
	}

	// G1��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		Log.out("------- G1 -------");
		this.setDataList(data_info);
		
		if (Incremental.flag || Ehtml.flag) {
			String row = "";
			String column = "";
			// ページネーション
			if (decos.containsKey("row") && decos.containsKey("column")) {
				html_env.g1PaginationRowNum = Integer.parseInt(decos
						.getStr("row"));
				row = " row=\'" + html_env.g1PaginationRowNum + "\'";
				html_env.g1PaginationColumnNum = Integer.parseInt(decos
						.getStr("column"));
				column = " column=\'" + html_env.g1PaginationColumnNum + "\'";
			} else if (decos.containsKey("column")) { // 複合反復子
				html_env.g1RetNum = Integer.parseInt(decos.getStr("column"));
				column = " column=\'" + html_env.g1RetNum + "\'";
			}
//			if (decos.containsKey("row") && decos.containsKey("column")) {
//				html_env.itemNumPerPage = Integer.parseInt(decos.getStr("row"));
//				column = " row=\'"
//					+ html_env.itemNumPerPage
//					+ "\'";
//			}
			String outType = "div";
			if (html_env.xmlDepth != 0) {
				// 親のoutTypeを継承
				outType = html_env.outTypeList.get(html_env.xmlDepth - 1);
			}
			if (decos.containsKey("table") || !outType.equals("div")) {
				html_env.outTypeList.add(html_env.xmlDepth, "table");
			} else {
				html_env.outTypeList.add(html_env.xmlDepth, "div");
			}
			if (decos.containsKey("div")) {
				html_env.outTypeList.add(html_env.xmlDepth, "div");
			}
			Log.info("out:"+html_env.outTypeList);

			// System.out.println("G1 tableFlg = " + tableFlg + ", divFlg = " +
			// divFlg);
			html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			Incremental.outXMLData(html_env.xmlDepth, "<Grouper"
					+ html_env.gLevel + " type=\'G1\' outType=\'"
					+ html_env.outTypeList.get(html_env.xmlDepth)
					+ "\' class=\'" + HTMLEnv.getClassID(this) + "\'" + row + column + ">\n");
			while (this.hasMoreItems()) {
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
			HTMLEnv.start_table(this.getSymbol(), decos, html_env);
			
			String classname = Modifier.getClassName(decos, HTMLEnv.getClassID(this));
//			if (this.decos.containsKey("class")) {
//				classname = this.decos.getStr("class");
//			} else {
//				classname = HTMLEnv.getClassID(this);
//			}

			// tk start///////////////////////////////////////////////////
			html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

			// 20140526_masato
			int count = 0; // 20140526_masato
			int count2 = 0; // 20140526_masato
			int i = 0; // 20140526_masato
			int j = 0; // 20140526_masato
			if (decos.containsKey("column")) {
				i = Integer.parseInt(decos.getStr("column"));
				retFlag = true;
			}
			if (decos.containsKey("column") && decos.containsKey("row")) {
				i = Integer.parseInt(decos.getStr("column"));
				j = Integer.parseInt(decos.getStr("row"));
				pageFlag = true;
				retFlag = false;
			}

			// 20140602_masato
			if (pageFlag) {
				String paginationHTML_JS = HTMLPagination.getPaginationHTML_JS1();
				
				if (html_env.decorationStartFlag.size() > 0) {
					if (html_env.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0)
						.append(paginationHTML_JS);
					} else {
						HTMLDecoration.ends.get(0)
						.append(paginationHTML_JS);
					}
				} else {
					html_env.code
							.append(paginationHTML_JS);
				}
			}

			if (!GlobalEnv.isOpt()) {
				if (html_env.decorationStartFlag.size() > 0) {
					HTMLDecoration.fronts.get(0).append(HTMLEnv.getNewTableBorderDIV_start());
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
						HTMLDecoration.ends.get(0).append(HTMLEnv.getNewTableBorderStyle()+">");
						if (!HTMLEnv.isNewTableBorder)	HTMLDecoration.ends.get(0).append("<TR>");	//TODO 不要？
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
						HTMLDecoration.ends.get(0).append(HTMLEnv.getNewTableBorderStyle()+">");
						if (!HTMLEnv.isNewTableBorder)	HTMLDecoration.ends.get(0).append("<TR>");	//TODO 不要？
					}
				} else {
					html_env.code.append(HTMLEnv.getNewTableBorderDIV_start());
					html_env.code
							.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					html_env.code.append(html_env.tableBorder + "\"");
	
					html_env.code.append(" class=\"");
	
					if (html_env.embedFlag)
						html_env.code.append("embed ");
	
					if (decos.containsKey("outborder"))
						html_env.code.append(" noborder ");
	
//					if (decos.containsKey("class")) {
//						// class=menu�Ȃǂ̎w�肪��������t��
//						html_env.code.append(decos.getStr("class") + " ");
//					}
					html_env.code.append(Modifier.getClassModifierValue(decos) + " ");//kotani_idmodifier_ok
					
				
					if (html_env.haveClass == 1) {
						// class=menu�Ȃǂ̎w�肪��������t��
						html_env.code.append(HTMLEnv.getClassID(this) + " ");
					}
					html_env.code.append("nest\"");
	
					html_env.code.append(html_env.getOutlineMode());
	
					html_env.code.append(Modifier.getIdModifierValue(decos) + " ");//kotani_idmodifier_ok
					html_env.code.append(HTMLEnv.getNewTableBorderStyle()+">");
					if (!HTMLEnv.isNewTableBorder)	html_env.code.append("<TR>");	//TODO 不要？
				}
			}
			// tk end//////////////////////////////////////////////////////

			Log.out("<TABLE class=\"" + HTMLEnv.getClassID(this) + "\"><TR>");

			while (this.hasMoreItems()) {
				html_env.gLevel++;
				html_env.xmlDepth++;
				count++;
				if (GlobalEnv.isOpt()) {
					html_env2.code
							.append("<tfe type=\"repeat\" dimension=\"1\"");

//					if (decos.containsKey("class")) {
//						// class=menu�Ȃǂ̎w�肪��������t��
//						html_env2.code.append(" class=\"");
//						html_env2.code.append(decos.getStr("class") + " ");
//					}
					if (decos.containsKey("class")) {
						html_env2.code.append(" class=\"");//kotani_idmodifier_ok
						html_env2.code.append(Modifier.getClassModifierValue(decos)+ " ");//ここで中途半端にclassやって
					}
					
					
					if (html_env.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						// TFE10000�Ȃǂ̎w�肪��������t��
//						if (decos.containsKey("class")) {
//							html_env2.code.append(HTMLEnv.getClassID(this)
//									+ "\"");
//						} else {
//							html_env2.code.append(" class=\""
//									+ HTMLEnv.getClassID(this) + "\"");
//						}
						html_env2.code.append(Modifier.getClassName(decos, HTMLEnv.getClassID(this)));//kotani_idmodifier_ok、ここでidで終わらせる
						
					} else if (decos.containsKey("class")) {
						html_env2.code.append("\"");
					}
					html_env2.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
					
					html_env2.code.append(" border=\"" + html_env.tableBorder
							+ "\"");

					if (decos.containsKey("tablealign"))
						html_env2.code.append(" align=\""
								+ decos.getStr("tablealign") + "\"");
					if (decos.containsKey("tablevalign"))
						html_env2.code.append(" valign=\""
								+ decos.getStr("tablevalign") + "\"");

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
					html_env2.code.append(Modifier.getIdModifierValue(decos)+ " ");//kotani_idmodifier_ok
					
					html_env2.code.append(">");
				}

				if (html_env.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("<TD class=\"" + HTMLEnv.getClassID(tfe) + " nest\">\n");
				} else {
//					if(!((ExtList)data.get(dindex)).get(0).toString().equals("dummydummydummy")){
						html_env.code.append("<TD class=\"" + HTMLEnv.getClassID(tfe)
							+ " nest\">\n");
//					}
				}
				String classid = HTMLEnv.getClassID(tfe);

				Log.out("<TD class=\"" + HTMLEnv.getClassID(tfe) + " nest\">");

				this.worknextItem();
//				if(!((ExtList)data.get(dindex - 1)).get(0).toString().equals("dummydummydummy")){
					if (html_env.notWrittenClassId.contains(classid)) {
						html_env.code.delete(html_env.code.indexOf(classid),
								html_env.code.indexOf(classid) + classid.length()
								+ 1);
					}
//				}

				html_env2.code.append("</tfe>");

				if (html_env.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("</TD>\n");
				} else {
					html_env.code.append("</TD>\n");
				}
				Log.out("</TD>");
				
				if (retFlag) {
					if ((count % i) == 0) { // 20140526_masato
						if (html_env.decorationStartFlag.size() > 0) {
							HTMLDecoration.ends.get(0).append("</TR>\n");
							HTMLDecoration.ends.get(0).append("<TR>\n");
						} else {
							html_env.code.append("</TR>\n");
							html_env.code.append("\n");
						}
						count2++;
					}
				}

				if (pageFlag) {
					if ((count % i) == 0) { // 20140526_masato
						count2++;
						if (html_env.decorationStartFlag.size() > 0) {
							HTMLDecoration.ends.get(0).append("</TR>\n");
						} else {
							html_env.code.append("</TR>\n");
						}

						if (count2 % j == 0 && this.hasMoreItems()) {
							if (html_env.decorationStartFlag.size() > 0) {
								HTMLDecoration.ends.get(0).append("</TABLE>\n"
										+ "</div>\n<div class=\"result\">");
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
								HTMLDecoration.ends.get(0).append(">");
							} else {
								html_env.code.append("</TABLE>\n"
										+ "</div>\n<div class = \"result\">");
								html_env.code
										.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
								html_env.code.append(html_env.tableBorder + "\"");
	
								html_env.code.append(" class=\"");
	
								if (html_env.embedFlag)
									html_env.code.append("embed ");
	
								if (decos.containsKey("outborder"))
									html_env.code.append(" noborder ");
	
//								if (decos.containsKey("class")) {
//									html_env.code.append(decos.getStr("class")+ " ");
//								}
								html_env.code.append(Modifier.getClassModifierValue(decos)+ " ");
								html_env.code.append(Modifier.getIdModifierValue(decos)+ " ");//kotani_idmodifier_ok
								
								if (html_env.haveClass == 1) {
									// class=menu�Ȃǂ̎w�肪��������t��
									html_env.code.append(HTMLEnv.getClassID(this)
											+ " ");
								}
								html_env.code.append("nest\"");
	
								// masato_20140602 なんじゃこりゃ？
								// html_env.code.append(html_env.getOutlineMode());
	
								html_env.code.append(">");
							}
						}
						if (html_env.decorationStartFlag.size() > 0) {
							HTMLDecoration.ends.get(0).append("<TR>\n");
						} else {
							html_env.code.append("<TR>\n");
						}
					}
				}
				html_env.gLevel--;
				html_env.xmlDepth--;
			}

			if (HTMLEnv.getFormItemFlg()) {
				HTMLEnv.incrementFormPartsNumber();
			}

			// html_env2.code.append("</tfe>");
			
			if (html_env.decorationStartFlag.size() > 0) {
				if (html_env.decorationStartFlag.get(0)) {
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
					HTMLDecoration.ends.get(0).append(HTMLEnv.getNewTableBorderDIV_end(this.getSymbol()));
					if (pageFlag) {
						HTMLDecoration.ends.get(0).append("</div>\n");
						HTMLDecoration.ends.get(0).append("</div>\n");
						HTMLDecoration.ends.get(0).append("</div>\n");
					}
					html_env.decorationStartFlag.set(0, false);
				} else {
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
					HTMLDecoration.ends.get(0).append(HTMLEnv.getNewTableBorderDIV_end(this.getSymbol()));
					if (pageFlag) {
						HTMLDecoration.ends.get(0).append("</div>\n");
						HTMLDecoration.ends.get(0).append("</div>\n");
						HTMLDecoration.ends.get(0).append("</div>\n");
					}
				}
			} else {
				html_env.code.append("</TR></TABLE>\n");
				html_env.code.append(HTMLEnv.getNewTableBorderDIV_end(this.getSymbol()));
				if (pageFlag) {
					html_env.code.append("</div>\n");
					html_env.code.append("</div>\n");
					html_env.code.append("</div>\n");
				}
			}
			Log.out("</TR></TABLE>");
			HTMLEnv.end_table(this.getSymbol());

			Log.out("TFEId = " + HTMLEnv.getClassID(this));
			// html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			// System.out.println("</Grouper" + html_env.gLevel + ">");
			// html_env.xmlCode.append("</Grouper" + html_env.gLevel + ">\n");
			return null;
		}
	}

}
