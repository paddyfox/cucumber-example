package pfox.seleniumframework.site;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import pfox.seleniumframework.driver.DriverManager;
import pfox.seleniumframework.log.Log;
import pfox.seleniumframework.properties.CommonProperties;
import pfox.seleniumframework.properties.PropertyLoader;

import static pfox.seleniumframework.driver.DriverManager.getDriver;

public class Site {

    private static final long DEFAULT_WAIT_TIMEOUT = 15;

    public static WebDriverWait webDriverWait(Duration timeoutInSeconds) {

        return new WebDriverWait(getDriver(), timeoutInSeconds);
    }

    public static WebDriverWait webDriverWait() {

        Duration timeoutInSeconds = Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT);

        String timeoutProperty = PropertyLoader.getProperty(CommonProperties.SELENIUM_DRIVER_WAIT_TIMEOUT);

        if (timeoutProperty != null) {
            try {
                timeoutInSeconds = Duration.parse(timeoutProperty);
            } catch (NumberFormatException e) {
                throw new SiteCreationException("The format of " + timeoutProperty + " for the driver_wait_timeout property is invalid", e);
            }
        }

        return webDriverWait(timeoutInSeconds);
    }

    public static void verifyPageTitle(String expected) {
        waitForH1ToAppear();
        int tries = 40;
        while (tries > 0) {
            try {
                String actualTitle = findElement(By.tagName("h1")).getText();
                if (actualTitle.contains(expected)) {
                    Log.Info("Page loaded: " + actualTitle);
                    break;
                } else {
                    Log.Debug("Expected Page: " + expected + ", But loaded page: " + actualTitle + ". " + tries + " tries remaining");
                    tries--;
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                Log.Debug("Unable to find page title");
            }
        }
        if (tries <= 0) {
            throw new NotFoundException("ERROR: Page title did not match, expected: " + expected + " but got: " + findElement(By.tagName("h1")).getText());
        }
    }

    public static void waitForH1ToAppear() {
        int tries = 70;
        while (tries > 0) {
            try {
                tries --;
                findElement(By.tagName("h1"));
                break;
            } catch (StaleElementReferenceException ex) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static WebElement findElement(By by) {
        return getDriver().findElement(by);
    }

    public static void waitForElementToAppear(WebElement webElement, int seconds) {
        webDriverWait(Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static boolean verifyPageTitle(String actual, String expected) {
        if (actual.contains(expected)) {
            Log.Info("Page loaded: " + actual);

            return true;
        }

        throw new NotFoundException("Page title did not match: " + expected + " but got: " + actual);
    }

    public static void goToURL(String webAddress) {
        getDriver().get(webAddress);
    }

    public static void closeWindow() {
        getDriver().close();
    }

    public static void switchFocusToNewTab() throws InterruptedException {
        String currentWindowHandle = DriverManager.getDriver().getWindowHandle();
        Thread.sleep(1000);
        Log.Debug("Current handle: {}" + currentWindowHandle);
        Set<String> s = DriverManager.getDriver().getWindowHandles();

        int n = s.size();
        String[] arr = new String[n];

        int i = 0;
        for (String x : s)
            arr[i++] = x;

        for (String item : arr) {
            if (!item.equals(currentWindowHandle)) {
                Log.Debug("Switching to: {}" + item);
                DriverManager.getDriver().switchTo().window(item);
                break;
            }
        }
    }

    public void switchFocusToPreviousTab() {
        ArrayList<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());

        getDriver().switchTo().window(windowHandles.get(0));
    }

    public String getBrowserstackSessionID() {
        Object response = ((JavascriptExecutor) getDriver()).executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
        JsonObject jsonObject = JsonParser.parseString((String) response).getAsJsonObject();
        return String.valueOf(jsonObject.get("hashed_id"));
    }
}
