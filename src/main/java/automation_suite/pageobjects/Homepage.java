package automation_suite.pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import automation_suite.BaseClass.BaseClass;

public class Homepage extends BaseClass {

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@id='ui-id-2']/li/a")
	List<WebElement> TabElements;

	@FindBy(css = "div[class='minicart-wrapper'] a ")
	WebElement CartButton;

	@FindBy(css = ".categories-menu ul li a")
	List<WebElement> searchCategory;
	
	@FindBy(css="div.page-title-wrapper h1 span")
	WebElement Title;
	
	@FindBy(xpath = "//ul[@class='header links']/li[@class='greet welcome']/span[@class='logged-in']")
	WebElement LoginSucces;
	
	@FindBy(xpath="//div[@class='panel wrapper']//ul//button")
	WebElement dropOption;
	
	@FindBy(xpath = "//span[@class='base']")
	WebElement SignOutConfim_msg;
	
	
	@FindBy(css="div[class='panel wrapper'] ul[class='header links'] li a ")
	List<WebElement> NavigationOptions;
	
	public void TabSearch(String gender) {
		for (WebElement ele : TabElements) {
			if (ele.getText().equalsIgnoreCase(gender)) {
				ele.click();
				break;
			}
		}
	}
		
	
		
	public String LoginSuccess() {
		driver.navigate().refresh();
		Waitmethod(LoginSucces);
		return LoginSucces.getText();
	}
	
	public String LogOff() {
		try {
	        // Ensure dropOption (user dropdown) is visible before interacting
	        Waitmethod(dropOption);
	        clickableMethod(dropOption);
	        dropOption.click();

	        // Find and click the "Sign Out" option
	        for (WebElement option : NavigationOptions) {
	            if (option.getText().equalsIgnoreCase("Sign Out")) {
	                clickableMethod(option);
	                option.click();
	                break;
	            }
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur during logout
	        System.out.println("Exception during logout: " + e.getMessage());
	        e.printStackTrace();
	    }
		
		return SignOutConfim_msg.getText();
	}
	}
	
	

	


