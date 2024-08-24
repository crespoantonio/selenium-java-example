package automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust timeout as necessary
    }

    // Private method to locate the "Add to Basket" button
    private WebElement addToBasketButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.single_add_to_cart_button")));
    }

    // Public method to interact with the "Add to Basket" button
    public WebElement getAddToBasketButton() {
        return addToBasketButton();
    }
}
