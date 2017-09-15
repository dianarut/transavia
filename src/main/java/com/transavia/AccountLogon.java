package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountLogon {
	private WebDriverWait wait;
	private final WebDriver driver;
	
	@FindBy (id = "retrieveBookingByLastname_RecordLocator")
	private WebElement bookingNumber;
	
	@FindBy (id = "retrieveBookingByLastname_LastName")
	private WebElement lastname;
		
	@FindBy (id = "retrieveBookingByLastname_FlightDate-datepicker")
	private WebElement flightDate ;
	
	@FindBy (xpath = ".//*[@id='top']/section/div/div")
	private WebElement justArea;
	
	@FindBy (xpath = ".//*[@id='access-booking']/div/div/div[4]/button")
	private WebElement viewBooking;
	
	public AccountLogon (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 60);

		if ((!driver.getTitle().equals("Log in"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/my-transavia/account/logon/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	public AccountLogon setBookingNumber(String number){
		bookingNumber.sendKeys(number);
		return this;
	}
	
	public AccountLogon setLastName (String name){
		lastname.sendKeys(name);
		return this;
	}
	
	public AccountLogon setFlightDate (String date){
		flightDate.sendKeys(date);
		return this;
	}
	
	// Устанавливаем значения в поля 
	public AccountLogon setNumberNameDate (String number, String name,String date){
		setBookingNumber(number);
		setLastName(name);
		setFlightDate(date);
		return this;
	}
	
	public String getBookingNumber(){
		return bookingNumber.getAttribute("value");
	}
	
	public String getLastName (){
		return lastname.getAttribute("value");
	}
	
	public String getFlightDate (){
		return flightDate.getAttribute("value");
	}
	
	
	public AccountLogon justClick(){
		justArea.click();
		return this;
	}
	
	public AccountLogon clickViewBooking(){
		viewBooking.click();
		return this;
	}
}
