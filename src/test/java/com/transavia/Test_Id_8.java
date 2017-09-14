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

public class Test_Id_8 {
//	1.1 open transavia.com
//	1.2 click field "From"
//	1.2.1 input "Dubai" into field
//	1.3 click field "To"
//	1.3.1 input " Agadir,Morocco" into field
//	1.4 click button "Search"
//	1.5 get error message
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
	public void TestId8() {
		
		driver.get(base_url + "/en-UK/home/");
		String from = "Dubai, United Arab Emirates";
		String to = "Agadir, Morocco";
		
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		//1 Проверка наличия секции "Where do you want to go?" 
		Assert.assertTrue(page1.isSectionWhereDoYouWantToGoPresent(), "No suitable forms found!");
		
		// 2, 3 Заполнение  From and To
		// т.к. в выпадающем списке отсутствует пункт отправления London
		// заменяю его на Amsterdam (Schiphol), Netherlands, т.к. считаю, что это не повлияет на конечную цель теста
		page1.setFromTo(from,to);
		
		// 2, 3 Проверяем установленные значени To и From
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		//4 Кликаем Search
		page1.clickSearch();
		
		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);

		//5 Ищем заголовок Outbound flight
		Assert.assertTrue(page2.errorMessageEquals("Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again."),"Message is absent or is not in a proper place");
	}
}
