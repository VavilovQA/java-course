import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
/**
 * @author Arsentiy Vavilov
 */
class FindJunit5ExampleCodeTests {
    @Test
    void junit5ExampleCodeIsHere() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(byText("Show 4 more pages…")).click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $(byText("3. Using JUnit5 extend test class:")).shouldBe(visible);
    }
}
