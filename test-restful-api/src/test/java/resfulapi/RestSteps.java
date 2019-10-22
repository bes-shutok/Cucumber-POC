package resfulapi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.logging.LoggingFeature;
import org.testng.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

public class RestSteps {
    @Given("The Lambda Integration server is on and FHIRE API responds on \\/metadata request")
    public void theLambdaIntegrationServerIsOnAndFHIREAPIIsRespondsOnMetadataRequest() throws Throwable {
        try {
            Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFeature.class ) );
            WebTarget webTarget = client.target("http://aws-integration-dev.datafusion.idexx.com/fhir/r4" +
                    "/ConceptMap/gender-map/$translate?target=http://snomed.info/sct" + "/m");
            Invocation.Builder invocationBuilder =  webTarget.request();
            Response response = invocationBuilder.get();
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
        //Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,  "Did not receive OK response: ");
    }

    @When("We are requesting FHIRE response for code m")
    public void weAreRequestingFHIREResponseForCodeM() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the response should be JSON:")
    public void theResponseShouldBeJSON(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
