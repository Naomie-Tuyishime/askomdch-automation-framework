package Steps;
import Hooks.Hooks;
import context.TestContext;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class AddToCartSteps {
    private final TestContext context;


    public AddToCartSteps(TestContext context) {
        this.context= context;
    }

    @Given("I am on the product listing page")
    public void i_am_on_the_product_listing_page(){

     context.getCartPage().load("https://askomdch.com/store/");


    }
    @And("I click on product item for opening product detail page")
    public void click_on_random_product(){
context.getCartPage().addRandomProductToCart();
    }


    @When("I click on the Add to Cart button for a product")
    public void i_click_on_the_button_for_a_product() {
        context.getCartPage().clickViewCartLink();
    }

    @Then("a confirmation message is displayed")
    public void a_confirmation_message_is_displayed() {
        context.getCartPage().areProductDetailsDisplayed();
    }
}
