package automationpractice.tests;

import automationpractice.pages.HomePage;
import automationpractice.pages.ProductPage;
import automationpractice.pages.ShopPage;
import automationpractice.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;


public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;
    private ShopPage shopPage;
    private ProductPage productPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.get("https://practice.automationtesting.in/");
        homePage = new HomePage(driver);
        shopPage = new ShopPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void testHomePageWithThreeSlidersOnly() {
        homePage.clickShopMenu();
        shopPage.clickHome();
        Assert.assertEquals(homePage.getAllSlides().size(), 3);
    }

    @Test
    public void testHomePageWithThreeArrivalsOnly(){
        homePage.clickShopMenu();
        shopPage.clickHome();
        Assert.assertEquals(homePage.getAllNewArrivals().size(), 3);
    }

    @Test
    public void testImagesInArrivalsShouldNavigate(){
        Random random = new Random();
        int imageNumber =  random.nextInt(homePage.getAllNewArrivals().size())+1;
        homePage.getOneImageInArrivals(imageNumber).click();
        Assert.assertTrue(productPage.getAddToBasketButton().isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
