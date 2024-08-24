package automationpractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
