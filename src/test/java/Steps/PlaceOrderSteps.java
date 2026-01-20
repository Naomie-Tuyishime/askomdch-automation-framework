package Steps;

//public class PlaceOrderSteps {
//WebDriver driver ;
//   private final TestContext context;
//   private final MproceedToCheckOutSteps checkout = new MproceedToCheckOutSteps();
//   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//    public PlaceOrderSteps( TestContext context) {
//        this.context = context ;
//    }
//
//    @Given("I am on the checkout page")
//    public void i_am_on_the_checkout_page() {
//        checkout.i_am_on_the_cart_page_then_the_proceed_to_checkout_button_is_visible();
//        checkout.i_click_the_proceed_to_checkout_button();
//        checkout.i_am_redirected_to_the_checkout_page();
//    }
//    @When("I enter valid billing details:")
//    public void i_enter_valid_billing_details(io.cucumber.datatable.DataTable table) {
//
//        Map<String, String> form = table.asMap(String.class, String.class);
//        String firstName = form.get("First name");
//        String lastName = form.get("Last name");
//        String email = form.get("Email");
//        String street = form.get("Street");
//        String city = form.get("City");
//        String zipCode = form.get("Zip code");
//        String phone = form.get("Phone");
//        driver.findElement(By.id("billing_first_name")).clear();
//        driver.findElement(By.id("billing_first_name")).sendKeys(firstName);
//        driver.findElement(By.id("billing_last_name")).clear();
//        driver.findElement(By.id("billing_last_name")).sendKeys(lastName);
//        driver.findElement(By.id("billing_email")).clear();
//        driver.findElement(By.id("billing_email")).sendKeys(email);
//        driver.findElement(By.id("billing_address_1")).clear();
//        driver.findElement(By.id("billing_address_1")).sendKeys(street);
//        driver.findElement(By.id("billing_city")).clear();
//        driver.findElement(By.id("billing_city")).sendKeys(city);
//        driver.findElement(By.id("billing_postcode")).clear();
//        driver.findElement(By.id("billing_postcode")).sendKeys(zipCode);
//        driver.findElement(By.id("billing_phone")).clear();
//        driver.findElement(By.id("billing_phone")).sendKeys(phone);
//
//    }
//
//    @And("I click on Place order button")
//    public void i_click_on_place_order() {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(
//                By.cssSelector(".blockUI.blockOverlay")
//        ));
//
//        WebElement placeOrderBtn = wait.until(
//                ExpectedConditions.presenceOfElementLocated(By.id("place_order"))
//        );
//
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].scrollIntoView({block:'center'});", placeOrderBtn);
//
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].click();", placeOrderBtn);
//    }
//
//
//
//    @Then("the order is placed successfully")
//    public void the_order_is_placed_successfully() {
//        WebElement successMessage = driver.findElement(By.cssSelector("div.woocommerce-order p"));
//        wait.until(ExpectedConditions.visibilityOf(successMessage));
//        String message = successMessage.getText();
//        Assert.assertEquals("Thank you. Your order has been received.",message);
//
//    }
//
//    @When("I leave required billing fields empty")
//    public void iLeaveRequiredBillingFieldsEmpty() {
//        driver.findElement(By.id("billing_first_name")).clear();
//        driver.findElement(By.id("billing_last_name")).clear();
//        driver.findElement(By.id("billing_email")).clear();
//        driver.findElement(By.id("billing_address_1")).clear();
//        driver.findElement(By.id("billing_city")).clear();
//        driver.findElement(By.id("billing_postcode")).clear();
//        driver.findElement(By.id("billing_phone")).clear();
//
//    }
//
//    @And("I click on {string}")
//    public void iClickOn(String arg0) {
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(
//                By.cssSelector(".blockUI.blockOverlay")
//        ));
//
//        WebElement placeOrder = wait.until(
//                ExpectedConditions.elementToBeClickable(By.cssSelector("button[id='place_order']"))
//        );
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrder);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);
//    }
//
//    @Then("error messages are displayed for each empty required field")
//    public void errorMessagesAreDisplayedForEachEmptyRequiredField() {
//        WebElement errorList = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//*[@id='post-1221']/div/div/div/div/form[2]/div[1]/ul")));
//
//        String[] expectedErrors = {
//            "Billing First name is a required field.",
//            "Billing Last name is a required field.",
//            "Billing Street address is a required field.",
//            "Billing Town / City is a required field.",
//            "Billing ZIP Code is a required field.",
//        };
//
//        String errorText = errorList.getText();
//        for (String expectedError : expectedErrors) {
//            Assert.assertTrue("Missing error: " + expectedError, errorText.contains(expectedError));
//        }
//    }
//
//    @And("the order is not placed")
//    public void theOrderIsNotPlaced() {
//        WebElement placeOrder = driver.findElement(By.cssSelector("button[id='place_order']"));
//        Assert.assertTrue(placeOrder.isDisplayed());
//    }
//}
