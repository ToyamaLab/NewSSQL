package supersql.codegenerator.VR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;

import javax.print.attribute.standard.RequestingUserName;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import com.ibm.db2.jcc.a.b;
import com.ibm.db2.jcc.am.in;
import com.ibm.db2.jcc.am.k;
import com.ibm.db2.jcc.am.s;
import com.ibm.db2.jcc.sqlj.StaticSection;
//import com.sun.org.apache.xpath.internal.operations.Mult;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//import jdk.nashorn.internal.ir.annotations.Ignore;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.parser.queryParser.SortingContext;

public class VRfilecreate {

	private static final String fs = GlobalEnv.OS_FS;
	private static String filename;
	public static String template_museum = "Type";//museum
	public static String template_stand = "Type";//stand
	public static String b = "";
	
	
	public static void process(String outFileName) {
		filename = outFileName;
		String s = "";/////ジャンル出す
		VRcjoinarray.getJoin();
		VRcjoinarray.getexhJoin();
		
		VRAttribute.groupcount1 = VRAttribute.cjoinarray.size()+1;
		
		if(VRAttribute.groupcount == 0){//////ビルが一個だけだった時
			VRAttribute.groupcount = 1;
			b = getCS1();
				
			for(int i=1; i<=VRAttribute.groupcount; i++){
				b += "				if(groupflag ==" + i + "){\n";

				for(int k=0; k<VRAttribute.genrearray2.size(); k++){/////ジャンル出すはじめ
					s += VRAttribute.genrearray2.get(k)+",";
				}
				System.out.println("s="+VRAttribute.genrearray2);
				if(s.length()>0){
					s = s.substring(0,s.length()-1);/////最後のカンマとる
				}
				if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
					b += getCS2(VRAttribute.exharray.get(i-1),s);
				}else{
					compgetCS2(VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1] ,VRAttribute.compflag[i-1], s);
				}
				s = "";////////ジャンル出す終わり

				if(VRAttribute.floorarray.get(i-1) == 1){
					 b += "					int objx = 0;/////////////museum change\n";
				}else if(VRAttribute.floorarray.get(i-1) == 2){
				}else if(VRAttribute.floorarray.get(i-1) == 3){
					b += "					int objz = 0;/////////////museum change\n";
				}
				b += getCS3();
				if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
					getCS4_1(VRAttribute.exharray.get(i-1));
				}else{
					compgetCS4_1(VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1] ,VRAttribute.compflag[i-1]);
				}
				String m = VRAttribute.multiexhary.get(0);////[name,name]start
				String array[] = m.split("");////TFE一個ずつ入ってる
				for(int j=0; j<VRAttribute.multiexhcount.get(0);j++){			
					if(j == 0){
						b += "												if(k == " + j + "){\n";
					}else{
						b += "												}else if(k == " + j + "){\n";
						b += "													if(exhmoveflag == " + (j-1) + "){\n";
						if(array[j-1].equals(",")){
							b += "	 													exhmovex -= 1.3f;\n";	
						}else if(array[j-1].equals("!")){
							b += "	 													exhmovey += 2.0f;\n";
						}else if(array[j-1].equals("%")){
							b += "	 													exhmovez -= 1.3f;\n";
						}
						b += "														exhmoveflag++;\n";
						b += "													}\n";
					}
					if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
						getCS4_2(VRAttribute.exharray.get(i-1), VRAttribute.floorarray.get(i-1));
					}else{
						compgetCS4_2(VRAttribute.floorarray.get(i-1), VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1], VRAttribute.compflag[i-1]);
					}
				}
				b += "	 											}\n";/////[name,name]end
				b += getCS5();
				b += getCS6(VRAttribute.floorarray.get(i-1));
				getCS7(VRAttribute.floorarray.get(i-1), "entrance", "");
				b += getCS8(VRAttribute.floorarray.get(i-1));
			}
		}else{////ビルが複数の時
			b = getCS1();
			for(int i=1; i<=VRAttribute.groupcount1; i++){
				if(i == 1){
					b += "				if(groupflag == " + i + "){\n";
				}else{
					b += "				}else if(groupflag == " + i + "){\n";
				}

				if(i != 1){
					int a = VRAttribute.genrearray22.get(i-1) - VRAttribute.genrearray22.get(i-2);
					if(",".equals(VRAttribute.cjoinarray.get(i-2))){//前のビル　○(結合子)　今のビル　
						if(VRAttribute.floorarray.get(i-2) == 1){
							b +="					billmovex += " + -50*a + ";\n";
						}else if(VRAttribute.floorarray.get(i-2) == 2){
							b +="					billmovex += -50;\n";
						}else{
							b +="					billmovex += -50;\n";
							b +="					billmovez += " + -30*(a-1) + ";\n";
						}
					}else if("!".equals(VRAttribute.cjoinarray.get(i-2))){
						if(VRAttribute.floorarray.get(i-2) == 1){
							b +="					billmovex += " + -50*(a-1) + ";\n";
							b +="					billmovey += 20;\n";
						}else if(VRAttribute.floorarray.get(i-2) == 2){
							b +="					billmovey += " + 20*a + ";\n";
						}else{
							b +="					billmovey += 20;\n";
							b +="					billmovez += " + -30*(a-1) + ";\n";
						}
					}else if("%".equals(VRAttribute.cjoinarray.get(i-2))){
						if(VRAttribute.floorarray.get(i-2) == 1){
							b +="					billmovex += " + -50*(a-1) + ";\n";
							b +="					billmovez += -30;\n";
						}else if(VRAttribute.floorarray.get(i-2) == 2){
							b +="					billmovez += -30;\n";
						}else{
							b +="					billmovez += " + -30*a + ";\n";
						}
					}
				}


				for(int k=VRAttribute.genrearray22.get(i-1); k<VRAttribute.genrearray22.get(i); k++){/////ジャンル出すはじめ
					s += VRAttribute.genrearray2.get(k)+",";
				}
				s = s.substring(0,s.length()-1);/////最後のカンマとる
				if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
					b += getCS2(VRAttribute.exharray.get(VRAttribute.genrearray22.get(i)-1),s);
				}else{
					compgetCS2(VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1] ,VRAttribute.compflag[i-1], s);
				}
				s = "";////////ジャンル出す終わり


				if(VRAttribute.floorarray.get(i-1) == 1){
					 b += "					int objx = 0;/////////////museum change";
					 b += "\n";
				}else if(VRAttribute.floorarray.get(i-1) == 2){
				}else if(VRAttribute.floorarray.get(i-1) == 3){
					b += "					int objz = 0;/////////////museum change";
					b += "\n";
				}
				b += getCS3();
						
				if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
					getCS4_1(VRAttribute.exharray.get(VRAttribute.genrearray22.get(i)-1));
				}else{
					compgetCS4_1(VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1] ,VRAttribute.compflag[i-1]);
				}
				
				String m = VRAttribute.multiexhary.get(i-1);////[name,name]start
				
				String array[] = m.split("");////TFE一個ずつ入ってる
				for(int j=0; j<VRAttribute.multiexhcount.get(i-1);j++){			
					if(j == 0){
						b += "												if(k == " + j + "){\n";
					}else{
						b += "												}else if(k == " + j + "){\n";
						b += "													if(exhmoveflag == " + (j-1) + "){\n";
						if(array[j-1].equals(",")){
							b += "	 													exhmovex -= 1.3f;\n";	
						}else if(array[j-1].equals("!")){
							b += "	 													exhmovey += 2.0f;\n";
						}else if(array[j-1].equals("%")){
							b += "	 													exhmovez -= 1.3f;\n";
						}
						b += "														exhmoveflag++;\n";
						b += "													}\n";
					}
					if(VRAttribute.compx[i-1] == 0 && VRAttribute.compy[i-1] == 0 && VRAttribute.compz[i-1] == 0){
						getCS4_2(VRAttribute.exharray.get(VRAttribute.genrearray22.get(i)-1), VRAttribute.floorarray.get(i-1));
					}else{
						compgetCS4_2(VRAttribute.floorarray.get(i-1), VRAttribute.compx[i-1], VRAttribute.compy[i-1], VRAttribute.compz[i-1], VRAttribute.compflag[i-1]);
					}
				}
				b += "	 											}\n";/////[name,name]end
				
				b += getCS5();
				b += getCS6(VRAttribute.floorarray.get(i-1));
				if(i == 1){
					getCS7(VRAttribute.floorarray.get(i-1), "entrance" ,VRAttribute.cjoinarray.get(i-1));
				}else if((i != VRAttribute.groupcount1) && (i != 1)){
					getCS7(VRAttribute.floorarray.get(i-1), VRAttribute.cjoinarray.get(i-2), VRAttribute.cjoinarray.get(i-1));
				}else if(i == VRAttribute.groupcount1){
					getCS7(VRAttribute.floorarray.get(i-1), VRAttribute.cjoinarray.get(i-2), "exit");
				}
				
				
				b += getCS8(VRAttribute.floorarray.get(i-1));
				if(i != VRAttribute.groupcount1){
					getCS9(VRAttribute.cjoinarray.get(i-1), VRAttribute.floorarray.get(i-1));
				}
				getCS10(i, VRAttribute.floorarray.get(i-1));
								
			}
		}
		b += getCS11();
		createfile(outFileName+".cs", b);
	}

	private static String getCS1(){
		return
"using System;\n"+
//using System.Collections.Generic;
"using System.ComponentModel;\n"+
//using System.Data;
//using System.Drawing;
"using System.Linq;\n"+
"using System.Text;\n"+
//using System.Windows.Forms;
"using System.Xml;\n"+
"using UnityEngine;\n"+
"using System.Collections;\n"+
"\n"+
"public class "+ filename.split("/")[filename.split("/").length - 1] +" : MonoBehaviour {\n"+
"	public Rigidbody rigid;\n"+
"	public Vector3 size = new Vector3(0, 0, 0);\n"+
"	static int billmovex = 0;\n"+
"	static int billmovey = 0;\n"+
"	static int billmovez = 0;\n"+
"	float exhmovex = 0f;\n"+
"	float exhmovey = 0f;\n"+
"	float exhmovez = 0f;\n"+
"	int exhmoveflag = 0;\n"+
"	void Start () {\n"+
"		GameObject[] array = new GameObject[500];\n"+
"		String[] sarray = new String[100];///////////////////テキスト生成\n"+
"		int groupflag = 1;\n"+
"\n"+
"		XmlDocument xmlDocument = new XmlDocument();\n"+
"		xmlDocument.Load(\""+filename+".xml\");\n"+
"		XmlElement elem = xmlDocument.DocumentElement; //elem.Nameはdoc\n"+
	"\n"+
"		if (elem.HasChildNodes == true) {\n"+
"	        XmlNode childNode2 = elem.FirstChild;\n"+

"	        while (childNode2 != null) {\n";

}

	private static String getCS2(int exhflag,String s){
		if(exhflag == 1){
			return
					"\n"+
"					String[] genrearray = {"+ s + "};///////////タイトル表示\n"+

"					int[] xarray = new int[500];\n"+
"					int[] xxarray = new int[100];\n"+
"					int[] zarray = new int[100];\n"+
"					int r;\n"+
"					float objhigh = 2.8f;\n"+
"					float standhigh = 1.25f;\n"+
"					int museumcount = 0;\n"+
"				\n"+
"					for(int n=10,k=0; n >= -10; n = n-5,k++){\n"+
"						zarray[k] = n;\n"+
"					}\n"+

"					for(int m=20, l=0; m >= -20; m = m-5,l++){	\n"+
"						xxarray[l] = m;\n"+
"					}\n"+
"					for(int p=0, q=0; p<500; p++,q++){    	\n"+
"						if(q == 9){\n"+
"							q=0;\n"+
"						}\n"+
"						xarray[p] = xxarray[q];	\n"+
"					}   \n"+
"\n";
		}else if(exhflag == 2){
			return
"\n"+
		"					String[] genrearray = {"+ s + "};///////////タイトル表示\n"+
		"					ArrayList yarray = new ArrayList();\n"+
		"					float objhigh = 2.8f;\n"+
		"					float standhigh = 1.25f;\n"+
		"					int museumcount = 0;\n"+
		"					\n"+
		"					for(int n=0; n<500; n++){\n"+
		"						yarray.Add(n*2);\n"+
		"					}\n"+
		"\n";			
		}else if(exhflag == 3){
			return
					"\n"+
"					String[] genrearray = {"+ s + "};///////////タイトル表示\n"+
"					int[] xarray = new int[100];\n"+
"					int[] zarray = new int[500];\n"+
"					int[] zzarray = new int[100];\n"+
"					int r;\n"+
"					float objhigh = 2.8f;\n"+
"					float standhigh = 1.25f;\n"+
"					int museumcount = 0;\n"+
"					\n"+

"					for(int n=20,k=0; n >= -20; n = n-5,k++){\n"+
"						xarray[k] = n;\n"+
"					}\n"+

"					for(int m=10, l=0; m >= -10; m = m-5,l++){\n"+
"						zzarray[l] = m;\n"+
"					}\n"+
"					for(int p=0, q=0; p<500; p++,q++){    	\n"+
"						if(q == 5){\n"+
"							q=0;\n"+
"						}\n"+
"						zarray[p] = zzarray[q];	\n"+
"					}\n"+
"\n";
		}else{
			return "";
		}
	}
	
	private static void compgetCS2(int compx, int compy, int compz ,int compflag, String s){
			b +="					String[] genrearray = {"+ s + "};///////////タイトル表示\n";
			b +="					int r;\n";
			b +="					float objhigh = 2.8f;\n";
			b +="					float standhigh = 1.25f;\n";
			b +="					int museumcount = 0;\n";
			b +="					\n";
			
		if(compx == -1 || compy == -1 || compz == -1){////,5%系、CodeGeneratorの714行目で-1の印つけてる
			if(compx == -1){//2番目
				b +="					int[] xarray = new int[100];\n";
				b +="					for(int n=20,k=0; n >= -20; n -= 5,k++){\n";
				b +="						xarray[k] = n;\n";
				b +="					}\n";
			}else if(compy == -1){
				b +="					int[] yarray = new int[100];\n";
				b +="					for(int n=0,k=0; k<100; n += 2, k++){\n";
				b +="						yarray[k] = n;\n";
				b +="					}\n";
			}else if(compz == -1){
				b +="					int[] zarray = new int[100];\n";
				b +="					for(int n=10,k=0; n >= -10; n -= 5,k++){\n";
				b +="						zarray[k] = n;\n";
				b +="					}\n";
			}
			
			if(compflag == 1){//1番目
				b +="					int[] xarray = new int[500];\n";
				b +="					int[] xxarray = new int[100];\n";
				b +="					for(int m=20, l=0; l<"+ compx +"; m = m-5,l++){\n";
				b +="						xxarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){ \n";
				b +="						if(q == "+ compx +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						xarray[p] = xxarray[q];	\n";
				b +="					} \n";
			}else if(compflag == 2){
				b +="					int[] yarray = new int[500];\n";
				b +="					int[] yyarray = new int[100];\n";
				b +="					for(int m=0, l=0; l<"+ compy +"; m += 2,l++){\n";
				b +="						yyarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){ \n";
				b +="						if(q == "+ compy +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						yarray[p] = yyarray[q];	\n";
				b +="					} \n";
			}else if(compflag == 3){
				b +="					int[] zarray = new int[500];\n";
				b +="					int[] zzarray = new int[100];\n";
				b +="					for(int m=10, l=0; l<"+ compz +"; m -= 5,l++){\n";
				b +="						zzarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){ \n";
				b +="						if(q == "+ compz +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						zarray[p] = zzarray[q];	\n";
				b +="					}   \n";
			}
			
			if(compx == 0){///無いTFEについて
				b +="					int[] xarray = new int[500];\n";
				b +="					for(int k=0; k<500; k++){\n";
				b +="						xarray[k] = 20;\n";
				b +="					}\n";
			}else if(compy == 0){
				b +="					int[] yarray = new int[500];\n";
				b +="					for(int k=0; k<500; k++){\n";
				b +="						yarray[k] = 0;\n";
				b +="					}\n";
			}else if(compz == 0){
				b +="					int[] zarray = new int[500];\n";
				b +="					for(int k=0; k<500; k++){\n";
				b +="						zarray[k] = 10;\n";
				b +="					}\n";
			}
			
			
		}else{///,5%4!系
			b +="					int[] xarray = new int[500];\n";
			b +="					int[] xxarray = new int[100];\n";
			b +="					int[] zarray = new int[500];\n";
			b +="					int[] zzarray = new int[100];\n";
			b +="					int[] yarray = new int[500];\n";
			b +="					int[] yyarray = new int[100];\n";
			
			if(compflag == 1){///1番目の記号
				b +="					for(int m=20, l=0; l<"+ compx +"; m = m-5,l++){	\n";
				b +="						xxarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){  \n";
				b +="						if(q == "+ compx +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						xarray[p] = xxarray[q];	\n";
				b +="					}\n";
			}else if(compflag == 2){
				b +="					for(int m=0, l=0; l<"+ compy +"; m += 2, l++){\n";
				b +="						yyarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){\n";
				b +="						if(q == "+ compy +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						yarray[p] = yyarray[q];	\n";
				b +="					}\n";
			}else if(compflag == 3){
				b +="					for(int m=10, l=0; l<"+ compz +"; m = m-5,l++){	\n";
				b +="						zzarray[l] = m;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){  \n";
				b +="						if(q == "+ compz +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						zarray[p] = zzarray[q];	\n";
				b +="					}\n";
			}
			
			if(compx != 0 && compflag != 1){//2番目
				b +="					for(int n=20,k=0; k<"+ compx +"; n = n-5,k++){\n";
				b +="						xxarray[k] = n;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){ \n";
				b +="						if(q == "+ compx +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						xarray[p] = xxarray[q];	\n";
				b +="					}\n";
			}else if(compy != 0 && compflag != 2){
				b +="					for(int n=0,k=0; k<"+ compy +"; n += 2,k++){\n";
				b +="						yyarray[k] = n;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){  \n";
				b +="						if(q == "+ compy +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						yarray[p] = yyarray[q];	\n";
				b +="					}\n";
			}else if(compz != 0 && compflag != 3){
				b +="					for(int n=10,k=0; k<"+ compz +"; n = n-5,k++){\n";
				b +="						zzarray[k] = n;\n";
				b +="					}\n";
				b +="					for(int p=0, q=0; p<500; p++,q++){ \n";
				b +="						if(q == "+ compz +"){\n";
				b +="							q=0;\n";
				b +="						}\n";
				b +="						zarray[p] = zzarray[q];	\n";
				b +="					}\n";
			}
			
			if(compx == 0){//3番目
				b +="					for(int m=20, l=0; l<9; m -= 5,l++){	\n";
				b +="						xxarray[l] = m; 	\n";
				b +="					}	\n";
				b +="					for(int p=0,q=0; p<500; p++){ \n";
				b +="						if((p!=0) && (p%("+ compy +"*"+ compz +")==0)){\n";
				b +="							q++;\n";
				b +="						}\n";
				b +="						xarray[p] = xxarray[q];	\n";
				b +="					}\n";
			}else if(compy == 0){
				b +="					for(int m=0, l=0; l<10; m += 2, l++){\n";
				b +="						yyarray[l] = m; \n";
				b +="					}\n";
				b +="					for(int p=0,q=0; p<500; p++){ \n";
				b +="						if((p!=0) && (p%("+ compx +"*"+ compz +")==0)){\n";
				b +="							q++;\n";
				b +="						}\n";
				b +="						yarray[p] = yyarray[q];	\n";
				b +="					}\n";
			}else if(compz == 0){
				b +="					for(int m=10, l=0; l<5; m -= 5,l++){	\n";
				b +="						zzarray[l] = m; 	\n";
				b +="					}	\n";
				b +="					for(int p=0,q=0; p<500; p++){ \n";
				b +="						if((p!=0) && (p%("+ compx +"*"+ compy +")==0)){\n";
				b +="							q++;\n";
				b +="						}\n";
				b +="						zarray[p] = zzarray[q];	\n";
				b +="					}\n";
			}
			
		}
		
	}
	


	private static String getCS3(){
		return
"		        	if(childNode2.HasChildNodes == true){\n"+
"	        			XmlNode childNode = childNode2.FirstChild; ////////category\n"+
"						///////////group追加 end\n"+
"				        while (childNode != null) { //childNode.Nameはcategory\n"+
"				        	museumcount++;\n"+

"				          	if (childNode.HasChildNodes == true) {\n"+
"					            for (int i=0; i < childNode.ChildNodes.Count; i++) {\n"+
"					              	XmlNode dataNode2= childNode.ChildNodes[i]; //dataNode.NameはShapeというか二種類目のcategory\n"+
"\n"+
"									if (dataNode2.HasChildNodes == true) {//////////n2 change\n"+
"									    for (int k=0; k < dataNode2.ChildNodes.Count; k++) {\n"+
"									      	XmlNode dataNode= dataNode2.ChildNodes[k]; \n"+
"\n"+
"						     		       for (int j=0; j < dataNode.ChildNodes.Count; j++) {     /////element\n"+
"					                			XmlNode xmlAttr = dataNode.ChildNodes[j]; //xmlAttrはkindCubekind  \n"+        
"												//array[j] = Instantiate(Resources.Load(xmlAttr.InnerText)) as GameObject;///////bill change tatsu:code change\n"+
"												sarray[j] = xmlAttr.InnerText;//オブジェクトのテキスト生成のため\n"+
"												array[j] = CreateObject.MakeObject((XmlElement)xmlAttr);\n";
	}

	private static void getCS4_1(int exhflag){
		if(exhflag == 1){
b+="												r = j/9;\n";
b+="												if(j == 0){ //0を割ることはできないから \n";
b+="													r = 0; \n";
b+="												} \n";
b+="						\n";
		}else if(exhflag == 3){
b+="												r = j/5; \n";
b+="												if(j == 0){ //0を割ることはできないから \n";
b+="													r = 0; \n";
b+="												} \n";
b+="					\n";		
		}
	}
	
	private static void compgetCS4_1(int compx, int compy, int compz, int compflag){
		if(compflag == 1){
			b+="												r = j/"+ compx +";\n";
		}else if(compflag == 2){
			b+="												r = j/"+ compy +";\n";
		}else if(compflag == 3){
			b+="												r = j/"+ compz +";\n";
		}
		b+="												if(j == 0){ //0を割ることはできないから \n";
		b+="													r = 0; \n";
		b+="												} \n";
		b+="						\n";
	}
		
		private static void getCS4_2(int exhflag, int floorflag){
		if(exhflag == 1){
			if(floorflag == 1){
				b+="													array[j].transform.position  = new Vector3 (xarray[j]+objx, objhigh, zarray[r]);\n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[j]+objx, standhigh, zarray[r]);  \n";
				b+="													\n";
				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f+objx, standhigh+1.4f, zarray[r]+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f);\n ";
			}else if(floorflag ==2){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh, zarray[r]); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[r]);  \n";
				b+="													\n";
				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f, zarray[r]+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f);\n ";

			}else if(floorflag == 3){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh, zarray[r]+objz); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[r]+objz); \n";
//				b+="													\n";
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f, zarray[r]+objz+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f);\n ";
			}
	
		}else if(exhflag == 2){
			if(floorflag == 1){
				b+="													array[j].transform.position  = new Vector3 (objx, objhigh+(int)yarray[j], 0); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(objx, standhigh, 0);  \n";
//				b+="			\n";	
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(0.5f+objx, standhigh+1.4f+(int)yarray[j], 0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";
			}else if(floorflag == 2){
				b+="													array[j].transform.position  = new Vector3 (0, objhigh+(int)yarray[j], 0); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(0, standhigh, 0);  \n";
				b+="			\n";	
				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(0.5f, standhigh+1.4f+(int)yarray[j], 0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";

			}else if(floorflag == 3){
				b+="													array[j].transform.position  = new Vector3 (0, objhigh+(int)yarray[j], objz); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(0, standhigh, objz);  \n";
//				b+="			\n";	
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(0.5f, standhigh+1.4f+(int)yarray[j], objz+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";
			}
		}else if(exhflag == 3){
			if(floorflag == 1){
				b+="													array[j].transform.position  = new Vector3 (xarray[r]+objx, objhigh, zarray[j]); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[r]+objx, standhigh, zarray[j]);  \n";
//				b+="			\n";	
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f+objx, standhigh+1.4f, zarray[j]+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";
			}else if(floorflag == 2){
				b+="													array[j].transform.position  = new Vector3 (xarray[r], objhigh, zarray[j]); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[r], standhigh, zarray[j]);  \n";
//				b+="			\n";	
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f, standhigh+1.4f, zarray[j]+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";

			}else if(floorflag == 3){
				b+="													array[j].transform.position  = new Vector3 (xarray[r], objhigh, zarray[j]+objz); \n";
				b+="\n";	
				b+="													//stand生成 \n";
//				b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//				b+="													stand.transform.position= new Vector3(xarray[r], standhigh, zarray[j]+objz);  \n";
//				b+="			\n";	
//				b+="													//オブジェクトのテキスト生成 \n";
//				b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//				b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//				b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//				b+="													messageText.transform.Rotate(0,180,0); \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f, standhigh+1.4f, zarray[j]+objz+0.7f);  \n";
//				b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";
			}
		}	
		b += "\n";
		b+="													array[j].transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
//		b+="													stand.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
//		b+="													messageText.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
		b+="													array[j].transform.position  += new Vector3 (exhmovex, exhmovey, exhmovez);\n";
//		b+="													stand.transform.position  += new Vector3 (exhmovex, 0, exhmovez); \n";
//		b+="													messageText.transform.position  += new Vector3 (exhmovex, exhmovey, exhmovez); 	\n";
	}
		
	private static void compgetCS4_2(int floorflag, int compx, int compy, int compz, int compflag){//2番目のTFEとfloorで場合分け	
		b+="													//stand生成 \n";
//		b+="													GameObject stand = Instantiate(Resources.Load(\""+template_stand+"/Stand\")) as GameObject; \n";
//		b+="			\n";	
//		b+="													//オブジェクトのテキスト生成 \n";
//		b+="													GameObject  messageText = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//		b+="													messageText.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify \n";
//		b+="													messageText.GetComponent<TextMesh>().text = sarray[j].ToString(); \n";
//		b+="													messageText.transform.Rotate(0,180,0); \n";
//		b+="													messageText.transform.localScale = new Vector3(0.22f, 0.22f, 0.22f); \n";
		b+="\n";
		if(floorflag == 1){
			if(compx != 0 && compflag != 1){//2番目
				b+="													array[j].transform.position  = new Vector3 (xarray[r]+objx, objhigh+yarray[j], zarray[j]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[r]+objx, standhigh, zarray[j]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f+objx, standhigh+1.4f+yarray[j], zarray[j]+0.7f);  \n";
			}else if(compy != 0 && compflag != 2){
				b+="													array[j].transform.position  = new Vector3 (xarray[j]+objx, objhigh+yarray[r], zarray[j]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j]+objx, standhigh, zarray[j]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f+objx, standhigh+1.4f+yarray[r], zarray[j]+0.7f);  \n";
			}else if(compz != 0 && compflag != 3){
				b+="													array[j].transform.position  = new Vector3 (xarray[j]+objx, objhigh+yarray[j], zarray[r]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j]+objx, standhigh, zarray[r]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f+objx, standhigh+1.4f+yarray[j], zarray[r]+0.7f);  \n";
			}
		}else if(floorflag == 2){
			if(compx != 0 && compflag != 1){//2番目
				b+="													array[j].transform.position  = new Vector3 (xarray[r], objhigh+yarray[j], zarray[j]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[r], standhigh, zarray[j]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f, standhigh+1.4f+yarray[j], zarray[j]+0.7f);  \n";
			}else if(compy != 0 && compflag != 2){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh+yarray[r], zarray[j]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[j]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f+yarray[r], zarray[j]+0.7f);  \n";
			}else if(compz != 0 && compflag != 3){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh+yarray[j], zarray[r]); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[r]);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f+yarray[j], zarray[r]+0.7f);  \n";
			}
		}else if(floorflag == 3){
			if(compx != 0 && compflag != 1){//2番目
				b+="													array[j].transform.position  = new Vector3 (xarray[r], objhigh+yarray[j], zarray[j]+objz); \n";
//				b+="													stand.transform.position= new Vector3(xarray[r], standhigh, zarray[j]+objz);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[r]+0.5f, standhigh+1.4f+yarray[j], zarray[j]+objz+0.7f);  \n";
			}else if(compy != 0 && compflag != 2){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh+yarray[r], zarray[j]+objz); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[j]+objz);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f+yarray[r], zarray[j]+objz+0.7f);  \n";
			}else if(compz != 0 && compflag != 3){
				b+="													array[j].transform.position  = new Vector3 (xarray[j], objhigh+yarray[j], zarray[r]+objz); \n";
//				b+="													stand.transform.position= new Vector3(xarray[j], standhigh, zarray[r]+objz);  \n";
//				b+="													messageText.transform.position= new Vector3(xarray[j]+0.5f, standhigh+1.4f+yarray[j], zarray[r]+objz+0.7f);  \n";
			}
		}	
		b += "\n";
		b+="													array[j].transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
//		b+="													stand.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
//		b+="													messageText.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
		b+="													array[j].transform.position  += new Vector3 (exhmovex, exhmovey, exhmovez);\n";
//		b+="													stand.transform.position  += new Vector3 (exhmovex, 0, exhmovez); \n";
//		b+="													messageText.transform.position  += new Vector3 (exhmovex, exhmovey, exhmovez); 	\n";
	}
	

	private static String getCS5(){
		return
				"\n"+
"												size = Get(array[j]);\n"+
"												array[j].AddComponent<Rigidbody>();\n"+
"												rigid = array[j].GetComponent<Rigidbody>();\n"+
"												if (rigid) {\n"+
"												     rigid.constraints = RigidbodyConstraints.FreezeAll;\n"+
"												}\n"+
"												array[j].tag = \"GameController\";\n"+
"												array[j].AddComponent<BoxCollider>();\n"+
"\n"+
"												float nx = size.x;\n"+
"												float ny = size.y;\n"+
"												float nz = size.z;\n"+
"												\n"+
"												float max = nx;\n"+
"												if(max < ny){\n"+
"													max = ny;\n"+
"												}\n"+
"												if(max < nz){\n"+
"													max = nz;\n"+
"												}\n"+
"												float rate = 1.2f / max;\n"+
"												float mx = array[j].transform.localScale.x;\n"+
"												float my = array[j].transform.localScale.y;\n"+
"												float mz = array[j].transform.localScale.z;\n"+
"												array[j].transform.localScale = new Vector3(mx*rate, my*rate, mz*rate); \n"+

"											} \n"+
"										} \n"+
"									} \n"+
"								} \n"+
"							} \n"+
"							childNode = childNode.NextSibling; \n"+
"							exhmovex = 0f;\n"+
"							exhmovey = 0f;\n"+
"							exhmovez = 0f;\n"+
"							exhmoveflag = 0;\n";
	}
	private static String getCS6(int floorflag){

				if(floorflag == 1){
			return
"							objx += -50;/////////////////////////////museumchange\n"+
"						}	\n"+
"					}\n";
		}else if(floorflag ==2){
			return
"							objhigh += 20.0f;\n"+
"	     			       	standhigh += 20.0f;\n"+
"	     			   }\n"+
"					}\n";
		}else if(floorflag == 3){
			return
"							objz += -30;/////////////////////////////museumchange\n"+
"						}	\n"+
"					}\n";
		}else{
			return "";
		}
	}
	


	private static void getCS7(int floorflag, String prejoin, String afterjoin){
			if(floorflag == 1){
b  += "					//museum生成\n";
b  += "					for(int i=0; i<museumcount; i++){	\n";
//b  += "						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/DoorMuseum\")) as GameObject;\n";
//b  += "						museum.transform.position= new Vector3(-50*i, 0, 0);\n" ;
//b  += "						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  += "					}\n";				


				if(",".equals(prejoin)){
				}else{
b  +=	"			 		for(int i=0; i<1; i++){	\n";
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(25, 10, 0);\n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"					}\n";
				}
			
				
b  +=	"					for(int i=0; i<museumcount; i++){	\n";
				if("%".equals(prejoin) || "entrance".equals(prejoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(-50*i,10, 15);\n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if("%".equals(prejoin) || "entrance".equals(prejoin)){b += "					}\n";}
b  +=	"					}\n";



b  +=	"					for(int i=0; i<museumcount; i++){	\n";
				if("%".equals(afterjoin) || "exit".equals(afterjoin)){b += "					if(i < museumcount-1){\n";}
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(-50*i,10, -15);\n";  
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if("%".equals(afterjoin) || "exit".equals(afterjoin)){b += "					}\n";}
b  +=	"					}\n";



				if(",".equals(afterjoin)){					
				}else{
b  +=	"					for(int i=0; i<1; i++){	\n";
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(25-(museumcount)*50,10, 0);\n" ;
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"					}\n";
				}

				
				
			}else if(floorflag == 2){
b  +=	"					//museum生成\n";
b  +=	"					for(int i=0; i<museumcount; i++){	\n";
//b  +=	"						GameObject museum3= Instantiate(Resources.Load(\""+ template_museum +"/DoorMuseum\")) as GameObject;\n";
//b  +=	"						museum3.transform.position = new Vector3(0, 20*i, 0); \n";
//b  +=	"						museum3.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"\n";

				if("%".equals(prejoin) || "entrance".equals(prejoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum1= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum1.transform.position = new Vector3(0, 10+20*i, 15);\n";
//b  +=	"						museum1.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if("%".equals(prejoin) || "entrance".equals(prejoin)){b += "					}\n";}
b  +=	"\n";


				if("exit".equals(afterjoin)){b += "					if(i < museumcount-1){\n";}
				if("%".equals(afterjoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum2= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum2.transform.position = new Vector3(0, 10+20*i, -15); \n";
//b  +=	"						museum2.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if("%".equals(afterjoin) || "exit".equals(afterjoin)){b += "					}\n";}
b  +=	"\n";


				if(",".equals(prejoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum4= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum4.transform.position = new Vector3(25, 10+20*i, 0); \n";
//b  +=	"						museum4.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if(",".equals(prejoin)){b += "					}\n";}
b  +=	"\n";


				if(",".equals(afterjoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum5= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum5.transform.position = new Vector3(-25, 10+20*i, 0); \n";
//b  +=	"						museum5.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if(",".equals(afterjoin)){b += "					}\n";}
b  +=	"\n";



			}else if(floorflag == 3){
b  +=	"					//museum生成  \n";
b  +=	"					for(int i=0; i<museumcount; i++){	\n";
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/DoorMuseum\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(0, 0, -30*i); \n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"					}\n";


				if("%".equals(prejoin) || "entrance".equals(prejoin)){
				}else{
b  +=	"					for(int i=0; i<1; i++){	\n";
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(0, 10, 15); \n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"					}\n";
				}
				
				
b  +=	"					for(int i=0; i<museumcount; i++){	\n";
				if(",".equals(prejoin)){b += "					if(i>=1){\n";}
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(25, 10, -30*i); \n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if(",".equals(prejoin)){b += "					}\n";}
b  +=	"					}\n";



b  +=	"					for(int i=0; i<museumcount; i++){	\n";
				if(",".equals(afterjoin)){b += "					if(i < museumcount-1){\n";}
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallx\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(-25, 10, -30*i); \n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
				if(",".equals(afterjoin)){b += "					}\n";}
b  +=	"					}		\n";


				if("%".equals(afterjoin)|| "exit".equals(afterjoin)){
				}else{
b  +=	"					for(int i=0; i<1; i++){	\n";
//b  +=	"						GameObject museum= Instantiate(Resources.Load(\""+ template_museum +"/Wallz\")) as GameObject;\n";
//b  +=	"						museum.transform.position= new Vector3(0, 10, 15-30*museumcount); //本当は15-30*(museumcount-1)\n";
//b  +=	"						museum.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n";
b  +=	"					}\n";
				}
			}
	}
	
	private static String getCS8(int floorflag){
		if(floorflag == 1){
			return
"					//タイトル生成とビル内案内矢印\n"+
"					for(int i=0; i<museumcount; i++){\n"+
"						if(i < museumcount-1){\n"+
//"							GameObject Arrow= Instantiate(Resources.Load(\"Prefab/BlueArrow\")) as GameObject;\n"+
//"							Arrow.transform.position = new Vector3(-20-50*i, 12, -10);\n"+
//"							Arrow.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
//"							Arrow.transform.localScale = new Vector3(2.5f, 1.5f, 2.5f);\n"+
//"							Arrow.transform.Rotate(0,180,0); \n"+
"						}\n"+
"\n"+
//"						GameObject  messageText1 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n"+
//"						messageText1.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); \n"+
//"						messageText1.GetComponent<TextMesh>().text = genrearray[i].ToString(); \n"+
//"						messageText1.transform.Rotate(0,180,0); \n"+
//"						messageText1.transform.position= new Vector3(20-50*i, 16, -13);\n"+
//"						messageText1.transform.localScale = new Vector3(2f, 2f, 2f); \n"+
//"						messageText1.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
"					}	\n"+
"					childNode2 = childNode2.NextSibling;\n";
		}else if(floorflag == 2){
			return
"						//ビル内案内arrow\n"+
"						if(i < museumcount-1){\n"+
//"							GameObject Arrow= Instantiate(Resources.Load(\"Prefab/BlueArrow\")) as GameObject;\n"+
//"							Arrow.transform.position = new Vector3(-20, 12+20*i, -10);\n"+
//"							Arrow.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
//"							Arrow.transform.localScale = new Vector3(2.5f, 1.5f, 2.5f);\n"+
//"							Arrow.transform.Rotate(0,0,90); \n"+
"						}\n"+
"					//タイトル生成\n"+
//"						GameObject  messageText1 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n"+
//"						messageText1.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); \n"+
//"						messageText1.GetComponent<TextMesh>().text = genrearray[i].ToString(); \n"+
//"						messageText1.transform.Rotate(0,180,0); \n"+
//"						messageText1.transform.position= new Vector3(20, 16+20*i, -13);\n"+
//"						messageText1.transform.localScale = new Vector3(2f, 2f, 2f); 		\n"+
//"						messageText1.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
"					}\n"+
"					childNode2 = childNode2.NextSibling;\n";

		}else if(floorflag == 3){
			return
"					//タイトル生成とビル内案内矢印\n"+
"					for(int i=0; i<museumcount; i++){\n"+
"						if(i < museumcount-1){\n"+
//"							GameObject Arrow= Instantiate(Resources.Load(\"Prefab/BlueArrow\")) as GameObject;\n"+
//"							Arrow.transform.position = new Vector3(-20, 12, -10-30*i); \n"+
//"							Arrow.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
//"							Arrow.transform.localScale = new Vector3(2.5f, 1.5f, 2.5f);\n"+
//"							Arrow.transform.Rotate(0,90,0); \n"+
"						}\n"+
"\n"+
//"						GameObject  messageText1 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n"+
//"						messageText1.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" );  \n"+
//"						messageText1.GetComponent<TextMesh>().text = genrearray[i].ToString(); \n"+
//"						messageText1.transform.Rotate(0,180,0); \n"+
//"						messageText1.transform.position= new Vector3(20, 16, -13-30*i);\n"+
//"						messageText1.transform.localScale = new Vector3(2f, 2f, 2f); \n"+
//"						messageText1.transform.position  += new Vector3 (billmovex, billmovey, billmovez); \n"+
"					}					 \n"+
"					childNode2 = childNode2.NextSibling;\n";

		}else{
			return "";
		}
}

	private static void getCS9(String afterjoin, int floorflag){//To next build arrow
		b +="					//To next bulid arrow \n";
//		b +="					GameObject Arrow1= Instantiate(Resources.Load(\"Prefab/RedArrow\")) as GameObject;\n";
//		b +="					Arrow1.transform.position  = new Vector3 (billmovex, billmovey, billmovez); \n";
//		b +="					Arrow1.transform.localScale = new Vector3(2.5f, 1.5f, 2.5f);\n";
		if(",".equals(afterjoin)){			
//			b +="					Arrow1.transform.Rotate(0,180,0);\n";
		}else if("!".equals(afterjoin)){	
//			b +="					Arrow1.transform.Rotate(0,0,90);\n";				
		}else if("%".equals(afterjoin)){
//			b +="					Arrow1.transform.Rotate(0,90,0);\n";			
		}
		
		if(floorflag == 1){
//			b +="					Arrow1.transform.position += new Vector3(-20-50*(museumcount-1), 12, -10);\n";							
		}else if(floorflag == 2){
			if(",".equals(afterjoin) || "%".equals(afterjoin)){
//				b +="					Arrow1.transform.position += new Vector3(-20, 12, -10);\n";		
			}else if("!".equals(afterjoin)){
//				b +="					Arrow1.transform.position += new Vector3(-20, 12+20*(museumcount-1), -10);\n";	
			}
		}else if(floorflag == 3){
//			b +="					Arrow1.transform.position += new Vector3(-20, 12, -10-30*(museumcount-1));\n";	
		}
		
	}
	
	private static void  getCS10(int i, int floorflag){//entranceとexitの文字
				if(i==1){
b +="					//entrance change \n";
//b +="					GameObject  messageText2 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject; \n";
//b +="					messageText2.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify  \n";
//b +="					messageText2.GetComponent<TextMesh>().text = \"Entrance\";  \n";
//b +="					messageText2.GetComponent<Renderer>().material.color = Color.green;		 \n";		
//b +="					messageText2.transform.position= new Vector3(-4, 13, 14.5f);	 \n";
//b +="					messageText2.transform.localScale = new Vector3(1.5f, 1.5f, 1.5f);  \n";
				}else if(i == VRAttribute.groupcount1){ 
b +="					///Exit change \n";
//b +="					GameObject  messageText2 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject;  \n";
//b +="					messageText2.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify  \n";
//b +="					messageText2.GetComponent<TextMesh>().text = \"Exit\";  \n";
//b +="					messageText2.GetComponent<Renderer>().material.color = Color.green;		 \n";
//b +="					messageText2.transform.Rotate(0,180,0);  \n";
					if(floorflag == 1){
//b +="					messageText2.transform.position= new Vector3(billmovex+1.7f-50*(museumcount-1), 13+billmovey, -14.5f+billmovez); \n";
					}else if(floorflag == 2){
//b +="					messageText2.transform.position= new Vector3(billmovex+1.7f, 13+billmovey+(museumcount-1)*20, -14.5f+billmovez); \n";	
					}else if(floorflag == 3){
//b +="					messageText2.transform.position= new Vector3(billmovex+1.7f, 13+billmovey, -14.5f+billmovez-30*(museumcount-1)); \n";	
					}
//b +="					messageText2.transform.localScale = new Vector3(1.5f, 1.5f, 1.5f);  \n";
b +="\n";
//b +="					GameObject  messageText3 = Instantiate(Resources.Load(\"Prefab/TextPrefab\")) as GameObject;  \n";
//b +="					messageText3.GetComponent<Renderer>().material.shader = Shader.Find( \"shaderZOn\" ); //title modify  \n";
//b +="					messageText3.GetComponent<Renderer>().material.color = Color.green;		 \n";
//b +="					messageText3.transform.Rotate(0,180,0); 		 \n";
					if(floorflag ==1){
b +="					if(billmovey/20+1 > 1){\n";
//b +="						messageText3.GetComponent<TextMesh>().text = \"ここは\"+ (billmovey/20+1) +\"階です。地上へ降りる\\nには左コントローラーの\\nグリップを\"+ (billmovey/20) +\"回押してください。\";  \n";
//b +="						messageText3.transform.position= new Vector3(billmovex-5-50*(museumcount-1), 11+billmovey, -14.5f+billmovez);	 \n";
b +="					}else{}\n";
					}else if(floorflag == 2){
b +="					if(billmovey/20+museumcount > 1){\n";
//b +="						messageText3.GetComponent<TextMesh>().text = \"ここは\"+ (billmovey/20+museumcount) +\"階です。地上へ降りる\\nには左コントローラーの\\nグリップを\"+ (billmovey/20+museumcount-1) +\"回押してください。\";  \n";
//b +="						messageText3.transform.position= new Vector3(billmovex-5, 11+billmovey+(museumcount-1)*20, -14.5f+billmovez);	 \n";
b +="					}else{}\n";
					}else if(floorflag == 3){
b +="					if(billmovey/20+1 > 1){\n";
//b +="						messageText3.GetComponent<TextMesh>().text = \"ここは\"+ (billmovey/20+1) +\"階です。地上へ降りる\\nには左コントローラーの\\nグリップを\"+ (billmovey/20) +\"回押してください。\";  \n";
//b +="						messageText3.transform.position= new Vector3(billmovex-5, 11+billmovey, -14.5f+billmovez-30*(museumcount-1));	 \n";
b +="					}else{}\n";					
					}
				}
	}
	
	private static String getCS11(){
		return
				
"				}\n"+
"				groupflag++;\n"+
"			}/////////group追加end\n"+
"		}\n"+
"	}\n"+
"\n"+
"	Vector3 Get(GameObject gameObject)\n"+
"        {\n"+
"            if(gameObject.GetComponent<Renderer>()){\n"+
"               return gameObject.GetComponent<Renderer>().bounds.size;\n"+
"            } else if(gameObject.GetComponent<Collider>()){\n"+
"               return gameObject.GetComponent<Collider>().bounds.size;\n"+
"            } else if(gameObject.GetComponent<Mesh>()){\n"+
"               return gameObject.GetComponent<Mesh>().bounds.size;\n"+
"            }\n"+
"        \n"+
"            if(gameObject.transform.childCount == 1){\n"+
"                return Get(gameObject.transform.GetChild(0).gameObject);\n"+
"            } else if(gameObject.transform.childCount == 0){\n"+
"            	return new Vector3(0,0,0);\n"+
"            } else {\n"+
"                return(new Vector3(GetSizeXParent(gameObject),GetSizeYParent(gameObject),GetSizeZParent(gameObject)));\n"+
"            }\n"+
"        }\n"+
"\n"+
"    float GetSizeXParent(GameObject gameObjectParent){\n"+
"        //GameObject[] childrenGameObjects = gameObjectTemp.\n"+
"            GameObject firstGameObject = null, lastGameObject = null;\n"+
"            firstGameObject = gameObjectParent.transform.GetChild(0).gameObject ;\n"+
"            lastGameObject = gameObjectParent.transform.GetChild(1).gameObject;\n"+
"            float sizeX = 0;\n"+
"            foreach (Transform child in gameObjectParent.transform)\n"+
"            {\n"+
"                if (child.transform.position.x < firstGameObject.transform.position.x)\n"+
"                {\n"+
"                    firstGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"\n"+ 
"                if (child.transform.position.x > lastGameObject.transform.position.x)\n"+
"                {\n"+
"                    lastGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"            }\n"+
"            \n"+
"            if ((firstGameObject != null) && (lastGameObject != null) && (firstGameObject != lastGameObject))\n"+
"            {\n"+
"                sizeX = (lastGameObject.transform.position.x - firstGameObject.transform.position.x) + Get(lastGameObject).x / 2 + Get(firstGameObject).x / 2;\n"+
"            }\n"+
"            \n"+
"            return sizeX;\n"+
"    }\n"+
"    \n"+
"    float GetSizeYParent(GameObject gameObjectParent){\n"+
"       //GameObject[] childrenGameObjects = gameObjectTemp.\n"+
"            GameObject firstGameObject = null, lastGameObject = null;\n"+
"            firstGameObject = gameObjectParent.transform.GetChild(0).gameObject ;\n"+
"            lastGameObject = gameObjectParent.transform.GetChild(1).gameObject;\n"+
"            float sizeY = 0;\n"+
"            foreach (Transform child in gameObjectParent.transform)\n"+
"            {\n"+
"                if (child.transform.position.y < firstGameObject.transform.position.y)\n"+
"                {\n"+
"                    firstGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"                \n"+
"                if (child.transform.position.y > lastGameObject.transform.position.y)\n"+
"                {\n"+
"                    lastGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"            }\n"+
"            \n"+
"            if ((firstGameObject != null) && (lastGameObject != null) && (firstGameObject != lastGameObject))\n"+
"            {\n"+
"                sizeY = (lastGameObject.transform.position.y - firstGameObject.transform.position.y) + Get(lastGameObject).y / 2 + Get(firstGameObject).y / 2;\n"+
"            }\n"+
"            \n"+
"            return sizeY;\n"+
"    }\n"+
"    \n"+
"    float GetSizeZParent(GameObject gameObjectParent){\n"+
"        //GameObject[] childrenGameObjects = gameObjectTemp.\n"+
"            GameObject firstGameObject = null, lastGameObject = null;\n"+
"            firstGameObject = gameObjectParent.transform.GetChild(0).gameObject ;\n"+
"            lastGameObject = gameObjectParent.transform.GetChild(1).gameObject;\n"+
"            float sizeY = 0;\n"+
"            foreach (Transform child in gameObjectParent.transform)\n"+
"            {\n"+
"                if (child.transform.position.z < firstGameObject.transform.position.z)\n"+
"                {\n"+
"                    firstGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"\n"+     
"                if (child.transform.position.z > lastGameObject.transform.position.z)\n"+
"                {\n"+
"                    lastGameObject = child.gameObject;\n"+
"                    continue;\n"+
"                }\n"+
"            }\n"+
"            \n"+
"            if ((firstGameObject != null) && (lastGameObject != null) && (firstGameObject != lastGameObject))\n"+
"            {\n"+
"                sizeY = (lastGameObject.transform.position.z - firstGameObject.transform.position.z) + Get(lastGameObject).z / 2 + Get(firstGameObject).z / 2;\n"+
"            }\n"+
"            \n"+
"            return sizeY;\n"+
"    }\n"+
"}\n";
	}





	/////////////////こっから先はディレクトリ生成について　今はまだ使ってない
	private static void createfile(String fn, String content){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(
							fn), "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		pw.println(content);
		pw.close();
	}


	//directoryCopy
	private static Boolean directoryCopy(File fromDir, File toDir) {
		File[] fromFile = fromDir.listFiles();
		toDir = new File(toDir.getPath() + fs + fromDir.getName());

		toDir.mkdir();

		if (fromFile != null) {
			for (File f : fromFile) {
				if (f.isFile()) {
					if (!fileCopy(f, toDir)) {
						return false;
					}
				} else {
					if (!directoryCopy(f, toDir)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	//fileCopy
	private static Boolean fileCopy(File file, File dir) {
		File copyFile = new File(dir.getPath() + fs + file.getName());

		if (!copyFile.isHidden()) {	//if it's not a hidden file
			FileChannel channelFrom = null;
			FileChannel channelTo = null;

			try {
				copyFile.createNewFile();
				channelFrom = new FileInputStream(file).getChannel();
				channelTo = new FileOutputStream(copyFile).getChannel();
				channelFrom.transferTo(0, channelFrom.size(), channelTo);
				return true;
			} catch (IOException e) {
				return false;
			} finally {
				try {
					if (channelFrom != null) {
						channelFrom.close();
					}
					if (channelTo != null) {
						channelTo.close();
					}
					copyFile.setLastModified(file.lastModified());	//copy the update date
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}


	private static boolean createFile(String fileName, String content) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter
					(new FileOutputStream(fileName), "UTF-8")));
			pw.println(content);
			pw.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
