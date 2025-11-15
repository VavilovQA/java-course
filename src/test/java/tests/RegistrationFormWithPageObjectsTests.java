package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

/**
 * @author Arsentiy Vavilov
 */
class RegistrationFormWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage().setFirstName("Ars").setLastName("Vavilov")
                .setUserEmail("123@mail.com").setGender().setUserPhoneNumber("1234567890")
                .setDateOfBirth("May", "1996").setSubjects("English").setHobbies("Sports")
                .setPicture("front_test_picture.jpeg").setAddress("Ижевск, Школьная 48")
                .setStateAndCity("Rajasthan", "Jaipur").clickSubmit()

                .checkResult("Student Name", "Ars Vavilov")
                .checkResult("Student Email", "123@mail.com").checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890").checkResult("Date of Birth", "27 May,1996")
                .checkResult("Subjects", "English").checkResult("Hobbies", "Sports")
                .checkResult("Picture", "front_test_picture.jpeg")
                .checkResult("Address", "Ижевск, Школьная 48")
                .checkResult("State and City", "Rajasthan Jaipur");

    }

    @Test
    void successfulRegistrationWhithMinimalRequiredFields() {

        registrationPage.openPage().setFirstName("Oleg").setLastName("Mongol").setGender()
                .setUserPhoneNumber("9533677638").clickSubmit()

                .checkResult("Student Name", "Oleg Mongol").checkResult("Mobile", "9533677638");
    }

    @Test
    void negativeRegistrationWithoutFillingFields() {

        registrationPage.openPage().clickSubmit().checkTextOnPage();

    }

}
