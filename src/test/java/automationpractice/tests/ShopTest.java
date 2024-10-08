package automationpractice.tests;

import automationpractice.pages.ShopPage;
import automationpractice.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ShopTest {

    private WebDriver driver;
    private ShopPage shopPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.get("https://practice.automationtesting.in/shop/");
        shopPage = new ShopPage(driver);
    }

    @Test
    public void filterByPriceBetween280And428() {
        double from = 280;
        double to = 428;
        shopPage.filterByPrice(from, to);
        shopPage.clickFilterButton();
        shopPage.priceOfProductsInRange(from, to);
    }

    @Test
    public void productCategoryFunctionality() {
        shopPage.selectOneCategory(2);
        Assert.assertEquals(shopPage.getAllElementsOfPage().size(), 3);
        Assert.assertTrue(shopPage.navigationText().contains("JavaScript"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
