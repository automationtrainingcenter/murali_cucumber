package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ebanking.cucumber_framework.AdminHomePage;
import ebanking.cucumber_framework.BankHomePage;
import ebanking.cucumber_framework.RoleDetailsPage;
import ebanking.cucumber_framework.RoleInsertionPage;

public class RoleCreationSteps {
	private WebDriver driver;
	private RoleDetailsPage roleDetailsPageObj;
	private RoleInsertionPage roleInsertionPageObj;
	private Alert alert;
	
	
	@Given("user do login with valid credentials")
	public void user_do_login_with_valid_credentials() {
		BankHomePage bankHomePageObj = new BankHomePage(driver);
		bankHomePageObj.fillUserName("Admin");
		bankHomePageObj.fillPassword("Admin");
		bankHomePageObj.clickLogin();

	}

	@Given("user clicks on roles button in admin home page")
	public void user_clicks_on_roles_button_in_admin_home_page() {
		AdminHomePage adminHomePageObj = PageFactory.initElements(driver, AdminHomePage.class);
		roleDetailsPageObj = adminHomePageObj.clickRoles();
	}

	@Given("user clicks on new role button in role details page")
	public void user_clicks_on_new_role_button_in_role_details_page() {
		roleInsertionPageObj = roleDetailsPageObj.clickNewRoleButton();
	}

	
	@When("user enters valid role name {string}")
	public void user_enters_valid_role_name(String roleName) {
		roleInsertionPageObj.fillRoleName(roleName);
	}

	@When("user selects valid role type {string}")
	public void user_selects_valid_role_type(String roleType) {
		roleInsertionPageObj.selectRoleType(roleType);
	}

	@When("user clicks on submit button")
	public void user_clicks_on_submit_button() {
		alert = roleInsertionPageObj.clickSubmit();
	}

	@Then("user can see role created with some id message")
	public void user_can_see_role_created_with_some_id_message() {
		String text = alert.getText();
		alert.accept();
		Assert.assertTrue(text.toLowerCase().contains("sucessfully"));
	}

}
