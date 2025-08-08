package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testGoogleTitle() {
        String title = page.title();
        Assert.assertTrue(title.contains("Google"), "Title does not contain Google!");
    }
}
