package ru.netology.patterns.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.RegistrationDto;
import ru.netology.patterns.generator.ApiClient;
import ru.netology.patterns.generator.ApiGenerator;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    void shouldCreateActiveUser() {
        RegistrationDto user = ApiGenerator.generateActiveUser();
        ApiClient.registerUser(user);
    }

    @Test
    void shouldCreateBlockedUser() {
        RegistrationDto user = ApiGenerator.generateBlockedUser();
        ApiClient.registerUser(user);
    }

    @Test
    void shouldRewriteExistingUser() {
        RegistrationDto user = ApiGenerator.generateActiveUser();
        ApiClient.registerUser(user);
        
        user.setStatus("blocked");
        ApiClient.registerUser(user);
    }

    @Test
    void shouldHandleInvalidLogin() {
        RegistrationDto user = ApiGenerator.generateInvalidLoginUser();
        
        given()
                .baseUri("http://localhost")
                .port(9999)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }

    @Test
    void shouldHandleInvalidPassword() {
        RegistrationDto user = ApiGenerator.generateInvalidPasswordUser();
        
        given()
                .baseUri("http://localhost")
                .port(9999)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(400);
    }
}