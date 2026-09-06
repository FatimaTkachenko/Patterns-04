package ru.netology.ibank.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void setLogin(String login) {
        loginField.setValue(login);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public DashboardPage validLogin(String login, String password) {
        setLogin(login);
        setPassword(password);
        clickLoginButton();
        return new DashboardPage();
    }

    public void verifyErrorNotification() {
        errorNotification.shouldBe(visible)
            .shouldHave(text("Ошибка"));
    }

    public void verifyBlockedUserError() {
        errorNotification.shouldBe(visible)
            .shouldHave(text("Пользователь заблокирован"));
    }
}
