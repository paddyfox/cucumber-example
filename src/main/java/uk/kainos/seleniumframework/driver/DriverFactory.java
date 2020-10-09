package uk.kainos.seleniumframework.driver;

import org.openqa.selenium.WebDriver;

import uk.kainos.seleniumframework.driver.producer.WebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeEmulatedLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeEmulatedRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeEuropeanLocaleRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeLocalNoSandboxWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.chrome.ChromeRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.edge.EdgeLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.edge.EdgeRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.firefox.FirefoxEagerLoadRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.firefox.FirefoxLocalDebugWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.firefox.FirefoxLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.firefox.FirefoxRemoteDebugWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.firefox.FirefoxRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.ie.IELocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.ie.IENativeEventsRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.ie.IERemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.opera.OperaLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.opera.OperaRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.safari.SafariLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.safari.SafariRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.safari.SafariTechPreviewLocalWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.safari.SafariTechPreviewRemoteWebDriverProducer;
import uk.kainos.seleniumframework.driver.producer.desktop.windows.WindowsTenRemoteDriverProducer;
import uk.kainos.seleniumframework.driver.producer.mobile.appium.AppiumMobileWebDriverProducer;
import uk.kainos.seleniumframework.log.Log;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Map<BrowserType, WebDriverProducer> producers = new HashMap<>();

    static {
        producers.put(BrowserType.ChromeLocal, new ChromeLocalWebDriverProducer());
        producers.put(BrowserType.ChromeLocalNoSandbox, new ChromeLocalNoSandboxWebDriverProducer());
        producers.put(BrowserType.ChromeRemote, new ChromeRemoteWebDriverProducer());
        producers.put(BrowserType.ChromeMobileEmulationLocal, new ChromeEmulatedLocalWebDriverProducer());
        producers.put(BrowserType.ChromeMobileEmulationRemote, new ChromeEmulatedRemoteWebDriverProducer());
        producers.put(BrowserType.ChromeEuropeanLocaleRemote, new ChromeEuropeanLocaleRemoteWebDriverProducer());
        producers.put(BrowserType.AppiumWindowsTenRemote, new WindowsTenRemoteDriverProducer());
        producers.put(BrowserType.AppiumMobile, new AppiumMobileWebDriverProducer());
        producers.put(BrowserType.EdgeLocal, new EdgeLocalWebDriverProducer());
        producers.put(BrowserType.EdgeRemote, new EdgeRemoteWebDriverProducer());
        producers.put(BrowserType.FirefoxLocal, new FirefoxLocalWebDriverProducer());
        producers.put(BrowserType.FirefoxLocalDebug, new FirefoxLocalDebugWebDriverProducer());
        producers.put(BrowserType.FirefoxRemote, new FirefoxRemoteWebDriverProducer());
        producers.put(BrowserType.FirefoxEagerLoadRemote, new FirefoxEagerLoadRemoteWebDriverProducer());
        producers.put(BrowserType.FirefoxRemoteDebug, new FirefoxRemoteDebugWebDriverProducer());
        producers.put(BrowserType.InternetExplorerLocal, new IELocalWebDriverProducer());
        producers.put(BrowserType.InternetExplorerNativeEventsRemote, new IENativeEventsRemoteWebDriverProducer());
        producers.put(BrowserType.InternetExplorerRemote, new IERemoteWebDriverProducer());
        producers.put(BrowserType.OperaLocal, new OperaLocalWebDriverProducer());
        producers.put(BrowserType.OperaRemote, new OperaRemoteWebDriverProducer());
        producers.put(BrowserType.SafariLocal, new SafariLocalWebDriverProducer());
        producers.put(BrowserType.SafariRemote, new SafariRemoteWebDriverProducer());
        producers.put(BrowserType.SafariTechPreviewLocal, new SafariTechPreviewLocalWebDriverProducer());
        producers.put(BrowserType.SafariTechPreviewRemote, new SafariTechPreviewRemoteWebDriverProducer());
    }

    private final BrowserType browserType;

    DriverFactory(String browserTypeName) {
        browserType = BrowserType.valueOf(browserTypeName);
        Log.Info("Browser type: " + browserType + " has been set in the Driver Factory on thread " + Thread.currentThread().getName());
    }

    WebDriver createInstance() {
        return producers.get(browserType).produce();
    }
}
