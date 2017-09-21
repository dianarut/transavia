package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingOverview extends BasePage{
	
	@FindBy (xpath = ".//*[@class = 'flight-details']/*[@class = 'HV-gs--bp0']")
	private WebElement table;
	
	@FindBy (xpath = ".//a[@class = 'icon-left-side icon-animation-next'][parent :: div[@class = 'HV-gu--bp0--x3-3 HV-gu--bp0--33p text-align-right']]") 
	private WebElement bookingDetails;
	
	@FindBy (xpath = ".//time[ancestor :: div[@class = 'HV-gu--bp0--x1-3 HV-gu--bp0--y2-4 HV-gu--bp0--40p']]") 
	private WebElement departureTime;
		
	@FindBy (xpath = ".//time[ancestor :: div[@class = 'HV-gu--bp0--x2-3 HV-gu--bp0--y2-4 HV-gu--bp0--40p']]")
	private WebElement arrivalTime;
	
	@FindBy (xpath = ".//*[contains(@class, 'h5 h5--white no-margin-bottom')]//*[contains(@class, 'nowrap')][1]") 
	private WebElement fromCity;
	
	@FindBy (xpath = ".//*[contains(@class, 'h5 h5--white no-margin-bottom')]//*[contains(@class, 'nowrap')][2]") 
	private WebElement toCity;
	
	public BookingOverview (WebDriver driver) {
		super(driver);
		if ((!driver.getTitle().equals("View your booking"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/my-transavia/booking/booking-overview/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	public boolean checkTable(){
		wait.until(ExpectedConditions.visibilityOf(table));
		return table.isDisplayed();
	}
	
	public BookingOverview clickBookingDetails(){
		wait.until(ExpectedConditions.visibilityOf(bookingDetails));
		bookingDetails.click();
		return this;
	}
	
	public String getDepartureTime(){
		wait.until(ExpectedConditions.visibilityOf(departureTime));
		return departureTime.getText();
	}

	public String getArrivalTime(){
		wait.until(ExpectedConditions.visibilityOf(arrivalTime));
		return arrivalTime.getText();
	}
	
	public String getFromCity(){
		wait.until(ExpectedConditions.visibilityOf(fromCity));
		return fromCity.getText();
	}
	
	public String getToCity(){
		wait.until(ExpectedConditions.visibilityOf(toCity));
		return toCity.getText();
	}
}
