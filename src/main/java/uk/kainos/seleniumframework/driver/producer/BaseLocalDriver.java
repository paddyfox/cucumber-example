package uk.kainos.seleniumframework.driver.producer;

import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

public class BaseLocalDriver {

    protected final static String browserVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_VERSION);
}
