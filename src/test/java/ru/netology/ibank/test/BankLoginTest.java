package ru.netology.ibank.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.ibank.data.DataGenerator;
import ru.netology.ibank.data.UserInfo;
import ru.netology.ibank.page.DashboardPage;
import ru.netology.ibank.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class BankLoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
    }

    @Test
    @DisplayName("Успешный вход с валидным активным пользователем")
    void shouldLoginWithValidActiveUser() {
        UserInfo user = DataGenerator.generateActiveUser();
        DashboardPage dashboard = loginPage.validLogin(user.getLogin(), user.getPassword());
        dashboard.verifyDashboardIsVisible();
    }

    @Test
    @DisplayName("Ошибка при входе заблокированного пользователя")
    void shouldShowErrorForBlockedUser() {
        UserInfo user = DataGenerator.generateBlockedUser();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.verifyBlockedUserError();
    }

    @Test
    @DisplayName("Ошибка при неверном логине")
    void shouldShowErrorWithInvalidLogin() {
        UserInfo user = DataGenerator.generateActiveUser();
        loginPage.setLogin(DataGenerator.generateInvalidLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        loginPage.verifyErrorNotification();
    }

    @Test
    @DisplayName("Ошибка при неверном пароле")
    void shouldShowErrorWithInvalidPassword() {
        UserInfo user = DataGenerator.generateActiveUser();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(DataGenerator.generateInvalidPassword());
        loginPage.clickLoginButton();
        loginPage.verifyErrorNotification();
    }
}
