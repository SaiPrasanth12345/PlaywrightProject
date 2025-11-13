package TestRunner;
import StepDefs.StepDefinitions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions ( features = "src/test/resources/login.feature",
                   glue = "StepDefs",
                   tags = "@LoginUser",
                   plugin = {"json:target/cucumber.json", "html:target/html-reports/report.html"})

public class TestRunner {

}
