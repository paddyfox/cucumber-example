package uk.kainos.seleniumframework.driver.producer.desktop.opera;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import uk.kainos.seleniumframework.driver.producer.BaseLocalDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class OperaLocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.operadriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.operadriver().setup();
        }

        return new OperaDriver();
    }
}

