package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shiftDays) {
        return LocalDate.now().plusDays(shiftDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        // Для надежности используем "Москва"
        return "Москва";
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
        String date;
    }

    public static UserInfo generateValidUser() {
        return new UserInfo(
                generateCity(),
                generateName(),
                generatePhone(),
                generateDate(3)
        );
    }

    public static UserInfo generateUserWithNewDate() {
        return new UserInfo(
                generateCity(),
                generateName(),
                generatePhone(),
                generateDate(7)
        );
    }
}
