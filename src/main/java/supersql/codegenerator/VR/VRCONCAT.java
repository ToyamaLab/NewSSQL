package supersql.codegenerator.VR;

import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

//li add 20210601
//for join string
public class VRCONCAT extends Connector {
	
	public static String joinClassID = "";
	public static DecorateList joinDecos = null;

	private VREnv vrEnv;
	private VREnv vrEnv2;

    public VRCONCAT(Manager manager, VREnv vrEnv, VREnv vrEnv2) {
        this.vrEnv = vrEnv;
        this.vrEnv2 = vrEnv2;
    }

    @Override
    public String getSymbol() {
        return "VRCONCAT";
    }

    @Override
    public String work(ExtList data_info) {
        Log.out("------- CONCAT -------");
        Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
        Log.out("tfes.size=" + tfes.size());
        Log.out("countconnetitem=" + countconnectitem());
        this.setDataList(data_info);
        String buffer = new String();
        int i = 0;
        GlobalEnv.joinFlag = true;
        while (this.hasMoreItems()) {
            vrEnv.cNum++;
            vrEnv.xmlDepth++;
            i++;
            buffer += this.worknextItem();
            Log.out("String is "+buffer);
            vrEnv.cNum--;
            vrEnv.xmlDepth--;
        }
        Log.out("result is "+buffer);
        Log.out("TFEId = " + VREnv.getClassID(this));
        GlobalEnv.joinFlag = false;
//        htmlEnv.code.append("<td class=\""+HTMLEnv.getClassID(this)+"\">");
//<<<<<<< HEAD
        
        if (!Incremental.flag && !Ehtml.flag) {
        	// TODO_old このときのCSSが効いていない
        	vrEnv.code.append(buffer);
        	String classID = "TFE"+ (this.getId() + 1);	//OK?
        	vrEnv.append_css_def_td(classID, joinDecos);

        } else {
//        	Incremental.outXMLData(1, buffer+"\n");
//        	Incremental.outXMLData(htmlEnv.xmlDepth, "<"+Ehtml.tfe_id+" outType='"+htmlEnv.outTypeList.get(htmlEnv.xmlDepth)+"'>"+buffer+"</"+Ehtml.tfe_id+">\n");
        	String outType = "div";	//TODO
//        	String outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth);	//TODO
        	if(!VRFunction.VRFunctionFlag){
        		// TODO_old このときのCSSが効いていない
//        		Incremental.outXMLData(htmlEnv.xmlDepth, "<"+Ehtml.tfe_id+" outType='"+outType+"'>"+buffer+"</"+Ehtml.tfe_id+">\n");
        		Incremental.outXMLData(vrEnv.xmlDepth, "<"+joinClassID+" outType='"+outType+"'>"+buffer+"</"+joinClassID+">\n");
			}else {
				return buffer;
			}
        	
		}
//        htmlEnv.code.append("</td>");
        Log.out("+++++++ JOIN +++++++");
//        return null;
        
        return buffer;
//=======
//        htmlEnv.code.append(buffer);
////        htmlEnv.code.append("</td>");
//        Log.out("+++++++ JOIN +++++++");
//        return null;
//>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
}
