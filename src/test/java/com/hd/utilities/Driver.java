package com.hd.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	//Its private because  nobody create a Driver object from other classes. 
	private Driver() {}
	// its private so we can use only inside the class. 
	private static WebDriver driver;
    
	//creating method to use any browser in our tests. We can call by Driver.getDriver() method in other classes. 
	public static WebDriver getDriver() {
		if (driver == null) {
			switch (ConfigurationReader.getProperty("browser")) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			}
		}
		return driver;
	}
	
	
  // if our driver equal to any browsers then close it. 
	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			//ask
			driver = null;
		}
	}
}
