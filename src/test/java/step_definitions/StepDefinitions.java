package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import uk.kainos.seleniumframework.driver.DriverManager;
import uk.kainos.seleniumframework.properties.PropertyLoader;
import uk.kainos.seleniumframework.site.Site;
import uk.kainos.seleniumframework.site.pageobjects.StartPage;

public class StepDefinitions {

    WebDriver driver = DriverManager.getDriver();

    StartPage startPage = PageFactory.initElements(driver, StartPage.class);

    @Before
    public void setup() {
        Site.goToURL(PropertyLoader.getProperty("baseUrl"));
    }

    @Given("^the client wants to purchase (.*?)$")
    public StepDefinitions open_start_page() {
        startPage.verifyPageHeader();

        return this;
    }


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            DriverManager.getDriver().manage().window().maximize();
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", String.valueOf(scenario));
        }
        Site.closeWindow();
        DriverManager.clearDriver();
    }
}
