package hu.masterfield.pages;

import hu.masterfield.datatypes.ModifyProfileData;
import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**  A profil oldalon található elemek azonosítása. Ezek szükségesek a profil adatok módosításához.
     */

public class MyProfilePage extends BasePage{

    protected static Logger logger = LogManager.getLogger(MyProfilePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    protected static RegistrationData registrationData = new RegistrationData();


    // megszólítás megadása
    @FindBy(id="title")
    private WebElement titleSelect;

    // keresztnév megadása
    @FindBy(id="firstName")
    private WebElement firstnameInput;

    // vezetéknév megadása
    @FindBy(id="lastName")
    private WebElement lastnameInput;

    // otthoni telefonszám megadása
    @FindBy(id="homePhone")
    private WebElement homePhoneInput;

    // mobil telefonszám megadása
    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;

    // munkahelyi telefonszám megadása
    @FindBy(id="workPhone")
    private WebElement workPhoneInput;

    // cím megadása
    @FindBy(id="address")
    private WebElement addressInput;

    // település megadása
    @FindBy(id="locality")
    private WebElement localityInput;

    //régió megadása
    @FindBy(id="region")
    private WebElement regionInput;

    // irányítószám megadása
    @FindBy(id="postalCode")
    private WebElement postalCodeInput;

    // ország megadása
    @FindBy(id="country")
    private WebElement countryInput;

    // Submit gomb megadása
    @FindBy(xpath ="//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    // Reset gomb megadása
    @FindBy(xpath = "//button[contains(text(),' Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//span[contains(text(), 'Profile Updated Successfully.')]")
    private WebElement successMessage;


    // Ellenõrzi hogy betõltõdött-e a profil oldal minden eleme
    @Step("ProfilePage betöltõdésének ellenõrzése.")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(titleSelect)
                && isLoaded(firstnameInput)
                && isLoaded(lastnameInput)
                && isLoaded(homePhoneInput)
                && isLoaded(mobilePhoneInput)
                && isLoaded(workPhoneInput)
                && isLoaded(addressInput)
                && isLoaded(localityInput)
                && isLoaded(regionInput)
                && isLoaded(postalCodeInput)
                && isLoaded(countryInput)
                && isLoaded(submitButton)
                && isLoaded(resetButton);

        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }


    @Step("Profil mezõ adatok ellenõrzése.")
    public void validateProfileData() {
        logger.info("validateProfileData called");
        assertEquals(globalTestData.getProperty(Consts.REG_FIRST_NAME), firstnameInput.getAttribute("value"));
        assertEquals(globalTestData.getProperty(Consts.REG_LAST_NAME), lastnameInput.getAttribute("value"));
        System.out.println(firstnameInput.getAttribute("value"));
        System.out.println(lastnameInput.getAttribute("lvalue"));


        takesScreenshot();
    }

    // Ellenõrzi hogy megjelent-e a sikeres módosításról a felirat
    @Step("SuccessMessage betöltõdésének ellenõrzése.")
    public boolean successMessageIsLoaded() {
        boolean successMessageIsLoaded = isLoaded(successMessage);

        logger.trace("successMessageIsLoaded= " + successMessageIsLoaded);
        return successMessageIsLoaded;
    }

    // Példányosítjuk a ModifyProfileData osztályt hogy az adatok elérhetõek legyenek.
    ModifyProfileData modifyProfileData = new ModifyProfileData();

    @Step("Profil mezõkbe írás.")
    public void modifyProfileData() {

        logger.info("Starting the data modification.");

        firstnameInput.clear();
        setTextbox(firstnameInput, "firstNameInput", modifyProfileData.getModFirstName());

        lastnameInput.clear();
        setTextbox(lastnameInput, "lastNameInput", modifyProfileData.getModLastName());

        takesScreenshot();

        logger.info("Clicking on the Submit button");
        submitButton.click();

    }


    public MyProfilePage(WebDriver driver) {
        super(driver);
    }
}