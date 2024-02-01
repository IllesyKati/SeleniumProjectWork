package hu.masterfield.pages;

import hu.masterfield.datatypes.Saving;
import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Megtakar�t�s t�pus� accountok l�trehoz�s��rt felel�s oszt�ly
 */

@Feature("Megtakar�t�s t�pus� accountok l�trehoz�s��rt felel�s oszt�ly")
public class CreateSavingsPage extends BasePage{

    protected static Logger logger = LogManager.getLogger(CreateSavingsPage.class);

    // Az oldalon tal�lhat� webelementek azonos�t�sa.
    // Ezek sz�ks�gesek a Savings accountok l�trehoz�s�hoz-

    // Savings radio button
    @FindBy(id=Consts.ACCOUNT_TYPES_SAVINGS)
    private WebElement radioSavings;

    // Money Market radio button
    @FindBy(id=Consts.ACCOUNT_TYPES_MONEY_MARKET)
    private WebElement radioMoneyMarket;

    // Individual radio button
    @FindBy(id=Consts.OWNERSHIP_TYPES_INDIVIDUAL)
    private WebElement radioIndividual;

    // Joint radio button
    @FindBy(id=Consts.OWNERSHIP_TYPES_JOINT)
    private WebElement radioJoint;

    // account name
    @FindBy(id="name")
    private WebElement textName;

    // opening balance
    @FindBy(id="openingBalance")
    private WebElement textOpeningBalance;

    // new Savings submit
    @FindBy(id="newSavingsSubmit")
    private WebElement buttonNewSavingsSubmit;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * �j Saving l�trehoz�sa
     *
     * @return ViewSavingsAccountsPage, ha siker�lt l�trehozni a Saving t�pus� objektumot
     */
    @Step("�j Saving l�trehoz�sa")
    public ViewSavingsAccountsPage createNewSaving(Saving saving) {
        logger.info("createNewSaving() called.");

        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_SAVINGS)) {
            // RadioButton eset�ben ez az ellen�rz�s nem sz�ks�ges.
            if (radioSavings.isSelected()) {
                // TO DO NOTHING
            } else {
                radioSavings.click();
            }
        }
        if (saving.getAccountTypes().equals(Consts.ACCOUNT_TYPES_MONEY_MARKET)) {
            radioMoneyMarket.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_INDIVIDUAL)) {
            radioIndividual.click();
        }
        if (saving.getOwnershipTypes().equals(Consts.OWNERSHIP_TYPES_JOINT)) {
            radioJoint.click();
        }

        //textName.sendKeys(saving.getAccountName());
        setTextbox(textName, "Account Name", saving.getAccountName());

        setTextbox(textOpeningBalance, "Opening Balance", saving.getOpeningBalance());

        takesScreenshot();

        buttonNewSavingsSubmit.click();

        return new ViewSavingsAccountsPage(driver);

    }
}