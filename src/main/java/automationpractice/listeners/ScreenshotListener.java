package automationpractice.listeners;

import automationpractice.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        // Optional: Initialize WebDriver if needed
        // driver = DriverFactory.getDriver("chrome"); // Example initialization
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            // Capture screenshot on failure
            ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Optional: Implement if needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Optional: Implement if needed
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Implement if needed
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: Initialize WebDriver or setup context if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        // Optional: Cleanup WebDriver if needed
        // DriverFactory.quitDriver(); // Example cleanup
    }
}