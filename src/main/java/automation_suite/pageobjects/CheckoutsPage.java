package automation_suite.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import automation_suite.BaseClass.BaseClass;

public class CheckoutsPage extends BaseClass{
	
	public CheckoutsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div[class='primary'] button[class='button action continue primary'] ")
	WebElement NextPage;
	
	@FindBy(css="input[name='ko_unique_1']")
	WebElement ShippingOption;
	
	@FindBy(css = "img[src*='loader-2.gif']")
	WebElement imageLoader;
	
	@FindBy(css="div div[class='payment-option-title field choice'] span span")
	WebElement ApplyforDiscount;
	
	@FindBy(css="div button[class='action primary checkout'] span")
	WebElement PlaceOrder;
	
	@FindBy(css="div[class='control'] input[id='discount-code']")
	WebElement DiscountCode;
	
	@FindBy(css="div[class='primary'] button[value='Apply Discount']")
	WebElement ApplyforDiscountButton;
	
	@FindBy(css="div input[name='company']")
	WebElement CompanyName;
	
	@FindBy(css="div input[name='street[0]']")
	WebElement Address;
	
	@FindBy(css="div input[name='city']")
	WebElement City;
	
	@FindBy(css="div select[name='region_id']")
	WebElement Province;
	
	@FindBy(css="div input[name='postcode']")
	WebElement postal_code;
	
	@FindBy(css="div select[name='country_id']")
	WebElement CountryCode;
	
	@FindBy(css="div input[name='telephone']")
	WebElement telephone;
	
	@FindBy(css="input[name='ko_unique_3']")
	WebElement Shipping5$;
	
	
	public void Address(String companyName,String address,String city,String Province,String postalCode,String Country_Code,String mobilenum) {
		invisibilityWait(imageLoader);
		Waitmethod(Address);
		CompanyName.sendKeys(companyName);
		Select sel = new Select(this.CountryCode);
		sel.selectByVisibleText(Country_Code);
		Waitmethod(Shipping5$);
		Address.sendKeys(address);
		City.sendKeys(city);
		Select sel1 = new Select(this.Province);
		sel1.selectByVisibleText(Province);
		postal_code.sendKeys(postalCode);
		telephone.sendKeys(mobilenum);
		Waitmethod(NextPage);
		NextPage.click();
		invisibilityWait(imageLoader);
		
	}
	
	public void Shipping() {
		Waitmethod(ShippingOption);
		Waitmethod(NextPage);
		
		ShippingOption.click();
		NextPage.click();
		Waitmethod(imageLoader);
	}
	
	public void Payments() throws InterruptedException {
		invisibilityWait(imageLoader);
		Waitmethod(ApplyforDiscount);
		Waitmethod(PlaceOrder);
		int count = 0;
		while(count<3) {
			try {
				PlaceOrder.click();
				break;
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				System.out.println("ElementClickInterceptedException caught. Retrying...");
				Thread.sleep(2000);
				count++;
			}
		}
		invisibilityWait(imageLoader);
	}

}
