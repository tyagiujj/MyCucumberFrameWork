Feature: Customers

Background:
    Given User Launch Chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And click on login button
    Then User can view Dashboard

Scenario: Add New Customer
    When User click on customers Menu
    And click on customers Menu Item
    And click on Add new Button
    Then User can view Add new customers page
    When user enter customer info
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

Scenario: Search Customer by Email
    When User click on customers Menu 
    And click on customers Menu Item 
    And Enter customer EMail
    When Click on search button
    Then User should found Email in the Search table
    And close browser 

Scenario: Search Customer by Name
    When User click on customers Menu 
    And click on customers Menu Item 
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the Search table
    And close browser