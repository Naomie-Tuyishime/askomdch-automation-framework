package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    // Page Title
    @FindBy(css = "h1.has-text-align-center")
    private WebElement checkoutPageTitle;

    // Billing Information Fields
    @FindBy(id = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "billing_email")
    private WebElement emailInput;

    @FindBy(id = "billing_phone")
    private WebElement phoneInput;

    @FindBy(id = "billing_address_1")
    private WebElement addressInput;

    @FindBy(id = "billing_city")
    private WebElement cityInput;

    @FindBy(id = "billing_postcode")
    private WebElement postcodeInput;

    @FindBy(id = "billing_country")
    private WebElement countryDropdown;

    // Place Order Button
    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    // Order Confirmation
    @FindBy(css = "p.woocommerce-notice")
    private WebElement orderConfirmationMessage;

    // Error Messages
    @FindBy(css = "ul.woocommerce-error li")
    private WebElement errorMessages;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ==================== PAGE VERIFICATION ====================

    /**
     * Verify checkout page is displayed
     */
    public boolean isCheckoutPageDisplayed() {
        return isElementDisplayed(checkoutPageTitle) &&
                checkoutPageTitle.getText().contains("Checkout");
    }

    /**
     * Get checkout page title
     */
    public String getCheckoutPageTitle() {
        return getTextFromElement(checkoutPageTitle);
    }

    // ==================== BILLING INFORMATION ====================

    /**
     * Fill all billing details
     */
    public void fillBillingDetails(String firstName, String lastName, String email,
                                   String phone, String address, String city, String postcode) {
        clearAndType(firstNameInput, firstName);
        clearAndType(lastNameInput, lastName);
        clearAndType(emailInput, email);
        clearAndType(phoneInput, phone);
        clearAndType(addressInput, address);
        clearAndType(cityInput, city);
        clearAndType(postcodeInput, postcode);
    }

    /**
     * Clear all billing fields
     */
    public void clearAllBillingFields() {
        firstNameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
        phoneInput.clear();
        addressInput.clear();
        cityInput.clear();
        postcodeInput.clear();
    }

    // ==================== PLACE ORDER ====================

    /**
     * Click Place Order button
     */
    public void clickPlaceOrderButton() {
        // Wait for any loading overlays to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".blockUI.blockOverlay")
        ));

        waitForElementToBeClickable(placeOrderButton);
        scrollToElement(placeOrderButton);
        jsClick(placeOrderButton);
    }

    /**
     * Get order confirmation message
     */
    public String getOrderConfirmationMessage() {
        waitForElementToBeVisible(orderConfirmationMessage);
        return orderConfirmationMessage.getText();
    }

    /**
     * Verify order was placed successfully
     */
    public boolean isOrderPlacedSuccessfully() {
        String confirmationMessage = getOrderConfirmationMessage();
        return confirmationMessage.contains("Thank you. Your order has been received.");
    }

    // ==================== ERROR HANDLING ====================

    /**
     * Get error messages
     */
    public String getErrorMessages() {
        return getTextFromElement(errorMessages);
    }

    /**
     * Verify required field errors are displayed
     */
    public boolean areRequiredFieldErrorsDisplayed() {
        String errors = getErrorMessages();
        return errors.contains("required field");
    }

    /**
     * Verify specific error message is displayed
     */
    public boolean verifyErrorMessage(String expectedError) {
        String actualErrors = getErrorMessages();
        return actualErrors.contains(expectedError);
    }

    /**
     * Verify place order button is still displayed (order not placed)
     */
    public boolean isPlaceOrderButtonStillDisplayed() {
        return isElementDisplayed(placeOrderButton);
    }
}