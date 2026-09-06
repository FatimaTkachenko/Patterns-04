package ru.netology.ibank.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement heading = $("h2");

    public void verifyDashboardIsVisible() {
        heading.shouldBe(visible)
            .shouldHave(text("Личный кабинет"));
    }
}
