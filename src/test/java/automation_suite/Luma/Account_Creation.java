package automation_suite.Luma;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.TestData.readData;

public class Account_Creation extends readData {
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
			driver = new ChromeDriver();
		}
		
		if(browser_name.equalsIgnoreCase("firefox")) {
			System.setProperty("webriver.gecko.driver", "D:\\Selenium\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() {
		driver.close();
	}

	@Test(dataProvider = "data")
	public void createAccount(HashMap<String, String> data) {

		// Luma website is launched in a new window
		driver.get("https://magento.softwaretestingboard.com/");
		driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)")).click();
		String title = "Create New Customer Account";
		Assert.assertEquals(title, driver.getTitle());
		driver.findElement(By.id("firstname")).sendKeys(data.get("name"));
		driver.findElement(By.id("lastname")).sendKeys(data.get("lastname"));
		driver.findElement(By.id("email_address")).sendKeys(data.get("email"));
		driver.findElement(By.id("password")).sendKeys(data.get("password"));
		driver.findElement(By.id("password-confirmation")).sendKeys(data.get("re-type_password"));
		driver.findElement(By.cssSelector("[class='action submit primary']")).click();
		String confirmation_message = driver.findElement(By.xpath("//div[@role='alert']/div/div")).getText();
		if (confirmation_message.contains(driver.findElement(By.xpath("//div[@role='alert']/div/div")).getText())) {
			System.out.println(confirmation_message);
		}
		List<WebElement> web = driver.findElements(By.xpath("//div[@class='box box-information']/div"));
		for (WebElement ele : web) {
			String fname = ele.getText();
			System.out.println(fname);
		}

	}

	@DataProvider(name = "data")
	public Object[][] getDatatofill() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\LumaStoreCredentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) },{data.get(2)} };

	}

}
