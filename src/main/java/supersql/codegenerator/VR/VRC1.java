package supersql.codegenerator.VR;

import java.io.Serializable;

import org.apache.bcel.verifier.exc.StaticCodeConstraintException;
import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRC1 extends Connector implements Serializable {

	private VREnv vrEnv;
	private VREnv vrEnv2;
	public static int j = 0;

	public VRC1(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRC1";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- C1 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfes.size=" + tfes.size());
		Log.out("countconnectitem=" + countconnectitem());
		this.setDataList(data_info);

		// TODO: check if the code between A and B is relevant to VR.
		// A
		if (decos.containsKey("insert")) {
			VREnv.setIDU("insert");
		}
		if (decos.containsKey("update")) {
			VREnv.setIDU("update");
		}
		if (decos.containsKey("delete")) {
			VREnv.setIDU("delete");
		}

		String classname;
		if (this.decos.containsKey("class")) {
			classname = this.decos.getStr("class");
		} else {
			classname = VREnv.getClassID(this);
		}

		if (!GlobalEnv.isOpt()) {
			if (vrEnv.decorationStartFlag.size() > 0) {
				if (vrEnv.decorationStartFlag.get(0)) {
					VRDecoration.ends.get(0).append(classname);
					vrEnv.decorationStartFlag.set(0, false);
				} else {
					VRDecoration.ends.get(0).append(classname);
				}
			} else {
				if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
					vrEnv.code.append(VREnv.getClassID(this));
				}

				if (decos.containsKey("class")) {
					// System.out.println("existing classdecolater:::::::::");
					if (!vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
					} else {
						vrEnv.code.append(" ");
					}
				} else if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
				}
			}
		}

		// xml
		if (GlobalEnv.isOpt()) {
			vrEnv2.code.append("<tfe type=\"connect\" dimension =\"1\"");
			if (decos.containsKey("tablealign"))
				vrEnv2.code.append(" align=\"" + decos.getStr("tablealign") + "\"");
			if (decos.containsKey("tablevalign"))
				vrEnv2.code.append(" valign=\"" + decos.getStr("tablevalign") + "\"");
			if (decos.containsKey("tabletype")) {
				vrEnv2.code.append(" tabletype=\"" + decos.getStr("tabletype") + "\"");
				if (decos.containsKey("cellspacing")) {
					vrEnv2.code.append(" cellspacing=\"" + decos.getStr("cellspacing") + "\"");
				}
				if (decos.containsKey("cellpadding")) {
					vrEnv2.code.append(" cellpadding=\"" + decos.getStr("cellpadding") + "\"");
				}
				if (decos.containsKey("border")) {
					vrEnv2.code.append(" border=\"" + decos.getStr("border").replace("\"", "") + "\"");
				}
				if (decos.containsKey("tableborder")) {
					vrEnv2.code.append(" tableborder=\"" + decos.getStr("tableborder").replace("\"", "") + "\"");
				}
			} else {
				if (decos.containsKey("border")) {
					vrEnv2.code.append(" border=\"" + decos.getStr("border").replace("\"", "") + "\"");
				} else {
					vrEnv2.code.append(" border=\"" + vrEnv.tableBorder.replace("\"", "") + "\"");
				}
				if (decos.containsKey("tableborder")) {
					vrEnv2.code.append(" tableborder=\"" + decos.getStr("tableborder").replace("\"", "") + "\"");
				}
			}
			if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
				vrEnv2.code.append(" class=\"");
				vrEnv2.code.append(VREnv.getClassID(this));
			}
			if (decos.containsKey("class")) {
				if (!vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
					vrEnv2.code.append(" class=\"");
				} else {
					vrEnv2.code.append(" ");
				}
				vrEnv2.code.append(decos.getStr("class") + "\" ");
			} else if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
				vrEnv2.code.append("\" ");
			}

			if (decos.containsKey("form")) {
				vrEnv2.code.append(" form=\"" + VREnv.getFormNumber() + "\" ");
			}
			vrEnv2.code.append(">");
		}
		// B

		int i = 0;

		if (CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")) {
			Element connector = vrEnv.xml.createElement("Connector" + j);
			connector.setAttribute("type", "C1");
			vrEnv.currentNode = vrEnv.currentNode.appendChild(connector);
			j++;
		}

		while (this.hasMoreItems()) {
			vrEnv.cNum++;
			vrEnv.xmlDepth++;
			ITFE tfe = tfes.get(i);

			if (CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")) {// tatsu
				String classid = VREnv.getClassID(tfe);
				this.worknextItem();

				// TODO: check what this if does
				if (vrEnv.notWrittenClassId.contains(classid)) {
					if (vrEnv.code.indexOf(classid) >= 0) {
						vrEnv.code.delete(vrEnv.code.indexOf(classid),
								vrEnv.code.indexOf(classid) + classid.length() + 1);
					}
				}
				vrEnv.cNum--;
				vrEnv.xmlDepth--;
			} else {
				if (VRAttribute.genre.equals("")) {// kotani 16/10/04
					if (vrEnv.gLevel == 0) {
						VRAttribute.groupcount++;
					} else if (vrEnv.gLevel < 2) { // vrEnv.gLevel == 1
						vrEnv.currentNode = vrEnv.currentNode.appendChild(vrEnv.xml.createElement("category"));
					}
				} else {
					Element category = vrEnv.xml.createElement("category");
					category.setAttribute("name", VRAttribute.genre);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(category);

					VRAttribute.genrearray2.add("\"" + VRAttribute.genre + "\"");

					if (VRAttribute.genrecount == 0) {
						VRAttribute.genrearray22.add(0);
					}
					VRAttribute.genrecount++;
				}

				String classid = VREnv.getClassID(tfe);
				this.worknextItem();

				// TODO: check what this if does
				if (vrEnv.notWrittenClassId.contains(classid)) {
					if (vrEnv.code.indexOf(classid) >= 0) {
						vrEnv.code.delete(vrEnv.code.indexOf(classid),
								vrEnv.code.indexOf(classid) + classid.length() + 1);
					}
				}

				i++;
				vrEnv.cNum--;
				vrEnv.xmlDepth--;
			}

		}

		if (CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")) {
			vrEnv.currentNode = vrEnv.currentNode.getParentNode();
			j--;
		} else {
			// TODO: check what this if does
			if (VRAttribute.gjudge == 0) {
				if (VRAttribute.billnum >= 2) {
					VRAttribute.billnum = 0;
				}
			}

			vrEnv2.code.append("</tfe>");

			if (vrEnv.gLevel == 1) {
				vrEnv.currentNode = vrEnv.currentNode.getParentNode().getParentNode();
			}
		}

		Log.out("+++++++ C1 +++++++");
		return null;
	}
}