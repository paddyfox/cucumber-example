package uk.kainos.seleniumframework.site;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.WebDriverWait;

import uk.kainos.seleniumframework.driver.DriverManager;
import uk.kainos.seleniumframework.log.Log;
import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

/**
 * Helper class for using the Selenium web driver.
 */
public class Site {

    private static final long DEFAULT_WAIT_TIMEOUT = 10;

    /**
     * Effectively a getter for the web driver wait object. Just makes code more readable to not have "get" at the front.
     * <p>
     * E.g. site.webDriverWait().until(...)
     *
     * @return a web driver wait object
     */
    public static WebDriverWait webDriverWait(long timeoutInSeconds) {

        return new WebDriverWait(DriverManager.getDriver(), timeoutInSeconds);
    }

    /**
     * Like <code>webDriverWait(long timeoutInSeconds) but with the default timeout in seconds.</code>
     *
     * @return a web driver wait object
     */
    public static WebDriverWait webDriverWait() {

        long timeoutInSeconds = DEFAULT_WAIT_TIMEOUT;

        String timeoutProperty = PropertyLoader.getProperty(CommonProperties.SELENIUM_DRIVER_WAIT_TIMEOUT);

        if (timeoutProperty != null) {
            try {
                timeoutInSeconds = Long.parseLong(timeoutProperty);
            } catch (NumberFormatException e) {
                throw new SiteCreationException("The format of " + timeoutProperty + " for the driver_wait_timeout property is invalid", e);
            }
        }

        return webDriverWait(timeoutInSeconds);
    }

    public static boolean verifyPageTitle(String actual, String expected) {
        if (actual.contains(expected)) {
            Log.Info("Page loaded: " + actual);

            return true;
        }

        throw new NotFoundException("Page title did not match: " + expected + " but got: " + actual);
    }

    public static void goToURL(String webAddress) {
        DriverManager.getDriver().get(webAddress);
    }

    public static void closeWindow() {
        DriverManager.getDriver().close();
    }
}
