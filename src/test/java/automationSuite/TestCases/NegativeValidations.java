package automationSuite.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.BaseClass.BaseClass;
import automation_suite.pageobjects.LoginPage;

@Test(dataProvider = "User Validations", description = "Negative Validation")
public class NegativeValidations extends BaseClass {

	public void NegativeTest(HashMap<String, String> data) {
		LoginPage lp = new LoginPage(driver);
		lp.LumaLogin(data.get("email"), data.get("password"));
		WebElement error_msg = lp.Negative();
		String WebTitle = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		Assert.assertEquals(WebTitle, error_msg.getText());

	}

	@DataProvider(name = "User Validations")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\NegativeValidations.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
}
