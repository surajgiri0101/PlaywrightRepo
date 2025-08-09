package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utils.ReportManager;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected static ExtentTest test;

    @Parameters({"headless"})
    @BeforeMethod
    public void setUp(@Optional("false") String headless) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))
        );
        page = browser.newPage();
        test = ReportManager.getInstance().createTest("Sample Test");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
        ReportManager.flushReports();
    }
}
