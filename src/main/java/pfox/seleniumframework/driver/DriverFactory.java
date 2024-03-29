package pfox.seleniumframework.driver;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import pfox.seleniumframework.driver.producer.WebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeEmulatedLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeEmulatedRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeEuropeanLocaleRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeLocalNoSandboxWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.chrome.ChromeRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.edge.EdgeLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.edge.EdgeRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.firefox.FirefoxEagerLoadRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.firefox.FirefoxLocalDebugWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.firefox.FirefoxLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.firefox.FirefoxRemoteDebugWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.firefox.FirefoxRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.ie.IELocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.ie.IENativeEventsRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.ie.IERemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.safari.SafariLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.safari.SafariRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.safari.SafariTechPreviewLocalWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.safari.SafariTechPreviewRemoteWebDriverProducer;
import pfox.seleniumframework.driver.producer.desktop.windows.WindowsTenRemoteDriverProducer;
import pfox.seleniumframework.driver.producer.mobile.appium.AppiumMobileWebDriverProducer;
import pfox.seleniumframework.log.Log;

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
