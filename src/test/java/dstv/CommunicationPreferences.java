package dstv;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import util.Base;
import util.ExtentTestManager;
import util.Xls_Reader;

public class CommunicationPreferences extends Base{
	
	String testCaseName = this.getClass().getSimpleName();
	Xls_Reader xls;
	
	@BeforeTest
	public void checkTestRunnable() 
	{
		xls=new Xls_Reader(configProp.getProperty("SUITE_DSTV_MOZILLA_XLS_PATH"));
		
		if (!isRunnable(dataSheetName, xls)) {
			
			 ExtentTestManager.getTest().log(LogStatus.SKIP, "RunMode status set to N, hence skipping the test");
			 System.out.println("Runmode set to No");
			 throw new SkipException("Skipping the test");
		}
		else
		{
			loadWebBrowser();
		}
	}
	
	@Test(dataProvider = "getData")
	public void communicationPreferences(Hashtable<String, String> data)
	{	
		try 
		{	    	
			ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase Communication Preferences started");    
			
		    //wait(3);
			click("click_communication_preferences_xpath");
		    //driver.findElement(By.xpath("//i[@class='account-communication-preferences']")).click();
		    wait(5);
		    click("sms_xpath");
		    click("email_xpath");
		    
		    if(existsElement("reason_communication_preferences_id"));
		    {
		    	select("reason_communication_preferences_id",data.get("Reason"));
		    	//select=new Select(driver.findElement(By.id("addressCity")));
			    //select.selectByVisibleText("ABA");
		    }
		    
		    click("update_communication_preferences_xpath");
		    //driver.findElement(By.xpath("//input[contains(@value,'SMS')]")).click();
		    //driver.findElement(By.xpath("//input[contains(@name,'Email')]")).click();
		    //driver.findElement(By.xpath("//button[@id='btnUpdate']")).click();
		    wait(3);
		    
		    clear("update_sms_xpath");
		    write("update_sms_xpath", data.get("NewMobile"));
		    
		    clear("update_email_xpath");
		    write("update_email_xpath", data.get("Email"));
		    
		    //driver.findElement(By.xpath("//input[@id='txtEmail']")).clear();
		    //driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(data.get("Email"));
		    click("change_communication_preferences_xpath");
		    //driver.findElement(By.xpath("//button[.='Update']")).click();
		    wait(5);
		    
		    boolean element=existsElement("communication_preferences_positive_assert_xpath");
		    
		    if (dataSheetName.toLowerCase().contains("negative"))
		    {
		    	if(existsElement("communication_preferences_negative_assert_xpath"))
		    	{
				   	ExtentTestManager.getTest().log(LogStatus.PASS, "Log from threadId: " + Thread.currentThread().getId());
				   	//Assert.assertEquals(ExtentTestManager.getTest().getRunStatus(), LogStatus.PASS);
		    	}
		    	else if(existsElement("communication_preferences_same_assert_xpath"))
		    		ExtentTestManager.getTest().log(LogStatus.PASS, "Log from threadId: " + Thread.currentThread().getId());
		    	else 
		    	{
		 	    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Log from threadId: " + Thread.currentThread().getId());
		 	    	System.out.println("in negative else element: ");
		    	}
		    }
		    else if(!dataSheetName.toLowerCase().contains("negative"))
		    {
		    	if(existsElement("communication_preferences_positive_assert_xpath"))
		    	{
				   	ExtentTestManager.getTest().log(LogStatus.PASS, "Log from threadId: " + Thread.currentThread().getId());
				   	//Assert.assertEquals(ExtentTestManager.getTest().getRunStatus(), LogStatus.PASS);
				   	System.out.println("in positive pass if element: ");
		    	}
		    	else 
		    	{
		 	    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Log from threadId: " + Thread.currentThread().getId());
		 	    	System.out.println("in positive else element: "+element);
		    	}
		    }
		    ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase Communication Preferences completed on "+browserType);
			
			System.out.println("result.getStatus():  "+ExtentTestManager.getTest().getRunStatus());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@DataProvider
	public Object[][] getData() {

		try {
			xls = new Xls_Reader(configProp.getProperty("SUITE_DSTV_MOZILLA_XLS_PATH"));
			//test = rep.startTest(testCaseName);

			if (!Base.isRunnable(testCaseName, xls)) {
				System.out.println("Runmode set to No");
				//test.log(LogStatus.SKIP,"Skipping the test case as Runmode is set to NO");
				//throw new SkipException("Skipping the test case as Runmode is set to NO");
			}		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base.getTestData(xls, testCaseName);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		getResults(result);
	}

	@AfterTest
	public void quitBrowse()
	{
		try
		{
			closeWebBrowser();
		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
	}
}
