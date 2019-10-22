Feature: Does FHIRE API works as expected?
  FHIRE API works as expected for gender coding

  Scenario: Gender coding with FHIRE API
    Given The Lambda Integration server is on and FHIRE API responds on /metadata request
    When We are requesting FHIRE response for code m
    Then the response should be JSON:
      """
      {"resourceType":"Parameters","parameter":[{"name":"result","valueBoolean":true},{"name":"match",
      "part":[{"name":"equivalence","valueCode":"EQUIVALENT"},{"name":"concept",
      "valueCoding":{"system":"http://snomed.info/sct","code":"248153007","display":"Generic male"}}]}]}
      """
