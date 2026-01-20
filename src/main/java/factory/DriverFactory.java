package factory;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(); // configure ChromeOptions if needed
            driver.manage().window().maximize();
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // allows next scenario to initialize a fresh driver
        }

    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
