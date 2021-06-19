package supersql.dataconstructor;

import java.util.ArrayList;

import supersql.extendclass.ExtList;

public class SQLQuery extends ArrayList {

	private ExtList result;
	private long execTime;
	public SQLQuery()
	{
        result = null;
	}
	
	public String getString()
	{
		int column = 0;
		long start, end;

		start = System.nanoTime();
		
		String stmt = "";
		String sql = "SELECT DISTINCT ";
		String atts = "";
		String tbls = "";
		String where = "";
		
		for ( int j = 0; j < size(); j++ )
		{
			Attribute current = (Attribute) get(j);
			
			if ( current.isAttribute() )
			{
				atts = atts + ", " + current.getLabel();
				current.setColumn( column++ );
			}
			
			if ( tbls.indexOf( current.getTable() ) == -1 )
			{
				tbls = tbls + ", " + current.getTable();
			}
			
			if ( current.getWhere() != null )
			{
				//System.out.println( current.getLabel() );
				if ( where.indexOf( current.getWhere() ) == -1)// && current.getWhere().indexOf('(') != 0 )
				{
					where = where + " AND " + current.getWhere();
				}
			}
		}
		
		where = where + " ";
        
        //System.out.println(where);
		//by goto
//		System.out.println("where:"+where);
//		System.out.println("atts:"+atts);
//		System.out.println("tbls:"+tbls);
		//if(atts!="")	atts = atts.substring(2).trim();
		atts = atts.substring(2).trim();
		tbls = tbls.substring(2).trim();
		System.out.println("atts=:"+atts);
		System.out.println("tbls=:"+tbls);
		//by goto
		
		if ( where.trim().length() != 0 )
		{
			where = where.substring(5).trim();
			
			stmt = sql + atts + " FROM " + tbls + " WHERE " + where + ";";
		}
		else
		{
			stmt = sql + atts + " FROM " + tbls + ";";
		}
		//System.out.println ("WHERE 2 " + where);
		end = System.nanoTime();
		execTime = end - start;
		return stmt;
	}

	public void setResult(ExtList result) {
		
		int noOfAtts = size();

		for ( int i = 0; i < noOfAtts; i++ )
		{
			Attribute a = (Attribute) get(i);
			if ( a.isAttribute() )
			{
				a.setTuples(result);
			}
		}
	}

	public ExtList getResult() {
		return result;
	}

	public long getExecTime() {
		return execTime;
	}
	
}
