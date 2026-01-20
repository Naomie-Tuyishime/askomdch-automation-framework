package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    // Login Elements
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(css = "a[href*='/account/']")
    private WebElement dashboardLink;

    @FindBy(css = "ul.woocommerce-error li")
    private WebElement errorMessage;

    @FindBy(linkText = "Lost your password?")
    private WebElement lostPasswordLink;

    // Registration Elements
    @FindBy(id = "reg_username")
    private WebElement regUsernameInput;

    @FindBy(id = "reg_email")
    private WebElement regEmailInput;

    @FindBy(id = "reg_password")
    private WebElement regPasswordInput;

    @FindBy(name = "register")
    private WebElement registerButton;

    @FindBy(css = "div.woocommerce-MyAccount-content p")
    private WebElement welcomeMessage;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // ==================== LOGIN METHODS ====================

    /**
     * Enter username and password
     */
    public void enterCredentials(String username, String password) {
        clearAndType(usernameInput, username);
        clearAndType(passwordInput, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    /**
     * Complete login flow
     */
    public void login(String username, String password) {
        enterCredentials(username, password);
        clickLoginButton();
    }

    /**
     * Verify dashboard is displayed
     */
    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardLink);
    }

    /**
     * Get error message text
     */
    public String getErrorMessageText() {
        return getTextFromElement(errorMessage);
    }

    /**
     * Verify error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Verify lost password link is displayed
     */
    public boolean isLostPasswordLinkDisplayed() {
        return isElementDisplayed(lostPasswordLink);
    }

    /**
     * Get lost password link text
     */
    public String getLostPasswordLinkText() {
        return getTextFromElement(lostPasswordLink);
    }

    // ==================== REGISTRATION METHODS ====================

    /**
     * Enter registration details
     */
    public void enterRegistrationDetails(String username, String email, String password) {
        clearAndType(regUsernameInput, username);
        clearAndType(regEmailInput, email);
        clearAndType(regPasswordInput, password);
    }

    /**
     * Enter unique registration details (with timestamp)
     */
    public void enterUniqueRegistrationDetails(String username, String email, String password) {
        long timestamp = System.currentTimeMillis();
        String uniqueUsername = username + timestamp;

        String[] emailParts = email.split("@");
        String uniqueEmail = emailParts[0] + timestamp + "@" + emailParts[1];

        enterRegistrationDetails(uniqueUsername, uniqueEmail, password);
    }

    /**
     * Click register button
     */
    public void clickRegisterButton() {
        waitForElementToBeClickable(registerButton);
        registerButton.click();
    }

    /**
     * Complete registration flow
     */
    public void register(String username, String email, String password) {
        enterRegistrationDetails(username, email, password);
        clickRegisterButton();
    }

    /**
     * Complete registration with unique details
     */
    public void registerWithUniqueDetails(String username, String email, String password) {
        enterUniqueRegistrationDetails(username, email, password);
        clickRegisterButton();
    }

    /**
     * Get welcome message text
     */
    public String getWelcomeMessageText() {
        scrollToElement(welcomeMessage);
        return getTextFromElement(welcomeMessage);
    }

    /**
     * Verify welcome message contains expected text
     */
    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualMessage = getWelcomeMessageText();
        return actualMessage.contains(expectedText);
    }

    /**
     * Verify email already exists error is displayed
     */
    public boolean isEmailAlreadyExistsErrorDisplayed() {
        String errorText = getErrorMessageText();
        return errorText.contains("An account is already registered with your email address");
    }
}