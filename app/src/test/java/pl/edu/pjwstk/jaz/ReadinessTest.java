package pl.edu.pjwstk.jaz;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonAlias;
import pl.edu.pjwstk.jaz.requests.LoginRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class ReadinessTest {

    @Test
    public void should_respond_to_readiness_request(){
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

    @Test
    public void should_not_respond_to_readiness_request() {
        // @formatter:off

        given()
                .when()
                   .get("/api/auth0/is-ready")
                .then()
                    .statusCode(HttpStatus.UNAUTHORIZED.value());
        // @formatter:on
    }
}
