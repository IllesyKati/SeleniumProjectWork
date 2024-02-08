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

/**  A profil oldalon tal�lhat� elemek azonos�t�sa. Ezek sz�ks�gesek a profil adatok m�dos�t�s�hoz.
     */

public class MyProfilePage extends BasePage{

    protected static Logger logger = LogManager.getLogger(MyProfilePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    protected static RegistrationData registrationData = new RegistrationData();


    // megsz�l�t�s megad�sa
    @FindBy(id="title")
    private WebElement titleSelect;

    // keresztn�v megad�sa
    @FindBy(id="firstName")
    private WebElement firstnameInput;

    // vezet�kn�v megad�sa
    @FindBy(id="lastName")
    private WebElement lastnameInput;

    // otthoni telefonsz�m megad�sa
    @FindBy(id="homePhone")
    private WebElement homePhoneInput;

    // mobil telefonsz�m megad�sa
    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;

    // munkahelyi telefonsz�m megad�sa
    @FindBy(id="workPhone")
    private WebElement workPhoneInput;

    // c�m megad�sa
    @FindBy(id="address")
    private WebElement addressInput;

    // telep�l�s megad�sa
    @FindBy(id="locality")
    private WebElement localityInput;

    //r�gi� megad�sa
    @FindBy(id="region")
    private WebElement regionInput;

    // ir�ny�t�sz�m megad�sa
    @FindBy(id="postalCode")
    private WebElement postalCodeInput;

    // orsz�g megad�sa
    @FindBy(id="country")
    private WebElement countryInput;

    // Submit gomb megad�sa
    @FindBy(xpath ="//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    // Reset gomb megad�sa
    @FindBy(xpath = "//button[contains(text(),' Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//span[contains(text(), 'Profile Updated Successfully.')]")
    private WebElement successMessage;


    // Ellen�rzi hogy bet�lt�d�tt-e a profil oldal minden eleme
    @Step("ProfilePage bet�lt�d�s�nek ellen�rz�se.")
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


    @Step("Profil mez� adatok ellen�rz�se.")
    public void validateProfileData() {
        logger.info("validateProfileData called");
        assertEquals(globalTestData.getProperty(Consts.REG_FIRST_NAME), firstnameInput.getAttribute("value"));
        assertEquals(globalTestData.getProperty(Consts.REG_LAST_NAME), lastnameInput.getAttribute("value"));
        System.out.println(firstnameInput.getAttribute("value"));
        System.out.println(lastnameInput.getAttribute("lvalue"));


        takesScreenshot();
    }

    // Ellen�rzi hogy megjelent-e a sikeres m�dos�t�sr�l a felirat
    @Step("SuccessMessage bet�lt�d�s�nek ellen�rz�se.")
    public boolean successMessageIsLoaded() {
        boolean successMessageIsLoaded = isLoaded(successMessage);

        logger.trace("successMessageIsLoaded= " + successMessageIsLoaded);
        return successMessageIsLoaded;
    }

    // P�ld�nyos�tjuk a ModifyProfileData oszt�lyt hogy az adatok el�rhet�ek legyenek.
    ModifyProfileData modifyProfileData = new ModifyProfileData();

    @Step("Profil mez�kbe �r�s.")
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