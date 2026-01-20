Feature: Shopping Cart Management
  As a customer
  I want to manage my shopping cart
  So that I can review and modify my order before checkout

  @smoke @cart @addtocart
  Scenario: Add product to cart
    Given I am on the product listing page
    And I click on product item for opening product detail page
    When I click on the Add to Cart button for a product
    Then a confirmation message is displayed

  @cart @viewcart
  Scenario: View cart details
    Given I have added a product to the cart
    When I hover over the shopping cart icon for seeing added products
    And I click on the View cart button in cart preview
    Then I am redirected to the cart page
    And the cart page displays product details

  @cart @update
  Scenario: Update product quantity in cart
    Given I am on the cart page
    When I increase or decrease the product quantity in cart
    Then Product quantity in the cart should be updated

  @cart @remove
  Scenario: Remove product from cart
    Given I am on the cart page
    When I remove a product from the cart
    Then the product should be removed from the cart