package Steps;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        context.getAccountPage().load(ConfigReader.getAccountUrl());
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        String username = ConfigReader.getValidUsername();
        String password = ConfigReader.getValidPassword();

        context.getAccountPage().enterCredentials(username, password);
    }

    @When("I enter valid credentials {string} {string}")
    public void i_enter_valid_credentials_with_parameters(String username, String password) {
        context.getAccountPage().enterCredentials(username, password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        context.getAccountPage().clickLoginButton();
    }

    @When("I enter invalid password")
    public void i_enter_invalid_password() {
        String username = ConfigReader.getValidUsername();
        String invalidPassword = "wrongPassword123";

        context.getAccountPage().enterCredentials(username, invalidPassword);
        context.getAccountPage().clickLoginButton();
    }

    @Then("I should be taken to the dashboard")
    public void i_should_be_taken_to_the_dashboard() {
        boolean isDashboardDisplayed = context.getAccountPage().isDashboardDisplayed();
        assertTrue("Dashboard is not displayed after login!", isDashboardDisplayed);
        System.out.println("Dashboard displayed successfully");
    }

    @Then("I should see the error message")
    public void i_should_see_the_error_message() {
        boolean isErrorDisplayed = context.getAccountPage().isErrorMessageDisplayed();
        assertTrue("Error message is not displayed!", isErrorDisplayed);

        String errorMessage = context.getAccountPage().getErrorMessageText();
        System.out.println("Error message displayed: " + errorMessage);
    }

    @And("I should see the Lost your password? option")
    public void i_should_see_the_lost_password_option() {
        boolean isLostPasswordDisplayed = context.getAccountPage().isLostPasswordLinkDisplayed();
        assertTrue("Lost your password link is not displayed!", isLostPasswordDisplayed);

        String linkText = context.getAccountPage().getLostPasswordLinkText();
        System.out.println("Lost password link text: " + linkText);
    }
}