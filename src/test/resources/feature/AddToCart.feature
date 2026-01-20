Feature: Add product to cart

  Scenario: Successfully add a product to the cart
    Given I am on the product listing page
    And I click on product item for opening product detail page
    When I click on the Add to Cart button for a product
    Then a confirmation message is displayed
