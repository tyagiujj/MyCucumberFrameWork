package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features", // Updated to run all .feature files in Features folder (includes both Customers.feature and Login.feature)
		glue= "StepDefinition",
		dryRun=false,
		monochrome=true,
		tags="", // Removed "@Sanity" to run all scenarios (fix for no tests executing)
		plugin= {"pretty","junit:target/Cucumber-reports/reports_xml.xml", // Removed duplicate "pretty" and fixed paths
				"html:target/Cucumber-reports/reports_html.html",
				"json:target/Cucumber-reports/reports_json.json"}
		)

public class Run {
	
	/*This class will be Empty*/

}