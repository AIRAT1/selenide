package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest extends UITestBase{
    private static final Random RANDOM = new Random();

    @BeforeAll
    public static void openSite() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void practiceFormTest() {
        initPracticeFormPage().fillForm("firstName", "lastName", "email" + Math.abs(RANDOM.nextInt()) + "@.com", "Male", 8901234567L,
                        "subject", 1, "address", "NCR", "Delhi");
    }

    @AfterAll()
    public static void closeDriver() {
        closeWebDriver();
    }
}
