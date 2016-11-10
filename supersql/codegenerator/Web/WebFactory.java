package supersql.codegenerator.Web;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Decorator;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;

public class WebFactory extends Factory {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	@Override
	public Attribute createAttribute(Manager manager) {
		return new WebAttribute(manager, webEnv, webEnv2);
	}
	
	@Override
	public Decorator createDecoration(Manager manager) {
		return new WebDecoration(manager, webEnv, webEnv2);
	}
	
	@Override
	public Function createFunction(Manager manager) {
		return new WebFunction(manager, webEnv, webEnv2);
	}
	
	@Override
	public Connector createC1(Manager manager) {
		return new WebC1(manager, webEnv, webEnv2);
	}
	
	@Override
	public Connector createC2(Manager manager) {
		return new WebC2(manager, webEnv, webEnv2);
	}
	
	@Override
	public Connector createC3(Manager manager) {
		return new WebC3(manager, webEnv, webEnv2);
	}
	
	@Override
	public Grouper createG1(Manager manager) {
		return new WebG1(manager, webEnv, webEnv2);
	}
	
	@Override
	public Grouper createG2(Manager manager) {
		return new WebG2(manager, webEnv, webEnv2);
	}
	
	@Override
	public void createLocalEnv() {
		webEnv = new WebEnv();
		webEnv2 = new WebEnv();
	}
	
	@Override
	public Manager createManager() {
		return new WebManager(webEnv, webEnv2);
	}
}
