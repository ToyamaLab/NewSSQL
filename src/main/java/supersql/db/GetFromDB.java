package supersql.db;

import java.util.ArrayList;
import java.util.HashMap;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class GetFromDB {

	private SQLManager sqlm;

    public GetFromDB() {
        Log.out("[GetFromDB]");

        String hostname = GlobalEnv.gethost();
				String port = GlobalEnv.getport();
        String dbname = GlobalEnv.getdbname();
        String user = GlobalEnv.getusername();
        String driver = GlobalEnv.getDriver();
        String dbms = GlobalEnv.getdbms();
        String url = GlobalEnv.geturl();
        String password = GlobalEnv.getpassword();


        Log.out ("[hostname : " + hostname + "]");
				Log.out ("[port : " + port + "]");
        Log.out("[dbname   : " + dbname + "]");
        Log.out("[user : " + user + "]");
        Log.out("[driver : " + driver + "]");
        Log.out("[dbms : " + dbms + "]");
        Log.out("[password: " + password + "]");
        Log.out("[url: " + url + "]");

        sqlm = new SQLManager(url, user, driver, password);

    }

    public GetFromDB(ConnectDB cdb) {
        sqlm = new SQLManager(cdb);

    }

    public void execQuery(String query, ExtList ResultData) {

        ResultData.clear();
        //180906
        //Hive cannot accept the query which ends with ";"
        if(GlobalEnv.getdbms().equals("hive")){
            query = query.replace(";", "");
        }

        if (GlobalEnv.getframeworklist() != null) {
            Log.out("## From framework DB list ##");
            Log.out(GlobalEnv.getframeworklist());
            sqlm.ExecListToResult(GlobalEnv.getframeworklist(),query);
        	ResultData.addAll(sqlm.GetBody());
        }else{
        	sqlm.ExecSQL(query);
        	ResultData.addAll(sqlm.GetBody());
        }
        return;

    }

    public void close() {
        sqlm.close();
        return;
    }

    //added by taji 171103 start
	public void execUpdate(String TRIGGER, ExtList sep_data_info) {
        if(GlobalEnv.getdbms().equals("hive")){
            TRIGGER = TRIGGER.replace(";", "");
        }
        sqlm.ExecUpdate(TRIGGER);
	}

	public void create_log(String query_name, ArrayList pTables, HashMap<String, ArrayList> trigger_tables) {
		sqlm.create_log(query_name, pTables, trigger_tables);

	}


    //added by taji 171103 end

    public void getTableSize(String tblName, ExtList result) {
        sqlm.ExecMetaQuery(tblName);
        result.add(sqlm.GetBody());
    }

    public void getTableAtt(String tblName, ExtList result){
        sqlm.getTableAtt(tblName);
        result.add(sqlm.GetBody());
    }

}
