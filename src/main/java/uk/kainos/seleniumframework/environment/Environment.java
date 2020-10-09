package uk.kainos.seleniumframework.environment;

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
}
