package supersql.codegenerator.VR;

import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRG3 extends Grouper {

	private VREnv vrEnv;
	private VREnv vr_env2;
	boolean retFlag = false;	// 20140611_masato pagenationフラグ

	public VRG3(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vr_env2 = henv2;
	}


	@Override
	public String getSymbol() {
		return "VRG3";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- G3 -------");
		if(!CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			if(vrEnv.gLevel == 0){
				vrEnv.currentNode = vrEnv.currentNode.appendChild(vrEnv.xml.createElement("group"));
			}
		}

		int i = -1;			// 20140526_masato
		int j = -1;			// 20140526_masato
		int k = 0;	
		
		String margin = "10.0";
		
		if (vrEnv.decorationStartFlag.size() > 0 
				&& ((vrEnv.decorationStartFlag.get(0) || decos.size()>0) 
						&& !vrEnv.decorationEndFlag.get(0))) {
			for (String key : decos.keySet()) {
				String value = decos.get(key).toString();
				//if the decoration value is an attribute, register its name to decorationProperty to process it later
				if (!(value.startsWith("\"") && value.endsWith("\"")) 
						&& !(value.startsWith("\'") && value.endsWith("\'")) 
						&& !supersql.codegenerator.CodeGenerator.isNumber(value)
						) {
					vrEnv.decorationProperty.get(0).add(0, key);
				}
			}
		}

		//		System.out.println(decos);
		if (decos.containsKey("vr_x")) {
			i = Integer.parseInt(decos.getStr("vr_x"));
			retFlag = true;
			if(!VRAttribute.componexflag){
				VRAttribute.compx[VRAttribute.cgcount] = i;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componexflag = true;
		}
		if (decos.containsKey("vr_y")) {///column->row_x, row->vr_y
			j = Integer.parseInt(decos.getStr("vr_y"));
			retFlag = true;
			if(!VRAttribute.componeyflag){
				VRAttribute.compy[VRAttribute.cgcount] = j;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componeyflag = true;
		}		
		if (decos.containsKey("vr_z")) {
			k = Integer.parseInt(decos.getStr("vr_z"));
			retFlag = true;
			if(!VRAttribute.componezflag){
				VRAttribute.compz[VRAttribute.cgcount] = k;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componezflag = true;
		}
		
		if (decos.containsKey("margin")) {
			margin = decos.getStr("margin");
		}
		
		this.setDataList(data_info);

		if(vrEnv.gLevel == 0) {
			VRAttribute.floorarray.add(3);
		} else if(vrEnv.gLevel == 1) {
			VRAttribute.exharray.add(3);
		}

		VRAttribute.gjudge++;

		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			if(k==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G3");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				VRG1.level++;
				while(this.hasMoreItems()){
					this.worknextItem();
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				if (vrEnv.currentNode.getNodeName().equals("foreach")){
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				}
				VRG1.level--;
			} else if (j==-1 && i==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G1");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				VRG1.level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G3");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					VRG1.level++;
					for(int s = 0; s < k && this.hasMoreItems(); s++){
						this.worknextItem();
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					VRG1.level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				VRG1.level--;
			} else if (i==-1 && j==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G2");
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				VRG1.level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G3");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					VRG1.level++;
					for(int s = 0; s < k && this.hasMoreItems(); s++){
						this.worknextItem();
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					VRG1.level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				VRG1.level--;
			} else if (j == 0 && i > 0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G2");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				VRG1.level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G1");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					VRG1.level++;
					for(int s = 0; s < i && this.hasMoreItems(); s++){
						Element grouper3 = vrEnv.xml.createElement("Grouper"+VRG1.level);
						grouper3.setAttribute("type","G3");
						grouper3.setAttribute("margin", margin);
						vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper3);
						VRG1.level++;
						for (int t=0; t < k && this.hasMoreItems(); t++){
							this.worknextItem();
						}
						vrEnv.currentNode = vrEnv.currentNode.getParentNode();
						VRG1.level--;
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					VRG1.level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				VRG1.level--;
			} else if (i==0 && j > 0) {
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G1");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				VRG1.level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G2");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					VRG1.level++;
					for(int s = 0; s < j && this.hasMoreItems(); s++){
						Element grouper3 = vrEnv.xml.createElement("Grouper"+VRG1.level);
						grouper3.setAttribute("type","G3");
						grouper3.setAttribute("margin", margin);
						vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper3);
						VRG1.level++;
						for (int t=0; t < k && this.hasMoreItems(); t++){
							this.worknextItem();
						}
						vrEnv.currentNode = vrEnv.currentNode.getParentNode();
						VRG1.level--;
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					VRG1.level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				VRG1.level--;
			}
		}

		if(!CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){	
			while (this.hasMoreItems()) {
				//////////////////////////G22//////////////////////////
				VRAttribute.genre = "";

				VRAttribute.elearraySeq = 0;//n2 kotani

				try {
					int l=VRManager.gindex.get(vrEnv.gLevel);
					VRManager.gindex.set(vrEnv.gLevel,l+1);//gindex[]++
				} catch (Exception e) {
					VRManager.gindex.add(1);	//gindex[]=1
				}

				vrEnv.gLevel++;
				Log.out("selectFlg" + VREnv.getSelectFlg());
				Log.out("selectRepeatFlg" + VREnv.getSelectRepeat());
				Log.out("formItemFlg" + VREnv.getFormItemFlg());

				String classid = VREnv.getClassID(tfe);

				//TODO: check this has nothing to do with vr
				if (GlobalEnv.isOpt() && !VREnv.getSelectRepeat()) {
					vr_env2.code.append("<tfe type=\"repeat\" dimension=\"2\"");
					vr_env2.code.append(" border=\"" + vrEnv.tableBorder
							+ "\"");

					if (decos.containsKey("tablealign"))
						vr_env2.code.append(" align=\""
								+ decos.getStr("tablealign") + "\"");
					if (decos.containsKey("tablevalign"))
						vr_env2.code.append(" valign=\""
								+ decos.getStr("tablevalign") + "\"");

					if (decos.containsKey("class")) {
						// class=menu�Ȃǂ̎w�肪��������t��
						vr_env2.code.append(" class=\"");
						vr_env2.code.append(decos.getStr("class") + " ");
					}
					if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
						// TFE10000�Ȃǂ̎w�肪��������t��
						if (decos.containsKey("class")) {
							vr_env2.code.append(VREnv.getClassID(this) + "\"");
						} else {
							vr_env2.code.append(" class=\""
									+ VREnv.getClassID(this) + "\"");
						}
					} else if (decos.containsKey("class")) {
						vr_env2.code.append("\"");
					}

					if (decos.containsKey("tabletype")) {
						vr_env2.code.append(" tabletype=\""
								+ decos.getStr("tabletype") + "\"");
						if (decos.containsKey("cellspacing")) {
							vr_env2.code.append(" cellspacing=\""
									+ decos.getStr("cellspacing") + "\"");
						}
						if (decos.containsKey("cellpadding")) {
							vr_env2.code.append(" cellpadding=\""
									+ decos.getStr("cellpadding") + "\"");
						}
					}
					vr_env2.code.append(">");
				}

				this.worknextItem();

				//TODO: check what this does
				if (vrEnv.notWrittenClassId.contains(classid)
						&& vrEnv.code.indexOf(classid) >= 0) {
					vrEnv.code.delete(vrEnv.code.indexOf(classid),
							vrEnv.code.indexOf(classid) + classid.length() + 1);
				}

				vrEnv.gLevel--;
			

			

				VRManager.gindex.set(vrEnv.gLevel, 0);
				if(vrEnv.gLevel == 0){
					VRManager.nest1count++;
				}

				for(int l=0; l<VRAttribute.elearrayXML.size();l++){///n2 kotani		
					vrEnv.currentNode.appendChild(VRAttribute.elearrayXML.get(l));
				}
				VRAttribute.elearrayXML.clear();//初期化
				VRAttribute.elearraySeq = 0;//初期化

				if(VRAttribute.gjudge==1){
					VRAttribute.billnum++;
				}
				VRAttribute.gjudge--;

				/////////////////////////G22end//////////////////////

				if(vrEnv.gLevel == 0){
					VRAttribute.componexflag = false;
					VRAttribute.componeyflag = false;
					VRAttribute.componezflag = false;
					VRAttribute.cgcount++;

					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					VRAttribute.grouptag++;
					VRAttribute.genrearray22.add(VRAttribute.genrecount);
				}
			}
		}
		return null;

	}

}


