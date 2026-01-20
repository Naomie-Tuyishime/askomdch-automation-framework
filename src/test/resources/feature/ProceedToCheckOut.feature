
Feature: Proceed to checkout
  As a customer
  I want to proceed from my cart to the checkout page
  So that I can start the purchasing process

  Background:
    Given I am logged in as a customer "Naomy" "nana"

  Scenario: Successfully proceed to checkout with items in cart
    Given I have at least one product in my cart
    When I am on the cart page Then the Proceed to checkout button is visible
    When I click the Proceed to checkout button
    Then I am redirected to the checkout page
