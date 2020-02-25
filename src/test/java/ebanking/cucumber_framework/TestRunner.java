package ebanking.cucumber_framework;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*
 * features : accepts an array string which are path to the folders which contains features file
 * glue: accepts an array of String which are package names which contains selenium test code for
 * test steps in Scenarios
 * dryRun: accepts either true or false. If it's true then it will check for every step in 
 * feature file contains an equivalent java method for selenium code in step definition package
 * 
 * 
 */

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/java/features" },
		glue = {"stepdefinitions"},
		dryRun = false,
		monochrome = true
	)

public class TestRunner {

}
