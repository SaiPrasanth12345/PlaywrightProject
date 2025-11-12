package StepDefs;

import Commom.HeaderSection;
import Commom.LoginPage;
import Commom.MyAccountPage;
import Commom.RegisterPage;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.Duration;

public class StepDefinitions {

    Scenario scenario = DriverInstansiation.scenario;
    Page page = DriverInstansiation.page;
    HeaderSection headerPage = new HeaderSection(page);
    RegisterPage registerPage = new RegisterPage(page);
    MyAccountPage myAccountPage = new MyAccountPage(page);
    LoginPage loginPage = new LoginPage(page);


    @Given("navigate to URL {string}")
    public void navigate_to_url(String url) {
        headerPage.launchURL(url);
        page.waitForLoadState(LoadState.LOAD);
    }

    @Given("click Register")
    public void click_register() {
        headerPage.clickRegister();
    }

    @When("click Continue button with empty mandatory fields")
    public void click_continue_button_with_empty_mandatory_fields() {
        registerPage.clickContinue();
    }

    @Then("Error message should get displayed")
    public void error_message_should_get_displayed() {
        System.out.println("Error Message displayed: "+ registerPage.isPrivacyPolicyWarningDisplayed());
        System.out.println("Warning count: "+ registerPage.mandatoryWarningMessageDisplayed());
        Assert.assertEquals(registerPage.mandatoryWarningMessageDisplayed(), 5);
    }

    @When("fill all the fields with the respective details")
    public void fill_all_the_fields_with_the_respective_details() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName + lastName + "@mail.com";
        String mobile = faker.phoneNumber().phoneNumber();
        String password = faker.name().username();

        registerPage.fillRegistrationDetails(firstName, lastName, email, mobile, password);
        System.out.printf("Details : %s, %s, %s, %s, %s", firstName, lastName, email, mobile, password);
    }
    @When("click on Register button")
    public void click_on_register_button() {
        registerPage.clickContinue();
    }


    @Then("the user registration should be successsful")
    public void the_user_registration_should_be_successsful() throws InterruptedException {
        boolean registrationSuccessful = registerPage.validateAccountCreationSuccessful();
        Assert.assertTrue("Registration is successful", registrationSuccessful);
    }

    @Given("click on Continue button in resgistration page")
    public void click_on_continue_button_in_resgistration_page() {
        registerPage.clickContinueinAccountSuccesfulPage();
    }

    @Then("the user should get navigaetd to MyAccount Page")
    public void the_user_should_get_navigaetd_to_my_account_page() {
       boolean myAccountDisplayed =  myAccountPage.myAccountPageDisplayed();
        Assert.assertTrue("My Account Page is displayed", myAccountDisplayed);

        String currentPage = headerPage.getCurrentPage();
        Assert.assertEquals("Current Page is MyAccount Page", currentPage, "Account");

        String title = headerPage.getPageTitle();
        Assert.assertEquals("Current Page Title is MyAccount", title, "My Account");
    }

    @Given("click Login button")
    public void click_login_button() {
        headerPage.clickLogin();
    }

    @When("login with the credentials {string} and {string}")
    public void login_with_the_credentials_and(String email, String password) {
        loginPage.login(email, password);
    }

}
