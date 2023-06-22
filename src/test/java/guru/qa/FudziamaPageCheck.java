package guru.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.texts;

public class FudziamaPageCheck extends TestBase{



    @ValueSource (strings = {"Напитки", "Добавки"})
    @ParameterizedTest(name = "Отображение заголовка при переходе в меню {0}")
    void headerMenuValues(String value) {
        open(baseUrl);
        $$("a[data-menu-block]").findBy(text(value)).click();
        $(".title").shouldHave(text(value));
    }


    @CsvSource (value = {
            "Напитки | Компот",
            "Добавки | Палочки одноразовые",
            "Салаты | Салат Чукка"
    },
    delimiter = '|')

    @ParameterizedTest (name = "Отображение продукта {1} при переходе в меню {0}")
    void productsListValues(String menu, String products) {
        open(baseUrl);
        $$("a[data-menu-block]").findBy(text(menu)).click();
        $(".one_block_menu").shouldHave(text(products));
    }

    static Stream<Arguments> productValues() {
        return Stream.of(
                Arguments.of("Напитки", List.of("Компот Вишня Мята 1 л", "Компот Вишня Мята 0,5 л", "Компот Малина Мята 1 л",
                        "Компот Малина Мята 0,5 л", "Компот Вишня Малина Мята 1 л", "Компот Вишня Малина Мята 0,5 л",
                        "Вишневый Компот 1 л", "Вишневый Компот 0.5 л", "Смородиновый Компот 0.5 л", "Клюквенный Компот 0.5 л",
                        "Добрый Cola 1 л", "Добрый Cola 0,5л.")),
                Arguments.of("Добавки", List.of("Сырный соус Хайнц", "Палочки одноразовые", "Васаби 6 гр.", "Имбирь 20 гр.", "Соевый соус",
                        "Салфетка влажная", "Соус Соул 40 гр", "Соус Спайс 40 гр", "Соус Унаги 40 гр", "Соус Фирменный 40 гр",
                        "Соус Ореховый 30 гр", "Соус Мега Цезарь 40 гр"))
        );
    }
    @MethodSource
    @ParameterizedTest
    void productValues(String menu, List<String> products) {
        open(baseUrl);
        $$("a[data-menu-block]").findBy(text(menu)).click();
        $$(".text_1").should(texts(products));

 }

}
