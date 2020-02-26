package ebanking.cucumber_framework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RoleInsertionPage {

	private WebDriver driver;

	// role name
	@FindBy(id = "txtRname")
	private WebElement roleName;

	// role type
	@FindBy(id = "lstRtypeN")
	private WebElement roleType;

	// submit
	@FindBy(id = "btninsert")
	private WebElement submitButton;

	// constructor
	public RoleInsertionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill role name
	public void fillRoleName(String roleName) {
		this.roleName.sendKeys(roleName);
	}

	// select roleType
	public void selectRoleType(String roleType) {
		Select roleSelect = new Select(this.roleType);
		roleSelect.selectByVisibleText(roleType);
	}

	// click on submit button
	public Alert clickSubmit() {
		this.submitButton.click();
		return driver.switchTo().alert();
	}

}
