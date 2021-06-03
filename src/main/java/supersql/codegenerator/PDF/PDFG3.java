package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.extendclass.ExtList;

public class PDFG3 extends Grouper {

	private PDFManager manager;

	private PDFEnv pdf_env;

	public PDFG3(Manager manager, PDFEnv pdf_env) {
		this.manager = (PDFManager) manager;
		this.pdf_env = pdf_env;
	}

	@Override
	public String work(ExtList data_info) {
		System.out.println("\n------- G3 -------");
		System.out.println("[PDFG3:work]tfe_info = " + makele0());
		System.out.println("[PDFG3:work]data_info = " + data_info);

		PDFModifier modifier = new PDFModifier();

		int i;
		boolean fold = false;
		int alternate = 1;

		modifier.clean1();
		/*
		 * if( le2.car() instanceof AddedInfo ){ modifier.get_modifier1(
		 * le2.car() ); limit = modifier.limit; modifier.set_modifier1( writer );
		 * //writer.separate = modifier.separate; }
		 */
		modifier.clean2();
		/*
		 * if( (le2.cdr()).car() instanceof AddedInfo ) modifier.get_modifier2(
		 * (le2.cdr()).car() );
		 */

		setDataList(data_info);

		///////////////////////////////
		///////////////////////////////

		if (tfe instanceof Attribute) {
			System.out.println("[PDFG3:work]tfe is Attribute");
			System.out.println("number of data = " + data_info.size());

			//cast????
			//	    ExtList test = ((Attribute)tfe).get_DecorateList();
			//	    System.out.println("[PDFG2:work]decorate = " + test );
			//



			for (i = 0; i < data_info.size(); i++) {

				//Object data_List = data_info.get(i);
				//System.out.println(data_List);
				//Object data = ((ExtList)data_List).get(0);
				//System.out.println(data);

				pdf_env.tate = 1;////////////
				pdf_env.yoko = 1;////////////
				
				worknextItem();
				

				//String s1 = data.toString();

//del

				if (hasMoreItems()) {
//					manager.pagePrint();
					//modifier.page_clean(writer);
					manager.pageReady();
				}

				alternate++;
			}
		}

		//////////////////////////////////////////
		//////////////////////////////////////////
		else {
			System.out.println("[PDFG3:work]tfe is Operator");

			//writer.x_back = 1;
			//writer.y_back = 1;

			//////////////////////////////
			//tate_tmp = writer.tate_back;
			//yoko_tmp = writer.yoko_back;

			for (i = 0; i < data_info.size(); i++) {
				//System.out.println("le2.cdr() = " + le2.cdr());
				//System.out.println("le2.car() = " + le2.car());

				//Object tmp2 = le2.car();

				pdf_env.alternate = alternate;

				//tfe.work( (ExtList)data_info.get(i) );

				pdf_env.pre_operator = getSymbol();
				worknextItem();


				//if( fold != true )
				//    tate_inside = tate_inside + writer.tate_back;

				///////////////////////////////
				//writer.tate_back = tate_tmp;
				//writer.yoko_back = yoko_tmp;

				//if( yoko_inside < writer.yoko_back )
				//    yoko_inside = writer.yoko_back;

				/*
				 * fold == true && !(e2.hasMoreElements()) ) ){ yoko_tmp =
				 * yoko_tmp + yoko_inside; yoko_inside = 0; fold_now = false; }
				 */
				//////////////////////
				//writer.tate_back++;
				//if( fold == true )//25
				//    tate_inside = tate_inside + tate_fold;//25
				//else//25
				//    tate_inside = tate_inside + writer.tate_back;
				if (hasMoreItems()) {
//					manager.pagePrint();
					//modifier.page_clean(writer);
					manager.pageReady();
				}

				alternate++;

			}

			if (fold == true) {
				//writer.y_back = y_tmp + limit - 1;
				//tate_inside = tate_inside + tate_fold;//25
			}
		}
		/*
		 * /////////////////////////////// writer.tate_back = tate_inside;
		 * 
		 * if( fold == false ) writer.yoko_back = yoko_inside; else
		 * writer.yoko_back = yoko_tmp;
		 */
		return null;

	}

	/*
	 * public void createSchema(List table, List le, List le1, List le2, List
	 * le3){ }
	 */

	@Override
	public String getSymbol() {
		return "G3";
	}

}