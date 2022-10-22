package kz.akvelon.report;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/shopkzReport.feature",
        glue = {"kz.akvelon.stepDefinitions"},
        plugin = {"pretty", "html:target/index.html", "json:target/report/cucumber.json"},
        monochrome = true,
        tags = "@shop.kz"
)


public class RunTest {
}
