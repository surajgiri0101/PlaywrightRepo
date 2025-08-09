package tests;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import org.testng.annotations.*;

public class SampleTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false) // NON-HEADLESS mode
        );
        context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
        page = context.newPage();
    }

    @Test
    public void testGoogleSearch() {
        page.navigate("https://www.google.com");
        page.fill("textarea[name='q']", "Playwright Java");
        page.keyboard().press("Enter");
        page.waitForTimeout(2000);
        System.out.println("Title after search: " + page.title());
    }

    @AfterClass
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
