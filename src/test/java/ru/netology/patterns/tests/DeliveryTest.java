package ru.netology.patterns.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.UserInfo;
import ru.netology.patterns.generator.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "true"));
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.holdBrowserOpen = false;

        userInfo = DataGenerator.generateUserInfo();
        open("http://localhost:9999");
    }

    @Test
    void shouldReplanMeeting() {
        // Заполнение формы
        $("[data-test-id='city'] input").setValue(userInfo.getCity());
        $("[data-test-id='date'] input").doubleClick().setValue(userInfo.getDate());
        $("[data-test-id='name'] input").setValue(userInfo.getName());
        $("[data-test-id='phone'] input").setValue(userInfo.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.text("Запланировать")).click();

        // Проверка успешного бронирования
        $("[data-test-id='success-notification']").shouldBe(Condition.visible);

        // Новая дата
        UserInfo updatedUserInfo = DataGenerator.updateDate(userInfo, 5);

        // Закрываем уведомление
        $("[data-test-id='success-notification'] .icon-button").click();

        // Обновляем дату
        $("[data-test-id='date'] input").doubleClick().setValue(updatedUserInfo.getDate());
        $$("button").find(Condition.text("Запланировать")).click();

        // Проверка диалога перепланирования
        $("[data-test-id='replan-notification']").shouldBe(Condition.visible);
        $("[data-test-id='replan-notification'] button").click();

        // Проверка успешного перепланирования
        $("[data-test-id='success-notification']").shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text(updatedUserInfo.getDate()));
    }
}