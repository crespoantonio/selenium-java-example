package automationpractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Parameters("browser")
    public static WebDriver getDriver(String browser) {
        if(driver == null) {
            switch (browser.toLowerCase()){
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    //options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            driver.manage().window().maximize();

            wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Default timeout
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            throw new IllegalStateException("WebDriverWait is not initialized. Ensure that getDriver() is called first.");
        }
        return wait;
    }

    public static void quitDriver(){
        if(driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
