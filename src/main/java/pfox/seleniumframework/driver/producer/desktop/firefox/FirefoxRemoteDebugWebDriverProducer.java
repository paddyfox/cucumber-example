package pfox.seleniumframework.driver.producer.desktop.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

import pfox.seleniumframework.ProjectEntity;
import pfox.seleniumframework.driver.GridUtils;
import pfox.seleniumframework.driver.producer.WebDriverProducer;
import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

public class FirefoxRemoteDebugWebDriverProducer implements WebDriverProducer {

    private final static String platform = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM);
    private final static String platformVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM_VERSION);

    @Override
    public WebDriver produce() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("dom.ipc.plugins.enabled.libflashplayer.so", "true");
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
        firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        if (platform != null || platformVersion != null) {
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            if (platform != null) {
                browserstackOptions.put("os", platform);
            }
            if (platformVersion != null) {
                browserstackOptions.put("osVersion", platformVersion);
            }
            browserstackOptions.put("local", "false");
            firefoxOptions.setCapability("bstack:options", browserstackOptions);
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), firefoxOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
