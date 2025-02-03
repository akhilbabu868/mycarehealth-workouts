package stepDefinitions;

import com.matchmaker.user_mgt.model.Gender;
import com.matchmaker.user_mgt.model.dto.UserRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Before;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class userRegistrationStepDefinition {


    private static final String BASE_URI = "http://localhost:8081/api/v1"; // Update with the actual base URI
    private Response response;

    @Before
    public void setup() {
        // Registering the parser for text/plain content
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @When("the user sends a POST request to the \\\\\\/register endpoint with the following details:")
    public void the_user_sends_a_post_request_to_the_register_endpoint_with_the_following_details(DataTable dataTable) {
        // Create request payload from the feature file details
        Map<String, String> userDetails = dataTable.asMap(String.class, String.class);

        String email = userDetails.get("email");
        String password = userDetails.get("password");
        String fullName = userDetails.get("fullName");
        String gender = userDetails.get("gender");

        Gender genderEnum = Gender.valueOf(gender);
        // Sending POST request to the /register endpoint with user details
        response = given()
                .contentType("application/json")
                .body(new UserRequest(email, password, fullName, genderEnum)) // UserRequest is a POJO class
                .when()
                .post(BASE_URI + "/auth/register");

        String token = response.getBody().asString();

    }

    @Then("the  response should have status code {int}")
    public void the_registration_response_should_have_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a JWT token")
    public void the_response_should_contain_a_jwt_token() {

        String token = response.getBody().asString();
        assertThat("JWT token should not be empty", token, not(""));
    }
}
