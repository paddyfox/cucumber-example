package uk.kainos.seleniumframework.driver.producer.desktop.edge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class EdgeRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("env", new String[]{"LANG=en_GB.UTF-8", "LANGUAGE=en_GB:en", "LC_ALL=en_GB.UTF-8"});
        if (browserVersion != null) {
            edgeOptions.setCapability("browserVersion", browserVersion);
        }
        if (getBrowserstackOptions() != null) {
            edgeOptions.setCapability("bstack:options", getBrowserstackOptions());
            //Only needed for legacy Edge
            edgeOptions.setCapability("browserstack.edge.enablePopups", "true");
            edgeOptions.setCapability("browserstack.geoLocation", "GB");
        }
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(GridUtils.getSeleniumGridURL(), edgeOptions);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
