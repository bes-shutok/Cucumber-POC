package restfulapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.Assert;

import static com.google.gson.JsonParser.parseString;



final class Utils {

    /*
    * Parsing responses of this type
    * {"resourceType":"Parameters","parameter":[{"name":"result","valueBoolean":true}, {"name":"match",
    * "part":[{"name":"equivalence","valueCode":"EQUIVALENT"},{"name":"concept",
    * "valueCoding":{"system":"http://snomed.info/sct","code":"248153007","display":"Generic male"}}]}]}
    * */
    void validateFhirResponse(JsonElement jsonResponse, String display) {
        JsonArray parameter = jsonResponse.getAsJsonObject().getAsJsonArray("parameter");
        JsonArray part = parameter.get(1).getAsJsonObject().getAsJsonArray("part");
        JsonObject valueCoding = part.get(1).getAsJsonObject().getAsJsonObject("valueCoding");
        String displayActual = valueCoding.getAsJsonPrimitive("display").getAsString();

        // Assert that the actual data is equal to received data
        Assert.assertEquals(displayActual, display, "Actual display value {" + displayActual +
                "} is not equal to expected display value {" + display + "}.");
    }

    void validateFhirResponse(JsonElement jsonResponse, Long code,  String display) {
        JsonArray parameter = jsonResponse.getAsJsonObject().getAsJsonArray("parameter");
        JsonArray part = parameter.get(1).getAsJsonObject().getAsJsonArray("part");
        JsonObject valueCoding = part.get(1).getAsJsonObject().getAsJsonObject("valueCoding");
        Long codeActual = valueCoding.getAsJsonPrimitive("code").getAsLong();
        String displayActual = valueCoding.getAsJsonPrimitive("display").getAsString();

        // Assert that the actual data is equal to received data
        Assert.assertEquals(codeActual, code,"Actual code value {" + codeActual +
                        "} is not equal to expected code value {" + code + "}.");
        Assert.assertEquals(displayActual, display, "Actual display value {" + displayActual +
                        "} is not equal to expected display value {" + display + "}.");
    }
}
