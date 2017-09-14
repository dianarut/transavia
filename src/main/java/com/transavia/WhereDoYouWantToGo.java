package com.transavia;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WhereDoYouWantToGo extends Base {

	private WebDriverWait wait;
	private final WebDriver driver;
	
	@FindBy (id = "desktop")
	private WebElement sectionWherDoYouWantToGo;
	
	@FindBy (id = "routeSelection_DepartureStation-input")
	private WebElement from;
	
	@FindBy (id = "routeSelection_ArrivalStation-input")
	private WebElement to;

	@FindBy (xpath = "//*[@id='desktop']/section/div/div/div[1]/div/div/div/div/div/div")
	private WebElement fromDropDownList;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/div[2]/div/div/div/div/div/div")
	private WebElement toDropDownListl;
	
	@FindBy (xpath = ".//*[@id='top']/div[2]/section")
	private WebElement image;
	
	@FindBy (xpath = " .//*[@id='desktop']/section/div[2]/div[2]/div/div/div[1]/div/div[1]/span")
	private WebElement  calendarIcon;

	@FindBy (id = "dateSelection_OutboundDate-datepicker")
	private WebElement calendarField;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/div/div/div/div/div/label")
	private WebElement returnOn;
	
	@FindBy (id = "booking-passengers-input")
	private WebElement numberOfPassengers;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/div/div/div/div/div[1]/div/div/div/div/div/button[2]")
	private WebElement plusAdults;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div/div/button[2]")
	private WebElement plusChildren;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[3]/div/div/div[2]/div/div/button[2]")
	private WebElement plusBabies;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[1]/div/div/div[2]/div/div/div[2]/input")
	private WebElement numberAdults;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/input")
	private WebElement numberChildren;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/div/div/div/div/div[3]/div/div/div/div/div/div/input")
	private WebElement numberBabies;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div/div/button")
	private WebElement searchButton;
	
	@FindBy (xpath = ".//*[@id='desktop']/section/div[3]/ul/li[2]/a")
	private WebElement AddMultipleDestinations;
	
	public WhereDoYouWantToGo(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 60);
		// Verifying that we have rigth page
		if ((!driver.getTitle().equals("Transavia is the airline of choice for affordable flights!"))
				|| (!driver.getCurrentUrl().equals("https://www.transavia.com/en-UK/home/"))) {
			throw new IllegalStateException("Wrong site page!");
		}
	}

	//Проверка наличия секции "Where do you want to go?"
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
	
	//Проверка наличия выпадающего списка From
	public boolean checkFromDropDownList(String search_string) {
		wait.until((ExpectedConditions.visibilityOf (from)));
		from.click();
		return fromDropDownList.getAttribute("class").contains("autocomplete-results");
	}
	
	// Заполнение From.
	public WhereDoYouWantToGo setFrom(String sfrom) {
		from.clear();
		from.sendKeys(sfrom);
		return this;
	}

	// Получение значения From.
	public String getFrom() {
		return from.getAttribute("value");
	}
	
	//Проверка наличия выпадающего списка To
	public boolean checkToDropDownList(String search_string) {
		wait.until(ExpectedConditions.visibilityOf(to));
		to.click();
		return 	toDropDownListl.getAttribute("class").contains("autocomplete-results");
	}
	
	// Заполнение To.
	public WhereDoYouWantToGo setTo(String sto) {
		to.clear();
		to.sendKeys(sto);
		return this;
	}

	// Получение значения To.
	public String getTo() {
		return to.getAttribute("value");
	}
	
	// Клик на постороннюю область
	public WhereDoYouWantToGo clickOnOtherArea(){
		image.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//заполнение from to
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
	
	//Создает строку, содержащую дату в формате d.MM.yyyy
	public String currentDate(){
		 Date dateNow = new Date(0);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
		 dateFormat.format(dateNow);
		 String date = dateFormat.format(new Date(System.currentTimeMillis()));
		 return date;
	}
	
	
	// Устанавливает текущую дату в в Depart On
	public WhereDoYouWantToGo setDepartOnDate() {
		calendarIcon.click();
		calendarField.clear();
		calendarField.sendKeys(currentDate());;		 
		return this;
	}
	
	// Проверяет установку даты в Depart On
	public boolean checkDepartOnDate(){
		return calendarField.getAttribute("value").contains(currentDate());
	}
	
	//Клик по Return On
	public WhereDoYouWantToGo clickReturnOn() {
		returnOn.click();
	return this;
	}
	
	//Проверка состояния Return On
	public boolean checkReturnOn(){
		return returnOn.isSelected();
	}
	
	//Добавляем одного взрослога пассажира
	public WhereDoYouWantToGo plusAdults(){
		plusAdults.click();
		return this;
	}
	
	// Добавляем одного ребенка в пассажиры
	public WhereDoYouWantToGo plusChildren(){
		plusChildren.click();
		return this;
	}
	
	// Добавляем одного младенца в пассажиры
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
	
	// Получаем количество взрослых пассажиров
	public int getNumberOfAduls(){
		return Integer.parseInt(numberAdults.getAttribute("value"));
	}
	
	//Получаем количество детей
	public int getNumberOfChildren(){
		return Integer.parseInt(numberChildren.getAttribute("value"));
	}
	
	// Получаем количество младенцев
	public int getNumberOfBabies(){
		return Integer.parseInt(numberBabies.getAttribute("value"));
	}
	
	// Кликаем Search
	public WhereDoYouWantToGo clickSearch() {
		try {
			searchButton.click();
		} catch (TimeoutException ignore) {
		}
		return this;
	}
	
	public WhereDoYouWantToGo clickAddMultipleDestinations(){
		AddMultipleDestinations.click();
		return this;
	}
}
