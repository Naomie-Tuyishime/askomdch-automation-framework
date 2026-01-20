package Steps;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private final TestContext context;
    private final AccountPage accountPage;

    public RegistrationSteps(TestContext context) {
        this.context = context;
        this.accountPage = context.getAccountPage(); // reuse TestContext
    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        accountPage.load("https://askomdch.com/account/");
    }

    @When("I register with valid details:")
    public void i_register_with_valid_details(DataTable dataTable) {
        Map<String, String> form = dataTable.asMap(String.class, String.class);

        String username = form.get("userName");
        String email = form.get("email");
        String password = form.get("password");

        if (username != null && email != null && password != null) {
            accountPage.enterUniqueRegistrationDetails(username, email, password);
            accountPage.clickRegisterButton();
        } else {
            throw new IllegalArgumentException("Registration details cannot be null!");
        }
    }

    @When("I register with email that already exists:")
    public void i_register_with_exist_email(DataTable dataTable) {
        Map<String, String> form = dataTable.asMap(String.class, String.class);

        accountPage.enterRegistrationDetails(
                form.get("userName"),
                form.get("email"),
                form.get("password")
        );
        accountPage.clickRegisterButton();
    }

    @Then("I should be redirected to the dashboard and see welcome message")
    public void i_should_be_redirected_to_the_dashboard_and_see_welcome_message() {
        boolean isDisplayed = accountPage.isWelcomeMessageDisplayed("Hello");
        assertTrue("Welcome message not displayed!", isDisplayed);
        System.out.println("Welcome message: " + accountPage.getWelcomeMessageText());
    }
    @Then("I should see the error message Email already exists")
    public void get_error_message() {
        boolean isDisplayed = accountPage.isEmailAlreadyExistsErrorDisplayed();
        assertTrue("Email already exists error not displayed!", isDisplayed);
        System.out.println("Error message: " + accountPage.getWelcomeMessageText());
    }
}
