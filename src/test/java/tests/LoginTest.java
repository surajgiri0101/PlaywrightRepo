package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void googleSearch() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.title().contains("Google"));
    }

    @Test
    public void failingTest() {
        page.navigate("https://www.google.com");
        Assert.assertTrue(page.title().contains("Bing")); // Intentional fail
    }
}
