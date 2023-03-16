package helpers;

import com.browserstack.local.Local;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import pfox.seleniumframework.environment.Proxy;
import pfox.seleniumframework.log.Log;
import pfox.seleniumframework.properties.PropertyLoader;

import static pfox.seleniumframework.environment.Environment.executingInJenkins;
import static pfox.seleniumframework.properties.BrowserstackProperties.BROWSERSTACK_ACCESS_KEY;

public class BrowserStack {

    private static Local bsLocal;

    public static void browserstackSetUp() {
        String localIdentifier = String.valueOf(UUID.randomUUID());
        System.setProperty("localIdentifier", localIdentifier);

        bsLocal = new Local();
        Map<String, String> bsLocalArgs = new HashMap<String, String>();
        if (executingInJenkins()) {
            Log.Info("BrowserStack set up for jenkins");
            bsLocalArgs.put("key", System.getenv("bsPassword"));
            System.getProperties().put("http.nonProxyHosts", "localhost|127.0.0.1");
        } else {
            Log.Info("BrowserStack set up for laptop");
            bsLocalArgs.put("key", PropertyLoader.getProperty(BROWSERSTACK_ACCESS_KEY));
            bsLocalArgs.put("forceproxy", "true");
        }
        System.getProperties().put("https.proxyHost", Proxy.getProxyName());
        System.getProperties().put("https.proxyPort", Proxy.getProxyPort());
        bsLocalArgs.put("proxyHost", Proxy.getProxyName());
        bsLocalArgs.put("proxyPort", Proxy.getProxyPort());
        bsLocalArgs.put("force", "true");
        bsLocalArgs.put("forcelocal", "true");
        bsLocalArgs.put("localIdentifier", localIdentifier);

        try {
            if (bsLocal.isRunning()) {
                Log.Info("Browserstack local is already running...");
                Log.Info("Browserstack running..." + bsLocal.isRunning());
            } else {
                Log.Info("Attempting to start Browserstack local instance with following arg: " + bsLocalArgs);
                bsLocal.start(bsLocalArgs);
                Log.Info("Browserstack local running: " + bsLocal.isRunning());
            }
        } catch (Exception e) {
            Log.Error("Unable to start local Browserstack instance");
            throw new RuntimeException("Unable to start Browserstack local instance: " + e.getMessage());
        }
    }

    public static void browserstackTearDown() throws Exception {
        if (bsLocal != null) {
            try {
                Log.Info("Attempting to stop Browserstack local instance...");
                bsLocal.stop();
            } catch (Exception e) {
                throw new RuntimeException("Unable to stop Browserstack local instance: " + e.getMessage());
            }
            Log.Info("Browserstack local running: " + bsLocal.isRunning());
        }
    }
}
