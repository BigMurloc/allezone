package pl.edu.pjwstk.jaz.authority;

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
public class GrantAuthorityTest {

    private static Response adminResponse;
    private static Response authenticatedUser;

    @BeforeClass
    public static void initialize(){
        adminResponse = given()
                .when()
                    .body(new LoginRequest("admin", "admin"))
                    .contentType(ContentType.JSON)
                    .post("/api/login")
                .thenReturn();
        given()
                .when()
                .body(new RegisterRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        given()
                .when()
                .body(new RegisterRequest("moderator", "1234"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        authenticatedUser = given()
                .when()
                    .body(new LoginRequest("user", "user"))
                    .contentType(ContentType.JSON)
                    .post("/api/login")
                .thenReturn();
    }

    @AfterClass
    public static void afterClass(){
        given()
                .cookies(adminResponse.getCookies())
                .contentType(ContentType.JSON)
                .post("/api/deleteUser/moderator");
        given()
                .cookies(adminResponse.getCookies())
                .contentType(ContentType.JSON)
                .post("/api/deleteUser/user");

    }
    @Test
    public void admin_user_should_have_access_to_grant_authority_with_status_code_200_OK(){
        given()
                .cookies(adminResponse.getCookies())
                .body(new AuthorityRequest("moderator", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/grant-authority")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void authenticated_user_should_not_have_access_to_grant_authority_with_status_code_200_OK(){
        given()
                .cookies(authenticatedUser.getCookies())
                .body(new AuthorityRequest("moderator", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/grant-authority")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void unauthenticated_user_should_not_have_access_to_grant_authority_with_status_code_200_OK(){
        given()
            .when()
                .body(new AuthorityRequest("moderator", "grant-authority"))
                .post("/api/grant-authority")
            .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void when_admin_grant_moderator_grantAuthority_then_moderator_should_have_access_to_with_status_code_200_OK(){
        given()
                .cookies(adminResponse.getCookies())
                .body(new AuthorityRequest("moderator", "grant-authority"))
                .contentType(ContentType.JSON)
                .post("/api/grant-authority");

        var moderatorResponse = given()
            .when()
                .body(new LoginRequest("moderator", "1234"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .thenReturn();

        given()
            .cookies(moderatorResponse.getCookies())
            .body(new AuthorityRequest("user", "grant-authority"))
            .contentType(ContentType.JSON)
            .post("/api/grant-authority")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

}
