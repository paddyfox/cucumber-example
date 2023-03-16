package pfox.seleniumframework.testDataHelpers;

import io.cucumber.java.Scenario;

import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pfox.seleniumframework.log.Log;

import static pfox.seleniumframework.driver.DriverManager.getDriver;
import static pfox.seleniumframework.environment.Environment.executingInBrowserstack;

public class BrowserStackHelper {

    public static void passTest() {
        Log.Info("TEST PASSED :)");
        execute(action("setSessionStatus", Collections.singletonMap("status", "passed")));
    }

    public static void failTest(String sessionId, String url, Scenario scenario) {
        String path = scenario.getUri().getPath();
        String feature = path.substring(path.lastIndexOf("/") + 1);
        int line = scenario.getLine();
        if (url.contains("error/5")) {
            Log.Error("FAILED DUE TO A HTTP 500 RESPONSE ERROR!!");
        }
        else if (url.contains("error/4")) {
            Log.Error("FAILED DUE TO A HTTP 400 RESPONSE ERROR!!");
        }
        else {
            Log.Error("TEST FAILED!!");
        }
        Log.Error("FAILED SCENARIO NAME: " + scenario.getName() + " - " + feature + " - " + line);
        Log.Error("FAILED URL: " + url);
        Log.Error("FAILED SESSION ID: " + sessionId);
        execute(action("setSessionStatus", Collections.singletonMap("status", "failed")));
    }

    public static void setSessionName(String name) {
        execute(action("setSessionName", Collections.singletonMap("name", name)));
    }

    public static void annotate(String data, String level) {
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("data", data);
        arguments.put("level", level);

        execute(action("annotate", arguments));
    }

    public static void annotateStep(String step) {
        annotate(String.format("Step: %s", step), "info");
    }

    public static void execute(String command) {
        if (executingInBrowserstack()) {
            ((JavascriptExecutor) getDriver()).executeScript(String.format("browserstack_executor: %s", command));
        }
    }

    public static String action(String action, Map<String, String> arguments) {
        JSONObject argumentObject = new JSONObject();
        for (String key : arguments.keySet()) {
            argumentObject.put(key, arguments.get(key));
        }
        return new JSONObject()
                .put("action", action)
                .put("arguments", argumentObject).toString();
    }
}
