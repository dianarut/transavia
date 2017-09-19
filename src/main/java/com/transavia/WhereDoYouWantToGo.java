package com.transavia;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WhereDoYouWantToGo extends BasePage{
	
	@FindBy (id = "desktop")
	private WebElement sectionWherDoYouWantToGo;
	
	@FindBy (id = "routeSelection_DepartureStation-input")
	private WebElement from;
	
	@FindBy (id = "routeSelection_ArrivalStation-input")
	private WebElement to;

	@FindBy (xpath = "//div [@class='autocomplete-results'][preceding-sibling :: input [@id = 'routeSelection_DepartureStation-input']]")
	private WebElement fromDropDownList;
	
	@FindBy (xpath = "//div [@class='autocomplete-results'][preceding-sibling :: input [@id = 'routeSelection_ArrivalStation-input']]")
	private WebElement toDropDownListl;
	
	@FindBy (xpath = ".//*[@id='top']/div[2]")
	private WebElement image;
	
	@FindBy (xpath = "//span [@class='datepicker-trigger icon-font icon-calendar'][preceding-sibling :: input [@id = 'dateSelection_OutboundDate-datepicker']]") 
	private WebElement  calendarIcon;

	@FindBy (id = "dateSelection_OutboundDate-datepicker")
	private WebElement calendarField;
	
	@FindBy (xpath = "//label [@class='h6'][preceding-sibling :: input [@id = 'dateSelection_IsReturnFlight']]") 
	private WebElement returnOn;
	
	@FindBy (id = "booking-passengers-input")
	private WebElement numberOfPassengers;
	
	@FindBy (xpath = "//button [@class='button button-secondary increase'][preceding-sibling :: div [child :: select [@id = 'booking-adults']]]") 
	private WebElement plusAdults;
	
	@FindBy (xpath = "//button [@class='button button-secondary increase'][preceding-sibling :: div [child :: select [@id = 'booking-children']]]")
	private WebElement plusChildren;
	
	@FindBy (xpath = "//button [@class='button button-secondary increase'][preceding-sibling :: div [child :: select [@id = 'booking-infants']]]") 
	private WebElement plusBabies;
	
	@FindBy (xpath = "//div [@class='textfield'][preceding-sibling :: div [child :: select [@id = 'booking-adults']]]/input") 
	private WebElement numberAdults;
	
	@FindBy (xpath = "//div [@class='textfield'][preceding-sibling :: div [child :: select [@id = 'booking-children']]]/input") 
	private WebElement numberChildren;
	
	@FindBy (xpath = "//div [@class='textfield'][preceding-sibling :: div [child :: select [@id = 'booking-infants']]]/input") 
	private WebElement numberBabies;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/button") 
	private WebElement searchButton;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div[3]/ul/li[2]/a")
	private WebElement AddMultipleDestinations;
	
	@FindBy (xpath ="html/body/header/nav/div[1]/div[1]/ul/li[3]/a")
	private WebElement manageYourBooking;
	
	@FindBy (xpath =".//a[@class = 'sub-navigation_link sub-navigation-level-1_link h5'][descendant :: span[@class = 'stamp icon-font icon-account']]") 
	private WebElement  viewYourBooking;
	
	public WhereDoYouWantToGo(WebDriver driver) {
		super(driver);
		if ((!driver.getTitle().equals("Transavia is the airline of choice for affordable flights!"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/home/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}

	//  Check if "Where do you want to go?" is  available 
	public boolean isSectionWhereDoYouWantToGoPresent() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (sectionWherDoYouWantToGo.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if "From" drop-down list is visible
	public boolean checkFromDropDownList(String search_string) {
		wait.until((ExpectedConditions.visibilityOf (from)));
		from.click();
		return fromDropDownList.getAttribute("class").contains("autocomplete-results");
	}
	
	// Set "From"
	public WhereDoYouWantToGo setFrom(String sfrom) {
		from.clear();
		from.sendKeys(sfrom);
		
		return this;
	}

	// Get "From"
	public String getFrom() {
		return from.getAttribute("value");
	}
	
	// Check if "To" drop-down list is visible
	public boolean checkToDropDownList(String search_string) {
		wait.until(ExpectedConditions.visibilityOf(to));
		to.click();
		return 	toDropDownListl.getAttribute("class").contains("autocomplete-results");
	}
	
	// Set "To"
	public WhereDoYouWantToGo setTo(String sto) {
		to.clear();
		to.sendKeys(sto);
		return this;
	}

	// Get "From"
	public String getTo() {
		return to.getAttribute("value");
	}
	
	// Just click
	public WhereDoYouWantToGo clickOnOtherArea(){
		image.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//Set "From" and "To" 
	public void setFromTo(String sfrom,String sto){
		checkFromDropDownList(sfrom);
		setFrom(sfrom);
		getFrom();
		clickOnOtherArea();
		checkToDropDownList(sto);
		setTo(sto);
		getTo();
		clickOnOtherArea();
	}
	
	//Create a string with date d.MM.yyyy
	public String currentDate(){
		 Date dateNow = new Date(0);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
		 dateFormat.format(dateNow);
		 String date = dateFormat.format(new Date(System.currentTimeMillis()));
		 return date;
	}
	
	
	// Set current date in "Depart On"
	public WhereDoYouWantToGo setDepartOnDate() {
		calendarIcon.click();
		calendarField.clear();
		calendarField.sendKeys(currentDate());;		 
		return this;
	}
	
	// Check current date in "Depart On"
	public boolean checkDepartOnDate(){
		return calendarField.getAttribute("value").contains(currentDate());
	}
	
	//Click "Return On"
	public WhereDoYouWantToGo clickReturnOn() {
		returnOn.click();
	return this;
	}
	
	//Check "Return On"
	public boolean checkReturnOn(){
		return returnOn.isSelected();
	}
	
	//Add adult
	public WhereDoYouWantToGo plusAdults(){
		plusAdults.click();
		return this;
	}
	
	// Add child
	public WhereDoYouWantToGo plusChildren(){
		plusChildren.click();
		return this;
	}
	
	// Add baby
	public WhereDoYouWantToGo plusBabies(){
		plusBabies.click();
		return this;
	}
	
	public void setNumberOfPassengers(int adults,int children,int babies){
		numberOfPassengers.click();
		while(adults-1>0){
			plusAdults();
			adults--;
		}

		while(children>0){
			plusChildren();
			children--;
		}

		while(babies>0){
			plusBabies();
			babies--;
		}
	}
	
	// Get number of adults
	public int getNumberOfAduls(){
		return Integer.parseInt(numberAdults.getAttribute("value"));
	}
	
	// Get number of children
	public int getNumberOfChildren(){
		return Integer.parseInt(numberChildren.getAttribute("value"));
	}
	
	// Get number of babies
	public int getNumberOfBabies(){
		return Integer.parseInt(numberBabies.getAttribute("value"));
	}
	
	
	public WhereDoYouWantToGo clickSearch() {
		try {
			searchButton.click();
		} catch (TimeoutException ignore) {
		}
		return this;
	}
	
	public WhereDoYouWantToGo clickAddMultipleDestinations(){
		wait.until(ExpectedConditions.visibilityOf(AddMultipleDestinations));
		AddMultipleDestinations.click();
		return this;
	}
	
	public WhereDoYouWantToGo clickManageYourBooking(){
		wait.until(ExpectedConditions.visibilityOf(manageYourBooking));
		manageYourBooking.click();
		return this;
	}
	
	public WhereDoYouWantToGo clickViewYourBooking(){
		wait.until(ExpectedConditions.visibilityOf(viewYourBooking));
		viewYourBooking.click();
		return this;
	}
	
	
}
