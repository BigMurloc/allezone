package pl.edu.pjwstk.jaz.auction;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class getAuctionIT {

    @Test
    public void unauthenticated_user_should_have_access_200(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/auction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void unauthenticated_user_should_have_access_to_certain_auction_200(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("/api/auction/15")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
