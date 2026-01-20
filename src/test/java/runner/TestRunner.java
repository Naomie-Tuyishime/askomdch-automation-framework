package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * TestRunner class to execute Cucumber BDD tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"Steps", "Hooks"},

        // Tags for filtering scenarios
        tags = "@smoke",

        // Report plugins
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        // Display console output in readable format
        monochrome = true,

        // Stop execution on first failure (useful for debugging)
        // dryRun = false,

        // Generate snippets for undefined steps
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TestRunner {
    // This class remains empty - it's just an entry point for JUnit
}