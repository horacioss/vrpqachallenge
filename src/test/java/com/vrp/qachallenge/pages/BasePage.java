package com.vrp.qachallenge.pages;

import com.vrp.qachallenge.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> element.isDisplayed());
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> element.isEnabled());
    }

    public void clickOnElement(WebElement element) {
        waitForElementToBeVisible(element);
        waitForElementToBeClickable(element);
        element.click();
    }

    public void sendKeysToElement(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void openAccountsPage() {
        openRelativeUrl("/o/Account/list");
    }

    public void validateAlertMessage(String message) {
        waitForElementToBeVisible(alertMessage);
        assertTrue(alertMessage.isDisplayed());
        assertEquals(message, alertMessage.getText());
    }

}
