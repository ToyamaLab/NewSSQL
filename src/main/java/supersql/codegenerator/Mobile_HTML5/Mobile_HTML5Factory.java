package supersql.codegenerator.Mobile_HTML5;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Decorator;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;

//Operator, Manager���������륯�饹

public class Mobile_HTML5Factory extends Factory {

	Mobile_HTML5Env html_env;
	Mobile_HTML5Env html_env2;

	@Override
	public void createLocalEnv() {
		html_env = new Mobile_HTML5Env();
		html_env2 = new Mobile_HTML5Env();
	}

	@Override
	public Manager createManager() {
		return new Mobile_HTML5Manager(html_env,html_env2);
	}

	@Override
	public Connector createC0(Manager manager) {
		return new Mobile_HTML5C0(manager, html_env,html_env2);
	}

	@Override
	public Connector createC1(Manager manager) {
		return new Mobile_HTML5C1(manager, html_env,html_env2);
	}

	@Override
	public Connector createC2(Manager manager) {
		return new Mobile_HTML5C2(manager, html_env,html_env2);
	}

	@Override
	public Connector createC3(Manager manager) {
		//return new HTMLC1(manager, html_env);
		return new Mobile_HTML5C3(manager, html_env,html_env2);
	}

	@Override
	public Connector createC4(Manager manager) {
		return new Mobile_HTML5C1(manager, html_env,html_env2);
		//return new HTMLC4(manager);
	}
	
	//tbt add 180806
	@Override
	public Connector createConcat(Manager manager) {
		return new Mobile_HTML5CONCAT(manager, html_env, html_env2);
	}
	//tbt end

	@Override
	public Grouper createG0(Manager manager) {
		//return new HTMLG0(manager, html_env);
		return new Mobile_HTML5G1(manager, html_env,html_env2);
	}

	@Override
	public Grouper createG1(Manager manager) {
		return new Mobile_HTML5G1(manager, html_env,html_env2);
	}

	@Override
	public Grouper createG2(Manager manager) {
		return new Mobile_HTML5G2(manager, html_env,html_env2);
	}

	@Override
	public Grouper createG3(Manager manager) {
		return new Mobile_HTML5G3(manager, html_env,html_env2);

	}

	@Override
	public Grouper createG4(Manager manager) {
		return new Mobile_HTML5G1(manager, html_env,html_env2);
		//return new HTMLG4(manager, html_env);
	}

	@Override
	public Attribute createAttribute(Manager manager) {
		return new Mobile_HTML5Attribute(manager, html_env,html_env2);
	}
	
	@Override
	public Decorator createDecoration(Manager manager) {
		return new Mobile_HTML5Decoration(manager, html_env,html_env2);
	}

	@Override
	public Function createFunction(Manager manager) {
		return new Mobile_HTML5Function(manager, html_env,html_env2);
	}

	@Override
	public Attribute createConditionalAttribute(Manager manager) {
		// TODO Auto-generated method stub
		return new supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Attribute(manager, html_env, html_env2, true);
	}

	@Override
	public IfCondition createIfCondition(Manager manager, Attribute condition,
			TFE thenTfe, TFE elseTfe) {
		// TODO Auto-generated method stub
		return new supersql.codegenerator.Mobile_HTML5.Mobile_HTML5IfCondition(manager, html_env, html_env2, condition, thenTfe, elseTfe);
	}

}