package hu.masterfield.pages;

/* Bejelentkez�si k�perny� oszt�lya.
 */

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

@Feature("Bejelentkez�si k�perny� kezele�se")
public class LoginPage extends BasePage {

    protected static Logger logger = LogManager.getLogger(LoginPage.class);

    /*  Az oldalon tal�lhat� webelementek azonos�t�sa.
        Ezek sz�ks�gesek a bejelentkez�s megval�s�t�s�hoz �s regisztr�ci� elind�t�s�hoz.
     */

    @FindBy(id = "username")
    private WebElement usernameInput;       // bejelentkez�s -> felhaszn�l�n�v

    @FindBy(id = "password")
    private WebElement passwordInput;        // bejelentkez�s -> jelsz�

    @FindBy(id = "submit")
    private WebElement submitButton;        // bejelentkez�s gomb

    //    @FindBy(linkText = " Sign Up Here")
    @FindBy(xpath = "//a[text()=' Sign Up Here']")
    private WebElement registrationLink;    // regisztr�ci�s link

    @FindBy(xpath = "//span[text()='Registration Successful. Please Login.']")
    private WebElement registrationSuccessfulLabel; // sikeres regisztr�ci� visszajelz�se

    //@FindBy(xpath = "//p[text()='Az oldal s�tiket haszn�l']")
    @FindBy(id = "cc-nb-title")
    private WebElement cookieUsageMessage;      // cookie elem

    @FindBy(xpath = "//div[span[text()='Success'] and contains(.,'Logout completed.')]")
    private WebElement logoutCompletedMessage;  // sikeres kijelentkez�sr�l t�j�koztat�s

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox;      // eml�kezz r�m v�laszt�

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ellen�rzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal bet�lt�d�tt, megjelentek az elemek �s kattinthat�ak
     */
    @Step("Login oldal bet�lt�s�nek ellen�rz�se")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(usernameInput) && isLoaded(passwordInput)
                && isLoaded(submitButton) && isLoaded(registrationLink)
                && isLoaded(rememberMeCheckbox);
        logger.trace("isLoaded= " + isLoaded);
        return isLoaded;
    }

    /**
     * Ellen�rzi, hogy a sikeres regisztr�ci� ut�n megjelent-e az oldalon a megadott elem.
     *
     * @return trua az oldalon megjelent az elv�rt sz�veg
     */
    @Step("Regisztr�ci� sikeress�g�nek ellen�rz�se")
    public boolean registrationIsSuccessful() {
        logger.info("registrationIsSuccessful() called");
        boolean isRegistrationSuccessful = isLoaded(registrationSuccessfulLabel);
        logger.trace("registrationIsSuccess= " + isRegistrationSuccessful);

        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/register"));
        assertTrue(registrationSuccessfulLabel.getText().contains("Success"));
        assertEquals("Registration Successful. Please Login.", registrationSuccessfulLabel.getText());

        return isRegistrationSuccessful;
    }

    /**
     * Bejelentkez�st megval�s�t� f�ggv�ny
     *
     * @param username Digital Bank felhaszn�l�
     * @param password Digital Bank felhaszn�l� jelszava
     * @return a nyit� oldalt megval�s�t� HomePage objektum
     */

    @Step("Bejelentkez�s")
    public HomePage login(String username, String password) {
        logger.info("login() called with username= " + username + ", password= " + password + " .");

        setTextbox(usernameInput, "usernameInput", username);
        setTextbox(passwordInput, "passwordInput", password);

        takesScreenshot();

        logger.trace("submitButton.click()");
        submitButton.click();

        takesScreenshot();
        return new HomePage(driver);
    }

    /**
     * Regisztr�ci� ind�t�sa.
     */
    @Step("Regisztr�ci�")
    public void registrationStart() {
        logger.trace("registrationLink.click()");
        registrationLink.click();
    }

    /**
     *
     * @return true , abban az esetben, ha sikeres volt a kil�p�s
     */
    @Step("Logout ellen�rz�se")
    public boolean validateLogout() {
        boolean isLogoutSuccess = isLoaded(usernameInput) && isLoaded(passwordInput)
                && isLoaded(submitButton) && isLoaded(registrationLink)
                && isLoaded(rememberMeCheckbox) && isLoaded(logoutCompletedMessage);
        logger.info("isLogoutSuccess= " + isLogoutSuccess);
        return isLogoutSuccess;
    }

    /**
     * Cookiek ellen�rz�se
     * @return true, ha a "s�tik haszn�l az oldal" sz�veget tartalmaz� webelement l�that�ak az oldalon.
     */
    @Step("Cookiek ellen�rz�se")
    public boolean isCookieVisible(){
        boolean isCookieVisible = cookieUsageMessage.isDisplayed();
        logger.info("isCookieVisible= " + isCookieVisible);
        return isCookieVisible;
    }

}