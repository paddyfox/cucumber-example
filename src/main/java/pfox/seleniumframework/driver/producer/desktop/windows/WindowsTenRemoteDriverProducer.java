package pfox.seleniumframework.driver.producer.desktop.windows;

import io.appium.java_client.windows.WindowsDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;

import pfox.seleniumframework.driver.GridUtils;
import pfox.seleniumframework.driver.producer.WebDriverProducer;

public class WindowsTenRemoteDriverProducer implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "WindowsPC");
        capabilities.setCapability("app", "Root");
        WindowsDriver remoteWebDriver = new WindowsDriver(GridUtils.getSeleniumGridURL(), capabilities);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
