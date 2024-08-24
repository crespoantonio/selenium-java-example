package automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as necessary
    }

    // Private methods to locate elements
    private WebElement menuShopLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-40")));
    }

    private WebElement menuMyAccountLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-50")));
    }

    private WebElement menuTestCasesLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-224")));
    }

    private WebElement menuAtSitesLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-244")));
    }

    private WebElement menuDemoSitesLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-251")));
    }

    private WebElement menuCartLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpmenucartli")));
    }

    private WebElement slideMenu() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("n2-ss-slider-3")));
    }

    private WebElement slideMenuButtonNext() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n2-ss-6-arrow-next")));
    }

    private WebElement slideMenuButtonPrev() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n2-ss-6-arrow-previous")));
    }

    private WebElement newArrivalsSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".themify_builder_sub_row.sub_row_1-0-2")));
    }

    // Public methods to interact with elements
    public void clickShopMenu() {
        menuShopLink().click();
    }

    public void clickMyAccountMenu() {
        menuMyAccountLink().click();
    }

    public List <WebElement> getAllSlides() {
        return slideMenu().findElements(By.cssSelector(".n2-ss-slide"));
    }

    public List <WebElement> getAllNewArrivals() {
        return newArrivalsSection().findElements(By.cssSelector(".col3-1"));
    }

    public WebElement getOneImageInArrivals(int n) {
        if (n > 0 && n < 4) {
            return newArrivalsSection().findElement(By.cssSelector("div:nth-child(" + n + ")"));
        } else {
            throw new IllegalArgumentException("The entered value is incorrect.");
        }
    }
}