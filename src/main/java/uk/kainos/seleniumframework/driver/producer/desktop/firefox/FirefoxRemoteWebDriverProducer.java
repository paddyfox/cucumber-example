package uk.kainos.seleniumframework.driver.producer.desktop.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.ProjectEntity;
import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class FirefoxRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
        firefoxOptions.setCapability("enableVNC", true);
        firefoxOptions.setCapability("enableVideo", false);
        firefoxOptions.setCapability("name", ProjectEntity.getProjectName);
        firefoxOptions.setCapability("dom.ipc.plugins.enabled.libflashplayer.so", "true");
        firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("intl.accept_languages", "en-GB");
        firefoxOptions.setProfile(firefoxProfile);
        if (browserVersion != null) {
            firefoxOptions.setCapability("browserVersion", browserVersion);
        }
        if (getBrowserstackOptions() != null) {
            firefoxOptions.setCapability("bstack:options", getBrowserstackOptions());
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), firefoxOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
