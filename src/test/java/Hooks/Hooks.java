package Hooks;

import context.TestContext;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Hooks class manages setup and teardown operations for each scenario
 */
public class Hooks {

    private TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    /**
     * Setup before each scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("========================================");

        // Initialize driver through context
        WebDriver driver = context.getDriver();
    }

    /**
     * Teardown after each scenario
     */
    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            System.out.println("Scenario FAILED: " + scenario.getName());
            takeScreenshot(scenario);
        } else {
            System.out.println("Scenario PASSED: " + scenario.getName());
        }

        // Clean up page objects
        context.resetPageObjects();

        // Quit driver
        DriverFactory.quitDriver();

        System.out.println("========================================");
        System.out.println("Finished Scenario: " + scenario.getName());
        System.out.println("========================================\n");
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