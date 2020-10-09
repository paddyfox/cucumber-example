package uk.kainos.seleniumframework.driver.producer.desktop.ie;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;
import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

import java.util.HashMap;

public class IENativeEventsRemoteWebDriverProducer implements WebDriverProducer {

    private final static String platform = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM);
    private final static String platformVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM_VERSION);

    @Override
    public WebDriver produce() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.destructivelyEnsureCleanSession();
        internetExplorerOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (platform != null || platformVersion != null) {
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            if (platform != null) {
                browserstackOptions.put("os", platform);
            }
            if (platformVersion != null) {
                browserstackOptions.put("osVersion", platformVersion);
            }
            browserstackOptions.put("local", "false");
            internetExplorerOptions.setCapability("bstack:options", browserstackOptions);
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), internetExplorerOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
