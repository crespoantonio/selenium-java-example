package automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class ShopPage {
    private WebDriver driver;
    private Actions actions;

    // Locator strings
    private String productsOnSaleLocator = "ins .woocommerce-Price-amount";
    private String productsNotOnSaleLocator = ".price>span";

    // Constructor
    public ShopPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Getters for locators
    private WebElement navigation() {
        return driver.findElement(By.cssSelector(".woocommerce-breadcrumb"));
    }

    private WebElement fromFilterPrice() {
        return driver.findElement(By.cssSelector(".price_slider>span:nth-child(2)"));
    }

    private WebElement toFilterPrice() {
        return driver.findElement(By.cssSelector(".price_slider>span:nth-child(3)"));
    }

    private WebElement filterButton() {
        return driver.findElement(By.cssSelector(".price_slider_amount > .button"));
    }

    private WebElement fromPriceLabel() {
        return driver.findElement(By.cssSelector(".from"));
    }

    private WebElement toPriceLabel() {
        return driver.findElement(By.cssSelector(".to"));
    }

    private List<WebElement> priceProductsOnSale() {
        return driver.findElements(By.cssSelector(productsOnSaleLocator));
    }

    private List<WebElement> priceProductsNotOnSale() {
        return driver.findElements(By.cssSelector(productsNotOnSaleLocator));
    }

    private WebElement productCategoryList() {
        return driver.findElement(By.cssSelector(".product-categories"));
    }

    private List<WebElement> listOfProductsOnPage() {
        return driver.findElements(By.cssSelector(".products.masonry-done > li"));
    }

    // Click Home
    public void clickHome() {
        navigation().findElement(By.tagName("a")).click();
    }

    // Getters for elements
    public List<WebElement> getAllElementsOfPage() {
        return listOfProductsOnPage();
    }

    public String navigationText(){
        return navigation().getText();
    }

    public WebElement getAllCategories() {
        return productCategoryList();
    }

    public void selectOneCategory(int category) {
        List<WebElement> categories = productCategoryList().findElements(By.tagName("a"));
        categories.get(category).click();
    }

    public int getFromPriceLabel() {
        return Integer.parseInt(fromPriceLabel().getText().replace("₹", "").replace(",", "").trim());
    }

    public int getToPriceLabel() {
        return Integer.parseInt(toPriceLabel().getText().replace("₹", "").replace(",", "").trim());
    }

    // Price assertion
    private void assertPricesInRange(List<WebElement> elements, double min, double max) {
        for (WebElement element : elements) {
            double price = Double.parseDouble(element.getText().replace("₹", ""));
            Assert.assertTrue(price >= min && price <= max, "Price " + price + " is not in range " + min + " - " + max);
        }
    }

    public void priceOfProductsInRange(double min, double max) {
        List<WebElement> productsOnSale = priceProductsOnSale();
        List<WebElement> productsNotOnSale = priceProductsNotOnSale();
        if (!productsOnSale.isEmpty()) {
            assertPricesInRange(productsOnSale, min, max);
        }

        if (!productsNotOnSale.isEmpty()) {
            assertPricesInRange(productsNotOnSale, min, max);
        }
    }

    // Move slider methods
    private void selectPriceFrom(double from) {
        double fromSelected = Double.parseDouble(fromPriceLabel().getText().replace("₹", ""));
        // Check if we need to move the slider
        if (fromSelected < from) {
            actions.clickAndHold(fromFilterPrice())
                    .moveByOffset(5, 0) // Adjust the offset value if necessary
                    .release()
                    .perform();
            selectPriceFrom(from); // Recursively call until the desired position is reached
        }
    }

    private void selectPriceTo(double to) {
        double toSelected = Double.parseDouble(toPriceLabel().getText().replace("₹", ""));
        // Check if we need to move the slider
        if (toSelected > to) {
            actions.clickAndHold(toFilterPrice())
                    .moveByOffset(-5, 0) // Use negative value to move left, adjust if necessary
                    .release()
                    .perform();
            selectPriceTo(to); // Recursively call until the desired position is reached
        }
    }

    public void filterByPrice(Double from, Double to) {
        if (from != null) {
            selectPriceFrom(from);
        }
        if (to != null) {
            selectPriceTo(to);
        }
    }

    public void clickFilterButton() {
        filterButton().click();
    }
}
