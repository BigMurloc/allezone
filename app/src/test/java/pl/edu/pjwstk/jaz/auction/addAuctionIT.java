package pl.edu.pjwstk.jaz.auction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONNavi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.controllers.requests.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class addAuctionIT {

    private static Response adminResponse;
    private static Response authenticatedUser;
    private static AuctionRequest auctionRequest = new AuctionRequest();

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
        List<PhotoRequest> photos = new ArrayList<>();
        List<ParameterRequest> parameters = new ArrayList<>();
        auctionRequest.setTitle("test");
        auctionRequest.setDescription("test");
        auctionRequest.setPrice(24L);
        auctionRequest.setPhotos(photos);
        auctionRequest.setParameters(parameters);
        auctionRequest.setCategory("Pistolety Snajperskie");
    }

    @Test
    public void unauthenticated_user_should_have_access_403(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .post("/api/auction")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void authenticated_user_should_have_access_200(){
        given()
                .cookies(authenticatedUser.getCookies())
                .contentType(ContentType.JSON)
                .body(auctionRequest)
                .post("/api/auction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }



}
