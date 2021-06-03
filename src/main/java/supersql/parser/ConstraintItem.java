package supersql.parser;

import java.util.ArrayList;

public class ConstraintItem {
    public String statement;
    public ArrayList<String> attributes;
    public String operator;
    public String comparison;


    public ConstraintItem(String state){
        if(state.trim().charAt(0) == '(' || state.trim().charAt(state.length() - 1) == ')'){
            if(state.trim().charAt(0) != '('){
                state = state.substring(0, state.lastIndexOf(")"));
            }else if(state.trim().charAt(state.length() - 1) != ')'){
                state = state.substring(state.indexOf("(") + 1);
            }else{
                state = state.substring(state.indexOf("(") + 1, state.lastIndexOf(")"));
            }
        }
        statement = state.trim();
        int diff = 1;
        if(state.trim().split(" ").length == 4) {
            operator = state.trim().split(" ")[0];
            diff = 0;
        }else{
            operator = "";
        }
        String attLeft = "";
        if(state.trim().split(" ")[1 - diff].trim().charAt(0) == '\'' || state.trim().split(" ")[1 - diff].trim().charAt(0) == '"'){
            attLeft = "";
        }else {
            try {
                Integer.parseInt(state.trim().split(" ")[1 - diff]);
                attLeft = "";

            } catch (NumberFormatException e) {
                attLeft = state.trim().split(" ")[1 - diff];
            }
        }

        comparison = state.trim().split(" ")[2 - diff];

        String attRight = "";
        if(state.trim().split(" ")[3 - diff].trim().charAt(0) == '\'' || state.trim().split(" ")[3 - diff].trim().charAt(0) == '"'){
            attRight = "";
        }else {
            try {
                Integer.parseInt(state.trim().split(" ")[3 - diff]);
                attRight = "";
            } catch (NumberFormatException e) {
                attRight = state.trim().split(" ")[3 - diff];
            }
        }
        attributes = new ArrayList<>();
        if(attLeft.equals("")){
            attributes.add(attRight);
        }else if (attRight.equals("")){
            attributes.add(attLeft);
        }else{
            attributes.add(attLeft);
            attributes.add(attRight);
        }
    }

    public ArrayList<String> getUsedTables(){
        ArrayList<String> ut = new ArrayList<>();
        for (int i = 0; i < attributes.size(); i++) {
            ut.add(attributes.get(i).split("\\.")[0]);
        }
        return ut;
    }

    @Override
    public String toString() {
        return "ConstraintItem{" +
                "statement='" + statement + '\'' +
                ", attributes=" + attributes +
                ", operator='" + operator + '\'' +
                ", comparison='" + comparison + '\'' +
                '}';
    }
}
