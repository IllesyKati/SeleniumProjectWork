package hu.masterfield.testcases;


import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.ViewSavingsAccountsPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *  TC10 - Accountok sikeres törlésének vizsgálata
 */
public class TC10_DeleteAccounts_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC10_DeleteAccounts_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    protected static RegistrationData registrationData = new RegistrationData();

    @Test
    @DisplayName("TC10_DeleteAccounts_Test")
    @Description("TC10 - Accountok sikeres törlésének vizsgálata")
    @Tag("TC10")
    @Tag("Delete")
    @Tag("Accounts")

    public void TC10_Delete_accounts(TestInfo testInfo) throws InterruptedException, IOException {
        Thread.sleep(5000);
        logger.info(testInfo + " Started.");

        //GDPR nyilatkozat elfogadása

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        assertTrue(gdprBannerPage.isLoaded());
        gdprBannerPage.acceptCookies();

        //Ellenõrzés, hogy a Login oldal minden eleme betöltõdött.

        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.isLoaded();

        // Érvényes, a rendszerben már regisztrált belépési adatok megadása

        HomePage homePage = loginPage.login(username, password);

        //Ellenõrzés, hogy a HomePage oldal minden eleme betöltõdött.

        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // Meglévõ accountok törlése.

        homePage.deleteData();

        homePage.isLoaded();

        // Ellenõrzés, a törlés után a "View Savings" oldalon megjelenõ, az accountok hiányára vonatkozó figyelmeztetésre.

        ViewSavingsAccountsPage viewSavingsAccountsPage = homePage.gotoViewSavingsPage();

        if (viewSavingsAccountsPage.isNoAccountsModalLoaded()){
            logger.info("TEST PASSED");
        }
        else {
            fail("TEST FAILED");
        }

        Thread.sleep(5000);


    }

}
