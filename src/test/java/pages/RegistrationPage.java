package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

/**
 * @author Arsentiy Vavilov
 */
public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    CheckResultComponent checkResultComponent = new CheckResultComponent();

    private final SelenideElement

    firstName = $("#firstName"), lastName = $("#lastName"), userEmail = $("#userEmail"),
            genderWrapper = $(".custom-control-label"), userNumber = $("#userNumber"),
            calendarType = $("#dateOfBirthInput"), subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"), uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"), state = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"), city = $("#city"), submit = $("#submit"),
            practiceForm = $(".practice-form-wrapper");

    @Step("Открываем форму регистрации")
    public RegistrationPage openPage() {

        String endPoint = "/automation-practice-form";
        open(endPoint);
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    @Step("Вводим имя")
    public RegistrationPage setFirstName(String name) {

        firstName.setValue(name);
        return this;
    }

    @Step("Вводим фамилию")
    public RegistrationPage setLastName(String surname) {

        lastName.setValue(surname);
        return this;
    }

    @Step("Вводим почту")
    public RegistrationPage setUserEmail(String email) {

        userEmail.setValue(email);
        return this;
    }

    @Step("Выбираем пол")
    public RegistrationPage setGender() {

        genderWrapper.click();
        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationPage setUserPhoneNumber(String phoneNumber) {

        userNumber.setValue(phoneNumber);
        return this;
    }

    @Step("Выбираем дату рождения")
    public RegistrationPage setDateOfBirth(String month, String year) {

        calendarType.click();
        calendarComponent.setDate(month, year);
        return this;
    }

    @Step("Выбираем предметы")
    public RegistrationPage setSubjects(String subject) {

        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage setHobbies(String hobbie) {

        hobbiesWrapper.$(byText(hobbie)).click();
        return this;
    }

    @Step("Загружаем аватарку")
    public RegistrationPage setPicture(String picturePath) {

        uploadPicture.uploadFromClasspath(picturePath);
        return this;
    }

    @Step("Заполняем адрес")
    public RegistrationPage setAddress(String address) {

        currentAddress.setValue(address);
        return this;
    }

    @Step("Выбор штата и города")
    public RegistrationPage setStateAndCity(String stateName, String cityName) {

        state.click();
        stateCityWrapper.$(byText(stateName)).click();
        city.click();
        stateCityWrapper.$(byText(cityName)).click();
        return this;
    }

    @Step("Отправляем форму")
    public RegistrationPage clickSubmit() {

        submit.click();
        return this;
    }

    @Step("Проверяем, что данные правильно отправились")
    public RegistrationPage checkResult(String key, String value) {

        checkResultComponent.result(key, value);
        return this;
    }

    @Step("Проверяем, что на странице есть текст 'Student Registration Form'")
    public void checkTextOnPage() {

        practiceForm.shouldHave(text("Student Registration Form"));
    }
}
