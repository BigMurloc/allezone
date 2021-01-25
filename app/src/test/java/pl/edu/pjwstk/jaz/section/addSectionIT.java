package pl.edu.pjwstk.jaz.section;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.controllers.requests.SectionRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class addSectionIT {

    private static Response adminResponse;
    private static Response authenticatedUser;


    @BeforeClass
    public static void setUp(){
        adminResponse = given()
                .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        authenticatedUser = given()
                .when()
                .body(new LoginRequest("testUser", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    @AfterClass
    public static void tearDown(){
        given()
                .cookies(adminResponse.getCookies())
                .contentType(ContentType.JSON)
                .delete("/api/section/testSectionToBeDeleted");
    }

    @Test
    public void admin_should_be_able_to_create_section_200(){
        SectionRequest sectionRequest = new SectionRequest();
        sectionRequest.setName("testSectionToBeDeleted");
        given()
                .cookies(adminResponse.getCookies())
                .contentType(ContentType.JSON)
                .body(sectionRequest)
                .post("/api/section")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void unauthenticated_user_should_not_have_access_403(){
        SectionRequest sectionRequest = new SectionRequest();
        sectionRequest.setName("testSection");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(sectionRequest)
                .post("/api/section")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void authenticated_user_should_not_have_access_403(){
        SectionRequest sectionRequest = new SectionRequest();
        sectionRequest.setName("testSection");
        given()
                .cookies(authenticatedUser.getCookies())
                .contentType(ContentType.JSON)
                .body(sectionRequest)
                .post("/api/section")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void adding_section_that_already_exists_should_throw_exception_400(){
        SectionRequest sectionRequest = new SectionRequest();
        sectionRequest.setName("testSection");
        given()
                .cookies(adminResponse.getCookies())
                .contentType(ContentType.JSON)
                .body(sectionRequest)
                .post("/api/section")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .and()
                .content(equalTo("Section already exists!"));
    }






}
