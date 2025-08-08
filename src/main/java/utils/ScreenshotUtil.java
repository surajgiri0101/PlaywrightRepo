package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static void takeScreenshot(Page page, String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("reports/screenshots/" + fileName + ".png")));
    }
}
