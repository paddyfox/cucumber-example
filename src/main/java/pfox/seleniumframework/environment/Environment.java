package pfox.seleniumframework.environment;

import java.util.Objects;

import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

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
        return executingInJenkins() || Objects.requireNonNull(PropertyLoader.getProperty(CommonProperties.BROWSER_TYPE)).contains("Remote");
    }

    public static Boolean executingInJenkins() {
        String JenkinsUrl = System.getenv("JENKINS_URL");
        return !isNullOrEmpty(JenkinsUrl);
    }

    public static Boolean executingWithLegacyUsers() {
        String user = System.getProperty("user.name");
        return user.contains("pfox");
    }
}
