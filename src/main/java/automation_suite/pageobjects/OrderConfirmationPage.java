package automation_suite.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_suite.BaseClass.BaseClass;

public class OrderConfirmationPage extends BaseClass {

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div h1 span")
	WebElement Order_Confirmation_msg;
	
	@FindBy(css = "div p a strong")
	WebElement OrderID;
	
	
	public String Confirmation() {
		Waitmethod(Order_Confirmation_msg);
		String confirmation_msg = Order_Confirmation_msg.getText();
		
		return confirmation_msg;
	}
	
	public void ReceiptsPage() throws InterruptedException {
		Waitmethod(OrderID);
		OrderID.click();
		Thread.sleep(2000);
	}
	
	
}
