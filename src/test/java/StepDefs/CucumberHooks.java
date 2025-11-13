package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.nio.file.Files;

public class CucumberHooks {

    public DriverInstansiation driverInstansiation =  new DriverInstansiation();
    public static Scenario scenario;

    @Before
    public void scenarioLog(Scenario scenario) {
        this.scenario = scenario;
        scenario.log("Executing Scenario: " + scenario.getName());
    }

    @Before
    public void launchBrowser() {
        driverInstansiation.launchBrowser();

    }

    @AfterStep
    public void afterStep() {
        driverInstansiation.takeScreenshot(scenario);
    }

    @After
    //public void teardown(Scenario scenario, TestCaseFinished event) {
    public void teardown(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            driverInstansiation.takeFullPageScreenshot(scenario);

            //get error for the failed reason
            //io.cucumber.plugin.event.Result result = event.getResult();
            //final Throwable error = result.getError();
            //error.printStackTrace();
        }

        scenario.attach(Files.readAllBytes(driverInstansiation.getVideoPath()), "video/webm", scenario.getName()+"- Video");

        DriverInstansiation.page.close();
        DriverInstansiation.browser.close();
        DriverInstansiation.playwright.close();
    }
}
