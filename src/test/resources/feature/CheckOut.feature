Feature: Checkout Process
  As a customer
  I want to complete the checkout process
  So that I can place my order

  @smoke @checkout @proceed
  Scenario: Proceed to checkout from cart
    Given I am logged in as a customer "Naomy" "nana"
    And I have at least one product in my cart
    When I am on the cart page Then the Proceed to checkout button is visible
    And I click the Proceed to checkout button
    Then I am redirected to the checkout page

  @checkout @placeorder @positive
  Scenario: Place order with valid billing details
    Given I am on the checkout page
    When I enter valid billing details:
      | First name | John              |
      | Last name  | Doe               |
      | Email      | john@example.com  |
      | Phone      | 1234567890        |
      | Street     | 123 Main Street   |
      | City       | New York          |
      | Zip code   | 10001             |
    And I click on Place order button
    Then the order is placed successfully

  @checkout @negative
  Scenario: Place order with empty required fields
    Given I am on the checkout page
    When I leave required billing fields empty
    And I click on "Place order"
    Then error messages are displayed for each empty required field
    And the order is not placed