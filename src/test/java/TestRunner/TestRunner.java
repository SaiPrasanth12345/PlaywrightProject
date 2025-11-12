package TestRunner;
import StepDefs.StepDefinitions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions ( features = "src/test/resources/login.feature",
                   glue = "StepDefs",
                   tags = "@LoginUser")

public class TestRunner {

}
