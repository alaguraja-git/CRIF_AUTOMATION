package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/com/features"},
		glue = {"stepdefinitions","AppHooks"},
				plugin = {"pretty",				
						"timeline:test-output-thread/"				
				},
		monochrome=true
		)
public class MyTestRunner {

}

