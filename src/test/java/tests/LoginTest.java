package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.login(username, password);
    }
}
