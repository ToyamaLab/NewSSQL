package supersql.codegenerator;

import java.io.Serializable;


import org.apache.log4j.net.SyslogAppender;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Grouper extends Operator implements Serializable{
	
    public TFE tfe; // 引数TFE

    public Grouper() {
    	super();
        Dimension = -1;
    }

    public Grouper(int d) {
    	super();
        Dimension = d;
    }

    public Grouper(int d, TFE t) {
    	super();
        Dimension = d;
        tfe = t;
    }

    public void setTFE(TFE t) {
        tfe = t;
    }

    public void debugout() {
        debugout(0);
    }

    public void debugout(int count) {

        Debug dbgout = new Debug();
        dbgout.prt(count, "<Grouper type=" + getSymbol() + " decoitems="
                + decos.size() + " id=" + id + ">");

        decos.debugout(count + 1);
        tfe.debugout(count + 1);

        dbgout.prt(count, "</Grouper>");

    }

    public ExtList makesch() {

        ExtList outsch = new ExtList();
        outsch.add(tfe.makesch());
//          Log.out("Grp outsch:"+outsch);

        return outsch;
    }

    public ExtList makele0() {
        ExtList le0 = new ExtList();
        le0.add(this.getSymbol());
        le0.add(tfe.makele0());
        Log.out("Grp le0:" + le0);
        return le0;
    }

    public String getSymbol() {
        return "G?";
    }

    public String work(ExtList data_info) {
		return null;
//		return aggregate;
    }

    public int countconnectitem() {
        return 1;
    }

    public void setDataList(ExtList d) {
        data = d;
        dindex = 0;
    }

    public boolean hasMoreItems() {
        return (dindex < data.size());
    }

    public Object createNextItemNode(){
    	ExtList subdata = (ExtList) (data.get(dindex));
    	dindex++;
        if (tfe instanceof Connector || tfe instanceof Attribute
                || tfe instanceof Function || tfe instanceof IfCondition || tfe instanceof Decorator) {
            return tfe.createNode(subdata);
        } else {
            return tfe.createNode((ExtList) subdata.get(0));
        }
    }

    public void worknextItem() {
        ExtList subdata = (ExtList) (data.get(dindex));
        if (tfe instanceof Connector || tfe instanceof Attribute
                || tfe instanceof Function || tfe instanceof IfCondition || tfe instanceof Decorator) {
        	Log.out("In Grouper.java subdata to be work: " + subdata);
        	tfe.work(subdata);
        } else {
            tfe.work((ExtList) subdata.get(0));
            Log.out(subdata.get(0));
        }
        dindex++;

    }

    public boolean isFirstItem() {
        return (dindex == 0);
    }

	//added by ria 20110913 start
	public ExtList makeschImage() {
        ExtList outsch = new ExtList();
        outsch.add(tfe.makeschImage());

        return outsch;
	}
	//added by ria 20110913 end

	public void addDeco(String name, String value, String condition) {
        decos.put(name, value, condition);
	}

	@Override
	public Object createNode(ExtList<ExtList<String>> data_info) {
		// TODO Auto-generated method stub
		return null;
	}


	//added by taji 171102 start
	public ExtList get_keys(boolean flag){
		ExtList keys = new ExtList();
		if(this.decos.containsKey("infinite-scroll")){
			keys = tfe.get_keys(true);
		}else{
			keys = tfe.get_keys(false);
		}

		return keys;

	}
	//added by taji 171102 end


}
