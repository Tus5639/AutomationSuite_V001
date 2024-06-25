package automation_suite.Luma;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.TestData.readData;

public class Login_page extends readData {

	WebDriver driver;

	@BeforeMethod
	public void Login() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\automation_suite\\TestData\\Browser.properties");
		prop.load(fis);
		String browser_name = prop.getProperty("browser");
		String mode = prop.getProperty("mode");

		if (browser_name.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			// opt.addArguments(mode);
			driver = new ChromeDriver(opt);
		}
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() {
		driver.close();
	}

	@Test(dataProvider = "data")
	public void LoginPage(HashMap<String, String> data) {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(2) a:nth-child(1)")).click();
		String title = "Customer Login";
		Assert.assertEquals(title, driver.getTitle());
		WebElement username = driver.findElement(By.name("login[username]"));
		WebElement password = driver.findElement(By.name("login[password]"));
		WebElement submit_button = driver
				.findElement(By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]"));
		username.sendKeys(data.get("email"));
		password.sendKeys(data.get("password"));
		submit_button.click();
		String WebTitle = "Home Page";
		Assert.assertEquals(WebTitle, driver.getTitle());
	}

	@DataProvider(name = "data")
	public Object[][] loginCredentials() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\Valid_credentials.JSON");

		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}
