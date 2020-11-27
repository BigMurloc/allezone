package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.requests.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class RegisterTest {

    @Test
    public void when_user_does_not_exist_add_user_with_status_code_200_OK(){
        given()
                .when()
                .body(new RegisterRequest("notInDB","WithSomePassword"))
                .contentType(ContentType.JSON)
                .post("/api/register")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void when_user_already_exists_throw_exception_with_status_code_409_CONFLICT(){
        given()
                .when()
                .body(new RegisterRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/register")
        .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

}
