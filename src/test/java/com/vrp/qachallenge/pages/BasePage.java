package com.vrp.qachallenge.pages;

import com.vrp.qachallenge.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @FindBy(xpath = "//div[@role='alertdialog']//span[contains(@class, 'toastMessage')]")
    public WebElement alertMessage;


    protected WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void openRelativeUrl(String url) {
        driver.get(ConfigReader.getProperty("orgUrl") + url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void close() {
        driver.quit();
    }

    public void back() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void openAccountsPage1() {
        openRelativeUrl("/o/Account/list");
    }

}
