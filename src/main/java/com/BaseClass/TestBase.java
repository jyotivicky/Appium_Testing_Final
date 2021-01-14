package com.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.utility.TestUtility;
import org.json.JSONObject;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;
import com.utility.JsonParser;
import com.utility.TimeUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class TestBase {

	protected static ThreadLocal<HashMap<String, String>> hs = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	protected static TestUtility utility = new TestUtility();
	
	public static final String USERNAME = "vicky282";
	public static final String AUTOMATE_KEY = "rmx3pMxzQxHcgZgRexxS";
	public static final String BrowserStackURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static JSONObject testValues;

	public static AppiumDriver getTheDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return hs.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		hs.set(strings2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public TestBase() {
		PageFactory.initElements(new AppiumFieldDecorator(getTheDriver()), this);
	}
	
	@BeforeClass
	public void beforeClass() throws IOException {
		closeApp();
		launchApp();
	}
	
	/*
	 * Once all the test case get executed then here we are quiting the driver instance 
	 */
	@AfterTest(alwaysRun = true)
	public void QuitTheDriver() {
		if(getTheDriver() != null) {
			getTheDriver().quit();
        }
	}

	/*
	 * Here initializing all the configuration, URL to open the Mobile App and start the execution
	 */
	@Parameters({"platformName", "platformVersion", "deviceName"})
	@BeforeTest(alwaysRun = true)
	public void DriverInitializataion(String platformName,String platformVersion,String deviceName) throws Exception {
		setDateTime(utility.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		AppiumDriver driver = null;
        setDriver(driver);

		String strFile = "Logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		utility.log().info("log path: " + strFile);

		try {
			props = new Properties();
			String proFileName = "Config.properties";
			String xmlFileName = "Messages/Messages.xml";
			
			String jsonFile = "TestData/TestData.json";
			testValues = JsonParser.parse(jsonFile);
			utility.log().info("Load " + jsonFile);
			
			utility.log().info("Load " + proFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);
			props.load(inputStream);
			setProps(props);

			utility.log().info("Load " + xmlFileName);
			stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			setStrings(utility.parseStringXML(stringsis));			
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("project", "New Mobile Automation");
			cap.setCapability("name", "New Appium Testing");
			cap.setCapability("build", "New Testing");
			
			cap.setCapability("platformName", platformName);
			cap.setCapability("device", deviceName);
			cap.setCapability("os_version", platformVersion);
			cap.setCapability("app", "bs://5af3f1f3594652dd7739482fd198944cde88d642");
			
			URL LocalBrowserStackURL = new URL(BrowserStackURL);

		    String userName = System.getenv("BROWSERSTACK_USERNAME");
		    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
		    String app = System.getenv("BROWSERSTACK_APP_ID");
		    URL ServerBrowserStackURL = new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");
		    
			switch (deviceName) {
			case "Google Pixel 2":
				cap.setCapability("automationName", props.getProperty("Android Automation On Pixel_2"));
				driver = new AndroidDriver(LocalBrowserStackURL, cap);
				break;
				
			case "Google Pixel 3":
				cap.setCapability("automationName", props.getProperty("Android Automation On Pixel_3"));
				driver = new AndroidDriver(LocalBrowserStackURL, cap);
				break;
				
			default:
				throw new Exception("Invalid platform! - " + platformName);
			}
			setDriver(driver);
			utility.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utility.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
			throw e;

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (stringsis != null) {
				stringsis.close();
			}
		}
	}

	/*
	 * This condition will check for an element for the visibilty in the Mobile UI
	 * before doing any action on the element
	 */
	public static void waitForVisibilty(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(getTheDriver(), TimeUtils.Wait);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * To clear a text field
	 */
	public void clear(MobileElement e) {
		waitForVisibilty(e);
		e.clear();
	}
	
	/*
	 * To click an element
	 */
	public static void click(MobileElement e) {
		waitForVisibilty(e);
		e.click();
	}

	/*
	 * To click an element and log
	 */
	public void click(MobileElement e, String msg) {
		waitForVisibilty(e);
		utility.log().info(msg);
		TestReport.getTest().log(Status.INFO, msg);
		e.click();
	}

	/*
	 * To enter text on textbox
	 */
	public void sendKeys(MobileElement e, String text) {
		waitForVisibilty(e);
		e.sendKeys(text);
	}

	/*
	 * To get and capture the text from the UI
	 */
	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibilty(e);
		return e.getAttribute(attribute);
	}

	/*
	 * To capture the Text from an Element
	 */
	public String getText(MobileElement e) {
		return getAttribute(e, "text");
	}

	/*
	 * To Scroll down to an perticular Element
	 */
	public MobileElement scrollToElement() {
		return (MobileElement) ((FindsByAndroidUIAutomator) getTheDriver()).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollIntoView("
						+ "new UiSelector().description(\"test-Price\"));");
	}

	/*
	 * To initialize the driver and Launch the App 
	 */
	public void launchApp() {
		((InteractsWithApps) getTheDriver()).launchApp();
	}

	/*
	 * To initialize the driver and close the App if App is already 
	 * Launched
	 */
	public void closeApp() {
		((InteractsWithApps) getTheDriver()).closeApp();
	}
}
    
    
    
    
