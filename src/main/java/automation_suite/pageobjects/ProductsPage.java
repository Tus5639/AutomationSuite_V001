package automation_suite.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation_suite.BaseClass.BaseClass;

public class ProductsPage extends BaseClass{
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.page-title-wrapper h1 span")
	WebElement Title;
	
	@FindBy(css = "[class='products list items product-items'] li div strong a ")
	List<WebElement> products;
	
	@FindBy(css="h1[class='page-title'] span")
	WebElement ProductTitle;
	
	@FindBy(css="div[class='swatch-attribute size'] div div")
	List<WebElement> sizeOptions;
	
	@FindBy(css="div[class='actions'] button[title='Add to Cart'] span")
	WebElement AddToCart;
	
	@FindBy(xpath="//div[@role='alert']/div/div")
	WebElement ConfirmationMsg;
	
	@FindBy(css="div[class='minicart-wrapper'] a[class='action showcart'] ")
	WebElement GoToCartButton;
	
	@FindBy(css="div div button[id='top-cart-btn-checkout']")
	WebElement CheckOutButton;
	
	@FindBy(css = ".categories-menu ul li a")
	List<WebElement> searchCategory;
	
	public void ProductCategory(String category) {

		for (WebElement ele : searchCategory) {
			if (ele.getText().equalsIgnoreCase(category)) {
				ele.click();
				break;
			}
		}
		Waitmethod(Title);
	}
	
	public void ProductSelect(String ProductName) {
		for (WebElement ele : products) {
			if (ele.getText().equalsIgnoreCase(ProductName)) {
				ele.click();
				break;
			}
		}
		Waitmethod(ProductTitle);
	}
	
	public void SizeSelect(String Size) {
		//Size select
		for (WebElement size : sizeOptions) {
			if (size.getText().equalsIgnoreCase(Size)) {
				size.click();
				break;
			}
		}
	}
		
		
		public void ColorClass(HashMap<String, String> data) throws InterruptedException {
		//for Colour
		driver.findElement(
				By.cssSelector("div[class='swatch-attribute color'] div div[aria-label='" + data.get("Color") + "']"))
				.click();
		AddToCart.click();
		String confirmation_msg = "You added " + data.get("product") + " to your ";
		Waitmethod(ConfirmationMsg);
		Waitmethod(GoToCartButton);
		GoToCartButton.click();
		Waitmethod(CheckOutButton);
		int count = 0;
		while(count<3) {
			try{
				CheckOutButton.click();
				break;
			}
			catch(org.openqa.selenium.ElementClickInterceptedException e){
				System.out.println("ElementClickInterceptedException caught. Retrying...");
				Thread.sleep(2000);
				count++;
			}
		}
		
		System.out.println("Product is added to Cart successfully and Proceed to checkout");
		
		
	}

}
