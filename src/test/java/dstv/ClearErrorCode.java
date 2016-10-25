package dstv;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Base;
import util.Constants;
import util.Xls_Reader;

import com.relevantcodes.extentreports.LogStatus;

public class ClearErrorCode extends Base{
	String testCaseName = this.getClass().getSimpleName();
	Xls_Reader xls;
	@BeforeTest
	public void checkTestRunnable() 
	{
		// System.out.println(testCaseName);
		xls=new Xls_Reader(configProp.getProperty("SUITE_DSTV_MOZILLA_XLS_PATH"));
		//test = rep.startTest(testCaseName);

		if (!Base.isRunnable(testCaseName, xls)) 
		{
			System.out.println("Runmode set to No");
			//test.log(LogStatus.SKIP,"Skipping the test case as Runmode is set to NO");
			//throw new SkipException("Skipping the test case as Runmode is set to NO");
		}
		loadWebBrowser();
	}

	@BeforeMethod
	public void init() 
	{
		if (driver == null) 
		{			
			Base.loadWebBrowser();
		}
		//softAssert = new SoftAssert();
	}
	@Test(dataProvider = "getData")
	public void communicationPreferences(Hashtable<String, String> data)
	{	
		try 
		{	    
			 driver.findElement(By.xpath("//i[@class='clear-error-code']")).click();
			    select=new Select(driver.findElement(By.id("ddlSmartCards")));
			    select.selectByVisibleText(data.get("SmartCardNumber"));
			    select=new Select(driver.findElement(By.id("ddlErrorCodes")));
			    select.selectByVisibleText("E16");
			    driver.findElement(By.xpath("//button[@id='btnClearError']")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//span[contains(text(),'Error has been cleared successfully')]"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			driver.quit();
		}
	}
	@DataProvider
	public Object[][] getData() {

		try {
			//super.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base.getTestData(xls, testCaseName);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
    	Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toLowerCase();
	    
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		Date today = Calendar.getInstance().getTime();  
		String reportDate = df.format(today);
		
		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus()){				
			try {
							
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for failure"+browserName);
				filePath = "../Screenshots/fail/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				System.out.println(filePath);
				image = test.addScreenCapture(filePath);
				test.log(LogStatus.FAIL, "Test Case :"+result.getName()+" -Failed", image);	
			} 
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 	 
		}
		else if (ITestResult.SUCCESS==result.getStatus()){
			try{
				
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./Screenshots/pass/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png"));
				System.out.println("Screenshot taken for Success"+browserName);
				filePath = "../Screenshots/pass/"+"TestCase Name = "+result.getName()+"-Execution Date-"+reportDate+"/"+"Browser-"+browserName+"/"+reportDate+".png";
				System.out.println(filePath);
				image = test.addScreenCapture(filePath);
				test.log(LogStatus.PASS, "Test Case :"+result.getName()+" -Passed", image);
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
		
		//rep.endTest(test);
		//rep.flush();
		closeWebBrowser();
	}

	@AfterTest
	public void quitBrowse(){
		try{
			test.log(LogStatus.INFO, "clousing Browser");
			closeWebBrowser();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			driver.quit();
		}
	}
}
