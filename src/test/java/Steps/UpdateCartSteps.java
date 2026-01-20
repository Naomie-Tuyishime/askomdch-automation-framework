package Steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import static org.junit.Assert.assertTrue;

public class UpdateCartSteps {

    private final TestContext context;
    private int initialQuantity;

    public UpdateCartSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the cart page")
    public void i_am_on_the_cart_page() {

        context.getCartPage().load(ConfigReader.getStoreUrl());
        context.getCartPage().addRandomProductToCart();
        context.getCartPage().hoverOverCartIcon();
        context.getCartPage().clickViewCartLink();

        System.out.println("Successfully navigated to cart page");
    }

    @When("I increase or decrease the product quantity in cart")
    public void i_increase_or_decrease_the_product_quantity_in_cart() {

        initialQuantity = context.getCartPage().getProductQuantity();
        System.out.println("Initial quantity: " + initialQuantity);


        context.getCartPage().increaseProductQuantity();
        System.out.println("Quantity increased");
    }

    @When("I increase the product quantity in cart")
    public void i_increase_the_product_quantity_in_cart() {
        initialQuantity = context.getCartPage().getProductQuantity();
        context.getCartPage().increaseProductQuantity();
    }

    @When("I decrease the product quantity in cart")
    public void i_decrease_the_product_quantity_in_cart() {
        initialQuantity = context.getCartPage().getProductQuantity();
        context.getCartPage().decreaseProductQuantity();
    }

    @When("I remove a product from the cart")
    public void i_remove_a_product_from_the_cart() {
        context.getCartPage().removeItemFromCart();
        System.out.println("Item removed from cart");
    }

    @Then("Product quantity in the cart should be updated")
    public void product_quantity_in_the_cart_should_be_updated() {
        boolean isUpdated = context.getCartPage().updateCartAndVerify();
        assertTrue("Cart was not updated successfully!", isUpdated);

        int newQuantity = context.getCartPage().getProductQuantity();
        System.out.println("Initial quantity: " + initialQuantity + ", New quantity: " + newQuantity);
        assertTrue("Quantity did not change!", newQuantity != initialQuantity);
    }

    @Then("the product should be removed from the cart")
    public void the_product_should_be_removed_from_the_cart() {
        boolean isRemoved = context.getCartPage().isItemRemoved();
        assertTrue("Item was not removed from cart!", isRemoved);

        String message = context.getCartPage().getNotificationMessage();
        System.out.println("Removal message: " + message);
    }
}