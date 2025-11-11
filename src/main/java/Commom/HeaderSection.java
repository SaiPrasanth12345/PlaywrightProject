package Commom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class HeaderSection {

    private Page page;
    public String myAccount = "//a[@data-toggle='dropdown']//span[contains(.,'My account')]";
    public String login = "//span[contains(text(),'Login')]";
    public String register = "//span[contains(text(),'Register')]";

    public HeaderSection (Page page) {
        this.page = page;
    }

    public void launchURL(String URL) {
        page.navigate(URL);
        page.waitForLoadState(LoadState.LOAD);
    }

    public void clickHome() {
        Locator home = page.locator("'Home'");
        home.click();
    }

    public void clickLogin() {
        //Locator myAccountBtn = page.locator(myAccount);

        //click the visible myAccount
        Locator myAccountBtn = page.locator("//span[contains(.,'My account')] >> visible=true");
        myAccountBtn.hover();

        // login click
        Locator Login = page.locator(login);
        Login.click();
    }

    public void clickRegister() {
        Locator myAccountBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account"));
        myAccountBtn.hover();
        page.locator(register).click();
    }

    public String getCurrentPage() {
        // to check the navigation
        String currentPage = page.locator(" //li[@aria-current='page']").textContent();
        System.out.println("Current Page is : " + currentPage);
        return currentPage;
    }

}
