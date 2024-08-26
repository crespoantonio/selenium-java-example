package automationpractice.tests;

import automationpractice.pages.MyAccountPage;
import automationpractice.types.IUserInfo;
import automationpractice.utils.DriverFactory;
import automationpractice.utils.Helpers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTest {

    private WebDriver driver;
    private MyAccountPage myAccountPage;
    private Helpers helpers;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.get("https://practice.automationtesting.in/my-account/");
        myAccountPage = new MyAccountPage(driver);
        helpers = new Helpers();
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
        myAccountPage.registerNewUser("pedro.mercado@caso", user.getPassword());
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
