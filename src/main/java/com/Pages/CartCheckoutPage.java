package com.Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

    public class CartCheckoutPage extends MenuPage {

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")private MobileElement AddToCart;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView") private MobileElement CartIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='REMOVE']") static MobileElement Remove;

	@AndroidFindBy(xpath = "//android.widget.TextView[text()='CHECKOUT: INFORMATION']") private MobileElement CheckoutInformation;
	
	public void pressAddToCart() {
		waitForVisibilty(AddToCart);
		 click(AddToCart);
	}

	public String getCartButtonText() {
		waitForVisibilty(Remove);
		return getText(Remove);
	}

	public void clickCartButton() {
		waitForVisibilty(CartIcon);
		click(CartIcon);
	}

}
