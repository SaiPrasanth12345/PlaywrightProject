package TestRunner;
import StepDefs.StepDefinitions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions ( features = "src/test/resources/features",
                   glue = "StepDefs",
                   tags = "@LoginUser",
                   plugin = {"pretty","json:target/cucumber.json", "html:target/html-reports/report.html",
                                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class TestRunner {

}
