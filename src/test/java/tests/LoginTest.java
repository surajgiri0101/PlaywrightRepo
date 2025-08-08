package tests;

import base.BaseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

	@BeforeMethod
    @Test
    public void loginTest() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.login(username, password);
    }
    
    
    @AfterMethod
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
