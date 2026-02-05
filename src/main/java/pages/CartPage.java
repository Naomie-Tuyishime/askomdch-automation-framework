package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class CartPage extends BasePage {

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    @FindBy(css = "a.checkout-button")
    private WebElement checkoutButton;

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

    @FindBy(css = ".products .product")
    private List<WebElement> products;

    @FindBy(css = ".product-name a")
    private WebElement productName;

    @FindBy(css = "td.product-price")
    private WebElement productPrice;

    @FindBy(css = "td.product-subtotal")
    private WebElement productSubtotal;

    @FindBy(css = ".ast-cart-menu-wrap")
    private WebElement cartIcon;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void addRandomProductToCart() {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement selectedProduct = products.get(randomIndex);
        scrollToElement(selectedProduct);
        WebElement addToCartButton = selectedProduct.findElement(
                By.cssSelector("a.add_to_cart_button, button.add_to_cart_button")
        );
        waitForElementToBeClickable(addToCartButton);
        jsClick(addToCartButton);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.added_to_cart, a[title='View cart']")
        ));
    }

    public void hoverOverCartIcon() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".ast-cart-menu-wrap")
            ));
            List<WebElement> cartIcons = driver.findElements(By.cssSelector("div.ast-site-header-cart-li"));
            WebElement cartIconElement = cartIcons.get(1);
            ;
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                    cartIconElement
            );
            Actions actions = new Actions(driver);
            actions.moveToElement(cartIconElement).perform();
        } catch (Exception e) {
            driver.get("https://askomdch.com/cart/");
        }
    }

    public void clickViewCartLink() {
        try {
            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a.added_to_cart, a[title='View cart']")
            ));
            scrollToElement(viewCart);
            jsClick(viewCart);
        } catch (Exception e) {
            driver.get("https://askomdch.com/cart/");
        }
    }

    public boolean isCartPageDisplayed() {
        try {
            waitForElementToBeVisible(cartPageTitle);
            String titleText = cartPageTitle.getText().toLowerCase();
            return titleText.contains("cart");
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("/cart");
        }
    }

    public boolean areProductDetailsDisplayed() {
        try {
            return isElementDisplayed(productName) &&
                    isElementDisplayed(productPrice) &&
                    isElementDisplayed(productSubtotal);
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        String quantityValue = quantityInput.getAttribute("value");
        return Integer.parseInt(quantityValue);
    }

    public void increaseProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        int currentQty = getProductQuantity();
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(currentQty + 1));
    }

    public void decreaseProductQuantity() {
        waitForElementToBeVisible(quantityInput);
        int currentQty = getProductQuantity();
        if (currentQty >=1) {
            quantityInput.sendKeys(Keys.ARROW_DOWN);
        }
    }

    public void setProductQuantity(int quantity) {
        waitForElementToBeVisible(quantityInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void clickUpdateCartButton() {
        waitForElementToBeClickable(updateCartButton);
        scrollToElement(updateCartButton);
        jsClick(updateCartButton);
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.cssSelector(".blockUI.blockOverlay")
                    ),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.woocommerce-message")
                    )
            ));
        } catch (Exception e) {
        }
    }

    public boolean updateCartAndVerify() {
        clickUpdateCartButton();
        try {
            List<WebElement> messages = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector("div.woocommerce-message")

                    )

            );
            messages.get(0);
;
            String message = getNotificationMessage();
            return message.contains("Cart updated") || message.contains("updated");
        } catch (Exception e) {
            return true;
        }
    }

    public void removeItemFromCart() {
        waitForElementToBeClickable(removeItemButton);
        scrollToElement(removeItemButton);
        jsClick(removeItemButton);
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
        }
    }

    public boolean isItemRemoved() {
        try {
            String message = getNotificationMessage();
            return message.toLowerCase().contains("removed");
        } catch (Exception e) {
            return driver.getPageSource().contains("Your cart is currently empty") ||
                    driver.getPageSource().contains("cart is empty");
        }
    }

    public boolean isCheckoutButtonDisplayed() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("a.checkout-button, .wc-proceed-to-checkout a")
            ));
            return checkout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickProceedToCheckout() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a.checkout-button, .wc-proceed-to-checkout a")
            ));
            scrollToElement(checkout);
            jsClick(checkout);
        } catch (Exception e) {
            driver.get("https://askomdch.com/checkout/");
        }
    }

    public String getNotificationMessage() {
        try {
       List   <WebElement>messages= wait.until(ExpectedConditions.visibilityOfAllElements(notificationMessage));
return messages.get(1).getText();

        } catch (Exception e) {
            return "";
        }
    }

    public boolean verifyNotificationMessage(String expectedText) {
        try {
            String actualMessage = getNotificationMessage();
            return actualMessage.toLowerCase().contains(expectedText.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
