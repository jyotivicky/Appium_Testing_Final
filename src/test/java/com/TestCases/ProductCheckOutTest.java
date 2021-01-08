package com.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.BaseClass.TestBase;
import com.Pages.CartCheckoutPage;
import com.Pages.CheckoutInformationPage;
import com.Pages.CheckoutOverviewPage;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Pages.YourCartPage;
import com.Report.TestReport;
import com.aventstack.extentreports.Status;

    public class ProductCheckOutTest extends TestBase {

	@Test(priority = 1, groups = "regression")
	public void validateAddToCart() throws Exception {
		SoftAssert sft = new SoftAssert();
		ProductPage productsPage = new LoginPage().userLogin(testValues.getJSONObject("validUser").getString("username"),
				testValues.getJSONObject("validUser").getString("password"));
		CartCheckoutPage cartCheckout = new CartCheckoutPage();
		cartCheckout.pressAddToCart();
		sft.assertEquals(cartCheckout.getCartButtonText(), getStrings().get("after_added"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_added_sucessfully"));
		cartCheckout.clickCartButton();
		sft.assertAll();
	}
	
	@Test(dependsOnMethods = {"validateAddToCart"}, groups = "regression")
	public void validateProductOnCartPage() {
		SoftAssert sft = new SoftAssert();
		YourCartPage cartPage = new YourCartPage(); 
		sft.assertEquals(cartPage.getPageTitleText(), getStrings().get("your_cart"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_showing_in_cart_page"));
		cartPage.clickCheckoutButton();
		sft.assertAll();
	}	
		
	@Test(dependsOnMethods = {"validateProductOnCartPage"}, groups = "regression")
	public void validateProductOnInformationPage() {	
		SoftAssert sft = new SoftAssert();
		CheckoutInformationPage checkoutInfo = new CheckoutInformationPage();
		sft.assertEquals(checkoutInfo.getPageTitle(), getStrings().get("information_page_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_showing_in_info_page"));
		checkoutInfo.enterFirstname(testValues.getJSONObject("CheckoutInformation").getString("firstname"));
		checkoutInfo.enterLastname(testValues.getJSONObject("CheckoutInformation").getString("lastname"));
		checkoutInfo.enterPostalcode(testValues.getJSONObject("CheckoutInformation").getString("postalCode"));
		checkoutInfo.clickContinueButton();
		sft.assertAll();
	}
	
	@Test(dependsOnMethods = {"validateProductOnInformationPage"}, groups = "regression")
	public void validateProductOnOverviewPage() throws InterruptedException {
		SoftAssert sft = new SoftAssert();
		CheckoutOverviewPage overview = new CheckoutOverviewPage();
		sft.assertEquals(overview.getPageTitleText(), getStrings().get("overview_page_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_showing_in_overview_page"));
		overview.clickFinishButton();
		sft.assertAll();	
	}
	
	@Test(dependsOnMethods = {"validateProductOnOverviewPage"})
	public void validateProductOrderSucess() {
		SoftAssert sft = new SoftAssert();
		CheckoutOverviewPage overview = new CheckoutOverviewPage();
		sft.assertEquals(overview.getPageTitleCompletePage(), getStrings().get("checkout_complete_page_title"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_showing_in_checkout_complete_page"));		
		sft.assertEquals(overview.getThankyouMessage(), getStrings().get("thank_you_msg"));
		TestReport.getTest().log(Status.INFO, getStrings().get("product_ordered_sucessfully"));
		sft.assertAll();
	}
 
  }

    
    
    
    