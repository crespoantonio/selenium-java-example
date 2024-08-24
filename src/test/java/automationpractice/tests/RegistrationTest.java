package automationpractice.tests;

import automationpractice.pages.HomePage;
import automationpractice.pages.MyAccountPage;
import automationpractice.types.IUserInfo;
import automationpractice.utils.DriverFactory;
import automationpractice.utils.Helpers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {

    private WebDriver driver;
    private HomePage homePage;
    private MyAccountPage myAccountPage;
    private Helpers helpers;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://practice.automationtesting.in/");
        homePage = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        helpers = new Helpers();
        homePage.clickMyAccountMenu();
    }

    @Test
    public void testSignIn() {
        IUserInfo user = helpers.createsNewUser();
        myAccountPage.registerNewUser(user.getEmail(), user.getPassword());
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().isDisplayed(), "Login welcome message should be displayed");
        Assert.assertTrue(myAccountPage.getLogInMessageWelcome().getText().contains(user.getName().toLowerCase() + "." + user.getLastName().toLowerCase()), "Welcome message should include user name and last name");
    }

    @Test
    public void testRegistrationWithInvalidEmail() {
        IUserInfo user = helpers.createsNewUser();
        myAccountPage.registerNewUser(user.getName() + "@lamd", user.getPassword());
        Assert.assertTrue(myAccountPage.getErrorMessage().isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(myAccountPage.getErrorMessage().getText().contains("Error: Please provide a valid email address."), "Error message should include 'Please provide a valid email address.'");
    }

    @Test
    public void testRegistrationWithEmptyPassword() {
        IUserInfo user = helpers.createsNewUser();
        myAccountPage.registerNewUser(user.getEmail(), null);
        Assert.assertTrue(myAccountPage.getErrorMessage().isDisplayed(), "Error message should be displayed");
        Assert.assertTrue(myAccountPage.getErrorMessage().getText().contains("Error: Please enter an account password."), "Error message should include 'Please enter an account password.'");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
