Feature: Updating product in cart
  In order to increase or decrease the product in cart
  As a customer on AskM online shopping website
  I want to update the cart successfully

  Scenario: Update product in cart successfully
    Given I am on the cart page
    When I increase or decrease the product quantity in cart
    Then Product quantity in the cart should be updated

  Scenario: Remove product successfully
    Given I am on the cart page
    When I remove a product from the cart
    Then the product should be removed from the cart
