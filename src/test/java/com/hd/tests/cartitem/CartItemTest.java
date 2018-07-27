package com.hd.tests.cartitem;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hd.pages.CartItemPage;
import com.hd.tests.TestBase;


public class CartItemTest extends TestBase {
	//We have to create object from page class, so we can call variables of locators.. 
	CartItemPage cartItem = new CartItemPage();
	
	// TESTING "CART" FUNCTIONALITY FROM HOME PAGE
	// we putting smoke label with groups option
	
	@Test(priority=1,groups= {"smoke"})
	
	public void cartItem() {
		
		extentLogger=report.createTest("Cart functionality Test");
		//clicking on cart button from home page
		cartItem.cartButton.click();
		//verifying if we are in correct "Shopping cart" page
		String expectedText="Shopping Cart";
		String actualText = cartItem.shoppingCart.getText();
		
		assertEquals(actualText,expectedText);
		extentLogger.pass("Shopping Cart log is displayed");
		
	    
		
	}
	
	

}
