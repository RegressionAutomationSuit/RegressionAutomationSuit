package gotv;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import util.Base;
import util.ScreenCapture;

public class ClearErrorCode extends Base {
		
	
	@BeforeMethod
	public void openBrowser() throws Exception{
		try{
						
			loadWebBrowser();
		    System.out.println("Title: "+driver.getTitle());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TSS_ClearErrorCode() throws Exception {
		
			ExecutionDate = getdate();
			TC_Name = this.getClass().getSimpleName();
			
			test.log(LogStatus.INFO, "Testcase clear error started");
			Thread.sleep(5000);
		 	driver.findElement(By.xpath("//span[@class='caret']")).click();
		    driver.findElement(By.xpath("//span[.='Nigeria'][@class='countryName']")).click();
		    driver.findElement(By.xpath("//span[.='Sign in']")).click();
		    driver.findElement(By.xpath("//input[@id='signIn_surnameOrMobile']")).sendKeys("SHUBHA");
		    driver.findElement(By.xpath("//input[@id='signIn_smartCardNumber']")).sendKeys("32529302");
		    driver.findElement(By.xpath("//button[@id='btnSignInWithEazy']")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//a[@href='/en/ng/clear-error-code']/span[.='Clear Error Code']")).click();
		    Thread.sleep(3000);
		    select=new Select(driver.findElement(By.id("ddlSmartCards")));
		    select.selectByVisibleText("2004468938");
		    select=new Select(driver.findElement(By.id("ddlErrorCodes")));
		    select.selectByVisibleText("GOTVE30");
		    Thread.sleep(20000);
		    driver.findElement(By.xpath("//span[contains(text(),'Error has been cleared successfully.')]")).click();
		    
		    ScreenCapture.ActiveSnapShot("2. Error Cleared Successfully, ", this.getClass().getSimpleName());
		    
		    test.log(LogStatus.INFO, "Test case clear error complited successfully");

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
			closeWebBrowser();
		}
	
	@AfterTest
	public void clouseBrowse(){
		try{
			test.log(LogStatus.INFO, "clousing Browser");
			closeWebBrowser();
		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
	}
	
}
