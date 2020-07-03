package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	private By messageCentreLocator=By.xpath("//span[text()='Message Center']");
	private By patientClientLocator=By.xpath("//div[text()='Patient/Client']");
	private By patientsLocator=By.xpath("//div[text()='Patients']");
	private WebDriver driver;
	
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForPresenceOfMessageCenterText()
	{
		WebDriverWait wait =new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(messageCentreLocator));
	}
	
	public String getCurrentTitle()
	{
		return driver.getTitle();
	}
	
	public void mouseHoverOnPatientClient()
	{
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(patientClientLocator)).build().perform();
	}

	public void clickOnPatients()
	{
		driver.findElement(patientsLocator).click();
	}
	
}
