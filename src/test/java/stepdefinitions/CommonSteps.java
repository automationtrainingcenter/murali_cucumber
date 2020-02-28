package stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Result.Type;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;

public class CommonSteps {
	WebDriver driver;
	private static boolean isInitalized = false;

	// browser launch
	public WebDriver getDriver() {
		if (!isInitalized) {
			System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("http://primusbank.qedgetech.com/");
			driver.manage().window().maximize();
			isInitalized = true;
		}
		return driver;
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		// capture the screenshot if and only if the scenario got failed
		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] srcImgBytes = ts.getScreenshotAs(OutputType.BYTES);
			scenario.embed(srcImgBytes, "image/png");
		}
	}

	// close the browser
	public void close() {
		driver.close();
		isInitalized = false;
	}
}
