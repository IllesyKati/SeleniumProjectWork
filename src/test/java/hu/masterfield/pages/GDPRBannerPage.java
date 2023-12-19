package hu.masterfield.pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A Cookiek �s GDPR elfogad�s��rt felel�s POM oszt�ly
 */

@Feature("Cookie-k elfogad�sa")
public class GDPRBannerPage extends BasePage {
    protected static Logger logger = LogManager.getLogger(GDPRBannerPage.class);

    // OK gomb
    @FindBy(xpath="//button[@class='cc-nb-okagree' and text()='OK']")
    private WebElement okButton;

    // Az oldal s�tiket haszn�l sz�veg
    // @FindBy(xpath = "//p[@class='cc-nb-title' and text()='Az oldal s�tiket haszn�l']")
    @FindBy(id = "cc-nb-title")
    private WebElement cookieUsageMessage;

    public GDPRBannerPage(WebDriver driver) {
        super(driver);
    }

    @Step("OK gomb megjelen�s�nek ellen�rz�se")
    public boolean isLoaded() {
        logger.info("OK button loaded...");
        return isLoaded(okButton);
    }

    @Step("Cookie-k elfogad�sa")
    public void acceptCookies() {
        okButton.click();
        logger.info("Ok button clicked...");
    }

    @Step("Cooke-k ablak megjeln�s�nek ellen�rz�se")
    public boolean isCookieMessageVisible() {
        logger.info("isCookieMessageVisible called...");
        boolean isCookieVisible = cookieUsageMessage.isDisplayed();

        logger.info("isCookieVisible: " + isCookieVisible);
        logger.info("Title: " + cookieUsageMessage.getText());

        return isCookieVisible;
    }


}