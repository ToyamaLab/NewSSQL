package supersql.dataconstructor;

import java.util.TreeMap;

import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

public class QueryDivider {

	private final double GROUP = 0.1;
	private Start_Parse parser;
	private TreeMap<String, Attribute> vertices;
	private ExtList schema = new ExtList();
	int num; // the number in charge for setting grouping
	int level; // the level of the attribute in the schema
	int column; // for sorting the table
	boolean dupAtt = false;
	Graph g;

//	public QueryDivider( Start_Parse p )
//	{
//		this.parser = p;
//		//MakeGraph();
//	}
//
//	public boolean MakeGraph()
//	{
//		schema = parser.schema;
//		level = 0;
//		num = 0;
//		column = 0;
//		vertices = new TreeMap<String, Attribute>();
//		//System.out.println( schema );
//		if ( MakeSchemaNodes( schema ) ) 	//make nodes from the schema
//		{
//			return false;
//		}
//		MakeWhereNodes(); //make nodes from the where
//		g = new Graph( vertices ); 	//make the graph
//		AddWhereEdges();  			//connect nodes equated in where
//		g.setRoot();
//		//g.printGraph();
//		return true;
//	}

	private boolean MakeSchemaNodes(ExtList sch) 
	{
		Object o;
		double group;
		Attribute temp;
		level++;
		for (int i = 0; i < sch.size(); i++ )
		{
			o = sch.get( i );
			num++;
			group = level + ( num * GROUP );

			if ( !(o instanceof ExtList) )
			{
				Attribute n = NewNode( o, level );
				temp = vertices.put( o.toString(), n );

				if ( temp != null )
				{
					dupAtt = true;
				}

				n.setAttribute( true );
				n.setColumn( column++ );
				sch.set( sch.indexOf( o ), n);

			}
			else if ( IsLeaf( (ExtList) o ) )
			{
				ExtList obj = (ExtList) o;
				for ( int j = 0; j < obj.size(); j++ )
				{
					Attribute n = NewNode( obj.get(j), group );
					temp = vertices.put( obj.get( j ).toString(), n );
					if ( temp != null )
					{
						dupAtt = true;
					}
					n.setAttribute( true );
					n.setColumn( column++ );
					obj.set( j, n );
				}
			}
			else 
			{
				MakeSchemaNodes( (ExtList) o );
			}
		}
		level--;
		return dupAtt;
	}

	private void MakeWhereNodes()
	{	
		ExtList w1 = new ExtList();
		ExtList winfo_clause = (ExtList) ((ExtList) parser.list_where.get(1)).get(1);
		ExtList winfo_att = new ExtList();
		int winfo_size = winfo_clause.size();
		String att = new String();

		for ( int i = 0; i < winfo_size; i++)
		{
			Attribute n;
			ExtList w = (ExtList) winfo_clause.get(i);
			if(w.get(0).toString().equals("expr")){
				w = (ExtList) w.get(1);
				for(int j = 0; j < w.size(); j++){
					if(w.get(j) instanceof ExtList) w1 = (ExtList) w.get(j);
					else continue;
					if(w1.get(0).toString().equals("expr")){
						if(((ExtList)((ExtList)w1.get(1)).get(0)).get(0).toString().equals("table_alias")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)w1.get(1)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
							att = att + ((ExtList)w1.get(1)).get(1).toString();
							att = att + ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)w1.get(1)).get(2)).get(1)).get(0)).get(1)).get(0);
						}else if(((ExtList)((ExtList)w1.get(1)).get(0)).get(0).toString().equals("column_name")){
							att = ((ExtList)((ExtList)((ExtList)((ExtList)w1.get(0)).get(1)).get(0)).get(1)).get(0).toString();
						}else{
							continue;
						}
					}else{
						continue;
					}
					winfo_att.add(att);
				}
				int winfo_asize = winfo_att.size();
				String label;

				for ( int k = 0; k < winfo_asize; k++ )
				{
					label = ( String ) winfo_att.get( k );

					//if label is not a table, continue
					if ( label.indexOf('\'') != -1 ) continue;
					if ( ( n = vertices.get( label ) ) == null )
					{
						n = NewNode( ( String ) winfo_att.get( k ), -1 );
					}

					//n.setWhere( ( ( WhereParse ) winfo_clause.get( i ) ).getLine() );
					vertices.put( label, n );
				}
			}else{
				continue;
			}
		}
	}

//	private void AddWhereEdges()
//	{
//		//where info: with parenthesis, add edge
//		//same group, add an edge
//		//add filters too
//
//		int winfo_size = parser.get_where_info().getWhereClause().size();
//
//		for ( int i = 0; i < winfo_size; i++)
//		{
//			ExtList winfo_clause = parser.get_where_info().getWhereClause();
//			ExtList winfo_att = ( (WhereParse) winfo_clause.get( i ) ).getUseAtts();
//			int winfo_asize = winfo_att.size();
//
//			for ( int j = 0; j < winfo_asize; j+=2 )
//			{   	
//				Attribute node1 = vertices.get( (String) winfo_att.get( j ) ); 
//				Attribute node2 = vertices.get( (String) winfo_att.get( j + 1 ) ); 
//
//				if ( node2 == null ) continue;
//
//				node1.setWhere( ( ( WhereParse ) winfo_clause.get( i ) ).getLine() );
//				node2.setWhere( ( ( WhereParse ) winfo_clause.get( i ) ).getLine() );
//
//				//algorithm for checking connectors start
//				if ( node1.getConnTable().isEmpty() )
//				{
//					node1.setConnTable(node2.getTable());
//				}
//
//				if ( node2.getConnTable().isEmpty() )
//				{
//					node2.setConnTable(node1.getTable());
//				}
//
//				if ( node1.getConnTable().compareTo( node2.getTable() ) != 0  
//						&& node1.getTable().compareTo( node2.getTable() ) != 0 )
//				{ 
//					node1.setConnector(true);
//					node1.setWhere("");
//				}
//
//				if ( node2.getConnTable().compareTo( node1.getTable() ) != 0
//						&& node1.getTable().compareTo( node2.getTable() ) != 0 )
//				{ 
//					node2.setConnector(true);
//					node2.setWhere("");
//				}
//				node1.connectTo( node2 );
//				node2.connectTo( node1 );
//				//algorithm for checking connectors end
//
//			}
//
//			//connect nodes within the parentheses
//			for ( int j = 0; (j + 2) < winfo_asize; j+=2 )
//			{ 
//
//				Attribute node1 = vertices.get( (String) winfo_att.get( j ) ); 
//				Attribute node2 = vertices.get( (String) winfo_att.get( j + 2 ) );
//
//				node1.connectTo( node2 );
//				node2.connectTo( node1 );
//
//			}
//		}
//	}

	private Attribute NewNode( Object l, double group )
	{
		Attribute n;
		String label = (String) l;
		String table_name = new String();
		String table_alias = new String();

		ExtList tables = parser.list_table;
		for(int i = 0; i < tables.size(); i++){
			table_name = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tables.get(i)).get(0)).get(1)).get(0)).get(1)).get(0).toString();
			if(((ExtList)tables.get(i)).size() == 2){
				table_alias = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tables.get(i)).get(1)).get(1)).get(0)).get(1)).get(0).toString();
			}

			if(label.substring(0, label.indexOf( '.' )).equals(table_alias)){
				break;
			}
		}

		String table = 	table_name + " " + table_alias;
		Log.info(table);
		n = new Attribute( label, table, group );

		return n;
	}

	private boolean IsLeaf(ExtList sch)
	{
		for (int i = 0; i < sch.size(); i++)
		{
			if ( sch.get(i) instanceof ExtList)
				return false;
		}
		return true;
	}

//	public ArrayList<SQLQuery> divideQuery()
//	{
//		return g.connectedComponents();
//	}

	public ExtList getSchema()
	{
		return schema;
	}


}
