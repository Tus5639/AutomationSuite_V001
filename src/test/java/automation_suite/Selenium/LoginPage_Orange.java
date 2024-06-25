package automation_suite.Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.TestData.readData;

public class LoginPage_Orange extends readData {

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
			opt.addArguments(mode);
			driver = new ChromeDriver(opt);
		}
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "dp")
	public void test(HashMap<String, String> data) {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String title = driver.getTitle();
		System.out.println(title);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.name("username")).sendKeys(data.get("username"));
		driver.findElement(By.name("password")).sendKeys(data.get("password"));
		driver.findElement(By.cssSelector("[type='submit']")).click();
		System.out.println(driver.getTitle());

	}

	@AfterMethod
	public void close() {

		driver.close();
	}

	@DataProvider(name = "dp")
	public Object[][] LoginCredentuials() throws IOException {
		List<HashMap<String, String>> data = readJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\automation_suite\\TestData\\credentials.JSON");

		return new Object[][] { { (data.get(0)) }, { (data.get(1)) } };

	}

}
