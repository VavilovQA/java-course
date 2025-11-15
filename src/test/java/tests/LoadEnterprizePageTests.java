package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
/**
 * @author Arsentiy Vavilov
 */
class LoadEnterprizePageTests {
    @Test
    void correctLoadEnterprizePage() {
        open("https://github.com/");
        $(withText("Solutions")).hover();
        $("a[href='https://github.com/enterprise']").click();
        $(".application-main ").shouldHave(text("The AI-powered developer platform"));
    }
}
