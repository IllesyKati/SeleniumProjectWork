package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Regisztr�ci�s form els� oldal�nak oszt�lya
 *
 */

@Feature("Regisztr�ci� - 1. oldal")
public class RegistrationFirstPage extends BasePage{

    // Az oldalon tal�lhat� webelementek azonos�t�sa, amelyekre sz�ks�g�nk van.

    // megsz�l�t�s megad�sa
    @FindBy(id="title")
    private WebElement titleSelect;

    // keresztn�v megad�sa
    @FindBy(id="firstName")
    private WebElement firstnameInput;

    // vezet�kn�v megad�sa
    @FindBy(id="lastName")
    private WebElement lastnameInput;

    // f�rfi nem kiv�laszt�sa
    @FindBy(xpath="//input[@type='radio' and @name='gender' and @value='M']")
    private WebElement genderMaleRadio;

    // n�i nem kiv�lszt�sa
    @FindBy(css="input[type='radio'][name='gender'][value='F']")
    private WebElement genderFemaleRadio;

    // sz�let�si d�tum
    @FindBy(id="dob")
    private WebElement dateOfBirthInput;

    // tb sz�m
    @FindBy(id="ssn")
    private WebElement socialSecurityNumberInput;

    // email c�m
    @FindBy(id="emailAddress")
    private WebElement emailAddressInput;

    // jelsz� megad�sa
    @FindBy(id="password")
    private WebElement regPasswordInput;

    // jelsz� meger�s�t�se
    @FindBy(id="confirmPassword")
    private WebElement regConfirmPasswordInput;

    // tov�bb gomb
    @FindBy(xpath="//button[@type='submit' and text()='Next']")
    private WebElement nextPageButton;

    public RegistrationFirstPage(WebDriver driver) {
        super(driver);
    }

    /** Ellen�rizz�k, hogy megjelentek-e az oldalon az adott elemek.
     *
     * @return true , ha az oldal bet�lt�d�tt, megjelentek az elv�rt elemek �s
     * kattinthat�ak
     */
    @Step("Regisztr�ci�s oldal bet�lt�s�nek ellen�rz�se")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect)
                && isLoaded(firstnameInput)
                && isLoaded(lastnameInput)
                && isLoaded(genderMaleRadio)
                && isLoaded(genderFemaleRadio)
                && isLoaded((dateOfBirthInput))
                && isLoaded(socialSecurityNumberInput)
                && isLoaded(emailAddressInput)
                && isLoaded(regPasswordInput)
                && isLoaded(regConfirmPasswordInput)
                && isLoaded(nextPageButton);
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    /**
     * P�ld�nyos�tjuk a RegistrationData oszt�lyt, hogy az oldalon tal�lhat� input mez�ket
     * ki tudjuk t�lteni a globalTestData.properties fileb�l felolvasott tesztadatokkal.
     *
     */

    RegistrationData registrationData = new RegistrationData();

    /**
     * Regisztr�ci� els� oldal�t val�s�tjuk meg.
     */
    @Step("Regisztr�ci�s �rlap 1. oldal�nak kit�lt�se")
    public RegistrationSecondPage registrationFirstPage() {
        logger.info("registrationFirstPage() called.");

        logger.trace("titleSelect.select");
        Select selectTitle = new Select(titleSelect);
        selectTitle.selectByVisibleText(registrationData.getTitle());

        setTextbox(firstnameInput, "firstnameInput"
                , registrationData.getFirstName());

        setTextbox(lastnameInput, "lastnameInput"
                , registrationData.getLastName());

        if (registrationData.getGender().equals("M")) {
            if (genderMaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderMaleRadio.click() called.");
                genderMaleRadio.click();
            }
        }
        if (registrationData.getGender().equals("F")) {
            if (genderFemaleRadio.isSelected()) {
                // TO DO NOTHING
            } else {
                logger.trace("genderFemaleRadio.click() called.");
                genderFemaleRadio.click();
            }
        }

        setTextbox(dateOfBirthInput, "dateOfBirthInput"
                ,registrationData.getDateOfBirth());

        setTextbox(socialSecurityNumberInput, "socialSecurityNumberInput",
                registrationData.getSocialSecurityNumber());

        setTextbox(emailAddressInput, "emailAddressInput",
                registrationData.getEmailAddress());

        setTextbox(regPasswordInput, "regPasswordInput",
                registrationData.getPassword());

        setTextbox(regConfirmPasswordInput, "regConfirmPasswordInput",
                registrationData.getConfirmPassword());

        takesScreenshot();

        logger.trace("nextPageButton.click() called.");
        nextPageButton.click();

        return new RegistrationSecondPage(driver);
    }
}