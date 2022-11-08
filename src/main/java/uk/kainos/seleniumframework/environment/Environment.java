package uk.kainos.seleniumframework.environment;

import uk.kainos.seleniumframework.properties.CommonProperties;
import uk.kainos.seleniumframework.properties.PropertyLoader;

import java.util.Objects;

import static com.google.common.base.Strings.isNullOrEmpty;

public class Environment {

    private static final String defaultEnvironmentName = "dev1";

    public static String getName() {
        String environmentName = System.getenv("environment");

        if (isNullOrEmpty(environmentName)) {
            environmentName = defaultEnvironmentName;
        }
        return environmentName;
    }

    public static Boolean executingInBrowserstack() {
        return Objects.requireNonNull(PropertyLoader.getProperty(CommonProperties.BROWSER_TYPE)).contains("Remote")
                || Objects.requireNonNull(PropertyLoader.getProperty(CommonProperties.BROWSER_TYPE)).contains("AppiumMobile");
    }
}
