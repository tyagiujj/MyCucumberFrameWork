Feature: Login Functionality
  As an admin user, I want to log in to the nopCommerce admin panel
  so that I can manage the store.
@Sanity
  Scenario: Successful login with valid credentials
    Given User Launch Chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And click on login button
    Then Page Title Should be "Dashboard / nopCommerce administration"
    When User Click on Log out Link
    Then Page Title Should be "nopCommerce demo store. Login"
    And close Browser
    
@Regression
  Scenario Outline: Successful Login with Valid Credentials DDT
    Given User Launch Chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as "<email>" and password as "<password>"
    And click on login button
    Then Page Title Should be "Dashboard / nopCommerce administration"
    When User Click on Log out Link
    Then Page Title Should be "nopCommerce demo store. Login"
    And close Browser

    Examples:
      | email              | password |
      | admin@yourstore.com| admin    |
      | test@yourstore.com | admin    |
