package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import uk.kainos.seleniumframework.driver.DriverManager;
import uk.kainos.seleniumframework.environment.Environment;
import uk.kainos.seleniumframework.log.Log;
import uk.kainos.seleniumframework.site.Site;
import uk.kainos.seleniumframework.site.pageobjects.AmazonHomePage;

import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

import static uk.kainos.seleniumframework.driver.DriverManager.getDriver;

public class StepDefinitions {

    WebDriver driver = getDriver();
    AmazonHomePage amazonHomePage = PageFactory.initElements(driver, AmazonHomePage.class);

    @After
    public void clearDown(Scenario scenario) {
        if (scenario.isFailed() || scenario.getStatus().name().contains("UNDEFINED")) {
            Log.Error("TEST FAILED!!");
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", String.valueOf(scenario) + UUID.randomUUID());
            if (Environment.executingInBrowserstack()) {
                Log.Error("URL FOR FAILED TEST WAS: " + getDriver().getCurrentUrl());
                ((JavascriptExecutor) getDriver()).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\"}}");
            }
        }
        else {
            Log.Info("TEST PASSED :)");
            if (Environment.executingInBrowserstack()) {
                ((JavascriptExecutor) getDriver()).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
            }
        }
        Site.closeWindow();
        DriverManager.clearDriver();
    }

    @Given("the client opens {string}")
    public StepDefinitions open_start_page(String website) {
        Site.goToURL("https://" + website);

        switch (website) {
            case "www.amazon.co.uk":
                amazonHomePage.amazonWebsiteIsDisplayed();
                break;
            case "www.currys.co.uk":
                //TODO: Do stuff here
            default:
                throw new IllegalArgumentException("No behaviour defined for: " + website);
        }
        return this;
    }

    @When("they search for item: {string} on {string}")
    public StepDefinitions search_for_item_on_website(String item, String websiteName) {
        item = getItemName(item);

        if (websiteName.contains("amazon")) {
            amazonHomePage.searchForItem(getItemName(item));
            amazonHomePage.clickSearch();

            assertTrue("Unable to find " + item + "in list: " + amazonHomePage.getAllSearchResults(),
                    amazonHomePage.getAllSearchResults().contains(item));
            amazonHomePage.selectFirstSearchResult();
        }
        else if (websiteName.contains("currys")) {
            //TODO: Do stuff here
        }

        return this;
    }

    @And("it is less than their maximum budget of {float}")
    public StepDefinitions less_than_budget(float amount) {
        //TODO: Do stuff here
        return null;
    }

    private String getItemName(String item) {
        if ("The Doggfather CD album by Snoop Dogg".equalsIgnoreCase(item)) {
            item = "Tha Doggfather";
        }
        //TODO: Do stuff here
        return item;
    }
}
