package pfox.seleniumframework.driver.producer.desktop.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

import pfox.seleniumframework.ProjectEntity;
import pfox.seleniumframework.driver.GridUtils;
import pfox.seleniumframework.driver.producer.BaseRemoteDriver;
import pfox.seleniumframework.driver.producer.WebDriverProducer;


public class ChromeRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--safebrowsing-disable-download-protection");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", "true");
        chromeOptions.setExperimentalOption("prefs", prefs);
        if (browserVersion != null) {
            chromeOptions.setCapability("browserVersion", browserVersion);
        }
        if (getBrowserstackOptions() != null) {
            chromeOptions.setCapability("bstack:options", getBrowserstackOptions());
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), chromeOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
