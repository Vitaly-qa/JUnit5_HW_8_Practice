package ru.avtozp2015;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Tag("Simple")
@DisplayName("Тесты на поиск вещей")

public class SearchLineTest {

    @BeforeEach
    void setUp() {
        open("https://sudar.su");
    }

    @ValueSource(strings = {
            "Брюки",
            "Костюм",
            "Куртка"})

    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    @Tag("SMOKE")
    void successfulSearchTest(String searchQuery) {
        $("#input-searchbox").setValue(searchQuery).pressEnter();

    }

    @CsvFileSource(resources = "/test_data/searchResult.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточки должна быть карточка {0}")
    @Tag("WEB")
    void successfulSearchTest(String searchQuery, String expectedLink) {
        $("#input-searchbox").setValue(searchQuery).pressEnter()
                .shouldHave(text(expectedLink));

    }

    @MethodSource
    @ParameterizedTest(name = "Должен проверять ассортимент верхней одежды по их марке {0}")
    @Tag("Smoke")
    void findModelСlothes(СlothesName СlothesName, List<String> models) {

}

