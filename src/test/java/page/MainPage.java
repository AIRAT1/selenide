package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class MainPage implements Checked<MainPage> {
    public static final String DIV_ID_MAIN_MENU = "div [id='main-menu']";

    @FindBy(css = DIV_ID_MAIN_MENU + " button")
    private SelenideElement vacanciesButton;

    @FindBy(css = ".video-block")
    private SelenideElement videoBlock;

    @Override
    public MainPage checkOpen() {
        vacanciesButton.shouldBe(visible, enabled).shouldHave(exactText("Вакансии"));
        videoBlock.shouldBe(visible);
        return this;
    }

    public VacanciesPage clickVacanciesButton() {
        vacanciesButton.click();
        videoBlock.shouldNotBe(visible);
        return page(VacanciesPage.class).checkOpen();
    }
}
