package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_2   extends BaseTest{
	
//	1.1 click field "From"  
//	1.1.1 input "London"                       
//	1.1.2 check  "London" in dropdown list                           
//	1.2. click field "To"
//	1.2.1 input "Paris"                       
//	1.2.2 check "Paris" in dropdown list                                        
//	1.3 click field " Who will be travelling?"  
//	1.3.1  click  "+" for "Adults" in dropdown list
//	1.3.2  click  "+" for "Children" in dropdown list
//	1.4 click button "Search"
//	1.5 find title "Outbound flight"
//	1.5.1 click first button "select"
//	1.6 find title "Inbound flight"
//	1.6.1 click first button "select"
//	1.7 click button "next"
//	1.8 find title "Plus"
//	1.8.1 click button " Select"
//	1.9 get total price
	
	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void testId2() {
		
		String from = "Amsterdam (Schiphol), Netherlands";
		String to = "Paris (Orly South), France";
		int adults = 2;
		int children = 1;
		int babies = 0;
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
//		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
//			public Boolean apply (WebDriver d){
//				return d.getTitle().toLowerCase().startsWith("transavia");
//			}
//		});
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		// 1, 2 Set  "From" and "To"
		// There is no "London" in "From" drop-down list, so I changed it to "Amsterdam (Schiphol), Netherlands"
		page1.setFromTo(from,to);
		
		// 1, 2 Check "To" and "From"
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		// 3 Set number of passengers
		page1.setNumberOfPassengers(adults, children, babies);
		
		// 3. Check number of passengers
		Assert.assertEquals(page1.getNumberOfAduls(), adults);
		Assert.assertEquals(page1.getNumberOfChildren(), children);
		Assert.assertEquals(page1.getNumberOfBabies(), babies);
		
		page1.clickOnOtherArea();
		
		//4 Click "Search"
		page1.clickSearch();
		
		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);

		//5 Search "Outbound flight"
		page2.findOutboundFlight();

		// Remember prices of flights 
		double toPrice = page2.getToPrice();
		double fromPrice = page2.getFromPrice();

		//5 Click first "Select" in section "OutboundFlight"
		page2.clickSelectOutbound();

		//6 Search "Inbound flight"
		page2.findInboundFlight();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		//6 Click first "Select" in section "InboundFlight"		
		page2.clickInboundFlightSelect();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//7 Click "Next"
		page2.clickNext();

		ChooseFare page3 = PageFactory.initElements(driver, ChooseFare.class);
				
		verificationErrors.append(page3.getErrorOnTextAbsence("Plus"));

		// Remember prices of luggage
		double luggagePrice = page3.getLuggagePrice();
		
		//8 Click "Select" in section "Plus"
		page3.clickSelectPlus();

		// Remember total price
		double totalPrice = page3.getTotalPrice();

		//9 Check total price
		Assert.assertTrue((((toPrice+fromPrice)*3)+(luggagePrice*3)==totalPrice),"The total price is wrong");
	}
}