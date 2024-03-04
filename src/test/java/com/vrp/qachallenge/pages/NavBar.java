package com.vrp.qachallenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBar extends BasePage {

    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Home']")
    public WebElement homeTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Accounts']")
    public WebElement accountsTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Contacts']")
    public WebElement contactsTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Opportunities']")
    public WebElement opportunitiesTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Calendar']")
    public WebElement calendarTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Forecasts']")
    public WebElement forecastsTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Dashboards']")
    public WebElement dashboardsTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Reports']")
    public WebElement reportsTab;
    @FindBy(xpath = "//one-app-nav-bar//one-app-nav-bar-item-root//a[@title='Quotes']")
    public WebElement quotesTab;
    public NavBar(WebDriver driver) {
        super(driver);
    }
}
