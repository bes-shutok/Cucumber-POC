package resfulapi;

import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.testng.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

public class RestSteps {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Response response;
    private String responseString;
    final static String FHIR_API = "http://aws-integration-dev.datafusion.idexx.com/fhir/r4";
    @Given("^the FHIR API responds$")
    public void theLambdaIntegrationServerIsOnAndFHIREAPIRespondsOnMetadataRequest() throws Throwable {
        try {
            HttpAuthenticationFeature feature =
                    HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
            final Client client = ClientBuilder.newClient(
                    new ClientConfig().register( LoggingFeature.class ).register(feature) );

            WebTarget webTarget = client.target(FHIR_API + "/metadata");
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            response = invocationBuilder.get();
            responseString=response.readEntity(String.class);
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                    "Did not receive OK, the response: " + response.getStatus());
            logger.info("Response: " + responseString);

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    @When("^we are requesting FHIR API code for (\\w)")
    public void weAreRequestingFHIRECode(String code) throws Throwable  {
        try {
            HttpAuthenticationFeature feature =
                    HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
            final Client client = ClientBuilder.newClient(
                    new ClientConfig().register( LoggingFeature.class ).register(feature) );

            WebTarget webTarget = client.target(FHIR_API +
                    "/ConceptMap/gender-map/$translate?target=http://snomed.info/sct&code=" + code);
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            response = invocationBuilder.get();
            responseString=response.readEntity(String.class);
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                    "Did not receive OK, the response: " + response.getStatus());
            logger.info("Response: " + responseString);

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    @Then("^the response should be JSON:")
    public void theResponseShouldBeJSON(String jsonExpected) {
        JsonParser parser = new JsonParser();
        Assert.assertEquals(parser.parse(responseString),parser.parse(jsonExpected), "Incorrect JSON representation");
    }

}
