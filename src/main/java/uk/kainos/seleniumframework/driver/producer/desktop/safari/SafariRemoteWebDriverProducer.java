package uk.kainos.seleniumframework.driver.producer.desktop.safari;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import uk.kainos.seleniumframework.ProjectEntity;
import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class SafariRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("enableVNC", true);
        safariOptions.setCapability("enableVideo", false);
        safariOptions.setCapability("name", ProjectEntity.getProjectName);
        if (browserVersion != null) {
            safariOptions.setCapability("browserVersion", browserVersion);
        }
        if (getBrowserstackOptions() != null) {
            safariOptions.setCapability("bstack:options", getBrowserstackOptions());
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), safariOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
