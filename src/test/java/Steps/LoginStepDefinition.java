package Steps;

import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class LoginStepDefinition {

 private  final TestContext context;


    public LoginStepDefinition( TestContext context) {
        this.context = context;
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
       context.getAccountPage().load("https://askomdch.com/account/");
    }

    @When("I enter valid credentials {string} {string}")
    public void i_enter_valid_credentials(String username, String password) {
        context.getAccountPage().enterCredentials(username, password);
        context.getAccountPage().clickLoginButton();
    }


    @Then("I should be taken to the dashboard")
    public void i_should_be_taken_to_the_dashboard() {
        boolean isDashboardVisible = context.getAccountPage().isDashboardDisplayed();
        assertTrue("Dashboard page is not displayed!", isDashboardVisible);

        System.out.println("Dashboard is displayed: " + isDashboardVisible);


    }


}
