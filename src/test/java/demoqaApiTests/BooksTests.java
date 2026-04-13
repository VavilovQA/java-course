package demoqaApiTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;

import demoqaApiTests.steps.Steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * @author Arsentiy Vavilov
 */
@ExtendWith(LoginExtension.class)
public class BooksTests extends TestBase {
    Steps api = new Steps();

    @Test
    @WithLogin
    void addBookToCollection_withDelete1Book_Test(UserSession user) {

        String isbn = "9781449365035";

        api.addBook(user.getUserId(), user.getToken(), isbn);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", user.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", user.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", user.getToken()));

        open("/profile");
        $(".mr-2").shouldHave(text("Speaking JavaScript"));

        api.deleteBooks(user.getUserId(), user.getToken());
    }

}
