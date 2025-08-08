package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utils.ConfigReader;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String baseUrl;

    @BeforeSuite
    public void setupSuite() {
        ConfigReader.loadProperties();
        System.out.println("Loaded baseUrl: " + ConfigReader.getProperty("baseUrl")); // Debug line
    }


    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        baseUrl = ConfigReader.getProperty("baseUrl");

        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException("baseUrl not found in config.properties. Please check the file.");
        }

        page.navigate(baseUrl);
    }


    @AfterMethod
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
