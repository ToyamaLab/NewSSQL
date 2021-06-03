package supersql.codegenerator.PDF;

import java.util.Vector;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.extendclass.ExtList;

public class PDFC3 extends Connector {

	private PDFManager manager;

	private PDFEnv pdf_env;

	public PDFC3(Manager manager, PDFEnv pdf_env) {
		this.manager = (PDFManager) manager;
		this.pdf_env = pdf_env;
	}

	@Override
	public String work(ExtList data_info) {
		System.out.println("");
		System.out.println("------- C3 -------");
		System.out.println("[PDFC3:work]tfe_info = " + makele0());
		System.out.println("[PDFC3:work]data_info = " + data_info);

		Vector vector_local = new Vector();
		PDFModifier modifier = new PDFModifier();

		int i;
		int y_default; //y_tmp
		int tate_tmp;
		int tate_max = 1;
		int yoko_inside = 0;
		int lap = 0;

		y_default = pdf_env.y_back;
		//y_max = y_default;





		setDataList(data_info);

		///////////////////////////////////////////////////////////////////////
		for (i = 0; i < tfeItems; i++) {
			ITFE tfe = (ITFE) tfes.get(i);

			if (tfe instanceof Attribute) {
				System.out.println("[PDFC3:work]tfe is Attribute");

				//modi if( ope_modi == false )
				modifier.clean2();
				//modi if( o_modi[i] instanceof AddedInfo )
				//modi modifier.get_modifier2(o_modi[i]);

				//String s_tmp = o[i].toString();
				//int i_tmp = Integer.valueOf(s_tmp).intValue();
				//System.out.println("[PDFC3:work]i_tmp = " + i_tmp);

				//List tab = table;

				//for(j=0; j<i; j++)
				//    tab = tab.cdr();

				//Object val = tab.car();
				//table = table.cdr();

				//Object data = data_info.get(i);
				//Object data = ((ExtList)data_List).get(0);

				worknextItem();
				

				//String s = data.toString();

//del
				
/////			vector_local.addElement(value);		////////////


				//lap++;
				//?? table = table.cdr();

				//if( i == number-1 && writer.flag == true ) {
				//    writer.str[] = new PDFValue[writer.vector.size()];
				//    writer.vector.copyInto(str);
				//}


				if (hasMoreItems()) {
//					manager.pagePrint();
					manager.pageReady();
				}
			}

			else {
				System.out.println("[PDFC3:work]tfe is instance of Operator");


				//if( i == number-1 && writer.flag == true )
				//    writer.flag = true;
				//else
				//    writer.flag = false;

				System.out.println("lap=" + lap);
				//for(i=0; i<lap; i++)
				//    table_list = table_list.cdr();

				//////////////////////
				pdf_env.y_back = y_default;

				////////////////////
				//if(ope.toString() == "C1" || ope.toString() == "C2")
				//    size_of_connector = o[i].size() - 1;

				//tfe.work(data_info);

				pdf_env.pre_operator = getSymbol();
				worknextItem();



				/*
				 * int cdr_num = 0; cdr_num = cdr_count((List)o[i], cdr_num);
				 * for( j=0; j <cdr_num; j++) table = table.cdr();
				 */

				yoko_inside = yoko_inside + pdf_env.yoko_back;

				//if(flag == true)
				//    writer.y_back = y_tmp;

				tate_tmp = pdf_env.tate_back;
				if (tate_max < tate_tmp)
					tate_max = tate_tmp;
				//y_tmp = writer.y_back;
				//if( y_max < y_tmp )
				//    y_max = y_tmp;

				//if( ope.toString() == "C2" || ope.toString() == "G2") {
				//    writer.x_back++;
				//    System.out.println("************* **************");
				//}


				if (hasMoreItems()) {
//					manager.pagePrint();
					manager.pageReady();
				}
			}

		}
		///////////////////////////////////////////////////////////////////////////
		pdf_env.tate_back = tate_max;

		System.out.println("***** tate_max = " + tate_max);

		PDFValue[] array = new PDFValue[vector_local.size()];
		vector_local.copyInto(array);

		//for(i=0; i<vector_local.size(); i++)
		//    ((PDFValue)((writer.vector.elementAt(array[i].element)))).tate =
		// tate_max;

		///////////////////////////////
		pdf_env.y_back = y_default + tate_max - 1;
		//writer.y_back = y_max;

		pdf_env.yoko_back = yoko_inside;
		return null;

	}

	/*
	 * public int cdr_count(List le_tmp, int cdr_num){
	 * if(le_tmp.car().toString() == "G1"||le_tmp.car().toString() ==
	 * "G2"||le_tmp.car().toString() == "G3") cdr_num++; else{ Enumeration e3 =
	 * le_tmp.cdr().elements(); while( e3.hasMoreElements() ){ Object le_element =
	 * e3.nextElement(); if( le_element instanceof List ) cdr_num =
	 * cdr_count((List)le_element, cdr_num ); else { cdr_num++;
	 * System.out.println("element = "+le_element); System.out.println("num =
	 * "+cdr_num); } } } return cdr_num; }
	 */

	/*
	 * public void createSchema(List table, List le, List le1, List le2, List
	 * le3){ }
	 */

	/*
	 * public String toString(){ return "C3"; }
	 */

	@Override
	public String getSymbol() {
		return "C3";
	}

}