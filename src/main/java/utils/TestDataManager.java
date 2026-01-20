package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TestDataManager handles test data from properties files
 */
public class TestDataManager {

    private static final String TEST_DATA_PATH = "src/test/resources/testdata/";
    private static Map<String, Properties> dataFiles = new HashMap<>();

    /**
     * Load test data file
     */
    private static Properties loadDataFile(String fileName) {
        if (!dataFiles.containsKey(fileName)) {
            try {
                Properties properties = new Properties();
                FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName + ".properties");
                properties.load(fis);
                fis.close();
                dataFiles.put(fileName, properties);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load test data file: " + fileName, e);
            }
        }
        return dataFiles.get(fileName);
    }

    /**
     * Get test data value
     */
    public static String getData(String fileName, String key) {
        Properties properties = loadDataFile(fileName);
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Test data key '" + key + "' not found in file: " + fileName);
        }
        return value;
    }

    // ==================== USER DATA ====================

    public static String getValidUsername() {
        return getData("users", "valid.username");
    }

    public static String getValidPassword() {
        return getData("users", "valid.password");
    }

    public static String getValidEmail() {
        return getData("users", "valid.email");
    }

    public static String getInvalidUsername() {
        return getData("users", "invalid.username");
    }

    public static String getInvalidPassword() {
        return getData("users", "invalid.password");
    }

    // ==================== BILLING DATA ====================

    public static String getBillingFirstName() {
        return getData("billing", "first.name");
    }

    public static String getBillingLastName() {
        return getData("billing", "last.name");
    }

    public static String getBillingEmail() {
        return getData("billing", "email");
    }

    public static String getBillingPhone() {
        return getData("billing", "phone");
    }

    public static String getBillingAddress() {
        return getData("billing", "address");
    }

    public static String getBillingCity() {
        return getData("billing", "city");
    }

    public static String getBillingZipCode() {
        return getData("billing", "zipcode");
    }

    /**
     * Get complete billing details as map
     */
    public static Map<String, String> getCompleteBillingDetails() {
        Map<String, String> billingData = new HashMap<>();
        billingData.put("firstName", getBillingFirstName());
        billingData.put("lastName", getBillingLastName());
        billingData.put("email", getBillingEmail());
        billingData.put("phone", getBillingPhone());
        billingData.put("address", getBillingAddress());
        billingData.put("city", getBillingCity());
        billingData.put("zipcode", getBillingZipCode());
        return billingData;
    }

    // ==================== PRODUCT DATA ====================

    public static String getProductName() {
        return getData("products", "product.name");
    }

    public static String getProductQuantity() {
        return getData("products", "product.quantity");
    }
}