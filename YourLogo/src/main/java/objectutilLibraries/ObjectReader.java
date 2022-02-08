package objectutilLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	
Properties objpro;
	
	public ObjectReader() throws IOException
	{
		String path=System.getProperty("user.dir");
		//File fi = new File("E:\\ProjectUIAutomation\\YourLogo\\src\\test\\resources\\ObjectRepository\\ObjectConfig.properties");
		File fi = new File(path+"\\ObjectRepository\\ObjectConfig.properties");
	    FileInputStream fis = new FileInputStream(fi);	    
	    objpro = new Properties();	    
	    objpro.load(fis);	
	}
	
	public String getDriverRelPath()
	{
		return objpro.getProperty("DriverRelPath");
	}	
	
	public String getDriverAbsPath()
	{
		return objpro.getProperty("DriverAbsPath");
	}
	
	public String getURL()
	{
		return objpro.getProperty("baseUrl");
	}
	public String getChromeKey()
	{
		return objpro.getProperty("chromeKey");
	}

}
