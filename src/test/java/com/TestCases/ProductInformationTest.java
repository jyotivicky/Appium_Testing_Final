package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.LoginPage;
import com.Pages.MenuPage;
import com.Pages.ProductDetailsPage;
import com.Pages.ProductPage;
import com.Pages.SettingPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;

    public class ProductInformationTest extends TestBase {

	@Test(priority = 1, groups = "regression")
	public void validateProductsOnProductPage() throws Exception {
		SoftAssert sft = new SoftAssert();
		ProductPage productsPage = new LoginPage().userLogin(testValues.getJSONObject("validUser").getString("username"),
				testValues.getJSONObject("validUser").getString("password"));
		sft.assertEquals(productsPage.getBagTitle(), getStrings().get("products_page_Bag_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("products_page_Bag_title_status"));
		sft.assertEquals(productsPage.getBagPrice(), getStrings().get("products_page_Bag_price"));
		TestReport.getTest().log(Status.INFO, getStrings().get("products_page_Bag_price_status"));
		SettingPage settingPage = new MenuPage().pressSettingsBtn();
		settingPage.pressLogOutBtn();
		sft.assertAll();
	}

	@Test(priority = 2, groups = "regression")
	public void validateProductsOnProductDetailsPage() throws Exception {
		SoftAssert sft = new SoftAssert();
		ProductPage productsPage = new LoginPage().userLogin(testValues.getJSONObject("validUser").getString("username"),
				testValues.getJSONObject("validUser").getString("password"));
		ProductDetailsPage productDetailsPage = productsPage.pressBagTitle();
		sft.assertEquals(productDetailsPage.getBagTitle(), getStrings().get("products_page_Bag_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("products_details_page_Bag_title_status_msg"));
		productDetailsPage.scrollToBagPrice();
		sft.assertEquals(productDetailsPage.getBagDescription(),
				getStrings().get("product_details_page_Bag_description"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_details_page_Bag_description_status_msg"));
		sft.assertEquals(productDetailsPage.getBagPrice(), getStrings().get("product_details_page_Bag_price"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_details_page_Bag_price_status_msg"));
		productDetailsPage.pressBackToProductButton();
		SettingPage settingPage = new MenuPage().pressSettingsBtn();
		settingPage.pressLogOutBtn();
		sft.assertAll();
	}
}
