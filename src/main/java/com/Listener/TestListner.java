package com.Listener;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.BaseClass.TestBase;
import com.Report.TestReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.utility.TestUtility;

public class TestListner implements ITestListener {
	TestUtility utils = new TestUtility();
	TestBase base = new TestBase();

	public void onTestFailure(ITestResult result) {
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}

		TestBase base = new TestBase();		
		File file = base.getTheDriver().getScreenshotAs(OutputType.FILE);

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> params = new HashMap<String, String>();
		params = result.getTestContext().getCurrentXmlTest().getAllParameters();

		String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
				+ File.separator + base.getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("Test Case Got Failed Screenshot Taken");
			Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath
					+ "' height='400' width='400'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			TestReport.getTest().fail("Test Case Got Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());

			TestReport.getTest().fail("Test Case Got Failed", MediaEntityBuilder
					.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
        TestReport.getTest().fail(result.getThrowable());
	}

	@Override
	public void onTestStart(ITestResult result) {
		TestBase base = new TestBase();
		TestReport.startTest(result.getName(), result.getMethod().getDescription())
				.assignCategory(base.getPlatform() + "_" + base.getDeviceName()).assignAuthor("Jyotiprakash Das");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TestReport.getTest().log(Status.PASS, "Test Case Got Passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		TestReport.getTest().log(Status.SKIP, "Test Case Got Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		TestReport.getReporter().flush();
	}

}
