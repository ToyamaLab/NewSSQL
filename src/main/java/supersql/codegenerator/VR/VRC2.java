package supersql.codegenerator.VR;

import java.io.Serializable;

import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRC2 extends Connector implements Serializable {

	private VREnv vrEnv;
	private VREnv vrEnv2;
	

	public VRC2(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRC2";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- C2 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfessize=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());

		this.setDataList(data_info);

		//TODO: check if the code between A and B is relevant to VR.
		//A
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
					VRDecoration.fronts.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					VRDecoration.fronts.get(0).append(vrEnv.tableBorder + "\"");
					VRDecoration.fronts.get(0).append(vrEnv.getOutlineMode());
					VRDecoration.classes.get(0).append(" class=\"");
					VRDecoration.ends.get(0).append(classname);
					VRDecoration.ends.get(0).append("\">");
					vrEnv.decorationStartFlag.set(0, false);
				} else {
					VRDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					VRDecoration.ends.get(0).append(vrEnv.tableBorder + "\"");
					VRDecoration.ends.get(0).append(vrEnv.getOutlineMode());
					VRDecoration.ends.get(0).append(" class=\"");
					VRDecoration.ends.get(0).append(classname);
					VRDecoration.ends.get(0).append("\">");
				}
			} else {
				vrEnv.code
				.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
				vrEnv.code.append(vrEnv.tableBorder + "\" ");
				vrEnv.code.append(vrEnv.getOutlineMode());
				if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
					vrEnv.code.append(" class=\"");
					vrEnv.code.append(VREnv.getClassID(this));
				}

				if (decos.containsKey("class")) {
					if (!vrEnv.writtenClassId.contains(VREnv
							.getClassID(this))) {
						vrEnv.code.append(" class=\"");
					} else {
						vrEnv.code.append(" ");
					}
					vrEnv.code.append(decos.getStr("class") + "\" ");
				} else if (vrEnv.writtenClassId.contains(VREnv
						.getClassID(this))) {
					vrEnv.code.append("\" ");
				}
				vrEnv.code.append(">");
			}
		}
		if (GlobalEnv.isOpt()) {
			vrEnv2.code.append("<tfe type=\"connect\" dimension=\"2\"");
			if (decos.containsKey("tablealign"))
				vrEnv2.code.append(" align=\""
						+ decos.getStr("tablealign") + "\"");
			if (decos.containsKey("tablevalign"))
				vrEnv2.code.append(" valign=\""
						+ decos.getStr("tablevalign") + "\"");
			if (decos.containsKey("height"))
				vrEnv2.code.append(" height=\"" + decos.getStr("height")
				+ "\"");
			if (decos.containsKey("tabletype")) {
				vrEnv2.code.append(" tabletype=\""
						+ decos.getStr("tabletype") + "\"");
				if (decos.containsKey("cellspacing")) {
					vrEnv2.code.append(" cellspacing=\""
							+ decos.getStr("cellspacing") + "\"");
				}
				if (decos.containsKey("cellpadding")) {
					vrEnv2.code.append(" cellpadding=\""
							+ decos.getStr("cellpadding") + "\"");
				}
				if (decos.containsKey("border")) {
					vrEnv2.code.append(" border=\""
							+ decos.getStr("border").replace("\"", "")
							+ "\"");
				}

				if (decos.containsKey("tableborder")) {
					vrEnv2.code.append(" tableborder=\""
							+ decos.getStr("tableborder").replace("\"", "")
							+ "\"");
				}
			} else {
				if (decos.containsKey("border")) {
					vrEnv2.code.append(" border=\""
							+ decos.getStr("border").replace("\"", "")
							+ "\"");
				} else {
					vrEnv2.code.append(" border=\""
							+ vrEnv.tableBorder.replace("\"", "") + "\"");
				}
				if (decos.containsKey("tableborder")) {
					vrEnv2.code.append(" tableborder=\""
							+ decos.getStr("tableborder").replace("\"", "")
							+ "\"");
				}
			}
			if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
				vrEnv2.code.append(" class=\"");
				vrEnv2.code.append(VREnv.getClassID(this));
			}

			if (decos.containsKey("class")) {
				if (!vrEnv.writtenClassId.contains(VREnv
						.getClassID(this))) {
					vrEnv2.code.append(" class=\"");
				} else {
					vrEnv2.code.append(" ");
				}
				vrEnv2.code.append(decos.getStr("class"));
			} else if (vrEnv.writtenClassId.contains(VREnv
					.getClassID(this))) {
				vrEnv2.code.append("\" ");
			}

			if (decos.containsKey("form")) {
				vrEnv2.code.append(" form=\"" + VREnv.getFormNumber()
				+ "\" ");
			}

			vrEnv2.code.append(">");
		}
		//B

		int i = 0;
		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			Element connector = vrEnv.xml.createElement("Connector"+VRC1.j);
			connector.setAttribute("type","C2");
			vrEnv.currentNode = vrEnv.currentNode.appendChild(connector);
			VRC1.j++;
		}
		while (this.hasMoreItems()) {
			vrEnv.cNum++;
			vrEnv.xmlDepth++;
			ITFE tfe = tfes.get(i);

			if(VRAttribute.genre.equals("")){// kotani 16/10/04
				if(vrEnv.gLevel == 0){
					VRAttribute.groupcount++;
				}
			}

			this.worknextItem();
			i++;
			vrEnv.cNum--;
			vrEnv.xmlDepth--;
		}
		
		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			vrEnv.currentNode = vrEnv.currentNode.getParentNode();
			VRC1.j--;
		}

		//TODO: check what this if does
		if(VRAttribute.gjudge == 0){
			if(VRAttribute.billnum >= 2){
				VRAttribute.billnum = 0;
			}
		}

		vrEnv2.code.append("</tfe>");

		Log.out("TFEId = " + VREnv.getClassID(this));
		Log.out("+++++++ C2 +++++++");
		return null;
	}
}