package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void openGoogle() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.title().contains("Google"));
        test.pass("Google opened successfully in browser");
    }
}
