package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testGoogleTitle() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.title().contains("Google"));
    }

    @Test
    public void failingTest() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.title().contains("Bing")); // Will fail & take screenshot
    }
}
