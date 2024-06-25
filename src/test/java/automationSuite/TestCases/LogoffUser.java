package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.Homepage;
import automation_suite.pageobjects.LoginPage;

public class LogoffUser extends BaseClass {

	@Test(dataProvider = "User Validations", description = "Test to validate Login Functionality")
	public void LogoffTest(HashMap<String, String> data) {
		LoginPage lp = new LoginPage(driver);
		lp.LumaLogin(data.get("email"), data.get("password"));
		Assert.assertEquals(driver.getTitle(), "Home Page");
		String fullName = data.get("name") + " " + data.get("lastname");
		Homepage hp = new Homepage(driver);
		String Validationsucces = hp.LoginSuccess();
		Assert.assertEquals(Validationsucces, "Welcome, " + fullName + "!");
		String logOutmessage = hp.LogOff();
		Assert.assertEquals(logOutmessage, "You are signed out");

	}

	@DataProvider(name = "User Validations")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\LumaStoreCredentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}
