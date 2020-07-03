package com.tieto.pages;

import org.openqa.selenium.WebDriver;

public class PatientFinderPage {
	private String frameName="fin";
	
	
	private WebDriver driver;
	
	public PatientFinderPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void switchToFrameWithFin()
	{
		driver.switchTo().frame(frameName);
	}

}
