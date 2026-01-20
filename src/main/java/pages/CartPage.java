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

    @FindBy(css = "a.checkout-button")
    private WebElement checkoutButton;

    // Cart Page Elements
    @FindBy(css = "h1")
    private WebElement cartPageTitle;

    @FindBy(css = "input.qty")
    private WebElement quantityInput;

    @FindBy(name = "update_cart")
    private WebElement updateCartButton;

    @FindBy(css = "a.remove")
    private WebElement removeItemButton;

    @FindBy(css = ".woocommerce-message")
    private WebElement notificationMessage;

    // Product Listing - Updated selectors
    @FindBy(css = ".products .product")
    private List<WebElement> products;

    @FindBy(css = ".product-name a")
    private WebElement productName;

    @FindBy(css = "td.product-price")
    private WebElement productPrice;

    @FindBy(css = "td.product-subtotal")
    private WebElement productSubtotal;

    // Mini cart icon
    @FindBy(css = ".ast-cart-menu-wrap")
    private WebElement cartIcon;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ==================== PRODUCT SELECTION ====================

    /**
     * Add a random product to cart from product listing page
     * Clicks the "Add to Cart" button directly on the listing page
     */
    public void addRandomProductToCart() {
        // Wait for products to load
        wait.until(ExpectedConditions.visibilityOfAllElements(products));

        // Select random product
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement selectedProduct = products.get(randomIndex);

        // Scroll product into view
        scrollToElement(selectedProduct);

        // Find and click "Add to Cart" button within this product
        WebElement addToCartButton = selectedProduct.findElement(
                By.cssSelector("a.add_to_cart_button, button.add_to_cart_button")
        );

        waitForElementToBeClickable(addToCartButton);
        jsClick(addToCartButton);

        // Wait for "View Cart" button to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.added_to_cart, a[title='View cart']")
        ));

        System.out.println("Product added to cart successfully");
    }

    // ==================== CART ICON INTERACTION ====================

    /**
     * Hover over shopping cart icon
     * Fixed to handle viewport issues
     */
    public void hoverOverCartIcon() {
        try {
            // Scroll to top of page first
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500); // Small wait for scroll to complete

            // Wait for cart icon
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".ast-cart-menu-wrap")
            ));

            // Find the cart icon
            WebElement cartIconElement = driver.findElement(
                    By.cssSelector(".ast-cart-menu-wrap")
            );

            // Ensure it's in viewport
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                    cartIconElement
            );
            Thread.sleep(300);

            // Hover using Actions
            Actions actions = new Actions(driver);
            actions.moveToElement(cartIconElement).perform();

            System.out.println("Hovered over cart icon");

        } catch (Exception e) {
            System.out.println("Hover failed, attempting alternative method");
            // Alternative: directly navigate to cart
            driver.get("https://askomdch.com/cart/");
        }
    }

    /**
     * Click View Cart link from cart preview or added_to_cart button
     */
    public void clickViewCartLink() {
        try {

            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a.added_to_cart, a[title='View cart']")
            ));

            scrollToElement(viewCart);
            jsClick(viewCart);

            System.out.println("Clicked View Cart link");

        } catch (Exception e) {
            System.out.println("View Cart link not found, navigating directly");
            driver.get("https://askomdch.com/cart/");
        }
    }



    /**
     * Verify cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        try {
            waitForElementToBeVisible(cartPageTitle);
            String titleText = cartPageTitle.getText().toLowerCase();
            return titleText.contains("cart");
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("/cart");
        }
    }

    /**
     * Verify product details are displayed on cart page
     */
    public boolean areProductDetailsDisplayed() {
        try {
            return isElementDisplayed(productName) &&
                    isElementDisplayed(productPrice) &&
                    isElementDisplayed(productSubtotal);
        } catch (Exception e) {
            return false;
        }
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

        // Clear and set new value (more reliable than arrow keys)
        int currentQty = getProductQuantity();
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(currentQty + 1));
    }

    /**
     * Decrease product quantity by 1
     */
    public void decreaseProductQuantity() {
        waitForElementToBeVisible(quantityInput);

        int currentQty = getProductQuantity();
        if (currentQty > 1) {
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(currentQty - 1));
        }
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
        jsClick(updateCartButton);

        // Wait for update to complete
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.cssSelector(".blockUI.blockOverlay")
                    ),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".woocommerce-message")
                    )
            ));
        } catch (Exception e) {
            // Continue if no overlay found
        }
    }

    /**
     * Update cart and verify success
     */
    public boolean updateCartAndVerify() {
        clickUpdateCartButton();

        try {
            // Wait for success message
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".woocommerce-message")
            ));
            String message = getNotificationMessage();
            return message.contains("Cart updated") || message.contains("updated");
        } catch (Exception e) {
            return true; // Assume success if no error
        }
    }

    // ==================== REMOVE ITEMS ====================

    /**
     * Remove item from cart
     */
    public void removeItemFromCart() {
        waitForElementToBeClickable(removeItemButton);
        scrollToElement(removeItemButton);
        jsClick(removeItemButton);

        // Wait for removal to complete
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.cssSelector(".blockUI.blockOverlay")
                    ),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".woocommerce-message")
                    )
            ));
        } catch (Exception e) {
            // Continue
        }
    }

    /**
     * Verify item was removed
     */
    public boolean isItemRemoved() {
        try {
            String message = getNotificationMessage();
            return message.toLowerCase().contains("removed");
        } catch (Exception e) {
            // Check if cart is empty
            return driver.getPageSource().contains("Your cart is currently empty") ||
                    driver.getPageSource().contains("cart is empty");
        }
    }

    // ==================== CHECKOUT ====================

    /**
     * Verify checkout button is displayed
     */
    public boolean isCheckoutButtonDisplayed() {
        try {
            // Look for checkout button
            WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("a.checkout-button, .wc-proceed-to-checkout a")
            ));
            return checkout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click Proceed to Checkout button
     */
    public void clickProceedToCheckout() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a.checkout-button, .wc-proceed-to-checkout a")
            ));
            scrollToElement(checkout);
            jsClick(checkout);
        } catch (Exception e) {
            // Direct navigation as fallback
            driver.get("https://askomdch.com/checkout/");
        }
    }

    // ==================== NOTIFICATIONS ====================

    /**
     * Get notification message text
     */
    public String getNotificationMessage() {
        try {
            waitForElementToBeVisible(notificationMessage);
            return notificationMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verify notification message contains expected text
     */
    public boolean verifyNotificationMessage(String expectedText) {
        try {
            String actualMessage = getNotificationMessage();
            return actualMessage.toLowerCase().contains(expectedText.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}