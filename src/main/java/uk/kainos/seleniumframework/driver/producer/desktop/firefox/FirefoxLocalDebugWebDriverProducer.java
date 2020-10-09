package uk.kainos.seleniumframework.driver.producer.desktop.firefox;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import uk.kainos.seleniumframework.driver.producer.BaseLocalDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class FirefoxLocalDebugWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.firefoxdriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.firefoxdriver().setup();
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
        firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);

        return new FirefoxDriver(firefoxOptions);
    }
}
