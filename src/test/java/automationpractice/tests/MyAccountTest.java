package automationpractice.tests;

import automationpractice.pages.MyAccountPage;
import automationpractice.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MyAccountTest {

    private WebDriver driver;
    private MyAccountPage myAccountPage;
    String username = "tonios89test@mailinator.com";
    String password = "Tonios89test!@";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.get("https://practice.automationtesting.in/my-account/");
        myAccountPage = new MyAccountPage(driver);
    }

    @Test
    public void testLoginWithValidUsernameAndPassword() {
        myAccountPage.logIn(username, password);
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().isDisplayed(), "Login welcome message should be displayed");
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().getText().contains("tonios89test"), "Welcome message should include 'tonios89test'");
    }

    @Test
    public void testLoginWithIncorrectUsernameAndPassword() {
        myAccountPage.logIn("prueba", "prueba");
        Assert.assertTrue(myAccountPage.getErrorMessage().isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(myAccountPage.getLoginSection().isDisplayed(), "Login section should be visible");
    }

    @Test
    public void testPasswordShouldBeMasked() {
        myAccountPage.getUsernameLabel().sendKeys(username);
        myAccountPage.getPasswordLabel().sendKeys(password);
        Assert.assertEquals(myAccountPage.getPasswordLabel().getAttribute("type"), "password", "Password field should be masked");
    }

    @Test
    public void testAccountDetails() {
        myAccountPage.logIn(username, password);
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().isDisplayed(), "Login welcome message should be displayed");
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().getText().contains("tonios89test"), "Welcome message should include 'tonios89test'");
        myAccountPage.clickAccountDetailsLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account/edit-account/"), "URL should contain '/my-account/edit-account/'");
        Assert.assertTrue(myAccountPage.getPasswordChangeSection().getText().contains("Password Change"), "Password change section should be present");
    }

    @Test
    public void testLogOut() {
        myAccountPage.logIn(username, password);
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().isDisplayed(), "Login welcome message should be displayed");
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().getText().contains("tonios89test"), "Welcome message should include 'tonios89test'");
        myAccountPage.clickLogoutButton();
        Assert.assertTrue(myAccountPage.getLoginSection().isDisplayed(), "Login section should be visible");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account/"), "URL should contain '/my-account/'");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
