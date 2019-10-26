package resfulapi;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

public class RestSteps {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Given("The Lambda Integration server is on and FHIRE API responds on \\/metadata request")
    public void theLambdaIntegrationServerIsOnAndFHIREAPIRespondsOnMetadataRequest() throws Throwable {
        try {
            HttpAuthenticationFeature feature =
                    HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
            final Client client = ClientBuilder.newClient(
                    new ClientConfig().register( LoggingFeature.class ).register(feature) );

            WebTarget webTarget = client.target("http://aws-integration-dev.datafusion.idexx.com/fhir/r4" +
                    "/metadata");
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            Response response = invocationBuilder.get();
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                    "Did not receive OK, the response: " + response.getStatus());
            System.out.println(response.readEntity(String.class));

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    @When("We are requesting FHIRE response for code m")
    public void weAreRequestingFHIREResponseForCodeM() {
        try {
            HttpAuthenticationFeature feature =
                    HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
            final Client client = ClientBuilder.newClient(
                    new ClientConfig().register( LoggingFeature.class ).register(feature) );

            WebTarget webTarget = client.target("http://aws-integration-dev.datafusion.idexx.com/fhir/r4" +
                    "/ConceptMap/gender-map/$translate?target=http://snomed.info/sct" + "&code=m");
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            Response response = invocationBuilder.get();
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                    "Did not receive OK, the response: " + response.getStatus());
            System.out.println(response.readEntity(String.class));

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }

    @Then("the response should be JSON:")
    public void theResponseShouldBeJSON(String docString) {
        try {
            HttpAuthenticationFeature feature =
                    HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
            final Client client = ClientBuilder.newClient(
                    new ClientConfig().register( LoggingFeature.class ).register(feature) );

            WebTarget webTarget = client.target("http://aws-integration-dev.datafusion.idexx.com/fhir/r4" +
                    "/ConceptMap/gender-map/$translate?target=http://snomed.info/sct" + "&code=m");
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            Response response = invocationBuilder.get();
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                    "Did not receive OK, the response: " + response.getStatus());
            System.out.println(response.readEntity(String.class));

        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }
}
