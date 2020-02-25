Feature: login functionality

  Scenario: login with valid credentials
    Given user is in bank home page
    When user enters valid username
    And user enters valid password
    And user clicks on login button
    Then user can welcome to admin messages with logout link
