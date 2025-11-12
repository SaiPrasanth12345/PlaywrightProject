package Commom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class LoginPage {

    private Page page;

    public LoginPage (Page page) {
        this.page = page;
    }

    public void login(String email, String password) {
        Locator emailLocator = page.getByLabel("E-Mail Address");
        emailLocator.fill(email);

        page.getByPlaceholder("Password").fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.waitForLoadState(LoadState.LOAD);
    }
}
