package supersql.codegenerator.HTML;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

public class HTMLPagination {
	
	private static int paginationCount = 0;
	
	public HTMLPagination() {

	}
	
	public static String getPaginationHTML_JS1() {
		String now = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(Calendar.getInstance().getTime());
		String randomStr = RandomStringUtils.random(8,"0123456789abcdefghijklmnopqrstuvwxyz");
		int pc = (++paginationCount);
		
		String ssqlPaginationStr = "ssqlPagination";
		String paginationLabel1 = ssqlPaginationStr+pc;
		String paginationLabel2 = paginationLabel1+"_"+now+"_"+randomStr;
		
		return 
				"<div id=\""+paginationLabel1+"_res\" class=\""+ssqlPaginationStr+"_res\"></div>\n" +
				"<div id=\""+paginationLabel1+"\" class=\""+ssqlPaginationStr+"_pagination\"></div>\n" +
				
				"<script type=\"text/javascript\">\n" +
				"	$(function(){\n" +
				"		var "+paginationLabel1+"_pageIndex = Number(window.sessionStorage.getItem('"+paginationLabel2+"_current-page-index'));\n" +
				"		initPagination"+pc+"();\n" +
				"		$(\"#"+paginationLabel1+"\")[0].selectPage("+paginationLabel1+"_pageIndex);\n" +
				"	});\n" +
				"	function initPagination"+pc+"() {\n" +
				"		var num_entries = $('#"+paginationLabel1+"_hiddenresult div.result').length;\n" +
				"		$(\"#"+paginationLabel1+"\").pagination(num_entries, {\n" +
				"			num_edge_entries: 2,\n" +
				"			num_display_entries: 8,\n" +
				"			callback: pageselectCallback"+pc+",\n" +
				"			items_per_page:1\n" +
				"		});\n" +
				"	}\n" +
				"	function pageselectCallback"+pc+"(page_index, jq){\n" +
				"		var new_content = $('#"+paginationLabel1+"_hiddenresult div.result:eq('+page_index+')').clone();\n" +
				"		$('#"+paginationLabel1+"_res').empty().append(new_content);\n" +
				"		window.sessionStorage.setItem('"+paginationLabel2+"_current-page-index', page_index);\n" +
				"		return false;\n" +
				"	}" +
				"</script>\n" +

				"<!-- Container element for all the Elements that are to be paginated -->\n" +
				"<div id=\""+paginationLabel1+"_hiddenresult\" style=\"display:none;\">\n" +
				"<div class=\"result\">\n";
	}

}
