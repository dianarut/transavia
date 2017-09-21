package com.transavia;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResults extends BasePage {

	@FindBy(xpath = ".//*[@class = 'button button-secondary'][ancestor :: *[@class = 'flight outbound']]") 
	private WebElement rightAttowOut;

	@FindBy(xpath = ".//*[@class = 'day day-with-availability'or @class = 'day day-with-availability is-selected'][ancestor :: *[@class = 'flight outbound']]")
	private WebElement availableFlightOut;

	@FindBy(xpath = ".//*[@id='top']/div/div")
	private WebElement body;

	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gu-buttons--bp0--y1-1 HV-gu-buttons--bp0--x1-3 HV-gu--bp0--33p HV-gu-buttons--bp20--y1-1 HV-gu-buttons--bp20--x2-5 HV-gu--bp20--20p HV-gu-buttons--bp27--y1-1 HV-gu-buttons--bp27--x3-7 HV-gu--bp27--14p']]") //.//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]
	private WebElement toPrice;

	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gs--bp0 HV-gs-type-g--bp25']]") 
	private WebElement fromPrice;

	@FindBy(xpath = ".//*[@class = 'h4'][child :: *[@class = 'icon-font icon-outbound']]") 
	private WebElement outboundFlight;						

	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gu-buttons--bp0--y1-1 HV-gu-buttons--bp0--x1-3 HV-gu--bp0--33p HV-gu-buttons--bp20--y1-1 HV-gu-buttons--bp20--x2-5 HV-gu--bp20--20p HV-gu-buttons--bp27--y1-1 HV-gu-buttons--bp27--x3-7 HV-gu--bp27--14p']]") 					
	private WebElement outboundFlightFirstSelect;

	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gs--bp0 HV-gs-type-g--bp25']]")
	private WebElement inboundFlightFirstSelect;

	@FindBy(xpath = ".//*[@class = 'panel panel-total']//*[@name = 'next_button']")
	private WebElement nextButton;

	@FindBy(xpath = "//*[@class = 'notification-message notification-inline notification-error']/p") 
	private WebElement errorMessage;

	@FindBy(xpath = ".//*[@class = 'h4'][child :: *[@class = 'icon-font icon-outbound']]") 
	private WebElement outboundSection;

	@FindBy(id = "openJawRouteSelection_DepartureStationOutbound-input")
	private WebElement fromOutbound;

	@FindBy(id = "openJawRouteSelection_ArrivalStationOutbound-input")
	private WebElement toOutbound;
	
	@FindBy(xpath = ".//*[@class='datepicker-trigger icon-font icon-calendar'][preceding-sibling :: *[@id = 'dateSelection_OutboundDate-datepicker']]") 
	private WebElement calendarIcon;

	@FindBy(id = "dateSelection_OutboundDate-datepicker")
	private WebElement calendarField;

	@FindBy(xpath = ".//*[@class = 'h4'][child :: *[@class = 'icon-font icon-inbound']]")
	private WebElement inboundSection;

	@FindBy(id = "openJawRouteSelection_DepartureStationInbound-input")
	private WebElement fromInbound;

	@FindBy(id = "openJawRouteSelection_ArrivalStationInbound-input")
	private WebElement toInbound;

	@FindBy(xpath = ".//*[@class = 'button button-primary'][ancestor :: *[@class ='panel_section panel_section--button-search']]") 
	private WebElement searchButton;

	@FindBy(xpath = ".//*[@class='day day-with-availability' or @class='day day-with-availability is-selected'][ancestor :: *[@class = 'flight outbound']]")
	private WebElement outboundAvailableDate;

	@FindBy(xpath = ".//*[@class='day day-with-availability' or @class='day day-with-availability is-selected'][ancestor :: *[@class = 'flight inbound']]") 
	private WebElement inboundAvailableDate;

	@FindBy(xpath = ".//*[@class = 'panel flight-result active'][ancestor :: *[@class = 'flight outbound']]")
	private WebElement selectOutboundButton;

	@FindBy(xpath = ".//*[@class = 'panel flight-result active'][ancestor :: *[@class = 'flight inbound']]") 
	private WebElement selectInboundButton;

	//formPrice
	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gu-buttons--bp0--y1-1 HV-gu-buttons--bp0--x1-3 HV-gu--bp0--33p HV-gu-buttons--bp20--y1-1 HV-gu-buttons--bp20--x2-5 HV-gu--bp20--20p HV-gu-buttons--bp27--y1-1 HV-gu-buttons--bp27--x3-7 HV-gu--bp27--14p']]")
	private WebElement outPrice;
	//toPrice
	@FindBy(xpath = ".//*[@class = 'price'][ancestor :: *[@class = 'HV-gs--bp0 HV-gs-type-g--bp25']]")
	private WebElement inPrice;

	@FindBy(xpath = ".//*[@class = 'back'][ancestor :: *[@class = 'grand-total__price-container']]") 
	private WebElement totalPrice;

	
	@FindBy (xpath = ".//*[@class = 'panel panel--rounded-group'][preceding-sibling :: *[@value = 'InboundFlight']]") 
	private WebElement inboundFlightInforamation;
	
	public SearchResults(WebDriver driver) {
		super(driver);
		if ((!driver.getTitle().equals("Book a flight"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/book-a-flight/flights/search/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}

	// Check if there is available flight
	public boolean searchAvailableFlight() {
		wait.until(ExpectedConditions.visibilityOf(rightAttowOut));
		rightAttowOut.click();
		return availableFlightOut.getAttribute("class").contains("day day-with-availability");
	}

	public boolean findSection (WebElement element, String sectionName){
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText().contains(sectionName);
	}
	
	// Create the error messege
	public String getErrorOnTextAbsence(WebElement element, String sectionName) {
		if (!findSection(element,sectionName)) {
			return "No '" + sectionName + "' is found inside page text!\n";
		} else {
			return "";
		}
	}
	
	// Search section "Outbound flight"
	public void findOutboundFlight(){
		getErrorOnTextAbsence(outboundSection, "Outbound flight");
	}
	
	// Search section "Inbound flight"
	public void findInboundFlight(){
		getErrorOnTextAbsence(inboundSection, "Inbound flight");
	}
	
	
	public double getToPrice() {
		wait.until(ExpectedConditions.visibilityOf(toPrice));
		String tPrice = toPrice.getText();
		return Double.parseDouble(tPrice.replace("€ ", "").replace(",", "").replace("From", ""));
	}

	
	public double getFromPrice() {
		wait.until(ExpectedConditions.visibilityOf(fromPrice));
		String frPrice = fromPrice.getText();
		return Double.parseDouble(frPrice.replace("€ ", "").replace(",", "").replace("From", ""));
	}

	// Click "Select" in section "Outbound flight"
	public SearchResults clickOutboundFlightSelect() {
		wait.until(ExpectedConditions.visibilityOf(outboundFlightFirstSelect));
		outboundFlightFirstSelect.click();
		return this;
	}

	// Click "Select" in section "Inbound flight"
	public SearchResults clickInboundFlightSelect() {
		wait.until(ExpectedConditions.visibilityOf(inboundFlightFirstSelect));
		inboundFlightFirstSelect.click();
		return this;
	}

	// Click "Next"
	public SearchResults clickNext() {
		wait.until(ExpectedConditions.visibilityOf(nextButton));
		nextButton.click();
		return this;
	}

	// Check if error massage is there
	public boolean errorMessageEquals(String search_string) {
		return errorMessage.getText().equals(search_string);
	}

	// Search section "Outbound flight"
	public boolean findOutboundSection() {
		wait.until(ExpectedConditions.visibilityOf(outboundSection));	
		if (outboundSection != null) {
			return true;
		} else {
			return false;
		}
	}


	public SearchResults setFromOutboundLocator(String from_outbound) {
		wait.until(ExpectedConditions.visibilityOf(fromOutbound));
		fromOutbound.clear();
		fromOutbound.sendKeys(from_outbound);
		return this;
	}


	public SearchResults setToOutboundLocator(String to_outbound) {
		wait.until(ExpectedConditions.visibilityOf(toOutbound));
		toOutbound.clear();
		toOutbound.sendKeys(to_outbound);
		return this;
	}

	// Set "From" and "To" in section "Outbound"
	public SearchResults setFieldsFromToOutbound(String from_outbound, String to_outbound) {
		setFromOutboundLocator(from_outbound);
		setToOutboundLocator(to_outbound);
		return this;
	}


	public String getFromOutbound() {
		return fromOutbound.getAttribute("value");
	}


	public String getToOutbound() {
		return toOutbound.getAttribute("value");
	}

	// Set current date in "Outbound"
	public SearchResults setOutboundDate() {
		calendarIcon.click();
		calendarField.clear();
		calendarField.sendKeys(currentDate());
		return this;
	}

	// Create a string with date d.MM.yyyy
	public String currentDate() {
		Date dateNow = new Date(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
		dateFormat.format(dateNow);
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		return date;
	}

	// Check date in "OutboundDate"
	public boolean checkOutboundDate() {
		return calendarField.getAttribute("value").contains(currentDate());
	}

	public boolean findInboundSection() {
		if (inboundSection != null) {
			return true;
		} else {
			return false;
		}
	}


	public SearchResults setFromInboundLocator(String from_inbound) {
		wait.until(ExpectedConditions.visibilityOf(fromInbound));
		fromInbound.clear();
		fromInbound.sendKeys(from_inbound);
		return this;
	}


	public SearchResults setToInboundLocator(String to_inbound) {
		wait.until(ExpectedConditions.visibilityOf(toInbound));
		toInbound.clear();
		toInbound.sendKeys(to_inbound);
		return this;
	}

	// Set "From" and "To"
	public SearchResults setFieldsFromToInbound(String from_inbound, String to_inbound) {
		setFromInboundLocator(from_inbound);
		setToInboundLocator(to_inbound);
		return this;
	}

	public String getFromInbound() {
		return fromInbound.getAttribute("value");
	}

	public String getToInbound() {
		return toInbound.getAttribute("value");
	}

	public SearchResults clickSearch() {
		searchButton.click();
		return this;
	}

	// Click on first available flight in Outbound
	public SearchResults selectOutboundAvailableDate() {
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));
		outboundAvailableDate.click();
		return this;
	}

	public double getOutPrice() {
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));
		String price = outPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}

	// Click on first available flight in Inbound
	public SearchResults selectInboundAvailableDate() {
		wait.until(ExpectedConditions.visibilityOf(inboundAvailableDate));
		inboundAvailableDate.click();
		return this;
	}

	public double getInPrice() {
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));
		String price = inPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}

	// Select first available flight in Outbound
	public SearchResults clickSelectOutbound() {
		wait.until(ExpectedConditions.visibilityOf(selectOutboundButton));
		selectOutboundButton.click();
		return this;
	}

	// Select on first available flight in Inbound
	public SearchResults clickSelectInbound() {
		wait.until(ExpectedConditions.visibilityOf(selectInboundButton));
		selectInboundButton.click();
		return this;
	}

	public double getTotalPrice() {
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));
		String price = totalPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
}
