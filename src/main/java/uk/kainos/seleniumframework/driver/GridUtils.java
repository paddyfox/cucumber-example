package uk.kainos.seleniumframework.driver;

import uk.kainos.seleniumframework.log.Log;
import uk.kainos.seleniumframework.properties.BrowserstackProperties;
import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class GridUtils {

    public static URL getSeleniumGridURL() {
        String username = PropertyLoader.getProperty(BrowserstackProperties.BROWSERSTACK_USERNAME);
        String accessKey = PropertyLoader.getProperty(BrowserstackProperties.BROWSERSTACK_ACCESS_KEY);

        //Presumed Browserstack request
        if (username != null || accessKey != null) {
            if (username == null) {
                throw new DriverCreationException("Trying to use Browserstack for testing, but the browserstack.username property is not provided.");
            }
            if (accessKey == null) {
                throw new DriverCreationException("Trying to use Browserstack for testing, but the browserstack.access.key property is not provided.");
            }
            Log.Info("Loading driver with Browserstack");
            return getBrowserstackGridURL(username, accessKey);
        }
        return getAssumedSeleniumGridURL();
    }

    public static URL getAssumedSeleniumGridURL() {
        String seleniumGridUrl = PropertyLoader.getProperty(CommonProperties.SELENIUM_GRID_URL);

        if (seleniumGridUrl == null) {
            throw new DriverCreationException("No Selenium grid url specified in the configuration.  Make sure to set the selenium.grid.url property.   " +
                    "If you are trying to user Browserstack, make sure to set browserstack.username and browserstack.access.key properties.");
        }
        try {
            Log.Info("Loading driver with grid url of " + seleniumGridUrl);
            return new URL(seleniumGridUrl);
        } catch (MalformedURLException e) {
            throw new DriverCreationException("Unable create a seleniumframework grid url from: " + seleniumGridUrl, e);
        }
    }

    public static URL getBrowserstackGridURL(String username, String accessKey) {
        String browserstackUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

        if (username == null) {
            throw new DriverCreationException("Browserstack Error: No username specified");
        }
        if (accessKey == null) {
            throw new DriverCreationException("Browserstack Error: No access key specified");
        }
        try {
            Log.Info("Loading driver via Browserstack...");
            return new URL(browserstackUrl);
        } catch (MalformedURLException e) {
            throw new DriverCreationException("Unable create a driver for browserstack from: " + browserstackUrl, e);
        }
    }
}
