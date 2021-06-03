package supersql.codegenerator.Responsive;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import supersql.codegenerator.Fraction;


public class Fix_C1 {	
	
	public static void C1Fix(LinkedHashMap ClassMap){
		
		LinkedHashMap<String, LinkedHashMap>ClassMap1 = ClassMap;
		
		String sizeArray[] = {"md", "sm", "xs"};
		
		int parts = ClassMap1.size();
		
		ArrayList<String> classArray = new ArrayList<String>();
		
        //class array
		for(Map.Entry<String, LinkedHashMap> e : ClassMap1.entrySet()){
			String TFEid = e.getKey();
			classArray.add(TFEid);   
			WebElement element_lg = Driver.driver_lg.findElement(By.className(TFEid));
			WebElement element_md = Driver.driver_md.findElement(By.className(TFEid));
			WebElement element_sm = Driver.driver_sm.findElement(By.className(TFEid));
			WebElement element_xs = Driver.driver_xs.findElement(By.className(TFEid));
		}
		
		
		LinkedHashMap<String,LinkedHashMap> fixMap_best = new LinkedHashMap<String,LinkedHashMap>();
		for(String classid: classArray){
			Fraction original_size = (Fraction) ClassMap1.get(classid).get("xs");
			LinkedHashMap<String, Fraction> lg_size = new LinkedHashMap<String, Fraction>();
			lg_size.put("lg", original_size);
			fixMap_best.put(classid, lg_size);
		}
		
		for(String size: sizeArray){
			int min_size_difference_total = 50000;
			
			LinkedHashMap<String,LinkedHashMap> fixMap_best_candidate = new LinkedHashMap<String,LinkedHashMap>();
			
			ArrayList<ArrayList<Integer>> divisionPatternArray = Fix_C1.getAllC1Pattern(parts);
			for(int i=0; i<divisionPatternArray.size(); i++){
				//それぞれのパターン [1,1] [2]
				ArrayList<Integer> eachPatternArray = divisionPatternArray.get(i);
				
				int size_difference_total = 0;
				LinkedHashMap<String,LinkedHashMap> fixMap_candidate = new LinkedHashMap<String,LinkedHashMap>();
				int blockstart = 0;
				int classArraykey = 0;
				for(int j=0; j<eachPatternArray.size(); j++){
					
					
					int block = eachPatternArray.get(j);
					int count = eachPatternArray.get(j);
					
					
					Fraction classSizeTotal = new Fraction("0/1");
					for(int k=blockstart; k<block+blockstart; k++){
						Fraction eachsize = (Fraction) ClassMap1.get(classArray.get(k)).get("xs");
						classSizeTotal.addition(eachsize);
					}
					blockstart += block;
					
					
					while(count>0){
						String classid = classArray.get(classArraykey);
						LinkedHashMap classInfo = ClassMap1.get(classid);
						Fraction classSize = (Fraction) classInfo.get("xs");
											
						Fraction classSizeAllocation = new Fraction("1/1");
						classSizeAllocation.multiplication(classSize);
						classSizeAllocation.division(classSizeTotal);
						
						LinkedHashMap<String,Fraction> C1fixMap_candidate = new LinkedHashMap<String,Fraction>();
						int original_size = 0;
						int changed_size = 0;
						WebElement element_lg = Driver.driver_lg.findElement(By.className(classid));
						WebElement element_md = Driver.driver_md.findElement(By.className(classid));
						WebElement element_sm = Driver.driver_sm.findElement(By.className(classid));
						WebElement element_xs = Driver.driver_xs.findElement(By.className(classid));
						
			            if(size.equals("md")){
			            	C1fixMap_candidate.put("md", classSizeAllocation);
							original_size = element_lg.getSize().width;
							((JavascriptExecutor)Driver.driver_md).executeScript("arguments[0].setAttribute('style', 'width:" + getValue(classSizeAllocation) + "%')",element_md);
							changed_size = element_md.getSize().width;
			            }else if(size.equals("sm")){
			            	C1fixMap_candidate.put("sm", classSizeAllocation);
							original_size = element_md.getSize().width;
							((JavascriptExecutor)Driver.driver_sm).executeScript("arguments[0].setAttribute('style', 'width:" + getValue(classSizeAllocation) + "%')",element_sm);
							changed_size = element_sm.getSize().width;
			            }else if(size.equals("xs")){
			            	C1fixMap_candidate.put("xs", classSizeAllocation);
			            	original_size = element_sm.getSize().width;
							((JavascriptExecutor)Driver.driver_xs).executeScript("arguments[0].setAttribute('style', 'width:" + getValue(classSizeAllocation) + "%')",element_xs);
							changed_size = element_xs.getSize().width;
			            }
						
						
						fixMap_candidate.put(classid, C1fixMap_candidate);
						
						//差
						int size_difference = Math.abs(original_size - changed_size);
						size_difference_total += size_difference;
						
						classArraykey++;
						count--;
					}
				}
				if(size_difference_total<min_size_difference_total){
					min_size_difference_total = size_difference_total;
					fixMap_best_candidate.clear();
					fixMap_best_candidate.putAll(fixMap_candidate);
				}
				
				//mdの幅を戻す。
				//fixMap_best.get(classid).get("lg");
				for (String classid: classArray){
					WebElement element_lg = Driver.driver_lg.findElement(By.className(classid));
					WebElement element_md = Driver.driver_md.findElement(By.className(classid));
					WebElement element_sm = Driver.driver_sm.findElement(By.className(classid));
					WebElement element_xs = Driver.driver_xs.findElement(By.className(classid));
					
//					if(size.equals("md")){
//						((JavascriptExecutor)Driver.driver_lg).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get("lg")) + "%')",element_lg);
//		            }else 
		            	if(size.equals("sm")){
						((JavascriptExecutor)Driver.driver_md).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get("md")) + "%')",element_md);
		            }else if(size.equals("xs")){
						((JavascriptExecutor)Driver.driver_sm).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get("sm")) + "%')",element_sm);
		            }
				}
				
				
			}
			
			for(String classid: classArray){
				fixMap_best.get(classid).putAll(fixMap_best_candidate.get(classid));
			}
			
			//bestを適用
			for (String classid: classArray){
				WebElement element_lg = Driver.driver_lg.findElement(By.className(classid));
				WebElement element_md = Driver.driver_md.findElement(By.className(classid));
				WebElement element_sm = Driver.driver_sm.findElement(By.className(classid));
				WebElement element_xs = Driver.driver_xs.findElement(By.className(classid));
				
				if(size.equals("md")){
					((JavascriptExecutor)Driver.driver_md).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get(size)) + "%')",element_md);
	            }else if(size.equals("sm")){
					((JavascriptExecutor)Driver.driver_sm).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get(size)) + "%')",element_sm);
	            }else if(size.equals("xs")){
					((JavascriptExecutor)Driver.driver_xs).executeScript("arguments[0].setAttribute('style', 'width:" + getValue((Fraction) fixMap_best.get(classid).get(size)) + "%')",element_xs);
	            }

			}
			
		}
		Driver.fixMap.putAll(fixMap_best);
	}
	
	public static ArrayList getAllC1Pattern(int limit){
		ArrayList<ArrayList<Integer>> patternArray = new ArrayList<ArrayList<Integer>>();
		
		Deque<Integer> patternStack = new ArrayDeque<Integer>();
		int total = 0;
		
		ArrayList<Integer> firstPattern = new ArrayList<Integer>();
		for (int i=0; i<limit; i++){
			patternStack.addFirst(1);
			total++;
			firstPattern.add(1);
		}
		
		patternArray.add(firstPattern);
		
		while(patternStack.peekFirst() != limit){
			ArrayList<Integer> eachPattern = new ArrayList<Integer>();
			
			total = total - patternStack.peekFirst();
			patternStack.removeFirst();
			
			total = total - patternStack.peekFirst();
			int out = patternStack.removeFirst();
			
			out++;
			total = total + out;
			patternStack.addFirst(out);
			
			while(total<limit){
				patternStack.addFirst(1);
				total++;
			}
			for (Integer num: patternStack) {
                eachPattern.add(num);
			}
			patternArray.add(eachPattern);
		}
		return patternArray;
	}
	
	public static BigDecimal getValue(Fraction fraction){
		int numerator = fraction.getNumerator();
		int denominator = fraction.getDenominator();
		
		double value = (100.00*numerator)/denominator;
        BigDecimal bd =new BigDecimal(value);
        BigDecimal bd4 = bd.setScale(10, BigDecimal.ROUND_DOWN);
		return bd4;
	}
}
