package supersql.parser;


import java.util.ArrayList;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class From {
    private static ArrayList<FromTable> fromItems = new ArrayList<>();
    private static ArrayList<JoinItem> joinItems = new ArrayList<>();
    private static StringBuilder fromLine = new StringBuilder();

    public From(ExtList listFrom){
        parseFromlist(listFrom);
        Log.out("[FromParse] fromItems: " + fromItems);
        Log.out("[FromParse] joinItems: " + joinItems);
        Log.out("[FromParse] fromLine: " + fromLine.toString());
    }

    private void parseFromlist(ExtList listFrom) {
        if(listFrom.size() > 1){
//            System.out.println("Join無しのクエリ");
            parseNoJoinFromList(listFrom);
        }else{
            if (listFrom.getExtListString(0, 0).equals("table_or_subquery")){
//                System.out.println("Join無しのクエリ");
                parseNoJoinFromList(listFrom);
            }else {
//                System.out.println("Join有りのクエリ");
                parseJoinFromList(listFrom);
            }
        }
    }

    private void parseNoJoinFromList(ExtList listFrom) {
        boolean flag = false;
        for (int i = 0; i < listFrom.size(); i++) {
            String name;
            if(listFrom.get(i) instanceof ExtList){
                name = Start_Parse.getText(listFrom.getExtList(i), Start_Parse.ruleNames);
                Start_Parse.builder = "";
                FromTable fromTable = new FromTable(name);
                fromItems.add(fromTable);
            }else{
                name = ", ";
            }
            fromLine.append(name);
        }
    }

    private void parseJoinFromList(ExtList listFrom) {
        fromLine.append(Start_Parse.getText(listFrom.getExtList(0), Start_Parse.ruleNames));
        String dummyStatement = "Select hoge FROM " + fromLine.toString();
        try{
            Statement stmt = CCJSqlParserUtil.parse(dummyStatement);
            Select select = (Select) stmt;
            PlainSelect pselect = (PlainSelect) select.getSelectBody();
            FromTable fromTable = new FromTable(pselect.getFromItem().toString());
            fromItems.add(fromTable);
            for (int i = 0; i < pselect.getJoins().size(); i++) {
                JoinItem joinItem = new JoinItem(pselect.getJoins().get(i));
                joinItems.add(joinItem);
            }
        }catch (JSQLParserException e){
        	e.printStackTrace();
        }

    }

    public static boolean hasFromItems(){
        if (fromItems.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean hasJoinItems(){
        if (joinItems.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<FromTable> getFromItems(){
        return fromItems;
    }

    public static FromTable getFromTable(String alias){
        for(FromTable ft: fromItems){
            if(ft.getAlias().equals(alias)){
                return ft;
            }
        }
        return null;
    }

    public static JoinItem getJoinItem(String alias){
        if(hasJoinItems()) {
            for (JoinItem ji : joinItems) {
                if(ji.table.getAlias().equals(alias)){
                    return ji;
                }
            }
        }
        return null;
    }

    public static ArrayList<JoinItem> getJoinItems(){
        return joinItems;
    }

    public static void clear(){
        fromItems.clear();
        joinItems.clear();
        fromLine = new StringBuilder();
    }
}
