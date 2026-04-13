package demoqaApiTests.steps;

import demoqaApiTests.TestBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

/**
 * @author Arsentiy Vavilov
 */
public class Steps extends TestBase {

    @Step("Авторизация на сайте demoqa")
    public Response login(String username, String password) {
        String authData = "{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}";

        return given()
                .log().all()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Step("Добавляем книгу в коллекцию")
    public Response addBook(String userId, String token, String isbn) {
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);

        return given()
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201)
                .extract().response();
    }

    @Step("Удаляем все книги из коллекции")
    public void deleteBooks(String userId, String token) {
        given()
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);
    }

}
