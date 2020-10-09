package uk.kainos.seleniumframework.driver.producer.desktop.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class ChromeEuropeanLocaleRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.setCapability("enableVideo", false);
        chromeOptions.setCapability("env", new String[]{"LANG=pt_PT.UTF-8", "LANGUAGE=pt_PT:en", "LC_ALL=pt_PT.UTF-8"});
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
