package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormPage implements Checked<PracticeFormPage> {
    public static final String BASE = ".practice-form-wrapper";
    public static final String GENDER_WRAPPER = " [id='genterWrapper']"; // fixme wrong idName
    public static final String USER_NAME_WRAPPER = " [id='userName-wrapper']";
    public static final String USER_EMAIL_WRAPPER = " [id='userEmail-wrapper']";
    public static final String USER_NUMBER_WRAPPER = " [id='userNumber-wrapper']";
    public static final String DATE_OF_BIRTH_WRAPPER = " [id='dateOfBirth-wrapper']";
    public static final String HOBBIES_WRAPPER = " [id='hobbiesWrapper']";
    public static final String CURRENT_ADDRESS_WRAPPER = " [id='currentAddress-wrapper']";
    public static final String STATE_CITY_WRAPPER = " [id='stateCity-wrapper']";

    @FindBy(css = BASE + USER_NAME_WRAPPER)
    private SelenideElement userNameWrapper;

    @FindBy(css = BASE + USER_NAME_WRAPPER + " [id='userName-label']")
    private SelenideElement userNameLabel;

    @FindBy(css = BASE + USER_EMAIL_WRAPPER)
    private SelenideElement userEmailWrapper;

    @FindBy(css = BASE + USER_EMAIL_WRAPPER + " [id='userEmail-label']")
    private SelenideElement userEmailLabel;

    @FindBy(css = BASE + GENDER_WRAPPER)
    private SelenideElement genderWrapper;

    @FindBy(css = BASE + GENDER_WRAPPER + " input")
    private ElementsCollection genderRadioButtons;

    @FindBy(css = BASE + USER_NUMBER_WRAPPER)
    private SelenideElement userNumberWrapper;

    @FindBy(css = BASE + USER_NUMBER_WRAPPER + " [id='userNumber-label']")
    private SelenideElement userNumberLabel;

    @FindBy(css = BASE + DATE_OF_BIRTH_WRAPPER)
    private SelenideElement dateOfBirthWrapper;

    @FindBy(css = BASE + DATE_OF_BIRTH_WRAPPER + " [id='dateOfBirth-label']")
    private SelenideElement dateOfBirthLabel;// subjectsWrapper

    @FindBy(css = BASE + " [id='subjectsWrapper']")
    private SelenideElement subjectsWrapper;

    @FindBy(css = BASE + HOBBIES_WRAPPER)
    private SelenideElement hobbiesWrapper;

    @FindBy(css = BASE + HOBBIES_WRAPPER + " input")
    private ElementsCollection hobbiesWrapperInputs;

    @FindBy(css = BASE + " [id='subjects-label']")
    private ElementsCollection subjectsLabels;

    @FindBy(css = BASE + " input[id='uploadPicture']")
    private SelenideElement uploadPictureButton;

    @FindBy(css = BASE + CURRENT_ADDRESS_WRAPPER)
    private SelenideElement currentAddressWrapper;

    @FindBy(css = BASE + CURRENT_ADDRESS_WRAPPER + " [id='currentAddress-label']")
    private SelenideElement currentAddressLabel;

    @FindBy(css = BASE + STATE_CITY_WRAPPER)
    private SelenideElement stateCityWrapper;

    @FindBy(css = BASE + STATE_CITY_WRAPPER + " [id='stateCity-label']")
    private SelenideElement stateCityLabel;

    @FindBy(css = BASE + STATE_CITY_WRAPPER + " div[id='state']")
    private SelenideElement stateSelector;

    @FindBy(css = BASE + STATE_CITY_WRAPPER + " div[id='city']")
    private SelenideElement citySelector;

    @Override
    public PracticeFormPage checkOpen() {
        userNameLabel.shouldBe(visible, exactText("Name"));
        userEmailLabel.shouldBe(visible, exactText("Email"));
        genderWrapper.shouldBe(visible);
        genderRadioButtons.shouldHave(size(3));
        genderRadioButtons.get(0).shouldBe(enabled, exactValue("Male"));
        genderRadioButtons.get(1).shouldBe(enabled, exactValue("Female"));
        genderRadioButtons.get(2).shouldBe(enabled, exactValue("Other"));
        userNumberLabel.shouldBe(visible, exactText("Mobile(10 Digits)"));
        dateOfBirthLabel.shouldBe(visible, exactText("Date of Birth"));
        subjectsLabels.shouldHave(size(3));
        subjectsLabels.get(0).shouldBe(enabled, exactText("Subjects"));
        subjectsLabels.get(1).shouldBe(enabled, exactText("Hobbies"));
        subjectsLabels.get(2).shouldBe(enabled, exactText("Picture"));
        hobbiesWrapperInputs.shouldHave(size(3));
        uploadPictureButton.shouldBe(visible, enabled);
        currentAddressLabel.shouldBe(visible, exactText("Current Address"));
        stateCityLabel.shouldBe(visible, exactText("State and City"));
        stateSelector.shouldBe(visible);
        citySelector.shouldBe(visible);
        return this;
    }

    public PracticeFormPage fillForm(String firstName, String lastName, String email, String gender, long mobileNumber
            , String subjects, int hobbiesIndex, String currentAddress, String state, String city) {
        userNameWrapper.find("input[id='firstName']")
                .shouldBe(visible).shouldHave(attribute("placeholder", "First Name"))
                .setValue(firstName);
        userNameWrapper.find("input[id='lastName']")
                .shouldBe(visible).shouldHave(attribute("placeholder", "Last Name"))
                .setValue(lastName);
        userEmailWrapper.find("input[id='userEmail']")
                .shouldBe(visible).shouldHave(attribute("placeholder", "name@example.com"))
                .setValue(email);
        genderRadioButtons.findBy(value(gender)).shouldBe(enabled).click();
        userNumberWrapper.find("input[id='userNumber']")
                .shouldBe(visible).shouldHave(attribute("placeholder", "Mobile Number"))
                .setValue(String.valueOf(mobileNumber));
        dateOfBirthWrapper.find("input[id='dateOfBirthInput']")
                .shouldBe(visible)
                .sendKeys(Keys.ENTER);
//        hobbiesWrapperInputs.get(hobbiesIndex).find(".custom-control-input").shouldBe(enabled).click();
        uploadPictureButton.uploadFile(new File("src/test/resources/test.png"));
        currentAddressWrapper.find("textArea[id='currentAddress']")
                .shouldBe(visible).shouldHave(attribute("placeholder", "Current Address"))
                .setValue(currentAddress);
//        stateSelector.shouldBe(enabled).selectOptionByValue(state); // NCR
//        citySelector.shouldBe(enabled).selectOptionByValue(city); // Delhi
        subjectsWrapper.find("input[id='subjectsInput']")
                .shouldBe(visible)
                .setValue(subjects)
                .sendKeys(Keys.ENTER);
        $(BASE).shouldNotBe(visible);
        return this;
    }

}
