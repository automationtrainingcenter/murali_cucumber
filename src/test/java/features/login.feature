@login
Feature: login functionality

  @valid
  Scenario: login with valid credentials
    Given user is in bank home page
    When user enters valid username
    And user enters valid password
    And user clicks on login button
    Then user can welcome to admin messages with logout link

  @inpass
  Scenario: login with invalid password
    Given user is in bank home page
    When user enters valid username
    But user enters invalid password
    And user clicks on login button
    Then user can welcome to admin messages with logout link

  @data
  Scenario Outline: login with valid credentials
    Given user is in bank home page
    When user enters valid username "<username>"
    And user enters valid password "<password>"
    And user clicks on login button
    Then user can an error message

    Examples: 
      | username | password |
      | Admin    | admin    |
      | admin    | Admin    |
      | Admin    |          |
      
