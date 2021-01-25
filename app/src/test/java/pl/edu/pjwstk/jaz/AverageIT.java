package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {

    private static Response authorizedUserResponse;

    @BeforeClass
    public static void initialize(){
        authorizedUserResponse =
        given()
            .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .thenReturn();
    }

    @Test
    public void when_authorized_user_should_give_access_to_endpoint(){
        given()
            .cookies(authorizedUserResponse.getCookies())
            .get("/api/average")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_unauthorized_user_should_deny_access_to_endpoint(){
        given()
            .when()
                .get("/api/average")
            .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
        .when()
                .get("/api/average")
        .then()
                .body("message", equalTo("Please put parameters."));
        // @formatter:on
    }

    @Test
    public void when_incorrect_parameters_should_print_a_message() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
                .param("numbers", "1!,2")
                .get("/api/average")
        .then()
                .body("message", equalTo("Incorrect parameters."));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero_case_1() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
                .param("numbers", "3,2")
                .get("/api/average")
        .then()
                .body("message", equalTo("Average equals: 2.5"));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero_case_2() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
                .param("numbers", "4,4")
                .get("/api/average")
        .then()
                .body("message", equalTo("Average equals: 4"));
        // @formatter:on
    }

    @Test
    public void should_half_round_up_to_two_decimal_points() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
                .param("numbers", "1,1,3")
                .get("/api/average")
        .then()
                .body("message", equalTo("Average equals: 1.67"));
        // @formatter:on
    }

    @Test
    public void should_half_round_up_away_from_zero() {
        // @formatter:off
        given()
                .cookies(authorizedUserResponse.getCookies())
                .param("numbers", "-1,-1,-3")
                .get("/api/average")
        .then()
                .body("message", equalTo("Average equals: -1.67"));
        // @formatter:on
    }



}
