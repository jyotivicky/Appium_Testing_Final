package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;

    public class LoginTest extends TestBase {

	@Test(priority = 1, groups = "smoke")
	public void invalidUsernameTest() throws Exception {
		SoftAssert sft = new SoftAssert();
		LoginPage loginPage = new LoginPage();
		loginPage.enterUsername(testValues.getJSONObject("invalidusername").getString("username"));
		loginPage.enterPassword(testValues.getJSONObject("invalidusername").getString("password"));
		loginPage.clickLoginButton();
		sft.assertEquals(loginPage.getErrorTxt(), getStrings().get("err_invalid_username_or_password"));
		TestReport.getTest().log(Status.INFO, getStrings().get("invalid_credential_status_msg"));
		sft.assertAll();
	}

	@Test(priority = 2, groups = "smoke")
	public void invalidPasswordTest() {
		SoftAssert sft = new SoftAssert();
		LoginPage loginPage = new LoginPage();
		loginPage.enterUsername(testValues.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(testValues.getJSONObject("invalidPassword").getString("password"));
		loginPage.clickLoginButton();
		sft.assertEquals(loginPage.getErrorTxt(), getStrings().get("err_invalid_username_or_password"));
		TestReport.getTest().log(Status.INFO, getStrings().get("invalid_credential_status_msg"));
		sft.assertAll();
	}

	@Test(priority = 3, groups = "smoke")
	public void validUsernameTest() throws InterruptedException {
		SoftAssert sft = new SoftAssert();
		LoginPage loginPage = new LoginPage();
		loginPage.enterUsername(testValues.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(testValues.getJSONObject("validUser").getString("password"));
		ProductPage productsPage = loginPage.clickLoginButton();
		sft.assertEquals(productsPage.getPageTitle(), getStrings().get("page_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("Valid_credential_status_msg"));
		sft.assertAll();
	}

}
