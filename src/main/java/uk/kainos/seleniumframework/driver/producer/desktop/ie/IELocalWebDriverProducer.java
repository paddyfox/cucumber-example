package uk.kainos.seleniumframework.driver.producer.desktop.ie;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import uk.kainos.seleniumframework.driver.producer.BaseLocalDriver;
import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;

public class IELocalWebDriverProducer extends BaseLocalDriver implements WebDriverProducer {

    @Override
    public WebDriver produce() {
        if (browserVersion != null) {
            WebDriverManager.iedriver().browserVersion(browserVersion).setup();
        } else {
            WebDriverManager.iedriver().setup();
        }
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.requireWindowFocus();
        internetExplorerOptions.destructivelyEnsureCleanSession();

        return new InternetExplorerDriver(internetExplorerOptions);
    }
}
