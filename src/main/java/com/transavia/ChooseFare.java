package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChooseFare extends BasePage{
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/thead/tr/th[3]")
	private WebElement plusFare;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/thead/tr/th[3]/span[2]")
	private WebElement luggagePrice;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/tfoot/tr/td[3]/div/div/button[1]")
	private WebElement selectPlus;
	
	@FindBy (xpath = ".//*[@id='top']/div[2]/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]")
	private WebElement totalPrice;
	
	public ChooseFare(WebDriver driver) {
		super(driver);
		// Провекрка того факта, что мы на верной странице.
		if (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/book-a-flight/choose-a-fare/select/")) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	

	// Поиск строки на странице 	
	public boolean pageTextContains(String search_string) {
		return plusFare.getText().contains(search_string);
	}
	//Формирование текста ошибки
	public String getErrorOnTextAbsence(String search_string) {
		if (!pageTextContains(search_string)) {
			return "No '" + search_string + "' is found inside page text!\n";
		} else {
			return "";
		}
	}
	
	// Получаем стоимость багажа
	public double getLuggagePrice(){
		wait.until(ExpectedConditions.visibilityOf(luggagePrice));		
		String luggPrice = luggagePrice.getText();
		return Double.parseDouble(luggPrice.replace("+ € ", "").replace(",", ""));		
	}

	// Выбираем тип поездки Plus
	public ChooseFare clickSelectPlus(){
		selectPlus.click();
		return this;
	}
	
	// Получаем итоговую цену за перелет
	public double getTotalPrice(){
		wait.until(ExpectedConditions.visibilityOf(totalPrice));		
		String sTotalPrice = totalPrice.getText();
		return Double.parseDouble(sTotalPrice.replace(",", "").replace("€ ", ""));		
	}
	
}