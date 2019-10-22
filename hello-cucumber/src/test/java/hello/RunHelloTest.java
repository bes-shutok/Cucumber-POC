package hello;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@CucumberOptions(plugin = {"pretty", "html:out"}, features = "src/test/resources/hello",
        snippets= CucumberOptions.SnippetType.CAMELCASE
        //, dryRun=true
)

public class RunHelloTest extends AbstractTestNGCucumberTests {
    public void testRunner() {
        Assert.assertEquals(1, 1);
    }
}
