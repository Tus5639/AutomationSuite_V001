package automation_suite.Luma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation_suite.TestData.readData;

public class PurchaseOrderExistingAccount extends readData {

	WebDriver driver;

	@BeforeMethod
	public void Initialise() throws FileNotFoundException, IOException {
		// To read from properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\automation_suite\\TestData\\Browser.properties");
		prop.load(fis);
		String browser_name = prop.getProperty("browser");
		String run_mode = prop.getProperty("mode");

		if (browser_name.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments(run_mode);
			driver = new ChromeDriver(opt);
		}

		if (browser_name.equalsIgnoreCase("firefox")) {
			System.setProperty("webriver.gecko.driver",
					"D:\\Selenium\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void closeAll() {
		driver.quit();
	}

	@Test(dataProvider = "data")
	public void PurchaseorderExistingUser(HashMap<String, String> data) throws InterruptedException, IOException {

		driver.get("https://magento.softwaretestingboard.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement SignIN = driver
				.findElement(By.cssSelector("header[class='page-header'] li:nth-child(2) a:nth-child(1)"));
		wait.until(ExpectedConditions.visibilityOf(SignIN));
		SignIN.click();
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
		String click_element = "Men";
		List<WebElement> search = driver.findElements(By.xpath("//ul[@id='ui-id-2']/li/a"));
		for (WebElement ele : search) {
			if (ele.getText().equalsIgnoreCase(click_element)) {
				ele.click();
				break;
			}

		}

		Assert.assertEquals(click_element, driver.getTitle());

		List<WebElement> categories = driver.findElements(By.cssSelector(".categories-menu ul li a"));
		for (WebElement ele : categories) {
			if (ele.getText().equalsIgnoreCase(data.get("category"))) {
				ele.click();
				break;
			}
		}

		// wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title-wrapper h1 span")));
		// Verify the category title
		String categoryTitle = driver.findElement(By.cssSelector("div.page-title-wrapper h1 span")).getText();
		Assert.assertEquals(categoryTitle, data.get("category"), "Categories don't match");

		// ViewProducts
		List<WebElement> productsList = driver
				.findElements(By.cssSelector("[class='products list items product-items'] li div strong a "));

		for (WebElement ele : productsList) {
			if (ele.getText().equalsIgnoreCase(data.get("product"))) {
				ele.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='page-title'] span")));
		// product size
		List<WebElement> productSize = driver
				.findElements(By.cssSelector("div[class='swatch-attribute size'] div div"));
		for (WebElement size : productSize) {
			if (size.getText().equalsIgnoreCase(data.get("size"))) {
				size.click();
				break;
			}
		}
		// product color
		driver.findElement(
				By.cssSelector("div[class='swatch-attribute color'] div div[aria-label='" + data.get("Color") + "']"))
				.click();

		// Add to cart
		driver.findElement(By.cssSelector("div[class='actions'] button[title='Add to Cart'] span")).click();
		String confirmation_msg = "You added " + data.get("product") + " to your ";

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']/div/div")));
		WebElement cartElement = driver
				.findElement(By.cssSelector("div[class='minicart-wrapper'] a[class='action showcart'] "));
		wait.until(ExpectedConditions.visibilityOf(cartElement));
		cartElement.click();

		// Proceed to Checkout
		driver.findElement(By.cssSelector("div div button[id='top-cart-btn-checkout']")).click();

		// wait.until(ExpectedConditions.presenceOfElementLocated(
		// By.cssSelector("div[class='primary'] button[class='button action continue
		// primary'] ")));

		System.out.println("Executed till now");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement nextpage = driver
				.findElement(By.cssSelector("div[class='primary'] button[class='button action continue primary'] "));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='ko_unique_1']")));
		WebElement Shipping_methods = driver.findElement(By.cssSelector("input[name='ko_unique_1']"));
		Shipping_methods.click();
		nextpage.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img[src*='loader-2.gif']")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("div div[class='payment-option-title field choice'] span span")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("div button[class='action primary checkout'] span")));

		int attempts = 0;
		while (attempts < 3) {
			try {
				driver.findElement(By.cssSelector("div button[class='action primary checkout'] span")).click();
				break;
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				System.out.println("ElementClickInterceptedException caught. Retrying...");
				Thread.sleep(2000);
				attempts++;
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img[src*='loader-2.gif']")));
		// }
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// confirmation Page

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div h1 span")));
		WebElement Orderconfirmation_msg = driver.findElement(By.cssSelector("div h1 span"));
		WebElement OrderPage = driver.findElement(By.cssSelector("div p a strong"));

		wait.until(ExpectedConditions.visibilityOf(Orderconfirmation_msg));
		String orderConfirmation = "Thank you for your purchase!";
		if (Orderconfirmation_msg.getText().equalsIgnoreCase(orderConfirmation)) {
			OrderPage.click();
		}

		TakesScreenshot srcshot = (TakesScreenshot) driver;
		File srcdest = srcshot.getScreenshotAs(OutputType.FILE);
		File destFIle = new File(
				"C:\\Users\\HP\\eclipse-workspace\\automation_suite\\Screenshots\\ExistingUserNewOrder.png");
		FileUtils.copyFile(srcdest, destFIle);

	}

	@DataProvider(name = "data")
	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\Valid_credentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
}
