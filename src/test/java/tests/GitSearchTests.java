package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
/**
 * @author Arsentiy Vavilov
 */
class GitSearchTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void successfulSearchSelenideRepo() {
        open("");
        $(".search-input").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $("a[href='/selenide/selenide']").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }
}
