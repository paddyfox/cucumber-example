# cucumber-example
This project is a Cucumber Selenium UI automation test example framework for teaching automated testing. 
It is written in Java with Cucumber BDD, TestNG and produces reports of test results.

## Getting Started
Have the Cucumber & TestNG plugin installed in IntelliJ.
Have Java 11 or newer installed.

## Tasks
1. Implement the step definitions to get the smoke test feature working.
2. Execute the tests against another browser other than Chrome.
3. Get the four smoke tests to run all at once in parallel.
4. Create new smoke tests of your own choosing to extend the suite eg purchasing clothing.

### Run tests locally
Right click the feature file and select "Run" or "Debug" to start the test.

### Run tests through the commandline
To run the test, use your CI or point Maven to the project and use the goals:
```
mvn clean test -P smoke
```

To run a specific test, use the Cucumber filter:
```
mvn clean test -Dcucumber.filter.tags=@smk1
```

### Run tests in parallel
By default, all tests run in parallel but if you wish to run a test in series mode then tag the test or scenario with the annotation: `@serial`  
To change the amount of tests which run in parallel, change the value of `data-provider-thread-count="5"` in the testng.xml file i.e. `regression.xml`

##### Appium
When writing tests using this framework, you can easily leverage these tests to run against an Appium grid with the following settings:

| Browser type                | System property value              |
|:----------------------------|:-----------------------------------|
| Appium Mobile               | AppiumMobile                       |

You can set Appium properties using the following to create a request (for capability values information, see http://appium.io/docs/en/writing-running-appium/caps/) :
```
- appium.browser.name
- appium.device.name
- appium.platform.name
- appium.os.version
```

There is a special pre-set Appium driver for WinAppDriver that can be used for Windows desktop testing by using:

| Browser type                | System property value              |
|:----------------------------|:-----------------------------------|
|Windows Driver (WinAppDriver)| AppiumWindowsTenRemote             |

For more Appium driver capabilities information, see: http://appium.io/docs/en/writing-running-appium/caps/

##### Executing tests on a Selenium Grid
To point your tests to a Selenium grid, at runtime or through VM options in your IDE, pass the following property:

```
-Dselenium.grid.url=http://localhost:4444/wd/hub
```

## Grid Test Execution
When running tests on a remote grid, you must specify a "Remote" type browser, e.g. ChromeRemote:

```
-Dbrowser.type=ChromeRemote
```

##### Executing test on Browserstack
To point your tests to Browserstack at runtime or through VM options in your IDE, pass the following properties:

```
-Dbrowserstack.username=TESTUSER
-Dbrowserstack.access.key=KEY123!
```

To override Browserstack logging levels from their default to turning all logs off, pass the following property:

```
-Dbrowserstack.logging=OFF
```

To override Browserstack logging levels from their default to turning all logs on and at error level, pass the following property:

```
-Dbrowserstack.logging=ON
```

##  Properties
Properties are retrieved by default from a file called **dev1.properties**, which should be located under:

src > main > resources > properties

The properties declared here can be overriden, however, if specified as a system property at runtime.

For example, if you declare the following property in your **dev1.properties** file:

```
browser.type=FirefoxLocal
```

You also pass the following at runtime:

```
-Dbrowser.type=EdgeLocal
```

##  Logging
Logging can be easily added to tests by using the static methods from the Log class.

Available logging levels are:

|Logging Level|Method                                       |
|:------------|:--------------------------------------------|
|Info         |Log.Info("This is an info level message");   |
|Warn         |Log.Warn("This is a warning level message"); |
|Error        |Log.Info("This is an error level message");  |
|Debug        |Log.Info("This is a debug level message");   |

Cucumber reports can be published to Cucumber online for viewing upto 24 hours later at which they will be destroyed.
To enable cucumber reports change `cucumber.publish.enabled=false` to `cucumber.publish.enabled=true` in src/test/resources/cucumber.properties.


## Built With
* [Selenium](https://github.com/SeleniumHQ/selenium) - Browser automation framework
* [Maven](https://maven.apache.org/) - Dependency management
* [TestNG](https://github.com/cbeust/testng) - Testing framework
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) - Local webdriver binary management

## Some Selenium best practices:
* Don't use Selenium to cover tests which are better placed at unit or integration level 
* Be familiar with and use the ageObjects pattern
* Preferred web element selector order preference: id > name > css > xpath 
* Avoid Thread.sleep prefer explicit waits such as: FluentWait or WebDriverWait
* Use relative URLs
