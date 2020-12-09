package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.requests.LoginRequest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class LoginTest {
    // SPRING
    @Test
    public void when_correct_credentials_should_login_successfully_with_status_code_200_OK(){
        given()
            .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_incorrect_credentials_should_not_login_with_status_code_409_CONFLICT(){
        given()
            .when()
                .body(new LoginRequest(":XO:", ":XO:"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void when_incorrect_password_should_not_login_with_status_code_409_CONFLICT(){
        given()
            .when()
                .body(new LoginRequest("admin", "wrongPassword"))
                .contentType(ContentType.JSON)
                .post("api/login")
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void when_incorrect_username_should_not_login_with_status_code_409_CONFLICT(){
        given()
                .when()
                .body(new LoginRequest(":O:O", "admin"))
                .contentType(ContentType.JSON)
                .post("api/login")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void when_correct_credentials_should_give_access_to_average_endpoint_with_status_code_200_OK(){
        var response = given()
                .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/average")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    //AUTH0
    @Test
    public void AUTH0_when_correct_credentials_should_login_successfully_with_status_code_400_BAD_REQUEST(){
        given()
                .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/auth0/login")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void AUTH0_when_incorrect_credentials_should_not_login_with_status_code_409_CONFLICT(){
        given()
                .when()
                .body(new LoginRequest(":XO:", ":XO:"))
                .contentType(ContentType.JSON)
                .post("/api/auth0/login")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }
    @Test
    public void AUTH0when_incorrect_password_should_not_login_with_status_code_409_CONFLICT(){
        given()
                .when()
                .body(new LoginRequest("admin", "wrongPassword"))
                .contentType(ContentType.JSON)
                .post("api/auth0/login")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void AUTH0when_incorrect_username_should_not_login_with_status_code_409_CONFLICT(){
        given()
                .when()
                .body(new LoginRequest(":O:O", "admin"))
                .contentType(ContentType.JSON)
                .post("api/auth0/login")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }
    @Test
    public void AUTHO_when_correct_credentials_should_give_access_to_readiness_endpoint(){
        var response = given()
                .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/auth0/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
