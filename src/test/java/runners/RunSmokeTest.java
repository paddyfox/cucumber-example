package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import org.testng.annotations.*;

import java.util.Arrays;
import java.util.function.Predicate;

import pfox.seleniumframework.driver.DriverManager;
import pfox.seleniumframework.environment.Environment;
import pfox.seleniumframework.log.Log;
import pfox.seleniumframework.site.Site;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@smoke",
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber-reports.html"})

public class RunSmokeTest extends AbstractTestNGCucumberTests {

    private static final Predicate<Pickle> isSerial = pickle -> pickle.getTags().contains("@Serial") || pickle.getTags().contains("@serial");

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        if (Environment.executingInBrowserstack()) {
            Log.Info("Executing in BrowserStack");
        } else {
            Log.Info("Executing locally");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
        Site.closeWindow();
        DriverManager.clearDriver();
    }

    @Test(groups = "cucumber parallel", description = "Runs Parallel enabled Scenarios", dataProvider = "parallelScenarios")
    public void runParallelScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        DriverManager.getDriver().manage().deleteAllCookies();
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        DriverManager.clearDriver();
    }

    @Test(groups = "cucumber serial", description = "Runs Scenarios with Serial tags", dataProvider = "serialScenarios")
    public void runSerialScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        DriverManager.getDriver().manage().deleteAllCookies();
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        DriverManager.clearDriver();
    }

    @DataProvider(parallel = true)
    public Object[][] parallelScenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }

        return filter(testNGCucumberRunner.provideScenarios(), isSerial.negate());
    }

    @DataProvider
    public Object[][] serialScenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }

        return filter(testNGCucumberRunner.provideScenarios(), isSerial);
    }

    private Object[][] filter(Object[][] scenarios, Predicate<Pickle> accept) {
        return Arrays.stream(scenarios).filter(objects -> {
            PickleWrapper candidate = (PickleWrapper) objects[0];
            return accept.test(candidate.getPickle());
        }).toArray(Object[][]::new);
    }
}
