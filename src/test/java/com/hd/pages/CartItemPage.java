package com.hd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hd.utilities.Driver;

public class CartItemPage {
	
	
	public CartItemPage() {
		//PageFactory is a class coming from Selenium
		PageFactory.initElements(Driver.getDriver(), this);
	}
    // locator for cart button
	@FindBy(css=".MyCart__contents.hide.show--sm")
	public WebElement cartButton;
	
	//locator to verify of text "Shopping Cart"
	@FindBy(xpath="//*[.='Shopping Cart']")
	public WebElement shoppingCart;
	
	
	
	
}
