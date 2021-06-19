package supersql.codegenerator.VR;

import org.w3c.dom.Element;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRG1 extends Grouper {

	private VREnv vrEnv;
	private VREnv vr_env2;
	boolean retFlag = false;	// 20140602_masato pagenationフラグ
	boolean pageFlag = false;	// 20140602_masato pagenationフラグ
	boolean iteratorFlag = false;
	public static int level = 0;

	public VRG1(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vr_env2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "VRG1";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- G1 -------");
		if(!CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			if(vrEnv.gLevel == 0){
				vrEnv.currentNode = vrEnv.currentNode.appendChild(vrEnv.xml.createElement("group"));
			}
		}
		
		this.setDataList(data_info);
		int i = 0;			
		int j = -1;			
		int k = -1;	

		String margin="10.0";
		
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
				VRAttribute.compflag[VRAttribute.cgcount] = 1;//compflag G1,G2,G3の判別　compx,y,z,は%だったらcompzを-1!だったら、compyを,　,だったらcompxを
			}
			VRAttribute.componexflag = true;//componexflag=trueで2回目以降のループを無視


		}
		if (decos.containsKey("vr_y")) {///column->row_x, row->vr_y

			j = Integer.parseInt(decos.getStr("vr_y"));
			//			System.out.println("vr_y="+j);
			retFlag = true;
			if(!VRAttribute.componeyflag){
				VRAttribute.compy[VRAttribute.cgcount] = j;
				VRAttribute.compflag[VRAttribute.cgcount] = 1;
			}
			VRAttribute.componeyflag = true;
		}
		if (decos.containsKey("vr_z")) {

			k = Integer.parseInt(decos.getStr("vr_z"));
			//			System.out.println("vr_z="+k);

			retFlag = true;
			if(!VRAttribute.componezflag){
				VRAttribute.compz[VRAttribute.cgcount] = k;
				VRAttribute.compflag[VRAttribute.cgcount] = 1;
			}
			VRAttribute.componezflag = true;
		}

		if (decos.containsKey("margin")) {
			margin = decos.getStr("margin");
		}
		
		Log.out("decolator is :"+ decos);

		if(vrEnv.gLevel == 0){
			VRAttribute.floorarray.add(1);
		} else if(vrEnv.gLevel == 1){
			VRAttribute.exharray.add(1);
		}

		VRAttribute.gjudge++;

		if(CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			if(i==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+level);
				grouper.setAttribute("type","G1");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				level++;
				while(this.hasMoreItems()){
					this.worknextItem();
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				if (vrEnv.currentNode.getNodeName().equals("foreach")){
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				}
				level--;
			} else if (k==-1 && j==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G2");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G1");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					level++;
					for(int s = 0; s < i && this.hasMoreItems(); s++){
						this.worknextItem();
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				level--;
			} else if (j==-1 && k==0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G3");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G1");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					level++;
					for(int s = 0; s < i && this.hasMoreItems(); s++){
						this.worknextItem();
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				level--;
			} else if (k==0 && j > 0){
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G3");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G2");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					level++;
					for(int s = 0; s < j && this.hasMoreItems(); s++){
						Element grouper3 = vrEnv.xml.createElement("Grouper"+VRG1.level);
						grouper3.setAttribute("type","G1");
						grouper3.setAttribute("margin", margin);
						vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper3);
						level++;
						for (int t=0; t < i && this.hasMoreItems(); t++){
							this.worknextItem();
						}
						vrEnv.currentNode = vrEnv.currentNode.getParentNode();
						level--;
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				level--;
			} else if (j==0 && k >0) {
				Element grouper = vrEnv.xml.createElement("Grouper"+VRG1.level);
				grouper.setAttribute("type","G2");
				grouper.setAttribute("margin", margin);
				vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper);
				level++;
				while(this.hasMoreItems()){
					Element grouper2 = vrEnv.xml.createElement("Grouper"+VRG1.level);
					grouper2.setAttribute("type","G3");
					grouper2.setAttribute("margin", margin);
					vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper2);
					level++;
					for(int s = 0; s < k && this.hasMoreItems(); s++){
						Element grouper3 = vrEnv.xml.createElement("Grouper"+VRG1.level);
						grouper3.setAttribute("type","G1");
						grouper3.setAttribute("margin", margin);
						vrEnv.currentNode = vrEnv.currentNode.appendChild(grouper3);
						level++;
						for (int t=0; t < i && this.hasMoreItems(); t++){
							this.worknextItem();
						}
						vrEnv.currentNode = vrEnv.currentNode.getParentNode();
						level--;
					}
					vrEnv.currentNode = vrEnv.currentNode.getParentNode();
					level--;
				}
				vrEnv.currentNode = vrEnv.currentNode.getParentNode();
				level--;
			}
		}

		if(!CodeGenerator.getMedia().equalsIgnoreCase("unity_dv")){
			while (this.hasMoreItems()) {
				VRAttribute.genre = "";

				try {
					int l=VRManager.gindex.get(vrEnv.gLevel);
					VRManager.gindex.set(vrEnv.gLevel,l+1);//gindex[]++
				} catch (Exception e) {
					VRManager.gindex.add(1);	//gindex[]=1
				}

				vrEnv.gLevel++;
				VRAttribute.elearraySeq = 0;///n2 kotani
				this.worknextItem();
				vrEnv.gLevel--;
			}
			VRManager.gindex.set(vrEnv.gLevel, 0);






			if(vrEnv.gLevel == 0){
				VRManager.nest1count++;
			}

			for(int l=0; l<VRAttribute.elearrayXML.size();l++){
				vrEnv.currentNode.appendChild(VRAttribute.elearrayXML.get(l));
			}

			VRAttribute.elearrayXML.clear();//初期化
			VRAttribute.elearraySeq = 0;//初期化

			if(VRAttribute.gjudge==1){
				VRAttribute.billnum++;
			}
			VRAttribute.gjudge--;

			if (VREnv.getFormItemFlg()) {
				VREnv.incrementFormPartsNumber();
			}

			if(vrEnv.gLevel == 0){
				VRAttribute.componexflag = false;
				VRAttribute.componeyflag = false;
				VRAttribute.componezflag = false;
				VRAttribute.cgcount++;

				vrEnv.currentNode = vrEnv.currentNode.getParentNode();

				VRAttribute.grouptag++;
				VRAttribute.genrearray22.add(VRAttribute.genrecount);
			}

			Log.out("TFEId = " + VREnv.getClassID(this));
		}
		return null;
	}
}
