package uk.kainos.seleniumframework.driver.producer.desktop.opera;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.ProjectEntity;
import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;


public class OperaRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setCapability("enableVNC", true);
        operaOptions.setCapability("enableVideo", false);
        operaOptions.setCapability("name", ProjectEntity.getProjectName);
        operaOptions.setCapability("browserName", "opera");
        operaOptions.setCapability("env", new String[]{"LANG=en_GB.UTF-8", "LANGUAGE=en_GB:en", "LC_ALL=en_GB.UTF-8"});
        operaOptions.addArguments("--no-sandbox");
        operaOptions.addArguments("--disable-dev-shm-usage");
        if (browserVersion != null) {
            operaOptions.setCapability("browserVersion", browserVersion);
        }
        if (getBrowserstackOptions() != null) {
            operaOptions.setCapability("bstack:options", getBrowserstackOptions());
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), operaOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
