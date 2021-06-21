//added by goto 20161019 for new foreach
package supersql.codegenerator;

import supersql.codegenerator.Compiler.PHP.PHP;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_dynamic;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5_stream;
import supersql.parser.Start_Parse;


public class LinkForeach {
	public final static String ID1 = "ssql_foreach";
	public final static String ID2 = "att";
	public final static StringBuffer C3contents = new StringBuffer();

	public static boolean plink_glink = false;				//added by goto 20161109 for plink/glink
	private String r;

	public LinkForeach() {

	}

	public static String getJS(String tfe, String G3_dynamic_funcname){
		String r = "<script type=\"text/javascript\">\n" +
				   "<!--\n" +
				   "var "+ID1+"_Func = new Array;\n";
		if(tfe.equals("C3")){
			final String bodyDivID = "ssql_body_contents";
			final String ID3 = "ssqlForeach";
			r += 	"var "+ID3+"_currentID = \"\";\n" +
					"$(function(){\n" +
					"	window.onload = function(){ "+ID3+"(); }\n" +
					"	window.onhashchange = function(){ "+ID3+"(); }\n" +
					"});\n" +
					"function "+ID3+"(){\n" +
					"	if (location.hash == \"\") {\n" +
					"		document.getElementById("+ID3+"_currentID).style.display=\"none\";\n" +
					"		document.getElementById(\""+bodyDivID+"\").style.display=\"block\";\n" +
					"	}else{\n" +
					"		document.getElementById(\""+bodyDivID+"\").style.display=\"none\";\n" +
					"		var id = location.hash.substring(location.search.length+1);\n" +
					"		id = decodeURI(id);\n" +
					"		var elementID = document.getElementById(id);\n" +
					"		if(elementID)\n" +
					"			elementID.style.display=\"block\";\n" +
					"		else\n" +
					//"			document.write(\"No Data Found : \"+id);\n" +
					"			document.body.innerHTML = \"No Data Found : \"+id;\n" +
					"		\n" +
					"		"+ID3+"_currentID = id;\n" +
					"	}\n" +
					"}\n";

		}else if(tfe.equals("G3")){
			//r += 	"window.onload = function(){\n" +
			r += 	"$(document).ready(function(){\n" +
					"	if(location.search.length<1){\n";
			//added by goto 20161112 for dynamic foreach
			if(PHP.isPHP || Mobile_HTML5_dynamic.dynamicDisplay){

				if(!Start_Parse.sessionFlag){
					r += "		var atts = \"<?php echo $_POST['att']; ?>\";\n";
				}else{
					r += "		var atts = \"\n" +
						 "EOF;\n" +
						 "		echo $_POST['att'];\n" +
						 "		echo <<<EOF\n" +
						 "\";\n";
				}
				r += "		if(atts.length>0)\n" +
					 "			SSQL_DynamicDisplay1(atts);\n" +
					 "		else\n" +
					 //"			document.write(\"SuperSQL Foreach Page\");\n";
					 "			document.body.innerHTML = \"SuperSQL Foreach Page\";\n";
			}
			else if(PHP.isPHP || Mobile_HTML5_stream.streamDisplay){

				if(!Start_Parse.sessionFlag){
					r += "		var atts = \"<?php echo $_POST['att']; ?>\";\n";
				}else{
					r += "		var atts = \"\n" +
						 "EOF;\n" +
						 "		echo $_POST['att'];\n" +
						 "		echo <<<EOF\n" +
						 "\";\n";
				}
				r += "		if(atts.length>0)\n" +
					 "			SSQL_StreamDisplay1(atts);\n" +
					 "		else\n" +
					 "			document.body.innerHTML = \"SuperSQL Foreach Page\";\n";
			}
			else{
				//r += "		document.write(\"SuperSQL Foreach Page\");\n";
				r += "		document.body.innerHTML += \"SuperSQL Foreach Page<br>\";\n" +
					 "      	get_id();\n"  ; //added by li 20201207
			}
			r +=
					"	}else{\n" +
					"		var id = location.search.substring(1, location.search.length);\n" +
					"		id = id.substring(\""+ID2+"\".length+1);\n" +
					"		id = decodeURI(id);\n";
			//added by goto 20161112 for dynamic foreach
			if(PHP.isPHP || (Mobile_HTML5_dynamic.dynamicDisplay || Mobile_HTML5_stream.streamDisplay))
				r +=
						"		id = id.replace(/\\+/g, \" \");\n" +
						"		"+G3_dynamic_funcname+"(id);\n";
			else
				r +=
						"		var elementID = document.getElementById(\""+ID1+"_\"+id);\n" +
						"		if(elementID){\n" +
						"			elementID.style.display=\"block\";\n" +
						"			"+ID1+"_Func[\"sff_\"+id]();\n" +
						"		}else{\n" +
						"			id = id.replace(/\\+/g, \" \");\n" +
						"			elementID = document.getElementById(\""+ID1+"_\"+id);\n" +
						"			if(elementID){\n" +
						"				elementID.style.display=\"block\";\n" +
						"				"+ID1+"_Func[\"sff_\"+id]();\n" +
						"			}else\n" +
						//"				document.write(\"No Data Found : \"+id);\n" +
						"				document.body.innerHTML = \"No Data Found : \"+id;\n" +
						"		}\n";
			r += 	"	}\n" +
					//"}\n" +
					"});\n";
		}
		r += 
				"function get_id(){\n" +  //added by li 20201207

				"     $(\"div\").each(function() {\n" + 
				"          var url = location.href ;\n" + 
				"          var foreach_id = $(this).attr('id') ;\n" + 
				"          if(foreach_id != 'ssql_body_contents'){\n" + 
				"               var id = foreach_id.substr(\""+ID1+"_\".length);\n" + 
				"               //console.log( url );\n" + 
				"               document.body.innerHTML += \"<a href=\" + url + \"?att=\" + id + \">\" + foreach_id + \"<a><br>\";\n" + 
				"          }\n" + 
				"     });\n" + 
				"}";
		r += 	"//-->" +
				"</script>\n";
		return r;
	}

	public static String getC3contents(){
		String r = "";
		if(!C3contents.toString().isEmpty()){
			r += "<!-- SuperSQL C3(%) Contents  Start -->";
			r += getJS("C3", "")+C3contents;
			r += "<!-- SuperSQL C3(%) Contents  End -->";
		}
		return r;
	}

	//added by goto 20161109 for plink/glink
	//plink()/glink() JS and HTML
	public static String getPlinkGlinkContents(){
		String r = "";
		if(plink_glink){
			r =	"<!-- SuperSQL plink/glink Contents  Start -->\n" +
				"<script type=\"text/javascript\">\n" +
				"<!--\n" +
				"function "+ID1+"(formMethod, filename, value){\n" +
				"	var e1 = document.createElement('form');\n" +
				"	e1.setAttribute('name', '"+ID1+"_form');\n" +
				"	e1.setAttribute('method', formMethod);\n" +
				"	e1.setAttribute('action', filename);\n" +
				"	\n" +
				"	var e2 = document.createElement('input');\n" +
				"	e2.setAttribute('type', 'hidden');\n" +
				"	e2.setAttribute('name', '"+ID2+"');\n" +
				"	e2.setAttribute('value', value);\n" +
				"	\n" +
				"	document.body.appendChild(e1);\n" +
				"	document."+ID1+"_form.appendChild(e2);\n" +
				"	document."+ID1+"_form.submit();\n" +
				"}\n"	+
				"//-->" +
				"</script>\n" +
				//"<form name=\""+ID+"_form\" method=\""+formType+"\" action=\""+filename+"\"> </form>\n" +
				"<!-- SuperSQL plink/glink Contents  End -->";
		}
		return r;
	}
}
