package StepDefs;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.awt.*;

public class DriverInstansiation {

    Playwright playwright;
    BrowserType.LaunchOptions launchOptions;
    BrowserContext context;
    Browser browser;
    public static Page page;
    public static Scenario scenario;

    @Before
    public void scenarioLog(Scenario scenario) {
        DriverInstansiation.scenario = scenario;
        scenario.log("Executing Scenario: " + scenario.getName());
    }

    @Before
    public void launchBrowser() {
        playwright = Playwright.create();
        launchOptions = new BrowserType.LaunchOptions();
        browser = playwright.chromium().launch(getLaunchOptions());
        context = screenMaxmize();
        page = context.newPage();
    }

    public BrowserType.LaunchOptions getLaunchOptions() {
        launchOptions.setHeadless(false);
        launchOptions.setChannel("chrome");
        return launchOptions;
    }

    public BrowserContext screenMaxmize() {
        // To get the screensize dynamically, we need to use Toolkit
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        return browserContext;
    }

    @After
    public void teardown(Scenario scenario) {
        //page.close();
        //browser.close();
        //playwright.close();
    }

}
