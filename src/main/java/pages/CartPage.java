package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class CartPage extends BasePage {

    // Cart Icon & Navigation
    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    @FindBy(css = ".button.checkout.wc-forward")
    private WebElement checkoutButton;

    @FindBy(css = "a.button.wc-forward")
    private WebElement viewCartButton;

    // Cart Page Elements
    @FindBy(css = "h1.has-text-align-center")
    private WebElement cartPageTitle;

    @FindBy(css = "input.input-text.qty.text")
    private WebElement quantityInput;

    @FindBy(name = "update_cart")
    private WebElement updateCartButton;

    @FindBy(css = "a.remove")
    private WebElement removeItemButton;

    @FindBy(css = ".woocommerce-message")
    private WebElement notificationMessage;

    // Product Listing
    @FindBy(css = ".astra-shop-thumbnail-wrap")
    private List<WebElement> productThumbnails;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".product-price")
    private WebElement productPrice;

    @FindBy(css = ".product-subtotal")
    private WebElement productSubtotal;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ==================== PRODUCT SELECTION ====================

    /**
     * Add a random product to cart from product listing page
     */
    public void addRandomProductToCart() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productThumbnails));
        Random random = new Random();
        int randomIndex = random.nextInt(productThumbnails.size());

        WebElement selectedProduct = productThumbnails.get(randomIndex);
        waitForElementToBeClickable(selectedProduct);
        selectedProduct.click();

        // Wait for product detail page and click Add to Cart
        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add-to-cart']"))
        );
        addToCartButton.click();

        // Wait for confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-message")));
    }

    // ==================== CART ICON INTERACTION ====================

    /**
     * Hover over shopping cart icon
     */
    public void hoverOverCartIcon() {

        // Wait for the cart icon to be present
        WebElement cartIcon = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".button.checkout.wc-forward"))
        );

        // Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartIcon);

        // Hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(cartIcon).perform();
    }


    /**
     * Click View Cart link from cart preview
     */
    public void clickViewCartLink() {
        waitForElementToBeClickable(viewCartLink);
        jsClick(viewCartLink);
    }

    // ==================== CART PAGE VERIFICATION ====================

    /**
     * Verify cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        return isElementDisplayed(cartPageTitle) &&
                cartPageTitle.getText().contains("Cart");
    }

    /**
     * Verify product details are displayed on cart page
     */
    public boolean areProductDetailsDisplayed() {
        return isElementDisplayed(productName) &&
                isElementDisplayed(productPrice) &&
                isElementDisplayed(productSubtotal);
    }

    // ==================== CART QUANTITY MANAGEMENT ====================

    /**
     * Get current product quantity
     */
    public int getProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        String quantityValue = quantityInput.getAttribute("value");
        return Integer.parseInt(quantityValue);
    }

    /**
     * Increase product quantity by 1
     */
    public void increaseProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        quantityInput.sendKeys(Keys.ARROW_UP);
    }

    /**
     * Decrease product quantity by 1
     */
    public void decreaseProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        quantityInput.sendKeys(Keys.ARROW_DOWN);
    }

    /**
     * Set product quantity to specific value
     */
    public void setProductQuantity(int quantity) {
        waitForElementToBeVisible(quantityInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    /**
     * Click Update Cart button
     */
    public void clickUpdateCartButton() {
        waitForElementToBeClickable(updateCartButton);
        scrollToElement(updateCartButton);
        updateCartButton.click();

        // Wait for update to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".blockUI.blockOverlay")
        ));
    }

    /**
     * Update cart and verify success
     */
    public boolean updateCartAndVerify() {
        int initialQuantity = getProductQuantity();
        clickUpdateCartButton();

        // Verify update message
        String message = getNotificationMessage();
        return message.contains("Cart updated");
    }

    // ==================== REMOVE ITEMS ====================

    /**
     * Remove item from cart
     */
    public void removeItemFromCart() {
        waitForElementToBeClickable(removeItemButton);
        scrollToElement(removeItemButton);
        removeItemButton.click();

        // Wait for removal to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".blockUI.blockOverlay")
        ));
    }

    /**
     * Verify item was removed
     */
    public boolean isItemRemoved() {
        String message = getNotificationMessage();
        return message.contains("removed");
    }

    // ==================== CHECKOUT ====================

    /**
     * Verify checkout button is displayed
     */
    public boolean isCheckoutButtonDisplayed() {
        return isElementDisplayed(checkoutButton);
    }

    /**
     * Click Proceed to Checkout button
     */
    public void clickProceedToCheckout() {
        waitForElementToBeClickable(checkoutButton);
        scrollToElement(checkoutButton);
        checkoutButton.click();
    }

    // ==================== NOTIFICATIONS ====================

    /**
     * Get notification message text
     */
    public String getNotificationMessage() {
        return getTextFromElement(notificationMessage);
    }

    /**
     * Verify notification message contains expected text
     */
    public boolean verifyNotificationMessage(String expectedText) {
        String actualMessage = getNotificationMessage();
        return actualMessage.contains(expectedText);
    }
}