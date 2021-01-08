package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

    public class SettingPage extends TestBase {

	@AndroidFindBy(accessibility =  "test-LOGOUT") private MobileElement logoutButton;

	public LoginPage pressLogOutBtn() {
		waitForVisibilty(logoutButton);
		click(logoutButton);
		return new LoginPage();
	}
}
