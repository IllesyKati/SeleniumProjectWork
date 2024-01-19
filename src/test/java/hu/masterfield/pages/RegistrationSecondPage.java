package hu.masterfield.pages;

import hu.masterfield.datatypes.RegistrationData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Regisztr�ci�s form m�sodik oldal�nak oszt�lya
 *
 */

@Feature("Regisztr�ci� - 2. oldal")
public class RegistrationSecondPage extends BasePage{
    // Az oldalon tal�lhat� webelementek azonos�t�sa, amelyekre sz�ks�g�nk van.

    protected static Logger logger = LogManager.getLogger(RegistrationSecondPage.class);
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

    // otthoni telefonsz�m megad�sa
    @FindBy(id="homePhone")
    private WebElement homePhoneInput;

    // mobil telefonsz�m megad�sa
    @FindBy(id="mobilePhone")
    private WebElement mobilePhoneInput;

    // munkahelyi telefonsz�m megad�sa
    @FindBy(id="workPhone")
    private WebElement workPhoneInput;

    // felt�telek elfogad�sa
    @FindBy(id="agree-terms")
    private WebElement agreeTermsCheckbox;

    // regisztr�ci� gomb
    // @FindBy(css="button[type='submit']")
    @FindBy(xpath="//button[@type='submit' and text()='Register']")
    private WebElement registerButton;

    public RegistrationSecondPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellen�rzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true, ha az oldal bet�lt�d�tt, megjelentek az elemek �s kattinthat�ak.
     */
    @Step("Regisztr�ci�s �rlap 2. oldal�nak bet�lt�s�nek ellen�rz�se")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(addressInput)
                && isLoaded(localityInput)
                && isLoaded(regionInput)
                && isLoaded(postalCodeInput)
                && isLoaded(countryInput)
                && isLoaded(homePhoneInput)
                && isLoaded(mobilePhoneInput)
                && isLoaded(workPhoneInput)
                && isLoaded(agreeTermsCheckbox)
                && isLoaded(registerButton);

        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * P�ld�nyos�tjuk a RegistrationData oszt�lyt, hogy az oldalon tal�lhat� input mez�ket
     * a GlobalTestData.properties fileban megadott adatokkal tudjuk kit�lteni.
     * �gy a regisztr�ci� 2. oldal�nak kit�lt�sekor nem kell felsorolni a sok bemen� param�tert.
     *
     */
    RegistrationData registrationData = new RegistrationData();

    /**
     * A regisztr�ci� m�sodik oldal�nak kit�lt�s�t val�s�tjuk meg.
     */
    @Step("A regisztr�ci� m�sodik oldal�nak kit�lt�se.")
    public LoginPage registrationSecondPage() {
        logger.info("registrationSecondPage() called.");

        setTextbox(addressInput,"addressInput"
                ,registrationData.getAddress());

        setTextbox(localityInput, "localityInput"
                ,registrationData.getLocality());

        setTextbox(regionInput, "regionInput"
                ,registrationData.getRegion());

        setTextbox(postalCodeInput, "postalCodeInput"
                ,registrationData.getPostalCode());

        setTextbox(countryInput, "countryInput"
                ,registrationData.getCountry());

        setTextbox(homePhoneInput, "homePhoneInput"
                ,registrationData.getHomePhone());

        setTextbox(mobilePhoneInput, "mobilePhoneInput"
                ,registrationData.getMobilePhone());

        setTextbox(workPhoneInput, "workPhoneInput"
                ,registrationData.getWorkPhone());

        logger.trace("agreeTermsCheckbox.click()");
        if (agreeTermsCheckbox.isSelected()) {
            // TO DO NOTHING!!
        } else {
            agreeTermsCheckbox.click();
        }

        takesScreenshot();

        logger.trace("registerButton.click()");
        registerButton.click();

        return new LoginPage(driver);
    }
}