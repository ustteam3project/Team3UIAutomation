package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import excelUtility.ExcelReader;
import utils.CommonUtilities;

public class LoginPage {
	
	WebDriver driver;
	ExcelReader reader;
	By username = By.name("email");
	By password = By.name("passwd");
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public void Login(String user, String Pass)
	{
		CommonUtilities.sleep(4);
		driver.findElement(username).sendKeys(user);
		CommonUtilities.sleep(2);
		driver.findElement(password).sendKeys(Pass);
		CommonUtilities.sleep(2);
		WebElement submitLogin=driver.findElement(By.name("SubmitLogin"));
		CommonUtilities.scrollToElement(driver, submitLogin);
		CommonUtilities.sleep(2);
		submitLogin.click();
		CommonUtilities.sleep(5);
	}
	
	public void Login1()
	{
		reader =new ExcelReader();
		CommonUtilities.sleep(4);
		driver.findElement(username).sendKeys(reader.getSingleExcelData(0, 1, 0));
		CommonUtilities.sleep(2);
		driver.findElement(password).sendKeys(reader.getSingleExcelData(0, 1, 1));
		CommonUtilities.sleep(2);
		WebElement submitLogin1=driver.findElement(By.name("SubmitLogin"));
		CommonUtilities.scrollToElement(driver, submitLogin1);
		CommonUtilities.sleep(2);
		submitLogin1.click();
		CommonUtilities.sleep(5);
	}

}
