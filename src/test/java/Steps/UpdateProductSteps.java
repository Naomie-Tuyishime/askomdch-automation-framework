package Steps;

import context.TestContext;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;


public class UpdateProductSteps {
    private final TestContext context;
    public UpdateProductSteps(TestContext context){
        this.context = context;
    }

    @Given("I am on the cart page")
    public void i_am_on_the_cart_page() {
        DriverFactory.getDriver();
    context.getCartPage().load("https://askomdch.com/");
  context.getCartPage().addRandomProductToCart();
  context.getCartPage().clickViewCartLink();


    }

    @When("I increase or decrease the product quantity in cart")
    public void i_increase_or_decrease_the_product_quantity_in_cart() {
      context.getCartPage().increaseProductQuantity();
    }

    @When("I remove a product from the cart")
    public void i_remove_a_product_from_the_cart() {
    context.getCartPage().removeItemFromCart();
    }

    @Then("Product quantity in the cart should be updated")
    public void product_quantity_in_the_cart_should_be_updated() {
       context.getCartPage().clickUpdateCartButton();
    }
    @Then("the product should be removed from the cart")
    public void the_product_should_be_removed_from_the_cart() {
      context.getCartPage().isItemRemoved();
}}
