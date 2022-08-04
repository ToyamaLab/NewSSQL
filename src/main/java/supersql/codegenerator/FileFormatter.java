//created by goto 20161019 for HTML Formatter

package supersql.codegenerator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileFormatter {
	static boolean isExec = true;
//	static boolean isExec = false;
	
	public FileFormatter() {
		
	}

    public static String process(String html) {
    	if (isExec) {
	    	String r = "";
	    	try {
				Document doc = Jsoup.parse(html);
				doc.outputSettings().prettyPrint(true);
				doc.outputSettings().indentAmount(5);
				doc.outputSettings().outline(true);
				r = doc.html();
				
				r = r.replace("<!--?php", "<?php")
					 .replace("?--><!DOCTYPE html>", "?>\n<!DOCTYPE html>")
					 .replace("?-->", "?>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	        return (!r.equals(""))? r : html;
    	} else {
			return html;
		}
    	
    	
//    	// Create an instance of HtmlCleaner
//      HtmlCleaner cleaner = new HtmlCleaner();
//      // take default cleaner properties
//      CleanerProperties props = cleaner.getProperties();
//      
//      // customize cleaner's behaviour with property setters
////      props.setUseCdataFor("script,style");
//
//      try {
//          // Exec the Cleaner
//      	
//      	// Clean HTML taken from simple string, file, URL, input stream, 
//      	// input source or reader. Result is root node of created 
//      	// tree-like structure. Single cleaner instance may be safely used
//      	// multiple times.
//          TagNode node = cleaner.clean(html);
//
//          
////      	// serialize a node to a file, output stream, DOM, JDom...
////      	new XXXSerializer(props).writeXmlXXX(aNode, ...);
////          StringWriter writer = new StringWriter();
////      	new PrettyXmlSerializer(props).writeXml(node, writer, "utf-8");
////      	r = new PrettyXmlSerializer(props).getAsString(node);
//      	r = new PrettyHtmlSerializer(props).getAsString(node);
////      	r = new CompactHtmlSerializer(props).getAsString(node);
////      	r = new SimpleHtmlSerializer(props).getAsString(node);
////      	new Serializer(props).getAsString(node);//.writeXml(node, writer, "utf-8");
//          
////          XmlSerializer serializer = new PrettyXmlSerializer( props );
////          StringWriter writer = new StringWriter();
////          serializer.writeXml(node, writer, "utf-8");
////
////          r = writer.getBuffer().toString();
////          writer.close();
//          
//      } catch(Exception e) {
//          e.printStackTrace();
//      }
    	
    }
}