package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features="src/test/java/feature/firstTest.feature",
        glue= {"StepDefinitions"},
        plugin = {"pretty",
                "testng:build/cucumber-reports/cucumber.xml",
        },
        monochrome = true
)

public class Run extends AbstractTestNGCucumberTests {
}
