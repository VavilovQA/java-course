package demoqaApiTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * @author Arsentiy Vavilov
 */
public class TestBase {

    public static String login = "Ars";
    public static String password = "Vavilov27!";


    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }
}
