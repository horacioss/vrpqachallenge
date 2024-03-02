package com.vrp.qachallenge.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AttachScreenshot {
    private static final DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void addScreenshot(WebDriver driver, String testName) {

        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    String.format("%s %s.png", testName, formatDate.format(new Date())),
                    "image/png",
                    Arrays.toString(screenshot),
                    "png"
            );
        }

    }
}
