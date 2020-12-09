package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class RegisterTest {

    @AfterClass
    public static void afterClass(){
        var admin = given()
                .when()
                    .body(new LoginRequest("admin", "admin"))
                    .contentType(ContentType.JSON)
                    .post("/api/login")
                .thenReturn();
        given()
                .cookies(admin.getCookies())
                .contentType(ContentType.JSON)
                .post("/api/deleteUser/notInDB");
    }

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
            .statusCode(HttpStatus.CONFLICT.value())
        .and()
            .content(equalTo("User already exists"));
    }

}
