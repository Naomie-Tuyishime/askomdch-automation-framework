package Steps;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CheckOutSteps {

    private final TestContext context;

    public CheckOutSteps(TestContext context) {
        this.context = context;
    }


    @Given("I am logged in as a customer")
    public void i_am_logged_in_as_a_customer() {
        context.getAccountPage().load(ConfigReader.getAccountUrl());
        context.getAccountPage().login(
                ConfigReader.getValidUsername(),
                ConfigReader.getValidPassword()
        );
    }

    @Given("I am logged in as a customer {string} {string}")
    public void i_am_logged_in_as_a_customer_with_credentials(String username, String password) {
        context.getAccountPage().load(ConfigReader.getAccountUrl());
        context.getAccountPage().login(username, password);
    }

    @Given("I have at least one product in my cart")
    public void i_have_at_least_one_product_in_my_cart() {
        context.getCartPage().load(ConfigReader.getStoreUrl());
        context.getCartPage().addRandomProductToCart();
    }


    @When("I am on the cart page Then the Proceed to checkout button is visible")
    public void i_am_on_the_cart_page_then_the_proceed_to_checkout_button_is_visible() {
        context.getCartPage().hoverOverCartIcon();
        boolean isCheckoutButtonVisible = context.getCartPage().isCheckoutButtonDisplayed();
        assertTrue("Proceed to checkout button is not visible!", isCheckoutButtonVisible);
        System.out.println("Checkout button is visible");
    }

    @When("I click the Proceed to checkout button")
    public void i_click_the_proceed_to_checkout_button() {
        context.getCartPage().clickProceedToCheckout();
    }

    @Then("I am redirected to the checkout page")
    public void i_am_redirected_to_the_checkout_page() {
        boolean isCheckoutPageDisplayed = context.getCheckoutPage().isCheckoutPageDisplayed();
        assertTrue("Checkout page is not displayed!", isCheckoutPageDisplayed);
        System.out.println("Successfully redirected to checkout page");
    }


    @Given("I am on the checkout page")
    public void i_am_on_the_checkout_page() {

        i_am_logged_in_as_a_customer();
        i_have_at_least_one_product_in_my_cart();
        context.getCartPage().hoverOverCartIcon();
        context.getCartPage().clickProceedToCheckout();
    }

    @When("I enter valid billing details:")
    public void i_enter_valid_billing_details(DataTable dataTable) {
        Map<String, String> billingData = dataTable.asMap(String.class, String.class);

        String firstName = billingData.get("First name");
        String lastName = billingData.get("Last name");
        String email = billingData.get("Email");
        String phone = billingData.get("Phone");
        String street = billingData.get("Street");
        String city = billingData.get("City");
        String zipCode = billingData.get("Zip code");

        context.getCheckoutPage().fillBillingDetails(
                firstName, lastName, email, phone, street, city, zipCode
        );
    }

    @When("I leave required billing fields empty")
    public void i_leave_required_billing_fields_empty() {
        context.getCheckoutPage().clearAllBillingFields();
    }

    @When("I click on Place order button")
    public void i_click_on_place_order_button() {
        context.getCheckoutPage().clickPlaceOrderButton();
    }

    @And("I click on {string}")
    public void i_click_on(String buttonName) {
        if (buttonName.equalsIgnoreCase("Place order")) {
            context.getCheckoutPage().clickPlaceOrderButton();
        }
    }

    @Then("the order is placed successfully")
    public void the_order_is_placed_successfully() {
        boolean isOrderPlaced = context.getCheckoutPage().isOrderPlacedSuccessfully();
        assertTrue("Order was not placed successfully!", isOrderPlaced);

        String confirmationMessage = context.getCheckoutPage().getOrderConfirmationMessage();
        System.out.println("Order confirmation: " + confirmationMessage);
    }

    @Then("error messages are displayed for each empty required field")
    public void error_messages_are_displayed_for_each_empty_required_field() {
        boolean areErrorsDisplayed = context.getCheckoutPage().areRequiredFieldErrorsDisplayed();
        assertTrue("Required field errors are not displayed!", areErrorsDisplayed);

        String errors = context.getCheckoutPage().getErrorMessages();
        System.out.println("Validation errors: " + errors);
    }

    @Then("the order is not placed")
    public void the_order_is_not_placed() {
        boolean isPlaceOrderButtonStillDisplayed =
                context.getCheckoutPage().isPlaceOrderButtonStillDisplayed();
        assertTrue("Place order button is not displayed - order may have been placed!",
                isPlaceOrderButtonStillDisplayed);
        System.out.println("Order was not placed as expected");
    }
}