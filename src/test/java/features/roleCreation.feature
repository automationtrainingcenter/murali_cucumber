@role
Feature: role creation

  Background: 
    Given user is in bank home page
    And user do login with valid credentials
    And user clicks on roles button in admin home page
    And user clicks on new role button in role details page

  @rc_valid
  Scenario: role creation with valid data
    When user enters valid role name "someNewRole"
    And user selects valid role type "E"
    And user clicks on submit button
    Then user can see role created with some id message

  @rc_multi
  Scenario Outline: role creation with multiple sets of data
    When user enters valid role name "<roleName>"
    And user selects valid role type "<roleType>"
    And user clicks on submit button
    Then user can see role created with some id message

    Examples: 
      | roleName            | roleType |
      | newFeatureRoleOne   | E        |
      | newFeatureRoleTwo   | E        |
      | newFeatureRoleThree | E        |
      | newFeatureRoleFour  | E        |
      | newFeatureRoleFive  | E        |

  @rc_dtable
  Scenario: role creation reset with valid data
    When user fill role creation form with valid data
      | rName      | rType |
      | anynewRole | E     |
    And user clicks on reset button
    Then user can see empty role creation form
    
    
 @rc_json
 Scenario: role creation reset with json data
 	When user clicks reset after filling role creation form with valid data from "roledata.json"
 	Then user can see empty role creation form
