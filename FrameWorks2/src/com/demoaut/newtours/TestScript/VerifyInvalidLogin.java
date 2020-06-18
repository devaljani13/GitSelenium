package com.demoaut.newtours.TestScript;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demoaut.newtours.Pages.Log;

import com.demoaut.newtours.Pages.SignOn;

import utils.GenericMethods;

public class VerifyInvalidLogin {
	WebDriver driver;
	@BeforeClass
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30 , TimeUnit.SECONDS);
		driver.get("http://www.newtours.demoaut.com/");

	}
	@Test 
	public void verifyInvalidLogin() throws IOException {
		Log lp = new Log (driver);
		SignOn so = new SignOn (driver);
		String [][]data = GenericMethods.getData("D:\\IT TRAINING 2020\\SELENIUM\\testdata.xlsx", "Sheet1");
		for (int i = 1; i < data.length; i++){
			lp.applicationLogin (data[i][0], data [i][0]);
			String actualMsg = so.getMessage();
			String expectedMsg= "Welcome back to Mercury Tours! Enter your user information to access the member-only areas of this site. If you don't have a log-in, please fill out the registration form.";
			Assert.assertEquals(actualMsg, expectedMsg);
			driver.navigate().back();
		}


	}

	@AfterClass
	public void closeApplication() {

		driver.close(); 
	}

}
