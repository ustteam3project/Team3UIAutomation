package browserConfiguration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import objectutilLibraries.ObjectReader;

public class BrowserConfig {
	
	ObjectReader obj;
	public BrowserConfig() throws IOException
	{
		obj = new ObjectReader();
	}
	
	public WebDriver getChromeBrowser()
	{		
		String key = obj.getChromeKey();
		String path= obj.getDriverAbsPath();
		String url = obj.getURL();
		
		System.setProperty(key, path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
		
         
		
	}


}
