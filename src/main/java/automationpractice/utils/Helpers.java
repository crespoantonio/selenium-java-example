package automationpractice.utils;

import automationpractice.types.IUserInfo;
import com.github.javafaker.Faker;

public class Helpers {
    private Faker faker;

    public Helpers() {
        // Initialize Faker with default locale
        this.faker = new Faker();
    }

    public String getRandomName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomPassword() {
        return faker.internet().password();
    }

    public IUserInfo createsNewUser() {
        String name = getRandomName();
        String lastName = getRandomLastName();
        String email = name.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
        String password = getRandomPassword();

        return new IUserInfo(name, lastName, email, password);
    }
}