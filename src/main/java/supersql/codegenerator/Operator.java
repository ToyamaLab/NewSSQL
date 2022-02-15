package supersql.codegenerator;


import java.io.Serializable;

import supersql.extendclass.ExtList;

public abstract class Operator extends TFE implements IOperator, Serializable{

	protected int Dimension;
	public int getDimension() {
		return Dimension;
	}

	protected ExtList data;
    protected int dindex;

    public Operator() {
    	super();
    }

	@Override
	public abstract String work(ExtList<ExtList<String>> data_info);

}
