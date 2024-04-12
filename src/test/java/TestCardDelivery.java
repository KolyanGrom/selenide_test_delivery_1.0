import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;
import static org.openqa.selenium.Keys.ESCAPE;


public class TestCardDelivery {
    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void sendFormWithValidCityNamePhoneNumberAndSetDate () {
        String planningDate = generateDate(12);
        $("[placeholder=Город]").setValue("Кра");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(".notification__content")

                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate),
                        Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void sendFormWithValidNamePhoneNumberAndDefaultDateChooseCity() {

        String planningDate = generateDate(3);
        $("[placeholder=Город]").setValue("Ка");
        $("[placeholder='Город']").click();
        $(byText("Чебоксары")).click();
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(".notification__content")

                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate),
                        Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void sendFormWithValidNamePhoneNumberSetDateAndEmptyCity() {

        String planningDate = generateDate(12);
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));

    }

    @Test
    void sendFormWithValidCityPhoneNumberSetDateAndEmptyName() {

        String planningDate = generateDate(12);
        $("[placeholder=Город]").setValue("Кра");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));

    }

    @Test
    void sendFormWithValidCityNameSetDateAndEmptyPhoneNumber() {

        String planningDate = generateDate(12);
        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[placeholder='Дата встречи']").sendKeys(ESCAPE);
        $("[name=name]").setValue("Василий Булкин");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(5));
    }

    @Test
    void sendFormWithValidCityNamePhoneNumberSetDateAndEmptyCheckBox() {

        String planningDate = generateDate(12);
        $("[placeholder=Город]").setValue("Красноярск");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[class=button__text]").click();
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных"))
                .shouldBe(visible, Duration.ofSeconds(5));
    }
    
    @Test
    void sendFormWithValidCityNamePhoneNumberAndInvalidDate() {
        String planningDate = generateDate(2);
        $("[placeholder=Город]").setValue("Кра");
        $(byText("Красноярск")).click();
        $("[data-test-id='date']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[data-test-id='date']").click();
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name=name]").setValue("Василий Булкин");
        $("[name=phone]").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Заказ на выбранную дату невозможен"))
                .shouldBe(visible, Duration.ofSeconds(5));
    }
}