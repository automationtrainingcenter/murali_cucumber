package ebanking.cucumber_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoleDetailsPage {
	private WebDriver driver;

	// new role button
	@FindBy(id = "btnRoles")
	private WebElement newRoleButton;

	// constructor
	public RoleDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click on new role button
	public RoleInsertionPage clickNewRoleButton() {
		this.newRoleButton.click();
		return PageFactory.initElements(driver, RoleInsertionPage.class);
	}
}
