package Commom;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class RegisterPage {

    private Page page;

    public RegisterPage (Page page) {
        this.page = page;
    }

    public void clickContinue() {
        Locator locContinue =  page.locator("input[value='Continue']");
        locContinue.focus();
        locContinue.click();
    }

    public boolean isPrivacyPolicyWarningDisplayed() {
        Locator privacyPolicyWarning =  page.locator("//div[contains(@class,'alert-danger') and contains(.,'Privacy Policy')]");
        return privacyPolicyWarning.isVisible();
    }

    public int mandatoryWarningMessageDisplayed() {
        Locator mandatoryWarning = page.locator("div.text-danger");
        return mandatoryWarning.count();
    }

    public void fillRegistrationDetails(String firstName, String lastName, String email, String mobileNum, String password) {
        page.getByLabel("First Name").fill(firstName);
        page.getByPlaceholder("Last Name").fill(lastName);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail")).fill(email);
        page.getByText("Telephone").fill(mobileNum);
        page.getByLabel("Password", new Page.GetByLabelOptions().setExact(true)).fill(password);
        page.getByPlaceholder("Password Confirm", new Page.GetByPlaceholderOptions().setExact(true)).fill(password);
        page.waitForTimeout(2500);
        subscribe();
        checkPrivacyCheckbox();
    }

    public void checkPrivacyCheckbox() {
        Locator checkPrivacy = page.locator("//label[@for='input-agree']");
        checkPrivacy.check();
    }

    public void subscribe() {
        page.locator("//label[@for='input-newsletter-yes']").focus();
        page.locator("//label[@for='input-newsletter-yes']").click();
        page.waitForTimeout(2500);
        page.locator("//label[@for='input-newsletter-no']").click();
        page.waitForTimeout(2500);
    }

    public boolean validateAccountCreationSuccessful() {
        page.waitForLoadState(LoadState.LOAD);
        // xpath ->  //h1/i['Your Account Has Been Created!']
        Locator successMessage = page.getByText(" Your Account Has Been Created!");
        return successMessage.isVisible();
    }

    public void clickContinueinAccountSuccesfulPage() {
        page.locator("//a[.='Continue']").click();
        page.waitForLoadState(LoadState.LOAD);
    }

}
