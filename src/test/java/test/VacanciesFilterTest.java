package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class VacanciesFilterTest extends UITestBase {
    @BeforeAll
    public static void openSite() {
        open("https://people.sovcombank.ru/");
    }

    @Test
    public void vacanciesFilterTest() {
        initMainPage().clickVacanciesButton().selectCity("Moscow")
                .selectCompany()
                .checkVacanciesList();
    }
}
