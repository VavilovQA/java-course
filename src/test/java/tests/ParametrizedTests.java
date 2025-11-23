package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import com.codeborne.selenide.Configuration;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * @author Arsentiy Vavilov
 */
class ParametrizedTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {"Музыка", "Видео", "Игра" })
    @ParameterizedTest(name = "При поиске {0} в заголовке страницы должно быть {0}")
    void searchWikiShouldBeNotEmpty(String searchValue) {
        open("https://ru.wikipedia.org/");
        $(".vector-search-box-input").setValue(searchValue).pressEnter();
        $(".mw-page-title-main").shouldHave(text(searchValue));

    }

    @CsvSource(value = {
            "Андрей Петров, https://ok.ru/profile/573324008149",
            "Михаил Петухов, https://ok.ru/profile/527385736984"
    })
    @ParameterizedTest(name = "При поиске {0} у первой карточки ссылка на профиль = {1}")
    void searchOkShouldBeNotEmpty(String searchValue, String profileLink) {
        open("https://ok.ru/");
        $(".input__prt1l").setValue(searchValue).pressEnter();
        $(".card-caption__02cy5 a").shouldHave(attribute("href", profileLink));
    }

    static Stream<Arguments> checkTransactionsForDifferentAccounts() {
        return Stream.of(
                Arguments.of("Harry Potter", "1006", "5000"),
                Arguments.of("Ron Weasly", "1008", "10000"),
                Arguments.of("Neville Longbottom", "1015", "30000")
        );
    }

    @MethodSource("checkTransactionsForDifferentAccounts")
    @ParameterizedTest
    void checkTransactionsForDifferentAccounts(String userName, String account, String amount) {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        $(".btn-lg").shouldBe(text("Customer Login")).click();
        $("#userSelect").click();
        $("#userSelect").selectOption(userName);
        $("[type='submit']").click();

        $("#accountSelect").selectOption(account);
        $("[ng-click='deposit()']").click();
        $("[type='number']").setValue(amount);
        $("[type='submit']").click();
        sleep(2000); //либо сайт, либо мой пк, но без слипа не успевает заполниться, хз
        $(".center").shouldHave(text(amount));
        $("[ng-click='transactions()']").click();

        $(".table-bordered > tbody").shouldHave(text(amount));


    }
}
