package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingOverview {
	private WebDriverWait wait;
	private final WebDriver driver;
	
	@FindBy (xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]")
	private WebElement table;
	
	@FindBy (xpath = "	.//*[@id='top']/div[1]/div/div[1]/div[2]/div[2]/div/div/div/div[3]/a")
	private WebElement bookingDetails
;
	
	public BookingOverview (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 60);

		if ((!driver.getTitle().equals("View your booking"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/my-transavia/booking/booking-overview/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	public boolean checkTable(){
		return table.isDisplayed();
	}
	
	public BookingOverview clickBookingDetails(){
		bookingDetails.click();
		return this;
	}

}
