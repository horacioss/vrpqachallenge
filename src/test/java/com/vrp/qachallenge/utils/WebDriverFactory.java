package com.vrp.qachallenge.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browserName) {

        Browser browser = getBrowser(browserName);
        WebDriver driver =
                switch (browser) {
                    case CHROME ->   new ChromeDriver();
                    case FIREFOX -> new FirefoxDriver();
                    case SAFARI -> new SafariDriver();
                    case EDGE -> new EdgeDriver();
                };

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    private static Browser getBrowser(String browserName) {
        Browser browser;
        try {
            browser = Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
        return browser;
    }

    enum Browser {
        CHROME,
        FIREFOX,
        SAFARI,
        EDGE
    }

}
