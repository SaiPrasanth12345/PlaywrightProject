@allTCRun
Feature: Login Feature

  @positive @LoginUser
  Scenario Outline: To perform successful login for the user
    Given navigate to URL "https://ecommerce-playground.lambdatest.io/"
    And click Login button
    When login with the credentials "<email>" and "<password>"
    Then the user should get navigaetd to MyAccount Page

    Examples:
    |email               |password|
    |koushik350@gmail.com|Pass123$|
    |abcdef@ghijkl.com   |ABCDEFG |



