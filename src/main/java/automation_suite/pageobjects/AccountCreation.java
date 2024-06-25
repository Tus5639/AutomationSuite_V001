package automation_suite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_suite.BaseClass.BaseClass;

public class AccountCreation extends BaseClass {

	public AccountCreation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "firstname")
	WebElement firstName;

	@FindBy(id = "lastname")
	WebElement lastName;

	@FindBy(id = "email_address")
	WebElement userID;

	@FindBy(id = "password")
	WebElement Userpassword;

	@FindBy(id = "password-confirmation")
	WebElement cnfPassword;

	@FindBy(css = "[class='action submit primary']")
	WebElement submitAccCreation;

	@FindBy(xpath = "//div[@role='alert']/div/div")
	WebElement PrintMessage;

	@FindBy(xpath = "//div[@class='box box-information']/div")
	List<WebElement> AccountUser;

	@FindBy(css = "header[class='page-header'] li:nth-child(3) a:nth-child(1)")
	WebElement CreateNewAccountTab;

	public String CreatingAccount(String Username, String password, String lastname, String emailID) {
		Waitmethod(firstName);
		firstName.sendKeys(Username);
		lastName.sendKeys(lastname);
		userID.sendKeys(emailID);
		Userpassword.sendKeys(password);
		cnfPassword.sendKeys(password);
		submitAccCreation.click();
		Waitmethod(PrintMessage);
		return PrintMessage.getText();
	}

	public void Validate() {
		for (WebElement ele : AccountUser) {
			System.out.println(ele.getText());
		}

	}
	public void createAccountTab() {
		CreateNewAccountTab.click();
	}
}
