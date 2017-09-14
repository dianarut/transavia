package com.transavia;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults {
	private WebDriverWait wait;
	private final WebDriver driver;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/section/div[1]/nav[2]/form/button")
	private WebElement rightAttowOut;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/section/div[1]/div/div[2]/div/form/ol/*/div[@class = 'day day-with-availability'or @class = 'day day-with-availability is-selected']")
	private WebElement availableFlightOut;

	@FindBy(xpath =".//*[@id='top']/div/div")
	private WebElement body;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]")
	private WebElement toPrice;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]")
	private WebElement fromPrice;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[1]/div/div[1]/h2")
	private WebElement outboundFlight;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement outboundFlightFirstSelect;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement inboundFlightFirstSelect;
	
	@FindBy (xpath = ".//*[@id='top']/form/div[1]/div/footer/div/div/section/div/button")
	private WebElement nextButton;
	
	@FindBy (xpath = "//*[@id='flights']/div/section/div[2]/div[1]/div/div/div[1]/div/div/div[2]/p")
	private WebElement errorMessage;
	
	@FindBy (xpath = ".//*[@id='flights']/div/section/div[2]/div[1]/div[1]")
	private WebElement outboundSection;
	
	@FindBy (id = "openJawRouteSelection_DepartureStationOutbound-input")
	private WebElement fromOutbound;
	
	@FindBy (id = "openJawRouteSelection_ArrivalStationOutbound-input")
	private WebElement toOutbound;
	
	@FindBy (xpath = ".//*[@id='flights']/div/section/div[2]/div[1]/div[1]/div/div/div[3]/div/div[1]/span")
	private WebElement calendarIcon;
	
	@FindBy (id = "dateSelection_OutboundDate-datepicker")
	private WebElement calendarField;
	
	@FindBy (xpath = ".//*[@id='flights']/div/section/div[2]/div[1]/div[2]")
	private WebElement inboundSection;
	
	@FindBy (id = "openJawRouteSelection_DepartureStationInbound-input")
	private WebElement fromInbound;
	
	@FindBy (id = "openJawRouteSelection_ArrivalStationInbound-input")
	private WebElement toInbound;
	
	@FindBy (xpath =".//*[@id='flights']/div/section/div[3]/div/button[2]")
	private WebElement searchButton;
	
	@FindBy (xpath =".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li/div[@class='day day-with-availability']")
	private WebElement outboundAvailableDate;
	
	@FindBy (xpath =".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li/div[@class='day day-with-availability']")
	private WebElement inboundAvailableDate;
	
	@FindBy (xpath =".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement selectOutboundButton;
	
	@FindBy (xpath =".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement selectInboundButton;
		
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]")
	private WebElement outPrice;
	
	@FindBy (xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]")
	private WebElement inPrice;
	
	@FindBy (xpath = ".//*[@id='top']/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]")
	private WebElement totalPrice;
	
	public SearchResults(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 200);
		// Verifying that we have rigth page
		if ((!driver.getTitle().equals("Book a flight"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/book-a-flight/flights/search/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}
	
	//Поиск доступных рейсов в период от 1 до 7 дней
	public boolean searchAvailableFlight() {
		wait.until(ExpectedConditions.visibilityOf(rightAttowOut));
		rightAttowOut.click();
		return availableFlightOut.getAttribute("class").contains("day day-with-availability");
		}
	
	//Формирование текста ошибки
	public String getErrorOnTextAbsence(String search_string) {
		if (!pageTextContains2(search_string)) {
			return "No '" + search_string + "' is found inside page text!\n";
		} else {
			return "";
		}
	}
	
	// Поиск строки на странице 
	public boolean pageTextContains2(String search_string) {
		return body.getText().contains(search_string);
	}
	
	//Получем цену перелета туда
	public double getToPrice(){
		wait.until(ExpectedConditions.visibilityOf(toPrice));		
		String tPrice = toPrice.getText();
		return Double.parseDouble(tPrice.replace("€ ", ""));		
	}
	
	//Получем цену перелета обратно
	public double getFromPrice(){
		wait.until(ExpectedConditions.visibilityOf(fromPrice));		
		String frPrice = fromPrice.getText();
		return Double.parseDouble(frPrice.replace("€ ", ""));		
	}
	
	//Кликаем на первую кнопку селект в секции OutboundFlight
	public SearchResults clickOutboundFlightSelect() {
		wait.until(ExpectedConditions.visibilityOf(outboundFlightFirstSelect));
		outboundFlightFirstSelect.click();
		return this;
	}
	
	// Кликаем на первую кнопку селект в секции InboundFlight
	public SearchResults clickInboundFlightSelect() {
		wait.until(ExpectedConditions.visibilityOf(inboundFlightFirstSelect));
		inboundFlightFirstSelect.click();
		return this;
	}
	
	// Кликаем Next
	public SearchResults clickNext() {
		wait.until(ExpectedConditions.visibilityOf(nextButton));
		nextButton.click();
		return this;
	}
	
	// Проверка наличия сообщения об ошибке
	public boolean errorMessageEquals(String search_string) {
		return errorMessage.getText().equals(search_string);
	}
	
	// Проверяем наличие секции Outbound
	public boolean findOutboundSection() {
		if (outboundSection != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// Заполнение from.
	public SearchResults setFromOutboundLocator(String from_outbound) {
		wait.until(ExpectedConditions.visibilityOf(fromOutbound));
		fromOutbound.clear();
		fromOutbound.sendKeys(from_outbound);
		return this;
	}

	// Заполнение to.
	public SearchResults setToOutboundLocator(String to_outbound) {
		wait.until(ExpectedConditions.visibilityOf(toOutbound));
		toOutbound.clear();
		toOutbound.sendKeys(to_outbound);
		return this;
	}

	// Заполнение всех полей формы.
	public SearchResults setFieldsFromToOutbound(String from_outbound, String to_outbound) {
		setFromOutboundLocator(from_outbound);
		setToOutboundLocator(to_outbound);
		return this;
	}
	
	// Получение значения From Outbound
	public String getFromOutbound() {
		return fromOutbound.getAttribute("value");
	}
	
	// Получение значения To Outbound
	public String getToOutbound() {
		return toOutbound.getAttribute("value");
	}
	
	// Устанавливает текущую дату в OutboundDate
	public SearchResults setOutboundDate() {
		calendarIcon.click();
		calendarField.clear();
		calendarField.sendKeys(currentDate());;		 
		return this;
	}
	
	//Создает строку, содержащую дату в формате d.MM.yyyy
	public String currentDate(){
		 Date dateNow = new Date(0);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
		 dateFormat.format(dateNow);
		 String date = dateFormat.format(new Date(System.currentTimeMillis()));
		 return date;
	}
	
	// Проверяет установку даты в OutboundDate
	public boolean checkOutboundDate(){
		return calendarField.getAttribute("value").contains(currentDate());
	}
	
	public boolean findInboundSection() {
		if (inboundSection != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// Заполнение from.
	public SearchResults setFromInboundLocator(String from_inbound) {
		wait.until(ExpectedConditions.visibilityOf(fromInbound));
		fromInbound.clear();
		fromInbound.sendKeys(from_inbound);
		return this;
	}

	// Заполнение to.
	public SearchResults setToInboundLocator(String to_inbound) {
		wait.until(ExpectedConditions.visibilityOf(toInbound));
		toInbound.clear();
		toInbound.sendKeys(to_inbound);
		return this;
	}

	// Заполнение всех полей формы.
	public SearchResults setFieldsFromToInbound(String from_inbound, String to_inbound) {
		setFromInboundLocator(from_inbound);
		setToInboundLocator(to_inbound);
		return this;
	}
	
	// Получение значения From Inbound
	public String getFromInbound() {
		return fromInbound.getAttribute("value");
	}
	
	// Получение значения To Inbound
	public String getToInbound() {
		return toInbound.getAttribute("value");
	}
	
	// Кликаем Search
	public SearchResults clickSearch() {
		searchButton.click();
		return this;
	}
	
	// Выделяем первый рейс
	public SearchResults selectOutboundAvailableDate(){
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));
		outboundAvailableDate.click();
		outboundAvailableDate.click();
		return this;
	}
	
	// Получаем стоимость первого рейса
	public double getOutPrice(){
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));		
		String price = outPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
	// Выделяем второй рейс
	public SearchResults selectInboundAvailableDate(){
		wait.until(ExpectedConditions.visibilityOf(inboundAvailableDate));
		inboundAvailableDate.click();
		return this;
	}
	
	//Получаем стоимоть второго рейса
	public double getInPrice(){
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));		
		String price = inPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
	
	// Выбираем первый рейс
	public SearchResults clickSelectOutbound(){
		wait.until(ExpectedConditions.visibilityOf(selectOutboundButton));
		selectOutboundButton.click();
		return this;
	}
	
	// Выбираем второй рейс
	public SearchResults clickSelectInbound(){
		wait.until(ExpectedConditions.visibilityOf(selectInboundButton));
		selectInboundButton.click();
		return this;
	}
	
	// Получаем итоговую стоимось поездки
	public double getTotalPrice(){
		wait.until(ExpectedConditions.visibilityOf(outboundAvailableDate));	
		String price = totalPrice.getText();
		return Double.parseDouble(price.replace("€ ", ""));
	}
}
