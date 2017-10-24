package com.transavia;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
	//
	protected String base_url = "https://www.transavia.com";
	protected StringBuffer verificationErrors = new StringBuffer();
	protected FirefoxProfile profile = new FirefoxProfile();
	protected WebDriver driver = null;
	protected String bookingNumber = "MF8C9R";
	protected String lastName  = "kukharau";
	protected String flightDate  = "9 June 2016";
	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:/Program Files/geckodriver-v0.18.0-win64/geckodriver.exe");
		profile.setPreference("browser.startup.homepage", "about:blank");
		driver = new FirefoxDriver();
		driver.get(base_url + "/en-UK/home/");
	}
	
	@AfterClass
	public void afterClass() {
		try { Runtime.getRuntime().exec("taskkill /f /IM firefox.exe"); } catch (IOException e) { e.printStackTrace(); }
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
}
