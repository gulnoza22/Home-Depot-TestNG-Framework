package com.hd.tests.productsearch;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hd.pages.ProductSearchPage;
import com.hd.tests.TestBase;
import com.hd.utilities.BrowserUtils;
import com.hd.utilities.ConfigurationReader;

public class ProductSearchTest extends TestBase {

	ProductSearchPage productSearchPage = new ProductSearchPage();
	
	// we putting smoke label with groups option

	@Test(priority=1, groups={"smoke"})  //TESTING POSITIVE SEARCH
	
	public void positiveSearchTest() {
		extentLogger=report.createTest("Positive search Test");
		//info(-> to print a important message
		extentLogger.info("searching an item");
    	// search for stand mixers in the search box
		productSearchPage.searchBox.sendKeys(ConfigurationReader.getProperty("item"));
		
		extentLogger.info("clicking on search button");
		// click on a search button
		productSearchPage.searchButton.click();
		// we are verifying title of the page, if we are in correct page or not.
		String expectedTitle = "Search Results for stand mixer at The Home Depot";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
		// now we are clicking on item->(KitchenAid White Stand Mixer)
		productSearchPage.itemName.click();
		//again we are verifying if we are getting correct "text" of the item 
		String expectedItemName= "Classic 4.5 Qt. Tilt-Head White Stand Mixer";
		String actualItemName=productSearchPage.standMixer.getText();

		BrowserUtils.waitFor(2);
		assertEquals(actualItemName,expectedItemName);
		//pass->message that tells you what passed and what verified 
		extentLogger.pass("Item discriptions is displayed");
		
	
	}

	@Test(priority=2)  //TESTING NEGATIVE SEARCH
	public void negativeSearchTest() {
		extentLogger=report.createTest("Negative search Test");
		//writing wrong credential to search box
		productSearchPage.searchBox.sendKeys(ConfigurationReader.getProperty("wrongItem"));
		//clicking on search button
		productSearchPage.searchButton.click();
        // verifying the result after writing wrong credential
		String expectedText = "Hmm...we couldn't find \"asdfg\"";
		String actualText = productSearchPage.WeCouldntFind.getText();
		assertEquals(actualText, expectedText);
		fail("failed to search");
		extentLogger.pass("Item asdfg log is not a valid item");
		

	}
	
	@Test(priority=3)  //Skipping a search test
	public void skipSearchTest() {
		extentLogger=report.createTest("Negative search Test");
		//writing wrong credential to search box
		productSearchPage.searchBox.sendKeys(ConfigurationReader.getProperty("wrongItem"));
		//clicking on search button
		productSearchPage.searchButton.click();
        // verifying the result after writing wrong credential
		String expectedText = "Hmm...we couldn't find \"asdfg\"";
		String actualText = productSearchPage.WeCouldntFind.getText();
		assertEquals(actualText, expectedText);
		// We use skipException-> if we have known test that fails and in progress we can skip that specific test.
		throw new SkipException("This test in work in progress");

	}
}