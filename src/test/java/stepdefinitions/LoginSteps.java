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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	WebDriver driver;
	WebDriverWait wait;

	@Given("user is in bank home page")
	public void user_is_in_bank_home_page() {
		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);

	}

	@When("user enters valid username")
	public void user_enters_valid_username() {
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
	}

	@When("user enters valid password")
	public void user_enters_valid_password() {
		driver.findElement(By.id("txtPword")).sendKeys("Admin");
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		driver.findElement(By.id("login")).click();
	}

	@Then("user can welcome to admin messages with logout link")
	public void user_can_welcome_to_admin_messages_with_logout_link() {
		WebElement logoutLink = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href *= 'primus']")));

		// assertions will verify a test case is passed or failed
		Assert.assertTrue(logoutLink.isDisplayed());
		driver.close();
	}

	@When("user enters invalid password")
	public void user_enters_invalid_password() {
		driver.findElement(By.id("txtPword")).sendKeys("askldjfh");
	}

	@When("user enters valid username {string}")
	public void user_enters_valid_username(String string) {
		driver.findElement(By.id("txtuId")).sendKeys(string);
	}

	@When("user enters valid password {string}")
	public void user_enters_valid_password(String string) {
		driver.findElement(By.id("txtPword")).sendKeys(string);
	}

	@Then("user can an error message")
	public void user_can_an_error_message() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
//		scenario.write("Error message is "+text);
		Assert.assertTrue(text.toLowerCase().contains("incorrect") || text.toLowerCase().contains("please fill"));
		driver.close();
	
	}

}
