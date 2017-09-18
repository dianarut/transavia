package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_3 extends BaseTest{
	
//	1. open transavia.com
//	2. click Manage your booking (additional menu drops down)
//	3. click View your booking (login page loads)
//	4. Enter booking no. "MF8C9R"; last name "kukharau", flight date "9 June 2016" (booking page loads)
//	5. Get arrival time and arrived time
	
	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void TestId3() {

		String departureTime = "21:25";
		String arrivalTime = "23:35";
		String fromCity = "Pisa";
		String toCity = "Amsterdam (Schiphol)";
		
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
		
		// Кликаем ViewBooking
		page2.clickViewBooking();
		
		BookingOverview page3 = PageFactory.initElements(driver, BookingOverview.class);
		
		// Проверяем наличие формы на странице
		page3.checkTable();
		
		//Проверяем данные по рейсу
		Assert.assertEquals(page3.getDepartureTime(), departureTime,"Departure time is wrong!");
		Assert.assertEquals(page3.getArrivalTime(), arrivalTime,"Arrival time is wrong!");
		Assert.assertEquals(page3.getFromCity(), fromCity,"Departure city is wrong!");
		Assert.assertEquals(page3.getToCity(), toCity,"Arrival city is wrong!");
	}
}
