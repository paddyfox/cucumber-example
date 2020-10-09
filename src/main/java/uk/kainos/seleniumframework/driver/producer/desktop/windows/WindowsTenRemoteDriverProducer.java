package uk.kainos.seleniumframework.driver.producer.desktop.windows;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;

import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class WindowsTenRemoteDriverProducer implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "WindowsPC");
        capabilities.setCapability("app", "Root");
        WindowsDriver<WindowsElement> remoteWebDriver = new WindowsDriver<>(GridUtils.getSeleniumGridURL(), capabilities);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }
}
