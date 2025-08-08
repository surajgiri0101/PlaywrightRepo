package utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void takeScreenshot(Page page, String name) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("test-output/reports/" + name + "_" + timestamp + ".png")));
    }
}
