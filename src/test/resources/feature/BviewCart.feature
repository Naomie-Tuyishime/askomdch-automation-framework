Feature: View cart
  As a customer
  I want to view my shopping cart
  So that I can review the products I added to the cart

  Scenario: Successfully view products in the cart
    Given I have added a product to the cart
    When I hover over the shopping cart icon for seeing added products
    When I click on the View cart button in cart preview
    Then I am redirected to the cart page
    And the cart page displays product details