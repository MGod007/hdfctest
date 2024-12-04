package com.qa.hdfc.PageEvents;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportListener implements ITestListener {

    private ExtentReports extent;
    private ExtentHtmlReporter htmlReporter;
    private ExtentTest test;
    private WebDriver driver;

    // Called before the first test method in the current class is invoked.
    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports
        htmlReporter = new ExtentHtmlReporter("hdfctest/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // Called after each test method
    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test instance with the name of the current test method
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // Called when a test method fails
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed: " + result.getThrowable());
        // You can capture screenshots here if you want (optional)
        // For example: captureScreenshot(driver);
    }

    // Called when a test method passes
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    // Called when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    // Called after the last test method in the current class is invoked
    @Override
    public void onFinish(ITestContext context) {
        // Flush the reports
        extent.flush();
    }
}
