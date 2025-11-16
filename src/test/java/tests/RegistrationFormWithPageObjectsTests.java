package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.TestData;

/**
 * @author Arsentiy Vavilov
 */
class RegistrationFormWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
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
    void negativeRegistrationWithoutFillingFields() {

        registrationPage.openPage().clickSubmit().checkTextOnPage();

    }
}
