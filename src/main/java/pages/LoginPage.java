package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Locator;

public class LoginPage {
    private Page page;

    private Locator usernameField;
    private Locator passwordField;
    private Locator checkbox;
    private Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("input[name='userName']");
        this.passwordField = page.locator("input[name='password']");
        this.checkbox = page.locator("input[name='acceptTerms']");
        this.loginButton = page.locator("//button[.//span[text()='Login']]");
    }

    public void login(String username, String password) {
   //     usernameField.waitFor(); // waits until input appears
        usernameField.fill(username);

//        passwordField.waitFor();
        passwordField.fill(password);

        checkbox.check(); // checkbox action

        loginButton.click();
    }
}
