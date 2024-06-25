package automation_suite.pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import automation_suite.BaseClass.BaseClass;
import freemarker.log.Logger;

public class LoginPage extends BaseClass {

	Logger logger = Logger.getLogger("LoginPage");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "header[class='page-header'] li:nth-child(2) a:nth-child(1)")
	WebElement SignInTab;

	@FindBy(name = "login[username]")
	WebElement name;

	@FindBy(name = "login[password]")
	WebElement password;

	@FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
	WebElement Submit_Button;
	
	@FindBy(xpath="//div[@role='alert']/div/div")
	WebElement error_msg;

	public void LumaLogin(String UserName, String Password) {
		logger.info("User launches on homepage");
		Waitmethod(SignInTab);
		SignInTab.click();
		name.sendKeys(UserName);
		logger.info("Enters Username");
		password.sendKeys(Password);
		logger.info("ENters Password");
		Submit_Button.click();
		logger.info("Clicks on Submit Button");

	}
	
	public WebElement Negative() {
		Waitmethod(error_msg);
		logger.info("Printes the error message");
		return error_msg;
		
	}
	
	public void submit() {
		Submit_Button.click();
	}

	public Boolean isErrorMessageDisplayed() {
		Waitmethod(error_msg);
		return (error_msg).isDisplayed();
	}

}
