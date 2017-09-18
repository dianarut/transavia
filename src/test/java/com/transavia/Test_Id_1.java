package com.transavia;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		driver.get(base_url + "/en-UK/home/");
		String from = "Edinburgh, United Kingdom";
		String to = "Paris (Orly South), France";
		int adults = 3;
		int children = 2;
		int babies = 1;

		WhereDoYouWantToGo page1 = PageFactory.initElements(driver, WhereDoYouWantToGo.class);
		
		//1. Проверка наличия секции "Where do you want to go?" 
		Assert.assertTrue(page1.isSectionWhereDoYouWantToGoPresent(), "No suitable forms found!");
		
		//2. Проверка наличия выпадающего списка у From
		Assert.assertTrue(page1.checkFromDropDownList(from), "No FROM drop-down list found!");
		
		//2. Установка значения From
		page1.setFrom(from);
		
		//2. Проверка уставки значения From
		Assert.assertEquals(page1.getFrom(),from, "Unable to fill 'From' field");
		
		// Чтобы выпадающий список From не заслонял собой поле To, кликаем на на посторонюю область на сайте
		page1.clickOnOtherArea();
		
		//3. Проверка наличия выпадающего списка у To
		Assert.assertTrue(page1.checkToDropDownList(to), "No TO drop-down list found!");
		
		//3.  Установка значения To
		page1.setTo(to);
		
		//3. Проверка уставки значения To 
		Assert.assertEquals(page1.getTo(), to, "Unable to fill 'To' field");
		
		// Чтобы выпадающий список To не заслонял собой иконку календаря, кликаем на на посторонюю область на сайте
		page1.clickOnOtherArea();
		
		// 4. Устанавливаем текущую дату в Depart On
		page1.setDepartOnDate();
		
		// 4. Проверка, что дата установлена верно в Depart On
		Assert.assertTrue(page1.checkDepartOnDate(), "The Depart On date is wrong!");
			
		// 5. Переводим checkbox "Return on" в состоянии unchecked
		page1.clickReturnOn();
		
		// 5. Проверяем, что checkbox "Return on" в состоянии unchecked
		Assert.assertFalse(page1.checkReturnOn(), "The checkbox Retun On is checked!");
		
		// 6. Устанавливаем необходимое колическо пассажиров
		page1.setNumberOfPassengers(adults,children,babies);
		
		
		// 6. Проверяем, что установилось заданное количество пассажиров
		Assert.assertEquals(page1.getNumberOfAduls(), adults);
		Assert.assertEquals(page1.getNumberOfChildren(), children);
		Assert.assertEquals(page1.getNumberOfBabies(), babies);
		
		page1.clickOnOtherArea();
		// 7. Кликаем Search
		page1.clickSearch();
		
		SearchResults page2 = PageFactory.initElements(driver, SearchResults.class);
		// . Проверяем, есть ли доступный рейс в период от 1 до 7 днейй
		Assert.assertTrue(page2.searchAvailableFlight(), "No available flight found!");
	}
}
