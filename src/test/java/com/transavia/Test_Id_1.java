package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

	// this comment is just for second commit.
	
public class Test_Id_1  extends BaseTest{
	// 1. Появляется окно выбора маршрута "Where do you want to go?"
	// 2. Появляется выпадающий список с названиями доступных мест для перелета в поле "FROM"
	// 3. Появляется выпадающий список с названием доступных мест в поле "TO"
	// 4. Появляется поле с каллендарным списком дней недели, месяцами и годом
	// 5. Перевести checkbox "Where do you want to go?" в состоянии unchecked
	// 6. Появляктся дополнительное поле для выбора количества пассажиров в поле "Who will be travelling?"
	
	@BeforeClass
	public void beforeClass() throws Exception {
		super.beforeClass();
	}
	
	@AfterClass
	public void afterClass() {
		super.afterClass();
	}
	
	@Test
	public void TestId1() {
		
		String from = "Edinburgh, United Kingdom";
		String to = "Paris (Orly South), France";
		int adults = 3;
		int children = 2;
		int babies = 1;

		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		//1.  Check if "Where do you want to go?" is  available 
		Assert.assertTrue(page1.isSectionWhereDoYouWantToGoPresent(), "No suitable forms found!");
		
		//2. Check if "From" drop-down list is visible
		Assert.assertTrue(page1.checkFromDropDownList(from), "No FROM drop-down list found!");
		
		//2. Set "From"
		page1.setFrom(from);
		
		//2. Check "From"
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		page1.clickOnOtherArea();
		
		//3. Check if "To" drop-down list is visible
		Assert.assertTrue(page1.checkToDropDownList(to), "No TO drop-down list found!");
		
		//3.  Set "To"
		page1.setTo(to);
		
		//3. Check "To" 
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		
		page1.clickOnOtherArea();
		
		// 4. Set date in "Depart On"
		page1.setDepartOnDate();
		
		// 4. Check date in "Depart On"
		Assert.assertTrue(page1.checkDepartOnDate(), "The Depart On date is wrong!");
			
		// 5. Click checkbox "Return on" to it'll be unchecked
		page1.clickReturnOn();
		
		// 5. Check checkbox "Return on"
		Assert.assertFalse(page1.checkReturnOn(), "The checkbox Retun On is checked!");
		
		// 6. Set number of passengers
		page1.setNumberOfPassengers(adults,children,babies);
		
		// 6. Check number of passengers
		Assert.assertEquals(page1.getNumberOfAduls(), adults,"There is wrong number of adults!");
		Assert.assertEquals(page1.getNumberOfChildren(), children,"There is wrong number of children!");
		Assert.assertEquals(page1.getNumberOfBabies(), babies,"There is wrong number of babies!");

		page1.clickOnOtherArea();

		// 7. Click "Search"
		page1.clickSearch();

		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);
		
		// Check if there is available flight
		Assert.assertTrue(page2.searchAvailableFlight(), "No available flight found!");
	}
}
