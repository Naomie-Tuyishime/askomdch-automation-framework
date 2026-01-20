Feature: User Login
  As a customer
  I want to log in to my account
  So that I can access my dashboard and place orders

  Background:
    Given I am on the login page

  @smoke @login @positive
  Scenario: Successful login with valid credentials
    When I enter valid credentials
    And I click the login button
    Then I should be taken to the dashboard

  @login @negative
  Scenario: Login with invalid password
    When I enter invalid password
    Then I should see the error message
    And I should see the Lost your password? option

  @login @parameterized
  Scenario Outline: Login with different credentials
    When I enter valid credentials "<username>" "<password>"
    And I click the login button
    Then I should be taken to the dashboard

    Examples:
      | username | password |
      | Naomy    | nana     |