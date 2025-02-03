package stepDefinitions;


import com.matchmaker.user_mgt.model.dto.AuthenticationRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class loginStepsDefinition {

    private Response response;

    private static final String BASE_URI = "http://localhost:8081/api/v1";

    @When("the user sends a POST request to the \\\\\\/login endpoint with the following credentials:")
    public void the_user_sends_a_post_request_to_the_login_endpoint_with_the_following_credentials(Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Sending POST request to the /login endpoint
        response = given()
                .contentType("application/json")
                .body(new AuthenticationRequest(username, password)) // AuthenticationRequest is the POJO class
                .when()
                .post(BASE_URI + "/auth/login");
    }


    @Then("the response should have status code {int}")
    public void the_response_should_have_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a token")
    public void the_response_should_contain_a_token() {
        response.then().body("token", notNullValue());
    }

}
