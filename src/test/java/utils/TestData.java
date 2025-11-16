package utils;
import com.github.javafaker.Faker;

/**
 * @author Arsentiy Vavilov
 */
public class TestData {
    public Faker faker = new Faker();
    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userGender = faker.options().option("Male", "Female", "Other"),
            userPhoneNumber = faker.number().digits(10),
            calendarDay = String.format("%s", faker.number().numberBetween(1, 28)),
            calendarMonth = faker.options().option("December", "January", "February",
                                                            "March","April","May","June",
                                                            "July", "August","September",
                                                            "October","November"),
            calendarYear = String.format("%s", faker.number().numberBetween(1970, 2030)),
            subject1 = faker.options().option("English", "Maths", "Chemistry"),
            subject2 = faker.options().option("Computer Science", "Commerce", "Economics"),
            userHobbies = faker.options().option("Sports", "Reading", "Music"),
            userPicture = faker.options().option("front_test_picture.jpeg",
                                                        "cat.jpeg", "lion.jpeg"),
            userAddress = faker.address().fullAddress(),
            userState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            userCity = getRandomCity();

            public String getRandomCity() {
                if (userState.equals("NCR")) userCity = faker.options().option("Delhi", "Gurgaon", "Noida");
                if (userState.equals("Uttar Pradesh")) userCity = faker.options().option("Agra","Lucknow","Merrut");
                if (userState.equals("Haryana")) userCity = faker.options().option("Karnal","Panipat");
                if (userState.equals("Rajasthan")) userCity = faker.options().option("Jaipur","Jaiselmer");
                return userCity;
            }
}
