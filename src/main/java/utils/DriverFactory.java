package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");
        WebDriver driver;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fxOpts = new FirefoxOptions();
                if (headless) fxOpts.addArguments("-headless");
                driver = new FirefoxDriver(fxOpts);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOpts = new EdgeOptions();
                if (headless) edgeOpts.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOpts);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--disable-notifications");
                chromeOpts.addArguments("--remote-allow-origins=*");
                chromeOpts.addArguments("--disable-blink-features=AutomationControlled");
                chromeOpts.addArguments("--disable-popup-blocking");
                chromeOpts.addArguments("--disable-extensions");
                chromeOpts.addArguments("--start-maximized");

                // Block ads/images for faster loading
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("profile.managed_default_content_settings.images", 2);
                chromeOpts.setExperimentalOption("prefs", prefs);

                if (headless) chromeOpts.addArguments("--headless=new", "--window-size=1920,1080");
                driver = new ChromeDriver(chromeOpts);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getInt("page.load.timeout")));
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        return driver;
    }
}