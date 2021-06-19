package supersql.parser;

import java.util.ArrayList;

public class Constraint {
    public ArrayList<ConstraintItem> constraints;

    public Constraint(String state){
        String[] stateList = state.trim().split(" ");
        constraints = new ArrayList();
        for (int i = 0; i < stateList.length; i++) {
            if (i == 0) {
                ConstraintItem ci = new ConstraintItem(stateList[i] + " " + stateList[i + 1] + " " + stateList[i + 2]);
                i += 2;
                constraints.add(ci);
            }else{
                ConstraintItem ci = new ConstraintItem(stateList[i] + " " + stateList[i + 1] + " " + stateList[i + 2] + " " + stateList[i + 3]);
                i += 3;
                constraints.add(ci);
            }
        }
    }
}
