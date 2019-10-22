package resfulapi;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@CucumberOptions(plugin = {"pretty", "html:out"}, features = "src/test/resources/resfulapi",
        snippets= CucumberOptions.SnippetType.CAMELCASE
        //, dryRun=true
)

public class RunRestfulApiTest extends AbstractTestNGCucumberTests {
    public void testRunner() {
        Assert.assertEquals(1, 1);
    }
}
