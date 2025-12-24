package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import tests.steps.WebSteps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

/**
 * @author Arsentiy Vavilov
 */
public class IssueSearchTests {
    private static final String REPOSITORY = "VavilovQA/java-course";
    private static final String TEXT = "Try adjusting your search filters.";
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize="1920x1080";
//        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


    @Test
    void testSuccessfulSearchIssueWithListener() {
        SelenideLogger.addListener("allure",new AllureSelenide());
        open("https://github.com/");
        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();
        $(By.linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("Try adjusting your")).shouldHave(text(TEXT));
        takeScreenshot();
    }

    @Test
    void testSuccessfulSearchIssueWithLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем текст", () -> {
            $(withText("Try adjusting your")).shouldHave(text(TEXT));
            takeScreenshot();
        });
    }

    @Test
    void testSuccessfulSearchIssueWithAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeText(TEXT);
        takeScreenshot();
    }
}
