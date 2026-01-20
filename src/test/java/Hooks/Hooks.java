package Hooks;

import context.TestContext;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {

    private TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }


    @Before
    public void setUp(Scenario scenario) {

        System.out.println("Starting Scenario: " + scenario.getName());


        WebDriver driver = context.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            System.out.println("Scenario FAILED: " + scenario.getName());
            takeScreenshot(scenario);
        } else {
            System.out.println("Scenario PASSED: " + scenario.getName());
        }


        context.resetPageObjects();


        DriverFactory.quitDriver();

        System.out.println("Finished Scenario: " + scenario.getName());

    }

    /**
     * Take screenshot and attach to Cucumber report
     */
    private void takeScreenshot(Scenario scenario) {
        try {
            WebDriver driver = context.getDriver();
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            System.out.println("Screenshot captured for failed scenario");
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}