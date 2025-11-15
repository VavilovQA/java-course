package tests;

import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;

/**
 * @author Arsentiy Vavilov
 */
public class TestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }
}
