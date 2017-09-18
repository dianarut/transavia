package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_4  extends BaseTest{
	
//	1. repeat steps 1-4 from case 3
//	2. click Booking details (new page loads)
//	3. find Price breakdown page section
//	4. Get Total sum
//	5. Get Payment amount
//	6. Compare 4. and 5.
	
	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void TestId4() {
		
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
		
		// Кликаем BookingDetails
		page3.clickBookingDetails();
		
		BookingDetails page4 = PageFactory.initElements(driver, BookingDetails.class);
			
		// Запоминаем стоимость билета
		Double total = page4.getTotal();
		
		// Запоминаем стоимость оплаты по брони
		Double paymentAmount = page4.getPaymentAmount();
		
		// Сравниваем
		Assert.assertFalse(total == paymentAmount, "Price is wrong!");
	}
}
