package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingOverview extends BasePage{
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]")
	private WebElement table;
	
	@FindBy (xpath = "	.//*[@id='top']/div[1]/div/div[1]/div[2]/div[2]/div/div/div/div[3]/a")
	private WebElement bookingDetails;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div[3]/div/p/em/time")
	private WebElement departureTime;
		
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div[4]/div/p/em/time")
	private WebElement arrivalTime;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[1]")
	private WebElement fromCity;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[3]")
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
	
	// Получаем информацию о рейсе
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
