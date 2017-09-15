package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingDetails {
	private WebDriverWait wait;
	private final WebDriver driver;
	
	@FindBy (xpath = ".//*[@id='top']/div/div[5]/div/div/div/section/div[5]/div/div[2]/div/div/div")
	private WebElement total;
	
	@FindBy (xpath = ".//*[@id='top']/div/div[6]/section/div/div[2]/div/div/div[2]/div")
	private WebElement paymentAmount;
	
	public BookingDetails (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 60);

		if ((!driver.getTitle().equals("Booking details"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/my-transavia/booking/booking-details/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	public double getTotal(){
		String price = total.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
	public double getPaymentAmount(){
		String price = paymentAmount.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
}
