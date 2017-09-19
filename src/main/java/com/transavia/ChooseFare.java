package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChooseFare extends BasePage{
	
	@FindBy (xpath = ".//*[@class = 'th position-relative cursor-pointer' and @data-product-class = 'B']") 
	private WebElement plusFare;
	
	@FindBy (xpath = ".//*[@class = 'th position-relative cursor-pointer' and @data-product-class = 'B']/*[@class = 'price pull-right']")
	private WebElement luggagePrice;
	
	@FindBy (xpath = " .//*[@class = 'button button--selection' and @value = 'B'][ancestor :: *[@class ='is-hidden is-visible-block--bp25']]") 
	private WebElement selectPlus;
	
	@FindBy (xpath = ".//*[@class = 'back'][child :: *[@class ='price-decimals']]")
	private WebElement totalPrice;
	
	public ChooseFare(WebDriver driver) {
		super(driver);
		if (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/book-a-flight/choose-a-fare/select/")) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
		
	public boolean pageTextContains(String search_string) {
		return plusFare.getText().contains(search_string);
	}
	
	// Create the error messege
	public String getErrorOnTextAbsence(String search_string) {
		if (!pageTextContains(search_string)) {
			return "No '" + search_string + "' is found inside page text!\n";
		} else {
			return "";
		}
	}
	
	public double getLuggagePrice(){
		wait.until(ExpectedConditions.visibilityOf(luggagePrice));		
		String luggPrice = luggagePrice.getText();
		return Double.parseDouble(luggPrice.replace("+ € ", "").replace(",", ""));		
	}

	// Click "Select" in section Plus
	public ChooseFare clickSelectPlus(){
		selectPlus.click();
		return this;
	}
	
	public double getTotalPrice(){
		wait.until(ExpectedConditions.visibilityOf(totalPrice));		
		String sTotalPrice = totalPrice.getText();
		return Double.parseDouble(sTotalPrice.replace(",", "").replace("€ ", ""));		
	}
	
}