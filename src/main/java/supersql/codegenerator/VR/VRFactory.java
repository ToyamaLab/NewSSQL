package supersql.codegenerator.VR;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Decorator;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;



//Operator, Manager鐃緒申鐃緒申鐃緒申鐃緒申鐃暑ク鐃初ス

public class VRFactory extends Factory {

	private VREnv vrEnv;
	private VREnv vrEnv2;

	@Override
	public Attribute createAttribute(Manager manager) {
		return new VRAttribute(manager, vrEnv, vrEnv2);
	}

	@Override
	public Connector createC0(Manager manager) {
		return new VRC0(manager, vrEnv, vrEnv2);
	}

	@Override
	public Connector createC1(Manager manager) {
		return new VRC1(manager, vrEnv, vrEnv2);
	}

	@Override
	public Connector createC2(Manager manager) {
		return new VRC2(manager, vrEnv, vrEnv2);
	}

	@Override
	public Connector createC3(Manager manager) {
		return new VRC3(manager, vrEnv, vrEnv2);
	}

	@Override
	public Connector createC4(Manager manager) {
		return new VRC4(manager, vrEnv, vrEnv2);
	}


	@Override
	public Grouper createG1(Manager manager) {
		return new VRG1(manager, vrEnv, vrEnv2);
	}

	@Override
	public Grouper createG2(Manager manager) {
		return new VRG2(manager, vrEnv, vrEnv2);
	}

	@Override
	public Grouper createG3(Manager manager) {
		return new VRG3(manager, vrEnv, vrEnv2);

	}

	@Override
	public Grouper createG4(Manager manager) {
		return new VRG4(manager, vrEnv, vrEnv2);
	}
	
	@Override
	public Grouper createG5(Manager manager) {
		return new VRG5(manager, vrEnv, vrEnv2);
	}
	
	@Override
	public Function createFunction(Manager manager) {
		return new VRFunction(manager, vrEnv, vrEnv2);
	}
	
	@Override
	public IfCondition createIfCondition(Manager manager, Attribute condition,
			TFE thenTfe, TFE elseTfe) {
		return new VRIfCondition(manager, vrEnv, vrEnv2, condition, thenTfe, elseTfe);
	}

	public Decorator createDecoration(Manager manager) {
		return new VRDecoration(manager, vrEnv, vrEnv2);
	}
	
	@Override
	public void createLocalEnv() {
		vrEnv = new VREnv();
		vrEnv2 = new VREnv();
	}

	@Override
	public Manager createManager() {
		return new VRManager(vrEnv, vrEnv2);
	}

	@Override
	public Connector createConcat(Manager manager) {
		return new VRCONCAT(manager, vrEnv, vrEnv2);
	}

}
