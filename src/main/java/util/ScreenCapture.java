package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import util.Base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScreenCapture extends Base{
	public static void ActiveSnapShot(String ScreenName, String TestCaseName) throws IOException{
		
		try{
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh");
			Date today = Calendar.getInstance().getTime();  
			String reportDate = df.format(today);
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source= ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshots/Runs/TestCase Name ="+TestCaseName+"Execution Date"+reportDate+"/"+ScreenName+".png"));
			System.out.println("Screenshot taken");
		}
		catch (Exception e)
		{
			System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
	}

}
