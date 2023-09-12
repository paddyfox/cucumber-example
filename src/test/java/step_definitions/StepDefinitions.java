package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;

import pfox.seleniumframework.driver.DriverManager;
import pfox.seleniumframework.environment.Environment;
import pfox.seleniumframework.log.Log;
import pfox.seleniumframework.site.Site;
import pfox.seleniumframework.site.pageobjects.AmazonHomePage;
import pfox.seleniumframework.testDataHelpers.BrowserStackHelper;

import static org.testng.AssertJUnit.assertTrue;

import static pfox.seleniumframework.driver.DriverManager.getDriver;

public class StepDefinitions extends Site {

    WebDriver driver = getDriver();
    AmazonHomePage amazonHomePage = PageFactory.initElements(driver, AmazonHomePage.class);

    @Before
    public void setup(Scenario scenario) {
        DriverManager.getDriver().manage().deleteAllCookies();
        if (Environment.executingInBrowserstack()) {
            Log.Info("SESSION ID: " + getBrowserstackSessionID());
            DriverManager.getDriver().manage().window().maximize();

            String path = scenario.getUri().getPath();
            String feature = path.substring(path.lastIndexOf("/") + 1);
            int line = scenario.getLine();

            BrowserStackHelper.setSessionName(String.format("%s - %s:%s", scenario.getName(), feature, line));
        }
    }

    @After
    public void clearDown(Scenario scenario) {
        if (scenario.isFailed() || scenario.getStatus().name().contains("UNDEFINED")) {
            Log.Error("TEST FAILED!!");
            final byte [] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", String.valueOf(scenario) + UUID.randomUUID());
            if (Environment.executingInBrowserstack()) {
                Log.Error("URL FOR FAILED TEST WAS: " + getDriver().getCurrentUrl());
                ((JavascriptExecutor) getDriver()).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\"}}");
            }
        } else {
            Log.Info("TEST PASSED :)");
            if (Environment.executingInBrowserstack()) {
                ((JavascriptExecutor) getDriver()).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
            }
        }
        Site.closeWindow();
        DriverManager.clearDriver();
    }

    @Given("the customer goes shopping on {string}")
    public StepDefinitions open_start_page(String website) {
        driver.manage().deleteAllCookies();
        Site.goToURL("https://" + website);

        switch (website) {
            case "www.amazon.co.uk":
                amazonHomePage.amazonWebsiteIsDisplayed();
                amazonHomePage.acceptCookies();
                break;
            case "www.currys.co.uk":
                break;
            default:
                throw new IllegalArgumentException("No behaviour defined for: " + website);
        }
        return this;
    }

    @When("they search for item: {string} on {string}")
    public StepDefinitions search_for_item_on_website(String item, String websiteName) {
        if (websiteName.contains("amazon")) {
            amazonHomePage.searchForItem(item);
            amazonHomePage.clickSearch();

            assertTrue("Unable to find " + item + " in list: " + amazonHomePage.getAllSearchResults(), amazonHomePage.getAllSearchResults().contains(item));
        }
        else if (websiteName.contains("currys")) {
            //TODO: Do stuff here
        }

        return this;
    }

    @And("if it is less than their maximum budget of {float}")
    public StepDefinitions less_than_budget(float amount) {
        //TODO: Do stuff here
        return null;
    }
}
