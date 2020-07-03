package com.tieto.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.LaunchSuite;

import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.PropUtils;

public class WebDriverWrapper {
	protected WebDriver driver;
	protected LoginPage login;
	protected DashboardPage dashboardPage;

	@Parameters({"browser"})
	@BeforeMethod
	public void init(@Optional("ch")String browserName) throws IOException 
	{
		launchBrowser(browserName);
		//loadPageObject();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String baseUrl = PropUtils.getValueFromKey("testdata/data.properties", "url");
		driver.get(baseUrl);
	}
	
	
	public void launchBrowser(String browserName)
	{
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
		System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
		if(browserName.toLowerCase().equals("ie"))
		{
			driver=new InternetExplorerDriver();
		}
		else if(browserName.toLowerCase().equals("ff"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
	}
	
//	public void loadPageObject()
//	{
//		login=new LoginPage(driver);
//		dashboardPage=new DashboardPage(driver);
//	}

	@AfterMethod
	public void end()
	{
		driver.quit();
	}
}
