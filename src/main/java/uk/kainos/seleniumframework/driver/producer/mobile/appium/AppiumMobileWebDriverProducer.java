package uk.kainos.seleniumframework.driver.producer.mobile.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;

import uk.kainos.seleniumframework.driver.GridUtils;
import uk.kainos.seleniumframework.driver.producer.BaseRemoteDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;
import uk.kainos.seleniumframework.properties.AppiumMobileProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

import java.util.HashMap;

public class AppiumMobileWebDriverProducer extends BaseRemoteDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        String browserName = PropertyLoader.getProperty(AppiumMobileProperties.BROWSER_NAME);
        String platformName = PropertyLoader.getProperty(AppiumMobileProperties.PLATFORM_NAME);
        String osVersion = PropertyLoader.getProperty(AppiumMobileProperties.OS_VERSION);
        String deviceName = PropertyLoader.getProperty(AppiumMobileProperties.DEVICE_NAME);
        String appiumVersion = PropertyLoader.getProperty(AppiumMobileProperties.APPIUM_VERSION) != null ?
                PropertyLoader.getProperty(AppiumMobileProperties.APPIUM_VERSION) : "1.17.0";

        DesiredCapabilities caps = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<>(getBrowserstackOptions());

        browserstackOptions.put("realMobile", "true");
        browserstackOptions.put("appiumVersion", appiumVersion);
        browserstackOptions.put("local", "false");

        browserstackOptions.put(MobileCapabilityType.DEVICE_NAME, deviceName);
        if (osVersion != null) {
            browserstackOptions.put("osVersion", osVersion);
        }
        if (browserName != null) {
            caps.setBrowserName(browserName);
        }
        if (platformName != null) {
            caps.setBrowserName(platformName);
        }
        caps.setCapability("bstack:options", browserstackOptions);
        AppiumDriver<WebElement> remoteWebDriver = new AppiumDriver<>(GridUtils.getSeleniumGridURL(), caps);
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }
}
