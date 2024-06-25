package automation_suite.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\automation_suite\\feature",glue = "automation_suite.StepDefinitions",
monochrome = true, 
plugin = {"html:target/cucumber-report.html"})

public class RunnerTest extends AbstractTestNGCucumberTests{

}
