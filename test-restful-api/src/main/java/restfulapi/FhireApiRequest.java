package restfulapi;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

class FhireApiRequest {
    final static String FHIR_API = "http://aws-integration-dev.datafusion.idexx.com/fhir/r4";

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final HttpAuthenticationFeature feature =
            HttpAuthenticationFeature.basic("vcp_dev", "y6s3fmZR4j");
    private final Client client = ClientBuilder.newClient(
            new ClientConfig().register( LoggingFeature.class ).register(feature) );

    private Response response;

    Response fhirApiRequest(String query)  throws Throwable {
        try {
            WebTarget webTarget = client.target(FHIR_API + query);
            logger.info("Trying to invoke the " + webTarget.toString());
            Invocation.Builder invocationBuilder =  webTarget.request("application/json+fhir");
            response = invocationBuilder.get();
        } catch (RuntimeException r) {
            throw r;
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }
        return response;
    }
}
