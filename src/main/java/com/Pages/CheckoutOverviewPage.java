package com.Pages;

import com.BaseClass.TestBase;
import com.utility.mobileBotUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

   public class CheckoutOverviewPage extends TestBase{
	   
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']") private MobileElement CheckoutOverview;

		@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']") private MobileElement CheckoutComplete;

		@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]\r\n"
				+ "")
		private MobileElement ThankYouMessage;
		
		@AndroidFindBy(accessibility = "test-FINISH") private MobileElement Finish;

		public String getPageTitleText() {
			waitForVisibilty(CheckoutOverview);
			return getText(CheckoutOverview);
		}
		
		public void clickFinishButton() throws InterruptedException {
			Thread.sleep(2000);
			mobileBotUtility.scrollUpAndDown("up");
			click(Finish);
		}
		
		public String getPageTitleCompletePage() {
			waitForVisibilty(CheckoutComplete);
			return getText(CheckoutComplete);
		}
		
		public String getThankyouMessage() {
			waitForVisibilty(ThankYouMessage);
			return getText(ThankYouMessage);
		}
		
   }
