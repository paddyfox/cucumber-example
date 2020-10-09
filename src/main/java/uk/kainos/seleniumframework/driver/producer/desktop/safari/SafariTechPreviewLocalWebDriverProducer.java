package uk.kainos.seleniumframework.driver.producer.desktop.safari;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class SafariTechPreviewLocalWebDriverProducer implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setUseTechnologyPreview(true);

        return new SafariDriver(safariOptions);
    }
}
