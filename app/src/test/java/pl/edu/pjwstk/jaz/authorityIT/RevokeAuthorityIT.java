package pl.edu.pjwstk.jaz.authorityIT;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.controllers.requests.AuthorityRequest;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class RevokeAuthorityIT {

    private static Response admin;
    private static Response user;

    @BeforeClass
    public static void beforeClass(){
        //register
        given()
                .body(new RegisterRequest("userToBeRevoked", "userToBeRevoked"))
                .contentType(ContentType.JSON)
                .post("/api/register");

        given()
                .body(new RegisterRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        //login
        admin = given()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        user = given()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        //grant authority
        given()
                .cookies(admin.getCookies())
                .body(new AuthorityRequest("userToBeRevoked", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/grant-authority");
    }

    @AfterClass
    public static void afterClass(){
        given()
                .cookies(admin.getCookies())
                .post("/api/deleteUser/userToBeRevoked");
        given()
                .cookies(admin.getCookies())
                .post("/api/deleteUser/user");
    }

    @Test
    public void adminShouldRevokeDifferentUserAuthority(){
        given()
                .cookies(admin.getCookies())
                .body(new AuthorityRequest("userToBeRevoked", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/revoke-authority")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void userShouldNotRevokeDifferentUserAuthority(){
        given()
                .cookies(user.getCookies())
                .body(new AuthorityRequest("userToBeRevoked", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/revoke-authority")
        .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

}
