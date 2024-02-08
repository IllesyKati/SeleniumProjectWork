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
 * TC4 - "Savings" típusú számlák sorozatos létrehozása külsõ adatforrásból
 */

public class TC4_Datasource_Test extends BaseTest{

    protected static Logger logger = LogManager.getLogger(TC4_Datasource_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC4_Datasource")
    @Description("TC4 - \"Savings\" típusú számlák sorozatos létrehozása külsõ adatforrásból")
    @Tag("TC4")
    @Tag("Sorozatos")
    @Tag("Adatbevitel")
    @Tag("Adatforrás")
    public void TC4_Datasource(TestInfo testInfo) throws InterruptedException {

        Thread.sleep(5000);
        logger.info(testInfo.getDisplayName() + " started.");

        // CSV file betöltése
        List<Saving> savings = DataSource.loadSavings();

        // Cookiek törlése
        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);
        gdprPage.acceptCookies();

        // Login megvalósítása
        String emailAddress = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("login");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        HomePage homePage = loginPage.login(emailAddress, password);
        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // Végigmegyünk a CSV fileból betöltött Saving típusú elemeken és
        // mindegyikre létrehoznk az oladalon keresztûl 1-1 Savinget.
        // A végén visszaellenõrizzük, hogy a View Savings oldalon helyesen jellenk-e meg a Savingek.

        for (Saving saving : savings) {
            logger.trace(saving);

            CreateSavingsPage createSavingsPage = homePage.gotoNewSavingsPage();

            logger.info("Create new Saving.");

            ViewSavingsAccountsPage viewSavingsAccountsPage = createSavingsPage.createNewSaving(saving);

            viewSavingsAccountsPage.validatePage(saving);;
        }


    }
}