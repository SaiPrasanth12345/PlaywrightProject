package StepDefs;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCaseFinished;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DriverInstansiation {

    public static Playwright playwright;
    BrowserType.LaunchOptions launchOptions;
    Browser.NewContextOptions contextOptions;
    public static BrowserContext context;
    public static Browser browser;
    public static Page page;

    public void launchBrowser() {
        // initializing the browser launch & context options
        launchOptions = new BrowserType.LaunchOptions();
        contextOptions = new Browser.NewContextOptions();

        // browser launch
        playwright = Playwright.create();
        browser = playwright.chromium().launch(getLaunchOptions());
        context = browser.newContext(getBrowserContextOptions());
        page = context.newPage();
    }

    public BrowserType.LaunchOptions getLaunchOptions() {
        launchOptions.setHeadless(false);
        launchOptions.setChannel("chrome");
        return launchOptions;
    }

    public Browser.NewContextOptions getBrowserContextOptions() {
        // setting screen size / Maxmize
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        contextOptions.setViewportSize(width, height);

        // video Record
        contextOptions.setRecordVideoDir(Paths.get("./target/videos"));
        return contextOptions;
    }

    public void takeScreenshot(Scenario scenario) {
        byte[] scrscnht= page.screenshot();
        scenario.attach(scrscnht, "img/png", scenario.getName() + " - Image");
    }

    public void takeFullPageScreenshot(Scenario scenario) {
        byte[] scrscnht= page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        scenario.attach(scrscnht, "img/png", scenario.getName() + " - Screenshot");
    }

    public void takLocatorScreenshot(Locator locator , Scenario scenario) {
        byte[] locatorScrnscht = locator.screenshot();
        scenario.attach(locatorScrnscht, "img/png", scenario.getName() + " - Locator Screenshot");
    }

    public Path getVideoPath() {
        Path path = null;
        Video video = context.pages().getFirst().video();
        if(video != null) {
            path = video.path();
        }
        return path;
    }

    /*  After method - added in CucumberHooks Page
    @After
    //public void teardown(Scenario scenario, TestCaseFinished event) {
    public void teardown(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            takeFullPageScreenshot(scenario);

            //get error for the failed reason
            //io.cucumber.plugin.event.Result result = event.getResult();
            //final Throwable error = result.getError();
            //error.printStackTrace();
        }

        scenario.attach(Files.readAllBytes(getVideoPath()), "video/webm", scenario.getName());

        page.close();
        browser.close();
        playwright.close();
    }

     */

}
