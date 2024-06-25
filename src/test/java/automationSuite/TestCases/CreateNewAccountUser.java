package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.AccountCreation;

public class CreateNewAccountUser extends BaseClass {

	@Test(dataProvider = "User Validations")
	public void CreateAccount(HashMap<String, String> data) {
		// while running this test make sure you have updated the JSON with new and
		// fresh credentials to create new customers
		AccountCreation ac = new AccountCreation(driver);
		ac.createAccountTab();
		String message = ac.CreatingAccount(data.get("name"), data.get("password"), data.get("lastname"),
				data.get("email"));
		Assert.assertEquals(message, "Thank you for registering with Main Website Store.");
		
	}

	@DataProvider(name = "User Validations")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\New_Valid_Credentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
}
