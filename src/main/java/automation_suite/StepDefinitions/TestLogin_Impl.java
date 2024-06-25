package automation_suite.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestLogin_Impl extends BaseClass {

	@Given("user launches the Luma Webapplication in browser")
	public void user_launches_the_luma_webapplication_in_browser() throws IOException {
		initialise();
	}

	@When("^user enters the (.+) and (.+) to the webpage$")
	public void user_enters_the_username_and_password_to_the_webpage(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.LumaLogin(username, password);
	}

	@Then("user is able login to webpApplication")
	public void user_is_able_login_to_webp_application() {
		Assert.assertEquals(driver.getTitle(), "Home Page");
	}
	
	@When("user hits on submit button")
	public void When_user_hits_on_submit_button(){
		LoginPage lp = new LoginPage(driver);
		lp.Negative();
	}
	
	/*
	 * @When("user hits on submit button") public void user_hits_on_submit_button()
	 * { // Write code here that turns the phrase above into concrete actions throw
	 * new io.cucumber.java.PendingException(); }
	 */

	
	
	@Then("user gets the error message printed on the screen")
	public void user_gets_the_error_message_printed_on_the_screen() {
		LoginPage lp = new LoginPage(driver);
		Assert.assertTrue(lp.isErrorMessageDisplayed(), "Error message was not displayed as expected");
	}

}
