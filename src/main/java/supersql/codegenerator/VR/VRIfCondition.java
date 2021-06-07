package supersql.codegenerator.VR;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Function;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.common.Log;
import supersql.extendclass.ExtList;

//TODO: check if needed/if works
public class VRIfCondition extends IfCondition {

	public VRIfCondition(Manager manager, VREnv html_env, VREnv html_env2, Attribute condition, TFE thenTfe, TFE elseTfe) {
		super(condition, thenTfe, elseTfe);
	}

	@Override
	public Element createNode(ExtList data_info){
		String conditionResult = (String) data_info.get(0);
		if((((conditionResult)).toString()).equals("t")){
			if(thenTfe instanceof Connector || thenTfe instanceof Attribute
				|| thenTfe instanceof Function || thenTfe instanceof IfCondition)
				return (Element) thenTfe.createNode(data_info.ExtsubList(1, thenTfe.countconnectitem()+1));
			else
				return (Element) thenTfe.createNode((ExtList)data_info.get(1));
		}
		else if(elseTfe != null){
			int from = thenTfe.countconnectitem()+1;
			if(elseTfe instanceof Connector || elseTfe instanceof Attribute
					|| elseTfe instanceof Function || elseTfe instanceof IfCondition)
				return (Element) elseTfe.createNode(data_info.ExtsubList(from, data_info.size()));
			else
				return (Element) elseTfe.createNode((ExtList)data_info.get(from));		
		}
		return new Element(Tag.valueOf("span"), "");
	}

	public String work(ExtList data_info) {

		String conditionResult = (String) data_info.get(0);
		if(conditionResult.equals("t") || conditionResult.equals("1")) {
			if(thenTfe instanceof Connector || thenTfe instanceof Attribute
				|| thenTfe instanceof Function || thenTfe instanceof IfCondition)
				thenTfe.work((ExtList)data_info.ExtsubList(1, thenTfe.countconnectitem()+1));
		}
		else if(elseTfe != null){
			int from = thenTfe.countconnectitem()+1;
			if(elseTfe instanceof Connector || elseTfe instanceof Attribute
					|| elseTfe instanceof Function || elseTfe instanceof IfCondition){
				elseTfe.work(data_info.ExtsubList(from, data_info.size()));
			}
			else
				elseTfe.work((ExtList)data_info.get(from));
			}
		return null;		
		}
}