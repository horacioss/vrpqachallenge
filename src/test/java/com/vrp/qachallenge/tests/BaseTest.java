package com.vrp.qachallenge.tests;

import com.vrp.qachallenge.utils.ConfigReader;
import com.vrp.qachallenge.utils.WebDriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createWebDriver();
        driver.get(ConfigReader.getProperty("loginUrl"));
    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                Thread.sleep(500);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                saveScreenshotToAllure(screenshot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] saveScreenshotToAllure(byte[] screenshot) {
        return screenshot;
    }

}
