package supersql.codegenerator.HTML;

import java.io.Serializable;

import org.apache.xpath.operations.Mod;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

//tk

public class HTMLC1 extends Connector implements Serializable {

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;

	// ���󥹥ȥ饯��
	public HTMLC1(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "HTMLC1";
	}

	// C1��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		Log.out("------- C1 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfes.size=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());
		this.setDataList(data_info);

		if (Incremental.flag || Ehtml.flag) {
			String outType = "div";
			
			if(htmlEnv.xmlDepth!=0){
				// 親のoutTypeを継承
				outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth-1);
			}
			if (decos.containsKey("table") || !outType.equals("div")) {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "table");
			} else {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
			}
			if (decos.containsKey("div")) {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
			}
			Log.info("out:"+htmlEnv.outTypeList);

//			System.out.println("C1 tableFlg = " + tableFlg + ", divFlg = " + divFlg);
			htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
				Incremental.outXMLData(htmlEnv.xmlDepth,
						"<Connector" + htmlEnv.cNum
								+ " type=\'C1\' outType=\'" + htmlEnv.outTypeList.get(htmlEnv.xmlDepth) + "\' class=\'"
								+ HTMLEnv.getClassID(this) + "\'>\n");
//			String ret = "";
			while (this.hasMoreItems()) {
				htmlEnv.cNum++;
				htmlEnv.xmlDepth++;
//				Incremental.outXMLData(htmlEnv.xmlDepth, "<"+HTMLEnv.getClassID(this)+" outType='"+htmlEnv.outTypeList.get(htmlEnv.xmlDepth)+"'>"+ret+"</"+HTMLEnv.getClassID(this)+">\n");
//				ret = this.worknextItem();
				this.worknextItem();
				htmlEnv.cNum--;
				htmlEnv.xmlDepth--;
			}
			Incremental.outXMLData(htmlEnv.xmlDepth, "</Connector"
					+ htmlEnv.cNum + ">\n");
			return null;
		} else {
			if (decos.containsKey("form")) {
				htmlEnv2.code.append("<form" + HTMLEnv.getFormNumber()
						+ "start />");
				if (decos.getStr("form").toLowerCase().equals("search"))
					HTMLEnv.setSearch(true);
			}

			if (decos.containsKey("insert")) {
				HTMLEnv.setIDU("insert");
			}
			if (decos.containsKey("update")) {
				HTMLEnv.setIDU("update");
			}
			if (decos.containsKey("delete")) {
				HTMLEnv.setIDU("delete");
			}
			
			
//			if (this.decos.containsKey("class")) {
//				classname = this.decos.getStr("class");
//			} else {
//				classname = HTMLEnv.getClassID(this);
//			}
			 String classname = Modifier.getClassName(decos, HTMLEnv.getClassID(this));//kotani_idmodifier_ok

			// tk
			// start///////////////////////////////////////////////////////////////////////
			htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

			if (!GlobalEnv.isOpt()) {

				if (htmlEnv.decorationStartFlag.size() > 0) {
					if (htmlEnv.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.fronts.get(0).append(htmlEnv.tableBorder + "\"");
						HTMLDecoration.fronts.get(0).append(htmlEnv.getOutlineMode());
						HTMLDecoration.classes.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						HTMLDecoration.ends.get(0).append("\" "+ Modifier.getIdModifierValue(decos)+" ><TR>");//kotani_idmodifier_ok
						htmlEnv.decorationStartFlag.set(0, false);
					} else {
						HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.ends.get(0).append(htmlEnv.tableBorder + "\"");
						HTMLDecoration.ends.get(0).append(htmlEnv.getOutlineMode());
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						HTMLDecoration.ends.get(0).append("\" "+ Modifier.getIdModifierValue(decos)+" ><TR>");//kotani_idmodifier_ok
					}
				} else {
					htmlEnv.code
							.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					htmlEnv.code.append(htmlEnv.tableBorder + "\"");
					htmlEnv.code.append(htmlEnv.getOutlineMode());
	
					// classid������Ȥ��ˤ�������
					if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
						htmlEnv.code.append(" class=\"");
						htmlEnv.code.append(HTMLEnv.getClassID(this));
					}
	
					if (decos.containsKey("class")) {
						if (!htmlEnv.writtenClassId.contains(HTMLEnv
								.getClassID(this))) {
							htmlEnv.code.append(" class=\"");
						} else {
							htmlEnv.code.append(" ");
						}

						htmlEnv.code.append(Modifier.getClassModifierValue(decos) + "\" ");//kotani_idmodifier_ok
						
					} else if (htmlEnv.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						htmlEnv.code.append("\" ");
					}
					
					htmlEnv.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
					htmlEnv.code.append("><TR>");
				}
			}

			// xml
			if (GlobalEnv.isOpt()) {
				htmlEnv2.code.append("<tfe type=\"connect\" dimension =\"1\"");
				if (decos.containsKey("tablealign"))
					htmlEnv2.code.append(" align=\""
							+ decos.getStr("tablealign") + "\"");
				if (decos.containsKey("tablevalign"))
					htmlEnv2.code.append(" valign=\""
							+ decos.getStr("tablevalign") + "\"");
				if (decos.containsKey("tabletype")) {
					htmlEnv2.code.append(" tabletype=\""
							+ decos.getStr("tabletype") + "\"");
					if (decos.containsKey("cellspacing")) {
						htmlEnv2.code.append(" cellspacing=\""
								+ decos.getStr("cellspacing") + "\"");
					}
					if (decos.containsKey("cellpadding")) {
						htmlEnv2.code.append(" cellpadding=\""
								+ decos.getStr("cellpadding") + "\"");
					}
					if (decos.containsKey("border")) {
						htmlEnv2.code.append(" border=\""
								+ decos.getStr("border").replace("\"", "")
								+ "\"");
					}
					if (decos.containsKey("tableborder")) {
						htmlEnv2.code.append(" tableborder=\""
								+ decos.getStr("tableborder").replace("\"", "")
								+ "\"");
					}
				} else {
					if (decos.containsKey("border")) {
						htmlEnv2.code.append(" border=\""
								+ decos.getStr("border").replace("\"", "")
								+ "\"");
					} else {
						htmlEnv2.code.append(" border=\""
								+ htmlEnv.tableBorder.replace("\"", "") + "\"");
					}
					if (decos.containsKey("tableborder")) {
						htmlEnv2.code.append(" tableborder=\""
								+ decos.getStr("tableborder").replace("\"", "")
								+ "\"");
					}
				}
				if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
					htmlEnv2.code.append(" class=\"");
					htmlEnv2.code.append(HTMLEnv.getClassID(this));
				}
				if (decos.containsKey("class")) {
					if (!htmlEnv.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						htmlEnv2.code.append(" class=\"");
					} else {
						htmlEnv2.code.append(" ");
					}
					//htmlEnv2.code.append(decos.getStr("class") + "\" ");
					htmlEnv2.code.append(Modifier.getClassModifierValue(decos) + "\" ");//kotani_idmodifier_ok
					
				} else if (htmlEnv.writtenClassId.contains(HTMLEnv
						.getClassID(this))) {
					htmlEnv2.code.append("\" ");
				}
				htmlEnv2.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
				
				if (decos.containsKey("form")) {
					htmlEnv2.code.append(" form=\"" + HTMLEnv.getFormNumber()
							+ "\" ");
				}
				htmlEnv2.code.append(">");
			}

			// tk
			// end////////////////////////////////////////////////////////////////////

			// Log.out("<TABLE class=\""+HTMLEnv.getClassID(this) + "\"><TR>");
			int i = 0;

			if (decos.containsKey("form")) {
				htmlEnv.code.append(HTMLFunction.createForm(decos));
				HTMLEnv.setFormItemFlg(true, null);
			}

			while (this.hasMoreItems()) {
				htmlEnv.cNum++;
				htmlEnv.xmlDepth++;
				ITFE tfe = tfes.get(i);
				
				if (htmlEnv.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("<TD class=\"" + classname + " nest\">\n");
				} else {
//					if(!data.isEmpty()){
//						if(!data.get(dindex).equals("dummydummydummy")){
							htmlEnv.code.append("<TD class=\"" + HTMLEnv.getClassID(tfe)
								+ " nest\">\n");
//						}
//					}else{
//						htmlEnv.code.append("<TD class=\"" + HTMLEnv.getClassID(tfe)
//						+ " nest\">\n");
//					}
				}
				String classid = HTMLEnv.getClassID(tfe);

				// Log.out("<TD class=\""
				// + HTMLEnv.getClassID(tfe) + " nest\"> decos : " + decos);
				this.worknextItem();

				// if (htmlEnv.notWrittenClassId.contains(classid)) {
				// htmlEnv.code.delete(htmlEnv.code.indexOf(classid),
				// htmlEnv.code.indexOf(classid) + classid.length()
				// + 1);
				// }

				if (htmlEnv.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("</TD>\n");
				} else {
//					if(!data.isEmpty()){
//						if(!data.get(dindex - 1).equals("dummydummydummy")){
							htmlEnv.code.append("</TD>\n");
//						}
//					}else{
//						htmlEnv.code.append("</TD>\n");
//					}
					// Log.out("</TD>");
				}
				i++;
				htmlEnv.cNum--;
				htmlEnv.xmlDepth--;
			}

			htmlEnv2.code.append("</tfe>");
			if (decos.containsKey("form")) {
				htmlEnv2.code.append("<form" + HTMLEnv.getFormNumber()
						+ "end />");
				Log.out("<form" + HTMLEnv.getFormNumber() + "end />");
				htmlEnv.code.append(HTMLEnv.exFormNameCreate());
				htmlEnv.code.append("</form>");
				HTMLEnv.setFormItemFlg(false, null);
				HTMLEnv.incrementFormNumber();
				if (decos.getStr("form").toLowerCase().equals("search"))
					HTMLEnv.setSearch(false);
			}

			if (htmlEnv.decorationStartFlag.size() > 0) {
				if (htmlEnv.decorationStartFlag.get(0)) {
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
					htmlEnv.decorationStartFlag.set(0, false);
				} else {
					HTMLDecoration.ends.get(0).append("</TR></TABLE>\n");
				}
			} else {
				htmlEnv.code.append("</TR></TABLE>\n");
			}

			Log.out("+++++++ C1 +++++++");
			return null;
		}
	}

}
