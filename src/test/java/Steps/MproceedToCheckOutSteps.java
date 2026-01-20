package Steps;


import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MproceedToCheckOutSteps {
    private  final TestContext context;

    public MproceedToCheckOutSteps(TestContext context) {
        this.context= context;
    }
    @Given("I am logged in as a customer {string} {string}")
    public void loginn(String username, String password){
        context.getAccountPage().load("https://askomdch.com/account/");
        context.getAccountPage().login(username, password);
    }

    @Given("I have at least one product in my cart")
    public void i_have_at_least_one_product_in_my_cart() {

        context.getCartPage().hoverOverCartIcon();



    }
    @When("I am on the cart page Then the Proceed to checkout button is visible")
    public void i_am_on_the_cart_page_then_the_proceed_to_checkout_button_is_visible() {

context.getCartPage().isCheckoutButtonDisplayed();
    }
    @When("I click the Proceed to checkout button")
    public void i_click_the_proceed_to_checkout_button() {
        context.getCartPage().clickProceedToCheckout();

    }
    @Then("I am redirected to the checkout page")
    public void i_am_redirected_to_the_checkout_page() {
context.getCheckoutPage().isCheckoutPageDisplayed();
    }

}
