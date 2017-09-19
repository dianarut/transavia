package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Id_8 extends BaseTest{
	
//	1.1 open transavia.com
//	1.2 click field "From"
//	1.2.1 input "Dubai" into field
//	1.3 click field "To"
//	1.3.1 input " Agadir,Morocco" into field
//	1.4 click button "Search"
//	1.5 get error message

	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void TestId8() {
		
		String from = "Dubai, United Arab Emirates";
		String to = "Agadir, Morocco";
		
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		//1.  Check if "Where do you want to go?" is  available  
		Assert.assertTrue(page1.isSectionWhereDoYouWantToGoPresent(), "No suitable forms found!");
		
		// 2, 3 Set  "From" and "To"
		page1.setFromTo(from,to);

		// 2, 3 Check "To" and "From"
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		//4 Click "Search"
		page1.clickSearch();

		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);

		//5 Search error message
		Assert.assertTrue(page2.errorMessageEquals("Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again."),"Message is absent or is not in a proper place");
	}
}
