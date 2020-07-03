package com.tieto.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtils;

public class LoginTest extends WebDriverWrapper {
	
	@Test
	public void checkLinkCountTest()
	{
		int acutalValue = driver.findElements(By.tagName("a")).size();	
		Assert.assertEquals(acutalValue, 1);
	}
	
	@DataProvider
	public Object[][] invalidCredentialData()
	{
		Object[][] main=new Object[2][4];
		main[0][0]="john";
		main[0][1]="john123";
		main[0][2]="French (Standard)";
		main[0][3]="Invalid username or password";
		
		main[1][0]="peter";
		main[1][1]="peter123";
		main[1][2]="Armenian";
		main[1][3]="Invalid username or password";
		
		return main;
	}
	
	@Test(priority = 2,dataProvider = "invalidCredentialData")
	public void invalidCredentialTest(String username,String password,String language,String expectedValue)
	{
		LoginPage login =new LoginPage(driver);	
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();
		String actualValue=login.getErrorMessage();
		//Assert.assertEquals(actualValue.trim(), "Invalid username or password");
		Assert.assertTrue(actualValue.contains(expectedValue));
	}
	
	@DataProvider
	public Object[][] validCredentialData() throws IOException
	{
		Object[][] main= ExcelUtils.getSheetIntoObject("testdata/OpenEMRData.xlsx", "validCredentialData");
		return main;
	}
	
	
	@Test(dataProvider = "validCredentialData")
	public void validCredentialTest(String username,String password,String language,String expectedValue)
	{
		LoginPage login =new LoginPage(driver);	
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();
		
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.waitForPresenceOfMessageCenterText();
		String actualValue = dashboard.getCurrentTitle();

		Assert.assertEquals(actualValue, expectedValue);
	}
}






