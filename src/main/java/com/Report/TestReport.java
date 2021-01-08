package com.Report;

import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

    public class TestReport {
	
    static ExtentReports extent;
    final static String filePath = "Extent.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
        	ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
        	html.config().setDocumentTitle("Appium Framework");
        	html.config().setReportName("Appium Testing");
        	html.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(html);
        }       
        return extent;
    }
      
    /*
     * Here we are gtting the object which is created below by reading the Hashmap and we are retreving the test object
     * for the currect thread which is running and we are using synchronized to handle parallel execution
     */
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /*
     * to start the test case and take the test case and description as the argument. then we are calling the getReporter 
     * method and first it will be null and create an HTML report and return then, by using that object we can create our 
     * object in ExtentTest Test, By using the hashmap we are creating an entry and getting the id and mapping the test which
     * is just got created
     */
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

}
