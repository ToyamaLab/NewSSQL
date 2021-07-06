package supersql.tasuku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import supersql.common.GlobalEnv;

public class HTMLwrite_ssql_embedded {
	//SSQL文の埋め込み
	public static String Html_Embed() {


		String filename = GlobalEnv.getfilename();
		String html_file = filename.replace(".ssql", ".html");
		String ssql = "";

				if(filename != null) {
				try{
					  //FileWriter file1 = new FileWriter(html_file, true);
			            //PrintWriter pw1 = new PrintWriter(new BufferedWriter(file1));
			            //FileWriter file2 = new FileWriter(html_file, true);
			           // PrintWriter pw2 = new PrintWriter(new BufferedWriter(file2));
			            FileInputStream input=new FileInputStream(filename);
						FileOutputStream output=new FileOutputStream(html_file, true);
						BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
						String tmp = "";

				/*pw1.println("\n<!---SSQL CODE---");
				pw1.flush();
				  pw1.close();*/

				/*byte buf[]=new byte[256];
				int len;
				while((len=input.read(buf))!=-1){
				  output.write(buf,0,len);
				}*/

					 /* while(in.readLine() != null){
					    //System.out.println(str);
					    ssql += in.readLine();
					  }*/

					  while ((tmp = in.readLine()) != null) {
			                ssql += tmp;
			                ssql += "\n";
			            }

					  for(int i = 0 ; i < 10; i++) {
							 if(ssql.contains("|| "))
								 ssql = ssql.replace("|| ", "||");
							 if(ssql.contains(" || "))
								 ssql = ssql.replace(" || ", "||");
							 if(ssql.contains(" ||"))
								 ssql = ssql.replace(" ||", "||");
							 //System.out.println(ssql);
					  }

					  output.close();
						 input.close();
in.close();



				/*pw2.println("\n---SSQL CODE END--->");
				pw2.flush();
				pw2.close();*/

					}catch(FileNotFoundException e){
					  System.out.println(e);
					}catch(IOException e){
					  System.out.println(e);
					}
				}
				return ssql;
	}
}
