# Selenium-Java Framework

A Maven framework in which to build Selenium tests written in Java with ?? reports of test results.

## Getting Started

Copy the repo into your local machine.

### Run tests locally

Right click the feature file and select "Run" or "Debug" to start the test.

### Run tests through the commandline

As this project uses Maven, we can invoke the tests using Maven goals.

To run the test, use your CI or point Maven to the project and use the goals:

```
mvn clean install
```

## Grid Test Execution

When running tests on a remote grid, you must specify a "Remote" type browser, e.g. ChromeRemote:

```
-Dbrowser.type=ChromeRemote
```

##### Executing tests on a Selenium Grid

To point your tests to a Selenium grid, at runtime or through VM options in your IDE, pass the following property:

```
-Dselenium.grid.url=http://localhost:4444/wd/hub
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

Properties are retrieved by default from a file called **test-automation.properties**, which should be located under:

src > main > resources > test-automation.properties

The properties declared here can be overriden, however, if specified as a system property at runtime.

For example, if you declare the following property in your **test-automation.properties** file:

```
browser.type=FirefoxLocal
```

And you also pass the following at runtime:

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


## Built With

* [Selenium](https://github.com/SeleniumHQ/selenium) - Browser automation framework
* [Maven](https://maven.apache.org/) - Dependency management
* [TestNG](https://github.com/cbeust/testng) - Testing framework
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) - Local driver binary management
