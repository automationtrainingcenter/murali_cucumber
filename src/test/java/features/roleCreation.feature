@role
Feature: role creation

@rc_valid
Scenario: role creation with valid data
	Given user is in bank home page
	And user do login with valid credentials
	And user clicks on roles button in admin home page
	And user clicks on new role button in role details page
	When user enters valid role name "someNewRole"
	And user selects valid role type "E"
	And user clicks on submit button
	Then user can see role created with some id message