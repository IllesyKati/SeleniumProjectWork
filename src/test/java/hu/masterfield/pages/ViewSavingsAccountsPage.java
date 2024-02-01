package hu.masterfield.pages;

import hu.masterfield.datatypes.Saving;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * A Savings t�pus� accountok megjelen�t�s�nek oszt�lya. Itt t�rt�nik az adatforr�sb�l l�trehozott
 * Saving t�pusok visszaellen�rz�se l�trehoz�s ut�n
 */

@Feature("Megtakar�t�s t�pus� accountok megtekint�se/ellen�rz�se")
public class ViewSavingsAccountsPage extends BasePage{

    protected static Logger logger = LogManager.getLogger(ViewSavingsAccountsPage.class);

    // Az oldalon tal�lhat� webelementek azonos�t�sa */

    // accountokhoz tartoz� k�rty�k (amik tartalmaznak minden inf�t az accountr�l)
    //@FindBy(xpath="//div[@id='firstRow']/div/div/form/div[@class='card-body']")
    //@FindBy(xpath="//div[@id='firstRow']//div[@class='card-body']") -> hosszabb t�von jobb, mint a fenti xpath
    //@FindBy(css="div#firstRow div.card-body")
    //@FindBy(css="div[id='firstRow'] div[class='card-body']")
    //@FindBy(css="div#firstRow > div > div > form > div.card-body")

    @FindBy(css="div#firstRow div.card-body")
    private List<WebElement> cards;

    // Page title
    @FindBy(xpath="//h1[text()='View Savings Accounts']")
    private WebElement pageTitle;

    public ViewSavingsAccountsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Megjelen� Savingek ellen�rz�se
     */
    @Step("Megjelen� Savingek ellen�rz�se")
    public void validatePage(Saving expectedSaving) {
        logger.info("validatePage() called");

        takesScreenshot();

        WebElement account = findAccount(expectedSaving.getAccountName());
        assertNotNull(account, "Account with accountName="
                + expectedSaving.getAccountName() + " is not found!");

        WebElement divCartBody = account.findElement(By.xpath("./.."));
        List<WebElement> cardDivs = divCartBody.findElements(By.cssSelector("div > div"));
        Saving actualSaving = getSavingFromCard(cardDivs);
        assertEquals(expectedSaving, actualSaving);
    }

    /**
     * A fel�leten tal�lhat� �sszes Saving k�rtya �talak�t�sa Java objektumm� (Saving)
     * @return Saving objektumokat tartalmaz� List-el
     */
    @Step("A fel�leten l�v� �sszes Saving �talak�t�sa Java objektumm�")
    public List<Saving> getAllSavings() {
        logger.info("getAllSavings() called.");

        takesScreenshot();

        List<Saving> savingList = new ArrayList<>();
        for (WebElement card : cards) {
            List<WebElement> cardDivs = card.findElements(By.cssSelector("div"));
            savingList.add(getSavingFromCard(cardDivs));
        }
        return savingList;

    }




    private WebElement findAccount(String accountName) {
        for (WebElement card : cards) {
//            String labelAccountName = card.findElement(By.xpath("//div[@class='h4 m-0' and @contenteditable='true']"))
//                    .getText();
            String labelAccountName = card.findElements(By.tagName("div")).get(0)
                    .getText();

            if (labelAccountName.equals(accountName)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Saving objektum l�trehoz�sa a k�rty�b�l
     * @param cardDivs k�rtya div elemei
     * @return Saving t�pus� objektum
     */
    private Saving getSavingFromCard(List<WebElement> cardDivs) {
        logger.info("getSavingFromCart() called.");

        String openingBalanceText = cardDivs.get(6).getText();
        Saving saving = new Saving(cardDivs.get(1).getText().substring(9),
                cardDivs.get(2).getText().substring(11),
                //cardDivs.get(2).getText().replace("Ownership: ", "")
                cardDivs.get(0).getText(),
                openingBalanceText.substring(10, openingBalanceText.lastIndexOf('.')));
        return saving;
    }

    /**
     * Ellen�rzi, hogy megjelentek-e az oldalon a megadott elemek.
     *
     * @return true az oldal bet�lt�d�tt, megjelentek az elemek �s kattinthat�ak
     */
    @Step("ViewSavingsAccounts oldal bet�lt�s�nek ellen�rz�se")
    public boolean isLoaded() {
        boolean isLoaded = isLoaded(pageTitle) && isCardsLoaded(cards);

        logger.info("Page title: " + pageTitle.getText());
        logger.trace("isLoaded=" + isLoaded);
        return isLoaded;
    }

    public boolean isCardsLoaded(List<WebElement> cards) {
        boolean returnValue = false;
        for (WebElement card : cards) {
            if (isLoaded(card)) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }
}







