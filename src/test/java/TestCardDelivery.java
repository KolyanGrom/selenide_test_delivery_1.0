import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;


public class TestCardDelivery {

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
        LocalDate today = LocalDate.now();
    }

    @Test
    void sendFormWithValidCityNamePhoneNumberAndDefaultDate() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").click();
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void sendFormWithValidCityNamePhoneNumberAndSetDate() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("20.04.2024");
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void sendFormWithValidNamePhoneNumberSetDateAndEmptyCity() {


        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("20.04.2024");
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));

    }

    @Test
    void sendFormWithValidCityPhoneNumberSetDateAndEmptyName() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("20.04.2024");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));

    }

    @Test
    void sendFormWithValidCityNameSetDateAndEmptyPhoneNumber() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("20.04.2024");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));

    }

    @Test
    void sendFormWithValidCityNamePhoneNumberSetDateAndEmptyCheckBox() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("20.04.2024");
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void sendFormWithValidNamePhoneNumberAndDefaultDateChooseCity() {

        $("[placeholder=Город]").setValue("Ка");
        $("[placeholder='Город']").click();
        $(byText("Чебоксары")).click();
        $("[data-test-id='date']").click();
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void sendFormWithValidCityNamePhoneNumberAndChooseDate() {

        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue("18.04.2024");
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }
}