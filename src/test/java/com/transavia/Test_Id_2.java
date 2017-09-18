package com.transavia;

import org.openqa.selenium.support.PageFactory;
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
		
		driver.get(base_url + "/en-UK/home/");
		String from = "Amsterdam (Schiphol), Netherlands";
		String to = "Paris (Orly South), France";
		int adults = 2;
		int children = 1;
		int babies = 0;
		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		// Проверка наличия секции "Where do you want to go?" 
		Assert.assertTrue(page1.isSectionWhereDoYouWantToGoPresent(), "No suitable forms found!");
			
		// 1, 2 Заполнение  From and To
		// т.к. в выпадающем списке отсутствует пункт отправления London
		// заменяю его на Amsterdam (Schiphol), Netherlands, т.к. считаю, что это не повлияет на конечную цель теста
		page1.setFromTo(from,to);
		
		// 1, 2 Проверяем установленные значени To и From
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		// 3 Устанавливаем необходимое количество пассажиров
		page1.setNumberOfPassengers(adults, children, babies);
		
		// 3. Проверяем, что установилось заданное количество пассажиров
		Assert.assertEquals(page1.getNumberOfAduls(), adults);
		Assert.assertEquals(page1.getNumberOfChildren(), children);
		Assert.assertEquals(page1.getNumberOfBabies(), babies);
		
		page1.clickOnOtherArea();
		
		//4 Кликаем Search
		page1.clickSearch();
		
		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);
		
		//5 Ищем заголовок Outbound flight
		page2.findOutboundSection();
		
		//Запоминаем цены перелетов
		double toPrice = page2.getToPrice();
		double fromPrice = page2.getFromPrice();
		
		//5 Кликаем первую кнопку select в секции OutboundFlight
		page2.clickOutboundFlightSelect();
			
		//6 Ищем заголовок Inbound flight
		page2.findInboundSection();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		//6 Кликаем первую кнопку select в секции InboundFlight		
		page2.clickInboundFlightSelect();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Кликаем Next
		page2.clickNext();
		
		ChooseFare page3 = PageFactory.initElements(driver, ChooseFare.class);
				
		verificationErrors.append(page3.getErrorOnTextAbsence("Plus"));
		
		// Запоминаем цену багажа
		double luggagePrice = page3.getLuggagePrice();
				
		page3.clickSelectPlus();
		
		// Запоминам итог
		double totalPrice = page3.getTotalPrice();
		// Проверяем, правильно ли подсчитан итог
		Assert.assertTrue((((toPrice+fromPrice)*3)+(luggagePrice*3)==totalPrice),"The total price is wrong");
	}
}
