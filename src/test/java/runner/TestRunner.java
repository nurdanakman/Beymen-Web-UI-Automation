package runner;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/java/features",
        glue = {"utility", "stepDefinition"},
        plugin = {"pretty"}
)
public class TestRunner {

}
