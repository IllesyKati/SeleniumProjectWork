package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Megtakar�t�s t�pus� accountok l�trehoz�s��rt felel�s oszt�ly
 */

@Feature("Megtakar�t�s t�pus� accountok l�trehoz�s��rt felel�s oszt�ly")
public class CreateSavingsPage extends BasePage{

    // Az oldalon tal�lhat� webelementek azonos�t�sa.
    // Ezek sz�ks�gesek a Savings accountok l�trehoz�s�hoz-

    // Savings radio button
    @FindBy(id= Consts.ACCOUNT_TYPES_SAVINGS)
    private WebElement radioSavings;

    // Money Market radio button
    @FindBy(id=Consts.ACCOUNT_TYPES_MONEY_MARKET)
    private WebElement radioMoneyMarket;

    public CreateSavingsPage(WebDriver driver) {
        super(driver);
    }
}