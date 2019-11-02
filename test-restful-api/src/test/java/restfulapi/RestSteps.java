package restfulapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class RestSteps {
    private static final String CODE_FOR = "/ConceptMap/gender-map/$translate?target=http://snomed.info/sct&code=";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final FhireApiRequest request = new FhireApiRequest();
    private String responseString;
    private Response response;
    private JsonElement jsonResponse;

    @Given("the FHIR API responds")
    public void theFhirApiResponds() throws Throwable  {

        // Sending get request for metadata
        response = request.fhirApiRequest("metadata");

        // Confirming that the request was sent successfully
        Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                "Did not receive OK, the response: " + response.getStatus());

        // Logging step
        logger.info("Response: " + response.readEntity(String.class));
    }

    @When("we are requesting FHIR API code for ([a-zA-Z_ ]+$)")
    public void weAreRequestingFhirApiCode(String code) throws Throwable  {

        // Sending get request for the FHIR API coding
        response = request.fhirApiRequest(CODE_FOR + URLEncoder.encode(code, "UTF-8"));

        // Confirming that the request was sent successfully
        Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,
                "Did not receive OK, the response: " + response.getStatus());

        // Saving the result for later check
        responseString=response.readEntity(String.class);

        jsonResponse = JsonParser.parseString(responseString);
        // should be a JSON object
        Assert.assertTrue(jsonResponse.isJsonObject());

        // Logging step
        logger.info("Response: " + responseString);
    }

    @Then("the response should be JSON:")
    public void theResponseShouldBeJson(String expectedResponse) {
        JsonElement jsonExpected = JsonParser.parseString(expectedResponse);

        // should be a JSON object
        Assert.assertTrue(jsonExpected.isJsonObject());
        logger.info("The JSON" + jsonResponse);

        // Confirming that the response JSON is the same as expected
        Assert.assertEquals(jsonResponse,jsonExpected, "Incorrect JSON representation");
    }

    @Then("the JSON response should contain correct values for {long} and {string}")
    public void theJSONResponseShouldContainCorrectValues(Long codeValue, String displayValue) {

        Utils utils = new Utils();
        utils.validateFhirResponse(jsonResponse, codeValue, displayValue);

        // Write code here that turns the phrase above into concrete actions
        System.out.println("Received code '" + codeValue + "' and display '" + displayValue + "'.");


    }
}
