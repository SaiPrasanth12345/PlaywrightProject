@allTCRun
Feature: Register Feature

  @negative @register
Scenario: To validate that Error message is displayed when performing register with Mandatory fields as empty
  Given navigate to URL "https://ecommerce-playground.lambdatest.io/"
  And click Register
  When click Continue button with empty mandatory fields
  Then Error message should get displayed

  @positive @registerUser
Scenario: To perform successful registration for the user
  Given navigate to URL "https://ecommerce-playground.lambdatest.io/"
  And click Register
  When fill all the fields with the respective details
  And click on Register button
  Then the user registration should be successsful
  And click on Continue button in resgistration page
  Then the user should get navigaetd to MyAccount Page


