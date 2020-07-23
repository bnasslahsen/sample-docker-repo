package fr.training.samples.spring.shop.domain.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, monochrome = true, glue = "fr.training.samples.spring.shop.domain.account.steps", features = "src/test/resources/features/account.feature", plugin = {
		"pretty", "html:target/cucumber", "json:target/cucumber/cucumber.json" })
public class CucumberTest {

}
