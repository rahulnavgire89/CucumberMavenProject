package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/java/Feature/Login.feature",glue={"Definition"}
//,dryRun=false,format={"pretty","html:Output"}
,plugin = {"pretty","html:ExecutionReports/FeaturedReport"}
)
public class Runner {

}
