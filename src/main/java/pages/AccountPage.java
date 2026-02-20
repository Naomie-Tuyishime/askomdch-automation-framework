package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

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

    public void enterCredentials(String username, String password) {
        clearAndType(usernameInput, username);
        clearAndType(passwordInput, password);
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void login(String username, String password) {
        enterCredentials(username, password);
        clickLoginButton();
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardLink);
    }

    public String getErrorMessageText() {
        return getTextFromElement(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public boolean isLostPasswordLinkDisplayed() {
        return isElementDisplayed(lostPasswordLink);
    }

    public String getLostPasswordLinkText() {
        return getTextFromElement(lostPasswordLink);
    }

    public void enterRegistrationDetails(String username, String email, String password) {
        clearAndType(regUsernameInput, username);
        clearAndType(regEmailInput, email);
        clearAndType(regPasswordInput, password);
    }

    public void enterUniqueRegistrationDetails(String username, String email, String password) {
        long timestamp = System.currentTimeMillis();
        String uniqueUsername = username + timestamp;

        String[] emailParts = email.split("@");
        String uniqueEmail = emailParts[0] + timestamp + "@" + emailParts[1];

        enterRegistrationDetails(uniqueUsername, uniqueEmail, password);
    }

    public void clickRegisterButton() {
        waitForElementToBeClickable(registerButton);
        registerButton.click();
    }

    public void register(String username, String email, String password) {
        enterRegistrationDetails(username, email, password);
        clickRegisterButton();
    }

    public void registerWithUniqueDetails(String username, String email, String password) {
        enterUniqueRegistrationDetails(username, email, password);
        clickRegisterButton();
    }

    public String getWelcomeMessageText() {
        scrollToElement(welcomeMessage);
        return getTextFromElement(welcomeMessage);
    }

    public boolean isWelcomeMessageDisplayed(String expectedText) {
        String actualMessage = getWelcomeMessageText();
        return actualMessage.contains(expectedText);
    }

    public boolean isEmailAlreadyExistsErrorDisplayed() {
        String errorText = getErrorMessageText();
        return errorText.contains("An account is already registered with your email address");
    }
}
