package supersql.codegenerator.Responsive;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;

import supersql.codegenerator.Fraction;
import supersql.common.GlobalEnv;


public class Test1 {

	public static void main(String[] args) {
		//Initialization of WebDriver
		String driverPath = GlobalEnv.getworkingDir()+"/webdriver/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverPath);
		WebDriver driver = new FirefoxDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        
		//LinkedHashMap for final fixes
		LinkedHashMap<String,LinkedHashMap> fixMap = new LinkedHashMap<String,LinkedHashMap>();
		
		
		//Get page of URL
        driver.get("http://localhost/dvdrental/php_movie_foreach.html?att=27");

        //lg 1200, md 992, sm 768, xs 400
        int x=8;
        String G1TFE = "TFE10020";
        WebElement element = driver.findElement(By.className("TFE10020"));
        List<WebElement> elements = driver.findElements(By.className("TFE10020"));
        
        
        
        HashMap<String,Integer> G1widthMap = new HashMap<String,Integer>();
        driver.manage().window().setSize(new Dimension(1200,3000));
        G1widthMap.put("lg", element.getSize().width);
        int lg_width = element.getSize().width;
//        widths.add(lg_width);
        System.out.println(element.getSize().width);
        
        driver.manage().window().setSize(new Dimension(992,3000));
        G1widthMap.put("md", element.getSize().width);
        int md_width = element.getSize().width;
        System.out.println(element.getSize().width);
        
        driver.manage().window().setSize(new Dimension(768,3000));
        G1widthMap.put("sm", element.getSize().width);
        int sm_width = element.getSize().width;
        System.out.println(element.getSize().width);
        
        
        driver.manage().window().setSize(new Dimension(400,3000));
        G1widthMap.put("xs", element.getSize().width);
        int xs_width = element.getSize().width;
        System.out.println(element.getSize().width+"\n");
        
        for(Map.Entry<String, Integer> e : G1widthMap.entrySet()) {
        	double minDiff=5000;
            int best = 0;
            
            LinkedHashMap<String,Fraction> G1fixMap = new LinkedHashMap<String,Fraction>();
            
        	String size = e.getKey();
        	int eachwidth = e.getValue();
        	
        	if(e.getKey()!="lg"){
	        	for(int i=0; i<x; i++){
	                double width = Math.floor( (eachwidth * x ) / ( x-i ) );
	                if (Math.abs(width-G1widthMap.get("lg")) < minDiff){
	                	minDiff = Math.abs(width-lg_width);
	                	best = x-i;
	                }
	            }
	        	System.out.println(best+"\n");
	        	
	            double fixWidth = 100.0/best;
	            BigDecimal bd =new BigDecimal(fixWidth);
	            BigDecimal bd4 = bd.setScale(3, BigDecimal.ROUND_DOWN);

	            
	        	G1fixMap.put("TFE10020", new Fraction("1/"+best));
	        	fixMap.put(size, G1fixMap);
	            System.out.println(fixMap);
        	}
        }
        	
        
        
        
//        WebElement element2 = driver.findElement(By.className("TFE10020"));
//        
//        driver.manage().window().setSize(new Dimension(1200,900));
//        System.out.println(element2.getSize().width);
//        driver.manage().window().setSize(new Dimension(992,900));
//        System.out.println(element2.getSize().width);
//        driver.manage().window().setSize(new Dimension(768,900));
//        System.out.println(element2.getSize().width);
//        driver.manage().window().setSize(new Dimension(400,900));
//        System.out.println(element2.getSize().width);
        
//        List<WebElement> allspan = element.findElements(By.tagName("span"));
//        
//        int max=0;
//        for(WebElement span : allspan){
//        	int spanwidth = (span.getSize().width);
//        	if (spanwidth > max) {
//                max = spanwidth;
//            }
//        }
//        System.out.println(max);
        
//        driver.manage().window().setSize(new Dimension(400,900));
//        element = driver.findElement(By.className("TFE10039"));
//        allspan = element.findElements(By.tagName("span"));
        
//        driver.execute_script("");
        for(WebElement each : elements){
        	((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'width:100%')",each);
//        	System.out.println(element.getSize().width);
        }
        
//        CaptureScreenshot(driver, js);
        
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        try {
//			FileUtils.copyFile(scrFile, new File("/Users/ryosuke/Desktop/screenshot1.png"));
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//        ((JavascriptExecutor)driver).executeScript("document.getElementsByName('myField')[0].style.left='40%'");
        
//        max=0;
//        for(WebElement span : allspan){
//        	int spanwidth = (span.getSize().width);
//        	if (spanwidth > max) {
//                max = spanwidth;
//            }
//        }
//        System.out.println(max);
        
//        System.out.println(element.getSize().height);
//        System.out.println(element.getSize().width);
//        System.out.println(element.getText());
        driver.manage().window().setSize(new Dimension(430,900));
        
//        System.out.println(element.getSize().height);
//        System.out.println(element.getSize().width);
        
        // Enter something to search for
//        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
//        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().startsWith("cheese!");
//            }
//        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        driver.quit();
    }
	
	public static void CaptureScreenshot(WebDriver driver, JavascriptExecutor js){
		TakesScreenshot ts = (TakesScreenshot) new Augmenter().augment(driver);
        
	      //画面サイズで必要なものを取得
	        int innerH = Integer.parseInt(String.valueOf(js.executeScript("return window.innerHeight")));
	        int innerW =Integer.parseInt(String.valueOf(js.executeScript("return window.innerWidth")));
	        int scrollH = Integer.parseInt(String.valueOf(js.executeScript("return document.documentElement.scrollHeight")));
	        
	      //イメージを扱うための準備
	        BufferedImage img = new BufferedImage(innerW, scrollH, BufferedImage.TYPE_INT_ARGB);
	        Graphics g = img.getGraphics();
	        
	        try {
		      //スクロールを行うかの判定
		        if(innerH>scrollH){
		            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		            g.drawImage(imageParts, 0, 0, null);
		        } else {
		            int scrollableH = scrollH;
		            int i = 0;
		
		            //スクロールしながらなんどもイメージを結合していく
		            while(scrollableH>innerH){
		                BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		                g.drawImage(imageParts, 0, innerH*i, null);
		                scrollableH=scrollableH - innerH;
		                i++;
		                js.executeScript("window.scrollTo(0,"+innerH*i+")");
		            }
		
		            //一番下まで行ったときは、下から埋めるように貼り付け
		            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		            g.drawImage(imageParts, 0, scrollH - innerH, null);
		        }
		
		        ImageIO.write(img, "png", new File("/Users/ryosuke/Desktop/screenshot"+ System.currentTimeMillis() +".png"));
	        } catch (WebDriverException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	}

}
