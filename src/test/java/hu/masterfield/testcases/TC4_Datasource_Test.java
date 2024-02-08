package hu.masterfield.testcases;

import hu.masterfield.datatypes.Saving;
import hu.masterfield.pages.*;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.DataSource;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC4 - "Savings" t�pus� sz�ml�k sorozatos l�trehoz�sa k�ls� adatforr�sb�l
 */

public class TC4_Datasource_Test extends BaseTest{

    protected static Logger logger = LogManager.getLogger(TC4_Datasource_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC4_Datasource")
    @Description("TC4 - \"Savings\" t�pus� sz�ml�k sorozatos l�trehoz�sa k�ls� adatforr�sb�l")
    @Tag("TC4")
    @Tag("Sorozatos")
    @Tag("Adatbevitel")
    @Tag("Adatforr�s")
    public void TC4_Datasource(TestInfo testInfo) throws InterruptedException {

        Thread.sleep(5000);
        logger.info(testInfo.getDisplayName() + " started.");

        // CSV file bet�lt�se
        List<Saving> savings = DataSource.loadSavings();

        // Cookiek t�rl�se
        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);
        gdprPage.acceptCookies();

        // Login megval�s�t�sa
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("login");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // V�gigmegy�nk a CSV fileb�l bet�lt�tt Saving t�pus� elemeken �s
        // mindegyikre l�trehoznk az oladalon kereszt�l 1-1 Savinget.
        // A v�g�n visszaellen�rizz�k, hogy a View Savings oldalon helyesen jellenk-e meg a Savingek.

        for (Saving saving : savings) {
            logger.trace(saving);

            CreateSavingsPage createSavingsPage = homePage.gotoNewSavingsPage();

            logger.info("Create new Saving.");

            ViewSavingsAccountsPage viewSavingsAccountsPage = createSavingsPage.createNewSaving(saving);

            viewSavingsAccountsPage.validatePage(saving);;
        }


    }
}