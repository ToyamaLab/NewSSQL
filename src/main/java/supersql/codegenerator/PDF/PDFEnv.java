package supersql.codegenerator.PDF;

import com.pdflib.PDFlibException;
import com.pdflib.pdflib;

import supersql.codegenerator.LocalEnv;
import supersql.extendclass.ExtList;

public class PDFEnv extends LocalEnv {

	pdflib p;

	String data;
	int tate, yoko;//////////////////
	
	String pre_operator;

	//Vector vector;

	int font;

	int x_back = 1;

	int y_back = 1;

	int tate_back = 1;

	int yoko_back = 1;

	int alternate;

	
	float widthPaper = 595;//test1:217;//test2:350;//600;//1000;//10000;
	float heightPaper = 842;//600;//10000;//600;
	float paddingPaper_H = 10;
	float paddingPaper_V = 10;

	PDFValue result;
	//
	//TFE tfeInfo;
	
	float padding_H = 5;
	float padding_V = 5;
	float tmp_width;
	float tmp_height;
	float linewidth = 1;//1.5f;
	//String fontname = "HeiseiKakuGo-W5";
	String fontname = "Helvetica-Bold";
	//String encoding = "UniJIS-UCS2-H";
	String encoding = "winansi";
	int DefaultFontSize;
	String DefaultFontStyle = "normal";
	

	int labelH;
	int labelV;
	int labelmaxH;
	int labelmaxV;
	
	String labelSuffixH;
	String labelSuffixV;
	ExtList labelListH;
	ExtList labelListV;
	
	//----------------------------
	int labelO;
	int labelmaxO;
	ExtList labelListOH;
	ExtList labelListOV;
	
	
	float flexTH;
	int minFontsize = 3;
	
	//Stack stack;
	float cutWidth;
	
	
	
	public PDFEnv() {
		try {

			p = new pdflib();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		labelListH = new ExtList();
		labelListV = new ExtList();
		labelListOH = new ExtList();
		labelListOV = new ExtList();
		
		//stack = new Stack();
	}

	public void createPDFfile(String filename) {
		try {

			if (p.begin_document(filename, "") == -1) {
			//if (p.open_file(filename) == -1) {
				System.err.println("Couldn't open PDF file" + filename);
				System.exit(1);
			}
			//p.set_info("Creator", filename+".java");
			//p.set_info("Author", "Toyama_Lab");
			//p.set_info("Title", filename);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void page_ready() {
		try {
			
			//p.begin_page_ext(595, 842, "");
			p.begin_page_ext(widthPaper, heightPaper, "");
			
			//p.scale(28.3465, 28.3465);
			
			//p.begin_page(595, 842);

			/* Change "host" encoding to "winansi" or whatever you need! */
			//font = p.load_font("Helvetica-Bold", "host", "");
			
			//font = p.load_font("HeiseiKakuGo-W5", "UniJIS-UCS2-HW-H", "");
			//font = p.load_font("HeiseiKakuGo-W5", "UniJIS-UCS2-H", "");
			font = p.load_font(fontname, encoding, "");
			
			//font = p.findfont("Helvetica-Bold", "host", 0);
			////font = p.findfont("HeiseiKakuGo-W5", "EUC-H", 0);
			System.out.println("This is PDFValue:font = " + font);

			DefaultFontSize = 8;
			//p.setfont(font, fontsize);


		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void end_page() {
		try {

			p.end_page_ext("");
			//p.end_page();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		}
		/* catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
	}

	public void closePDFfile(){
		try {

			p.end_document("");
			//p.close();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		}
		/* catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
	}

	public float stringwidth(String data, float fontsize) {	
		float data_width = 0;
		
		try {

			data_width = (float) p.stringwidth(data, font, fontsize);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return data_width;
	}
	
/*
	public float stringwidth(String s, PDFEnv pdf_env) {
		float s_width;
		//int char_length;

		fontsize = 8;
		

		s_width = pdf_env.stringwidth(s, fontsize);
		s_width = (int) s_width;

		//char_all = s.length();
		//s_width = char_all * fontsize;

		return s_width;
	}
*/
	public void setlinewidth() {
		try {

			p.setlinewidth(linewidth);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setlinewidth(int linewidth) {
		try {

			p.setlinewidth(linewidth);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void defaultborder() {
		try {
			p.setcolor("stroke", "rgb", 0.5f, 0.5f, 0.5f, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void setBorderColor(float R, float G, float B) {
		try {

			p.setcolor("stroke", "rgb", R, G, B, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void defaultbackground() {
		try {

			p.setcolor("fill", "rgb", 1, 1, 1, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setBGColor(float R, float G, float B) {
		try {

			p.setcolor("fill", "rgb", R, G, B, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void defaultfont() {
		try {

			p.setcolor("fill", "rgb", 0, 0, 0, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setFontColor(float R, float G, float B) {
		try {

			p.setcolor("fill", "rgb", R, G, B, 0);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void setfont(int font_type, float out_fontsize) {
		try {

			p.setfont(font_type, out_fontsize);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void show_xy(String s, float str_x, float str_y) {
		try {

			p.show_xy(s, str_x, str_y);
			
			//p.show_boxed(s, str_x, str_y, 100, 200, "left", "");
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public float textflow_blind(String data, float fontsize, String fontstyle, float data_width){
		float data_height = 0;
		
		try {
			
			int flow_num;
			String optlistC;
			String optlistF;
			
			optlistC = "fontname="+fontname+" ";
			//optlistC += "font="+font+" ";
			optlistC += "encoding="+encoding+" ";
			optlistC += "adjustmethod=split"+" ";
			optlistC += "alignment=justify"+" ";
			optlistC += "fontsize="+fontsize+" ";
			optlistC += "fontstyle="+fontstyle+" ";
			optlistC += "hyphenchar=1"+" ";
	//		optlistC += "shrinklimit=100%"+" ";
	//		optlistC += "spreadlimit=100%"+" ";
			
			flow_num = p.create_textflow(data, optlistC);
			optlistF = "blind";
			p.fit_textflow(flow_num, 0, 0, data_width, heightPaper, optlistF);
			
			int textline = (int)p.info_textflow(flow_num, "boxlinecount");
//			System.out.println("number of textline "+textline);
			p.delete_textflow(flow_num);
			
			System.out.println("TTTTTTTTTTTT "+textline);
			data_height = fontsize * textline;
	
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return data_height;
	}
	
	public void textflow(PDFValue instance, float posH, float posV, float[] fontcolor){
		try {
			
			int flow_num;
			String optlistC;
			String optlistF;
			
			optlistC = "fontname="+fontname+" ";
			//optlistC += "font="+font+" ";
			optlistC += "encoding="+encoding+" ";
			optlistC += "adjustmethod=split"+" ";
			optlistC += "alignment=justify"+" ";
			optlistC += "fontsize="+instance.fontsize+" ";
			optlistC += "fontstyle="+instance.fontstyle+" ";
			optlistC += "fillcolor={rgb "+fontcolor[0]+" "+fontcolor[1]+" "+fontcolor[2]+"}"+" ";
			optlistC += "hyphenchar=1"+" ";
			optlistC += "shrinklimit=100%"+" ";
			optlistC += "spreadlimit=100%"+" ";
			
			flow_num = p.create_textflow(instance.data, optlistC);		
			//flow_num = p.create_textflow(instance.data, "fontstyle=italic textwarning fontname=HeiseiKakuGo-W5 encoding=UniJIS-UCS2-H fontsize="+fontsize+" adjustmethod=split hyphenchar=1 alignment=center");			
			optlistF = "blind";
			p.fit_textflow(flow_num, posH, posV, posH+instance.data_width, posV+instance.data_height, optlistF);
			
			int textline = (int)p.info_textflow(flow_num, "boxlinecount");
			//System.out.println("number of textline "+textline);
			p.delete_textflow(flow_num);
			
			flow_num = p.create_textflow(instance.data, optlistC);
			//flow_num = p.create_textflow(instance.labelV, optlistC);
			//flow_num = p.create_textflow(Float.toString(instance.box_width), optlistC);
			optlistF = "showborder=false";
			posV += linewidth/2;//��Ĵ��
			p.fit_textflow(flow_num, posH, posV, posH+instance.data_width+1, posV+instance.data_height, optlistF);
			
			/*setlinewidth();
			defaultborder();
			save();
			p.rect(30,550,90,-fontsize*num-linewidth);
			fill_stroke();
			restore();*/
			
			p.delete_textflow(flow_num);
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	
	public int open_image_file(String type,String filename){
		int image_num = 0;
		
		try {
		
			image_num = p.load_image(type, filename, "");
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return image_num;
	}
	
	public void fit_image_blind(int image_num, float posH, float posV, float width, float height){		
		try {

			p.fit_image(image_num, posH, posV, "blind=true boxsize={"+width+" "+height+"}");
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void fit_image(int image_num, float posH, float posV, float width, float height){		
		try {

			p.fit_image(image_num, posH, posV, "boxsize={"+width+" "+height+"} fitmethod=meet");
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void close_image(int image_num){		
		try {
		
			p.close_image(image_num);
			
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public float get_value(String key, int num){
		float value = 0;
		try {
			if(num!=-1)
				value = (float)p.get_value(key, num);
				
		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return value;
	}
	
	public void save() {
		try {

			p.save();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void rect(float box_x, float box_y, float box_width, float box_height) {
		try {

			p.rect(box_x, box_y, box_width, box_height);

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void stroke() {
		try {

			p.stroke();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void fill_stroke() {
		try {

			p.fill_stroke();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void fill() {
		try {

			p.fill();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void restore() {
		try {

			p.restore();

		} catch (PDFlibException e) {
			System.err.print("PDFlib exception occurred in hello sample:\n");
			System.err.print("[" + e.get_errnum() + "] " + e.get_apiname()
					+ ": " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

/*
	public Vector get_vector() {
		return vector;
	}
*/

}