package automationpractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            //ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            //options.addArguments("--no-sandbox");
            //driver = new ChromeDriver(options);
            driver = new FirefoxDriver(options);
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
