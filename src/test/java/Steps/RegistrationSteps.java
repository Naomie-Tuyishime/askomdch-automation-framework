package Steps;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private final TestContext context;

    public RegistrationSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        context.getAccountPage().load(ConfigReader.getAccountUrl());
    }

    @When("I register with valid details:")
    public void i_register_with_valid_details(DataTable dataTable) {
        Map<String, String> registrationData = dataTable.asMap(String.class, String.class);

        String username = registrationData.get("username");
        String email = registrationData.get("email");
        String password = registrationData.get("password");


        if (username == null || email == null || password == null) {
            throw new IllegalArgumentException("Registration details cannot be null!");
        }


        context.getAccountPage().enterUniqueRegistrationDetails(username, email, password);
        context.getAccountPage().clickRegisterButton();
    }

    @When("I register with the following details:")
    public void i_register_with_the_following_details(DataTable dataTable) {
        Map<String, String> registrationData = dataTable.asMap(String.class, String.class);

        String username = registrationData.get("username");
        String email = registrationData.get("email");
        String password = registrationData.get("password");

        context.getAccountPage().enterRegistrationDetails(username, email, password);
        context.getAccountPage().clickRegisterButton();
    }

    @When("I register with email that already exists:")
    public void i_register_with_email_that_already_exists(DataTable dataTable) {
        Map<String, String> registrationData = dataTable.asMap(String.class, String.class);

        String username = registrationData.get("username");
        String email = registrationData.get("email");
        String password = registrationData.get("password");

    
        context.getAccountPage().enterRegistrationDetails(username, email, password);
        context.getAccountPage().clickRegisterButton();
    }

    @When("I register with username {string} email {string} and password {string}")
    public void i_register_with_username_email_and_password(String username, String email, String password) {
        context.getAccountPage().enterUniqueRegistrationDetails(username, email, password);
        context.getAccountPage().clickRegisterButton();
    }

    @Then("I should be redirected to the dashboard and see welcome message")
    public void i_should_be_redirected_to_the_dashboard_and_see_welcome_message() {
        boolean isWelcomeDisplayed = context.getAccountPage().isWelcomeMessageDisplayed("Hello");
        assertTrue("Welcome message is not displayed after registration!", isWelcomeDisplayed);

        String welcomeMessage = context.getAccountPage().getWelcomeMessageText();
        System.out.println("Welcome message: " + welcomeMessage);
    }

    @Then("I should see welcome message containing {string}")
    public void i_should_see_welcome_message_containing(String expectedText) {
        boolean isWelcomeDisplayed = context.getAccountPage().isWelcomeMessageDisplayed(expectedText);
        assertTrue("Welcome message does not contain '" + expectedText + "'", isWelcomeDisplayed);
    }

    @Then("I should see the error message Email already exists")
    public void i_should_see_the_error_message_email_already_exists() {
        boolean isErrorDisplayed = context.getAccountPage().isEmailAlreadyExistsErrorDisplayed();
        assertTrue("Email already exists error is not displayed!", isErrorDisplayed);

        String errorMessage = context.getAccountPage().getErrorMessageText();
        System.out.println("Error message: " + errorMessage);
    }
}