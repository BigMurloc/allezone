package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.requests.LoginRequest;


import static io.restassured.RestAssured.given;

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
    public void when_incorrect_credentials_should_not_login_with_status_code_500_INTERNAL_SERVER_ERROR(){
        given()
            .when()
                .body(new LoginRequest(":XO:", ":XO:"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }




}
