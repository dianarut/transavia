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
		
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		//.//*[@class = 'sub-navigation_link sub-navigation-level-1_link h5'][descendant :: *[@class = 'stamp icon-font icon-account']]]
//		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//			public Boolean apply (WebDriver d){
//				return d.getTitle().toLowerCase().startsWith("transavia");
//			}
//		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//2 Click "ManageYourBooking"
		page1.clickManageYourBooking();
		
		//3 Click "ViewYourBooking"
		page1.clickViewYourBooking();
		
		AccountLogon page2 = PageFactory.initElements(driver, AccountLogon.class);

		//4 Set values in fields
		page2.setNumberNameDate(bookingNumber, lastName, flightDate);
		
		// 4 Check values in fields
		Assert.assertEquals(page2.getBookingNumber(), bookingNumber,"Unable to fill 'BookingNumber' field");
		Assert.assertEquals(page2.getLastName(), lastName,"Unable to fill 'LastName' field");
		Assert.assertEquals(page2.getFlightDate(), flightDate,"Unable to fill 'FlightDate' field");
		
		page2.justClick();
		
		// Click "ViewBooking"
		page2.clickViewBooking();
		
		BookingOverview page3 = PageFactory.initElements(driver, BookingOverview.class);
		
		// Check page
		page3.checkTable();
		
		//Check information about flight
		Assert.assertEquals(page3.getDepartureTime(), departureTime,"Departure time is wrong!");
		Assert.assertEquals(page3.getArrivalTime(), arrivalTime,"Arrival time is wrong!");
		Assert.assertEquals(page3.getFromCity(), fromCity,"Departure city is wrong!");
		Assert.assertEquals(page3.getToCity(), toCity,"Arrival city is wrong!");
	}
}
