import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
/**
 * @author Arsentiy Vavilov
 */
class BestContributorToSelenideTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void bestContributorMustBeSolntsev() {
        open("selenide/selenide");
        $("div.Layout-sidebar").$(byText("Contributors")).closest("h2").sibling(0).$$("li").first()
                .hover();
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }
}
