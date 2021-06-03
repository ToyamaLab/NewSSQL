package supersql.codegenerator.Responsive;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.openqa.selenium.JavascriptExecutor;

import supersql.codegenerator.Fraction;


public class Test1 {

	public static void main(String[] args) {
		
		Driver.setupDriver();
		
		LinkedHashMap<Integer, LinkedHashMap> HTMLCheckMap = new LinkedHashMap<Integer, LinkedHashMap>();
		LinkedHashMap<String, LinkedHashMap> C1G1Map = new LinkedHashMap<String, LinkedHashMap>();
		LinkedHashMap<String, LinkedHashMap> ClassMap = new LinkedHashMap<String, LinkedHashMap>();
		LinkedHashMap<String, Fraction> SizeMap = new LinkedHashMap<String, Fraction>();
		
//		for()
		
		
//		G1_Fix.G1Fix("TFE10020", new Fraction("1/6"));
//		System.out.println(Driver.fixMap);
		
//	//C1
//		//{ 1={C1={TFE10008={xs=2/7}, TFE10023={xs=5/7}}}, 
//		//2={C1={TFE10010={xs=7/8}, TFE10011={xs=1/8}}}, 
//		//3={G1={TFE10020={xs=1/8}}}, 
//		//4={C1={TFE10035={xs=2/7}, TFE10048={xs=5/7}}}, 
//		//5={G1={TFE10039={xs=1/2}}} }
//		
////		LinkedHashMap<String, Fraction> SizeMap1 = putsize("xs", new Fraction("1/5"));
////		LinkedHashMap<String, LinkedHashMap> ClassMap1 = putinmap("TFE10035", SizeMap1);
////		LinkedHashMap<String, Fraction> SizeMap2 = putsize("xs", new Fraction("1/3"));
////		LinkedHashMap<String, Fraction> SizeMap3 = putsize("xs", new Fraction("7/15"));
////		ClassMap1.put("TFE10041", SizeMap2);
////		ClassMap1.put("TFE10049", SizeMap3);
//		
		LinkedHashMap<String, Fraction> SizeMap1 = putsize("xs", new Fraction("2/7"));
		LinkedHashMap<String, LinkedHashMap> ClassMap1 = putinmap("TFE10008", SizeMap1);
		LinkedHashMap<String, Fraction> SizeMap2 = putsize("xs", new Fraction("5/7"));
		ClassMap1.put("TFE10023", SizeMap2);
		
		C1G1Map.put("C1", ClassMap1);
		HTMLCheckMap.put(1, C1G1Map);
		
		LinkedHashMap<String, Fraction> SizeMap3 = putsize("xs", new Fraction("1/8"));
		LinkedHashMap<String, LinkedHashMap> ClassMap2 = putinmap("TFE10020", SizeMap3);
		
		LinkedHashMap<String, LinkedHashMap> C1G1Map2 = new LinkedHashMap<String, LinkedHashMap>();
		
		C1G1Map2.put("G1", ClassMap2);
		HTMLCheckMap.put(2, C1G1Map2);
		
		System.out.println(HTMLCheckMap);
		
		for(Entry<Integer, LinkedHashMap> e : HTMLCheckMap.entrySet()) {
			LinkedHashMap<String, LinkedHashMap> C1G1Map_B = e.getValue();
			if(C1G1Map_B.containsKey("C1")){
				C1G1Map_B.get("C1");
				System.out.println(C1G1Map_B.get("C1"));
				Fix_C1.C1Fix(C1G1Map_B.get("C1"));
			}else if(C1G1Map_B.containsKey("G1")){
				C1G1Map_B.get("G1");
				System.out.println(C1G1Map_B.get("G1"));
				Fix_G1.G1Fix(C1G1Map_B.get("G1"));
			}
		}
		
		
//		Fix_C1.C1Fix(ClassMap1);
		System.out.println(Driver.fixMap);
		
		Screenshot.CaptureScreenshot(Driver.driver_lg, (JavascriptExecutor)Driver.driver_lg);
		Screenshot.CaptureScreenshot(Driver.driver_md, (JavascriptExecutor)Driver.driver_md);
		Screenshot.CaptureScreenshot(Driver.driver_sm, (JavascriptExecutor)Driver.driver_sm);
		Screenshot.CaptureScreenshot(Driver.driver_xs, (JavascriptExecutor)Driver.driver_xs);

		Driver.quitDriver();
    }
	
	public static LinkedHashMap putinmap(String key, LinkedHashMap map){
		LinkedHashMap<String, LinkedHashMap> returnMap = new LinkedHashMap<String, LinkedHashMap>();
		returnMap.put(key, map);
		return returnMap;
	}
	
	public static LinkedHashMap putsize(String key, Fraction size){
		LinkedHashMap<String, Fraction> returnMap = new LinkedHashMap<String, Fraction>();
		returnMap.put(key, size);
		return returnMap;
	}
}
