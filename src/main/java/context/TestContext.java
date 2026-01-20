package context;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.CartPage;
import pages.CheckoutPage;

public class TestContext {

    private WebDriver driver;
    private AccountPage accountPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public TestContext() {
        this.driver = DriverFactory.getDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public AccountPage getAccountPage() {
        if (accountPage == null) {
            accountPage = new AccountPage(driver);
        }
        return accountPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver);
        }
        return checkoutPage;
    }

    public void resetPageObjects() {
        accountPage = null;
        cartPage = null;
        checkoutPage = null;
    }
}
