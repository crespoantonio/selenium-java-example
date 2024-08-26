package automationpractice.pages;

import automationpractice.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MyAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = DriverFactory.getWait(); // Adjust timeout as necessary
    }

    // Private methods to locate elements
    private WebElement usernameLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    }

    private WebElement passwordLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
    }

    private WebElement loginButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
    }

    private WebElement loginMessageWelcome() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-MyAccount-content")));
    }

    private WebElement messageError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-error")));
    }

    private WebElement loginSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
    }

    private WebElement registerSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("register")));
    }

    private WebElement registerEmailAddressLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_email")));
    }

    private WebElement registerPasswordLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_password")));
    }

    private WebElement registerButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("register")));
    }

    private WebElement accountDetailsLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Account Details")));
    }

    private WebElement passwordChangeTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("fieldset > legend")));
    }

    private WebElement logoutButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
    }

    // Public methods to interact with elements
    public void logIn(String username, String password) {
        usernameLabel().sendKeys(username);
        passwordLabel().sendKeys(password); // Log false not needed in Selenium
        loginButton().click();
    }

    public void clickLogoutButton() {
        logoutButton().click();
    }

    public WebElement getPasswordChangeSection() {
        return passwordChangeTitle();
    }

    public void clickAccountDetailsLink() {
        accountDetailsLink().click();
    }

    public WebElement getLogInMessageWelcome() {
        return loginMessageWelcome();
    }

    public WebElement getLoginSection() {
        return loginSection();
    }

    public WebElement getUsernameLabel() {
        return usernameLabel();
    }

    public WebElement getPasswordLabel() {
        return passwordLabel();
    }

    public WebElement getErrorMessage() {
        return messageError();
    }

    //Create this because the password input is having issues if you send the password all at once.
    private static void sendKeysSlowly(WebElement element, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            Thread.sleep(300); // Delay between each key press
        }
    }


    public void completeFieldsForNewUser(String username, String password) {
        if (username != null) {
            registerEmailAddressLabel().sendKeys(username);
            if (password != null) {
                try {
                    sendKeysSlowly(registerPasswordLabel(), password); // Slowly type password
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void registerNewUser(String username, String password) {
        completeFieldsForNewUser(username, password);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton()));
        registerButton().click();
    }
}
