package uk.kainos.seleniumframework.driver;

import org.openqa.selenium.WebDriver;

import uk.kainos.seleniumframework.log.Log;
import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

public class DriverManager {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final BrowserType DEFAULT_BROWSER = BrowserType.ChromeLocal;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (webDriver.get() != null) {
                webDriver.get().quit();
            }
        }));
    }

    public static WebDriver getDriver() {
        WebDriver d = webDriver.get();

        if (d == null) {
            Log.Info("Driver needs to be created, creating one...");
            d = createDriver();
        }

        return d;
    }

    public static void clearDriver() {
        WebDriver d = webDriver.get();
        if (d != null) {
            try {
                webDriver.get().quit();
            } catch (Exception e) {
                Log.Warn("Couldn't quit one of the locally created drivers because: " + e);
            }
            webDriver.set(null);
        }
    }

    private static WebDriver createDriver() {
        String currentBrowser = PropertyLoader.getProperty(CommonProperties.BROWSER_TYPE);
        if (currentBrowser == null) {
            String fallbackBrowser = DEFAULT_BROWSER.toString();
            Log.Warn(currentBrowser + " is not a valid browser type, falling back to default of " + fallbackBrowser);
            currentBrowser = fallbackBrowser;
        }
        DriverMode.setRemote(currentBrowser.contains("Remote") || currentBrowser.contains("Appium"));
        WebDriver d = new DriverFactory(currentBrowser).createInstance();
        webDriver.set(d);

        return d;
    }
}
