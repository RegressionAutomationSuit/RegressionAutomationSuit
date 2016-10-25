package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Base {

	//public static Read_XLS TestSuiteListExcel=null;
	//public static Read_XLS TestCaseListExcelOne=null;
	//public static Read_XLS TestCaseListExcelTwo=null;
	//public static Logger Add_Log = null;
	public boolean BrowseralreadyLoaded=false;
	public static Properties Param = null;
	public static Properties Object = null;
	public static WebDriver driver=null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	public static Properties configProp;
	public static Properties objectProp;
	public static WebElement element;
	public static Select select;
	public static ExtentReports extent;
	public static String suitName;
	public static ExtentTest test;
	public static ExtentTest SuitTest;
	public static String TC_Name = null;
	public static String filePath = null;
	public static String image = null;
	public static String ExecutionDate = null;
	public static String browserType;
	public static String dataSheetName;
	public static String testCaseExecutionFlag;
	//public static String reportfilePath = null; 
	
	@BeforeTest
	/*@Parameters({"browser","datasheet"})
	public static void GetDataFromXml(String browser,String datasheet){
		System.out.println("GetDataFromXml browser: "+browser);
		System.out.println("GetDataFromXml sheetname: "+datasheet);
		browserType=browser;
		dataSheetName=datasheet;
		/*String[] AllFnFv=testdata.split(",");
		for(int i=0; i<AllFnFv.length ;i++)
		{
			String FieldName=AllFnFv[i].split("=")[0];
			String FieldValue=AllFnFv[i].split("=")[1];
			if(FieldName.trim().equalsIgnoreCase("browser"))
			{
				browserType=FieldValue;
			}
			if(FieldName.trim().equalsIgnoreCase("datasheet"))
			{
				dataSheetName=FieldValue;
			}
		}
		//System.out.println("GetDataFromXml: sheetname "+dataSheetName);
		//System.out.println("GetDataFromXml browser "+browserType);
	}*/
	@Parameters({"base"})
	public static void GetDataFromXml(String testdata){
		String[] AllFnFv=testdata.split(",");
		for(int i=0; i<AllFnFv.length ;i++)
		{
			String FieldName=AllFnFv[i].split("=")[0];
			String FieldValue=AllFnFv[i].split("=")[1];
			if(FieldName.trim().equalsIgnoreCase("browser"))
			{
				browserType=FieldValue;
			}
			if(FieldName.trim().equalsIgnoreCase("datasheet"))
			{
				dataSheetName=FieldValue;
			}
		}
		System.out.println("GetDataFromXml: sheetname "+dataSheetName);
		System.out.println("GetDataFromXml browser "+browserType);
	}
	
	    
	@BeforeSuite
    public void beforeSuite(ITestContext context) {
		try 
		{
			if (configProp == null) 
			{
				configProp = new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
								+ "//src//test//resources//Config.properties");
				configProp.load(fs);
			} 
			if (objectProp == null) 
			{
				objectProp = new Properties();
				FileInputStream fs = new FileInputStream(configProp.getProperty("pathForObjectProperty"));
				objectProp.load(fs);
			}
			suitName=context.getSuite().getName().toLowerCase();
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
    }
	/*
	@AfterSuite
    protected void afterSuite() {
		//System.out.println("Extent in Base after suit: "+extent);
		extent.endTest(SuitTest);
		extent.flush();
        extent.close();
    }
	
	@BeforeTest
	public void init() throws IOException
	{
		System.out.println("REPORT_PATH: "+configProp.getProperty("REPORT_PATH")+" File separator: "+File.separator);
		ExecutionDate = getdate();
		//TC_Name=dataSheetName;
		reportfilePath=configProp.getProperty("REPORT_PATH")+File.separator+"Automation Regression Summarized Report for "+ExecutionDate+".html";
        //extent = ExtentReportsNG.generateReport(".\\report\\ Execution Date -"+ExecutionDate+".html");
		System.out.println("reportfilePath: "+reportfilePath);
		extent = ExtentReportsNG.generateReport(reportfilePath);
		
		SuitTest = extent.startTest(dataSheetName);
		SuitTest.assignCategory(dataSheetName);
        System.out.println("dataSheetName: "+dataSheetName);
	}
	
	@AfterTest
    protected void afterTest() {
		System.out.println("Extent in Base after suit: "+extent);
		extent.endTest(SuitTest);
		extent.flush();
		
    }    */
	
	 @BeforeTest
	    public void beforeMethod(ITestContext context) {
		 System.out.println("In before method datasheet: "+dataSheetName);
	        //ExtentTestManager.startTest(dataSheetName).assignCategory(this.getClass().getName());
		 ExtentTestManager.startTest(dataSheetName).assignCategory(context.getSuite().getName(),this.getClass().getName());
	     
	    }
	 @AfterMethod
	    protected void afterMethod(ITestResult result) {
	        /*if (result.getStatus() == ITestResult.FAILURE) {
	            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	        } else {
	            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	        }*/
	        
	        ExtentReportsNG.getReporter().endTest(ExtentTestManager.getTest());        
	        ExtentReportsNG.getReporter().flush();
	    }
	
	public static Object[][] getTestData(Xls_Reader xls, String testCaseName) 
	{
		Hashtable<String, String> table = null;
		int numberofcolumns=0;
		Object[][] data = null;
		
		
		if(xls.isSheetExist(testCaseName))
		{
			System.out.println("Reading sheet: "+xls.getSheet(testCaseName).getSheetName());
			int numberofrows=xls.getSheet(testCaseName).getLastRowNum();
			System.out.println("Number of rows in test sheet "+ testCaseName+" are: "+numberofrows);
			
			data=new Object[numberofrows-1][1];
			int dataRow = 0;
			
			for(int row=2; row<=numberofrows; row++ ,dataRow++)
			{			
				table = new Hashtable<String, String>();
				numberofcolumns=xls.getSheet(testCaseName).getRow(row).getPhysicalNumberOfCells();
				System.out.println("Number of columns in row "+ row+" are: "+numberofcolumns);
				for(int column=0;column<numberofcolumns;column++)
				{					
					String key = xls.getSheet(testCaseName).getRow(1).getCell(column).toString();
					//System.out.println("key: :"+key);
					String value = xls.getSheet(testCaseName).getRow(row).getCell(column).toString();
					//System.out.println("value: :"+value);
					table.put(key, value);
				}
				//System.out.println("Table:"+table);
				data[dataRow][0] = table;
				//System.out.println("data["+dataRow+"][0]"+data[dataRow][0]);
			}
			
		}
		//System.out.println("data[0][0]"+data[0][0]);
		//System.out.println("data[1][0]"+data[1][0]);
		
		//System.out.println("Data length:"+data.length);
		return data;
	}	
	
	public static boolean isRunnable(String testName, Xls_Reader xls) {
		String sheet = configProp.getProperty("TESTCASE_SHEET"); //Constants.TESTCASE_SHEET;
		int totalnumberrows = xls.getRowCount(sheet);
		for (int row = 2; row <= totalnumberrows; row++) {
			String tName = xls.getCellData(sheet, configProp.getProperty("TESTCASE_NAME"), row);
			if (tName.equals(testName)) {
				String runmode = xls.getCellData(sheet,
						configProp.getProperty("COL_RUNMODENAME"), row);
				if (runmode.equals(configProp.getProperty("RUNMODE_YES")))
					return true;
				else
					return false;

			}
		}
		return false;
	}	
	
	public static  void loadWebBrowser(){
		System.out.println(browserType);
			if(browserType.equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
				driver = ExistingmozillaBrowser;
				return ;
			}else if(browserType.equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
				driver = ExistingchromeBrowser;
				return ;
			}else if(browserType.equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
				driver = ExistingIEBrowser;
				return ;
			}	
		
			if(browserType.equalsIgnoreCase("Mozilla")){
				//To Load Firefox driver Instance. 
				driver = new FirefoxDriver();
				ExistingmozillaBrowser=driver;
				//Add_Log.info("Firefox Driver Instance loaded successfully.");
				
			}else if(browserType.equalsIgnoreCase("Chrome")){
				//To Load Chrome driver Instance.
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
				caps.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				caps.setCapability("nativeEvents", false);
				driver = new ChromeDriver(caps);
				//Add_Log.info("Chrome Driver Instance loaded successfully.");
				
			}else if(browserType.equalsIgnoreCase("IE")){
				//To Load IE driver Instance.
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//BrowserDrivers//IEDriverServer.exe");
				caps.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				caps.setCapability("nativeEvents", false);
				driver = new InternetExplorerDriver(caps);
				//Add_Log.info("IE Driver Instance loaded successfully.");
				
			}			
			if(suitName.contains("dstv"))
			{
				driver.get(configProp.getProperty("dstvSiteURL"));
			}
			else if(suitName.contains("gotv"))
			{
				driver.get(configProp.getProperty("gotvSiteURL"));
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}
		/*//Check If any previous webdriver browser Instance Is exist then run new test In that existing webdriver browser Instance.
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
				driver = ExistingmozillaBrowser;
				return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
				driver = ExistingchromeBrowser;
				return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
				driver = ExistingIEBrowser;
				return;
			}		
		
		
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
				//To Load Firefox driver Instance. 
				driver = new FirefoxDriver();
				ExistingmozillaBrowser=driver;
				//Add_Log.info("Firefox Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("Chrome")){
				//To Load Chrome driver Instance.
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
				driver = new ChromeDriver();
				ExistingchromeBrowser=driver;
				//Add_Log.info("Chrome Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
				//To Load IE driver Instance.
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//BrowserDrivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				ExistingIEBrowser=driver;
				//Add_Log.info("IE Driver Instance loaded successfully.");
				
			}			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();		
			*/

	public void closeWebBrowser(){
		driver.quit();
		//null browser Instance when close.
		ExistingchromeBrowser=null;
		ExistingmozillaBrowser=null;
		ExistingIEBrowser=null;
	}
	
	public void getResults(ITestResult result)
	{
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
	    
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		Date today = Calendar.getInstance().getTime();  
		String reportDate = df.format(today);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		if (ExtentTestManager.getTest().getRunStatus().equals(LogStatus.FAIL)) {
	        result.setStatus(ITestResult.FAILURE);
	    } else {
	        result.setStatus(ITestResult.SUCCESS);
	    }
		/*
		System.out.println("ITestResult.FAILURE "+ITestResult.FAILURE);
		System.out.println("ITestResult.SUCCESS "+ITestResult.SUCCESS);
		System.out.println("result.getStatus(): "+result.getStatus());*/
		
		// Here will compare if test is failing then only it will enter into if condition
		if(result.getStatus()==ITestResult.FAILURE){				
			try {
				FileUtils.copyFile(source, new File("./Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for failure"+browserName);
				filePath = "../Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				System.out.println(filePath);
				image = ExtentTestManager.getTest().addScreenCapture(filePath);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Case :"+result.getName()+"  "+" failed on browser "+browserType);
				ExtentTestManager.getTest().log(LogStatus.FAIL, image);
				System.out.println("result.getStatus()==ITestResult.FAILURE");
			} 
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 	 
		}
		else if (result.getStatus()==ITestResult.SUCCESS){
			try{
				
				FileUtils.copyFile(source, new File("./Screenshots/pass/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for Success"+browserName);
				filePath = "../Screenshots/pass/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				System.out.println(filePath);
				image = ExtentTestManager.getTest().addScreenCapture(filePath);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case :"+result.getName()+" -Passed", image);
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			try
			{
				FileUtils.copyFile(source, new File("./Screenshots/skip/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for Skip"+browserName);
				filePath = "../Screenshots/skip/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case :"+result.getName()+" -skipped " +result.getThrowable());
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}	
	    }
	}
	
	public WebElement getElement(String locatorKey) {

		WebElement element = null;

		try {
			if (locatorKey.endsWith("_xpath")) {
				element = driver.findElement(By.xpath(objectProp.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_id")) {
				element = driver.findElement(By.id(objectProp.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_linkText")) {
				element = driver.findElement(By.linkText(objectProp
						.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_name")) {
				element = driver.findElement(By.name(objectProp.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_partialLinkText")) {
				element = driver.findElement(By.partialLinkText(locatorKey));
			} else {
				ExtentTestManager.getTest().log(LogStatus.ERROR, "Locator not correct - " + locatorKey);
				//reportFailure("Locator not correct - " + locatorKey);
				//Assert.fail("Locator not correct - " + locatorKey);
			}

		} catch (Exception e) {
			// FAIL THE TEST AND REPORT THE ERROR
			//reportFailure(e.getMessage());
			e.printStackTrace();
			// Assert.fail("Failed the Test " + e.getMessage());
		}
		return element;
	}

	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void click(String locatorKey) {

		// driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
		try {
			getElement(locatorKey).click();
			wait(2);
		} catch (Exception e) {
			//reportFailure(e.getMessage());
		}

	}

	public void clear(String locatorKey) {

		// driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
		try {
			getElement(locatorKey).clear();
			wait(2);
		} catch (Exception e) {
			//reportFailure(e.getMessage());
		}

	}
	
	public void select(String locatorKey,String visibleText) {

		// driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
		try {
			select=new Select(getElement(locatorKey));
			wait(2);
		    select.selectByVisibleText(visibleText);
			wait(2);
		} catch (Exception e) {
			//reportFailure(e.getMessage());
		}

	}
	
	public void write(String locatorKey, String data) {

		// driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys(data);
		try {
			getElement(locatorKey).sendKeys(data);
		} catch (Exception e) {
			//reportFailure(e.getMessage());
		}

	}

	public String read(String locatorKey) {
		return getElement(locatorKey).getText();
	}

	public boolean existsElement(String locatorKey) {
	    try {
	    	getElement(locatorKey);
	        //driver.findElement(By.xpath(objectProp.getProperty(locatorKey)));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	public static String getdate(){
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		Date today = Calendar.getInstance().getTime();  
		String reportDate = df.format(today);
		
		return reportDate;
	}
	
	//getElementByXPath function for static xpath
	public WebElement getElementByXPath(String Key){
		try{
			//This block will find element using Key value from web page and return It.
			return driver.findElement(By.xpath(Object.getProperty(Key)));
		}catch(Throwable t){
			//If element not found on page then It will return null.
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//getElementByXPath function for dynamic xpath
	public WebElement getElementByXPath(String Key1, int val, String key2){
		try{
			//This block will find element using values of Key1, val and key2 from web page and return It.
			return driver.findElement(By.xpath(Object.getProperty(Key1)+val+Object.getProperty(key2)));
		}catch(Throwable t){
			//If element not found on page then It will return null.
			//Add_Log.debug("Object not found for custom xpath");
			return null;
		}
	}
	
	//Call this function to locate element by ID locator.
	public WebElement getElementByID(String Key){
		try{
			return driver.findElement(By.id(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by Name Locator.
	public WebElement getElementByName(String Key){
		try{
			return driver.findElement(By.name(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by cssSelector Locator.
	public WebElement getElementByCSS(String Key){
		try{
			return driver.findElement(By.cssSelector(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by ClassName Locator.
	public WebElement getElementByClass(String Key){
		try{
			return driver.findElement(By.className(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by tagName Locator.
	public WebElement getElementByTagName(String Key){
		try{
			return driver.findElement(By.tagName(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by link text Locator.
	public WebElement getElementBylinkText(String Key){
		try{
			return driver.findElement(By.linkText(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}
	
	//Call this function to locate element by partial link text Locator.
	public WebElement getElementBypLinkText(String Key){
		try{
			return driver.findElement(By.partialLinkText(Object.getProperty(Key)));
		}catch(Throwable t){
			//Add_Log.debug("Object not found for key --"+Key);
			return null;
		}
	}

}
