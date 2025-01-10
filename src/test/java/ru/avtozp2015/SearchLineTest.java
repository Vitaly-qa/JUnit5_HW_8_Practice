package ru.avtozp2015;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Tag("Simple")
@DisplayName("Тесты на поиск вещей")
public class SearchLineTest {

    @BeforeEach
    void setUp() {

        Configuration.browser = "chrome";
        Configuration.browserVersion = "131";
        Configuration.pageLoadStrategy = "eager";

    }

    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    @ValueSource(strings = {
            "Обувь",
            "Костюм",
            "Куртка"})
    @Tag("SMOKE")
    void successfulSearchNotEmptyTest(String searchQuery) {
        open("https://sudar.su");
        $("#input-searchbox").setValue(searchQuery).pressEnter();
        $$(".catalog_my_search").shouldHave(sizeGreaterThan(0));
    }

    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть карточка {1}")
    @CsvFileSource(resources = "/test_data/searchResult.csv", delimiter = '|')
    @Tag("WEB")
    void successfulSearchWithExpectedResultTest(String searchQuery, String expectedName) {
        open("https://www.ebay.com");
        $("#gh-ac").setValue(searchQuery).pressEnter();
        $("#srp-river-results").shouldHave(text(expectedName));

    }
}


