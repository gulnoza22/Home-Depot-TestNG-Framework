package com.hd.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hd.utilities.BrowserUtils;
import com.hd.utilities.ConfigurationReader;
import com.hd.utilities.Driver;

public abstract class TestBase {
	protected WebDriver driver;

	// to show your manager what tests passed or failed ExtentReports generates a nice colorful html report
	//How did you test, where did you test, when did you test? To answer for all this questions we use reports.
	protected ExtentReports report; // we will use ExtentReports to generate our report
	protected ExtentHtmlReporter htmlReporter; //takes the report and builds an HTML page
	protected ExtentTest extentLogger; // tells you what to print on the report
   
	@BeforeTest //we are initializing inside @BeforeTest so that it runs once before every test
	public void setUpTest() {
		// actual reporter object 
		report = new ExtentReports();
		// System.getProperty("user.dir")-> get the path to current project
		// test-output-->folder in the current project, will be created by testng if it
		// does not already exist.
		// report.html-->name of the report file
		String filePath = System.getProperty("user.dir") + "/test-output/report.html";
		// we are creating htmlReporter object that requires file path
		htmlReporter = new ExtentHtmlReporter(filePath);
		
		//we are attaching  to report our htmlReporter.
		report.attachReporter(htmlReporter);
		//setSystemInfo method takes parameters as key and value.
		// when developers test the code it means stage environment 
		report.setSystemInfo("Environment", "staging"); 
		//this is our browser name to print in the report
		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));
		//After giving all information to our report finally we can give the name to see in our report 
		htmlReporter.config().setReportName("Home Depot Automated Test Reports");

	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(ConfigurationReader.getProperty("url"));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		// checking if the test method failed
		// which has status code 2
		//ItestResult is interface coming from TestNG. It checks if the test failed or passed.
		if (result.getStatus() == ITestResult.FAILURE) {
			
			// getting screenshot using TakeScreenShot interface from BrowserUtils class 
			// gets the failed test name
			String screenshotLocation = BrowserUtils.getScreenshot(result.getName());//<--this takes a test name only

			// getting the name of the failed method
			extentLogger.fail(result.getName());

			// adding the screenshot to the bottom of the report
			extentLogger.addScreenCaptureFromPath(screenshotLocation);

			// capture the exception thrown
			extentLogger.fail(result.getThrowable());

			//sometimes tests can be skipped. 
		} else if (result.getStatus() == ITestResult.SKIP) {
			//report let us know which test was skipped
			extentLogger.skip("Test Case Skipped is " + result.getName());
		}
//		Driver.closeDriver();
	}

	@AfterTest
	public void tearDownTest() {

//		 cleaning the report so we can be able to see new reports
		 report.flush();
	}

}
