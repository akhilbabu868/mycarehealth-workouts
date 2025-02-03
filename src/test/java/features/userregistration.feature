Feature: User Registration

  Scenario: Successful user registration
    When the user sends a POST request to the \/register endpoint with the following details:
      | email    | asokkk112@gmail.com |
      | password | asokkk112           |
      | fullName | asokkk112 aajesh    |
      | gender   | MALE                |
    Then the  response should have status code 201
    And the response should contain a JWT token

