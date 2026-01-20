package context;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.CartPage;
import pages.CheckoutPage;

/**
 * TestContext manages page object instances for the test scenarios.
 * Uses non-static variables to avoid state issues between scenarios.
 */
public class TestContext {

    private WebDriver driver;
    private AccountPage accountPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public TestContext() {
        this.driver = DriverFactory.getDriver();
    }

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get AccountPage instance (lazy initialization)
     */
    public AccountPage getAccountPage() {
        if (accountPage == null) {
            accountPage = new AccountPage(driver);
        }
        return accountPage;
    }

    /**
     * Get CartPage instance (lazy initialization)
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    /**
     * Get CheckoutPage instance (lazy initialization)
     */
    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver);
        }
        return checkoutPage;
    }

    /**
     * Reset all page objects (useful for cleanup between scenarios)
     */
    public void resetPageObjects() {
        accountPage = null;
        cartPage = null;
        checkoutPage = null;
    }
}