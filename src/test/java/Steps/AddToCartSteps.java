package Steps;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import static org.junit.Assert.assertTrue;

public class AddToCartSteps {

    private final TestContext context;

    public AddToCartSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the product listing page")
    public void i_am_on_the_product_listing_page() {
        context.getCartPage().load(ConfigReader.getStoreUrl());
    }

    @And("I click on product item for opening product detail page")
    public void i_click_on_product_item_for_opening_product_detail_page() {

        context.getCartPage().addRandomProductToCart();
    }

    @When("I click on the Add to Cart button for a product")
    public void i_click_on_the_add_to_cart_button_for_a_product() {

        System.out.println("Product added to cart");
    }

    @Then("I should see the product in the cart")
    public void  i_should_see_the_product_in_the_cart() {
        context.getCartPage().clickViewCartLink();
    }
}