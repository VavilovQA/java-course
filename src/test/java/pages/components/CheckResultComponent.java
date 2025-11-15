package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

/**
 * @author Arsentiy Vavilov
 */
public class CheckResultComponent {

    private final SelenideElement table = $(".table-responsive");

    public void result(String key, String value) {

        table.$(byText(key)).parent().shouldHave(text(value));
    }
}
