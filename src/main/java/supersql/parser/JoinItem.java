package supersql.parser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jsqlparser.statement.select.Join;

public class JoinItem {
    public String method;
    public ArrayList<ConstraintItem> constraint;
    public FromTable table;
    private String statement;
    private boolean isSimple = true;

    public JoinItem(Join join){
        statement = join.toString();
        constraint = new ArrayList<>();
        if(!join.isSimple()){
            List<String> joinList = Arrays.asList(join.toString().split(" "));
            int idx = joinList.indexOf("JOIN");
            method = new String();
            for (int i = 0; i <= idx; i++) {
                method += (joinList.get(i) + " ");
            }
            method.trim();
            Constraint constraints = new Constraint(join.getOnExpression().toString());
            constraint = constraints.constraints;
            isSimple = false;
        }
        table = new FromTable(join.getRightItem().toString());
    }

    public ArrayList<ArrayList<String>> getUseTables(){
        ArrayList<ArrayList<String>> useTables = new ArrayList<>();
        for (ConstraintItem ci: constraint){
            ArrayList<String> tablePair = new ArrayList<>();
            for(String att: ci.attributes){
                tablePair.add(att.split("\\.")[0]);
            }
            if(tablePair.size() == 1){
                tablePair.add("constant_value");
            }
            useTables.add(tablePair);
        }
        return useTables;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public String getStatement(){
        return statement;
    }
}
