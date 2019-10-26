import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@CucumberOptions(plugin = {"pretty", "html:out"}, features = "src/test/resources/restfulapi",
        glue={"restfulapi"}, snippets= CucumberOptions.SnippetType.CAMELCASE
        ,tags={"@FunctionalTest1, @FunctionalTest2"}
        //,tags={"not @FunctionalTest"}
        //, dryRun=true
)

public class RunRestfulApiTest extends AbstractTestNGCucumberTests {
    public void testRunner() {
        Assert.assertEquals(1, 1);
    }
}
