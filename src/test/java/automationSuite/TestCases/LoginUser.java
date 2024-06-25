package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.LoginPage;

public class LoginUser extends BaseClass {

	@Test(dataProvider = "User Validations", description = "Test to validate Login Functionality")
	public void Login(HashMap<String, String> data) {
		LoginPage lp = new LoginPage(driver);
		lp.LumaLogin(data.get("email"), data.get("password"));
		Assert.assertEquals(driver.getTitle(), "Home Page");

	}

	@DataProvider(name = "User Validations")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\LumaStoreCredentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
}
