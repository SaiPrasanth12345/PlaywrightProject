package Commom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MyAccountPage {

    private Page page;

    public MyAccountPage (Page page) {
        this.page = page;
    }

    public boolean myAccountPageDisplayed() {
        Locator myAccount = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My Account"));
        return myAccount.isVisible();
    }

}
