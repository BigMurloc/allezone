package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class UserIT {

    private static Response admin;
    private static Response testUser;


    @BeforeClass
    public static void beforeClass(){
    admin =
            given()
            .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .thenReturn();
    given()
            .body(new RegisterRequest("testUser", "testUser"))
            .contentType(ContentType.JSON)
            .post("/api/register");
    given()
            .body(new RegisterRequest("testUserToBeDeleted", "1234"))
            .contentType(ContentType.JSON)
            .post("/api/register");
    testUser =
            given()
            .when()
                .body(new LoginRequest("testUser", "testUser"))
                .contentType(ContentType.JSON)
                .post("/api/login")
            .thenReturn();
    }

    @AfterClass
    public static void afterClass(){
        given()
            .cookies(admin.getCookies())
            .post("/api/deleteUser/testUser");
    }
// GET INFO
    @Test
    public void unauthorizedUserShouldNotHaveAccess(){
        given()
        .when()
            .post("/api/admin")
        .then()
            .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void adminShouldHaveAccessToDifferentUserInfo(){
        given()
            .cookies(admin.getCookies())
            .get("/api/testUser")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testUserShouldNotHaveAccessToDifferentUserInfo(){
        given()
            .cookies(testUser.getCookies())
            .get("/api/admin")
        .then()
            .statusCode(HttpStatus.FORBIDDEN.value());
    }

//    @Test
//    public void testUserShouldHaveAccessToHisInfo(){
//        given()
//            .cookies(testUser.getCookies())
//            .get("/api/testUser")
//        .then()
//            .statusCode(HttpStatus.OK.value());
//    }

//DELETE
    @Test
    public void testUserShouldNotDeleteOtherUser(){
        given()
            .cookies(testUser.getCookies())
            .post("/api/deleteUser/testUserToBeDeleted")
        .then()
            .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void adminShouldDeleteOtherUser(){
        given()
            .cookies(admin.getCookies())
            .post("/api/deleteUser/testUserToBeDeleted")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
}
