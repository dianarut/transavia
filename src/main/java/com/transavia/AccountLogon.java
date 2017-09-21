package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountLogon extends BasePage{

	
	@FindBy (id = "retrieveBookingByLastname_RecordLocator")
	private WebElement bookingNumber;
	
	@FindBy (id = "retrieveBookingByLastname_LastName")
	private WebElement lastname;
		
	@FindBy (id = "retrieveBookingByLastname_FlightDate-datepicker")
	private WebElement flightDate ;
	
	@FindBy (xpath = ".//*[@class = 'content-block content-block--green']")
	private WebElement justArea;
	
	@FindBy (xpath = ".//*[@class = 'button button-primary'][parent :: *[@class = 'field no-padding-bottom']]")
	private WebElement viewBooking;
	
	public AccountLogon (WebDriver driver) {
		super(driver);
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
	
	// Set values in the fields
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
		wait.until(ExpectedConditions.visibilityOf(viewBooking));
		viewBooking.click();
		return this;
	}
}
