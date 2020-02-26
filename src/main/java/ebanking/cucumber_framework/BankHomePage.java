package ebanking.cucumber_framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankHomePage {
	private WebDriver driver;

	// username
	private WebElement username() {
		return driver.findElement(By.id("txtuId"));
	}

	// password
	@FindBy(id = "txtPword")
	private WebElement password;

	// loginbutton
	@FindBy(id = "login")
	private WebElement loginButton;

	public BankHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill user name
	public void fillUserName(String username) {
		this.username().sendKeys(username);
	}

	// fill password
	public void fillPassword(String password) {
		this.password.sendKeys(password);
	}

	// click on login button
	public void clickLogin() {
		this.loginButton.click();
	}

}
