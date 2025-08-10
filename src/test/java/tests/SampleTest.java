package tests;


import com.microsoft.playwright.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }


    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
        page = context.newPage();
    }

    @AfterMethod
    public void captureFailure(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = "screenshots/" + result.getName() + "_" + timeStamp + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
			test.fail("Test Failed: " + result.getThrowable());
			test.addScreenCaptureFromPath(screenshotPath);
        }
    }
    
    @Test
    public void testGoogleSearch() {
        test = extent.createTest("Google Search Test");
        page.navigate("https://www.google.com");
        page.fill("textarea[name='q']", "Playwright Java");
        page.keyboard().press("Enter");
        page.waitForTimeout(2000);
        String title = page.title();
        test.info("Page title is: " + title);
        Assert.assertTrue(title.contains("Playwright Java"), "Title validation failed!");
    }

    @Test
    public void testLoginPage() {
        test = extent.createTest("Login Page Test");

        // Navigate to demo login page
        page.navigate("https://the-internet.herokuapp.com/login");

        // Fill username and password
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");

        // Click login button
        page.click("button[type='submit']");

        // Validate login success
        String successMessage = page.textContent("#flash");
        test.info("Login Message: " + successMessage);

        Assert.assertTrue(successMessage.contains("You logged into a secure area!"), "Login failed!");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
