package supersql.codegenerator.HTML;

import supersql.codegenerator.Connector;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;


//tbt add 180806
//for join string
public class HTMLCONCAT extends Connector {
	
	public static String joinClassID = "";
	public static DecorateList joinDecos = null;

    private HTMLEnv htmlEnv;
    private HTMLEnv htmlEnv2;

    public HTMLCONCAT(Manager manager, HTMLEnv htmlEnv, HTMLEnv htmlEnv2) {
        this.htmlEnv = htmlEnv;
        this.htmlEnv2 = htmlEnv2;
    }

    @Override
    public String getSymbol() {
        return "HTMLCONCAT";
    }

    @Override
    public String work(ExtList data_info) {
        Log.out("------- CONCAT -------");
        Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
        Log.out("tfes.size=" + tfes.size());
        Log.out("countconnetitem=" + countconnectitem());
        this.setDataList(data_info);
        String buffer = new String();
        GlobalEnv.joinFlag = true;
        while (this.hasMoreItems()) {
            htmlEnv.cNum++;
            htmlEnv.xmlDepth++;
            buffer += this.worknextItem();
            Log.out("String is "+buffer);
            htmlEnv.cNum--;
            htmlEnv.xmlDepth--;
        }
        Log.out("result is "+buffer);
        Log.out("TFEId = " + HTMLEnv.getClassID(this));
        GlobalEnv.joinFlag = false;
//        htmlEnv.code.append("<td class=\""+HTMLEnv.getClassID(this)+"\">");
        if (!Incremental.flag && !Ehtml.flag) {
        	// TODO_old このときのCSSが効いていない
        	htmlEnv.code.append(buffer);
        	String classID = "TFE"+ (this.getId() + 1);	//OK?
        	htmlEnv.append_css_def_td(classID, joinDecos);
        } else {
//        	Incremental.outXMLData(1, buffer+"\n");
//        	Incremental.outXMLData(htmlEnv.xmlDepth, "<"+Ehtml.tfe_id+" outType='"+htmlEnv.outTypeList.get(htmlEnv.xmlDepth)+"'>"+buffer+"</"+Ehtml.tfe_id+">\n");
        	String outType = "div";	//TODO
//        	String outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth);	//TODO
        	if(!HTMLFunction.HTMLFunctionFlag){
        		// TODO_old このときのCSSが効いていない
//        		Incremental.outXMLData(htmlEnv.xmlDepth, "<"+Ehtml.tfe_id+" outType='"+outType+"'>"+buffer+"</"+Ehtml.tfe_id+">\n");
        		Incremental.outXMLData(htmlEnv.xmlDepth, "<"+joinClassID+" outType='"+outType+"'>"+buffer+"</"+joinClassID+">\n");
			}else {
				return buffer;
			}
        	
		}
//        htmlEnv.code.append("</td>");
        Log.out("+++++++ JOIN +++++++");
//        return null;
        return buffer;
    }
}
