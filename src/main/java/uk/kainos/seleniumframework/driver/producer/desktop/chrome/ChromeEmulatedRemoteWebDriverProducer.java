package uk.kainos.seleniumframework.driver.producer.desktop.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.ProjectEntity;
import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

import java.util.HashMap;
import java.util.Map;


public class ChromeEmulatedRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.setCapability("enableVideo", false);
        chromeOptions.setCapability("name", ProjectEntity.getProjectName);
        chromeOptions.setCapability("env", new String[]{"LANG=en_GB.UTF-8", "LANGUAGE=en_GB:en", "LC_ALL=en_GB.UTF-8"});
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--safebrowsing-disable-download-protection");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", "true");
        chromeOptions.setExperimentalOption("prefs", prefs);
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
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
