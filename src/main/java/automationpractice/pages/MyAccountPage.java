package automationpractice.pages;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Adjust timeout as necessary
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

    public void registerNewUser(String username, String password) {
        WebElement emailField = registerEmailAddressLabel();
        WebElement passwordField = registerPasswordLabel();
        WebElement registerBtn = registerButton();

        if (username != null) {
            emailField.sendKeys(username);
        }
        if (password != null) {
            passwordField.sendKeys(password);
        }

        // Ensure the button is clickable before clicking
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
        registerBtn.click();
    }
}
