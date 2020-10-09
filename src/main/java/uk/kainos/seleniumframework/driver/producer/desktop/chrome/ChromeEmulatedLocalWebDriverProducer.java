package uk.kainos.seleniumframework.driver.producer.desktop.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import uk.kainos.seleniumframework.driver.producer.BaseLocalDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

import java.util.HashMap;
import java.util.Map;

public class ChromeEmulatedLocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        if (browserVersion != null) {
            WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }

        return new ChromeDriver(chromeOptions);
    }
}
