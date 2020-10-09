package uk.kainos.seleniumframework.driver.producer.desktop.safari;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class SafariLocalWebDriverProducer implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        return new SafariDriver();
    }
}
