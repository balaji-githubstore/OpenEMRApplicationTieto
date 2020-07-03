package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchOrAddPatientPage {

	private WebDriver driver;

	public SearchOrAddPatientPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void fillPatientDetails(String firstname,String lastname,String dateOfBirth)
	{
		driver.findElement(By.id("form_fname")).sendKeys(firstname);
		driver.findElement(By.id("form_lname")).sendKeys(lastname);
		driver.findElement(By.id("DOB")).sendKeys(dateOfBirth);
	}
}
