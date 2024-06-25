package automation_suite.BaseClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation_suite.utils.ExtentReporterManager;
import freemarker.log.Logger;

public class TestListeners extends BaseClass implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterManager.Reportgeneration();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		Logger logger = Logger.getLogger("BaseClass");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test is Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String resource = null;
		test.fail(result.getThrowable());
		
		
		String filepath = result.getMethod().getMethodName();
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			resource = SrcSCreenshot(filepath, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(resource, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
