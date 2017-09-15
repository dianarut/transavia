package com.transavia;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_3 {
	
//	1. open transavia.com
//	2. click Manage your booking (additional menu drops down)
//	3. click View your booking (login page loads)
//	4. Enter booking no. "MF8C9R"; last name "kukharau", flight date "9 June 2016" (booking page loads)
//	5. Get arrival time and arrived time
	
	String base_url = "https://www.transavia.com";
	StringBuffer verificationErrors = new StringBuffer();
	FirefoxProfile profile = new FirefoxProfile();
	WebDriver driver = null;
	

	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:/Program Files/geckodriver-v0.18.0-win64/geckodriver.exe");
		profile.setPreference("browser.startup.homepage", "about:blank");
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public void afterClass() {
		try { Runtime.getRuntime().exec("taskkill /f /IM firefox.exe"); } catch (IOException e) { e.printStackTrace(); }
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
	
	@Test
	public void TestId3() {
		String bookingNumber = "MF8C9R";
		String lastName  = "kukharau";
		String flightDate  = "9 June 2016";
		
		driver.get(base_url + "/en-UK/home/");
		
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Кликаем AddMultipleDestinations
		page1.clickAddMultipleDestinations();
		
		// Кликаем ViewYourBooking
		page1.clickViewYourBooking();
		
		AccountLogon page2 = PageFactory.initElements(driver, AccountLogon.class);
		
		// Устанавливаем значения в поля
		page2.setNumberNameDate(bookingNumber, lastName, flightDate);
		
		// Проверяем установленные значения
		Assert.assertEquals(page2.getBookingNumber(), bookingNumber,"Unable to fill 'BookingNumber' field");
		Assert.assertEquals(page2.getLastName(), lastName,"Unable to fill 'LastName' field");
		Assert.assertEquals(page2.getFlightDate(), flightDate,"Unable to fill 'FlightDate' field");
		
		page2.justClick();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Кликаем ViewBooking
		page2.clickViewBooking();
		
		BookingOverview page3 = PageFactory.initElements(driver, BookingOverview.class);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Проверяем наличие формы на странице
		page3.checkTable();
		
	}
}
