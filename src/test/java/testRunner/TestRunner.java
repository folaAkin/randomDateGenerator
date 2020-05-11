package testRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        tags = {}, monochrome = true,
        plugin = {
                "html:reports/site/cucumber-pretty",
                "json:reports/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:reports/cucumber-reports/report.html"},
        glue = {"com.football.index.tests.dategenerator"})


public class TestRunner {
}
