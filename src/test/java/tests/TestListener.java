package tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            BaseTest base = (BaseTest) currentClass;
            ScreenshotUtil.takeScreenshot(base.page, result.getMethod().getMethodName());
            test.fail("Test Failed, screenshot taken");
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}
