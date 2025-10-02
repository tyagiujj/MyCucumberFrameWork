package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/Customers.feature",
		glue= "StepDefinition",
		dryRun=false,
		monochrome=true,
		tags="@Regression",
		plugin= {"pretty","junit:target/Cucumber-reports/reports1_xml.xml",
				"pretty","html:target/Cucumber-reports/reports1.html",
				"json:target/Cucumber-reports/reports1_json.json"}
		)

//	plugin= {"pretty","html:target/Cucumber-reports/reports1.html
//json:target/Cucumber-reports/reports1_json.json
public class Run {
	
	/*This class will bw Empty*/

}
