package Steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import static org.junit.Assert.assertTrue;

public class ViewCartSteps {

    private final TestContext context;

    public ViewCartSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have added a product to the cart")
    public void i_have_added_a_product_to_the_cart() {
        context.getCartPage().load(ConfigReader.getStoreUrl());
        context.getCartPage().addRandomProductToCart();
    }

    @When("I hover over the shopping cart icon for seeing added products")
    public void i_hover_over_the_shopping_cart_icon() {
        context.getCartPage().hoverOverCartIcon();
    }

    @When("I click on the View cart button in cart preview")
    public void i_click_on_the_view_cart_button_in_cart_preview() {
        context.getCartPage().clickViewCartLink();
    }

    @Then("I am redirected to the cart page")
    public void i_am_redirected_to_the_cart_page() {
        boolean isCartPageDisplayed = context.getCartPage().isCartPageDisplayed();
        assertTrue("Cart page is not displayed!", isCartPageDisplayed);
        System.out.println("Successfully navigated to cart page");
    }

    @Then("the cart page displays product details")
    public void the_cart_page_displays_product_details() {
        boolean areDetailsDisplayed = context.getCartPage().areProductDetailsDisplayed();
        assertTrue("Product details are not displayed on cart page!", areDetailsDisplayed);
        System.out.println("Product details are visible on cart page");
    }
}