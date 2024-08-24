package automationpractice.tests;

import automationpractice.pages.HomePage;
import automationpractice.pages.ShopPage;
import automationpractice.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopTest {

    private WebDriver driver;
    private HomePage homePage;
    private ShopPage shopPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://practice.automationtesting.in/");
        homePage = new HomePage(driver);
        shopPage = new ShopPage(driver);
        homePage.clickShopMenu();
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
