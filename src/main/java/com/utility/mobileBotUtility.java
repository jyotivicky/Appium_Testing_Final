package com.utility;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.BaseClass.TestBase;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

    public class mobileBotUtility extends TestBase {
//	public static AppiumDriver driver;
	public static TimeUtils timeUtils;
	
	/*
	 * to Scroll Down or Up based on the argument we pass
	 */
	public static void scrollUpAndDown(String direction) {
		Dimension dim = getTheDriver().manage().window().getSize();
		int x = dim.width / 2;
		int startY = 0;
		int endY = 0;

		switch (direction) {
		case "up":
			startY = (int) (dim.getHeight() * 0.8);
			endY = (int) (dim.getWidth() * 0.2);
			break;

		case "down":
			startY = (int) (dim.getHeight() * 0.2);
			endY = (int) (dim.getWidth() * 0.8);
			break;
		}
		TouchAction ts = new TouchAction(getTheDriver());
		ts.press(PointOption.point(x, startY)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(PointOption.point(x, endY)).release().perform();
	}
	/*
	 * to check whether element is visible or not in the UI
	 * It will return True or False
	 */
	public static boolean isDisplay(final By e) {
		getTheDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(getTheDriver(), 20);
			return wait.until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					if (driver.findElement(e).isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e2) {
			return false;
		}

	}
	
	/*
	 * To Scroll the Page 
	 */
	public static void scrollToElement(By e, String direction) {
		for(int i=0;i<100;i++) {
			if(isDisplay(e)) {
				break;
			}else {
				if(direction.equalsIgnoreCase("up")) {
					scrollUpAndDown("up");
					}else {
						scrollUpAndDown("down");
					}
				}
			}
			
		}
	
	/*
	 * to wait for an element until it's visible in the page
	 */
	public static void waitForVisibility(MobileElement element,AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeUtils.Wait20Seconds);	
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/*
	 * to click on any element(Button or Link)
	 */
	public static void click(MobileElement ele,AppiumDriver driver) {
		waitForVisibility(ele, driver);
		ele.click();
	}
	
	/*
	 * to pass text inside a text box
	 */
	public static void sendTheText(MobileElement ele,String text,AppiumDriver driver) {
		waitForVisibility(ele, driver);
		ele.sendKeys(text);
	}

	/*
	 * Horizontal Swipe by percentages
	 */
	public static void horizontalSwipeByPercentage(AndroidDriver driver, double startPercentage, double endPercentage,
			double anchorPercentage) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.height * anchorPercentage);
		int startPoint = (int) (size.width * startPercentage);
		int endPoint = (int) (size.width * endPercentage);

		TouchAction touch = new TouchAction(driver).press(point(startPoint, anchor))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endPoint, anchor)).release().perform();
	}

	/*
	 * Vertical Swipe by percentages
	 */
	public static void verticalSwipeByPercentages(AndroidDriver driver,double startPercentage, double endPercentage,
			double anchorPercentage) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width * anchorPercentage);
		int startPoint = (int) (size.height * startPercentage);
		int endPoint = (int) (size.height * endPercentage);

		TouchAction touch = new TouchAction(driver).press(point(anchor, startPoint))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(anchor, endPoint)).release().perform();
	}

	/*
	 * to scroll the mobile screen horizontally
	 */
	public static void horizontalScroll(int k, AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int x_start = (int) (size.width * 0.60);
		int x_end = (int) (size.width * 0.30);
		int y = 130;
		TouchAction touch = new TouchAction(driver);
		for (int i = 0; i <= k; i++) {
			touch.press(PointOption.point(x_end, x_start)).moveTo(PointOption.point(y, x_start)).release().perform();
		}
	}

	/*
	 * to drag the top of the drop down
	 */
//	public static String dragTheTop() {
//		driver.openNotifications();
//		String otp = driver.findElementByXPath("//*[contains(@text,'is')]").getText().split("code:")[0];
//		return otp;
//	}

	/*
	 * to scroll to an perticular element
	 */
	public static void scrollToElement(WebElement element, AndroidDriver driver) {
		TouchActions action = new TouchActions(driver);
		action.scroll(element, 20, 200);
		action.perform();
	}

	// *******************************************************************************************************************************//
	// *******************************************************************************************************************************//
	// *******************************************************************************************************************************//

//	/*
//	 * Tap to an text for 250 milliseconds
//	 */
//	public static void tapByText(AndroidDriver driver,String str) {
//	    TouchAction touch = new TouchAction(driver);
//	    		touch.tap(tapOptions().withElement(element(str))
//	        .waitAction(waitOptions(Duration.ofMillis(250))).perform().release();
//	 }

	/*
	 * Swipe by elements
	 */
	public static void swipeByElements(AndroidDriver driver, MobileElement startElement, MobileElement endElement)
			throws InterruptedException {
		TouchAction act = new TouchAction(driver);
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
		int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
		int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
		do {
			act.press(point(startX, startY)).waitAction(waitOptions(ofMillis(2000))).moveTo(point(endX, endY)).release()
					.perform();
		} while (!endElement.isDisplayed());
	}

	/*
	 * to move the seek bar
	 */
	public static void moveSeekBar(AppiumDriver<MobileElement> driver, MobileElement ele, double dou) {
		waitForVisibility(ele, driver);
		int startX = ele.getLocation().getX();
		int startY = ele.getLocation().getY();
		int endX = ele.getSize().getWidth();
		TouchAction action = new TouchAction(driver);
		int moveSeekBar = (int) (endX * dou);
		action.press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(moveSeekBar, startY))
				.release().perform();
	}

	/*
	 * to swipe horizontally an element
	 */
	public static void swipeHorizontallyTillEnd(AndroidDriver driver, List<MobileElement> web, int i) {
		int screenWidth = (int) driver.manage().window().getSize().width;
		int screenWidth90 = (int) (.9 * screenWidth);
		int screenWidth65 = (int) (.65 * screenWidth);
		int screenWidth40 = (int) (.4 * screenWidth);
		List<MobileElement> ListElement = web;
		TouchAction touchAction = new TouchAction(driver);
		MobileElement elel = ListElement.get(i);
		int iii = elel.getLocation().getY();

		touchAction.press(point(screenWidth90, iii)).moveTo(point(screenWidth65, iii)).moveTo(point(screenWidth40, iii))
				.waitAction(waitOptions(ofMillis(2000))).release().perform();
	}

	/*
	 * to drag one element to another element
	 */
	public static void dragFirstElementToSecondElement(AndroidDriver driver, WebElement element1) {
		TouchAction action = new TouchAction(driver);
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element1))
				.withDuration(ofSeconds(4))).perform().release();
	}

	/*
	 * to long press on any element
	 */
	public static void longPressOnElement(AndroidDriver driver, WebElement element1, WebElement element2) {
		TouchAction action = new TouchAction(driver);
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element1))
				.withDuration(ofSeconds(4))).moveTo(element(element2)).perform().release();
	}

	/*
	 * Tap by coordinates
	 */
	public static void tapByCoordinates(AndroidDriver driver, int x, int y) {
		TouchAction touch = new TouchAction(driver).tap(point(x, y)).waitAction(waitOptions(Duration.ofMillis(250)))
				.perform();
	}

	/*
	 * Press by element
	 */
	public static void pressByElement(AndroidDriver driver, WebElement Element, long seconds) {
		TouchAction touch = new TouchAction(driver).press(element(Element)).waitAction(waitOptions(ofSeconds(seconds)))
				.release().perform();
	}

	/*
	 * Press by coordinates
	 */
	public static void pressByCoordinates(AndroidDriver driver, int x, int y, long seconds) {
		TouchAction touch = new TouchAction(driver).press(point(x, y)).waitAction(waitOptions(ofSeconds(seconds)))
				.release().perform();
	}

	/*
	 * Multitouch action by using an android element
	 */
	public static void multiTouchByElement(AndroidElement androidElement, AndroidDriver driver) {
		TouchAction press = new TouchAction(driver).press(element(androidElement)).waitAction(waitOptions(ofSeconds(1)))
				.release();
		MultiTouchAction mulTouch = new MultiTouchAction(driver);
		mulTouch.add(press);
		mulTouch.perform();
	}

	/*
	 * click based on text using UI Automator Viewer
	 */
	public static void clickOnText(AndroidDriver driver, String str) {
		driver.findElementByAndroidUIAutomator("text(\"str\")").click();
	}

	/*
	 * click based on text using UI Automator Viewer
	 */
	public static void clickOnTextInteger(AndroidDriver driver, int i) {
		driver.findElementByAndroidUIAutomator("//*[@content-desc=i]").click();
	}

	/*
	 * click based on text using UI Automator Viewer
	 */
	public static void clickOnTextString(AndroidDriver driver, String str) {
		driver.findElementByAndroidUIAutomator("//*[@content-desc=\"str\")]").click();
	}


	/*****************************
	 * Working Codes Down Below
	 ********************************************/

	/*
	 * This functions are to perform the scroll up
	 */
	public static void scrollUp(AppiumDriver<MobileElement> driver) {
		TouchAction act = new TouchAction(driver);
		int height = driver.manage().window().getSize().getHeight();
		act.press(point(5, height / 3)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(5, height * 2 / 3))
				.release().perform();
	}

	public static void scrollDown(AppiumDriver<MobileElement> driver) {
		TouchAction act = new TouchAction(driver);
		int height = driver.manage().window().getSize().getHeight();
		act.press(point(5, height * 2 / 3)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(5, height / 3))
				.release().perform();
	}
	
	/*
	 * s Tap to an element for 250 milliseconds
	 */
	public static void tapOnElement(AppiumDriver driver, MobileElement Element) {
		waitForVisibility(Element, driver);
		TouchAction touch = new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(Element))).waitAction(waitOptions(Duration.ofMillis(3000))).perform();
	}

	/*
	 * it will scroll down the screen based on the number (WORKING FINE)
	 */
	public static void scrollDownTillFindTheText(AppiumDriver<MobileElement> driver, String text, MobileElement element) {
		try {
			for (int i = 1; i <= 100; i++) {
				scrollDown(driver);
				String str = driver.getPageSource();
				if (str.contains(text)) {
					waitForVisibility(element, driver);
					tapOnElement(driver, element);
					break;
				} else {
					i++;
				}
			}
		} catch (Exception e) {
			System.out.println(e + " : Is Coming");
		}
	}

	/*
	 * it will scroll down the screen based on the number (WORKING FINE)
	 */
	public static void scrollUpTillFindTheText(AndroidDriver driver, String text, MobileElement element) {
		try {
			for (int i = 1; i <= 100; i++) {
				scrollUp(driver);
				String str = driver.getPageSource();
				if (str.contains(text)) {
					tapOnElement(driver, element);
					break;
				} else {
					i++;
				}
			}
		} catch (Exception e) {
			System.out.println(e + " : Is Coming");
		}
	}

	/*
	 * scroll down till weblement found
	 */
	public static void scrollDownTillFindElement(AndroidDriver driver, String st) throws InterruptedException {
		try {
			String str = driver.getPageSource();
			do {
				scrollDown(driver);
			} while (!str.contains(st));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*
	 * scroll up till weblement found
	 */
	public static void scrolUpTillFindElement(AndroidDriver driver, String st, MobileElement element) {
		try {
			String str = driver.getPageSource();
			do {
				scrollDown(driver);
			} while (!str.contains(st));
		} catch (Exception e) {
			System.out.println(e);
		}
		tapOnElement(driver, element);
	}
	
	/*
	 * to capture the text and return
	 */
	public static void logValidatation(AppiumDriver<MobileElement> driver, MobileElement element, String Expected, String extentMessage) {
		String Actual = element.getText();
		Assert.assertEquals(Actual, Expected, extentMessage);
	}
	
	public static void logValidatation2(AppiumDriver<MobileElement> driver,MobileElement element,String Expected, String extentMessage) {
		String actualString = element.getText();
		Assert.assertTrue(actualString.contains(Expected),extentMessage);
	}

	/*
	 * To log the message
	 */
	public static void logMsg(String StatusMessage) {
		timeUtils.test.log(Status.PASS, StatusMessage);
	}

	/*
	 * to wait till load the page using Implicitly wait
	 */
	public static void waitFor_implicitly(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(timeUtils.Implictly_Wait, TimeUnit.SECONDS);
	}

	/*
	 * to wait till 1 second forcefully
	 */
	public static void waitFor_20() throws InterruptedException {
		Thread.sleep(timeUtils.Wait_2000);
	}

	/*
	 * to wait till 2 second forcefully
	 */
	public static void waitFor_30() throws InterruptedException {
		Thread.sleep(timeUtils.Wait_3000);
	}

	/*
	 * to go back
	 */
	public static void navigateBack(AndroidDriver driver) {
		driver.navigate().back();
	}

	/*
	 * This will Scroll the page till the bottom
	 */
	public static void scrollTillBottom(AndroidDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int startY = (int) (height * 0.20);
		int endY = (int) (height * 0.80);
		TouchAction ts = new TouchAction(driver);
		ts.press(PointOption.point(x, startY)).waitAction(waitOptions(ofMillis(1000))).
		moveTo(PointOption.point(x, endY)).release().perform();
	}

	/*
	 * This will Scroll the page till the top
	 */
	public static void scrollTillTop(AndroidDriver driver) {
		Dimension dim1 = driver.manage().window().getSize();

		int height1 = dim1.getHeight();
		int width1 = dim1.getWidth();
		int x = width1 / 2;

		int startY = (int) (height1 * 0.80);
		int endY = (int) (height1 * 0.20);
		TouchAction ts1 = new TouchAction(driver);
		ts1.press(PointOption.point(x, endY)).waitAction(waitOptions(ofMillis(1000))).
		moveTo(PointOption.point(x, startY)).release().perform();
	}
	
	
	/*
	 * to swipe an element horizontally
	 */
	public static void scrollToHorizantalWay(AppiumDriver driver,String text){
		
		List<AndroidElement> list = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + text + "\"))"));
		AndroidElement first = list.get(0);
		AndroidElement third = list.get(2);	
		int cord = first.getLocation().y+ (first.getSize().height/2);
		int firstX = first.getLocation().x;
		int thirdY = third.getLocation().x;
		TouchAction touch = new TouchAction(driver).press(point(thirdY, cord))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(firstX, cord)).release().perform();
		
	}
	
	
	public static void swipeElementWithDirection(AndroidDriver driver,AndroidElement element, String direction) {

		double endXPercen = 0.9;
		int startX, startY, endX, endY;

		int screenWidth = driver.manage().window().getSize().width;
		int screenHeight = driver.manage().window().getSize().height;

		switch (direction) {
		case "TOP":
		startX = element.getCenter().getX();
		startY = element.getSize().getHeight();

		endX = startX;
		endY = screenHeight - (int) (screenHeight * endXPercen);
		break;
		case "right":
		startX = element.getCenter().getX();
		startY = element.getCenter().getY();

		endX = (int) (screenWidth * endXPercen);
		endY = startY;
		break;
		case "left":
		startX = element.getSize().getWidth();
		startY = element.getCenter().getY();

		endX = screenWidth - (int) (screenWidth * endXPercen);
		endY = startY;
		break;

		default:
		startX = element.getCenter().getX();
		startY = element.getLocation().getY();

		endX = startX;
		endY = (int) (screenHeight * endXPercen);
		break;
		}

		Point startPoint = new Point(startX, startY);
		PointOption startPointOption = new PointOption().withCoordinates(startPoint);

		Point endPoint = new Point(endX, endY);
		PointOption endPointOption = new PointOption().withCoordinates(endPoint);

		WaitOptions waitOptions = new WaitOptions().withDuration(Duration.ofMillis(500));
		TouchAction ts1 = new TouchAction(driver);

//		Log.addLog("Start Point: " + startPoint + " and End point: " + endPoint);
		ts1.press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).waitAction().release().perform();
     }
    }
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    