Feature: User Registration
  As a new customer
  I want to create an account
  So that I can access the platform and make purchases

  Background:
    Given I am on the registration page

  @smoke @registration @positive
  Scenario: Register successfully with valid details
    When I register with valid details:
      | username | TestUser         |
      | email    | test@example.com |
      | password | SecurePass@123   |
    Then I should be redirected to the dashboard and see welcome message
  @registration @negative
  Scenario: Registration with missing or invalid details
    When I register with the following details:
      | username |        |
      | email    | bademail@example.com |
      | password | Pass123 |
    Then I should see the error message Email already exists

  @registration @negative
  Scenario: Attempt registration with an existing email address
    When I register with email that already exists:
      | username | ExistingUser      |
      | email    | naomy@example.com |
      | password | Password123       |
    Then I should see the error message Email already exists
