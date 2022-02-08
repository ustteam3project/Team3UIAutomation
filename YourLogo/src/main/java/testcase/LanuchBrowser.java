package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import browserConfiguration.BrowserConfig;
import excelUtility.ExcelReader;
import listeners.RetryAnalyzer;
import pages.LandingPage;
import pages.LoginPage;
import utils.CommonUtilities;
import utils.TakeScreensht;

public class LanuchBrowser {

	WebDriver driver;
	LandingPage lp;
	LoginPage login;
	BrowserConfig obj;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	ExcelReader reader;

	@BeforeSuite
	public void setup() {

		htmlReporter = new ExtentHtmlReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@BeforeMethod
	public void LunchApplication() throws IOException {

		obj = new BrowserConfig();
		driver = obj.getChromeBrowser();
		lp = new LandingPage(driver);

	}
    
	@Test
	public void VerifyLoginTest() throws InterruptedException {

		test = extent.createTest("LoginTest", "Login with Register Email Address");
		CommonUtilities.sleep(2);
		lp.verifyTitle();
		test.log(Status.INFO, "Title Verified Successfully");
		CommonUtilities.sleep(2);
		lp.clickSignIn();
		test.log(Status.INFO, "Navigated into Login Page");
		login = new LoginPage(driver);
		login.Login1();
		test.log(Status.INFO, "Loged In Successfully");

	}

	@Test(retryAnalyzer= listeners.RetryAnalyzer.class)
	public void VerifyRetryFailedTc() throws InterruptedException {

		test = extent.createTest("RetryFailedTc", "VerifyRetryAnalyzer");
		CommonUtilities.sleep(2);
		lp.verifyTitle();
		test.log(Status.INFO, "Title Verified Successfully");
		int i=1/0;

	}
	
	@DataProvider()
	public String[][] getData(){
		
		
		reader =new ExcelReader();
		String[][] exceldata=reader.getExcelData();
		return exceldata;
		
	}
	
	@Test(dataProvider="getData")
	public void VerifyLoginData(String data[]) throws InterruptedException {
		test = extent.createTest("Data Driven LoginTest", "Login with Data Driven");
		CommonUtilities.sleep(2);
		lp.verifyTitle();
		test.log(Status.INFO, "Title Verified Successfully");
		CommonUtilities.sleep(2);
		lp.clickSignIn();
		test.log(Status.INFO, "Navigated into Login Page");
		login = new LoginPage(driver);
		login.Login(data[0], data[1]);
		test.log(Status.INFO, "Loged In Successfully");

	}
	

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp=TakeScreensht.getScreenshot(driver);
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
	        test.log(Status.FAIL,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test case FAILED due to below issues:",
	                        ExtentColor.RED));
	        test.fail(result.getThrowable());
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	        test.log(
	                Status.PASS,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test Case PASSED", ExtentColor.GREEN));
	    } else {
	        test.log(
	                Status.SKIP,
	                MarkupHelper.createLabel(result.getName()
	                        + " Test Case SKIPPED", ExtentColor.ORANGE));
	        test.skip(result.getThrowable());
	    }

		driver.quit();
	}

	@AfterSuite
	public void teardown1() {

		extent.flush();
	}

}
