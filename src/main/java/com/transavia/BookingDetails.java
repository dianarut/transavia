package com.transavia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookingDetails extends BasePage{
	
	@FindBy (xpath = ".//*[@class = 'flipper']//*[@class = 'front']") //.//*[@id='top']/div/div[5]/div/div/div/section/div[5]/div/div[2]/div/div/div
	private WebElement total;
	
	@FindBy (xpath = ".//*[@class = 'amount'][ancestor :: *[@class = 'panel_section panel_section--content no-padding-bottom']]") //.//*[@id='top']/div/div[6]/section/div/div[2]/div/div/div[2]/div
	private WebElement paymentAmount;
	
	public BookingDetails (WebDriver driver) {
		super(driver);

		if ((!driver.getTitle().equals("Booking details"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/my-transavia/booking/booking-details/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	public double getTotal(){
		wait.until(ExpectedConditions.visibilityOf(total));
		String price = total.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
	public double getPaymentAmount(){
		wait.until(ExpectedConditions.visibilityOf(paymentAmount));
		String price = paymentAmount.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
}
