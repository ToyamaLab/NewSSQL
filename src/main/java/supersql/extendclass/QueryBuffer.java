package supersql.extendclass;


import java.util.*;

import supersql.codegenerator.AttributeItem;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.parser.*;

public class QueryBuffer {
    private ExtList schf;
    public ExtList sep_sch;
    private HashSet tg;
    private FromInfo fi;
    private HashMap<Integer, AttributeItem> atts;
    private ExtList aggregate_list;
    private ExtList aggregate_attnum_list;
    private ExtList result;
    private String query = "";
    public ExtList constructedResult;
//    private ExtList orderTable = Preprocessor.getOrderByTable();
    public int forestNum = 0; //Which tree belongs to in forest
    public int treeNum = 0; //集約によって分割される前はどの木にいたか
    private ArrayList UsedTables;
    public int fromGroupNum = 0;
    public String selectClause = "";
    public String fromClause = "";
    public String whereCluase = "";
    public String groupbyClause = "";


    public QueryBuffer(ExtList schf){
//        ArrayList<Integer> tmp = new ArrayList<>();
//        for (int i = 0; i < schf.unnest().size(); i++) {
//            tmp.add((int)schf.unnest().get(i));
//        }
//        tmp.sort(Comparator.naturalOrder());
//        this.schf = new ExtList();
//        for (int i: tmp) {
//            this.schf.add(i);
//        }
        schf = schf.unnest();
        schf.sort(Comparator.naturalOrder());
        this.schf = new ExtList(schf);
        this.result = new ExtList();
    }

    public void setFromInfo(String line){
        fi = new FromInfo(line);
    }

    public void setTg(HashSet tg){
        this.tg = tg;
    }

    public ExtList getSchf(){
        return schf;
    }

    public HashSet getTg(){
        return tg;
    }

    public FromInfo getFi() {
        return fi;
    }

    public void setAtts(HashMap atts) {
        this.atts = atts;
    }

    public HashMap<Integer, AttributeItem> getAtts() {
        return atts;
    }

    public void setAggregate_list(ExtList aggregate_list) {
        this.aggregate_list = aggregate_list;
    }

    public ExtList getAggregate_list() {
        return aggregate_list;
    }

    public void setAggregate_attnum_list(ExtList aggregate_attnum_list) {
        this.aggregate_attnum_list = aggregate_attnum_list;
    }

    public void setResult(ExtList result) {
        this.result = new ExtList(result);
    }

    public ExtList getResult() {
        return result;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public ExtList getAggregate_attnum_list() {
        return aggregate_attnum_list;
    }

    public void makeQuery(WhereInfo where){
        Boolean flag = false;
        StringBuffer buf = new StringBuffer();
        StringBuffer buf2 = new StringBuffer();
        //SELECT句作成
        //make SELECT clause
        buf.append("SELECT ");
        boolean isAgg = false;
//        boolean isOrder = false;
        boolean containAgg = false;
//        String orderStr = new String();
        schf.sort(Comparator.naturalOrder());
        this.UsedTables = this.makeTableGroup();
        int aggCount = 0;
        for(int index = 0; index < this.schf.size(); index++){
            int attnum = Integer.parseInt(this.schf.getExtListString(index));
//            isOrder = false;
//            for (int i = 0; i < orderTable.size(); i++) {
//                String ordert = orderTable.get(i).toString();
//                if(ordert.substring(ordert.indexOf("["), ordert.indexOf("]") + 1).equals("["+attnum+"]")){
//                    orderStr = ordert.substring(0, ordert.indexOf("]") + 1);
//                    isOrder = true;
//                }
//            }
            String att = atts.get(attnum).getSQLimage();
            isAgg = false;
            String func_att = new String();
            if(this.aggregate_attnum_list.contains(attnum)){
                //集約だったらfunction_name(att)の形にして追加
                //if the attribute is to be aggregated, add by form of aggregation.
                String function_name = new String();
                String agg_str = new String();
                for(Object o: aggregate_list) {
                    if (Integer.parseInt(o.toString().split(" ")[0]) == attnum) {
                        agg_str = o.toString();
                    }
                }
                function_name = agg_str.split(" ")[1];
                func_att = function_name + "(" + att + ")";
                isAgg = true;
                containAgg = true;
            }
            if(!isAgg){
                //集約じゃなかったらそのまま追加
                //if Not aggregation, add as it is.
                if(index == 0){
                    buf2.append(att);
                }else{
                    buf2.append(", "+att);
                }
            }else{
                aggCount++;
                if(index == 0){
                    buf2.append(func_att);
                }else{
                    buf2.append(", "+func_att);
                }
            }
        }
        if(!isAgg){
            buf.append("");
        }
        buf.append(buf2.toString());
        this.selectClause = buf.toString().substring(buf.toString().indexOf("SELECT")).trim();

//        System.out.println("relatedGLO:::"+GlobalEnv.relatedTableSet);
//        System.out.println("usedTables:::"+usedTables);
        makeUsedTables(this.UsedTables);

//        System.out.println("relateSet:::"+GlobalEnv.relatedTableSet);
//        System.out.println("usedTables_after:::"+this.UsedTables);
        ArrayList<String> orderedTables = new ArrayList<>();
        if(GlobalEnv.isOrderFrom()) {
            Long min = Long.MAX_VALUE;
            String minTbl = "";
            for (int i = 0; i < this.UsedTables.size(); i++) {
                Long size = GlobalEnv.tableSize.get(this.UsedTables.get(i));
                if (size < min) {
                    min = size;
                    minTbl = this.UsedTables.get(i).toString();
                }
            }
            orderedTables.add(minTbl);
//        System.out.println("order:::"+orderedTables);
            orderTable(orderedTables);
        }else{
            orderedTables = this.UsedTables;
        }
//        System.out.println("orderedTable:::"+orderedTables);

        //FROM句作成
        //make From clause
        // UsedTableが空だったらFromはいらない
        if (this.UsedTables.size() != 0) {
            buf.append(" FROM ");
            StringBuffer fromc = new StringBuffer();
            List<FromTable> fts = From.getFromItems();
            if (GlobalEnv.isOrderFrom()) {
                if (From.hasFromItems()) {
                    ArrayList<ConstraintItem> constraintBuffer = new ArrayList<>();
                    boolean isFirst = true;
                    ArrayList<String> processedTables = new ArrayList<>();
                    for (String alias : orderedTables) {
//                System.out.println("ConstraintBuffer");
                        for (int i = 0; i < constraintBuffer.size(); i++) {
//                    System.out.println("state:::" + constraintBuffer.get(i).statement);
                        }
                        FromTable fi = From.getFromTable(alias);
                        processedTables.add(alias);
//                System.out.println("processTables:::" + processedTables);
                        String constraint_tmp = "(";
                        FromTable fromTableTmp;
                        if (fi == null) {
                            JoinItem ji = From.getJoinItem(alias);
                            fromTableTmp = ji.table;
                            //今見てるjoin(table)にひっついてる条件を含むかどうか判断
                            for (int i = 0; i < ji.constraint.size(); i++) {
//                        System.out.println("constraint_tmp_ji:::" + constraint_tmp);
//                        System.out.println("state_ji:::" + ji.constraint.get(i).statement);
                                ArrayList<String> ut_const = ji.constraint.get(i).getUsedTables();
//                        System.out.println("ut_const_ji:::" + ut_const);
                                if (isFirst) {
                                    constraintBuffer.add(ji.constraint.get(i));
                                } else {
                                    boolean cont = true;
                                    for (int j = 0; j < ut_const.size(); j++) {
                                        if (!processedTables.contains(ut_const.get(j))) {
                                            cont = false;
                                            break;
                                        }
                                    }
                                    if (cont) {
                                        if (constraint_tmp.length() == 1) {
                                            if (ji.constraint.get(i).operator == "") {
                                                constraint_tmp += ji.constraint.get(i).statement + " ";
                                            } else {
                                                constraint_tmp += ji.constraint.get(i).statement.substring(ji.constraint.get(i).statement.indexOf(" ") + 1) + " ";
                                            }
                                        } else {
                                            if (ji.constraint.get(i).operator == "") {
                                                constraint_tmp += " OR " + ji.constraint.get(i).statement + " ";
                                            } else {
                                                constraint_tmp += ji.constraint.get(i).statement + " ";
                                            }
                                        }
                                    } else {
                                        constraintBuffer.add(ji.constraint.get(i));
                                    }
                                }
                            }
                        } else {
                            fromTableTmp = fi;
                        }
                        //以前で使わなかったやつを使うか判断
                        if (!isFirst) {
                            for (int i = 0; i < constraintBuffer.size(); i++) {
//                            System.out.println("constraint_tmp_buf:::" + constraint_tmp);
//                            System.out.println("state_buf:::" + constraintBuffer.get(i).statement);
                                ArrayList<String> ut_const = constraintBuffer.get(i).getUsedTables();
//                            System.out.println("ut_const_buf:::" + ut_const);
                                boolean cont = true;
                                for (int j = 0; j < ut_const.size(); j++) {
                                    if (!processedTables.contains(ut_const.get(j))) {
                                        cont = false;
                                        break;
                                    }
                                }
                                if (cont) {
                                    if (constraint_tmp.length() == 1) {
                                        if (constraintBuffer.get(i).operator == "") {
                                            constraint_tmp += constraintBuffer.get(i).statement + " ";
                                        } else {
                                            constraint_tmp += constraintBuffer.get(i).statement.substring(constraintBuffer.get(i).statement.indexOf(" ") + 1) + " ";
                                        }
                                    } else {
                                        if (constraintBuffer.get(i).operator == "") {
                                            constraint_tmp += " OR " + constraintBuffer.get(i).statement + " ";
                                        } else {
                                            constraint_tmp += constraintBuffer.get(i).statement + " ";
                                        }
                                    }
                                    constraintBuffer.remove(i);
                                    i--;
                                }
                            }
                        }
                        constraint_tmp.trim();
                        constraint_tmp += ")";
                        if (isFirst) {
                            fromc.append(fromTableTmp.getLine());
                            isFirst = false;
                        } else {
                            if (constraint_tmp.equals("()")) {
                                fromc.append(", ");
                                fromc.append(fromTableTmp.getLine());
                            } else {
                                fromc.append(" JOIN " + fromTableTmp.getAlias() + " ON " + constraint_tmp);
                            }
                        }
                    }
                }
                if (fromc.charAt(fromc.length() - 1) == ',') {
                    fromc = new StringBuffer(fromc.substring(0, fromc.length() - 1));
                }
                buf.append(" ");
                buf.append(fromc);
            } else {
                if (From.hasFromItems()) {
                    for (int i = 0; i < fts.size(); i++) {
                        FromTable ft = fts.get(i);
                        if (this.UsedTables.contains(ft.getAlias())) {
                            buf.append(ft.getLine());
                            buf.append(" ,");
                        }
                    }
                    if (From.hasJoinItems()) {
                        List<JoinItem> jis = From.getJoinItems();
                        for (int i = 0; i < jis.size(); i++) {
                            JoinItem ji = jis.get(i);
                            if (this.UsedTables.contains(ji.table.getAlias())) {
                                if (ji.isSimple()) {
                                    buf.append(ji.table.getLine());
                                    buf.append(" ,");
                                } else {
                                    boolean same = true;
                                    for (int j = 0; j < ji.getUseTables().size(); j++) {
                                        for (int k = 0; k < ji.getUseTables().get(j).size(); k++) {
                                            String alias1 = ji.getUseTables().get(j).get(k);
                                            if (!this.UsedTables.contains(alias1) && !alias1.equals("constant_value")) {
                                                same = false;
                                                break;
                                            }
                                        }
                                        if (!same) {
                                            break;
                                        }
                                    }
                                    if (same) {
                                        if (buf.charAt(buf.length() - 1) == ',') {
                                            buf = new StringBuffer(buf.substring(0, buf.length() - 1));
                                        }
                                        buf.append(" ");
                                        buf.append(ji.getStatement() + ",");
                                    } else {
                                        buf.append(ji.table.getLine() + ",");
                                    }
                                }
                            }
                        }
                    }
                }
                if (buf.charAt(buf.length() - 1) == ',') {
                    buf = new StringBuffer(buf.substring(0, buf.length() - 1));
                }
            }
        }


        if(buf.toString().indexOf("FROM") != -1){
            this.fromClause = buf.toString().substring(buf.toString().indexOf("FROM")).trim();
        }
//        System.out.println("used_tables:::"+usedTables);
//        System.out.println("query:::"+buf.toString());

        //WHERE句作成
        //make Where clause
        Iterator e2 = where.getWhereClause().iterator();
        boolean first = true;
        while (e2.hasNext()) {
            WhereParse whe = (WhereParse) e2.next();
//            System.out.println("where clause:::"+whe.getUseTables());
            boolean addFlag = true;
            Iterator ut = whe.getUseTables().iterator();
            int counter = 0;
            String tname_bak = new String();
            while(ut.hasNext()){
                counter++;
                String tname = ut.next().toString();
                tname_bak = tname;
                if(!this.UsedTables.contains(tname)){
                    addFlag = false;
                    break;
                }
            }
            if(counter == 1){
                if(this.UsedTables.contains(tname_bak)){
                    addFlag = true;
                }
            }
            if(addFlag){
                if(first){
                    buf.append(" WHERE ");
                    buf.append(whe.getLine());
                    first = false;
                }else{
                    buf.append(" AND ");
                    buf.append(whe.getLine());
                }
            }
        }
        if(buf.toString().indexOf("WHERE") != -1){
            this.whereCluase = buf.toString().substring(buf.toString().indexOf("WHERE")).trim();
        }

        //Group By句作成
        //make Group By clause
        if(containAgg && schf.size() - aggCount >= 1) {
            StringBuilder bufGroupClause = new StringBuilder();
            bufGroupClause.append(" GROUP BY ");
            boolean existAggregateAtt = false;
            int j = 0;
            for (Object attnum : this.schf) {
                if (!this.aggregate_attnum_list.contains(attnum) && !atts.get((int)attnum).isConst) {
                    String attribute = atts.get((int) attnum).getSQLimage();
                    //if (!attribute.startsWith("'") || !attribute.endsWith("'")) {
                	if ((!attribute.startsWith("'") || !attribute.endsWith("'")) &&
                		(!attribute.startsWith("N'") || !attribute.endsWith("'"))) {
                	    existAggregateAtt = true;
                        if (j == 0) {
                            bufGroupClause.append(attribute);
                            j++;
                        } else {
                            bufGroupClause.append(", ");
                            bufGroupClause.append(attribute);
                        }
                    }
                }
            }
            if (existAggregateAtt) {
                buf.append(bufGroupClause.toString());
            }
        }
        if(buf.toString().indexOf("GROUP BY") != -1){
            this.groupbyClause = buf.toString().substring(buf.toString().indexOf("GROUP BY")).trim();
        }

        buf.append(";");
        this.query = buf.toString();
//        System.out.println("query:::"+this.query);
    }

    private void orderTable(ArrayList<String> orderedTables) {
        String minTbl = new String();
        Long min = Long.MAX_VALUE;
        for (int i = 0; i < orderedTables.size(); i++) {
            ArrayList<String> next_tmp = GlobalEnv.relatedTableSet.get(orderedTables.get(i));
            for (int j = 0; j < next_tmp.size(); j++) {
                String tbl = next_tmp.get(j);
                if(!orderedTables.contains(tbl) && this.UsedTables.contains(tbl)){
                    Long size = GlobalEnv.tableSize.get(tbl);
                    if(size < min){
                        minTbl = tbl;
                        min = size;
                    }
                }
            }
        }
        if(!minTbl.equals("")){
            orderedTables.add(minTbl);
            orderTable(orderedTables);
        }
    }

    private ArrayList<String> makeTableGroup() {
        HashSet<String> usedTables = new HashSet<>();
        for(int index = 0; index < this.schf.size(); index++){
            int attnum = Integer.parseInt(this.schf.getExtListString(index));
            AttributeItem attribute = (AttributeItem)atts.get(attnum);
            usedTables.addAll(attribute.getUseTables());
        }
        return new ArrayList<>(usedTables);
    }

    private void makeUsedTables(ArrayList<String> usedTables) {
        for (int i = 0; i < usedTables.size(); i++) {
            ArrayList<String> relatedtables = GlobalEnv.relatedTableSet.get(usedTables.get(i));
//            System.out.println("rel: " + GlobalEnv.relatedTableSet);
//            System.out.println("used: " + usedTables);
//            System.out.println("relatedtables:::"+relatedtables);
            for (int j = 0; j < relatedtables.size(); j++) {
                if(relatedtables.get(j).equals("contains_one_side_constraint")){
                    continue;
                }
                findUsedTables(usedTables.get(i), relatedtables.get(j), GlobalEnv.relatedTableSet.get(relatedtables.get(j)), usedTables);
            }
        }
    }

    private Boolean findUsedTables(String parent, String now, ArrayList<String> child, ArrayList<String> usedTables) {
//        System.out.println("parent:::"+parent);
//        System.out.println("now:::"+now);
//        System.out.println("child:::"+child);
//        System.out.println("usedTables:::"+usedTables);
        if(usedTables.contains(now)){
            return true;
        }
//        System.out.println("child:::"+child);
        for (int i = 0; i < child.size(); i++) {
            if(child.get(i).equals("contains_one_side_constraint")){
                if(!usedTables.contains(now)){
                    usedTables.add(now);
                }
                return true;
            }
            if(!child.get(i).equals(parent)){
                boolean result = findUsedTables(now, child.get(i), GlobalEnv.relatedTableSet.get(child.get(i)), usedTables);
                if(result){
                    if(!usedTables.contains(now)){
                        usedTables.add(now);
                    }
                    return result;
                }
            }
        }
        return false;
    }

    public void showDebug(){
        showDebug("");
    }

    public void showDebug(String str){
//        ArrayList<String> ut = makeTableGroup();
//        this.makeUsedTables(ut);
//        Collections.sort(ut);
        System.out.println(str + "----------QueryBuffer Information----------");
        System.out.println(str + "Forest Num is "+this.forestNum);
        System.out.println(str + "Tree Num is "+this.treeNum);
        System.out.println(str + "From Group Num is "+this.fromGroupNum);
        System.out.println(str + "sep_sch is "+this.sep_sch);
        System.out.println(str + "SQL Query is "+this.getQuery());
        System.out.println(str + "Tuples Num is "+this.result.size());
        System.out.println(str + "Schf is " + this.schf);
//        System.out.println("Used Tables are "+ ut);
//        System.out.println("SELECT Clouse is "+ this.selectClause);
//        System.out.println("FROM clouse is "+ this.fromClause);
//        System.out.println("WHERE clouse is "+ this.whereCluase);
//        System.out.println("GroupBY clouse is "+ this.groupbyClause);
        System.out.println(str + "atts is " + this.atts);
//        System.out.println(str + "Result is "+this.getResult());
        System.out.println(str + "Constructed Result is "+this.constructedResult);
        System.out.println(str + "+++++++++++++++++++++++++++++++++++++++++++");

    }

    public ArrayList<String> getUsedTables(){
        ArrayList<String> tg = this.makeTableGroup();
        makeUsedTables(tg);
        return tg;
    }

    public void makeAllPattern() {
//        this.showDebug("MakeAll: ");
        ExtList info = Preprocessor.getCtabList();
        ExtList infoCorresponding = new ExtList();
        int num = info.size();
        boolean contain = false;
        ExtList sep_sch = this.sep_sch.unnest();
        for (int i = 0; i < sep_sch.size(); i++) {
            for (int j = 0; j < num; j++) {
                if(info.getExtListString(j).split(" ")[0].equals(sep_sch.getExtListString(i))){
                    if(!infoCorresponding.contains(info.getExtListString(j))){
                        infoCorresponding.add(info.getExtListString(j));
                    }
                    if(info.getExtListString(j).split(" ")[1].indexOf("ctab_side") != -1 || info.getExtListString(j).split(" ")[1].indexOf("ctab_value") != -1){
                        contain = true;
                    }
                }
            }
        }
//        Log.info("contain:::"+contain);
//        Log.info("Corre:::"+infoCorresponding);
        ExtList result = this.result;
        if(!contain){
//            Log.info("\tThis QueryBuffer is not a Cross_tab form");
            boolean onlyHead = true;
            for (int i = 0; i < infoCorresponding.size(); i++) {
                if(infoCorresponding.getExtListString(i).indexOf("ctab_head") == -1 && infoCorresponding.getExtListString(i).indexOf("ctab_leftup") == -1){
                    onlyHead = false;
                    break;
                }
            }
            if(onlyHead){
//                Log.info("\tThis QueryBuffer only contains head attributes");
//                Log.info("\tExtract head Attribute Start");
                Long ehsetStart = System.currentTimeMillis();
                ExtList tmpKey = new ExtList();
                ArrayList<Integer> headIdx = new ArrayList();
                ArrayList<Integer> ctabSch = new ArrayList<>();
                ArrayList<Integer> notContainSch = new ArrayList<>();
                for (int i = 0; i < this.sep_sch.unnest().size(); i++) {
                    int sepsch = Integer.parseInt(this.sep_sch.unnest().getExtListString(i));
                    boolean isContain = false;
                    for (int j = 0; j < infoCorresponding.size(); j++) {
                        String tmp = infoCorresponding.getExtListString(j).split(" ")[1].trim();
                        int sch = Integer.parseInt(infoCorresponding.getExtListString(j).split(" ")[0].trim());
                        if (sch == sepsch){
                            isContain = true;
                            if (tmp.contains("head") || tmp.contains("leftup")){
                                if (!tmp.contains("agg")){
                                    tmpKey.add(tmp);
                                }else{
                                    notContainSch.add(sch);
                                }
                            }
                        }
                    }
                    if (!isContain){
                        tmpKey.add("notCntainedAtt");
                    }
                }


                ExtList tmpValue = new ExtList();
                for (int i = 0; i < result.size(); i++) {
                    ExtList tmp = new ExtList();
                    for (int j = 0; j < result.getExtList(i).size(); j++) {
                        int sch = Integer.parseInt(this.sep_sch.unnest().getExtListString(j));
                        if(!notContainSch.contains(sch)) {
                            tmp.add(result.getExtListString(i, j));
                        }
                    }
                    if(!tmpValue.contains(tmp)) {
                        tmpValue.add(tmp);
                    }
                }

                GlobalEnv.headSet.put(tmpKey, tmpValue);
//                for (int i = 0; i < infoCorresponding.size(); i++) {
//                    String tmp = infoCorresponding.getExtListString(i).split(" ")[1];
//                    ExtList tmpSet = new ExtList();
//                    for (int j = 0; j < result.size(); j++) {
//                        if (!tmpSet.contains(result.getExtList(j).getExtListString(i))) {
//                            tmpSet.add(result.getExtList(j).getExtListString(i));
//                        }
//                    }
//                    GlobalEnv.headSet.put(tmp.trim(), tmpSet);
//                }
//                Log.info("headSet:::"+GlobalEnv.headSet);
                Long ehsetEnd = System.currentTimeMillis();
//                Log.info("\tExtract head Attribute End Time taken: " + (ehsetEnd - ehsetStart) + "ms");
            }

            return;
        }
//        Log.info("result:::"+result);
//        Log.info("info_corres:::"+infoCorresponding);
        int[] index = new int[sep_sch.unnest().size()];
//        int[] index = new int[infoCorresponding.size()];
//        Log.info("index:::");
        ExtList headKey = new ExtList();
        ArrayList<Integer> sideIdx = new ArrayList<>();
        for (int i = 0; i < this.sep_sch.unnest().size(); i++) {
            int sch = Integer.parseInt(this.sep_sch.unnest().getExtListString(i));
            boolean isSide = false;
            for (int j = 0; j < infoCorresponding.size(); j++) {
                int infoNum = Integer.parseInt(infoCorresponding.getExtListString(j).split(" ")[0].trim());
                String infoStr = infoCorresponding.getExtListString(j).split(" ")[1].trim();
                if (infoStr.contains("side") && infoNum == sch) {
                    isSide = true;
                    break;
                }
            }
            if (isSide) {
                sideIdx.add(i);
            }
        }
        for (int i = 0; i < infoCorresponding.size(); i++) {
            if(infoCorresponding.getExtListString(i).contains("head") || infoCorresponding.getExtListString(i).contains("leftup")){
                headKey.add(infoCorresponding.getExtListString(i).split(" ")[1].trim());
            }
        }
        ExtList headSet = new ExtList();
        ExtList sideSet = new ExtList();
//        Log.info("\tExtracting side and head value");
        Long extractStart = System.currentTimeMillis();
        for (int i = 0; i < result.size(); i++) {
            ExtList one = result.getExtList(i);
//            ExtList head_tmp = new ExtList();
            ExtList side_tmp = new ExtList();
            for (int j = 0; j < one.size(); j++) {
                /*if(index[j] == 0){
                    head_tmp.add(one.getExtListString(j));
                }else */if(sideIdx.contains(j)){
                    side_tmp.add(one.getExtListString(j));
                }
            }
//            if(!headSet.contains(head_tmp)) {
//                headSet.add(head_tmp);
//            }
            if(!sideSet.contains(side_tmp)) {
                sideSet.add(side_tmp);
            }
        }
        Set<ExtList> keys = GlobalEnv.headSet.keySet();
        ExtList realHeadKey = new ExtList();
        for(ExtList key: keys){
            boolean keyContain = true;
            for (int i = 0; i < headKey.size(); i++) {
                if(!key.contains(headKey.getExtListString(i))){
                    keyContain = false;
                    break;
                }
            }
            if(keyContain){
                headSet = GlobalEnv.headSet.get(key);
                realHeadKey = (ExtList)key.clone();
            }
        }
        Long extractEnd = System.currentTimeMillis();
//        Log.info("\tExtracting side and head value Time taken: " + (extractEnd - extractStart) + "ms");
        //種類全部出し
//        Log.info("headSet:::"+headSet);
//        Log.info("sideSet:::"+sideSet);
//        Log.info("result:::"+result.size());
        //sideSet*headSetの数がresultの数と同じなら終わり
        int addNum = (headSet.size() * sideSet.size()) - result.size();
        if(headSet.size() * sideSet.size() == result.size()){
//            Log.info("\tNo Additional Pattern");
            return;
        }
        //ここから全通りの組み合わせを作る
        //順番はその他の値→side→head
//        System.out.println("size:::"+size);
//        System.out.println("result_size:::"+result.size());
//        if(size > result.size()) {
//        Log.info("\tMaking All Pattern");
        Long makeStart = System.currentTimeMillis();
        ExtList allPattern_sidehead = new ExtList();
        ExtList info2 = new ExtList();
        for (int i = 0; i < realHeadKey.size(); i++) {
            if(realHeadKey.getExtListString(i).contains("notCntainedAtt")){
                info2.add(realHeadKey.getExtListString(i));
            }else{
                break;
            }
        }
        for (int i = 0; i < infoCorresponding.size(); i++) {
            info2.add(infoCorresponding.getExtListString(i).split(" ")[1]);
        }
//        System.out.println("info2:::"+info2);


        for (int i = 0; i < sideSet.size(); i++) {
            ExtList side = sideSet.getExtList(i);
            for (int j = 0; j < headSet.size(); j++) {
                ExtList one = new ExtList();
                ExtList head = headSet.getExtList(j);
                int headItr = 0;
                int sideItr = 0;
                for (int k = 0; k < info2.size(); k++) {
                    if (info2.getExtListString(k).contains("notCntainedAtt")){
                        one.add(head.getExtListString(headItr));
                        headItr++;
                    }else if(info2.getExtListString(k).contains("side")){
                        one.add(side.getExtListString(sideItr));
                        sideItr++;
                    }else if(info2.getExtListString(k).contains("head") || info2.getExtListString(k).contains("leftup")){
                        one.add(head.getExtListString(headItr));
                        headItr++;
                    }
                }
                allPattern_sidehead.add(one);
            }
        }
        Long makeEnd = System.currentTimeMillis();
//        Log.info("\tMaking All Pattern Time taken:" + (makeEnd - makeStart) + "ms");

//        Log.info("allP_sidehead:::" + allPattern_sidehead);
        String nullValue = "N/A";
        if (!GlobalEnv.nullValue.equals("PqVyySBvmTiyfKjsspwt56kXMxwqubX9DXkVNDKN")) {
            nullValue = GlobalEnv.nullValue;
        }
//        System.out.println("nullValue:::"+nullValue);
//        Log.info("\tMaking All Data");
        Long makedStart = System.currentTimeMillis();
        ExtList result_copy = new ExtList(result);
        // 以下でnullの時の値を入れている
        for (int i = 0; i < allPattern_sidehead.size(); i++) {
            // 表頭・表側の全組み合わせに対して
            ExtList one = allPattern_sidehead.getExtList(i);
            // diffは値が何個入るか計算してる
            int base = one.size();
            int diff = result.getExtList(0).size() - base;
            boolean contain2 = false;
//               Log.info("result::: " + result);
//            System.out.println("result_copy:::" + result_copy);]
            for (int j = 0; j < result_copy.size(); j++) {
//                loop1++;
                boolean same = true;
                ExtList result_one = result_copy.getExtList(j);
//                if (result_one.toString().trim().contains(one.toString().trim())){
//                    same = true;
//                }
                for (int k = 0; k < one.size(); k++) {
                    if (!result_one.getExtListString(k).equals(one.get(k))) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    contain2 = true;
                    result_copy.remove(j);
                    break;
                }
            }
            if (!contain2) {
                ExtList tmp = (ExtList) one.clone();
                for (int j = 0; j < diff; j++) {
                    if (!atts.get(Integer.parseInt(sep_sch.getExtListString(base + j))).isConst) {
                        tmp.add(nullValue);
                    } else {
                        tmp.add("");
                    }
                }
//                System.out.print("\tADD");
//                System.out.println(" " + addNum + " ");
                result.add(tmp);
            }
        }
        this.result = result;
//        Log.info("resultFinal:::"+result.size());
//        Log.info("finalresult:::"+result);
    }
}
