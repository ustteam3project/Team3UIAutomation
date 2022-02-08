package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utils.CommonUtilities;

public class LandingPage {
	
	WebDriver driver;
	By signIn = By.cssSelector("a.login");
	
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public void clickSignIn()
	{
		 driver.findElement(signIn).click();
		 CommonUtilities.sleep(5);
	}
	
	public void verifyTitle()
	{
		String exTitle = "My Store";
		String acTitle = driver.getTitle();
		Assert.assertEquals(acTitle,exTitle);	
		
	}

}
