package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";

    static {
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH);
        }
    }


    private static void loadProperties() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
        properties.load(fileInputStream);
        fileInputStream.close();
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config file");
        }
        return value;
    }

    // ==================== APPLICATION URLs ====================

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getAccountUrl() {
        return getProperty("account.url");
    }

    public static String getStoreUrl() {
        return getProperty("store.url");
    }

    public static String getCartUrl() {
        return getProperty("cart.url");
    }

    public static String getCheckoutUrl() {
        return getProperty("checkout.url");
    }

    // ==================== CREDENTIALS ====================

    public static String getValidUsername() {
        return getProperty("valid.username");
    }

    public static String getValidPassword() {
        return getProperty("valid.password");
    }

    public static String getValidEmail() {
        return getProperty("valid.email");
    }

    // ==================== BROWSER SETTINGS ====================

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
}