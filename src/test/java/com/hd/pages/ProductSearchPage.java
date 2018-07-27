package com.hd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hd.utilities.Driver;

public class ProductSearchPage {

	public ProductSearchPage() {

		PageFactory.initElements(Driver.getDriver(), this);

	}

	// locator for "search box"
	@FindBy(css = "#headerSearch")
	public WebElement searchBox;

	// locator for "search" button
	@FindBy(css = "#headerSearchButton")
	public WebElement searchButton;

	// locator for item: Kitchenaid
	@FindBy(css = ".pod-plp__description.js-podclick-analytics")
	public WebElement itemName;
	
    // locator to verify item name
	@FindBy(css = ".product-title__title")
	public WebElement standMixer;
	
    // negative test
	// locator to verify after giving "wrong data" to search box
	@FindBy(css = ".helvetica-neue-black-condensed.results-nrf-hero__text")
	public WebElement WeCouldntFind;

}
