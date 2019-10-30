@FunctionalTest
Feature: Does FHIR API works as expected?
  FHIR API works as expected for gender coding


  Scenario Outline: Gender coding with FHIR API
    Given the FHIR API responds
    When we are requesting FHIR API code for <equivalence>

    Then the JSON response should contain correct values for <code> and <display>
    Examples:
      | equivalence  | code      | display          |
      | m            | 248153007 | "Generic male"   |
      | male         | 248153007 | "Generic male"   |
      | buck         | 248153007 | "Generic male"   |
      | f            | 248152002 | "Generic female" |