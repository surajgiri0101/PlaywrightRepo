package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(isHeadless)
        );

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
