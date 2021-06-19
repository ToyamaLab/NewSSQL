package supersql.codegenerator.Responsive;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import supersql.codegenerator.Fraction;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.common.GlobalEnv;
import supersql.common.Log;


public class Driver {
	
	private final static boolean BACKGROUND_EXEC = true;	//true:ブラウザを立ち上げずバックグラウンドで実行 (Default)
//	private final static boolean BACKGROUND_EXEC = false;	//false:ブラウザを立ち上げて実行 (確認テスト, デモ用)

	public static WebDriver driver_lg;
	public static WebDriver driver_md;
	public static WebDriver driver_sm;
	public static WebDriver driver_xs;
	
//	private static final String driverPath = GlobalEnv.getworkingDir()+"/webdriver/geckodriver";
////	private static final String driverPath = GlobalEnv.getworkingDir()+"/webdriver/chromedriver";
	
	public static LinkedHashMap<String,LinkedHashMap> fixMap = new LinkedHashMap<String,LinkedHashMap>();
	
	
	public static void setupDriver() {
		
//		final String WEB_DRIVER = "gecko";	//FireFox
		final String WEB_DRIVER = "chrome";	//Chrome
		
		Log.i("sD1");
		Log.info("OS: "+OS);
		String driverPath = GlobalEnv.getworkingDir()+"/webdriver/"+WEB_DRIVER+"driver_";
		if (isWindows()) {
			driverPath += "win.exe";
		} else if (isMac()) {
			driverPath += "mac";
		} else if (isUnix()) {
			driverPath += "linux";
		} else {
			Log.err("Your OS is not supported!!");
			System.exit(0);
		}
		System.setProperty("webdriver."+WEB_DRIVER+".driver", driverPath);
//		WebDriver driver = new FirefoxDriver();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Log.i("driverPath = "+driverPath);
		Log.i("sD2:"+WEB_DRIVER);
		
//		//test
//		try {
////			WebDriver driver = new FirefoxDriver();
//			WebDriver driver = new ChromeDriver();
////			Dimension d = new Dimension(800,480);
////			driver.manage().window().setSize(d);
//		} catch (Exception e) {
////			e.printStackTrace();
//			StringWriter errors = new StringWriter();
//			e.printStackTrace(new PrintWriter(errors));
//			Log.i(errors.toString());
//		}

		
		
		
		Integer h = 3000;
//		Integer h = 768;
		
		
			// FireFoxの場合
			if (WEB_DRIVER.equals("gecko")) {
				FirefoxOptions options = new FirefoxOptions();		// FireFoxの場合
				
				// バックグラウンドで実行
				if (BACKGROUND_EXEC) {
					FirefoxBinary firefoxBinary = new FirefoxBinary();
				    firefoxBinary.addCommandLineOptions("--headless");
					options.setBinary(firefoxBinary);
				}
				Log.i("sD3");
				
				//Setup Webdriver for 4 display size
				driver_lg = new FirefoxDriver(options);
				driver_lg.manage().window().setSize(new Dimension(1200,h));
				driver_md = new FirefoxDriver(options);
				driver_md.manage().window().setSize(new Dimension(992,h));
				driver_sm = new FirefoxDriver(options);
				driver_sm.manage().window().setSize(new Dimension(768,h));
				driver_xs = new FirefoxDriver(options);
				driver_xs.manage().window().setSize(new Dimension(400,h));
			}
			
			// Chromeの場合
			else if (WEB_DRIVER.equals("chrome")) {
				ChromeOptions options = new ChromeOptions();		// Chromeの場合
				
				// バックグラウンドで実行
				if (BACKGROUND_EXEC) {
					// TODO
	//				options.addArguments("headless");
					options.addArguments("--headless");
	//				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
	//				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				}
				Log.i("sD3");
				
				driver_lg = new ChromeDriver(options);
				driver_lg.manage().window().setSize(new Dimension(1200,h));
				driver_md = new ChromeDriver(options);
				driver_md.manage().window().setSize(new Dimension(992,h));
				driver_sm = new ChromeDriver(options);
				driver_sm.manage().window().setSize(new Dimension(768,h));
				driver_xs = new ChromeDriver(options);
				driver_xs.manage().window().setSize(new Dimension(400,h));
			}
		
		
//		//Setup Webdriver for 4 display size
//		Integer h = 3000;
////		Integer h = 768;
//		
//		// FireFoxの場合
//		if (WEB_DRIVER.equals("gecko")) {
//			driver_lg = new FirefoxDriver(options);
//			driver_lg.manage().window().setSize(new Dimension(1200,h));
//			
//			driver_md = new FirefoxDriver(options);
//			driver_md.manage().window().setSize(new Dimension(992,h));
//			
//			driver_sm = new FirefoxDriver(options);
//			driver_sm.manage().window().setSize(new Dimension(768,h));
//			
//			driver_xs = new FirefoxDriver(options);
//			driver_xs.manage().window().setSize(new Dimension(400,h));
//		}
//		// Chromeの場合
//		if (WEB_DRIVER.equals("chrome")) {
//			driver_lg = new ChromeDriver(options);
//			driver_lg.manage().window().setSize(new Dimension(1200,h));
//			
//			driver_md = new ChromeDriver(options);
//			driver_md.manage().window().setSize(new Dimension(992,h));
//			
//			driver_sm = new ChromeDriver(options);
//			driver_sm.manage().window().setSize(new Dimension(768,h));
//			
//			driver_xs = new ChromeDriver(options);
//			driver_xs.manage().window().setSize(new Dimension(400,h));
//		}
		
		Log.i("sD4");

		//Get page of URL: 基準ページの生成
		String Base_fn = "";
		if (Responsive.USE_OPTION) {
			// -rurlで指定された別ファイルを使用
			Base_fn = Responsive.getResponsiveURL();					//基準ページ 2-1
		} else {
			// 同じファイルを使用
//			Base_fn = new Mobile_HTML5Env().getFileName4()+".html";	//@responsiveページ 2-2
			Base_fn = new Mobile_HTML5Env().getFileName2()+".html";	//@responsiveページ 2-2
			if (!Base_fn.startsWith("http"))
				Base_fn = "file://"+Base_fn;
		}
		Log.info("Base_fn = "+Base_fn);
		driver_lg.get(Base_fn);
		driver_md.get(Base_fn);
		driver_sm.get(Base_fn);
		driver_xs.get(Base_fn);
		
		
		
		
		Log.i("sD5");
		
	}


//	private Object _driver;
//	
//	protected WebDriver getDriver() {
//		if (this._driver == null) {
//			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setJavascriptEnabled(true);
//			caps.setCapability(
//					PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX
//							+ "userAgent", spoofUserAgent);
//
//			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
//					new String[] { "--web-security=false",
//							"--ssl-protocol=any", "--ignore-ssl-errors=true",
//							"--webdriver-loglevel=INFO" });
//
//			PhantomJSDriverService service = new PhantomJSDriverService.Builder()
//			        .usingPort(8080)
//			        .usingPhantomJSExecutable(new File("/usr/local/bin/phantomjs"))
//			        .build();
//			this._driver = new PhantomJSDriver(service, caps);
//		}
//
//		return this._driver;
//	}
	
	
	public static void quitDriver(){
        driver_lg.quit();
        driver_md.quit();
        driver_sm.quit();
        driver_xs.quit();
    }
	
	public static void G1Fix(String TFEid, Fraction original_size, WebDriver driver_lg, WebDriver driver_md, WebDriver driver_sm, WebDriver driver_xs, LinkedHashMap fixMap){
		
		int original_division = original_size.getDenominator();
		
		//Get G1 Element x4
        WebElement element_lg = driver_lg.findElement(By.className(TFEid));
        List<WebElement> elements_lg = driver_lg.findElements(By.className(TFEid));
        
        WebElement element_md = driver_md.findElement(By.className(TFEid));
        List<WebElement> elements_md = driver_md.findElements(By.className(TFEid));
        
        WebElement element_sm = driver_sm.findElement(By.className(TFEid));
        List<WebElement> elements_sm = driver_sm.findElements(By.className(TFEid));
        
        WebElement element_xs = driver_xs.findElement(By.className(TFEid));
        List<WebElement> elements_xs = driver_xs.findElements(By.className(TFEid));
        
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

            switch (e.getKey()){
            case "md":
            	for(WebElement each : elements_md){
            		((JavascriptExecutor)driver_md).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "sm":
            	for(WebElement each : elements_sm){
            		((JavascriptExecutor)driver_sm).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "xs":
            	for(WebElement each : elements_xs){
            		((JavascriptExecutor)driver_xs).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            }
        }
        fixMap.put(TFEid, G1fixMap);
    	System.out.println(fixMap);
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
	
	
	// OS check for WebDriver
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	private static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
	private static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
}
