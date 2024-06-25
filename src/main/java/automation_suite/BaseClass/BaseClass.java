package automation_suite.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.core.exec.WaitContainerCmdExec;


import io.cucumber.java.After;


public class BaseClass {

	public WebDriver driver;
	ExtentSparkReporter reporter;
	ExtentReports repo;
	
	Logger logger = Logger.getLogger("BaseClass");

	public void initialise() throws IOException {
	
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\automation_suite\\TestData\\Browser.properties");
		prop.load(fis);

		String browser_name = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// prop.getProperty("browser");
		String run_mode = prop.getProperty("mode");

		if (browser_name.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments(run_mode);
			driver = new ChromeDriver(opt);
		}

		if (browser_name.equalsIgnoreCase("firefox")) {
			System.setProperty("webriver.gecko.driver",
					"D:\\Selenium\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("-headless");
	        
			driver = new FirefoxDriver(options);
		}
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void StartExecution() throws Exception{
		logger.info("Driver initialised");
		initialise();
		driver.get("https://magento.softwaretestingboard.com/");
	}

	

	public void Waitmethod(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void invisibilityWait(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public void clickableMethod(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	public List<HashMap<String, String>> readJsonData(String filepath) throws IOException {

		String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String SrcSCreenshot(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot src = ((TakesScreenshot) driver);
		File SrcFile = src.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String filename = TestCaseName + "_" + timestamp + ".png";
		String path = System.getProperty("user.dir") + "/screenshots/" + filename;

		File DestFile = new File(path);
		FileUtils.copyFile(SrcFile, DestFile);
		return path;
	}

	public Object[][] LoginData() throws IOException {
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "\\src\\test\\java\\automation_suite\\TestData\\Valid_credentials.JSON");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}
