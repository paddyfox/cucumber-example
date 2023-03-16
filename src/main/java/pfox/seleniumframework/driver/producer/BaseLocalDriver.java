package pfox.seleniumframework.driver.producer;

import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

public class BaseLocalDriver {

    protected final static String browserVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_VERSION);
}
