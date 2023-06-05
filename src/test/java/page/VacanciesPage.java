package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VacanciesPage implements Checked<VacanciesPage> {
    private final static List<String> LABEL_NAMES = Arrays.asList("Город", "Кампания", "Специализауия", "Опыт работы", "Тип занятости");
    public static final String DIV_ID_MAIN_MENU = "div [id='main-menu']";
    public static final String SECTION_VACANCIES_FILTER = ".section-vacancies-filter";

    @FindBy(css = ".page-vacancies-container .header-block .container")
    private SelenideElement header;

    @FindBy(css = SECTION_VACANCIES_FILTER + " .label")
    private ElementsCollection filterLabels;

    @FindBy(css = SECTION_VACANCIES_FILTER + " .search-cities")
    private SelenideElement searchCitiesField;

    @FindBy(css = ".v-menu__content .v-list-item div")
    private ElementsCollection citiesItems;

    @Override
    public VacanciesPage checkOpen() {
        header.shouldBe(visible, exactText("Вакансии в Совкомбанке"));
        filterLabels.shouldHave(size(5)).containsAll(LABEL_NAMES);
        searchCitiesField.shouldBe(visible);
        return this;
    }

    public VacanciesPage selectCity(String cityName) {
        searchCitiesField.click();
        citiesItems.shouldHave(size(9));
        citiesItems.get(0).click();
        return this;
    }

    public VacanciesPage selectCompany() {
        $$(".section-vacancies-filter .v-input").get(1).shouldBe(enabled).click();
        $$(".v-menu__content .v-list-item").shouldHave(size(6)).get(3).shouldBe(enabled).click();
        $(DIV_ID_MAIN_MENU).click();
        return this;
    }

    public VacanciesPage checkVacanciesList() {
        ElementsCollection selectedVacancies = $$(".section-vacancies .block-vacancy");
        for (SelenideElement selectedVacancy : selectedVacancies) {
            selectedVacancy.shouldBe(visible, text("Совкомбанк Технологии"), or("span", text("Москва"), text("Вся Россия")));
        }
        return this;
    }
}
