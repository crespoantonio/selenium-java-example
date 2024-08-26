package automationpractice.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {

    private WebDriver driver;
    private static final String SCREENSHOTS_DIR = "screenshots";

    public ScreenshotManager(WebDriver driver) {
        this.driver = driver;
        createScreenshotsDirectory();
    }

    // Create screenshots directory if it does not exist
    private void createScreenshotsDirectory() {
        File dir = new File(SCREENSHOTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Take a screenshot and save it with a timestamp
    public void takeScreenshot() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOTS_DIR + "/screenshot_" + timestamp + ".png");
            Files.copy(screenshot.toPath(), destination.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}