package uk.kainos.seleniumframework.driver.producer.desktop.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import uk.kainos.seleniumframework.driver.producer.BaseLocalDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class ChromeLocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }

        return new ChromeDriver();
    }
}
