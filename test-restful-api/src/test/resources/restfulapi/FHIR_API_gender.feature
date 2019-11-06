@FunctionalTest
Feature: Does FHIR API works for known equivalences as expected?
  FHIR API works as expected for gender coding


  Scenario Outline: Gender coding with FHIR API for known equivalences
    Given the FHIR API responds
    When we are requesting FHIR API coding for: <equivalence>
    Then the JSON response should contain correct code <code> and display <display>
    Examples:
      | equivalence    | code             | display          |
      | m              | 248153007        | "Generic male"   |
      | male           | 248153007        | "Generic male"   |
      | buck           | 248153007        | "Generic male"   |
      | f              | 248152002        | "Generic female" |
      | chick          | 27701000009108   | "Intact female"  |
      | m c            | 354541000009105  | "Castrated male" |


  Scenario Outline: Negative testing for FHIR API
    Given the FHIR API responds
    When we are requesting FHIR API coding for: <equivalence>
    Then the JSON response confirms that the mapping result (valueBoolean) = <resultExpected>
    Examples:
      | equivalence    | resultExpected |
      | zzz            | false          |
      | noSuchCode     | false          |
      | m              | true           |