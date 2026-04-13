package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author Arsentiy Vavilov
 */
public class ReqresTests {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test
    void getTest() {

        given().log().all().get("/get").then().log().all().statusCode(200).body("origin",
                is("95.24.96.212"));
    }

    @Test
    void getBearerTest() {

        given().header("Authorization", "Bearer ars123").log().all().get("/bearer").then().log()
                .all().statusCode(200).body("authenticated", is(true));
    }

    @Test
    void postCodeTest() {

        int codes = 500;

        given().pathParam("codes", codes).log().all().post("/status/{codes}").then().log().all()
                .statusCode(500);
    }

    @Test
    void getRandomCodeTest() {

        int[] codes = { 100, 200, 400, 500 };

        Random random = new Random();
        int randomCode = codes[random.nextInt(codes.length)];

        given().pathParam("codes", randomCode).log().all().post("/status/{codes}").then().log()
                .all().statusCode(not(300));
    }

    @Test
    void setAndCheckCookiesTest() {

        given().log().all().get("/cookies").then().log().all()
                .statusCode(200);
    }

}
