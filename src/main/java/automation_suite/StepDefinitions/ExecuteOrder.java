package automation_suite.StepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.CheckoutsPage;
import automation_suite.pageobjects.Homepage;
import automation_suite.pageobjects.LoginPage;
import automation_suite.pageobjects.OrderConfirmationPage;
import automation_suite.pageobjects.ProductPage2;
import automation_suite.pageobjects.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Product;

public class ExecuteOrder extends BaseClass {
	//WebDriver driver;
	LoginPage lp ;
	Homepage hp ;
	ProductsPage pg ;
	
	@Given("user launches the Ecommerce webpage")
	public void user_launches_the_ecommerce_webpage() throws IOException {
		
		 initialise();
	}

	@When("^user enters the credentials for (.+) and (.+)$")
	public void user_enters_the_credentials_for_username_and_password(String username, String password) {
		lp = new LoginPage(driver);
		lp.LumaLogin(username, password);
	}

	@When("user navigates to Homepage")
	public void user_navigates_to_homepage() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Home Page");
	}

	
	@When("^user navigates to (.+) category tab to search for Products$")
	public void user_navigates_to_gender_category_tab_to_search_for_products(String gender) {
		hp = new Homepage(driver);
		hp.TabSearch(gender);
		
	}

	@When("^user clicks to (.+) and (.+)$")
	public void user_navigates_to_product_and_productCategory(String productCategory,String product ) {
		ProductsPage pg = new ProductsPage(driver);
		pg.ProductCategory(productCategory);
		pg.ProductSelect(product);
	}

	@Then("^user enters the required (.+) and (.+) for the product and adds the product to cart$")
	public void user_enters_the_required_size_and_color_for_the_product_and_adds_the_product_to_cart(String size,String color) throws InterruptedException {
		ProductPage2 pg2 = new ProductPage2(driver);
		pg2.SizeSelect(size);
		pg2.ColorClass(color);
	}

	@Then("user confirms and confirms the order on checkout page")
	public void user_confirms_and_confirms_the_order_on_checkout_page() throws InterruptedException {
		CheckoutsPage cp = new CheckoutsPage(driver);
		cp.Shipping();
		cp.Payments();
	}
	
	@Then("user gets the Order confirmation page")
	public void user_gets_the_order_confirmation_page() throws InterruptedException {
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		ocp.ReceiptsPage();
	}

}
