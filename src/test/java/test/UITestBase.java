package test;

import page.PracticeFormPage;

import static com.codeborne.selenide.Selenide.page;

public class UITestBase {
    public static PracticeFormPage init() {
        return page(PracticeFormPage.class).checkOpen();
    }
}
