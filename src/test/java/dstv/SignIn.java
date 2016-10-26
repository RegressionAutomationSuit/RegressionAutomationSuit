package dstv;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Hashtable;
import util.Base;
import util.ExtentTestManager;
import util.Xls_Reader;

public class SignIn extends Base{
	String testCaseName = this.getClass().getSimpleName();
	Xls_Reader xls;
	String path;
	
	@BeforeTest
	public void checkTestRunnable() {
		xls=new Xls_Reader(configProp.getProperty("SUITE_DSTV_MOZILLA_XLS_PATH"));
		
	
		if (!isRunnable(dataSheetName, xls)) {
			
			 ExtentTestManager.getTest().log(LogStatus.SKIP, "RunMode status set to N, hence skipping the test");
			 System.out.println("Runmode set to No");
			 throw new SkipException("Skipping the test");
		}
		else
			loadWebBrowser();
	}
	
	@BeforeMethod
	public void init() 
	{
		if (driver == null) 
		{			
			loadWebBrowser();
		}
		//softAssert = new SoftAssert();
	}
	@Test(dataProvider = "getData")
	public void signIn(Hashtable<String, String> data) throws Exception
	{	
		/*String key=null;
		Enumeration<String> e=data.keys();
		 while(e.hasMoreElements()) {
		      key = (String) e.nextElement();
		      //System.out.println("Key: " +key+ " & Value: " +data.get(key));
		   }
		System.out.println("Number of records in signIn are: "+data.size());*/
		//Second push
		
		System.out.println("ExtentTestManager.getTest() in signIN at Test: "+ExtentTestManager.getTest());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Log from threadId: " + Thread.currentThread().getId());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase SignIn started");    
		System.out.println("Title1: "+driver.getTitle());
       
    	click("dstv_countrySelect_dropdown_xpath");
    	ExtentTestManager.getTest().log(LogStatus.INFO, "CountryDrop selected"); 
	    new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Nigeria'][@data-reactid='127']")));
	    path=objectProp.getProperty("selectCountryNigeria_part1_xpath")+data.get("CountryName")+objectProp.getProperty("selectCountryNigeria_part2_xpath")+data.get("DataReactId")+objectProp.getProperty("completeBracket_xpath")+"_xpath";
	    System.out.println("Path: "+path);
	    click(path);
	    wait(2);
	    click("clickSignIn_xpath");
	    write("username_xpath", data.get("Mobile"));
	    write("password_xpath", data.get("CuctomerID"));
	    click("buttonSignIn_xpath");
	    wait(5);
	    
	    //boolean element=existsElement("signIn_positive_assert_xpath");
	    
	    if (dataSheetName.toLowerCase().contains("negative"))
	    {
	    	if(existsElement("signIn_negative_assert_xpath"))
	    	{
			   	ExtentTestManager.getTest().log(LogStatus.PASS, "Log from threadId: " + Thread.currentThread().getId());
			   	Assert.assertEquals(ExtentTestManager.getTest().getRunStatus(), LogStatus.PASS);
	    	}
	    	else 
	    	{
	 	    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Log from threadId: " + Thread.currentThread().getId());
	 	    	System.out.println("in negative else element: ");
	    	}
	    }
	    else if(!dataSheetName.toLowerCase().contains("negative"))
	    {
	    	if(existsElement("signIn_positive_assert_xpath"))
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
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Testcase SignIn completed successfully on "+browserType);
		
		System.out.println("result.getStatus():  "+ExtentTestManager.getTest().getRunStatus());
	}
	
	@DataProvider
	public Object[][] getData() {
		try {
			xls = new Xls_Reader(configProp.getProperty("SUITE_DSTV_MOZILLA_XLS_PATH"));

			if (!Base.isRunnable(testCaseName, xls)) {
				System.out.println("Runmode set to No");
			}		
			//super.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("xls: "+xls+" testCaseName: "+testCaseName+" Sheetname: "+dataSheetName);
		
		//return Base.getTestData(xls, testCaseName);
		/*Object[][] object=Base.getTestData(xls, dataSheetName);
		System.out.println("Length"+object.length);
		System.out.println("After length");
		System.out.println("object[0][0]"+object[0][0]);
		System.out.println(object.toString());
		System.out.println("After object.toString()");*/
		return Base.getTestData(xls, dataSheetName);
		//return object;
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		System.out.println("ITestResult.FAILURE "+ITestResult.FAILURE);
		System.out.println("ITestResult.SUCCESS "+ITestResult.SUCCESS);
		System.out.println("result.getStatus(): "+result.getStatus());
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
