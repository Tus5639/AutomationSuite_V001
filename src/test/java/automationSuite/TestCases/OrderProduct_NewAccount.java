package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.CheckoutsPage;
import automation_suite.pageobjects.Homepage;
import automation_suite.pageobjects.LoginPage;
import automation_suite.pageobjects.OrderConfirmationPage;
import automation_suite.pageobjects.ProductsPage;

public class OrderProduct_NewAccount extends BaseClass {

	@Test(description = "Order Product using New Account", dataProvider = "New User")
	public void Validation(HashMap<String, String> data) throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.LumaLogin(data.get("email"), data.get("password"));
		Homepage hp = new Homepage(driver);
		hp.TabSearch(data.get("Gender"));

		ProductsPage pg = new ProductsPage(driver);
		pg.ProductCategory(data.get("category"));
		pg.ProductSelect(data.get("product"));
		pg.SizeSelect(data.get("size"));
		pg.ColorClass(data);
		CheckoutsPage cp = new CheckoutsPage(driver);
		cp.Address("223 B", "108A hudda", "Gurugram", "Uttar Pradesh", "273006", "India", "9005000100");
		cp.Payments();
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		ocp.ReceiptsPage();

	}

	@DataProvider(name = "New User")
	public Object[][] credentials() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\New_Valid_Credentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}
