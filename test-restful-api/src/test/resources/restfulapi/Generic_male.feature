@FunctionalTest1
Feature: Does FHIR API works for known equivalences as expected?
  FHIR API works as expected for gender coding

  Scenario Outline: 'Generic male' coding with FHIR API for known equivalences
    Given the FHIR API responds
    When we are requesting FHIR API code for <equivalence>

    Then the response should be JSON:
      """
      {"resourceType":"Parameters","parameter":[{"name":"result","valueBoolean":true},{"name":"match",
      "part":[{"name":"equivalence","valueCode":"EQUIVALENT"},{"name":"concept",
      "valueCoding":{"system":"http://snomed.info/sct","code":"248153007","display":"Generic male"}}]}]}
      """
    Examples:
      | equivalence  |
      | m            |
      | male         |
      | buck         |