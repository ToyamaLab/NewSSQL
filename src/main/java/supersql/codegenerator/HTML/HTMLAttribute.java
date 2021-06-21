package supersql.codegenerator.HTML;

import java.io.File;
import java.util.ArrayList;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Preprocessor;

//added by goto

public class HTMLAttribute extends Attribute {

	private String[] formHtml = { "", "submit", "select", "checkbox", "radio",
			"text", "textarea", "hidden" };
	private String[] formSql = { "", "delete", "update", "insert", "login",
			"logout" };

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;
	private int whichForm;

	// private boolean tableFlg = false;
	// private boolean divFlg = false;
	// 鐃緒申鐃藷ストラク鐃緒申
	public HTMLAttribute(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		super();
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	public HTMLAttribute(Manager manager, HTMLEnv henv, HTMLEnv henv2, boolean b) {
		super(b);
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	private <T> String computeStringForDecoration(ExtList<T> data_info) {
		String classNames = "";
		for (int i = 1; i < this.AttNames.size(); i++) {
			if (((data_info.get(i))).toString().equals("t")) {
				if (decos.getClassesIds().get(AttNames.get(i)) != null)
					classNames += " C_"
							+ decos.getClassesIds().get(AttNames.get(i));
			} else {
				if (decos.getClassesIds().get("!" + AttNames.get(i)) != null)
					classNames += " C_"
							+ decos.getClassesIds().get("!" + AttNames.get(i));
			}
		}
		return classNames;
	}

	private String cond() {
		String ret = "";
		if (HTMLEnv.formPartsNumber != HTMLEnv.searchId) {
			HTMLEnv.searchId = HTMLEnv.formPartsNumber;
			if (!HTMLEnv.condName.isEmpty() && !HTMLEnv.cond.isEmpty()) {
				ret += "<input type=\"hidden\" name=\"cond_name"
						+ HTMLEnv.formPartsNumber + "\" value=\""
						+ HTMLEnv.condName + "\" />";
				ret += "<input type=\"hidden\" name=\"cond"
						+ HTMLEnv.formPartsNumber + "\" value=\""
						+ HTMLEnv.cond + "\" />";
				ret += "<input type=\"hidden\" name=\"value_type"
						+ HTMLEnv.formPartsNumber + "\" value=\"String\" />";
			}
		}
		return ret;
	}

	private void createForm(ExtList data_info) {

		String name = new String();
		String inputFormString = new String();

		for (int i = 1; i < formSql.length; i++) {
			if (decos.containsKey(formSql[i])
					|| HTMLEnv.getIDU().equals(formSql[i])) {
				switch (i) {
				case 1: // delete
					if (decos.containsKey(formSql[i])) {
						name = decos.getStr("delete");
					} else {
						name = decos.getStr("attributeName");
					}
					inputFormString += "<input type=\"checkbox\" name=\""
							+ name + "\" value=\"" + this.getStr(data_info)
							+ "\" />";
					whichForm = i;
					break;
				case 2: // update
					if (decos.containsKey(formSql[i])) {
						name = decos.getStr("update");
					} else {
						name = decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 3: // insert
					if (decos.containsKey(formSql[i])) {
						name = decos.getStr("insert");
					} else {
						name = decos.getStr("attributeName");
					}
					whichForm = i;
					break;
				case 4: // login
					name = decos.getStr("login");
					if (decos.containsKey("att")) {
						inputFormString += "<input type=\"hidden\" name=\"att\" value=\""
								+ decos.getStr("att") + "\" />";
					}
					whichForm = i;
					break;
				case 5: // logout
					inputFormString += "<input type=\"hidden\" name=\"sqlfile\" value=\""
							+ decos.getStr("linkfile").replace("\"", "")
							+ "\" />";
					inputFormString += "<input type=\"submit\" name=\"logout\" value=\""
							+ this.getStr(data_info) + "\" />";
					whichForm = i;
					break;
				}
			}
		}

		if (1 < whichForm && whichForm < formSql.length - 1) { // update,insert,login
			String s;
			if (whichForm < 3) {// update
				s = this.getStr(data_info);
			} else {// insert,login,logout
				s = "";
			}
			if (decos.containsKey("pwd")) {
				inputFormString += "<input type=\"password\" name=\"" + name
						+ "\" value=\"" + s + "\" />";
				if (decos.containsKey("md5")) {
					inputFormString += "<input type=\"hidden\" name=\"" + name
							+ ":pwd\" value=\"md5\" />";
				}

			} else {
				if (s.isEmpty()) {
					inputFormString += "<input type=\"text\" name=\"" + name
							+ "\" />";
				} else {
					inputFormString += "<input type=\"text\" name=\"" + name
							+ "\" value=\"" + s + "\" />";
				}
			}

			// add constraint
			String constraint = new String();
			if (decos.containsKey("notnull")) {// not null
				constraint = "notnull";
			}
			if (decos.containsKey("number")) {// num or eng
				if (decos.containsKey("english")) {
					if (constraint.isEmpty())
						constraint = "numeng";
					else
						constraint += ",numeng";
				} else {// number
					if (constraint.isEmpty())
						constraint = "number";
					else
						constraint += ",number";
				}
			} else if (decos.containsKey("english")) {// eng
				if (constraint.isEmpty())
					constraint = "english";
				else
					constraint += ",english";
			}

			if (decos.containsKey("unique")) {// unique
				if (constraint.isEmpty())
					constraint = "unique";
				else
					constraint += ",unique";
			}

			if (constraint != null && !constraint.isEmpty())
				inputFormString += "<input type=\"hidden\" name=\"" + name
						+ ":const\" value=\"" + constraint + "\" />";

			Log.out("pppppp" + decos.containsKey("pkey"));
			if (decos.containsKey("pkey") && whichForm == 2) {// update
				if (!htmlEnv.code.toString().contains(
						"<input type=\"hidden\" name=\"pkey\" value=\"" + name
								+ "\" />"))
					inputFormString += "<input type=\"hidden\" name=\"pkey\" value=\""
							+ name + "\" />";
			}
		}

		htmlEnv.code.append(inputFormString);
		htmlEnv2.code.append(inputFormString);
		Log.out(inputFormString);

		inputFormString = new String();

		if (HTMLEnv.getFormItemFlg()) {
			for (int i = 1; i < formHtml.length; i++) {
				String real_value = HTMLEnv.getFormValueString();
				if (HTMLEnv.getFormItemName().equals(formHtml[i])) {
					switch (i) {
					case 1: // submit
						inputFormString = inputFormItems(data_info,
								formHtml[i], "");
						whichForm = i + formSql.length;
						break;
					case 2: // select
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;
					case 3: // checkbox
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;
					case 4: // radio
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;

					case 5: // input text
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;

					case 6: // textarea
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;

					case 7: // hidden
						inputFormString = inputFormItems(data_info,
								formHtml[i], real_value);
						whichForm = i + formSql.length;
						break;
					}
				}
			}
		}

		htmlEnv.code.append(inputFormString);
		htmlEnv2.code.append(inputFormString);
		Log.out(inputFormString);

	}

	/*
	 * private String closeFormItems(String itemType){ String ret = new
	 * String(); tuple_count = 0; if(itemType.equals("select")){
	 * HTMLEnv.setSelectRepeat(false); ret = "</select>"; }
	 * HTMLEnv.incrementFormName(); return ret; }
	 */

	private String inputFormItems(ExtList data_info, String itemType,
			String real_value) {
		String ret = "";
		String formname = HTMLEnv.getFormPartsName();
		;
		if (HTMLEnv.getSearch()) {
			ret += cond();
			formname = "value" + HTMLEnv.formPartsNumber;
		}
		String s = this.getStr(data_info);
		// tuple_count++;
		if (real_value.isEmpty()) {
			real_value = s;
		}
		// sizeoption
		String size = new String();
		if (decos.containsKey("size")) {
			size += " size=\"" + decos.getStr("size") + "\"";
		}
		if (decos.containsKey("height")) {
			size += " height=\"" + decos.getStr("height") + "\"";
		}
		if (decos.containsKey("cols")) {
			size += " cols=\"" + decos.getStr("cols") + "\"";
		}
		if (decos.containsKey("rows")) {
			size += " rows=\"" + decos.getStr("rows") + "\"";
		}

		size += Modifier.getClassAndIdMOdifierValues(decos);//kotani_idmodifier_ok

		if (itemType.equals(formHtml[1])) {// submit

		} else if (itemType.equals(formHtml[2])) {// select
			if (HTMLEnv.getSelectRepeat() == false) {
				ret += "<select name=\"" + formname + "\">";
				HTMLEnv.setSelectRepeat(true);
			}
			if (HTMLEnv.getSelected().length() != 0
					&& HTMLEnv.getSelected().equals(real_value)) {
				ret += "<option value=\"" + real_value + "\"" + size
						+ " selected=\"selected\" >" + s + "</option>";
			} else {
				ret += "<option value=\"" + real_value + "\"" + size + " >" + s
						+ "</option>";
			}
		} else if (itemType.equals(formHtml[3])) {// checkbox
			String checked = "";
			if (HTMLEnv.getChecked().length() != 0
					&& HTMLEnv.getChecked().equals(real_value)) {
				checked = " checked=\"checked\" ";
			}
			if (HTMLEnv.nameId.length() != 0) {
				ret += "<input type=\"checkbox\" name=\"" + formname + "["
						+ HTMLEnv.nameId + "]" + "\" value=\"" + real_value
						+ "\"" + size + checked + " />";
				ret += s;
			} else {
				ret += "<input type=\"checkbox\" name=\"" + formname
						+ "\" value=\"" + real_value + "\"" + size + checked
						+ " />";
				ret += s;
			}
		} else if (itemType.equals(formHtml[4])) {// radio
			String checked = "";
			if (HTMLEnv.getChecked().length() != 0
					&& HTMLEnv.getChecked().equals(real_value)) {
				checked = " checked=\"checked\" ";
			}
			ret += "<input type=\"radio\" name=\"" + formname + "\" value=\""
					+ real_value + "\"" + size + checked + " />";
			ret += s;
		} else if (itemType.equals(formHtml[5])) {// text
			if (decos.containsKey("pwd")) {
				ret += "<input type=\"password\" name=\"" + formname
						+ "\" value=\"" + real_value + "\"" + size + " />";
				if (decos.containsKey("md5")) {
					ret += "<input type=\"hidden\" name=\"" + formname
							+ ":pwd\" value=\"md5\" />";
				}
			} else {
				ret += "<input type=\"text\" name=\"" + formname
						+ "\" value=\"" + real_value + "\"" + size + " />";
			}
		} else if (itemType.equals(formHtml[6])) {// textarea
			ret += "<textarea name=\"" + formname + "\"" + size + ">";
			if (s != null) {
				ret += s;
			}
			ret += "</textarea>";
		} else if (itemType.equals(formHtml[7])) {// text
			ret += "<input type=\"hidden\" name=\"" + formname + "\" value=\""
					+ real_value + "\"" + size + " />";
		}

		String constraint = new String();
		if (decos.containsKey("notnull")) {
			constraint = "notnull";
		}
		if (decos.containsKey("number")) {
			if (decos.containsKey("english")) {// number or english
				if (constraint.isEmpty())
					constraint = "numeng";
				else
					constraint += ",numeng";
			} else {// number
				if (constraint.isEmpty())
					constraint = "number";
				else
					constraint += ",number";
			}
		} else if (decos.containsKey("english")) {// english
			if (constraint.isEmpty())
				constraint = "english";
			else
				constraint += ",english";
		}
		if (decos.containsKey("unique")) {// unique
			if (constraint.isEmpty())
				constraint = "unique";
			else
				constraint += ",unique";
		}

		if (constraint != null && !constraint.isEmpty())
			ret += "<input type=\"hidden\" name=\"" + formname
					+ ":const\" value=\"" + constraint + "\" />";

		return ret;
	}

	// Attribute鐃緒申work鐃潤ソ鐃獣ワ申
	@Override
	public String work(ExtList data_info) {
		Log.out("----Attribute----");
		/*
		 * if(GlobalEnv.getSelectFlg()) data_info = (ExtList) data_info.get(0);
		 */
//		if(this.getStr(data_info).equals("dummydummydummy")){
//			return null;
//		}
		String classname;
		classname = Modifier.getClassName(decos, HTMLEnv.getClassID(this));
		String link_a_tag_str = "";
		
		
//		// added by masato 20150924 incremental update
//		if (Incremental.flag || Ehtml.flag) {
//			// modified by masato 20151201 XMLの要素名をTFE******に変更
//			// Incremental.outXMLData(htmlEnv.xmlDepth, "<" +
//			// Items.get(0) + tfe + ">" + this.getStr(data_info) + "</"
//			// + Items.get(0) + ">\n");
//			String outType = "div";
//
//			if (htmlEnv.xmlDepth != 0) {
//				// 親のoutTypeを継承
//				outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth - 1);
//			}
//			if (decos.containsKey("table") || !outType.equals("div")) {
//				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "table");
//			} else {
//				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
//			}
//			if (decos.containsKey("div")) {
//				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
//			}
//			Log.info("out:"+htmlEnv.outTypeList);
//			String data = this.getStr(data_info)
//					.replaceAll("<", "&lt;");
//			data = data.replaceAll(">", "&gt;");
//			Incremental.outXMLData(
//					htmlEnv.xmlDepth,
//					"<Value outType=\'"
//							+ htmlEnv.outTypeList.get(htmlEnv.xmlDepth)
//							+ "\' class=\'" + HTMLEnv.getClassID(this)
//							+ "'>" + data + "</Value>\n");
//
//		}
//		Incremental.outXMLData(1, classname);	//Test
		
		
		HTMLEnv.start_table("HTMLAttribute", decos, htmlEnv);
		
		//tbt acc 180806
		if(GlobalEnv.joinFlag){
			// if (Incremental.flag || Ehtml.flag) {
			HTMLCONCAT.joinClassID = HTMLEnv.getClassID(this);
			HTMLCONCAT.joinDecos = this.decos;
			// htmlEnv.append_css_def_td(HTMLCONCAT.joinClassID, this.decos);
			if ((!Incremental.flag && !Ehtml.flag) || !Ehtml.isEhtml2()) ;
			else	htmlEnv.append_css_def_td(HTMLCONCAT.joinClassID, this.decos);
			return this.getStr(data_info);
		}
		//tbt end
//		Incremental.outXMLData(1, classname);	//Test

		htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
		if (GlobalEnv.isOpt()) {
			work_opt(data_info);
		} else {
			if (HTMLEnv.getFormItemFlg()
					&& HTMLEnv.getFormItemName().equals(formHtml[2])) {

			} else {
				if (htmlEnv.decorationStartFlag.size() > 0) {
					HTMLDecoration.fronts.get(0).append(HTMLEnv.getNewTableBorderDIV_start());
					if (htmlEnv.decorationEndFlag.get(0)) {
						// do nothing
					} else if (htmlEnv.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("<table" + htmlEnv.getOutlineModeAtt());
						HTMLDecoration.classes.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						if (decos.getConditions().size() > 0) {
							HTMLDecoration.ends.get(0).append(" " + computeStringForDecoration(data_info));
						}
						HTMLDecoration.ends.get(0).append(" att\" "+HTMLEnv.getNewTableBorderStyle()+">");
					} else {
						HTMLDecoration.ends.get(0).append("<table" + htmlEnv.getOutlineModeAtt());
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						if (decos.getConditions().size() > 0) {
							HTMLDecoration.ends.get(0).append(" " + computeStringForDecoration(data_info));
						}
						HTMLDecoration.ends.get(0).append(" att\" "+HTMLEnv.getNewTableBorderStyle()+">");
					}
				} else {
					htmlEnv.code.append(HTMLEnv.getNewTableBorderDIV_start());
					htmlEnv.code.append("<table" + htmlEnv.getOutlineModeAtt() + " ");
					htmlEnv.code.append("class=\"att");
					// tk
					// start/////////////////////////////////////////////////////////
					if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
						// class鐃緒申鐃獣てわ申鐃緒申箸鐃x.TFE10000)�Τ߻���
						htmlEnv.code.append(" " + HTMLEnv.getClassID(this));
					}

					htmlEnv.code.append(" " + Modifier.getClassModifierValue(decos));// added by masato 20140711　属性が一つのときにclassを指定しても機能しなかった問題を解決
					//kotani_idmodifier_ok
					if (decos.getConditions().size() > 0) {
						htmlEnv.code.append(" "
								+ computeStringForDecoration(data_info));
					}
					htmlEnv.code.append("\"");
					htmlEnv.code.append(Modifier.getIdModifierValue(decos));
					htmlEnv.code.append(HTMLEnv.getNewTableBorderStyle()+">");
				}
			}

			if (HTMLEnv.getFormItemFlg()) {

			} else {
				if (htmlEnv.decorationEndFlag.size() > 0) {
					if (htmlEnv.decorationEndFlag.get(0)) {
						// do nothing
					} else {
						HTMLDecoration.ends.get(0).append("<tr><td>\n");
					}
				} else {
					htmlEnv.code.append("<tr><td>\n");
				}
			}


			if (htmlEnv.linkFlag > 0 || htmlEnv.sinvokeFlag) {
				String s = "";
				if (htmlEnv.draggable) {
					s += "<div id=\"" + htmlEnv.dragDivId
							+ "\" class=\"draggable\"";
					Log.out("<div id=\"" + htmlEnv.dragDivId + "\" ");
				} else {
					// tk end for draggable
					// div/////////////////////////////////////////
					if (htmlEnv.isPanel)
						s += "<div id=\"container\">";

					// added by goto 20120614 start
					// [%Ϣ���
					// ������2�Ĥ����꤬���ä����ᡢhref�λ�������Хѥ���������Хѥ������פ��ѹ�
					// 1.���Хѥ�����Firefox�Ǥϥ����
					// ������ʤ�
					// 2.ITC�μ½��Ķ��Ǥϥ����
					// ������ʤ�
					String fileDir = new File(htmlEnv.linkUrl).getAbsoluteFile().getParent();
					if (fileDir.length() < htmlEnv.linkUrl.length()
							&& fileDir.equals(htmlEnv.linkUrl.substring(0,fileDir.length()))) 
					{
						String relative_path = htmlEnv.linkUrl.substring(fileDir.length() + 1);
						s += "<A href=\"" + relative_path
								+ "\" ";
					} else
						s += "<A href=\"" + htmlEnv.linkUrl
								+ "\" ";
					// html_env.code.append("<A href=\"" + html_env.linkurl +
					// "\" ";
					// added by goto 20120614 end

				}
				// tk
				// start//////////////////////////////////////////////////////////
				if (decos.containsKey("target")) {
					s += " target=\"" + decos.getStr("target")
							+ "\"";
				}
//				if (decos.containsKey("class")) {
//					s += " class=\"" + decos.getStr("class")
//							+ "\"";
//				}
				Modifier.getClassAndIdMOdifierValues(decos);

				if (GlobalEnv.isAjax() && htmlEnv.isPanel) {
					s += " onClick =\"return panel('Panel','"
							+ htmlEnv.ajaxQuery + "'," + "'"
							+ htmlEnv.dragDivId + "','" + htmlEnv.ajaxCond
							+ "')\"";
				} else if (GlobalEnv.isAjax() && !htmlEnv.draggable) {
					String target = GlobalEnv.getAjaxTarget();
					if (target == null) {
						String query = htmlEnv.ajaxQuery;
						if (query.indexOf(".sql") > 0) {
							if (query.contains("/")) {
								target = query.substring(
										query.lastIndexOf("/") + 1,
										query.indexOf(".sql"));
							} else {
								target = query.substring(0,
										query.indexOf(".sql"));
							}
						} else if (query.indexOf(".ssql") > 0) {
							if (query.contains("/")) {
								target = query.substring(
										query.lastIndexOf("/") + 1,
										query.indexOf(".ssql"));
							} else {
								target = query.substring(0,
										query.indexOf(".ssql"));
							}
						}

						if (htmlEnv.hasDispDiv) {
							target = htmlEnv.ajaxtarget;
						}
						Log.out("a target:" + target);
					}
					s += " onClick =\"return loadFile('"
							+ htmlEnv.ajaxQuery + "','" + target + "','"
							+ htmlEnv.ajaxCond + "'," + htmlEnv.inEffect + ","
							+ htmlEnv.outEffect + ")\"";

				}
				s += ">\n";

				if (htmlEnv.decorationEndFlag.size() < 1 )
					htmlEnv.code.append(s);
				else
					link_a_tag_str = s;

				Log.out("<A href=\"" + htmlEnv.linkUrl + "\">");
			} 
			// added by masato 20151124 for plink
			if (htmlEnv.plinkFlag) {
				String tmp = "";
				for (int i = 0; i < htmlEnv.valueArray.size(); i++) {
					tmp += " value" + (i + 1) + "='" + htmlEnv.valueArray.get(i) + "'";
				}
				Incremental.outXMLData(htmlEnv.xmlDepth, "<PostLink target='" + htmlEnv.linkUrl + "'" + tmp + ">\n");
			}
			// Log.out("data_info: "+this.getStr(data_info));


			createForm(data_info);

			if (whichForm == 0) { // normal process (not form)
				// ***APPEND DATABASE VALUE***//
				Log.out(data_info);
				// added by masato 20150924 incremental update
				if (Incremental.flag || Ehtml.flag) {
					// modified by masato 20151201 XMLの要素名をTFE******に変更
					// Incremental.outXMLData(htmlEnv.xmlDepth, "<" +
					// Items.get(0) + tfe + ">" + this.getStr(data_info) + "</"
					// + Items.get(0) + ">\n");
					String outType = "div";

					if (htmlEnv.xmlDepth != 0) {
						// 親のoutTypeを継承
						outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth - 1);
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
					String data = this.getStr(data_info)
							.replaceAll("<", "&lt;");
					data = data.replaceAll(">", "&gt;");
					
					if (!Ehtml.infinitescroll_flag) {
						String cid = HTMLEnv.getClassID(this);
						Incremental.outXMLData(
								htmlEnv.xmlDepth,
								"<"+cid+" outType=\'"
										+ htmlEnv.outTypeList.get(htmlEnv.xmlDepth)
										+ "\'>" + data + "</"+cid+">\n");
					} else {
						Incremental.outXMLData(
								htmlEnv.xmlDepth,
								"<Value outType=\'"
										+ htmlEnv.outTypeList.get(htmlEnv.xmlDepth)
										+ "\' class=\'" + HTMLEnv.getClassID(this)
										+ "'>" + data + "</Value>\n");
					}

				} else if (this.getStr(data_info).contains("ggplot")) {
					String width = "700";
					String height = "700";

					for (int i = 0; i < new Preprocessor().getGGplotList().size(); i++) {
						int n = new Preprocessor().getGGplotList().get(i).toString().split(",").length;
						for (int j = 1; j < n; j++) {
							if (new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].contains("width")) {
								width = new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].substring(new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].indexOf("=") + 1);
							}

							if (new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].contains("height")) {
								height = new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].substring(new Preprocessor().getGGplotList().getExtListString(i).split(",")[j].indexOf("=") + 1);
							}
						}
					}
					if((this.getStr(data_info).substring(0, 6).equals("ggplot")) && (this.getStr(data_info).substring(this.getStr(data_info).length() - 5).equals(".html"))) {
						htmlEnv.code.append("<iframe src=\"" + this.getStr(data_info).substring(6) + "\" width=\"" + width + "\" height=\"" + height + "\" seamlesss=\"seamless\"></iframe>");
					} else {
						htmlEnv.code.append(this.getStr(data_info));
					}
				}	else {
					if (htmlEnv.decorationEndFlag.size() > 0) {
						if (htmlEnv.decorationEndFlag.get(0)) {
							String property = htmlEnv.decorationProperty.get(0).get(0);
							ArrayList<String> declaration = new ArrayList<String>();
							declaration = Modifier.replaceModifierValues(property, (this).getStr(data_info));
							property = declaration.get(0);
							String value = declaration.get(1);
							if (property.equals("class")) {//kotani_idmodifier_ok
//								HTMLEnv.cssClass.add((this).getStr(data_info));
								HTMLDecoration.classes.get(0).append(value + " ");
							} else {
								HTMLDecoration.styles.get(0).append(property + ":" + value + ";");
							}
							htmlEnv.decorationProperty.get(0).remove(0);
						} else {
							HTMLDecoration.ends.get(0).append(
									link_a_tag_str +
									(this).getStr(data_info) +
									((link_a_tag_str.length() < 1)? "" : getEndOfA(htmlEnv.draggable, htmlEnv.isPanel)));
							Log.out("HTMLDecoration append data "/*+HTMLDecoration.ends.get(0)*/);
						}
					} else {
						htmlEnv.code.append(this.getStr(data_info));
					}
				}
				Log.out(this.getStr(data_info));
			}

			if (htmlEnv.linkFlag > 0 || htmlEnv.sinvokeFlag) {
				if (htmlEnv.decorationEndFlag.size() < 1 )
					htmlEnv.code.append(getEndOfA(htmlEnv.draggable, htmlEnv.isPanel));
				Log.out("</A>");
			}

			// added by masato 20151124 for plink
			if (htmlEnv.plinkFlag) {
				Incremental.outXMLData(htmlEnv.xmlDepth, "</PostLink>\n");

			}

			/*
			 * if(whichForm > 0){ html_env.code.append("\" />\n");
			 * Log.out("\" \\>\n"); }
			 */

			// Log.out("tuple: " + tuple_count + "/"+GlobalEnv.getTuplesNum() );

			if (HTMLEnv.getFormItemFlg()
					&& HTMLEnv.getFormItemName().equals(formHtml[2])) {

			} else {
				if (htmlEnv.decorationEndFlag.size() > 0) {
					if (htmlEnv.decorationEndFlag.get(0)) {
						// do nothing
					} else if (htmlEnv.decorationStartFlag.get(0)) {
						HTMLDecoration.ends.get(0).append("</td></tr></table>\n");
						htmlEnv.decorationStartFlag.set(0, false);
					} else {
						HTMLDecoration.ends.get(0).append("</td></tr></table>\n");
					}
					HTMLDecoration.ends.get(0).append(HTMLEnv.getNewTableBorderDIV_end("HTMLAttribute"));
				} else {
					htmlEnv.code.append("</td></tr></table>\n");
					htmlEnv.code.append(HTMLEnv.getNewTableBorderDIV_end("HTMLAttribute"));
					Log.out("</td></tr></table>");
				}
			}
			HTMLEnv.end_table("HTMLAttribute");

			Log.out("TFEId = " + HTMLEnv.getClassID(this));
			// html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
		}


		return null;
	}
	private String getEndOfA(boolean draggable, boolean isPanel) {
		String s = "";
		if (htmlEnv.draggable)
			s += "</div>\n";
		else {
			s += "</A>\n";

			if (htmlEnv.isPanel)
				s += "</div>\n";
		}
		return s;
	}

	// optimizer
	public void work_opt(ExtList data_info) {
		StringBuffer string_tmp = new StringBuffer();
		string_tmp.append("<VALUE");
		if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
			// class���äƤ���Ȥ�
			// ex.TFE10000)�Τ߻���
			string_tmp.append(" class=\"");
			string_tmp.append(HTMLEnv.getClassID(this));
		}

		if (decos.containsKey("class")) {//kotani_idmodifier_ok
			// class����(ex.class=menu)������Ȥ�
			if (!htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
				string_tmp.append(" class=\"");
			} else {
				string_tmp.append(" ");
			}
			string_tmp.append(Modifier.getClassModifierValue(decos) + "\"");//kotani_idmodifier_ok

		} else if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
			string_tmp.append("\"");
		}

		string_tmp.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok

		if (decos.containsKey("update") || decos.containsKey("insert")
				|| decos.containsKey("delete") || decos.containsKey("login")
				|| decos.containsKey("logout") || HTMLEnv.getFormItemFlg()
				|| (HTMLEnv.getIDU() != null && !HTMLEnv.getIDU().isEmpty())) {
			string_tmp.append(" type=\"form\"");
		}

		if (decos.containsKey("tabletype")) {
			string_tmp.append(" tabletype=\"" + decos.getStr("tabletype")
					+ "\"");
		}

		// link and sinvoke
		if (htmlEnv.linkFlag > 0 || htmlEnv.sinvokeFlag) {
			string_tmp.append(" href=\"" + htmlEnv.linkUrl + "\" ");
			if (decos.containsKey("target")) {
				string_tmp.append(" target=\"" + decos.getStr("target") + "\"");
			}
			//if (decos.containsKey("class")) {
				//string_tmp.append("class=\"" + decos.getStr("class") + "\"");
				string_tmp.append(Modifier.getClassAndIdMOdifierValues(decos));//kotani_idmodifier_ok
			//}
		}

		string_tmp.append(">");

		if (HTMLEnv.getFormItemFlg()
				&& HTMLEnv.getFormItemName().equals(formHtml[2])
				&& HTMLEnv.getSelectRepeat()) {

		} else {
			htmlEnv2.code.append(string_tmp);
			Log.out(string_tmp);
		}

		createForm(data_info);

		if (whichForm == 0) {
			// ***APPEND DATABASE VALUE***//
			String s = this.getStr(data_info);
			if (s.contains("&"))
				s = s.replace("&", "&amp;");
			if (s.contains("<"))
				s = s.replaceAll("<", "&lt;");
			if (s.contains(">"))
				s = s.replaceAll(">", "&gt;");
			if (s.contains("���"))
				s = s.replaceAll("���", "&#65374;");
			if (s.isEmpty())
				s = "��";
			htmlEnv2.code.append(s);
			Log.out(this.getStr(data_info));
		}

		/*
		 * if(decos.containsKey("update") || decos.containsKey("insert")||
		 * decos.containsKey("delete") || decos.containsKey("login")){
		 * html_env2.code.append("\" />"); Log.out("\" \\>\n"); }
		 */

		// Log.out("tuple: " + tuple_count + "/"+GlobalEnv.getTuplesNum() );

		if (HTMLEnv.getFormItemFlg()
				&& HTMLEnv.getFormItemName().equals(formHtml[2])) {
			// select
		} else {
			htmlEnv2.code.append("</VALUE>");
			Log.out("</VALUE>");
			if (HTMLEnv.getFormItemFlg()
					&& HTMLEnv.getFormItemName().equals(formHtml[5])) {
				HTMLEnv.incrementFormPartsNumber();
			}
		}

	}

}
