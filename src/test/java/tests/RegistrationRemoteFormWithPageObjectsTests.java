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

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * @author Arsentiy Vavilov
 */
public class RegistrationRemoteFormWithPageObjectsTests extends TestBaseRemote{

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
