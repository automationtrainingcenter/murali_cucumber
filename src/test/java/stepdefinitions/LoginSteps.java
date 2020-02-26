package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ebanking.cucumber_framework.BankHomePage;

public class LoginSteps {
	WebDriver driver;
	WebDriverWait wait;
	private Scenario scenario;
	private BankHomePage bankHomePageObj;

	@Before
	public void launch(Scenario scenario) {
		this.scenario = scenario;
		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);

	}

	@After
	public void tearDown(Scenario scenario) {
		driver.close();
		scenario.write("browser closed");
	}

	@Given("user is in bank home page")
	public void user_is_in_bank_home_page() {
		bankHomePageObj = new BankHomePage(driver);
		this.scenario.write("browser launched");
	}

	@When("user enters valid username")
	public void user_enters_valid_username() {
		bankHomePageObj.fillUserName("Admin");
		this.scenario.write("username located and filled Admin");
	}

	@When("user enters valid password")
	public void user_enters_valid_password() {
		bankHomePageObj.fillPassword("Admin");
		this.scenario.write("password located and filled Admin");
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		bankHomePageObj.clickLogin();
		this.scenario.write("login located and clicked");
	}

	@Then("user can welcome to admin messages with logout link")
	public void user_can_welcome_to_admin_messages_with_logout_link() {
		WebElement logoutLink = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href *= 'primus']")));

		// assertions will verify a test case is passed or failed
		Assert.assertTrue(logoutLink.isDisplayed());
		this.scenario.write("logout link displayed with welcome to admin message");
	}

	@When("user enters invalid password")
	public void user_enters_invalid_password() {
		bankHomePageObj.fillPassword("askldjfh");
		this.scenario.write("password located and filled askldjfh");
	}

	@When("user enters valid username {string}")
	public void user_enters_valid_username(String string) {
		bankHomePageObj.fillUserName(string);
		this.scenario.write("username located and filled " + string);
	}

	@When("user enters valid password {string}")
	public void user_enters_valid_password(String string) {
		bankHomePageObj.fillPassword(string);
		this.scenario.write("password located and filled " + string);
	}

	@Then("user can an error message")
	public void user_can_an_error_message() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		this.scenario.write("Error message is " + text);
		Assert.assertTrue(text.toLowerCase().contains("incorrect") || text.toLowerCase().contains("please fill"));

	}

}