package com.transavia;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    //protected WindowSwitchUtil switchUtil;
   // protected ExplicitWait wait;
    //protected static final Logger log = LogManager.getRootLogger();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(this.driver, 60);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       // switchUtil = new WindowSwitchUtil(this.driver);
        //wait = new ExplicitWait(this.driver);
    }
}
