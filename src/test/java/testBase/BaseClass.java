
package testBase;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public  Logger loger;
	public Properties p;  //creating variable for properties
	
	@BeforeClass(groups={"Master","sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");  //dynamically getting location of current project
		
		p=new Properties();  //object craetion for properties
		
		p.load(file);   //loading file
		 
		loger=LogManager.getLogger(this.getClass());  //Log4j
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))	
		 
		{
			//os
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				
			capabilities.setPlatform(Platform.WIN10);
			
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
			capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome"); break;
			
			case "edge":capabilities.setBrowserName("MicrsoftEdge"); break;
			
			case "firefox":capabilities.setBrowserName("firefox");break;
			
			default: System.out.println("No matching browser");return;
			}
			
			 driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())   //for cross & parallel testing (Local enviironment or local system)
		  {
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		
		default: System.out.println("Invalid Browser");return;
		  }
	  }
	
		driver.get(p.getProperty("appURL1"));   //instead of Hardcoding,reading url from properties file
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		
	}
	@AfterClass(groups={"Master","sanity","Regression"})
	public void tearDown() 
	{
		driver.quit();
	}
	 public String randomeString()
	 {
		
		@SuppressWarnings("deprecation")
		String generatedstring=RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	 }
	 
	public String randomeNumber()
	{
		@SuppressWarnings("deprecation")
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomeAlphaNumeric()
	{
		@SuppressWarnings("deprecation")
		
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		@SuppressWarnings("deprecation")
		String generatenumber=RandomStringUtils.randomNumeric(8);
		
		return (generatedstring+"@ * % $ # - _ "+generatenumber);   //if you want add any special charected then we can add 
		}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); //screenshot also we should capture with time timestamp

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;//TakesScreenshot is interface and we have assigned the driver to the screenshot 

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		/*copy the source  file to Target file so here we have to copy sourcefile in the project location in the 
		screenshot folder and with the time stamp, so Tname ,underscore the time stamp + .png because it is screenshot we have to save in png format 
		*/
		
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);   //copy the source file to Target file by using these command

		return targetFilePath;

	}


}
