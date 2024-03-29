package pfox.seleniumframework.driver.producer.desktop.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import pfox.seleniumframework.driver.GridUtils;
import pfox.seleniumframework.driver.producer.BaseRemoteDriver;
import pfox.seleniumframework.driver.producer.WebDriverProducer;

public class ChromeEuropeanLocaleRemoteWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        ChromeOptions chromeOptions = new ChromeOptions();
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
