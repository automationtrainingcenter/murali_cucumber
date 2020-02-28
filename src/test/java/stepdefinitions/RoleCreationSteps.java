package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ebanking.cucumber_framework.AdminHomePage;
import ebanking.cucumber_framework.BankHomePage;
import ebanking.cucumber_framework.RoleDataPOJO;
import ebanking.cucumber_framework.RoleDetailsPage;
import ebanking.cucumber_framework.RoleInsertionPage;
import gherkin.deps.com.google.gson.Gson;
import io.cucumber.datatable.DataTable;

public class RoleCreationSteps {
	private static final Type String = null;
	private WebDriver driver;
	private CommonSteps commonSteps;
	private RoleDetailsPage roleDetailsPageObj;
	private RoleInsertionPage roleInsertionPageObj;
	private Alert alert;

	public RoleCreationSteps(CommonSteps commonSteps) {
		this.commonSteps = commonSteps;
		this.driver = this.commonSteps.getDriver();
	}

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
		Assert.assertTrue(text.toLowerCase().contains("sucessfully123"));
	}

	@When("user fill role creation form with valid data")
	public void user_fill_role_creation_form_with_valid_data(DataTable dataTable) {
		// single row multiple columns
//		List<String> data = dataTable.asList();
//		roleInsertionPageObj.fillRoleName(data.get(0));
//		roleInsertionPageObj.selectRoleType(data.get(1));

		// multiple rows and multiple columns
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> row : data) {
			roleInsertionPageObj.fillRoleName(row.get("rName"));
			roleInsertionPageObj.selectRoleType(row.get("rType"));
		}

	}

	@Then("user can see empty role creation form")
	public void user_can_see_empty_role_creation_form() {
		Assert.assertTrue(roleInsertionPageObj.isFormEmpty());
	}

	@When("user clicks on reset button")
	public void user_clicks_on_reset_button() {
		roleInsertionPageObj = roleInsertionPageObj.clickReset();
	}

	@When("user clicks reset after filling role creation form with valid data from {string}")
	public void user_clicks_reset_after_filling_role_creation_form_with_valid_data_from(String fileName) {
		FileReader reader;
		try {
			reader = new FileReader(".//resources//"+fileName);
			// gson 
			Gson gson = new Gson();
			RoleDataPOJO[] rolesData = gson.fromJson(reader, RoleDataPOJO[].class);
			for(RoleDataPOJO roleData : rolesData) {
				roleInsertionPageObj.fillRoleName(roleData.getRoleName());
				roleInsertionPageObj.selectRoleType(roleData.getRoleType());
				roleInsertionPageObj.clickReset();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
