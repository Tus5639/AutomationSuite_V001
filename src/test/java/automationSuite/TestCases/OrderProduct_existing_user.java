package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.CheckoutsPage;
import automation_suite.pageobjects.Homepage;
import automation_suite.pageobjects.LoginPage;
import automation_suite.pageobjects.OrderConfirmationPage;
import automation_suite.pageobjects.ProductsPage;

public class OrderProduct_existing_user extends BaseClass {

	@Test(dataProvider = "data", description = "Order Product using Existing Account")
	public void Purchase(HashMap<String, String> data) throws IOException, InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.LumaLogin(data.get("email"), data.get("password"));
		String title = driver.getTitle();
		Assert.assertEquals(title, "Home Page");
		Homepage hp = new Homepage(driver);
		hp.TabSearch(data.get("Gender"));
		
		ProductsPage pg = new ProductsPage(driver);
		pg.ProductCategory(data.get("category"));
		pg.ProductSelect(data.get("product"));
		pg.SizeSelect(data.get("size"));
		pg.ColorClass(data);
		CheckoutsPage cp = new CheckoutsPage(driver);
		cp.Shipping();
		cp.Payments();
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		ocp.ReceiptsPage();
		

	}

	@DataProvider(name = "data")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\Valid_credentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}
