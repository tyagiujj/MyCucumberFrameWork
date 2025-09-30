Feature: Customers
Scenario: Add New Customer
Given User Launch Chrome browser
When User open URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
And User enters Email as "admin@yourstore.com" and password as "admin"
And click on login button
Then User can view Dashboard
When User click on customers Menu
And click on customers Menu Item
And click on Add new Button
Then User can view Add new customers page
When user enter customer info
And click on save button
Then User can view confirmation message "The new customer has been added successfully."
And close browser
