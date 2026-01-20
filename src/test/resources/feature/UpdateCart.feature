Feature: Update shopping cart
  As a customer on the AskOm online shopping website
  I want to manage products in my cart
  So that I can adjust my order before checkout

  Background:
    Given I am on the cart page

  @cart @positive
  Scenario: Update product quantity in the cart
    When I increase or decrease the product quantity in cart
    Then Product quantity in the cart should be updated

  @cart @positive
  Scenario: Increase product quantity in the cart
    When I increase the product quantity in cart
    Then Product quantity in the cart should be updated

  @cart @positive
  Scenario: Decrease product quantity in the cart
    When I decrease the product quantity in cart
    Then Product quantity in the cart should be updated

  @cart @negative
  Scenario: Remove product from the cart
    When I remove a product from the cart
    Then the product should be removed from the cart
