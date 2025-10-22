import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Arsentiy Vavilov
 */
public class RegistrationFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Arsentiy");1
        $("#lastName").setValue("Vavilov");
        $("#userEmail").setValue("example@mail.com");
        $(".custom-control-label").click();
        $("#userNumber").setValue("9533677638");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--027").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("front_test_picture.jpeg");
        $("#currentAddress").setValue("School Street 48");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaipur")).click();
        $("#submit").click();

        $x("//td[contains(text(),'Student Name')]/../td[2]").shouldHave(text("Arsentiy Vavilov"));
        $x("//td[contains(text(),'Student Email')]/../td[2]").shouldHave(text("example@mail.com"));
        $x("//td[contains(text(),'Gender')]/../td[2]").shouldHave(text("Male"));
        $x("//td[contains(text(),'Mobile')]/../td[2]").shouldHave(text("9533677638"));
        $x("//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(text("27 May,1996"));
        $x("//td[contains(text(),'Subjects')]/../td[2]").shouldHave(text("English"));
        $x("//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(text("Sports"));
        $x("//td[contains(text(),'Picture')]/../td[2]").shouldHave(text("front_test_picture.jpeg"));
        $x("//td[contains(text(),'Address')]/../td[2]").shouldHave(text("School Street 48"));
        $x("//td[contains(text(),'State and City')]/../td[2]").shouldHave(text("Rajasthan Jaipur"));
    }
}
