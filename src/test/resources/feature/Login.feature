Feature: Login functionality
  As a registered customer,
  I want to log into my account using my email or username and password,
  So that I can access my personalized dashboard and services.

  Scenario: Successful login
    Given I am on the login page
    When I enter valid credentials "Naomy" "nana"


    Then I should be taken to the dashboard

