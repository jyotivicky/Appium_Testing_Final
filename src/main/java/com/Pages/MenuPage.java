package com.Pages;

import com.BaseClass.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class MenuPage extends TestBase {

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n"
			+ "")
	private MobileElement settingsBtn;

	public SettingPage pressSettingsBtn() {
		waitForVisibilty(settingsBtn);
		click(settingsBtn);
		return new SettingPage();
	}

  }
