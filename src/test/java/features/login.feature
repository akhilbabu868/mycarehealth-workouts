Feature: User Login

    Scenario: Successful login
        When the user sends a POST request to the \/login endpoint with the following credentials:
            | username  | test123@gmail.com |
            | password  | test123@ |
        Then the response should have status code 200
        And the response should contain a token
