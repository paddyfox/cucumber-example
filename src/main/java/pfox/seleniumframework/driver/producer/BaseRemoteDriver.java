package pfox.seleniumframework.driver.producer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import pfox.seleniumframework.ProjectEntity;
import pfox.seleniumframework.log.Log;
import pfox.seleniumframework.properties.BrowserstackProperties;
import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

public class BaseRemoteDriver {

    protected final static String platform = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM);
    protected final static String platformVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_PLATFORM_VERSION);
    protected final static String browserVersion = PropertyLoader.getProperty(CommonProperties.BROWSER_VERSION);
    protected final static String projectName = ProjectEntity.getProjectName;

    protected HashMap<String, Object> getBrowserstackOptions() {
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        if (platform != null || platformVersion != null) {
            if (platform != null) {
                browserstackOptions.put("os", platform);
            }
            if (platformVersion != null) {
                browserstackOptions.put("osVersion", platformVersion);
            }
            browserstackOptions.putAll(setLogging());
        }
        browserstackOptions.put("projectName", projectName);
        browserstackOptions.put("buildName", projectName + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        browserstackOptions.put("sessionName", System.getProperty("user.name"));
        return browserstackOptions;
    }

    protected HashMap<String, Object> setLogging() {
        String logging = PropertyLoader.getProperty(BrowserstackProperties.BROWSERSTACK_LOGGING);
        HashMap<String, Object> loggingOptions = new HashMap<>();
        if (logging == null) {
            return loggingOptions;
        } else if (logging.equalsIgnoreCase("off")) {
            loggingOptions.put("debug", "false");
            loggingOptions.put("consoleLogs", "disable");
            loggingOptions.put("networkLogs", "false");
            loggingOptions.put("video", "false");
            loggingOptions.put("seleniumLogs", "false");
            loggingOptions.put("appiumLogs", "false");
            loggingOptions.put("maskCommands", "setValues, getValues, setCookies, getCookies");
        } else {
            loggingOptions.put("debug", "true");
            loggingOptions.put("consoleLogs", "error");
            loggingOptions.put("networkLogs", "true");
            loggingOptions.put("video", "true");
            loggingOptions.put("seleniumLogs", "true");
            loggingOptions.put("appiumLogs", "true");
        }
        Log.Info("Setting logging options: " + loggingOptions);
        return loggingOptions;
    }
}
