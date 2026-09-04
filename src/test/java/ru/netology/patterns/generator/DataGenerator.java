package ru.netology.patterns.generator;

import com.github.javafaker.Faker;
import ru.netology.patterns.data.UserInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    public static UserInfo generateUserInfo() {
        String city = generateCity();
        String name = generateName();
        String phone = generatePhone();
        String date = generateDate(3);
        return new UserInfo(city, name, phone, date);
    }

    public static UserInfo updateDate(UserInfo originalUser, int daysToAdd) {
        return new UserInfo(
                originalUser.getCity(),
                originalUser.getName(),
                originalUser.getPhone(),
                generateDate(daysToAdd)
        );
    }

    private static String generateCity() {
        String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Новосибирск", "Екатеринбург", "Нижний Новгород"};
        return cities[ThreadLocalRandom.current().nextInt(cities.length)];
    }

    private static String generateName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generatePhone() {
        return "+7" + faker.number().digits(10);
    }

    private static String generateDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}