package ebanking.cucumber_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {
	WebDriver driver;

	// roles
	@FindBy(css = "a[href *= 'Roles']")
	private WebElement roles;

	// constructor
	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click on roles button
	public RoleDetailsPage clickRoles() {
		this.roles.click();
		return PageFactory.initElements(driver, RoleDetailsPage.class);
	}
}
