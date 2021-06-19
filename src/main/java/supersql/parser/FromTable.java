package supersql.parser;

public class FromTable {
    private String alias;
    private String tableName;
    private String line;

    public FromTable(String name){
        name = name.trim();
        line = name;
        if(name.split(" ").length == 1){
            if (name.startsWith("\"") && name.endsWith("\"")) {
                name = name.substring(1, name.length() - 1);
            }
            alias = name;
            tableName = name;
        }else{
            alias = name.split(" ")[1];
            tableName = name.split(" ")[0];
            if (alias.startsWith("\"") && alias.endsWith("\"")) {
                alias = alias.substring(1, alias.length() - 1);
            }
            if (tableName.startsWith("\"") && tableName.endsWith("\"")) {
                tableName = tableName.substring(1, tableName.length() - 1);
            }
        }
    }

    public String getAlias() {
        return alias;
    }

    public String getTableName() {
        return tableName;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "FromTable{" +
                "alias='" + alias + '\'' +
                ", tableName='" + tableName + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
