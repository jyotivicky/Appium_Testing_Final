package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class CheckoutInformationPage extends TestBase {
	
	@AndroidFindBy(accessibility = "test-First Name") private MobileElement FirstName;

	@AndroidFindBy(accessibility = "test-Last Name") private MobileElement LastName;

	@AndroidFindBy(accessibility = "test-Zip/Postal Code") private MobileElement PostalCode;

	@AndroidFindBy(accessibility = "test-CONTINUE") private MobileElement Continue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: INFORMATION']") private MobileElement CheckoutInformation;
	
	public void enterFirstname(String text) {
		waitForVisibilty(FirstName);
		sendKeys(FirstName, text);
	}
	
	public void enterLastname(String text) {
		waitForVisibilty(LastName);
		sendKeys(LastName, text);
	}
	
	public void enterPostalcode(String text) {
		waitForVisibilty(PostalCode);
		sendKeys(PostalCode, text);
	}
	
	public void clickContinueButton() {
		waitForVisibilty(Continue);
		click(Continue);
	}

	public String getPageTitle() {
		waitForVisibilty(CheckoutInformation);
		return getText(CheckoutInformation);
	}
  }
