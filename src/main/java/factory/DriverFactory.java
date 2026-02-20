package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Detect if running in GitHub Actions/Pipeline
            boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));

            if (isCI) {
                // Mandatory settings for Pipeline stability
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

            if (!isCI) {
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // This method is redundant if you use quitDriver(),
    // but kept for compatibility with your existing calls.
    public void tearDown() {
        quitDriver();
    }
}