package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import pages.RegistrationPage;
import utils.TestData;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import java.util.Map;

/**
 * @author Arsentiy Vavilov
 */
public class RegistrationRemoteFormWithPageObjectsTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        //        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        //        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    @Tag("demoqa")
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setGender(testData.userGender)
                .setUserPhoneNumber(testData.userPhoneNumber)
                .setDateOfBirth(testData.calendarDay, testData.calendarMonth, testData.calendarYear)
                .setSubjects(testData.subject1, testData.subject2)
                .setHobbies(testData.userHobbies)
                .setPicture("img/" + testData.userPicture)
                .setAddress(testData.userAddress)
                .setStateAndCity(testData.userState, testData.userCity)
                .clickSubmit()

                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.userEmail)
                .checkResult("Gender", testData.userGender)
                .checkResult("Mobile", testData.userPhoneNumber)
                .checkResult("Date of Birth", testData.calendarDay + " " + testData.calendarMonth + "," + testData.calendarYear)
                .checkResult("Subjects", testData.subject1 + ", " + testData.subject2)
                .checkResult("Hobbies", testData.userHobbies)
                .checkResult("Picture", testData.userPicture)
                .checkResult("Address", testData.userAddress)
                .checkResult("State and City", testData.userState + " " + testData.userCity);

    }

    @Test
    @Tag("demoqa")
    void successfulRegistrationWhithMinimalRequiredFields() {

        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.userGender)
                .setUserPhoneNumber(testData.userPhoneNumber)
                .clickSubmit()

                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Mobile", testData.userPhoneNumber);
    }

    @Test
    @Tag("demoqa")
    void negativeRegistrationWithoutFillingFields() {

        registrationPage.openPage().clickSubmit().checkTextOnPage();

    }
}
