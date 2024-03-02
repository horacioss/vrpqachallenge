package com.vrp.qachallenge.utils;

import com.vrp.qachallenge.tests.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        AttachScreenshot.addScreenshot(driver, result.getName());
    }
}
