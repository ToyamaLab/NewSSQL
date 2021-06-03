package supersql.codegenerator.Responsive;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import supersql.codegenerator.Fraction;


public class Fix_G1 {
		
	public static void G1Fix(LinkedHashMap map){
		
		LinkedHashMap<String, LinkedHashMap>ClassMap = map;
		String TFEid = "";
		Fraction original_size = new Fraction("1/1");
		
		for(Map.Entry<String, LinkedHashMap> e : ClassMap.entrySet()){
			TFEid = e.getKey();
			original_size = (Fraction) e.getValue().get("xs");
		}
		
		int original_division = original_size.getDenominator();
		
		//Get G1 Element x4
        WebElement element_lg = Driver.driver_lg.findElement(By.className(TFEid));
        List<WebElement> elements_lg = Driver.driver_lg.findElements(By.className(TFEid));
        
        WebElement element_md = Driver.driver_md.findElement(By.className(TFEid));
        List<WebElement> elements_md = Driver.driver_md.findElements(By.className(TFEid));
        
        WebElement element_sm = Driver.driver_sm.findElement(By.className(TFEid));
        List<WebElement> elements_sm = Driver.driver_sm.findElements(By.className(TFEid));
        
        WebElement element_xs = Driver.driver_xs.findElement(By.className(TFEid));
        List<WebElement> elements_xs = Driver.driver_xs.findElements(By.className(TFEid));
        
        //Get width of each display size
        LinkedHashMap<String,Integer> G1widthMap = new LinkedHashMap<String,Integer>();
        G1widthMap.put("lg", element_lg.getSize().width);
        G1widthMap.put("md", element_md.getSize().width);
        G1widthMap.put("sm", element_sm.getSize().width);
        G1widthMap.put("xs", element_xs.getSize().width);
        
        
        LinkedHashMap<String,Fraction> G1fixMap = new LinkedHashMap<String,Fraction>();
        //Calculate Best
        for(Map.Entry<String, Integer> e : G1widthMap.entrySet()) {
        	double minDiff=5000;
            int best = 0;
            
        	String size = e.getKey();
        	int eachwidth = e.getValue();
        	
        	for(int i=0; i<original_division; i++){
                double width = Math.floor( (eachwidth * original_division ) / ( original_division - i ) );
                if (Math.abs(width-G1widthMap.get("lg")) < minDiff){
                	minDiff = Math.abs(width-G1widthMap.get("lg"));
                	best = original_division - i;
                }
            }
        	
        	G1fixMap.put(size, new Fraction("1/"+best));
        	
            double fixWidth = 100.0/best;
            BigDecimal bd =new BigDecimal(fixWidth);
            BigDecimal bd4 = bd.setScale(3, BigDecimal.ROUND_DOWN);

            //ExecuteCSS
            switch (e.getKey()){
            case "md":
            	for(WebElement each : elements_md){
            		((JavascriptExecutor)Driver.driver_md).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "sm":
            	for(WebElement each : elements_sm){
            		((JavascriptExecutor)Driver.driver_sm).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "xs":
            	for(WebElement each : elements_xs){
            		((JavascriptExecutor)Driver.driver_xs).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            }
        }
        Driver.fixMap.put(TFEid, G1fixMap);
	}
}
