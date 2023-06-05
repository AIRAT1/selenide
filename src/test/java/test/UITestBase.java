package test;

import page.PracticeFormPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class UITestBase {
    public static PracticeFormPage initPracticeFormPage() {
        return page(PracticeFormPage.class).checkOpen();
    }

    public static MainPage initMainPage() {
        return page(MainPage.class).checkOpen();
    }

}
