package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;


public class PDFFactory extends Factory {

	PDFEnv pdf_env;

	@Override
	public void createLocalEnv() {
		pdf_env = new PDFEnv();
	}

	@Override
	public Manager createManager() {
		return new PDFManager(pdf_env);
	}

	@Override
	public Connector createC0(Manager manager) {
		//return new PDFC0(manager, pdf_env);
		return new PDFC1(manager, pdf_env);
	}

	@Override
	public Connector createC1(Manager manager) {
		return new PDFC1(manager, pdf_env);
	}

	@Override
	public Connector createC2(Manager manager) {
		return new PDFC2(manager, pdf_env);
	}

	@Override
	public Connector createC3(Manager manager) {
		return new PDFC3(manager, pdf_env);
	}

	@Override
	public Grouper createG0(Manager manager) {
		//return new PDFG0(manager, pdf_env);
		return new PDFG1(manager, pdf_env);
	}

	@Override
	public Grouper createG1(Manager manager) {
		return new PDFG1(manager, pdf_env);
	}

	@Override
	public Grouper createG2(Manager manager) {
		return new PDFG2(manager, pdf_env);
	}

	@Override
	public Grouper createG3(Manager manager) {
		return new PDFG3(manager, pdf_env);
	}

	@Override
	public Grouper createG4(Manager manager) {
		return null;
	}

	@Override
	public Attribute createAttribute(Manager manager) {
		return new PDFAttribute(manager, pdf_env);
	}

	@Override
	public Function createFunction(Manager manager) {
		return new PDFFunction(manager, pdf_env);
	}

	@Override
	public Attribute createConditionalAttribute(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IfCondition createIfCondition(Manager manager, Attribute condition,
			TFE thenTfe, TFE elseTfe) {
		return null;
	}

	@Override
	public Connector createC4(Manager manager) {
		return null;
	}

}
