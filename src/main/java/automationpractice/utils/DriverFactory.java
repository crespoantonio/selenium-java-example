package automationpractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
