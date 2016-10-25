package gotv;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import util.Base;
import util.ExtentTestManager;
import util.Xls_Reader;

public class SignIn extends Base {
	
	String testCaseName = this.getClass().getSimpleName();
	Xls_Reader xls;
	String path;
	
	@BeforeTest
	public void checkTestRunnable() {
		xls=new Xls_Reader(configProp.getProperty("SUITE_GOTV_MOZILLA_XLS_PATH"));
		
		if (!Base.isRunnable(testCaseName, xls)) {
			System.out.println("Runmode set to No");
		}
		loadWebBrowser();
	}

	/*@BeforeMethod
	public void init() 
	{
		System.out.println("Extent in signIN before method: "+extent);
		if (driver == null) 
		{		
			loadWebBrowser();
		}
	}*/

	@Test(dataProvider = "getData")
	public void signIn(Hashtable<String, String> data) throws Exception
	{	
		System.out.println("ExtentTestManager.getTest() in signIN at Test: "+ExtentTestManager.getTest());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log from threadId: " + Thread.currentThread().getId());	        
		ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase GoTV SignIn started");    
	    
		System.out.println("Title1: "+driver.getTitle());
		
	   	wait(5);
		click("gotv_countrySelect_dropdown_xpath");
		path=objectProp.getProperty("gotv_selectCountry_part1_xpath")+data.get("CountryName")+objectProp.getProperty("gotv_selectCountry_part2_xpath");
		System.out.println("Path: "+path);
		click(path);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Country "+data.get("CountryName")+" selected");
		wait(2);
	    click("clickSignIn_xpath");
	    write("username_xpath", data.get("Mobile"));
	    write("password_xpath", data.get("CuctomerID"));
	    click("buttonSignIn_xpath");
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Sign In button clicked");
	    wait(10);
	    /*driver.findElement(By.xpath("//span[@class='caret']")).click();
	    //driver.findElement(By.xpath("//span[.='Nigeria'][@class='countryName']")).click();
	    //driver.findElement(By.xpath("//span[.='Sign in']")).click();
	    driver.findElement(By.xpath("//input[@id='signIn_surnameOrMobile']")).sendKeys("SHUBHA");
	    driver.findElement(By.xpath("//input[@id='signIn_smartCardNumber']")).sendKeys("32529302");
	    driver.findElement(By.xpath("//button[@id='btnSignInWithEazy']")).click();*/
		
		/*    
	    if(existsElement("signIn_positive_assert_xpath") )
	    {
		    String actual= read("signIn_positive_assert_xpath");
		    System.out.println("Actual: "+actual+ " Expected: "+data.get("Expected"));
		    
		    if(actual.equalsIgnoreCase(data.get("Expected")))
		    {
		    	System.out.println("Expected== actual");
		    	Assert.assertEquals(read("signIn_positive_assert_xpath"), data.get("Expected"), "Testcase SignIn completed successfull with valid user");
		    }			    
	    }
	    else
	    {
	    	String actual= read("signIn_negative_assert_xpath");
		    System.out.println("Actual:   "+actual + "\nExpected: "+data.get("Expected"));
		    
		    if(actual.equalsIgnoreCase(data.get("Expected")))
		    {
		    	System.out.println("Expected== actual");
		    	Assert.assertEquals(actual, data.get("Expected"), "Testcase SignIn completed with invalid user");
		    }
	    }*/
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase SignIn completed successfull");
	}
	
	@DataProvider
	public Object[][] getData() 
	{
		try {
			xls = new Xls_Reader(configProp.getProperty("SUITE_GOTV_MOZILLA_XLS_PATH"));

			if (!Base.isRunnable(testCaseName, xls)) {
				System.out.println("Runmode set to No");
			}		
			//super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("xls: "+xls+" testCaseName: "+testCaseName+" Sheetname: "+dataSheetName);
		return Base.getTestData(xls, dataSheetName);		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
    	Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		Date today = Calendar.getInstance().getTime();  
		String reportDate = df.format(today);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);

		// Here will compare if test is failing then only it will enter into if condition
		if(result.getStatus()==ITestResult.FAILURE){				
			try {
							
				FileUtils.copyFile(source, new File("./Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for failure"+browserName);
				filePath = "../Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				System.out.println(filePath);
				image = ExtentTestManager.getTest().addScreenCapture(filePath);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Case :"+result.getName()+" -Failed"+result.getThrowable(), image);	
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
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case :"+result.getName()+" -Passed"+result.getThrowable(), image);
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
				ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case :"+result.getName()+" -skipped " +result.getThrowable(),image);
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}	
	    }
	}

	@AfterTest
	public void quitBrowse(){
		try{
			closeWebBrowser();
		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
	}	
}
