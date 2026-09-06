package ru.netology.ibank.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private DataGenerator() {}

    private static final Faker faker = new Faker();

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setPort(9999)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .build();

    public static void registerUser(UserInfo user) {
        given()
            .spec(requestSpec)
            .body(user)
        .when()
            .post("/api/system/users")
        .then()
            .statusCode(200);
    }

    public static UserInfo generateActiveUser() {
        UserInfo user = new UserInfo(
            faker.name().username(),
            faker.internet().password(),
            "active"
        );
        registerUser(user);
        return user;
    }

    public static UserInfo generateBlockedUser() {
        UserInfo user = new UserInfo(
            faker.name().username(),
            faker.internet().password(),
            "blocked"
        );
        registerUser(user);
        return user;
    }

    public static String generateInvalidLogin() {
        return faker.name().username();
    }

    public static String generateInvalidPassword() {
        return faker.internet().password();
    }
}
