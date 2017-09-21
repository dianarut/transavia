package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_9 extends BaseTest{
	
//	1. open transavia.com
//	2. click Add multiple destinations
//	3. Find Outbound flight page section
//	3.1 type "Bologna, Italy" into field From
//	3.2 type "Eindhoven, Netherlands" into field To
//	3.3 type 1st date  into field Date (e,g, 2 May 2017)
//	4. Find Inboud flight page section
//	4.1 type "Amsterdam (Schiphol), Netherlands" into field From
//	4.2 type "Casablanca, Morocco" into field To
//	4.3 type 2nd date into field Date (e,g, 8 May 2017)
//	5. click Search
//	6. click select button next to 15:40 outbound flight
//	7. click select button next to 10:20 inbound flight
//	8. get Total amount 
	
	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void testId9() {
		String outFrom = "Bologna, Italy";
		String outTo = "Eindhoven, Netherlands";
		String inFrom = "Amsterdam (Schiphol), Netherlands";
		String inTo = "Casablanca, Morocco";
		
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		// 2 Click "Multiple destinations"
		page1.clickAddMultipleDestinations();
		
		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 3 Search outbound section
		Assert.assertTrue(page2.findOutboundSection(), "The Outbound section is not on page");
		
		// 3 Set "From" and "To" in Outbound
		page2.setFieldsFromToOutbound(outFrom, outTo);
		
		// 3 Check "From" and "To" in Outbound
		Assert.assertEquals(page2.getToOutbound(), outTo, "Unable to fill 'To' field");
		Assert.assertEquals(page2.getFromOutbound(),outFrom, "Unable to fill 'From' field");
		
		// 4 Search inbound section
		Assert.assertTrue(page2.findInboundSection(), "The Inbound section is not on page");
		
		// 4 Set "From" and "To" in Inbound
		page2.setFieldsFromToInbound(inFrom, inTo);

		// 4 Check "From" and "To" in Inbound
		Assert.assertEquals(page2.getToInbound(), inTo, "Unable to fill 'To' field");
		Assert.assertEquals(page2.getFromInbound(),inFrom, "Unable to fill 'From' field");

		// 5 Click "Search"
		page2.clickSearch();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 6 Select flight
		page2.selectOutboundAvailableDate();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 6 Remember price of first flights
		double outPrice = page2.getOutPrice();

		// 6  Click "Select"
		page2.clickSelectOutbound();	

		// 7 Select flight
		page2.selectInboundAvailableDate();

		// 7 Remember price of second flights
		double inPrice = page2.getInPrice();

		// 7  Click "Select"
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page2.clickSelectInbound();
		
		// Remember total price
		double totalPrice = page2.getTotalPrice();

		// Check total price
		Assert.assertFalse((outPrice+inPrice==totalPrice), "The total price is wrong!");
	}

}
