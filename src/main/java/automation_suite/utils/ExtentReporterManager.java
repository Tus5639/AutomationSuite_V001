package automation_suite.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterManager{
public static ExtentReports Reportgeneration() {
		File filepath = new File(System.getProperty("user.dir")+"//report//Test_Report.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		reporter.config().setDocumentTitle("Luma E-Commerce");
		reporter.config().setReportName("Test validation ");
		
		ExtentReports repo = new ExtentReports();
		repo.attachReporter(reporter);
		return repo;
	}}