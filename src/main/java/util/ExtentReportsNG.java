package util;


import java.io.*;
import java.util.*;

import org.testng.*;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReportsNG implements IReporter {
	
	private static ExtentReports extent;
	
    @SuppressWarnings("deprecation")
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,String arg2) {
	
    	
    }
        
        public synchronized static ExtentReports getReporter() {
        	if (extent == null) {
                extent = new ExtentReports(".\\report\\ Automation Regression Summarized Report for -"+Base.getdate()+".html", true, NetworkMode.OFFLINE);
                
                extent.addSystemInfo("Environment", "QA");
                extent.loadConfig(new File(".\\Libraries\\extentreports-java-v2.41.1\\extent-config.xml"));
            }
        	return extent;
    }
  
        /*for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
  
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
  
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }  */
       
         
       // extent.assignProject("Multichoice");
        //System.out.println("Extent project Name: "+extent.getProjectName());
        //extent.flush();
        //extent.close();

  
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
  
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
  
                //test.getTest().startedTime = getTime(result.getStartMillis());
                //test.getTest().endedTime = getTime(result.getEndMillis());
  
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
  
                String message = "Test " + status.toString().toLowerCase() + "ed";
  
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
  
                test.log(status, message);
  
                extent.endTest(test);
            }
        }
    }
  
    @SuppressWarnings("unused")
	private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }

	

}
