Feature: Test department CRUD oeration
  Scenario: Get all department list
    When the client calls the endpoint "/departments"
    Then response status code is 200
    And return the list of department more than 1

  Scenario: :Get department by id
    When the client calls the endpoint to get department "/departments/1"
    Then response status code is 200
    And return the department id 1